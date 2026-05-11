package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzbk extends zzeha<zzbk> {
    private static volatile zzbk[] zzwh;
    public int key = 0;
    public int value = 0;

    public zzbk() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzbk[] zzr() {
        if (zzwh == null) {
            synchronized (zzehe.zzngo) {
                if (zzwh == null) {
                    zzwh = new zzbk[0];
                }
            }
        }
        return zzwh;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbk)) {
            return false;
        }
        zzbk zzbkVar = (zzbk) obj;
        if (this.key == zzbkVar.key && this.value == zzbkVar.value) {
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzbkVar.zzngg == null || zzbkVar.zzngg.isEmpty() : this.zzngg.equals(zzbkVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zzngg == null || this.zzngg.isEmpty()) ? 0 : this.zzngg.hashCode()) + ((((((getClass().getName().hashCode() + 527) * 31) + this.key) * 31) + this.value) * 31);
    }

    @Override // com.google.android.gms.internal.zzehg
    public final /* synthetic */ zzehg zza(zzegx zzegxVar) throws IOException {
        while (true) {
            int iZzcby = zzegxVar.zzcby();
            switch (iZzcby) {
                case 0:
                    break;
                case 8:
                    this.key = zzegxVar.zzccj();
                    break;
                case 16:
                    this.value = zzegxVar.zzccj();
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
        zzegyVar.zzv(1, this.key);
        zzegyVar.zzv(2, this.value);
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        return super.zzn() + zzegy.zzaf(1, this.key) + zzegy.zzaf(2, this.value);
    }
}
