package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/* loaded from: classes.dex */
public class FirebaseInstanceId {
    private static Map<String, FirebaseInstanceId> zzhtm = new ArrayMap();
    private static zzk zzmlj;
    private final FirebaseApp zzmlk;
    private final zzj zzmll;
    private final String zzmlm;

    private FirebaseInstanceId(FirebaseApp firebaseApp, zzj zzjVar) {
        this.zzmlk = firebaseApp;
        this.zzmll = zzjVar;
        String gcmSenderId = this.zzmlk.getOptions().getGcmSenderId();
        if (gcmSenderId == null) {
            gcmSenderId = this.zzmlk.getOptions().getApplicationId();
            if (gcmSenderId.startsWith("1:")) {
                String[] strArrSplit = gcmSenderId.split(":");
                if (strArrSplit.length < 2) {
                    gcmSenderId = null;
                } else {
                    gcmSenderId = strArrSplit[1];
                    if (gcmSenderId.isEmpty()) {
                        gcmSenderId = null;
                    }
                }
            }
        }
        this.zzmlm = gcmSenderId;
        if (this.zzmlm == null) {
            throw new IllegalStateException("IID failing to initialize, FirebaseApp is missing project ID");
        }
        FirebaseInstanceIdService.zza(this.zzmlk.getApplicationContext(), this);
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(@NonNull FirebaseApp firebaseApp) {
        FirebaseInstanceId firebaseInstanceId;
        firebaseInstanceId = zzhtm.get(firebaseApp.getOptions().getApplicationId());
        if (firebaseInstanceId == null) {
            zzj zzjVarZza = zzj.zza(firebaseApp.getApplicationContext(), null);
            if (zzmlj == null) {
                zzmlj = new zzk(zzj.zzbyo());
            }
            firebaseInstanceId = new FirebaseInstanceId(firebaseApp, zzjVarZza);
            zzhtm.put(firebaseApp.getOptions().getApplicationId(), firebaseInstanceId);
        }
        return firebaseInstanceId;
    }

    static String zza(KeyPair keyPair) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            bArrDigest[0] = (byte) ((bArrDigest[0] & 15) + 112);
            return Base64.encodeToString(bArrDigest, 0, 8, 11);
        } catch (NoSuchAlgorithmException e) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required alghorithms");
            return null;
        }
    }

    static void zza(Context context, zzr zzrVar) {
        zzrVar.zzasw();
        Intent intent = new Intent();
        intent.putExtra("CMD", "RST");
        zzq.zzbys().zze(context, intent);
    }

    private final void zzac(Bundle bundle) {
        bundle.putString("gmp_app_id", this.zzmlk.getOptions().getApplicationId());
    }

    static int zzao(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            String strValueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(strValueOf).length() + 23).append("Failed to find package ").append(strValueOf).toString());
            return 0;
        }
    }

    static zzk zzbyn() {
        return zzmlj;
    }

    static String zzdd(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            String strValueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(strValueOf).length() + 38).append("Never happens: can't find own package ").append(strValueOf).toString());
            return null;
        }
    }

    static int zzej(Context context) {
        return zzao(context, context.getPackageName());
    }

    static void zzek(Context context) {
        Intent intent = new Intent();
        intent.putExtra("CMD", "SYNC");
        zzq.zzbys().zze(context, intent);
    }

    static String zzm(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    public void deleteInstanceId() throws IOException {
        this.zzmll.zza("*", "*", null);
        this.zzmll.zzass();
    }

    @WorkerThread
    public void deleteToken(String str, String str2) throws IOException {
        Bundle bundle = new Bundle();
        zzac(bundle);
        this.zzmll.zza(str, str2, bundle);
    }

    public long getCreationTime() {
        return this.zzmll.getCreationTime();
    }

    public String getId() {
        return zza(this.zzmll.zzasr());
    }

    @Nullable
    public String getToken() {
        zzs zzsVarZzbyl = zzbyl();
        if (zzsVarZzbyl == null || zzsVarZzbyl.zzqk(zzj.zzhts)) {
            FirebaseInstanceIdService.zzem(this.zzmlk.getApplicationContext());
        }
        if (zzsVarZzbyl != null) {
            return zzsVarZzbyl.zzkoo;
        }
        return null;
    }

    @WorkerThread
    public String getToken(String str, String str2) throws IOException {
        Bundle bundle = new Bundle();
        zzac(bundle);
        return this.zzmll.getToken(str, str2, bundle);
    }

    @Nullable
    final zzs zzbyl() {
        return zzj.zzbyo().zzo("", this.zzmlm, "*");
    }

    final String zzbym() throws IOException {
        return getToken(this.zzmlm, "*");
    }

    public final void zzqa(String str) {
        zzmlj.zzqa(str);
        FirebaseInstanceIdService.zzem(this.zzmlk.getApplicationContext());
    }

    final void zzqb(String str) throws IOException {
        zzs zzsVarZzbyl = zzbyl();
        if (zzsVarZzbyl == null || zzsVarZzbyl.zzqk(zzj.zzhts)) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String strValueOf = String.valueOf("/topics/");
        String strValueOf2 = String.valueOf(str);
        bundle.putString("gcm.topic", strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        String str2 = zzsVarZzbyl.zzkoo;
        String strValueOf3 = String.valueOf("/topics/");
        String strValueOf4 = String.valueOf(str);
        String strConcat = strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3);
        zzac(bundle);
        this.zzmll.zzb(str2, strConcat, bundle);
    }

    final void zzqc(String str) throws IOException {
        zzs zzsVarZzbyl = zzbyl();
        if (zzsVarZzbyl == null || zzsVarZzbyl.zzqk(zzj.zzhts)) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String strValueOf = String.valueOf("/topics/");
        String strValueOf2 = String.valueOf(str);
        bundle.putString("gcm.topic", strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        zzj zzjVar = this.zzmll;
        String str2 = zzsVarZzbyl.zzkoo;
        String strValueOf3 = String.valueOf("/topics/");
        String strValueOf4 = String.valueOf(str);
        zzjVar.zza(str2, strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3), bundle);
    }
}
