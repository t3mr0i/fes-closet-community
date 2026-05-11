package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.zzt;
import java.io.File;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/* loaded from: classes.dex */
final class zzr {
    private Context zzahy;
    SharedPreferences zzhuk;

    public zzr(Context context) {
        this(context, "com.google.android.gms.appid");
    }

    private zzr(Context context, String str) {
        this.zzahy = context;
        this.zzhuk = context.getSharedPreferences(str, 0);
        String strValueOf = String.valueOf(str);
        String strValueOf2 = String.valueOf("-no-backup");
        File file = new File(zzt.getNoBackupFilesDir(this.zzahy), strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        if (file.exists()) {
            return;
        }
        try {
            if (!file.createNewFile() || isEmpty()) {
                return;
            }
            Log.i("InstanceID/Store", "App restored, clearing state");
            FirebaseInstanceId.zza(this.zzahy, this);
        } catch (IOException e) {
            if (Log.isLoggable("InstanceID/Store", 3)) {
                String strValueOf3 = String.valueOf(e.getMessage());
                Log.d("InstanceID/Store", strValueOf3.length() != 0 ? "Error creating file in no backup dir: ".concat(strValueOf3) : new String("Error creating file in no backup dir: "));
            }
        }
    }

    private static String zzbk(String str, String str2) {
        return new StringBuilder(String.valueOf(str).length() + String.valueOf("|S|").length() + String.valueOf(str2).length()).append(str).append("|S|").append(str2).toString();
    }

    private final void zzht(String str) {
        SharedPreferences.Editor editorEdit = this.zzhuk.edit();
        for (String str2 : this.zzhuk.getAll().keySet()) {
            if (str2.startsWith(str)) {
                editorEdit.remove(str2);
            }
        }
        editorEdit.commit();
    }

    private static String zzn(String str, String str2, String str3) {
        return new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf("|T|").length() + String.valueOf(str2).length() + String.valueOf(str3).length()).append(str).append("|T|").append(str2).append("|").append(str3).toString();
    }

    public final synchronized boolean isEmpty() {
        return this.zzhuk.getAll().isEmpty();
    }

    public final synchronized void zza(String str, String str2, String str3, String str4, String str5) {
        String strZzc = zzs.zzc(str4, str5, System.currentTimeMillis());
        if (strZzc != null) {
            SharedPreferences.Editor editorEdit = this.zzhuk.edit();
            editorEdit.putString(zzn(str, str2, str3), strZzc);
            editorEdit.commit();
        }
    }

    public final synchronized void zzasw() {
        this.zzhuk.edit().clear().commit();
    }

    public final synchronized void zzf(String str, String str2, String str3) {
        String strZzn = zzn(str, str2, str3);
        SharedPreferences.Editor editorEdit = this.zzhuk.edit();
        editorEdit.remove(strZzn);
        editorEdit.commit();
    }

    public final synchronized void zzhu(String str) {
        zzht(String.valueOf(str).concat("|T|"));
    }

    public final synchronized zzs zzo(String str, String str2, String str3) {
        return zzs.zzqj(this.zzhuk.getString(zzn(str, str2, str3), null));
    }

    public final synchronized long zzqf(String str) {
        long j;
        String string = this.zzhuk.getString(zzbk(str, "cre"), null);
        if (string != null) {
            try {
                j = Long.parseLong(string);
            } catch (NumberFormatException e) {
            }
        } else {
            j = 0;
        }
        return j;
    }

    final synchronized KeyPair zzqg(String str) {
        KeyPair keyPairZzasq;
        keyPairZzasq = zza.zzasq();
        long jCurrentTimeMillis = System.currentTimeMillis();
        SharedPreferences.Editor editorEdit = this.zzhuk.edit();
        editorEdit.putString(zzbk(str, "|P|"), FirebaseInstanceId.zzm(keyPairZzasq.getPublic().getEncoded()));
        editorEdit.putString(zzbk(str, "|K|"), FirebaseInstanceId.zzm(keyPairZzasq.getPrivate().getEncoded()));
        editorEdit.putString(zzbk(str, "cre"), Long.toString(jCurrentTimeMillis));
        editorEdit.commit();
        return keyPairZzasq;
    }

    final synchronized void zzqh(String str) {
        zzht(String.valueOf(str).concat("|"));
    }

    public final synchronized KeyPair zzqi(String str) {
        KeyPair keyPair;
        String string = this.zzhuk.getString(zzbk(str, "|P|"), null);
        String string2 = this.zzhuk.getString(zzbk(str, "|K|"), null);
        if (string == null || string2 == null) {
            keyPair = null;
        } else {
            try {
                byte[] bArrDecode = Base64.decode(string, 8);
                byte[] bArrDecode2 = Base64.decode(string2, 8);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                keyPair = new KeyPair(keyFactory.generatePublic(new X509EncodedKeySpec(bArrDecode)), keyFactory.generatePrivate(new PKCS8EncodedKeySpec(bArrDecode2)));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                String strValueOf = String.valueOf(e);
                Log.w("InstanceID/Store", new StringBuilder(String.valueOf(strValueOf).length() + 19).append("Invalid key stored ").append(strValueOf).toString());
                FirebaseInstanceId.zza(this.zzahy, this);
                keyPair = null;
            }
        }
        return keyPair;
    }
}
