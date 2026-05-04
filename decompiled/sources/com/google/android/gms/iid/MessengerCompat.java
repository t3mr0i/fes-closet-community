package com.google.android.gms.iid;

import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.internal.ReflectedParcelable;

/* loaded from: classes.dex */
public class MessengerCompat implements ReflectedParcelable {
    public static final Parcelable.Creator<MessengerCompat> CREATOR = new zzd();
    private Messenger zzhtt;
    private zzb zzhtu;

    public MessengerCompat(IBinder iBinder) {
        zzb zzcVar;
        if (Build.VERSION.SDK_INT >= 21) {
            this.zzhtt = new Messenger(iBinder);
            return;
        }
        if (iBinder == null) {
            zzcVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.iid.IMessengerCompat");
            zzcVar = iInterfaceQueryLocalInterface instanceof zzb ? (zzb) iInterfaceQueryLocalInterface : new zzc(iBinder);
        }
        this.zzhtu = zzcVar;
    }

    private final IBinder getBinder() {
        return this.zzhtt != null ? this.zzhtt.getBinder() : this.zzhtu.asBinder();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return getBinder().equals(((MessengerCompat) obj).getBinder());
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        return getBinder().hashCode();
    }

    public final void send(Message message) throws RemoteException {
        if (this.zzhtt != null) {
            this.zzhtt.send(message);
        } else {
            this.zzhtu.send(message);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzhtt != null) {
            parcel.writeStrongBinder(this.zzhtt.getBinder());
        } else {
            parcel.writeStrongBinder(this.zzhtu.asBinder());
        }
    }
}
