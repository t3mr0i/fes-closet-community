// Secrets bag for the FES Closet community fork.
// Real values are held in skip-worktree local copies of this file in
// raw-apk/assets/www/scripts/, ios-app/www/scripts/, and (gitignored)
// ios-app/platforms/ios/www/scripts/. The committed copy uses
// __PLACEHOLDER__ strings so the public repo never carries secrets.
window.FES_SECRETS = {
    // OpenAI image + text APIs (in-app AI watchface generator)
    OPENAI_API_KEY: "__OPENAI_API_KEY__",
    OPENAI_MODEL: "gpt-image-2",
    // gpt-image-2 max aspect ratio is 3:1; the watch is 1:4.6, so we ask
    // for a 1:3 portrait (1024x3072) and crop the top/bottom band on the
    // canvas rasterizer.
    OPENAI_SIZE: "1024x3072",
    OPENAI_REPHRASE_MODEL: "gpt-5.4-nano",

    // GitHub fine-grained PAT scoped to Contents:write on
    // t3mr0i/fes-closet-community. Used by the in-app "Submit to
    // Community" flow to PUT skin.zip + metadata.json into
    // submissions/pending/<slug>/. A separate workflow promotes
    // approved submissions into the public catalog.
    GITHUB_SUBMISSION_TOKEN: "__GITHUB_SUBMISSION_TOKEN__",
    GITHUB_SUBMISSION_REPO: "t3mr0i/fes-closet-community",
    GITHUB_SUBMISSION_BRANCH: "codex/fes-community-offline"
};
