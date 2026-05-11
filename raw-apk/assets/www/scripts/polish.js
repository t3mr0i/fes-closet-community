/*!
 * FES polish — runtime details + micro-interactions
 * Haptics, tap-ripples, lightweight toasts, passive listeners.
 * Idempotent and isolated. Loaded after Sony's Cordova bundle.
 */
(function () {
  "use strict";

  var HAPTIC_SELECTOR = [
    ".ui-btn-confirm",
    ".ui-btn-primary",
    "[data-haptic]",
    "#edit-save-button",
    "#settings-fi-button-fw-update",
    ".skin-manage-button-remove",
    "#settings-skin-manage-button-select",
    ".purchase-skin",
    ".fes-button-regular",
    ".fes-cv-action",
    ".catalog-genre-pill",
    ".r-btn.primary",
  ].join(",");

  // selector for buttons that should get a tap-ripple
  var RIPPLE_SELECTOR = [
    ".fes-button-regular",
    ".fes-cv-action",
    ".purchase-skin",
    ".catalog-genre-pill",
    ".catalog-chip",
    ".r-btn",
    ".aigen-style-tile",
  ].join(",");

  function tap(ms) {
    // Prefer the native iOS TapticEngine plugin if available — navigator.vibrate
    // is a no-op on iOS WKWebView, so without this the user gets nothing.
    var t = window.TapticEngine;
    if (t && typeof t.impact === "function") {
      try { t.impact({ style: "light" }); return; } catch (_) {}
    }
    if (t && typeof t.selection === "function") {
      try { t.selection(); return; } catch (_) {}
    }
    if (navigator.vibrate) {
      try { navigator.vibrate(ms || 8); } catch (_) {}
    }
  }

  function bindHaptics() {
    document.addEventListener(
      "click",
      function (e) {
        var t = e.target.closest && e.target.closest(HAPTIC_SELECTOR);
        if (t) tap(8);
      },
      true
    );
  }

  function bindRipples() {
    document.addEventListener(
      "pointerdown",
      function (e) {
        if (e.button !== undefined && e.button !== 0) return;
        var host = e.target.closest && e.target.closest(RIPPLE_SELECTOR);
        if (!host) return;
        if (host.disabled || host.getAttribute("aria-disabled") === "true") return;
        var rect = host.getBoundingClientRect();
        if (!rect.width) return;
        var ripple = document.createElement("span");
        ripple.className = "fes-tap-ripple";
        var size = Math.max(rect.width, rect.height) * 0.5;
        ripple.style.width = size + "px";
        ripple.style.height = size + "px";
        ripple.style.left = (e.clientX - rect.left - size / 2) + "px";
        ripple.style.top = (e.clientY - rect.top - size / 2) + "px";
        host.appendChild(ripple);
        setTimeout(function () {
          if (ripple.parentNode) ripple.parentNode.removeChild(ripple);
        }, 520);
      },
      true
    );
  }

  function makePassive() {
    if (!window.EventTarget) return;
    var orig = EventTarget.prototype.addEventListener;
    var passiveTypes = { touchstart: 1, touchmove: 1, wheel: 1, mousewheel: 1 };
    EventTarget.prototype.addEventListener = function (type, listener, opts) {
      if (passiveTypes[type]) {
        if (opts === undefined || opts === false) {
          opts = { passive: true };
        } else if (typeof opts === "object" && opts.passive === undefined) {
          opts = Object.assign({}, opts, { passive: true });
        }
      }
      return orig.call(this, type, listener, opts);
    };
  }

  // Lightweight global toast: window.FES.toast("Saved")
  var toastTimer = null;
  function showToast(msg, ms) {
    var el = document.querySelector(".fes-toast");
    if (!el) {
      el = document.createElement("div");
      el.className = "fes-toast";
      document.body.appendChild(el);
    }
    el.textContent = String(msg == null ? "" : msg);
    requestAnimationFrame(function () {
      el.classList.add("is-visible");
    });
    if (toastTimer) clearTimeout(toastTimer);
    toastTimer = setTimeout(function () {
      el.classList.remove("is-visible");
    }, Math.max(800, ms || 1800));
  }

  // Page transitions via View Transitions API (Safari 18+, WKWebView 17.4+).
  // We can't hook jQuery Mobile's transition system without breaking it, but we
  // CAN wrap manual location.reload calls (used in our reset flow) to feel fluid.
  function wrapReloadWithTransition() {
    if (!document.startViewTransition) return;
    var origReload = location.reload.bind(location);
    location.reload = function () {
      try {
        document.startViewTransition(function () { origReload(); });
      } catch (_) {
        origReload();
      }
    };
  }

  function ready(fn) {
    if (document.readyState !== "loading") fn();
    else document.addEventListener("DOMContentLoaded", fn, { once: true });
  }

  // Passive must run before frameworks attach handlers.
  makePassive();

  // Haptic feedback when catalog/closet skin selection changes.
  // We watch the skin-name element; when its textContent changes we trigger a
  // light tap. This catches both swipe-and-snap and tap-to-select.
  function bindCatalogScrollHaptics() {
    var lastByPage = Object.create(null);
    var observe = function (page) {
      if (!page) return;
      var nameEls = page.querySelectorAll(".main-page .info-and-controllers .skin-name");
      nameEls.forEach(function (el) {
        if (el.dataset.fesHaptic === "1") return;
        el.dataset.fesHaptic = "1";
        var pageId = page.id || "";
        var key = pageId + ":" + (el.className || "");
        var mo = new MutationObserver(function () {
          var t = (el.textContent || "").trim();
          if (!t) return;
          if (lastByPage[key] !== t) {
            lastByPage[key] = t;
            tap(6);
          }
        });
        mo.observe(el, { childList: true, characterData: true, subtree: true });
      });
    };
    document.addEventListener("pageshow", function (ev) {
      var p = ev.target;
      if (!p) return;
      if (p.id === "page-home-store" || p.id === "page-home-closet") {
        setTimeout(function () { observe(p); }, 200);
      }
    });
  }

  ready(function () {
    bindHaptics();
    bindRipples();
    bindCatalogScrollHaptics();
    wrapReloadWithTransition();
  });

  // Public API
  window.FES = window.FES || {};
  window.FES.toast = showToast;
  window.FES.haptic = tap;
})();
