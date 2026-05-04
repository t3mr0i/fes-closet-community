package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

/* loaded from: classes.dex */
final class zza implements Comparator<Scope> {
    zza() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Scope scope, Scope scope2) {
        return scope.zzaft().compareTo(scope2.zzaft());
    }
}
