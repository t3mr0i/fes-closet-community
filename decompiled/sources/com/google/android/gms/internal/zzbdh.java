package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class zzbdh extends zzbck implements zzbdn<String, Integer> {
    public static final Parcelable.Creator<zzbdh> CREATOR = new zzbdj();
    private int zzdxr;
    private final HashMap<String, Integer> zzfwk;
    private final SparseArray<String> zzfwl;
    private final ArrayList<zzbdi> zzfwm;

    public zzbdh() {
        this.zzdxr = 1;
        this.zzfwk = new HashMap<>();
        this.zzfwl = new SparseArray<>();
        this.zzfwm = null;
    }

    zzbdh(int i, ArrayList<zzbdi> arrayList) {
        this.zzdxr = i;
        this.zzfwk = new HashMap<>();
        this.zzfwl = new SparseArray<>();
        this.zzfwm = null;
        zzd(arrayList);
    }

    private final void zzd(ArrayList<zzbdi> arrayList) {
        ArrayList<zzbdi> arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            zzbdi zzbdiVar = arrayList2.get(i);
            i++;
            zzbdi zzbdiVar2 = zzbdiVar;
            zzi(zzbdiVar2.zzfwn, zzbdiVar2.zzfwo);
        }
    }

    @Override // com.google.android.gms.internal.zzbdn
    public final /* synthetic */ String convertBack(Integer num) {
        String str = this.zzfwl.get(num.intValue());
        return (str == null && this.zzfwk.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxr);
        ArrayList arrayList = new ArrayList();
        for (String str : this.zzfwk.keySet()) {
            arrayList.add(new zzbdi(str, this.zzfwk.get(str).intValue()));
        }
        zzbcn.zzc(parcel, 2, arrayList, false);
        zzbcn.zzai(parcel, iZze);
    }

    public final zzbdh zzi(String str, int i) {
        this.zzfwk.put(str, Integer.valueOf(i));
        this.zzfwl.put(i, str);
        return this;
    }
}
