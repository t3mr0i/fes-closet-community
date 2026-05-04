package com.google.android.gms.tagmanager;

/* loaded from: classes.dex */
final class zzcb implements Runnable {
    private /* synthetic */ String zzbwj;
    private /* synthetic */ zzbz zzjrx;
    private /* synthetic */ long zzjry;
    private /* synthetic */ zzca zzjrz;

    zzcb(zzca zzcaVar, zzbz zzbzVar, long j, String str) {
        this.zzjrz = zzcaVar;
        this.zzjrx = zzbzVar;
        this.zzjry = j;
        this.zzbwj = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzjrz.zzjrw == null) {
            zzfo zzfoVarZzbfa = zzfo.zzbfa();
            zzfoVarZzbfa.zza(this.zzjrz.mContext, this.zzjrx);
            this.zzjrz.zzjrw = zzfoVarZzbfa.zzbfb();
        }
        this.zzjrz.zzjrw.zzb(this.zzjry, this.zzbwj);
    }
}
