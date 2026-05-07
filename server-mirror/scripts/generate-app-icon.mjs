// Generates an iOS App Store icon set for "FES Community" using OpenAI gpt-image-2.
//
// Output layout (for a given slug):
//   ios-app/store-assets/icon/<slug>/
//     icon-master.png             (1024x1024, source from model, flattened on white)
//     icon-{20,29,40,50,57,58,60,72,76,80,87,100,114,120,144,152,167,180,1024}.png
//     AppIcon.appiconset/         (drop-in for Xcode Assets.xcassets)
//       Contents.json
//       <all sizes>.png
//     request.json
//
// ENV:
//   OPENAI_API_KEY (required)
//   ICON_CONCEPT   free-text concept (default: see below)
//   ICON_STYLE     style hint (default: "minimalist flat")
//   ICON_PALETTE   comma-separated hex colors (optional, hint to model)
//   ICON_SLUG      stable slug for output dir (default: timestamp-based)
//   OPENAI_MODEL   default "gpt-image-2"
//   OPENAI_SIZE    default "1024x1024"

import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";
import { execFileSync } from "child_process";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const repoRoot = path.resolve(__dirname, "..", "..");

const apiKey = process.env.OPENAI_API_KEY;
const model = process.env.OPENAI_MODEL || "gpt-image-2";
const size = process.env.OPENAI_SIZE || "1024x1024";
const concept = process.env.ICON_CONCEPT
  || "an abstract emblem combining a watch silhouette with a rising sun, suggesting community and continuity";
const style = process.env.ICON_STYLE || "minimalist flat";
const palette = (process.env.ICON_PALETTE || "").trim();
const requestedSlug = (process.env.ICON_SLUG || "").trim();

if (!apiKey) {
  console.error("OPENAI_API_KEY is required.");
  process.exit(1);
}

// Apple iOS app icon sizes (points * scale = pixels). We generate the union
// across iPhone, iPad, Spotlight, Settings, Notification, and the App Store 1024.
const APPLE_ICON_PX = [20, 29, 40, 50, 57, 58, 60, 72, 76, 80, 87, 100, 114, 120, 144, 152, 167, 180, 1024];

// Drop-in Xcode AppIcon set entries (idiom + size + scale → filename).
const APPICONSET = [
  { size: "20x20",     idiom: "iphone",         scale: "2x", px: 40 },
  { size: "20x20",     idiom: "iphone",         scale: "3x", px: 60 },
  { size: "29x29",     idiom: "iphone",         scale: "2x", px: 58 },
  { size: "29x29",     idiom: "iphone",         scale: "3x", px: 87 },
  { size: "40x40",     idiom: "iphone",         scale: "2x", px: 80 },
  { size: "40x40",     idiom: "iphone",         scale: "3x", px: 120 },
  { size: "60x60",     idiom: "iphone",         scale: "2x", px: 120 },
  { size: "60x60",     idiom: "iphone",         scale: "3x", px: 180 },
  { size: "20x20",     idiom: "ipad",           scale: "1x", px: 20 },
  { size: "20x20",     idiom: "ipad",           scale: "2x", px: 40 },
  { size: "29x29",     idiom: "ipad",           scale: "1x", px: 29 },
  { size: "29x29",     idiom: "ipad",           scale: "2x", px: 58 },
  { size: "40x40",     idiom: "ipad",           scale: "1x", px: 40 },
  { size: "40x40",     idiom: "ipad",           scale: "2x", px: 80 },
  { size: "76x76",     idiom: "ipad",           scale: "2x", px: 152 },
  { size: "83.5x83.5", idiom: "ipad",           scale: "2x", px: 167 },
  { size: "1024x1024", idiom: "ios-marketing",  scale: "1x", px: 1024 },
];

function slugify(text) {
  return String(text || "")
    .toLowerCase()
    .replace(/[^a-z0-9]+/g, "-")
    .replace(/^-+|-+$/g, "")
    .slice(0, 48) || "fes-community-icon";
}

const slug = requestedSlug ? slugify(requestedSlug) : `${slugify(concept)}-${Date.now().toString(36)}`;

function buildPrompt() {
  const lines = [
    `Design a square app icon, exact pixel size 1024x1024, for an iOS application called "FES Community".`,
    `Style: ${style}.`,
    `Concept: ${concept}.`,
    palette ? `Palette hint (use these colors): ${palette}.` : "",
    `Composition rules:`,
    `- Single centered emblem, simple silhouette, instantly recognizable at 60x60 px.`,
    `- Solid opaque background (no transparency, no checkerboard).`,
    `- No text, no letters, no numbers, no logos, no watermarks.`,
    `- No drop shadows, no gradients leaking off the canvas, no outer glow.`,
    `- Edge-to-edge artwork: fill the full 1024x1024 canvas (iOS will mask the corners).`,
    `- High contrast between subject and background.`,
    `- Flat illustration, vector-feel, sharp edges.`,
    `Render only the icon as a single PNG image.`,
  ].filter(Boolean);
  return lines.join("\n");
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

function magick(args) {
  try {
    execFileSync("magick", args, { stdio: "inherit" });
  } catch {
    execFileSync("convert", args, { stdio: "inherit" });
  }
}

function flattenToMaster(sourceBuffer, masterPath) {
  const tmp = fs.mkdtempSync(path.join(process.env.RUNNER_TEMP || "/tmp", "icon-"));
  const sourcePath = path.join(tmp, "source.png");
  fs.writeFileSync(sourcePath, sourceBuffer);
  // Apple rejects icons with alpha. Flatten on white, force 1024x1024 sRGB,
  // strip metadata, save as 8-bit RGB PNG (no transparency).
  magick([
    sourcePath,
    "-resize", "1024x1024^",
    "-gravity", "center",
    "-extent", "1024x1024",
    "-background", "white",
    "-alpha", "remove",
    "-alpha", "off",
    "-colorspace", "sRGB",
    "-strip",
    "-define", "png:color-type=2",
    masterPath,
  ]);
}

function resizeFromMaster(masterPath, targetPath, px) {
  magick([
    masterPath,
    "-resize", `${px}x${px}`,
    "-strip",
    "-define", "png:color-type=2",
    targetPath,
  ]);
}

function writeAppIconSet(outDir, masterPath) {
  const setDir = path.join(outDir, "AppIcon.appiconset");
  fs.mkdirSync(setDir, { recursive: true });

  const images = APPICONSET.map((entry) => {
    const filename = `Icon-${entry.idiom}-${entry.size.replace(/\./g, "_")}@${entry.scale}.png`;
    resizeFromMaster(masterPath, path.join(setDir, filename), entry.px);
    return { size: entry.size, idiom: entry.idiom, filename, scale: entry.scale };
  });

  const contents = {
    images,
    info: { version: 1, author: "fes-community-generator" },
  };
  fs.writeFileSync(path.join(setDir, "Contents.json"), JSON.stringify(contents, null, 2) + "\n");
}

function buildOutput(sourceBuffer) {
  const outDir = path.join(repoRoot, "ios-app/store-assets/icon", slug);
  fs.mkdirSync(outDir, { recursive: true });

  const masterPath = path.join(outDir, "icon-master.png");
  flattenToMaster(sourceBuffer, masterPath);

  for (const px of APPLE_ICON_PX) {
    if (px === 1024) continue; // master already at 1024
    resizeFromMaster(masterPath, path.join(outDir, `icon-${px}.png`), px);
  }
  // Also expose the master under the canonical icon-1024 name for clarity.
  fs.copyFileSync(masterPath, path.join(outDir, "icon-1024.png"));

  writeAppIconSet(outDir, masterPath);

  fs.writeFileSync(
    path.join(outDir, "request.json"),
    JSON.stringify(
      { concept, style, palette, slug, model, size, createdAt: new Date().toISOString() },
      null,
      2,
    ) + "\n",
  );

  return outDir;
}

(async function main() {
  console.log(`Generating app icon (slug=${slug}, model=${model})`);
  const sourceBuffer = await callOpenAI();
  console.log(`Got ${sourceBuffer.length} bytes from OpenAI, rasterizing icon set...`);
  const outDir = buildOutput(sourceBuffer);
  console.log(`Wrote icon set to ${path.relative(repoRoot, outDir)}`);
  console.log(`Drop-in for Xcode: ${path.relative(repoRoot, path.join(outDir, "AppIcon.appiconset"))}`);
})().catch((err) => {
  console.error(err);
  process.exit(1);
});
