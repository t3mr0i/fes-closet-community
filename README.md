# FES Closet — Community Offline

Community revival of the **Sony FES U Watch** companion app (`FES Closet`, package `jp.co.sony.fes`, v1.13.0). The original Sony backend is gone; this repo keeps the watch usable by:

- Patching the existing APK to run fully offline against a community mirror.
- Hosting that mirror as a static site on GitHub Pages.
- Documenting the BLE protocol the app uses to push watchfaces ("skins") to the watch.
- Staging an iOS Cordova rebuild of the same app.

The watch hardware is unchanged — only the BLE skin-transfer path runs at runtime. Backend is purely static catalog metadata + skin ZIPs.

## Hosted catalog

<https://t3mr0i.github.io/fes-closet-community/>

The Pages site exposes three tabs: **Community** (community-maintained skins), **Generated** (workflow-built watchfaces), **Official bundle** (preserved skins from the original app).

## Repo map

| Path | What's there |
|---|---|
| `raw-apk/` | Extracted Android app. `assets/www/scripts/app.js` is patched in place; `*.before-offline-patch` are the originals. |
| `decompiled/` | JADX output. Reference only. |
| `analysis-js/` | Pretty-printed copies of the four important minified JS files. Read these, not `raw-apk/`. |
| `server-mirror/public/` | Static site deployed by GitHub Pages. |
| `server-mirror/scripts/generate-watchface.mjs` | Watchface generator used by the Actions workflow. |
| `ios-app/` | Cordova iOS rebuild + the local `fes-nativebridge-stubs` plugin. |
| `test-skins/minimal-bg/` | Minimal hand-built skin used as the format reference. |
| `DECOMPILE_NOTES.md` | Running log of patches, BLE command codes, skin install flow, endpoint redirections. |
| `CLAUDE.md` | Guidance for AI assistants working in this repo. |

## Quick start

Local mirror preview:

```sh
npx serve server-mirror/public
# or
python3 -m http.server 8080 --directory server-mirror/public
```

Generate a watchface locally:

```sh
WATCHFACE_DIRECTION="minimal warm" \
WATCHFACE_PALETTE="#101318,#F4F1EA,#D0A85C" \
node server-mirror/scripts/generate-watchface.mjs
```

iOS build:

```sh
cd ios-app
npm install
npx cordova build ios
```

## Status

See `DECOMPILE_NOTES.md` for what's already patched. Open issues / known gaps:

- Watchface generator emits a metadata zip that previews the design but is not yet a watch-installable skin (PNG layers are not rasterized — only a flat background is produced).
- Real installable skins on the watch require the `config.json` + PNG component layout documented in `test-skins/minimal-bg/`.
- Original APK is preserved unsigned + debug-signed; no automated build pipeline.

## License / attribution

Decompiled and patched files retain their original Sony copyrights. Patches and tooling here are community-maintained for keeping existing watches usable. Not affiliated with Sony.
