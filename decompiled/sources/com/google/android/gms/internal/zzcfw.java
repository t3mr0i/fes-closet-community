package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes.dex */
public final class zzcfw extends zzcdu {
    private static String[] zzixd = {"firebase_"};
    private SecureRandom zzixe;
    private final AtomicLong zzixf;
    private int zzixg;

    zzcfw(zzccw zzccwVar) {
        super(zzccwVar);
        this.zzixf = new AtomicLong(0L);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0029 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zza(java.lang.String r8, java.lang.Object r9, boolean r10) throws java.lang.IllegalStateException {
        /*
            r7 = this;
            r1 = 1
            r6 = 0
            if (r10 == 0) goto L3c
            java.lang.String r2 = "param"
            com.google.android.gms.internal.zzcax.zzavt()
            boolean r0 = r9 instanceof android.os.Parcelable[]
            if (r0 == 0) goto L2c
            r0 = r9
            android.os.Parcelable[] r0 = (android.os.Parcelable[]) r0
            int r0 = r0.length
        L11:
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r0 <= r3) goto L3a
            com.google.android.gms.internal.zzcbw r1 = r7.zzaum()
            com.google.android.gms.internal.zzcby r1 = r1.zzayg()
            java.lang.String r3 = "Parameter array is too long; discarded. Value kind, name, array length"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.zzd(r3, r2, r8, r0)
            r0 = r6
        L27:
            if (r0 != 0) goto L3c
            r0 = 17
        L2b:
            return r0
        L2c:
            boolean r0 = r9 instanceof java.util.ArrayList
            if (r0 == 0) goto L38
            r0 = r9
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r0 = r0.size()
            goto L11
        L38:
            r0 = r1
            goto L27
        L3a:
            r0 = r1
            goto L27
        L3c:
            boolean r0 = zzkd(r8)
            if (r0 == 0) goto L54
            java.lang.String r1 = "param"
            int r3 = com.google.android.gms.internal.zzcax.zzavs()
            r0 = r7
            r2 = r8
            r4 = r9
            r5 = r10
            boolean r0 = r0.zza(r1, r2, r3, r4, r5)
        L50:
            if (r0 == 0) goto L63
            r0 = r6
            goto L2b
        L54:
            java.lang.String r1 = "param"
            int r3 = com.google.android.gms.internal.zzcax.zzavr()
            r0 = r7
            r2 = r8
            r4 = r9
            r5 = r10
            boolean r0 = r0.zza(r1, r2, r3, r4, r5)
            goto L50
        L63:
            r0 = 4
            goto L2b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcfw.zza(java.lang.String, java.lang.Object, boolean):int");
    }

    private static Object zza(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf(((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf(((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf(((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1L : 0L);
        }
        if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        }
        if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
            return zza(String.valueOf(obj), i, z);
        }
        return null;
    }

    public static String zza(String str, int i, boolean z) {
        if (str.codePointCount(0, str.length()) <= i) {
            return str;
        }
        if (z) {
            return String.valueOf(str.substring(0, str.offsetByCodePoints(0, i))).concat("...");
        }
        return null;
    }

    @Nullable
    public static String zza(String str, String[] strArr, String[] strArr2) {
        com.google.android.gms.common.internal.zzbp.zzu(strArr);
        com.google.android.gms.common.internal.zzbp.zzu(strArr2);
        int iMin = Math.min(strArr.length, strArr2.length);
        for (int i = 0; i < iMin; i++) {
            if (zzas(str, strArr[i])) {
                return strArr2[i];
            }
        }
        return null;
    }

    public static boolean zza(Context context, String str, boolean z) {
        ActivityInfo receiverInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, str), 2)) == null) {
                return false;
            }
            return receiverInfo.enabled;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private final boolean zza(String str, String str2, int i, Object obj, boolean z) throws IllegalStateException {
        if (obj == null || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Boolean) || (obj instanceof Double)) {
            return true;
        }
        if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
            String strValueOf = String.valueOf(obj);
            if (strValueOf.codePointCount(0, strValueOf.length()) <= i) {
                return true;
            }
            zzaum().zzayg().zzd("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(strValueOf.length()));
            return false;
        }
        if ((obj instanceof Bundle) && z) {
            return true;
        }
        if ((obj instanceof Parcelable[]) && z) {
            for (Parcelable parcelable : (Parcelable[]) obj) {
                if (!(parcelable instanceof Bundle)) {
                    zzaum().zzayg().zze("All Parcelable[] elements must be of type Bundle. Value type, name", parcelable.getClass(), str2);
                    return false;
                }
            }
            return true;
        }
        if (!(obj instanceof ArrayList) || !z) {
            return false;
        }
        ArrayList arrayList = (ArrayList) obj;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj2 = arrayList.get(i2);
            i2++;
            if (!(obj2 instanceof Bundle)) {
                zzaum().zzayg().zze("All ArrayList elements must be of type Bundle. Value type, name", obj2.getClass(), str2);
                return false;
            }
        }
        return true;
    }

    private final boolean zza(String str, String[] strArr, String str2) throws IllegalStateException {
        boolean z;
        boolean z2;
        if (str2 == null) {
            zzaum().zzaye().zzj("Name is required and can't be null. Type", str);
            return false;
        }
        com.google.android.gms.common.internal.zzbp.zzu(str2);
        int i = 0;
        while (true) {
            if (i >= zzixd.length) {
                z = false;
                break;
            }
            if (str2.startsWith(zzixd[i])) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            zzaum().zzaye().zze("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        }
        if (strArr != null) {
            com.google.android.gms.common.internal.zzbp.zzu(strArr);
            int i2 = 0;
            while (true) {
                if (i2 >= strArr.length) {
                    z2 = false;
                    break;
                }
                if (zzas(str2, strArr[i2])) {
                    z2 = true;
                    break;
                }
                i2++;
            }
            if (z2) {
                zzaum().zzaye().zze("Name is reserved. Type, name", str, str2);
                return false;
            }
        }
        return true;
    }

    public static boolean zza(long[] jArr, int i) {
        return i < (jArr.length << 6) && (jArr[i / 64] & (1 << (i % 64))) != 0;
    }

    static byte[] zza(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(parcelObtain, 0);
            return parcelObtain.marshall();
        } finally {
            parcelObtain.recycle();
        }
    }

    public static long[] zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = 0;
            for (int i2 = 0; i2 < 64 && (i << 6) + i2 < bitSet.length(); i2++) {
                if (bitSet.get((i << 6) + i2)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
            }
        }
        return jArr;
    }

    public static Bundle[] zzac(Object obj) {
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        }
        if (obj instanceof Parcelable[]) {
            return (Bundle[]) Arrays.copyOf((Parcelable[]) obj, ((Parcelable[]) obj).length, Bundle[].class);
        }
        if (!(obj instanceof ArrayList)) {
            return null;
        }
        ArrayList arrayList = (ArrayList) obj;
        return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
    }

    public static Object zzad(Object obj) throws Throwable {
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
        try {
            if (obj == null) {
                return null;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                try {
                    objectOutputStream.writeObject(obj);
                    objectOutputStream.flush();
                    objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                } catch (Throwable th) {
                    th = th;
                    objectInputStream = null;
                }
                try {
                    Object object = objectInputStream.readObject();
                    objectOutputStream.close();
                    objectInputStream.close();
                    return object;
                } catch (Throwable th2) {
                    th = th2;
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                objectInputStream = null;
                objectOutputStream = null;
            }
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e2) {
            return null;
        }
    }

    private final boolean zzai(Context context, String str) {
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = zzbed.zzcr(context).getPackageInfo(str, 64);
            if (packageInfo != null && packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
            }
        } catch (PackageManager.NameNotFoundException e) {
            zzaum().zzaye().zzj("Package name not found", e);
        } catch (CertificateException e2) {
            zzaum().zzaye().zzj("Error obtaining certificate", e2);
        }
        return true;
    }

    private final boolean zzaq(String str, String str2) throws IllegalStateException {
        if (str2 == null) {
            zzaum().zzaye().zzj("Name is required and can't be null. Type", str);
            return false;
        }
        if (str2.length() == 0) {
            zzaum().zzaye().zzj("Name is required and can't be empty. Type", str);
            return false;
        }
        int iCodePointAt = str2.codePointAt(0);
        if (!Character.isLetter(iCodePointAt)) {
            zzaum().zzaye().zze("Name must start with a letter. Type, name", str, str2);
            return false;
        }
        int length = str2.length();
        int iCharCount = Character.charCount(iCodePointAt);
        while (iCharCount < length) {
            int iCodePointAt2 = str2.codePointAt(iCharCount);
            if (iCodePointAt2 != 95 && !Character.isLetterOrDigit(iCodePointAt2)) {
                zzaum().zzaye().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt2);
        }
        return true;
    }

    private final boolean zzar(String str, String str2) throws IllegalStateException {
        if (str2 == null) {
            zzaum().zzaye().zzj("Name is required and can't be null. Type", str);
            return false;
        }
        if (str2.length() == 0) {
            zzaum().zzaye().zzj("Name is required and can't be empty. Type", str);
            return false;
        }
        int iCodePointAt = str2.codePointAt(0);
        if (!Character.isLetter(iCodePointAt) && iCodePointAt != 95) {
            zzaum().zzaye().zze("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
        int length = str2.length();
        int iCharCount = Character.charCount(iCodePointAt);
        while (iCharCount < length) {
            int iCodePointAt2 = str2.codePointAt(iCharCount);
            if (iCodePointAt2 != 95 && !Character.isLetterOrDigit(iCodePointAt2)) {
                zzaum().zzaye().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt2);
        }
        return true;
    }

    public static boolean zzas(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null) {
            return false;
        }
        return str.equals(str2);
    }

    private static void zzb(Bundle bundle, Object obj) {
        com.google.android.gms.common.internal.zzbp.zzu(bundle);
        if (obj != null) {
            if ((obj instanceof String) || (obj instanceof CharSequence)) {
                bundle.putLong("_el", String.valueOf(obj).length());
            }
        }
    }

    private final boolean zzb(String str, int i, String str2) throws IllegalStateException {
        if (str2 == null) {
            zzaum().zzaye().zzj("Name is required and can't be null. Type", str);
            return false;
        }
        if (str2.codePointCount(0, str2.length()) <= i) {
            return true;
        }
        zzaum().zzaye().zzd("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
        return false;
    }

    private static boolean zzd(Bundle bundle, int i) {
        if (bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", i);
        return true;
    }

    @WorkerThread
    static boolean zzd(zzcbk zzcbkVar, zzcas zzcasVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzcbkVar);
        com.google.android.gms.common.internal.zzbp.zzu(zzcasVar);
        if (!TextUtils.isEmpty(zzcasVar.zzilt)) {
            return true;
        }
        zzcax.zzawl();
        return false;
    }

    static MessageDigest zzec(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 2) {
                return null;
            }
            try {
                messageDigest = MessageDigest.getInstance(str);
            } catch (NoSuchAlgorithmException e) {
            }
            if (messageDigest != null) {
                return messageDigest;
            }
            i = i2 + 1;
        }
    }

    static boolean zzju(String str) {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        return str.charAt(0) != '_' || str.equals("_ep");
    }

    private final int zzjz(String str) {
        if (!zzaq("event param", str)) {
            return 3;
        }
        if (zza("event param", (String[]) null, str)) {
            return zzb("event param", zzcax.zzavq(), str) ? 0 : 3;
        }
        return 14;
    }

    private final int zzka(String str) {
        if (!zzar("event param", str)) {
            return 3;
        }
        if (zza("event param", (String[]) null, str)) {
            return zzb("event param", zzcax.zzavq(), str) ? 0 : 3;
        }
        return 14;
    }

    private static int zzkc(String str) {
        return "_ldl".equals(str) ? zzcax.zzavv() : zzcax.zzavu();
    }

    public static boolean zzkd(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    static boolean zzkf(String str) {
        return str != null && str.matches("(\\+|-)?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    @WorkerThread
    static boolean zzki(String str) {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        switch (str) {
            case "_in":
            case "_ui":
            case "_ug":
                return true;
            default:
                return false;
        }
    }

    public static boolean zzl(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    static long zzq(byte[] bArr) {
        int i = 0;
        com.google.android.gms.common.internal.zzbp.zzu(bArr);
        com.google.android.gms.common.internal.zzbp.zzbg(bArr.length > 0);
        long j = 0;
        for (int length = bArr.length - 1; length >= 0 && length >= bArr.length - 8; length--) {
            j += (bArr[length] & 255) << i;
            i += 8;
        }
        return j;
    }

    public static boolean zzv(Context context, String str) {
        ServiceInfo serviceInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 4)) == null) {
                return false;
            }
            return serviceInfo.enabled;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final Bundle zza(String str, Bundle bundle, @Nullable List<String> list, boolean z, boolean z2) throws IllegalStateException {
        int iZzjz;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = new Bundle(bundle);
        zzcax.zzavn();
        int i = 0;
        for (String str2 : bundle.keySet()) {
            if (list == null || !list.contains(str2)) {
                iZzjz = z ? zzjz(str2) : 0;
                if (iZzjz == 0) {
                    iZzjz = zzka(str2);
                }
            } else {
                iZzjz = 0;
            }
            if (iZzjz != 0) {
                if (zzd(bundle2, iZzjz)) {
                    bundle2.putString("_ev", zza(str2, zzcax.zzavq(), true));
                    if (iZzjz == 3) {
                        zzb(bundle2, str2);
                    }
                }
                bundle2.remove(str2);
            } else {
                int iZza = zza(str2, bundle.get(str2), z2);
                if (iZza != 0 && !"_ev".equals(str2)) {
                    if (zzd(bundle2, iZza)) {
                        bundle2.putString("_ev", zza(str2, zzcax.zzavq(), true));
                        zzb(bundle2, bundle.get(str2));
                    }
                    bundle2.remove(str2);
                } else if (!zzju(str2) || (i = i + 1) <= 25) {
                    i = i;
                } else {
                    zzaum().zzaye().zze(new StringBuilder(48).append("Event can't contain more then 25 params").toString(), zzauh().zzjc(str), zzauh().zzx(bundle));
                    zzd(bundle2, 5);
                    bundle2.remove(str2);
                }
            }
        }
        return bundle2;
    }

    final zzcbk zza(String str, Bundle bundle, String str2, long j, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (zzjw(str) != 0) {
            zzaum().zzaye().zzj("Invalid conditional property event name", zzauh().zzje(str));
            throw new IllegalArgumentException();
        }
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        bundle2.putString("_o", str2);
        return new zzcbk(str, new zzcbh(zzy(zza(str, bundle2, Collections.singletonList("_o"), false, false))), str2, j);
    }

    public final void zza(int i, String str, String str2, int i2) {
        zza((String) null, i, str, str2, i2);
    }

    public final void zza(Bundle bundle, String str, Object obj) throws IllegalStateException {
        if (bundle == null) {
            return;
        }
        if (obj instanceof Long) {
            bundle.putLong(str, ((Long) obj).longValue());
            return;
        }
        if (obj instanceof String) {
            bundle.putString(str, String.valueOf(obj));
        } else if (obj instanceof Double) {
            bundle.putDouble(str, ((Double) obj).doubleValue());
        } else if (str != null) {
            zzaum().zzayh().zze("Not putting event parameter. Invalid value type. name, type", zzauh().zzjd(str), obj != null ? obj.getClass().getSimpleName() : null);
        }
    }

    public final void zza(zzcgi zzcgiVar, Object obj) {
        com.google.android.gms.common.internal.zzbp.zzu(obj);
        zzcgiVar.zzfwn = null;
        zzcgiVar.zziza = null;
        zzcgiVar.zzixb = null;
        if (obj instanceof String) {
            zzcgiVar.zzfwn = (String) obj;
            return;
        }
        if (obj instanceof Long) {
            zzcgiVar.zziza = (Long) obj;
        } else if (obj instanceof Double) {
            zzcgiVar.zzixb = (Double) obj;
        } else {
            zzaum().zzaye().zzj("Ignoring invalid (type) event param value", obj);
        }
    }

    public final void zza(zzcgm zzcgmVar, Object obj) {
        com.google.android.gms.common.internal.zzbp.zzu(obj);
        zzcgmVar.zzfwn = null;
        zzcgmVar.zziza = null;
        zzcgmVar.zzixb = null;
        if (obj instanceof String) {
            zzcgmVar.zzfwn = (String) obj;
            return;
        }
        if (obj instanceof Long) {
            zzcgmVar.zziza = (Long) obj;
        } else if (obj instanceof Double) {
            zzcgmVar.zzixb = (Double) obj;
        } else {
            zzaum().zzaye().zzj("Ignoring invalid (type) user attribute value", obj);
        }
    }

    public final void zza(String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        zzd(bundle, i);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", i2);
        }
        zzcax.zzawl();
        this.zzikh.zzaua().zzc("auto", "_err", bundle);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0078  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x006b -> B:18:0x0078). Please report as a decompilation issue!!! */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final long zzah(android.content.Context r9, java.lang.String r10) throws java.security.NoSuchAlgorithmException {
        /*
            r8 = this;
            r0 = -1
            r8.zzuj()
            com.google.android.gms.common.internal.zzbp.zzu(r9)
            com.google.android.gms.common.internal.zzbp.zzgg(r10)
            r2 = 0
            android.content.pm.PackageManager r4 = r9.getPackageManager()
            java.lang.String r5 = "MD5"
            java.security.MessageDigest r5 = zzec(r5)
            if (r5 != 0) goto L27
            com.google.android.gms.internal.zzcbw r2 = r8.zzaum()
            com.google.android.gms.internal.zzcby r2 = r2.zzaye()
            java.lang.String r3 = "Could not get MD5 instance"
            r2.log(r3)
        L26:
            return r0
        L27:
            if (r4 == 0) goto L78
            boolean r4 = r8.zzai(r9, r10)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            if (r4 != 0) goto L78
            com.google.android.gms.internal.zzbec r4 = com.google.android.gms.internal.zzbed.zzcr(r9)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            android.content.Context r6 = r8.getContext()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            java.lang.String r6 = r6.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            r7 = 64
            android.content.pm.PackageInfo r4 = r4.getPackageInfo(r6, r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            android.content.pm.Signature[] r6 = r4.signatures     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            if (r6 == 0) goto L5c
            android.content.pm.Signature[] r6 = r4.signatures     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            int r6 = r6.length     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            if (r6 <= 0) goto L5c
            android.content.pm.Signature[] r0 = r4.signatures     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            r1 = 0
            r0 = r0[r1]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            byte[] r0 = r0.toByteArray()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            byte[] r0 = r5.digest(r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            long r0 = zzq(r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            goto L26
        L5c:
            com.google.android.gms.internal.zzcbw r4 = r8.zzaum()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            com.google.android.gms.internal.zzcby r4 = r4.zzayg()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            java.lang.String r5 = "Could not get signatures"
            r4.log(r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L6a
            goto L26
        L6a:
            r0 = move-exception
            com.google.android.gms.internal.zzcbw r1 = r8.zzaum()
            com.google.android.gms.internal.zzcby r1 = r1.zzaye()
            java.lang.String r4 = "Package name not found"
            r1.zzj(r4, r0)
        L78:
            r0 = r2
            goto L26
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcfw.zzah(android.content.Context, java.lang.String):long");
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

    public final long zzazy() {
        long andIncrement;
        if (this.zzixf.get() == 0) {
            synchronized (this.zzixf) {
                long jNextLong = new Random(System.nanoTime() ^ zzvx().currentTimeMillis()).nextLong();
                int i = this.zzixg + 1;
                this.zzixg = i;
                andIncrement = jNextLong + i;
            }
        } else {
            synchronized (this.zzixf) {
                this.zzixf.compareAndSet(-1L, 1L);
                andIncrement = this.zzixf.getAndIncrement();
            }
        }
        return andIncrement;
    }

    @WorkerThread
    final SecureRandom zzazz() {
        zzuj();
        if (this.zzixe == null) {
            this.zzixe = new SecureRandom();
        }
        return this.zzixe;
    }

    final <T extends Parcelable> T zzb(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.unmarshall(bArr, 0, bArr.length);
            parcelObtain.setDataPosition(0);
            return creator.createFromParcel(parcelObtain);
        } catch (zzbcm e) {
            zzaum().zzaye().log("Failed to load parcelable from buffer");
            return null;
        } finally {
            parcelObtain.recycle();
        }
    }

    public final byte[] zzb(zzcgj zzcgjVar) {
        try {
            byte[] bArr = new byte[zzcgjVar.zzhi()];
            zzegy zzegyVarZzi = zzegy.zzi(bArr, 0, bArr.length);
            zzcgjVar.zza(zzegyVarZzi);
            zzegyVarZzi.zzccm();
            return bArr;
        } catch (IOException e) {
            zzaum().zzaye().zzj("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    @WorkerThread
    public final boolean zzdt(String str) {
        zzuj();
        if (zzbed.zzcr(getContext()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzaum().zzayj().zzj("Permission not granted", str);
        return false;
    }

    public final boolean zzf(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzvx().currentTimeMillis() - j) > j2;
    }

    public final int zzjv(String str) {
        if (!zzaq("event", str)) {
            return 2;
        }
        if (zza("event", AppMeasurement.Event.zziki, str)) {
            return zzb("event", zzcax.zzavo(), str) ? 0 : 2;
        }
        return 13;
    }

    public final int zzjw(String str) {
        if (!zzar("event", str)) {
            return 2;
        }
        if (zza("event", AppMeasurement.Event.zziki, str)) {
            return zzb("event", zzcax.zzavo(), str) ? 0 : 2;
        }
        return 13;
    }

    public final int zzjx(String str) {
        if (!zzaq("user property", str)) {
            return 6;
        }
        if (zza("user property", AppMeasurement.UserProperty.zzikp, str)) {
            return zzb("user property", zzcax.zzavp(), str) ? 0 : 6;
        }
        return 15;
    }

    public final int zzjy(String str) {
        if (!zzar("user property", str)) {
            return 6;
        }
        if (zza("user property", AppMeasurement.UserProperty.zzikp, str)) {
            return zzb("user property", zzcax.zzavp(), str) ? 0 : 6;
        }
        return 15;
    }

    public final Object zzk(String str, Object obj) {
        if ("_ev".equals(str)) {
            return zza(zzcax.zzavs(), obj, true);
        }
        return zza(zzkd(str) ? zzcax.zzavs() : zzcax.zzavr(), obj, false);
    }

    public final boolean zzkb(String str) {
        if (TextUtils.isEmpty(str)) {
            zzaum().zzaye().log("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            return false;
        }
        com.google.android.gms.common.internal.zzbp.zzu(str);
        if (str.matches("^1:\\d+:android:[a-f0-9]+$")) {
            return true;
        }
        zzaum().zzaye().zzj("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", str);
        return false;
    }

    public final boolean zzke(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String strZzaxh = zzauo().zzaxh();
        zzcax.zzawl();
        return strZzaxh.equals(str);
    }

    final boolean zzkg(String str) {
        return "1".equals(zzauj().zzan(str, "measurement.upload.blacklist_internal"));
    }

    final boolean zzkh(String str) {
        return "1".equals(zzauj().zzan(str, "measurement.upload.blacklist_public"));
    }

    public final int zzl(String str, Object obj) {
        return "_ldl".equals(str) ? zza("user property referrer", str, zzkc(str), obj, false) : zza("user property", str, zzkc(str), obj, false) ? 0 : 7;
    }

    public final Object zzm(String str, Object obj) {
        return "_ldl".equals(str) ? zza(zzkc(str), obj, true) : zza(zzkc(str), obj, false);
    }

    public final byte[] zzo(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzaum().zzaye().zzj("Failed to gzip content", e);
            throw e;
        }
    }

    public final Bundle zzp(@NonNull Uri uri) {
        String queryParameter;
        String queryParameter2;
        String queryParameter3;
        String queryParameter4;
        Bundle bundle = null;
        if (uri != null) {
            try {
                if (uri.isHierarchical()) {
                    queryParameter4 = uri.getQueryParameter("utm_campaign");
                    queryParameter3 = uri.getQueryParameter("utm_source");
                    queryParameter2 = uri.getQueryParameter("utm_medium");
                    queryParameter = uri.getQueryParameter("gclid");
                } else {
                    queryParameter = null;
                    queryParameter2 = null;
                    queryParameter3 = null;
                    queryParameter4 = null;
                }
                if (!TextUtils.isEmpty(queryParameter4) || !TextUtils.isEmpty(queryParameter3) || !TextUtils.isEmpty(queryParameter2) || !TextUtils.isEmpty(queryParameter)) {
                    bundle = new Bundle();
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString(FirebaseAnalytics.Param.CAMPAIGN, queryParameter4);
                    }
                    if (!TextUtils.isEmpty(queryParameter3)) {
                        bundle.putString(FirebaseAnalytics.Param.SOURCE, queryParameter3);
                    }
                    if (!TextUtils.isEmpty(queryParameter2)) {
                        bundle.putString(FirebaseAnalytics.Param.MEDIUM, queryParameter2);
                    }
                    if (!TextUtils.isEmpty(queryParameter)) {
                        bundle.putString("gclid", queryParameter);
                    }
                    String queryParameter5 = uri.getQueryParameter("utm_term");
                    if (!TextUtils.isEmpty(queryParameter5)) {
                        bundle.putString(FirebaseAnalytics.Param.TERM, queryParameter5);
                    }
                    String queryParameter6 = uri.getQueryParameter("utm_content");
                    if (!TextUtils.isEmpty(queryParameter6)) {
                        bundle.putString(FirebaseAnalytics.Param.CONTENT, queryParameter6);
                    }
                    String queryParameter7 = uri.getQueryParameter(FirebaseAnalytics.Param.ACLID);
                    if (!TextUtils.isEmpty(queryParameter7)) {
                        bundle.putString(FirebaseAnalytics.Param.ACLID, queryParameter7);
                    }
                    String queryParameter8 = uri.getQueryParameter(FirebaseAnalytics.Param.CP1);
                    if (!TextUtils.isEmpty(queryParameter8)) {
                        bundle.putString(FirebaseAnalytics.Param.CP1, queryParameter8);
                    }
                    String queryParameter9 = uri.getQueryParameter("anid");
                    if (!TextUtils.isEmpty(queryParameter9)) {
                        bundle.putString("anid", queryParameter9);
                    }
                }
            } catch (UnsupportedOperationException e) {
                zzaum().zzayg().zzj("Install referrer url isn't a hierarchical URI", e);
            }
        }
        return bundle;
    }

    public final byte[] zzp(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int i = gZIPInputStream.read(bArr2);
                if (i <= 0) {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr2, 0, i);
            }
        } catch (IOException e) {
            zzaum().zzaye().zzj("Failed to ungzip content", e);
            throw e;
        }
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    @Override // com.google.android.gms.internal.zzcdu
    protected final void zzuk() {
        SecureRandom secureRandom = new SecureRandom();
        long jNextLong = secureRandom.nextLong();
        if (jNextLong == 0) {
            jNextLong = secureRandom.nextLong();
            if (jNextLong == 0) {
                zzaum().zzayg().log("Utils falling back to Random for random id");
            }
        }
        this.zzixf.set(jNextLong);
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ com.google.android.gms.common.util.zzd zzvx() {
        return super.zzvx();
    }

    final Bundle zzy(Bundle bundle) throws IllegalStateException {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object objZzk = zzk(str, bundle.get(str));
                if (objZzk == null) {
                    zzaum().zzayg().zzj("Param value can't be null", zzauh().zzjd(str));
                } else {
                    zza(bundle2, str, objZzk);
                }
            }
        }
        return bundle2;
    }
}
