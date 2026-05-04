package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzcbg {
    final String mAppId;
    final String mName;
    final long zzink;
    final long zzinl;
    final long zzinm;

    zzcbg(String str, String str2, long j, long j2, long j3) {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzgg(str2);
        com.google.android.gms.common.internal.zzbp.zzbh(j >= 0);
        com.google.android.gms.common.internal.zzbp.zzbh(j2 >= 0);
        this.mAppId = str;
        this.mName = str2;
        this.zzink = j;
        this.zzinl = j2;
        this.zzinm = j3;
    }

    final zzcbg zzaxz() {
        return new zzcbg(this.mAppId, this.mName, this.zzink + 1, this.zzinl + 1, this.zzinm);
    }

    final zzcbg zzbb(long j) {
        return new zzcbg(this.mAppId, this.mName, this.zzink, this.zzinl, j);
    }
}
