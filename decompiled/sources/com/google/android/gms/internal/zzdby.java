package com.google.android.gms.internal;

import com.google.android.gms.tagmanager.zzdj;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes.dex */
final class zzdby implements zzdbz {
    private HttpURLConnection zzkdu;
    private InputStream zzkdv = null;

    zzdby() {
    }

    @Override // com.google.android.gms.internal.zzdbz
    public final void close() throws IOException {
        HttpURLConnection httpURLConnection = this.zzkdu;
        try {
            if (this.zzkdv != null) {
                this.zzkdv.close();
            }
        } catch (IOException e) {
            String strValueOf = String.valueOf(e.getMessage());
            zzdj.zzb(strValueOf.length() != 0 ? "HttpUrlConnectionNetworkClient: Error when closing http input stream: ".concat(strValueOf) : new String("HttpUrlConnectionNetworkClient: Error when closing http input stream: "), e);
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    @Override // com.google.android.gms.internal.zzdbz
    public final InputStream zzna(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setConnectTimeout(20000);
        this.zzkdu = httpURLConnection;
        HttpURLConnection httpURLConnection2 = this.zzkdu;
        int responseCode = httpURLConnection2.getResponseCode();
        if (responseCode == 200) {
            this.zzkdv = httpURLConnection2.getInputStream();
            return this.zzkdv;
        }
        String string = new StringBuilder(25).append("Bad response: ").append(responseCode).toString();
        if (responseCode == 404) {
            throw new FileNotFoundException(string);
        }
        if (responseCode == 503) {
            throw new zzdcb(string);
        }
        throw new IOException(string);
    }
}
