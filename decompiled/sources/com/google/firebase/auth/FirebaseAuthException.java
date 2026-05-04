package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbp;
import com.google.firebase.FirebaseException;

/* loaded from: classes.dex */
public class FirebaseAuthException extends FirebaseException {
    private final String zzliw;

    public FirebaseAuthException(@NonNull String str, @NonNull String str2) {
        super(str2);
        this.zzliw = zzbp.zzgg(str);
    }

    @NonNull
    public String getErrorCode() {
        return this.zzliw;
    }
}
