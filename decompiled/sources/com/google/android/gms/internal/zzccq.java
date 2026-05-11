package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzccq extends zzcdu {
    private final Map<String, Map<String, String>> zziru;
    private final Map<String, Map<String, Boolean>> zzirv;
    private final Map<String, Map<String, Boolean>> zzirw;
    private final Map<String, zzcge> zzirx;
    private final Map<String, String> zziry;

    zzccq(zzccw zzccwVar) {
        super(zzccwVar);
        this.zziru = new ArrayMap();
        this.zzirv = new ArrayMap();
        this.zzirw = new ArrayMap();
        this.zzirx = new ArrayMap();
        this.zziry = new ArrayMap();
    }

    private static Map<String, String> zza(zzcge zzcgeVar) {
        ArrayMap arrayMap = new ArrayMap();
        if (zzcgeVar != null && zzcgeVar.zziyn != null) {
            for (zzcgf zzcgfVar : zzcgeVar.zziyn) {
                if (zzcgfVar != null) {
                    arrayMap.put(zzcgfVar.key, zzcgfVar.value);
                }
            }
        }
        return arrayMap;
    }

    private final void zza(String str, zzcge zzcgeVar) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        if (zzcgeVar != null && zzcgeVar.zziyo != null) {
            for (zzcgd zzcgdVar : zzcgeVar.zziyo) {
                if (zzcgdVar != null) {
                    String strZzil = AppMeasurement.Event.zzil(zzcgdVar.name);
                    if (strZzil != null) {
                        zzcgdVar.name = strZzil;
                    }
                    arrayMap.put(zzcgdVar.name, zzcgdVar.zziyj);
                    arrayMap2.put(zzcgdVar.name, zzcgdVar.zziyk);
                }
            }
        }
        this.zzirv.put(str, arrayMap);
        this.zzirw.put(str, arrayMap2);
    }

    @WorkerThread
    private final zzcge zzc(String str, byte[] bArr) throws IllegalStateException {
        if (bArr == null) {
            return new zzcge();
        }
        zzegx zzegxVarZzh = zzegx.zzh(bArr, 0, bArr.length);
        zzcge zzcgeVar = new zzcge();
        try {
            zzcgeVar.zza(zzegxVarZzh);
            zzaum().zzayk().zze("Parsed config. version, gmp_app_id", zzcgeVar.zziyl, zzcgeVar.zzilt);
            return zzcgeVar;
        } catch (IOException e) {
            zzaum().zzayg().zze("Unable to merge remote config. appId", zzcbw.zzjf(str), e);
            return new zzcge();
        }
    }

    @WorkerThread
    private final void zzjm(String str) throws Throwable {
        zzwk();
        zzuj();
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        if (this.zzirx.get(str) == null) {
            byte[] bArrZziy = zzaug().zziy(str);
            if (bArrZziy == null) {
                this.zziru.put(str, null);
                this.zzirv.put(str, null);
                this.zzirw.put(str, null);
                this.zzirx.put(str, null);
                this.zziry.put(str, null);
                return;
            }
            zzcge zzcgeVarZzc = zzc(str, bArrZziy);
            this.zziru.put(str, zza(zzcgeVarZzc));
            zza(str, zzcgeVarZzc);
            this.zzirx.put(str, zzcgeVarZzc);
            this.zziry.put(str, null);
        }
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    final String zzan(String str, String str2) {
        zzuj();
        zzjm(str);
        Map<String, String> map = this.zziru.get(str);
        if (map != null) {
            return map.get(str2);
        }
        return null;
    }

    @WorkerThread
    final boolean zzao(String str, String str2) throws Throwable {
        Boolean bool;
        zzuj();
        zzjm(str);
        if (zzaui().zzkg(str) && zzcfw.zzkd(str2)) {
            return true;
        }
        if (zzaui().zzkh(str) && zzcfw.zzju(str2)) {
            return true;
        }
        Map<String, Boolean> map = this.zzirv.get(str);
        if (map != null && (bool = map.get(str2)) != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @WorkerThread
    final boolean zzap(String str, String str2) {
        Boolean bool;
        zzuj();
        zzjm(str);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(str2)) {
            return true;
        }
        Map<String, Boolean> map = this.zzirw.get(str);
        if (map != null && (bool = map.get(str2)) != null) {
            return bool.booleanValue();
        }
        return false;
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
    protected final boolean zzb(String str, byte[] bArr, String str2) throws IllegalStateException {
        zzwk();
        zzuj();
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        zzcge zzcgeVarZzc = zzc(str, bArr);
        if (zzcgeVarZzc == null) {
            return false;
        }
        zza(str, zzcgeVarZzc);
        this.zzirx.put(str, zzcgeVarZzc);
        this.zziry.put(str, str2);
        this.zziru.put(str, zza(zzcgeVarZzc));
        zzcau zzcauVarZzatz = zzatz();
        zzcfx[] zzcfxVarArr = zzcgeVarZzc.zziyp;
        com.google.android.gms.common.internal.zzbp.zzu(zzcfxVarArr);
        for (zzcfx zzcfxVar : zzcfxVarArr) {
            for (zzcfy zzcfyVar : zzcfxVar.zzixk) {
                String strZzil = AppMeasurement.Event.zzil(zzcfyVar.zzixn);
                if (strZzil != null) {
                    zzcfyVar.zzixn = strZzil;
                }
                zzcfz[] zzcfzVarArr = zzcfyVar.zzixo;
                for (zzcfz zzcfzVar : zzcfzVarArr) {
                    String strZzil2 = AppMeasurement.Param.zzil(zzcfzVar.zzixv);
                    if (strZzil2 != null) {
                        zzcfzVar.zzixv = strZzil2;
                    }
                }
            }
            for (zzcgb zzcgbVar : zzcfxVar.zzixj) {
                String strZzil3 = AppMeasurement.UserProperty.zzil(zzcgbVar.zziyc);
                if (strZzil3 != null) {
                    zzcgbVar.zziyc = strZzil3;
                }
            }
        }
        zzcauVarZzatz.zzaug().zza(str, zzcfxVarArr);
        try {
            zzcgeVarZzc.zziyp = null;
            byte[] bArr2 = new byte[zzcgeVarZzc.zzhi()];
            zzcgeVarZzc.zza(zzegy.zzi(bArr2, 0, bArr2.length));
            bArr = bArr2;
        } catch (IOException e) {
            zzaum().zzayg().zze("Unable to serialize reduced-size config. Storing full config instead. appId", zzcbw.zzjf(str), e);
        }
        zzcay zzcayVarZzaug = zzaug();
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        zzcayVarZzaug.zzuj();
        zzcayVarZzaug.zzwk();
        new ContentValues().put("remote_config", bArr);
        try {
            if (zzcayVarZzaug.getWritableDatabase().update("apps", r2, "app_id = ?", new String[]{str}) == 0) {
                zzcayVarZzaug.zzaum().zzaye().zzj("Failed to update remote config (got 0). appId", zzcbw.zzjf(str));
            }
        } catch (SQLiteException e2) {
            zzcayVarZzaug.zzaum().zzaye().zze("Error storing remote config. appId", zzcbw.zzjf(str), e2);
        }
        return true;
    }

    @WorkerThread
    protected final zzcge zzjn(String str) {
        zzwk();
        zzuj();
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        zzjm(str);
        return this.zzirx.get(str);
    }

    @WorkerThread
    protected final String zzjo(String str) {
        zzuj();
        return this.zziry.get(str);
    }

    @WorkerThread
    protected final void zzjp(String str) {
        zzuj();
        this.zziry.put(str, null);
    }

    @WorkerThread
    final void zzjq(String str) {
        zzuj();
        this.zzirx.remove(str);
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    @Override // com.google.android.gms.internal.zzcdu
    protected final void zzuk() {
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ com.google.android.gms.common.util.zzd zzvx() {
        return super.zzvx();
    }
}
