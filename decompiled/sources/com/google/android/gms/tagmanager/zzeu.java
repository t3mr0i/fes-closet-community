package com.google.android.gms.tagmanager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
final class zzeu implements zzex {
    zzeu(zzet zzetVar) {
    }

    @Override // com.google.android.gms.tagmanager.zzex
    public final ScheduledExecutorService zzbem() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
