package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzaok extends zzeb implements zzaoj {
    zzaok(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.analytics.internal.IAnalyticsService");
    }

    @Override // com.google.android.gms.internal.zzaoj
    public final void zza(Map map, long j, String str, List<zzanp> list) throws RemoteException {
        Parcel parcelZzax = zzax();
        parcelZzax.writeMap(map);
        parcelZzax.writeLong(j);
        parcelZzax.writeString(str);
        parcelZzax.writeTypedList(list);
        zzb(1, parcelZzax);
    }

    @Override // com.google.android.gms.internal.zzaoj
    public final void zzvr() throws RemoteException {
        zzb(2, zzax());
    }
}
