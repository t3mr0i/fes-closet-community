package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class zzang extends zzams {
    private boolean mStarted;
    private final zzand zzdpo;
    private final zzaop zzdpp;
    private final zzaoo zzdpq;
    private final zzamy zzdpr;
    private long zzdps;
    private final zzanx zzdpt;
    private final zzanx zzdpu;
    private final zzaoz zzdpv;
    private long zzdpw;
    private boolean zzdpx;

    protected zzang(zzamu zzamuVar, zzamw zzamwVar) {
        super(zzamuVar);
        com.google.android.gms.common.internal.zzbp.zzu(zzamwVar);
        this.zzdps = Long.MIN_VALUE;
        this.zzdpq = new zzaoo(zzamuVar);
        this.zzdpo = new zzand(zzamuVar);
        this.zzdpp = new zzaop(zzamuVar);
        this.zzdpr = new zzamy(zzamuVar);
        this.zzdpv = new zzaoz(zzvx());
        this.zzdpt = new zzanh(this, zzamuVar);
        this.zzdpu = new zzani(this, zzamuVar);
    }

    private final void zza(zzamx zzamxVar, zzalw zzalwVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzamxVar);
        com.google.android.gms.common.internal.zzbp.zzu(zzalwVar);
        com.google.android.gms.analytics.zza zzaVar = new com.google.android.gms.analytics.zza(zzvw());
        zzaVar.zzcw(zzamxVar.zzws());
        zzaVar.enableAdvertisingIdCollection(zzamxVar.zzwt());
        com.google.android.gms.analytics.zzg zzgVarZzts = zzaVar.zzts();
        zzame zzameVar = (zzame) zzgVarZzts.zzb(zzame.class);
        zzameVar.zzdh("data");
        zzameVar.zzai(true);
        zzgVarZzts.zza(zzalwVar);
        zzalz zzalzVar = (zzalz) zzgVarZzts.zzb(zzalz.class);
        zzalv zzalvVar = (zzalv) zzgVarZzts.zzb(zzalv.class);
        for (Map.Entry<String, String> entry : zzamxVar.zziy().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if ("an".equals(key)) {
                zzalvVar.setAppName(value);
            } else if ("av".equals(key)) {
                zzalvVar.setAppVersion(value);
            } else if ("aid".equals(key)) {
                zzalvVar.setAppId(value);
            } else if ("aiid".equals(key)) {
                zzalvVar.setAppInstallerId(value);
            } else if ("uid".equals(key)) {
                zzameVar.setUserId(value);
            } else {
                zzalzVar.set(key, value);
            }
        }
        zzb("Sending installation campaign to", zzamxVar.zzws(), zzalwVar);
        zzgVarZzts.zzl(zzwf().zzzb());
        zzgVarZzts.zzua();
    }

    private final boolean zzdt(String str) {
        return zzbed.zzcr(getContext()).checkCallingOrSelfPermission(str) == 0;
    }

    private final long zzxa() {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        try {
            return this.zzdpo.zzxa();
        } catch (SQLiteException e) {
            zze("Failed to get min/max hit times from local store", e);
            return 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzxf() {
        zzb(new zzank(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzxg() {
        try {
            this.zzdpo.zzwz();
            zzxk();
        } catch (SQLiteException e) {
            zzd("Failed to delete stale hits", e);
        }
        this.zzdpu.zzs(86400000L);
    }

    private final void zzxh() {
        if (this.zzdpx || !zzanv.zzxv() || this.zzdpr.isConnected()) {
            return;
        }
        if (this.zzdpv.zzu(zzaod.zzdsm.get().longValue())) {
            this.zzdpv.start();
            zzdm("Connecting to service");
            if (this.zzdpr.connect()) {
                zzdm("Connected to service");
                this.zzdpv.clear();
                onServiceConnected();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0054, code lost:
    
        zzdm("Store is empty, nothing to dispatch");
        zzxm();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005c, code lost:
    
        r12.zzdpo.setTransactionSuccessful();
        r12.zzdpo.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0067, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0068, code lost:
    
        zze("Failed to commit local dispatch transaction", r0);
        zzxm();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00e7, code lost:
    
        if (r12.zzdpr.isConnected() == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00e9, code lost:
    
        zzdm("Service connected, sending hits to the service");
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00f2, code lost:
    
        if (r8.isEmpty() != false) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00f4, code lost:
    
        r0 = r8.get(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0101, code lost:
    
        if (r12.zzdpr.zzb(r0) == false) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0103, code lost:
    
        r4 = java.lang.Math.max(r4, r0.zzym());
        r8.remove(r0);
        zzb("Hit sent do device AnalyticsService for delivery", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0113, code lost:
    
        r12.zzdpo.zzp(r0.zzym());
        r3.add(java.lang.Long.valueOf(r0.zzym()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0128, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0129, code lost:
    
        zze("Failed to remove hit that was send for delivery", r0);
        zzxm();
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0131, code lost:
    
        r12.zzdpo.setTransactionSuccessful();
        r12.zzdpo.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x013d, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x013e, code lost:
    
        zze("Failed to commit local dispatch transaction", r0);
        zzxm();
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0148, code lost:
    
        r0 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x014f, code lost:
    
        if (r12.zzdpp.zzyx() == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0151, code lost:
    
        r8 = r12.zzdpp.zzs(r8);
        r9 = r8.iterator();
        r4 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0160, code lost:
    
        if (r9.hasNext() == false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0162, code lost:
    
        r4 = java.lang.Math.max(r4, r9.next().longValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0171, code lost:
    
        r12.zzdpo.zzq(r8);
        r3.addAll(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0179, code lost:
    
        r0 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x017e, code lost:
    
        if (r3.isEmpty() == false) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0180, code lost:
    
        r12.zzdpo.setTransactionSuccessful();
        r12.zzdpo.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x018c, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x018d, code lost:
    
        zze("Failed to commit local dispatch transaction", r0);
        zzxm();
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0197, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0198, code lost:
    
        zze("Failed to remove successfully uploaded hits", r0);
        zzxm();
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01a0, code lost:
    
        r12.zzdpo.setTransactionSuccessful();
        r12.zzdpo.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01ac, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01ad, code lost:
    
        zze("Failed to commit local dispatch transaction", r0);
        zzxm();
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01b7, code lost:
    
        r12.zzdpo.setTransactionSuccessful();
        r12.zzdpo.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01c4, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01c5, code lost:
    
        zze("Failed to commit local dispatch transaction", r0);
        zzxm();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean zzxi() {
        /*
            Method dump skipped, instructions count: 486
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzang.zzxi():boolean");
    }

    private final void zzxl() {
        zzaoa zzaoaVarZzwd = zzwd();
        if (zzaoaVarZzwd.zzyj() && !zzaoaVarZzwd.zzdp()) {
            long jZzxa = zzxa();
            if (jZzxa == 0 || Math.abs(zzvx().currentTimeMillis() - jZzxa) > zzaod.zzdrl.get().longValue()) {
                return;
            }
            zza("Dispatch alarm scheduled (ms)", Long.valueOf(zzanv.zzxy()));
            zzaoaVarZzwd.schedule();
        }
    }

    private final void zzxm() {
        if (this.zzdpt.zzdp()) {
            zzdm("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzdpt.cancel();
        zzaoa zzaoaVarZzwd = zzwd();
        if (zzaoaVarZzwd.zzdp()) {
            zzaoaVarZzwd.cancel();
        }
    }

    private final long zzxn() {
        if (this.zzdps != Long.MIN_VALUE) {
            return this.zzdps;
        }
        long jLongValue = zzaod.zzdrg.get().longValue();
        zzape zzapeVarZzwe = zzwe();
        zzapeVarZzwe.zzwk();
        if (!zzapeVarZzwe.zzdum) {
            return jLongValue;
        }
        zzwe().zzwk();
        return r0.zzdst * 1000;
    }

    private final void zzxo() {
        zzwk();
        com.google.android.gms.analytics.zzj.zzuj();
        this.zzdpx = true;
        this.zzdpr.disconnect();
        zzxk();
    }

    protected final void onServiceConnected() {
        com.google.android.gms.analytics.zzj.zzuj();
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        if (!zzanv.zzxv()) {
            zzdp("Service client disabled. Can't dispatch local hits to device AnalyticsService");
        }
        if (!this.zzdpr.isConnected()) {
            zzdm("Service not connected");
            return;
        }
        if (this.zzdpo.isEmpty()) {
            return;
        }
        zzdm("Dispatching local hits to device AnalyticsService");
        while (true) {
            try {
                List<zzaoi> listZzo = this.zzdpo.zzo(zzanv.zzxz());
                if (listZzo.isEmpty()) {
                    zzxk();
                    return;
                }
                while (!listZzo.isEmpty()) {
                    zzaoi zzaoiVar = listZzo.get(0);
                    if (!this.zzdpr.zzb(zzaoiVar)) {
                        zzxk();
                        return;
                    }
                    listZzo.remove(zzaoiVar);
                    try {
                        this.zzdpo.zzp(zzaoiVar.zzym());
                    } catch (SQLiteException e) {
                        zze("Failed to remove hit that was send for delivery", e);
                        zzxm();
                        return;
                    }
                }
            } catch (SQLiteException e2) {
                zze("Failed to read hits from store", e2);
                zzxm();
                return;
            }
        }
    }

    final void start() {
        zzwk();
        com.google.android.gms.common.internal.zzbp.zza(!this.mStarted, "Analytics backend already started");
        this.mStarted = true;
        zzwa().zzc(new zzanj(this));
    }

    public final long zza(zzamx zzamxVar, boolean z) {
        com.google.android.gms.common.internal.zzbp.zzu(zzamxVar);
        zzwk();
        com.google.android.gms.analytics.zzj.zzuj();
        try {
            try {
                this.zzdpo.beginTransaction();
                zzand zzandVar = this.zzdpo;
                long jZzwr = zzamxVar.zzwr();
                String strZzve = zzamxVar.zzve();
                com.google.android.gms.common.internal.zzbp.zzgg(strZzve);
                zzandVar.zzwk();
                com.google.android.gms.analytics.zzj.zzuj();
                int iDelete = zzandVar.getWritableDatabase().delete("properties", "app_uid=? AND cid<>?", new String[]{String.valueOf(jZzwr), strZzve});
                if (iDelete > 0) {
                    zzandVar.zza("Deleted property records", Integer.valueOf(iDelete));
                }
                long jZza = this.zzdpo.zza(zzamxVar.zzwr(), zzamxVar.zzve(), zzamxVar.zzws());
                zzamxVar.zzm(1 + jZza);
                zzand zzandVar2 = this.zzdpo;
                com.google.android.gms.common.internal.zzbp.zzu(zzamxVar);
                zzandVar2.zzwk();
                com.google.android.gms.analytics.zzj.zzuj();
                SQLiteDatabase writableDatabase = zzandVar2.getWritableDatabase();
                Map<String, String> mapZziy = zzamxVar.zziy();
                com.google.android.gms.common.internal.zzbp.zzu(mapZziy);
                Uri.Builder builder = new Uri.Builder();
                for (Map.Entry<String, String> entry : mapZziy.entrySet()) {
                    builder.appendQueryParameter(entry.getKey(), entry.getValue());
                }
                String encodedQuery = builder.build().getEncodedQuery();
                String str = encodedQuery == null ? "" : encodedQuery;
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_uid", Long.valueOf(zzamxVar.zzwr()));
                contentValues.put("cid", zzamxVar.zzve());
                contentValues.put("tid", zzamxVar.zzws());
                contentValues.put("adid", Integer.valueOf(zzamxVar.zzwt() ? 1 : 0));
                contentValues.put("hits_count", Long.valueOf(zzamxVar.zzwu()));
                contentValues.put("params", str);
                try {
                    if (writableDatabase.insertWithOnConflict("properties", null, contentValues, 5) == -1) {
                        zzandVar2.zzdq("Failed to insert/update a property (got -1)");
                    }
                } catch (SQLiteException e) {
                    zzandVar2.zze("Error storing a property", e);
                }
                this.zzdpo.setTransactionSuccessful();
                return jZza;
            } finally {
                try {
                    this.zzdpo.endTransaction();
                } catch (SQLiteException e2) {
                    zze("Failed to end transaction", e2);
                }
            }
        } catch (SQLiteException e3) {
            zze("Failed to update Analytics property", e3);
            try {
                this.zzdpo.endTransaction();
            } catch (SQLiteException e4) {
                zze("Failed to end transaction", e4);
            }
            return -1L;
        }
    }

    public final void zza(zzaoi zzaoiVar) throws Throwable {
        Pair<String, Long> pairZzzi;
        com.google.android.gms.common.internal.zzbp.zzu(zzaoiVar);
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        if (this.zzdpx) {
            zzdn("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        } else {
            zza("Delivering hit", zzaoiVar);
        }
        if (TextUtils.isEmpty(zzaoiVar.zzyr()) && (pairZzzi = zzwf().zzzg().zzzi()) != null) {
            Long l = (Long) pairZzzi.second;
            String str = (String) pairZzzi.first;
            String strValueOf = String.valueOf(l);
            String string = new StringBuilder(String.valueOf(strValueOf).length() + 1 + String.valueOf(str).length()).append(strValueOf).append(":").append(str).toString();
            HashMap map = new HashMap(zzaoiVar.zziy());
            map.put("_m", string);
            zzaoiVar = new zzaoi(this, map, zzaoiVar.zzyn(), zzaoiVar.zzyp(), zzaoiVar.zzym(), zzaoiVar.zzyl(), zzaoiVar.zzyo());
        }
        zzxh();
        if (this.zzdpr.zzb(zzaoiVar)) {
            zzdn("Hit sent to the device AnalyticsService for delivery");
            return;
        }
        try {
            this.zzdpo.zzc(zzaoiVar);
            zzxk();
        } catch (SQLiteException e) {
            zze("Delivery failed to save hit to a database", e);
            zzvy().zza(zzaoiVar, "deliver: failed to insert hit to database");
        }
    }

    protected final void zzb(zzamx zzamxVar) {
        com.google.android.gms.analytics.zzj.zzuj();
        zzb("Sending first hit to property", zzamxVar.zzws());
        if (zzwf().zzzc().zzu(zzanv.zzyf())) {
            return;
        }
        String strZzzf = zzwf().zzzf();
        if (TextUtils.isEmpty(strZzzf)) {
            return;
        }
        zzalw zzalwVarZza = zzapd.zza(zzvy(), strZzzf);
        zzb("Found relevant installation campaign", zzalwVarZza);
        zza(zzamxVar, zzalwVarZza);
    }

    public final void zzb(zzaob zzaobVar) {
        long j = this.zzdpw;
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        long jZzzd = zzwf().zzzd();
        zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(jZzzd != 0 ? Math.abs(zzvx().currentTimeMillis() - jZzzd) : -1L));
        zzxh();
        try {
            zzxi();
            zzwf().zzze();
            zzxk();
            if (zzaobVar != null) {
                zzaobVar.zzb(null);
            }
            if (this.zzdpw != j) {
                this.zzdpq.zzyw();
            }
        } catch (Throwable th) {
            zze("Local dispatch failed", th);
            zzwf().zzze();
            zzxk();
            if (zzaobVar != null) {
                zzaobVar.zzb(th);
            }
        }
    }

    public final void zzdu(String str) {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.analytics.zzj.zzuj();
        zzalw zzalwVarZza = zzapd.zza(zzvy(), str);
        if (zzalwVarZza == null) {
            zzd("Parsing failed. Ignoring invalid campaign data", str);
            return;
        }
        String strZzzf = zzwf().zzzf();
        if (str.equals(strZzzf)) {
            zzdp("Ignoring duplicate install campaign");
            return;
        }
        if (!TextUtils.isEmpty(strZzzf)) {
            zzd("Ignoring multiple install campaigns. original, new", strZzzf, str);
            return;
        }
        zzwf().zzdx(str);
        if (zzwf().zzzc().zzu(zzanv.zzyf())) {
            zzd("Campaign received too late, ignoring", zzalwVarZza);
            return;
        }
        zzb("Received installation campaign", zzalwVarZza);
        Iterator<zzamx> it = this.zzdpo.zzq(0L).iterator();
        while (it.hasNext()) {
            zza(it.next(), zzalwVarZza);
        }
    }

    public final void zzr(long j) {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        if (j < 0) {
            j = 0;
        }
        this.zzdps = j;
        zzxk();
    }

    @Override // com.google.android.gms.internal.zzams
    protected final void zzuk() {
        this.zzdpo.initialize();
        this.zzdpp.initialize();
        this.zzdpr.initialize();
    }

    public final void zzvr() {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        zzdm("Delete all hits from local store");
        try {
            zzand zzandVar = this.zzdpo;
            com.google.android.gms.analytics.zzj.zzuj();
            zzandVar.zzwk();
            zzandVar.getWritableDatabase().delete("hits2", null, null);
            zzand zzandVar2 = this.zzdpo;
            com.google.android.gms.analytics.zzj.zzuj();
            zzandVar2.zzwk();
            zzandVar2.getWritableDatabase().delete("properties", null, null);
            zzxk();
        } catch (SQLiteException e) {
            zzd("Failed to delete hits from store", e);
        }
        zzxh();
        if (this.zzdpr.zzwv()) {
            zzdm("Device service unavailable. Can't clear hits stored on the device service.");
        }
    }

    final void zzvv() {
        com.google.android.gms.analytics.zzj.zzuj();
        this.zzdpw = zzvx().currentTimeMillis();
    }

    protected final void zzxe() {
        zzwk();
        com.google.android.gms.analytics.zzj.zzuj();
        Context context = zzvw().getContext();
        if (!zzaou.zzbe(context)) {
            zzdp("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!zzaov.zzbi(context)) {
            zzdq("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zzbe(context)) {
            zzdp("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        zzwf().zzzb();
        if (!zzdt("android.permission.ACCESS_NETWORK_STATE")) {
            zzdq("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzxo();
        }
        if (!zzdt("android.permission.INTERNET")) {
            zzdq("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzxo();
        }
        if (zzaov.zzbi(getContext())) {
            zzdm("AnalyticsService registered in the app manifest and enabled");
        } else {
            zzdp("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!this.zzdpx && !this.zzdpo.isEmpty()) {
            zzxh();
        }
        zzxk();
    }

    public final void zzxj() {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        zzdn("Sync dispatching local hits");
        long j = this.zzdpw;
        zzxh();
        try {
            zzxi();
            zzwf().zzze();
            zzxk();
            if (this.zzdpw != j) {
                this.zzdpq.zzyw();
            }
        } catch (Throwable th) {
            zze("Sync local dispatch failed", th);
            zzxk();
        }
    }

    public final void zzxk() {
        boolean zIsConnected;
        long jMin;
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        if (!(!this.zzdpx && zzxn() > 0)) {
            this.zzdpq.unregister();
            zzxm();
            return;
        }
        if (this.zzdpo.isEmpty()) {
            this.zzdpq.unregister();
            zzxm();
            return;
        }
        if (zzaod.zzdsh.get().booleanValue()) {
            zIsConnected = true;
        } else {
            this.zzdpq.zzyu();
            zIsConnected = this.zzdpq.isConnected();
        }
        if (!zIsConnected) {
            zzxm();
            zzxl();
            return;
        }
        zzxl();
        long jZzxn = zzxn();
        long jZzzd = zzwf().zzzd();
        if (jZzzd != 0) {
            jMin = jZzxn - Math.abs(zzvx().currentTimeMillis() - jZzzd);
            if (jMin <= 0) {
                jMin = Math.min(zzanv.zzxx(), jZzxn);
            }
        } else {
            jMin = Math.min(zzanv.zzxx(), jZzxn);
        }
        zza("Dispatch scheduled (ms)", Long.valueOf(jMin));
        if (this.zzdpt.zzdp()) {
            this.zzdpt.zzt(Math.max(1L, jMin + this.zzdpt.zzyg()));
        } else {
            this.zzdpt.zzs(jMin);
        }
    }
}
