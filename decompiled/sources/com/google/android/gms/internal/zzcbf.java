package com.google.android.gms.internal;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class zzcbf {
    final String mAppId;
    final String mName;
    private String mOrigin;
    final long zzfdb;
    final long zzini;
    final zzcbh zzinj;

    zzcbf(zzccw zzccwVar, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        com.google.android.gms.common.internal.zzbp.zzgg(str2);
        com.google.android.gms.common.internal.zzbp.zzgg(str3);
        this.mAppId = str2;
        this.mName = str3;
        this.mOrigin = TextUtils.isEmpty(str) ? null : str;
        this.zzfdb = j;
        this.zzini = j2;
        if (this.zzini != 0 && this.zzini > this.zzfdb) {
            zzccwVar.zzaum().zzayg().zzj("Event created with reverse previous/current timestamps. appId", zzcbw.zzjf(str2));
        }
        this.zzinj = zza(zzccwVar, bundle);
    }

    private zzcbf(zzccw zzccwVar, String str, String str2, String str3, long j, long j2, zzcbh zzcbhVar) {
        com.google.android.gms.common.internal.zzbp.zzgg(str2);
        com.google.android.gms.common.internal.zzbp.zzgg(str3);
        com.google.android.gms.common.internal.zzbp.zzu(zzcbhVar);
        this.mAppId = str2;
        this.mName = str3;
        this.mOrigin = TextUtils.isEmpty(str) ? null : str;
        this.zzfdb = j;
        this.zzini = j2;
        if (this.zzini != 0 && this.zzini > this.zzfdb) {
            zzccwVar.zzaum().zzayg().zzj("Event created with reverse previous/current timestamps. appId", zzcbw.zzjf(str2));
        }
        this.zzinj = zzcbhVar;
    }

    private static zzcbh zza(zzccw zzccwVar, Bundle bundle) throws IllegalStateException {
        if (bundle == null || bundle.isEmpty()) {
            return new zzcbh(new Bundle());
        }
        Bundle bundle2 = new Bundle(bundle);
        Iterator<String> it = bundle2.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next == null) {
                zzccwVar.zzaum().zzaye().log("Param name can't be null");
                it.remove();
            } else {
                Object objZzk = zzccwVar.zzaui().zzk(next, bundle2.get(next));
                if (objZzk == null) {
                    zzccwVar.zzaum().zzayg().zzj("Param value can't be null", zzccwVar.zzauh().zzjd(next));
                    it.remove();
                } else {
                    zzccwVar.zzaui().zza(bundle2, next, objZzk);
                }
            }
        }
        return new zzcbh(bundle2);
    }

    public final String toString() {
        String str = this.mAppId;
        String str2 = this.mName;
        String strValueOf = String.valueOf(this.zzinj);
        return new StringBuilder(String.valueOf(str).length() + 33 + String.valueOf(str2).length() + String.valueOf(strValueOf).length()).append("Event{appId='").append(str).append("', name='").append(str2).append("', params=").append(strValueOf).append("}").toString();
    }

    final zzcbf zza(zzccw zzccwVar, long j) {
        return new zzcbf(zzccwVar, this.mOrigin, this.mAppId, this.mName, this.zzfdb, j, this.zzinj);
    }
}
