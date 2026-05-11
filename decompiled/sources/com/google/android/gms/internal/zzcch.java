package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

/* loaded from: classes.dex */
final class zzcch extends zzcdu {
    static final Pair<String, Long> zziqm = new Pair<>("", 0L);
    private SharedPreferences zzdtp;
    public final zzccl zziqn;
    public final zzcck zziqo;
    public final zzcck zziqp;
    public final zzcck zziqq;
    public final zzcck zziqr;
    public final zzcck zziqs;
    public final zzcck zziqt;
    public final zzccm zziqu;
    private String zziqv;
    private boolean zziqw;
    private long zziqx;
    private String zziqy;
    private long zziqz;
    private final Object zzira;
    public final zzcck zzirb;
    public final zzcck zzirc;
    public final zzccj zzird;
    public final zzcck zzire;
    public final zzcck zzirf;
    public boolean zzirg;

    zzcch(zzccw zzccwVar) {
        super(zzccwVar);
        this.zziqn = new zzccl(this, "health_monitor", zzcax.zzawr());
        this.zziqo = new zzcck(this, "last_upload", 0L);
        this.zziqp = new zzcck(this, "last_upload_attempt", 0L);
        this.zziqq = new zzcck(this, "backoff", 0L);
        this.zziqr = new zzcck(this, "last_delete_stale", 0L);
        this.zzirb = new zzcck(this, "time_before_start", 10000L);
        this.zzirc = new zzcck(this, "session_timeout", 1800000L);
        this.zzird = new zzccj(this, "start_new_session", true);
        this.zzire = new zzcck(this, "last_pause_time", 0L);
        this.zzirf = new zzcck(this, "time_active", 0L);
        this.zziqs = new zzcck(this, "midnight_offset", 0L);
        this.zziqt = new zzcck(this, "first_open_time", 0L);
        this.zziqu = new zzccm(this, "app_instance_id", null);
        this.zzira = new Object();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final SharedPreferences zzaym() {
        zzuj();
        zzwk();
        return this.zzdtp;
    }

    @WorkerThread
    final void setMeasurementEnabled(boolean z) {
        zzuj();
        zzaum().zzayk().zzj("Setting measurementEnabled", Boolean.valueOf(z));
        SharedPreferences.Editor editorEdit = zzaym().edit();
        editorEdit.putBoolean("measurement_enabled", z);
        editorEdit.apply();
    }

    @WorkerThread
    final String zzayn() {
        zzuj();
        return zzaym().getString("gmp_app_id", null);
    }

    final String zzayo() {
        String str;
        synchronized (this.zzira) {
            str = Math.abs(zzvx().elapsedRealtime() - this.zziqz) < 1000 ? this.zziqy : null;
        }
        return str;
    }

    @WorkerThread
    final Boolean zzayp() {
        zzuj();
        if (zzaym().contains("use_service")) {
            return Boolean.valueOf(zzaym().getBoolean("use_service", false));
        }
        return null;
    }

    @WorkerThread
    final void zzayq() {
        zzuj();
        zzaum().zzayk().log("Clearing collection preferences.");
        boolean zContains = zzaym().contains("measurement_enabled");
        boolean zZzbn = zContains ? zzbn(true) : true;
        SharedPreferences.Editor editorEdit = zzaym().edit();
        editorEdit.clear();
        editorEdit.apply();
        if (zContains) {
            setMeasurementEnabled(zZzbn);
        }
    }

    @WorkerThread
    protected final String zzayr() {
        zzuj();
        String string = zzaym().getString("previous_os_version", null);
        zzauc().zzwk();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor editorEdit = zzaym().edit();
            editorEdit.putString("previous_os_version", str);
            editorEdit.apply();
        }
        return string;
    }

    @WorkerThread
    final void zzbm(boolean z) {
        zzuj();
        zzaum().zzayk().zzj("Setting useService", Boolean.valueOf(z));
        SharedPreferences.Editor editorEdit = zzaym().edit();
        editorEdit.putBoolean("use_service", z);
        editorEdit.apply();
    }

    @WorkerThread
    final boolean zzbn(boolean z) {
        zzuj();
        return zzaym().getBoolean("measurement_enabled", z);
    }

    @WorkerThread
    @NonNull
    final Pair<String, Boolean> zzjh(String str) {
        zzuj();
        long jElapsedRealtime = zzvx().elapsedRealtime();
        if (this.zziqv != null && jElapsedRealtime < this.zziqx) {
            return new Pair<>(this.zziqv, Boolean.valueOf(this.zziqw));
        }
        this.zziqx = jElapsedRealtime + zzauo().zza(str, zzcbm.zziob);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
            if (advertisingIdInfo != null) {
                this.zziqv = advertisingIdInfo.getId();
                this.zziqw = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zziqv == null) {
                this.zziqv = "";
            }
        } catch (Throwable th) {
            zzaum().zzayj().zzj("Unable to get advertising id", th);
            this.zziqv = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zziqv, Boolean.valueOf(this.zziqw));
    }

    @WorkerThread
    final String zzji(String str) {
        zzuj();
        String str2 = (String) zzjh(str).first;
        MessageDigest messageDigestZzec = zzcfw.zzec("MD5");
        if (messageDigestZzec == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, messageDigestZzec.digest(str2.getBytes())));
    }

    @WorkerThread
    final void zzjj(String str) {
        zzuj();
        SharedPreferences.Editor editorEdit = zzaym().edit();
        editorEdit.putString("gmp_app_id", str);
        editorEdit.apply();
    }

    final void zzjk(String str) {
        synchronized (this.zzira) {
            this.zziqy = str;
            this.zziqz = zzvx().elapsedRealtime();
        }
    }

    @Override // com.google.android.gms.internal.zzcdu
    protected final void zzuk() {
        this.zzdtp = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzirg = this.zzdtp.getBoolean("has_been_opened", false);
        if (this.zzirg) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.zzdtp.edit();
        editorEdit.putBoolean("has_been_opened", true);
        editorEdit.apply();
    }
}
