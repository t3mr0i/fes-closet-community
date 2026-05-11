package com.google.android.gms.tagmanager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes.dex */
final class zzfw implements zzfy {
    zzfw() {
    }

    @Override // com.google.android.gms.tagmanager.zzfy
    public final HttpURLConnection zzc(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }
}
