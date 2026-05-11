package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.util.LruCache;

@TargetApi(12)
/* loaded from: classes.dex */
final class zzdc<K, V> implements zzp<K, V> {
    private LruCache<K, V> zzjsp;

    zzdc(int i, zzs<K, V> zzsVar) {
        this.zzjsp = new zzdd(this, 1048576, zzsVar);
    }

    @Override // com.google.android.gms.tagmanager.zzp
    public final V get(K k) {
        return this.zzjsp.get(k);
    }

    @Override // com.google.android.gms.tagmanager.zzp
    public final void zzf(K k, V v) {
        this.zzjsp.put(k, v);
    }
}
