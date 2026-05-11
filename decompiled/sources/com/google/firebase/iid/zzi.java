package com.google.firebase.iid;

import android.support.annotation.Nullable;

@Deprecated
/* loaded from: classes.dex */
public final class zzi {
    private final FirebaseInstanceId zzmli;

    private zzi(FirebaseInstanceId firebaseInstanceId) {
        this.zzmli = firebaseInstanceId;
    }

    public static zzi zzbyk() {
        return new zzi(FirebaseInstanceId.getInstance());
    }

    public final String getId() {
        return this.zzmli.getId();
    }

    @Nullable
    public final String getToken() {
        return this.zzmli.getToken();
    }
}
