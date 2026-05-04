package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbcl;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zzb implements Parcelable.Creator<GoogleSignInAccount> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleSignInAccount createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        int iZzg = 0;
        String strZzq = null;
        String strZzq2 = null;
        String strZzq3 = null;
        String strZzq4 = null;
        Uri uri = null;
        String strZzq5 = null;
        long jZzi = 0;
        String strZzq6 = null;
        ArrayList arrayListZzc = null;
        String strZzq7 = null;
        String strZzq8 = null;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    strZzq = zzbcl.zzq(parcel, i);
                    break;
                case 3:
                    strZzq2 = zzbcl.zzq(parcel, i);
                    break;
                case 4:
                    strZzq3 = zzbcl.zzq(parcel, i);
                    break;
                case 5:
                    strZzq4 = zzbcl.zzq(parcel, i);
                    break;
                case 6:
                    uri = (Uri) zzbcl.zza(parcel, i, Uri.CREATOR);
                    break;
                case 7:
                    strZzq5 = zzbcl.zzq(parcel, i);
                    break;
                case 8:
                    jZzi = zzbcl.zzi(parcel, i);
                    break;
                case 9:
                    strZzq6 = zzbcl.zzq(parcel, i);
                    break;
                case 10:
                    arrayListZzc = zzbcl.zzc(parcel, i, Scope.CREATOR);
                    break;
                case 11:
                    strZzq7 = zzbcl.zzq(parcel, i);
                    break;
                case 12:
                    strZzq8 = zzbcl.zzq(parcel, i);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new GoogleSignInAccount(iZzg, strZzq, strZzq2, strZzq3, strZzq4, uri, strZzq5, jZzi, strZzq6, arrayListZzc, strZzq7, strZzq8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleSignInAccount[] newArray(int i) {
        return new GoogleSignInAccount[i];
    }
}
