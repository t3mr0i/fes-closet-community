// Generate one canonical 152x704 style preview per style preset and write it
// to server-mirror/public/img/style-tiles/<style>.png. Run once (or whenever
// style presets change) via the GitHub Action.

import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";
import { execFileSync } from "child_process";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const repoRoot = path.resolve(__dirname, "..", "..");

const apiKey = process.env.OPENAI_API_KEY;
const model = process.env.OPENAI_MODEL || "gpt-image-2";
const size = process.env.OPENAI_SIZE || "1024x3072";
if (!apiKey) {
  console.error("OPENAI_API_KEY is required.");
  process.exit(1);
}

// Canonical concept used for every style — chosen to be vivid enough that the
// model commits to a recognizable style direction. Mood neutral.
const CANONICAL_CONCEPT = "abstract horizon with one focal accent";

const STYLES = [
  { id: "minimalist", desc: "minimalist, generous negative space, restrained palette, single thin accent" },
  { id: "editorial", desc: "editorial typographic sensibility, magazine cover feel, bold contrast, refined color blocks" },
  { id: "abstract", desc: "abstract gradient field, smooth color transitions, soft glow, no hard edges" },
  { id: "nature", desc: "photorealistic nature, soft golden-hour light, organic textures, atmospheric depth" },
  { id: "geometric", desc: "geometric pattern, repeating shapes, precise angles, tight grid alignment" },
  { id: "brutalist", desc: "brutalist mono-tone, raw concrete texture, harsh light, single saturated accent" },
  { id: "anime", desc: "anime illustrative style, painterly cel-shading, vivid sky, dramatic perspective" },
];

function buildPrompt(style) {
  return [
    `Design a watchface artwork for a vertical narrow display, exact pixel size 152x704 (aspect ratio 1:4.6).`,
    `Style: ${style.desc}.`,
    `Concept: ${CANONICAL_CONCEPT}.`,
    `Composition rules:`,
    `- Vertical reading order top to bottom.`,
    `- Leave the upper third visually quiet so a digital time readout remains legible against the artwork.`,
    `- High contrast, no text, no logos, no watermarks.`,
    `- Sharp at small render sizes.`,
    `- Edge-to-edge artwork, no white border, no padding.`,
    `Render only the artwork as a single PNG/JPEG image.`,
  ].join("\n");
}

async function callOpenAI(prompt) {
  const res = await fetch("https://api.openai.com/v1/images/generations", {
    method: "POST",
    headers: {
      "Authorization": `Bearer ${apiKey}`,
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ model, prompt, size, n: 1 }),
  });
  if (!res.ok) {
    const errText = await res.text();
    throw new Error(`OpenAI ${res.status}: ${errText.slice(0, 500)}`);
  }
  const data = await res.json();
  const b64 = data?.data?.[0]?.b64_json;
  if (!b64) throw new Error("OpenAI response had no image.");
  return Buffer.from(b64, "base64");
}

async function rasterize(sourceBuffer) {
  const isPng = sourceBuffer[0] === 0x89 && sourceBuffer[1] === 0x50;
  const ext = isPng ? "png" : "jpg";
  const tmpDir = fs.mkdtempSync(path.join(process.env.RUNNER_TEMP || "/tmp", "style-tile-"));
  const sourcePath = path.join(tmpDir, `source.${ext}`);
  const targetPath = path.join(tmpDir, "tile.png");
  fs.writeFileSync(sourcePath, sourceBuffer);
  const args = [sourcePath, "-resize", "152x704^", "-gravity", "center", "-extent", "152x704", "-strip", targetPath];
  try { execFileSync("magick", args, { stdio: "inherit" }); }
  catch { execFileSync("convert", args, { stdio: "inherit" }); }
  return fs.readFileSync(targetPath);
}

(async function main() {
  const outDir = path.join(repoRoot, "server-mirror/public/img/style-tiles");
  fs.mkdirSync(outDir, { recursive: true });

  for (const style of STYLES) {
    const target = path.join(outDir, `${style.id}.png`);
    if (fs.existsSync(target) && !process.env.FORCE) {
      console.log(`Skipping ${style.id} (exists, set FORCE=1 to regenerate)`);
      continue;
    }
    console.log(`Generating ${style.id}…`);
    try {
      const sourceBuffer = await callOpenAI(buildPrompt(style));
      const pngBuffer = await rasterize(sourceBuffer);
      fs.writeFileSync(target, pngBuffer);
      console.log(`  -> ${target} (${pngBuffer.length} bytes)`);
    } catch (err) {
      console.error(`  ${style.id} failed:`, err.message);
    }
  }
})();
