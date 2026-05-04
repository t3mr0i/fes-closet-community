package com.google.android.gms.internal;

import java.io.PrintStream;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public final class zzdiq {
    private static zzdir zzlft;

    static final class zza extends zzdir {
        zza() {
        }

        @Override // com.google.android.gms.internal.zzdir
        public final void zza(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
        }

        @Override // com.google.android.gms.internal.zzdir
        public final void zza(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
        }
    }

    static {
        zzdir zzaVar;
        try {
            Integer numZzbnl = zzbnl();
            if (numZzbnl == null || numZzbnl.intValue() < 19) {
                zzaVar = !Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ? new zzdiu() : new zza();
            } else {
                zzaVar = new zzdiv();
            }
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            String name = zza.class.getName();
            printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
            th.printStackTrace(System.err);
            zzaVar = new zza();
        }
        zzlft = zzaVar;
    }

    public static void zza(Throwable th, PrintStream printStream) {
        zzlft.zza(th, printStream);
    }

    public static void zza(Throwable th, PrintWriter printWriter) {
        zzlft.zza(th, printWriter);
    }

    private static Integer zzbnl() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }
}
