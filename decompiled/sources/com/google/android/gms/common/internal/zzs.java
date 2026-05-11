package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Scope;
import java.util.Collections;
import java.util.Set;

/* loaded from: classes.dex */
public final class zzs {
    public final Set<Scope> zzecl;

    public zzs(Set<Scope> set) {
        zzbp.zzu(set);
        this.zzecl = Collections.unmodifiableSet(set);
    }
}
