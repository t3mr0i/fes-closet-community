package com.google.android.gms.tagmanager;

/* loaded from: classes.dex */
final class zzgj extends Number implements Comparable<zzgj> {
    private double zzjwe;
    private long zzjwf;
    private boolean zzjwg = false;

    private zzgj(double d) {
        this.zzjwe = d;
    }

    private zzgj(long j) {
        this.zzjwf = j;
    }

    public static zzgj zza(Double d) {
        return new zzgj(d.doubleValue());
    }

    public static zzgj zzbh(long j) {
        return new zzgj(j);
    }

    public static zzgj zzmd(String str) throws NumberFormatException {
        try {
            return new zzgj(Long.parseLong(str));
        } catch (NumberFormatException e) {
            try {
                return new zzgj(Double.parseDouble(str));
            } catch (NumberFormatException e2) {
                throw new NumberFormatException(String.valueOf(str).concat(" is not a valid TypedNumber"));
            }
        }
    }

    @Override // java.lang.Number
    public final byte byteValue() {
        return (byte) longValue();
    }

    @Override // java.lang.Number
    public final double doubleValue() {
        return this.zzjwg ? this.zzjwf : this.zzjwe;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzgj) && compareTo((zzgj) obj) == 0;
    }

    @Override // java.lang.Number
    public final float floatValue() {
        return (float) doubleValue();
    }

    public final int hashCode() {
        return new Long(longValue()).hashCode();
    }

    @Override // java.lang.Number
    public final int intValue() {
        return (int) longValue();
    }

    @Override // java.lang.Number
    public final long longValue() {
        return this.zzjwg ? this.zzjwf : (long) this.zzjwe;
    }

    @Override // java.lang.Number
    public final short shortValue() {
        return (short) longValue();
    }

    public final String toString() {
        return this.zzjwg ? Long.toString(this.zzjwf) : Double.toString(this.zzjwe);
    }

    @Override // java.lang.Comparable
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final int compareTo(zzgj zzgjVar) {
        return (this.zzjwg && zzgjVar.zzjwg) ? new Long(this.zzjwf).compareTo(Long.valueOf(zzgjVar.zzjwf)) : Double.compare(doubleValue(), zzgjVar.doubleValue());
    }

    public final boolean zzbfe() {
        return !this.zzjwg;
    }

    public final boolean zzbff() {
        return this.zzjwg;
    }
}
