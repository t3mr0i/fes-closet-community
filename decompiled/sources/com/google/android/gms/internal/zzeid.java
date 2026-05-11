package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzeid extends zzeha<zzeid> {
    private static volatile zzeid[] zznkg;
    public String zznkh = "";

    public zzeid() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzeid[] zzcfa() {
        if (zznkg == null) {
            synchronized (zzehe.zzngo) {
                if (zznkg == null) {
                    zznkg = new zzeid[0];
                }
            }
        }
        return zznkg;
    }

    @Override // com.google.android.gms.internal.zzehg
    public final /* synthetic */ zzehg zza(zzegx zzegxVar) throws IOException {
        while (true) {
            int iZzcby = zzegxVar.zzcby();
            switch (iZzcby) {
                case 0:
                    break;
                case 10:
                    this.zznkh = zzegxVar.readString();
                    break;
                default:
                    if (!super.zza(zzegxVar, iZzcby)) {
                        break;
                    } else {
                        break;
                    }
            }
        }
        return this;
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    public final void zza(zzegy zzegyVar) throws IOException {
        if (this.zznkh != null && !this.zznkh.equals("")) {
            zzegyVar.zzl(1, this.zznkh);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        return (this.zznkh == null || this.zznkh.equals("")) ? iZzn : iZzn + zzegy.zzm(1, this.zznkh);
    }
}
