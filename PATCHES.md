# app.js Patches

The Sony Cordova bundle ships as a single ~322 KB minified file
(`raw-apk/assets/www/scripts/app.js` and the iOS mirror at
`ios-app/www/scripts/app.js`). We carry **6 surgical patches** in that file.
They are not commits in upstream Sony source — every patch is invisible
inside the minified blob.

This file lists each patch with:
- a **search anchor** (a unique substring you can grep for)
- the **before** and **after** text
- the **why**

If `app.js` is ever refreshed from a fresh APK extraction, run through this
list and re-apply each patch. The patches are independent of each other.

A backup of the unmodified file lives at
`raw-apk/assets/www/scripts/app.js.before-offline-patch` (Sony's offline
gate is also patched there — out of scope for this list).

---

## 1. Skin install: short-response hex dump + real error code

**Anchor:** `Failed while posting SkinTransferRequest`

The watch responds to a `SkinInstall(48)` command with 7 bytes when it
accepts; with 2 bytes (`[opcode, errorCode]`) when it refuses. Sony's
default rejects the 2-byte response with a generic
`"return value too short"` (error code 80), losing the actual reason.
Our patch reads byte 1 as the error code and surfaces it (e.g. 5 =
`COMMON_OWNER_UNVERIFIED`) so the in-app dialog can offer a Repair flow.

The actual patch lives in
`ios-app/www/lib/scripts/fes.protocol.ble.js`, **not** app.js. See the
`_postSkinTransferRequest` function and the `.done(function(b){...})`
short-response branch that hex-dumps `b` into the rejected error.

---

## 2. OOBE wizard: auto-trigger Repair on connection failure

**Anchor:** `isCanceledError(c)||(window.FES&&window.FES.Repair`

In the OOBE registration page's `connectToRegisteredDevice` failure
handler, before falling through to `handleReject(c)`, we check if the
error is `OWNER_UNVERIFIED` or a `FI_CONNECTION` error and open the
Repair flow instead. On successful Repair, the wizard calls
`registerDevice()` again.

**Before:** `.fail(function(c){a.isCanceledError(c)||b.handleReject(c)})}`

**After:**
```js
.fail(function(c){if(a.isCanceledError(c))return;
  if(window.FES&&window.FES.Repair&&
     (window.FES.Repair.isOwnerUnverifiedError(c)||
      window.FES.Repair.isConnectionError(c))){
    window.FES.Repair.open({connection:b._connection,
      reason:"…",onClose:function(ok){
        if(ok)b.registerDevice(); else b.handleReject(c);
      }});
    return;
  }
  b.handleReject(c)})
```

---

## 3. OOBE wizard: post-connect regState mismatch -> Repair

**Anchor:** `b._isFailedRegistration?(window.FES&&window.FES.Repair`

Even when the connect itself succeeds, `regState` may indicate the watch
is bound to a different owner. Sony's wizard navigates to a settings
page in that case. We intercept and offer Repair first.

**Before:**
```js
b._showingSkipDialog||(b._isFailedRegistration?b.navigateToSettings():b.navigateToNextPage())
```

**After:**
```js
b._showingSkipDialog||(b._isFailedRegistration?
  (window.FES&&window.FES.Repair?
    window.FES.Repair.open({connection:b._connection,reason:"…",
      onClose:function(ok){
        if(ok)b.registerDevice(); else b.navigateToSettings();
      }}):
    b.navigateToSettings())
  :b.navigateToNextPage())
```

---

## 4. Skin transfer error dialog: auto-trigger Repair on owner-unverified

**Anchor:** `showTransferErrorDialog=function(b)`

If a skin install fails at runtime (not OOBE) with `OWNER_UNVERIFIED`,
open Repair instead of the generic "Cannot send skin" dialog.

The patch inserts an early-return at the top of the function:

```js
if(window.FES&&window.FES.Repair&&
   window.FES.Repair.isOwnerUnverifiedError(b)&&this._connection){
  window.FES.Repair.open({connection:this._connection,reason:"…"});
  return{on:function(){return this}};
}
```

(Returns a chainable stub so existing `.on(…)` callers don't crash.)

---

## 5. Settings: community Repair button handler

**Anchor:** `command-community-repair-pairing":this.onCommunityRepairPairing`

Adds a single Settings entry (rendered via `settings-general.html` —
that template patch is separate) that opens the Repair flow on demand,
without going through the connect flow first.

The events-hash entry plus the handler:

```js
"vclick .command-community-repair-pairing":this.onCommunityRepairPairing

c.prototype.onCommunityRepairPairing=function(b){
  var c=this;
  c._prmsManager.add(c.ensureConnectionInstance()).done(function(){
    if(window.FES&&window.FES.Repair){
      window.FES.Repair.open({connection:c._connection,
        reason:"Setze die Uhr zurück und koppele sie neu, …"});
    }
  }).fail(function(e){a.handleErrorInfo(e)})
}
```

---

## 6. Suppress "What's new in v…" startup popup

**Anchor:** `dialog.updateinfo.title`

Sony shows an alert dialog on every version bump. We resolve the
deferred immediately so the dialog never opens.

**Before:**
```js
return a.Utils.checkInformationCompletion().done(function(c){
  c?a.Utils.getAppVersion().done(…dialog…):b.resolve()
}…
```

**After:**
```js
return a.Utils.checkInformationCompletion().done(function(c){
  b.resolve();return;
  c&&a.Utils.getAppVersion().done(…dialog…)
}…
```

The `return;` makes the rest of the closure dead code (kept for
diff-locality with upstream).

---

## 7. Catalog detail: drop FES_logo.png fallback

**Anchor:** `renderImages=function(b,c){var d=function(c,d)`

If a custom image (`customImage1/2/3`) fails to load, Sony's code in
TARGET_LOCAL mode falls back to `res/images/nodpi/FES_logo.png` (the
"FASHION ENTERTAINMENTS" wordmark). That looks like Sony branding
sneaking in on community skins. We replace the fallback with a
transparent slide so the carousel size is preserved but no Sony asset
appears.

**Before:**
```js
.fail(function(b){
  a.TARGET_LOCAL?
    (e.css("background-image","url('"+CDP.Framework.toUrl("/res/images/nodpi/FES_logo.png")+"')"),
     e.removeClass("loading notfound"),e=null):
    (e.removeClass("loading"),e.addClass("notfound"),e=null)
})
```

**After:**
```js
.fail(function(b){
  e.removeClass("loading").addClass("notfound")
   .css({"background-image":"none","background-color":"transparent"}),
  e=null
})
```

---

## Re-applying after upstream refresh

1. Extract the new APK with `apktool` (see `DECOMPILE_NOTES.md`).
2. For each patch above, grep for the anchor in the new `app.js`.
3. If the surrounding minified code still matches the **Before** snippet,
   apply the **After**.
4. If minification has changed variable names, find the equivalent code
   path in `analysis-js/app.pretty.js` (the pretty-printed reference)
   first, then re-derive the search-and-replace.
