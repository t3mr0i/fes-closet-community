package com.google.android.gms.common.api;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.internal.zzbp;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AvailabilityException extends Exception {
    private final ArrayMap<zzh<?>, ConnectionResult> zzfgi;

    public AvailabilityException(ArrayMap<zzh<?>, ConnectionResult> arrayMap) {
        this.zzfgi = arrayMap;
    }

    public ConnectionResult getConnectionResult(GoogleApi<? extends Api.ApiOptions> googleApi) {
        Object objZzafk = googleApi.zzafk();
        zzbp.zzb(this.zzfgi.get(objZzafk) != null, "The given API was not part of the availability request.");
        return this.zzfgi.get(objZzafk);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (zzh<?> zzhVar : this.zzfgi.keySet()) {
            ConnectionResult connectionResult = this.zzfgi.get(zzhVar);
            if (connectionResult.isSuccess()) {
                z = false;
            }
            String strZzafv = zzhVar.zzafv();
            String strValueOf = String.valueOf(connectionResult);
            arrayList.add(new StringBuilder(String.valueOf(strZzafv).length() + 2 + String.valueOf(strValueOf).length()).append(strZzafv).append(": ").append(strValueOf).toString());
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("None of the queried APIs are available. ");
        } else {
            sb.append("Some of the queried APIs are unavailable. ");
        }
        sb.append(TextUtils.join("; ", arrayList));
        return sb.toString();
    }

    public final ArrayMap<zzh<?>, ConnectionResult> zzafh() {
        return this.zzfgi;
    }
}
