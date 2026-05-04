package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@KeepName
/* loaded from: classes.dex */
public final class DataHolder extends zzbck implements Closeable {
    public static final Parcelable.Creator<DataHolder> CREATOR = new zzf();
    private static final zza zzfqr = new zze(new String[0], null);
    private boolean mClosed;
    private int zzdxr;
    private final int zzfab;
    private final String[] zzfqk;
    private Bundle zzfql;
    private final CursorWindow[] zzfqm;
    private final Bundle zzfqn;
    private int[] zzfqo;
    int zzfqp;
    private boolean zzfqq;

    public static class zza {
        private final String[] zzfqk;
        private final ArrayList<HashMap<String, Object>> zzfqs;
        private final String zzfqt;
        private final HashMap<Object, Integer> zzfqu;
        private boolean zzfqv;
        private String zzfqw;

        private zza(String[] strArr, String str) {
            this.zzfqk = (String[]) zzbp.zzu(strArr);
            this.zzfqs = new ArrayList<>();
            this.zzfqt = str;
            this.zzfqu = new HashMap<>();
            this.zzfqv = false;
            this.zzfqw = null;
        }

        /* synthetic */ zza(String[] strArr, String str, zze zzeVar) {
            this(strArr, null);
        }

        public zza zza(ContentValues contentValues) {
            com.google.android.gms.common.internal.zzc.zzr(contentValues);
            HashMap<String, Object> map = new HashMap<>(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            return zza(map);
        }

        public zza zza(HashMap<String, Object> map) {
            Object obj;
            int iIntValue;
            com.google.android.gms.common.internal.zzc.zzr(map);
            if (this.zzfqt == null || (obj = map.get(this.zzfqt)) == null) {
                iIntValue = -1;
            } else {
                Integer num = this.zzfqu.get(obj);
                if (num == null) {
                    this.zzfqu.put(obj, Integer.valueOf(this.zzfqs.size()));
                    iIntValue = -1;
                } else {
                    iIntValue = num.intValue();
                }
            }
            if (iIntValue == -1) {
                this.zzfqs.add(map);
            } else {
                this.zzfqs.remove(iIntValue);
                this.zzfqs.add(iIntValue, map);
            }
            this.zzfqv = false;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final DataHolder zzbz(int i) {
            return new DataHolder(this, 0, (Bundle) null, (zze) (0 == true ? 1 : 0));
        }
    }

    public static class zzb extends RuntimeException {
        public zzb(String str) {
            super(str);
        }
    }

    DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.mClosed = false;
        this.zzfqq = true;
        this.zzdxr = i;
        this.zzfqk = strArr;
        this.zzfqm = cursorWindowArr;
        this.zzfab = i2;
        this.zzfqn = bundle;
    }

    private DataHolder(zza zzaVar, int i, Bundle bundle) {
        this(zzaVar.zzfqk, zza(zzaVar, -1), i, (Bundle) null);
    }

    /* synthetic */ DataHolder(zza zzaVar, int i, Bundle bundle, zze zzeVar) {
        this(zzaVar, 0, null);
    }

    private DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.mClosed = false;
        this.zzfqq = true;
        this.zzdxr = 1;
        this.zzfqk = (String[]) zzbp.zzu(strArr);
        this.zzfqm = (CursorWindow[]) zzbp.zzu(cursorWindowArr);
        this.zzfab = i;
        this.zzfqn = bundle;
        zzaiw();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static zza zza(String[] strArr) {
        return new zza(strArr, null, 0 == true ? 1 : 0);
    }

    private static CursorWindow[] zza(zza zzaVar, int i) {
        int i2;
        boolean z;
        if (zzaVar.zzfqk.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList arrayList = zzaVar.zzfqs;
        int size = arrayList.size();
        CursorWindow cursorWindow = new CursorWindow(false);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(cursorWindow);
        cursorWindow.setNumColumns(zzaVar.zzfqk.length);
        int i3 = 0;
        boolean z2 = false;
        while (i3 < size) {
            try {
                if (!cursorWindow.allocRow()) {
                    Log.d("DataHolder", new StringBuilder(72).append("Allocating additional cursor window for large data set (row ").append(i3).append(")").toString());
                    cursorWindow = new CursorWindow(false);
                    cursorWindow.setStartPosition(i3);
                    cursorWindow.setNumColumns(zzaVar.zzfqk.length);
                    arrayList2.add(cursorWindow);
                    if (!cursorWindow.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList2.remove(cursorWindow);
                        return (CursorWindow[]) arrayList2.toArray(new CursorWindow[arrayList2.size()]);
                    }
                }
                Map map = (Map) arrayList.get(i3);
                boolean zPutDouble = true;
                for (int i4 = 0; i4 < zzaVar.zzfqk.length && zPutDouble; i4++) {
                    String str = zzaVar.zzfqk[i4];
                    Object obj = map.get(str);
                    if (obj == null) {
                        zPutDouble = cursorWindow.putNull(i3, i4);
                    } else if (obj instanceof String) {
                        zPutDouble = cursorWindow.putString((String) obj, i3, i4);
                    } else if (obj instanceof Long) {
                        zPutDouble = cursorWindow.putLong(((Long) obj).longValue(), i3, i4);
                    } else if (obj instanceof Integer) {
                        zPutDouble = cursorWindow.putLong(((Integer) obj).intValue(), i3, i4);
                    } else if (obj instanceof Boolean) {
                        zPutDouble = cursorWindow.putLong(((Boolean) obj).booleanValue() ? 1L : 0L, i3, i4);
                    } else if (obj instanceof byte[]) {
                        zPutDouble = cursorWindow.putBlob((byte[]) obj, i3, i4);
                    } else if (obj instanceof Double) {
                        zPutDouble = cursorWindow.putDouble(((Double) obj).doubleValue(), i3, i4);
                    } else {
                        if (!(obj instanceof Float)) {
                            String strValueOf = String.valueOf(obj);
                            throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 32 + String.valueOf(strValueOf).length()).append("Unsupported object for column ").append(str).append(": ").append(strValueOf).toString());
                        }
                        zPutDouble = cursorWindow.putDouble(((Float) obj).floatValue(), i3, i4);
                    }
                }
                if (zPutDouble) {
                    i2 = i3;
                    z = false;
                } else {
                    if (z2) {
                        throw new zzb("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                    }
                    Log.d("DataHolder", new StringBuilder(74).append("Couldn't populate window data for row ").append(i3).append(" - allocating new window.").toString());
                    cursorWindow.freeLastRow();
                    cursorWindow = new CursorWindow(false);
                    cursorWindow.setStartPosition(i3);
                    cursorWindow.setNumColumns(zzaVar.zzfqk.length);
                    arrayList2.add(cursorWindow);
                    i2 = i3 - 1;
                    z = true;
                }
                i3 = i2 + 1;
                z2 = z;
            } catch (RuntimeException e) {
                int size2 = arrayList2.size();
                for (int i5 = 0; i5 < size2; i5++) {
                    ((CursorWindow) arrayList2.get(i5)).close();
                }
                throw e;
            }
        }
        return (CursorWindow[]) arrayList2.toArray(new CursorWindow[arrayList2.size()]);
    }

    public static DataHolder zzby(int i) {
        return new DataHolder(zzfqr, i, null);
    }

    private final void zzh(String str, int i) {
        if (this.zzfql == null || !this.zzfql.containsKey(str)) {
            String strValueOf = String.valueOf(str);
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "No such column: ".concat(strValueOf) : new String("No such column: "));
        }
        if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (i < 0 || i >= this.zzfqp) {
            throw new CursorIndexOutOfBoundsException(i, this.zzfqp);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (int i = 0; i < this.zzfqm.length; i++) {
                    this.zzfqm[i].close();
                }
            }
        }
    }

    protected final void finalize() throws Throwable {
        try {
            if (this.zzfqq && this.zzfqm.length > 0 && !isClosed()) {
                close();
                String string = toString();
                Log.e("DataBuffer", new StringBuilder(String.valueOf(string).length() + 178).append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ").append(string).append(")").toString());
            }
        } finally {
            super.finalize();
        }
    }

    public final int getCount() {
        return this.zzfqp;
    }

    public final int getStatusCode() {
        return this.zzfab;
    }

    public final boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 1, this.zzfqk, false);
        zzbcn.zza(parcel, 2, (Parcelable[]) this.zzfqm, i, false);
        zzbcn.zzc(parcel, 3, this.zzfab);
        zzbcn.zza(parcel, 4, this.zzfqn, false);
        zzbcn.zzc(parcel, 1000, this.zzdxr);
        zzbcn.zzai(parcel, iZze);
        if ((i & 1) != 0) {
            close();
        }
    }

    public final void zza(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zzh(str, i);
        this.zzfqm[i2].copyStringToBuffer(i, this.zzfql.getInt(str), charArrayBuffer);
    }

    public final Bundle zzafi() {
        return this.zzfqn;
    }

    public final void zzaiw() {
        this.zzfql = new Bundle();
        for (int i = 0; i < this.zzfqk.length; i++) {
            this.zzfql.putInt(this.zzfqk[i], i);
        }
        this.zzfqo = new int[this.zzfqm.length];
        int numRows = 0;
        for (int i2 = 0; i2 < this.zzfqm.length; i2++) {
            this.zzfqo[i2] = numRows;
            numRows += this.zzfqm[i2].getNumRows() - (numRows - this.zzfqm[i2].getStartPosition());
        }
        this.zzfqp = numRows;
    }

    public final long zzb(String str, int i, int i2) {
        zzh(str, i);
        return this.zzfqm[i2].getLong(i, this.zzfql.getInt(str));
    }

    public final int zzbx(int i) {
        int i2 = 0;
        zzbp.zzbg(i >= 0 && i < this.zzfqp);
        while (true) {
            if (i2 >= this.zzfqo.length) {
                break;
            }
            if (i < this.zzfqo[i2]) {
                i2--;
                break;
            }
            i2++;
        }
        return i2 == this.zzfqo.length ? i2 - 1 : i2;
    }

    public final int zzc(String str, int i, int i2) {
        zzh(str, i);
        return this.zzfqm[i2].getInt(i, this.zzfql.getInt(str));
    }

    public final String zzd(String str, int i, int i2) {
        zzh(str, i);
        return this.zzfqm[i2].getString(i, this.zzfql.getInt(str));
    }

    public final boolean zze(String str, int i, int i2) {
        zzh(str, i);
        return Long.valueOf(this.zzfqm[i2].getLong(i, this.zzfql.getInt(str))).longValue() == 1;
    }

    public final float zzf(String str, int i, int i2) {
        zzh(str, i);
        return this.zzfqm[i2].getFloat(i, this.zzfql.getInt(str));
    }

    public final boolean zzfu(String str) {
        return this.zzfql.containsKey(str);
    }

    public final byte[] zzg(String str, int i, int i2) {
        zzh(str, i);
        return this.zzfqm[i2].getBlob(i, this.zzfql.getInt(str));
    }

    public final boolean zzh(String str, int i, int i2) {
        zzh(str, i);
        return this.zzfqm[i2].isNull(i, this.zzfql.getInt(str));
    }
}
