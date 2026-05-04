package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* loaded from: classes.dex */
public class ApiException extends Exception {
    protected final Status mStatus;

    /* JADX WARN: Illegal instructions before constructor call */
    public ApiException(@NonNull Status status) {
        int statusCode = status.getStatusCode();
        String statusMessage = status.getStatusMessage() != null ? status.getStatusMessage() : "";
        super(new StringBuilder(String.valueOf(statusMessage).length() + 13).append(statusCode).append(": ").append(statusMessage).toString());
        this.mStatus = status;
    }

    public int getStatusCode() {
        return this.mStatus.getStatusCode();
    }

    @Nullable
    public String getStatusMessage() {
        return this.mStatus.getStatusMessage();
    }
}
