# App Store Screenshots

Required (App Store Connect, as of 2026):

| Device class | Size | Min count | Notes |
|---|---|---|---|
| iPhone 6.9" (15/16 Pro Max) | 1290×2796 portrait | 3 | **Required** |
| iPhone 6.5" (older Plus/Max) | 1284×2778 or 1242×2688 | optional fallback | Auto-scaled if absent |
| iPad 13" (M-class) | 2064×2752 | only if iPad universal | We ship universal — required |

Suggested 5 screens:

1. **Connection** — pairing screen with watch silhouette
2. **Generator** — prompt → preview SVG of watchface
3. **Closet** — grid of installed/available faces
4. **Detail** — single face with "Send to Watch" CTA
5. **Settings** — about / open-source notice / non-Sony disclaimer

## Capture flow

```sh
# 1. Build & run on iPhone 16 Pro Max simulator
cd ios-app
npx cordova prepare ios
open platforms/ios/*.xcworkspace
# In Xcode: select "iPhone 16 Pro Max", Run, then Cmd+S in simulator
# Screenshots land on Desktop at correct resolution
```

For iPad: use "iPad Pro 13-inch (M4)" simulator.
