package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.internal.zzn;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbcl;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zzd implements Parcelable.Creator<GoogleSignInOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleSignInOptions createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        ArrayList arrayListZzc = null;
        String strZzq = null;
        String strZzq2 = null;
        boolean zZzc = false;
        boolean zZzc2 = false;
        boolean zZzc3 = false;
        Account account = null;
        ArrayList arrayListZzc2 = null;
        int iZzg = 0;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    arrayListZzc2 = zzbcl.zzc(parcel, i, Scope.CREATOR);
                    break;
                case 3:
                    account = (Account) zzbcl.zza(parcel, i, Account.CREATOR);
                    break;
                case 4:
                    zZzc3 = zzbcl.zzc(parcel, i);
                    break;
                case 5:
                    zZzc2 = zzbcl.zzc(parcel, i);
                    break;
                case 6:
                    zZzc = zzbcl.zzc(parcel, i);
                    break;
                case 7:
                    strZzq2 = zzbcl.zzq(parcel, i);
                    break;
                case 8:
                    strZzq = zzbcl.zzq(parcel, i);
                    break;
                case 9:
                    arrayListZzc = zzbcl.zzc(parcel, i, zzn.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new GoogleSignInOptions(iZzg, (ArrayList<Scope>) arrayListZzc2, account, zZzc3, zZzc2, zZzc, strZzq2, strZzq, (ArrayList<zzn>) arrayListZzc);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleSignInOptions[] newArray(int i) {
        return new GoogleSignInOptions[i];
    }
}
