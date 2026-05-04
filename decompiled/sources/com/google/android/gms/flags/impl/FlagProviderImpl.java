package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzbvm;

@DynamiteApi
/* loaded from: classes.dex */
public class FlagProviderImpl extends zzbvm {
    private boolean zzaqe = false;
    private SharedPreferences zzbfk;

    @Override // com.google.android.gms.internal.zzbvl
    public boolean getBooleanFlagValue(String str, boolean z, int i) {
        return !this.zzaqe ? z : zzb.zza(this.zzbfk, str, Boolean.valueOf(z)).booleanValue();
    }

    @Override // com.google.android.gms.internal.zzbvl
    public int getIntFlagValue(String str, int i, int i2) {
        return !this.zzaqe ? i : zzd.zza(this.zzbfk, str, Integer.valueOf(i)).intValue();
    }

    @Override // com.google.android.gms.internal.zzbvl
    public long getLongFlagValue(String str, long j, int i) {
        return !this.zzaqe ? j : zzf.zza(this.zzbfk, str, Long.valueOf(j)).longValue();
    }

    @Override // com.google.android.gms.internal.zzbvl
    public String getStringFlagValue(String str, String str2, int i) {
        return !this.zzaqe ? str2 : zzh.zza(this.zzbfk, str, str2);
    }

    @Override // com.google.android.gms.internal.zzbvl
    public void init(IObjectWrapper iObjectWrapper) {
        Context context = (Context) zzn.zzx(iObjectWrapper);
        if (this.zzaqe) {
            return;
        }
        try {
            this.zzbfk = zzj.zzcy(context.createPackageContext("com.google.android.gms", 0));
            this.zzaqe = true;
        } catch (PackageManager.NameNotFoundException e) {
        } catch (Exception e2) {
            String strValueOf = String.valueOf(e2.getMessage());
            Log.w("FlagProviderImpl", strValueOf.length() != 0 ? "Could not retrieve sdk flags, continuing with defaults: ".concat(strValueOf) : new String("Could not retrieve sdk flags, continuing with defaults: "));
        }
    }
}
