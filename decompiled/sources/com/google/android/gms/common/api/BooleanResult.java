package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzbp;

/* loaded from: classes.dex */
public class BooleanResult implements Result {
    private final Status mStatus;
    private final boolean zzfgp;

    public BooleanResult(Status status, boolean z) {
        this.mStatus = (Status) zzbp.zzb(status, "Status must not be null");
        this.zzfgp = z;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BooleanResult)) {
            return false;
        }
        BooleanResult booleanResult = (BooleanResult) obj;
        return this.mStatus.equals(booleanResult.mStatus) && this.zzfgp == booleanResult.zzfgp;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.mStatus;
    }

    public boolean getValue() {
        return this.zzfgp;
    }

    public final int hashCode() {
        return (this.zzfgp ? 1 : 0) + ((this.mStatus.hashCode() + 527) * 31);
    }
}
