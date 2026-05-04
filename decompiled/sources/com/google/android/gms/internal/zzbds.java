package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzbds extends zzbck {
    public static final Parcelable.Creator<zzbds> CREATOR = new zzbdv();
    final String className;
    private int versionCode;
    private ArrayList<zzbdt> zzfxc;

    zzbds(int i, String str, ArrayList<zzbdt> arrayList) {
        this.versionCode = i;
        this.className = str;
        this.zzfxc = arrayList;
    }

    zzbds(String str, Map<String, zzbdm<?, ?>> map) {
        ArrayList<zzbdt> arrayList;
        this.versionCode = 1;
        this.className = str;
        if (map == null) {
            arrayList = null;
        } else {
            ArrayList<zzbdt> arrayList2 = new ArrayList<>();
            for (String str2 : map.keySet()) {
                arrayList2.add(new zzbdt(str2, map.get(str2)));
            }
            arrayList = arrayList2;
        }
        this.zzfxc = arrayList;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.versionCode);
        zzbcn.zza(parcel, 2, this.className, false);
        zzbcn.zzc(parcel, 3, this.zzfxc, false);
        zzbcn.zzai(parcel, iZze);
    }

    final HashMap<String, zzbdm<?, ?>> zzakx() {
        HashMap<String, zzbdm<?, ?>> map = new HashMap<>();
        int size = this.zzfxc.size();
        for (int i = 0; i < size; i++) {
            zzbdt zzbdtVar = this.zzfxc.get(i);
            map.put(zzbdtVar.key, zzbdtVar.zzfxd);
        }
        return map;
    }
}
