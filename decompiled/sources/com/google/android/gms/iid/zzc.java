package com.google.android.gms.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

/* loaded from: classes.dex */
public final class zzc extends zzeb implements zzb {
    zzc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.iid.IMessengerCompat");
    }

    @Override // com.google.android.gms.iid.zzb
    public final void send(Message message) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, message);
        zzc(1, parcelZzax);
    }
}
