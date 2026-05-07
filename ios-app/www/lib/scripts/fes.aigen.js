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
                    '<div class="aigen-watch"><div class="aigen-watch-screen"><span>preview</span></div></div>' +
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
                '<div class="aigen-mood-wrap">' +
                    '<span class="aigen-section-label">Mood</span>' +
                    '<select id="aigen-mood">' +
                        '<option value="calm">Calm</option>' +
                        '<option value="energetic">Energetic</option>' +
                        '<option value="dark">Dark / moody</option>' +
                        '<option value="bright">Bright / playful</option>' +
                        '<option value="nostalgic">Nostalgic</option>' +
                    "</select>" +
                "</div>" +
                '<div class="aigen-status" id="aigen-status">Designs render in monochrome — the FES watch display is greyscale.</div>' +
                '<div class="aigen-history" id="aigen-history" hidden>' +
                    '<span class="aigen-section-label">Recent</span>' +
                    '<div class="aigen-history-strip" id="aigen-history-strip"></div>' +
                "</div>" +
            "</div>" +
            '<div class="aigen-actions">' +
                '<button type="button" class="aigen-btn secondary" id="aigen-go">Generate</button>' +
                '<button type="button" class="aigen-btn" id="aigen-accept" disabled>Use this design</button>' +
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

    function wire(modal) {
        modal.querySelector(".aigen-close").addEventListener("click", close);
        modal.querySelector("#aigen-go").addEventListener("click", function () { runGeneration(false); });
        modal.querySelector("#aigen-accept").addEventListener("click", accept);

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

    function buildPrompt(opts) {
        var lines = [
            "Design a watchface artwork for a vertical narrow display, exact pixel size 152x704 (aspect ratio 1:4.6).",
            "VERY IMPORTANT: render in pure greyscale / black-and-white only. NO colour. The display is monochrome.",
            "Style: " + opts.styleDesc + ".",
            "Mood: " + opts.mood + ".",
            "Concept: " + opts.concept + ".",
            "Composition rules:",
            "- Vertical reading order top to bottom.",
            "- Leave the upper third visually quiet so a digital time readout remains legible (the watch overlays digits separately).",
            "- High contrast greyscale tones with strong blacks and clean whites.",
            "- No text, no logos, no watermarks.",
            "- Sharp at small render sizes (~30 mm wide on the wrist).",
            "- Edge-to-edge artwork, no border or padding.",
            "Render only the artwork as a single greyscale image."
        ];
        if (opts.vary) lines.push("Generate a fresh greyscale variation: keep the concept and style, change composition, value structure, and details.");
        return lines.join("\n");
    }

    function getSecrets() {
        var s = global.FES_SECRETS || {};
        return {
            key: s.OPENAI_API_KEY || "",
            model: s.OPENAI_MODEL || "gpt-image-2",
            size: s.OPENAI_SIZE || "1024x3072"
        };
    }

    async function callOpenAI(promptText) {
        var sec = getSecrets();
        if (!sec.key || sec.key.indexOf("__") === 0) {
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
        var concept = modal.querySelector("#aigen-prompt").value.trim();
        var style = currentStyle();
        var styleDesc = currentStyleDesc();
        var mood = modal.querySelector("#aigen-mood").value;

        if (!concept) { setStatus("Describe what you want first.", "error"); return; }

        goBtn.disabled = true; acceptBtn.disabled = true;
        var screen = modal.querySelector(".aigen-watch-screen");
        if (screen) screen.classList.add("is-loading");
        setStatus("Generating greyscale watchface…");

        try {
            var promptText = buildPrompt({ concept: concept, styleDesc: styleDesc, mood: mood, vary: varyOnly });
            var sourceDataUrl = await callOpenAI(promptText);
            setStatus("Converting to monochrome 152×704…");
            var result = await rasterizeToWatch(sourceDataUrl);

            var preview = modal.querySelector("#aigen-preview");
            preview.innerHTML =
                '<div class="aigen-watch"><div class="aigen-watch-screen"><img src="' + result.dataUrl + '" alt=""></div></div>';

            state.currentDataUrl = result.dataUrl;
            state.currentBlob = result.blob;
            state.currentMeta = { concept: concept, style: style, mood: mood };

            pushHistory({
                concept: concept, style: style, mood: mood,
                dataUrl: result.dataUrl, createdAt: new Date().toISOString()
            });

            acceptBtn.disabled = false;
            setStatus("Ready. Tap Use this design to apply.", "ok");
        } catch (err) {
            console.error("[aigen]", err);
            setStatus(err.message || String(err), "error");
        } finally {
            goBtn.disabled = false;
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

        var hItems = modal.querySelectorAll(".aigen-history-item");
        for (var i = 0; i < hItems.length; i++) hItems[i].setAttribute("aria-pressed", String(i === idx));

        state.currentDataUrl = it.dataUrl;
        state.currentBlob = null;
        state.currentMeta = { concept: it.concept, style: it.style, mood: it.mood };
        modal.querySelector("#aigen-accept").disabled = false;
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
