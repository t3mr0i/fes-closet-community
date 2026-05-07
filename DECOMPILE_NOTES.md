# Sony FES U Watch APK decompile notes

APK: `FES Closet_1.13.0_APKPure.apk`

## Output folders

- `raw-apk/` - APK contents extracted as-is.
- `decompiled/` - JADX decompile output.
- `analysis-js/` - formatted copies of the important minified JavaScript files.

`jadx` completed with 3 decompile errors, but produced usable Java sources and resources.

## App basics

- App name: FES Closet / FES-SA
- Package: `jp.co.sony.fes`
- Version: `1.13.0`
- Version code: `229`
- Android min SDK: `21`
- Android target SDK: `28`
- Main activity: `jp.co.sony.fes.MainActivity`
- Framework: Cordova / PhoneGap hybrid app

Important native/plugin files:

- `decompiled/resources/AndroidManifest.xml`
- `decompiled/resources/res/xml/config.xml`
- `decompiled/sources/jp/co/sony/fes/MainActivity.java`
- `decompiled/sources/jp/co/sony/fes/nativebridge/LocalContentProvider.java`
- `decompiled/sources/com/randdusing/bluetoothle/BluetoothLePlugin.java`

Important web/app files:

- `raw-apk/assets/www/scripts/config.js`
- `raw-apk/assets/www/lib/scripts/fes.protocol.ble.js`
- `raw-apk/assets/www/lib/scripts/fes.plugin.fi-watch.js`
- `analysis-js/fes.protocol.ble.pretty.js`
- `analysis-js/fes.plugin.fi-watch.pretty.js`

## Local content

This APK appears to be built for local world-wide content:

```js
Config.TARGET_SERVER = "prod-local-ww"
Config.BUILD_TYPE = "ww"
```

Local store data exists under:

- `raw-apk/assets/www/res/data/api/store/`
- `raw-apk/assets/www/res/data/storage/`

There are 167 bundled `skin.zip` files.

Example `skin.zip` structure:

- `config.json`
- PNG assets such as `bg.png`, `hh-0.png` ... `hh-9.png`, `mm-0.png` ... `mm-9.png`

The example `config.json` describes a 152 x 704 background and component layout data. The converter code turns this JSON plus PNG files into the binary watch payload.

Firmware files are bundled under:

- `raw-apk/assets/www/res/data/fw/main.bin`
- `raw-apk/assets/www/res/data/fw/main_hi.bin`
- `raw-apk/assets/www/res/data/fw/version.json`

## BLE protocol findings

FES BLE service:

- Service UUID: `BD090001-605B-5558-A58A-880C162ACA10`

Important characteristics:

- ControlPoint: `BD090010-605B-5558-A58A-880C162ACA10`
- DataPacket_C_P: `BD090011-605B-5558-A58A-880C162ACA10`

Relevant command codes from `fes.protocol.ble`:

- `GetStatus`: `2`
- `FotaQuery`: `19`
- `FotaStart`: `21`
- `VerifyFotaData`: `22`
- `FotaCancel`: `23`
- `SetAccountID`: `32`
- `ClearAccountID`: `33`
- `SkinInstall`: `48`
- `VerifySkinImage`: `49`
- `SkinInstallCancel`: `50`
- `SkinUninstall`: `51`
- `SkinList`: `52`
- `DataAck`: `80`

Skin install flow:

1. Convert skin ID hex string to 16 bytes.
2. Read skin blob as `Uint8Array`.
3. Post `SkinInstall` to ControlPoint with payload:
   - 16-byte skin ID
   - 3-byte image/data length
4. Receive install handle and starting packet number.
5. Send image data to `DataPacket_C_P`.
6. Data is sent in 19-byte chunks plus a 1-byte packet sequence number.
7. Session sends up to 10 writes before waiting for `DataAck`.
8. Verify image by sending handle plus 4-byte CRC with `VerifySkinImage`.

The key implementation starts around:

- `analysis-js/fes.plugin.fi-watch.pretty.js`: `installSkin`
- `analysis-js/fes.protocol.ble.pretty.js`: `installSkin`, `_postSkinTransferRequest`, `transferData`, `_postDataPacketsQuick`, `_verifyImage`

Skin package/conversion code:

- `analysis-js/fes.plugin.fi-watch.pretty.js`: `FIImageConverter`, `FIImageCompressor`, `FIJSONConverter`, `buildStructureInfo`, `buildImageData`, `package`

## Practical next steps

1. Preserve the original APK and generated folders.
2. Use `analysis-js/fes.protocol.ble.pretty.js` to document the full BLE protocol.
3. Inspect one `skin.zip` to reconstruct the exact skin archive format.
4. Decide whether the community app should be:
   - a patched Cordova APK, or
   - a clean new Android app that reuses only the documented BLE protocol and skin format.
5. For rebuilding the original APK, install/use `apktool` in addition to `jadx`.

## Minimal Test Skin

A first server-independent test package was created here:

- `test-skins/minimal-bg/bg.png`
- `test-skins/minimal-bg/config.json`
- `test-skins/minimal-bg/minimal-bg.skin.zip`

This package contains only one 152 x 704 grayscale background image. It is meant to test the local conversion and BLE transfer path before adding watch-face/time components.

Expected app flow for upload:

1. Skin object provides `id` and `getSkinZip()`.
2. Transfer manager calls `getSkinData()`.
3. `FIWatchTransfer.convertSkin()` calls `FIWatchConverter.convertSkinData()`.
4. Converter parses ZIP, builds binary `SKNH`/`INFO`/`IMG `/`SKNF` payload.
5. Transfer manager calls `installSkin(skin.id, convertedBlob)`.
6. BLE layer runs `SkinInstall`, data packet transfer, and `VerifySkinImage`.

## Offline Patch

Patched file:

- `raw-apk/assets/www/scripts/app.js`

Backups:

- `raw-apk/assets/www/scripts/app.js.before-offline-patch`
- `raw-apk/assets/www/scripts/init.js.before-offline-patch`

Changes:

- Disabled Sony/S3 app update check.
- Disabled Sony notification server registration.

The original APK was already configured with `TARGET_SERVER = "prod-local-ww"`, so store metadata and storage assets are local by default.

## Static Server Mirror

A static mirror was prepared under `server-mirror/public/`.

This is deployed through GitHub Pages and is now the community-hosted catalog.
The app no longer needs the Sony server for store browsing or skin downloads.

Hosting support files:

- `.github/workflows/deploy-pages.yml`
- `.github/workflows/generate-watchface.yml`
- `server-mirror/public/index.json`
- `server-mirror/public/index.html`
- `server-mirror/public/generated/`
- `server-mirror/scripts/generate-watchface.mjs`

Published URL:

- `https://t3mr0i.github.io/fes-closet-community/`

The patched app keeps `Config.TARGET_SERVER = "prod-local-ww"` so legacy
offline gates remain active. The local endpoint constants are redirected:

- `STORE_API_ENDPOINT_LOCAL = "https://t3mr0i.github.io/fes-closet-community/api/"`
- `STORE_STORAGE_ENDPOINT_LOCAL = "https://t3mr0i.github.io/fes-closet-community/storage/"`

The transfer path to the watch remains local Bluetooth LE.

## Legacy UI cleanup

- Removed the visible firmware update entry from watch settings.
- Stopped OOBE and transfer flows from forcing users into the legacy firmware
  update path when Sony update services are unavailable.
- Removed the legacy purchase restore button from Closet settings.
- Removed dead EULA/Terms/Privacy links and the analytics opt-in toggle from
  the Legal screen; the page now exposes only bundled OSS licenses.
- Redirected support/legal URLs and support-style error messages to the
  community catalog instead of Sony/FES support pages.

Local test passed for:

- `/index.json`
- `/api/store/skins-en-us.json`
- `/storage/community-0000-0000-0000-000000000001/c0ffee00000000000000000000000001/skin.zip`

## Community Skin Support

First community skin added:

- Creator ID: `community-0000-0000-0000-000000000001`
- Skin ID: `c0ffee00000000000000000000000001`
- Name: `Community Test Skin`

Added to:

- `raw-apk/assets/www/res/data/api/store/skins-en-us.json`
- `raw-apk/assets/www/res/data/api/store/skins-ja-jp.json`
- `raw-apk/assets/www/res/data/api/store/skins-zh-cn.json`
- `raw-apk/assets/www/res/data/api/store/creators-en-us.json`
- `raw-apk/assets/www/res/data/api/store/creators-ja-jp.json`
- `raw-apk/assets/www/res/data/api/store/creators-zh-cn.json`
- detail files under `raw-apk/assets/www/res/data/api/store/skins/`
- creator files under `raw-apk/assets/www/res/data/api/store/creators/`
- assets under `raw-apk/assets/www/res/data/storage/community-0000-0000-0000-000000000001/`

The same metadata/assets were added to `server-mirror/public/`.

Updated APK:

- `FES Closet_1.13.0_OFFLINE_PATCH_debugsigned.apk`

## Watchface generator output

The Actions workflow `generate-watchface.yml` produces two artifacts per run under
`server-mirror/public/generated/watchfaces/<slug>/`:

- `watchface-package.zip` - design bundle: `request.json`, `spec.json`,
  `preview.svg`, `README.md`. For browsing/editing only. Not installable.
- `skin.zip` - watch-installable skin matching the format in
  `test-skins/minimal-bg/`: a single 152x704 8-bit grayscale `bg.png` filled
  with the requested background color, plus a minimal `config.json` with one
  `background` component.

PNG is generated in pure Node via a small encoder (`encodeFlatGrayPng` in
`server-mirror/scripts/generate-watchface.mjs`). No imagemagick/sharp dependency.

Limitation: generated skins are flat backgrounds only. Time/date/battery layers
shown in the SVG preview are not rasterized into the installable skin -
those need digit-strip PNGs (`hh-0.png`...`hh-9.png`, `mm-0.png`...`mm-9.png`)
which is a separate piece of work.

## iOS splash hang debug (2026-05-07)

Symptom: iOS Cordova build (`ios-app/`) hangs at the FASHION ENTERTAINMENTS
splash. Last visible log on device:

    [INIT] deviceready fired
    JQMIGRATE: Migrate is installed, version 3.1.0
    [INIT] framework ready, loading app
    [warn] [FES.Model.FIWatchConnection] context property allowed debug mode only.   (x2)
    [INIT] calling app.main()
    [warn] [CDP.Framework] cdp.framework.jqm is already initialized, ignored.

So `app.main()` is invoked but nothing inside it logs. Probes were added to
`ios-app/platforms/ios/www/scripts/app.js` (the file actually packaged into
the IPA - `cordova prepare` had not been re-run, so source-side
`ios-app/www/scripts/app.js` patches did not flow through). Probes log
entry/exit of:

- `b()` (the AMD-exported `main` function)
- `c()` (init runtime: `$.when(g, Analytics.initialize, ClosetCollection.ensureFetchComplete)`)
- the three deferreds inside `c()` (each individually so we see which one hangs)
- `d()` (`checkOobeCompletion` -> route selection)
- `Router.start()` return

Same probes were also added to `ios-app/www/scripts/app.js` so they survive a
future `cordova prepare ios`.

Additionally, the Sony anti-debug guard

    Object.defineProperty(n, "context", { get: function () {
        return CDP.global.Config.DEBUG ? m : void console.warn(...);
    }});

on `FES.Model.FIWatchConnection` was simplified to always return `m`. The
guard was cosmetic noise (logged twice during init, logged the warnings
visible in `[INIT]` traces) - returning `undefined` to its callers in
non-DEBUG mode is the only thing it did, and that's a real footgun for
anything that reads `.context` later. Patch applied to both
`ios-app/www/scripts/app.js` and `ios-app/platforms/ios/www/scripts/app.js`;
not (yet) applied to `raw-apk/assets/www/scripts/app.js`.

Backups: `ios-app/platforms/ios/www/scripts/app.js.before-offline-patch`
already exists from earlier work and was not touched by these edits.

Stubs reviewed under `fes-nativebridge-stubs/src/ios/`:
`FESBatteryStatus`, `FESLocalContentProvider`, `FESMisc`,
`FESNotificationHandler`, `FESUUID`. All five call `[self resolveParams:...]`
on every code path - none of them is the silent-hang culprit. `plugin.xml`
has only `<source-file>` entries (no `<feature>`), but `cdp-nativebridge`
resolves classes via `NSClassFromString`, so `<feature>` registration is
not required.

Hang root cause confirmed by next probe run:

    [c] g done          <- resolved
    [c] Closet done     <- resolved
    (no [c] Analytics done)

`Analytics.initialize()` was the silent-hung deferred. It calls
`ga.startTrackerWithId(id, success, error)` on the cordova-plugin-google-
analytics shim. The previous `window.ga = {...}` shim in `debug-console.js`
returned synchronously without invoking the success callback, so the
deferred never resolved and `$.when()` in `c()` never completed.

Fix: replaced `a.Utils.Analytics.initialize()` in `c()` with
`$.Deferred().resolve().promise()` (Analytics is fully removed for the
community build - no GA tracking, ever). Done in both
`ios-app/www/scripts/app.js` and `ios-app/platforms/ios/www/scripts/app.js`.

Removed the `window.ga = {...}` stub from `debug-console.js` (both copies).
All other `Analytics.trackView/trackEvent/trackError` calls in the app are
guarded by `d.Mobile && this.enable`, and `enable` requires `s_initialized`,
which now stays `false` permanently - so the guards short-circuit and `ga`
is never read. No `cordova-plugin-google-analytics` is installed as a real
plugin (it was never in package.json), so nothing else to remove.

## Mirror cleanup (2026-05-07)

- Removed `server-mirror/public/_headers` - GitHub Pages does not honor
  Cloudflare/Netlify-style `_headers` syntax, so the file was dead config.
- Removed `server-mirror/public/api/store/extstoreskins.json` - not referenced
  by any app code (`raw-apk/assets/www/`, `analysis-js/`, `ios-app/www/`) and
  one of its three skin IDs (`f9c462bf980e41df918b27da3db4f313`) had no
  corresponding metadata or storage entries.
