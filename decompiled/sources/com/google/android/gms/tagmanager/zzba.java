package com.google.android.gms.tagmanager;

import android.util.Log;

/* loaded from: classes.dex */
public final class zzba implements zzdk {
    private int zzdqq = 5;

    @Override // com.google.android.gms.tagmanager.zzdk
    public final void e(String str) {
        if (this.zzdqq <= 6) {
            Log.e("GoogleTagManager", str);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdk
    public final void setLogLevel(int i) {
        this.zzdqq = i;
    }

    @Override // com.google.android.gms.tagmanager.zzdk
    public final void v(String str) {
        if (this.zzdqq <= 2) {
            Log.v("GoogleTagManager", str);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdk
    public final void zzb(String str, Throwable th) {
        if (this.zzdqq <= 6) {
            Log.e("GoogleTagManager", str, th);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdk
    public final void zzc(String str, Throwable th) {
        if (this.zzdqq <= 5) {
            Log.w("GoogleTagManager", str, th);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdk
    public final void zzca(String str) {
        if (this.zzdqq <= 3) {
            Log.d("GoogleTagManager", str);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdk
    public final void zzcq(String str) {
        if (this.zzdqq <= 4) {
            Log.i("GoogleTagManager", str);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdk
    public final void zzcr(String str) {
        if (this.zzdqq <= 5) {
            Log.w("GoogleTagManager", str);
        }
    }
}
