package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class zzs {
    private AtomicInteger zzaw;
    private final Map<String, Queue<zzp<?>>> zzax;
    private final Set<zzp<?>> zzay;
    private final PriorityBlockingQueue<zzp<?>> zzaz;
    private final PriorityBlockingQueue<zzp<?>> zzba;
    private zzl[] zzbb;
    private zzd zzbc;
    private List<Object> zzbd;
    private final zzb zzi;
    private final zzw zzj;
    private final zzk zzx;

    public zzs(zzb zzbVar, zzk zzkVar) {
        this(zzbVar, zzkVar, 4);
    }

    private zzs(zzb zzbVar, zzk zzkVar, int i) {
        this(zzbVar, zzkVar, 4, new zzh(new Handler(Looper.getMainLooper())));
    }

    private zzs(zzb zzbVar, zzk zzkVar, int i, zzw zzwVar) {
        this.zzaw = new AtomicInteger();
        this.zzax = new HashMap();
        this.zzay = new HashSet();
        this.zzaz = new PriorityBlockingQueue<>();
        this.zzba = new PriorityBlockingQueue<>();
        this.zzbd = new ArrayList();
        this.zzi = zzbVar;
        this.zzx = zzkVar;
        this.zzbb = new zzl[4];
        this.zzj = zzwVar;
    }

    public final void start() {
        if (this.zzbc != null) {
            this.zzbc.quit();
        }
        for (int i = 0; i < this.zzbb.length; i++) {
            if (this.zzbb[i] != null) {
                this.zzbb[i].quit();
            }
        }
        this.zzbc = new zzd(this.zzaz, this.zzba, this.zzi, this.zzj);
        this.zzbc.start();
        for (int i2 = 0; i2 < this.zzbb.length; i2++) {
            zzl zzlVar = new zzl(this.zzba, this.zzx, this.zzi, this.zzj);
            this.zzbb[i2] = zzlVar;
            zzlVar.start();
        }
    }

    public final <T> zzp<T> zzc(zzp<T> zzpVar) {
        zzpVar.zza(this);
        synchronized (this.zzay) {
            this.zzay.add(zzpVar);
        }
        zzpVar.zza(this.zzaw.incrementAndGet());
        zzpVar.zzb("add-to-queue");
        if (zzpVar.zzh()) {
            synchronized (this.zzax) {
                String strZzd = zzpVar.zzd();
                if (this.zzax.containsKey(strZzd)) {
                    Queue<zzp<?>> linkedList = this.zzax.get(strZzd);
                    if (linkedList == null) {
                        linkedList = new LinkedList<>();
                    }
                    linkedList.add(zzpVar);
                    this.zzax.put(strZzd, linkedList);
                    if (zzab.DEBUG) {
                        zzab.zza("Request for cacheKey=%s is in flight, putting on hold.", strZzd);
                    }
                } else {
                    this.zzax.put(strZzd, null);
                    this.zzaz.add(zzpVar);
                }
            }
        } else {
            this.zzba.add(zzpVar);
        }
        return zzpVar;
    }

    final <T> void zzd(zzp<T> zzpVar) {
        synchronized (this.zzay) {
            this.zzay.remove(zzpVar);
        }
        synchronized (this.zzbd) {
            Iterator<Object> it = this.zzbd.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        if (zzpVar.zzh()) {
            synchronized (this.zzax) {
                String strZzd = zzpVar.zzd();
                Queue<zzp<?>> queueRemove = this.zzax.remove(strZzd);
                if (queueRemove != null) {
                    if (zzab.DEBUG) {
                        zzab.zza("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(queueRemove.size()), strZzd);
                    }
                    this.zzaz.addAll(queueRemove);
                }
            }
        }
    }
}
