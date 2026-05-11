package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
final class zzec implements zzcc {
    private static final String zzdpi = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time");
    private final Context mContext;
    private com.google.android.gms.common.util.zzd zzasb;
    private final zzee zzjsx;
    private volatile zzbe zzjsy;
    private final zzcd zzjsz;
    private final String zzjta;
    private long zzjtb;
    private final int zzjtc;

    zzec(zzcd zzcdVar, Context context) {
        this(zzcdVar, context, "gtm_urls.db", 2000);
    }

    private zzec(zzcd zzcdVar, Context context, String str, int i) {
        this.mContext = context.getApplicationContext();
        this.zzjta = str;
        this.zzjsz = zzcdVar;
        this.zzasb = com.google.android.gms.common.util.zzh.zzald();
        this.zzjsx = new zzee(this, this.mContext, this.zzjta);
        this.zzjsy = new zzfv(this.mContext, new zzed(this));
        this.zzjtb = 0L;
        this.zzjtc = 2000;
    }

    private final int zzbef() {
        Cursor cursorRawQuery = null;
        SQLiteDatabase sQLiteDatabaseZzlm = zzlm("Error opening database for getNumStoredHits.");
        try {
            if (sQLiteDatabaseZzlm != null) {
                try {
                    cursorRawQuery = sQLiteDatabaseZzlm.rawQuery("SELECT COUNT(*) from gtm_hits", null);
                    i = cursorRawQuery.moveToFirst() ? (int) cursorRawQuery.getLong(0) : 0;
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                } catch (SQLiteException e) {
                    zzdj.zzcr("Error getting numStoredHits");
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                }
            }
            return i;
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzbeg() throws java.lang.Throwable {
        /*
            r10 = this;
            r8 = 0
            r9 = 0
            java.lang.String r0 = "Error opening database for getNumStoredHits."
            android.database.sqlite.SQLiteDatabase r0 = r10.zzlm(r0)
            if (r0 != 0) goto Lb
        La:
            return r8
        Lb:
            java.lang.String r1 = "gtm_hits"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: android.database.sqlite.SQLiteException -> L2f java.lang.Throwable -> L3d
            r3 = 0
            java.lang.String r4 = "hit_id"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L2f java.lang.Throwable -> L3d
            r3 = 1
            java.lang.String r4 = "hit_first_send_time"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L2f java.lang.Throwable -> L3d
            java.lang.String r3 = "hit_first_send_time=0"
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: android.database.sqlite.SQLiteException -> L2f java.lang.Throwable -> L3d
            int r0 = r1.getCount()     // Catch: java.lang.Throwable -> L45 android.database.sqlite.SQLiteException -> L4d
            if (r1 == 0) goto L2d
            r1.close()
        L2d:
            r8 = r0
            goto La
        L2f:
            r0 = move-exception
            r0 = r9
        L31:
            java.lang.String r1 = "Error getting num untried hits"
            com.google.android.gms.tagmanager.zzdj.zzcr(r1)     // Catch: java.lang.Throwable -> L49
            if (r0 == 0) goto L50
            r0.close()
            r0 = r8
            goto L2d
        L3d:
            r0 = move-exception
            r2 = r0
        L3f:
            if (r9 == 0) goto L44
            r9.close()
        L44:
            throw r2
        L45:
            r0 = move-exception
            r2 = r0
            r9 = r1
            goto L3f
        L49:
            r1 = move-exception
            r2 = r1
            r9 = r0
            goto L3f
        L4d:
            r0 = move-exception
            r0 = r1
            goto L31
        L50:
            r0 = r8
            goto L2d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzec.zzbeg():int");
    }

    private final void zzd(String[] strArr) {
        SQLiteDatabase sQLiteDatabaseZzlm;
        if (strArr == null || strArr.length == 0 || (sQLiteDatabaseZzlm = zzlm("Error opening database for deleteHits.")) == null) {
            return;
        }
        try {
            sQLiteDatabaseZzlm.delete("gtm_hits", String.format("HIT_ID in (%s)", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))), strArr);
            this.zzjsz.zzbu(zzbef() == 0);
        } catch (SQLiteException e) {
            zzdj.zzcr("Error deleting hits");
        }
    }

    private final List<String> zzek(int i) throws Throwable {
        Cursor cursorQuery;
        Cursor cursor = null;
        ArrayList arrayList = new ArrayList();
        if (i <= 0) {
            zzdj.zzcr("Invalid maxHits specified. Skipping");
            return arrayList;
        }
        SQLiteDatabase sQLiteDatabaseZzlm = zzlm("Error opening database for peekHitIds.");
        try {
            if (sQLiteDatabaseZzlm == null) {
                return arrayList;
            }
            try {
                cursorQuery = sQLiteDatabaseZzlm.query("gtm_hits", new String[]{"hit_id"}, null, null, null, null, String.format("%s ASC", "hit_id"), Integer.toString(i));
                try {
                    if (cursorQuery.moveToFirst()) {
                        do {
                            arrayList.add(String.valueOf(cursorQuery.getLong(0)));
                        } while (cursorQuery.moveToNext());
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (SQLiteException e) {
                    e = e;
                    String strValueOf = String.valueOf(e.getMessage());
                    zzdj.zzcr(strValueOf.length() != 0 ? "Error in peekHits fetching hitIds: ".concat(strValueOf) : new String("Error in peekHits fetching hitIds: "));
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return arrayList;
                }
            } catch (SQLiteException e2) {
                e = e2;
                cursorQuery = null;
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00f8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<com.google.android.gms.tagmanager.zzbx> zzel(int r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 391
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzec.zzel(int):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzh(long j, long j2) {
        SQLiteDatabase sQLiteDatabaseZzlm = zzlm("Error opening database for getNumStoredHits.");
        if (sQLiteDatabaseZzlm == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_first_send_time", Long.valueOf(j2));
        try {
            sQLiteDatabaseZzlm.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j)});
        } catch (SQLiteException e) {
            zzdj.zzcr(new StringBuilder(69).append("Error setting HIT_FIRST_DISPATCH_TIME for hitId: ").append(j).toString());
            zzp(j);
        }
    }

    private final SQLiteDatabase zzlm(String str) {
        try {
            return this.zzjsx.getWritableDatabase();
        } catch (SQLiteException e) {
            zzdj.zzcr(str);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzp(long j) {
        zzd(new String[]{String.valueOf(j)});
    }

    @Override // com.google.android.gms.tagmanager.zzcc
    public final void dispatch() throws Throwable {
        zzdj.v("GTM Dispatch running...");
        if (this.zzjsy.zzbdl()) {
            List<zzbx> listZzel = zzel(40);
            if (listZzel.isEmpty()) {
                zzdj.v("...nothing to dispatch");
                this.zzjsz.zzbu(true);
            } else {
                this.zzjsy.zzai(listZzel);
                if (zzbeg() > 0) {
                    zzfo.zzbfa().dispatch();
                }
            }
        }
    }

    @Override // com.google.android.gms.tagmanager.zzcc
    public final void zzb(long j, String str) throws Throwable {
        long jCurrentTimeMillis = this.zzasb.currentTimeMillis();
        if (jCurrentTimeMillis > this.zzjtb + 86400000) {
            this.zzjtb = jCurrentTimeMillis;
            SQLiteDatabase sQLiteDatabaseZzlm = zzlm("Error opening database for deleteStaleHits.");
            if (sQLiteDatabaseZzlm != null) {
                sQLiteDatabaseZzlm.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(this.zzasb.currentTimeMillis() - 2592000000L)});
                this.zzjsz.zzbu(zzbef() == 0);
            }
        }
        int iZzbef = (zzbef() - this.zzjtc) + 1;
        if (iZzbef > 0) {
            List<String> listZzek = zzek(iZzbef);
            zzdj.v(new StringBuilder(51).append("Store full, deleting ").append(listZzek.size()).append(" hits to make room.").toString());
            zzd((String[]) listZzek.toArray(new String[0]));
        }
        SQLiteDatabase sQLiteDatabaseZzlm2 = zzlm("Error opening database for putHit");
        if (sQLiteDatabaseZzlm2 != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_time", Long.valueOf(j));
            contentValues.put("hit_url", str);
            contentValues.put("hit_first_send_time", (Integer) 0);
            try {
                sQLiteDatabaseZzlm2.insert("gtm_hits", null, contentValues);
                this.zzjsz.zzbu(false);
            } catch (SQLiteException e) {
                zzdj.zzcr("Error storing hit");
            }
        }
    }
}
