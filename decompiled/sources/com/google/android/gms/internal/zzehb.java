package com.google.android.gms.internal;

import com.google.android.gms.internal.zzeha;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class zzehb<M extends zzeha<M>, T> {
    public final int tag;
    private int type;
    protected final Class<T> zzmju;
    private zzeev<?, ?> zzncg;
    protected final boolean zzngh;

    private zzehb(int i, Class<T> cls, int i2, boolean z) {
        this(11, cls, null, i2, false);
    }

    private zzehb(int i, Class<T> cls, zzeev<?, ?> zzeevVar, int i2, boolean z) {
        this.type = i;
        this.zzmju = cls;
        this.tag = i2;
        this.zzngh = false;
        this.zzncg = null;
    }

    public static <M extends zzeha<M>, T extends zzehg> zzehb<M, T> zza(int i, Class<T> cls, long j) {
        return new zzehb<>(11, cls, (int) j, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object zzb(zzegx zzegxVar) {
        Class componentType = this.zzngh ? this.zzmju.getComponentType() : this.zzmju;
        try {
            switch (this.type) {
                case 10:
                    zzehg zzehgVar = (zzehg) componentType.newInstance();
                    zzegxVar.zza(zzehgVar, this.tag >>> 3);
                    return zzehgVar;
                case 11:
                    zzehg zzehgVar2 = (zzehg) componentType.newInstance();
                    zzegxVar.zza(zzehgVar2);
                    return zzehgVar2;
                default:
                    throw new IllegalArgumentException(new StringBuilder(24).append("Unknown type ").append(this.type).toString());
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading extension field", e);
        } catch (IllegalAccessException e2) {
            String strValueOf = String.valueOf(componentType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf).length() + 33).append("Error creating instance of class ").append(strValueOf).toString(), e2);
        } catch (InstantiationException e3) {
            String strValueOf2 = String.valueOf(componentType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf2).length() + 33).append("Error creating instance of class ").append(strValueOf2).toString(), e3);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzehb)) {
            return false;
        }
        zzehb zzehbVar = (zzehb) obj;
        return this.type == zzehbVar.type && this.zzmju == zzehbVar.zzmju && this.tag == zzehbVar.tag && this.zzngh == zzehbVar.zzngh;
    }

    public final int hashCode() {
        return (this.zzngh ? 1 : 0) + ((((((this.type + 1147) * 31) + this.zzmju.hashCode()) * 31) + this.tag) * 31);
    }

    protected final void zza(Object obj, zzegy zzegyVar) {
        try {
            zzegyVar.zzhf(this.tag);
            switch (this.type) {
                case 10:
                    int i = this.tag >>> 3;
                    ((zzehg) obj).zza(zzegyVar);
                    zzegyVar.zzu(i, 4);
                    return;
                case 11:
                    zzegyVar.zzb((zzehg) obj);
                    return;
                default:
                    throw new IllegalArgumentException(new StringBuilder(24).append("Unknown type ").append(this.type).toString());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    final T zzav(List<zzehi> list) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (list == null) {
            return null;
        }
        if (!this.zzngh) {
            if (list.isEmpty()) {
                return null;
            }
            return this.zzmju.cast(zzb(zzegx.zzav(list.get(list.size() - 1).zzjaw)));
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            zzehi zzehiVar = list.get(i);
            if (zzehiVar.zzjaw.length != 0) {
                arrayList.add(zzb(zzegx.zzav(zzehiVar.zzjaw)));
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        T tCast = this.zzmju.cast(Array.newInstance(this.zzmju.getComponentType(), size));
        for (int i2 = 0; i2 < size; i2++) {
            Array.set(tCast, i2, arrayList.get(i2));
        }
        return tCast;
    }

    protected final int zzbw(Object obj) {
        int i = this.tag >>> 3;
        switch (this.type) {
            case 10:
                return (zzegy.zzgs(i) << 1) + ((zzehg) obj).zzhi();
            case 11:
                return zzegy.zzb(i, (zzehg) obj);
            default:
                throw new IllegalArgumentException(new StringBuilder(24).append("Unknown type ").append(this.type).toString());
        }
    }
}
