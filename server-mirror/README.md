# FES Closet Static Mirror

This folder is the GitHub-native hosting layer for the old Sony content.

It contains copied local APK data:

- `public/api/` - store JSON metadata
- `public/storage/` - icons, images, bundled `skin.zip` files
- `public/fw/` - bundled firmware files
- `public/generated/` - community-generated watchface packages

The current offline APK patch still uses bundled local assets. The mirror is for community hosting and for generated watchfaces that should stay available on GitHub Pages.

## Local test

```sh
npx serve public
```

or any static HTTP server:

```sh
python3 -m http.server 8080 --directory public
```

## Deployment target

Use GitHub Pages with GitHub Actions.

The app expects paths shaped like:

- `/api/store/skins-en-us.json`
- `/api/store/creators-en-us.json`
- `/storage/<creatorId>/<skinId>/skin.zip`
- `/fw/version.json`

## GitHub Pages

1. Keep the static files under `server-mirror/public`.
2. Use `.github/workflows/deploy-pages.yml` to publish the static mirror.
3. GitHub Pages serves the site from the workflow artifact.

The generated watchfaces live under `public/generated/watchfaces/<slug>/` and are published the same way.

The public catalog page at `/` has three user-facing tabs:

- `Community` - community-maintained skins that are part of the store metadata
- `Generated` - watchfaces created by the GitHub Actions generator
- `Official bundle` - the preserved skins that shipped with the original app mirror

Each entry links to its package ZIP and preview/detail assets. The page does not call the Sony backend.

## Watchface generation

Use `.github/workflows/generate-watchface.yml` to create a new watchface package from a direction string.

That workflow:

- takes the prompt inputs from the Actions UI
- writes `request.json`, `spec.json`, `preview.svg`, and `watchface-package.zip`
- updates `public/generated/index.json`
- commits the generated files back to the repo
- triggers the Pages deployment workflow

## VPS

Any static web server works. Example Nginx root:

```nginx
root /var/www/fes-closet-community-mirror/public;
add_header Access-Control-Allow-Origin * always;
```

## App Integration

The current APK still uses bundled local data. To use this hosted mirror, patch `fes.utils.js` or `config.js` so:

- `STORE_API_ENDPOINT` points to `https://t3mr0i.github.io/fes-closet-community/api/`
- `STORE_STORAGE_ENDPOINT` points to `https://t3mr0i.github.io/fes-closet-community/storage/`

Then rebuild/sign the APK.

The Sony server should not be required for browsing or downloading skins. Transfer to the watch remains local Bluetooth LE work inside the patched app.
