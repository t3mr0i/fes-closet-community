package com.google.android.gms.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class zzccw {
    private static volatile zzccw zzisq;
    private final Context mContext;
    private final com.google.android.gms.common.util.zzd zzasb;
    private final boolean zzdod;
    private final zzcax zzisr;
    private final zzcch zziss;
    private final zzcbw zzist;
    private final zzccr zzisu;
    private final zzcfl zzisv;
    private final zzccq zzisw;
    private final AppMeasurement zzisx;
    private final FirebaseAnalytics zzisy;
    private final zzcfw zzisz;
    private final zzcbu zzita;
    private final zzcay zzitb;
    private final zzcbs zzitc;
    private final zzcca zzitd;
    private final zzcek zzite;
    private final zzceo zzitf;
    private final zzcbe zzitg;
    private final zzcdw zzith;
    private final zzcbr zziti;
    private final zzccf zzitj;
    private final zzcfr zzitk;
    private final zzcau zzitl;
    private final zzcan zzitm;
    private boolean zzitn;
    private Boolean zzito;
    private long zzitp;
    private FileLock zzitq;
    private FileChannel zzitr;
    private List<Long> zzits;
    private List<Runnable> zzitt;
    private int zzitu;
    private int zzitv;
    private long zzitw;
    private long zzitx;
    private boolean zzity;
    private boolean zzitz;
    private boolean zziua;
    private final long zziub;

    class zza implements zzcba {
        List<zzcgh> zzaob;
        zzcgk zziud;
        List<Long> zziue;
        private long zziuf;

        private zza() {
        }

        /* synthetic */ zza(zzccw zzccwVar, zzccx zzccxVar) {
            this();
        }

        private static long zza(zzcgh zzcghVar) {
            return ((zzcghVar.zziyx.longValue() / 1000) / 60) / 60;
        }

        @Override // com.google.android.gms.internal.zzcba
        public final boolean zza(long j, zzcgh zzcghVar) {
            com.google.android.gms.common.internal.zzbp.zzu(zzcghVar);
            if (this.zzaob == null) {
                this.zzaob = new ArrayList();
            }
            if (this.zziue == null) {
                this.zziue = new ArrayList();
            }
            if (this.zzaob.size() > 0 && zza(this.zzaob.get(0)) != zza(zzcghVar)) {
                return false;
            }
            long jZzhi = this.zziuf + zzcghVar.zzhi();
            if (jZzhi >= zzcax.zzaws()) {
                return false;
            }
            this.zziuf = jZzhi;
            this.zzaob.add(zzcghVar);
            this.zziue.add(Long.valueOf(j));
            return this.zzaob.size() < zzcax.zzawt();
        }

        @Override // com.google.android.gms.internal.zzcba
        public final void zzb(zzcgk zzcgkVar) {
            com.google.android.gms.common.internal.zzbp.zzu(zzcgkVar);
            this.zziud = zzcgkVar;
        }
    }

    private zzccw(zzcdv zzcdvVar) throws IllegalStateException {
        zzcby zzcbyVarZzayi;
        String strConcat;
        com.google.android.gms.common.internal.zzbp.zzu(zzcdvVar);
        this.mContext = zzcdvVar.mContext;
        this.zzitw = -1L;
        this.zzasb = com.google.android.gms.common.util.zzh.zzald();
        this.zziub = this.zzasb.currentTimeMillis();
        this.zzisr = new zzcax(this);
        zzcch zzcchVar = new zzcch(this);
        zzcchVar.initialize();
        this.zziss = zzcchVar;
        zzcbw zzcbwVar = new zzcbw(this);
        zzcbwVar.initialize();
        this.zzist = zzcbwVar;
        zzaum().zzayi().zzj("App measurement is starting up, version", Long.valueOf(zzcax.zzauw()));
        zzcax.zzawl();
        zzaum().zzayi().log("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzcfw zzcfwVar = new zzcfw(this);
        zzcfwVar.initialize();
        this.zzisz = zzcfwVar;
        zzcbu zzcbuVar = new zzcbu(this);
        zzcbuVar.initialize();
        this.zzita = zzcbuVar;
        zzcbe zzcbeVar = new zzcbe(this);
        zzcbeVar.initialize();
        this.zzitg = zzcbeVar;
        zzcbr zzcbrVar = new zzcbr(this);
        zzcbrVar.initialize();
        this.zziti = zzcbrVar;
        zzcax.zzawl();
        String appId = zzcbrVar.getAppId();
        if (zzaui().zzke(appId)) {
            zzcbyVarZzayi = zzaum().zzayi();
            strConcat = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
        } else {
            zzcbyVarZzayi = zzaum().zzayi();
            String strValueOf = String.valueOf(appId);
            strConcat = strValueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(strValueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ");
        }
        zzcbyVarZzayi.log(strConcat);
        zzaum().zzayj().log("Debug-level message logging enabled");
        zzcay zzcayVar = new zzcay(this);
        zzcayVar.initialize();
        this.zzitb = zzcayVar;
        zzcbs zzcbsVar = new zzcbs(this);
        zzcbsVar.initialize();
        this.zzitc = zzcbsVar;
        zzcau zzcauVar = new zzcau(this);
        zzcauVar.initialize();
        this.zzitl = zzcauVar;
        this.zzitm = new zzcan(this);
        zzcca zzccaVar = new zzcca(this);
        zzccaVar.initialize();
        this.zzitd = zzccaVar;
        zzcek zzcekVar = new zzcek(this);
        zzcekVar.initialize();
        this.zzite = zzcekVar;
        zzceo zzceoVar = new zzceo(this);
        zzceoVar.initialize();
        this.zzitf = zzceoVar;
        zzcdw zzcdwVar = new zzcdw(this);
        zzcdwVar.initialize();
        this.zzith = zzcdwVar;
        zzcfr zzcfrVar = new zzcfr(this);
        zzcfrVar.initialize();
        this.zzitk = zzcfrVar;
        this.zzitj = new zzccf(this);
        this.zzisx = new AppMeasurement(this);
        this.zzisy = new FirebaseAnalytics(this);
        zzcfl zzcflVar = new zzcfl(this);
        zzcflVar.initialize();
        this.zzisv = zzcflVar;
        zzccq zzccqVar = new zzccq(this);
        zzccqVar.initialize();
        this.zzisw = zzccqVar;
        zzccr zzccrVar = new zzccr(this);
        zzccrVar.initialize();
        this.zzisu = zzccrVar;
        if (this.zzitu != this.zzitv) {
            zzaum().zzaye().zze("Not all components initialized", Integer.valueOf(this.zzitu), Integer.valueOf(this.zzitv));
        }
        this.zzdod = true;
        zzcax.zzawl();
        if (this.mContext.getApplicationContext() instanceof Application) {
            zzcdw zzcdwVarZzaua = zzaua();
            if (zzcdwVarZzaua.getContext().getApplicationContext() instanceof Application) {
                Application application = (Application) zzcdwVarZzaua.getContext().getApplicationContext();
                if (zzcdwVarZzaua.zzius == null) {
                    zzcdwVarZzaua.zzius = new zzcej(zzcdwVarZzaua, null);
                }
                application.unregisterActivityLifecycleCallbacks(zzcdwVarZzaua.zzius);
                application.registerActivityLifecycleCallbacks(zzcdwVarZzaua.zzius);
                zzcdwVarZzaua.zzaum().zzayk().log("Registered activity lifecycle callback");
            }
        } else {
            zzaum().zzayg().log("Application context is not an Application");
        }
        this.zzisu.zzg(new zzccx(this));
    }

    @WorkerThread
    private final int zza(FileChannel fileChannel) throws IOException {
        int i = 0;
        zzaul().zzuj();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzaum().zzaye().log("Bad chanel to read from");
        } else {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            try {
                fileChannel.position(0L);
                int i2 = fileChannel.read(byteBufferAllocate);
                if (i2 == 4) {
                    byteBufferAllocate.flip();
                    i = byteBufferAllocate.getInt();
                } else if (i2 != -1) {
                    zzaum().zzayg().zzj("Unexpected data length. Bytes read", Integer.valueOf(i2));
                }
            } catch (IOException e) {
                zzaum().zzaye().zzj("Failed to read from channel", e);
            }
        }
        return i;
    }

    private final void zza(zzcbf zzcbfVar, zzcas zzcasVar) throws IllegalStateException {
        boolean z;
        zzaul().zzuj();
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(zzcbfVar);
        com.google.android.gms.common.internal.zzbp.zzu(zzcasVar);
        com.google.android.gms.common.internal.zzbp.zzgg(zzcbfVar.mAppId);
        com.google.android.gms.common.internal.zzbp.zzbh(zzcbfVar.mAppId.equals(zzcasVar.packageName));
        zzcgk zzcgkVar = new zzcgk();
        zzcgkVar.zzizd = 1;
        zzcgkVar.zzizl = "android";
        zzcgkVar.zzch = zzcasVar.packageName;
        zzcgkVar.zzilu = zzcasVar.zzilu;
        zzcgkVar.zzhts = zzcasVar.zzhts;
        zzcgkVar.zzizy = zzcasVar.zzima == -2147483648L ? null : Integer.valueOf((int) zzcasVar.zzima);
        zzcgkVar.zzizp = Long.valueOf(zzcasVar.zzilv);
        zzcgkVar.zzilt = zzcasVar.zzilt;
        zzcgkVar.zzizu = zzcasVar.zzilw == 0 ? null : Long.valueOf(zzcasVar.zzilw);
        Pair<String, Boolean> pairZzjh = zzaun().zzjh(zzcasVar.packageName);
        if (pairZzjh != null && !TextUtils.isEmpty((CharSequence) pairZzjh.first)) {
            zzcgkVar.zzizr = (String) pairZzjh.first;
            zzcgkVar.zzizs = (Boolean) pairZzjh.second;
        } else if (!zzauc().zzdm(this.mContext)) {
            String string = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
            if (string == null) {
                zzaum().zzayg().zzj("null secure ID. appId", zzcbw.zzjf(zzcgkVar.zzch));
                string = "null";
            } else if (string.isEmpty()) {
                zzaum().zzayg().zzj("empty secure ID. appId", zzcbw.zzjf(zzcgkVar.zzch));
            }
            zzcgkVar.zzjab = string;
        }
        zzauc().zzwk();
        zzcgkVar.zzizm = Build.MODEL;
        zzauc().zzwk();
        zzcgkVar.zzcv = Build.VERSION.RELEASE;
        zzcgkVar.zzizo = Integer.valueOf((int) zzauc().zzaxx());
        zzcgkVar.zzizn = zzauc().zzaxy();
        zzcgkVar.zzizq = null;
        zzcgkVar.zzizg = null;
        zzcgkVar.zzizh = null;
        zzcgkVar.zzizi = null;
        zzcgkVar.zzjad = Long.valueOf(zzcasVar.zzimc);
        if (isEnabled() && zzcax.zzaxi()) {
            zzaub();
            zzcgkVar.zzjae = null;
        }
        zzcar zzcarVarZziw = zzaug().zziw(zzcasVar.packageName);
        if (zzcarVarZziw == null) {
            zzcarVarZziw = new zzcar(this, zzcasVar.packageName);
            zzcarVarZziw.zzim(zzaub().zzayb());
            zzcarVarZziw.zzip(zzcasVar.zzimb);
            zzcarVarZziw.zzin(zzcasVar.zzilt);
            zzcarVarZziw.zzio(zzaun().zzji(zzcasVar.packageName));
            zzcarVarZziw.zzaq(0L);
            zzcarVarZziw.zzal(0L);
            zzcarVarZziw.zzam(0L);
            zzcarVarZziw.setAppVersion(zzcasVar.zzhts);
            zzcarVarZziw.zzan(zzcasVar.zzima);
            zzcarVarZziw.zziq(zzcasVar.zzilu);
            zzcarVarZziw.zzao(zzcasVar.zzilv);
            zzcarVarZziw.zzap(zzcasVar.zzilw);
            zzcarVarZziw.setMeasurementEnabled(zzcasVar.zzily);
            zzcarVarZziw.zzaz(zzcasVar.zzimc);
            zzaug().zza(zzcarVarZziw);
        }
        zzcgkVar.zzizt = zzcarVarZziw.getAppInstanceId();
        zzcgkVar.zzimb = zzcarVarZziw.zzaur();
        List<zzcfv> listZziv = zzaug().zziv(zzcasVar.packageName);
        zzcgkVar.zzizf = new zzcgm[listZziv.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= listZziv.size()) {
                try {
                    break;
                } catch (IOException e) {
                    zzaum().zzaye().zze("Data loss. Failed to insert raw event metadata. appId", zzcbw.zzjf(zzcgkVar.zzch), e);
                    return;
                }
            } else {
                zzcgm zzcgmVar = new zzcgm();
                zzcgkVar.zzizf[i2] = zzcgmVar;
                zzcgmVar.name = listZziv.get(i2).mName;
                zzcgmVar.zzjai = Long.valueOf(listZziv.get(i2).zzixc);
                zzaui().zza(zzcgmVar, listZziv.get(i2).mValue);
                i = i2 + 1;
            }
        }
        long jZza = zzaug().zza(zzcgkVar);
        zzcay zzcayVarZzaug = zzaug();
        if (zzcbfVar.zzinj != null) {
            Iterator<String> it = zzcbfVar.zzinj.iterator();
            while (true) {
                if (!it.hasNext()) {
                    boolean zZzap = zzauj().zzap(zzcbfVar.mAppId, zzcbfVar.mName);
                    zzcaz zzcazVarZza = zzaug().zza(zzazg(), zzcbfVar.mAppId, false, false, false, false, false);
                    if (zZzap && zzcazVarZza.zzinb < this.zzisr.zzis(zzcbfVar.mAppId)) {
                        z = true;
                    }
                } else if ("_r".equals(it.next())) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (zzcayVarZzaug.zza(zzcbfVar, jZza, z)) {
            this.zzitx = 0L;
        }
    }

    private static void zza(zzcdt zzcdtVar) {
        if (zzcdtVar == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static void zza(zzcdu zzcduVar) {
        if (zzcduVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (!zzcduVar.isInitialized()) {
            throw new IllegalStateException("Component not initialized");
        }
    }

    @WorkerThread
    private final boolean zza(int i, FileChannel fileChannel) throws IOException {
        zzaul().zzuj();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzaum().zzaye().log("Bad chanel to read from");
            return false;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.putInt(i);
        byteBufferAllocate.flip();
        try {
            fileChannel.truncate(0L);
            fileChannel.write(byteBufferAllocate);
            fileChannel.force(true);
            if (fileChannel.size() == 4) {
                return true;
            }
            zzaum().zzaye().zzj("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            return true;
        } catch (IOException e) {
            zzaum().zzaye().zzj("Failed to write to channel", e);
            return false;
        }
    }

    private final zzcgg[] zza(String str, zzcgm[] zzcgmVarArr, zzcgh[] zzcghVarArr) {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        return zzatz().zza(str, zzcghVarArr, zzcgmVarArr);
    }

    static void zzatv() {
        zzcax.zzawl();
        throw new IllegalStateException("Unexpected call on client side");
    }

    private final zzccf zzazc() {
        if (this.zzitj == null) {
            throw new IllegalStateException("Network broadcast receiver not created");
        }
        return this.zzitj;
    }

    private final zzcfr zzazd() {
        zza((zzcdu) this.zzitk);
        return this.zzitk;
    }

    @WorkerThread
    private final boolean zzaze() {
        zzaul().zzuj();
        try {
            this.zzitr = new RandomAccessFile(new File(this.mContext.getFilesDir(), zzcax.zzawj()), "rw").getChannel();
            this.zzitq = this.zzitr.tryLock();
        } catch (FileNotFoundException e) {
            zzaum().zzaye().zzj("Failed to acquire storage lock", e);
        } catch (IOException e2) {
            zzaum().zzaye().zzj("Failed to access storage lock file", e2);
        }
        if (this.zzitq != null) {
            zzaum().zzayk().log("Storage concurrent access okay");
            return true;
        }
        zzaum().zzaye().log("Storage concurrent data access panic");
        return false;
    }

    private final long zzazg() {
        long jCurrentTimeMillis = this.zzasb.currentTimeMillis();
        zzcch zzcchVarZzaun = zzaun();
        zzcchVarZzaun.zzwk();
        zzcchVarZzaun.zzuj();
        long jNextInt = zzcchVarZzaun.zziqs.get();
        if (jNextInt == 0) {
            jNextInt = zzcchVarZzaun.zzaui().zzazz().nextInt(86400000) + 1;
            zzcchVarZzaun.zziqs.set(jNextInt);
        }
        return ((((jNextInt + jCurrentTimeMillis) / 1000) / 60) / 60) / 24;
    }

    private final boolean zzazi() {
        zzaul().zzuj();
        zzwk();
        return zzaug().zzaxo() || !TextUtils.isEmpty(zzaug().zzaxj());
    }

    @WorkerThread
    private final void zzazj() {
        long jZzawy;
        long jMax;
        zzaul().zzuj();
        zzwk();
        if (zzazm()) {
            if (this.zzitx > 0) {
                long jAbs = 3600000 - Math.abs(this.zzasb.elapsedRealtime() - this.zzitx);
                if (jAbs > 0) {
                    zzaum().zzayk().zzj("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(jAbs));
                    zzazc().unregister();
                    zzazd().cancel();
                    return;
                }
                this.zzitx = 0L;
            }
            if (!zzayw() || !zzazi()) {
                zzaum().zzayk().log("Nothing to upload or uploading impossible");
                zzazc().unregister();
                zzazd().cancel();
                return;
            }
            long jCurrentTimeMillis = this.zzasb.currentTimeMillis();
            long jZzaxe = zzcax.zzaxe();
            boolean z = zzaug().zzaxp() || zzaug().zzaxk();
            if (z) {
                String strZzaxh = this.zzisr.zzaxh();
                jZzawy = (TextUtils.isEmpty(strZzaxh) || ".none.".equals(strZzaxh)) ? zzcax.zzawz() : zzcax.zzaxa();
            } else {
                jZzawy = zzcax.zzawy();
            }
            long j = zzaun().zziqo.get();
            long j2 = zzaun().zziqp.get();
            long jMax2 = Math.max(zzaug().zzaxm(), zzaug().zzaxn());
            if (jMax2 != 0) {
                long jAbs2 = jCurrentTimeMillis - Math.abs(jMax2 - jCurrentTimeMillis);
                long jAbs3 = jCurrentTimeMillis - Math.abs(j - jCurrentTimeMillis);
                long jAbs4 = jCurrentTimeMillis - Math.abs(j2 - jCurrentTimeMillis);
                long jMax3 = Math.max(jAbs3, jAbs4);
                long jZzaxf = jAbs2 + jZzaxe;
                if (z && jMax3 > 0) {
                    jZzaxf = Math.min(jAbs2, jMax3) + jZzawy;
                }
                if (!zzaui().zzf(jMax3, jZzawy)) {
                    jZzaxf = jMax3 + jZzawy;
                }
                if (jAbs4 != 0 && jAbs4 >= jAbs2) {
                    int i = 0;
                    while (true) {
                        if (i >= zzcax.zzaxg()) {
                            jMax = 0;
                            break;
                        }
                        jZzaxf += (1 << i) * zzcax.zzaxf();
                        if (jZzaxf > jAbs4) {
                            jMax = jZzaxf;
                            break;
                        }
                        i++;
                    }
                } else {
                    jMax = jZzaxf;
                }
            } else {
                jMax = 0;
            }
            if (jMax == 0) {
                zzaum().zzayk().log("Next upload time is 0");
                zzazc().unregister();
                zzazd().cancel();
                return;
            }
            if (!zzazb().zzyx()) {
                zzaum().zzayk().log("No network");
                zzazc().zzyu();
                zzazd().cancel();
                return;
            }
            long j3 = zzaun().zziqq.get();
            long jZzawx = zzcax.zzawx();
            if (!zzaui().zzf(j3, jZzawx)) {
                jMax = Math.max(jMax, j3 + jZzawx);
            }
            zzazc().unregister();
            long jCurrentTimeMillis2 = jMax - this.zzasb.currentTimeMillis();
            if (jCurrentTimeMillis2 <= 0) {
                jCurrentTimeMillis2 = zzcax.zzaxb();
                zzaun().zziqo.set(this.zzasb.currentTimeMillis());
            }
            zzaum().zzayk().zzj("Upload scheduled in approximately ms", Long.valueOf(jCurrentTimeMillis2));
            zzazd().zzs(jCurrentTimeMillis2);
        }
    }

    @WorkerThread
    private final boolean zzazm() {
        zzaul().zzuj();
        zzwk();
        return this.zzitn;
    }

    @WorkerThread
    private final void zzazn() throws IllegalStateException {
        zzaul().zzuj();
        if (this.zzity || this.zzitz || this.zziua) {
            zzaum().zzayk().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzity), Boolean.valueOf(this.zzitz), Boolean.valueOf(this.zziua));
            return;
        }
        zzaum().zzayk().log("Stopping uploading service(s)");
        if (this.zzitt != null) {
            Iterator<Runnable> it = this.zzitt.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
            this.zzitt.clear();
        }
    }

    @WorkerThread
    private final void zzb(zzcar zzcarVar) throws IllegalStateException {
        ArrayMap arrayMap;
        zzaul().zzuj();
        if (TextUtils.isEmpty(zzcarVar.getGmpAppId())) {
            zzb(zzcarVar.getAppId(), 204, null, null, null);
            return;
        }
        String gmpAppId = zzcarVar.getGmpAppId();
        String appInstanceId = zzcarVar.getAppInstanceId();
        Uri.Builder builder = new Uri.Builder();
        Uri.Builder builderEncodedAuthority = builder.scheme(zzcbm.zzioe.get()).encodedAuthority(zzcbm.zziof.get());
        String strValueOf = String.valueOf(gmpAppId);
        builderEncodedAuthority.path(strValueOf.length() != 0 ? "config/app/".concat(strValueOf) : new String("config/app/")).appendQueryParameter("app_instance_id", appInstanceId).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", "11400");
        String string = builder.build().toString();
        try {
            URL url = new URL(string);
            zzaum().zzayk().zzj("Fetching remote configuration", zzcarVar.getAppId());
            zzcge zzcgeVarZzjn = zzauj().zzjn(zzcarVar.getAppId());
            String strZzjo = zzauj().zzjo(zzcarVar.getAppId());
            if (zzcgeVarZzjn == null || TextUtils.isEmpty(strZzjo)) {
                arrayMap = null;
            } else {
                ArrayMap arrayMap2 = new ArrayMap();
                arrayMap2.put("If-Modified-Since", strZzjo);
                arrayMap = arrayMap2;
            }
            this.zzity = true;
            zzcca zzccaVarZzazb = zzazb();
            String appId = zzcarVar.getAppId();
            zzcda zzcdaVar = new zzcda(this);
            zzccaVarZzazb.zzuj();
            zzccaVarZzazb.zzwk();
            com.google.android.gms.common.internal.zzbp.zzu(url);
            com.google.android.gms.common.internal.zzbp.zzu(zzcdaVar);
            zzccaVarZzazb.zzaul().zzh(new zzcce(zzccaVarZzazb, appId, url, null, arrayMap, zzcdaVar));
        } catch (MalformedURLException e) {
            zzaum().zzaye().zze("Failed to parse config URL. Not fetching. appId", zzcbw.zzjf(zzcarVar.getAppId()), string);
        }
    }

    @WorkerThread
    private final void zzc(zzcbk zzcbkVar, zzcas zzcasVar) throws IllegalStateException {
        long jRound;
        zzcfv zzcfvVar;
        zzcbg zzcbgVarZzbb;
        zzcar zzcarVarZziw;
        com.google.android.gms.common.internal.zzbp.zzu(zzcasVar);
        com.google.android.gms.common.internal.zzbp.zzgg(zzcasVar.packageName);
        long jNanoTime = System.nanoTime();
        zzaul().zzuj();
        zzwk();
        String str = zzcasVar.packageName;
        zzaui();
        if (zzcfw.zzd(zzcbkVar, zzcasVar)) {
            if (!zzcasVar.zzily) {
                zzf(zzcasVar);
                return;
            }
            if (zzauj().zzao(str, zzcbkVar.name)) {
                zzaum().zzayg().zze("Dropping blacklisted event. appId", zzcbw.zzjf(str), zzauh().zzjc(zzcbkVar.name));
                boolean z = zzaui().zzkg(str) || zzaui().zzkh(str);
                if (!z && !"_err".equals(zzcbkVar.name)) {
                    zzaui().zza(str, 11, "_ev", zzcbkVar.name, 0);
                }
                if (!z || (zzcarVarZziw = zzaug().zziw(str)) == null || Math.abs(this.zzasb.currentTimeMillis() - Math.max(zzcarVarZziw.zzavb(), zzcarVarZziw.zzava())) <= zzcax.zzawp()) {
                    return;
                }
                zzaum().zzayj().log("Fetching config for blacklisted app");
                zzb(zzcarVarZziw);
                return;
            }
            if (zzaum().zzad(2)) {
                zzaum().zzayk().zzj("Logging event", zzauh().zzb(zzcbkVar));
            }
            zzaug().beginTransaction();
            try {
                Bundle bundleZzaya = zzcbkVar.zzinq.zzaya();
                zzf(zzcasVar);
                if ("_iap".equals(zzcbkVar.name) || FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzcbkVar.name)) {
                    String string = bundleZzaya.getString(FirebaseAnalytics.Param.CURRENCY);
                    if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzcbkVar.name)) {
                        double d = bundleZzaya.getDouble(FirebaseAnalytics.Param.VALUE) * 1000000.0d;
                        if (d == 0.0d) {
                            d = bundleZzaya.getLong(FirebaseAnalytics.Param.VALUE) * 1000000.0d;
                        }
                        if (d > 9.223372036854776E18d || d < -9.223372036854776E18d) {
                            zzaum().zzayg().zze("Data lost. Currency value is too big. appId", zzcbw.zzjf(str), Double.valueOf(d));
                            zzaug().setTransactionSuccessful();
                            return;
                        }
                        jRound = Math.round(d);
                    } else {
                        jRound = bundleZzaya.getLong(FirebaseAnalytics.Param.VALUE);
                    }
                    if (!TextUtils.isEmpty(string)) {
                        String upperCase = string.toUpperCase(Locale.US);
                        if (upperCase.matches("[A-Z]{3}")) {
                            String strValueOf = String.valueOf("_ltv_");
                            String strValueOf2 = String.valueOf(upperCase);
                            String strConcat = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
                            zzcfv zzcfvVarZzah = zzaug().zzah(str, strConcat);
                            if (zzcfvVarZzah == null || !(zzcfvVarZzah.mValue instanceof Long)) {
                                zzcay zzcayVarZzaug = zzaug();
                                int iZzb = this.zzisr.zzb(str, zzcbm.zzipe) - 1;
                                com.google.android.gms.common.internal.zzbp.zzgg(str);
                                zzcayVarZzaug.zzuj();
                                zzcayVarZzaug.zzwk();
                                try {
                                    zzcayVarZzaug.getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(iZzb)});
                                } catch (SQLiteException e) {
                                    zzcayVarZzaug.zzaum().zzaye().zze("Error pruning currencies. appId", zzcbw.zzjf(str), e);
                                }
                                zzcfvVar = new zzcfv(str, zzcbkVar.zzimf, strConcat, this.zzasb.currentTimeMillis(), Long.valueOf(jRound));
                            } else {
                                zzcfvVar = new zzcfv(str, zzcbkVar.zzimf, strConcat, this.zzasb.currentTimeMillis(), Long.valueOf(jRound + ((Long) zzcfvVarZzah.mValue).longValue()));
                            }
                            if (!zzaug().zza(zzcfvVar)) {
                                zzaum().zzaye().zzd("Too many unique user properties are set. Ignoring user property. appId", zzcbw.zzjf(str), zzauh().zzje(zzcfvVar.mName), zzcfvVar.mValue);
                                zzaui().zza(str, 9, (String) null, (String) null, 0);
                            }
                        }
                    }
                }
                boolean zZzju = zzcfw.zzju(zzcbkVar.name);
                boolean zEquals = "_err".equals(zzcbkVar.name);
                zzcaz zzcazVarZza = zzaug().zza(zzazg(), str, true, zZzju, false, zEquals, false);
                long jZzavx = zzcazVarZza.zzimy - zzcax.zzavx();
                if (jZzavx > 0) {
                    if (jZzavx % 1000 == 1) {
                        zzaum().zzaye().zze("Data loss. Too many events logged. appId, count", zzcbw.zzjf(str), Long.valueOf(zzcazVarZza.zzimy));
                    }
                    zzaug().setTransactionSuccessful();
                    return;
                }
                if (zZzju) {
                    long jZzavy = zzcazVarZza.zzimx - zzcax.zzavy();
                    if (jZzavy > 0) {
                        if (jZzavy % 1000 == 1) {
                            zzaum().zzaye().zze("Data loss. Too many public events logged. appId, count", zzcbw.zzjf(str), Long.valueOf(zzcazVarZza.zzimx));
                        }
                        zzaui().zza(str, 16, "_ev", zzcbkVar.name, 0);
                        zzaug().setTransactionSuccessful();
                        return;
                    }
                }
                if (zEquals) {
                    long jMax = zzcazVarZza.zzina - Math.max(0, Math.min(1000000, this.zzisr.zzb(zzcasVar.packageName, zzcbm.zziol)));
                    if (jMax > 0) {
                        if (jMax == 1) {
                            zzaum().zzaye().zze("Too many error events logged. appId, count", zzcbw.zzjf(str), Long.valueOf(zzcazVarZza.zzina));
                        }
                        zzaug().setTransactionSuccessful();
                        return;
                    }
                }
                zzaui().zza(bundleZzaya, "_o", zzcbkVar.zzimf);
                if (zzaui().zzke(str)) {
                    zzaui().zza(bundleZzaya, "_dbg", (Object) 1L);
                    zzaui().zza(bundleZzaya, "_r", (Object) 1L);
                }
                long jZzix = zzaug().zzix(str);
                if (jZzix > 0) {
                    zzaum().zzayg().zze("Data lost. Too many events stored on disk, deleted. appId", zzcbw.zzjf(str), Long.valueOf(jZzix));
                }
                zzcbf zzcbfVar = new zzcbf(this, zzcbkVar.zzimf, str, zzcbkVar.name, zzcbkVar.zzinr, 0L, bundleZzaya);
                zzcbg zzcbgVarZzaf = zzaug().zzaf(str, zzcbfVar.mName);
                if (zzcbgVarZzaf == null) {
                    long jZzja = zzaug().zzja(str);
                    zzcax.zzavw();
                    if (jZzja >= 500) {
                        zzaum().zzaye().zzd("Too many event names used, ignoring event. appId, name, supported count", zzcbw.zzjf(str), zzauh().zzjc(zzcbfVar.mName), Integer.valueOf(zzcax.zzavw()));
                        zzaui().zza(str, 8, (String) null, (String) null, 0);
                        return;
                    }
                    zzcbgVarZzbb = new zzcbg(str, zzcbfVar.mName, 0L, 0L, zzcbfVar.zzfdb);
                } else {
                    zzcbfVar = zzcbfVar.zza(this, zzcbgVarZzaf.zzinm);
                    zzcbgVarZzbb = zzcbgVarZzaf.zzbb(zzcbfVar.zzfdb);
                }
                zzaug().zza(zzcbgVarZzbb);
                zza(zzcbfVar, zzcasVar);
                zzaug().setTransactionSuccessful();
                if (zzaum().zzad(2)) {
                    zzaum().zzayk().zzj("Event recorded", zzauh().zza(zzcbfVar));
                }
                zzaug().endTransaction();
                zzazj();
                zzaum().zzayk().zzj("Background event processing time, ms", Long.valueOf(((System.nanoTime() - jNanoTime) + 500000) / 1000000));
            } finally {
                zzaug().endTransaction();
            }
        }
    }

    public static zzccw zzdn(Context context) {
        com.google.android.gms.common.internal.zzbp.zzu(context);
        com.google.android.gms.common.internal.zzbp.zzu(context.getApplicationContext());
        if (zzisq == null) {
            synchronized (zzccw.class) {
                if (zzisq == null) {
                    zzisq = new zzccw(new zzcdv(context));
                }
            }
        }
        return zzisq;
    }

    @WorkerThread
    private final void zzf(zzcas zzcasVar) {
        boolean z = true;
        zzaul().zzuj();
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(zzcasVar);
        com.google.android.gms.common.internal.zzbp.zzgg(zzcasVar.packageName);
        zzcar zzcarVarZziw = zzaug().zziw(zzcasVar.packageName);
        String strZzji = zzaun().zzji(zzcasVar.packageName);
        boolean z2 = false;
        if (zzcarVarZziw == null) {
            zzcarVarZziw = new zzcar(this, zzcasVar.packageName);
            zzcarVarZziw.zzim(zzaub().zzayb());
            zzcarVarZziw.zzio(strZzji);
            z2 = true;
        } else if (!strZzji.equals(zzcarVarZziw.zzauq())) {
            zzcarVarZziw.zzio(strZzji);
            zzcarVarZziw.zzim(zzaub().zzayb());
            z2 = true;
        }
        if (!TextUtils.isEmpty(zzcasVar.zzilt) && !zzcasVar.zzilt.equals(zzcarVarZziw.getGmpAppId())) {
            zzcarVarZziw.zzin(zzcasVar.zzilt);
            z2 = true;
        }
        if (!TextUtils.isEmpty(zzcasVar.zzimb) && !zzcasVar.zzimb.equals(zzcarVarZziw.zzaur())) {
            zzcarVarZziw.zzip(zzcasVar.zzimb);
            z2 = true;
        }
        if (zzcasVar.zzilv != 0 && zzcasVar.zzilv != zzcarVarZziw.zzauw()) {
            zzcarVarZziw.zzao(zzcasVar.zzilv);
            z2 = true;
        }
        if (!TextUtils.isEmpty(zzcasVar.zzhts) && !zzcasVar.zzhts.equals(zzcarVarZziw.zzuo())) {
            zzcarVarZziw.setAppVersion(zzcasVar.zzhts);
            z2 = true;
        }
        if (zzcasVar.zzima != zzcarVarZziw.zzauu()) {
            zzcarVarZziw.zzan(zzcasVar.zzima);
            z2 = true;
        }
        if (zzcasVar.zzilu != null && !zzcasVar.zzilu.equals(zzcarVarZziw.zzauv())) {
            zzcarVarZziw.zziq(zzcasVar.zzilu);
            z2 = true;
        }
        if (zzcasVar.zzilw != zzcarVarZziw.zzaux()) {
            zzcarVarZziw.zzap(zzcasVar.zzilw);
            z2 = true;
        }
        if (zzcasVar.zzily != zzcarVarZziw.zzauy()) {
            zzcarVarZziw.setMeasurementEnabled(zzcasVar.zzily);
            z2 = true;
        }
        if (!TextUtils.isEmpty(zzcasVar.zzilx) && !zzcasVar.zzilx.equals(zzcarVarZziw.zzavj())) {
            zzcarVarZziw.zzir(zzcasVar.zzilx);
            z2 = true;
        }
        if (zzcasVar.zzimc != zzcarVarZziw.zzavl()) {
            zzcarVarZziw.zzaz(zzcasVar.zzimc);
        } else {
            z = z2;
        }
        if (z) {
            zzaug().zza(zzcarVarZziw);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x0366 A[Catch: all -> 0x019d, TryCatch #5 {all -> 0x019d, blocks: (B:3:0x0007, B:16:0x007e, B:17:0x0081, B:19:0x0085, B:23:0x0090, B:24:0x00a4, B:26:0x00ac, B:28:0x00c4, B:30:0x00f9, B:34:0x010a, B:36:0x011c, B:120:0x033b, B:122:0x0353, B:178:0x0584, B:124:0x0366, B:126:0x0374, B:127:0x0381, B:129:0x0390, B:131:0x039c, B:132:0x03a6, B:133:0x03aa, B:135:0x03b4, B:138:0x03c2, B:140:0x0420, B:141:0x047c, B:143:0x04a5, B:144:0x04ae, B:146:0x04b3, B:148:0x04c1, B:150:0x04ca, B:151:0x04d1, B:153:0x04d4, B:154:0x04dd, B:165:0x0551, B:155:0x04df, B:158:0x04f1, B:160:0x051b, B:162:0x0541, B:164:0x054d, B:166:0x0555, B:171:0x0566, B:173:0x0575, B:175:0x0579, B:176:0x057d, B:177:0x0581, B:180:0x0599, B:181:0x05a7, B:183:0x05be, B:185:0x05c6, B:186:0x05d4, B:187:0x0602, B:189:0x0609, B:191:0x0621, B:192:0x0627, B:194:0x0639, B:195:0x063f, B:196:0x0642, B:198:0x0650, B:199:0x0665, B:201:0x066c, B:203:0x067d, B:230:0x0757, B:208:0x0695, B:205:0x0681, B:207:0x068b, B:229:0x0740, B:209:0x069e, B:210:0x06af, B:211:0x06bd, B:232:0x0760, B:216:0x06d4, B:218:0x06db, B:220:0x06e5, B:221:0x06e9, B:225:0x06fd, B:226:0x0701, B:234:0x0776, B:48:0x0198, B:84:0x0293, B:106:0x030a, B:113:0x0329, B:97:0x02d3, B:89:0x02ac, B:116:0x0331, B:117:0x0334, B:63:0x01f2, B:72:0x0220), top: B:255:0x0007, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x05bc A[PHI: r13
      0x05bc: PHI (r13v4 boolean) = (r13v3 boolean), (r13v3 boolean), (r13v3 boolean), (r13v3 boolean), (r13v1 boolean) binds: [B:156:0x04ed, B:157:0x04ef, B:159:0x0519, B:181:0x05a7, B:123:0x0364] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0776 A[Catch: all -> 0x019d, TRY_LEAVE, TryCatch #5 {all -> 0x019d, blocks: (B:3:0x0007, B:16:0x007e, B:17:0x0081, B:19:0x0085, B:23:0x0090, B:24:0x00a4, B:26:0x00ac, B:28:0x00c4, B:30:0x00f9, B:34:0x010a, B:36:0x011c, B:120:0x033b, B:122:0x0353, B:178:0x0584, B:124:0x0366, B:126:0x0374, B:127:0x0381, B:129:0x0390, B:131:0x039c, B:132:0x03a6, B:133:0x03aa, B:135:0x03b4, B:138:0x03c2, B:140:0x0420, B:141:0x047c, B:143:0x04a5, B:144:0x04ae, B:146:0x04b3, B:148:0x04c1, B:150:0x04ca, B:151:0x04d1, B:153:0x04d4, B:154:0x04dd, B:165:0x0551, B:155:0x04df, B:158:0x04f1, B:160:0x051b, B:162:0x0541, B:164:0x054d, B:166:0x0555, B:171:0x0566, B:173:0x0575, B:175:0x0579, B:176:0x057d, B:177:0x0581, B:180:0x0599, B:181:0x05a7, B:183:0x05be, B:185:0x05c6, B:186:0x05d4, B:187:0x0602, B:189:0x0609, B:191:0x0621, B:192:0x0627, B:194:0x0639, B:195:0x063f, B:196:0x0642, B:198:0x0650, B:199:0x0665, B:201:0x066c, B:203:0x067d, B:230:0x0757, B:208:0x0695, B:205:0x0681, B:207:0x068b, B:229:0x0740, B:209:0x069e, B:210:0x06af, B:211:0x06bd, B:232:0x0760, B:216:0x06d4, B:218:0x06db, B:220:0x06e5, B:221:0x06e9, B:225:0x06fd, B:226:0x0701, B:234:0x0776, B:48:0x0198, B:84:0x0293, B:106:0x030a, B:113:0x0329, B:97:0x02d3, B:89:0x02ac, B:116:0x0331, B:117:0x0334, B:63:0x01f2, B:72:0x0220), top: B:255:0x0007, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0090 A[Catch: all -> 0x019d, TryCatch #5 {all -> 0x019d, blocks: (B:3:0x0007, B:16:0x007e, B:17:0x0081, B:19:0x0085, B:23:0x0090, B:24:0x00a4, B:26:0x00ac, B:28:0x00c4, B:30:0x00f9, B:34:0x010a, B:36:0x011c, B:120:0x033b, B:122:0x0353, B:178:0x0584, B:124:0x0366, B:126:0x0374, B:127:0x0381, B:129:0x0390, B:131:0x039c, B:132:0x03a6, B:133:0x03aa, B:135:0x03b4, B:138:0x03c2, B:140:0x0420, B:141:0x047c, B:143:0x04a5, B:144:0x04ae, B:146:0x04b3, B:148:0x04c1, B:150:0x04ca, B:151:0x04d1, B:153:0x04d4, B:154:0x04dd, B:165:0x0551, B:155:0x04df, B:158:0x04f1, B:160:0x051b, B:162:0x0541, B:164:0x054d, B:166:0x0555, B:171:0x0566, B:173:0x0575, B:175:0x0579, B:176:0x057d, B:177:0x0581, B:180:0x0599, B:181:0x05a7, B:183:0x05be, B:185:0x05c6, B:186:0x05d4, B:187:0x0602, B:189:0x0609, B:191:0x0621, B:192:0x0627, B:194:0x0639, B:195:0x063f, B:196:0x0642, B:198:0x0650, B:199:0x0665, B:201:0x066c, B:203:0x067d, B:230:0x0757, B:208:0x0695, B:205:0x0681, B:207:0x068b, B:229:0x0740, B:209:0x069e, B:210:0x06af, B:211:0x06bd, B:232:0x0760, B:216:0x06d4, B:218:0x06db, B:220:0x06e5, B:221:0x06e9, B:225:0x06fd, B:226:0x0701, B:234:0x0776, B:48:0x0198, B:84:0x0293, B:106:0x030a, B:113:0x0329, B:97:0x02d3, B:89:0x02ac, B:116:0x0331, B:117:0x0334, B:63:0x01f2, B:72:0x0220), top: B:255:0x0007, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0185 A[Catch: SQLiteException -> 0x02bd, all -> 0x0787, TRY_LEAVE, TryCatch #9 {SQLiteException -> 0x02bd, all -> 0x0787, blocks: (B:44:0x0160, B:46:0x0185, B:71:0x0211, B:72:0x0220, B:73:0x0223, B:75:0x0229, B:76:0x023a, B:78:0x0246, B:79:0x0258, B:91:0x02b1, B:87:0x0299), top: B:257:0x0160 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0210  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean zzg(java.lang.String r19, long r20) {
        /*
            Method dump skipped, instructions count: 1957
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzccw.zzg(java.lang.String, long):boolean");
    }

    @WorkerThread
    private final zzcas zzjr(String str) {
        zzcar zzcarVarZziw = zzaug().zziw(str);
        if (zzcarVarZziw == null || TextUtils.isEmpty(zzcarVarZziw.zzuo())) {
            zzaum().zzayj().zzj("No app data available; dropping", str);
            return null;
        }
        try {
            String str2 = zzbed.zzcr(this.mContext).getPackageInfo(str, 0).versionName;
            if (zzcarVarZziw.zzuo() != null && !zzcarVarZziw.zzuo().equals(str2)) {
                zzaum().zzayg().zzj("App version does not match; dropping. appId", zzcbw.zzjf(str));
                return null;
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return new zzcas(str, zzcarVarZziw.getGmpAppId(), zzcarVarZziw.zzuo(), zzcarVarZziw.zzauu(), zzcarVarZziw.zzauv(), zzcarVarZziw.zzauw(), zzcarVarZziw.zzaux(), (String) null, zzcarVarZziw.zzauy(), false, zzcarVarZziw.zzaur(), zzcarVarZziw.zzavl(), 0L, 0);
    }

    public final Context getContext() {
        return this.mContext;
    }

    @WorkerThread
    public final boolean isEnabled() {
        boolean zBooleanValue = false;
        zzaul().zzuj();
        zzwk();
        if (this.zzisr.zzawm()) {
            return false;
        }
        Boolean boolZzit = this.zzisr.zzit("firebase_analytics_collection_enabled");
        if (boolZzit != null) {
            zBooleanValue = boolZzit.booleanValue();
        } else if (!zzcax.zzaif()) {
            zBooleanValue = true;
        }
        return zzaun().zzbn(zBooleanValue);
    }

    @WorkerThread
    protected final void start() {
        zzaul().zzuj();
        zzaug().zzaxl();
        if (zzaun().zziqo.get() == 0) {
            zzaun().zziqo.set(this.zzasb.currentTimeMillis());
        }
        if (Long.valueOf(zzaun().zziqt.get()).longValue() == 0) {
            zzaum().zzayk().zzj("Persisting first open", Long.valueOf(this.zziub));
            zzaun().zziqt.set(this.zziub);
        }
        if (zzayw()) {
            zzcax.zzawl();
            if (!TextUtils.isEmpty(zzaub().getGmpAppId())) {
                String strZzayn = zzaun().zzayn();
                if (strZzayn == null) {
                    zzaun().zzjj(zzaub().getGmpAppId());
                } else if (!strZzayn.equals(zzaub().getGmpAppId())) {
                    zzaum().zzayi().log("Rechecking which service to use due to a GMP App Id change");
                    zzaun().zzayq();
                    this.zzitf.disconnect();
                    this.zzitf.zzxh();
                    zzaun().zzjj(zzaub().getGmpAppId());
                    zzaun().zziqt.set(this.zziub);
                    zzaun().zziqu.zzjl(null);
                }
            }
            zzaua().zzjk(zzaun().zziqu.zzays());
            zzcax.zzawl();
            if (!TextUtils.isEmpty(zzaub().getGmpAppId())) {
                zzcdw zzcdwVarZzaua = zzaua();
                zzcdwVarZzaua.zzuj();
                zzcdwVarZzaua.zzatw();
                zzcdwVarZzaua.zzwk();
                if (zzcdwVarZzaua.zzikh.zzayw()) {
                    zzcdwVarZzaua.zzaud().zzazr();
                    String strZzayr = zzcdwVarZzaua.zzaun().zzayr();
                    if (!TextUtils.isEmpty(strZzayr)) {
                        zzcdwVarZzaua.zzauc().zzwk();
                        if (!strZzayr.equals(Build.VERSION.RELEASE)) {
                            Bundle bundle = new Bundle();
                            bundle.putString("_po", strZzayr);
                            zzcdwVarZzaua.zzc("auto", "_ou", bundle);
                        }
                    }
                }
                zzaud().zza(new AtomicReference<>());
            }
        } else if (isEnabled()) {
            if (!zzaui().zzdt("android.permission.INTERNET")) {
                zzaum().zzaye().log("App is missing INTERNET permission");
            }
            if (!zzaui().zzdt("android.permission.ACCESS_NETWORK_STATE")) {
                zzaum().zzaye().log("App is missing ACCESS_NETWORK_STATE permission");
            }
            zzcax.zzawl();
            if (!zzbed.zzcr(this.mContext).zzalr()) {
                if (!zzccn.zzj(this.mContext, false)) {
                    zzaum().zzaye().log("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzcfh.zzk(this.mContext, false)) {
                    zzaum().zzaye().log("AppMeasurementService not registered/enabled");
                }
            }
            zzaum().zzaye().log("Uploading is not possible. App measurement disabled");
        }
        zzazj();
    }

    @WorkerThread
    protected final void zza(int i, Throwable th, byte[] bArr) throws IllegalStateException {
        zzaul().zzuj();
        zzwk();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } finally {
                this.zzitz = false;
                zzazn();
            }
        }
        List<Long> list = this.zzits;
        this.zzits = null;
        if ((i == 200 || i == 204) && th == null) {
            try {
                zzaun().zziqo.set(this.zzasb.currentTimeMillis());
                zzaun().zziqp.set(0L);
                zzazj();
                zzaum().zzayk().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzaug().beginTransaction();
                try {
                    for (Long l : list) {
                        zzcay zzcayVarZzaug = zzaug();
                        long jLongValue = l.longValue();
                        zzcayVarZzaug.zzuj();
                        zzcayVarZzaug.zzwk();
                        try {
                            if (zzcayVarZzaug.getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(jLongValue)}) != 1) {
                                throw new SQLiteException("Deleted fewer rows from queue than expected");
                            }
                        } catch (SQLiteException e) {
                            zzcayVarZzaug.zzaum().zzaye().zzj("Failed to delete a bundle in a queue table", e);
                            throw e;
                        }
                    }
                    zzaug().setTransactionSuccessful();
                    zzaug().endTransaction();
                    if (zzazb().zzyx() && zzazi()) {
                        zzazh();
                    } else {
                        this.zzitw = -1L;
                        zzazj();
                    }
                    this.zzitx = 0L;
                } catch (Throwable th2) {
                    zzaug().endTransaction();
                    throw th2;
                }
            } catch (SQLiteException e2) {
                zzaum().zzaye().zzj("Database error while trying to delete uploaded bundles", e2);
                this.zzitx = this.zzasb.elapsedRealtime();
                zzaum().zzayk().zzj("Disable upload, time", Long.valueOf(this.zzitx));
            }
        } else {
            zzaum().zzayk().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            zzaun().zziqp.set(this.zzasb.currentTimeMillis());
            if (i == 503 || i == 429) {
                zzaun().zziqq.set(this.zzasb.currentTimeMillis());
            }
            zzazj();
        }
    }

    @WorkerThread
    public final byte[] zza(@NonNull zzcbk zzcbkVar, @Size(min = 1) String str) throws IllegalStateException {
        long j;
        zzwk();
        zzaul().zzuj();
        zzatv();
        com.google.android.gms.common.internal.zzbp.zzu(zzcbkVar);
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        zzcgj zzcgjVar = new zzcgj();
        zzaug().beginTransaction();
        try {
            zzcar zzcarVarZziw = zzaug().zziw(str);
            if (zzcarVarZziw == null) {
                zzaum().zzayj().zzj("Log and bundle not available. package_name", str);
                return new byte[0];
            }
            if (!zzcarVarZziw.zzauy()) {
                zzaum().zzayj().zzj("Log and bundle disabled. package_name", str);
                return new byte[0];
            }
            zzcgk zzcgkVar = new zzcgk();
            zzcgjVar.zzizb = new zzcgk[]{zzcgkVar};
            zzcgkVar.zzizd = 1;
            zzcgkVar.zzizl = "android";
            zzcgkVar.zzch = zzcarVarZziw.getAppId();
            zzcgkVar.zzilu = zzcarVarZziw.zzauv();
            zzcgkVar.zzhts = zzcarVarZziw.zzuo();
            long jZzauu = zzcarVarZziw.zzauu();
            zzcgkVar.zzizy = jZzauu == -2147483648L ? null : Integer.valueOf((int) jZzauu);
            zzcgkVar.zzizp = Long.valueOf(zzcarVarZziw.zzauw());
            zzcgkVar.zzilt = zzcarVarZziw.getGmpAppId();
            zzcgkVar.zzizu = Long.valueOf(zzcarVarZziw.zzaux());
            if (isEnabled() && zzcax.zzaxi() && this.zzisr.zziu(zzcgkVar.zzch)) {
                zzaub();
                zzcgkVar.zzjae = null;
            }
            Pair<String, Boolean> pairZzjh = zzaun().zzjh(zzcarVarZziw.getAppId());
            if (pairZzjh != null && !TextUtils.isEmpty((CharSequence) pairZzjh.first)) {
                zzcgkVar.zzizr = (String) pairZzjh.first;
                zzcgkVar.zzizs = (Boolean) pairZzjh.second;
            }
            zzauc().zzwk();
            zzcgkVar.zzizm = Build.MODEL;
            zzauc().zzwk();
            zzcgkVar.zzcv = Build.VERSION.RELEASE;
            zzcgkVar.zzizo = Integer.valueOf((int) zzauc().zzaxx());
            zzcgkVar.zzizn = zzauc().zzaxy();
            zzcgkVar.zzizt = zzcarVarZziw.getAppInstanceId();
            zzcgkVar.zzimb = zzcarVarZziw.zzaur();
            List<zzcfv> listZziv = zzaug().zziv(zzcarVarZziw.getAppId());
            zzcgkVar.zzizf = new zzcgm[listZziv.size()];
            for (int i = 0; i < listZziv.size(); i++) {
                zzcgm zzcgmVar = new zzcgm();
                zzcgkVar.zzizf[i] = zzcgmVar;
                zzcgmVar.name = listZziv.get(i).mName;
                zzcgmVar.zzjai = Long.valueOf(listZziv.get(i).zzixc);
                zzaui().zza(zzcgmVar, listZziv.get(i).mValue);
            }
            Bundle bundleZzaya = zzcbkVar.zzinq.zzaya();
            if ("_iap".equals(zzcbkVar.name)) {
                bundleZzaya.putLong("_c", 1L);
                zzaum().zzayj().log("Marking in-app purchase as real-time");
                bundleZzaya.putLong("_r", 1L);
            }
            bundleZzaya.putString("_o", zzcbkVar.zzimf);
            if (zzaui().zzke(zzcgkVar.zzch)) {
                zzaui().zza(bundleZzaya, "_dbg", (Object) 1L);
                zzaui().zza(bundleZzaya, "_r", (Object) 1L);
            }
            zzcbg zzcbgVarZzaf = zzaug().zzaf(str, zzcbkVar.name);
            if (zzcbgVarZzaf == null) {
                zzaug().zza(new zzcbg(str, zzcbkVar.name, 1L, 0L, zzcbkVar.zzinr));
                j = 0;
            } else {
                j = zzcbgVarZzaf.zzinm;
                zzaug().zza(zzcbgVarZzaf.zzbb(zzcbkVar.zzinr).zzaxz());
            }
            zzcbf zzcbfVar = new zzcbf(this, zzcbkVar.zzimf, str, zzcbkVar.name, zzcbkVar.zzinr, j, bundleZzaya);
            zzcgh zzcghVar = new zzcgh();
            zzcgkVar.zzize = new zzcgh[]{zzcghVar};
            zzcghVar.zziyx = Long.valueOf(zzcbfVar.zzfdb);
            zzcghVar.name = zzcbfVar.mName;
            zzcghVar.zziyy = Long.valueOf(zzcbfVar.zzini);
            zzcghVar.zziyw = new zzcgi[zzcbfVar.zzinj.size()];
            Iterator<String> it = zzcbfVar.zzinj.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                String next = it.next();
                zzcgi zzcgiVar = new zzcgi();
                zzcghVar.zziyw[i2] = zzcgiVar;
                zzcgiVar.name = next;
                zzaui().zza(zzcgiVar, zzcbfVar.zzinj.get(next));
                i2++;
            }
            zzcgkVar.zzizx = zza(zzcarVarZziw.getAppId(), zzcgkVar.zzizf, zzcgkVar.zzize);
            zzcgkVar.zzizh = zzcghVar.zziyx;
            zzcgkVar.zzizi = zzcghVar.zziyx;
            long jZzaut = zzcarVarZziw.zzaut();
            zzcgkVar.zzizk = jZzaut != 0 ? Long.valueOf(jZzaut) : null;
            long jZzaus = zzcarVarZziw.zzaus();
            if (jZzaus != 0) {
                jZzaut = jZzaus;
            }
            zzcgkVar.zzizj = jZzaut != 0 ? Long.valueOf(jZzaut) : null;
            zzcarVarZziw.zzavc();
            zzcgkVar.zzizv = Integer.valueOf((int) zzcarVarZziw.zzauz());
            zzcgkVar.zzizq = Long.valueOf(zzcax.zzauw());
            zzcgkVar.zzizg = Long.valueOf(this.zzasb.currentTimeMillis());
            zzcgkVar.zzizw = Boolean.TRUE;
            zzcarVarZziw.zzal(zzcgkVar.zzizh.longValue());
            zzcarVarZziw.zzam(zzcgkVar.zzizi.longValue());
            zzaug().zza(zzcarVarZziw);
            zzaug().setTransactionSuccessful();
            try {
                byte[] bArr = new byte[zzcgjVar.zzhi()];
                zzegy zzegyVarZzi = zzegy.zzi(bArr, 0, bArr.length);
                zzcgjVar.zza(zzegyVarZzi);
                zzegyVarZzi.zzccm();
                return zzaui().zzo(bArr);
            } catch (IOException e) {
                zzaum().zzaye().zze("Data loss. Failed to bundle and serialize. appId", zzcbw.zzjf(str), e);
                return null;
            }
        } finally {
            zzaug().endTransaction();
        }
    }

    public final zzcan zzaty() {
        zza(this.zzitm);
        return this.zzitm;
    }

    public final zzcau zzatz() {
        zza((zzcdu) this.zzitl);
        return this.zzitl;
    }

    public final zzcdw zzaua() {
        zza((zzcdu) this.zzith);
        return this.zzith;
    }

    public final zzcbr zzaub() {
        zza((zzcdu) this.zziti);
        return this.zziti;
    }

    public final zzcbe zzauc() {
        zza((zzcdu) this.zzitg);
        return this.zzitg;
    }

    public final zzceo zzaud() {
        zza((zzcdu) this.zzitf);
        return this.zzitf;
    }

    public final zzcek zzaue() {
        zza((zzcdu) this.zzite);
        return this.zzite;
    }

    public final zzcbs zzauf() {
        zza((zzcdu) this.zzitc);
        return this.zzitc;
    }

    public final zzcay zzaug() {
        zza((zzcdu) this.zzitb);
        return this.zzitb;
    }

    public final zzcbu zzauh() {
        zza((zzcdt) this.zzita);
        return this.zzita;
    }

    public final zzcfw zzaui() {
        zza((zzcdt) this.zzisz);
        return this.zzisz;
    }

    public final zzccq zzauj() {
        zza((zzcdu) this.zzisw);
        return this.zzisw;
    }

    public final zzcfl zzauk() {
        zza((zzcdu) this.zzisv);
        return this.zzisv;
    }

    public final zzccr zzaul() {
        zza((zzcdu) this.zzisu);
        return this.zzisu;
    }

    public final zzcbw zzaum() {
        zza((zzcdu) this.zzist);
        return this.zzist;
    }

    public final zzcch zzaun() {
        zza((zzcdt) this.zziss);
        return this.zziss;
    }

    public final zzcax zzauo() {
        return this.zzisr;
    }

    @WorkerThread
    protected final boolean zzayw() {
        boolean z = false;
        zzwk();
        zzaul().zzuj();
        if (this.zzito == null || this.zzitp == 0 || (this.zzito != null && !this.zzito.booleanValue() && Math.abs(this.zzasb.elapsedRealtime() - this.zzitp) > 1000)) {
            this.zzitp = this.zzasb.elapsedRealtime();
            zzcax.zzawl();
            if (zzaui().zzdt("android.permission.INTERNET") && zzaui().zzdt("android.permission.ACCESS_NETWORK_STATE") && (zzbed.zzcr(this.mContext).zzalr() || (zzccn.zzj(this.mContext, false) && zzcfh.zzk(this.mContext, false)))) {
                z = true;
            }
            this.zzito = Boolean.valueOf(z);
            if (this.zzito.booleanValue()) {
                this.zzito = Boolean.valueOf(zzaui().zzkb(zzaub().getGmpAppId()));
            }
        }
        return this.zzito.booleanValue();
    }

    public final zzcbw zzayx() {
        if (this.zzist == null || !this.zzist.isInitialized()) {
            return null;
        }
        return this.zzist;
    }

    final zzccr zzayy() {
        return this.zzisu;
    }

    public final AppMeasurement zzayz() {
        return this.zzisx;
    }

    public final FirebaseAnalytics zzaza() {
        return this.zzisy;
    }

    public final zzcca zzazb() {
        zza((zzcdu) this.zzitd);
        return this.zzitd;
    }

    final long zzazf() {
        Long lValueOf = Long.valueOf(zzaun().zziqt.get());
        return lValueOf.longValue() == 0 ? this.zziub : Math.min(this.zziub, lValueOf.longValue());
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0187 A[Catch: all -> 0x0290, TryCatch #0 {all -> 0x0290, blocks: (B:3:0x0013, B:5:0x0020, B:8:0x0033, B:10:0x0039, B:12:0x004c, B:14:0x0052, B:16:0x005b, B:20:0x0069, B:23:0x007e, B:25:0x0088, B:27:0x009e, B:29:0x00bc, B:30:0x00d3, B:32:0x00e1, B:34:0x00e7, B:35:0x00f1, B:37:0x0114, B:38:0x0118, B:40:0x011e, B:42:0x0130, B:45:0x0136, B:47:0x013c, B:49:0x014e, B:51:0x0156, B:52:0x015c, B:54:0x0178, B:58:0x0182, B:60:0x0187, B:62:0x01ca, B:63:0x01d1, B:66:0x01dc, B:68:0x01e7, B:69:0x01f0, B:70:0x01fc, B:72:0x0207, B:74:0x020e, B:75:0x021b, B:77:0x022b, B:78:0x0232, B:81:0x0275, B:84:0x027e, B:64:0x01d5, B:90:0x0299, B:92:0x02af, B:94:0x02b9), top: B:99:0x0013, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01e7 A[Catch: all -> 0x0290, TryCatch #0 {all -> 0x0290, blocks: (B:3:0x0013, B:5:0x0020, B:8:0x0033, B:10:0x0039, B:12:0x004c, B:14:0x0052, B:16:0x005b, B:20:0x0069, B:23:0x007e, B:25:0x0088, B:27:0x009e, B:29:0x00bc, B:30:0x00d3, B:32:0x00e1, B:34:0x00e7, B:35:0x00f1, B:37:0x0114, B:38:0x0118, B:40:0x011e, B:42:0x0130, B:45:0x0136, B:47:0x013c, B:49:0x014e, B:51:0x0156, B:52:0x015c, B:54:0x0178, B:58:0x0182, B:60:0x0187, B:62:0x01ca, B:63:0x01d1, B:66:0x01dc, B:68:0x01e7, B:69:0x01f0, B:70:0x01fc, B:72:0x0207, B:74:0x020e, B:75:0x021b, B:77:0x022b, B:78:0x0232, B:81:0x0275, B:84:0x027e, B:64:0x01d5, B:90:0x0299, B:92:0x02af, B:94:0x02b9), top: B:99:0x0013, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x020e A[Catch: MalformedURLException -> 0x027d, all -> 0x0290, TryCatch #1 {MalformedURLException -> 0x027d, blocks: (B:70:0x01fc, B:72:0x0207, B:74:0x020e, B:75:0x021b, B:77:0x022b, B:78:0x0232, B:81:0x0275), top: B:100:0x01fc, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x022b A[Catch: MalformedURLException -> 0x027d, all -> 0x0290, TryCatch #1 {MalformedURLException -> 0x027d, blocks: (B:70:0x01fc, B:72:0x0207, B:74:0x020e, B:75:0x021b, B:77:0x022b, B:78:0x0232, B:81:0x0275), top: B:100:0x01fc, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0275 A[Catch: MalformedURLException -> 0x027d, all -> 0x0290, TRY_ENTER, TRY_LEAVE, TryCatch #1 {MalformedURLException -> 0x027d, blocks: (B:70:0x01fc, B:72:0x0207, B:74:0x020e, B:75:0x021b, B:77:0x022b, B:78:0x0232, B:81:0x0275), top: B:100:0x01fc, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02bd  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzazh() throws java.lang.IllegalStateException {
        /*
            Method dump skipped, instructions count: 710
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzccw.zzazh():void");
    }

    final void zzazk() {
        this.zzitv++;
    }

    @WorkerThread
    final void zzazl() throws IllegalStateException, IOException {
        zzaul().zzuj();
        zzwk();
        if (this.zzitn) {
            return;
        }
        zzaum().zzayi().log("This instance being marked as an uploader");
        zzaul().zzuj();
        zzwk();
        if (zzazm() && zzaze()) {
            int iZza = zza(this.zzitr);
            int iZzayc = zzaub().zzayc();
            zzaul().zzuj();
            if (iZza > iZzayc) {
                zzaum().zzaye().zze("Panic: can't downgrade version. Previous, current version", Integer.valueOf(iZza), Integer.valueOf(iZzayc));
            } else if (iZza < iZzayc) {
                if (zza(iZzayc, this.zzitr)) {
                    zzaum().zzayk().zze("Storage version upgraded. Previous, current version", Integer.valueOf(iZza), Integer.valueOf(iZzayc));
                } else {
                    zzaum().zzaye().zze("Storage version upgrade failed. Previous, current version", Integer.valueOf(iZza), Integer.valueOf(iZzayc));
                }
            }
        }
        this.zzitn = true;
        zzazj();
    }

    @WorkerThread
    final void zzb(zzcav zzcavVar, zzcas zzcasVar) {
        boolean z = true;
        com.google.android.gms.common.internal.zzbp.zzu(zzcavVar);
        com.google.android.gms.common.internal.zzbp.zzgg(zzcavVar.packageName);
        com.google.android.gms.common.internal.zzbp.zzu(zzcavVar.zzimf);
        com.google.android.gms.common.internal.zzbp.zzu(zzcavVar.zzimg);
        com.google.android.gms.common.internal.zzbp.zzgg(zzcavVar.zzimg.name);
        zzaul().zzuj();
        zzwk();
        if (TextUtils.isEmpty(zzcasVar.zzilt)) {
            return;
        }
        if (!zzcasVar.zzily) {
            zzf(zzcasVar);
            return;
        }
        zzcav zzcavVar2 = new zzcav(zzcavVar);
        zzcavVar2.zzimi = false;
        zzaug().beginTransaction();
        try {
            zzcav zzcavVarZzai = zzaug().zzai(zzcavVar2.packageName, zzcavVar2.zzimg.name);
            if (zzcavVarZzai != null && !zzcavVarZzai.zzimf.equals(zzcavVar2.zzimf)) {
                zzaum().zzayg().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", zzauh().zzje(zzcavVar2.zzimg.name), zzcavVar2.zzimf, zzcavVarZzai.zzimf);
            }
            if (zzcavVarZzai != null && zzcavVarZzai.zzimi) {
                zzcavVar2.zzimf = zzcavVarZzai.zzimf;
                zzcavVar2.zzimh = zzcavVarZzai.zzimh;
                zzcavVar2.zziml = zzcavVarZzai.zziml;
                zzcavVar2.zzimj = zzcavVarZzai.zzimj;
                zzcavVar2.zzimm = zzcavVarZzai.zzimm;
                zzcavVar2.zzimi = zzcavVarZzai.zzimi;
                zzcavVar2.zzimg = new zzcft(zzcavVar2.zzimg.name, zzcavVarZzai.zzimg.zziwy, zzcavVar2.zzimg.getValue(), zzcavVarZzai.zzimg.zzimf);
                z = false;
            } else if (TextUtils.isEmpty(zzcavVar2.zzimj)) {
                zzcavVar2.zzimg = new zzcft(zzcavVar2.zzimg.name, zzcavVar2.zzimh, zzcavVar2.zzimg.getValue(), zzcavVar2.zzimg.zzimf);
                zzcavVar2.zzimi = true;
            } else {
                z = false;
            }
            if (zzcavVar2.zzimi) {
                zzcft zzcftVar = zzcavVar2.zzimg;
                zzcfv zzcfvVar = new zzcfv(zzcavVar2.packageName, zzcavVar2.zzimf, zzcftVar.name, zzcftVar.zziwy, zzcftVar.getValue());
                if (zzaug().zza(zzcfvVar)) {
                    zzaum().zzayj().zzd("User property updated immediately", zzcavVar2.packageName, zzauh().zzje(zzcfvVar.mName), zzcfvVar.mValue);
                } else {
                    zzaum().zzaye().zzd("(2)Too many active user properties, ignoring", zzcbw.zzjf(zzcavVar2.packageName), zzauh().zzje(zzcfvVar.mName), zzcfvVar.mValue);
                }
                if (z && zzcavVar2.zzimm != null) {
                    zzc(new zzcbk(zzcavVar2.zzimm, zzcavVar2.zzimh), zzcasVar);
                }
            }
            if (zzaug().zza(zzcavVar2)) {
                zzaum().zzayj().zzd("Conditional property added", zzcavVar2.packageName, zzauh().zzje(zzcavVar2.zzimg.name), zzcavVar2.zzimg.getValue());
            } else {
                zzaum().zzaye().zzd("Too many conditional properties, ignoring", zzcbw.zzjf(zzcavVar2.packageName), zzauh().zzje(zzcavVar2.zzimg.name), zzcavVar2.zzimg.getValue());
            }
            zzaug().setTransactionSuccessful();
        } finally {
            zzaug().endTransaction();
        }
    }

    @WorkerThread
    final void zzb(zzcbk zzcbkVar, zzcas zzcasVar) {
        List<zzcav> listZzc;
        List<zzcav> listZzc2;
        List<zzcav> listZzc3;
        com.google.android.gms.common.internal.zzbp.zzu(zzcasVar);
        com.google.android.gms.common.internal.zzbp.zzgg(zzcasVar.packageName);
        zzaul().zzuj();
        zzwk();
        String str = zzcasVar.packageName;
        long j = zzcbkVar.zzinr;
        zzaui();
        if (zzcfw.zzd(zzcbkVar, zzcasVar)) {
            if (!zzcasVar.zzily) {
                zzf(zzcasVar);
                return;
            }
            zzaug().beginTransaction();
            try {
                zzcay zzcayVarZzaug = zzaug();
                com.google.android.gms.common.internal.zzbp.zzgg(str);
                zzcayVarZzaug.zzuj();
                zzcayVarZzaug.zzwk();
                if (j < 0) {
                    zzcayVarZzaug.zzaum().zzayg().zze("Invalid time querying timed out conditional properties", zzcbw.zzjf(str), Long.valueOf(j));
                    listZzc = Collections.emptyList();
                } else {
                    listZzc = zzcayVarZzaug.zzc("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
                }
                for (zzcav zzcavVar : listZzc) {
                    if (zzcavVar != null) {
                        zzaum().zzayj().zzd("User property timed out", zzcavVar.packageName, zzauh().zzje(zzcavVar.zzimg.name), zzcavVar.zzimg.getValue());
                        if (zzcavVar.zzimk != null) {
                            zzc(new zzcbk(zzcavVar.zzimk, j), zzcasVar);
                        }
                        zzaug().zzaj(str, zzcavVar.zzimg.name);
                    }
                }
                zzcay zzcayVarZzaug2 = zzaug();
                com.google.android.gms.common.internal.zzbp.zzgg(str);
                zzcayVarZzaug2.zzuj();
                zzcayVarZzaug2.zzwk();
                if (j < 0) {
                    zzcayVarZzaug2.zzaum().zzayg().zze("Invalid time querying expired conditional properties", zzcbw.zzjf(str), Long.valueOf(j));
                    listZzc2 = Collections.emptyList();
                } else {
                    listZzc2 = zzcayVarZzaug2.zzc("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(listZzc2.size());
                for (zzcav zzcavVar2 : listZzc2) {
                    if (zzcavVar2 != null) {
                        zzaum().zzayj().zzd("User property expired", zzcavVar2.packageName, zzauh().zzje(zzcavVar2.zzimg.name), zzcavVar2.zzimg.getValue());
                        zzaug().zzag(str, zzcavVar2.zzimg.name);
                        if (zzcavVar2.zzimo != null) {
                            arrayList.add(zzcavVar2.zzimo);
                        }
                        zzaug().zzaj(str, zzcavVar2.zzimg.name);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    zzc(new zzcbk((zzcbk) obj, j), zzcasVar);
                }
                zzcay zzcayVarZzaug3 = zzaug();
                String str2 = zzcbkVar.name;
                com.google.android.gms.common.internal.zzbp.zzgg(str);
                com.google.android.gms.common.internal.zzbp.zzgg(str2);
                zzcayVarZzaug3.zzuj();
                zzcayVarZzaug3.zzwk();
                if (j < 0) {
                    zzcayVarZzaug3.zzaum().zzayg().zzd("Invalid time querying triggered conditional properties", zzcbw.zzjf(str), zzcayVarZzaug3.zzauh().zzjc(str2), Long.valueOf(j));
                    listZzc3 = Collections.emptyList();
                } else {
                    listZzc3 = zzcayVarZzaug3.zzc("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
                }
                ArrayList arrayList3 = new ArrayList(listZzc3.size());
                for (zzcav zzcavVar3 : listZzc3) {
                    if (zzcavVar3 != null) {
                        zzcft zzcftVar = zzcavVar3.zzimg;
                        zzcfv zzcfvVar = new zzcfv(zzcavVar3.packageName, zzcavVar3.zzimf, zzcftVar.name, j, zzcftVar.getValue());
                        if (zzaug().zza(zzcfvVar)) {
                            zzaum().zzayj().zzd("User property triggered", zzcavVar3.packageName, zzauh().zzje(zzcfvVar.mName), zzcfvVar.mValue);
                        } else {
                            zzaum().zzaye().zzd("Too many active user properties, ignoring", zzcbw.zzjf(zzcavVar3.packageName), zzauh().zzje(zzcfvVar.mName), zzcfvVar.mValue);
                        }
                        if (zzcavVar3.zzimm != null) {
                            arrayList3.add(zzcavVar3.zzimm);
                        }
                        zzcavVar3.zzimg = new zzcft(zzcfvVar);
                        zzcavVar3.zzimi = true;
                        zzaug().zza(zzcavVar3);
                    }
                }
                zzc(zzcbkVar, zzcasVar);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i2 = 0;
                while (i2 < size2) {
                    Object obj2 = arrayList4.get(i2);
                    i2++;
                    zzc(new zzcbk((zzcbk) obj2, j), zzcasVar);
                }
                zzaug().setTransactionSuccessful();
            } finally {
                zzaug().endTransaction();
            }
        }
    }

    @WorkerThread
    final void zzb(zzcbk zzcbkVar, String str) {
        zzcar zzcarVarZziw = zzaug().zziw(str);
        if (zzcarVarZziw == null || TextUtils.isEmpty(zzcarVarZziw.zzuo())) {
            zzaum().zzayj().zzj("No app data available; dropping event", str);
            return;
        }
        try {
            String str2 = zzbed.zzcr(this.mContext).getPackageInfo(str, 0).versionName;
            if (zzcarVarZziw.zzuo() != null && !zzcarVarZziw.zzuo().equals(str2)) {
                zzaum().zzayg().zzj("App version does not match; dropping event. appId", zzcbw.zzjf(str));
                return;
            }
        } catch (PackageManager.NameNotFoundException e) {
            if (!"_ui".equals(zzcbkVar.name)) {
                zzaum().zzayg().zzj("Could not find package. appId", zzcbw.zzjf(str));
            }
        }
        zzb(zzcbkVar, new zzcas(str, zzcarVarZziw.getGmpAppId(), zzcarVarZziw.zzuo(), zzcarVarZziw.zzauu(), zzcarVarZziw.zzauv(), zzcarVarZziw.zzauw(), zzcarVarZziw.zzaux(), (String) null, zzcarVarZziw.zzauy(), false, zzcarVarZziw.zzaur(), zzcarVarZziw.zzavl(), 0L, 0));
    }

    final void zzb(zzcdu zzcduVar) {
        this.zzitu++;
    }

    @WorkerThread
    final void zzb(zzcft zzcftVar, zzcas zzcasVar) throws IllegalStateException {
        zzaul().zzuj();
        zzwk();
        if (TextUtils.isEmpty(zzcasVar.zzilt)) {
            return;
        }
        if (!zzcasVar.zzily) {
            zzf(zzcasVar);
            return;
        }
        int iZzjy = zzaui().zzjy(zzcftVar.name);
        if (iZzjy != 0) {
            zzaui();
            zzaui().zza(zzcasVar.packageName, iZzjy, "_ev", zzcfw.zza(zzcftVar.name, zzcax.zzavp(), true), zzcftVar.name != null ? zzcftVar.name.length() : 0);
            return;
        }
        int iZzl = zzaui().zzl(zzcftVar.name, zzcftVar.getValue());
        if (iZzl != 0) {
            zzaui();
            String strZza = zzcfw.zza(zzcftVar.name, zzcax.zzavp(), true);
            Object value = zzcftVar.getValue();
            if (value != null && ((value instanceof String) || (value instanceof CharSequence))) {
                length = String.valueOf(value).length();
            }
            zzaui().zza(zzcasVar.packageName, iZzl, "_ev", strZza, length);
            return;
        }
        Object objZzm = zzaui().zzm(zzcftVar.name, zzcftVar.getValue());
        if (objZzm != null) {
            zzcfv zzcfvVar = new zzcfv(zzcasVar.packageName, zzcftVar.zzimf, zzcftVar.name, zzcftVar.zziwy, objZzm);
            zzaum().zzayj().zze("Setting user property", zzauh().zzje(zzcfvVar.mName), objZzm);
            zzaug().beginTransaction();
            try {
                zzf(zzcasVar);
                boolean zZza = zzaug().zza(zzcfvVar);
                zzaug().setTransactionSuccessful();
                if (zZza) {
                    zzaum().zzayj().zze("User property set", zzauh().zzje(zzcfvVar.mName), zzcfvVar.mValue);
                } else {
                    zzaum().zzaye().zze("Too many unique user properties are set. Ignoring user property", zzauh().zzje(zzcfvVar.mName), zzcfvVar.mValue);
                    zzaui().zza(zzcasVar.packageName, 9, (String) null, (String) null, 0);
                }
            } finally {
                zzaug().endTransaction();
            }
        }
    }

    @WorkerThread
    final void zzb(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) throws IllegalStateException {
        boolean z = true;
        zzaul().zzuj();
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } finally {
                this.zzity = false;
                zzazn();
            }
        }
        zzaum().zzayk().zzj("onConfigFetched. Response size", Integer.valueOf(bArr.length));
        zzaug().beginTransaction();
        try {
            zzcar zzcarVarZziw = zzaug().zziw(str);
            boolean z2 = (i == 200 || i == 204 || i == 304) && th == null;
            if (zzcarVarZziw == null) {
                zzaum().zzayg().zzj("App does not exist in onConfigFetched. appId", zzcbw.zzjf(str));
            } else if (z2 || i == 404) {
                List<String> list = map != null ? map.get("Last-Modified") : null;
                String str2 = (list == null || list.size() <= 0) ? null : list.get(0);
                if (i == 404 || i == 304) {
                    if (zzauj().zzjn(str) == null && !zzauj().zzb(str, null, null)) {
                        return;
                    }
                } else if (!zzauj().zzb(str, bArr, str2)) {
                    return;
                }
                zzcarVarZziw.zzar(this.zzasb.currentTimeMillis());
                zzaug().zza(zzcarVarZziw);
                if (i == 404) {
                    zzaum().zzayh().zzj("Config not found. Using empty config. appId", str);
                } else {
                    zzaum().zzayk().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                }
                if (zzazb().zzyx() && zzazi()) {
                    zzazh();
                } else {
                    zzazj();
                }
            } else {
                zzcarVarZziw.zzas(this.zzasb.currentTimeMillis());
                zzaug().zza(zzcarVarZziw);
                zzaum().zzayk().zze("Fetching config failed. code, error", Integer.valueOf(i), th);
                zzauj().zzjp(str);
                zzaun().zziqp.set(this.zzasb.currentTimeMillis());
                if (i != 503 && i != 429) {
                    z = false;
                }
                if (z) {
                    zzaun().zziqq.set(this.zzasb.currentTimeMillis());
                }
                zzazj();
            }
            zzaug().setTransactionSuccessful();
        } finally {
            zzaug().endTransaction();
        }
    }

    public final void zzbo(boolean z) {
        zzazj();
    }

    @WorkerThread
    final void zzc(zzcav zzcavVar, zzcas zzcasVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzcavVar);
        com.google.android.gms.common.internal.zzbp.zzgg(zzcavVar.packageName);
        com.google.android.gms.common.internal.zzbp.zzu(zzcavVar.zzimg);
        com.google.android.gms.common.internal.zzbp.zzgg(zzcavVar.zzimg.name);
        zzaul().zzuj();
        zzwk();
        if (TextUtils.isEmpty(zzcasVar.zzilt)) {
            return;
        }
        if (!zzcasVar.zzily) {
            zzf(zzcasVar);
            return;
        }
        zzaug().beginTransaction();
        try {
            zzf(zzcasVar);
            zzcav zzcavVarZzai = zzaug().zzai(zzcavVar.packageName, zzcavVar.zzimg.name);
            if (zzcavVarZzai != null) {
                zzaum().zzayj().zze("Removing conditional user property", zzcavVar.packageName, zzauh().zzje(zzcavVar.zzimg.name));
                zzaug().zzaj(zzcavVar.packageName, zzcavVar.zzimg.name);
                if (zzcavVarZzai.zzimi) {
                    zzaug().zzag(zzcavVar.packageName, zzcavVar.zzimg.name);
                }
                if (zzcavVar.zzimo != null) {
                    zzc(zzaui().zza(zzcavVar.zzimo.name, zzcavVar.zzimo.zzinq != null ? zzcavVar.zzimo.zzinq.zzaya() : null, zzcavVarZzai.zzimf, zzcavVar.zzimo.zzinr, true, false), zzcasVar);
                }
            } else {
                zzaum().zzayg().zze("Conditional user property doesn't exist", zzcbw.zzjf(zzcavVar.packageName), zzauh().zzje(zzcavVar.zzimg.name));
            }
            zzaug().setTransactionSuccessful();
        } finally {
            zzaug().endTransaction();
        }
    }

    @WorkerThread
    final void zzc(zzcft zzcftVar, zzcas zzcasVar) {
        zzaul().zzuj();
        zzwk();
        if (TextUtils.isEmpty(zzcasVar.zzilt)) {
            return;
        }
        if (!zzcasVar.zzily) {
            zzf(zzcasVar);
            return;
        }
        zzaum().zzayj().zzj("Removing user property", zzauh().zzje(zzcftVar.name));
        zzaug().beginTransaction();
        try {
            zzf(zzcasVar);
            zzaug().zzag(zzcasVar.packageName, zzcftVar.name);
            zzaug().setTransactionSuccessful();
            zzaum().zzayj().zzj("User property removed", zzauh().zzje(zzcftVar.name));
        } finally {
            zzaug().endTransaction();
        }
    }

    final void zzd(zzcas zzcasVar) {
        zzaul().zzuj();
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzgg(zzcasVar.packageName);
        zzf(zzcasVar);
    }

    @WorkerThread
    final void zzd(zzcav zzcavVar) {
        zzcas zzcasVarZzjr = zzjr(zzcavVar.packageName);
        if (zzcasVarZzjr != null) {
            zzb(zzcavVar, zzcasVarZzjr);
        }
    }

    @WorkerThread
    public final void zze(zzcas zzcasVar) throws IllegalStateException, PackageManager.NameNotFoundException {
        int i;
        ApplicationInfo applicationInfo;
        zzaul().zzuj();
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(zzcasVar);
        com.google.android.gms.common.internal.zzbp.zzgg(zzcasVar.packageName);
        if (TextUtils.isEmpty(zzcasVar.zzilt)) {
            return;
        }
        zzcar zzcarVarZziw = zzaug().zziw(zzcasVar.packageName);
        if (zzcarVarZziw != null && TextUtils.isEmpty(zzcarVarZziw.getGmpAppId()) && !TextUtils.isEmpty(zzcasVar.zzilt)) {
            zzcarVarZziw.zzar(0L);
            zzaug().zza(zzcarVarZziw);
            zzauj().zzjq(zzcasVar.packageName);
        }
        if (!zzcasVar.zzily) {
            zzf(zzcasVar);
            return;
        }
        long jCurrentTimeMillis = zzcasVar.zzimd;
        if (jCurrentTimeMillis == 0) {
            jCurrentTimeMillis = this.zzasb.currentTimeMillis();
        }
        int i2 = zzcasVar.zzime;
        if (i2 == 0 || i2 == 1) {
            i = i2;
        } else {
            zzaum().zzayg().zze("Incorrect app type, assuming installed app. appId, appType", zzcbw.zzjf(zzcasVar.packageName), Integer.valueOf(i2));
            i = 0;
        }
        zzaug().beginTransaction();
        try {
            zzcar zzcarVarZziw2 = zzaug().zziw(zzcasVar.packageName);
            if (zzcarVarZziw2 != null && zzcarVarZziw2.getGmpAppId() != null && !zzcarVarZziw2.getGmpAppId().equals(zzcasVar.zzilt)) {
                zzaum().zzayg().zzj("New GMP App Id passed in. Removing cached database data. appId", zzcbw.zzjf(zzcarVarZziw2.getAppId()));
                zzcay zzcayVarZzaug = zzaug();
                String appId = zzcarVarZziw2.getAppId();
                zzcayVarZzaug.zzwk();
                zzcayVarZzaug.zzuj();
                com.google.android.gms.common.internal.zzbp.zzgg(appId);
                try {
                    SQLiteDatabase writableDatabase = zzcayVarZzaug.getWritableDatabase();
                    String[] strArr = {appId};
                    int iDelete = writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + writableDatabase.delete("events", "app_id=?", strArr) + 0 + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("apps", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("event_filters", "app_id=?", strArr) + writableDatabase.delete("property_filters", "app_id=?", strArr);
                    if (iDelete > 0) {
                        zzcayVarZzaug.zzaum().zzayk().zze("Deleted application data. app, records", appId, Integer.valueOf(iDelete));
                    }
                } catch (SQLiteException e) {
                    zzcayVarZzaug.zzaum().zzaye().zze("Error deleting application data. appId, error", zzcbw.zzjf(appId), e);
                }
                zzcarVarZziw2 = null;
            }
            if (zzcarVarZziw2 != null && zzcarVarZziw2.zzuo() != null && !zzcarVarZziw2.zzuo().equals(zzcasVar.zzhts)) {
                Bundle bundle = new Bundle();
                bundle.putString("_pv", zzcarVarZziw2.zzuo());
                zzb(new zzcbk("_au", new zzcbh(bundle), "auto", jCurrentTimeMillis), zzcasVar);
            }
            zzf(zzcasVar);
            zzcbg zzcbgVarZzaf = null;
            if (i == 0) {
                zzcbgVarZzaf = zzaug().zzaf(zzcasVar.packageName, "_f");
            } else if (i == 1) {
                zzcbgVarZzaf = zzaug().zzaf(zzcasVar.packageName, "_v");
            }
            if (zzcbgVarZzaf == null) {
                long j = (1 + (jCurrentTimeMillis / 3600000)) * 3600000;
                if (i == 0) {
                    zzb(new zzcft("_fot", jCurrentTimeMillis, Long.valueOf(j), "auto"), zzcasVar);
                    zzaul().zzuj();
                    zzwk();
                    Bundle bundle2 = new Bundle();
                    bundle2.putLong("_c", 1L);
                    bundle2.putLong("_r", 1L);
                    bundle2.putLong("_uwa", 0L);
                    bundle2.putLong("_pfo", 0L);
                    bundle2.putLong("_sys", 0L);
                    bundle2.putLong("_sysu", 0L);
                    if (this.mContext.getPackageManager() == null) {
                        zzaum().zzaye().zzj("PackageManager is null, first open report might be inaccurate. appId", zzcbw.zzjf(zzcasVar.packageName));
                    } else {
                        PackageInfo packageInfo = null;
                        try {
                            packageInfo = zzbed.zzcr(this.mContext).getPackageInfo(zzcasVar.packageName, 0);
                        } catch (PackageManager.NameNotFoundException e2) {
                            zzaum().zzaye().zze("Package info is null, first open report might be inaccurate. appId", zzcbw.zzjf(zzcasVar.packageName), e2);
                        }
                        if (packageInfo != null && packageInfo.firstInstallTime != 0) {
                            boolean z = false;
                            if (packageInfo.firstInstallTime != packageInfo.lastUpdateTime) {
                                bundle2.putLong("_uwa", 1L);
                            } else {
                                z = true;
                            }
                            zzb(new zzcft("_fi", jCurrentTimeMillis, Long.valueOf(z ? 1L : 0L), "auto"), zzcasVar);
                        }
                        try {
                            applicationInfo = zzbed.zzcr(this.mContext).getApplicationInfo(zzcasVar.packageName, 0);
                        } catch (PackageManager.NameNotFoundException e3) {
                            zzaum().zzaye().zze("Application info is null, first open report might be inaccurate. appId", zzcbw.zzjf(zzcasVar.packageName), e3);
                            applicationInfo = null;
                        }
                        if (applicationInfo != null) {
                            if ((applicationInfo.flags & 1) != 0) {
                                bundle2.putLong("_sys", 1L);
                            }
                            if ((applicationInfo.flags & 128) != 0) {
                                bundle2.putLong("_sysu", 1L);
                            }
                        }
                    }
                    zzcay zzcayVarZzaug2 = zzaug();
                    String str = zzcasVar.packageName;
                    com.google.android.gms.common.internal.zzbp.zzgg(str);
                    zzcayVarZzaug2.zzuj();
                    zzcayVarZzaug2.zzwk();
                    long jZzam = zzcayVarZzaug2.zzam(str, "first_open_count");
                    if (jZzam >= 0) {
                        bundle2.putLong("_pfo", jZzam);
                    }
                    zzb(new zzcbk("_f", new zzcbh(bundle2), "auto", jCurrentTimeMillis), zzcasVar);
                } else if (i == 1) {
                    zzb(new zzcft("_fvt", jCurrentTimeMillis, Long.valueOf(j), "auto"), zzcasVar);
                    zzaul().zzuj();
                    zzwk();
                    Bundle bundle3 = new Bundle();
                    bundle3.putLong("_c", 1L);
                    bundle3.putLong("_r", 1L);
                    zzb(new zzcbk("_v", new zzcbh(bundle3), "auto", jCurrentTimeMillis), zzcasVar);
                }
                Bundle bundle4 = new Bundle();
                bundle4.putLong("_et", 1L);
                zzb(new zzcbk("_e", new zzcbh(bundle4), "auto", jCurrentTimeMillis), zzcasVar);
            } else if (zzcasVar.zzilz) {
                zzb(new zzcbk("_cd", new zzcbh(new Bundle()), "auto", jCurrentTimeMillis), zzcasVar);
            }
            zzaug().setTransactionSuccessful();
        } finally {
            zzaug().endTransaction();
        }
    }

    @WorkerThread
    final void zze(zzcav zzcavVar) {
        zzcas zzcasVarZzjr = zzjr(zzcavVar.packageName);
        if (zzcasVarZzjr != null) {
            zzc(zzcavVar, zzcasVarZzjr);
        }
    }

    @WorkerThread
    final void zzi(Runnable runnable) {
        zzaul().zzuj();
        if (this.zzitt == null) {
            this.zzitt = new ArrayList();
        }
        this.zzitt.add(runnable);
    }

    public final String zzjs(String str) throws IllegalStateException {
        try {
            return (String) zzaul().zzd(new zzccy(this, str)).get(30000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzaum().zzaye().zze("Failed to get app instance id. appId", zzcbw.zzjf(str), e);
            return null;
        }
    }

    public final com.google.android.gms.common.util.zzd zzvx() {
        return this.zzasb;
    }

    final void zzwk() {
        if (!this.zzdod) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }
}
