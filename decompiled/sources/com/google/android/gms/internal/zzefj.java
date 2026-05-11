package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
public class zzefj extends IOException {
    private zzefq zzndb;

    public zzefj(String str) {
        super(str);
        this.zzndb = null;
    }

    static zzefj zzcdc() {
        return new zzefj("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzefj zzcdd() {
        return new zzefj("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzefk zzcde() {
        return new zzefk("Protocol message tag had invalid wire type.");
    }

    static zzefj zzcdf() {
        return new zzefj("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    public final zzefj zze(zzefq zzefqVar) {
        this.zzndb = zzefqVar;
        return this;
    }
}
