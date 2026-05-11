package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.internal.zzaom;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public class ExceptionReporter implements Thread.UncaughtExceptionHandler {
    private final Context mContext;
    private final Thread.UncaughtExceptionHandler zzdjr;
    private final Tracker zzdjs;
    private ExceptionParser zzdjt;
    private GoogleAnalytics zzdju;

    public ExceptionReporter(Tracker tracker, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, Context context) {
        if (tracker == null) {
            throw new NullPointerException("tracker cannot be null");
        }
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.zzdjr = uncaughtExceptionHandler;
        this.zzdjs = tracker;
        this.zzdjt = new StandardExceptionParser(context, new ArrayList());
        this.mContext = context.getApplicationContext();
        String strValueOf = String.valueOf(uncaughtExceptionHandler == null ? "null" : uncaughtExceptionHandler.getClass().getName());
        zzaom.v(strValueOf.length() != 0 ? "ExceptionReporter created, original handler is ".concat(strValueOf) : new String("ExceptionReporter created, original handler is "));
    }

    public ExceptionParser getExceptionParser() {
        return this.zzdjt;
    }

    public void setExceptionParser(ExceptionParser exceptionParser) {
        this.zzdjt = exceptionParser;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) throws ExecutionException, InterruptedException, TimeoutException {
        String description = "UncaughtException";
        if (this.zzdjt != null) {
            description = this.zzdjt.getDescription(thread != null ? thread.getName() : null, th);
        }
        String strValueOf = String.valueOf(description);
        zzaom.v(strValueOf.length() != 0 ? "Reporting uncaught exception: ".concat(strValueOf) : new String("Reporting uncaught exception: "));
        this.zzdjs.send(new HitBuilders.ExceptionBuilder().setDescription(description).setFatal(true).build());
        if (this.zzdju == null) {
            this.zzdju = GoogleAnalytics.getInstance(this.mContext);
        }
        GoogleAnalytics googleAnalytics = this.zzdju;
        googleAnalytics.dispatchLocalHits();
        googleAnalytics.zztr().zzwc().zzvt();
        if (this.zzdjr != null) {
            zzaom.v("Passing exception to the original handler");
            this.zzdjr.uncaughtException(thread, th);
        }
    }

    final Thread.UncaughtExceptionHandler zztv() {
        return this.zzdjr;
    }
}
