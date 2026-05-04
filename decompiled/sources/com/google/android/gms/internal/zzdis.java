package com.google.android.gms.internal;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
final class zzdis {
    private final ConcurrentHashMap<zzdit, List<Throwable>> zzlfv = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzlfw = new ReferenceQueue<>();

    zzdis() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        Reference<? extends Throwable> referencePoll = this.zzlfw.poll();
        while (referencePoll != null) {
            this.zzlfv.remove(referencePoll);
            referencePoll = this.zzlfw.poll();
        }
        return this.zzlfv.get(new zzdit(th, null));
    }
}
