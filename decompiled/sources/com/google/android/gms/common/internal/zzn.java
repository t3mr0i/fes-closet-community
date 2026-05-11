package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
public final class zzn extends zze {
    private /* synthetic */ zzd zzftk;
    private IBinder zzfto;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @BinderThread
    public zzn(zzd zzdVar, int i, IBinder iBinder, Bundle bundle) {
        super(zzdVar, i, bundle);
        this.zzftk = zzdVar;
        this.zzfto = iBinder;
    }

    @Override // com.google.android.gms.common.internal.zze
    protected final boolean zzajo() throws RemoteException {
        try {
            String interfaceDescriptor = this.zzfto.getInterfaceDescriptor();
            if (!this.zzftk.zzhd().equals(interfaceDescriptor)) {
                String strZzhd = this.zzftk.zzhd();
                Log.e("GmsClient", new StringBuilder(String.valueOf(strZzhd).length() + 34 + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(strZzhd).append(" vs. ").append(interfaceDescriptor).toString());
                return false;
            }
            IInterface iInterfaceZzd = this.zzftk.zzd(this.zzfto);
            if (iInterfaceZzd == null) {
                return false;
            }
            if (!this.zzftk.zza(2, 4, (int) iInterfaceZzd) && !this.zzftk.zza(3, 4, (int) iInterfaceZzd)) {
                return false;
            }
            this.zzftk.zzftf = null;
            Bundle bundleZzaeh = this.zzftk.zzaeh();
            if (this.zzftk.zzftb != null) {
                this.zzftk.zzftb.onConnected(bundleZzaeh);
            }
            return true;
        } catch (RemoteException e) {
            Log.w("GmsClient", "service probably died");
            return false;
        }
    }

    @Override // com.google.android.gms.common.internal.zze
    protected final void zzj(ConnectionResult connectionResult) {
        if (this.zzftk.zzftc != null) {
            this.zzftk.zzftc.onConnectionFailed(connectionResult);
        }
        this.zzftk.onConnectionFailed(connectionResult);
    }
}
