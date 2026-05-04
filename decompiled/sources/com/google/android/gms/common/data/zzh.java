package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public final class zzh<T> extends zzb<T> {
    private T zzfqz;

    public zzh(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    @Override // com.google.android.gms.common.data.zzb, java.util.Iterator
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException(new StringBuilder(46).append("Cannot advance the iterator beyond ").append(this.zzfqe).toString());
        }
        this.zzfqe++;
        if (this.zzfqe == 0) {
            this.zzfqz = this.zzfqd.get(0);
            if (!(this.zzfqz instanceof zzc)) {
                String strValueOf = String.valueOf(this.zzfqz.getClass());
                throw new IllegalStateException(new StringBuilder(String.valueOf(strValueOf).length() + 44).append("DataBuffer reference of type ").append(strValueOf).append(" is not movable").toString());
            }
        } else {
            ((zzc) this.zzfqz).zzbv(this.zzfqe);
        }
        return this.zzfqz;
    }
}
