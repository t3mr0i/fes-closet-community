package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.WorkerThread;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzca;
import com.google.firebase.iid.FirebaseInstanceId;
import java.math.BigInteger;
import java.util.Locale;

/* loaded from: classes.dex */
public final class zzcbr extends zzcdu {
    private String mAppId;
    private String zzcyd;
    private String zzdma;
    private String zzdmb;
    private String zzile;
    private long zzili;
    private int zziph;
    private long zzipi;
    private int zzipj;

    zzcbr(zzccw zzccwVar) {
        super(zzccwVar);
    }

    @WorkerThread
    private final String zzaur() {
        zzuj();
        try {
            return FirebaseInstanceId.getInstance().getId();
        } catch (IllegalStateException e) {
            zzaum().zzayg().log("Failed to retrieve Firebase Instance Id");
            return null;
        }
    }

    final String getAppId() {
        zzwk();
        return this.mAppId;
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    final String getGmpAppId() {
        zzwk();
        return this.zzcyd;
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatv() {
        super.zzatv();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatw() {
        super.zzatw();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatx() {
        super.zzatx();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcan zzaty() {
        return super.zzaty();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcau zzatz() {
        return super.zzatz();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcdw zzaua() {
        return super.zzaua();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbr zzaub() {
        return super.zzaub();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbe zzauc() {
        return super.zzauc();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzceo zzaud() {
        return super.zzaud();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcek zzaue() {
        return super.zzaue();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbs zzauf() {
        return super.zzauf();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcay zzaug() {
        return super.zzaug();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbu zzauh() {
        return super.zzauh();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcfw zzaui() {
        return super.zzaui();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzccq zzauj() {
        return super.zzauj();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcfl zzauk() {
        return super.zzauk();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzccr zzaul() {
        return super.zzaul();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbw zzaum() {
        return super.zzaum();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcch zzaun() {
        return super.zzaun();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcax zzauo() {
        return super.zzauo();
    }

    @WorkerThread
    final String zzayb() {
        byte[] bArr = new byte[16];
        zzaui().zzazz().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new BigInteger(1, bArr));
    }

    final int zzayc() {
        zzwk();
        return this.zziph;
    }

    @WorkerThread
    final zzcas zzjb(String str) {
        zzuj();
        String appId = getAppId();
        String gmpAppId = getGmpAppId();
        zzwk();
        String str2 = this.zzdmb;
        long jZzayc = zzayc();
        zzwk();
        String str3 = this.zzile;
        long jZzauw = zzcax.zzauw();
        zzwk();
        zzuj();
        if (this.zzipi == 0) {
            this.zzipi = this.zzikh.zzaui().zzah(getContext(), getContext().getPackageName());
        }
        long j = this.zzipi;
        boolean zIsEnabled = this.zzikh.isEnabled();
        boolean z = !zzaun().zzirg;
        String strZzaur = zzaur();
        zzwk();
        long jZzazf = this.zzikh.zzazf();
        zzwk();
        return new zzcas(appId, gmpAppId, str2, jZzayc, str3, jZzauw, j, str, zIsEnabled, z, strZzaur, 0L, jZzazf, this.zzipj);
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    @Override // com.google.android.gms.internal.zzcdu
    protected final void zzuk() throws IllegalStateException, PackageManager.NameNotFoundException {
        String string;
        boolean z;
        String installerPackageName = EnvironmentCompat.MEDIA_UNKNOWN;
        String str = "Unknown";
        int i = ExploreByTouchHelper.INVALID_ID;
        string = "Unknown";
        String packageName = getContext().getPackageName();
        PackageManager packageManager = getContext().getPackageManager();
        if (packageManager == null) {
            zzaum().zzaye().zzj("PackageManager is null, app identity information might be inaccurate. appId", zzcbw.zzjf(packageName));
        } else {
            try {
                installerPackageName = packageManager.getInstallerPackageName(packageName);
            } catch (IllegalArgumentException e) {
                zzaum().zzaye().zzj("Error retrieving app installer package name. appId", zzcbw.zzjf(packageName));
            }
            if (installerPackageName == null) {
                installerPackageName = "manual_install";
            } else if ("com.android.vending".equals(installerPackageName)) {
                installerPackageName = "";
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
                if (packageInfo != null) {
                    CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                    string = TextUtils.isEmpty(applicationLabel) ? "Unknown" : applicationLabel.toString();
                    str = packageInfo.versionName;
                    i = packageInfo.versionCode;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                zzaum().zzaye().zze("Error retrieving package info. appId, appName", zzcbw.zzjf(packageName), string);
            }
        }
        this.mAppId = packageName;
        this.zzile = installerPackageName;
        this.zzdmb = str;
        this.zziph = i;
        this.zzdma = string;
        this.zzipi = 0L;
        zzcax.zzawl();
        Status statusZzcb = zzca.zzcb(getContext());
        boolean z2 = statusZzcb != null && statusZzcb.isSuccess();
        if (!z2) {
            if (statusZzcb == null) {
                zzaum().zzaye().log("GoogleService failed to initialize (no status)");
            } else {
                zzaum().zzaye().zze("GoogleService failed to initialize, status", Integer.valueOf(statusZzcb.getStatusCode()), statusZzcb.getStatusMessage());
            }
        }
        if (z2) {
            Boolean boolZzit = zzauo().zzit("firebase_analytics_collection_enabled");
            if (zzauo().zzawm()) {
                zzaum().zzayi().log("Collection disabled with firebase_analytics_collection_deactivated=1");
                z = false;
            } else if (boolZzit != null && !boolZzit.booleanValue()) {
                zzaum().zzayi().log("Collection disabled with firebase_analytics_collection_enabled=0");
                z = false;
            } else if (boolZzit == null && zzcax.zzaif()) {
                zzaum().zzayi().log("Collection disabled with google_app_measurement_enable=0");
                z = false;
            } else {
                zzaum().zzayk().log("Collection enabled");
                z = true;
            }
        } else {
            z = false;
        }
        this.zzcyd = "";
        this.zzili = 0L;
        zzcax.zzawl();
        try {
            String strZzaie = zzca.zzaie();
            if (TextUtils.isEmpty(strZzaie)) {
                strZzaie = "";
            }
            this.zzcyd = strZzaie;
            if (z) {
                zzaum().zzayk().zze("App package, google app id", this.mAppId, this.zzcyd);
            }
        } catch (IllegalStateException e3) {
            zzaum().zzaye().zze("getGoogleAppId or isMeasurementEnabled failed with exception. appId", zzcbw.zzjf(packageName), e3);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.zzipj = zzbeb.zzcp(getContext()) ? 1 : 0;
        } else {
            this.zzipj = 0;
        }
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ com.google.android.gms.common.util.zzd zzvx() {
        return super.zzvx();
    }
}
