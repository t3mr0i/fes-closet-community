package com.google.firebase.auth;

import android.support.annotation.Nullable;

/* loaded from: classes.dex */
public class GetTokenResult {
    private String zzdxs;

    public GetTokenResult(String str) {
        this.zzdxs = str;
    }

    @Nullable
    public String getToken() {
        return this.zzdxs;
    }
}
