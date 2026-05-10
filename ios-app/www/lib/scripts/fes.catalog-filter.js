/* FES Community catalog/closet shell.
 *
 * Replaces Sony's home-header on the catalog and closet pages with a single
 * floating toolbar that switches mode based on which page is active. The
 * filter UI (genre/tag pills, full facet sheet, search with auto-complete)
 * is wired to Sony's SkinCollection so the arc reflects the filter.
 *
 * File sections:
 *   1. Catalog data: fetch + facet-build from the bundled catalog JSON
 *   2. Filter state + filter -> Sony arc adapter
 *   3. View: toolbar, pill-row, bottom-sheet, suggest-box rendering
 *   4. Page lifecycle: hook into jQM page events + DOM observation
 *
 * Sony's hidden buttons (in the templates) stay in the DOM; we trigger them
 * via jQuery vclick to preserve Sony's transition + back-stack.
 */
(function () {
  "use strict";

  // ---------- 1. CATALOG DATA --------------------------------------------

  var STORE_INSTANCE = 2;
  var CATALOG_URLS = function (loc) {
    return [
      "https://t3mr0i.github.io/fes-closet-community/api/store/skins-" + loc + ".json",
      "res/data/api/store/skins-" + loc + ".json",
      "/res/data/api/store/skins-" + loc + ".json",
    ];
  };
  var facets = null; // { genre, tag, studio, artist, _all, _counts }

  function getCollection() {
    try {
      return window.FES && FES.Model && FES.Model.SkinCollection &&
             FES.Model.SkinCollection.getInstance(STORE_INSTANCE);
    } catch (e) { return null; }
  }

  function localeKey() {
    try {
      var lang = (window.$ && $.i18n && $.i18n.language) || "en-US";
      var l = lang.toLowerCase();
      if (l === "ja-jp") return "ja-jp";
      if (l === "zh-cn" || l === "zh-sg") return "zh-cn";
    } catch (_) {}
    return "en-us";
  }

  function fetchJsonChain(urls) {
    var i = 0;
    var step = function () {
      if (i >= urls.length) return Promise.reject(new Error("no source"));
      return fetch(urls[i++]).then(function (r) {
        if (!r.ok) throw new Error("status " + r.status);
        return r.json();
      }).catch(step);
    };
    return step();
  }

  function buildFacets(skins) {
    var c = { genre: {}, tag: {}, studio: {}, artist: {} };
    skins.forEach(function (s) {
      if (s.genre)  c.genre[s.genre]   = (c.genre[s.genre]   || 0) + 1;
      if (s.studio) c.studio[s.studio] = (c.studio[s.studio] || 0) + 1;
      if (s.artist) c.artist[s.artist] = (c.artist[s.artist] || 0) + 1;
      (s.tags || []).forEach(function (t) { c.tag[t] = (c.tag[t] || 0) + 1; });
    });
    var sortByCount = function (o) {
      return Object.keys(o)
        .map(function (k) { return { value: k, count: o[k] }; })
        .sort(function (a, b) { return b.count - a.count || a.value.localeCompare(b.value); });
    };
    return {
      genre:  sortByCount(c.genre),
      tag:    sortByCount(c.tag),
      studio: sortByCount(c.studio),
      artist: sortByCount(c.artist),
      _all:   skins,
      _counts: c,
    };
  }

  function loadFacets() {
    if (facets) return Promise.resolve(facets);
    return fetchJsonChain(CATALOG_URLS(localeKey()))
      .catch(function () {
        // Fall back to whatever Sony's SkinCollection has in memory.
        var coll = getCollection();
        if (coll && coll.models && coll.models.length) {
          var list = coll.models.map(function (m) {
            var a = (m.attributes || m);
            return { id: a.id, name: a.name, brief: a.brief,
                     studio: a.studio, artist: a.artist,
                     genre: a.genre, tags: a.tags || [] };
          });
          return { skins: list };
        }
        throw new Error("no source");
      })
      .then(function (data) {
        var list = (data && data.skins) ? data.skins : (Array.isArray(data) ? data : []);
        facets = buildFacets(list);
        return facets;
      })
      .catch(function () {
        facets = buildFacets([]);
        return facets;
      });
  }

  // ---------- 2. FILTER STATE + SONY ADAPTER -----------------------------

  var FACET_GROUPS = ["genre", "tag", "studio", "artist"];
  var state = { genre: null, tag: null, studio: null, artist: null, q: "" };

  function activeFilterCount() {
    var n = 0;
    FACET_GROUPS.forEach(function (g) { if (state[g]) n++; });
    if (state.q) n++;
    return n;
  }

  function matchSkin(s) {
    if (state.genre  && s.genre !== state.genre) return false;
    if (state.tag    && (s.tags || []).indexOf(state.tag) === -1) return false;
    if (state.studio && s.studio !== state.studio) return false;
    if (state.artist && s.artist !== state.artist) return false;
    if (state.q) {
      var q = state.q.toLowerCase();
      var hay = ((s.name || "") + " " + (s.brief || "") + " " +
                 (s.studio || "") + " " + (s.artist || "")).toLowerCase();
      if (hay.indexOf(q) === -1) return false;
    }
    return true;
  }

  // Apply current filter to Sony's SkinCollection so the arc redraws.
  // We snapshot the original models on first call; subsequent filter changes
  // re-filter against that snapshot.
  function applyToArc() {
    var coll = getCollection();
    if (!coll || !coll.models) return;
    if (!facets || !facets._all.length) return;

    var allowedSet = Object.create(null);
    facets._all.filter(matchSkin).forEach(function (s) { allowedSet[s.id] = 1; });

    if (!coll._fesOriginalModels) coll._fesOriginalModels = coll.models.slice();
    var src = coll._fesOriginalModels;
    var filtered = src.filter(function (m) {
      var id = (m && m.id) || (m && m.attributes && m.attributes.id);
      return allowedSet[id];
    });
    if (typeof coll.reset === "function") {
      coll.reset(filtered, { silent: true });
      coll._totalCount = filtered.length;
      coll.trigger("sync", coll, coll, { reset: true });
    }
  }

  // Sony header buttons live in the DOM but are visually hidden by our CSS.
  // We forward taps via jQuery vclick so jQM's transition + back-stack works.
  function clickSonyButton(selector) {
    var btn = document.querySelector(selector);
    if (!btn) return false;
    if (window.$ && $.fn && $.fn.trigger) {
      try { $(btn).trigger("vclick"); return true; } catch (_) {}
    }
    try { btn.click(); return true; } catch (_) {}
    return false;
  }

  function navigateToCloset() {
    if (clickSonyButton("#page-home-store .home-header .left-svg-button button.command-navigate")) return;
    if (window.CDP && CDP.Framework && CDP.Framework.Router) {
      try { CDP.Framework.Router.navigate("/templates/home-closet.html", "slant", false); } catch (_) {}
    }
  }

  function navigateToCatalog() {
    if (clickSonyButton("#page-home-closet .home-header .left-svg-button button.command-back")) return;
    if (window.CDP && CDP.Framework && CDP.Framework.Router) {
      try { CDP.Framework.Router.back(); return; } catch (_) {}
    }
    history.back();
  }

  function clickClosetHeaderButton(rightButtonSelector) {
    clickSonyButton("#page-home-closet .home-header .right-buttons " + rightButtonSelector);
  }

  // ---------- 3. VIEW: toolbar, pills, sheet, suggest --------------------

  var TOOLBAR_ID = "fes-catalog-toolbar";
  var SHEET_ID = "fes-catalog-filter-sheet";

  function escapeHtml(s) {
    return String(s == null ? "" : s).replace(/[&<>"']/g, function (c) {
      return ({ "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;", "'": "&#39;" })[c];
    });
  }

  function pretty(s) { return String(s || "").replace(/-/g, " "); }

  // SVG paths for toolbar icons (kept inline so the toolbar has zero asset deps).
  var ICON = {
    closet:  '<svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round"><path d="M11 8.5a1.5 1.5 0 1 1 2.6-1"/><path d="M12 9v3"/><path d="M3 19l9-7 9 7"/><path d="M3 19h18"/></svg>',
    catalog: '<svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round"><rect x="4" y="3" width="16" height="18" rx="1.5"/><path d="M9 3v18"/><path d="M12 7h5"/><path d="M12 11h5"/></svg>',
    search:  '<svg viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round"><circle cx="11" cy="11" r="7"/><path d="m20 20-3.5-3.5"/></svg>',
    filter:  '<svg viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round"><path d="M4 6h16"/><path d="M7 12h10"/><path d="M10 18h4"/></svg>',
    add:     '<svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round"><path d="M12 5v14"/><path d="M5 12h14"/></svg>',
    settings:'<svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="3"/><path d="M19.4 15a1.7 1.7 0 0 0 .3 1.8l.1.1a2 2 0 1 1-2.8 2.8l-.1-.1a1.7 1.7 0 0 0-1.8-.3 1.7 1.7 0 0 0-1 1.5V21a2 2 0 1 1-4 0v-.1a1.7 1.7 0 0 0-1.1-1.5 1.7 1.7 0 0 0-1.8.3l-.1.1a2 2 0 1 1-2.8-2.8l.1-.1a1.7 1.7 0 0 0 .3-1.8 1.7 1.7 0 0 0-1.5-1H3a2 2 0 1 1 0-4h.1a1.7 1.7 0 0 0 1.5-1.1 1.7 1.7 0 0 0-.3-1.8l-.1-.1a2 2 0 1 1 2.8-2.8l.1.1a1.7 1.7 0 0 0 1.8.3H9a1.7 1.7 0 0 0 1-1.5V3a2 2 0 1 1 4 0v.1a1.7 1.7 0 0 0 1 1.5 1.7 1.7 0 0 0 1.8-.3l.1-.1a2 2 0 1 1 2.8 2.8l-.1.1a1.7 1.7 0 0 0-.3 1.8V9a1.7 1.7 0 0 0 1.5 1H21a2 2 0 1 1 0 4h-.1a1.7 1.7 0 0 0-1.5 1z"/></svg>',
  };

  function buildToolbar() {
    var existing = document.getElementById(TOOLBAR_ID);
    if (existing) return existing;
    var bar = document.createElement("div");
    bar.id = TOOLBAR_ID;
    bar.dataset.fctMode = "catalog";
    bar.innerHTML =
      '<div class="fct-row1" data-fct-row-catalog>' +
        '<button class="fct-icon-btn" id="fct-closet-btn" aria-label="Closet" type="button">' + ICON.closet + '</button>' +
        '<div class="fct-title">Catalog</div>' +
        '<button class="fct-icon-btn" id="fct-search-btn" aria-label="Search" type="button">' + ICON.search + '</button>' +
        '<button class="fct-icon-btn" id="fct-filter-btn" aria-label="Filter" type="button">' + ICON.filter +
          '<span class="fct-badge" data-active-count hidden></span>' +
        '</button>' +
      '</div>' +
      '<div class="fct-row1" data-fct-row-closet hidden>' +
        '<button class="fct-icon-btn" id="fct-catalog-btn" aria-label="Catalog" type="button">' + ICON.catalog + '</button>' +
        '<div class="fct-title">Closet</div>' +
        '<button class="fct-icon-btn" id="fct-add-btn" aria-label="Add" type="button">' + ICON.add + '</button>' +
        '<button class="fct-icon-btn" id="fct-settings-btn" aria-label="Settings" type="button">' + ICON.settings + '</button>' +
      '</div>' +
      '<div class="fct-search-row" hidden>' +
        '<input type="search" class="fct-search-input" placeholder="Search skins, creators, tags…" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false">' +
        '<button class="fct-search-clear" type="button" aria-label="Clear">×</button>' +
      '</div>' +
      '<div class="fct-suggest" data-suggest hidden></div>' +
      '<div class="fct-pill-row" data-pill-row></div>';
    document.body.appendChild(bar);
    return bar;
  }

  function buildSheet() {
    var existing = document.getElementById(SHEET_ID);
    if (existing) return existing;
    var sheet = document.createElement("div");
    sheet.id = SHEET_ID;
    sheet.hidden = true;
    sheet.innerHTML =
      '<div class="fcs-backdrop"></div>' +
      '<div class="fcs-panel">' +
        '<div class="fcs-head">' +
          '<div class="fcs-title">Filters</div>' +
          '<button class="fcs-reset" type="button">Reset</button>' +
          '<button class="fcs-close" aria-label="Close" type="button">×</button>' +
        '</div>' +
        '<div class="fcs-body">' +
          FACET_GROUPS.map(function (g) {
            return '<section class="fcs-group" data-group="' + g + '">' +
                     '<h4>' + g.charAt(0).toUpperCase() + g.slice(1) + '</h4>' +
                     '<div class="fcs-chips"></div>' +
                   '</section>';
          }).join("") +
        '</div>' +
        '<div class="fcs-foot">' +
          '<button class="fcs-apply" type="button">Done</button>' +
        '</div>' +
      '</div>';
    document.body.appendChild(sheet);
    return sheet;
  }

  function setToolbarMode(mode) {
    var bar = document.getElementById(TOOLBAR_ID);
    if (!bar) return;
    bar.dataset.fctMode = mode;
    var rowCat = bar.querySelector("[data-fct-row-catalog]");
    var rowClo = bar.querySelector("[data-fct-row-closet]");
    var pillRow = bar.querySelector("[data-pill-row]");
    var search = bar.querySelector(".fct-search-row");
    var suggest = bar.querySelector("[data-suggest]");
    if (mode === "closet") {
      rowCat.hidden = true; rowClo.hidden = false;
      pillRow.hidden = true; search.hidden = true; suggest.hidden = true;
    } else {
      rowCat.hidden = false; rowClo.hidden = true;
      pillRow.hidden = false;
    }
  }

  function attachTap(btn, fn) {
    // Direct on-element binding so jQM's global vclick capture can't swallow.
    btn.addEventListener("click", function (e) {
      e.preventDefault(); e.stopPropagation(); fn();
    });
    if (window.$ && $.fn) {
      $(btn).on("vclick", function (e) {
        e.preventDefault(); e.stopPropagation(); fn();
      });
    }
  }

  function renderPillRow() {
    var row = document.querySelector("#" + TOOLBAR_ID + " [data-pill-row]");
    if (!row || !facets) return;
    row.innerHTML = "";

    var allActive = !state.genre && !state.tag;
    var allBtn = document.createElement("button");
    allBtn.type = "button";
    allBtn.className = "fct-pill" + (allActive ? " is-active" : "");
    allBtn.textContent = "All";
    attachTap(allBtn, resetAll);
    row.appendChild(allBtn);

    facets.genre.forEach(function (g) {
      var b = document.createElement("button");
      b.type = "button";
      b.className = "fct-pill fct-pill-genre" + (state.genre === g.value ? " is-active" : "");
      b.innerHTML = '<span class="fct-pill-label">' + escapeHtml(pretty(g.value)) + '</span>' +
                    '<span class="fct-pill-count">' + g.count + '</span>';
      attachTap(b, function () { setSingle("genre", g.value); });
      row.appendChild(b);
    });

    facets.tag.slice(0, 20).forEach(function (t) {
      var b = document.createElement("button");
      b.type = "button";
      b.className = "fct-pill fct-pill-tag" + (state.tag === t.value ? " is-active" : "");
      b.innerHTML = '<span class="fct-pill-label">#' + escapeHtml(pretty(t.value)) + '</span>' +
                    '<span class="fct-pill-count">' + t.count + '</span>';
      attachTap(b, function () { setSingle("tag", t.value); });
      row.appendChild(b);
    });
  }

  function renderSheet() {
    var sheet = document.getElementById(SHEET_ID);
    if (!sheet || !facets) return;
    FACET_GROUPS.forEach(function (group) {
      var host = sheet.querySelector('[data-group="' + group + '"] .fcs-chips');
      if (!host) return;
      host.innerHTML = "";
      var values = facets[group] || [];
      if (!values.length) {
        host.innerHTML = '<span class="fcs-empty">—</span>';
        return;
      }
      values.forEach(function (v) {
        var b = document.createElement("button");
        b.type = "button";
        b.className = "fcs-chip" + (state[group] === v.value ? " is-active" : "");
        b.dataset.fcsGroup = group;
        b.dataset.fcsValue = v.value;
        b.innerHTML = '<span>' + escapeHtml(pretty(v.value)) + '</span>' +
                      '<span class="fcs-count">' + v.count + '</span>';
        host.appendChild(b);
      });
    });
  }

  function renderSuggest(query) {
    var box = document.querySelector("#" + TOOLBAR_ID + " [data-suggest]");
    if (!box || !facets) return;
    if (!query) { box.hidden = true; box.innerHTML = ""; return; }
    var ql = query.toLowerCase();
    var hits = [];
    var add = function (kind, list) {
      list.forEach(function (e) {
        if (e.value.toLowerCase().indexOf(ql) !== -1) {
          hits.push({ kind: kind, value: e.value, count: e.count });
        }
      });
    };
    add("tag", facets.tag);
    add("artist", facets.artist);
    add("studio", facets.studio);
    hits = hits.slice(0, 8);
    if (!hits.length) { box.hidden = true; return; }
    box.innerHTML = hits.map(function (h) {
      return '<button class="fct-sg-row" type="button" data-sg-kind="' + h.kind +
             '" data-sg-value="' + escapeHtml(h.value) + '">' +
               '<span class="fct-sg-kind">' + h.kind + '</span>' +
               '<span class="fct-sg-value">' + escapeHtml(pretty(h.value)) + '</span>' +
               '<span class="fct-sg-count">' + h.count + '</span>' +
             '</button>';
    }).join("");
    box.hidden = false;
  }

  function updateBadge() {
    var badge = document.querySelector("[data-active-count]");
    if (!badge) return;
    var n = activeFilterCount();
    if (n > 0) { badge.textContent = n; badge.hidden = false; }
    else { badge.hidden = true; }
  }

  // ---------- view event handlers ----------------------------------------

  function setSingle(group, value) {
    state[group] = state[group] === value ? null : value;
    refresh();
  }

  function resetAll() {
    FACET_GROUPS.forEach(function (g) { state[g] = null; });
    state.q = "";
    var input = document.querySelector(".fct-search-input");
    if (input) input.value = "";
    refresh();
  }

  function refresh() {
    applyToArc();
    renderPillRow();
    renderSheet();
    updateBadge();
  }

  function openSheet()  { renderSheet(); var s = document.getElementById(SHEET_ID); if (s) s.hidden = false; }
  function closeSheet() { var s = document.getElementById(SHEET_ID); if (s) s.hidden = true; }

  function bindToolbarEvents(toolbar, sheet) {
    if (toolbar.dataset.fctBound === "1") return;
    toolbar.dataset.fctBound = "1";

    // Top icon buttons - delegate through the toolbar.
    var onTap = function (e) {
      var t = e.target;
      if (!t || !t.closest) return;
      var hit = function (sel, fn) {
        if (t.closest(sel)) {
          e.preventDefault(); e.stopPropagation(); fn(); return true;
        }
        return false;
      };
      if (hit("#fct-closet-btn",   navigateToCloset)) return;
      if (hit("#fct-catalog-btn",  navigateToCatalog)) return;
      if (hit("#fct-add-btn",      function () { clickClosetHeaderButton(".button-add"); })) return;
      if (hit("#fct-settings-btn", function () { clickClosetHeaderButton(".button-settings"); })) return;
      if (hit("#fct-search-btn",   toggleSearch)) return;
      if (hit("#fct-filter-btn",   openSheet)) return;
      if (hit(".fct-search-clear", clearSearch)) return;
    };
    toolbar.addEventListener("click", onTap, true);
    if (window.$ && $.fn) $(toolbar).on("vclick", onTap);

    // Search input
    var input = toolbar.querySelector(".fct-search-input");
    var suggest = toolbar.querySelector("[data-suggest]");
    if (input) {
      var t = null;
      input.addEventListener("input", function (e) {
        clearTimeout(t);
        var v = e.target.value || "";
        renderSuggest(v);
        t = setTimeout(function () { state.q = v; refresh(); }, 180);
      });
      input.addEventListener("blur", function () {
        setTimeout(function () { if (suggest) suggest.hidden = true; }, 200);
      });
      input.addEventListener("focus", function () {
        if (input.value) renderSuggest(input.value);
      });
    }
    if (suggest) {
      suggest.addEventListener("click", function (e) {
        var row = e.target.closest("[data-sg-kind]");
        if (!row) return;
        e.preventDefault(); e.stopPropagation();
        state[row.dataset.sgKind] = row.dataset.sgValue;
        if (input) input.value = "";
        state.q = "";
        suggest.hidden = true;
        refresh();
      }, true);
    }

    // Bottom-sheet
    sheet.addEventListener("click", function (e) {
      if (e.target.closest(".fcs-close") ||
          e.target.closest(".fcs-apply") ||
          e.target.closest(".fcs-backdrop")) {
        closeSheet(); return;
      }
      if (e.target.closest(".fcs-reset")) { resetAll(); return; }
      var chip = e.target.closest("[data-fcs-group]");
      if (chip) {
        var group = chip.dataset.fcsGroup;
        var value = chip.dataset.fcsValue;
        state[group] = state[group] === value ? null : value;
        refresh();
      }
    });
  }

  function toggleSearch() {
    var bar = document.getElementById(TOOLBAR_ID);
    if (!bar) return;
    var row = bar.querySelector(".fct-search-row");
    if (!row) return;
    row.hidden = !row.hidden;
    if (!row.hidden) {
      var inp = row.querySelector(".fct-search-input");
      if (inp) inp.focus();
    } else {
      state.q = "";
      refresh();
    }
  }

  function clearSearch() {
    var bar = document.getElementById(TOOLBAR_ID);
    if (!bar) return;
    var input = bar.querySelector(".fct-search-input");
    if (input) input.value = "";
    state.q = "";
    refresh();
  }

  // ---------- 4. PAGE LIFECYCLE ------------------------------------------

  function isHomePage(id) {
    return id === "page-home-store" || id === "page-home-closet";
  }

  function detectActivePage() {
    return document.querySelector(".ui-page-active") ||
           document.getElementById("page-home-store");
  }

  var lastActiveId = null;

  function syncToolbarToActivePage() {
    var act = detectActivePage();
    if (!act) return;
    if (act.id === lastActiveId) return;
    lastActiveId = act.id;
    if (!isHomePage(act.id)) {
      hideToolbar();
      return;
    }
    showToolbarFor(act);
  }

  function hideToolbar() {
    var t = document.getElementById(TOOLBAR_ID);
    if (t) t.style.display = "none";
  }

  function showToolbarFor(page) {
    var toolbar = buildToolbar();
    var sheet = buildSheet();
    bindToolbarEvents(toolbar, sheet);
    toolbar.style.display = "";

    // Hide the legacy filter bar inside the home-store template if present.
    var legacy = document.getElementById("catalog-filter-bar");
    if (legacy && legacy.id !== TOOLBAR_ID) legacy.style.display = "none";

    if (page.id === "page-home-closet") {
      setToolbarMode("closet");
      return;
    }
    setToolbarMode("catalog");

    loadFacets().then(function () {
      renderPillRow();
      renderSheet();
      updateBadge();
      applyToArc();
      var coll = getCollection();
      if (coll && coll.on && !coll._fesPostSyncBound) {
        coll._fesPostSyncBound = true;
        coll.on("sync", function () {
          if (activeFilterCount() > 0) applyToArc();
        });
      }
    });
  }

  function init() {
    document.addEventListener("pageshow",       syncToolbarToActivePage);
    document.addEventListener("pagebeforeshow", syncToolbarToActivePage);
    document.addEventListener("pagehide", function () {
      setTimeout(syncToolbarToActivePage, 60);
    });

    // Watch jQM's per-page class toggle (ui-page-active) without observing
    // the entire body subtree (which would interfere with the arc's Hammer
    // recognizers as Sony's render churns DOM).
    var mo = new MutationObserver(syncToolbarToActivePage);
    var pages = document.querySelectorAll('[data-role="page"]');
    if (pages.length) {
      pages.forEach(function (p) {
        mo.observe(p, { attributes: true, attributeFilter: ["class"] });
      });
    } else {
      mo.observe(document.body, { childList: true });
    }

    // Initial-load fallback: jQM may not fire pageshow on the first page.
    var attempts = 0;
    var poll = setInterval(function () {
      syncToolbarToActivePage();
      if (lastActiveId || ++attempts > 40) clearInterval(poll);
    }, 250);
  }

  if (document.readyState === "complete" || document.readyState === "interactive") {
    init();
  } else {
    document.addEventListener("DOMContentLoaded", init);
  }
})();
