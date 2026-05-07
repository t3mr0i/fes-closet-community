# iOS App Store Release — FES Community

End-to-end checklist for shipping `ios-app/` to the App Store.

## 0. Prerequisites

- [ ] **Apple Developer Program membership** — $99/yr — `developer.apple.com/programs`
- [ ] Mac with Xcode 16+ installed
- [ ] CocoaPods (`brew install cocoapods`) — Cordova-iOS 8 uses Pods
- [ ] Bundle ID `community.feswatch.app` registered under Identifiers in Apple Developer
- [ ] An App record created in App Store Connect with that Bundle ID
- [ ] Master 1024×1024 icon PNG at `store-assets/icon/icon-master.png`
- [ ] Privacy policy hosted at a public HTTPS URL (see `store-assets/privacy-policy.md`)

## 1. Project sanity

```sh
cd ios-app
rm -rf platforms node_modules
npm install
npx cordova platform add ios
npx cordova prepare ios
```

After prepare, verify in `platforms/ios/FES Community/FES Community-Info.plist`:
- `CFBundleIdentifier` = `community.feswatch.app`
- `ITSAppUsesNonExemptEncryption` = `false`
- `UIBackgroundModes` contains `bluetooth-central`
- All `NS*UsageDescription` strings are present
- `MinimumOSVersion` = `14.0`

Copy the privacy manifest into the bundle:
```sh
cp res/ios/PrivacyInfo.xcprivacy "platforms/ios/FES Community/Resources/"
```
Then in Xcode add `PrivacyInfo.xcprivacy` to the app target (Build Phases → Copy Bundle Resources). Cordova does **not** wire this automatically.

## 2. Signing

In Xcode (`platforms/ios/*.xcworkspace`):
1. Select project → target **FES Community** → Signing & Capabilities
2. Team: your Apple Developer team
3. Bundle Identifier: `community.feswatch.app`
4. Automatically manage signing: ON (simplest) — Xcode will create the App ID and provisioning profile
5. Add Capability: **Background Modes** → Uses Bluetooth LE accessories ✓

## 3. Icon set

If you have `store-assets/icon/icon-master.png`:
```sh
cd store-assets/icon
for size in 20 29 40 50 57 58 60 72 76 80 87 100 114 120 144 152 167 180 1024; do
  magick icon-master.png -resize ${size}x${size} icon-${size}.png
done
```
Then add to `config.xml` inside `<platform name="ios">`:
```xml
<icon src="store-assets/icon/icon-1024.png" />
```
…and `cordova prepare ios` again. Or import the master PNG into `Assets.xcassets/AppIcon` in Xcode (lets Xcode size it).

## 4. Archive & upload

```sh
# Build for device, generic
# In Xcode: Product → Destination → Any iOS Device (arm64)
#          Product → Archive
```

When archive completes, Organizer opens automatically.
1. Validate App → fix any reported issues (missing icon size, missing privacy strings, etc.)
2. Distribute App → App Store Connect → Upload

The build appears in App Store Connect → TestFlight → Builds within ~5–30 min after processing.

## 5. App Store Connect — App Information

Use copy from `store-assets/marketing-copy.md`. Required fields:

- App Name, Subtitle, Promotional Text, Description
- Keywords
- Support URL: `https://github.com/t3mr0i/fes-closet-community/issues`
- Privacy Policy URL (must resolve to public HTTPS page)
- Category: Lifestyle → Utilities (secondary)
- Age Rating questionnaire → all "No"
- Pricing: Free, all territories
- App Privacy questionnaire: select **"Data Not Collected"** for every category
- Export Compliance: encryption = uses only standard encryption (HTTPS) → exempt

## 6. Screenshots

See `store-assets/screenshots/README.md`. Capture 3–5 per required device class.

## 7. Submit for review

- Build → select uploaded build from TestFlight
- App Review Information → paste the reviewer notes from `marketing-copy.md`
- Demo account: not needed (no login)
- Submit for Review

**Expect a back-and-forth on:**
- Trademark questions about "FES" / "Sony" mentions → answer with the disclaimer text already in the description; reference Apple Guideline 5.2.5 (compatibility apps allowed if disclosed)
- Bluetooth without hardware to test → reviewer notes already cover this
- Background Modes review → only `bluetooth-central` is used; do not add other modes

## 8. After approval

- [ ] Tag the release in git: `git tag ios-1.0.0 && git push --tags`
- [ ] Publish privacy policy URL alongside the GitHub Pages mirror
- [ ] Add App Store badge to README.md

## Known risks / open items

| Risk | Mitigation |
|---|---|
| Apple rejects on trademark (Sony / FES) | Have the disclaimer in description and Settings screen ready; appeal with prior-art (other compatibility apps for discontinued hardware). |
| Cordova plugins flagged for old APIs | Keep `cordova-ios@^8` and `cordova-plugin-bluetoothle@^6.7` (current). Avoid deprecated UIWebView paths. |
| Privacy manifest missing for a third-party plugin | Apple ITC console will flag specific frameworks; add their `PrivacyInfo.xcprivacy` to the relevant Pods. |
| Icon source 144×144 too small | Provide a fresh 1024×1024 master PNG before archive. |
