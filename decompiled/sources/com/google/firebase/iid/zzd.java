package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Intent;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
final class zzd {
    final Intent intent;
    private final BroadcastReceiver.PendingResult zzmkw;
    private boolean zzmkx = false;
    private final ScheduledFuture<?> zzmky;

    zzd(Intent intent, BroadcastReceiver.PendingResult pendingResult, ScheduledExecutorService scheduledExecutorService) {
        this.intent = intent;
        this.zzmkw = pendingResult;
        this.zzmky = scheduledExecutorService.schedule(new zze(this, intent), 9500L, TimeUnit.MILLISECONDS);
    }

    final synchronized void finish() {
        if (!this.zzmkx) {
            this.zzmkw.finish();
            this.zzmky.cancel(false);
            this.zzmkx = true;
        }
    }
}
