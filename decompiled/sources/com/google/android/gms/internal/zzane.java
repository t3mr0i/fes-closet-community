package com.google.android.gms.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
final class zzane extends SQLiteOpenHelper {
    private /* synthetic */ zzand zzdpn;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzane(zzand zzandVar, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.zzdpn = zzandVar;
    }

    private static void zza(SQLiteDatabase sQLiteDatabase) {
        Set<String> setZzb = zzb(sQLiteDatabase, "properties");
        String[] strArr = {"app_uid", "cid", "tid", "params", "adid", "hits_count"};
        for (int i = 0; i < 6; i++) {
            String str = strArr[i];
            if (!setZzb.remove(str)) {
                String strValueOf = String.valueOf(str);
                throw new SQLiteException(strValueOf.length() != 0 ? "Database properties is missing required column: ".concat(strValueOf) : new String("Database properties is missing required column: "));
            }
        }
        if (!setZzb.isEmpty()) {
            throw new SQLiteException("Database properties table has extra columns");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean zza(android.database.sqlite.SQLiteDatabase r11, java.lang.String r12) throws java.lang.Throwable {
        /*
            r10 = this;
            r8 = 0
            r9 = 0
            java.lang.String r1 = "SQLITE_MASTER"
            r0 = 1
            java.lang.String[] r2 = new java.lang.String[r0]     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L36
            r0 = 0
            java.lang.String r3 = "name"
            r2[r0] = r3     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L36
            java.lang.String r3 = "name=?"
            r0 = 1
            java.lang.String[] r4 = new java.lang.String[r0]     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L36
            r0 = 0
            r4[r0] = r12     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L36
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r11
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L36
            boolean r0 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L3d android.database.sqlite.SQLiteException -> L40
            if (r1 == 0) goto L25
            r1.close()
        L25:
            return r0
        L26:
            r0 = move-exception
            r1 = r9
        L28:
            com.google.android.gms.internal.zzand r2 = r10.zzdpn     // Catch: java.lang.Throwable -> L3d
            java.lang.String r3 = "Error querying for table"
            r2.zzc(r3, r12, r0)     // Catch: java.lang.Throwable -> L3d
            if (r1 == 0) goto L34
            r1.close()
        L34:
            r0 = r8
            goto L25
        L36:
            r0 = move-exception
        L37:
            if (r9 == 0) goto L3c
            r9.close()
        L3c:
            throw r0
        L3d:
            r0 = move-exception
            r9 = r1
            goto L37
        L40:
            r0 = move-exception
            goto L28
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzane.zza(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    private static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery(new StringBuilder(String.valueOf(str).length() + 22).append("SELECT * FROM ").append(str).append(" LIMIT 0").toString(), null);
        try {
            for (String str2 : cursorRawQuery.getColumnNames()) {
                hashSet.add(str2);
            }
            return hashSet;
        } finally {
            cursorRawQuery.close();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final SQLiteDatabase getWritableDatabase() {
        if (!this.zzdpn.zzdpm.zzu(3600000L)) {
            throw new SQLiteException("Database open failed");
        }
        try {
            return super.getWritableDatabase();
        } catch (SQLiteException e) {
            this.zzdpn.zzdpm.start();
            this.zzdpn.zzdq("Opening the database failed, dropping the table and recreating it");
            zzand zzandVar = this.zzdpn;
            this.zzdpn.getContext().getDatabasePath(zzand.zzxb()).delete();
            try {
                SQLiteDatabase writableDatabase = super.getWritableDatabase();
                this.zzdpn.zzdpm.clear();
                return writableDatabase;
            } catch (SQLiteException e2) {
                this.zzdpn.zze("Failed to open freshly created database", e2);
                throw e2;
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        String path = sQLiteDatabase.getPath();
        if (zzaoc.version() >= 9) {
            File file = new File(path);
            file.setReadable(false, false);
            file.setWritable(false, false);
            file.setReadable(true, true);
            file.setWritable(true, true);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onOpen(SQLiteDatabase sQLiteDatabase) throws SQLException {
        if (Build.VERSION.SDK_INT < 15) {
            Cursor cursorRawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
            try {
                cursorRawQuery.moveToFirst();
            } finally {
                cursorRawQuery.close();
            }
        }
        if (zza(sQLiteDatabase, "hits2")) {
            Set<String> setZzb = zzb(sQLiteDatabase, "hits2");
            String[] strArr = {"hit_id", "hit_string", "hit_time", "hit_url"};
            for (int i = 0; i < 4; i++) {
                String str = strArr[i];
                if (!setZzb.remove(str)) {
                    String strValueOf = String.valueOf(str);
                    throw new SQLiteException(strValueOf.length() != 0 ? "Database hits2 is missing required column: ".concat(strValueOf) : new String("Database hits2 is missing required column: "));
                }
            }
            boolean z = setZzb.remove("hit_app_id") ? false : true;
            if (!setZzb.isEmpty()) {
                throw new SQLiteException("Database hits2 has extra columns");
            }
            if (z) {
                sQLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id INTEGER");
            }
        } else {
            sQLiteDatabase.execSQL(zzand.zzdpi);
        }
        if (zza(sQLiteDatabase, "properties")) {
            zza(sQLiteDatabase);
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS properties ( app_uid INTEGER NOT NULL, cid TEXT NOT NULL, tid TEXT NOT NULL, params TEXT NOT NULL, adid INTEGER NOT NULL, hits_count INTEGER NOT NULL, PRIMARY KEY (app_uid, cid, tid)) ;");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
