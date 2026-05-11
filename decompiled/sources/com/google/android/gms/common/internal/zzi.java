package com.google.android.gms.common.internal;

import android.util.Log;

/* loaded from: classes.dex */
public abstract class zzi<TListener> {
    private TListener mListener;
    private /* synthetic */ zzd zzftk;
    private boolean zzftl = false;

    public zzi(zzd zzdVar, TListener tlistener) {
        this.zzftk = zzdVar;
        this.mListener = tlistener;
    }

    public final void removeListener() {
        synchronized (this) {
            this.mListener = null;
        }
    }

    public final void unregister() {
        removeListener();
        synchronized (this.zzftk.zzfsy) {
            this.zzftk.zzfsy.remove(this);
        }
    }

    public final void zzajp() {
        TListener tlistener;
        synchronized (this) {
            tlistener = this.mListener;
            if (this.zzftl) {
                String strValueOf = String.valueOf(this);
                Log.w("GmsClient", new StringBuilder(String.valueOf(strValueOf).length() + 47).append("Callback proxy ").append(strValueOf).append(" being reused. This is not safe.").toString());
            }
        }
        if (tlistener != null) {
            try {
                zzs(tlistener);
            } catch (RuntimeException e) {
                throw e;
            }
        }
        synchronized (this) {
            this.zzftl = true;
        }
        unregister();
    }

    protected abstract void zzs(TListener tlistener);
}
