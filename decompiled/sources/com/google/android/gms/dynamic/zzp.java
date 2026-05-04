package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzbp;

/* loaded from: classes.dex */
public abstract class zzp<T> {
    private final String zzgpe;
    private T zzgpf;

    protected zzp(String str) {
        this.zzgpe = str;
    }

    protected final T zzcu(Context context) throws zzq {
        if (this.zzgpf == null) {
            zzbp.zzu(context);
            Context remoteContext = com.google.android.gms.common.zzo.getRemoteContext(context);
            if (remoteContext == null) {
                throw new zzq("Could not get remote context.");
            }
            try {
                this.zzgpf = zze((IBinder) remoteContext.getClassLoader().loadClass(this.zzgpe).newInstance());
            } catch (ClassNotFoundException e) {
                throw new zzq("Could not load creator class.", e);
            } catch (IllegalAccessException e2) {
                throw new zzq("Could not access creator.", e2);
            } catch (InstantiationException e3) {
                throw new zzq("Could not instantiate creator.", e3);
            }
        }
        return this.zzgpf;
    }

    protected abstract T zze(IBinder iBinder);
}
