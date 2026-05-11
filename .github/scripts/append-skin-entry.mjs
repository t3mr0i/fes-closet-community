// Append a community-submission skin entry to all server-mirror skins-*.json
// catalogs. Used by the promote-submission workflow.

import fs from "fs";
import path from "path";

const args = parseArgs(process.argv.slice(2));
if (!args.skinId || !args.creatorId || !args.metadata) {
    console.error("Usage: append-skin-entry.mjs --skinId X --creatorId Y --metadata path/to/metadata.json");
    process.exit(1);
}

const metadata = JSON.parse(fs.readFileSync(args.metadata, "utf8"));
const skinId = args.skinId;
const creatorId = args.creatorId;

const conceptText = (metadata.refinedConcept || metadata.concept || "Community submission").trim();
const styleLabel = (metadata.style || "minimalist").replace(/^\w/, (c) => c.toUpperCase());
const moodLabel  = (metadata.mood || "calm").replace(/^\w/, (c) => c.toUpperCase());

const now = Date.now() / 1000;

// Per-locale name + brief + description
const locales = {
    "en-us": {
        name: titleCaseConcept(conceptText, 40),
        brief: `${styleLabel} • ${moodLabel}`,
        description: conceptText
    },
    "ja-jp": {
        name: titleCaseConcept(conceptText, 40),
        brief: `${styleLabel} • ${moodLabel}`,
        description: conceptText
    },
    "zh-cn": {
        name: titleCaseConcept(conceptText, 40),
        brief: `${styleLabel} • ${moodLabel}`,
        description: conceptText
    }
};

function entry(locale) {
    const l = locales[locale];
    return {
        id: skinId,
        creatorId: creatorId,
        price: "0",
        tier: 0,
        startDate: null,
        endDate: null,
        publishDate: now,
        modifiedDate: now,
        registeredDate: now,
        genreId: null,
        language: locale.split("-")[0],
        name: l.name,
        brief: l.brief,
        description: l.description,
        zip: `<STORAGE_DIRECTORY>/${creatorId}/${skinId}/skin.zip`,
        storeImage:   `<STORAGE_DIRECTORY>/${creatorId}/${skinId}/storeImage.png`,
        detailImage:  `<STORAGE_DIRECTORY>/${creatorId}/${skinId}/detailImage.png`,
        customImage1: `<STORAGE_DIRECTORY>/${creatorId}/${skinId}/customImage1.png`,
        customImage2: "",
        customImage3: "",
        trialImage:   `<STORAGE_DIRECTORY>/${creatorId}/${skinId}/trialImage.png`,
        thumbnail:    `<STORAGE_DIRECTORY>/${creatorId}/${skinId}/thumbnail.png`,
        smallImage:   `<STORAGE_DIRECTORY>/${creatorId}/${skinId}/smallImage.png`
    };
}

const repoRoot = path.resolve(path.dirname(new URL(import.meta.url).pathname), "../..");

for (const locale of Object.keys(locales)) {
    // Update the live Pages catalog
    const pagesPath = path.join(repoRoot, `server-mirror/public/api/store/skins-${locale}.json`);
    appendInto(pagesPath, locale, entry(locale));

    // Update the bundled catalog so a future iOS rebuild also has it
    const bundlePath = path.join(repoRoot, `raw-apk/assets/www/res/data/api/store/skins-${locale}.json`);
    appendInto(bundlePath, locale, entry(locale));

    const iosBundle = path.join(repoRoot, `ios-app/www/res/data/api/store/skins-${locale}.json`);
    appendInto(iosBundle, locale, entry(locale));
}

function appendInto(filePath, locale, e) {
    if (!fs.existsSync(filePath)) {
        console.warn(`skip: ${filePath} not found`);
        return;
    }
    const data = JSON.parse(fs.readFileSync(filePath, "utf8"));
    if (!Array.isArray(data.skins)) data.skins = [];
    // remove any prior entry with the same id
    data.skins = data.skins.filter((s) => s.id !== e.id);
    data.skins.unshift(e);
    data.totalCount = data.skins.length;
    fs.writeFileSync(filePath, JSON.stringify(data, null, 2) + "\n");
    console.log(`updated ${path.relative(repoRoot, filePath)} (${data.skins.length} skins)`);
}

function titleCaseConcept(text, maxLen) {
    let s = text.replace(/\s+/g, " ").trim();
    if (s.length > maxLen) s = s.slice(0, maxLen - 1).replace(/[^\w\s]+$/, "") + "…";
    // first letter capitalised, rest left as-is so user-supplied capitalisation is preserved
    return s.charAt(0).toUpperCase() + s.slice(1);
}

function parseArgs(argv) {
    const out = {};
    for (let i = 0; i < argv.length; i++) {
        if (argv[i].startsWith("--")) {
            const key = argv[i].slice(2);
            const val = argv[i + 1] && !argv[i + 1].startsWith("--") ? argv[++i] : true;
            out[key] = val;
        }
    }
    return out;
}
