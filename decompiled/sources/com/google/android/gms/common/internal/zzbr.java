package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.internal.zzbcl;

/* loaded from: classes.dex */
public final class zzbr implements Parcelable.Creator<zzbq> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbq createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        GoogleSignInAccount googleSignInAccount = null;
        int iZzg = 0;
        Account account = null;
        int iZzg2 = 0;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg2 = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    account = (Account) zzbcl.zza(parcel, i, Account.CREATOR);
                    break;
                case 3:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 4:
                    googleSignInAccount = (GoogleSignInAccount) zzbcl.zza(parcel, i, GoogleSignInAccount.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzbq(iZzg2, account, iZzg, googleSignInAccount);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbq[] newArray(int i) {
        return new zzbq[i];
    }
}
