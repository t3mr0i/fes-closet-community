package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
final class zzehd implements Cloneable {
    private Object value;
    private zzehb<?, ?> zzngm;
    private List<zzehi> zzngn = new ArrayList();

    zzehd() {
    }

    private final byte[] toByteArray() throws IOException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        byte[] bArr = new byte[zzn()];
        zza(zzegy.zzaw(bArr));
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zzcej, reason: merged with bridge method [inline-methods] */
    public zzehd clone() {
        int i = 0;
        zzehd zzehdVar = new zzehd();
        try {
            zzehdVar.zzngm = this.zzngm;
            if (this.zzngn == null) {
                zzehdVar.zzngn = null;
            } else {
                zzehdVar.zzngn.addAll(this.zzngn);
            }
            if (this.value != null) {
                if (this.value instanceof zzehg) {
                    zzehdVar.value = (zzehg) ((zzehg) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzehdVar.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    byte[][] bArr2 = new byte[bArr.length][];
                    zzehdVar.value = bArr2;
                    for (int i2 = 0; i2 < bArr.length; i2++) {
                        bArr2[i2] = (byte[]) bArr[i2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    zzehdVar.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    zzehdVar.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    zzehdVar.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    zzehdVar.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    zzehdVar.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzehg[]) {
                    zzehg[] zzehgVarArr = (zzehg[]) this.value;
                    zzehg[] zzehgVarArr2 = new zzehg[zzehgVarArr.length];
                    zzehdVar.value = zzehgVarArr2;
                    while (true) {
                        int i3 = i;
                        if (i3 >= zzehgVarArr.length) {
                            break;
                        }
                        zzehgVarArr2[i3] = (zzehg) zzehgVarArr[i3].clone();
                        i = i3 + 1;
                    }
                }
            }
            return zzehdVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzehd)) {
            return false;
        }
        zzehd zzehdVar = (zzehd) obj;
        if (this.value != null && zzehdVar.value != null) {
            if (this.zzngm == zzehdVar.zzngm) {
                return !this.zzngm.zzmju.isArray() ? this.value.equals(zzehdVar.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzehdVar.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzehdVar.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzehdVar.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzehdVar.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzehdVar.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzehdVar.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzehdVar.value);
            }
            return false;
        }
        if (this.zzngn != null && zzehdVar.zzngn != null) {
            return this.zzngn.equals(zzehdVar.zzngn);
        }
        try {
            return Arrays.equals(toByteArray(), zzehdVar.toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    final void zza(zzegy zzegyVar) throws IOException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (this.value == null) {
            for (zzehi zzehiVar : this.zzngn) {
                zzegyVar.zzhf(zzehiVar.tag);
                zzegyVar.zzay(zzehiVar.zzjaw);
            }
            return;
        }
        zzehb<?, ?> zzehbVar = this.zzngm;
        Object obj = this.value;
        if (!zzehbVar.zzngh) {
            zzehbVar.zza(obj, zzegyVar);
            return;
        }
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                zzehbVar.zza(obj2, zzegyVar);
            }
        }
    }

    final void zza(zzehi zzehiVar) {
        this.zzngn.add(zzehiVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    final <T> T zzb(zzehb<?, T> zzehbVar) {
        if (this.value == null) {
            this.zzngm = zzehbVar;
            this.value = zzehbVar.zzav(this.zzngn);
            this.zzngn = null;
        } else if (!this.zzngm.equals(zzehbVar)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return (T) this.value;
    }

    final int zzn() {
        int length = 0;
        if (this.value == null) {
            for (zzehi zzehiVar : this.zzngn) {
                length = zzehiVar.zzjaw.length + zzegy.zzhg(zzehiVar.tag) + 0 + length;
            }
            return length;
        }
        zzehb<?, ?> zzehbVar = this.zzngm;
        Object obj = this.value;
        if (!zzehbVar.zzngh) {
            return zzehbVar.zzbw(obj);
        }
        int length2 = Array.getLength(obj);
        int iZzbw = 0;
        for (int i = 0; i < length2; i++) {
            if (Array.get(obj, i) != null) {
                iZzbw += zzehbVar.zzbw(Array.get(obj, i));
            }
        }
        return iZzbw;
    }
}
