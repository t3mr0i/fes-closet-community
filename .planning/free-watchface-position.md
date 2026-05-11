# Free positioning of the watch-face overlay

## Status
**Phase A implemented.** The edit-page preview now allows x/y dragging and
saved skins include the x-offset in the generated watch-face config.

**Phase B still unverified.** BLE transfer/firmware acceptance of arbitrary
watch-face x/y layout values still needs a device round-trip test.

## Goal
Let the user freely place the watch-face module (digits + AM/PM + dial face)
anywhere on the 152×704 background, instead of the fixed centered y-offset
Sony's edit page exposes today.

## What ships today (as of v1.65)

Sony's `_watch-face.json` per face directory exposes a `defaultOffset` plus
a list of `templates.translateY[]` keys. The edit-pattern UI offers a
y-offset slider (limited range, ±~60 px) so the user nudges the face module
up or down a small amount.

`_config.json` per face has hard-coded `translate: [x, y]` arrays for each
component (face image, AM/PM, hour digits, minute digits). The `<%= … %>`
templating substitutes the user-picked translateY at render time.

## Why "free placement" is non-trivial

Three things are coupled to the current y-offset slider:

1. **The face PNG and digit PNGs ship pre-positioned.** They're rendered
   relative to a single x/y per layout entry. Moving them freely means
   moving every component reference together — a "module" concept Sony's
   edit pipeline doesn't have natively.
2. **PIXI/canvas synthesis** in `Q.prototype.synthesize*` reads
   `_config.json` once at apply-time. A live drag would need to re-render
   every frame.
3. **The watch firmware itself** lays out the face module based on the
   on-device clock — we can't push arbitrary x/y to the watch beyond what
   firmware accepts. Need to verify what the BLE skin payload actually
   permits at the binary level (see `analysis-js/fes.protocol.ble.pretty.js`
   `_postSkinTransferRequest` + the `INFO`/`SKNH` chunks).

## Smallest-step proposal

Phase A — UI only, edit-page preview only:
- Add a 2-axis pan handle on the watch-face overlay in the edit canvas.
- Translate (dx, dy) into a synthetic per-render override that is layered
  on top of `_config.json`'s static layouts before PIXI synthesis.
- Persist the chosen offset into the saved `_config.json` at "Done" time
  (same format Sony uses, just non-zero x and free-form y).

Implemented in `ios-app/www/scripts/polish.js` as a runtime extension of
Sony's existing `SpriteControllerWatchFace` and `WatchFaceLayoutInfo`.

Phase B — verify watch acceptance:
- Build a couple of skins with non-default `translate` values, install via
  BLE, see if the firmware honours them. If yes: Phase A ships. If no: the
  feature degrades to a preview-only mode (looks different in app, lays
  out canonically on watch).

Phase C — UX polish:
- Snap-to-centre, snap-to-band-edge guides.
- Reset button.
- Per-component handle (face circle vs. digit cluster) instead of moving
  the whole module as one.

## Files that would need to change

- `ios-app/www/scripts/polish.js` — runtime patch for 2-axis watch-face
  dragging and packaging x-offsets into the generated config.
- `ios-app/www/stylesheets/modern.css` — transient drag affordance.
- `ios-app/www/res/data/edit/watch-face/<id>/_config.json` — at save time
  the template substitution becomes user-driven, not just translateY.

## Effort estimate

Phase A is implemented without editing the minified `app.js`; the extension
hooks the exported runtime objects from `polish.js`. Phase B unknown until
BLE round-trip is tested.

## Decision

Ship Phase A behind the existing edit flow. Keep Phase B as the next
hardware-verification task before claiming full watch support.
