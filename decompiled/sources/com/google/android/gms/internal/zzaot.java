package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import java.util.UUID;

/* loaded from: classes.dex */
public final class zzaot {
    private final String mName;
    private final long zzdtt;
    private /* synthetic */ zzaor zzdtu;

    private zzaot(zzaor zzaorVar, String str, long j) {
        this.zzdtu = zzaorVar;
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzbh(j > 0);
        this.mName = str;
        this.zzdtt = j;
    }

    private final void zzzh() {
        long jCurrentTimeMillis = this.zzdtu.zzvx().currentTimeMillis();
        SharedPreferences.Editor editorEdit = this.zzdtu.zzdtp.edit();
        editorEdit.remove(zzzl());
        editorEdit.remove(zzzm());
        editorEdit.putLong(zzzk(), jCurrentTimeMillis);
        editorEdit.commit();
    }

    private final long zzzj() {
        return this.zzdtu.zzdtp.getLong(zzzk(), 0L);
    }

    private final String zzzk() {
        return String.valueOf(this.mName).concat(":start");
    }

    private final String zzzl() {
        return String.valueOf(this.mName).concat(":count");
    }

    private final String zzzm() {
        return String.valueOf(this.mName).concat(":value");
    }

    public final void zzdy(String str) {
        if (zzzj() == 0) {
            zzzh();
        }
        if (str == null) {
            str = "";
        }
        synchronized (this) {
            long j = this.zzdtu.zzdtp.getLong(zzzl(), 0L);
            if (j <= 0) {
                SharedPreferences.Editor editorEdit = this.zzdtu.zzdtp.edit();
                editorEdit.putString(zzzm(), str);
                editorEdit.putLong(zzzl(), 1L);
                editorEdit.apply();
                return;
            }
            boolean z = (UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE) < Long.MAX_VALUE / (j + 1);
            SharedPreferences.Editor editorEdit2 = this.zzdtu.zzdtp.edit();
            if (z) {
                editorEdit2.putString(zzzm(), str);
            }
            editorEdit2.putLong(zzzl(), j + 1);
            editorEdit2.apply();
        }
    }

    public final Pair<String, Long> zzzi() {
        long jZzzj = zzzj();
        long jAbs = jZzzj == 0 ? 0L : Math.abs(jZzzj - this.zzdtu.zzvx().currentTimeMillis());
        if (jAbs < this.zzdtt) {
            return null;
        }
        if (jAbs > (this.zzdtt << 1)) {
            zzzh();
            return null;
        }
        String string = this.zzdtu.zzdtp.getString(zzzm(), null);
        long j = this.zzdtu.zzdtp.getLong(zzzl(), 0L);
        zzzh();
        if (string == null || j <= 0) {
            return null;
        }
        return new Pair<>(string, Long.valueOf(j));
    }
}
