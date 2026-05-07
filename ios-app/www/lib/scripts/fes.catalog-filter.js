/* FES Catalog Filter
 * Adds studio/artist/tag/search filters to the existing Arc store.
 * Operates on the Backbone SkinCollection singleton (instance 2 = store).
 * No bundle modifications: hooks DOM events on #page-home-store.
 */
(function () {
  "use strict";

  var STORE_INSTANCE = 2;
  var snapshot = null; // { models: [...], totalCount: N }
  var state = { studio: null, artist: null, tag: null, q: "" };

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
    var tags = {};
    models.forEach(function (m) {
      var s = modelField(m, "studio");
      var a = modelField(m, "artist");
      if (s) studios.push(s);
      if (a) artists.push(a);
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
      tag: tagList,
    };
  }

  function matchModel(m) {
    if (state.studio && modelField(m, "studio") !== state.studio) return false;
    if (state.artist && modelField(m, "artist") !== state.artist) return false;
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
    if (filtered.length === 0) {
      // Arc with 0 children misbehaves; show empty state via stock pattern by
      // resetting to empty + non-zero totalCount triggers "no skins" UI.
      coll.reset([], { silent: true });
      coll._totalCount = 0;
      coll.trigger("sync", coll, coll, { reset: true });
      return;
    }
    coll.reset(filtered, { silent: true });
    coll._totalCount = filtered.length;
    coll.trigger("sync", coll, coll, { reset: true });
  }

  function renderChips(panel, facets) {
    ["studio", "artist", "tag"].forEach(function (group) {
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

    panel.addEventListener("click", function (e) {
      var t = e.target.closest("[data-catalog-pick]");
      if (t) {
        var group = t.getAttribute("data-catalog-pick");
        var val = t.getAttribute("data-value");
        state[group] = state[group] === val ? null : val;
        applyFilter();
        refreshFacets(panel);
        return;
      }
      if (e.target.matches("[data-catalog-reset]")) {
        state.studio = null;
        state.artist = null;
        state.tag = null;
        if (input) input.value = "";
        state.q = "";
        applyFilter();
        refreshFacets(panel);
      }
    });

    if (resetBtn) {
      // already handled via delegation but keep direct binding for safety
    }

    if (input) {
      var debounce;
      input.addEventListener("input", function () {
        clearTimeout(debounce);
        debounce = setTimeout(function () {
          state.q = input.value.trim();
          applyFilter();
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
      if (
        coll &&
        snapshot &&
        (state.studio || state.artist || state.tag || state.q)
      ) {
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
