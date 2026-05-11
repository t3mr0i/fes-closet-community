package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzdiq;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes.dex */
final class zzca extends Thread implements zzbz {
    private static zzca zzjrv;
    private volatile boolean mClosed;
    private final Context mContext;
    private volatile boolean zzcgu;
    private final LinkedBlockingQueue<Runnable> zzjru;
    private volatile zzcc zzjrw;

    private zzca(Context context) {
        super("GAThread");
        this.zzjru = new LinkedBlockingQueue<>();
        this.zzcgu = false;
        this.mClosed = false;
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        start();
    }

    static zzca zzdw(Context context) {
        if (zzjrv == null) {
            zzjrv = new zzca(context);
        }
        return zzjrv;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        while (true) {
            boolean z = this.mClosed;
            try {
                try {
                    Runnable runnableTake = this.zzjru.take();
                    if (!this.zzcgu) {
                        runnableTake.run();
                    }
                } catch (InterruptedException e) {
                    zzdj.zzcq(e.toString());
                }
            } catch (Throwable th) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                PrintStream printStream = new PrintStream(byteArrayOutputStream);
                zzdiq.zza(th, printStream);
                printStream.flush();
                String strValueOf = String.valueOf(new String(byteArrayOutputStream.toByteArray()));
                zzdj.e(strValueOf.length() != 0 ? "Error on Google TagManager Thread: ".concat(strValueOf) : new String("Error on Google TagManager Thread: "));
                zzdj.e("Google TagManager is shutting down.");
                this.zzcgu = true;
            }
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbz
    public final void zzk(Runnable runnable) {
        this.zzjru.add(runnable);
    }

    @Override // com.google.android.gms.tagmanager.zzbz
    public final void zzls(String str) {
        zzk(new zzcb(this, this, System.currentTimeMillis(), str));
    }
}
