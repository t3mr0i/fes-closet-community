package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes.dex */
final class zzegc<K, V> implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private /* synthetic */ zzefw zzndq;
    private boolean zzndr;
    private Iterator<Map.Entry<K, V>> zznds;

    private zzegc(zzefw zzefwVar) {
        this.zzndq = zzefwVar;
        this.pos = -1;
    }

    /* synthetic */ zzegc(zzefw zzefwVar, zzefx zzefxVar) {
        this(zzefwVar);
    }

    private final Iterator<Map.Entry<K, V>> zzcdo() {
        if (this.zznds == null) {
            this.zznds = this.zzndq.zzndk.entrySet().iterator();
        }
        return this.zznds;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.pos + 1 < this.zzndq.zzndj.size() || zzcdo().hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        this.zzndr = true;
        int i = this.pos + 1;
        this.pos = i;
        return i < this.zzndq.zzndj.size() ? (Map.Entry) this.zzndq.zzndj.get(this.pos) : zzcdo().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.zzndr) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzndr = false;
        this.zzndq.zzcdk();
        if (this.pos >= this.zzndq.zzndj.size()) {
            zzcdo().remove();
            return;
        }
        zzefw zzefwVar = this.zzndq;
        int i = this.pos;
        this.pos = i - 1;
        zzefwVar.zzgx(i);
    }
}
