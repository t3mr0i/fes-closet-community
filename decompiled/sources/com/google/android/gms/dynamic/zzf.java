package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/* loaded from: classes.dex */
final class zzf implements View.OnClickListener {
    private /* synthetic */ Context zzanz;
    private /* synthetic */ Intent zzgpc;

    zzf(Context context, Intent intent) {
        this.zzanz = context;
        this.zzgpc = intent;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        try {
            this.zzanz.startActivity(this.zzgpc);
        } catch (ActivityNotFoundException e) {
            Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", e);
        }
    }
}
