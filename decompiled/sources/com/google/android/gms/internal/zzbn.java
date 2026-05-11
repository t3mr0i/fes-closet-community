package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzbn extends zzeha<zzbn> {
    private static volatile zzbn[] zzxs;
    public String name = "";
    private zzbp zzxt = null;
    public zzbj zzxu = null;

    public zzbn() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzbn[] zzt() {
        if (zzxs == null) {
            synchronized (zzehe.zzngo) {
                if (zzxs == null) {
                    zzxs = new zzbn[0];
                }
            }
        }
        return zzxs;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbn)) {
            return false;
        }
        zzbn zzbnVar = (zzbn) obj;
        if (this.name == null) {
            if (zzbnVar.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzbnVar.name)) {
            return false;
        }
        if (this.zzxt == null) {
            if (zzbnVar.zzxt != null) {
                return false;
            }
        } else if (!this.zzxt.equals(zzbnVar.zzxt)) {
            return false;
        }
        if (this.zzxu == null) {
            if (zzbnVar.zzxu != null) {
                return false;
            }
        } else if (!this.zzxu.equals(zzbnVar.zzxu)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzbnVar.zzngg == null || zzbnVar.zzngg.isEmpty() : this.zzngg.equals(zzbnVar.zzngg);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31);
        zzbp zzbpVar = this.zzxt;
        int i = iHashCode2 * 31;
        int iHashCode3 = zzbpVar == null ? 0 : zzbpVar.hashCode();
        zzbj zzbjVar = this.zzxu;
        int iHashCode4 = ((zzbjVar == null ? 0 : zzbjVar.hashCode()) + ((iHashCode3 + i) * 31)) * 31;
        if (this.zzngg != null && !this.zzngg.isEmpty()) {
            iHashCode = this.zzngg.hashCode();
        }
        return iHashCode4 + iHashCode;
    }

    @Override // com.google.android.gms.internal.zzehg
    public final /* synthetic */ zzehg zza(zzegx zzegxVar) throws IOException {
        while (true) {
            int iZzcby = zzegxVar.zzcby();
            switch (iZzcby) {
                case 0:
                    break;
                case 10:
                    this.name = zzegxVar.readString();
                    break;
                case 18:
                    if (this.zzxt == null) {
                        this.zzxt = new zzbp();
                    }
                    zzegxVar.zza(this.zzxt);
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    if (this.zzxu == null) {
                        this.zzxu = new zzbj();
                    }
                    zzegxVar.zza(this.zzxu);
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
        if (this.name != null && !this.name.equals("")) {
            zzegyVar.zzl(1, this.name);
        }
        if (this.zzxt != null) {
            zzegyVar.zza(2, this.zzxt);
        }
        if (this.zzxu != null) {
            zzegyVar.zza(3, this.zzxu);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.name != null && !this.name.equals("")) {
            iZzn += zzegy.zzm(1, this.name);
        }
        if (this.zzxt != null) {
            iZzn += zzegy.zzb(2, this.zzxt);
        }
        return this.zzxu != null ? iZzn + zzegy.zzb(3, this.zzxu) : iZzn;
    }
}
