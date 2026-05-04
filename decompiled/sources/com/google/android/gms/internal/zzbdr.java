package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzbdr extends zzbck {
    public static final Parcelable.Creator<zzbdr> CREATOR = new zzbdu();
    private int zzdxr;
    private final HashMap<String, Map<String, zzbdm<?, ?>>> zzfwz;
    private final ArrayList<zzbds> zzfxa = null;
    private final String zzfxb;

    zzbdr(int i, ArrayList<zzbds> arrayList, String str) {
        this.zzdxr = i;
        HashMap<String, Map<String, zzbdm<?, ?>>> map = new HashMap<>();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            zzbds zzbdsVar = arrayList.get(i2);
            map.put(zzbdsVar.className, zzbdsVar.zzakx());
        }
        this.zzfwz = map;
        this.zzfxb = (String) com.google.android.gms.common.internal.zzbp.zzu(str);
        zzakv();
    }

    private final void zzakv() {
        Iterator<String> it = this.zzfwz.keySet().iterator();
        while (it.hasNext()) {
            Map<String, zzbdm<?, ?>> map = this.zzfwz.get(it.next());
            Iterator<String> it2 = map.keySet().iterator();
            while (it2.hasNext()) {
                map.get(it2.next()).zza(this);
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.zzfwz.keySet()) {
            sb.append(str).append(":\n");
            Map<String, zzbdm<?, ?>> map = this.zzfwz.get(str);
            for (String str2 : map.keySet()) {
                sb.append("  ").append(str2).append(": ");
                sb.append(map.get(str2));
            }
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxr);
        ArrayList arrayList = new ArrayList();
        for (String str : this.zzfwz.keySet()) {
            arrayList.add(new zzbds(str, this.zzfwz.get(str)));
        }
        zzbcn.zzc(parcel, 2, arrayList, false);
        zzbcn.zza(parcel, 3, this.zzfxb, false);
        zzbcn.zzai(parcel, iZze);
    }

    public final String zzakw() {
        return this.zzfxb;
    }

    public final Map<String, zzbdm<?, ?>> zzgk(String str) {
        return this.zzfwz.get(str);
    }
}
