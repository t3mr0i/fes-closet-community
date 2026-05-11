package com.google.android.gms.internal;

import java.util.Map;

/* loaded from: classes.dex */
final class zzefn<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzefl> zzndc;

    private zzefn(Map.Entry<K, zzefl> entry) {
        this.zzndc = entry;
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        return this.zzndc.getKey();
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.zzndc.getValue() == null) {
            return null;
        }
        return zzefl.zzcdg();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj instanceof zzefq) {
            return this.zzndc.getValue().zzg((zzefq) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
