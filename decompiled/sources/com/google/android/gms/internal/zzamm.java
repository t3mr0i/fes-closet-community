package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzamm implements Runnable {
    private /* synthetic */ zzamj zzdnw;
    private /* synthetic */ String zzdny;
    private /* synthetic */ Runnable zzdnz;

    zzamm(zzamj zzamjVar, String str, Runnable runnable) {
        this.zzdnw = zzamjVar;
        this.zzdny = str;
        this.zzdnz = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzdnw.zzdnu.zzdu(this.zzdny);
        if (this.zzdnz != null) {
            this.zzdnz.run();
        }
    }
}
