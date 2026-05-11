package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

/* loaded from: classes.dex */
public class zzam implements Releasable, Result {
    private Status mStatus;
    protected final DataHolder zzfle;

    protected zzam(DataHolder dataHolder, Status status) {
        this.mStatus = status;
        this.zzfle = dataHolder;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.mStatus;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public void release() {
        if (this.zzfle != null) {
            this.zzfle.close();
        }
    }
}
