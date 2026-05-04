package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
final class zzefv<E> extends zzeea<E> {
    private static final zzefv<Object> zzndg;
    private final List<E> zzndh;

    static {
        zzefv<Object> zzefvVar = new zzefv<>();
        zzndg = zzefvVar;
        zzefvVar.zzbht();
    }

    zzefv() {
        this(new ArrayList(10));
    }

    private zzefv(List<E> list) {
        this.zzndh = list;
    }

    public static <E> zzefv<E> zzcdh() {
        return (zzefv<E>) zzndg;
    }

    @Override // com.google.android.gms.internal.zzeea, java.util.AbstractList, java.util.List
    public final void add(int i, E e) {
        zzcbs();
        this.zzndh.add(i, e);
        this.modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int i) {
        return this.zzndh.get(i);
    }

    @Override // com.google.android.gms.internal.zzeea, java.util.AbstractList, java.util.List
    public final E remove(int i) {
        zzcbs();
        E eRemove = this.zzndh.remove(i);
        this.modCount++;
        return eRemove;
    }

    @Override // com.google.android.gms.internal.zzeea, java.util.AbstractList, java.util.List
    public final E set(int i, E e) {
        zzcbs();
        E e2 = this.zzndh.set(i, e);
        this.modCount++;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzndh.size();
    }

    @Override // com.google.android.gms.internal.zzefi
    public final /* synthetic */ zzefi zzgu(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzndh);
        return new zzefv(arrayList);
    }
}
