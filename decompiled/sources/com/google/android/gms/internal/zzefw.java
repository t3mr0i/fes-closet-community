package com.google.android.gms.internal;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* loaded from: classes.dex */
class zzefw<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzkff;
    private final int zzndi;
    private List<zzegb> zzndj;
    private Map<K, V> zzndk;
    private volatile zzegd zzndl;
    private Map<K, V> zzndm;

    private zzefw(int i) {
        this.zzndi = i;
        this.zzndj = Collections.emptyList();
        this.zzndk = Collections.emptyMap();
        this.zzndm = Collections.emptyMap();
    }

    /* synthetic */ zzefw(int i, zzefx zzefxVar) {
        this(i);
    }

    private final int zza(K k) {
        int i = 0;
        int size = this.zzndj.size() - 1;
        if (size >= 0) {
            int iCompareTo = k.compareTo((Comparable) this.zzndj.get(size).getKey());
            if (iCompareTo > 0) {
                return -(size + 2);
            }
            if (iCompareTo == 0) {
                return size;
            }
        }
        int i2 = size;
        while (i <= i2) {
            int i3 = (i + i2) / 2;
            int iCompareTo2 = k.compareTo((Comparable) this.zzndj.get(i3).getKey());
            if (iCompareTo2 < 0) {
                i2 = i3 - 1;
            } else {
                if (iCompareTo2 <= 0) {
                    return i3;
                }
                i = i3 + 1;
            }
        }
        return -(i + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzcdk() {
        if (this.zzkff) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzcdl() {
        zzcdk();
        if (this.zzndk.isEmpty() && !(this.zzndk instanceof TreeMap)) {
            this.zzndk = new TreeMap();
            this.zzndm = ((TreeMap) this.zzndk).descendingMap();
        }
        return (SortedMap) this.zzndk;
    }

    static <FieldDescriptorType extends zzeeu<FieldDescriptorType>> zzefw<FieldDescriptorType, Object> zzgv(int i) {
        return new zzefx(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final V zzgx(int i) {
        zzcdk();
        V v = (V) this.zzndj.remove(i).getValue();
        if (!this.zzndk.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = zzcdl().entrySet().iterator();
            this.zzndj.add(new zzegb(this, it.next()));
            it.remove();
        }
        return v;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        zzcdk();
        if (!this.zzndj.isEmpty()) {
            this.zzndj.clear();
        }
        if (this.zzndk.isEmpty()) {
            return;
        }
        this.zzndk.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza((zzefw<K, V>) comparable) >= 0 || this.zzndk.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zzndl == null) {
            this.zzndl = new zzegd(this, null);
        }
        return this.zzndl;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzefw)) {
            return super.equals(obj);
        }
        zzefw zzefwVar = (zzefw) obj;
        int size = size();
        if (size != zzefwVar.size()) {
            return false;
        }
        int iZzcdi = zzcdi();
        if (iZzcdi != zzefwVar.zzcdi()) {
            return entrySet().equals(zzefwVar.entrySet());
        }
        for (int i = 0; i < iZzcdi; i++) {
            if (!zzgw(i).equals(zzefwVar.zzgw(i))) {
                return false;
            }
        }
        if (iZzcdi != size) {
            return this.zzndk.equals(zzefwVar.zzndk);
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iZza = zza((zzefw<K, V>) comparable);
        return iZza >= 0 ? (V) this.zzndj.get(iZza).getValue() : this.zzndk.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int iZzcdi = zzcdi();
        int iHashCode = 0;
        for (int i = 0; i < iZzcdi; i++) {
            iHashCode += this.zzndj.get(i).hashCode();
        }
        return this.zzndk.size() > 0 ? this.zzndk.hashCode() + iHashCode : iHashCode;
    }

    public final boolean isImmutable() {
        return this.zzkff;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public /* synthetic */ Object put(Object obj, Object obj2) {
        return zza((zzefw<K, V>) obj, (Comparable) obj2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        zzcdk();
        Comparable comparable = (Comparable) obj;
        int iZza = zza((zzefw<K, V>) comparable);
        if (iZza >= 0) {
            return zzgx(iZza);
        }
        if (this.zzndk.isEmpty()) {
            return null;
        }
        return this.zzndk.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.zzndj.size() + this.zzndk.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final V zza(K k, V v) {
        zzcdk();
        int iZza = zza((zzefw<K, V>) k);
        if (iZza >= 0) {
            return (V) this.zzndj.get(iZza).setValue(v);
        }
        zzcdk();
        if (this.zzndj.isEmpty() && !(this.zzndj instanceof ArrayList)) {
            this.zzndj = new ArrayList(this.zzndi);
        }
        int i = -(iZza + 1);
        if (i >= this.zzndi) {
            return zzcdl().put(k, v);
        }
        if (this.zzndj.size() == this.zzndi) {
            zzegb zzegbVarRemove = this.zzndj.remove(this.zzndi - 1);
            zzcdl().put((Comparable) zzegbVarRemove.getKey(), zzegbVarRemove.getValue());
        }
        this.zzndj.add(i, new zzegb(this, k, v));
        return null;
    }

    public void zzbht() {
        if (this.zzkff) {
            return;
        }
        this.zzndk = this.zzndk.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzndk);
        this.zzndm = this.zzndm.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzndm);
        this.zzkff = true;
    }

    public final int zzcdi() {
        return this.zzndj.size();
    }

    public final Iterable<Map.Entry<K, V>> zzcdj() {
        return this.zzndk.isEmpty() ? zzefy.zzcdm() : this.zzndk.entrySet();
    }

    public final Map.Entry<K, V> zzgw(int i) {
        return this.zzndj.get(i);
    }
}
