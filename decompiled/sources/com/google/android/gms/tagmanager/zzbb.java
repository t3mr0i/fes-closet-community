package com.google.android.gms.tagmanager;

import android.content.Context;

/* loaded from: classes.dex */
public final class zzbb implements zzby {
    private static final Object zzjon = new Object();
    private static zzbb zzjrb;
    private zzek zzjpp;
    private zzbz zzjrc;

    private zzbb(Context context) {
        this(zzca.zzdw(context), new zzfm());
    }

    private zzbb(zzbz zzbzVar, zzek zzekVar) {
        this.zzjrc = zzbzVar;
        this.zzjpp = zzekVar;
    }

    public static zzby zzdq(Context context) {
        zzbb zzbbVar;
        synchronized (zzjon) {
            if (zzjrb == null) {
                zzjrb = new zzbb(context);
            }
            zzbbVar = zzjrb;
        }
        return zzbbVar;
    }

    @Override // com.google.android.gms.tagmanager.zzby
    public final boolean zzln(String str) {
        if (this.zzjpp.zzys()) {
            this.zzjrc.zzls(str);
            return true;
        }
        zzdj.zzcr("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
        return false;
    }
}
