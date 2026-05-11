package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzcav extends zzbck {
    public static final Parcelable.Creator<zzcav> CREATOR = new zzcaw();
    public String packageName;
    private int versionCode;
    public String zzimf;
    public zzcft zzimg;
    public long zzimh;
    public boolean zzimi;
    public String zzimj;
    public zzcbk zzimk;
    public long zziml;
    public zzcbk zzimm;
    public long zzimn;
    public zzcbk zzimo;

    zzcav(int i, String str, String str2, zzcft zzcftVar, long j, boolean z, String str3, zzcbk zzcbkVar, long j2, zzcbk zzcbkVar2, long j3, zzcbk zzcbkVar3) {
        this.versionCode = i;
        this.packageName = str;
        this.zzimf = str2;
        this.zzimg = zzcftVar;
        this.zzimh = j;
        this.zzimi = z;
        this.zzimj = str3;
        this.zzimk = zzcbkVar;
        this.zziml = j2;
        this.zzimm = zzcbkVar2;
        this.zzimn = j3;
        this.zzimo = zzcbkVar3;
    }

    zzcav(zzcav zzcavVar) {
        this.versionCode = 1;
        com.google.android.gms.common.internal.zzbp.zzu(zzcavVar);
        this.packageName = zzcavVar.packageName;
        this.zzimf = zzcavVar.zzimf;
        this.zzimg = zzcavVar.zzimg;
        this.zzimh = zzcavVar.zzimh;
        this.zzimi = zzcavVar.zzimi;
        this.zzimj = zzcavVar.zzimj;
        this.zzimk = zzcavVar.zzimk;
        this.zziml = zzcavVar.zziml;
        this.zzimm = zzcavVar.zzimm;
        this.zzimn = zzcavVar.zzimn;
        this.zzimo = zzcavVar.zzimo;
    }

    zzcav(String str, String str2, zzcft zzcftVar, long j, boolean z, String str3, zzcbk zzcbkVar, long j2, zzcbk zzcbkVar2, long j3, zzcbk zzcbkVar3) {
        this.versionCode = 1;
        this.packageName = str;
        this.zzimf = str2;
        this.zzimg = zzcftVar;
        this.zzimh = j;
        this.zzimi = z;
        this.zzimj = str3;
        this.zzimk = zzcbkVar;
        this.zziml = j2;
        this.zzimm = zzcbkVar2;
        this.zzimn = j3;
        this.zzimo = zzcbkVar3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.versionCode);
        zzbcn.zza(parcel, 2, this.packageName, false);
        zzbcn.zza(parcel, 3, this.zzimf, false);
        zzbcn.zza(parcel, 4, (Parcelable) this.zzimg, i, false);
        zzbcn.zza(parcel, 5, this.zzimh);
        zzbcn.zza(parcel, 6, this.zzimi);
        zzbcn.zza(parcel, 7, this.zzimj, false);
        zzbcn.zza(parcel, 8, (Parcelable) this.zzimk, i, false);
        zzbcn.zza(parcel, 9, this.zziml);
        zzbcn.zza(parcel, 10, (Parcelable) this.zzimm, i, false);
        zzbcn.zza(parcel, 11, this.zzimn);
        zzbcn.zza(parcel, 12, (Parcelable) this.zzimo, i, false);
        zzbcn.zzai(parcel, iZze);
    }
}
