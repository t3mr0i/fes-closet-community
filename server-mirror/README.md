# FES Closet Static Server Mirror

This folder is a cheap-server/static-hosting option for the old Sony endpoints.

It contains copied local APK data:

- `public/api/` - store JSON metadata
- `public/storage/` - icons, images, bundled `skin.zip` files
- `public/fw/` - bundled firmware files

For the current offline APK patch, the app still uses bundled local assets. This mirror is useful if we later want the app to point at a community-hosted URL instead.

## Local test

```sh
npx serve public
```

or any static HTTP server:

```sh
python3 -m http.server 8080 --directory public
```

## Cheap deployment targets

- Cloudflare Pages: free/static, good default.
- GitHub Pages: free/static, public repo required unless paid/private setup.
- Netlify/Vercel: free/static, simple drag-and-drop or git deploy.
- Small VPS: more control, but more maintenance.

The app expects paths shaped like:

- `/api/store/skins-en-us.json`
- `/api/store/creators-en-us.json`
- `/storage/<creatorId>/<skinId>/skin.zip`
- `/fw/version.json`

## Recommended: Cloudflare Pages

1. Create a repo containing this `server-mirror/` folder.
2. In Cloudflare Pages, create a project from that repo.
3. Use:
   - Build command: empty
   - Output directory: `server-mirror/public`
4. Deploy.

If you want dynamic generation endpoints too, keep the `functions/` directory in the same Pages project. That gives you:

- `/api/generate-watchface`
- `/api/store/...`
- `/storage/...`

The generator endpoint accepts a natural-language direction and returns a structured watchface spec plus a preview SVG. It is provider-agnostic, so you can wire an LLM behind it later without changing the API shape.

Alternative with Wrangler:

```sh
cd server-mirror
npx wrangler pages deploy public --project-name fes-closet-community-mirror
```

## Netlify

```sh
cd server-mirror
npx netlify deploy --prod --dir public
```

`netlify.toml` is included.

## VPS

Any static web server works. Example Nginx root:

```nginx
root /var/www/fes-closet-community-mirror/public;
add_header Access-Control-Allow-Origin * always;
```

## App Integration

The current APK still uses bundled local data. To use this hosted mirror, patch `fes.utils.js` or `config.js` so:

- `STORE_API_ENDPOINT` points to `https://your-domain.example/api/`
- `STORE_STORAGE_ENDPOINT` points to `https://your-domain.example/storage/`

Then rebuild/sign the APK.

## Generator API

Example request:

```sh
curl -X POST https://your-domain.example/api/generate-watchface \
  -H 'content-type: application/json' \
  -d '{
    "direction": "minimal editorial watchface with a warm accent",
    "style": "minimal",
    "language": "en-US",
    "mustInclude": ["time", "date", "battery"]
  }'
```

Response shape:

- `specVersion`
- `canvas`
- `palette`
- `layout`
- `copy`
- `generationPrompt`
- `previewSvg`

The JSON schema lives at `public/api/watchface-generator/schema.json`.
