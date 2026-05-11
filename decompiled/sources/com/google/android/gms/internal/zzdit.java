package com.google.android.gms.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
final class zzdit extends WeakReference<Throwable> {
    private final int zzlfx;

    public zzdit(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, null);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zzlfx = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzdit zzditVar = (zzdit) obj;
        return this.zzlfx == zzditVar.zzlfx && get() == zzditVar.get();
    }

    public final int hashCode() {
        return this.zzlfx;
    }
}
