package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.google.android.gms.tagmanager.DataLayer;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
final class zzat implements DataLayer.zzc {
    private static final String zzjqq = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", "datalayer", "ID", "key", FirebaseAnalytics.Param.VALUE, "expires");
    private final Context mContext;
    private com.google.android.gms.common.util.zzd zzasb;
    private final Executor zzjqr;
    private zzax zzjqs;
    private int zzjqt;

    public zzat(Context context) {
        this(context, com.google.android.gms.common.util.zzh.zzald(), "google_tagmanager.db", 2000, Executors.newSingleThreadExecutor());
    }

    private zzat(Context context, com.google.android.gms.common.util.zzd zzdVar, String str, int i, Executor executor) {
        this.mContext = context;
        this.zzasb = zzdVar;
        this.zzjqt = 2000;
        this.zzjqr = executor;
        this.zzjqs = new zzax(this, this.mContext, str);
    }

    private static byte[] zzae(Object obj) throws Throwable {
        Throwable th;
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2;
        byte[] byteArray = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
        } catch (IOException e) {
            objectOutputStream2 = null;
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream = null;
        }
        try {
            objectOutputStream2.writeObject(obj);
            byteArray = byteArrayOutputStream.toByteArray();
            try {
                objectOutputStream2.close();
                byteArrayOutputStream.close();
            } catch (IOException e2) {
            }
        } catch (IOException e3) {
            if (objectOutputStream2 != null) {
                try {
                    objectOutputStream2.close();
                } catch (IOException e4) {
                }
            }
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th3) {
            th = th3;
            objectOutputStream = objectOutputStream2;
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e5) {
                    throw th;
                }
            }
            byteArrayOutputStream.close();
            throw th;
        }
        return byteArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004c A[Catch: all -> 0x009b, TRY_LEAVE, TryCatch #2 {all -> 0x009b, blocks: (B:3:0x0001, B:5:0x0018, B:7:0x0049, B:13:0x0056, B:15:0x005e, B:16:0x007c, B:19:0x0083, B:21:0x0093, B:22:0x0097, B:30:0x00a3, B:9:0x004c), top: B:32:0x0001, outer: #0, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized void zzb(java.util.List<com.google.android.gms.tagmanager.zzay> r11, long r12) {
        /*
            r10 = this;
            monitor-enter(r10)
            com.google.android.gms.common.util.zzd r0 = r10.zzasb     // Catch: java.lang.Throwable -> L9b
            long r2 = r0.currentTimeMillis()     // Catch: java.lang.Throwable -> L9b
            r10.zzbg(r2)     // Catch: java.lang.Throwable -> L9b
            int r0 = r11.size()     // Catch: java.lang.Throwable -> L9b
            int r1 = r10.zzbdi()     // Catch: java.lang.Throwable -> L9b
            int r4 = r10.zzjqt     // Catch: java.lang.Throwable -> L9b
            int r1 = r1 - r4
            int r0 = r0 + r1
            if (r0 <= 0) goto L4c
            java.util.List r0 = r10.zzef(r0)     // Catch: java.lang.Throwable -> L9b
            int r1 = r0.size()     // Catch: java.lang.Throwable -> L9b
            r4 = 64
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9b
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L9b
            java.lang.String r4 = "DataLayer store full, deleting "
            java.lang.StringBuilder r4 = r5.append(r4)     // Catch: java.lang.Throwable -> L9b
            java.lang.StringBuilder r1 = r4.append(r1)     // Catch: java.lang.Throwable -> L9b
            java.lang.String r4 = " entries to make room."
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch: java.lang.Throwable -> L9b
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L9b
            com.google.android.gms.tagmanager.zzdj.zzcq(r1)     // Catch: java.lang.Throwable -> L9b
            r1 = 0
            java.lang.String[] r1 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> L9b
            java.lang.Object[] r0 = r0.toArray(r1)     // Catch: java.lang.Throwable -> L9b
            java.lang.String[] r0 = (java.lang.String[]) r0     // Catch: java.lang.Throwable -> L9b
            if (r0 == 0) goto L4c
            int r1 = r0.length     // Catch: java.lang.Throwable -> L9b
            if (r1 != 0) goto L56
        L4c:
            long r0 = r2 + r12
            r10.zzc(r11, r0)     // Catch: java.lang.Throwable -> L9b
            r10.zzbdj()     // Catch: java.lang.Throwable -> La0
            monitor-exit(r10)
            return
        L56:
            java.lang.String r1 = "Error opening database for deleteEntries."
            android.database.sqlite.SQLiteDatabase r1 = r10.zzlm(r1)     // Catch: java.lang.Throwable -> L9b
            if (r1 == 0) goto L4c
            java.lang.String r4 = "%s in (%s)"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L9b
            r6 = 0
            java.lang.String r7 = "ID"
            r5[r6] = r7     // Catch: java.lang.Throwable -> L9b
            r6 = 1
            java.lang.String r7 = ","
            int r8 = r0.length     // Catch: java.lang.Throwable -> L9b
            java.lang.String r9 = "?"
            java.util.List r8 = java.util.Collections.nCopies(r8, r9)     // Catch: java.lang.Throwable -> L9b
            java.lang.String r7 = android.text.TextUtils.join(r7, r8)     // Catch: java.lang.Throwable -> L9b
            r5[r6] = r7     // Catch: java.lang.Throwable -> L9b
            java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch: java.lang.Throwable -> L9b
            java.lang.String r5 = "datalayer"
            r1.delete(r5, r4, r0)     // Catch: android.database.sqlite.SQLiteException -> L82 java.lang.Throwable -> L9b
            goto L4c
        L82:
            r1 = move-exception
            java.lang.String r1 = "Error deleting entries "
            java.lang.String r0 = java.util.Arrays.toString(r0)     // Catch: java.lang.Throwable -> L9b
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch: java.lang.Throwable -> L9b
            int r4 = r0.length()     // Catch: java.lang.Throwable -> L9b
            if (r4 == 0) goto La3
            java.lang.String r0 = r1.concat(r0)     // Catch: java.lang.Throwable -> L9b
        L97:
            com.google.android.gms.tagmanager.zzdj.zzcr(r0)     // Catch: java.lang.Throwable -> L9b
            goto L4c
        L9b:
            r0 = move-exception
            r10.zzbdj()     // Catch: java.lang.Throwable -> La0
            throw r0     // Catch: java.lang.Throwable -> La0
        La0:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        La3:
            java.lang.String r0 = new java.lang.String     // Catch: java.lang.Throwable -> L9b
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L9b
            goto L97
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzat.zzb(java.util.List, long):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<DataLayer.zza> zzbdg() {
        try {
            zzbg(this.zzasb.currentTimeMillis());
            List<zzay> listZzbdh = zzbdh();
            ArrayList arrayList = new ArrayList();
            for (zzay zzayVar : listZzbdh) {
                arrayList.add(new DataLayer.zza(zzayVar.zzbfe, zzw(zzayVar.zzjqz)));
            }
            return arrayList;
        } finally {
            zzbdj();
        }
    }

    private final List<zzay> zzbdh() {
        SQLiteDatabase sQLiteDatabaseZzlm = zzlm("Error opening database for loadSerialized.");
        ArrayList arrayList = new ArrayList();
        if (sQLiteDatabaseZzlm == null) {
            return arrayList;
        }
        Cursor cursorQuery = sQLiteDatabaseZzlm.query("datalayer", new String[]{"key", FirebaseAnalytics.Param.VALUE}, null, null, null, null, "ID", null);
        while (cursorQuery.moveToNext()) {
            try {
                arrayList.add(new zzay(cursorQuery.getString(0), cursorQuery.getBlob(1)));
            } finally {
                cursorQuery.close();
            }
        }
        return arrayList;
    }

    private final int zzbdi() {
        Cursor cursorRawQuery = null;
        SQLiteDatabase sQLiteDatabaseZzlm = zzlm("Error opening database for getNumStoredEntries.");
        try {
            if (sQLiteDatabaseZzlm != null) {
                try {
                    cursorRawQuery = sQLiteDatabaseZzlm.rawQuery("SELECT COUNT(*) from datalayer", null);
                    i = cursorRawQuery.moveToFirst() ? (int) cursorRawQuery.getLong(0) : 0;
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                } catch (SQLiteException e) {
                    zzdj.zzcr("Error getting numStoredEntries");
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

    private final void zzbdj() {
        try {
            this.zzjqs.close();
        } catch (SQLiteException e) {
        }
    }

    private final void zzbg(long j) {
        SQLiteDatabase sQLiteDatabaseZzlm = zzlm("Error opening database for deleteOlderThan.");
        if (sQLiteDatabaseZzlm == null) {
            return;
        }
        try {
            zzdj.v(new StringBuilder(33).append("Deleted ").append(sQLiteDatabaseZzlm.delete("datalayer", "expires <= ?", new String[]{Long.toString(j)})).append(" expired items").toString());
        } catch (SQLiteException e) {
            zzdj.zzcr("Error deleting old entries.");
        }
    }

    private final void zzc(List<zzay> list, long j) {
        SQLiteDatabase sQLiteDatabaseZzlm = zzlm("Error opening database for writeEntryToDatabase.");
        if (sQLiteDatabaseZzlm == null) {
            return;
        }
        for (zzay zzayVar : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("expires", Long.valueOf(j));
            contentValues.put("key", zzayVar.zzbfe);
            contentValues.put(FirebaseAnalytics.Param.VALUE, zzayVar.zzjqz);
            sQLiteDatabaseZzlm.insert("datalayer", null, contentValues);
        }
    }

    private final List<String> zzef(int i) throws Throwable {
        Cursor cursorQuery;
        Cursor cursor = null;
        ArrayList arrayList = new ArrayList();
        if (i <= 0) {
            zzdj.zzcr("Invalid maxEntries specified. Skipping.");
            return arrayList;
        }
        SQLiteDatabase sQLiteDatabaseZzlm = zzlm("Error opening database for peekEntryIds.");
        try {
            if (sQLiteDatabaseZzlm == null) {
                return arrayList;
            }
            try {
                cursorQuery = sQLiteDatabaseZzlm.query("datalayer", new String[]{"ID"}, null, null, null, null, String.format("%s ASC", "ID"), Integer.toString(i));
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
                    zzdj.zzcr(strValueOf.length() != 0 ? "Error in peekEntries fetching entryIds: ".concat(strValueOf) : new String("Error in peekEntries fetching entryIds: "));
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzll(String str) {
        SQLiteDatabase sQLiteDatabaseZzlm = zzlm("Error opening database for clearKeysWithPrefix.");
        if (sQLiteDatabaseZzlm == null) {
            return;
        }
        try {
            zzdj.v(new StringBuilder(25).append("Cleared ").append(sQLiteDatabaseZzlm.delete("datalayer", "key = ? OR key LIKE ?", new String[]{str, String.valueOf(str).concat(".%")})).append(" items").toString());
        } catch (SQLiteException e) {
            String strValueOf = String.valueOf(e);
            zzdj.zzcr(new StringBuilder(String.valueOf(str).length() + 44 + String.valueOf(strValueOf).length()).append("Error deleting entries with key prefix: ").append(str).append(" (").append(strValueOf).append(").").toString());
        } finally {
            zzbdj();
        }
    }

    private final SQLiteDatabase zzlm(String str) {
        try {
            return this.zzjqs.getWritableDatabase();
        } catch (SQLiteException e) {
            zzdj.zzcr(str);
            return null;
        }
    }

    private static Object zzw(byte[] bArr) throws Throwable {
        Throwable th;
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2;
        Object object = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            objectInputStream2 = new ObjectInputStream(byteArrayInputStream);
        } catch (IOException e) {
            objectInputStream2 = null;
        } catch (ClassNotFoundException e2) {
            objectInputStream2 = null;
        } catch (Throwable th2) {
            th = th2;
            objectInputStream = null;
        }
        try {
            object = objectInputStream2.readObject();
            try {
                objectInputStream2.close();
                byteArrayInputStream.close();
            } catch (IOException e3) {
            }
        } catch (IOException e4) {
            if (objectInputStream2 != null) {
                try {
                    objectInputStream2.close();
                } catch (IOException e5) {
                }
            }
            byteArrayInputStream.close();
            return object;
        } catch (ClassNotFoundException e6) {
            if (objectInputStream2 != null) {
                try {
                    objectInputStream2.close();
                } catch (IOException e7) {
                }
            }
            byteArrayInputStream.close();
            return object;
        } catch (Throwable th3) {
            th = th3;
            objectInputStream = objectInputStream2;
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e8) {
                    throw th;
                }
            }
            byteArrayInputStream.close();
            throw th;
        }
        return object;
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.zzc
    public final void zza(zzaq zzaqVar) {
        this.zzjqr.execute(new zzav(this, zzaqVar));
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.zzc
    public final void zza(List<DataLayer.zza> list, long j) {
        ArrayList arrayList = new ArrayList();
        for (DataLayer.zza zzaVar : list) {
            arrayList.add(new zzay(zzaVar.zzbfe, zzae(zzaVar.mValue)));
        }
        this.zzjqr.execute(new zzau(this, arrayList, j));
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.zzc
    public final void zzlk(String str) {
        this.zzjqr.execute(new zzaw(this, str));
    }
}
