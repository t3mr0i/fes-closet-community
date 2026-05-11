package com.google.android.gms.internal;

import java.io.PrintStream;
import java.io.PrintWriter;

/* loaded from: classes.dex */
final class zzdiv extends zzdir {
    zzdiv() {
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
