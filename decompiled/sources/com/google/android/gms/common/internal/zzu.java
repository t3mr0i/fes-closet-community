package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.google.android.gms.common.api.internal.zzcg;

/* loaded from: classes.dex */
public abstract class zzu implements DialogInterface.OnClickListener {
    public static zzu zza(Activity activity, Intent intent, int i) {
        return new zzv(intent, activity, i);
    }

    public static zzu zza(@NonNull Fragment fragment, Intent intent, int i) {
        return new zzw(intent, fragment, i);
    }

    public static zzu zza(@NonNull zzcg zzcgVar, Intent intent, int i) {
        return new zzx(intent, zzcgVar, 2);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            zzakb();
        } catch (ActivityNotFoundException e) {
            Log.e("DialogRedirect", "Failed to start resolution intent", e);
        } finally {
            dialogInterface.dismiss();
        }
    }

    protected abstract void zzakb();
}
