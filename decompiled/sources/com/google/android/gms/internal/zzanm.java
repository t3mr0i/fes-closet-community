package com.google.android.gms.internal;

import android.content.Context;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* loaded from: classes.dex */
public final class zzanm extends zzams {
    private volatile String zzdmw;
    private Future<String> zzdqg;

    protected zzanm(zzamu zzamuVar) {
        super(zzamuVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0075 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x008e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v12, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v14, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x008c -> B:61:0x002f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x008e -> B:61:0x002f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x0093 -> B:61:0x002f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String zzbh(android.content.Context r7) throws java.lang.Throwable {
        /*
            r6 = this;
            r0 = 0
            java.lang.String r1 = "ClientId should be loaded from worker thread"
            com.google.android.gms.common.internal.zzbp.zzgh(r1)
            java.lang.String r1 = "gaClientId"
            java.io.FileInputStream r2 = r7.openFileInput(r1)     // Catch: java.io.FileNotFoundException -> L71 java.io.IOException -> L80 java.lang.Throwable -> L99
            r1 = 36
            byte[] r3 = new byte[r1]     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Lab java.io.FileNotFoundException -> Lad
            r1 = 0
            r4 = 36
            int r4 = r2.read(r3, r1, r4)     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Lab java.io.FileNotFoundException -> Lad
            int r1 = r2.available()     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Lab java.io.FileNotFoundException -> Lad
            if (r1 <= 0) goto L37
            java.lang.String r1 = "clientId file seems corrupted, deleting it."
            r6.zzdp(r1)     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Lab java.io.FileNotFoundException -> Lad
            r2.close()     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Lab java.io.FileNotFoundException -> Lad
            java.lang.String r1 = "gaClientId"
            r7.deleteFile(r1)     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Lab java.io.FileNotFoundException -> Lad
            if (r2 == 0) goto L2f
            r2.close()     // Catch: java.io.IOException -> L30
        L2f:
            return r0
        L30:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L2f
        L37:
            r1 = 14
            if (r4 >= r1) goto L55
            java.lang.String r1 = "clientId file is empty, deleting it."
            r6.zzdp(r1)     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Lab java.io.FileNotFoundException -> Lad
            r2.close()     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Lab java.io.FileNotFoundException -> Lad
            java.lang.String r1 = "gaClientId"
            r7.deleteFile(r1)     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Lab java.io.FileNotFoundException -> Lad
            if (r2 == 0) goto L2f
            r2.close()     // Catch: java.io.IOException -> L4e
            goto L2f
        L4e:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L2f
        L55:
            r2.close()     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Lab java.io.FileNotFoundException -> Lad
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Lab java.io.FileNotFoundException -> Lad
            r5 = 0
            r1.<init>(r3, r5, r4)     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Lab java.io.FileNotFoundException -> Lad
            java.lang.String r3 = "Read client id from disk"
            r6.zza(r3, r1)     // Catch: java.lang.Throwable -> La8 java.io.IOException -> Lab java.io.FileNotFoundException -> Lad
            if (r2 == 0) goto L68
            r2.close()     // Catch: java.io.IOException -> L6a
        L68:
            r0 = r1
            goto L2f
        L6a:
            r0 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r0)
            goto L68
        L71:
            r1 = move-exception
            r1 = r0
        L73:
            if (r1 == 0) goto L2f
            r1.close()     // Catch: java.io.IOException -> L79
            goto L2f
        L79:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L2f
        L80:
            r1 = move-exception
            r2 = r0
        L82:
            java.lang.String r3 = "Error reading client id file, deleting it"
            r6.zze(r3, r1)     // Catch: java.lang.Throwable -> La8
            java.lang.String r1 = "gaClientId"
            r7.deleteFile(r1)     // Catch: java.lang.Throwable -> La8
            if (r2 == 0) goto L2f
            r2.close()     // Catch: java.io.IOException -> L92
            goto L2f
        L92:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L2f
        L99:
            r1 = move-exception
            r2 = r0
        L9b:
            if (r2 == 0) goto La0
            r2.close()     // Catch: java.io.IOException -> La1
        La0:
            throw r1
        La1:
            r0 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r0)
            goto La0
        La8:
            r0 = move-exception
            r1 = r0
            goto L9b
        Lab:
            r1 = move-exception
            goto L82
        Lad:
            r1 = move-exception
            r1 = r2
            goto L73
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzanm.zzbh(android.content.Context):java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.String] */
    private final boolean zzu(Context context, String str) throws IOException {
        boolean z = false;
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzgh("ClientId should be saved from worker thread");
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        fileOutputStreamOpenFileOutput = null;
        fileOutputStreamOpenFileOutput = null;
        try {
            try {
                try {
                    zza("Storing clientId", str);
                    fileOutputStreamOpenFileOutput = context.openFileOutput("gaClientId", 0);
                    fileOutputStreamOpenFileOutput.write(str.getBytes());
                    if (fileOutputStreamOpenFileOutput != null) {
                        try {
                            fileOutputStreamOpenFileOutput.close();
                        } catch (IOException e) {
                            zze("Failed to close clientId writing stream", e);
                        }
                    }
                    z = true;
                    fileOutputStreamOpenFileOutput = fileOutputStreamOpenFileOutput;
                } catch (Throwable th) {
                    if (fileOutputStreamOpenFileOutput != null) {
                        try {
                            fileOutputStreamOpenFileOutput.close();
                        } catch (IOException e2) {
                            zze("Failed to close clientId writing stream", e2);
                        }
                    }
                    throw th;
                }
            } catch (IOException e3) {
                zze("Error writing to clientId file", e3);
                fileOutputStreamOpenFileOutput = fileOutputStreamOpenFileOutput;
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                        fileOutputStreamOpenFileOutput = fileOutputStreamOpenFileOutput;
                    } catch (IOException e4) {
                        zze("Failed to close clientId writing stream", e4);
                        fileOutputStreamOpenFileOutput = "Failed to close clientId writing stream";
                    }
                }
            }
        } catch (FileNotFoundException e5) {
            zze("Error creating clientId file", e5);
            fileOutputStreamOpenFileOutput = fileOutputStreamOpenFileOutput;
            if (fileOutputStreamOpenFileOutput != null) {
                try {
                    fileOutputStreamOpenFileOutput.close();
                    fileOutputStreamOpenFileOutput = fileOutputStreamOpenFileOutput;
                } catch (IOException e6) {
                    zze("Failed to close clientId writing stream", e6);
                    fileOutputStreamOpenFileOutput = "Failed to close clientId writing stream";
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zzxs() {
        String lowerCase = UUID.randomUUID().toString().toLowerCase();
        try {
            return !zzu(zzwa().getContext(), lowerCase) ? "0" : lowerCase;
        } catch (Exception e) {
            zze("Error saving clientId file", e);
            return "0";
        }
    }

    @Override // com.google.android.gms.internal.zzams
    protected final void zzuk() {
    }

    public final String zzxp() {
        String str;
        zzwk();
        synchronized (this) {
            if (this.zzdmw == null) {
                this.zzdqg = zzwa().zzc(new zzann(this));
            }
            if (this.zzdqg != null) {
                try {
                    this.zzdmw = this.zzdqg.get();
                } catch (InterruptedException e) {
                    zzd("ClientId loading or generation was interrupted", e);
                    this.zzdmw = "0";
                } catch (ExecutionException e2) {
                    zze("Failed to load or generate client id", e2);
                    this.zzdmw = "0";
                }
                if (this.zzdmw == null) {
                    this.zzdmw = "0";
                }
                zza("Loaded clientId", this.zzdmw);
                this.zzdqg = null;
                str = this.zzdmw;
            } else {
                str = this.zzdmw;
            }
        }
        return str;
    }

    final String zzxq() {
        synchronized (this) {
            this.zzdmw = null;
            this.zzdqg = zzwa().zzc(new zzano(this));
        }
        return zzxp();
    }

    final String zzxr() throws Throwable {
        String strZzbh = zzbh(zzwa().getContext());
        return strZzbh == null ? zzxs() : strZzbh;
    }
}
