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
