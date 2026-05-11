// FES Community: Watch repair / re-pairing flow.
// Triggered when the watch is in an OWNER_UNVERIFIED state, when the OOBE
// wizard cannot establish a connection, or manually from Settings.
// Self-contained: depends only on jQuery + window.FES (already loaded).
(function (window, document) {
  "use strict";

  var STYLE_ID = "fes-repair-style";
  var MODAL_ID = "fes-repair-modal";

  function ensureStyle() {
    if (document.getElementById(STYLE_ID)) return;
    var s = document.createElement("style");
    s.id = STYLE_ID;
    s.textContent =
      "#" + MODAL_ID + "{position:fixed;inset:0;background:#0a0a0c;color:#f4f1ea;z-index:99998;display:flex;flex-direction:column;font-family:-apple-system,system-ui,sans-serif;-webkit-overflow-scrolling:touch;overflow-y:auto;padding:env(safe-area-inset-top,40px) 18px env(safe-area-inset-bottom,24px)}" +
      "#" + MODAL_ID + " .r-head{display:flex;align-items:center;justify-content:space-between;margin-bottom:14px}" +
      "#" + MODAL_ID + " .r-title{font-size:17px;font-weight:600;letter-spacing:.2px}" +
      "#" + MODAL_ID + " .r-close{background:transparent;border:1px solid #3a3a40;color:#f4f1ea;padding:6px 12px;border-radius:6px;font-size:13px;cursor:pointer}" +
      "#" + MODAL_ID + " .r-step{padding:14px 16px;border:1px solid #2a2a30;border-radius:10px;margin-bottom:10px;background:#141417}" +
      "#" + MODAL_ID + " .r-step .r-step-title{font-weight:600;font-size:15px;margin-bottom:6px;display:flex;align-items:center;gap:8px}" +
      "#" + MODAL_ID + " .r-step .r-step-body{font-size:14px;line-height:1.45;color:#c8c5be}" +
      "#" + MODAL_ID + " .r-step.is-active{border-color:#D0A85C;background:#1a1814}" +
      "#" + MODAL_ID + " .r-step.is-done{border-color:#2a4a2a;background:#0f1c0f}" +
      "#" + MODAL_ID + " .r-step.is-fail{border-color:#5a2a2a;background:#1c0f0f}" +
      "#" + MODAL_ID + " .r-pill{display:inline-block;width:18px;height:18px;border-radius:50%;background:#3a3a40;text-align:center;font-size:12px;line-height:18px}" +
      "#" + MODAL_ID + " .r-step.is-active .r-pill{background:#D0A85C;color:#101318}" +
      "#" + MODAL_ID + " .r-step.is-done .r-pill{background:#4a8a4a;color:#fff}" +
      "#" + MODAL_ID + " .r-step.is-fail .r-pill{background:#a44}" +
      "#" + MODAL_ID + " .r-actions{margin-top:16px;display:flex;flex-direction:column;gap:8px}" +
      "#" + MODAL_ID + " .r-btn{display:block;padding:13px 16px;border-radius:8px;border:0;font-size:15px;font-weight:600;text-align:center;cursor:pointer;font-family:inherit}" +
      "#" + MODAL_ID + " .r-btn.primary{background:#D0A85C;color:#101318}" +
      "#" + MODAL_ID + " .r-btn.secondary{background:transparent;border:1px solid #3a3a40;color:#f4f1ea}" +
      "#" + MODAL_ID + " .r-btn[disabled]{opacity:.4;cursor:default}" +
      "#" + MODAL_ID + " .r-progress{height:4px;background:#2a2a30;border-radius:2px;overflow:hidden;margin-top:8px}" +
      "#" + MODAL_ID + " .r-progress-fill{height:100%;background:#D0A85C;width:0%;transition:width .35s ease}" +
      "#" + MODAL_ID + " .r-spin{display:inline-block;width:14px;height:14px;border:2px solid #3a3a40;border-top-color:#D0A85C;border-radius:50%;animation:r-spin 1s linear infinite}" +
      "#" + MODAL_ID + " .r-debug{margin-top:18px;padding:10px;background:#0d0d10;border:1px solid #1a1a1f;border-radius:6px;font-family:monospace;font-size:11px;color:#8a8a8a;white-space:pre-wrap;max-height:120px;overflow:auto}" +
      "@keyframes r-spin{to{transform:rotate(360deg)}}";
    document.head.appendChild(s);
  }

  function escapeHtml(s) {
    return String(s == null ? "" : s).replace(/[&<>\"']/g, function (c) {
      return ({ "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;", "'": "&#39;" })[c];
    });
  }

  // RepairFlow controller. Single-use modal — call .open(opts) once.
  // opts = { connection, reason, onClose }
  function RepairFlow(opts) {
    this.opts = opts || {};
    this.connection = this.opts.connection;
    this.steps = [
      { id: "connect", title: "Verbinde mit Uhr…", body: "Suche nach der Uhr per Bluetooth." },
      { id: "reset", title: "Reset auf Uhr starten", body: "Wenn ein Hinweis erscheint: Knopf an der Uhr drücken um zu bestätigen." },
      { id: "wait", title: "Werkseinstellung läuft…", body: "Die Uhr wird zurückgesetzt. Das dauert ca. 30 Sekunden — auf der Uhr läuft eine Animation." },
      { id: "rebond", title: "Uhr neu koppeln…", body: "App verbindet sich erneut mit der frisch zurückgesetzten Uhr." },
    ];
    this.activeStep = -1;
    this.debugLines = [];
  }

  RepairFlow.prototype.open = function () {
    ensureStyle();
    var prev = document.getElementById(MODAL_ID);
    if (prev) prev.remove();

    var wrap = document.createElement("div");
    wrap.id = MODAL_ID;
    wrap.innerHTML =
      '<div class="r-head">' +
      '<div class="r-title">Uhr neu koppeln</div>' +
      '<button class="r-close" type="button">Schließen</button>' +
      "</div>" +
      '<div class="r-intro" style="font-size:14px;color:#c8c5be;line-height:1.45;margin-bottom:14px">' +
      escapeHtml(this.opts.reason || "Die Uhr ist nicht ordentlich gekoppelt. Wir setzen sie kurz zurück und verbinden sie neu.") +
      "</div>" +
      '<div class="r-steps"></div>' +
      '<div class="r-progress"><div class="r-progress-fill"></div></div>' +
      '<div class="r-actions"></div>' +
      '<div class="r-debug" hidden></div>';
    document.body.appendChild(wrap);

    var self = this;
    wrap.querySelector(".r-close").onclick = function () { self.close(false); };

    this.$wrap = wrap;
    this.$steps = wrap.querySelector(".r-steps");
    this.$actions = wrap.querySelector(".r-actions");
    this.$progress = wrap.querySelector(".r-progress-fill");
    this.$debug = wrap.querySelector(".r-debug");

    this.renderSteps();
    this.runStep0_Connect();
  };

  RepairFlow.prototype.renderSteps = function () {
    var self = this;
    this.$steps.innerHTML = "";
    this.steps.forEach(function (st, idx) {
      var el = document.createElement("div");
      el.className = "r-step" + (idx === self.activeStep ? " is-active" : "") + (st.state === "done" ? " is-done" : "") + (st.state === "fail" ? " is-fail" : "");
      el.dataset.stepId = st.id;
      var icon = st.state === "done" ? "✓" : st.state === "fail" ? "!" : (idx + 1);
      el.innerHTML =
        '<div class="r-step-title"><span class="r-pill">' + icon + "</span>" + escapeHtml(st.title) + "</div>" +
        '<div class="r-step-body">' + (idx === self.activeStep ? '<span class="r-spin"></span> ' : "") + escapeHtml(st.body) + "</div>";
      self.$steps.appendChild(el);
    });
    var pct = this.steps.filter(function (s) { return s.state === "done"; }).length / this.steps.length * 100;
    this.$progress.style.width = pct + "%";
  };

  RepairFlow.prototype.setStep = function (idx, body) {
    this.activeStep = idx;
    if (body) this.steps[idx].body = body;
    this.renderSteps();
  };

  RepairFlow.prototype.markStep = function (idx, state, body) {
    this.steps[idx].state = state;
    if (body) this.steps[idx].body = body;
    this.renderSteps();
  };

  RepairFlow.prototype.log = function (line) {
    this.debugLines.push(line);
    if (this.debugLines.length > 12) this.debugLines.shift();
    this.$debug.textContent = this.debugLines.join("\n");
    this.$debug.hidden = false;
  };

  RepairFlow.prototype.setActions = function (actions) {
    this.$actions.innerHTML = "";
    var self = this;
    actions.forEach(function (a) {
      var btn = document.createElement("button");
      btn.type = "button";
      btn.className = "r-btn " + (a.kind || "primary");
      btn.textContent = a.label;
      if (a.disabled) btn.disabled = true;
      btn.onclick = function () { a.onClick(self); };
      self.$actions.appendChild(btn);
    });
  };

  RepairFlow.prototype.close = function (success) {
    if (this.$wrap) this.$wrap.remove();
    if (typeof this.opts.onClose === "function") this.opts.onClose(!!success);
  };

  // ---- Step 0: try to connect (with regState ignored). ----
  RepairFlow.prototype.runStep0_Connect = function () {
    var self = this;
    if (!this.connection) {
      this.markStep(0, "fail", "Keine Verbindungs-Instanz verfügbar.");
      this.showHardwareResetGuide("Konnte keine Verbindung initialisieren.");
      return;
    }
    this.setStep(0, "Suche Uhr… (bis zu 60 Sekunden)");
    this.setActions([]);

    var FES = window.FES || {};
    var lookupDuration = (FES && FES.MAX_LOCKUP_DURATION) || 60000;

    var disconnectFirst = $.Deferred();
    try {
      if (this.connection.isConnected) {
        this.connection.disconnectFromDevice();
        setTimeout(function () { disconnectFirst.resolve(); }, 600);
      } else {
        disconnectFirst.resolve();
      }
    } catch (e) {
      disconnectFirst.resolve();
    }

    disconnectFirst.then(function () {
      var p = self.connection.connectToRegisteredDevice({
        lookupDuration: lookupDuration,
        ignoreRegistrationError: true,
      });
      p.done(function () {
        self.log("[step0] connected, regState=" + self.connection.lastRegistrationState);
        self.markStep(0, "done", "Uhr verbunden.");
        self.runStep1_Reset();
      });
      p.fail(function (e) {
        self.log("[step0][fail] " + (e && e.code) + " " + ((e && e.message) || "").substring(0, 120));
        self.markStep(0, "fail", "Konnte Uhr nicht erreichen.");
        self.showHardwareResetGuide("Die Uhr antwortet nicht auf Bluetooth-Anfragen. Vermutlich ist sie aus oder noch mit einer alten App verbunden.");
      });
    });
  };

  // If connect failed: tell user to do hardware reset first.
  RepairFlow.prototype.showHardwareResetGuide = function (reason) {
    this.steps[1].body = "Halte den Knopf an der Uhr ca. 10 Sekunden gedrückt, bis die Uhr ein Reset-Symbol zeigt. Lass dann den Knopf los — die Uhr macht ihren Werks-Reset.";
    this.steps[2].body = "Warte bis die Animation auf der Uhr fertig ist (ca. 30 Sekunden) und die Uhr neu startet.";
    this.steps[3].body = "Tippe dann auf 'Erneut versuchen'. Die App findet die Uhr im factory-fresh Zustand und richtet sie sauber ein.";
    this.activeStep = 1;
    this.renderSteps();

    var introEl = this.$wrap.querySelector(".r-intro");
    introEl.innerHTML =
      "<strong>Hardware-Reset nötig.</strong><br>" +
      escapeHtml(reason) +
      "<br><br>So gehts:";

    var self = this;
    this.setActions([
      {
        label: "Erneut versuchen",
        kind: "primary",
        onClick: function () {
          self.steps.forEach(function (s) { s.state = undefined; });
          self.runStep0_Connect();
        },
      },
      { label: "Abbrechen", kind: "secondary", onClick: function () { self.close(false); } },
    ]);
  };

  // ---- Step 1: send DataResetQuery, wait for hardware confirm. ----
  RepairFlow.prototype.runStep1_Reset = function () {
    var self = this;
    this.setStep(1, "Sende Reset-Anfrage an Uhr…");
    this.setActions([]);

    var p;
    try {
      p = this.connection.initializeFI();
    } catch (e) {
      this.markStep(1, "fail", "initializeFI nicht verfügbar.");
      this.showHardwareResetGuide("Die App konnte den Reset nicht starten.");
      return;
    }

    p.progress(function () {
      self.log("[step1] DataResetQuery accepted, waiting for hardware confirm");
      self.setStep(1, "✦ Drücke jetzt den Knopf an der Uhr, um den Reset zu bestätigen.");
    });
    p.done(function () {
      self.log("[step1] initializeFI done");
      self.markStep(1, "done", "Reset bestätigt.");
      self.runStep2_Wait();
    });
    p.fail(function (e) {
      self.log("[step1][fail] " + (e && e.code) + " " + ((e && e.message) || "").substring(0, 120));
      self.markStep(1, "fail", "Reset wurde nicht bestätigt (Knopfdruck-Timeout?).");
      self.setActions([
        { label: "Nochmal versuchen", kind: "primary", onClick: function () { self.runStep1_Reset(); } },
        { label: "Abbrechen", kind: "secondary", onClick: function () { self.close(false); } },
      ]);
    });
  };

  // ---- Step 2: countdown 30s while watch finalizes the wipe. ----
  RepairFlow.prototype.runStep2_Wait = function () {
    var self = this;
    var seconds = 30;
    this.setStep(2, "Werkseinstellung läuft… noch " + seconds + "s. Auf der Uhr siehst du eine Animation.");
    this.setActions([]);

    try {
      // Drop the BLE link so the watch can fully restart cleanly.
      this.connection.disconnectFromDevice();
    } catch (e) {}

    var iv = setInterval(function () {
      seconds--;
      if (seconds > 0) {
        self.setStep(2, "Werkseinstellung läuft… noch " + seconds + "s.");
      } else {
        clearInterval(iv);
        self.markStep(2, "done", "Uhr ist jetzt zurückgesetzt.");
        self.runStep3_Rebond();
      }
    }, 1000);
  };

  // ---- Step 3: reconnect and let challengeRegistration set the new owner. ----
  RepairFlow.prototype.runStep3_Rebond = function () {
    var self = this;
    this.setStep(3, "Verbinde neu mit der frisch zurückgesetzten Uhr…");
    this.setActions([]);

    var FES = window.FES || {};
    var lookupDuration = (FES && FES.MAX_LOCKUP_DURATION) || 60000;

    // Optionally clear local owner state so a fresh ownerId is generated by ensureOwnerId().
    try {
      var ss = FES.Utils && FES.Utils.StorageAccess && FES.Utils.StorageAccess.getStorage(FES.Utils.STORAGE_KIND.SECURE_STORAGE);
      if (ss) {
        $.when(ss.removeItem("devices"), ss.removeItem("ownerId")).always(function () {
          self.attemptRebondConnect(lookupDuration);
        });
        return;
      }
    } catch (e) {}
    this.attemptRebondConnect(lookupDuration);
  };

  RepairFlow.prototype.attemptRebondConnect = function (lookupDuration) {
    var self = this;
    var attempt = 0;
    var maxAttempts = 3;

    var tryOnce = function () {
      attempt++;
      self.setStep(3, "Verbinde… (Versuch " + attempt + "/" + maxAttempts + ")");
      self.log("[step3] reconnect attempt " + attempt);
      var p = self.connection.connectToRegisteredDevice({ lookupDuration: lookupDuration });
      p.done(function () {
        self.log("[step3] OK regState=" + self.connection.lastRegistrationState);
        self.markStep(3, "done", "Uhr ist neu gekoppelt. Skin-Installation funktioniert wieder.");
        self.setActions([
          { label: "Fertig", kind: "primary", onClick: function () { self.close(true); } },
        ]);
      });
      p.fail(function (e) {
        self.log("[step3][fail " + attempt + "] " + (e && e.code) + " " + ((e && e.message) || "").substring(0, 120));
        if (attempt < maxAttempts) {
          setTimeout(tryOnce, 4000);
        } else {
          self.markStep(3, "fail", "Konnte die Uhr nach Reset nicht neu verbinden.");
          self.setActions([
            { label: "Nochmal versuchen", kind: "primary", onClick: function () { self.attemptRebondConnect(lookupDuration); } },
            { label: "Schließen", kind: "secondary", onClick: function () { self.close(false); } },
          ]);
        }
      });
    };
    tryOnce();
  };

  // ---- Public API ----
  window.FES = window.FES || {};
  window.FES.Repair = {
    open: function (opts) {
      var f = new RepairFlow(opts);
      f.open();
      return f;
    },
    isOwnerUnverifiedError: function (e) {
      if (!e) return false;
      var c = (e.code != null) ? e.code : (e.cause && e.cause.code);
      return c === 5;
    },
    isConnectionError: function (e) {
      if (!e) return false;
      var c = (e.code != null) ? e.code : (e.cause && e.cause.code);
      // 5002 = ERROR_APP_FI_CONNECTION
      return c === 5002 || c === 5003 || c === 5005;
    },
  };
})(window, document);
