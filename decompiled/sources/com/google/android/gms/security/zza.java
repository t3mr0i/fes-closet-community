package com.google.android.gms.security;

import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.zze;
import com.google.android.gms.security.ProviderInstaller;

/* loaded from: classes.dex */
final class zza extends AsyncTask<Void, Void, Integer> {
    private /* synthetic */ Context zzanz;
    private /* synthetic */ ProviderInstaller.ProviderInstallListener zzjnl;

    zza(Context context, ProviderInstaller.ProviderInstallListener providerInstallListener) {
        this.zzanz = context;
        this.zzjnl = providerInstallListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final Integer doInBackground(Void... voidArr) {
        try {
            ProviderInstaller.installIfNeeded(this.zzanz);
            return 0;
        } catch (GooglePlayServicesNotAvailableException e) {
            return Integer.valueOf(e.errorCode);
        } catch (GooglePlayServicesRepairableException e2) {
            return Integer.valueOf(e2.getConnectionStatusCode());
        }
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(Integer num) {
        Integer num2 = num;
        if (num2.intValue() == 0) {
            this.zzjnl.onProviderInstalled();
            return;
        }
        zze unused = ProviderInstaller.zzjnj;
        this.zzjnl.onProviderInstallFailed(num2.intValue(), zze.zza(this.zzanz, num2.intValue(), "pi"));
    }
}
