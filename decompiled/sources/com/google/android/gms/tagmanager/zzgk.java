package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class zzgk {
    private static final Object zzjwh = null;
    private static Long zzjwi = new Long(0);
    private static Double zzjwj = new Double(0.0d);
    private static zzgj zzjwk = zzgj.zzbh(0);
    private static String zzjwl = new String("");
    private static Boolean zzjwm = new Boolean(false);
    private static List<Object> zzjwn = new ArrayList(0);
    private static Map<Object, Object> zzjwo = new HashMap();
    private static com.google.android.gms.internal.zzbp zzjwp = zzah(zzjwl);

    private static double getDouble(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        zzdj.e("getDouble received non-Number");
        return 0.0d;
    }

    private static String zzag(Object obj) {
        return obj == null ? zzjwl : obj.toString();
    }

    public static com.google.android.gms.internal.zzbp zzah(Object obj) {
        boolean z = false;
        com.google.android.gms.internal.zzbp zzbpVar = new com.google.android.gms.internal.zzbp();
        if (obj instanceof com.google.android.gms.internal.zzbp) {
            return (com.google.android.gms.internal.zzbp) obj;
        }
        if (obj instanceof String) {
            zzbpVar.type = 1;
            zzbpVar.string = (String) obj;
        } else if (obj instanceof List) {
            zzbpVar.type = 2;
            List list = (List) obj;
            ArrayList arrayList = new ArrayList(list.size());
            Iterator it = list.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                com.google.android.gms.internal.zzbp zzbpVarZzah = zzah(it.next());
                if (zzbpVarZzah == zzjwp) {
                    return zzjwp;
                }
                boolean z3 = z2 || zzbpVarZzah.zzyi;
                arrayList.add(zzbpVarZzah);
                z2 = z3;
            }
            zzbpVar.zzxz = (com.google.android.gms.internal.zzbp[]) arrayList.toArray(new com.google.android.gms.internal.zzbp[0]);
            z = z2;
        } else if (obj instanceof Map) {
            zzbpVar.type = 3;
            Set<Map.Entry> setEntrySet = ((Map) obj).entrySet();
            ArrayList arrayList2 = new ArrayList(setEntrySet.size());
            ArrayList arrayList3 = new ArrayList(setEntrySet.size());
            boolean z4 = false;
            for (Map.Entry entry : setEntrySet) {
                com.google.android.gms.internal.zzbp zzbpVarZzah2 = zzah(entry.getKey());
                com.google.android.gms.internal.zzbp zzbpVarZzah3 = zzah(entry.getValue());
                if (zzbpVarZzah2 == zzjwp || zzbpVarZzah3 == zzjwp) {
                    return zzjwp;
                }
                boolean z5 = z4 || zzbpVarZzah2.zzyi || zzbpVarZzah3.zzyi;
                arrayList2.add(zzbpVarZzah2);
                arrayList3.add(zzbpVarZzah3);
                z4 = z5;
            }
            zzbpVar.zzya = (com.google.android.gms.internal.zzbp[]) arrayList2.toArray(new com.google.android.gms.internal.zzbp[0]);
            zzbpVar.zzyb = (com.google.android.gms.internal.zzbp[]) arrayList3.toArray(new com.google.android.gms.internal.zzbp[0]);
            z = z4;
        } else if (zzai(obj)) {
            zzbpVar.type = 1;
            zzbpVar.string = obj.toString();
        } else if (zzaj(obj)) {
            zzbpVar.type = 6;
            zzbpVar.zzye = zzak(obj);
        } else {
            if (!(obj instanceof Boolean)) {
                String strValueOf = String.valueOf(obj == null ? "null" : obj.getClass().toString());
                zzdj.e(strValueOf.length() != 0 ? "Converting to Value from unknown object type: ".concat(strValueOf) : new String("Converting to Value from unknown object type: "));
                return zzjwp;
            }
            zzbpVar.type = 8;
            zzbpVar.zzyf = ((Boolean) obj).booleanValue();
        }
        zzbpVar.zzyi = z;
        return zzbpVar;
    }

    private static boolean zzai(Object obj) {
        return (obj instanceof Double) || (obj instanceof Float) || ((obj instanceof zzgj) && ((zzgj) obj).zzbfe());
    }

    private static boolean zzaj(Object obj) {
        return (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || ((obj instanceof zzgj) && ((zzgj) obj).zzbff());
    }

    private static long zzak(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        zzdj.e("getInt64 received non-Number");
        return 0L;
    }

    public static String zzb(com.google.android.gms.internal.zzbp zzbpVar) {
        return zzag(zzg(zzbpVar));
    }

    public static Object zzbfg() {
        return null;
    }

    public static Long zzbfh() {
        return zzjwi;
    }

    public static Double zzbfi() {
        return zzjwj;
    }

    public static Boolean zzbfj() {
        return zzjwm;
    }

    public static zzgj zzbfk() {
        return zzjwk;
    }

    public static String zzbfl() {
        return zzjwl;
    }

    public static com.google.android.gms.internal.zzbp zzbfm() {
        return zzjwp;
    }

    public static zzgj zzc(com.google.android.gms.internal.zzbp zzbpVar) {
        Object objZzg = zzg(zzbpVar);
        return objZzg instanceof zzgj ? (zzgj) objZzg : zzaj(objZzg) ? zzgj.zzbh(zzak(objZzg)) : zzai(objZzg) ? zzgj.zza(Double.valueOf(getDouble(objZzg))) : zzmf(zzag(objZzg));
    }

    public static Long zzd(com.google.android.gms.internal.zzbp zzbpVar) {
        Object objZzg = zzg(zzbpVar);
        if (zzaj(objZzg)) {
            return Long.valueOf(zzak(objZzg));
        }
        zzgj zzgjVarZzmf = zzmf(zzag(objZzg));
        return zzgjVarZzmf == zzjwk ? zzjwi : Long.valueOf(zzgjVarZzmf.longValue());
    }

    public static Double zze(com.google.android.gms.internal.zzbp zzbpVar) {
        Object objZzg = zzg(zzbpVar);
        if (zzai(objZzg)) {
            return Double.valueOf(getDouble(objZzg));
        }
        zzgj zzgjVarZzmf = zzmf(zzag(objZzg));
        return zzgjVarZzmf == zzjwk ? zzjwj : Double.valueOf(zzgjVarZzmf.doubleValue());
    }

    public static Boolean zzf(com.google.android.gms.internal.zzbp zzbpVar) {
        Object objZzg = zzg(zzbpVar);
        if (objZzg instanceof Boolean) {
            return (Boolean) objZzg;
        }
        String strZzag = zzag(objZzg);
        return "true".equalsIgnoreCase(strZzag) ? Boolean.TRUE : "false".equalsIgnoreCase(strZzag) ? Boolean.FALSE : zzjwm;
    }

    public static Object zzg(com.google.android.gms.internal.zzbp zzbpVar) {
        int i = 0;
        if (zzbpVar == null) {
            return null;
        }
        switch (zzbpVar.type) {
            case 1:
                break;
            case 2:
                ArrayList arrayList = new ArrayList(zzbpVar.zzxz.length);
                com.google.android.gms.internal.zzbp[] zzbpVarArr = zzbpVar.zzxz;
                int length = zzbpVarArr.length;
                while (i < length) {
                    Object objZzg = zzg(zzbpVarArr[i]);
                    if (objZzg == null) {
                        break;
                    } else {
                        arrayList.add(objZzg);
                        i++;
                    }
                }
                break;
            case 3:
                if (zzbpVar.zzya.length == zzbpVar.zzyb.length) {
                    HashMap map = new HashMap(zzbpVar.zzyb.length);
                    while (i < zzbpVar.zzya.length) {
                        Object objZzg2 = zzg(zzbpVar.zzya[i]);
                        Object objZzg3 = zzg(zzbpVar.zzyb[i]);
                        if (objZzg2 == null || objZzg3 == null) {
                            break;
                        } else {
                            map.put(objZzg2, objZzg3);
                            i++;
                        }
                    }
                    break;
                } else {
                    String strValueOf = String.valueOf(zzbpVar.toString());
                    zzdj.e(strValueOf.length() != 0 ? "Converting an invalid value to object: ".concat(strValueOf) : new String("Converting an invalid value to object: "));
                    break;
                }
            case 4:
                zzdj.e("Trying to convert a macro reference to object");
                break;
            case 5:
                zzdj.e("Trying to convert a function id to object");
                break;
            case 6:
                break;
            case 7:
                StringBuffer stringBuffer = new StringBuffer();
                com.google.android.gms.internal.zzbp[] zzbpVarArr2 = zzbpVar.zzyg;
                int length2 = zzbpVarArr2.length;
                while (i < length2) {
                    String strZzb = zzb(zzbpVarArr2[i]);
                    if (strZzb == zzjwl) {
                        break;
                    } else {
                        stringBuffer.append(strZzb);
                        i++;
                    }
                }
                break;
            case 8:
                break;
            default:
                zzdj.e(new StringBuilder(46).append("Failed to convert a value of type: ").append(zzbpVar.type).toString());
                break;
        }
        return null;
    }

    public static com.google.android.gms.internal.zzbp zzme(String str) {
        com.google.android.gms.internal.zzbp zzbpVar = new com.google.android.gms.internal.zzbp();
        zzbpVar.type = 5;
        zzbpVar.zzyd = str;
        return zzbpVar;
    }

    private static zzgj zzmf(String str) {
        try {
            return zzgj.zzmd(str);
        } catch (NumberFormatException e) {
            zzdj.e(new StringBuilder(String.valueOf(str).length() + 33).append("Failed to convert '").append(str).append("' to a number.").toString());
            return zzjwk;
        }
    }
}
