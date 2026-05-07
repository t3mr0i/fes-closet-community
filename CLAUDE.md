# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is

A community-revival project for the **Sony FES U Watch** (`FES Closet` Android app, package `jp.co.sony.fes`, v1.13.0). The original Sony backend is gone. This repo:

1. Decompiles and patches the original APK so it runs fully offline / against a community mirror.
2. Hosts a static replacement for the Sony store via GitHub Pages (`server-mirror/`).
3. Documents the BLE protocol used to push watchfaces ("skins") to the watch.
4. Stages an iOS Cordova rebuild of the same app (`ios-app/`).

The watch hardware is unchanged — only the BLE skin-transfer path is needed at runtime. Backend is purely static metadata + skin ZIPs.

## Repository layout

- `raw-apk/` — APK extracted as-is (manifest, dex, `assets/www/` Cordova app). Treat as source of truth for the running app.
- `raw-apk/assets/www/scripts/app.js` — main app bundle, **patched in-place** for offline mode. Backups: `*.before-offline-patch`.
- `raw-apk/assets/www/scripts/config.js` — runtime config. Key flags: `TARGET_SERVER = "prod-local-ww"`, `BUILD_TYPE = "ww"`. Do not change these — the offline gates depend on them.
- `decompiled/` — JADX output from the original APK (Java sources + resources).
- `analysis-js/` — pretty-printed copies of the four important minified JS files. **Read these, not the minified ones**, when reasoning about app/BLE logic.
- `server-mirror/public/` — static site deployed to GitHub Pages. URL paths the app calls: `/api/store/skins-<locale>.json`, `/api/store/creators-<locale>.json`, `/storage/<creatorId>/<skinId>/skin.zip`, `/fw/version.json`.
- `server-mirror/scripts/generate-watchface.mjs` — Node script that turns prompt inputs into a generated watchface under `public/generated/`. Emits two zips: `watchface-package.zip` (design bundle, not installable) and `skin.zip` (watch-installable; flat-color background only — time/date/battery layers shown in `preview.svg` are not rasterized).
- `ios-app/` — Cordova iOS project mirroring the Android app. Uses `cordova-ios@^8`, `cordova-plugin-bluetoothle`, plus `fes-nativebridge-stubs/` (local file dep) which stubs the Sony-internal Cordova plugins so the JS layer loads.
- `fes-nativebridge-stubs/` — local Cordova plugin providing iOS Objective-C stubs (`FESBatteryStatus`, `FESLocalContentProvider`, `FESNotificationHandler`, `FESUUID`, `FESMisc`) for plugins that don't exist outside Sony's build.
- `test-skins/minimal-bg/` — minimal hand-built skin (one 152×704 background) for testing the conversion + BLE transfer path independently of the store.
- `DECOMPILE_NOTES.md` — running log of what's been patched, BLE command codes, skin install flow, server endpoint redirections. **Update this when you change patches.**

## Common commands

### Static mirror (local preview)
```sh
cd server-mirror
npx serve public                          # or
python3 -m http.server 8080 --directory public
```

### Watchface generator (locally)
```sh
WATCHFACE_DIRECTION="minimal warm" \
WATCHFACE_PALETTE="#101318,#F4F1EA,#D0A85C" \
node server-mirror/scripts/generate-watchface.mjs
```
Outputs `request.json`, `spec.json`, `preview.svg`, `watchface-package.zip` under `server-mirror/public/generated/watchfaces/<slug>/` and updates `public/generated/index.json`.

### Watchface generator (CI)
Run the `Generate Watchface` workflow via GitHub Actions UI (`.github/workflows/generate-watchface.yml`). It commits results back to the branch and triggers Pages deploy.

### Pages deploy
Auto-runs on push to `main` or `codex/fes-community-offline` when `server-mirror/public/**` changes. Manual via `workflow_dispatch`.

### iOS Cordova build
```sh
cd ios-app
npm install
npx cordova platform add ios            # if platforms/ios is missing
npx cordova prepare ios
npx cordova build ios                    # or open platforms/ios/*.xcworkspace
```
The `fes-nativebridge-stubs` plugin is wired through `package.json` as a local `file:` dep — `npm install` is what installs it.

### APK rebuild
Not automated. Use `apktool` (mentioned in `DECOMPILE_NOTES.md`) on `raw-apk/`, then sign with `community-debug.keystore`. The two checked-in APKs (`*_OFFLINE_GITHUB_*.apk`, `*_OFFLINE_PATCH_*.apk`) are the current outputs.

## Key architectural facts

### Hosted endpoint redirection
The patched app keeps the original offline-mode flag but redirects the local endpoint constants in `fes.utils.js` / `config.js` to the GitHub Pages mirror:
- `STORE_API_ENDPOINT_LOCAL → https://t3mr0i.github.io/fes-closet-community/api/`
- `STORE_STORAGE_ENDPOINT_LOCAL → https://t3mr0i.github.io/fes-closet-community/storage/`

When adding/changing community content, update **both**:
- `raw-apk/assets/www/res/data/api/store/*.json` and `res/data/storage/...` (bundled-in-APK copy)
- `server-mirror/public/api/store/*.json` and `public/storage/...` (Pages-hosted copy)

The `.json` catalogs are per-locale: `skins-en-us.json`, `skins-ja-jp.json`, `skins-zh-cn.json` (and same for creators).

### Skin format
A `skin.zip` is `config.json` + PNG layer assets. Display target is **152×704**. The runtime converter (`FIImageConverter`/`FIJSONConverter` in `fes.plugin.fi-watch.pretty.js`) packages the ZIP into a binary `SKNH`/`INFO`/`IMG `/`SKNF` payload before BLE transfer.

### BLE protocol (for any code touching the watch)
- Service: `BD090001-605B-5558-A58A-880C162ACA10`
- ControlPoint: `BD090010-...`, DataPacket: `BD090011-...`
- Skin install: `SkinInstall(48)` with 16-byte ID + 3-byte length → receive handle + start packet # → stream image as 19-byte chunks + 1-byte seq, ack every 10 writes (`DataAck(80)`) → `VerifySkinImage(49)` with handle + 4-byte CRC.
- Full command table and entry points are in `DECOMPILE_NOTES.md` — that doc is authoritative; code lives in `analysis-js/fes.protocol.ble.pretty.js` (`installSkin`, `_postSkinTransferRequest`, `transferData`, `_postDataPacketsQuick`, `_verifyImage`).

### Branches
- `main` — primary
- `codex/fes-community-offline` — current working branch (also a Pages deploy trigger)

## Patching conventions

- When patching `raw-apk/assets/www/scripts/app.js` (or any minified Cordova source), keep a sibling `*.before-offline-patch` backup the first time you touch it. Existing backups already exist for `app.js` and `init.js` — do not overwrite them.
- After patching, append a short note to `DECOMPILE_NOTES.md` describing what changed and why (offline gate, removed Sony URL, etc.). The "Offline Patch" and "Legacy UI cleanup" sections are the existing pattern.
- The pretty-printed `analysis-js/*.pretty.js` files are read-only references for understanding behavior. The actual app loads the minified versions under `raw-apk/assets/www/`.

## What this repo is NOT

- Not a clean rewrite of the FES app — the Cordova/jQuery Mobile/Backbone stack is preserved and patched in place.
- Not a generic watchface design tool — output format is fixed by the watch firmware.
- The decompiled Java under `decompiled/` is for reference only; do not edit it expecting changes to flow back into the APK.
