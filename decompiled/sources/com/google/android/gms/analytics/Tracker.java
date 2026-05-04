package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzams;
import com.google.android.gms.internal.zzamu;
import com.google.android.gms.internal.zzaol;
import com.google.android.gms.internal.zzapc;
import com.google.android.gms.internal.zzapd;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/* loaded from: classes.dex */
public class Tracker extends zzams {
    private final Map<String, String> zzbql;
    private boolean zzdlh;
    private final Map<String, String> zzdli;
    private final zzaol zzdlj;
    private final zza zzdlk;
    private ExceptionReporter zzdll;
    private zzapc zzdlm;

    class zza extends zzams implements GoogleAnalytics.zza {
        private boolean zzdlv;
        private int zzdlw;
        private long zzdlx;
        private boolean zzdly;
        private long zzdlz;

        protected zza(zzamu zzamuVar) {
            super(zzamuVar);
            this.zzdlx = -1L;
        }

        private final void zzum() {
            if (this.zzdlx >= 0 || this.zzdlv) {
                zzwb().zza(Tracker.this.zzdlk);
            } else {
                zzwb().zzb(Tracker.this.zzdlk);
            }
        }

        public final void enableAutoActivityTracking(boolean z) {
            this.zzdlv = z;
            zzum();
        }

        public final void setSessionTimeout(long j) {
            this.zzdlx = j;
            zzum();
        }

        @Override // com.google.android.gms.analytics.GoogleAnalytics.zza
        public final void zzl(Activity activity) {
            String canonicalName;
            String stringExtra;
            if (this.zzdlw == 0) {
                if (zzvx().elapsedRealtime() >= this.zzdlz + Math.max(1000L, this.zzdlx)) {
                    this.zzdly = true;
                }
            }
            this.zzdlw++;
            if (this.zzdlv) {
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Tracker.this.setCampaignParamsOnNextHit(intent.getData());
                }
                HashMap map = new HashMap();
                map.put("&t", "screenview");
                Tracker tracker = Tracker.this;
                if (Tracker.this.zzdlm != null) {
                    zzapc zzapcVar = Tracker.this.zzdlm;
                    String canonicalName2 = activity.getClass().getCanonicalName();
                    canonicalName = zzapcVar.zzduk.get(canonicalName2);
                    if (canonicalName == null) {
                        canonicalName = canonicalName2;
                    }
                } else {
                    canonicalName = activity.getClass().getCanonicalName();
                }
                tracker.set("&cd", canonicalName);
                if (TextUtils.isEmpty((CharSequence) map.get("&dr"))) {
                    zzbp.zzu(activity);
                    Intent intent2 = activity.getIntent();
                    if (intent2 == null) {
                        stringExtra = null;
                    } else {
                        stringExtra = intent2.getStringExtra("android.intent.extra.REFERRER_NAME");
                        if (TextUtils.isEmpty(stringExtra)) {
                            stringExtra = null;
                        }
                    }
                    if (!TextUtils.isEmpty(stringExtra)) {
                        map.put("&dr", stringExtra);
                    }
                }
                Tracker.this.send(map);
            }
        }

        @Override // com.google.android.gms.analytics.GoogleAnalytics.zza
        public final void zzm(Activity activity) {
            this.zzdlw--;
            this.zzdlw = Math.max(0, this.zzdlw);
            if (this.zzdlw == 0) {
                this.zzdlz = zzvx().elapsedRealtime();
            }
        }

        @Override // com.google.android.gms.internal.zzams
        protected final void zzuk() {
        }

        public final synchronized boolean zzul() {
            boolean z;
            z = this.zzdly;
            this.zzdly = false;
            return z;
        }
    }

    Tracker(zzamu zzamuVar, String str, zzaol zzaolVar) {
        super(zzamuVar);
        this.zzbql = new HashMap();
        this.zzdli = new HashMap();
        if (str != null) {
            this.zzbql.put("&tid", str);
        }
        this.zzbql.put("useSecure", "1");
        this.zzbql.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
        this.zzdlj = new zzaol("tracking", zzvx());
        this.zzdlk = new zza(zzamuVar);
    }

    private static String zza(Map.Entry<String, String> entry) {
        String key = entry.getKey();
        if (key.startsWith("&") && key.length() >= 2) {
            return entry.getKey().substring(1);
        }
        return null;
    }

    private static void zzb(Map<String, String> map, Map<String, String> map2) {
        zzbp.zzu(map2);
        if (map == null) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String strZza = zza(entry);
            if (strZza != null) {
                map2.put(strZza, entry.getValue());
            }
        }
    }

    private static void zzc(Map<String, String> map, Map<String, String> map2) {
        zzbp.zzu(map2);
        if (map == null) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String strZza = zza(entry);
            if (strZza != null && !map2.containsKey(strZza)) {
                map2.put(strZza, entry.getValue());
            }
        }
    }

    public void enableAdvertisingIdCollection(boolean z) {
        this.zzdlh = z;
    }

    public void enableAutoActivityTracking(boolean z) {
        this.zzdlk.enableAutoActivityTracking(z);
    }

    public void enableExceptionReporting(boolean z) {
        synchronized (this) {
            if ((this.zzdll != null) == z) {
                return;
            }
            if (z) {
                this.zzdll = new ExceptionReporter(this, Thread.getDefaultUncaughtExceptionHandler(), getContext());
                Thread.setDefaultUncaughtExceptionHandler(this.zzdll);
                zzdm("Uncaught exceptions will be reported to Google Analytics");
            } else {
                Thread.setDefaultUncaughtExceptionHandler(this.zzdll.zztv());
                zzdm("Uncaught exceptions will not be reported to Google Analytics");
            }
        }
    }

    public String get(String str) {
        zzwk();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.zzbql.containsKey(str)) {
            return this.zzbql.get(str);
        }
        if (str.equals("&ul")) {
            return zzapd.zza(Locale.getDefault());
        }
        if (str.equals("&cid")) {
            return zzwg().zzxp();
        }
        if (str.equals("&sr")) {
            return zzwj().zzyi();
        }
        if (str.equals("&aid")) {
            return zzwi().zzxd().getAppId();
        }
        if (str.equals("&an")) {
            return zzwi().zzxd().zzun();
        }
        if (str.equals("&av")) {
            return zzwi().zzxd().zzuo();
        }
        if (str.equals("&aiid")) {
            return zzwi().zzxd().zzup();
        }
        return null;
    }

    public void send(Map<String, String> map) {
        long jCurrentTimeMillis = zzvx().currentTimeMillis();
        if (zzwb().getAppOptOut()) {
            zzdn("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        boolean zIsDryRunEnabled = zzwb().isDryRunEnabled();
        HashMap map2 = new HashMap();
        zzb(this.zzbql, map2);
        zzb(map, map2);
        boolean zZzd = zzapd.zzd(this.zzbql.get("useSecure"), true);
        zzc(this.zzdli, map2);
        this.zzdli.clear();
        String str = map2.get("t");
        if (TextUtils.isEmpty(str)) {
            zzvy().zze(map2, "Missing hit type parameter");
            return;
        }
        String str2 = map2.get("tid");
        if (TextUtils.isEmpty(str2)) {
            zzvy().zze(map2, "Missing tracking id parameter");
            return;
        }
        boolean z = this.zzdlh;
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(str) || "pageview".equalsIgnoreCase(str) || "appview".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
                int i = Integer.parseInt(this.zzbql.get("&a")) + 1;
                if (i >= Integer.MAX_VALUE) {
                    i = 1;
                }
                this.zzbql.put("&a", Integer.toString(i));
            }
        }
        zzwa().zzc(new zzn(this, map2, z, str, jCurrentTimeMillis, zIsDryRunEnabled, zZzd, str2));
    }

    public void set(String str, String str2) {
        zzbp.zzb(str, "Key should be non-null");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.zzbql.put(str, str2);
    }

    public void setAnonymizeIp(boolean z) {
        set("&aip", zzapd.zzaj(z));
    }

    public void setAppId(String str) {
        set("&aid", str);
    }

    public void setAppInstallerId(String str) {
        set("&aiid", str);
    }

    public void setAppName(String str) {
        set("&an", str);
    }

    public void setAppVersion(String str) {
        set("&av", str);
    }

    public void setCampaignParamsOnNextHit(Uri uri) {
        if (uri == null || uri.isOpaque()) {
            return;
        }
        String queryParameter = uri.getQueryParameter("referrer");
        if (TextUtils.isEmpty(queryParameter)) {
            return;
        }
        String strValueOf = String.valueOf(queryParameter);
        Uri uri2 = Uri.parse(strValueOf.length() != 0 ? "http://hostname/?".concat(strValueOf) : new String("http://hostname/?"));
        String queryParameter2 = uri2.getQueryParameter("utm_id");
        if (queryParameter2 != null) {
            this.zzdli.put("&ci", queryParameter2);
        }
        String queryParameter3 = uri2.getQueryParameter("anid");
        if (queryParameter3 != null) {
            this.zzdli.put("&anid", queryParameter3);
        }
        String queryParameter4 = uri2.getQueryParameter("utm_campaign");
        if (queryParameter4 != null) {
            this.zzdli.put("&cn", queryParameter4);
        }
        String queryParameter5 = uri2.getQueryParameter("utm_content");
        if (queryParameter5 != null) {
            this.zzdli.put("&cc", queryParameter5);
        }
        String queryParameter6 = uri2.getQueryParameter("utm_medium");
        if (queryParameter6 != null) {
            this.zzdli.put("&cm", queryParameter6);
        }
        String queryParameter7 = uri2.getQueryParameter("utm_source");
        if (queryParameter7 != null) {
            this.zzdli.put("&cs", queryParameter7);
        }
        String queryParameter8 = uri2.getQueryParameter("utm_term");
        if (queryParameter8 != null) {
            this.zzdli.put("&ck", queryParameter8);
        }
        String queryParameter9 = uri2.getQueryParameter("dclid");
        if (queryParameter9 != null) {
            this.zzdli.put("&dclid", queryParameter9);
        }
        String queryParameter10 = uri2.getQueryParameter("gclid");
        if (queryParameter10 != null) {
            this.zzdli.put("&gclid", queryParameter10);
        }
        String queryParameter11 = uri2.getQueryParameter(FirebaseAnalytics.Param.ACLID);
        if (queryParameter11 != null) {
            this.zzdli.put("&aclid", queryParameter11);
        }
    }

    public void setClientId(String str) {
        set("&cid", str);
    }

    public void setEncoding(String str) {
        set("&de", str);
    }

    public void setHostname(String str) {
        set("&dh", str);
    }

    public void setLanguage(String str) {
        set("&ul", str);
    }

    public void setLocation(String str) {
        set("&dl", str);
    }

    public void setPage(String str) {
        set("&dp", str);
    }

    public void setReferrer(String str) {
        set("&dr", str);
    }

    public void setSampleRate(double d) {
        set("&sf", Double.toString(d));
    }

    public void setScreenColors(String str) {
        set("&sd", str);
    }

    public void setScreenName(String str) {
        set("&cd", str);
    }

    public void setScreenResolution(int i, int i2) {
        if (i >= 0 || i2 >= 0) {
            set("&sr", new StringBuilder(23).append(i).append("x").append(i2).toString());
        } else {
            zzdp("Invalid width or height. The values should be non-negative.");
        }
    }

    public void setSessionTimeout(long j) {
        this.zzdlk.setSessionTimeout(1000 * j);
    }

    public void setTitle(String str) {
        set("&dt", str);
    }

    public void setUseSecure(boolean z) {
        set("useSecure", zzapd.zzaj(z));
    }

    public void setViewportSize(String str) {
        set("&vp", str);
    }

    final void zza(zzapc zzapcVar) {
        zzdm("Loading Tracker config values");
        this.zzdlm = zzapcVar;
        if (this.zzdlm.zzdjn != null) {
            String str = this.zzdlm.zzdjn;
            set("&tid", str);
            zza("trackingId loaded", str);
        }
        if (this.zzdlm.zzduf >= 0.0d) {
            String string = Double.toString(this.zzdlm.zzduf);
            set("&sf", string);
            zza("Sample frequency loaded", string);
        }
        if (this.zzdlm.zzdug >= 0) {
            int i = this.zzdlm.zzdug;
            setSessionTimeout(i);
            zza("Session timeout loaded", Integer.valueOf(i));
        }
        if (this.zzdlm.zzduh != -1) {
            boolean z = this.zzdlm.zzduh == 1;
            enableAutoActivityTracking(z);
            zza("Auto activity tracking loaded", Boolean.valueOf(z));
        }
        if (this.zzdlm.zzdui != -1) {
            boolean z2 = this.zzdlm.zzdui == 1;
            if (z2) {
                set("&aip", "1");
            }
            zza("Anonymize ip loaded", Boolean.valueOf(z2));
        }
        enableExceptionReporting(this.zzdlm.zzduj == 1);
    }

    @Override // com.google.android.gms.internal.zzams
    protected final void zzuk() {
        this.zzdlk.initialize();
        String strZzun = zzwe().zzun();
        if (strZzun != null) {
            set("&an", strZzun);
        }
        String strZzuo = zzwe().zzuo();
        if (strZzuo != null) {
            set("&av", strZzuo);
        }
    }
}
