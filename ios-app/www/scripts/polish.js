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

  function installWatchFaceFreePositioning() {
    var ns = window.FES;
    var model = ns && ns.Model;
    var utils = ns && ns.Utils;
    var edit = ns && ns.View && ns.View.Edit;
    var WatchFaceLayoutInfo = model && model.WatchFaceLayoutInfo;
    var WatchFaceController = edit && edit.SpriteControllerWatchFace;
    var OffscreenEditor = edit && edit.OffscreenEditor;
    var SkinEditInfo = model && model.SkinEditInfo;
    if (!WatchFaceLayoutInfo || !WatchFaceController) return false;

    if (utils && utils.resizeImage && !utils.resizeImage.__fesAigenExact) {
      var resizeImage = utils.resizeImage;
      utils.resizeImage = function (src) {
        if (window.FES_AIGEN_EXACT_BACKGROUNDS && window.FES_AIGEN_EXACT_BACKGROUNDS[src]) {
          var df = $.Deferred();
          setTimeout(function () { df.resolve(src); });
          return df.promise();
        }
        return resizeImage.apply(this, arguments);
      };
      utils.resizeImage.__fesAigenExact = true;
    }

    if (SkinEditInfo && !SkinEditInfo.prototype.__fesAigenExactBackgrounds) {
      var setModel = SkinEditInfo.prototype.setModel;
      SkinEditInfo.prototype.setModel = function (type, layout) {
        if (
          type === "background" &&
          layout &&
          layout.src &&
          window.FES_AIGEN_EXACT_BACKGROUNDS &&
          window.FES_AIGEN_EXACT_BACKGROUNDS[layout.src]
        ) {
          layout.set({
            translationX: 0,
            translationY: 0,
            scale: 1,
            boundingScale: 1,
            rotation: 0,
          }, { silent: true });
        }
        return setModel.apply(this, arguments);
      };
      SkinEditInfo.prototype.__fesAigenExactBackgrounds = true;
    }

    if (OffscreenEditor && !OffscreenEditor.prototype.__fesAigenPreview) {
      var createSkinThumbnail = OffscreenEditor.prototype.createSkinThumbnail;
      var createSkinDetailImage = OffscreenEditor.prototype.createSkinDetailImage;

      function isExactAigenBackground(editor) {
        var src = editor &&
          editor._editInfo &&
          editor._editInfo.background &&
          editor._editInfo.background.src;
        return !!(src && window.FES_AIGEN_EXACT_BACKGROUNDS && window.FES_AIGEN_EXACT_BACKGROUNDS[src]);
      }

      function drawExactPreview(editor, width, height) {
        var df = $.Deferred();
        var bg = editor._editInfo.background.src;
        var face = editor._editInfo.face && editor._editInfo.face.src;
        var bgImg = new Image();
        bgImg.onload = function () {
          var canvas = document.createElement("canvas");
          canvas.width = width;
          canvas.height = height;
          var ctx = canvas.getContext("2d");
          ctx.fillStyle = "#d7d7d7";
          ctx.fillRect(0, 0, width, height);

          var bandW = Math.min(width * 0.34, height * 0.22);
          var bandH = Math.min(height * 0.9, bandW * 4.63);
          var bandX = (width - bandW) / 2;
          var bandY = (height - bandH) / 2;
          ctx.save();
          ctx.shadowColor = "rgba(0,0,0,0.22)";
          ctx.shadowBlur = Math.max(6, width * 0.035);
          ctx.shadowOffsetY = Math.max(3, height * 0.012);
          ctx.drawImage(bgImg, bandX, bandY, bandW, bandH);
          ctx.restore();

          var circleR = bandW * 0.82;
          var circleX = width / 2;
          var circleY = height / 2;
          ctx.save();
          ctx.beginPath();
          ctx.arc(circleX, circleY, circleR, 0, Math.PI * 2);
          ctx.clip();
          ctx.drawImage(bgImg, bandX, bandY, bandW, bandH);
          ctx.restore();
          ctx.lineWidth = Math.max(5, bandW * 0.13);
          ctx.strokeStyle = "#222";
          ctx.beginPath();
          ctx.arc(circleX, circleY, circleR, 0, Math.PI * 2);
          ctx.stroke();

          if (!face) {
            df.resolve(canvas.toDataURL("image/png"));
            return;
          }
          var faceImg = new Image();
          faceImg.onload = function () {
            var fw = circleR * 1.45;
            var fh = fw * (faceImg.height / Math.max(1, faceImg.width));
            ctx.drawImage(faceImg, circleX - fw / 2, circleY - fh / 2, fw, fh);
            df.resolve(canvas.toDataURL("image/png"));
          };
          faceImg.onerror = function () {
            df.resolve(canvas.toDataURL("image/png"));
          };
          faceImg.src = face;
        };
        bgImg.onerror = function () {
          createSkinDetailImage.call(editor).done(function (url) { df.resolve(url); }).fail(function (err) { df.reject(err); });
        };
        bgImg.src = bg;
        return df.promise();
      }

      OffscreenEditor.prototype.createSkinThumbnail = function () {
        if (isExactAigenBackground(this)) return drawExactPreview(this, 552, 882);
        return createSkinThumbnail.apply(this, arguments);
      };
      OffscreenEditor.prototype.createSkinDetailImage = function () {
        if (isExactAigenBackground(this)) return drawExactPreview(this, 444, 444);
        return createSkinDetailImage.apply(this, arguments);
      };
      OffscreenEditor.prototype.__fesAigenPreview = true;
    }

    if (!WatchFaceLayoutInfo.prototype.__fesFreePositionConfig) {
      var createConfig = WatchFaceLayoutInfo.prototype.createConfig;
      WatchFaceLayoutInfo.prototype.createConfig = function () {
        var config = createConfig.apply(this, arguments);
        var dx = Math.floor(Number(this.translationX) || 0);
        if (!config || !dx || !config.components) return config;

        config.components.forEach(function (component) {
          if (!component || component.type !== "watch" || !component.parts) return;
          component.parts.forEach(function (part) {
            if (!part || !part.layouts) return;
            part.layouts.forEach(function (layout) {
              if (layout && Array.isArray(layout.translate) && layout.translate.length > 0) {
                layout.translate[0] += dx;
              }
            });
          });
        });
        return config;
      };
      WatchFaceLayoutInfo.prototype.__fesFreePositionConfig = true;
    }

    if (!WatchFaceController.__fesFreePositionDrag) {
      WatchFaceController.onDragMove = function () {
        var sprite = this;
        var controller = sprite.controller;
        if (sprite.state !== 10 || !sprite.data) return;

        var point = sprite.data.getLocalPosition(sprite.parent);
        controller.checkGesture(
          "[FES.View.Edit.SpriteControllerWatchFace] onDragMove(x:" +
            point.x +
            ", y:" +
            point.y +
            ")"
        );
        controller.setEffectSpriteState(sprite, true);
        sprite.position.x += point.x - sprite.gestureInitTransform.position.x;
        sprite.position.y += point.y - sprite.gestureInitTransform.position.y;

        var editMetrics = model.EditMetrics || {};
        var minX = controller.basePosition.x - (editMetrics.BACKGROUND_WIDTH || 152) / 2;
        var maxX = controller.basePosition.x + (editMetrics.BACKGROUND_WIDTH || 152) / 2;
        var minY = controller.basePosition.y - (editMetrics.BACKGROUND_DISTANCE_U || 372);
        var maxY = controller.basePosition.y + (editMetrics.BACKGROUND_DISTANCE_L || 332);
        sprite.position.x = Math.max(minX, Math.min(maxX, sprite.position.x));
        sprite.position.y = Math.max(minY, Math.min(maxY, sprite.position.y));
        sprite.gestureInitTransform.position = point.clone();
      };

      WatchFaceController.onDragEnd = function () {
        var sprite = this;
        var controller = sprite.controller;
        controller.checkGesture("[FES.View.Edit.SpriteControllerWatchFace] onDragEnd()");
        sprite.data = null;
        controller.setEffectSpriteState(sprite, false);
        if (sprite.state === 1) return;

        sprite.state = 0;
        controller.stopAnimation();
        var threshold = (edit.UI_CONST && edit.UI_CONST.AUTO_ADJUST_THRESHOLD) || 10;
        var dx = Math.abs(sprite.defaultTransform.position.x - sprite.position.x);
        var dy = Math.abs(sprite.defaultTransform.position.y - sprite.position.y);
        if (dx <= threshold && dy <= threshold) {
          controller.toDefaultPosition();
        }
      };

      WatchFaceController.prototype.onAnimationProc = function (animation) {
        if (!this._face || !animation || animation.cookie !== "watch-face-controller" || !animation.amplitude) {
          return;
        }
        this._face.position.x =
          animation.start.position.x + animation.amplitude.position.x * animation.coeff;
        this._face.position.y =
          animation.start.position.y + animation.amplitude.position.y * animation.coeff;
        if (animation.complete) {
          this._face.position.x = this._face.defaultTransform.position.x;
          this._face.position.y = this._face.defaultTransform.position.y;
        }
      };

      WatchFaceController.prototype.toDefaultPosition = function () {
        if (this._face.state && this._face.state !== 10) return;
        this._face.state = 1;
        var current = this._face.position;
        var target = this._face.defaultTransform.position;
        var animation = {
          cookie: "watch-face-controller",
          timestamp: Date.now(),
          start: { position: current.clone() },
          amplitude: {
            position: new PIXI.Point(target.x - current.x, target.y - current.y),
          },
        };
        this.startAnimation(animation);
      };

      WatchFaceController.__fesFreePositionDrag = true;
    }

    return true;
  }

  function waitForRuntimePatches() {
    var attempts = 0;
    var timer = setInterval(function () {
      attempts += 1;
      if (installWatchFaceFreePositioning() || attempts > 600) {
        clearInterval(timer);
      }
    }, 100);
  }

  function ready(fn) {
    if (document.readyState !== "loading") fn();
    else document.addEventListener("DOMContentLoaded", fn, { once: true });
  }

  // Passive must run before frameworks attach handlers.
  makePassive();
  waitForRuntimePatches();

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

  function bindWatchFacePositionHint() {
    document.addEventListener("pageshow", function (ev) {
      var page = ev.target;
      if (!page || page.id !== "page-edit-pattern") return;
      var editArea = page.querySelector(".edit-area");
      if (!editArea || editArea.querySelector(".watch-face-position-handle")) return;
      var handle = document.createElement("div");
      handle.className = "watch-face-position-handle";
      editArea.appendChild(handle);
    });
  }

  ready(function () {
    bindHaptics();
    bindRipples();
    bindCatalogScrollHaptics();
    bindWatchFacePositionHint();
    wrapReloadWithTransition();
  });

  // Public API
  window.FES = window.FES || {};
  window.FES.toast = showToast;
  window.FES.haptic = tap;
})();
