package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.zzbp;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class zzb<T> implements Iterator<T> {
    protected final DataBuffer<T> zzfqd;
    protected int zzfqe = -1;

    public zzb(DataBuffer<T> dataBuffer) {
        this.zzfqd = (DataBuffer) zzbp.zzu(dataBuffer);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.zzfqe < this.zzfqd.getCount() + (-1);
    }

    @Override // java.util.Iterator
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException(new StringBuilder(46).append("Cannot advance the iterator beyond ").append(this.zzfqe).toString());
        }
        DataBuffer<T> dataBuffer = this.zzfqd;
        int i = this.zzfqe + 1;
        this.zzfqe = i;
        return dataBuffer.get(i);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
