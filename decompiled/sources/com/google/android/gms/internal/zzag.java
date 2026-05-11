package com.google.android.gms.internal;

import android.os.SystemClock;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzag implements zzb {
    private final Map<String, zzai> zzbv;
    private long zzbw;
    private final File zzbx;
    private final int zzby;

    public zzag(File file) {
        this(file, 5242880);
    }

    private zzag(File file, int i) {
        this.zzbv = new LinkedHashMap(16, 0.75f, true);
        this.zzbw = 0L;
        this.zzbx = file;
        this.zzby = 5242880;
    }

    private final synchronized void remove(String str) {
        boolean zDelete = zze(str).delete();
        zzai zzaiVar = this.zzbv.get(str);
        if (zzaiVar != null) {
            this.zzbw -= zzaiVar.size;
            this.zzbv.remove(str);
        }
        if (!zDelete) {
            zzab.zzb("Could not delete cache entry for key=%s, filename=%s", str, zzd(str));
        }
    }

    private static int zza(InputStream inputStream) throws IOException {
        int i = inputStream.read();
        if (i == -1) {
            throw new EOFException();
        }
        return i;
    }

    static void zza(OutputStream outputStream, int i) throws IOException {
        outputStream.write(i & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write(i >>> 24);
    }

    static void zza(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) j);
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 32));
        outputStream.write((byte) (j >>> 40));
        outputStream.write((byte) (j >>> 48));
        outputStream.write((byte) (j >>> 56));
    }

    static void zza(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        zza(outputStream, bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    private final void zza(String str, zzai zzaiVar) {
        if (this.zzbv.containsKey(str)) {
            this.zzbw = (zzaiVar.size - this.zzbv.get(str).size) + this.zzbw;
        } else {
            this.zzbw += zzaiVar.size;
        }
        this.zzbv.put(str, zzaiVar);
    }

    private static byte[] zza(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = inputStream.read(bArr, i2, i - i2);
            if (i3 == -1) {
                break;
            }
            i2 += i3;
        }
        if (i2 != i) {
            throw new IOException(new StringBuilder(50).append("Expected ").append(i).append(" bytes, read ").append(i2).append(" bytes").toString());
        }
        return bArr;
    }

    static int zzb(InputStream inputStream) throws IOException {
        return zza(inputStream) | 0 | (zza(inputStream) << 8) | (zza(inputStream) << 16) | (zza(inputStream) << 24);
    }

    static long zzc(InputStream inputStream) throws IOException {
        return 0 | (zza(inputStream) & 255) | ((zza(inputStream) & 255) << 8) | ((zza(inputStream) & 255) << 16) | ((zza(inputStream) & 255) << 24) | ((zza(inputStream) & 255) << 32) | ((zza(inputStream) & 255) << 40) | ((zza(inputStream) & 255) << 48) | ((zza(inputStream) & 255) << 56);
    }

    static String zzd(InputStream inputStream) throws IOException {
        return new String(zza(inputStream, (int) zzc(inputStream)), "UTF-8");
    }

    private static String zzd(String str) {
        int length = str.length() / 2;
        String strValueOf = String.valueOf(String.valueOf(str.substring(0, length).hashCode()));
        String strValueOf2 = String.valueOf(String.valueOf(str.substring(length).hashCode()));
        return strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
    }

    private final File zze(String str) {
        return new File(this.zzbx, zzd(str));
    }

    static Map<String, String> zze(InputStream inputStream) throws IOException {
        int iZzb = zzb(inputStream);
        Map<String, String> mapEmptyMap = iZzb == 0 ? Collections.emptyMap() : new HashMap<>(iZzb);
        for (int i = 0; i < iZzb; i++) {
            mapEmptyMap.put(zzd(inputStream).intern(), zzd(inputStream).intern());
        }
        return mapEmptyMap;
    }

    @Override // com.google.android.gms.internal.zzb
    public final synchronized void initialize() {
        Throwable th;
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2;
        if (this.zzbx.exists()) {
            File[] fileArrListFiles = this.zzbx.listFiles();
            if (fileArrListFiles != null) {
                for (File file : fileArrListFiles) {
                    try {
                        bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                        try {
                            try {
                                zzai zzaiVarZzf = zzai.zzf(bufferedInputStream2);
                                zzaiVarZzf.size = file.length();
                                zza(zzaiVarZzf.key, zzaiVarZzf);
                                try {
                                    bufferedInputStream2.close();
                                } catch (IOException e) {
                                }
                            } catch (IOException e2) {
                                if (file != null) {
                                    file.delete();
                                }
                                if (bufferedInputStream2 != null) {
                                    try {
                                        bufferedInputStream2.close();
                                    } catch (IOException e3) {
                                    }
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedInputStream = bufferedInputStream2;
                            if (bufferedInputStream == null) {
                                throw th;
                            }
                            try {
                                bufferedInputStream.close();
                                throw th;
                            } catch (IOException e4) {
                                throw th;
                            }
                        }
                    } catch (IOException e5) {
                        bufferedInputStream2 = null;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedInputStream = null;
                    }
                }
            }
        } else if (!this.zzbx.mkdirs()) {
            zzab.zzc("Unable to create cache dir %s", this.zzbx.getAbsolutePath());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00ac A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.google.android.gms.internal.zzb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized com.google.android.gms.internal.zzc zza(java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 193
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzag.zza(java.lang.String):com.google.android.gms.internal.zzc");
    }

    @Override // com.google.android.gms.internal.zzb
    public final synchronized void zza(String str, zzc zzcVar) {
        BufferedOutputStream bufferedOutputStream;
        zzai zzaiVar;
        int i;
        int i2 = 0;
        synchronized (this) {
            if (this.zzbw + zzcVar.data.length >= this.zzby) {
                if (zzab.DEBUG) {
                    zzab.zza("Pruning old cache entries.", new Object[0]);
                }
                long j = this.zzbw;
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                Iterator<Map.Entry<String, zzai>> it = this.zzbv.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        i = i2;
                        break;
                    }
                    zzai value = it.next().getValue();
                    if (zze(value.key).delete()) {
                        this.zzbw -= value.size;
                    } else {
                        zzab.zzb("Could not delete cache entry for key=%s, filename=%s", value.key, zzd(value.key));
                    }
                    it.remove();
                    i = i2 + 1;
                    if (this.zzbw + r2 < this.zzby * 0.9f) {
                        break;
                    } else {
                        i2 = i;
                    }
                }
                if (zzab.DEBUG) {
                    zzab.zza("pruned %d files, %d bytes, %d ms", Integer.valueOf(i), Long.valueOf(this.zzbw - j), Long.valueOf(SystemClock.elapsedRealtime() - jElapsedRealtime));
                }
            }
            File fileZze = zze(str);
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileZze));
                zzaiVar = new zzai(str, zzcVar);
            } catch (IOException e) {
                if (!fileZze.delete()) {
                    zzab.zzb("Could not clean up file %s", fileZze.getAbsolutePath());
                }
            }
            if (!zzaiVar.zza(bufferedOutputStream)) {
                bufferedOutputStream.close();
                zzab.zzb("Failed to write header for %s", fileZze.getAbsolutePath());
                throw new IOException();
            }
            bufferedOutputStream.write(zzcVar.data);
            bufferedOutputStream.close();
            zza(str, zzaiVar);
        }
    }
}
