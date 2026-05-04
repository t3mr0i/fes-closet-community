package com.google.firebase.messaging;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzehf;
import com.google.android.gms.internal.zzeid;
import com.google.android.gms.internal.zzeie;
import com.google.android.gms.measurement.AppMeasurement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class zzc {
    private static Bundle zza(@NonNull zzeie zzeieVar) {
        return zzay(zzeieVar.zznkh, zzeieVar.zznki);
    }

    @Nullable
    private static Object zza(@NonNull zzeie zzeieVar, @NonNull String str, @NonNull zzb zzbVar) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Object objNewInstance;
        try {
            Class<?> cls = Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            Bundle bundleZza = zza(zzeieVar);
            objNewInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            try {
                cls.getField("mOrigin").set(objNewInstance, str);
                cls.getField("mCreationTimestamp").set(objNewInstance, Long.valueOf(zzeieVar.zznkj));
                cls.getField("mName").set(objNewInstance, zzeieVar.zznkh);
                cls.getField("mValue").set(objNewInstance, zzeieVar.zznki);
                cls.getField("mTriggerEventName").set(objNewInstance, TextUtils.isEmpty(zzeieVar.zznkk) ? null : zzeieVar.zznkk);
                cls.getField("mTimedOutEventName").set(objNewInstance, !TextUtils.isEmpty(zzeieVar.zznkp) ? zzeieVar.zznkp : zzbVar.zzbnt());
                cls.getField("mTimedOutEventParams").set(objNewInstance, bundleZza);
                cls.getField("mTriggerTimeout").set(objNewInstance, Long.valueOf(zzeieVar.zznkl));
                cls.getField("mTriggeredEventName").set(objNewInstance, !TextUtils.isEmpty(zzeieVar.zznkn) ? zzeieVar.zznkn : zzbVar.zzbns());
                cls.getField("mTriggeredEventParams").set(objNewInstance, bundleZza);
                cls.getField("mTimeToLive").set(objNewInstance, Long.valueOf(zzeieVar.zzgcb));
                cls.getField("mExpiredEventName").set(objNewInstance, !TextUtils.isEmpty(zzeieVar.zznkq) ? zzeieVar.zznkq : zzbVar.zzbnu());
                cls.getField("mExpiredEventParams").set(objNewInstance, bundleZza);
            } catch (ClassNotFoundException e) {
                e = e;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return objNewInstance;
            } catch (IllegalAccessException e2) {
                e = e2;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return objNewInstance;
            } catch (InstantiationException e3) {
                e = e3;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return objNewInstance;
            } catch (NoSuchFieldException e4) {
                e = e4;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return objNewInstance;
            } catch (NoSuchMethodException e5) {
                e = e5;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return objNewInstance;
            } catch (InvocationTargetException e6) {
                e = e6;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return objNewInstance;
            }
        } catch (ClassNotFoundException e7) {
            e = e7;
            objNewInstance = null;
        } catch (IllegalAccessException e8) {
            e = e8;
            objNewInstance = null;
        } catch (InstantiationException e9) {
            e = e9;
            objNewInstance = null;
        } catch (NoSuchFieldException e10) {
            e = e10;
            objNewInstance = null;
        } catch (NoSuchMethodException e11) {
            e = e11;
            objNewInstance = null;
        } catch (InvocationTargetException e12) {
            e = e12;
            objNewInstance = null;
        }
        return objNewInstance;
    }

    private static String zza(@Nullable zzeie zzeieVar, @NonNull zzb zzbVar) {
        return (zzeieVar == null || TextUtils.isEmpty(zzeieVar.zznko)) ? zzbVar.zzbnv() : zzeieVar.zznko;
    }

    private static List<Object> zza(@NonNull AppMeasurement appMeasurement, @NonNull String str) throws NoSuchMethodException, SecurityException {
        List<Object> list;
        ArrayList arrayList = new ArrayList();
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("getConditionalUserProperties", String.class, String.class);
            declaredMethod.setAccessible(true);
            list = (List) declaredMethod.invoke(appMeasurement, str, "");
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            list = arrayList;
        }
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str).length() + 55).append("Number of currently set _Es for origin: ").append(str).append(" is ").append(list.size()).toString());
        }
        return list;
    }

    private static void zza(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String strValueOf = String.valueOf(str);
            Log.v("FirebaseAbtUtil", strValueOf.length() != 0 ? "_CE(experimentId) called by ".concat(strValueOf) : new String("_CE(experimentId) called by "));
        }
        if (zzeh(context)) {
            AppMeasurement appMeasurementZzcs = zzcs(context);
            try {
                Method declaredMethod = AppMeasurement.class.getDeclaredMethod("clearConditionalUserProperty", String.class, String.class, Bundle.class);
                declaredMethod.setAccessible(true);
                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str2).length() + 17 + String.valueOf(str3).length()).append("Clearing _E: [").append(str2).append(", ").append(str3).append("]").toString());
                }
                declaredMethod.invoke(appMeasurementZzcs, str2, str4, zzay(str2, str3));
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            }
        }
    }

    public static void zza(@NonNull Context context, @NonNull String str, @NonNull byte[] bArr, @NonNull zzb zzbVar, int i) throws NoSuchMethodException, InstantiationException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String strValueOf = String.valueOf(str);
            Log.v("FirebaseAbtUtil", strValueOf.length() != 0 ? "_SE called by ".concat(strValueOf) : new String("_SE called by "));
        }
        if (zzeh(context)) {
            AppMeasurement appMeasurementZzcs = zzcs(context);
            zzeie zzeieVarZzak = zzak(bArr);
            if (zzeieVarZzak == null) {
                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    Log.v("FirebaseAbtUtil", "_SE failed; either _P was not set, or we couldn't deserialize the _P.");
                    return;
                }
                return;
            }
            try {
                Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                boolean z = false;
                for (Object obj : zza(appMeasurementZzcs, str)) {
                    String strZzar = zzar(obj);
                    String strZzas = zzas(obj);
                    long jLongValue = ((Long) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mCreationTimestamp").get(obj)).longValue();
                    if (zzeieVarZzak.zznkh.equals(strZzar) && zzeieVarZzak.zznki.equals(strZzas)) {
                        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                            Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(strZzar).length() + 23 + String.valueOf(strZzas).length()).append("_E is already set. [").append(strZzar).append(", ").append(strZzas).append("]").toString());
                        }
                        z = true;
                    } else {
                        boolean z2 = false;
                        zzeid[] zzeidVarArr = zzeieVarZzak.zznks;
                        int length = zzeidVarArr.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                break;
                            }
                            if (zzeidVarArr[i2].zznkh.equals(strZzar)) {
                                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                    Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(strZzar).length() + 33 + String.valueOf(strZzas).length()).append("_E is found in the _OE list. [").append(strZzar).append(", ").append(strZzas).append("]").toString());
                                }
                                z2 = true;
                            } else {
                                i2++;
                            }
                        }
                        if (!z2) {
                            if (zzeieVarZzak.zznkj > jLongValue) {
                                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                    Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(strZzar).length() + 115 + String.valueOf(strZzas).length()).append("Clearing _E as it was not in the _OE list, andits start time is older than the start time of the _E to be set. [").append(strZzar).append(", ").append(strZzas).append("]").toString());
                                }
                                zza(context, str, strZzar, strZzas, zza(zzeieVarZzak, zzbVar));
                            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(strZzar).length() + 109 + String.valueOf(strZzas).length()).append("_E was not found in the _OE list, but not clearing it as it has a new start time than the _E to be set.  [").append(strZzar).append(", ").append(strZzas).append("]").toString());
                            }
                        }
                    }
                }
                if (!z) {
                    zza(appMeasurementZzcs, context, str, zzeieVarZzak, zzbVar, 1);
                } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    String str2 = zzeieVarZzak.zznkh;
                    String str3 = zzeieVarZzak.zznki;
                    Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str2).length() + 44 + String.valueOf(str3).length()).append("_E is already set. Not setting it again [").append(str2).append(", ").append(str3).append("]").toString());
                }
            } catch (ClassNotFoundException e) {
                e = e;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            } catch (IllegalAccessException e2) {
                e = e2;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            } catch (NoSuchFieldException e3) {
                e = e3;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            }
        }
    }

    private static void zza(@NonNull AppMeasurement appMeasurement, @NonNull Context context, @NonNull String str, @NonNull zzeie zzeieVar, @NonNull zzb zzbVar, int i) throws IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String str2 = zzeieVar.zznkh;
            String str3 = zzeieVar.zznki;
            Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str2).length() + 7 + String.valueOf(str3).length()).append("_SEI: ").append(str2).append(" ").append(str3).toString());
        }
        try {
            try {
                Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                List<Object> listZza = zza(appMeasurement, str);
                if (zza(appMeasurement, str).size() >= zzb(appMeasurement, str)) {
                    if ((zzeieVar.zznkr != 0 ? zzeieVar.zznkr : 1) != 1) {
                        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                            String str4 = zzeieVar.zznkh;
                            String str5 = zzeieVar.zznki;
                            Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str4).length() + 44 + String.valueOf(str5).length()).append("_E won't be set due to overflow policy. [").append(str4).append(", ").append(str5).append("]").toString());
                            return;
                        }
                        return;
                    }
                    Object obj = listZza.get(0);
                    String strZzar = zzar(obj);
                    String strZzas = zzas(obj);
                    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                        Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(strZzar).length() + 38).append("Clearing _E due to overflow policy: [").append(strZzar).append("]").toString());
                    }
                    zza(context, str, strZzar, strZzas, zza(zzeieVar, zzbVar));
                }
                for (Object obj2 : listZza) {
                    String strZzar2 = zzar(obj2);
                    String strZzas2 = zzas(obj2);
                    if (strZzar2.equals(zzeieVar.zznkh) && !strZzas2.equals(zzeieVar.zznki) && Log.isLoggable("FirebaseAbtUtil", 2)) {
                        Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(strZzar2).length() + 77 + String.valueOf(strZzas2).length()).append("Clearing _E, as only one _V of the same _E can be set atany given time: [").append(strZzar2).append(", ").append(strZzas2).append("].").toString());
                        zza(context, str, strZzar2, strZzas2, zza(zzeieVar, zzbVar));
                    }
                }
                Object objZza = zza(zzeieVar, str, zzbVar);
                if (objZza == null) {
                    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                        String str6 = zzeieVar.zznkh;
                        String str7 = zzeieVar.zznki;
                        Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str6).length() + 42 + String.valueOf(str7).length()).append("Could not create _CUP for: [").append(str6).append(", ").append(str7).append("]. Skipping.").toString());
                        return;
                    }
                    return;
                }
                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    String str8 = zzeieVar.zznkh;
                    String str9 = zzeieVar.zznki;
                    String str10 = zzeieVar.zznkk;
                    Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str8).length() + 27 + String.valueOf(str9).length() + String.valueOf(str10).length()).append("Setting _CUP for _E: [").append(str8).append(", ").append(str9).append(", ").append(str10).append("]").toString());
                }
                try {
                    Method declaredMethod = AppMeasurement.class.getDeclaredMethod("setConditionalUserProperty", Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty"));
                    declaredMethod.setAccessible(true);
                    appMeasurement.logEventInternal(str, !TextUtils.isEmpty(zzeieVar.zznkm) ? zzeieVar.zznkm : zzbVar.zzbnr(), zza(zzeieVar));
                    declaredMethod.invoke(appMeasurement, objZza);
                } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                }
            } catch (NoSuchFieldException e2) {
                e = e2;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            }
        } catch (ClassNotFoundException e3) {
            e = e3;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
        } catch (IllegalAccessException e4) {
            e = e4;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
        }
    }

    @Nullable
    private static zzeie zzak(@NonNull byte[] bArr) {
        try {
            return zzeie.zzaz(bArr);
        } catch (zzehf e) {
            return null;
        }
    }

    private static String zzar(@NonNull Object obj) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        return (String) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mName").get(obj);
    }

    private static String zzas(@NonNull Object obj) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        return (String) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mValue").get(obj);
    }

    private static Bundle zzay(@NonNull String str, @NonNull String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        return bundle;
    }

    private static int zzb(@NonNull AppMeasurement appMeasurement, @NonNull String str) throws NoSuchMethodException, SecurityException {
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("getMaxUserProperties", String.class);
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(appMeasurement, str)).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return 20;
        }
    }

    @Nullable
    private static AppMeasurement zzcs(Context context) {
        try {
            return AppMeasurement.getInstance(context);
        } catch (NoClassDefFoundError e) {
            return null;
        }
    }

    private static boolean zzeh(Context context) throws ClassNotFoundException {
        if (zzcs(context) == null) {
            if (!Log.isLoggable("FirebaseAbtUtil", 2)) {
                return false;
            }
            Log.v("FirebaseAbtUtil", "Firebase Analytics not available");
            return false;
        }
        try {
            Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            return true;
        } catch (ClassNotFoundException e) {
            if (!Log.isLoggable("FirebaseAbtUtil", 2)) {
                return false;
            }
            Log.v("FirebaseAbtUtil", "Firebase Analytics library is missing support for abt. Please update to a more recent version.");
            return false;
        }
    }
}
