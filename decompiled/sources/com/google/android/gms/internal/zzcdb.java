package com.google.android.gms.internal;

import android.os.Binder;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/* loaded from: classes.dex */
public final class zzcdb extends zzcbp {
    private final zzccw zzikh;
    private Boolean zziug;

    @Nullable
    private String zziuh;

    public zzcdb(zzccw zzccwVar) {
        this(zzccwVar, null);
    }

    private zzcdb(zzccw zzccwVar, @Nullable String str) {
        com.google.android.gms.common.internal.zzbp.zzu(zzccwVar);
        this.zzikh = zzccwVar;
        this.zziuh = null;
    }

    @BinderThread
    private final void zzb(zzcas zzcasVar, boolean z) {
        com.google.android.gms.common.internal.zzbp.zzu(zzcasVar);
        zzf(zzcasVar.packageName, false);
        this.zzikh.zzaui().zzkb(zzcasVar.zzilt);
    }

    @BinderThread
    private final void zzf(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            this.zzikh.zzaum().zzaye().log("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        if (z) {
            try {
                if (this.zziug == null) {
                    this.zziug = Boolean.valueOf("com.google.android.gms".equals(this.zziuh) || com.google.android.gms.common.util.zzv.zzf(this.zzikh.getContext(), Binder.getCallingUid()) || com.google.android.gms.common.zzp.zzbz(this.zzikh.getContext()).zzbo(Binder.getCallingUid()));
                }
                if (this.zziug.booleanValue()) {
                    return;
                }
            } catch (SecurityException e) {
                this.zzikh.zzaum().zzaye().zzj("Measurement Service called with invalid calling package. appId", zzcbw.zzjf(str));
                throw e;
            }
        }
        if (this.zziuh == null && com.google.android.gms.common.zzo.zzb(this.zzikh.getContext(), Binder.getCallingUid(), str)) {
            this.zziuh = str;
        }
        if (str.equals(this.zziuh)) {
        } else {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", str));
        }
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final List<zzcft> zza(zzcas zzcasVar, boolean z) throws IllegalStateException {
        zzb(zzcasVar, false);
        try {
            List<zzcfv> list = (List) this.zzikh.zzaul().zzd(new zzcdq(this, zzcasVar)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzcfv zzcfvVar : list) {
                if (z || !zzcfw.zzkd(zzcfvVar.mName)) {
                    arrayList.add(new zzcft(zzcfvVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzikh.zzaum().zzaye().zze("Failed to get user attributes. appId", zzcbw.zzjf(zzcasVar.packageName), e);
            return null;
        }
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final List<zzcav> zza(String str, String str2, zzcas zzcasVar) {
        zzb(zzcasVar, false);
        try {
            return (List) this.zzikh.zzaul().zzd(new zzcdj(this, zzcasVar, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzikh.zzaum().zzaye().zzj("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final List<zzcft> zza(String str, String str2, String str3, boolean z) throws IllegalStateException {
        zzf(str, true);
        try {
            List<zzcfv> list = (List) this.zzikh.zzaul().zzd(new zzcdi(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzcfv zzcfvVar : list) {
                if (z || !zzcfw.zzkd(zzcfvVar.mName)) {
                    arrayList.add(new zzcft(zzcfvVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzikh.zzaum().zzaye().zze("Failed to get user attributes. appId", zzcbw.zzjf(str), e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final List<zzcft> zza(String str, String str2, boolean z, zzcas zzcasVar) throws IllegalStateException {
        zzb(zzcasVar, false);
        try {
            List<zzcfv> list = (List) this.zzikh.zzaul().zzd(new zzcdh(this, zzcasVar, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzcfv zzcfvVar : list) {
                if (z || !zzcfw.zzkd(zzcfvVar.mName)) {
                    arrayList.add(new zzcft(zzcfvVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzikh.zzaum().zzaye().zze("Failed to get user attributes. appId", zzcbw.zzjf(zzcasVar.packageName), e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final void zza(long j, String str, String str2, String str3) throws IllegalStateException {
        this.zzikh.zzaul().zzg(new zzcds(this, str2, str3, str, j));
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final void zza(zzcas zzcasVar) throws IllegalStateException {
        zzb(zzcasVar, false);
        zzcdr zzcdrVar = new zzcdr(this, zzcasVar);
        if (this.zzikh.zzaul().zzayt()) {
            zzcdrVar.run();
        } else {
            this.zzikh.zzaul().zzg(zzcdrVar);
        }
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final void zza(zzcav zzcavVar, zzcas zzcasVar) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzu(zzcavVar);
        com.google.android.gms.common.internal.zzbp.zzu(zzcavVar.zzimg);
        zzb(zzcasVar, false);
        zzcav zzcavVar2 = new zzcav(zzcavVar);
        zzcavVar2.packageName = zzcasVar.packageName;
        if (zzcavVar.zzimg.getValue() == null) {
            this.zzikh.zzaul().zzg(new zzcdd(this, zzcavVar2, zzcasVar));
        } else {
            this.zzikh.zzaul().zzg(new zzcde(this, zzcavVar2, zzcasVar));
        }
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final void zza(zzcbk zzcbkVar, zzcas zzcasVar) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzu(zzcbkVar);
        zzb(zzcasVar, false);
        this.zzikh.zzaul().zzg(new zzcdl(this, zzcbkVar, zzcasVar));
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final void zza(zzcbk zzcbkVar, String str, String str2) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzu(zzcbkVar);
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        zzf(str, true);
        this.zzikh.zzaul().zzg(new zzcdm(this, zzcbkVar, str));
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final void zza(zzcft zzcftVar, zzcas zzcasVar) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzu(zzcftVar);
        zzb(zzcasVar, false);
        if (zzcftVar.getValue() == null) {
            this.zzikh.zzaul().zzg(new zzcdo(this, zzcftVar, zzcasVar));
        } else {
            this.zzikh.zzaul().zzg(new zzcdp(this, zzcftVar, zzcasVar));
        }
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final byte[] zza(zzcbk zzcbkVar, String str) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzu(zzcbkVar);
        zzf(str, true);
        this.zzikh.zzaum().zzayj().zzj("Log and bundle. event", this.zzikh.zzauh().zzjc(zzcbkVar.name));
        long jNanoTime = this.zzikh.zzvx().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zzikh.zzaul().zze(new zzcdn(this, zzcbkVar, str)).get();
            if (bArr == null) {
                this.zzikh.zzaum().zzaye().zzj("Log and bundle returned null. appId", zzcbw.zzjf(str));
                bArr = new byte[0];
            }
            this.zzikh.zzaum().zzayj().zzd("Log and bundle processed. event, size, time_ms", this.zzikh.zzauh().zzjc(zzcbkVar.name), Integer.valueOf(bArr.length), Long.valueOf((this.zzikh.zzvx().nanoTime() / 1000000) - jNanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zzikh.zzaum().zzaye().zzd("Failed to log and bundle. appId, event, error", zzcbw.zzjf(str), this.zzikh.zzauh().zzjc(zzcbkVar.name), e);
            return null;
        }
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final void zzb(zzcas zzcasVar) throws IllegalStateException {
        zzb(zzcasVar, false);
        this.zzikh.zzaul().zzg(new zzcdc(this, zzcasVar));
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final void zzb(zzcav zzcavVar) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzu(zzcavVar);
        com.google.android.gms.common.internal.zzbp.zzu(zzcavVar.zzimg);
        zzf(zzcavVar.packageName, true);
        zzcav zzcavVar2 = new zzcav(zzcavVar);
        if (zzcavVar.zzimg.getValue() == null) {
            this.zzikh.zzaul().zzg(new zzcdf(this, zzcavVar2));
        } else {
            this.zzikh.zzaul().zzg(new zzcdg(this, zzcavVar2));
        }
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final String zzc(zzcas zzcasVar) {
        zzb(zzcasVar, false);
        return this.zzikh.zzjs(zzcasVar.packageName);
    }

    @Override // com.google.android.gms.internal.zzcbo
    @BinderThread
    public final List<zzcav> zzj(String str, String str2, String str3) {
        zzf(str, true);
        try {
            return (List) this.zzikh.zzaul().zzd(new zzcdk(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzikh.zzaum().zzaye().zzj("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }
}
