const WIDTH = 152;
const HEIGHT = 704;

function json(body, status = 200, headers = {}) {
  return new Response(JSON.stringify(body, null, 2), {
    status,
    headers: {
      "content-type": "application/json; charset=utf-8",
      "access-control-allow-origin": "*",
      "access-control-allow-methods": "GET, POST, OPTIONS",
      "access-control-allow-headers": "content-type, authorization",
      "cache-control": "no-store",
      ...headers,
    },
  });
}

function clamp(n, min, max) {
  return Math.max(min, Math.min(max, n));
}

function normalizeHex(value, fallback) {
  if (typeof value !== "string") return fallback;
  const match = value.trim().match(/^#?([0-9a-fA-F]{6})$/);
  return match ? `#${match[1].toUpperCase()}` : fallback;
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
  const cleaned = Array.isArray(requestedColors)
    ? requestedColors.map((color) => normalizeHex(color, null)).filter(Boolean)
    : [];

  if (cleaned.length >= 3) {
    return {
      background: cleaned[0],
      foreground: cleaned[1],
      accent: cleaned[2],
      muted: cleaned[3] || "#8B919B",
    };
  }

  const palettes = {
    minimal: {
      background: "#F4F5F7",
      foreground: "#111318",
      accent: "#5D6778",
      muted: "#8B919B",
    },
    bold: {
      background: "#0B0D10",
      foreground: "#F5F7FA",
      accent: "#FF4D4D",
      muted: "#7B8088",
    },
    photo: {
      background: "#111111",
      foreground: "#F2F2F2",
      accent: "#C0A76A",
      muted: "#8D8A83",
    },
    retro: {
      background: "#1A1510",
      foreground: "#F4E4C1",
      accent: "#D66A3A",
      muted: "#A38E75",
    },
    tech: {
      background: "#0A0F14",
      foreground: "#E5F8FF",
      accent: "#57D7FF",
      muted: "#6E93A3",
    },
    editorial: {
      background: "#0F1115",
      foreground: "#F4F1EA",
      accent: "#D0A85C",
      muted: "#85807A",
    },
    balanced: {
      background: "#111318",
      foreground: "#F5F7FA",
      accent: "#7E9BFF",
      muted: "#8C919A",
    },
  };

  return palettes[theme] || palettes.balanced;
}

function estimateLayout(theme, mustInclude) {
  const include = new Set(Array.isArray(mustInclude) ? mustInclude.map((v) => String(v).toLowerCase()) : []);
  const timeTop = theme === "photo" ? 58 : 72;
  const dateTop = timeTop + 122;
  const batteryTop = HEIGHT - 94;
  const hasBattery = include.size === 0 || include.has("battery");
  const hasDate = include.size === 0 || include.has("date");

  return {
    time: {
      x: 12,
      y: timeTop,
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
          y: dateTop,
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
          y: batteryTop,
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

function buildPrompt(request, theme, palette, layout) {
  const direction = request.direction || request.style || "balanced";
  const language = request.language || "en-US";
  const mustInclude = Array.isArray(request.mustInclude) && request.mustInclude.length ? request.mustInclude.join(", ") : "time, date";
  const avoid = Array.isArray(request.avoid) && request.avoid.length ? request.avoid.join(", ") : "clutter, overlap, low contrast";

  return [
    "Create a single wearable watchface design for a Sony FES watch.",
    `Canvas: ${WIDTH}x${HEIGHT}, vertical.`,
    `Theme: ${theme}.`,
    `Direction: ${direction}.`,
    `Language: ${language}.`,
    `Must include: ${mustInclude}.`,
    `Avoid: ${avoid}.`,
    `Palette: background ${palette.background}, foreground ${palette.foreground}, accent ${palette.accent}, muted ${palette.muted}.`,
    `Layout summary: time block at x=${layout.time.x}, y=${layout.time.y}; date block at y=${layout.date ? layout.date.y : "none"}; battery at y=${layout.battery ? layout.battery.y : "none"}.`,
    "Return a structured JSON object with layers, typography, palette, and export-ready asset notes only.",
  ].join(" ");
}

function buildPreviewSvg(spec) {
  const { palette, layout, copy } = spec;
  const dateY = layout.date ? layout.date.y : 0;
  const batteryY = layout.battery ? layout.battery.y : 0;
  const dateLine = layout.date ? `<text x="${layout.date.x}" y="${dateY}" fill="${palette.muted}" font-size="${layout.date.fontSize}" font-family="Arial, sans-serif">${escapeXml(copy.date)}</text>` : "";
  const batteryLine = layout.battery ? `<rect x="${layout.battery.x}" y="${batteryY - 10}" width="42" height="6" rx="3" fill="${palette.accent}" opacity="0.9"/>` : "";

  return `data:image/svg+xml;base64,${btoa(unescape(encodeURIComponent(`
    <svg xmlns="http://www.w3.org/2000/svg" width="${WIDTH}" height="${HEIGHT}" viewBox="0 0 ${WIDTH} ${HEIGHT}">
      <rect width="${WIDTH}" height="${HEIGHT}" fill="${palette.background}"/>
      <rect x="${layout.accentBand.x}" y="${layout.accentBand.y}" width="${layout.accentBand.width}" height="${layout.accentBand.height}" fill="${palette.accent}" opacity="0.9"/>
      <text x="${layout.time.x}" y="${layout.time.y}" fill="${palette.foreground}" font-size="${layout.time.fontSize}" font-weight="${layout.time.weight}" font-family="Arial, sans-serif">${escapeXml(copy.time)}</text>
      ${dateLine}
      ${batteryLine}
      <text x="14" y="${HEIGHT - 20}" fill="${palette.muted}" font-size="9" font-family="Arial, sans-serif">watchface generator preview</text>
    </svg>
  `)))}`;
}

function escapeXml(text) {
  return String(text)
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;")
    .replace(/'/g, "&apos;");
}

function copyForLanguage(language) {
  if (typeof language !== "string") return { time: "12:45", date: "Mon 4 May" };
  if (language.startsWith("de")) return { time: "12:45", date: "Mo 4. Mai" };
  if (language.startsWith("ja")) return { time: "12:45", date: "5月4日 月" };
  if (language.startsWith("zh")) return { time: "12:45", date: "5月4日 周一" };
  return { time: "12:45", date: "Mon 4 May" };
}

function buildResponseSpec(request) {
  const directionText = [request.direction, request.style, request.mood, request.brief].filter(Boolean).join(" ");
  const theme = pickTheme(directionText);
  const palette = paletteForTheme(theme, request.palette || request.colors);
  const layout = estimateLayout(theme, request.mustInclude);
  const copy = copyForLanguage(request.language);

  const spec = {
    specVersion: "watchface-spec/v1",
    type: "watchface-design",
    request: {
      direction: request.direction || "",
      style: request.style || "",
      mood: request.mood || "",
      language: request.language || "en-US",
      mustInclude: Array.isArray(request.mustInclude) ? request.mustInclude : [],
      avoid: Array.isArray(request.avoid) ? request.avoid : [],
    },
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
  spec.previewSvg = buildPreviewSvg(spec);
  spec.export = {
    skinZip: "pending",
    previewImage: "data-uri/svg",
    storageHint: "/storage/<creatorId>/<skinId>/skin.zip",
  };

  return spec;
}

function parseBody(request) {
  return request.json().catch(() => ({}));
}

export async function onRequest(context) {
  const { request } = context;

  if (request.method === "OPTIONS") {
    return new Response(null, { status: 204, headers: { "access-control-allow-origin": "*", "access-control-allow-methods": "GET, POST, OPTIONS", "access-control-allow-headers": "content-type, authorization" } });
  }

  if (request.method !== "POST" && request.method !== "GET") {
    return json({ error: "Method not allowed" }, 405);
  }

  if (request.method === "GET") {
    return json({
      endpoint: "/api/generate-watchface",
      specVersion: "watchface-spec/v1",
      canvas: { width: WIDTH, height: HEIGHT },
      allowedMethods: ["GET", "POST", "OPTIONS"],
      note: "POST a design direction to receive a structured watchface spec and preview.",
    });
  }

  const body = await parseBody(request);
  const spec = buildResponseSpec(body);

  return json(spec);
}
