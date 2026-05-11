package com.google.android.gms.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class zzeer {
    private static volatile boolean zznbv = false;
    private static final Class<?> zznbw = zzccq();
    static final zzeer zznbx = new zzeer(true);
    private final Map<Object, Object> zznby;

    zzeer() {
        this.zznby = new HashMap();
    }

    private zzeer(boolean z) {
        this.zznby = Collections.emptyMap();
    }

    private static Class<?> zzccq() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static zzeer zzccr() {
        return zzeeq.zzccp();
    }
}
