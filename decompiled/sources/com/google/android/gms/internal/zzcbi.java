package com.google.android.gms.internal;

import java.util.Iterator;

/* loaded from: classes.dex */
final class zzcbi implements Iterator<String> {
    private Iterator<String> zzino;
    private /* synthetic */ zzcbh zzinp;

    zzcbi(zzcbh zzcbhVar) {
        this.zzinp = zzcbhVar;
        this.zzino = this.zzinp.zzinn.keySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzino.hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zzino.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}
