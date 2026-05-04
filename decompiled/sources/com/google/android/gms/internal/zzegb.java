package com.google.android.gms.internal;

import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes.dex */
final class zzegb<K, V> implements Comparable<zzegb>, Map.Entry<K, V> {
    private V value;

    /* JADX INFO: Incorrect field signature: TK; */
    private final Comparable zzndp;
    private /* synthetic */ zzefw zzndq;

    /* JADX WARN: Multi-variable type inference failed */
    zzegb(zzefw zzefwVar, K k, V v) {
        this.zzndq = zzefwVar;
        this.zzndp = k;
        this.value = v;
    }

    zzegb(zzefw zzefwVar, Map.Entry<K, V> entry) {
        this(zzefwVar, (Comparable) entry.getKey(), entry.getValue());
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(zzegb zzegbVar) {
        return ((Comparable) getKey()).compareTo((Comparable) zzegbVar.getKey());
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return equals(this.zzndp, entry.getKey()) && equals(this.value, entry.getValue());
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.zzndp;
    }

    @Override // java.util.Map.Entry
    public final V getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return (this.zzndp == null ? 0 : this.zzndp.hashCode()) ^ (this.value != null ? this.value.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        this.zzndq.zzcdk();
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zzndp);
        String strValueOf2 = String.valueOf(this.value);
        return new StringBuilder(String.valueOf(strValueOf).length() + 1 + String.valueOf(strValueOf2).length()).append(strValueOf).append("=").append(strValueOf2).toString();
    }
}
