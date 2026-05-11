#!/usr/bin/env bash
# Set or unset local API keys in the three scripts/secrets.js copies and
# manage their skip-worktree state. See README.md for the full story.
#
# Usage:
#   ./scripts/setup-secrets.sh              # interactive: prompt for keys, inject, skip-worktree
#   ./scripts/setup-secrets.sh --release    # remove keys, reset to placeholders, un-skip-worktree
#   ./scripts/setup-secrets.sh --status     # show current state per file
#
# The three secrets.js copies kept in sync:
#   - raw-apk/assets/www/scripts/secrets.js              (committed, skip-worktree when keys are set)
#   - ios-app/www/scripts/secrets.js                     (committed, skip-worktree when keys are set)
#   - ios-app/platforms/ios/www/scripts/secrets.js       (gitignored — always matches the live values)

set -euo pipefail

# Resolve repo root from this script's location.
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$ROOT"

FILES=(
  "raw-apk/assets/www/scripts/secrets.js"
  "ios-app/www/scripts/secrets.js"
  "ios-app/platforms/ios/www/scripts/secrets.js"
)

PLACEHOLDER_OPENAI="__OPENAI_API_KEY__"
PLACEHOLDER_GITHUB="__GITHUB_SUBMISSION_TOKEN__"

# Files that are *tracked* and need skip-worktree juggling.
TRACKED_FILES=(
  "raw-apk/assets/www/scripts/secrets.js"
  "ios-app/www/scripts/secrets.js"
)

bold() { printf "\033[1m%s\033[0m" "$1"; }
green() { printf "\033[32m%s\033[0m" "$1"; }
yellow() { printf "\033[33m%s\033[0m" "$1"; }
red() { printf "\033[31m%s\033[0m" "$1"; }

show_status() {
  echo
  echo "$(bold 'secrets.js status'):"
  for f in "${FILES[@]}"; do
    if [ ! -f "$f" ]; then
      echo "  $(red 'missing') $f"
      continue
    fi
    local has_oai="placeholder"
    local has_gh="placeholder"
    grep -q "OPENAI_API_KEY: \"sk-" "$f" 2>/dev/null && has_oai="$(green 'set')"
    grep -q "GITHUB_SUBMISSION_TOKEN: \"github_pat_" "$f" 2>/dev/null && has_gh="$(green 'set')"
    local skip="not skip-worktree"
    if printf '%s\n' "${TRACKED_FILES[@]}" | grep -qx "$f"; then
      if git ls-files -v -- "$f" 2>/dev/null | grep -q "^S"; then
        skip="$(green 'skip-worktree on')"
      else
        skip="$(yellow 'skip-worktree off')"
      fi
    else
      skip="(gitignored)"
    fi
    echo "  $f"
    echo "    OPENAI:  $has_oai    GITHUB:  $has_gh    $skip"
  done
  echo
}

inject_keys() {
  local oai="$1"
  local gh="$2"
  for f in "${FILES[@]}"; do
    if [ ! -f "$f" ]; then
      echo "  $(yellow 'skip') $f (does not exist yet — run cordova prepare or git pull first)"
      continue
    fi
    # Replace whichever placeholder or previous value is currently in the OPENAI line.
    # Use python for reliable replacement that doesn't choke on the slashes in tokens.
    python3 - "$f" "$oai" "$gh" <<'PY'
import re, sys
path, oai, gh = sys.argv[1], sys.argv[2], sys.argv[3]
src = open(path).read()
def replace_value(text, key, value):
    # key: "VALUE",
    pattern = re.compile(r'(' + re.escape(key) + r'\s*:\s*")[^"]*(")')
    return pattern.sub(lambda m: m.group(1) + value + m.group(2), text)
src = replace_value(src, 'OPENAI_API_KEY', oai)
src = replace_value(src, 'GITHUB_SUBMISSION_TOKEN', gh)
open(path, 'w').write(src)
PY
    echo "  $(green '✓') wrote keys into $f"
  done
}

set_skip_worktree() {
  for f in "${TRACKED_FILES[@]}"; do
    [ -f "$f" ] || continue
    if git ls-files --error-unmatch -- "$f" >/dev/null 2>&1; then
      git update-index --skip-worktree -- "$f"
      echo "  $(green '✓') skip-worktree set on $f"
    fi
  done
}

unset_skip_worktree() {
  for f in "${TRACKED_FILES[@]}"; do
    [ -f "$f" ] || continue
    if git ls-files -v -- "$f" 2>/dev/null | grep -q "^S"; then
      git update-index --no-skip-worktree -- "$f"
      echo "  $(yellow '○') skip-worktree off on $f"
    fi
  done
}

case "${1:-}" in
  --status|status)
    show_status
    exit 0
    ;;
  --release|release)
    echo "$(bold 'Resetting secrets.js to placeholders for commit') …"
    unset_skip_worktree
    inject_keys "$PLACEHOLDER_OPENAI" "$PLACEHOLDER_GITHUB"
    echo
    echo "$(green 'Done.') You can now stage and commit the placeholder versions:"
    echo "  git add ${TRACKED_FILES[*]}"
    echo "  git commit -m 'secrets: …'"
    echo
    echo "When you're back, run $(bold "./scripts/setup-secrets.sh") to re-inject your local keys."
    exit 0
    ;;
  ""|setup|--setup)
    show_status
    echo "$(bold 'Set up local API keys')"
    echo "  Both keys are optional. Press ENTER to leave a key as the placeholder."
    echo
    read -r -p "OpenAI API key (sk-…): " OAI
    OAI="${OAI:-$PLACEHOLDER_OPENAI}"
    read -r -p "GitHub fine-grained PAT (github_pat_…): " GH
    GH="${GH:-$PLACEHOLDER_GITHUB}"
    echo
    echo "$(bold 'Injecting keys') …"
    inject_keys "$OAI" "$GH"
    echo
    echo "$(bold 'Marking files skip-worktree') (so they never get committed) …"
    set_skip_worktree
    echo
    echo "$(green 'Done.') Verify with: ./scripts/setup-secrets.sh --status"
    exit 0
    ;;
  *)
    echo "Usage: ./scripts/setup-secrets.sh [--status | --release]"
    exit 1
    ;;
esac
