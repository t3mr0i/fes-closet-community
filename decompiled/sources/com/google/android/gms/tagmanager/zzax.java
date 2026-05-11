package com.google.android.gms.tagmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashSet;

/* loaded from: classes.dex */
final class zzax extends SQLiteOpenHelper {
    private /* synthetic */ zzat zzjqw;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzax(zzat zzatVar, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.zzjqw = zzatVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean zza(java.lang.String r10, android.database.sqlite.SQLiteDatabase r11) throws java.lang.Throwable {
        /*
            r8 = 0
            r9 = 0
            java.lang.String r1 = "SQLITE_MASTER"
            r0 = 1
            java.lang.String[] r2 = new java.lang.String[r0]     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L51
            r0 = 0
            java.lang.String r3 = "name"
            r2[r0] = r3     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L51
            java.lang.String r3 = "name=?"
            r0 = 1
            java.lang.String[] r4 = new java.lang.String[r0]     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L51
            r0 = 0
            r4[r0] = r10     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L51
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r11
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L51
            boolean r0 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteException -> L58
            if (r1 == 0) goto L25
            r1.close()
        L25:
            return r0
        L26:
            r0 = move-exception
            r0 = r9
        L28:
            java.lang.String r2 = "Error querying for table "
            java.lang.String r1 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> L48
            int r3 = r1.length()     // Catch: java.lang.Throwable -> L48
            if (r3 == 0) goto L42
            java.lang.String r1 = r2.concat(r1)     // Catch: java.lang.Throwable -> L48
        L38:
            com.google.android.gms.tagmanager.zzdj.zzcr(r1)     // Catch: java.lang.Throwable -> L48
            if (r0 == 0) goto L40
            r0.close()
        L40:
            r0 = r8
            goto L25
        L42:
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Throwable -> L48
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L48
            goto L38
        L48:
            r1 = move-exception
            r2 = r1
            r9 = r0
        L4b:
            if (r9 == 0) goto L50
            r9.close()
        L50:
            throw r2
        L51:
            r0 = move-exception
            r2 = r0
            goto L4b
        L54:
            r0 = move-exception
            r2 = r0
            r9 = r1
            goto L4b
        L58:
            r0 = move-exception
            r0 = r1
            goto L28
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzax.zza(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase writableDatabase = null;
        try {
            writableDatabase = super.getWritableDatabase();
        } catch (SQLiteException e) {
            this.zzjqw.mContext.getDatabasePath("google_tagmanager.db").delete();
        }
        return writableDatabase == null ? super.getWritableDatabase() : writableDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzbs.zzlq(sQLiteDatabase.getPath());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onOpen(SQLiteDatabase sQLiteDatabase) throws SQLException {
        if (Build.VERSION.SDK_INT < 15) {
            try {
                sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null).moveToFirst();
            } finally {
            }
        }
        if (!zza("datalayer", sQLiteDatabase)) {
            sQLiteDatabase.execSQL(zzat.zzjqq);
            return;
        }
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT * FROM datalayer WHERE 0", null);
        HashSet hashSet = new HashSet();
        try {
            for (String str : cursorRawQuery.getColumnNames()) {
                hashSet.add(str);
            }
            cursorRawQuery.close();
            if (!hashSet.remove("key") || !hashSet.remove(FirebaseAnalytics.Param.VALUE) || !hashSet.remove("ID") || !hashSet.remove("expires")) {
                throw new SQLiteException("Database column missing");
            }
            if (!hashSet.isEmpty()) {
                throw new SQLiteException("Database has extra columns");
            }
        } finally {
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
