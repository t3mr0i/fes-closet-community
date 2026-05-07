# App Store Listing — FES Community

## App Name (max 30 chars)
`FES Community`  *(13 chars)*

## Subtitle (max 30 chars)
`Watchfaces for FES U Watch`  *(26 chars)*

## Promotional Text (max 170 chars, editable post-release)
> Open community companion app for the Sony FES U Watch. Design, share, and install custom watchfaces. Independent project — not affiliated with Sony.

## Description (max 4000 chars)
```
FES Community is the open-source companion app for the Sony FES U Watch, built and maintained by enthusiasts after the original service was discontinued.

— Browse a community-curated catalog of watchfaces
— Generate your own face from a prompt or photo
— Send designs directly to your watch over Bluetooth Low Energy
— Works fully offline against a static community mirror

This app is an independent community project. It is not made by, endorsed by, sponsored by, or affiliated with Sony Corporation. "FES" and "FES U Watch" are trademarks of their respective owners and are used here only to identify the compatible hardware.

Source code, watchface format documentation, and build scripts:
github.com/t3mr0i/fes-closet-community

The app uses Bluetooth only to communicate with your FES U Watch. No tracking, no analytics, no account required.
```

## Keywords (max 100 chars, comma-separated)
```
watchface,wearable,bluetooth,companion,sony,fes,watch,custom,community,open source
```

## Category
- **Primary:** Lifestyle
- **Secondary:** Utilities

## Age Rating
4+ (no restricted content)

## Support URL
`https://github.com/t3mr0i/fes-closet-community/issues`

## Marketing URL (optional)
`https://t3mr0i.github.io/fes-closet-community/`

## Privacy Policy URL  *(REQUIRED)*
Host the file `store-assets/privacy-policy.md` rendered as HTML, e.g.:
`https://t3mr0i.github.io/fes-closet-community/privacy.html`

## Copyright
`2026 FES Community Project`

---

## App Review Information — Notes to Reviewer

```
Hello reviewer,

This is a community-built companion app for the Sony FES U Watch (a smartwatch
discontinued by Sony in 2018). The original Sony "FES Closet" companion app was
withdrawn from stores when Sony shut down the backend service. Existing watch
owners have no way to install new watchfaces without it.

This app is an independent open-source rebuild. It connects to the watch over
Bluetooth Low Energy and transfers community-designed watchfaces. It is NOT
affiliated with Sony in any way; this is stated in the app description and in
the Settings screen.

TESTING WITHOUT WATCH HARDWARE:
The watch itself is rare. To verify the app's UI and BLE permission handling
without the hardware, please:
  1. Launch the app — main screen loads with a watchface catalog (offline).
  2. Tap "Generate" — you can create a new watchface design and preview it.
  3. Tap "Send to Watch" — Bluetooth permission prompt appears; if no watch is
     present, the app will scan and time out gracefully.

The catalog and watchface ZIP files are fetched from a static GitHub Pages
mirror operated by the community (no user data sent, no accounts).

Source code: https://github.com/t3mr0i/fes-closet-community
Trademark: "FES" and "Sony" mentioned only for hardware compatibility.
```

## Export Compliance

`ITSAppUsesNonExemptEncryption = NO` is set in Info.plist (only HTTPS, no custom crypto). No additional ECCN documentation needed.
