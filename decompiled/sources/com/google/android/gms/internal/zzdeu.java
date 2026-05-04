package com.google.android.gms.internal;

import android.database.ContentObserver;
import android.os.Handler;

/* loaded from: classes.dex */
final class zzdeu extends ContentObserver {
    zzdeu(Handler handler) {
        super(null);
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        zzdet.zzkxx.set(true);
    }
}
