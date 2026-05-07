/* In-app AI watchface generator.
   Mounts a full-screen modal over the edit-background page when the
   "AI" footer button is tapped. Calls OpenAI gpt-image-2, crops the
   result to 152x704 on a canvas, and hands the resulting dataURL back
   to the caller (typically the edit-background BackgroundStage). */

(function (global) {
    "use strict";

    var WIDTH = 152;
    var HEIGHT = 704;
    var HISTORY_KEY = "fes-aigen-history-v1";
    var HISTORY_LIMIT = 8;

    var STYLE_PRESETS = [
        { id: "minimalist", label: "Minimalist" },
        { id: "editorial",  label: "Editorial"  },
        { id: "abstract",   label: "Abstract"   },
        { id: "nature",     label: "Nature"     },
        { id: "geometric",  label: "Geometric"  },
        { id: "brutalist",  label: "Brutalist"  },
        { id: "anime",      label: "Anime"      }
    ];

    var SUGGESTIONS = [
        "minimal warm horizon at dusk",
        "neon Tokyo skyline at night",
        "misty mountain morning",
        "deep ocean fade, blue to black",
        "brutalist concrete with one orange seam",
        "soft sunset gradient, pink to gold"
    ];

    var STYLE_TILE_BASE = "https://t3mr0i.github.io/fes-closet-community/img/style-tiles/";

    var state = {
        currentBlob: null,        // last generated PNG blob
        currentDataUrl: null,
        currentMeta: null,        // { concept, style, mood, name }
        onAccept: null,           // callback to hand the dataUrl to
        modal: null
    };

    function escapeHtml(s) {
        return String(s == null ? "" : s)
            .replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;").replace(/'/g, "&#039;");
    }

    // -------- modal DOM --------

    function renderModalHtml() {
        var styleTiles = STYLE_PRESETS.map(function (s, i) {
            return '<button type="button" class="aigen-style-tile" data-style="' + s.id + '"' +
                ' aria-pressed="' + (i === 0 ? "true" : "false") + '">' +
                '<div class="tile-img"><img src="' + STYLE_TILE_BASE + s.id + '.png" alt="" loading="lazy" onerror="this.replaceWith(Object.assign(document.createElement(\'span\'),{textContent:\'' + s.id + '\'}))"></div>' +
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
                '<span style="width:36px"></span>' +
            "</div>" +
            '<div class="aigen-body">' +
                '<div class="aigen-preview" id="aigen-preview">' +
                    '<div class="aigen-watch"><div class="aigen-watch-screen"><span>preview</span></div></div>' +
                "</div>" +
                '<div class="aigen-prompt-wrap">' +
                    '<label for="aigen-prompt">Watchface concept</label>' +
                    '<textarea id="aigen-prompt" placeholder="e.g. minimal warm desert at dusk, soft horizon line"></textarea>' +
                    '<div class="aigen-suggest">' + suggestions + "</div>" +
                "</div>" +
                "<div>" +
                    '<label style="display:block;margin-bottom:6px;font-size:13px;font-weight:600;color:#c5c1b6">Style</label>' +
                    '<div class="aigen-styles" role="radiogroup" aria-label="Style preset">' + styleTiles + "</div>" +
                "</div>" +
                '<div class="aigen-mood-wrap">' +
                    '<label for="aigen-mood">Mood</label>' +
                    '<select id="aigen-mood">' +
                        '<option value="calm">Calm</option>' +
                        '<option value="energetic">Energetic</option>' +
                        '<option value="dark">Dark / moody</option>' +
                        '<option value="bright">Bright / playful</option>' +
                        '<option value="nostalgic">Nostalgic</option>' +
                    "</select>" +
                "</div>" +
                '<div class="aigen-status" id="aigen-status">Generates a 152×704 watchface using OpenAI gpt-image-2.</div>' +
                '<div class="aigen-history" id="aigen-history" hidden>' +
                    '<div class="aigen-history-title">Recent in this session</div>' +
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

    // -------- wiring --------

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

    function wire(modal) {
        modal.querySelector(".aigen-close").addEventListener("click", close);
        modal.querySelector("#aigen-go").addEventListener("click", function () { runGeneration(false); });
        modal.querySelector("#aigen-accept").addEventListener("click", accept);

        var suggest = modal.querySelector(".aigen-suggest");
        suggest && suggest.addEventListener("click", function (e) {
            var btn = e.target.closest(".chip");
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

    // -------- OpenAI call --------

    function buildPrompt(opts) {
        var lines = [
            "Design a watchface artwork for a vertical narrow display, exact pixel size 152x704 (aspect ratio 1:4.6).",
            "Style: " + opts.style + ". Mood: " + opts.mood + ".",
            "Concept: " + opts.concept + ".",
            "Composition rules:",
            "- Vertical reading order top to bottom.",
            "- Leave the upper third visually quiet so a digital time readout remains legible against the artwork (the watch overlays digits separately).",
            "- High contrast, no text, no logos, no watermarks.",
            "- Sharp at small render sizes (final image will be displayed only ~30 mm wide on the wrist).",
            "- Edge-to-edge artwork, no white border, no padding.",
            "Render only the artwork as a single PNG/JPEG image."
        ];
        if (opts.vary) lines.push("Generate a fresh variation: keep the concept and style, change the specific composition, color accents, and details.");
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
        if (!b64) throw new Error("OpenAI response had no image. Try a different prompt.");
        return "data:image/png;base64," + b64;
    }

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

    // -------- generation flow --------

    async function runGeneration(varyOnly) {
        var modal = state.modal;
        var goBtn = modal.querySelector("#aigen-go");
        var acceptBtn = modal.querySelector("#aigen-accept");
        var concept = modal.querySelector("#aigen-prompt").value.trim();
        var style = currentStyle();
        var mood = modal.querySelector("#aigen-mood").value;

        if (!concept) { setStatus("Describe what you want first.", "error"); return; }

        goBtn.disabled = true; acceptBtn.disabled = true;
        var screen = modal.querySelector(".aigen-watch-screen");
        if (screen) screen.classList.add("is-loading");
        setStatus("Calling OpenAI…");

        try {
            var promptText = buildPrompt({ concept: concept, style: style, mood: mood, vary: varyOnly });
            var sourceDataUrl = await callOpenAI(promptText);
            setStatus("Rasterizing to 152×704…");
            var result = await rasterizeToWatch(sourceDataUrl);

            // Render preview
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

    // -------- history --------

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
        state.currentBlob = null; // will be re-derived on accept if needed
        state.currentMeta = { concept: it.concept, style: it.style, mood: it.mood };
        modal.querySelector("#aigen-accept").disabled = false;
    }

    // -------- accept / close --------

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
