#!/usr/bin/env node
// Enrich skins-*.json with `artist`, `studio`, `tags[]` derived from
// existing text fields + creators-*.json. Writes back in place.
//
// Run:  node server-mirror/scripts/enrich-skins.mjs
//
// Manual corrections live in server-mirror/scripts/enrich-overrides.json
// shaped as { "<skinId>": { "artist": "...", "studio": "...", "tags": [...] } }
// and { "creators": { "<creatorId>": { "studio": "...", "tags": [...] } } }.

import { readFile, writeFile } from "node:fs/promises";
import { existsSync } from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const repoRoot = path.resolve(__dirname, "..", "..");

const TARGETS = [
  "ios-app/www/res/data/api/store",
  "raw-apk/assets/www/res/data/api/store",
  "server-mirror/public/api/store",
];
const LOCALES = ["en-us", "ja-jp", "zh-cn"];

const OVERRIDES_PATH = path.join(__dirname, "enrich-overrides.json");

// Tag dictionary: tag -> regex matchers (case-insensitive) on combined text.
const TAG_RULES = [
  ["minimal",     /\bminimal|simple|clean|reduce|reduktiv|シンプル|簡約|极简\b/i],
  ["analog",      /\banalog|hands?\b|時計|指針|表盘\b/i],
  ["digital",     /\bdigital|numeric|数字|デジタル\b/i],
  ["typography",  /\btypograph|letter|font|字|書|calligraph|タイポ\b/i],
  ["geometric",   /\bgeometr|grid|pattern|stripe|dots?|circles?|squares?|幾何|几何\b/i],
  ["organic",     /\borganic|nature|leaf|flower|wave|花|葉|自然\b/i],
  ["abstract",    /\babstract|art|paint|brush|抽象\b/i],
  ["photo",       /\bphoto|landscape|cityscape|skyline|写真\b/i],
  ["mono",        /\bmono|black|white|monochrome|gray|grey|モノ|黑白\b/i],
  ["bold",        /\bbold|strong|impact|heavy|大胆\b/i],
  ["fashion",     /\bfashion|style|chic|elegant|ファッション|时尚\b/i],
  ["seasonal",    /\bspring|summer|autumn|fall|winter|holiday|christmas|new year|春|夏|秋|冬\b/i],
  ["geo-japan",   /\bjapan|japanese|tokyo|kyoto|osaka|和|日本\b/i],
  ["festival",    /\bfestival|creative festival\b/i],
];

function loadOverrides() {
  if (!existsSync(OVERRIDES_PATH)) return { skins: {}, creators: {} };
  try {
    const raw = JSON.parse(
      // synchronous-ish read via require? we're ESM. Use top-level await.
      // (fall through, will be filled below)
      "{}"
    );
    return raw;
  } catch {
    return { skins: {}, creators: {} };
  }
}

async function readJson(p) {
  return JSON.parse(await readFile(p, "utf8"));
}

async function writeJson(p, obj) {
  // keep file compact (single line) like the originals, but newline at EOF
  await writeFile(p, JSON.stringify(obj) + "\n", "utf8");
}

function deriveTags(text) {
  const out = new Set();
  for (const [tag, re] of TAG_RULES) {
    if (re.test(text)) out.add(tag);
  }
  return [...out];
}

function deriveStudio(creator) {
  if (!creator) return null;
  const intro = (creator.introduction || "").trim();
  // "X is a ... design studio" / "... studio founded by ..."
  const m =
    intro.match(/([A-Z][\w&'\-. ]{1,40}?(?:design studio|studio|design office|inc\.|Inc\.))/);
  if (m) return m[1].trim();
  // Fallback: creator name
  return creator.name || null;
}

function pickArtist(creator) {
  if (!creator) return null;
  if (creator.name && creator.name.toLowerCase() !== "dummy") return creator.name;
  return null;
}

async function main() {
  let overrides = { skins: {}, creators: {} };
  if (existsSync(OVERRIDES_PATH)) {
    overrides = await readJson(OVERRIDES_PATH);
    overrides.skins ||= {};
    overrides.creators ||= {};
  }

  for (const locale of LOCALES) {
    // Load creators once per locale (any target — they should be in sync)
    const refDir = path.join(repoRoot, TARGETS[0]);
    const creatorsPath = path.join(refDir, `creators-${locale}.json`);
    if (!existsSync(creatorsPath)) {
      console.warn(`[skip] missing ${creatorsPath}`);
      continue;
    }
    const creatorsDoc = await readJson(creatorsPath);
    const creatorsById = new Map(
      (creatorsDoc.creators || []).map((c) => [c.id, c])
    );

    for (const targetRel of TARGETS) {
      const skinsPath = path.join(repoRoot, targetRel, `skins-${locale}.json`);
      if (!existsSync(skinsPath)) {
        console.warn(`[skip] missing ${skinsPath}`);
        continue;
      }
      const skinsDoc = await readJson(skinsPath);
      let enriched = 0;
      for (const skin of skinsDoc.skins || []) {
        const creator = creatorsById.get(skin.creatorId) || null;
        const ovr = overrides.skins[skin.id] || {};
        const cOvr = (creator && overrides.creators[creator.id]) || {};

        const artist = ovr.artist || pickArtist(creator) || "";
        const studio = ovr.studio || cOvr.studio || deriveStudio(creator) || artist || "";

        const text = [
          skin.name,
          skin.brief,
          skin.description,
          creator?.name,
          creator?.introduction,
        ]
          .filter(Boolean)
          .join(" \n ");
        const autoTags = deriveTags(text);
        const tags = Array.from(
          new Set([...(ovr.tags || []), ...(cOvr.tags || []), ...autoTags])
        );

        skin.artist = artist;
        skin.studio = studio;
        skin.tags = tags;
        enriched++;
      }
      await writeJson(skinsPath, skinsDoc);
      console.log(`[ok] ${targetRel}/skins-${locale}.json (${enriched} skins)`);
    }
  }
}

main().catch((e) => {
  console.error(e);
  process.exit(1);
});
