package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public final class zzdbx {
    private static Integer zzkfs = 0;
    private static Integer zzkft = 1;
    private final Context mContext;
    private final ExecutorService zzirz;

    public zzdbx(Context context) {
        this(context, Executors.newSingleThreadExecutor());
    }

    private zzdbx(Context context, ExecutorService executorService) {
        this.mContext = context;
        this.zzirz = executorService;
    }
}
