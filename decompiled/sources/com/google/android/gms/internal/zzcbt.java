package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.WorkerThread;

@TargetApi(11)
/* loaded from: classes.dex */
final class zzcbt extends SQLiteOpenHelper {
    private /* synthetic */ zzcbs zzipm;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcbt(zzcbs zzcbsVar, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.zzipm = zzcbsVar;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    @WorkerThread
    public final SQLiteDatabase getWritableDatabase() {
        try {
            return super.getWritableDatabase();
        } catch (SQLiteException e) {
            if (Build.VERSION.SDK_INT >= 11 && (e instanceof SQLiteDatabaseLockedException)) {
                throw e;
            }
            this.zzipm.zzaum().zzaye().log("Opening the local database failed, dropping and recreating it");
            String strZzawk = zzcax.zzawk();
            if (!this.zzipm.getContext().getDatabasePath(strZzawk).delete()) {
                this.zzipm.zzaum().zzaye().zzj("Failed to delete corrupted local db file", strZzawk);
            }
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e2) {
                this.zzipm.zzaum().zzaye().zzj("Failed to open local database. Events will bypass local storage", e2);
                return null;
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    @WorkerThread
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzcay.zza(this.zzipm.zzaum(), sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    @WorkerThread
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    @WorkerThread
    public final void onOpen(SQLiteDatabase sQLiteDatabase) throws IllegalStateException, SQLException {
        if (Build.VERSION.SDK_INT < 15) {
            Cursor cursorRawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
            try {
                cursorRawQuery.moveToFirst();
            } finally {
                cursorRawQuery.close();
            }
        }
        zzcay.zza(this.zzipm.zzaum(), sQLiteDatabase, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", null);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    @WorkerThread
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
