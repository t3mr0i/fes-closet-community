package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zzefo<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zzlqk;

    public zzefo(Iterator<Map.Entry<K, Object>> it) {
        this.zzlqk = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzlqk.hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        Map.Entry<K, Object> next = this.zzlqk.next();
        return next.getValue() instanceof zzefl ? new zzefn(next) : next;
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.zzlqk.remove();
    }
}
