#!/usr/bin/env node
// Send each skin's storeImage to OpenAI vision and extract a closed set of
// visual tags. Writes results into enrich-overrides.json under
// `skins.<id>.tags` so the regular enrich-skins.mjs run can merge them.
//
// Cost-aware:
//   - low-detail vision input (~1 cent / skin on gpt-4o-mini)
//   - cached: skip skins that already have tags in overrides
//   - --limit N to dry-run a small batch first
//   - --force to re-tag everything
//
// Run:
//   OPENAI_API_KEY=sk-... node server-mirror/scripts/ai-tag-skins.mjs --limit 5
//   OPENAI_API_KEY=sk-... node server-mirror/scripts/ai-tag-skins.mjs

import { readFile, writeFile } from "node:fs/promises";
import { existsSync, readFileSync } from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const repoRoot = path.resolve(__dirname, "..", "..");
const OVERRIDES = path.join(__dirname, "enrich-overrides.json");
const SKINS_REF = path.join(
  repoRoot,
  "ios-app/www/res/data/api/store/skins-en-us.json"
);
const STORAGE_ROOT = path.join(repoRoot, "server-mirror/public/storage");

const argv = new Set(process.argv.slice(2));
const limitArg = process.argv.find((a) => a.startsWith("--limit="));
const LIMIT = limitArg ? parseInt(limitArg.split("=")[1], 10) : Infinity;
const FORCE = argv.has("--force");
const MODEL = process.env.OPENAI_VISION_MODEL || "gpt-4o-mini";
const apiKey =
  process.env.OPENAI_API_KEY ||
  // dev-only fallback: read from secrets.js if env not set
  (() => {
    const p = path.join(
      repoRoot,
      "ios-app/www/scripts/secrets.js"
    );
    if (!existsSync(p)) return null;
    const m = readFileSync(p, "utf8").match(
      /OPENAI_API_KEY:\s*"([^"]+)"/
    );
    return m && m[1] && !m[1].startsWith("__PLACE") ? m[1] : null;
  })();

if (!apiKey) {
  console.error("OPENAI_API_KEY not set and no live key in secrets.js");
  process.exit(1);
}

// Closed tag vocabulary — keep tags filterable, not free-form descriptions.
const ALLOWED_TAGS = [
  // tone
  "light", "dark", "high-contrast", "low-contrast",
  // density
  "minimal", "dense", "geometric", "organic",
  // motif
  "typography", "pattern", "illustration", "abstract", "photo",
  "stripes", "dots", "grid", "waves", "calligraphy", "linework",
  // direction / orientation
  "horizontal", "vertical", "centered",
];

const SYSTEM = `You tag watch face images with a closed vocabulary.
Vocabulary: ${ALLOWED_TAGS.join(", ")}.
Reply with ONLY a JSON object: {"tags":["...","..."]}
- Pick 3–6 most apt tags from the vocabulary above.
- Do not invent tags outside the vocabulary.
- Do not include explanation, only JSON.`;

function resolveStoreImage(skin) {
  const raw = skin.storeImage || "";
  // patterns: "<STORAGE_DIRECTORY>/<creator>/<skin>/storeImage.png"
  const m = raw.match(/<STORAGE_DIRECTORY>\/(.+)$/);
  if (!m) return null;
  return path.join(STORAGE_ROOT, m[1]);
}

async function tagOne(imgPath, skin) {
  const buf = await readFile(imgPath);
  const b64 = buf.toString("base64");
  const dataUrl = `data:image/png;base64,${b64}`;

  const body = {
    model: MODEL,
    messages: [
      { role: "system", content: SYSTEM },
      {
        role: "user",
        content: [
          {
            type: "text",
            text: `Skin name: ${skin.name || ""}\nBrief: ${skin.brief || ""}\nReturn JSON tags only.`,
          },
          { type: "image_url", image_url: { url: dataUrl, detail: "low" } },
        ],
      },
    ],
    response_format: { type: "json_object" },
    max_tokens: 80,
    temperature: 0,
  };

  const res = await fetch("https://api.openai.com/v1/chat/completions", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${apiKey}`,
    },
    body: JSON.stringify(body),
  });
  if (!res.ok) {
    const text = await res.text();
    throw new Error(`HTTP ${res.status}: ${text.slice(0, 200)}`);
  }
  const json = await res.json();
  const content = json.choices?.[0]?.message?.content || "{}";
  let parsed;
  try {
    parsed = JSON.parse(content);
  } catch {
    throw new Error(`bad JSON: ${content.slice(0, 200)}`);
  }
  const tags = Array.isArray(parsed.tags) ? parsed.tags : [];
  return tags
    .map((t) => String(t).toLowerCase().trim())
    .filter((t) => ALLOWED_TAGS.includes(t));
}

async function main() {
  const overrides = JSON.parse(await readFile(OVERRIDES, "utf8"));
  overrides.skins ||= {};
  const skinsDoc = JSON.parse(await readFile(SKINS_REF, "utf8"));

  const tasks = [];
  for (const skin of skinsDoc.skins || []) {
    const ovr = overrides.skins[skin.id] || {};
    if (!FORCE && Array.isArray(ovr.tags) && ovr.tags.length > 0) continue;
    const img = resolveStoreImage(skin);
    if (!img || !existsSync(img)) {
      console.warn(`[skip] ${skin.id} — image missing`);
      continue;
    }
    tasks.push({ skin, img });
    if (tasks.length >= LIMIT) break;
  }

  console.log(`tagging ${tasks.length} skins with ${MODEL}…`);
  let done = 0;
  for (const { skin, img } of tasks) {
    try {
      const tags = await tagOne(img, skin);
      if (tags.length === 0) {
        console.warn(`[empty] ${skin.id} ${skin.name}`);
      } else {
        overrides.skins[skin.id] = { ...(overrides.skins[skin.id] || {}), tags };
        console.log(`[ok] ${skin.name.padEnd(28)} → ${tags.join(", ")}`);
      }
    } catch (e) {
      console.error(`[err] ${skin.id} ${skin.name}: ${e.message}`);
    }
    done++;
    if (done % 10 === 0) {
      // checkpoint persist
      await writeFile(
        OVERRIDES,
        JSON.stringify(overrides, null, 2) + "\n",
        "utf8"
      );
    }
  }
  await writeFile(OVERRIDES, JSON.stringify(overrides, null, 2) + "\n", "utf8");
  console.log(`wrote overrides for ${tasks.length} skins`);
}

main().catch((e) => {
  console.error(e);
  process.exit(1);
});
