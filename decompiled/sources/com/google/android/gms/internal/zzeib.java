package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzeib extends zzeha<zzeib> implements Cloneable {
    private static volatile zzeib[] zznkd;
    private String key = "";
    private String value = "";

    public zzeib() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzeib[] zzcex() {
        if (zznkd == null) {
            synchronized (zzehe.zzngo) {
                if (zznkd == null) {
                    zznkd = new zzeib[0];
                }
            }
        }
        return zznkd;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    /* renamed from: zzcey, reason: merged with bridge method [inline-methods] */
    public zzeib clone() {
        try {
            return (zzeib) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzeib)) {
            return false;
        }
        zzeib zzeibVar = (zzeib) obj;
        if (this.key == null) {
            if (zzeibVar.key != null) {
                return false;
            }
        } else if (!this.key.equals(zzeibVar.key)) {
            return false;
        }
        if (this.value == null) {
            if (zzeibVar.value != null) {
                return false;
            }
        } else if (!this.value.equals(zzeibVar.value)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzeibVar.zzngg == null || zzeibVar.zzngg.isEmpty() : this.zzngg.equals(zzeibVar.zzngg);
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
        if (this.key != null && !this.key.equals("")) {
            zzegyVar.zzl(1, this.key);
        }
        if (this.value != null && !this.value.equals("")) {
            zzegyVar.zzl(2, this.value);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha
    /* renamed from: zzceh */
    public final /* synthetic */ zzeha clone() throws CloneNotSupportedException {
        return (zzeib) clone();
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    /* renamed from: zzcei */
    public final /* synthetic */ zzehg clone() throws CloneNotSupportedException {
        return (zzeib) clone();
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.key != null && !this.key.equals("")) {
            iZzn += zzegy.zzm(1, this.key);
        }
        return (this.value == null || this.value.equals("")) ? iZzn : iZzn + zzegy.zzm(2, this.value);
    }
}
