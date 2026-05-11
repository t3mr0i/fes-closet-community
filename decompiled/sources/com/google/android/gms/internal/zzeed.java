package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
final class zzeed implements Iterator {
    private final int limit;
    private int position = 0;
    private /* synthetic */ zzeec zznbf;

    zzeed(zzeec zzeecVar) {
        this.zznbf = zzeecVar;
        this.limit = this.zznbf.size();
    }

    private final byte nextByte() {
        try {
            zzeec zzeecVar = this.zznbf;
            int i = this.position;
            this.position = i + 1;
            return zzeecVar.zzgk(i);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.position < this.limit;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        return Byte.valueOf(nextByte());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
