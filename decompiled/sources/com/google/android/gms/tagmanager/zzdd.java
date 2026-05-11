package com.google.android.gms.tagmanager;

import android.util.LruCache;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes.dex */
final class zzdd<K, V> extends LruCache<K, V> {
    private /* synthetic */ zzs zzjsq;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdd(zzdc zzdcVar, int i, zzs zzsVar) {
        super(i);
        this.zzjsq = zzsVar;
    }

    @Override // android.util.LruCache
    protected final int sizeOf(K k, V v) {
        return this.zzjsq.sizeOf(k, v);
    }
}
