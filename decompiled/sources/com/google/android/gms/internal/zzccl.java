package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.support.annotation.WorkerThread;
import android.util.Pair;

/* loaded from: classes.dex */
public final class zzccl {
    private final long zzdtt;
    private /* synthetic */ zzcch zzirj;
    private String zzirl;
    private final String zzirm;
    private final String zzirn;

    private zzccl(zzcch zzcchVar, String str, long j) {
        this.zzirj = zzcchVar;
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzbh(j > 0);
        this.zzirl = String.valueOf(str).concat(":start");
        this.zzirm = String.valueOf(str).concat(":count");
        this.zzirn = String.valueOf(str).concat(":value");
        this.zzdtt = j;
    }

    @WorkerThread
    private final void zzzh() {
        this.zzirj.zzuj();
        long jCurrentTimeMillis = this.zzirj.zzvx().currentTimeMillis();
        SharedPreferences.Editor editorEdit = this.zzirj.zzdtp.edit();
        editorEdit.remove(this.zzirm);
        editorEdit.remove(this.zzirn);
        editorEdit.putLong(this.zzirl, jCurrentTimeMillis);
        editorEdit.apply();
    }

    @WorkerThread
    private final long zzzj() {
        return this.zzirj.zzaym().getLong(this.zzirl, 0L);
    }

    @WorkerThread
    public final void zzf(String str, long j) {
        this.zzirj.zzuj();
        if (zzzj() == 0) {
            zzzh();
        }
        if (str == null) {
            str = "";
        }
        long j2 = this.zzirj.zzdtp.getLong(this.zzirm, 0L);
        if (j2 <= 0) {
            SharedPreferences.Editor editorEdit = this.zzirj.zzdtp.edit();
            editorEdit.putString(this.zzirn, str);
            editorEdit.putLong(this.zzirm, 1L);
            editorEdit.apply();
            return;
        }
        boolean z = (this.zzirj.zzaui().zzazz().nextLong() & Long.MAX_VALUE) < Long.MAX_VALUE / (j2 + 1);
        SharedPreferences.Editor editorEdit2 = this.zzirj.zzdtp.edit();
        if (z) {
            editorEdit2.putString(this.zzirn, str);
        }
        editorEdit2.putLong(this.zzirm, j2 + 1);
        editorEdit2.apply();
    }

    @WorkerThread
    public final Pair<String, Long> zzzi() {
        long jAbs;
        this.zzirj.zzuj();
        this.zzirj.zzuj();
        long jZzzj = zzzj();
        if (jZzzj == 0) {
            zzzh();
            jAbs = 0;
        } else {
            jAbs = Math.abs(jZzzj - this.zzirj.zzvx().currentTimeMillis());
        }
        if (jAbs < this.zzdtt) {
            return null;
        }
        if (jAbs > (this.zzdtt << 1)) {
            zzzh();
            return null;
        }
        String string = this.zzirj.zzaym().getString(this.zzirn, null);
        long j = this.zzirj.zzaym().getLong(this.zzirm, 0L);
        zzzh();
        return (string == null || j <= 0) ? zzcch.zziqm : new Pair<>(string, Long.valueOf(j));
    }
}
