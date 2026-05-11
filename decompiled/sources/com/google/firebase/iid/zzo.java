package com.google.firebase.iid;

import android.content.Intent;
import android.os.ConditionVariable;
import android.util.Log;
import java.io.IOException;

/* loaded from: classes.dex */
final class zzo implements zzp {
    private Intent intent;
    private final ConditionVariable zzmly;
    private String zzmlz;

    private zzo() {
        this.zzmly = new ConditionVariable();
    }

    /* synthetic */ zzo(zzm zzmVar) {
        this();
    }

    @Override // com.google.firebase.iid.zzp
    public final void onError(String str) {
        this.zzmlz = str;
        this.zzmly.open();
    }

    public final Intent zzbyr() throws IOException {
        if (!this.zzmly.block(30000L)) {
            Log.w("InstanceID/Rpc", "No response");
            throw new IOException("TIMEOUT");
        }
        if (this.zzmlz != null) {
            throw new IOException(this.zzmlz);
        }
        return this.intent;
    }

    @Override // com.google.firebase.iid.zzp
    public final void zzq(Intent intent) {
        this.intent = intent;
        this.zzmly.open();
    }
}
