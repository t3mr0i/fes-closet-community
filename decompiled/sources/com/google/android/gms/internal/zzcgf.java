package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcgf extends zzeha<zzcgf> {
    private static volatile zzcgf[] zziyq;
    public String key = null;
    public String value = null;

    public zzcgf() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzcgf[] zzbaf() {
        if (zziyq == null) {
            synchronized (zzehe.zzngo) {
                if (zziyq == null) {
                    zziyq = new zzcgf[0];
                }
            }
        }
        return zziyq;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgf)) {
            return false;
        }
        zzcgf zzcgfVar = (zzcgf) obj;
        if (this.key == null) {
            if (zzcgfVar.key != null) {
                return false;
            }
        } else if (!this.key.equals(zzcgfVar.key)) {
            return false;
        }
        if (this.value == null) {
            if (zzcgfVar.value != null) {
                return false;
            }
        } else if (!this.value.equals(zzcgfVar.value)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcgfVar.zzngg == null || zzcgfVar.zzngg.isEmpty() : this.zzngg.equals(zzcgfVar.zzngg);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((this.value == null ? 0 : this.value.hashCode()) + (((this.key == null ? 0 : this.key.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
        if (this.zzngg != null && !this.zzngg.isEmpty()) {
            iHashCode = this.zzngg.hashCode();
        }
        return iHashCode2 + iHashCode;
    }

    @Override // com.google.android.gms.internal.zzehg
    public final /* synthetic */ zzehg zza(zzegx zzegxVar) throws IOException {
        while (true) {
            int iZzcby = zzegxVar.zzcby();
            switch (iZzcby) {
                case 0:
                    break;
                case 10:
                    this.key = zzegxVar.readString();
                    break;
                case 18:
                    this.value = zzegxVar.readString();
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
        if (this.key != null) {
            zzegyVar.zzl(1, this.key);
        }
        if (this.value != null) {
            zzegyVar.zzl(2, this.value);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.key != null) {
            iZzn += zzegy.zzm(1, this.key);
        }
        return this.value != null ? iZzn + zzegy.zzm(2, this.value) : iZzn;
    }
}
