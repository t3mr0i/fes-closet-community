package com.google.android.gms.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
final class zze implements zzi {
    private /* synthetic */ Bundle zzaxb;
    private /* synthetic */ zza zzgox;
    private /* synthetic */ FrameLayout zzgoz;
    private /* synthetic */ LayoutInflater zzgpa;
    private /* synthetic */ ViewGroup zzgpb;

    zze(zza zzaVar, FrameLayout frameLayout, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.zzgox = zzaVar;
        this.zzgoz = frameLayout;
        this.zzgpa = layoutInflater;
        this.zzgpb = viewGroup;
        this.zzaxb = bundle;
    }

    @Override // com.google.android.gms.dynamic.zzi
    public final int getState() {
        return 2;
    }

    @Override // com.google.android.gms.dynamic.zzi
    public final void zzb(LifecycleDelegate lifecycleDelegate) {
        this.zzgoz.removeAllViews();
        this.zzgoz.addView(this.zzgox.zzgot.onCreateView(this.zzgpa, this.zzgpb, this.zzaxb));
    }
}
