package com.google.android.gms.tagmanager;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

/* loaded from: classes.dex */
final class zzb implements zzd {
    private /* synthetic */ zza zzjop;

    zzb(zza zzaVar) {
        this.zzjop = zzaVar;
    }

    @Override // com.google.android.gms.tagmanager.zzd
    public final AdvertisingIdClient.Info zzbcj() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.zzjop.mContext);
        } catch (GooglePlayServicesNotAvailableException e) {
            this.zzjop.close();
            zzdj.zzc("GooglePlayServicesNotAvailableException getting Advertising Id Info", e);
            return null;
        } catch (GooglePlayServicesRepairableException e2) {
            zzdj.zzc("GooglePlayServicesRepairableException getting Advertising Id Info", e2);
            return null;
        } catch (IOException e3) {
            zzdj.zzc("IOException getting Ad Id Info", e3);
            return null;
        } catch (IllegalStateException e4) {
            zzdj.zzc("IllegalStateException getting Advertising Id Info", e4);
            return null;
        } catch (Exception e5) {
            zzdj.zzc("Unknown exception. Could not get the Advertising Id Info.", e5);
            return null;
        }
    }
}
