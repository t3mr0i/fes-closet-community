package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbcl;

/* loaded from: classes.dex */
public final class zzz implements Parcelable.Creator<zzy> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzy createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        com.google.android.gms.common.zzc[] zzcVarArr = null;
        Account account = null;
        Bundle bundleZzs = null;
        Scope[] scopeArr = null;
        IBinder iBinderZzr = null;
        String strZzq = null;
        int iZzg = 0;
        int iZzg2 = 0;
        int iZzg3 = 0;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg3 = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    iZzg2 = zzbcl.zzg(parcel, i);
                    break;
                case 3:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 4:
                    strZzq = zzbcl.zzq(parcel, i);
                    break;
                case 5:
                    iBinderZzr = zzbcl.zzr(parcel, i);
                    break;
                case 6:
                    scopeArr = (Scope[]) zzbcl.zzb(parcel, i, Scope.CREATOR);
                    break;
                case 7:
                    bundleZzs = zzbcl.zzs(parcel, i);
                    break;
                case 8:
                    account = (Account) zzbcl.zza(parcel, i, Account.CREATOR);
                    break;
                case 9:
                default:
                    zzbcl.zzb(parcel, i);
                    break;
                case 10:
                    zzcVarArr = (com.google.android.gms.common.zzc[]) zzbcl.zzb(parcel, i, com.google.android.gms.common.zzc.CREATOR);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzy(iZzg3, iZzg2, iZzg, strZzq, iBinderZzr, scopeArr, bundleZzs, account, zzcVarArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzy[] newArray(int i) {
        return new zzy[i];
    }
}
