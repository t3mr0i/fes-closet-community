package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Build;
import android.os.Parcel;
import android.os.SystemClock;
import android.support.annotation.WorkerThread;

/* loaded from: classes.dex */
public final class zzcbs extends zzcdu {
    private final zzcbt zzipk;
    private boolean zzipl;

    zzcbs(zzccw zzccwVar) {
        super(zzccwVar);
        this.zzipk = new zzcbt(this, getContext(), zzcax.zzawk());
    }

    @WorkerThread
    private final SQLiteDatabase getWritableDatabase() {
        if (this.zzipl) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzipk.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzipl = true;
        return null;
    }

    @WorkerThread
    @TargetApi(11)
    private final boolean zzb(int i, byte[] bArr) {
        zzatw();
        zzuj();
        if (this.zzipl) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put("entry", bArr);
        int i2 = 5;
        zzcax.zzawu();
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= 5) {
                zzaum().zzayg().log("Failed to write entry to local database");
                return false;
            }
            SQLiteDatabase sQLiteDatabase = null;
            Cursor cursor = null;
            try {
                try {
                    SQLiteDatabase writableDatabase = getWritableDatabase();
                    if (writableDatabase == null) {
                        this.zzipl = true;
                        if (writableDatabase != null) {
                            writableDatabase.close();
                        }
                        return false;
                    }
                    writableDatabase.beginTransaction();
                    long j = 0;
                    Cursor cursorRawQuery = writableDatabase.rawQuery("select count(1) from messages", null);
                    if (cursorRawQuery != null && cursorRawQuery.moveToFirst()) {
                        j = cursorRawQuery.getLong(0);
                    }
                    if (j >= 100000) {
                        zzaum().zzaye().log("Data loss, local db full");
                        long j2 = (100000 - j) + 1;
                        long jDelete = writableDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", new String[]{Long.toString(j2)});
                        if (jDelete != j2) {
                            zzaum().zzaye().zzd("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j2), Long.valueOf(jDelete), Long.valueOf(j2 - jDelete));
                        }
                    }
                    writableDatabase.insertOrThrow("messages", null, contentValues);
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                    return true;
                } catch (SQLiteException e) {
                    if (Build.VERSION.SDK_INT < 11 || !(e instanceof SQLiteDatabaseLockedException)) {
                        if (0 != 0 && sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                        zzaum().zzaye().zzj("Error writing entry to local database", e);
                        this.zzipl = true;
                    } else {
                        SystemClock.sleep(i2);
                        i2 += 20;
                    }
                    if (0 != 0) {
                        cursor.close();
                    }
                    if (0 != 0) {
                        sQLiteDatabase.close();
                    }
                }
            } catch (SQLiteFullException e2) {
                try {
                    zzaum().zzaye().zzj("Error writing entry to local database", e2);
                    this.zzipl = true;
                    if (0 != 0) {
                        cursor.close();
                    }
                    if (0 != 0) {
                        sQLiteDatabase.close();
                    }
                } catch (Throwable th) {
                    if (0 != 0) {
                        cursor.close();
                    }
                    if (0 != 0) {
                        sQLiteDatabase.close();
                    }
                    throw th;
                }
            }
            i3 = i4 + 1;
        }
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final boolean zza(zzcbk zzcbkVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzcbkVar.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length <= 131072) {
            return zzb(0, bArrMarshall);
        }
        zzaum().zzayg().log("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzcft zzcftVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzcftVar.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length <= 131072) {
            return zzb(1, bArrMarshall);
        }
        zzaum().zzayg().log("User property too long for local database. Sending directly to service");
        return false;
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatv() {
        super.zzatv();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatw() {
        super.zzatw();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatx() {
        super.zzatx();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcan zzaty() {
        return super.zzaty();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcau zzatz() {
        return super.zzatz();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcdw zzaua() {
        return super.zzaua();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbr zzaub() {
        return super.zzaub();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbe zzauc() {
        return super.zzauc();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzceo zzaud() {
        return super.zzaud();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcek zzaue() {
        return super.zzaue();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbs zzauf() {
        return super.zzauf();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcay zzaug() {
        return super.zzaug();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbu zzauh() {
        return super.zzauh();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcfw zzaui() {
        return super.zzaui();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzccq zzauj() {
        return super.zzauj();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcfl zzauk() {
        return super.zzauk();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzccr zzaul() {
        return super.zzaul();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbw zzaum() {
        return super.zzaum();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcch zzaun() {
        return super.zzaun();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcax zzauo() {
        return super.zzauo();
    }

    public final boolean zzc(zzcav zzcavVar) {
        zzaui();
        byte[] bArrZza = zzcfw.zza(zzcavVar);
        if (bArrZza.length <= 131072) {
            return zzb(2, bArrZza);
        }
        zzaum().zzayg().log("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00fb  */
    /* JADX WARN: Type inference failed for: r0v29, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v31 */
    /* JADX WARN: Type inference failed for: r0v32 */
    /* JADX WARN: Type inference failed for: r0v33 */
    /* JADX WARN: Type inference failed for: r0v34, types: [java.util.List<com.google.android.gms.internal.zzbck>] */
    /* JADX WARN: Type inference failed for: r0v35 */
    @android.annotation.TargetApi(11)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.google.android.gms.internal.zzbck> zzdw(int r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 527
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcbs.zzdw(int):java.util.List");
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    @Override // com.google.android.gms.internal.zzcdu
    protected final void zzuk() {
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ com.google.android.gms.common.util.zzd zzvx() {
        return super.zzvx();
    }
}
