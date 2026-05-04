package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class zzcmw extends zzbck {
    public static final Parcelable.Creator<zzcmw> CREATOR = new zzcnc();
    private static byte[][] zzfdq = new byte[0][];
    private static zzcmw zzjij = new zzcmw("", null, zzfdq, zzfdq, zzfdq, zzfdq, null, null);
    private static final zzcnb zzjis = new zzcmx();
    private static final zzcnb zzjit = new zzcmy();
    private static final zzcnb zzjiu = new zzcmz();
    private static final zzcnb zzjiv = new zzcna();
    private String zzjik;
    private byte[] zzjil;
    private byte[][] zzjim;
    private byte[][] zzjin;
    private byte[][] zzjio;
    private byte[][] zzjip;
    private int[] zzjiq;
    private byte[][] zzjir;

    public zzcmw(String str, byte[] bArr, byte[][] bArr2, byte[][] bArr3, byte[][] bArr4, byte[][] bArr5, int[] iArr, byte[][] bArr6) {
        this.zzjik = str;
        this.zzjil = bArr;
        this.zzjim = bArr2;
        this.zzjin = bArr3;
        this.zzjio = bArr4;
        this.zzjip = bArr5;
        this.zzjiq = iArr;
        this.zzjir = bArr6;
    }

    private static void zza(StringBuilder sb, String str, int[] iArr) {
        sb.append(str);
        sb.append("=");
        if (iArr == null) {
            sb.append("null");
            return;
        }
        boolean z = true;
        sb.append("(");
        int length = iArr.length;
        int i = 0;
        while (i < length) {
            int i2 = iArr[i];
            if (!z) {
                sb.append(", ");
            }
            sb.append(i2);
            i++;
            z = false;
        }
        sb.append(")");
    }

    private static void zza(StringBuilder sb, String str, byte[][] bArr) {
        sb.append(str);
        sb.append("=");
        if (bArr == null) {
            sb.append("null");
            return;
        }
        boolean z = true;
        sb.append("(");
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            byte[] bArr2 = bArr[i];
            if (!z) {
                sb.append(", ");
            }
            sb.append("'");
            sb.append(Base64.encodeToString(bArr2, 3));
            sb.append("'");
            i++;
            z = false;
        }
        sb.append(")");
    }

    private static List<String> zzb(byte[][] bArr) {
        if (bArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte[] bArr2 : bArr) {
            arrayList.add(Base64.encodeToString(bArr2, 3));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static List<Integer> zze(int[] iArr) {
        if (iArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(i));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzcmw)) {
            return false;
        }
        zzcmw zzcmwVar = (zzcmw) obj;
        return zzcnd.equals(this.zzjik, zzcmwVar.zzjik) && Arrays.equals(this.zzjil, zzcmwVar.zzjil) && zzcnd.equals(zzb(this.zzjim), zzb(zzcmwVar.zzjim)) && zzcnd.equals(zzb(this.zzjin), zzb(zzcmwVar.zzjin)) && zzcnd.equals(zzb(this.zzjio), zzb(zzcmwVar.zzjio)) && zzcnd.equals(zzb(this.zzjip), zzb(zzcmwVar.zzjip)) && zzcnd.equals(zze(this.zzjiq), zze(zzcmwVar.zzjiq)) && zzcnd.equals(zzb(this.zzjir), zzb(zzcmwVar.zzjir));
    }

    public final String toString() {
        String string;
        StringBuilder sb = new StringBuilder("ExperimentTokens");
        sb.append("(");
        if (this.zzjik == null) {
            string = "null";
        } else {
            String str = this.zzjik;
            string = new StringBuilder(String.valueOf("'").length() + String.valueOf(str).length() + String.valueOf("'").length()).append("'").append(str).append("'").toString();
        }
        sb.append(string);
        sb.append(", ");
        byte[] bArr = this.zzjil;
        sb.append("direct");
        sb.append("=");
        if (bArr == null) {
            sb.append("null");
        } else {
            sb.append("'");
            sb.append(Base64.encodeToString(bArr, 3));
            sb.append("'");
        }
        sb.append(", ");
        zza(sb, "GAIA", this.zzjim);
        sb.append(", ");
        zza(sb, "PSEUDO", this.zzjin);
        sb.append(", ");
        zza(sb, "ALWAYS", this.zzjio);
        sb.append(", ");
        zza(sb, "OTHER", this.zzjip);
        sb.append(", ");
        zza(sb, "weak", this.zzjiq);
        sb.append(", ");
        zza(sb, "directs", this.zzjir);
        sb.append(")");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 2, this.zzjik, false);
        zzbcn.zza(parcel, 3, this.zzjil, false);
        zzbcn.zza(parcel, 4, this.zzjim, false);
        zzbcn.zza(parcel, 5, this.zzjin, false);
        zzbcn.zza(parcel, 6, this.zzjio, false);
        zzbcn.zza(parcel, 7, this.zzjip, false);
        zzbcn.zza(parcel, 8, this.zzjiq, false);
        zzbcn.zza(parcel, 9, this.zzjir, false);
        zzbcn.zzai(parcel, iZze);
    }
}
