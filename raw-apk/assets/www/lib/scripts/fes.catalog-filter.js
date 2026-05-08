/* FES Catalog Filter
 * Adds studio/artist/tag/search filters to the existing Arc store.
 * Operates on the Backbone SkinCollection singleton (instance 2 = store).
 * No bundle modifications: hooks DOM events on #page-home-store.
 */
(function () {
  "use strict";

  var STORE_INSTANCE = 2;
  var snapshot = null; // { models: [...], totalCount: N }
  var state = { studio: null, artist: null, genre: null, tag: null };
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
    var studios = {};
    var artists = {};
    var genres = {};
    var tags = {};
    models.forEach(function (m) {
      var s = modelField(m, "studio");
      var a = modelField(m, "artist");
      var g = modelField(m, "genre");
      if (s) studios[s] = (studios[s] || 0) + 1;
      if (a) artists[a] = (artists[a] || 0) + 1;
      if (g) genres[g] = (genres[g] || 0) + 1;
      modelTags(m).forEach(function (t) {
        tags[t] = (tags[t] || 0) + 1;
      });
    });
    function sortByCount(obj) {
      return Object.keys(obj).sort(function (a, b) {
        return obj[b] - obj[a] || a.localeCompare(b);
      });
    }
    return {
      studio: sortByCount(studios),
      artist: sortByCount(artists),
      genre: sortByCount(genres),
      tag: sortByCount(tags),
      _counts: { studio: studios, artist: artists, genre: genres, tag: tags },
    };
  }

  function matchModel(m) {
    if (state.studio && modelField(m, "studio") !== state.studio) return false;
    if (state.artist && modelField(m, "artist") !== state.artist) return false;
    if (state.genre && modelField(m, "genre") !== state.genre) return false;
    if (state.tag && modelTags(m).indexOf(state.tag) === -1) return false;
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
      var counts = (facets._counts && facets._counts[group]) || {};
      values.forEach(function (v) {
        var b = document.createElement("button");
        b.className = "catalog-chip";
        b.type = "button";
        b.setAttribute("data-catalog-pick", group);
        b.setAttribute("data-value", v);
        if (state[group] === v) b.classList.add("active");
        var label = String(v).replace(/-/g, " ");
        var n = counts[v] || 0;
        b.innerHTML =
          '<span class="catalog-chip-label">' +
          label.replace(/&/g, "&amp;").replace(/</g, "&lt;") +
          "</span>" +
          (n ? '<span class="catalog-chip-count">' + n + "</span>" : "");
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
    return n;
  }

  function renderGenreRow(row) {
    if (!row) return;
    var coll = getCollection();
    var models =
      snapshot && snapshot.models ? snapshot.models : coll ? coll.models : [];
    var facets = buildFacets(models);
    var genres = facets.genre || [];

    // wipe existing content except the persistent "All" pill
    row.innerHTML = "";
    var all = document.createElement("button");
    all.type = "button";
    all.className =
      "catalog-genre-pill" + (state.genre ? "" : " active");
    all.setAttribute("data-catalog-genre", "");
    all.textContent = "All";
    row.appendChild(all);

    var gCounts = (facets._counts && facets._counts.genre) || {};
    genres.forEach(function (g) {
      var b = document.createElement("button");
      b.type = "button";
      b.className =
        "catalog-genre-pill" + (state.genre === g ? " active" : "");
      b.setAttribute("data-catalog-genre", g);
      var n = gCounts[g] || 0;
      b.innerHTML =
        '<span class="catalog-chip-label">' +
        String(g).replace(/-/g, " ").replace(/&/g, "&amp;").replace(/</g, "&lt;") +
        "</span>" +
        (n ? '<span class="catalog-chip-count">' + n + "</span>" : "");
      row.appendChild(b);
    });

    var more = document.createElement("button");
    more.type = "button";
    more.className =
      "catalog-genre-pill catalog-genre-pill-more" +
      (state.studio || state.artist || state.tag ? " active" : "");
    more.setAttribute("data-catalog-more", "");
    more.textContent = "+ Filter";
    row.appendChild(more);
  }

  function bind(page) {
    if (page.dataset.catalogFilterReady === "1") return;
    page.dataset.catalogFilterReady = "1";

    var bar = page.querySelector("#catalog-filter-bar");
    if (!bar) return;
    // Hoist the bar out of the legacy 50%-wide .main-page container so it can
    // span the full viewport. Append directly to the page root.
    if (bar.parentNode !== page) {
      page.appendChild(bar);
      bar.classList.add("catalog-filter-bar--hoisted");
    }
    var panel = bar.querySelector("[data-catalog-panel]");
    var genreRow = bar.querySelector("[data-catalog-genre-row]");

    function resetAll() {
      FACET_GROUPS.forEach(function (g) { state[g] = null; });
      applyFilter();
      renderGenreRow(genreRow);
      if (panel) {
        refreshFacets(panel);
        panel.setAttribute("hidden", "");
      }
    }

    function openPanel() {
      if (!panel) return;
      refreshFacets(panel);
      panel.removeAttribute("hidden");
    }
    function closePanel() {
      if (panel) panel.setAttribute("hidden", "");
    }

    if (genreRow) {
      genreRow.addEventListener("click", function (e) {
        var more = e.target.closest("[data-catalog-more]");
        if (more) {
          if (panel && panel.hasAttribute("hidden")) openPanel();
          else closePanel();
          return;
        }
        var pill = e.target.closest("[data-catalog-genre]");
        if (!pill) return;
        var v = pill.getAttribute("data-catalog-genre");
        state.genre = v && state.genre !== v ? v : null;
        applyFilter();
        renderGenreRow(genreRow);
      });
    }

    if (panel) {
      panel.addEventListener("click", function (e) {
        var t = e.target.closest("[data-catalog-pick]");
        if (t) {
          var group = t.getAttribute("data-catalog-pick");
          var val = t.getAttribute("data-value");
          state[group] = state[group] === val ? null : val;
          applyFilter();
          refreshFacets(panel);
          renderGenreRow(genreRow);
          return;
        }
        if (e.target.closest("[data-catalog-reset]")) resetAll();
      });
    }

    bar.addEventListener("click", function (e) {
      if (e.target.closest("[data-catalog-reset]")) {
        if (!panel || !panel.contains(e.target)) resetAll();
      }
    });
  }

  function init() {
    document.addEventListener("pageshow", function (ev) {
      var page = ev.target;
      if (!page || page.id !== "page-home-store") return;
      bind(page);
      var bar = page.querySelector("#catalog-filter-bar");
      var genreRow = bar && bar.querySelector("[data-catalog-genre-row]");
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
      // populate the genre row once the collection is ready
      if (coll) {
        if (snapshot) renderGenreRow(genreRow);
        else coll.once && coll.once("sync", function () { renderGenreRow(genreRow); });
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
