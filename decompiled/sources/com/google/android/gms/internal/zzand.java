package com.google.android.gms.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class zzand extends zzams implements Closeable {
    private static final String zzdpi = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", "hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id");
    private static final String zzdpj = String.format("SELECT MAX(%s) FROM %s WHERE 1;", "hit_time", "hits2");
    private final zzane zzdpk;
    private final zzaoz zzdpl;
    private final zzaoz zzdpm;

    zzand(zzamu zzamuVar) {
        super(zzamuVar);
        this.zzdpl = new zzaoz(zzvx());
        this.zzdpm = new zzaoz(zzvx());
        this.zzdpk = new zzane(this, zzamuVar.getContext(), "google_analytics_v4.db");
    }

    private final long zza(String str, String[] strArr, long j) throws Throwable {
        Cursor cursorRawQuery;
        Cursor cursor = null;
        try {
            try {
                cursorRawQuery = getWritableDatabase().rawQuery(str, strArr);
            } catch (Throwable th) {
                th = th;
            }
        } catch (SQLiteException e) {
            e = e;
        }
        try {
            if (!cursorRawQuery.moveToFirst()) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return 0L;
            }
            long j2 = cursorRawQuery.getLong(0);
            if (cursorRawQuery == null) {
                return j2;
            }
            cursorRawQuery.close();
            return j2;
        } catch (SQLiteException e2) {
            e = e2;
            zzd("Database error", str, e);
            throw e;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursorRawQuery;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private final long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                Cursor cursorRawQuery = getWritableDatabase().rawQuery(str, null);
                if (!cursorRawQuery.moveToFirst()) {
                    throw new SQLiteException("Database returned empty set");
                }
                long j = cursorRawQuery.getLong(0);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return j;
            } catch (SQLiteException e) {
                zzd("Database error", str, e);
                throw e;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private final Map<String, String> zzdr(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            if (!str.startsWith("?")) {
                String strValueOf = String.valueOf(str);
                str = strValueOf.length() != 0 ? "?".concat(strValueOf) : new String("?");
            }
            return com.google.android.gms.common.util.zzl.zza(new URI(str), "UTF-8");
        } catch (URISyntaxException e) {
            zze("Error parsing hit parameters", e);
            return new HashMap(0);
        }
    }

    private final Map<String, String> zzds(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            String strValueOf = String.valueOf(str);
            return com.google.android.gms.common.util.zzl.zza(new URI(strValueOf.length() != 0 ? "?".concat(strValueOf) : new String("?")), "UTF-8");
        } catch (URISyntaxException e) {
            zze("Error parsing property parameters", e);
            return new HashMap(0);
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0073: MOVE (r10 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:25:0x0073 */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<java.lang.Long> zzn(long r14) throws java.lang.Throwable {
        /*
            r13 = this;
            r10 = 0
            com.google.android.gms.analytics.zzj.zzuj()
            r13.zzwk()
            r0 = 0
            int r0 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1))
            if (r0 > 0) goto L12
            java.util.List r0 = java.util.Collections.emptyList()
        L11:
            return r0
        L12:
            android.database.sqlite.SQLiteDatabase r0 = r13.getWritableDatabase()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.lang.String r1 = "hits2"
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: android.database.sqlite.SQLiteException -> L5e java.lang.Throwable -> L6b
            r3 = 0
            java.lang.String r4 = "hit_id"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L5e java.lang.Throwable -> L6b
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "%s ASC"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: android.database.sqlite.SQLiteException -> L5e java.lang.Throwable -> L6b
            r11 = 0
            java.lang.String r12 = "hit_id"
            r8[r11] = r12     // Catch: android.database.sqlite.SQLiteException -> L5e java.lang.Throwable -> L6b
            java.lang.String r7 = java.lang.String.format(r7, r8)     // Catch: android.database.sqlite.SQLiteException -> L5e java.lang.Throwable -> L6b
            java.lang.String r8 = java.lang.Long.toString(r14)     // Catch: android.database.sqlite.SQLiteException -> L5e java.lang.Throwable -> L6b
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch: android.database.sqlite.SQLiteException -> L5e java.lang.Throwable -> L6b
            boolean r0 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L72 android.database.sqlite.SQLiteException -> L75
            if (r0 == 0) goto L57
        L45:
            r0 = 0
            long r2 = r1.getLong(r0)     // Catch: java.lang.Throwable -> L72 android.database.sqlite.SQLiteException -> L75
            java.lang.Long r0 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Throwable -> L72 android.database.sqlite.SQLiteException -> L75
            r9.add(r0)     // Catch: java.lang.Throwable -> L72 android.database.sqlite.SQLiteException -> L75
            boolean r0 = r1.moveToNext()     // Catch: java.lang.Throwable -> L72 android.database.sqlite.SQLiteException -> L75
            if (r0 != 0) goto L45
        L57:
            if (r1 == 0) goto L5c
            r1.close()
        L5c:
            r0 = r9
            goto L11
        L5e:
            r0 = move-exception
            r1 = r10
        L60:
            java.lang.String r2 = "Error selecting hit ids"
            r13.zzd(r2, r0)     // Catch: java.lang.Throwable -> L72
            if (r1 == 0) goto L5c
            r1.close()
            goto L5c
        L6b:
            r0 = move-exception
        L6c:
            if (r10 == 0) goto L71
            r10.close()
        L71:
            throw r0
        L72:
            r0 = move-exception
            r10 = r1
            goto L6c
        L75:
            r0 = move-exception
            goto L60
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzand.zzn(long):java.util.List");
    }

    private final long zzwu() {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        return zzb("SELECT COUNT(*) FROM hits2", (String[]) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String zzxb() {
        return "google_analytics_v4.db";
    }

    public final void beginTransaction() {
        zzwk();
        getWritableDatabase().beginTransaction();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        try {
            this.zzdpk.close();
        } catch (SQLiteException e) {
            zze("Sql error closing database", e);
        } catch (IllegalStateException e2) {
            zze("Error closing database", e2);
        }
    }

    public final void endTransaction() {
        zzwk();
        getWritableDatabase().endTransaction();
    }

    final SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzdpk.getWritableDatabase();
        } catch (SQLiteException e) {
            zzd("Error opening database", e);
            throw e;
        }
    }

    final boolean isEmpty() {
        return zzwu() == 0;
    }

    public final void setTransactionSuccessful() {
        zzwk();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final long zza(long j, String str, String str2) {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzgg(str2);
        zzwk();
        com.google.android.gms.analytics.zzj.zzuj();
        return zza("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[]{String.valueOf(j), str, str2}, 0L);
    }

    public final void zzc(zzaoi zzaoiVar) throws Throwable {
        com.google.android.gms.common.internal.zzbp.zzu(zzaoiVar);
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(zzaoiVar);
        Uri.Builder builder = new Uri.Builder();
        for (Map.Entry<String, String> entry : zzaoiVar.zziy().entrySet()) {
            String key = entry.getKey();
            if (!"ht".equals(key) && !"qt".equals(key) && !"AppUID".equals(key)) {
                builder.appendQueryParameter(key, entry.getValue());
            }
        }
        String encodedQuery = builder.build().getEncodedQuery();
        String str = encodedQuery == null ? "" : encodedQuery;
        if (str.length() > 8192) {
            zzvy().zza(zzaoiVar, "Hit length exceeds the maximum allowed size");
            return;
        }
        int iIntValue = zzaod.zzdrd.get().intValue();
        long jZzwu = zzwu();
        if (jZzwu > iIntValue - 1) {
            List<Long> listZzn = zzn((jZzwu - iIntValue) + 1);
            zzd("Store full, deleting hits to make room, count", Integer.valueOf(listZzn.size()));
            zzq(listZzn);
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", str);
        contentValues.put("hit_time", Long.valueOf(zzaoiVar.zzyn()));
        contentValues.put("hit_app_id", Integer.valueOf(zzaoiVar.zzyl()));
        contentValues.put("hit_url", zzaoiVar.zzyp() ? zzanv.zzyb() : zzanv.zzyc());
        try {
            long jInsert = writableDatabase.insert("hits2", null, contentValues);
            if (jInsert == -1) {
                zzdq("Failed to insert a hit (got -1)");
            } else {
                zzb("Hit saved to database. db-id, hit", Long.valueOf(jInsert), zzaoiVar);
            }
        } catch (SQLiteException e) {
            zze("Error storing a hit", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.google.android.gms.internal.zzaoi> zzo(long r14) throws java.lang.Throwable {
        /*
            r13 = this;
            r0 = 1
            r1 = 0
            r9 = 0
            r2 = 0
            int r2 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r2 < 0) goto L8f
        L9:
            com.google.android.gms.common.internal.zzbp.zzbh(r0)
            com.google.android.gms.analytics.zzj.zzuj()
            r13.zzwk()
            android.database.sqlite.SQLiteDatabase r0 = r13.getWritableDatabase()
            java.lang.String r1 = "hits2"
            r2 = 5
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: android.database.sqlite.SQLiteException -> L92 java.lang.Throwable -> La2
            r3 = 0
            java.lang.String r4 = "hit_id"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L92 java.lang.Throwable -> La2
            r3 = 1
            java.lang.String r4 = "hit_time"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L92 java.lang.Throwable -> La2
            r3 = 2
            java.lang.String r4 = "hit_string"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L92 java.lang.Throwable -> La2
            r3 = 3
            java.lang.String r4 = "hit_url"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L92 java.lang.Throwable -> La2
            r3 = 4
            java.lang.String r4 = "hit_app_id"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L92 java.lang.Throwable -> La2
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "%s ASC"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: android.database.sqlite.SQLiteException -> L92 java.lang.Throwable -> La2
            r10 = 0
            java.lang.String r11 = "hit_id"
            r8[r10] = r11     // Catch: android.database.sqlite.SQLiteException -> L92 java.lang.Throwable -> La2
            java.lang.String r7 = java.lang.String.format(r7, r8)     // Catch: android.database.sqlite.SQLiteException -> L92 java.lang.Throwable -> La2
            java.lang.String r8 = java.lang.Long.toString(r14)     // Catch: android.database.sqlite.SQLiteException -> L92 java.lang.Throwable -> La2
            android.database.Cursor r9 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch: android.database.sqlite.SQLiteException -> L92 java.lang.Throwable -> La2
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch: java.lang.Throwable -> La2 android.database.sqlite.SQLiteException -> La4
            r10.<init>()     // Catch: java.lang.Throwable -> La2 android.database.sqlite.SQLiteException -> La4
            boolean r0 = r9.moveToFirst()     // Catch: java.lang.Throwable -> La2 android.database.sqlite.SQLiteException -> La4
            if (r0 == 0) goto L89
        L59:
            r0 = 0
            long r6 = r9.getLong(r0)     // Catch: java.lang.Throwable -> La2 android.database.sqlite.SQLiteException -> La4
            r0 = 1
            long r3 = r9.getLong(r0)     // Catch: java.lang.Throwable -> La2 android.database.sqlite.SQLiteException -> La4
            r0 = 2
            java.lang.String r0 = r9.getString(r0)     // Catch: java.lang.Throwable -> La2 android.database.sqlite.SQLiteException -> La4
            r1 = 3
            java.lang.String r1 = r9.getString(r1)     // Catch: java.lang.Throwable -> La2 android.database.sqlite.SQLiteException -> La4
            r2 = 4
            int r8 = r9.getInt(r2)     // Catch: java.lang.Throwable -> La2 android.database.sqlite.SQLiteException -> La4
            java.util.Map r2 = r13.zzdr(r0)     // Catch: java.lang.Throwable -> La2 android.database.sqlite.SQLiteException -> La4
            boolean r5 = com.google.android.gms.internal.zzapd.zzed(r1)     // Catch: java.lang.Throwable -> La2 android.database.sqlite.SQLiteException -> La4
            com.google.android.gms.internal.zzaoi r0 = new com.google.android.gms.internal.zzaoi     // Catch: java.lang.Throwable -> La2 android.database.sqlite.SQLiteException -> La4
            r1 = r13
            r0.<init>(r1, r2, r3, r5, r6, r8)     // Catch: java.lang.Throwable -> La2 android.database.sqlite.SQLiteException -> La4
            r10.add(r0)     // Catch: java.lang.Throwable -> La2 android.database.sqlite.SQLiteException -> La4
            boolean r0 = r9.moveToNext()     // Catch: java.lang.Throwable -> La2 android.database.sqlite.SQLiteException -> La4
            if (r0 != 0) goto L59
        L89:
            if (r9 == 0) goto L8e
            r9.close()
        L8e:
            return r10
        L8f:
            r0 = r1
            goto L9
        L92:
            r0 = move-exception
            r1 = r9
        L94:
            java.lang.String r2 = "Error loading hits from the database"
            r13.zze(r2, r0)     // Catch: java.lang.Throwable -> L9a
            throw r0     // Catch: java.lang.Throwable -> L9a
        L9a:
            r0 = move-exception
            r9 = r1
        L9c:
            if (r9 == 0) goto La1
            r9.close()
        La1:
            throw r0
        La2:
            r0 = move-exception
            goto L9c
        La4:
            r0 = move-exception
            r1 = r9
            goto L94
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzand.zzo(long):java.util.List");
    }

    public final void zzp(long j) {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Long.valueOf(j));
        zza("Deleting hit, id", Long.valueOf(j));
        zzq(arrayList);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.google.android.gms.internal.zzamx> zzq(long r13) throws java.lang.Throwable {
        /*
            r12 = this;
            r12.zzwk()
            com.google.android.gms.analytics.zzj.zzuj()
            android.database.sqlite.SQLiteDatabase r0 = r12.getWritableDatabase()
            r9 = 0
            r1 = 5
            java.lang.String[] r2 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> Lbb android.database.sqlite.SQLiteException -> Lbd
            r1 = 0
            java.lang.String r3 = "cid"
            r2[r1] = r3     // Catch: java.lang.Throwable -> Lbb android.database.sqlite.SQLiteException -> Lbd
            r1 = 1
            java.lang.String r3 = "tid"
            r2[r1] = r3     // Catch: java.lang.Throwable -> Lbb android.database.sqlite.SQLiteException -> Lbd
            r1 = 2
            java.lang.String r3 = "adid"
            r2[r1] = r3     // Catch: java.lang.Throwable -> Lbb android.database.sqlite.SQLiteException -> Lbd
            r1 = 3
            java.lang.String r3 = "hits_count"
            r2[r1] = r3     // Catch: java.lang.Throwable -> Lbb android.database.sqlite.SQLiteException -> Lbd
            r1 = 4
            java.lang.String r3 = "params"
            r2[r1] = r3     // Catch: java.lang.Throwable -> Lbb android.database.sqlite.SQLiteException -> Lbd
            com.google.android.gms.internal.zzaoe<java.lang.Integer> r1 = com.google.android.gms.internal.zzaod.zzdrf     // Catch: java.lang.Throwable -> Lbb android.database.sqlite.SQLiteException -> Lbd
            java.lang.Object r1 = r1.get()     // Catch: java.lang.Throwable -> Lbb android.database.sqlite.SQLiteException -> Lbd
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch: java.lang.Throwable -> Lbb android.database.sqlite.SQLiteException -> Lbd
            int r10 = r1.intValue()     // Catch: java.lang.Throwable -> Lbb android.database.sqlite.SQLiteException -> Lbd
            java.lang.String r8 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> Lbb android.database.sqlite.SQLiteException -> Lbd
            java.lang.String r3 = "app_uid=?"
            r1 = 1
            java.lang.String[] r4 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> Lbb android.database.sqlite.SQLiteException -> Lbd
            r1 = 0
            java.lang.String r5 = "0"
            r4[r1] = r5     // Catch: java.lang.Throwable -> Lbb android.database.sqlite.SQLiteException -> Lbd
            java.lang.String r1 = "properties"
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r9 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> Lbb android.database.sqlite.SQLiteException -> Lbd
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            r11.<init>()     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            boolean r0 = r9.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            if (r0 == 0) goto L8d
        L55:
            r0 = 0
            java.lang.String r3 = r9.getString(r0)     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            r0 = 1
            java.lang.String r4 = r9.getString(r0)     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            r0 = 2
            int r0 = r9.getInt(r0)     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            if (r0 == 0) goto L9e
            r5 = 1
        L67:
            r0 = 3
            int r0 = r9.getInt(r0)     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            long r6 = (long) r0     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            r0 = 4
            java.lang.String r0 = r9.getString(r0)     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            java.util.Map r8 = r12.zzds(r0)     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            if (r0 != 0) goto L82
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            if (r0 == 0) goto La0
        L82:
            java.lang.String r0 = "Read property with empty client id or tracker id"
            r12.zzc(r0, r3, r4)     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
        L87:
            boolean r0 = r9.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            if (r0 != 0) goto L55
        L8d:
            int r0 = r11.size()     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            if (r0 < r10) goto L98
            java.lang.String r0 = "Sending hits to too many properties. Campaign report might be incorrect"
            r12.zzdp(r0)     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
        L98:
            if (r9 == 0) goto L9d
            r9.close()
        L9d:
            return r11
        L9e:
            r5 = 0
            goto L67
        La0:
            com.google.android.gms.internal.zzamx r0 = new com.google.android.gms.internal.zzamx     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            r1 = 0
            r0.<init>(r1, r3, r4, r5, r6, r8)     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            r11.add(r0)     // Catch: android.database.sqlite.SQLiteException -> Lab java.lang.Throwable -> Lbb
            goto L87
        Lab:
            r0 = move-exception
            r1 = r9
        Lad:
            java.lang.String r2 = "Error loading hits from the database"
            r12.zze(r2, r0)     // Catch: java.lang.Throwable -> Lb3
            throw r0     // Catch: java.lang.Throwable -> Lb3
        Lb3:
            r0 = move-exception
            r9 = r1
        Lb5:
            if (r9 == 0) goto Lba
            r9.close()
        Lba:
            throw r0
        Lbb:
            r0 = move-exception
            goto Lb5
        Lbd:
            r0 = move-exception
            r1 = r9
            goto Lad
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzand.zzq(long):java.util.List");
    }

    public final void zzq(List<Long> list) {
        com.google.android.gms.common.internal.zzbp.zzu(list);
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        if (list.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder("hit_id");
        sb.append(" in (");
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                sb.append(")");
                String string = sb.toString();
                try {
                    SQLiteDatabase writableDatabase = getWritableDatabase();
                    zza("Deleting dispatched hits. count", Integer.valueOf(list.size()));
                    int iDelete = writableDatabase.delete("hits2", string, null);
                    if (iDelete != list.size()) {
                        zzb("Deleted fewer hits then expected", Integer.valueOf(list.size()), Integer.valueOf(iDelete), string);
                        return;
                    }
                    return;
                } catch (SQLiteException e) {
                    zze("Error deleting hits", e);
                    throw e;
                }
            }
            Long l = list.get(i2);
            if (l == null || l.longValue() == 0) {
                break;
            }
            if (i2 > 0) {
                sb.append(",");
            }
            sb.append(l);
            i = i2 + 1;
        }
        throw new SQLiteException("Invalid hit id");
    }

    @Override // com.google.android.gms.internal.zzams
    protected final void zzuk() {
    }

    public final int zzwz() {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        if (!this.zzdpl.zzu(86400000L)) {
            return 0;
        }
        this.zzdpl.start();
        zzdm("Deleting stale hits (if any)");
        int iDelete = getWritableDatabase().delete("hits2", "hit_time < ?", new String[]{Long.toString(zzvx().currentTimeMillis() - 2592000000L)});
        zza("Deleted stale hits, count", Integer.valueOf(iDelete));
        return iDelete;
    }

    public final long zzxa() {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        return zza(zzdpj, (String[]) null, 0L);
    }
}
