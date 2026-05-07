// Secrets bag for the FES Closet community fork.
// The real OPENAI_API_KEY value is held in a skip-worktree local copy of
// this file. The committed copy uses a placeholder so the public repo
// never carries the secret. See CLAUDE.md / DECOMPILE_NOTES.md for the
// rotate-and-restore dance.
window.FES_SECRETS = {
    OPENAI_API_KEY: "__OPENAI_API_KEY__",
    OPENAI_MODEL: "gpt-image-2",
    // gpt-image-2 max aspect ratio is 3:1; the watch is 1:4.6, so we ask
    // for a 1:3 portrait (1024x3072) and crop the top/bottom band on the
    // canvas rasterizer.
    OPENAI_SIZE: "1024x3072"
};
