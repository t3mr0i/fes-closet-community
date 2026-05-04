package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzbdm<I, O> extends zzbck {
    public static final zzbdp CREATOR = new zzbdp();
    private final int zzdxr;
    protected final int zzfwp;
    protected final boolean zzfwq;
    protected final int zzfwr;
    protected final boolean zzfws;
    protected final String zzfwt;
    protected final int zzfwu;
    protected final Class<? extends zzbdl> zzfwv;
    private String zzfww;
    private zzbdr zzfwx;
    private zzbdn<I, O> zzfwy;

    zzbdm(int i, int i2, boolean z, int i3, boolean z2, String str, int i4, String str2, zzbdf zzbdfVar) {
        this.zzdxr = i;
        this.zzfwp = i2;
        this.zzfwq = z;
        this.zzfwr = i3;
        this.zzfws = z2;
        this.zzfwt = str;
        this.zzfwu = i4;
        if (str2 == null) {
            this.zzfwv = null;
            this.zzfww = null;
        } else {
            this.zzfwv = zzbdw.class;
            this.zzfww = str2;
        }
        if (zzbdfVar == null) {
            this.zzfwy = null;
        } else {
            this.zzfwy = (zzbdn<I, O>) zzbdfVar.zzakq();
        }
    }

    private zzbdm(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends zzbdl> cls, zzbdn<I, O> zzbdnVar) {
        this.zzdxr = 1;
        this.zzfwp = i;
        this.zzfwq = z;
        this.zzfwr = i2;
        this.zzfws = z2;
        this.zzfwt = str;
        this.zzfwu = i3;
        this.zzfwv = cls;
        if (cls == null) {
            this.zzfww = null;
        } else {
            this.zzfww = cls.getCanonicalName();
        }
        this.zzfwy = zzbdnVar;
    }

    public static zzbdm zza(String str, int i, zzbdn<?, ?> zzbdnVar, boolean z) {
        return new zzbdm(7, false, 0, false, str, i, null, zzbdnVar);
    }

    public static <T extends zzbdl> zzbdm<T, T> zza(String str, int i, Class<T> cls) {
        return new zzbdm<>(11, false, 11, false, str, i, cls, null);
    }

    private String zzaks() {
        if (this.zzfww == null) {
            return null;
        }
        return this.zzfww;
    }

    public static <T extends zzbdl> zzbdm<ArrayList<T>, ArrayList<T>> zzb(String str, int i, Class<T> cls) {
        return new zzbdm<>(11, true, 11, true, str, i, cls, null);
    }

    public static zzbdm<Integer, Integer> zzj(String str, int i) {
        return new zzbdm<>(0, false, 0, false, str, i, null, null);
    }

    public static zzbdm<Boolean, Boolean> zzk(String str, int i) {
        return new zzbdm<>(6, false, 6, false, str, i, null, null);
    }

    public static zzbdm<String, String> zzl(String str, int i) {
        return new zzbdm<>(7, false, 7, false, str, i, null, null);
    }

    public static zzbdm<ArrayList<String>, ArrayList<String>> zzm(String str, int i) {
        return new zzbdm<>(7, true, 7, true, str, i, null, null);
    }

    public static zzbdm<byte[], byte[]> zzn(String str, int i) {
        return new zzbdm<>(8, false, 8, false, str, 4, null, null);
    }

    public final I convertBack(O o) {
        return this.zzfwy.convertBack(o);
    }

    public final String toString() {
        com.google.android.gms.common.internal.zzbh zzbhVarZzg = com.google.android.gms.common.internal.zzbf.zzt(this).zzg("versionCode", Integer.valueOf(this.zzdxr)).zzg("typeIn", Integer.valueOf(this.zzfwp)).zzg("typeInArray", Boolean.valueOf(this.zzfwq)).zzg("typeOut", Integer.valueOf(this.zzfwr)).zzg("typeOutArray", Boolean.valueOf(this.zzfws)).zzg("outputFieldName", this.zzfwt).zzg("safeParcelFieldId", Integer.valueOf(this.zzfwu)).zzg("concreteTypeName", zzaks());
        Class<? extends zzbdl> cls = this.zzfwv;
        if (cls != null) {
            zzbhVarZzg.zzg("concreteType.class", cls.getCanonicalName());
        }
        if (this.zzfwy != null) {
            zzbhVarZzg.zzg("converterName", this.zzfwy.getClass().getCanonicalName());
        }
        return zzbhVarZzg.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxr);
        zzbcn.zzc(parcel, 2, this.zzfwp);
        zzbcn.zza(parcel, 3, this.zzfwq);
        zzbcn.zzc(parcel, 4, this.zzfwr);
        zzbcn.zza(parcel, 5, this.zzfws);
        zzbcn.zza(parcel, 6, this.zzfwt, false);
        zzbcn.zzc(parcel, 7, this.zzfwu);
        zzbcn.zza(parcel, 8, zzaks(), false);
        zzbcn.zza(parcel, 9, (Parcelable) (this.zzfwy == null ? null : zzbdf.zza(this.zzfwy)), i, false);
        zzbcn.zzai(parcel, iZze);
    }

    public final void zza(zzbdr zzbdrVar) {
        this.zzfwx = zzbdrVar;
    }

    public final int zzakr() {
        return this.zzfwu;
    }

    public final boolean zzakt() {
        return this.zzfwy != null;
    }

    public final Map<String, zzbdm<?, ?>> zzaku() {
        com.google.android.gms.common.internal.zzbp.zzu(this.zzfww);
        com.google.android.gms.common.internal.zzbp.zzu(this.zzfwx);
        return this.zzfwx.zzgk(this.zzfww);
    }
}
