#!/usr/bin/env node
// Enrich skins-*.json with `artist`, `studio`, `tags[]` derived from
// existing text fields + creators-*.json. Writes back in place.
//
// Run:  node server-mirror/scripts/enrich-skins.mjs
//
// Manual corrections live in server-mirror/scripts/enrich-overrides.json:
// {
//   "skins":    { "<skinId>":    { "artist": "...", "studio": "...",
//                                   "tags": [...], "genre": "..." } },
//   "creators": { "<creatorId>": { "studio": "...", "country": "...",
//                                   "city": "...", "genre": "...",
//                                   "discipline": "...", "intro": "...",
//                                   "icon": "<STORAGE_DIRECTORY>/<id>/icon.png",
//                                   "url": "...", "tags": [...] } }
// }
// Per-skin overrides win over per-creator overrides.

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

// Tag dictionary: tag -> regex matchers (case-insensitive).
// Run only against skin name+brief+description (NOT creator bio) so we don't
// pick up boilerplate from creator introductions.
const TAG_RULES = [
  ["minimal",     /\b(minimal|simple|clean|reduce)\w*|シンプル|簡約|极简/i],
  ["analog",      /\b(analog|analogue|hands?\b)|時計|指針|表盘/i],
  ["digital",     /\b(digital|numeric|number)\w*|数字|デジタル/i],
  ["typography",  /\b(typograph|letter|font|calligraph)\w*|タイポ|文字/i],
  ["geometric",   /\b(geometr|grid|stripe|dots?|circles?|squares?|triangles?)\w*|幾何|几何/i],
  ["pattern",     /\bpatterns?\b|柄|模様/i],
  ["organic",     /\b(nature|leaf|flower|plant|wave|garden)\w*|花|葉|自然/i],
  ["abstract",    /\b(abstract|expression|surreal)\w*|抽象/i],
  ["photo",       /\b(photo|landscape|cityscape|skyline|portrait)\w*|写真/i],
  ["bold",        /\b(bold|strong|impact|heavy)\w*|大胆/i],
  ["seasonal",    /\b(spring|summer|autumn|fall|winter|holiday|christmas|new year)\b|春|夏|秋|冬|年末/i],
  ["festival",    /\b(festival|creative festival)\b/i],
  ["nature",      /\b(sky|sea|ocean|mountain|forest|rain|cloud|sun|moon|star)\w*|空|海|山|森/i],
  ["urban",       /\b(city|street|metro|urban|tokyo|new york)\w*|街|都市/i],
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

// Names of creators that are placeholders / not real — don't expose as filters.
const DUMMY_CREATOR_NAMES = new Set(["dummy", "test", ""]);

function isDummyCreator(creator) {
  if (!creator) return true;
  const n = (creator.name || "").trim().toLowerCase();
  if (DUMMY_CREATOR_NAMES.has(n)) return true;
  if ((creator.introduction || "").trim().toLowerCase() === "dummy") return true;
  return false;
}

function cleanName(s) {
  return (s || "").replace(/\s+/g, " ").trim();
}

function deriveStudio(creator) {
  // Prefer the proper noun. The creator name *is* the studio in 90% of cases;
  // we only rewrite when the creator field is itself unclean.
  if (!creator || isDummyCreator(creator)) return "";
  const name = cleanName(creator.name);
  if (name) return name;
  return "";
}

function pickArtist(creator) {
  if (!creator || isDummyCreator(creator)) return "";
  return cleanName(creator.name);
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

    // Patch creators-*.json with creator-level overrides (intro, icon, url).
    for (const targetRel of TARGETS) {
      const cPath = path.join(repoRoot, targetRel, `creators-${locale}.json`);
      if (!existsSync(cPath)) continue;
      const cDoc = await readJson(cPath);
      let cTouched = 0;
      for (const cr of cDoc.creators || []) {
        const o = overrides.creators[cr.id];
        if (!o) continue;
        if (o.intro) cr.introduction = o.intro;
        if (o.url) cr.url = o.url;
        if (o.icon) cr.icon = o.icon;
        cTouched++;
      }
      if (cTouched > 0) {
        await writeJson(cPath, cDoc);
        console.log(`[ok] ${targetRel}/creators-${locale}.json (${cTouched} patched)`);
      }
    }

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
        const genre = ovr.genre || cOvr.genre || "";
        const country = ovr.country || cOvr.country || "";
        const city = ovr.city || cOvr.city || "";

        // Tag matching: skin's own text only, to avoid creator-bio noise.
        const text = [skin.name, skin.brief, skin.description]
          .filter(Boolean)
          .join(" \n ");
        const autoTags = deriveTags(text);
        const tags = Array.from(
          new Set([...(ovr.tags || []), ...(cOvr.tags || []), ...autoTags])
        );

        skin.artist = artist;
        skin.studio = studio;
        skin.genre = genre;
        skin.country = country;
        skin.city = city;
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
