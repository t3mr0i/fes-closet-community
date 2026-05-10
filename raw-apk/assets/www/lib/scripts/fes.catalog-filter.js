/* FES Catalog v2 — community filter shell.
 *
 * Replaces the legacy Sony home-header + the original filter bar with a
 * single floating top toolbar. Operates on Backbone SkinCollection (instance 2)
 * to actually filter, but builds the facet pills from the bundled catalog JSON
 * directly so the chip list shows up immediately, without waiting on Sony's
 * paginated sync.
 *
 * Public DOM contract: nothing. Selectors used by Sony's runtime
 * (.home-header, .skin-list-window, .info-and-controllers) are not modified;
 * the legacy .home-header is hidden via CSS only when Sony's #page-home-store
 * is the active page.
 */
(function () {
  "use strict";

  var STORE_INSTANCE = 2;
  var TOOLBAR_ID = "fes-catalog-toolbar";
  var SHEET_ID = "fes-catalog-filter-sheet";
  var state = { genre: null, tag: null, studio: null, artist: null, q: "" };
  var facets = null; // { genre:[{value,count}], tag:[...], studio:[...], artist:[...], _all:[skin,...] }
  var FACET_GROUPS = ["genre", "tag", "studio", "artist"];

  function getCollection() {
    try {
      return window.FES &&
        FES.Model &&
        FES.Model.SkinCollection &&
        FES.Model.SkinCollection.getInstance(STORE_INSTANCE);
    } catch (e) { return null; }
  }

  // Catalog JSON path follows Sony's locale convention.
  function localeKey() {
    try {
      var lang = (window.$ && $.i18n && $.i18n.language) || "en-US";
      var l = lang.toLowerCase();
      if (l === "ja-jp") return "ja-jp";
      if (l === "zh-cn" || l === "zh-sg") return "zh-cn";
      return "en-us";
    } catch (e) { return "en-us"; }
  }

  function fetchCatalog() {
    if (facets) return Promise.resolve(facets);
    var loc = localeKey();
    var urls = [
      "https://t3mr0i.github.io/fes-closet-community/api/store/skins-" + loc + ".json",
      "res/data/api/store/skins-" + loc + ".json",
      "/res/data/api/store/skins-" + loc + ".json",
    ];
    var attempt = function (i) {
      if (i >= urls.length) return Promise.reject(new Error("no catalog source"));
      return fetch(urls[i]).then(function (r) {
        if (!r.ok) throw new Error("status " + r.status);
        return r.json();
      }).catch(function () { return attempt(i + 1); });
    };
    return attempt(0).catch(function () {
      // Fallback: build from Sony's SkinCollection if it's already populated.
      var coll = getCollection();
      if (coll && coll.models && coll.models.length) {
        var list = coll.models.map(function (m) {
          var a = (m.attributes || m);
          return {
            id: a.id,
            name: a.name, brief: a.brief,
            studio: a.studio, artist: a.artist,
            genre: a.genre, tags: a.tags || []
          };
        });
        return { skins: list };
      }
      throw new Error("no source");
    }).then(function (data) {
      var list = (data && data.skins) ? data.skins : (Array.isArray(data) ? data : []);
      var counts = { genre: {}, tag: {}, studio: {}, artist: {} };
      list.forEach(function (s) {
        if (s.genre) counts.genre[s.genre] = (counts.genre[s.genre] || 0) + 1;
        (s.tags || []).forEach(function (t) {
          counts.tag[t] = (counts.tag[t] || 0) + 1;
        });
        if (s.studio) counts.studio[s.studio] = (counts.studio[s.studio] || 0) + 1;
        if (s.artist) counts.artist[s.artist] = (counts.artist[s.artist] || 0) + 1;
      });
      function sortByCount(o) {
        return Object.keys(o)
          .map(function (k) { return { value: k, count: o[k] }; })
          .sort(function (a, b) { return b.count - a.count || a.value.localeCompare(b.value); });
      }
      facets = {
        genre: sortByCount(counts.genre),
        tag: sortByCount(counts.tag),
        studio: sortByCount(counts.studio),
        artist: sortByCount(counts.artist),
        _all: list,
      };
      return facets;
    }).catch(function () {
      // fall back to empty facets
      facets = { genre: [], tag: [], studio: [], artist: [], _all: [] };
      return facets;
    });
  }

  function matchSkin(s) {
    if (state.genre && s.genre !== state.genre) return false;
    if (state.tag && (s.tags || []).indexOf(state.tag) === -1) return false;
    if (state.studio && s.studio !== state.studio) return false;
    if (state.artist && s.artist !== state.artist) return false;
    if (state.q) {
      var q = state.q.toLowerCase();
      var hay = ((s.name || "") + " " + (s.brief || "") + " " + (s.studio || "") + " " + (s.artist || "")).toLowerCase();
      if (hay.indexOf(q) === -1) return false;
    }
    return true;
  }

  // Apply state to Sony's SkinCollection so the arc reflects the filter.
  function applyToArc() {
    var coll = getCollection();
    if (!coll || !coll.models) return;
    if (!facets || !facets._all.length) return;
    // Build a model-id whitelist from the catalog JSON
    var allowed = facets._all.filter(matchSkin).map(function (s) { return s.id; });
    var allowedSet = Object.create(null);
    allowed.forEach(function (id) { allowedSet[id] = 1; });

    // We can't fully replace coll.models because Sony's sync re-populates it,
    // but we can re-filter it in place: keep models whose id is in the allowed
    // set, and trigger a sync event so the arc redraws.
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
    updateEmptyState(filtered.length === 0);
  }

  function updateEmptyState(empty) {
    var page = document.getElementById("page-home-store");
    if (!page) return;
    if (empty) page.classList.add("catalog-empty-active");
    else page.classList.remove("catalog-empty-active");
  }

  function activeFilterCount() {
    var n = 0;
    FACET_GROUPS.forEach(function (g) { if (state[g]) n++; });
    if (state.q) n++;
    return n;
  }

  // ---------- DOM ----------
  function escapeHtml(s) {
    return String(s == null ? "" : s).replace(/[&<>"']/g, function (c) {
      return ({ "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;", "'": "&#39;" })[c];
    });
  }

  function pretty(s) { return String(s || "").replace(/-/g, " "); }

  function buildToolbar() {
    if (document.getElementById(TOOLBAR_ID)) return document.getElementById(TOOLBAR_ID);
    var bar = document.createElement("div");
    bar.id = TOOLBAR_ID;
    bar.dataset.fctMode = "catalog";
    bar.innerHTML =
      // CATALOG MODE
      '<div class="fct-row1" data-fct-row-catalog>' +
        '<button class="fct-icon-btn" id="fct-closet-btn" aria-label="Closet" type="button">' +
          '<svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round"><path d="M11 8.5a1.5 1.5 0 1 1 2.6-1"/><path d="M12 9v3"/><path d="M3 19l9-7 9 7"/><path d="M3 19h18"/></svg>' +
        '</button>' +
        '<div class="fct-title">Catalog</div>' +
        '<button class="fct-icon-btn" id="fct-search-btn" aria-label="Search" type="button">' +
          '<svg viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round"><circle cx="11" cy="11" r="7"/><path d="m20 20-3.5-3.5"/></svg>' +
        '</button>' +
        '<button class="fct-icon-btn" id="fct-filter-btn" aria-label="Filter" type="button">' +
          '<svg viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round"><path d="M4 6h16"/><path d="M7 12h10"/><path d="M10 18h4"/></svg>' +
          '<span class="fct-badge" data-active-count hidden></span>' +
        '</button>' +
      '</div>' +
      // CLOSET MODE
      '<div class="fct-row1" data-fct-row-closet hidden>' +
        '<button class="fct-icon-btn" id="fct-catalog-btn" aria-label="Catalog" type="button">' +
          '<svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round"><rect x="4" y="3" width="16" height="18" rx="1.5"/><path d="M9 3v18"/><path d="M12 7h5"/><path d="M12 11h5"/></svg>' +
        '</button>' +
        '<div class="fct-title">Closet</div>' +
        '<button class="fct-icon-btn" id="fct-add-btn" aria-label="Add" type="button">' +
          '<svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round"><path d="M12 5v14"/><path d="M5 12h14"/></svg>' +
        '</button>' +
        '<button class="fct-icon-btn" id="fct-settings-btn" aria-label="Settings" type="button">' +
          '<svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="3"/><path d="M19.4 15a1.7 1.7 0 0 0 .3 1.8l.1.1a2 2 0 1 1-2.8 2.8l-.1-.1a1.7 1.7 0 0 0-1.8-.3 1.7 1.7 0 0 0-1 1.5V21a2 2 0 1 1-4 0v-.1a1.7 1.7 0 0 0-1.1-1.5 1.7 1.7 0 0 0-1.8.3l-.1.1a2 2 0 1 1-2.8-2.8l.1-.1a1.7 1.7 0 0 0 .3-1.8 1.7 1.7 0 0 0-1.5-1H3a2 2 0 1 1 0-4h.1a1.7 1.7 0 0 0 1.5-1.1 1.7 1.7 0 0 0-.3-1.8l-.1-.1a2 2 0 1 1 2.8-2.8l.1.1a1.7 1.7 0 0 0 1.8.3H9a1.7 1.7 0 0 0 1-1.5V3a2 2 0 1 1 4 0v.1a1.7 1.7 0 0 0 1 1.5 1.7 1.7 0 0 0 1.8-.3l.1-.1a2 2 0 1 1 2.8 2.8l-.1.1a1.7 1.7 0 0 0-.3 1.8V9a1.7 1.7 0 0 0 1.5 1H21a2 2 0 1 1 0 4h-.1a1.7 1.7 0 0 0-1.5 1z"/></svg>' +
        '</button>' +
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

  function setToolbarMode(mode) {
    var bar = document.getElementById(TOOLBAR_ID);
    if (!bar) return;
    bar.dataset.fctMode = mode;
    var rowCat = bar.querySelector("[data-fct-row-catalog]");
    var rowClo = bar.querySelector("[data-fct-row-closet]");
    var pillRow = bar.querySelector("[data-pill-row]");
    var search = bar.querySelector(".fct-search-row");
    if (mode === "closet") {
      rowCat.hidden = true;
      rowClo.hidden = false;
      pillRow.hidden = true;
      if (search) search.hidden = true;
    } else {
      rowCat.hidden = false;
      rowClo.hidden = true;
      pillRow.hidden = false;
    }
  }

  function buildSheet() {
    if (document.getElementById(SHEET_ID)) return;
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
          '<section class="fcs-group" data-group="genre"><h4>Genre</h4><div class="fcs-chips"></div></section>' +
          '<section class="fcs-group" data-group="tag"><h4>Tag</h4><div class="fcs-chips"></div></section>' +
          '<section class="fcs-group" data-group="studio"><h4>Studio</h4><div class="fcs-chips"></div></section>' +
          '<section class="fcs-group" data-group="artist"><h4>Artist</h4><div class="fcs-chips"></div></section>' +
        '</div>' +
        '<div class="fcs-foot">' +
          '<button class="fcs-apply" type="button">Done</button>' +
        '</div>' +
      '</div>';
    document.body.appendChild(sheet);
    return sheet;
  }

  function attachPillTap(btn, fn) {
    // Direct on-element binding so jQuery Mobile's global vclick capture
    // can't swallow the event before us.
    btn.addEventListener("click", function (e) {
      e.preventDefault();
      e.stopPropagation();
      fn();
    });
    if (window.$ && $.fn) {
      $(btn).on("vclick", function (e) {
        e.preventDefault();
        e.stopPropagation();
        fn();
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
    attachPillTap(allBtn, resetAll);
    row.appendChild(allBtn);

    // Genres first (typically 5-10)
    facets.genre.forEach(function (g) {
      var b = document.createElement("button");
      b.type = "button";
      b.className = "fct-pill fct-pill-genre" + (state.genre === g.value ? " is-active" : "");
      b.innerHTML =
        '<span class="fct-pill-label">' + escapeHtml(pretty(g.value)) + "</span>" +
        '<span class="fct-pill-count">' + g.count + "</span>";
      attachPillTap(b, function () { setGenre(g.value); });
      row.appendChild(b);
    });

    // Then top tags
    var tagLimit = 20;
    facets.tag.slice(0, tagLimit).forEach(function (t) {
      var b = document.createElement("button");
      b.type = "button";
      b.className = "fct-pill fct-pill-tag" + (state.tag === t.value ? " is-active" : "");
      b.innerHTML =
        '<span class="fct-pill-label">#' + escapeHtml(pretty(t.value)) + "</span>" +
        '<span class="fct-pill-count">' + t.count + "</span>";
      attachPillTap(b, function () { toggleFacet("tag", t.value); });
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
        b.innerHTML =
          '<span>' + escapeHtml(pretty(v.value)) + "</span>" +
          '<span class="fcs-count">' + v.count + "</span>";
        host.appendChild(b);
      });
    });
  }

  function updateBadge() {
    var badge = document.querySelector("[data-active-count]");
    if (!badge) return;
    var n = activeFilterCount();
    if (n > 0) {
      badge.textContent = n;
      badge.hidden = false;
    } else {
      badge.hidden = true;
    }
  }

  function openSheet() {
    renderSheet();
    var sheet = document.getElementById(SHEET_ID);
    if (sheet) sheet.hidden = false;
  }
  function closeSheet() {
    var sheet = document.getElementById(SHEET_ID);
    if (sheet) sheet.hidden = true;
  }

  function setGenre(v) {
    state.genre = state.genre === v || !v ? null : v;
    if (v === "") state.genre = null;
    applyAndRefresh();
  }

  function toggleFacet(group, value) {
    state[group] = state[group] === value ? null : value;
    applyAndRefresh();
  }

  function resetAll() {
    FACET_GROUPS.forEach(function (g) { state[g] = null; });
    state.q = "";
    var input = document.querySelector(".fct-search-input");
    if (input) input.value = "";
    applyAndRefresh();
  }

  function applyAndRefresh() {
    applyToArc();
    renderPillRow();
    renderSheet();
    updateBadge();
  }

  function bindEvents(toolbar, sheet) {
    if (toolbar.dataset.fctBound === "1") return;
    toolbar.dataset.fctBound = "1";

    var handleToolbarTap = function (e) {
      var target = e.target;
      if (!target || !target.closest) return;

      var resetBtn = target.closest("[data-fct-reset]");
      if (resetBtn) {
        e.preventDefault();
        e.stopPropagation();
        resetAll();
        return;
      }
      var g = target.closest("[data-fct-genre]");
      if (g) {
        e.preventDefault();
        e.stopPropagation();
        setGenre(g.dataset.fctGenre);
        return;
      }
      var tag = target.closest("[data-fct-tag]");
      if (tag) {
        e.preventDefault();
        e.stopPropagation();
        toggleFacet("tag", tag.dataset.fctTag);
        return;
      }
      if (target.closest("#fct-closet-btn")) {
        e.preventDefault();
        e.stopPropagation();
        navigateToCloset();
        return;
      }
      if (target.closest("#fct-back-btn") || target.closest("#fct-catalog-btn")) {
        e.preventDefault();
        e.stopPropagation();
        navigateBackToCatalog();
        return;
      }
      if (target.closest("#fct-add-btn")) {
        e.preventDefault();
        e.stopPropagation();
        triggerSonyClosetButton(".button-add");
        return;
      }
      if (target.closest("#fct-settings-btn")) {
        e.preventDefault();
        e.stopPropagation();
        triggerSonyClosetButton(".button-settings");
        return;
      }
      if (target.closest("#fct-search-btn")) {
        e.preventDefault();
        e.stopPropagation();
        var row = toolbar.querySelector(".fct-search-row");
        if (row) {
          row.hidden = !row.hidden;
          if (!row.hidden) {
            var inp = row.querySelector(".fct-search-input");
            inp && inp.focus();
          } else {
            state.q = "";
            applyAndRefresh();
          }
        }
        return;
      }
      if (target.closest("#fct-filter-btn")) {
        e.preventDefault();
        e.stopPropagation();
        openSheet();
        return;
      }
      if (target.closest(".fct-search-clear")) {
        e.preventDefault();
        e.stopPropagation();
        var input = toolbar.querySelector(".fct-search-input");
        if (input) input.value = "";
        state.q = "";
        applyAndRefresh();
        return;
      }
    };
    // Capture-phase click + vclick (jQuery Mobile's virtual click) so we win
    // against any framework-level swallow. Both fire on real taps in WKWebView.
    toolbar.addEventListener("click", handleToolbarTap, true);
    if (window.$ && $.fn) {
      $(toolbar).on("vclick", handleToolbarTap);
    }

    var searchInput = toolbar.querySelector(".fct-search-input");
    var suggestBox = toolbar.querySelector("[data-suggest]");
    function renderSuggest(q) {
      if (!suggestBox || !facets) return;
      if (!q || q.length < 1) { suggestBox.hidden = true; suggestBox.innerHTML = ""; return; }
      var ql = q.toLowerCase();
      var hits = [];
      // tags
      facets.tag.forEach(function (t) {
        if (t.value.toLowerCase().indexOf(ql) !== -1)
          hits.push({ kind: "tag", value: t.value, count: t.count });
      });
      // artists
      facets.artist.forEach(function (a) {
        if (a.value.toLowerCase().indexOf(ql) !== -1)
          hits.push({ kind: "artist", value: a.value, count: a.count });
      });
      // studios
      facets.studio.forEach(function (s) {
        if (s.value.toLowerCase().indexOf(ql) !== -1)
          hits.push({ kind: "studio", value: s.value, count: s.count });
      });
      hits = hits.slice(0, 8);
      if (!hits.length) { suggestBox.hidden = true; return; }
      suggestBox.innerHTML = hits.map(function (h) {
        return '<button class="fct-sg-row" type="button" data-sg-kind="' + h.kind + '" data-sg-value="' + escapeHtml(h.value) + '">' +
                 '<span class="fct-sg-kind">' + h.kind + '</span>' +
                 '<span class="fct-sg-value">' + escapeHtml(pretty(h.value)) + '</span>' +
                 '<span class="fct-sg-count">' + h.count + '</span>' +
               '</button>';
      }).join("");
      suggestBox.hidden = false;
    }
    if (searchInput) {
      var debounce = null;
      searchInput.addEventListener("input", function (e) {
        clearTimeout(debounce);
        var v = e.target.value || "";
        renderSuggest(v);
        debounce = setTimeout(function () {
          state.q = v;
          applyAndRefresh();
        }, 180);
      });
      searchInput.addEventListener("blur", function () {
        // delay so click on a suggestion still registers
        setTimeout(function () { if (suggestBox) suggestBox.hidden = true; }, 200);
      });
      searchInput.addEventListener("focus", function () {
        if (searchInput.value) renderSuggest(searchInput.value);
      });
    }
    if (suggestBox) {
      suggestBox.addEventListener("click", function (e) {
        var row = e.target.closest("[data-sg-kind]");
        if (!row) return;
        e.preventDefault();
        e.stopPropagation();
        var kind = row.dataset.sgKind;
        var val = row.dataset.sgValue;
        if (kind === "tag") state.tag = val;
        else if (kind === "artist") state.artist = val;
        else if (kind === "studio") state.studio = val;
        if (searchInput) searchInput.value = "";
        state.q = "";
        suggestBox.hidden = true;
        applyAndRefresh();
      }, true);
    }

    sheet.addEventListener("click", function (e) {
      if (e.target.closest(".fcs-close") || e.target.closest(".fcs-apply") || e.target.closest(".fcs-backdrop")) {
        closeSheet();
        return;
      }
      if (e.target.closest(".fcs-reset")) {
        resetAll();
        return;
      }
      var chip = e.target.closest("[data-fcs-group]");
      if (chip) {
        toggleFacet(chip.dataset.fcsGroup, chip.dataset.fcsValue);
      }
    });
  }

  function pageVisibilityCSSHook(visible) {
    document.documentElement.classList.toggle("fes-catalog-shell", !!visible);
  }

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
      try { CDP.Framework.Router.navigate("/templates/home-closet.html", "slant", false); return; } catch (_) {}
    }
  }

  function navigateBackToCatalog() {
    if (clickSonyButton("#page-home-closet .home-header .left-svg-button button.command-back")) return;
    if (window.CDP && CDP.Framework && CDP.Framework.Router) {
      try { CDP.Framework.Router.back(); return; } catch (_) {}
    }
    history.back();
  }

  function triggerSonyClosetButton(selector) {
    clickSonyButton("#page-home-closet .home-header .right-buttons " + selector);
  }

  function showToolbarFor(page) {
    var toolbar = buildToolbar();
    var sheet = buildSheet();
    bindEvents(toolbar, sheet);
    toolbar.style.display = "";
    pageVisibilityCSSHook(true);
    var legacy = document.getElementById("catalog-filter-bar");
    if (legacy && legacy.id !== TOOLBAR_ID) legacy.style.display = "none";

    if (page.id === "page-home-closet") {
      setToolbarMode("closet");
      return;
    }
    setToolbarMode("catalog");

    fetchCatalog().then(function () {
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

  function hideToolbar() {
    pageVisibilityCSSHook(false);
    var t = document.getElementById(TOOLBAR_ID);
    if (t) t.style.display = "none";
  }

  function detectActivePage() {
    // Active jQuery Mobile page has aria-hidden="false" or class ui-page-active
    var act = document.querySelector(".ui-page-active") ||
              document.querySelector('[data-role="page"]:not([style*="display: none"])');
    if (!act) {
      // Fallback: check #page-home-store visibility
      act = document.getElementById("page-home-store");
    }
    return act;
  }

  function isHomePage(id) {
    return id === "page-home-store" || id === "page-home-closet";
  }

  var lastActiveId = null;

  function syncToolbarToActivePage() {
    var act = detectActivePage();
    if (!act) return;
    var id = act.id;
    if (id === lastActiveId) return;
    if (!isHomePage(id)) {
      hideToolbar();
      lastActiveId = id;
      return;
    }
    showToolbarFor(act);
    lastActiveId = id;
  }

  function init() {
    document.addEventListener("pageshow", syncToolbarToActivePage);
    document.addEventListener("pagebeforeshow", syncToolbarToActivePage);
    document.addEventListener("pagehide", function () {
      setTimeout(syncToolbarToActivePage, 60);
    });

    // Robust fallback: watch ONLY the page-level class changes (ui-page-active),
    // not the entire subtree. Subtree:true causes the arc/Hammer recognizers
    // to glitch because every Sony render triggers a sync.
    var mo = new MutationObserver(function () { syncToolbarToActivePage(); });
    var pages = document.querySelectorAll('[data-role="page"]');
    if (pages.length) {
      pages.forEach(function (p) {
        mo.observe(p, { attributes: true, attributeFilter: ["class"] });
      });
    } else {
      // pages not in DOM yet; observe direct children of body for additions
      mo.observe(document.body, { childList: true });
    }

    // Initial-load: poll until the catalog is in the DOM.
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
