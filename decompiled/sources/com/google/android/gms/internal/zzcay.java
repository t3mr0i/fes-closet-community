package com.google.android.gms.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
final class zzcay extends zzcdu {
    private static final Map<String, String> zzimq;
    private static final Map<String, String> zzimr;
    private static final Map<String, String> zzims;
    private static final Map<String, String> zzimt;
    private static final Map<String, String> zzimu;
    private final zzcbb zzimv;
    private final zzcfq zzimw;

    static {
        ArrayMap arrayMap = new ArrayMap(1);
        zzimq = arrayMap;
        arrayMap.put(FirebaseAnalytics.Param.ORIGIN, "ALTER TABLE user_attributes ADD COLUMN origin TEXT;");
        ArrayMap arrayMap2 = new ArrayMap(18);
        zzimr = arrayMap2;
        arrayMap2.put("app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;");
        zzimr.put("app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;");
        zzimr.put("gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;");
        zzimr.put("dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;");
        zzimr.put("measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;");
        zzimr.put("last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;");
        zzimr.put("day", "ALTER TABLE apps ADD COLUMN day INTEGER;");
        zzimr.put("daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;");
        zzimr.put("daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;");
        zzimr.put("daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;");
        zzimr.put("remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;");
        zzimr.put("config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;");
        zzimr.put("failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;");
        zzimr.put("app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;");
        zzimr.put("firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;");
        zzimr.put("daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;");
        zzimr.put("daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;");
        zzimr.put("health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;");
        zzimr.put("android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;");
        ArrayMap arrayMap3 = new ArrayMap(1);
        zzims = arrayMap3;
        arrayMap3.put("realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;");
        ArrayMap arrayMap4 = new ArrayMap(1);
        zzimt = arrayMap4;
        arrayMap4.put("has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;");
        ArrayMap arrayMap5 = new ArrayMap(1);
        zzimu = arrayMap5;
        arrayMap5.put("previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;");
    }

    zzcay(zzccw zzccwVar) {
        super(zzccwVar);
        this.zzimw = new zzcfq(zzvx());
        this.zzimv = new zzcbb(this, getContext(), zzcax.zzawj());
    }

    @WorkerThread
    private final long zza(String str, String[] strArr, long j) {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = getWritableDatabase().rawQuery(str, strArr);
                if (cursorRawQuery.moveToFirst()) {
                    j = cursorRawQuery.getLong(0);
                } else if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return j;
            } catch (SQLiteException e) {
                zzaum().zzaye().zze("Database error", str, e);
                throw e;
            }
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
    }

    @WorkerThread
    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                zzaum().zzaye().log("Loaded invalid null value from database");
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                zzaum().zzaye().log("Loaded invalid blob type value, ignoring it");
                break;
            default:
                zzaum().zzaye().zzj("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                break;
        }
        return null;
    }

    @WorkerThread
    private static void zza(ContentValues contentValues, String str, Object obj) {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzu(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else {
            if (!(obj instanceof Double)) {
                throw new IllegalArgumentException("Invalid value type");
            }
            contentValues.put(str, (Double) obj);
        }
    }

    static void zza(zzcbw zzcbwVar, SQLiteDatabase sQLiteDatabase) {
        if (zzcbwVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        File file = new File(sQLiteDatabase.getPath());
        if (!file.setReadable(false, false)) {
            zzcbwVar.zzayg().log("Failed to turn off database read permission");
        }
        if (!file.setWritable(false, false)) {
            zzcbwVar.zzayg().log("Failed to turn off database write permission");
        }
        if (!file.setReadable(true, true)) {
            zzcbwVar.zzayg().log("Failed to turn on database read permission for owner");
        }
        if (file.setWritable(true, true)) {
            return;
        }
        zzcbwVar.zzayg().log("Failed to turn on database write permission for owner");
    }

    @WorkerThread
    static void zza(zzcbw zzcbwVar, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, Map<String, String> map) throws IllegalStateException, SQLException {
        if (zzcbwVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        if (!zza(zzcbwVar, sQLiteDatabase, str)) {
            sQLiteDatabase.execSQL(str2);
        }
        try {
            zza(zzcbwVar, sQLiteDatabase, str, str3, map);
        } catch (SQLiteException e) {
            zzcbwVar.zzaye().zzj("Failed to verify columns on table that was just created", str);
            throw e;
        }
    }

    @WorkerThread
    private static void zza(zzcbw zzcbwVar, SQLiteDatabase sQLiteDatabase, String str, String str2, Map<String, String> map) throws IllegalStateException, SQLException {
        if (zzcbwVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        Set<String> setZzb = zzb(sQLiteDatabase, str);
        for (String str3 : str2.split(",")) {
            if (!setZzb.remove(str3)) {
                throw new SQLiteException(new StringBuilder(String.valueOf(str).length() + 35 + String.valueOf(str3).length()).append("Table ").append(str).append(" is missing required column: ").append(str3).toString());
            }
        }
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!setZzb.remove(entry.getKey())) {
                    sQLiteDatabase.execSQL(entry.getValue());
                }
            }
        }
        if (setZzb.isEmpty()) {
            return;
        }
        zzcbwVar.zzayg().zze("Table has extra columns. table, columns", str, TextUtils.join(", ", setZzb));
    }

    @WorkerThread
    private static boolean zza(zzcbw zzcbwVar, SQLiteDatabase sQLiteDatabase, String str) throws Throwable {
        Cursor cursorQuery;
        boolean zMoveToFirst;
        Cursor cursor = null;
        try {
            if (zzcbwVar == null) {
                throw new IllegalArgumentException("Monitor must not be null");
            }
            try {
                cursorQuery = sQLiteDatabase.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
                try {
                    zMoveToFirst = cursorQuery.moveToFirst();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (SQLiteException e) {
                    e = e;
                    zzcbwVar.zzayg().zze("Error querying for table", str, e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    zMoveToFirst = false;
                    return zMoveToFirst;
                }
            } catch (SQLiteException e2) {
                e = e2;
                cursorQuery = null;
            } catch (Throwable th) {
                th = th;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
            return zMoveToFirst;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursorQuery;
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzcfy zzcfyVar) throws IllegalStateException {
        zzwk();
        zzuj();
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzu(zzcfyVar);
        if (TextUtils.isEmpty(zzcfyVar.zzixn)) {
            zzaum().zzayg().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzcbw.zzjf(str), Integer.valueOf(i), String.valueOf(zzcfyVar.zzixm));
            return false;
        }
        try {
            byte[] bArr = new byte[zzcfyVar.zzhi()];
            zzegy zzegyVarZzi = zzegy.zzi(bArr, 0, bArr.length);
            zzcfyVar.zza(zzegyVarZzi);
            zzegyVarZzi.zzccm();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzcfyVar.zzixm);
            contentValues.put("event_name", zzcfyVar.zzixn);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("event_filters", null, contentValues, 5) == -1) {
                    zzaum().zzaye().zzj("Failed to insert event filter (got -1). appId", zzcbw.zzjf(str));
                }
                return true;
            } catch (SQLiteException e) {
                zzaum().zzaye().zze("Error storing event filter. appId", zzcbw.zzjf(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzaum().zzaye().zze("Configuration loss. Failed to serialize event filter. appId", zzcbw.zzjf(str), e2);
            return false;
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzcgb zzcgbVar) throws IllegalStateException {
        zzwk();
        zzuj();
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzu(zzcgbVar);
        if (TextUtils.isEmpty(zzcgbVar.zziyc)) {
            zzaum().zzayg().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzcbw.zzjf(str), Integer.valueOf(i), String.valueOf(zzcgbVar.zzixm));
            return false;
        }
        try {
            byte[] bArr = new byte[zzcgbVar.zzhi()];
            zzegy zzegyVarZzi = zzegy.zzi(bArr, 0, bArr.length);
            zzcgbVar.zza(zzegyVarZzi);
            zzegyVarZzi.zzccm();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzcgbVar.zzixm);
            contentValues.put("property_name", zzcgbVar.zziyc);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                    return true;
                }
                zzaum().zzaye().zzj("Failed to insert property filter (got -1). appId", zzcbw.zzjf(str));
                return false;
            } catch (SQLiteException e) {
                zzaum().zzaye().zze("Error storing property filter. appId", zzcbw.zzjf(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzaum().zzaye().zze("Configuration loss. Failed to serialize property filter. appId", zzcbw.zzjf(str), e2);
            return false;
        }
    }

    private final boolean zzaxr() {
        return getContext().getDatabasePath(zzcax.zzawj()).exists();
    }

    @WorkerThread
    private final long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                Cursor cursorRawQuery = getWritableDatabase().rawQuery(str, strArr);
                if (!cursorRawQuery.moveToFirst()) {
                    throw new SQLiteException("Database returned empty set");
                }
                long j = cursorRawQuery.getLong(0);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return j;
            } catch (SQLiteException e) {
                zzaum().zzaye().zze("Database error", str, e);
                throw e;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    private static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery(new StringBuilder(String.valueOf(str).length() + 22).append("SELECT * FROM ").append(str).append(" LIMIT 0").toString(), null);
        try {
            Collections.addAll(hashSet, cursorRawQuery.getColumnNames());
            return hashSet;
        } finally {
            cursorRawQuery.close();
        }
    }

    private final boolean zzc(String str, List<Integer> list) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        zzwk();
        zzuj();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            long jZzb = zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int iMax = Math.max(0, Math.min(2000, zzauo().zzb(str, zzcbm.zzipf)));
            if (jZzb <= iMax) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String strJoin = TextUtils.join(",", arrayList);
            String string = new StringBuilder(String.valueOf(strJoin).length() + 2).append("(").append(strJoin).append(")").toString();
            return writableDatabase.delete("audience_filter_values", new StringBuilder(String.valueOf(string).length() + 140).append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ").append(string).append(" order by rowid desc limit -1 offset ?)").toString(), new String[]{str, Integer.toString(iMax)}) > 0;
        } catch (SQLiteException e) {
            zzaum().zzaye().zze("Database error querying filters. appId", zzcbw.zzjf(str), e);
            return false;
        }
    }

    @WorkerThread
    public final void beginTransaction() {
        zzwk();
        getWritableDatabase().beginTransaction();
    }

    @WorkerThread
    public final void endTransaction() {
        zzwk();
        getWritableDatabase().endTransaction();
    }

    @WorkerThread
    final SQLiteDatabase getWritableDatabase() {
        zzuj();
        try {
            return this.zzimv.getWritableDatabase();
        } catch (SQLiteException e) {
            zzaum().zzayg().zzj("Error opening database", e);
            throw e;
        }
    }

    @WorkerThread
    public final void setTransactionSuccessful() {
        zzwk();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final long zza(zzcgk zzcgkVar) throws IOException {
        long jZzq;
        zzuj();
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(zzcgkVar);
        com.google.android.gms.common.internal.zzbp.zzgg(zzcgkVar.zzch);
        try {
            byte[] bArr = new byte[zzcgkVar.zzhi()];
            zzegy zzegyVarZzi = zzegy.zzi(bArr, 0, bArr.length);
            zzcgkVar.zza(zzegyVarZzi);
            zzegyVarZzi.zzccm();
            zzcfw zzcfwVarZzaui = zzaui();
            com.google.android.gms.common.internal.zzbp.zzu(bArr);
            zzcfwVarZzaui.zzuj();
            MessageDigest messageDigestZzec = zzcfw.zzec("MD5");
            if (messageDigestZzec == null) {
                zzcfwVarZzaui.zzaum().zzaye().log("Failed to get MD5");
                jZzq = 0;
            } else {
                jZzq = zzcfw.zzq(messageDigestZzec.digest(bArr));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzcgkVar.zzch);
            contentValues.put("metadata_fingerprint", Long.valueOf(jZzq));
            contentValues.put("metadata", bArr);
            try {
                getWritableDatabase().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
                return jZzq;
            } catch (SQLiteException e) {
                zzaum().zzaye().zze("Error storing raw event metadata. appId", zzcbw.zzjf(zzcgkVar.zzch), e);
                throw e;
            }
        } catch (IOException e2) {
            zzaum().zzaye().zze("Data loss. Failed to serialize event metadata. appId", zzcbw.zzjf(zzcgkVar.zzch), e2);
            throw e2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0135  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.zzcaz zza(long r12, java.lang.String r14, boolean r15, boolean r16, boolean r17, boolean r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zza(long, java.lang.String, boolean, boolean, boolean, boolean, boolean):com.google.android.gms.internal.zzcaz");
    }

    @WorkerThread
    public final void zza(zzcar zzcarVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzcarVar);
        zzuj();
        zzwk();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzcarVar.getAppId());
        contentValues.put("app_instance_id", zzcarVar.getAppInstanceId());
        contentValues.put("gmp_app_id", zzcarVar.getGmpAppId());
        contentValues.put("resettable_device_id_hash", zzcarVar.zzauq());
        contentValues.put("last_bundle_index", Long.valueOf(zzcarVar.zzauz()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzcarVar.zzaus()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzcarVar.zzaut()));
        contentValues.put("app_version", zzcarVar.zzuo());
        contentValues.put("app_store", zzcarVar.zzauv());
        contentValues.put("gmp_version", Long.valueOf(zzcarVar.zzauw()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzcarVar.zzaux()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzcarVar.zzauy()));
        contentValues.put("day", Long.valueOf(zzcarVar.zzavd()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzcarVar.zzave()));
        contentValues.put("daily_events_count", Long.valueOf(zzcarVar.zzavf()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzcarVar.zzavg()));
        contentValues.put("config_fetched_time", Long.valueOf(zzcarVar.zzava()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzcarVar.zzavb()));
        contentValues.put("app_version_int", Long.valueOf(zzcarVar.zzauu()));
        contentValues.put("firebase_instance_id", zzcarVar.zzaur());
        contentValues.put("daily_error_events_count", Long.valueOf(zzcarVar.zzavi()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzcarVar.zzavh()));
        contentValues.put("health_monitor_sample", zzcarVar.zzavj());
        contentValues.put("android_id", Long.valueOf(zzcarVar.zzavl()));
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase.update("apps", contentValues, "app_id = ?", new String[]{zzcarVar.getAppId()}) == 0 && writableDatabase.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzaum().zzaye().zzj("Failed to insert/update app (got -1). appId", zzcbw.zzjf(zzcarVar.getAppId()));
            }
        } catch (SQLiteException e) {
            zzaum().zzaye().zze("Error storing app. appId", zzcbw.zzjf(zzcarVar.getAppId()), e);
        }
    }

    @WorkerThread
    public final void zza(zzcbg zzcbgVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzcbgVar);
        zzuj();
        zzwk();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzcbgVar.mAppId);
        contentValues.put("name", zzcbgVar.mName);
        contentValues.put("lifetime_count", Long.valueOf(zzcbgVar.zzink));
        contentValues.put("current_bundle_count", Long.valueOf(zzcbgVar.zzinl));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzcbgVar.zzinm));
        try {
            if (getWritableDatabase().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzaum().zzaye().zzj("Failed to insert/update event aggregates (got -1). appId", zzcbw.zzjf(zzcbgVar.mAppId));
            }
        } catch (SQLiteException e) {
            zzaum().zzaye().zze("Error storing event aggregates. appId", zzcbw.zzjf(zzcbgVar.mAppId), e);
        }
    }

    @WorkerThread
    final void zza(String str, zzcfx[] zzcfxVarArr) {
        boolean z;
        zzwk();
        zzuj();
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzu(zzcfxVarArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            zzwk();
            zzuj();
            com.google.android.gms.common.internal.zzbp.zzgg(str);
            SQLiteDatabase writableDatabase2 = getWritableDatabase();
            writableDatabase2.delete("property_filters", "app_id=?", new String[]{str});
            writableDatabase2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzcfx zzcfxVar : zzcfxVarArr) {
                zzwk();
                zzuj();
                com.google.android.gms.common.internal.zzbp.zzgg(str);
                com.google.android.gms.common.internal.zzbp.zzu(zzcfxVar);
                com.google.android.gms.common.internal.zzbp.zzu(zzcfxVar.zzixk);
                com.google.android.gms.common.internal.zzbp.zzu(zzcfxVar.zzixj);
                if (zzcfxVar.zzixi == null) {
                    zzaum().zzayg().zzj("Audience with no ID. appId", zzcbw.zzjf(str));
                } else {
                    int iIntValue = zzcfxVar.zzixi.intValue();
                    zzcfy[] zzcfyVarArr = zzcfxVar.zzixk;
                    int length = zzcfyVarArr.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            zzcgb[] zzcgbVarArr = zzcfxVar.zzixj;
                            int length2 = zzcgbVarArr.length;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= length2) {
                                    zzcfy[] zzcfyVarArr2 = zzcfxVar.zzixk;
                                    int length3 = zzcfyVarArr2.length;
                                    int i3 = 0;
                                    while (true) {
                                        if (i3 >= length3) {
                                            z = true;
                                            break;
                                        } else {
                                            if (!zza(str, iIntValue, zzcfyVarArr2[i3])) {
                                                z = false;
                                                break;
                                            }
                                            i3++;
                                        }
                                    }
                                    if (z) {
                                        zzcgb[] zzcgbVarArr2 = zzcfxVar.zzixj;
                                        int length4 = zzcgbVarArr2.length;
                                        int i4 = 0;
                                        while (true) {
                                            if (i4 >= length4) {
                                                break;
                                            }
                                            if (!zza(str, iIntValue, zzcgbVarArr2[i4])) {
                                                z = false;
                                                break;
                                            }
                                            i4++;
                                        }
                                    }
                                    if (!z) {
                                        zzwk();
                                        zzuj();
                                        com.google.android.gms.common.internal.zzbp.zzgg(str);
                                        SQLiteDatabase writableDatabase3 = getWritableDatabase();
                                        writableDatabase3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(iIntValue)});
                                        writableDatabase3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(iIntValue)});
                                    }
                                } else {
                                    if (zzcgbVarArr[i2].zzixm == null) {
                                        zzaum().zzayg().zze("Property filter with no ID. Audience definition ignored. appId, audienceId", zzcbw.zzjf(str), zzcfxVar.zzixi);
                                        break;
                                    }
                                    i2++;
                                }
                            }
                        } else {
                            if (zzcfyVarArr[i].zzixm == null) {
                                zzaum().zzayg().zze("Event filter with no ID. Audience definition ignored. appId, audienceId", zzcbw.zzjf(str), zzcfxVar.zzixi);
                                break;
                            }
                            i++;
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzcfx zzcfxVar2 : zzcfxVarArr) {
                arrayList.add(zzcfxVar2.zzixi);
            }
            zzc(str, arrayList);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @WorkerThread
    public final boolean zza(zzcav zzcavVar) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzu(zzcavVar);
        zzuj();
        zzwk();
        if (zzah(zzcavVar.packageName, zzcavVar.zzimg.name) == null) {
            long jZzb = zzb("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzcavVar.packageName});
            zzcax.zzawc();
            if (jZzb >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzcavVar.packageName);
        contentValues.put(FirebaseAnalytics.Param.ORIGIN, zzcavVar.zzimf);
        contentValues.put("name", zzcavVar.zzimg.name);
        zza(contentValues, FirebaseAnalytics.Param.VALUE, zzcavVar.zzimg.getValue());
        contentValues.put("active", Boolean.valueOf(zzcavVar.zzimi));
        contentValues.put("trigger_event_name", zzcavVar.zzimj);
        contentValues.put("trigger_timeout", Long.valueOf(zzcavVar.zziml));
        zzaui();
        contentValues.put("timed_out_event", zzcfw.zza(zzcavVar.zzimk));
        contentValues.put("creation_timestamp", Long.valueOf(zzcavVar.zzimh));
        zzaui();
        contentValues.put("triggered_event", zzcfw.zza(zzcavVar.zzimm));
        contentValues.put("triggered_timestamp", Long.valueOf(zzcavVar.zzimg.zziwy));
        contentValues.put("time_to_live", Long.valueOf(zzcavVar.zzimn));
        zzaui();
        contentValues.put("expired_event", zzcfw.zza(zzcavVar.zzimo));
        try {
            if (getWritableDatabase().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzaum().zzaye().zzj("Failed to insert/update conditional user property (got -1)", zzcbw.zzjf(zzcavVar.packageName));
            }
        } catch (SQLiteException e) {
            zzaum().zzaye().zze("Error storing conditional user property", zzcbw.zzjf(zzcavVar.packageName), e);
        }
        return true;
    }

    public final boolean zza(zzcbf zzcbfVar, long j, boolean z) throws IllegalStateException {
        zzuj();
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(zzcbfVar);
        com.google.android.gms.common.internal.zzbp.zzgg(zzcbfVar.mAppId);
        zzcgh zzcghVar = new zzcgh();
        zzcghVar.zziyy = Long.valueOf(zzcbfVar.zzini);
        zzcghVar.zziyw = new zzcgi[zzcbfVar.zzinj.size()];
        Iterator<String> it = zzcbfVar.zzinj.iterator();
        int i = 0;
        while (it.hasNext()) {
            String next = it.next();
            zzcgi zzcgiVar = new zzcgi();
            zzcghVar.zziyw[i] = zzcgiVar;
            zzcgiVar.name = next;
            zzaui().zza(zzcgiVar, zzcbfVar.zzinj.get(next));
            i++;
        }
        try {
            byte[] bArr = new byte[zzcghVar.zzhi()];
            zzegy zzegyVarZzi = zzegy.zzi(bArr, 0, bArr.length);
            zzcghVar.zza(zzegyVarZzi);
            zzegyVarZzi.zzccm();
            zzaum().zzayk().zze("Saving event, name, data size", zzauh().zzjc(zzcbfVar.mName), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzcbfVar.mAppId);
            contentValues.put("name", zzcbfVar.mName);
            contentValues.put(AppMeasurement.Param.TIMESTAMP, Long.valueOf(zzcbfVar.zzfdb));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("data", bArr);
            contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("raw_events", null, contentValues) != -1) {
                    return true;
                }
                zzaum().zzaye().zzj("Failed to insert raw event (got -1). appId", zzcbw.zzjf(zzcbfVar.mAppId));
                return false;
            } catch (SQLiteException e) {
                zzaum().zzaye().zze("Error storing raw event. appId", zzcbw.zzjf(zzcbfVar.mAppId), e);
                return false;
            }
        } catch (IOException e2) {
            zzaum().zzaye().zze("Data loss. Failed to serialize event params/data. appId", zzcbw.zzjf(zzcbfVar.mAppId), e2);
            return false;
        }
    }

    @WorkerThread
    public final boolean zza(zzcfv zzcfvVar) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzu(zzcfvVar);
        zzuj();
        zzwk();
        if (zzah(zzcfvVar.mAppId, zzcfvVar.mName) == null) {
            if (zzcfw.zzju(zzcfvVar.mName)) {
                long jZzb = zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzcfvVar.mAppId});
                zzcax.zzavz();
                if (jZzb >= 25) {
                    return false;
                }
            } else {
                long jZzb2 = zzb("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzcfvVar.mAppId, zzcfvVar.mOrigin});
                zzcax.zzawb();
                if (jZzb2 >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzcfvVar.mAppId);
        contentValues.put(FirebaseAnalytics.Param.ORIGIN, zzcfvVar.mOrigin);
        contentValues.put("name", zzcfvVar.mName);
        contentValues.put("set_timestamp", Long.valueOf(zzcfvVar.zzixc));
        zza(contentValues, FirebaseAnalytics.Param.VALUE, zzcfvVar.mValue);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzaum().zzaye().zzj("Failed to insert/update user property (got -1). appId", zzcbw.zzjf(zzcfvVar.mAppId));
            }
        } catch (SQLiteException e) {
            zzaum().zzaye().zze("Error storing user property. appId", zzcbw.zzjf(zzcfvVar.mAppId), e);
        }
        return true;
    }

    @WorkerThread
    public final boolean zza(zzcgk zzcgkVar, boolean z) throws IllegalStateException {
        zzuj();
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(zzcgkVar);
        com.google.android.gms.common.internal.zzbp.zzgg(zzcgkVar.zzch);
        com.google.android.gms.common.internal.zzbp.zzu(zzcgkVar.zzizi);
        zzaxl();
        long jCurrentTimeMillis = zzvx().currentTimeMillis();
        if (zzcgkVar.zzizi.longValue() < jCurrentTimeMillis - zzcax.zzawn() || zzcgkVar.zzizi.longValue() > zzcax.zzawn() + jCurrentTimeMillis) {
            zzaum().zzayg().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzcbw.zzjf(zzcgkVar.zzch), Long.valueOf(jCurrentTimeMillis), zzcgkVar.zzizi);
        }
        try {
            byte[] bArr = new byte[zzcgkVar.zzhi()];
            zzegy zzegyVarZzi = zzegy.zzi(bArr, 0, bArr.length);
            zzcgkVar.zza(zzegyVarZzi);
            zzegyVarZzi.zzccm();
            byte[] bArrZzo = zzaui().zzo(bArr);
            zzaum().zzayk().zzj("Saving bundle, size", Integer.valueOf(bArrZzo.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzcgkVar.zzch);
            contentValues.put("bundle_end_timestamp", zzcgkVar.zzizi);
            contentValues.put("data", bArrZzo);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzaum().zzaye().zzj("Failed to insert bundle (got -1). appId", zzcbw.zzjf(zzcgkVar.zzch));
                return false;
            } catch (SQLiteException e) {
                zzaum().zzaye().zze("Error storing bundle. appId", zzcbw.zzjf(zzcgkVar.zzch), e);
                return false;
            }
        } catch (IOException e2) {
            zzaum().zzaye().zze("Data loss. Failed to serialize bundle. appId", zzcbw.zzjf(zzcgkVar.zzch), e2);
            return false;
        }
    }

    public final void zzae(List<Long> list) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzu(list);
        zzuj();
        zzwk();
        StringBuilder sb = new StringBuilder("rowid in (");
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                break;
            }
            if (i2 != 0) {
                sb.append(",");
            }
            sb.append(list.get(i2).longValue());
            i = i2 + 1;
        }
        sb.append(")");
        int iDelete = getWritableDatabase().delete("raw_events", sb.toString(), null);
        if (iDelete != list.size()) {
            zzaum().zzaye().zze("Deleted fewer rows from raw events table than expected", Integer.valueOf(iDelete), Integer.valueOf(list.size()));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x009c  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.zzcbg zzaf(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r10 = 0
            com.google.android.gms.common.internal.zzbp.zzgg(r13)
            com.google.android.gms.common.internal.zzbp.zzgg(r14)
            r12.zzuj()
            r12.zzwk()
            android.database.sqlite.SQLiteDatabase r0 = r12.getWritableDatabase()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            java.lang.String r1 = "events"
            r2 = 3
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            r3 = 0
            java.lang.String r4 = "lifetime_count"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            r3 = 1
            java.lang.String r4 = "current_bundle_count"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            r3 = 2
            java.lang.String r4 = "last_fire_timestamp"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            java.lang.String r3 = "app_id=? and name=?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            r5 = 0
            r4[r5] = r13     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            r5 = 1
            r4[r5] = r14     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r11 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            boolean r0 = r11.moveToFirst()     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            if (r0 != 0) goto L44
            if (r11 == 0) goto L42
            r11.close()
        L42:
            r1 = r10
        L43:
            return r1
        L44:
            r0 = 0
            long r4 = r11.getLong(r0)     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            r0 = 1
            long r6 = r11.getLong(r0)     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            r0 = 2
            long r8 = r11.getLong(r0)     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            com.google.android.gms.internal.zzcbg r1 = new com.google.android.gms.internal.zzcbg     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            r2 = r13
            r3 = r14
            r1.<init>(r2, r3, r4, r6, r8)     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            boolean r0 = r11.moveToNext()     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            if (r0 == 0) goto L71
            com.google.android.gms.internal.zzcbw r0 = r12.zzaum()     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            com.google.android.gms.internal.zzcby r0 = r0.zzaye()     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            java.lang.String r2 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.internal.zzcbw.zzjf(r13)     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            r0.zzj(r2, r3)     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
        L71:
            if (r11 == 0) goto L43
            r11.close()
            goto L43
        L77:
            r0 = move-exception
            r1 = r10
        L79:
            com.google.android.gms.internal.zzcbw r2 = r12.zzaum()     // Catch: java.lang.Throwable -> La3
            com.google.android.gms.internal.zzcby r2 = r2.zzaye()     // Catch: java.lang.Throwable -> La3
            java.lang.String r3 = "Error querying events. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzcbw.zzjf(r13)     // Catch: java.lang.Throwable -> La3
            com.google.android.gms.internal.zzcbu r5 = r12.zzauh()     // Catch: java.lang.Throwable -> La3
            java.lang.String r5 = r5.zzjc(r14)     // Catch: java.lang.Throwable -> La3
            r2.zzd(r3, r4, r5, r0)     // Catch: java.lang.Throwable -> La3
            if (r1 == 0) goto L97
            r1.close()
        L97:
            r1 = r10
            goto L43
        L99:
            r0 = move-exception
        L9a:
            if (r10 == 0) goto L9f
            r10.close()
        L9f:
            throw r0
        La0:
            r0 = move-exception
            r10 = r11
            goto L9a
        La3:
            r0 = move-exception
            r10 = r1
            goto L9a
        La6:
            r0 = move-exception
            r1 = r11
            goto L79
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zzaf(java.lang.String, java.lang.String):com.google.android.gms.internal.zzcbg");
    }

    @WorkerThread
    public final void zzag(String str, String str2) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzgg(str2);
        zzuj();
        zzwk();
        try {
            zzaum().zzayk().zzj("Deleted user attribute rows", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzaum().zzaye().zzd("Error deleting user attribute. appId", zzcbw.zzjf(str), zzauh().zzje(str2), e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x009c  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.zzcfv zzah(java.lang.String r10, java.lang.String r11) {
        /*
            r9 = this;
            r8 = 0
            com.google.android.gms.common.internal.zzbp.zzgg(r10)
            com.google.android.gms.common.internal.zzbp.zzgg(r11)
            r9.zzuj()
            r9.zzwk()
            android.database.sqlite.SQLiteDatabase r0 = r9.getWritableDatabase()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            java.lang.String r1 = "user_attributes"
            r2 = 3
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            r3 = 0
            java.lang.String r4 = "set_timestamp"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            r3 = 1
            java.lang.String r4 = "value"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            r3 = 2
            java.lang.String r4 = "origin"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            java.lang.String r3 = "app_id=? and name=?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            r5 = 0
            r4[r5] = r10     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            r5 = 1
            r4[r5] = r11     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L99
            boolean r0 = r7.moveToFirst()     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            if (r0 != 0) goto L44
            if (r7 == 0) goto L42
            r7.close()
        L42:
            r0 = r8
        L43:
            return r0
        L44:
            r0 = 0
            long r4 = r7.getLong(r0)     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            r0 = 1
            java.lang.Object r6 = r9.zza(r7, r0)     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            r0 = 2
            java.lang.String r2 = r7.getString(r0)     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            com.google.android.gms.internal.zzcfv r0 = new com.google.android.gms.internal.zzcfv     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            r1 = r10
            r3 = r11
            r0.<init>(r1, r2, r3, r4, r6)     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            boolean r1 = r7.moveToNext()     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            if (r1 == 0) goto L71
            com.google.android.gms.internal.zzcbw r1 = r9.zzaum()     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            com.google.android.gms.internal.zzcby r1 = r1.zzaye()     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.internal.zzcbw.zzjf(r10)     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
            r1.zzj(r2, r3)     // Catch: java.lang.Throwable -> La0 android.database.sqlite.SQLiteException -> La6
        L71:
            if (r7 == 0) goto L43
            r7.close()
            goto L43
        L77:
            r0 = move-exception
            r1 = r8
        L79:
            com.google.android.gms.internal.zzcbw r2 = r9.zzaum()     // Catch: java.lang.Throwable -> La3
            com.google.android.gms.internal.zzcby r2 = r2.zzaye()     // Catch: java.lang.Throwable -> La3
            java.lang.String r3 = "Error querying user property. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzcbw.zzjf(r10)     // Catch: java.lang.Throwable -> La3
            com.google.android.gms.internal.zzcbu r5 = r9.zzauh()     // Catch: java.lang.Throwable -> La3
            java.lang.String r5 = r5.zzje(r11)     // Catch: java.lang.Throwable -> La3
            r2.zzd(r3, r4, r5, r0)     // Catch: java.lang.Throwable -> La3
            if (r1 == 0) goto L97
            r1.close()
        L97:
            r0 = r8
            goto L43
        L99:
            r0 = move-exception
        L9a:
            if (r8 == 0) goto L9f
            r8.close()
        L9f:
            throw r0
        La0:
            r0 = move-exception
            r8 = r7
            goto L9a
        La3:
            r0 = move-exception
            r8 = r1
            goto L9a
        La6:
            r0 = move-exception
            r1 = r7
            goto L79
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zzah(java.lang.String, java.lang.String):com.google.android.gms.internal.zzcfv");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x014d  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.zzcav zzai(java.lang.String r22, java.lang.String r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 347
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zzai(java.lang.String, java.lang.String):com.google.android.gms.internal.zzcav");
    }

    @WorkerThread
    public final int zzaj(String str, String str2) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzgg(str2);
        zzuj();
        zzwk();
        try {
            return getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzaum().zzaye().zzd("Error deleting conditional property", zzcbw.zzjf(str), zzauh().zzje(str2), e);
            return 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.zzcfy>> zzak(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            r9 = 0
            r10.zzwk()
            r10.zzuj()
            com.google.android.gms.common.internal.zzbp.zzgg(r11)
            com.google.android.gms.common.internal.zzbp.zzgg(r12)
            android.support.v4.util.ArrayMap r8 = new android.support.v4.util.ArrayMap
            r8.<init>()
            android.database.sqlite.SQLiteDatabase r0 = r10.getWritableDatabase()
            java.lang.String r1 = "event_filters"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> Lb3 android.database.sqlite.SQLiteException -> Lbd
            r3 = 0
            java.lang.String r4 = "audience_id"
            r2[r3] = r4     // Catch: java.lang.Throwable -> Lb3 android.database.sqlite.SQLiteException -> Lbd
            r3 = 1
            java.lang.String r4 = "data"
            r2[r3] = r4     // Catch: java.lang.Throwable -> Lb3 android.database.sqlite.SQLiteException -> Lbd
            java.lang.String r3 = "app_id=? AND event_name=?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> Lb3 android.database.sqlite.SQLiteException -> Lbd
            r5 = 0
            r4[r5] = r11     // Catch: java.lang.Throwable -> Lb3 android.database.sqlite.SQLiteException -> Lbd
            r5 = 1
            r4[r5] = r12     // Catch: java.lang.Throwable -> Lb3 android.database.sqlite.SQLiteException -> Lbd
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> Lb3 android.database.sqlite.SQLiteException -> Lbd
            boolean r0 = r1.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            if (r0 != 0) goto L47
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            if (r1 == 0) goto L46
            r1.close()
        L46:
            return r0
        L47:
            r0 = 1
            byte[] r0 = r1.getBlob(r0)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            r2 = 0
            int r3 = r0.length     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            com.google.android.gms.internal.zzegx r0 = com.google.android.gms.internal.zzegx.zzh(r0, r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            com.google.android.gms.internal.zzcfy r2 = new com.google.android.gms.internal.zzcfy     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            r2.<init>()     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            r2.zza(r0)     // Catch: java.io.IOException -> L87 android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            r0 = 0
            int r3 = r1.getInt(r0)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            java.lang.Object r0 = r8.get(r0)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            java.util.List r0 = (java.util.List) r0     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            if (r0 != 0) goto L77
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            r0.<init>()     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            r8.put(r3, r0)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
        L77:
            r0.add(r2)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
        L7a:
            boolean r0 = r1.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            if (r0 != 0) goto L47
            if (r1 == 0) goto L85
            r1.close()
        L85:
            r0 = r8
            goto L46
        L87:
            r0 = move-exception
            com.google.android.gms.internal.zzcbw r2 = r10.zzaum()     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            com.google.android.gms.internal.zzcby r2 = r2.zzaye()     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzcbw.zzjf(r11)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            r2.zze(r3, r4, r0)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            goto L7a
        L9a:
            r0 = move-exception
        L9b:
            com.google.android.gms.internal.zzcbw r2 = r10.zzaum()     // Catch: java.lang.Throwable -> Lbb
            com.google.android.gms.internal.zzcby r2 = r2.zzaye()     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r3 = "Database error querying filters. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzcbw.zzjf(r11)     // Catch: java.lang.Throwable -> Lbb
            r2.zze(r3, r4, r0)     // Catch: java.lang.Throwable -> Lbb
            if (r1 == 0) goto Lb1
            r1.close()
        Lb1:
            r0 = r9
            goto L46
        Lb3:
            r0 = move-exception
            r1 = r9
        Lb5:
            if (r1 == 0) goto Lba
            r1.close()
        Lba:
            throw r0
        Lbb:
            r0 = move-exception
            goto Lb5
        Lbd:
            r0 = move-exception
            r1 = r9
            goto L9b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zzak(java.lang.String, java.lang.String):java.util.Map");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.zzcgb>> zzal(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            r9 = 0
            r10.zzwk()
            r10.zzuj()
            com.google.android.gms.common.internal.zzbp.zzgg(r11)
            com.google.android.gms.common.internal.zzbp.zzgg(r12)
            android.support.v4.util.ArrayMap r8 = new android.support.v4.util.ArrayMap
            r8.<init>()
            android.database.sqlite.SQLiteDatabase r0 = r10.getWritableDatabase()
            java.lang.String r1 = "property_filters"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> Lb3 android.database.sqlite.SQLiteException -> Lbd
            r3 = 0
            java.lang.String r4 = "audience_id"
            r2[r3] = r4     // Catch: java.lang.Throwable -> Lb3 android.database.sqlite.SQLiteException -> Lbd
            r3 = 1
            java.lang.String r4 = "data"
            r2[r3] = r4     // Catch: java.lang.Throwable -> Lb3 android.database.sqlite.SQLiteException -> Lbd
            java.lang.String r3 = "app_id=? AND property_name=?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> Lb3 android.database.sqlite.SQLiteException -> Lbd
            r5 = 0
            r4[r5] = r11     // Catch: java.lang.Throwable -> Lb3 android.database.sqlite.SQLiteException -> Lbd
            r5 = 1
            r4[r5] = r12     // Catch: java.lang.Throwable -> Lb3 android.database.sqlite.SQLiteException -> Lbd
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> Lb3 android.database.sqlite.SQLiteException -> Lbd
            boolean r0 = r1.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            if (r0 != 0) goto L47
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            if (r1 == 0) goto L46
            r1.close()
        L46:
            return r0
        L47:
            r0 = 1
            byte[] r0 = r1.getBlob(r0)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            r2 = 0
            int r3 = r0.length     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            com.google.android.gms.internal.zzegx r0 = com.google.android.gms.internal.zzegx.zzh(r0, r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            com.google.android.gms.internal.zzcgb r2 = new com.google.android.gms.internal.zzcgb     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            r2.<init>()     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            r2.zza(r0)     // Catch: java.io.IOException -> L87 android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            r0 = 0
            int r3 = r1.getInt(r0)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            java.lang.Object r0 = r8.get(r0)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            java.util.List r0 = (java.util.List) r0     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            if (r0 != 0) goto L77
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            r0.<init>()     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            r8.put(r3, r0)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
        L77:
            r0.add(r2)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
        L7a:
            boolean r0 = r1.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            if (r0 != 0) goto L47
            if (r1 == 0) goto L85
            r1.close()
        L85:
            r0 = r8
            goto L46
        L87:
            r0 = move-exception
            com.google.android.gms.internal.zzcbw r2 = r10.zzaum()     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            com.google.android.gms.internal.zzcby r2 = r2.zzaye()     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.internal.zzcbw.zzjf(r11)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            r2.zze(r3, r4, r0)     // Catch: android.database.sqlite.SQLiteException -> L9a java.lang.Throwable -> Lbb
            goto L7a
        L9a:
            r0 = move-exception
        L9b:
            com.google.android.gms.internal.zzcbw r2 = r10.zzaum()     // Catch: java.lang.Throwable -> Lbb
            com.google.android.gms.internal.zzcby r2 = r2.zzaye()     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r3 = "Database error querying filters. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzcbw.zzjf(r11)     // Catch: java.lang.Throwable -> Lbb
            r2.zze(r3, r4, r0)     // Catch: java.lang.Throwable -> Lbb
            if (r1 == 0) goto Lb1
            r1.close()
        Lb1:
            r0 = r9
            goto L46
        Lb3:
            r0 = move-exception
            r1 = r9
        Lb5:
            if (r1 == 0) goto Lba
            r1.close()
        Lba:
            throw r0
        Lbb:
            r0 = move-exception
            goto Lb5
        Lbd:
            r0 = move-exception
            r1 = r9
            goto L9b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zzal(java.lang.String, java.lang.String):java.util.Map");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00b2 A[Catch: all -> 0x00e6, SQLiteException -> 0x00eb, TRY_LEAVE, TryCatch #0 {all -> 0x00e6, blocks: (B:3:0x0017, B:5:0x004a, B:7:0x0074, B:11:0x008b, B:13:0x00b2, B:15:0x00c8, B:19:0x00d1), top: B:27:0x0017 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00c8 A[Catch: all -> 0x00e6, SQLiteException -> 0x00eb, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x00e6, blocks: (B:3:0x0017, B:5:0x004a, B:7:0x0074, B:11:0x008b, B:13:0x00b2, B:15:0x00c8, B:19:0x00d1), top: B:27:0x0017 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00e2 -> B:26:0x0089). Please report as a decompilation issue!!! */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final long zzam(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r4 = 0
            r2 = -1
            com.google.android.gms.common.internal.zzbp.zzgg(r13)
            com.google.android.gms.common.internal.zzbp.zzgg(r14)
            r12.zzuj()
            r12.zzwk()
            android.database.sqlite.SQLiteDatabase r6 = r12.getWritableDatabase()
            r6.beginTransaction()
            java.lang.String r0 = java.lang.String.valueOf(r14)     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            int r0 = r0.length()     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            int r0 = r0 + 32
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            r1.<init>(r0)     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            java.lang.String r0 = "select "
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            java.lang.StringBuilder r0 = r0.append(r14)     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            java.lang.String r1 = " from app2 where app_id=?"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            java.lang.String r0 = r0.toString()     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            r1 = 1
            java.lang.String[] r1 = new java.lang.String[r1]     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            r7 = 0
            r1[r7] = r13     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            r8 = -1
            long r0 = r12.zza(r0, r1, r8)     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 != 0) goto L8b
            android.content.ContentValues r0 = new android.content.ContentValues     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            r0.<init>()     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            java.lang.String r1 = "app_id"
            r0.put(r1, r13)     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            java.lang.String r1 = "first_open_count"
            r7 = 0
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            r0.put(r1, r7)     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            java.lang.String r1 = "previous_install_count"
            r7 = 0
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            r0.put(r1, r7)     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            java.lang.String r1 = "app2"
            r7 = 0
            r8 = 5
            long r0 = r6.insertWithOnConflict(r1, r7, r0, r8)     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L8a
            com.google.android.gms.internal.zzcbw r0 = r12.zzaum()     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            com.google.android.gms.internal.zzcby r0 = r0.zzaye()     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            java.lang.String r1 = "Failed to insert column (got -1). appId"
            java.lang.Object r7 = com.google.android.gms.internal.zzcbw.zzjf(r13)     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            r0.zze(r1, r7, r14)     // Catch: android.database.sqlite.SQLiteException -> Lcf java.lang.Throwable -> Le6
            r6.endTransaction()
            r0 = r2
        L89:
            return r0
        L8a:
            r0 = r4
        L8b:
            android.content.ContentValues r7 = new android.content.ContentValues     // Catch: java.lang.Throwable -> Le6 android.database.sqlite.SQLiteException -> Leb
            r7.<init>()     // Catch: java.lang.Throwable -> Le6 android.database.sqlite.SQLiteException -> Leb
            java.lang.String r8 = "app_id"
            r7.put(r8, r13)     // Catch: java.lang.Throwable -> Le6 android.database.sqlite.SQLiteException -> Leb
            r8 = 1
            long r8 = r8 + r0
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch: java.lang.Throwable -> Le6 android.database.sqlite.SQLiteException -> Leb
            r7.put(r14, r8)     // Catch: java.lang.Throwable -> Le6 android.database.sqlite.SQLiteException -> Leb
            java.lang.String r8 = "app2"
            java.lang.String r9 = "app_id = ?"
            r10 = 1
            java.lang.String[] r10 = new java.lang.String[r10]     // Catch: java.lang.Throwable -> Le6 android.database.sqlite.SQLiteException -> Leb
            r11 = 0
            r10[r11] = r13     // Catch: java.lang.Throwable -> Le6 android.database.sqlite.SQLiteException -> Leb
            int r7 = r6.update(r8, r7, r9, r10)     // Catch: java.lang.Throwable -> Le6 android.database.sqlite.SQLiteException -> Leb
            long r8 = (long) r7     // Catch: java.lang.Throwable -> Le6 android.database.sqlite.SQLiteException -> Leb
            int r4 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r4 != 0) goto Lc8
            com.google.android.gms.internal.zzcbw r4 = r12.zzaum()     // Catch: java.lang.Throwable -> Le6 android.database.sqlite.SQLiteException -> Leb
            com.google.android.gms.internal.zzcby r4 = r4.zzaye()     // Catch: java.lang.Throwable -> Le6 android.database.sqlite.SQLiteException -> Leb
            java.lang.String r5 = "Failed to update column (got 0). appId"
            java.lang.Object r7 = com.google.android.gms.internal.zzcbw.zzjf(r13)     // Catch: java.lang.Throwable -> Le6 android.database.sqlite.SQLiteException -> Leb
            r4.zze(r5, r7, r14)     // Catch: java.lang.Throwable -> Le6 android.database.sqlite.SQLiteException -> Leb
            r6.endTransaction()
            r0 = r2
            goto L89
        Lc8:
            r6.setTransactionSuccessful()     // Catch: java.lang.Throwable -> Le6 android.database.sqlite.SQLiteException -> Leb
            r6.endTransaction()
            goto L89
        Lcf:
            r2 = move-exception
            r0 = r4
        Ld1:
            com.google.android.gms.internal.zzcbw r3 = r12.zzaum()     // Catch: java.lang.Throwable -> Le6
            com.google.android.gms.internal.zzcby r3 = r3.zzaye()     // Catch: java.lang.Throwable -> Le6
            java.lang.String r4 = "Error inserting column. appId"
            java.lang.Object r5 = com.google.android.gms.internal.zzcbw.zzjf(r13)     // Catch: java.lang.Throwable -> Le6
            r3.zzd(r4, r5, r14, r2)     // Catch: java.lang.Throwable -> Le6
            r6.endTransaction()
            goto L89
        Le6:
            r0 = move-exception
            r6.endTransaction()
            throw r0
        Leb:
            r2 = move-exception
            goto Ld1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zzam(java.lang.String, java.lang.String):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003c  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String zzaxj() throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.getWritableDatabase()
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            r3 = 0
            android.database.Cursor r2 = r1.rawQuery(r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L23 java.lang.Throwable -> L38
            boolean r1 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L43
            if (r1 == 0) goto L1d
            r1 = 0
            java.lang.String r0 = r2.getString(r1)     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L43
            if (r2 == 0) goto L1c
            r2.close()
        L1c:
            return r0
        L1d:
            if (r2 == 0) goto L1c
            r2.close()
            goto L1c
        L23:
            r1 = move-exception
            r2 = r0
        L25:
            com.google.android.gms.internal.zzcbw r3 = r5.zzaum()     // Catch: java.lang.Throwable -> L40
            com.google.android.gms.internal.zzcby r3 = r3.zzaye()     // Catch: java.lang.Throwable -> L40
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zzj(r4, r1)     // Catch: java.lang.Throwable -> L40
            if (r2 == 0) goto L1c
            r2.close()
            goto L1c
        L38:
            r1 = move-exception
            r2 = r0
        L3a:
            if (r2 == 0) goto L3f
            r2.close()
        L3f:
            throw r1
        L40:
            r0 = move-exception
            r1 = r0
            goto L3a
        L43:
            r1 = move-exception
            goto L25
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zzaxj():java.lang.String");
    }

    public final boolean zzaxk() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    @WorkerThread
    final void zzaxl() {
        int iDelete;
        zzuj();
        zzwk();
        if (zzaxr()) {
            long j = zzaun().zziqr.get();
            long jElapsedRealtime = zzvx().elapsedRealtime();
            if (Math.abs(jElapsedRealtime - j) > zzcax.zzawo()) {
                zzaun().zziqr.set(jElapsedRealtime);
                zzuj();
                zzwk();
                if (!zzaxr() || (iDelete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzvx().currentTimeMillis()), String.valueOf(zzcax.zzawn())})) <= 0) {
                    return;
                }
                zzaum().zzayk().zzj("Deleted stale rows. rowsDeleted", Integer.valueOf(iDelete));
            }
        }
    }

    @WorkerThread
    public final long zzaxm() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0L);
    }

    @WorkerThread
    public final long zzaxn() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0L);
    }

    public final boolean zzaxo() {
        return zzb("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzaxp() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final long zzaxq() {
        long j = -1;
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
                if (cursorRawQuery.moveToFirst()) {
                    j = cursorRawQuery.getLong(0);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                }
            } catch (SQLiteException e) {
                zzaum().zzaye().zzj("Error querying raw events", e);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            }
            return j;
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String zzba(long r8) throws java.lang.Throwable {
        /*
            r7 = this;
            r0 = 0
            r7.zzuj()
            r7.zzwk()
            android.database.sqlite.SQLiteDatabase r1 = r7.getWritableDatabase()     // Catch: android.database.sqlite.SQLiteException -> L3f java.lang.Throwable -> L54
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: android.database.sqlite.SQLiteException -> L3f java.lang.Throwable -> L54
            r4 = 0
            java.lang.String r5 = java.lang.String.valueOf(r8)     // Catch: android.database.sqlite.SQLiteException -> L3f java.lang.Throwable -> L54
            r3[r4] = r5     // Catch: android.database.sqlite.SQLiteException -> L3f java.lang.Throwable -> L54
            android.database.Cursor r2 = r1.rawQuery(r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L3f java.lang.Throwable -> L54
            boolean r1 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5f
            if (r1 != 0) goto L34
            com.google.android.gms.internal.zzcbw r1 = r7.zzaum()     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5f
            com.google.android.gms.internal.zzcby r1 = r1.zzayk()     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5f
            java.lang.String r3 = "No expired configs for apps with pending events"
            r1.log(r3)     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5f
            if (r2 == 0) goto L33
            r2.close()
        L33:
            return r0
        L34:
            r1 = 0
            java.lang.String r0 = r2.getString(r1)     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5f
            if (r2 == 0) goto L33
            r2.close()
            goto L33
        L3f:
            r1 = move-exception
            r2 = r0
        L41:
            com.google.android.gms.internal.zzcbw r3 = r7.zzaum()     // Catch: java.lang.Throwable -> L5c
            com.google.android.gms.internal.zzcby r3 = r3.zzaye()     // Catch: java.lang.Throwable -> L5c
            java.lang.String r4 = "Error selecting expired configs"
            r3.zzj(r4, r1)     // Catch: java.lang.Throwable -> L5c
            if (r2 == 0) goto L33
            r2.close()
            goto L33
        L54:
            r1 = move-exception
            r2 = r0
        L56:
            if (r2 == 0) goto L5b
            r2.close()
        L5b:
            throw r1
        L5c:
            r0 = move-exception
            r1 = r0
            goto L56
        L5f:
            r1 = move-exception
            goto L41
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zzba(long):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0085, code lost:
    
        zzaum().zzaye().zzj("Read more than the max allowed conditional properties, ignoring extra", java.lang.Integer.valueOf(com.google.android.gms.internal.zzcax.zzawc()));
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x016c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.google.android.gms.internal.zzcav> zzc(java.lang.String r24, java.lang.String[] r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 378
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zzc(java.lang.String, java.lang.String[]):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0097, code lost:
    
        zzaum().zzaye().zzj("Read more than the max allowed user properties, ignoring excess", java.lang.Integer.valueOf(com.google.android.gms.internal.zzcax.zzawa()));
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0109  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.google.android.gms.internal.zzcfv> zzg(java.lang.String r12, java.lang.String r13, java.lang.String r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 281
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zzg(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    @WorkerThread
    public final List<zzcav> zzh(String str, String str2, String str3) {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        zzuj();
        zzwk();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzc(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00b0  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.google.android.gms.internal.zzcfv> zziv(java.lang.String r12) {
        /*
            r11 = this;
            r10 = 0
            com.google.android.gms.common.internal.zzbp.zzgg(r12)
            r11.zzuj()
            r11.zzwk()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            android.database.sqlite.SQLiteDatabase r0 = r11.getWritableDatabase()     // Catch: java.lang.Throwable -> Lad android.database.sqlite.SQLiteException -> Lba
            java.lang.String r1 = "user_attributes"
            r2 = 4
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> Lad android.database.sqlite.SQLiteException -> Lba
            r3 = 0
            java.lang.String r4 = "name"
            r2[r3] = r4     // Catch: java.lang.Throwable -> Lad android.database.sqlite.SQLiteException -> Lba
            r3 = 1
            java.lang.String r4 = "origin"
            r2[r3] = r4     // Catch: java.lang.Throwable -> Lad android.database.sqlite.SQLiteException -> Lba
            r3 = 2
            java.lang.String r4 = "set_timestamp"
            r2[r3] = r4     // Catch: java.lang.Throwable -> Lad android.database.sqlite.SQLiteException -> Lba
            r3 = 3
            java.lang.String r4 = "value"
            r2[r3] = r4     // Catch: java.lang.Throwable -> Lad android.database.sqlite.SQLiteException -> Lba
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> Lad android.database.sqlite.SQLiteException -> Lba
            r5 = 0
            r4[r5] = r12     // Catch: java.lang.Throwable -> Lad android.database.sqlite.SQLiteException -> Lba
            r5 = 0
            r6 = 0
            java.lang.String r7 = "rowid"
            int r8 = com.google.android.gms.internal.zzcax.zzawa()     // Catch: java.lang.Throwable -> Lad android.database.sqlite.SQLiteException -> Lba
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch: java.lang.Throwable -> Lad android.database.sqlite.SQLiteException -> Lba
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> Lad android.database.sqlite.SQLiteException -> Lba
            boolean r0 = r7.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L93 java.lang.Throwable -> Lb4
            if (r0 != 0) goto L51
            if (r7 == 0) goto L4f
            r7.close()
        L4f:
            r0 = r9
        L50:
            return r0
        L51:
            r0 = 0
            java.lang.String r3 = r7.getString(r0)     // Catch: android.database.sqlite.SQLiteException -> L93 java.lang.Throwable -> Lb4
            r0 = 1
            java.lang.String r2 = r7.getString(r0)     // Catch: android.database.sqlite.SQLiteException -> L93 java.lang.Throwable -> Lb4
            if (r2 != 0) goto L5f
            java.lang.String r2 = ""
        L5f:
            r0 = 2
            long r4 = r7.getLong(r0)     // Catch: android.database.sqlite.SQLiteException -> L93 java.lang.Throwable -> Lb4
            r0 = 3
            java.lang.Object r6 = r11.zza(r7, r0)     // Catch: android.database.sqlite.SQLiteException -> L93 java.lang.Throwable -> Lb4
            if (r6 != 0) goto L89
            com.google.android.gms.internal.zzcbw r0 = r11.zzaum()     // Catch: android.database.sqlite.SQLiteException -> L93 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.zzcby r0 = r0.zzaye()     // Catch: android.database.sqlite.SQLiteException -> L93 java.lang.Throwable -> Lb4
            java.lang.String r1 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r2 = com.google.android.gms.internal.zzcbw.zzjf(r12)     // Catch: android.database.sqlite.SQLiteException -> L93 java.lang.Throwable -> Lb4
            r0.zzj(r1, r2)     // Catch: android.database.sqlite.SQLiteException -> L93 java.lang.Throwable -> Lb4
        L7c:
            boolean r0 = r7.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L93 java.lang.Throwable -> Lb4
            if (r0 != 0) goto L51
            if (r7 == 0) goto L87
            r7.close()
        L87:
            r0 = r9
            goto L50
        L89:
            com.google.android.gms.internal.zzcfv r0 = new com.google.android.gms.internal.zzcfv     // Catch: android.database.sqlite.SQLiteException -> L93 java.lang.Throwable -> Lb4
            r1 = r12
            r0.<init>(r1, r2, r3, r4, r6)     // Catch: android.database.sqlite.SQLiteException -> L93 java.lang.Throwable -> Lb4
            r9.add(r0)     // Catch: android.database.sqlite.SQLiteException -> L93 java.lang.Throwable -> Lb4
            goto L7c
        L93:
            r0 = move-exception
            r1 = r7
        L95:
            com.google.android.gms.internal.zzcbw r2 = r11.zzaum()     // Catch: java.lang.Throwable -> Lb7
            com.google.android.gms.internal.zzcby r2 = r2.zzaye()     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r3 = "Error querying user properties. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzcbw.zzjf(r12)     // Catch: java.lang.Throwable -> Lb7
            r2.zze(r3, r4, r0)     // Catch: java.lang.Throwable -> Lb7
            if (r1 == 0) goto Lab
            r1.close()
        Lab:
            r0 = r10
            goto L50
        Lad:
            r0 = move-exception
        Lae:
            if (r10 == 0) goto Lb3
            r10.close()
        Lb3:
            throw r0
        Lb4:
            r0 = move-exception
            r10 = r7
            goto Lae
        Lb7:
            r0 = move-exception
            r10 = r1
            goto Lae
        Lba:
            r0 = move-exception
            r1 = r10
            goto L95
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zziv(java.lang.String):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x01eb  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.zzcar zziw(java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 499
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zziw(java.lang.String):com.google.android.gms.internal.zzcar");
    }

    public final long zzix(String str) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        zzuj();
        zzwk();
        try {
            return getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzauo().zzb(str, zzcbm.zziop))))});
        } catch (SQLiteException e) {
            zzaum().zzaye().zze("Error deleting over the limit events. appId", zzcbw.zzjf(str), e);
            return 0L;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0074  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final byte[] zziy(java.lang.String r10) throws java.lang.Throwable {
        /*
            r9 = this;
            r8 = 0
            com.google.android.gms.common.internal.zzbp.zzgg(r10)
            r9.zzuj()
            r9.zzwk()
            android.database.sqlite.SQLiteDatabase r0 = r9.getWritableDatabase()     // Catch: android.database.sqlite.SQLiteException -> L56 java.lang.Throwable -> L70
            java.lang.String r1 = "apps"
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: android.database.sqlite.SQLiteException -> L56 java.lang.Throwable -> L70
            r3 = 0
            java.lang.String r4 = "remote_config"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L56 java.lang.Throwable -> L70
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: android.database.sqlite.SQLiteException -> L56 java.lang.Throwable -> L70
            r5 = 0
            r4[r5] = r10     // Catch: android.database.sqlite.SQLiteException -> L56 java.lang.Throwable -> L70
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: android.database.sqlite.SQLiteException -> L56 java.lang.Throwable -> L70
            boolean r0 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7a
            if (r0 != 0) goto L34
            if (r1 == 0) goto L32
            r1.close()
        L32:
            r0 = r8
        L33:
            return r0
        L34:
            r0 = 0
            byte[] r0 = r1.getBlob(r0)     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7a
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7a
            if (r2 == 0) goto L50
            com.google.android.gms.internal.zzcbw r2 = r9.zzaum()     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7a
            com.google.android.gms.internal.zzcby r2 = r2.zzaye()     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7a
            java.lang.String r3 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzcbw.zzjf(r10)     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7a
            r2.zzj(r3, r4)     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7a
        L50:
            if (r1 == 0) goto L33
            r1.close()
            goto L33
        L56:
            r0 = move-exception
            r1 = r8
        L58:
            com.google.android.gms.internal.zzcbw r2 = r9.zzaum()     // Catch: java.lang.Throwable -> L78
            com.google.android.gms.internal.zzcby r2 = r2.zzaye()     // Catch: java.lang.Throwable -> L78
            java.lang.String r3 = "Error querying remote config. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzcbw.zzjf(r10)     // Catch: java.lang.Throwable -> L78
            r2.zze(r3, r4, r0)     // Catch: java.lang.Throwable -> L78
            if (r1 == 0) goto L6e
            r1.close()
        L6e:
            r0 = r8
            goto L33
        L70:
            r0 = move-exception
            r1 = r8
        L72:
            if (r1 == 0) goto L77
            r1.close()
        L77:
            throw r0
        L78:
            r0 = move-exception
            goto L72
        L7a:
            r0 = move-exception
            goto L58
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zziy(java.lang.String):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final java.util.Map<java.lang.Integer, com.google.android.gms.internal.zzcgl> zziz(java.lang.String r10) {
        /*
            r9 = this;
            r8 = 0
            r9.zzwk()
            r9.zzuj()
            com.google.android.gms.common.internal.zzbp.zzgg(r10)
            android.database.sqlite.SQLiteDatabase r0 = r9.getWritableDatabase()
            java.lang.String r1 = "audience_filter_values"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> La3
            r3 = 0
            java.lang.String r4 = "audience_id"
            r2[r3] = r4     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> La3
            r3 = 1
            java.lang.String r4 = "current_results"
            r2[r3] = r4     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> La3
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> La3
            r5 = 0
            r4[r5] = r10     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> La3
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> La3
            boolean r0 = r1.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            if (r0 != 0) goto L39
            if (r1 == 0) goto L37
            r1.close()
        L37:
            r0 = r8
        L38:
            return r0
        L39:
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            r0.<init>()     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
        L3e:
            r2 = 0
            int r2 = r1.getInt(r2)     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            r3 = 1
            byte[] r3 = r1.getBlob(r3)     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            r4 = 0
            int r5 = r3.length     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            com.google.android.gms.internal.zzegx r3 = com.google.android.gms.internal.zzegx.zzh(r3, r4, r5)     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            com.google.android.gms.internal.zzcgl r4 = new com.google.android.gms.internal.zzcgl     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            r4.<init>()     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            r4.zza(r3)     // Catch: java.io.IOException -> L69 android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            r0.put(r2, r4)     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
        L5d:
            boolean r2 = r1.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            if (r2 != 0) goto L3e
            if (r1 == 0) goto L38
            r1.close()
            goto L38
        L69:
            r3 = move-exception
            com.google.android.gms.internal.zzcbw r4 = r9.zzaum()     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            com.google.android.gms.internal.zzcby r4 = r4.zzaye()     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.internal.zzcbw.zzjf(r10)     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            r4.zzd(r5, r6, r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L80 java.lang.Throwable -> La1
            goto L5d
        L80:
            r0 = move-exception
        L81:
            com.google.android.gms.internal.zzcbw r2 = r9.zzaum()     // Catch: java.lang.Throwable -> La1
            com.google.android.gms.internal.zzcby r2 = r2.zzaye()     // Catch: java.lang.Throwable -> La1
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzcbw.zzjf(r10)     // Catch: java.lang.Throwable -> La1
            r2.zze(r3, r4, r0)     // Catch: java.lang.Throwable -> La1
            if (r1 == 0) goto L97
            r1.close()
        L97:
            r0 = r8
            goto L38
        L99:
            r0 = move-exception
            r1 = r8
        L9b:
            if (r1 == 0) goto La0
            r1.close()
        La0:
            throw r0
        La1:
            r0 = move-exception
            goto L9b
        La3:
            r0 = move-exception
            r1 = r8
            goto L81
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zziz(java.lang.String):java.util.Map");
    }

    public final long zzja(String str) {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00e7  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<android.util.Pair<com.google.android.gms.internal.zzcgk, java.lang.Long>> zzl(java.lang.String r12, int r13, int r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcay.zzl(java.lang.String, int, int):java.util.List");
    }

    @Override // com.google.android.gms.internal.zzcdu
    protected final void zzuk() {
    }
}
