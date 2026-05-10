/* In-app AI watchface generator.
   Mounts a full-screen modal over the edit-background page when the
   "AI" footer button is tapped. Calls OpenAI gpt-image-2, converts the
   result to greyscale 152x704 (the FES watch is monochrome), and hands
   the resulting dataURL back to the caller. */

(function (global) {
    "use strict";

    var WIDTH = 152;
    var HEIGHT = 704;
    var HISTORY_KEY = "fes-aigen-history-v1";
    var HISTORY_LIMIT = 8;

    /* Curated Unsplash photo IDs for each style preset. The image is
       requested at a small portrait size (200×320) which is plenty for the
       tile thumbnail. CSS applies the greyscale filter, but Unsplash itself
       can crop/resize via the URL params. */
    var STYLE_PRESETS = [
        {
            id: "minimalist",
            label: "Minimalist",
            // calm, lots of negative space
            img: "https://images.unsplash.com/photo-1490604001847-b712b0c2f967?auto=format&fit=crop&w=200&h=320&q=70",
            desc: "minimalist, generous negative space, restrained palette, single thin accent"
        },
        {
            id: "editorial",
            label: "Editorial",
            // big typographic / magazine-cover feel
            img: "https://images.unsplash.com/photo-1455390582262-044cdead277a?auto=format&fit=crop&w=200&h=320&q=70",
            desc: "editorial typographic sensibility, magazine cover feel, bold contrast, refined composition"
        },
        {
            id: "abstract",
            label: "Abstract",
            // smooth gradients
            img: "https://images.unsplash.com/photo-1557672172-298e090bd0f1?auto=format&fit=crop&w=200&h=320&q=70",
            desc: "abstract gradient field, smooth tonal transitions, soft glow, no hard edges"
        },
        {
            id: "nature",
            label: "Nature",
            // moody landscape
            img: "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?auto=format&fit=crop&w=200&h=320&q=70",
            desc: "photorealistic nature, soft golden-hour light, organic textures, atmospheric depth"
        },
        {
            id: "geometric",
            label: "Geometric",
            // precise geometry
            img: "https://images.unsplash.com/photo-1507908708918-778587c9e563?auto=format&fit=crop&w=200&h=320&q=70",
            desc: "geometric pattern, repeating shapes, precise angles, tight grid alignment"
        },
        {
            id: "brutalist",
            label: "Brutalist",
            // raw concrete
            img: "https://images.unsplash.com/photo-1518481852452-9415b262eba4?auto=format&fit=crop&w=200&h=320&q=70",
            desc: "brutalist mono-tone, raw concrete texture, harsh light, single saturated accent"
        },
        {
            id: "anime",
            label: "Anime",
            // dramatic illustrative sky
            img: "https://images.unsplash.com/photo-1542309667-2a115d1f54c6?auto=format&fit=crop&w=200&h=320&q=70",
            desc: "anime illustrative style, painterly cel-shading, dramatic perspective, ink-line emphasis"
        }
    ];

    var SUGGESTIONS = [
        "minimal warm horizon at dusk",
        "neon Tokyo skyline at night",
        "misty mountain morning",
        "deep ocean fade",
        "brutalist concrete wall",
        "soft sunset gradient"
    ];

    var state = {
        currentBlob: null,
        currentDataUrl: null,
        currentMeta: null,
        onAccept: null,
        modal: null
    };

    function escapeHtml(s) {
        return String(s == null ? "" : s)
            .replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;").replace(/'/g, "&#039;");
    }

    /* ---------- modal DOM ---------- */

    function renderModalHtml() {
        var styleTiles = STYLE_PRESETS.map(function (s, i) {
            return '<button type="button" class="aigen-style-tile" data-style="' + s.id + '"' +
                ' aria-pressed="' + (i === 0 ? "true" : "false") + '">' +
                '<div class="tile-img"><img src="' + s.img + '" alt="" loading="lazy" referrerpolicy="no-referrer"></div>' +
                '<div class="tile-label">' + escapeHtml(s.label) + '</div>' +
                "</button>";
        }).join("");

        var suggestions = SUGGESTIONS.map(function (p) {
            return '<button type="button" class="chip" data-prompt="' + escapeHtml(p) + '">' + escapeHtml(p) + "</button>";
        }).join("");

        return "" +
            '<div class="aigen-header">' +
                '<button type="button" class="aigen-close" aria-label="Close">×</button>' +
                "<h2>AI Watchface</h2>" +
                '<span style="width:36px;flex:0 0 auto"></span>' +
            "</div>" +
            '<div class="aigen-body">' +
                '<div class="aigen-preview" id="aigen-preview">' +
                    '<div class="aigen-watch"><div class="aigen-watch-screen">' +
                        '<div class="aigen-watch-screen-placeholder">your<br>design<br>here</div>' +
                    '</div></div>' +
                "</div>" +
                '<div class="aigen-prompt-wrap">' +
                    '<span class="aigen-section-label">Concept</span>' +
                    '<textarea id="aigen-prompt" placeholder="e.g. minimal warm desert at dusk"></textarea>' +
                    '<div class="aigen-suggest">' + suggestions + "</div>" +
                "</div>" +
                "<div>" +
                    '<span class="aigen-section-label">Style</span>' +
                    '<div class="aigen-styles" role="radiogroup" aria-label="Style preset">' + styleTiles + "</div>" +
                "</div>" +
                '<details class="aigen-advanced">' +
                    '<summary>Advanced</summary>' +
                    '<div class="aigen-mood-wrap">' +
                        '<span class="aigen-section-label">Mood (optional)</span>' +
                        '<select id="aigen-mood">' +
                            '<option value="" selected>Let the concept decide</option>' +
                            '<option value="calm">Calm</option>' +
                            '<option value="energetic">Energetic</option>' +
                            '<option value="dark">Dark / moody</option>' +
                            '<option value="bright">Bright / playful</option>' +
                            '<option value="nostalgic">Nostalgic</option>' +
                        "</select>" +
                    "</div>" +
                "</details>" +
                '<div class="aigen-status" id="aigen-status">Describe a vibe and tap Generate.</div>' +
                '<div class="aigen-history" id="aigen-history" hidden>' +
                    '<span class="aigen-section-label">Recent</span>' +
                    '<div class="aigen-history-strip" id="aigen-history-strip"></div>' +
                "</div>" +
            "</div>" +
            '<div class="aigen-actions">' +
                '<button type="button" class="aigen-btn secondary" id="aigen-go">Generate</button>' +
                '<button type="button" class="aigen-btn" id="aigen-accept" disabled>Use this design</button>' +
            "</div>" +
            '<div class="aigen-actions aigen-actions-secondary">' +
                '<button type="button" class="aigen-btn ghost" id="aigen-submit" disabled>Share with community</button>' +
            "</div>";
    }

    function ensureModal() {
        if (state.modal && document.body.contains(state.modal)) return state.modal;
        var div = document.createElement("div");
        div.id = "aigen-modal";
        div.innerHTML = renderModalHtml();
        document.body.appendChild(div);
        state.modal = div;
        wire(div);
        return div;
    }

    /* ---------- wiring ---------- */

    function setStatus(msg, kind) {
        var el = document.getElementById("aigen-status");
        if (!el) return;
        el.className = "aigen-status" + (kind ? " " + kind : "");
        el.textContent = msg;
    }

    function selectTile(btn) {
        var tiles = state.modal.querySelectorAll(".aigen-style-tile");
        for (var i = 0; i < tiles.length; i++) {
            tiles[i].setAttribute("aria-pressed", String(tiles[i] === btn));
        }
    }

    function currentStyle() {
        var tile = state.modal.querySelector('.aigen-style-tile[aria-pressed="true"]');
        return tile ? tile.dataset.style : STYLE_PRESETS[0].id;
    }

    function currentStyleDesc() {
        var id = currentStyle();
        for (var i = 0; i < STYLE_PRESETS.length; i++) {
            if (STYLE_PRESETS[i].id === id) return STYLE_PRESETS[i].desc;
        }
        return STYLE_PRESETS[0].desc;
    }

    function currentStyleLabel() {
        var id = currentStyle();
        for (var i = 0; i < STYLE_PRESETS.length; i++) {
            if (STYLE_PRESETS[i].id === id) return STYLE_PRESETS[i].label;
        }
        return STYLE_PRESETS[0].label;
    }

    function wire(modal) {
        modal.querySelector(".aigen-close").addEventListener("click", close);
        modal.querySelector("#aigen-go").addEventListener("click", function () { runGeneration(false); });
        modal.querySelector("#aigen-accept").addEventListener("click", accept);
        var submitBtn = modal.querySelector("#aigen-submit");
        if (submitBtn) submitBtn.addEventListener("click", submitToCommunity);

        var suggest = modal.querySelector(".aigen-suggest");
        suggest && suggest.addEventListener("click", function (e) {
            var btn = e.target.closest("button.chip");
            if (!btn) return;
            var ta = modal.querySelector("#aigen-prompt");
            ta.value = btn.dataset.prompt;
            ta.focus();
        });

        var styles = modal.querySelector(".aigen-styles");
        styles && styles.addEventListener("click", function (e) {
            var btn = e.target.closest(".aigen-style-tile");
            if (btn) selectTile(btn);
        });

        var hist = modal.querySelector("#aigen-history-strip");
        hist && hist.addEventListener("click", function (e) {
            var btn = e.target.closest(".aigen-history-item");
            if (!btn) return;
            restoreFromHistory(Number(btn.dataset.idx));
        });
    }

    /* ---------- prompt + OpenAI ---------- */

    /* Per-style art-direction packet that goes into the structured prompt.
       Light, depth, negative space, contrast — concrete, image-makeable. */
    var STYLE_DIRECTION = {
        minimalist: {
            light:        "diffuse soft light from the upper edge",
            depth:        "essentially flat, one or two value layers",
            negativeSpace:"~70% empty space, the subject anchored low",
            contrast:     "low to medium contrast, gentle midtones",
            extras:       "single thin focal element, no decorative noise"
        },
        editorial: {
            light:        "directional studio light, hard edge",
            depth:        "two clear planes, foreground subject + flat background",
            negativeSpace:"asymmetric — strong vertical column of empty space on one side",
            contrast:     "high contrast, deep blacks and clean whites, magazine-cover feel",
            extras:       "graphic-design composition, no fine textures"
        },
        abstract: {
            light:        "no obvious light source, ambient gradient",
            depth:        "atmospheric depth, soft glow front-to-back",
            negativeSpace:"smooth gradient fields, no hard divisions",
            contrast:     "medium contrast, smooth tonal transitions, no hard edges",
            extras:       "painterly value washes, suggestive rather than literal"
        },
        nature: {
            light:        "golden-hour rim light or moody overcast",
            depth:        "interlocking organic forms filling the full canvas, no single horizon line",
            negativeSpace:"distributed pockets of negative space across the whole composition, NOT a sky-on-top / ground-on-bottom split",
            contrast:     "broad tonal range, deep shadows + bright highlights",
            extras:       "organic textures (foliage, water, rock, mist) covering top to bottom — avoid the default landscape with empty sky"
        },
        geometric: {
            light:        "flat lighting, no cast shadows",
            depth:        "completely flat, pure 2D shapes",
            negativeSpace:"precise grid alignment, repeating intervals",
            contrast:     "high contrast between figure and ground",
            extras:       "circles, lines, triangles, repeating motifs, mathematical precision"
        },
        brutalist: {
            light:        "harsh single-direction light from above-left",
            depth:        "shallow but textural, raw material surface",
            negativeSpace:"dominated by a heavy mass, small slice of negative space",
            contrast:     "very high contrast, deep crushed blacks",
            extras:       "concrete or rough stone texture, single intense focal accent"
        },
        anime: {
            light:        "dramatic backlight or rim light, lens-flare stylisation",
            depth:        "overlapping illustrated layers stacked floor-to-ceiling, characters / props / patterns interleaved",
            negativeSpace:"distributed across the canvas — NOT a sky-half + silhouette-half split",
            contrast:     "high contrast cel-shading, ink-line emphasis",
            extras:       "painterly illustrative style, no photorealism, fill the whole tall canvas with motif"
        }
    };

    function structuredPrompt(opts) {
        /* Build a concrete art-direction prompt the image model can
           execute on. Each line is a directive; the model handles them
           better as labelled bullets than as one paragraph. */
        var dir = STYLE_DIRECTION[opts.styleId] || STYLE_DIRECTION.minimalist;
        var lines = [
            "You are creating a printed skin — a flat greyscale image — for a wearable device called the Sony FES Watch U.",
            "The FES Watch U has a monochrome e-paper display that covers the entire device surface: both the round watch face AND both watch straps are one continuous e-paper screen.",
            "Your image IS that surface, unrolled and laid out flat. No background, no environment, no table, no shadow — just the artwork itself.",
            "",
            "SHAPE OF THE IMAGE (152 px wide × 704 px tall):",
            "  1. Upper strap: a short rectangle at the top (y=0–196), full width.",
            "  2. Watch face: a large circle in the middle (y=196–508), full width of the canvas.",
            "  3. Lower strap: a short rectangle at the bottom (y=508–704), full width.",
            "The artwork flows seamlessly across all three zones — no gap, no border, no frame between them.",
            "",
            "CANVAS DIMENSIONS: 152 px wide × 704 px tall.",
            "  - Upper strap region: x=0–152, y=0–196 (rectangular band).",
            "  - Watch face region: x=0–152, y=196–508 (the design is circular here but the canvas is still the full 152 px wide — corners of this zone are background).",
            "  - Lower strap region: x=0–152, y=508–704 (rectangular band).",
            "",
            "DISPLAY FORMAT: The screen is greyscale e-paper with only 4 tones: pure black, dark grey, light grey, and pure white. No colour exists on this device. Smooth gradients look terrible — they posterize into ugly steps. Design with flat tonal areas and hard tonal edges, like a screen-printed poster or a woodblock print.",
            "",
            "CONCEPT: " + opts.concept,
            opts.mood ? ("MOOD: " + opts.mood) : null,
            "STYLE: " + (opts.styleLabel || opts.styleId).toLowerCase(),
            "",
            "ART DIRECTION:",
            "- Light: " + dir.light + ".",
            "- Depth: " + dir.depth + ".",
            "- Negative space: " + dir.negativeSpace + ".",
            "- Contrast: " + dir.contrast + ".",
            "- Extras: " + dir.extras + ".",
            "",
            "COMPOSITION RULES:",
            "- The pattern or artwork must flow continuously and naturally across all three zones (upper strap → watch face circle → lower strap). Think of it like a textile print that wraps around the whole object.",
            "- The watch firmware draws the time (large clock digits) inside the circular face area, centred at approximately x=76, y=352, radius 76 px. This means the central circle of the watch face will have the time overlaid on top of your artwork. Design the watch face zone (y=196–508) with a CALM, UNIFORM background — either a solid dark value or a solid light value — so the time digits remain readable. Put your interesting textures and focal elements in the strap regions.",
            "- The two strap regions (top and bottom) are where the visual concept lives. They should be bold, detailed, and expressive.",
            "- Use the 4 tones boldly. Large flat areas of a single tone read better than busy detail at this size.",
            "- The watch is only 30 mm wide in real life. Shapes must be simple and readable at thumbnail size.",
            "- Fill every pixel — no white margins, no border, no padding around the edges.",
            "- Do not add any text, digits, clock hands, or logos. The watch firmware handles the time display.",
            "",
            "OUTPUT: A single flat greyscale image, 152×704 px, showing the complete artwork as it would appear printed across the full surface of the FES Watch U."
        ];
        if (opts.vary) {
            lines.push("");
            lines.push("VARIATION: keep the same concept, mood, and style direction. Change the specific visual composition, the tonal distribution, and the shape of the main focal element.");
        }
        return lines.filter(function (l) { return l !== null; }).join("\n");
    }

    function getSecrets() {
        var s = global.FES_SECRETS || {};
        return {
            key: s.OPENAI_API_KEY || "",
            model: s.OPENAI_MODEL || "gpt-image-2",
            size: s.OPENAI_SIZE || "1024x3072",
            rephraseModel: s.OPENAI_REPHRASE_MODEL || "gpt-5.4-nano"
        };
    }

    function isPromptKey(s) {
        return s && typeof s === "string" && s.indexOf("__") !== 0;
    }

    /* Pre-process the user's raw concept text through gpt-5-nano so a
       lazy "cool image" turns into a concrete subject + setting + mood
       phrase the image model can actually render. The structured prompt
       above wraps this. */
    async function rephraseConcept(rawConcept, styleLabel, mood) {
        var sec = getSecrets();
        if (!isPromptKey(sec.key)) {
            console.warn("[aigen] rephrase skipped: no key");
            return rawConcept;
        }
        var system = "You are a design brief writer for the Sony FES Watch U — a fashion watch where the entire device surface (round face + both straps) is one continuous monochrome e-paper screen. Your job is to rewrite vague user concepts into vivid, concrete image-generation briefs of 1–2 sentences. Output must be present-tense, sensory, and specific. No meta-commentary, no 'Sure!', no quotes, no explanation. Maximum 40 words.";
        var user = [
            "Style: " + styleLabel + ".",
            mood ? ("Mood: " + mood + ".") : "Mood: choose what fits the concept.",
            "User concept: " + rawConcept,
            "",
            "Rewrite this as a vivid, specific artwork brief for the FES Watch U skin.",
            "Remember: the image is 152×704 px greyscale (4 tones only). It shows the watch face as a circle in the centre, with two short rectangular straps above and below. The concept must work as a pattern or texture that flows continuously across all three zones — top strap, circular face, bottom strap — like a single printed fabric.",
            "Avoid describing scenes with a sky-and-ground horizon. Instead describe a bold repeating pattern, texture, motif, or abstract composition that fills the whole strip top-to-bottom."
        ].join("\n");

        try {
            var res = await fetch("https://api.openai.com/v1/chat/completions", {
                method: "POST",
                headers: {
                    "Authorization": "Bearer " + sec.key,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    model: sec.rephraseModel,
                    messages: [
                        { role: "system", content: system },
                        { role: "user",   content: user }
                    ]
                })
            });
            if (!res.ok) {
                // fall back to raw concept on rephrase failure — don't kill the run
                var errBody = await res.text();
                console.warn("[aigen] rephrase failed " + res.status + ", using raw concept:", errBody.slice(0, 200));
                return rawConcept;
            }
            var data = await res.json();
            var content = data && data.choices && data.choices[0] && data.choices[0].message && data.choices[0].message.content;
            if (!content || !content.trim()) return rawConcept;
            // trim any surrounding quotes the model might add
            return content.trim().replace(/^["“”']+|["“”']+$/g, "");
        } catch (e) {
            console.warn("[aigen] rephrase threw, using raw concept:", e && e.message);
            return rawConcept;
        }
    }

    async function callOpenAI(promptText) {
        var sec = getSecrets();
        if (!isPromptKey(sec.key)) {
            throw new Error("OPENAI_API_KEY missing — set it in scripts/secrets.js");
        }
        var res = await fetch("https://api.openai.com/v1/images/generations", {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + sec.key,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ model: sec.model, prompt: promptText, size: sec.size, n: 1 })
        });
        if (!res.ok) {
            var errText = await res.text();
            throw new Error("OpenAI " + res.status + ": " + errText.slice(0, 300));
        }
        var data = await res.json();
        var b64 = data && data.data && data.data[0] && data.data[0].b64_json;
        if (!b64) throw new Error("OpenAI returned no image. Try a different prompt.");
        return "data:image/png;base64," + b64;
    }

    /* Cover-fit crop to 152x704 AND greyscale conversion in one pass. */
    function rasterizeToWatch(sourceDataUrl) {
        return new Promise(function (resolve, reject) {
            var img = new Image();
            img.crossOrigin = "anonymous";
            img.onload = function () {
                try {
                    var canvas = document.createElement("canvas");
                    canvas.width = WIDTH;
                    canvas.height = HEIGHT;
                    var ctx = canvas.getContext("2d");

                    var sourceRatio = img.width / img.height;
                    var targetRatio = WIDTH / HEIGHT;
                    var sx = 0, sy = 0, sw = img.width, sh = img.height;
                    if (sourceRatio > targetRatio) {
                        sw = img.height * targetRatio;
                        sx = (img.width - sw) / 2;
                    } else {
                        sh = img.width / targetRatio;
                        sy = (img.height - sh) / 2;
                    }
                    ctx.drawImage(img, sx, sy, sw, sh, 0, 0, WIDTH, HEIGHT);

                    /* Force pure greyscale + a touch of contrast on the
                       pixel data so the bytes that ultimately reach the
                       watch are monochrome regardless of what the model
                       returned. BT.709 luminance, then S-curve. */
                    var imgData = ctx.getImageData(0, 0, WIDTH, HEIGHT);
                    var d = imgData.data;
                    for (var i = 0; i < d.length; i += 4) {
                        var l = 0.2126 * d[i] + 0.7152 * d[i + 1] + 0.0722 * d[i + 2];
                        // gentle S-curve for punch (centered on 128)
                        l = Math.max(0, Math.min(255, (l - 128) * 1.08 + 128));
                        d[i] = d[i + 1] = d[i + 2] = l;
                    }
                    ctx.putImageData(imgData, 0, 0);

                    canvas.toBlob(function (blob) {
                        if (!blob) { reject(new Error("Could not encode PNG")); return; }
                        var reader = new FileReader();
                        reader.onload = function () { resolve({ dataUrl: reader.result, blob: blob }); };
                        reader.readAsDataURL(blob);
                    }, "image/png");
                } catch (err) { reject(err); }
            };
            img.onerror = function () { reject(new Error("Could not decode generated image")); };
            img.src = sourceDataUrl;
        });
    }

    /* ---------- generation flow ---------- */

    async function runGeneration(varyOnly) {
        var modal = state.modal;
        var goBtn = modal.querySelector("#aigen-go");
        var acceptBtn = modal.querySelector("#aigen-accept");
        var rawConcept = modal.querySelector("#aigen-prompt").value.trim();
        var styleId = currentStyle();
        var styleLabel = currentStyleLabel();
        var mood = modal.querySelector("#aigen-mood").value;

        if (!rawConcept) { setStatus("Describe what you want first.", "error"); return; }

        goBtn.disabled = true; acceptBtn.disabled = true;
        var preview = modal.querySelector("#aigen-preview");
        var screen = modal.querySelector(".aigen-watch-screen");
        if (preview) preview.classList.add("is-loading");
        if (screen) screen.classList.add("is-loading");

        try {
            // Phase 1: rephrase the user's concept into a vivid brief.
            // Cheap (gpt-5.4-nano), ~1-2s, dramatically improves image quality.
            setStatus("Refining your concept…");
            var refinedConcept = await rephraseConcept(rawConcept, styleLabel, mood);
            console.log("[aigen] rephrased:", JSON.stringify(refinedConcept));

            // Phase 2: build the structured art-direction prompt around it.
            var promptText = structuredPrompt({
                concept: refinedConcept,
                styleId: styleId,
                styleLabel: styleLabel,
                mood: mood,
                vary: varyOnly
            });

            // Phase 3: image generation.
            setStatus("Generating your design (~20s)…");
            var sourceDataUrl = await callOpenAI(promptText);

            // Phase 4: crop + grayscale-bake.
            setStatus("Preparing for the watch…");
            var result = await rasterizeToWatch(sourceDataUrl);

            preview.innerHTML =
                '<div class="aigen-watch"><div class="aigen-watch-screen"><img src="' + result.dataUrl + '" alt=""></div></div>';
            preview.classList.add("has-image");
            preview.classList.remove("is-loading");

            state.currentDataUrl = result.dataUrl;
            state.currentBlob = result.blob;
            state.currentMeta = { concept: rawConcept, refinedConcept: refinedConcept, style: styleId, mood: mood };

            pushHistory({
                concept: rawConcept,
                refinedConcept: refinedConcept,
                style: styleId, mood: mood,
                dataUrl: result.dataUrl, createdAt: new Date().toISOString()
            });

            acceptBtn.disabled = false;
            var submitBtn = modal.querySelector("#aigen-submit");
            if (submitBtn) submitBtn.disabled = false;
            setStatus("Ready. Tap Use this design to apply.", "ok");
        } catch (err) {
            console.error("[aigen]", err);
            setStatus(err.message || String(err), "error");
        } finally {
            goBtn.disabled = false;
            var p2 = modal.querySelector("#aigen-preview");
            if (p2) p2.classList.remove("is-loading");
            var s2 = modal.querySelector(".aigen-watch-screen");
            if (s2) s2.classList.remove("is-loading");
        }
    }

    /* ---------- history ---------- */

    function readHistory() {
        try {
            var raw = localStorage.getItem(HISTORY_KEY);
            return raw ? JSON.parse(raw) : [];
        } catch (e) { return []; }
    }
    function writeHistory(items) {
        try { localStorage.setItem(HISTORY_KEY, JSON.stringify(items.slice(0, HISTORY_LIMIT))); }
        catch (e) { /* quota */ }
    }
    function pushHistory(entry) {
        var items = readHistory();
        items.unshift(entry);
        writeHistory(items);
        renderHistory();
    }
    function renderHistory() {
        var modal = state.modal;
        if (!modal) return;
        var wrap = modal.querySelector("#aigen-history");
        var strip = modal.querySelector("#aigen-history-strip");
        if (!wrap || !strip) return;
        var items = readHistory();
        if (!items.length) { wrap.hidden = true; return; }
        wrap.hidden = false;
        strip.innerHTML = items.map(function (it, i) {
            return '<button type="button" class="aigen-history-item" data-idx="' + i + '" aria-pressed="false" title="' + escapeHtml(it.concept || "") + '">' +
                '<div class="h-img"><img src="' + it.dataUrl + '" alt=""></div>' +
                "</button>";
        }).join("");
    }
    function restoreFromHistory(idx) {
        var items = readHistory();
        var it = items[idx];
        if (!it) return;
        var modal = state.modal;
        modal.querySelector("#aigen-prompt").value = it.concept || "";
        var styleBtn = modal.querySelector('.aigen-style-tile[data-style="' + it.style + '"]');
        if (styleBtn) selectTile(styleBtn);
        var moodSel = modal.querySelector("#aigen-mood");
        if (moodSel && it.mood) moodSel.value = it.mood;
        var preview = modal.querySelector("#aigen-preview");
        preview.innerHTML =
            '<div class="aigen-watch"><div class="aigen-watch-screen"><img src="' + it.dataUrl + '" alt=""></div></div>';
        preview.classList.add("has-image");
        preview.classList.remove("is-loading");

        var hItems = modal.querySelectorAll(".aigen-history-item");
        for (var i = 0; i < hItems.length; i++) hItems[i].setAttribute("aria-pressed", String(i === idx));

        state.currentDataUrl = it.dataUrl;
        state.currentBlob = null;
        state.currentMeta = { concept: it.concept, style: it.style, mood: it.mood };
        modal.querySelector("#aigen-accept").disabled = false;
        var submitBtn2 = modal.querySelector("#aigen-submit");
        if (submitBtn2) submitBtn2.disabled = false;
    }

    /* ---------- community submission ---------- */

    /* Tiny ZIP (store-only, no compression) writer in pure JS. Produces
       a Blob that is byte-identical to the existing community skin.zip
       format expected by the watch firmware. Mirrors the implementation
       in server-mirror/public/index.html so a lazy local edit there is
       reflected here too. */
    function makeStoreZip(files) {
        var encoder = new TextEncoder();
        var chunks = [];
        var central = [];
        var offset = 0;

        var crcTable = (function () {
            var t = new Uint32Array(256);
            for (var i = 0; i < 256; i++) {
                var c = i;
                for (var k = 0; k < 8; k++) c = (c & 1) ? (0xEDB88320 ^ (c >>> 1)) : (c >>> 1);
                t[i] = c >>> 0;
            }
            return t;
        })();
        function crc32(bytes) {
            var c = 0xFFFFFFFF;
            for (var i = 0; i < bytes.length; i++) c = crcTable[(c ^ bytes[i]) & 0xFF] ^ (c >>> 8);
            return (c ^ 0xFFFFFFFF) >>> 0;
        }
        for (var fi = 0; fi < files.length; fi++) {
            var f = files[fi];
            var nameBytes = encoder.encode(f.name);
            var data = f.data;
            var crc = crc32(data);
            var localHeader = new Uint8Array(30 + nameBytes.length);
            var dv = new DataView(localHeader.buffer);
            dv.setUint32(0, 0x04034b50, true);
            dv.setUint16(4, 20, true);
            dv.setUint16(6, 0, true);
            dv.setUint16(8, 0, true);
            dv.setUint16(10, 0, true);
            dv.setUint16(12, 0, true);
            dv.setUint32(14, crc, true);
            dv.setUint32(18, data.length, true);
            dv.setUint32(22, data.length, true);
            dv.setUint16(26, nameBytes.length, true);
            dv.setUint16(28, 0, true);
            localHeader.set(nameBytes, 30);
            chunks.push(localHeader, data);

            var cdEntry = new Uint8Array(46 + nameBytes.length);
            var cdv = new DataView(cdEntry.buffer);
            cdv.setUint32(0, 0x02014b50, true);
            cdv.setUint16(4, 20, true);
            cdv.setUint16(6, 20, true);
            cdv.setUint16(8, 0, true);
            cdv.setUint16(10, 0, true);
            cdv.setUint16(12, 0, true);
            cdv.setUint16(14, 0, true);
            cdv.setUint32(16, crc, true);
            cdv.setUint32(20, data.length, true);
            cdv.setUint32(24, data.length, true);
            cdv.setUint16(28, nameBytes.length, true);
            cdv.setUint16(30, 0, true);
            cdv.setUint16(32, 0, true);
            cdv.setUint16(34, 0, true);
            cdv.setUint16(36, 0, true);
            cdv.setUint32(38, 0, true);
            cdv.setUint32(42, offset, true);
            cdEntry.set(nameBytes, 46);
            central.push(cdEntry);

            offset += localHeader.length + data.length;
        }
        var cdSize = central.reduce(function (s, c) { return s + c.length; }, 0);
        var cdOffset = offset;
        for (var ci = 0; ci < central.length; ci++) { chunks.push(central[ci]); offset += central[ci].length; }

        var eocd = new Uint8Array(22);
        var edv = new DataView(eocd.buffer);
        edv.setUint32(0, 0x06054b50, true);
        edv.setUint16(4, 0, true);
        edv.setUint16(6, 0, true);
        edv.setUint16(8, files.length, true);
        edv.setUint16(10, files.length, true);
        edv.setUint32(12, cdSize, true);
        edv.setUint32(16, cdOffset, true);
        edv.setUint16(20, 0, true);
        chunks.push(eocd);

        var totalLen = 0;
        for (var ti = 0; ti < chunks.length; ti++) totalLen += chunks[ti].length;
        var out = new Uint8Array(totalLen);
        var pos = 0;
        for (var pi = 0; pi < chunks.length; pi++) { out.set(chunks[pi], pos); pos += chunks[pi].length; }
        return new Blob([out], { type: "application/zip" });
    }

    function arrayBufferToBase64(ab) {
        var bytes = new Uint8Array(ab);
        var bin = "";
        var chunk = 0x8000;
        for (var i = 0; i < bytes.length; i += chunk) {
            bin += String.fromCharCode.apply(null, bytes.subarray(i, Math.min(i + chunk, bytes.length)));
        }
        return btoa(bin);
    }

    function makeSlug() {
        // small, sortable, mostly-unique: timestamp + 4 random hex
        var ts = Math.floor(Date.now() / 1000).toString(36);
        var rnd = Math.floor(Math.random() * 0xFFFF).toString(16).padStart(4, "0");
        return "ai-" + ts + "-" + rnd;
    }

    /* PUT one file via the GitHub Contents API. */
    async function githubPut(token, repo, branch, path, contentB64, message) {
        var url = "https://api.github.com/repos/" + repo + "/contents/" + encodeURI(path);
        var res = await fetch(url, {
            method: "PUT",
            headers: {
                "Authorization": "Bearer " + token,
                "Accept": "application/vnd.github+json",
                "Content-Type": "application/json",
                "X-GitHub-Api-Version": "2022-11-28"
            },
            body: JSON.stringify({
                message: message,
                content: contentB64,
                branch: branch
            })
        });
        if (!res.ok) {
            var err = await res.text();
            throw new Error("GitHub PUT " + res.status + " " + path + ": " + err.slice(0, 200));
        }
        return await res.json();
    }

    async function submitToCommunity() {
        var modal = state.modal;
        var btn = modal.querySelector("#aigen-submit");
        if (!state.currentDataUrl) return;

        var sec = global.FES_SECRETS || {};
        var token = sec.GITHUB_SUBMISSION_TOKEN;
        var repo = sec.GITHUB_SUBMISSION_REPO || "t3mr0i/fes-closet-community";
        var branch = sec.GITHUB_SUBMISSION_BRANCH || "main";
        if (!isPromptKey(token)) {
            setStatus("Submission not configured — token missing.", "error");
            return;
        }

        btn.disabled = true;
        setStatus("Packaging skin.zip…");

        try {
            // 1. Build the installable skin.zip (config.json + bg.png) from
            //    the current 152x704 PNG dataURL.
            var pngBlob;
            if (state.currentBlob) {
                pngBlob = state.currentBlob;
            } else {
                var resp = await fetch(state.currentDataUrl);
                pngBlob = await resp.blob();
            }
            var pngBytes = new Uint8Array(await pngBlob.arrayBuffer());
            var config = {
                version: "1.0.0",
                revision: Date.now(),
                components: [
                    { type: "background", image: "bg.png", layout: { size: [WIDTH, HEIGHT] } }
                ]
            };
            var configBytes = new TextEncoder().encode(JSON.stringify(config, null, 2));
            var skinZip = makeStoreZip([
                { name: "config.json", data: configBytes },
                { name: "bg.png",      data: pngBytes }
            ]);
            var skinZipBytes = new Uint8Array(await skinZip.arrayBuffer());

            // 2. Build the metadata.json sidecar.
            var meta = state.currentMeta || {};
            var slug = makeSlug();
            var metadata = {
                slug: slug,
                concept: meta.concept || "",
                refinedConcept: meta.refinedConcept || "",
                style: meta.style || "minimalist",
                mood: meta.mood || "",
                createdAt: new Date().toISOString(),
                source: "in-app-aigen"
            };
            var metadataBytes = new TextEncoder().encode(JSON.stringify(metadata, null, 2));

            // 3. PUT all three files into submissions/pending/<slug>/
            var base = "submissions/pending/" + slug + "/";
            setStatus("Uploading metadata…");
            await githubPut(token, repo, branch, base + "metadata.json",
                arrayBufferToBase64(metadataBytes.buffer), "submission " + slug + ": metadata");

            setStatus("Uploading skin.zip (" + Math.round(skinZipBytes.length / 1024) + " KB)…");
            await githubPut(token, repo, branch, base + "skin.zip",
                arrayBufferToBase64(skinZipBytes.buffer), "submission " + slug + ": skin.zip");

            setStatus("Uploading preview…");
            await githubPut(token, repo, branch, base + "preview.png",
                arrayBufferToBase64(pngBytes.buffer), "submission " + slug + ": preview");

            setStatus("Submitted! Waiting for review.", "ok");
            // re-enable so user can submit again if they want, after a short delay
            setTimeout(function () { btn.disabled = false; }, 2000);
        } catch (err) {
            console.error("[aigen submit]", err);
            setStatus(err.message || String(err), "error");
            btn.disabled = false;
        }
    }

    /* ---------- accept / open / close ---------- */

    function accept() {
        if (!state.currentDataUrl) return;
        var cb = state.onAccept;
        var dataUrl = state.currentDataUrl;
        close();
        if (typeof cb === "function") cb(dataUrl);
    }

    function open(opts) {
        opts = opts || {};
        state.onAccept = opts.onAccept || null;
        ensureModal();
        renderHistory();
        state.modal.classList.add("is-open");
        document.documentElement.style.overflow = "hidden";
    }

    function close() {
        if (!state.modal) return;
        state.modal.classList.remove("is-open");
        document.documentElement.style.overflow = "";
    }

    global.FES_AIGEN = { open: open, close: close };

})(window);
