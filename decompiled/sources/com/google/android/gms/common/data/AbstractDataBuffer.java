package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {
    protected final DataHolder zzfle;

    protected AbstractDataBuffer(DataHolder dataHolder) {
        this.zzfle = dataHolder;
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    @Deprecated
    public final void close() {
        release();
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public abstract T get(int i);

    @Override // com.google.android.gms.common.data.DataBuffer
    public int getCount() {
        if (this.zzfle == null) {
            return 0;
        }
        return this.zzfle.zzfqp;
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    @Deprecated
    public boolean isClosed() {
        return this.zzfle == null || this.zzfle.isClosed();
    }

    @Override // com.google.android.gms.common.data.DataBuffer, java.lang.Iterable
    public Iterator<T> iterator() {
        return new zzb(this);
    }

    @Override // com.google.android.gms.common.data.DataBuffer, com.google.android.gms.common.api.Releasable
    public void release() {
        if (this.zzfle != null) {
            this.zzfle.close();
        }
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public Iterator<T> singleRefIterator() {
        return new zzh(this);
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public final Bundle zzafi() {
        return this.zzfle.zzafi();
    }
}
