package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzcef implements Runnable {
    private /* synthetic */ String val$name;
    private /* synthetic */ String zziul;
    private /* synthetic */ zzcdw zziux;
    private /* synthetic */ long zzivc;
    private /* synthetic */ Object zzivh;

    zzcef(zzcdw zzcdwVar, String str, String str2, Object obj, long j) {
        this.zziux = zzcdwVar;
        this.zziul = str;
        this.val$name = str2;
        this.zzivh = obj;
        this.zzivc = j;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException {
        this.zziux.zza(this.zziul, this.val$name, this.zzivh, this.zzivc);
    }
}
