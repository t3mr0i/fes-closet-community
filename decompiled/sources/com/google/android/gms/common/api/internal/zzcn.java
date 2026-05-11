package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class zzcn {
    private final Set<zzcj<?>> zzeuj = Collections.newSetFromMap(new WeakHashMap());

    public static <L> zzcl<L> zza(@NonNull L l, @NonNull String str) {
        com.google.android.gms.common.internal.zzbp.zzb(l, "Listener must not be null");
        com.google.android.gms.common.internal.zzbp.zzb(str, "Listener type must not be null");
        com.google.android.gms.common.internal.zzbp.zzh(str, "Listener type must not be empty");
        return new zzcl<>(l, str);
    }

    public static <L> zzcj<L> zzb(@NonNull L l, @NonNull Looper looper, @NonNull String str) {
        com.google.android.gms.common.internal.zzbp.zzb(l, "Listener must not be null");
        com.google.android.gms.common.internal.zzbp.zzb(looper, "Looper must not be null");
        com.google.android.gms.common.internal.zzbp.zzb(str, "Listener type must not be null");
        return new zzcj<>(looper, l, str);
    }

    public final void release() {
        Iterator<zzcj<?>> it = this.zzeuj.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        this.zzeuj.clear();
    }

    public final <L> zzcj<L> zza(@NonNull L l, @NonNull Looper looper, @NonNull String str) {
        zzcj<L> zzcjVarZzb = zzb(l, looper, str);
        this.zzeuj.add(zzcjVarZzb);
        return zzcjVarZzb;
    }
}
