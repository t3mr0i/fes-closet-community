package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public abstract class zzo extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected volatile boolean mStarted;
    protected final GoogleApiAvailability zzfhk;
    protected final AtomicReference<zzp> zzfiv;
    private final Handler zzfiw;

    protected zzo(zzcg zzcgVar) {
        this(zzcgVar, GoogleApiAvailability.getInstance());
    }

    private zzo(zzcg zzcgVar, GoogleApiAvailability googleApiAvailability) {
        super(zzcgVar);
        this.zzfiv = new AtomicReference<>(null);
        this.zzfiw = new Handler(Looper.getMainLooper());
        this.zzfhk = googleApiAvailability;
    }

    private static int zza(@Nullable zzp zzpVar) {
        if (zzpVar == null) {
            return -1;
        }
        return zzpVar.zzagc();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0011  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0014  */
    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onActivityResult(int r7, int r8, android.content.Intent r9) {
        /*
            r6 = this;
            r5 = 18
            r1 = 13
            r2 = 1
            r3 = 0
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.common.api.internal.zzp> r0 = r6.zzfiv
            java.lang.Object r0 = r0.get()
            com.google.android.gms.common.api.internal.zzp r0 = (com.google.android.gms.common.api.internal.zzp) r0
            switch(r7) {
                case 1: goto L34;
                case 2: goto L18;
                default: goto L11;
            }
        L11:
            r1 = r3
        L12:
            if (r1 == 0) goto L5a
            r6.zzagb()
        L17:
            return
        L18:
            com.google.android.gms.common.GoogleApiAvailability r1 = r6.zzfhk
            android.app.Activity r4 = r6.getActivity()
            int r4 = r1.isGooglePlayServicesAvailable(r4)
            if (r4 != 0) goto L68
            r1 = r2
        L25:
            if (r0 == 0) goto L17
            com.google.android.gms.common.ConnectionResult r2 = r0.zzagd()
            int r2 = r2.getErrorCode()
            if (r2 != r5) goto L12
            if (r4 != r5) goto L12
            goto L17
        L34:
            r4 = -1
            if (r8 != r4) goto L39
            r1 = r2
            goto L12
        L39:
            if (r8 != 0) goto L11
            if (r9 == 0) goto L43
            java.lang.String r2 = "<<ResolutionFailureErrorDetail>>"
            int r1 = r9.getIntExtra(r2, r1)
        L43:
            com.google.android.gms.common.api.internal.zzp r2 = new com.google.android.gms.common.api.internal.zzp
            com.google.android.gms.common.ConnectionResult r4 = new com.google.android.gms.common.ConnectionResult
            r5 = 0
            r4.<init>(r1, r5)
            int r0 = zza(r0)
            r2.<init>(r4, r0)
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.common.api.internal.zzp> r0 = r6.zzfiv
            r0.set(r2)
            r0 = r2
            r1 = r3
            goto L12
        L5a:
            if (r0 == 0) goto L17
            com.google.android.gms.common.ConnectionResult r1 = r0.zzagd()
            int r0 = r0.zzagc()
            r6.zza(r1, r0)
            goto L17
        L68:
            r1 = r3
            goto L25
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzo.onActivityResult(int, int, android.content.Intent):void");
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        zza(new ConnectionResult(13, null), zza(this.zzfiv.get()));
        zzagb();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zzfiv.set(bundle.getBoolean("resolving_error", false) ? new zzp(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zzp zzpVar = this.zzfiv.get();
        if (zzpVar != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zzpVar.zzagc());
            bundle.putInt("failed_status", zzpVar.zzagd().getErrorCode());
            bundle.putParcelable("failed_resolution", zzpVar.zzagd().getResolution());
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    protected abstract void zza(ConnectionResult connectionResult, int i);

    protected abstract void zzafw();

    protected final void zzagb() {
        this.zzfiv.set(null);
        zzafw();
    }

    public final void zzb(ConnectionResult connectionResult, int i) {
        zzp zzpVar = new zzp(connectionResult, i);
        if (this.zzfiv.compareAndSet(null, zzpVar)) {
            this.zzfiw.post(new zzq(this, zzpVar));
        }
    }
}
