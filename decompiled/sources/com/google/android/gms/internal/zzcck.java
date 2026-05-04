package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.support.annotation.WorkerThread;

/* loaded from: classes.dex */
public final class zzcck {
    private final String zzbfe;
    private long zzdms;
    private boolean zziri;
    private /* synthetic */ zzcch zzirj;
    private final long zzirk;

    public zzcck(zzcch zzcchVar, String str, long j) {
        this.zzirj = zzcchVar;
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        this.zzbfe = str;
        this.zzirk = j;
    }

    @WorkerThread
    public final long get() {
        if (!this.zziri) {
            this.zziri = true;
            this.zzdms = this.zzirj.zzdtp.getLong(this.zzbfe, this.zzirk);
        }
        return this.zzdms;
    }

    @WorkerThread
    public final void set(long j) {
        SharedPreferences.Editor editorEdit = this.zzirj.zzdtp.edit();
        editorEdit.putLong(this.zzbfe, j);
        editorEdit.apply();
        this.zzdms = j;
    }
}
