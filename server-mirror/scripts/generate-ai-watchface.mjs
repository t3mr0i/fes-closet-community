import fs from "fs";
import path from "path";
import zlib from "zlib";
import { fileURLToPath } from "url";
import { execFileSync } from "child_process";

const WIDTH = 152;
const HEIGHT = 704;
const __dirname = path.dirname(fileURLToPath(import.meta.url));
const repoRoot = path.resolve(__dirname, "..", "..");

const concept = process.env.AI_CONCEPT || "minimal warm horizon";
const style = process.env.AI_STYLE || "minimalist";
const mood = process.env.AI_MOOD || "calm";
const displayName = (process.env.AI_NAME || "").trim() || concept;
const requestedSlug = (process.env.AI_SLUG || "").trim();
const apiKey = process.env.OPENAI_API_KEY;
const model = process.env.OPENAI_MODEL || "gpt-image-2";
const size = process.env.OPENAI_SIZE || "1024x3072";

if (!apiKey) {
  console.error("OPENAI_API_KEY is required.");
  process.exit(1);
}

function slugify(text) {
  return String(text || "")
    .toLowerCase()
    .replace(/[^a-z0-9]+/g, "-")
    .replace(/^-+|-+$/g, "")
    .slice(0, 48) || "ai-watchface";
}

const slug = requestedSlug ? slugify(requestedSlug) : `${slugify(displayName)}-${Date.now().toString(36)}`;

function buildPrompt() {
  return [
    `Design a watchface artwork for a vertical narrow display, exact pixel size 152x704 (aspect ratio 1:4.6).`,
    `Style: ${style}. Mood: ${mood}.`,
    `Concept: ${concept}.`,
    `Composition rules:`,
    `- Vertical reading order top to bottom.`,
    `- Leave the upper third visually quiet so a digital time readout remains legible against the artwork (the watch overlays digits separately).`,
    `- High contrast, no text, no logos, no watermarks.`,
    `- Sharp at small render sizes (final image will be displayed only ~30 mm wide on the wrist).`,
    `- Edge-to-edge artwork, no white border, no padding.`,
    `Render only the artwork as a single PNG/JPEG image.`,
  ].join("\n");
}

async function callOpenAI() {
  const res = await fetch("https://api.openai.com/v1/images/generations", {
    method: "POST",
    headers: {
      "Authorization": `Bearer ${apiKey}`,
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ model, prompt: buildPrompt(), size, n: 1 }),
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

// PNG decode/encode using sharp would be ideal, but we want zero deps.
// Instead, we ask sips (macOS) or netpbm (linux), and fall back to writing the raw image as bg.png if it's already a PNG.
// Simpler: write the model's output as bg-source.<ext>, then use ImageMagick (available in ubuntu-latest) for the resize+crop.

async function rasterize(sourceBuffer) {
  // Detect format via magic bytes
  const isPng = sourceBuffer[0] === 0x89 && sourceBuffer[1] === 0x50;
  const ext = isPng ? "png" : "jpg";
  const tmpDir = fs.mkdtempSync(path.join(process.env.RUNNER_TEMP || "/tmp", "ai-watchface-"));
  const sourcePath = path.join(tmpDir, `source.${ext}`);
  const targetPath = path.join(tmpDir, "bg.png");
  fs.writeFileSync(sourcePath, sourceBuffer);

  // ImageMagick is preinstalled on github actions ubuntu-latest. -resize "WxH^" + -gravity center -extent WxH = cover-fit crop.
  try {
    execFileSync("magick", [
      sourcePath,
      "-resize", `${WIDTH}x${HEIGHT}^`,
      "-gravity", "center",
      "-extent", `${WIDTH}x${HEIGHT}`,
      "-strip",
      targetPath,
    ], { stdio: "inherit" });
  } catch (e) {
    // older ImageMagick: no `magick` wrapper, fall back to convert
    execFileSync("convert", [
      sourcePath,
      "-resize", `${WIDTH}x${HEIGHT}^`,
      "-gravity", "center",
      "-extent", `${WIDTH}x${HEIGHT}`,
      "-strip",
      targetPath,
    ], { stdio: "inherit" });
  }
  return fs.readFileSync(targetPath);
}

function buildOutput(pngBuffer) {
  const outDir = path.join(repoRoot, "server-mirror/public/generated/ai-watchfaces", slug);
  fs.mkdirSync(outDir, { recursive: true });

  const skinDir = path.join(outDir, "skin");
  fs.mkdirSync(skinDir, { recursive: true });
  fs.writeFileSync(path.join(skinDir, "bg.png"), pngBuffer);

  const skinConfig = {
    version: "1.0.0",
    revision: Date.now(),
    components: [{ type: "background", image: "bg.png", layout: { size: [WIDTH, HEIGHT] } }],
  };
  fs.writeFileSync(path.join(skinDir, "config.json"), JSON.stringify(skinConfig, null, 2));

  // skin.zip
  const zipPath = path.join(outDir, "skin.zip");
  try {
    execFileSync("zip", ["-q", "-j", zipPath, path.join(skinDir, "config.json"), path.join(skinDir, "bg.png")], { stdio: "inherit" });
  } catch (err) {
    console.error("zip failed:", err.message);
    throw err;
  }

  // also keep the rasterized bg as preview at the watchface root
  fs.writeFileSync(path.join(outDir, "preview.png"), pngBuffer);

  // request.json so we can audit what was generated
  fs.writeFileSync(
    path.join(outDir, "request.json"),
    JSON.stringify({ concept, style, mood, displayName, slug, model, createdAt: new Date().toISOString() }, null, 2),
  );

  return {
    slug,
    skin: `/generated/ai-watchfaces/${slug}/skin.zip`,
    preview: `/generated/ai-watchfaces/${slug}/preview.png`,
    request: { concept, style, mood, displayName },
    creator: "OpenAI gpt-image-2 · GitHub Actions",
    createdAt: new Date().toISOString(),
  };
}

function appendToIndex(entry) {
  const indexPath = path.join(repoRoot, "server-mirror/public/generated/index.json");
  let index;
  try { index = JSON.parse(fs.readFileSync(indexPath, "utf8")); }
  catch { index = { watchfaces: [] }; }
  if (!Array.isArray(index.watchfaces)) index.watchfaces = [];
  index.watchfaces = index.watchfaces.filter((w) => w.slug !== entry.slug);
  index.watchfaces.unshift(entry);
  fs.writeFileSync(indexPath, JSON.stringify(index, null, 2) + "\n");
}

(async function main() {
  console.log(`Generating AI watchface "${displayName}" (slug=${slug}, model=${model})`);
  const sourceBuffer = await callOpenAI();
  console.log(`Got ${sourceBuffer.length} bytes from OpenAI, rasterizing to ${WIDTH}x${HEIGHT}...`);
  const pngBuffer = await rasterize(sourceBuffer);
  console.log(`Rasterized to ${pngBuffer.length} bytes`);
  const entry = buildOutput(pngBuffer);
  appendToIndex(entry);
  console.log(`Wrote ${entry.skin} and updated index.json`);
})().catch((err) => {
  console.error(err);
  process.exit(1);
});
