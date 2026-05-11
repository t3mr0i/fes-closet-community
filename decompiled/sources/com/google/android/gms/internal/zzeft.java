package com.google.android.gms.internal;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* loaded from: classes.dex */
final class zzeft {
    static String zza(zzefq zzefqVar, String str) throws SecurityException {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(str);
        zza(zzefqVar, sb, 0);
        return sb.toString();
    }

    private static void zza(zzefq zzefqVar, StringBuilder sb, int i) throws SecurityException {
        boolean zBooleanValue;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        TreeSet treeSet = new TreeSet();
        for (Method method : zzefqVar.getClass().getDeclaredMethods()) {
            map2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                map.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            String strReplaceFirst = ((String) it.next()).replaceFirst("get", "");
            if (strReplaceFirst.endsWith("List") && !strReplaceFirst.endsWith("OrBuilderList")) {
                String strValueOf = String.valueOf(strReplaceFirst.substring(0, 1).toLowerCase());
                String strValueOf2 = String.valueOf(strReplaceFirst.substring(1, strReplaceFirst.length() - 4));
                String strConcat = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
                String strValueOf3 = String.valueOf(strReplaceFirst);
                Method method2 = (Method) map.get(strValueOf3.length() != 0 ? "get".concat(strValueOf3) : new String("get"));
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zzb(sb, i, zzrm(strConcat), zzeev.zza(method2, zzefqVar, new Object[0]));
                }
            }
            String strValueOf4 = String.valueOf(strReplaceFirst);
            if (((Method) map2.get(strValueOf4.length() != 0 ? "set".concat(strValueOf4) : new String("set"))) != null) {
                if (strReplaceFirst.endsWith("Bytes")) {
                    String strValueOf5 = String.valueOf(strReplaceFirst.substring(0, strReplaceFirst.length() - 5));
                    if (!map.containsKey(strValueOf5.length() != 0 ? "get".concat(strValueOf5) : new String("get"))) {
                    }
                }
                String strValueOf6 = String.valueOf(strReplaceFirst.substring(0, 1).toLowerCase());
                String strValueOf7 = String.valueOf(strReplaceFirst.substring(1));
                String strConcat2 = strValueOf7.length() != 0 ? strValueOf6.concat(strValueOf7) : new String(strValueOf6);
                String strValueOf8 = String.valueOf(strReplaceFirst);
                Method method3 = (Method) map.get(strValueOf8.length() != 0 ? "get".concat(strValueOf8) : new String("get"));
                String strValueOf9 = String.valueOf(strReplaceFirst);
                Method method4 = (Method) map.get(strValueOf9.length() != 0 ? "has".concat(strValueOf9) : new String("has"));
                if (method3 != null) {
                    Object objZza = zzeev.zza(method3, zzefqVar, new Object[0]);
                    if (method4 == null) {
                        boolean zEquals = objZza instanceof Boolean ? !((Boolean) objZza).booleanValue() : objZza instanceof Integer ? ((Integer) objZza).intValue() == 0 : objZza instanceof Float ? ((Float) objZza).floatValue() == 0.0f : objZza instanceof Double ? ((Double) objZza).doubleValue() == 0.0d : objZza instanceof String ? objZza.equals("") : objZza instanceof zzeec ? objZza.equals(zzeec.zznbd) : objZza instanceof zzefq ? objZza == ((zzefq) objZza).zzccx() : (objZza instanceof Enum) && ((Enum) objZza).ordinal() == 0;
                        zBooleanValue = !zEquals;
                    } else {
                        zBooleanValue = ((Boolean) zzeev.zza(method4, zzefqVar, new Object[0])).booleanValue();
                    }
                    if (zBooleanValue) {
                        zzb(sb, i, zzrm(strConcat2), objZza);
                    }
                }
            }
        }
        if (zzefqVar instanceof zzefa) {
            Iterator<Map.Entry<FieldDescriptorType, Object>> it2 = ((zzefa) zzefqVar).zzncl.iterator();
            if (it2.hasNext()) {
                ((Map.Entry) it2.next()).getKey();
                throw new NoSuchMethodError();
            }
        }
        if (((zzeev) zzefqVar).zznce != null) {
            ((zzeev) zzefqVar).zznce.zzd(sb, i);
        }
    }

    static final void zzb(StringBuilder sb, int i, String str, Object obj) throws SecurityException {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zzb(sb, i, str, it.next());
            }
            return;
        }
        sb.append('\n');
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"").append(zzege.zzac(zzeec.zzri((String) obj))).append('\"');
            return;
        }
        if (obj instanceof zzeec) {
            sb.append(": \"").append(zzege.zzac((zzeec) obj)).append('\"');
            return;
        }
        if (!(obj instanceof zzeev)) {
            sb.append(": ").append(obj.toString());
            return;
        }
        sb.append(" {");
        zza((zzeev) obj, sb, i + 2);
        sb.append("\n");
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(' ');
        }
        sb.append("}");
    }

    private static final String zzrm(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(cCharAt));
        }
        return sb.toString();
    }
}
