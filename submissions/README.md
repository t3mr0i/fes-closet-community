# Community Skin Submissions

The in-app **AI Watchface generator → Share with community** flow PUTs three
files into `submissions/pending/<slug>/` via the GitHub Contents API:

- `metadata.json` — concept, refined-concept, style, mood, source, slug, ISO timestamp
- `skin.zip`     — installable watch skin (config.json + bg.png) — same format as bundled skins
- `preview.png`  — 152×704 monochrome PNG, the watchface artwork itself

## Reviewing a pending submission

1. Browse `submissions/pending/<slug>/preview.png` in the GitHub UI to see the
   generated watchface.
2. Read `metadata.json` for the concept and refined-concept that produced it.
3. To **approve**: Actions tab → "Promote Community Submission" → Run workflow
   → enter the slug → leave reject as `false`. The workflow:
   - moves files to `submissions/approved/<slug>/`
   - copies `skin.zip` and `preview.png` into the live Pages storage path
   - appends an entry to `skins-en-us.json` / `skins-ja-jp.json` / `skins-zh-cn.json`
     in both the Pages mirror and the bundled APK/iOS data
   - the next Pages deploy publishes it to the public catalog
4. To **reject**: same workflow, set reject to `true`. Files move to
   `submissions/rejected/<slug>/`.

## Auth

The in-app submitter authenticates with a hardcoded fine-grained PAT in
`scripts/secrets.js` (skip-worktree). Scope: Contents:write on this repo.
If the token leaks, rotate via GitHub → Settings → Developer Settings →
Fine-grained tokens.
