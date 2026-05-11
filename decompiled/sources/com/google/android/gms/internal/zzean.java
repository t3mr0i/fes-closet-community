package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class zzean {
    private static final AtomicReference<zzean> zzlgm = new AtomicReference<>();

    private zzean(Context context) {
    }

    @Nullable
    public static zzean zzbyu() {
        return zzlgm.get();
    }

    public static Set<String> zzbyv() {
        return Collections.emptySet();
    }

    public static void zze(@NonNull FirebaseApp firebaseApp) {
    }

    public static zzean zzep(Context context) {
        zzlgm.compareAndSet(null, new zzean(context));
        return zzlgm.get();
    }

    public static FirebaseOptions zzql(@NonNull String str) {
        return null;
    }
}
