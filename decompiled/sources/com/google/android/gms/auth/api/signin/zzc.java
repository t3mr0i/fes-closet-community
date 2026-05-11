package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

/* loaded from: classes.dex */
final class zzc implements Comparator<Scope> {
    zzc() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Scope scope, Scope scope2) {
        return scope.zzaft().compareTo(scope2.zzaft());
    }
}
