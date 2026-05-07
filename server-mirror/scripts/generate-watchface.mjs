import fs from "fs";
import path from "path";
import zlib from "zlib";
import { fileURLToPath } from "url";
import { execFileSync } from "child_process";

const WIDTH = 152;
const HEIGHT = 704;
const __dirname = path.dirname(fileURLToPath(import.meta.url));
const repoRoot = path.resolve(__dirname, "..", "..");

function parseCsv(value) {
  return String(value || "")
    .split(",")
    .map((part) => part.trim())
    .filter(Boolean);
}

function normalizeHex(value, fallback) {
  if (typeof value !== "string") return fallback;
  const match = value.trim().match(/^#?([0-9a-fA-F]{6})$/);
  return match ? `#${match[1].toUpperCase()}` : fallback;
}

function escapeXml(text) {
  return String(text)
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;")
    .replace(/'/g, "&apos;");
}

function slugify(text) {
  return String(text || "")
    .toLowerCase()
    .replace(/[^a-z0-9]+/g, "-")
    .replace(/^-+|-+$/g, "")
    .slice(0, 48) || "watchface";
}

function pickTheme(text) {
  const lower = text.toLowerCase();
  if (/(minimal|minimalist|clean|quiet)/.test(lower)) return "minimal";
  if (/(bold|strong|maximal|hero)/.test(lower)) return "bold";
  if (/(photo|image|portrait|picture)/.test(lower)) return "photo";
  if (/(retro|vintage|grain|analog)/.test(lower)) return "retro";
  if (/(tech|futur|neon|grid|data)/.test(lower)) return "tech";
  if (/(editorial|magazine|luxury|fashion)/.test(lower)) return "editorial";
  return "balanced";
}

function paletteForTheme(theme, requestedColors) {
  const cleaned = requestedColors.map((color) => normalizeHex(color, null)).filter(Boolean);
  if (cleaned.length >= 3) {
    return {
      background: cleaned[0],
      foreground: cleaned[1],
      accent: cleaned[2],
      muted: cleaned[3] || "#8B919B",
    };
  }

  const palettes = {
    minimal: { background: "#F4F5F7", foreground: "#111318", accent: "#5D6778", muted: "#8B919B" },
    bold: { background: "#0B0D10", foreground: "#F5F7FA", accent: "#FF4D4D", muted: "#7B8088" },
    photo: { background: "#111111", foreground: "#F2F2F2", accent: "#C0A76A", muted: "#8D8A83" },
    retro: { background: "#1A1510", foreground: "#F4E4C1", accent: "#D66A3A", muted: "#A38E75" },
    tech: { background: "#0A0F14", foreground: "#E5F8FF", accent: "#57D7FF", muted: "#6E93A3" },
    editorial: { background: "#0F1115", foreground: "#F4F1EA", accent: "#D0A85C", muted: "#85807A" },
    balanced: { background: "#111318", foreground: "#F5F7FA", accent: "#7E9BFF", muted: "#8C919A" },
  };

  return palettes[theme] || palettes.balanced;
}

function estimateLayout(theme, mustInclude) {
  const include = new Set(mustInclude.map((value) => String(value).toLowerCase()));
  const hasDate = include.size === 0 || include.has("date");
  const hasBattery = include.size === 0 || include.has("battery");

  return {
    time: {
      x: 12,
      y: theme === "photo" ? 58 : 72,
      width: 128,
      height: 108,
      align: "left",
      fontSize: theme === "bold" ? 42 : 38,
      weight: 700,
      letterSpacing: 0,
    },
    date: hasDate
      ? {
          x: 14,
          y: theme === "photo" ? 176 : 194,
          width: 124,
          height: 28,
          align: "left",
          fontSize: 13,
          weight: 500,
          letterSpacing: 0,
        }
      : null,
    battery: hasBattery
      ? {
          x: 14,
          y: HEIGHT - 94,
          width: 56,
          height: 14,
          align: "left",
          fontSize: 10,
          weight: 600,
          letterSpacing: 0,
        }
      : null,
    accentBand: {
      x: 12,
      y: theme === "minimal" ? 192 : 228,
      width: 10,
      height: theme === "minimal" ? 240 : 176,
    },
  };
}

function copyForLanguage(language) {
  if (typeof language !== "string") return { time: "12:45", date: "Mon 4 May" };
  if (language.startsWith("de")) return { time: "12:45", date: "Mo 4. Mai" };
  if (language.startsWith("ja")) return { time: "12:45", date: "5月4日 月" };
  if (language.startsWith("zh")) return { time: "12:45", date: "5月4日 周一" };
  return { time: "12:45", date: "Mon 4 May" };
}

function buildPrompt(request, theme, palette, layout) {
  const direction = request.direction || request.style || "balanced";
  const mustInclude = request.mustInclude.length ? request.mustInclude.join(", ") : "time, date";
  const avoid = request.avoid.length ? request.avoid.join(", ") : "clutter, overlap, low contrast";

  return [
    "Create a single wearable watchface design for a Sony FES watch.",
    `Canvas: ${WIDTH}x${HEIGHT}, vertical.`,
    `Theme: ${theme}.`,
    `Direction: ${direction}.`,
    `Language: ${request.language}.`,
    `Must include: ${mustInclude}.`,
    `Avoid: ${avoid}.`,
    `Palette: background ${palette.background}, foreground ${palette.foreground}, accent ${palette.accent}, muted ${palette.muted}.`,
    `Layout summary: time at y=${layout.time.y}, date at y=${layout.date ? layout.date.y : "none"}, battery at y=${layout.battery ? layout.battery.y : "none"}.`,
    "Return a structured JSON object with layers, typography, palette, and export-ready asset notes only.",
  ].join(" ");
}

function buildPreviewSvg(spec) {
  const { palette, layout, copy } = spec;
  const dateLine = layout.date
    ? `<text x="${layout.date.x}" y="${layout.date.y}" fill="${palette.muted}" font-size="${layout.date.fontSize}" font-family="Arial, sans-serif">${escapeXml(copy.date)}</text>`
    : "";
  const batteryLine = layout.battery
    ? `<rect x="${layout.battery.x}" y="${layout.battery.y - 10}" width="42" height="6" rx="3" fill="${palette.accent}" opacity="0.9"/>`
    : "";

  return `<?xml version="1.0" encoding="UTF-8"?>
<svg xmlns="http://www.w3.org/2000/svg" width="${WIDTH}" height="${HEIGHT}" viewBox="0 0 ${WIDTH} ${HEIGHT}">
  <rect width="${WIDTH}" height="${HEIGHT}" fill="${palette.background}"/>
  <rect x="${layout.accentBand.x}" y="${layout.accentBand.y}" width="${layout.accentBand.width}" height="${layout.accentBand.height}" fill="${palette.accent}" opacity="0.9"/>
  <text x="${layout.time.x}" y="${layout.time.y}" fill="${palette.foreground}" font-size="${layout.time.fontSize}" font-weight="${layout.time.weight}" font-family="Arial, sans-serif">${escapeXml(copy.time)}</text>
  ${dateLine}
  ${batteryLine}
  <text x="14" y="${HEIGHT - 20}" fill="${palette.muted}" font-size="9" font-family="Arial, sans-serif">watchface generator preview</text>
</svg>
`;
}

function readJson(filePath, fallback) {
  try {
    return JSON.parse(fs.readFileSync(filePath, "utf8"));
  } catch {
    return fallback;
  }
}

function writeJson(filePath, data) {
  fs.mkdirSync(path.dirname(filePath), { recursive: true });
  fs.writeFileSync(filePath, `${JSON.stringify(data, null, 2)}\n`);
}

function hexToGray(hex) {
  const m = String(hex || "").match(/^#?([0-9a-fA-F]{6})$/);
  if (!m) return 0;
  const v = parseInt(m[1], 16);
  const r = (v >> 16) & 0xff;
  const g = (v >> 8) & 0xff;
  const b = v & 0xff;
  return Math.round(0.2126 * r + 0.7152 * g + 0.0722 * b);
}

function encodeFlatGrayPng(width, height, gray) {
  const sig = Buffer.from([0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a]);

  const ihdr = Buffer.alloc(13);
  ihdr.writeUInt32BE(width, 0);
  ihdr.writeUInt32BE(height, 4);
  ihdr[8] = 8;     // bit depth
  ihdr[9] = 0;     // color type: grayscale
  ihdr[10] = 0;    // compression
  ihdr[11] = 0;    // filter
  ihdr[12] = 0;    // interlace

  const rowLen = 1 + width;
  const raw = Buffer.alloc(rowLen * height);
  for (let y = 0; y < height; y += 1) {
    raw[y * rowLen] = 0; // filter: None
    raw.fill(gray, y * rowLen + 1, y * rowLen + 1 + width);
  }
  const idatData = zlib.deflateSync(raw, { level: 9 });

  return Buffer.concat([sig, makeChunk("IHDR", ihdr), makeChunk("IDAT", idatData), makeChunk("IEND", Buffer.alloc(0))]);
}

function makeChunk(type, data) {
  const len = Buffer.alloc(4);
  len.writeUInt32BE(data.length, 0);
  const typeBuf = Buffer.from(type, "ascii");
  const crc = Buffer.alloc(4);
  crc.writeInt32BE(crc32(Buffer.concat([typeBuf, data])), 0);
  return Buffer.concat([len, typeBuf, data, crc]);
}

const CRC_TABLE = (() => {
  const t = new Int32Array(256);
  for (let n = 0; n < 256; n += 1) {
    let c = n;
    for (let k = 0; k < 8; k += 1) c = c & 1 ? 0xedb88320 ^ (c >>> 1) : c >>> 1;
    t[n] = c;
  }
  return t;
})();

function crc32(buf) {
  let c = ~0;
  for (let i = 0; i < buf.length; i += 1) c = CRC_TABLE[(c ^ buf[i]) & 0xff] ^ (c >>> 8);
  return ~c;
}

function buildInstallableSkin(outDir, slug, palette) {
  const bgGray = hexToGray(palette.background);
  const skinDir = path.join(outDir, "skin");
  fs.mkdirSync(skinDir, { recursive: true });

  const png = encodeFlatGrayPng(WIDTH, HEIGHT, bgGray);
  fs.writeFileSync(path.join(skinDir, "bg.png"), png);

  const skinConfig = {
    version: "1.0.0",
    revision: Date.now(),
    components: [
      {
        type: "background",
        image: "bg.png",
        layout: { size: [WIDTH, HEIGHT] },
      },
    ],
  };
  writeJson(path.join(skinDir, "config.json"), skinConfig);

  try {
    execFileSync("zip", ["-q", "-j", path.join(outDir, "skin.zip"), path.join(skinDir, "config.json"), path.join(skinDir, "bg.png")]);
  } catch (error) {
    console.warn("zip unavailable; skipping skin.zip:", error.message);
    return null;
  }
  return "skin.zip";
}

function main() {
  const request = {
    direction: process.env.WATCHFACE_DIRECTION || "",
    style: process.env.WATCHFACE_STYLE || "",
    mood: process.env.WATCHFACE_MOOD || "",
    language: process.env.WATCHFACE_LANGUAGE || "en-US",
    mustInclude: parseCsv(process.env.WATCHFACE_MUST_INCLUDE),
    avoid: parseCsv(process.env.WATCHFACE_AVOID),
    palette: parseCsv(process.env.WATCHFACE_PALETTE),
  };

  const directionText = [request.direction, request.style, request.mood].filter(Boolean).join(" ");
  const theme = pickTheme(directionText);
  const palette = paletteForTheme(theme, request.palette);
  const layout = estimateLayout(theme, request.mustInclude);
  const copy = copyForLanguage(request.language);
  const slugBase = process.env.WATCHFACE_SLUG || `${slugify(request.direction || request.style || "watchface")}-${Date.now().toString(36)}`;
  const outDir = path.join(repoRoot, "server-mirror", "public", "generated", "watchfaces", slugBase);

  const spec = {
    specVersion: "watchface-spec/v1",
    type: "watchface-design",
    request,
    canvas: {
      width: WIDTH,
      height: HEIGHT,
      orientation: "vertical",
    },
    theme,
    palette,
    layout,
    copy,
  };

  spec.generationPrompt = buildPrompt(request, theme, palette, layout);

  fs.mkdirSync(outDir, { recursive: true });
  writeJson(path.join(outDir, "request.json"), request);
  writeJson(path.join(outDir, "spec.json"), spec);
  fs.writeFileSync(path.join(outDir, "preview.svg"), buildPreviewSvg(spec));
  fs.writeFileSync(
    path.join(outDir, "README.md"),
    [
      `# ${slugBase}`,
      "",
      `Theme: ${theme}`,
      `Direction: ${request.direction || request.style || "balanced"}`,
      `Language: ${request.language}`,
      "",
      "Generated by the GitHub Actions watchface workflow.",
      "",
      `- [request.json](./request.json)`,
      `- [spec.json](./spec.json)`,
      `- [preview.svg](./preview.svg)`,
    ].join("\n")
  );

  try {
    execFileSync("zip", ["-q", "-r", "watchface-package.zip", "request.json", "spec.json", "preview.svg", "README.md"], {
      cwd: outDir,
    });
  } catch (error) {
    console.warn("zip unavailable or failed; skipping package zip:", error.message);
  }

  const skinArtifact = buildInstallableSkin(outDir, slugBase, palette);

  const generatedIndexPath = path.join(repoRoot, "server-mirror", "public", "generated", "index.json");
  const currentIndex = readJson(generatedIndexPath, { watchfaces: [] });
  currentIndex.watchfaces = [
    {
      slug: slugBase,
      path: `/generated/watchfaces/${slugBase}/`,
      spec: `/generated/watchfaces/${slugBase}/spec.json`,
      preview: `/generated/watchfaces/${slugBase}/preview.svg`,
      package: `/generated/watchfaces/${slugBase}/watchface-package.zip`,
      skin: skinArtifact ? `/generated/watchfaces/${slugBase}/skin.zip` : null,
      createdAt: new Date().toISOString(),
      request,
      theme,
    },
    ...(Array.isArray(currentIndex.watchfaces) ? currentIndex.watchfaces.filter((entry) => entry.slug !== slugBase) : []),
  ];
  writeJson(generatedIndexPath, currentIndex);

  console.log(JSON.stringify({ outDir, slug: slugBase, theme }, null, 2));
}

main();
