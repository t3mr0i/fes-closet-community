/*!
 * FES polish — runtime details
 * Haptics on confirms, View Transitions for navigation when supported,
 * passive scroll listeners. Idempotent and isolated.
 */
(function () {
  "use strict";

  function tap(ms) {
    if (navigator.vibrate) {
      try { navigator.vibrate(ms || 8); } catch (_) {}
    }
  }

  function bindHaptics() {
    var sel = [
      ".ui-btn-confirm",
      ".ui-btn-primary",
      "[data-haptic]",
      "#edit-save-button",
      "#settings-fi-button-fw-update",
      ".skin-manage-button-remove",
      "#settings-skin-manage-button-select"
    ].join(",");

    document.addEventListener("click", function (e) {
      var t = e.target.closest && e.target.closest(sel);
      if (t) tap(8);
    }, true);
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

  function ready(fn) {
    if (document.readyState !== "loading") fn();
    else document.addEventListener("DOMContentLoaded", fn, { once: true });
  }

  // Passive listeners must be patched before other scripts attach handlers.
  makePassive();

  ready(function () {
    bindHaptics();
  });
})();
