package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzalv;
import com.google.android.gms.internal.zzami;
import com.google.android.gms.internal.zzamt;
import com.google.android.gms.internal.zzamx;
import com.google.android.gms.internal.zzaoi;
import com.google.android.gms.internal.zzapd;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
final class zzn implements Runnable {
    private /* synthetic */ Map zzdln;
    private /* synthetic */ boolean zzdlo;
    private /* synthetic */ String zzdlp;
    private /* synthetic */ long zzdlq;
    private /* synthetic */ boolean zzdlr;
    private /* synthetic */ boolean zzdls;
    private /* synthetic */ String zzdlt;
    private /* synthetic */ Tracker zzdlu;

    zzn(Tracker tracker, Map map, boolean z, String str, long j, boolean z2, boolean z3, String str2) {
        this.zzdlu = tracker;
        this.zzdln = map;
        this.zzdlo = z;
        this.zzdlp = str;
        this.zzdlq = j;
        this.zzdlr = z2;
        this.zzdls = z3;
        this.zzdlt = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzdlu.zzdlk.zzul()) {
            this.zzdln.put("sc", "start");
        }
        Map map = this.zzdln;
        GoogleAnalytics googleAnalyticsZzwb = this.zzdlu.zzwb();
        zzbp.zzgh("getClientId can not be called from the main thread");
        zzapd.zzc((Map<String, String>) map, "cid", googleAnalyticsZzwb.zztr().zzwq().zzxp());
        String str = (String) this.zzdln.get("sf");
        if (str != null) {
            double dZza = zzapd.zza(str, 100.0d);
            if (zzapd.zza(dZza, (String) this.zzdln.get("cid"))) {
                this.zzdlu.zzb("Sampling enabled. Hit sampled out. sample rate", Double.valueOf(dZza));
                return;
            }
        }
        zzami zzamiVarZzwh = this.zzdlu.zzwh();
        if (this.zzdlo) {
            zzapd.zzc((Map<String, String>) this.zzdln, "ate", zzamiVarZzwh.zzvg());
            zzapd.zzb(this.zzdln, "adid", zzamiVarZzwh.zzvn());
        } else {
            this.zzdln.remove("ate");
            this.zzdln.remove("adid");
        }
        zzalv zzalvVarZzxd = this.zzdlu.zzwi().zzxd();
        zzapd.zzb(this.zzdln, "an", zzalvVarZzxd.zzun());
        zzapd.zzb(this.zzdln, "av", zzalvVarZzxd.zzuo());
        zzapd.zzb(this.zzdln, "aid", zzalvVarZzxd.getAppId());
        zzapd.zzb(this.zzdln, "aiid", zzalvVarZzxd.zzup());
        this.zzdln.put("v", "1");
        this.zzdln.put("_v", zzamt.zzdoe);
        zzapd.zzb(this.zzdln, "ul", this.zzdlu.zzwj().zzyh().getLanguage());
        zzapd.zzb(this.zzdln, "sr", this.zzdlu.zzwj().zzyi());
        if (!(this.zzdlp.equals("transaction") || this.zzdlp.equals("item")) && !this.zzdlu.zzdlj.zzys()) {
            this.zzdlu.zzvy().zze(this.zzdln, "Too many hits sent too quickly, rate limiting invoked");
            return;
        }
        long jZzea = zzapd.zzea((String) this.zzdln.get("ht"));
        if (jZzea == 0) {
            jZzea = this.zzdlq;
        }
        if (this.zzdlr) {
            this.zzdlu.zzvy().zzc("Dry run enabled. Would have sent hit", new zzaoi(this.zzdlu, this.zzdln, jZzea, this.zzdls));
            return;
        }
        String str2 = (String) this.zzdln.get("cid");
        HashMap map2 = new HashMap();
        zzapd.zza(map2, "uid", (Map<String, String>) this.zzdln);
        zzapd.zza(map2, "an", (Map<String, String>) this.zzdln);
        zzapd.zza(map2, "aid", (Map<String, String>) this.zzdln);
        zzapd.zza(map2, "av", (Map<String, String>) this.zzdln);
        zzapd.zza(map2, "aiid", (Map<String, String>) this.zzdln);
        this.zzdln.put("_s", String.valueOf(this.zzdlu.zzwc().zza(new zzamx(0L, str2, this.zzdlt, TextUtils.isEmpty((CharSequence) this.zzdln.get("adid")) ? false : true, 0L, map2))));
        this.zzdlu.zzwc().zza(new zzaoi(this.zzdlu, this.zzdln, jZzea, this.zzdls));
    }
}
