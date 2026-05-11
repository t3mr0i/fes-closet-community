package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.support.annotation.WorkerThread;

/* loaded from: classes.dex */
public final class zzccj {
    private final String zzbfe;
    private boolean zzfgp;
    private final boolean zzirh;
    private boolean zziri;
    private /* synthetic */ zzcch zzirj;

    public zzccj(zzcch zzcchVar, String str, boolean z) {
        this.zzirj = zzcchVar;
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        this.zzbfe = str;
        this.zzirh = true;
    }

    @WorkerThread
    public final boolean get() {
        if (!this.zziri) {
            this.zziri = true;
            this.zzfgp = this.zzirj.zzdtp.getBoolean(this.zzbfe, this.zzirh);
        }
        return this.zzfgp;
    }

    @WorkerThread
    public final void set(boolean z) {
        SharedPreferences.Editor editorEdit = this.zzirj.zzdtp.edit();
        editorEdit.putBoolean(this.zzbfe, z);
        editorEdit.apply();
        this.zzfgp = z;
    }
}
