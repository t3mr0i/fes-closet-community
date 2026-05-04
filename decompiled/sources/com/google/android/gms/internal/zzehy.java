package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzehy extends zzeha<zzehy> implements Cloneable {
    private int zzime = 0;
    private String zznjf = "";
    private String version = "";

    public zzehy() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    /* renamed from: zzceu, reason: merged with bridge method [inline-methods] */
    public zzehy clone() {
        try {
            return (zzehy) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzehy)) {
            return false;
        }
        zzehy zzehyVar = (zzehy) obj;
        if (this.zzime != zzehyVar.zzime) {
            return false;
        }
        if (this.zznjf == null) {
            if (zzehyVar.zznjf != null) {
                return false;
            }
        } else if (!this.zznjf.equals(zzehyVar.zznjf)) {
            return false;
        }
        if (this.version == null) {
            if (zzehyVar.version != null) {
                return false;
            }
        } else if (!this.version.equals(zzehyVar.version)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzehyVar.zzngg == null || zzehyVar.zzngg.isEmpty() : this.zzngg.equals(zzehyVar.zzngg);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((this.version == null ? 0 : this.version.hashCode()) + (((this.zznjf == null ? 0 : this.zznjf.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.zzime) * 31)) * 31)) * 31;
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
                case 8:
                    this.zzime = zzegxVar.zzcdz();
                    break;
                case 18:
                    this.zznjf = zzegxVar.readString();
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    this.version = zzegxVar.readString();
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
        if (this.zzime != 0) {
            zzegyVar.zzv(1, this.zzime);
        }
        if (this.zznjf != null && !this.zznjf.equals("")) {
            zzegyVar.zzl(2, this.zznjf);
        }
        if (this.version != null && !this.version.equals("")) {
            zzegyVar.zzl(3, this.version);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha
    /* renamed from: zzceh */
    public final /* synthetic */ zzeha clone() throws CloneNotSupportedException {
        return (zzehy) clone();
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    /* renamed from: zzcei */
    public final /* synthetic */ zzehg clone() throws CloneNotSupportedException {
        return (zzehy) clone();
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zzime != 0) {
            iZzn += zzegy.zzaf(1, this.zzime);
        }
        if (this.zznjf != null && !this.zznjf.equals("")) {
            iZzn += zzegy.zzm(2, this.zznjf);
        }
        return (this.version == null || this.version.equals("")) ? iZzn : iZzn + zzegy.zzm(3, this.version);
    }
}
