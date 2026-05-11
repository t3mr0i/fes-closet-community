package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;

/* loaded from: classes.dex */
public final class zzccn {
    private final zzccp zzirp;

    public zzccn(zzccp zzccpVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzccpVar);
        this.zzirp = zzccpVar;
    }

    public static boolean zzj(Context context, boolean z) {
        com.google.android.gms.common.internal.zzbp.zzu(context);
        return zzcfw.zza(context, "com.google.android.gms.measurement.AppMeasurementReceiver", false);
    }

    @MainThread
    public final void onReceive(Context context, Intent intent) throws IllegalStateException {
        zzccw zzccwVarZzdn = zzccw.zzdn(context);
        zzcbw zzcbwVarZzaum = zzccwVarZzdn.zzaum();
        if (intent == null) {
            zzcbwVarZzaum.zzayg().log("Receiver called with null intent");
            return;
        }
        zzcax.zzawl();
        String action = intent.getAction();
        zzcbwVarZzaum.zzayk().zzj("Local receiver got", action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zzcfh.zzk(context, false);
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            this.zzirp.doStartService(context, className);
            return;
        }
        if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
            String stringExtra = intent.getStringExtra("referrer");
            if (stringExtra == null) {
                zzcbwVarZzaum.zzayk().log("Install referrer extras are null");
                return;
            }
            zzcbwVarZzaum.zzayi().zzj("Install referrer extras are", stringExtra);
            if (!stringExtra.contains("?")) {
                String strValueOf = String.valueOf(stringExtra);
                stringExtra = strValueOf.length() != 0 ? "?".concat(strValueOf) : new String("?");
            }
            Bundle bundleZzp = zzccwVarZzdn.zzaui().zzp(Uri.parse(stringExtra));
            if (bundleZzp == null) {
                zzcbwVarZzaum.zzayk().log("No campaign defined in install referrer broadcast");
                return;
            }
            long longExtra = 1000 * intent.getLongExtra("referrer_timestamp_seconds", 0L);
            if (longExtra == 0) {
                zzcbwVarZzaum.zzayg().log("Install referrer is missing timestamp");
            }
            zzccwVarZzdn.zzaul().zzg(new zzcco(this, zzccwVarZzdn, longExtra, bundleZzp, context, zzcbwVarZzaum));
        }
    }
}
