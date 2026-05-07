# App Icon

Generate via the **`Generate App Icon`** GitHub Actions workflow, or locally:

```sh
OPENAI_API_KEY=sk-... \
ICON_CONCEPT="abstract watch silhouette with rising sun, community emblem" \
ICON_STYLE="minimalist flat" \
ICON_PALETTE="#101318,#F4F1EA,#D0A85C" \
ICON_SLUG="fes-community-v1" \
node server-mirror/scripts/generate-app-icon.mjs
```

Each run produces `ios-app/store-assets/icon/<slug>/`:

| File | Use |
|---|---|
| `icon-master.png` | 1024×1024 sRGB, no alpha — the source of truth |
| `icon-{20…180}.png` | All iOS device sizes |
| `icon-1024.png` | Copy of master, named for App Store Connect |
| `AppIcon.appiconset/` | **Drop-in for Xcode** — copy into `platforms/ios/<App>/Images.xcassets/` (replace existing `AppIcon.appiconset`) |
| `request.json` | Audit trail of prompt + model |

## Wiring into Xcode

After generation, in the iOS workspace:

```sh
cp -R ios-app/store-assets/icon/<slug>/AppIcon.appiconset \
      ios-app/platforms/ios/FES\ Community/Images.xcassets/
```

Or in Xcode: drag `AppIcon.appiconset` onto `Images.xcassets`, replace existing.

## Constraints baked into the prompt

- Square 1024×1024
- No text, no Sony branding, no transparency
- Edge-to-edge (iOS masks corners — never pre-round)
- Flattened on white in post (Apple rejects icons with alpha channel)

## Fallback

If you have a hand-drawn 1024×1024 master and just need the size set, drop it as `icon-master.png` directly here and run:

```sh
cd ios-app/store-assets/icon
for size in 20 29 40 50 57 58 60 72 76 80 87 100 114 120 144 152 167 180 1024; do
  magick icon-master.png -resize ${size}x${size} -alpha off -strip icon-${size}.png
done
```
