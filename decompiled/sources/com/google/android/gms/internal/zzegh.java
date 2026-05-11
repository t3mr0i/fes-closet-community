package com.google.android.gms.internal;

import java.util.List;

/* loaded from: classes.dex */
public final class zzegh extends RuntimeException {
    private final List<String> zzndu;

    public zzegh(zzefq zzefqVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.zzndu = null;
    }

    public final zzefj zzcdp() {
        return new zzefj(getMessage());
    }
}
