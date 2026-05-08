/* FES Catalog Filter
 * Adds studio/artist/tag/search filters to the existing Arc store.
 * Operates on the Backbone SkinCollection singleton (instance 2 = store).
 * No bundle modifications: hooks DOM events on #page-home-store.
 */
(function () {
  "use strict";

  var STORE_INSTANCE = 2;
  var snapshot = null; // { models: [...], totalCount: N }
  var state = { studio: null, artist: null, genre: null, tag: null, q: "" };
  var FACET_GROUPS = ["studio", "artist", "genre", "tag"];

  function getCollection() {
    try {
      return window.FES &&
        FES.Model &&
        FES.Model.SkinCollection &&
        FES.Model.SkinCollection.getInstance(STORE_INSTANCE);
    } catch (e) {
      return null;
    }
  }

  function ensureSnapshot(coll) {
    if (snapshot) return true;
    if (!coll || !coll.models) return false;
    if (coll.length === 0 || coll.length !== coll._totalCount) return false;
    snapshot = {
      models: coll.models.slice(),
      totalCount: coll._totalCount,
    };
    return true;
  }

  function distinct(values) {
    var s = Object.create(null);
    var out = [];
    values.forEach(function (v) {
      if (v && !s[v]) {
        s[v] = 1;
        out.push(v);
      }
    });
    out.sort(function (a, b) {
      return a.localeCompare(b);
    });
    return out;
  }

  function modelField(m, field) {
    if (!m) return "";
    if (typeof m.get === "function") {
      var v = m.get(field);
      if (v != null) return v;
    }
    if (m.attributes && m.attributes[field] != null) return m.attributes[field];
    return m[field] || "";
  }

  function modelTags(m) {
    var t = modelField(m, "tags");
    if (Array.isArray(t)) return t;
    return [];
  }

  function buildFacets(models) {
    var studios = [];
    var artists = [];
    var genres = [];
    var tags = {};
    models.forEach(function (m) {
      var s = modelField(m, "studio");
      var a = modelField(m, "artist");
      var g = modelField(m, "genre");
      if (s) studios.push(s);
      if (a) artists.push(a);
      if (g) genres.push(g);
      modelTags(m).forEach(function (t) {
        tags[t] = (tags[t] || 0) + 1;
      });
    });
    var tagList = Object.keys(tags).sort(function (a, b) {
      return tags[b] - tags[a] || a.localeCompare(b);
    });
    return {
      studio: distinct(studios),
      artist: distinct(artists),
      genre: distinct(genres),
      tag: tagList,
    };
  }

  function matchModel(m) {
    if (state.studio && modelField(m, "studio") !== state.studio) return false;
    if (state.artist && modelField(m, "artist") !== state.artist) return false;
    if (state.genre && modelField(m, "genre") !== state.genre) return false;
    if (state.tag && modelTags(m).indexOf(state.tag) === -1) return false;
    if (state.q) {
      var q = state.q.toLowerCase();
      var hay = [
        modelField(m, "name"),
        modelField(m, "brief"),
        modelField(m, "description"),
        modelField(m, "studio"),
        modelField(m, "artist"),
        modelTags(m).join(" "),
      ]
        .join(" ")
        .toLowerCase();
      if (hay.indexOf(q) === -1) return false;
    }
    return true;
  }

  function applyFilter() {
    var coll = getCollection();
    if (!coll) return;
    if (!ensureSnapshot(coll)) {
      // try again after the next sync
      coll.once && coll.once("sync", applyFilter);
      return;
    }
    var filtered = snapshot.models.filter(matchModel);
    coll.reset(filtered, { silent: true });
    coll._totalCount = filtered.length;
    coll.trigger("sync", coll, coll, { reset: true });
    toggleEmpty(filtered.length === 0 && activeFilterCount() > 0);
  }

  function toggleEmpty(show) {
    var el = document.querySelector("#page-home-store [data-catalog-empty]");
    var page = document.getElementById("page-home-store");
    if (!el) return;
    if (show) {
      el.removeAttribute("hidden");
      page && page.classList.add("catalog-empty-active");
    } else {
      el.setAttribute("hidden", "");
      page && page.classList.remove("catalog-empty-active");
    }
  }

  function renderChips(panel, facets) {
    FACET_GROUPS.forEach(function (group) {
      var host = panel.querySelector('[data-catalog-chips="' + group + '"]');
      if (!host) return;
      host.innerHTML = "";
      var values = facets[group] || [];
      values.forEach(function (v) {
        var b = document.createElement("button");
        b.className = "catalog-chip";
        b.type = "button";
        b.setAttribute("data-catalog-pick", group);
        b.setAttribute("data-value", v);
        if (state[group] === v) b.classList.add("active");
        b.textContent = v;
        host.appendChild(b);
      });
      if (values.length === 0) {
        var empty = document.createElement("span");
        empty.className = "catalog-chip-empty";
        empty.textContent = "—";
        host.appendChild(empty);
      }
    });
  }

  function refreshFacets(panel) {
    var coll = getCollection();
    var models =
      snapshot && snapshot.models ? snapshot.models : coll ? coll.models : [];
    renderChips(panel, buildFacets(models));
  }

  function activeFilterCount() {
    var n = 0;
    FACET_GROUPS.forEach(function (g) { if (state[g]) n++; });
    if (state.q) n++;
    return n;
  }

  function refreshActive(bar) {
    var host = bar.querySelector("[data-catalog-active]");
    var countBadge = bar.querySelector("[data-catalog-count]");
    if (!host || !countBadge) return;
    host.innerHTML = "";
    var entries = [];
    FACET_GROUPS.forEach(function (g) {
      if (state[g]) entries.push({ group: g, value: state[g] });
    });
    entries.forEach(function (e) {
      var pill = document.createElement("button");
      pill.type = "button";
      pill.className = "catalog-active-pill";
      pill.setAttribute("data-catalog-clear", e.group);
      pill.innerHTML =
        '<span class="catalog-active-pill-label">' +
        e.value.replace(/[<>&]/g, "") +
        '</span><span class="catalog-active-pill-x" aria-hidden="true">×</span>';
      host.appendChild(pill);
    });
    if (entries.length) host.removeAttribute("hidden");
    else host.setAttribute("hidden", "");

    var n = activeFilterCount();
    if (n > 0) {
      countBadge.textContent = String(n);
      countBadge.removeAttribute("hidden");
    } else {
      countBadge.setAttribute("hidden", "");
    }
  }

  function bind(page) {
    if (page.dataset.catalogFilterReady === "1") return;
    page.dataset.catalogFilterReady = "1";

    var bar = page.querySelector("#catalog-filter-bar");
    if (!bar) return;
    var panel = bar.querySelector("[data-catalog-panel]");
    var toggle = bar.querySelector("[data-catalog-toggle]");
    var input = bar.querySelector("[data-catalog-search]");
    var resetBtn = bar.querySelector("[data-catalog-reset]");

    toggle.addEventListener("click", function () {
      var open = panel.hasAttribute("hidden") ? false : true;
      if (open) {
        panel.setAttribute("hidden", "");
        toggle.setAttribute("aria-expanded", "false");
      } else {
        refreshFacets(panel);
        panel.removeAttribute("hidden");
        toggle.setAttribute("aria-expanded", "true");
      }
    });

    function resetAll() {
      FACET_GROUPS.forEach(function (g) { state[g] = null; });
      if (input) input.value = "";
      state.q = "";
      applyFilter();
      refreshFacets(panel);
      refreshActive(bar);
    }

    panel.addEventListener("click", function (e) {
      var t = e.target.closest("[data-catalog-pick]");
      if (t) {
        var group = t.getAttribute("data-catalog-pick");
        var val = t.getAttribute("data-value");
        state[group] = state[group] === val ? null : val;
        applyFilter();
        refreshFacets(panel);
        refreshActive(bar);
        return;
      }
      if (e.target.closest("[data-catalog-reset]")) resetAll();
    });

    // Empty-state reset lives outside the panel; bind on the whole bar.
    bar.addEventListener("click", function (e) {
      if (e.target.closest("[data-catalog-reset]")) {
        // panel handler already fires when the click is inside the panel;
        // this catches the empty-state button outside it.
        if (!panel.contains(e.target)) resetAll();
      }
    });

    var activeHost = bar.querySelector("[data-catalog-active]");
    if (activeHost) {
      activeHost.addEventListener("click", function (e) {
        var t = e.target.closest("[data-catalog-clear]");
        if (!t) return;
        var group = t.getAttribute("data-catalog-clear");
        state[group] = null;
        applyFilter();
        refreshFacets(panel);
        refreshActive(bar);
      });
    }

    if (input) {
      var debounce;
      input.addEventListener("input", function () {
        clearTimeout(debounce);
        debounce = setTimeout(function () {
          state.q = input.value.trim();
          applyFilter();
          refreshActive(bar);
        }, 180);
      });
    }
  }

  function init() {
    document.addEventListener("pageshow", function (ev) {
      var page = ev.target;
      if (!page || page.id !== "page-home-store") return;
      bind(page);
      // reset snapshot when collection identity changes between sessions
      var coll = getCollection();
      if (
        coll &&
        snapshot &&
        coll.length > 0 &&
        coll.models[0] !== snapshot.models[0]
      ) {
        snapshot = null;
      }
      // re-apply last filter if user comes back from detail
      if (coll && snapshot && activeFilterCount() > 0) {
        applyFilter();
      }
    });
  }

  if (document.readyState === "complete" || document.readyState === "interactive") {
    init();
  } else {
    document.addEventListener("DOMContentLoaded", init);
  }
})();
