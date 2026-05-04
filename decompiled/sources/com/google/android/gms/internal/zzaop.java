package com.google.android.gms.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes.dex */
final class zzaop extends zzams {
    private static final byte[] zzdtl = "\n".getBytes();
    private final String zzbwh;
    private final zzaoz zzdtk;

    zzaop(zzamu zzamuVar) {
        super(zzamuVar);
        this.zzbwh = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", "GoogleAnalytics", zzamt.VERSION, Build.VERSION.RELEASE, zzapd.zza(Locale.getDefault()), Build.MODEL, Build.ID);
        this.zzdtk = new zzaoz(zzamuVar.zzvx());
    }

    private final int zza(URL url) {
        com.google.android.gms.common.internal.zzbp.zzu(url);
        zzb("GET request", url);
        HttpURLConnection httpURLConnectionZzb = null;
        try {
            try {
                httpURLConnectionZzb = zzb(url);
                httpURLConnectionZzb.connect();
                zzb(httpURLConnectionZzb);
                int responseCode = httpURLConnectionZzb.getResponseCode();
                if (responseCode == 200) {
                    zzwc().zzvv();
                }
                zzb("GET status", Integer.valueOf(responseCode));
                if (httpURLConnectionZzb == null) {
                    return responseCode;
                }
                httpURLConnectionZzb.disconnect();
                return responseCode;
            } catch (IOException e) {
                zzd("Network GET connection error", e);
                if (httpURLConnectionZzb != null) {
                    httpURLConnectionZzb.disconnect();
                }
                return 0;
            }
        } catch (Throwable th) {
            if (httpURLConnectionZzb != null) {
                httpURLConnectionZzb.disconnect();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zza(java.net.URL r6, byte[] r7) throws java.lang.Throwable {
        /*
            r5 = this;
            r2 = 0
            com.google.android.gms.common.internal.zzbp.zzu(r6)
            com.google.android.gms.common.internal.zzbp.zzu(r7)
            java.lang.String r0 = "POST bytes, url"
            int r1 = r7.length
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r5.zzb(r0, r1, r6)
            boolean r0 = zzqu()
            if (r0 == 0) goto L21
            java.lang.String r0 = "Post payload\n"
            java.lang.String r1 = new java.lang.String
            r1.<init>(r7)
            r5.zza(r0, r1)
        L21:
            android.content.Context r0 = r5.getContext()     // Catch: java.io.IOException -> L6b java.lang.Throwable -> L86
            r0.getPackageName()     // Catch: java.io.IOException -> L6b java.lang.Throwable -> L86
            java.net.HttpURLConnection r3 = r5.zzb(r6)     // Catch: java.io.IOException -> L6b java.lang.Throwable -> L86
            r0 = 1
            r3.setDoOutput(r0)     // Catch: java.lang.Throwable -> L9b java.io.IOException -> La0
            int r0 = r7.length     // Catch: java.lang.Throwable -> L9b java.io.IOException -> La0
            r3.setFixedLengthStreamingMode(r0)     // Catch: java.lang.Throwable -> L9b java.io.IOException -> La0
            r3.connect()     // Catch: java.lang.Throwable -> L9b java.io.IOException -> La0
            java.io.OutputStream r1 = r3.getOutputStream()     // Catch: java.lang.Throwable -> L9b java.io.IOException -> La0
            r1.write(r7)     // Catch: java.lang.Throwable -> L9e java.io.IOException -> La3
            r5.zzb(r3)     // Catch: java.lang.Throwable -> L9e java.io.IOException -> La3
            int r0 = r3.getResponseCode()     // Catch: java.lang.Throwable -> L9e java.io.IOException -> La3
            r2 = 200(0xc8, float:2.8E-43)
            if (r0 != r2) goto L50
            com.google.android.gms.internal.zzamj r2 = r5.zzwc()     // Catch: java.lang.Throwable -> L9e java.io.IOException -> La3
            r2.zzvv()     // Catch: java.lang.Throwable -> L9e java.io.IOException -> La3
        L50:
            java.lang.String r2 = "POST status"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> L9e java.io.IOException -> La3
            r5.zzb(r2, r4)     // Catch: java.lang.Throwable -> L9e java.io.IOException -> La3
            if (r1 == 0) goto L5e
            r1.close()     // Catch: java.io.IOException -> L64
        L5e:
            if (r3 == 0) goto L63
            r3.disconnect()
        L63:
            return r0
        L64:
            r1 = move-exception
            java.lang.String r2 = "Error closing http post connection output stream"
            r5.zze(r2, r1)
            goto L5e
        L6b:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L6e:
            java.lang.String r2 = "Network POST connection error"
            r5.zzd(r2, r0)     // Catch: java.lang.Throwable -> L9e
            if (r1 == 0) goto L78
            r1.close()     // Catch: java.io.IOException -> L7f
        L78:
            if (r3 == 0) goto L7d
            r3.disconnect()
        L7d:
            r0 = 0
            goto L63
        L7f:
            r0 = move-exception
            java.lang.String r1 = "Error closing http post connection output stream"
            r5.zze(r1, r0)
            goto L78
        L86:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L89:
            if (r1 == 0) goto L8e
            r1.close()     // Catch: java.io.IOException -> L94
        L8e:
            if (r3 == 0) goto L93
            r3.disconnect()
        L93:
            throw r0
        L94:
            r1 = move-exception
            java.lang.String r2 = "Error closing http post connection output stream"
            r5.zze(r2, r1)
            goto L8e
        L9b:
            r0 = move-exception
            r1 = r2
            goto L89
        L9e:
            r0 = move-exception
            goto L89
        La0:
            r0 = move-exception
            r1 = r2
            goto L6e
        La3:
            r0 = move-exception
            goto L6e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaop.zza(java.net.URL, byte[]):int");
    }

    private static void zza(StringBuilder sb, String str, String str2) throws UnsupportedEncodingException {
        if (sb.length() != 0) {
            sb.append('&');
        }
        sb.append(URLEncoder.encode(str, "UTF-8"));
        sb.append('=');
        sb.append(URLEncoder.encode(str2, "UTF-8"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v18, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.String] */
    private final int zzb(URL url, byte[] bArr) throws Throwable {
        OutputStream outputStream;
        HttpURLConnection httpURLConnectionZzb;
        OutputStream outputStream2;
        int responseCode;
        byte[] byteArray;
        com.google.android.gms.common.internal.zzbp.zzu(url);
        com.google.android.gms.common.internal.zzbp.zzu(bArr);
        try {
            try {
                getContext().getPackageName();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.close();
                byteArrayOutputStream.close();
                byteArray = byteArrayOutputStream.toByteArray();
                zza("POST compressed size, ratio %, url", Integer.valueOf(byteArray.length), Long.valueOf((100 * byteArray.length) / bArr.length), url);
                if (byteArray.length > bArr.length) {
                    zzc("Compressed payload is larger then uncompressed. compressed, uncompressed", Integer.valueOf(byteArray.length), Integer.valueOf(bArr.length));
                }
                if (zzqu()) {
                    String strValueOf = String.valueOf(new String(bArr));
                    zza("Post payload", strValueOf.length() != 0 ? "\n".concat(strValueOf) : new String("\n"));
                }
                httpURLConnectionZzb = zzb(url);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
            outputStream2 = null;
            httpURLConnectionZzb = null;
        } catch (Throwable th2) {
            th = th2;
            outputStream = null;
            httpURLConnectionZzb = null;
        }
        try {
            httpURLConnectionZzb.setDoOutput(true);
            httpURLConnectionZzb.addRequestProperty("Content-Encoding", "gzip");
            httpURLConnectionZzb.setFixedLengthStreamingMode(byteArray.length);
            httpURLConnectionZzb.connect();
            outputStream2 = httpURLConnectionZzb.getOutputStream();
            try {
                outputStream2.write(byteArray);
                outputStream2.close();
                zzb(httpURLConnectionZzb);
                responseCode = httpURLConnectionZzb.getResponseCode();
                if (responseCode == 200) {
                    zzwc().zzvv();
                }
                ?? r1 = "POST status";
                zzb("POST status", Integer.valueOf(responseCode));
                outputStream = r1;
                if (httpURLConnectionZzb != null) {
                    httpURLConnectionZzb.disconnect();
                    outputStream = r1;
                }
            } catch (IOException e2) {
                e = e2;
                zzd("Network compressed POST connection error", e);
                OutputStream outputStream3 = outputStream2;
                if (outputStream2 != null) {
                    try {
                        outputStream2.close();
                        outputStream3 = outputStream2;
                    } catch (IOException e3) {
                        zze("Error closing http compressed post connection output stream", e3);
                        outputStream3 = "Error closing http compressed post connection output stream";
                    }
                }
                if (httpURLConnectionZzb != null) {
                    httpURLConnectionZzb.disconnect();
                }
                responseCode = 0;
                outputStream = outputStream3;
                return responseCode;
            }
        } catch (IOException e4) {
            e = e4;
            outputStream2 = null;
        } catch (Throwable th3) {
            th = th3;
            outputStream = null;
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e5) {
                    zze("Error closing http compressed post connection output stream", e5);
                }
            }
            if (httpURLConnectionZzb != null) {
                httpURLConnectionZzb.disconnect();
            }
            throw th;
        }
        return responseCode;
    }

    private final HttpURLConnection zzb(URL url) throws IOException {
        URLConnection uRLConnectionOpenConnection = url.openConnection();
        if (!(uRLConnectionOpenConnection instanceof HttpURLConnection)) {
            throw new IOException("Failed to obtain http connection");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setConnectTimeout(zzaod.zzdsc.get().intValue());
        httpURLConnection.setReadTimeout(zzaod.zzdsd.get().intValue());
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestProperty("User-Agent", this.zzbwh);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    private final URL zzb(zzaoi zzaoiVar, String str) {
        String string;
        if (zzaoiVar.zzyp()) {
            String strZzyb = zzanv.zzyb();
            String strZzyd = zzanv.zzyd();
            string = new StringBuilder(String.valueOf(strZzyb).length() + 1 + String.valueOf(strZzyd).length() + String.valueOf(str).length()).append(strZzyb).append(strZzyd).append("?").append(str).toString();
        } else {
            String strZzyc = zzanv.zzyc();
            String strZzyd2 = zzanv.zzyd();
            string = new StringBuilder(String.valueOf(strZzyc).length() + 1 + String.valueOf(strZzyd2).length() + String.valueOf(str).length()).append(strZzyc).append(strZzyd2).append("?").append(str).toString();
        }
        try {
            return new URL(string);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private final void zzb(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getInputStream();
            do {
            } while (inputStream.read(new byte[1024]) > 0);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    zze("Error closing http connection input stream", e);
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    zze("Error closing http connection input stream", e2);
                }
            }
            throw th;
        }
    }

    private final URL zzd(zzaoi zzaoiVar) {
        String strConcat;
        if (zzaoiVar.zzyp()) {
            String strValueOf = String.valueOf(zzanv.zzyb());
            String strValueOf2 = String.valueOf(zzanv.zzyd());
            strConcat = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
        } else {
            String strValueOf3 = String.valueOf(zzanv.zzyc());
            String strValueOf4 = String.valueOf(zzanv.zzyd());
            strConcat = strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3);
        }
        try {
            return new URL(strConcat);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private final List<Long> zzt(List<zzaoi> list) {
        boolean z;
        ArrayList arrayList = new ArrayList(list.size());
        for (zzaoi zzaoiVar : list) {
            com.google.android.gms.common.internal.zzbp.zzu(zzaoiVar);
            String strZza = zza(zzaoiVar, !zzaoiVar.zzyp());
            if (strZza == null) {
                zzvy().zza(zzaoiVar, "Error formatting hit for upload");
                z = true;
            } else if (strZza.length() <= zzaod.zzdrs.get().intValue()) {
                URL urlZzb = zzb(zzaoiVar, strZza);
                if (urlZzb == null) {
                    zzdq("Failed to build collect GET endpoint url");
                    z = false;
                } else {
                    z = zza(urlZzb) == 200;
                }
            } else {
                String strZza2 = zza(zzaoiVar, false);
                if (strZza2 == null) {
                    zzvy().zza(zzaoiVar, "Error formatting hit for POST upload");
                    z = true;
                } else {
                    byte[] bytes = strZza2.getBytes();
                    if (bytes.length > zzaod.zzdrx.get().intValue()) {
                        zzvy().zza(zzaoiVar, "Hit payload exceeds size limit");
                        z = true;
                    } else {
                        URL urlZzd = zzd(zzaoiVar);
                        if (urlZzd == null) {
                            zzdq("Failed to build collect POST endpoint url");
                        } else if (zza(urlZzd, bytes) == 200) {
                            z = true;
                        }
                        z = false;
                    }
                }
            }
            if (!z) {
                break;
            }
            arrayList.add(Long.valueOf(zzaoiVar.zzym()));
            if (arrayList.size() >= zzanv.zzxz()) {
                break;
            }
        }
        return arrayList;
    }

    private final URL zzyy() {
        String strValueOf = String.valueOf(zzanv.zzyb());
        String strValueOf2 = String.valueOf(zzaod.zzdrr.get());
        try {
            return new URL(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    final String zza(zzaoi zzaoiVar, boolean z) {
        com.google.android.gms.common.internal.zzbp.zzu(zzaoiVar);
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : zzaoiVar.zziy().entrySet()) {
                String key = entry.getKey();
                if (!"ht".equals(key) && !"qt".equals(key) && !"AppUID".equals(key) && !"z".equals(key) && !"_gmsv".equals(key)) {
                    zza(sb, key, entry.getValue());
                }
            }
            zza(sb, "ht", String.valueOf(zzaoiVar.zzyn()));
            zza(sb, "qt", String.valueOf(zzvx().currentTimeMillis() - zzaoiVar.zzyn()));
            if (z) {
                long jZzyq = zzaoiVar.zzyq();
                zza(sb, "z", jZzyq != 0 ? String.valueOf(jZzyq) : String.valueOf(zzaoiVar.zzym()));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            zze("Failed to encode name or value", e);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<java.lang.Long> zzs(java.util.List<com.google.android.gms.internal.zzaoi> r9) {
        /*
            Method dump skipped, instructions count: 267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaop.zzs(java.util.List):java.util.List");
    }

    @Override // com.google.android.gms.internal.zzams
    protected final void zzuk() {
        zza("Network initialized. User agent", this.zzbwh);
    }

    public final boolean zzyx() {
        NetworkInfo activeNetworkInfo;
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        try {
            activeNetworkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException e) {
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzdm("No network connectivity");
        return false;
    }
}
