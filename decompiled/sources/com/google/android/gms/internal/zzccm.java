package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.support.annotation.WorkerThread;

/* loaded from: classes.dex */
public final class zzccm {
    private String mValue;
    private final String zzbfe;
    private boolean zziri;
    private /* synthetic */ zzcch zzirj;
    private final String zziro;

    public zzccm(zzcch zzcchVar, String str, String str2) {
        this.zzirj = zzcchVar;
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        this.zzbfe = str;
        this.zziro = null;
    }

    @WorkerThread
    public final String zzays() {
        if (!this.zziri) {
            this.zziri = true;
            this.mValue = this.zzirj.zzdtp.getString(this.zzbfe, null);
        }
        return this.mValue;
    }

    @WorkerThread
    public final void zzjl(String str) {
        if (zzcfw.zzas(str, this.mValue)) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.zzirj.zzdtp.edit();
        editorEdit.putString(this.zzbfe, str);
        editorEdit.apply();
        this.mValue = str;
    }
}
