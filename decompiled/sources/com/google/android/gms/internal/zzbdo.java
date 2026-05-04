package com.google.android.gms.internal;

import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class zzbdo extends zzbdl implements zzbco {
    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!getClass().isInstance(obj)) {
            return false;
        }
        zzbdl zzbdlVar = (zzbdl) obj;
        for (zzbdm<?, ?> zzbdmVar : zzzz().values()) {
            if (zza(zzbdmVar)) {
                if (zzbdlVar.zza(zzbdmVar) && zzb(zzbdmVar).equals(zzbdlVar.zzb(zzbdmVar))) {
                }
                return false;
            }
            if (zzbdlVar.zza(zzbdmVar)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int iHashCode = 0;
        Iterator<zzbdm<?, ?>> it = zzzz().values().iterator();
        while (true) {
            int i = iHashCode;
            if (!it.hasNext()) {
                return i;
            }
            zzbdm<?, ?> next = it.next();
            if (zza(next)) {
                iHashCode = zzb(next).hashCode() + (i * 31);
            } else {
                iHashCode = i;
            }
        }
    }

    @Override // com.google.android.gms.internal.zzbdl
    public Object zzgi(String str) {
        return null;
    }

    @Override // com.google.android.gms.internal.zzbdl
    public boolean zzgj(String str) {
        return false;
    }
}
