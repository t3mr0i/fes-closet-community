package com.google.android.gms.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes.dex */
final class zzcau extends zzcdu {
    zzcau(zzccw zzccwVar) {
        super(zzccwVar);
    }

    private final Boolean zza(double d, zzcga zzcgaVar) {
        try {
            return zza(new BigDecimal(d), zzcgaVar, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private final Boolean zza(long j, zzcga zzcgaVar) {
        try {
            return zza(new BigDecimal(j), zzcgaVar, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Boolean zza(zzcfy zzcfyVar, zzcgh zzcghVar, long j) throws IllegalStateException {
        Boolean boolZza;
        if (zzcfyVar.zzixq != null) {
            Boolean boolZza2 = zza(j, zzcfyVar.zzixq);
            if (boolZza2 == null) {
                return null;
            }
            if (!boolZza2.booleanValue()) {
                return false;
            }
        }
        HashSet hashSet = new HashSet();
        for (zzcfz zzcfzVar : zzcfyVar.zzixo) {
            if (TextUtils.isEmpty(zzcfzVar.zzixv)) {
                zzaum().zzayg().zzj("null or empty param name in filter. event", zzauh().zzjc(zzcghVar.name));
                return null;
            }
            hashSet.add(zzcfzVar.zzixv);
        }
        ArrayMap arrayMap = new ArrayMap();
        for (zzcgi zzcgiVar : zzcghVar.zziyw) {
            if (hashSet.contains(zzcgiVar.name)) {
                if (zzcgiVar.zziza != null) {
                    arrayMap.put(zzcgiVar.name, zzcgiVar.zziza);
                } else if (zzcgiVar.zzixb != null) {
                    arrayMap.put(zzcgiVar.name, zzcgiVar.zzixb);
                } else {
                    if (zzcgiVar.zzfwn == null) {
                        zzaum().zzayg().zze("Unknown value for param. event, param", zzauh().zzjc(zzcghVar.name), zzauh().zzjd(zzcgiVar.name));
                        return null;
                    }
                    arrayMap.put(zzcgiVar.name, zzcgiVar.zzfwn);
                }
            }
        }
        for (zzcfz zzcfzVar2 : zzcfyVar.zzixo) {
            boolean zEquals = Boolean.TRUE.equals(zzcfzVar2.zzixu);
            String str = zzcfzVar2.zzixv;
            if (TextUtils.isEmpty(str)) {
                zzaum().zzayg().zzj("Event has empty param name. event", zzauh().zzjc(zzcghVar.name));
                return null;
            }
            V v = arrayMap.get(str);
            if (v instanceof Long) {
                if (zzcfzVar2.zzixt == null) {
                    zzaum().zzayg().zze("No number filter for long param. event, param", zzauh().zzjc(zzcghVar.name), zzauh().zzjd(str));
                    return null;
                }
                Boolean boolZza3 = zza(((Long) v).longValue(), zzcfzVar2.zzixt);
                if (boolZza3 == null) {
                    return null;
                }
                if ((!boolZza3.booleanValue()) ^ zEquals) {
                    return false;
                }
            } else if (v instanceof Double) {
                if (zzcfzVar2.zzixt == null) {
                    zzaum().zzayg().zze("No number filter for double param. event, param", zzauh().zzjc(zzcghVar.name), zzauh().zzjd(str));
                    return null;
                }
                Boolean boolZza4 = zza(((Double) v).doubleValue(), zzcfzVar2.zzixt);
                if (boolZza4 == null) {
                    return null;
                }
                if ((!boolZza4.booleanValue()) ^ zEquals) {
                    return false;
                }
            } else {
                if (!(v instanceof String)) {
                    if (v == 0) {
                        zzaum().zzayk().zze("Missing param for filter. event, param", zzauh().zzjc(zzcghVar.name), zzauh().zzjd(str));
                        return false;
                    }
                    zzaum().zzayg().zze("Unknown param type. event, param", zzauh().zzjc(zzcghVar.name), zzauh().zzjd(str));
                    return null;
                }
                if (zzcfzVar2.zzixs != null) {
                    boolZza = zza((String) v, zzcfzVar2.zzixs);
                } else {
                    if (zzcfzVar2.zzixt == null) {
                        zzaum().zzayg().zze("No filter for String param. event, param", zzauh().zzjc(zzcghVar.name), zzauh().zzjd(str));
                        return null;
                    }
                    if (!zzcfw.zzkf((String) v)) {
                        zzaum().zzayg().zze("Invalid param value for number filter. event, param", zzauh().zzjc(zzcghVar.name), zzauh().zzjd(str));
                        return null;
                    }
                    boolZza = zza((String) v, zzcfzVar2.zzixt);
                }
                if (boolZza == null) {
                    return null;
                }
                if ((!boolZza.booleanValue()) ^ zEquals) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private final Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 1) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                try {
                    break;
                } catch (PatternSyntaxException e) {
                    zzaum().zzayg().zzj("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
        }
        return null;
    }

    private final Boolean zza(String str, zzcga zzcgaVar) {
        if (!zzcfw.zzkf(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzcgaVar, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private final Boolean zza(String str, zzcgc zzcgcVar) {
        List<String> arrayList;
        com.google.android.gms.common.internal.zzbp.zzu(zzcgcVar);
        if (str == null || zzcgcVar.zziye == null || zzcgcVar.zziye.intValue() == 0) {
            return null;
        }
        if (zzcgcVar.zziye.intValue() == 6) {
            if (zzcgcVar.zziyh == null || zzcgcVar.zziyh.length == 0) {
                return null;
            }
        } else if (zzcgcVar.zziyf == null) {
            return null;
        }
        int iIntValue = zzcgcVar.zziye.intValue();
        boolean z = zzcgcVar.zziyg != null && zzcgcVar.zziyg.booleanValue();
        String upperCase = (z || iIntValue == 1 || iIntValue == 6) ? zzcgcVar.zziyf : zzcgcVar.zziyf.toUpperCase(Locale.ENGLISH);
        if (zzcgcVar.zziyh == null) {
            arrayList = null;
        } else {
            String[] strArr = zzcgcVar.zziyh;
            if (z) {
                arrayList = Arrays.asList(strArr);
            } else {
                arrayList = new ArrayList<>();
                for (String str2 : strArr) {
                    arrayList.add(str2.toUpperCase(Locale.ENGLISH));
                }
            }
        }
        return zza(str, iIntValue, z, upperCase, arrayList, iIntValue == 1 ? upperCase : null);
    }

    private static Boolean zza(BigDecimal bigDecimal, zzcga zzcgaVar, double d) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        com.google.android.gms.common.internal.zzbp.zzu(zzcgaVar);
        if (zzcgaVar.zzixw == null || zzcgaVar.zzixw.intValue() == 0) {
            return null;
        }
        if (zzcgaVar.zzixw.intValue() == 4) {
            if (zzcgaVar.zzixz == null || zzcgaVar.zziya == null) {
                return null;
            }
        } else if (zzcgaVar.zzixy == null) {
            return null;
        }
        int iIntValue = zzcgaVar.zzixw.intValue();
        if (zzcgaVar.zzixw.intValue() == 4) {
            if (!zzcfw.zzkf(zzcgaVar.zzixz) || !zzcfw.zzkf(zzcgaVar.zziya)) {
                return null;
            }
            try {
                bigDecimal4 = new BigDecimal(zzcgaVar.zzixz);
                bigDecimal3 = new BigDecimal(zzcgaVar.zziya);
                bigDecimal2 = null;
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            if (!zzcfw.zzkf(zzcgaVar.zzixy)) {
                return null;
            }
            try {
                bigDecimal2 = new BigDecimal(zzcgaVar.zzixy);
                bigDecimal3 = null;
                bigDecimal4 = null;
            } catch (NumberFormatException e2) {
                return null;
            }
        }
        if (iIntValue != 4) {
            if (bigDecimal2 != null) {
            }
            return null;
        }
        if (bigDecimal4 == null) {
            return null;
        }
        switch (iIntValue) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                if (d == 0.0d) {
                    break;
                } else {
                    break;
                }
                break;
            case 4:
                break;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WorkerThread
    final zzcgg[] zza(String str, zzcgh[] zzcghVarArr, zzcgm[] zzcgmVarArr) throws IllegalStateException {
        Map<Integer, List<zzcgb>> map;
        Boolean boolZza;
        zzcbg zzcbgVarZzaxz;
        Map<Integer, List<zzcfy>> map2;
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        HashSet hashSet = new HashSet();
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        Map<Integer, zzcgl> mapZziz = zzaug().zziz(str);
        if (mapZziz != null) {
            Iterator<Integer> it = mapZziz.keySet().iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                zzcgl zzcglVar = mapZziz.get(Integer.valueOf(iIntValue));
                BitSet bitSet = (BitSet) arrayMap2.get(Integer.valueOf(iIntValue));
                BitSet bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(iIntValue));
                if (bitSet == null) {
                    bitSet = new BitSet();
                    arrayMap2.put(Integer.valueOf(iIntValue), bitSet);
                    bitSet2 = new BitSet();
                    arrayMap3.put(Integer.valueOf(iIntValue), bitSet2);
                }
                for (int i = 0; i < (zzcglVar.zzjaf.length << 6); i++) {
                    if (zzcfw.zza(zzcglVar.zzjaf, i)) {
                        zzaum().zzayk().zze("Filter already evaluated. audience ID, filter ID", Integer.valueOf(iIntValue), Integer.valueOf(i));
                        bitSet2.set(i);
                        if (zzcfw.zza(zzcglVar.zzjag, i)) {
                            bitSet.set(i);
                        }
                    }
                }
                zzcgg zzcggVar = new zzcgg();
                arrayMap.put(Integer.valueOf(iIntValue), zzcggVar);
                zzcggVar.zziyu = false;
                zzcggVar.zziyt = zzcglVar;
                zzcggVar.zziys = new zzcgl();
                zzcggVar.zziys.zzjag = zzcfw.zza(bitSet);
                zzcggVar.zziys.zzjaf = zzcfw.zza(bitSet2);
            }
        }
        if (zzcghVarArr != null) {
            ArrayMap arrayMap4 = new ArrayMap();
            int length = zzcghVarArr.length;
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 >= length) {
                    break;
                }
                zzcgh zzcghVar = zzcghVarArr[i3];
                zzcbg zzcbgVarZzaf = zzaug().zzaf(str, zzcghVar.name);
                if (zzcbgVarZzaf == null) {
                    zzaum().zzayg().zze("Event aggregate wasn't created during raw event logging. appId, event", zzcbw.zzjf(str), zzauh().zzjc(zzcghVar.name));
                    zzcbgVarZzaxz = new zzcbg(str, zzcghVar.name, 1L, 1L, zzcghVar.zziyx.longValue());
                } else {
                    zzcbgVarZzaxz = zzcbgVarZzaf.zzaxz();
                }
                zzaug().zza(zzcbgVarZzaxz);
                long j = zzcbgVarZzaxz.zzink;
                Map<Integer, List<zzcfy>> map3 = (Map) arrayMap4.get(zzcghVar.name);
                if (map3 == null) {
                    Map<Integer, List<zzcfy>> mapZzak = zzaug().zzak(str, zzcghVar.name);
                    if (mapZzak == null) {
                        mapZzak = new ArrayMap<>();
                    }
                    arrayMap4.put(zzcghVar.name, mapZzak);
                    map2 = mapZzak;
                } else {
                    map2 = map3;
                }
                Iterator<Integer> it2 = map2.keySet().iterator();
                while (it2.hasNext()) {
                    int iIntValue2 = it2.next().intValue();
                    if (hashSet.contains(Integer.valueOf(iIntValue2))) {
                        zzaum().zzayk().zzj("Skipping failed audience ID", Integer.valueOf(iIntValue2));
                    } else {
                        zzcgg zzcggVar2 = (zzcgg) arrayMap.get(Integer.valueOf(iIntValue2));
                        BitSet bitSet3 = (BitSet) arrayMap2.get(Integer.valueOf(iIntValue2));
                        BitSet bitSet4 = (BitSet) arrayMap3.get(Integer.valueOf(iIntValue2));
                        if (zzcggVar2 == null) {
                            zzcgg zzcggVar3 = new zzcgg();
                            arrayMap.put(Integer.valueOf(iIntValue2), zzcggVar3);
                            zzcggVar3.zziyu = true;
                            bitSet3 = new BitSet();
                            arrayMap2.put(Integer.valueOf(iIntValue2), bitSet3);
                            bitSet4 = new BitSet();
                            arrayMap3.put(Integer.valueOf(iIntValue2), bitSet4);
                        }
                        for (zzcfy zzcfyVar : map2.get(Integer.valueOf(iIntValue2))) {
                            if (zzaum().zzad(2)) {
                                zzaum().zzayk().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(iIntValue2), zzcfyVar.zzixm, zzauh().zzjc(zzcfyVar.zzixn));
                                zzaum().zzayk().zzj("Filter definition", zzauh().zza(zzcfyVar));
                            }
                            if (zzcfyVar.zzixm == null || zzcfyVar.zzixm.intValue() > 256) {
                                zzaum().zzayg().zze("Invalid event filter ID. appId, id", zzcbw.zzjf(str), String.valueOf(zzcfyVar.zzixm));
                            } else if (bitSet3.get(zzcfyVar.zzixm.intValue())) {
                                zzaum().zzayk().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(iIntValue2), zzcfyVar.zzixm);
                            } else {
                                Boolean boolZza2 = zza(zzcfyVar, zzcghVar, j);
                                zzaum().zzayk().zzj("Event filter result", boolZza2 == null ? "null" : boolZza2);
                                if (boolZza2 == null) {
                                    hashSet.add(Integer.valueOf(iIntValue2));
                                } else {
                                    bitSet4.set(zzcfyVar.zzixm.intValue());
                                    if (boolZza2.booleanValue()) {
                                        bitSet3.set(zzcfyVar.zzixm.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
                i2 = i3 + 1;
            }
        }
        if (zzcgmVarArr != null) {
            ArrayMap arrayMap5 = new ArrayMap();
            for (zzcgm zzcgmVar : zzcgmVarArr) {
                Map<Integer, List<zzcgb>> map4 = (Map) arrayMap5.get(zzcgmVar.name);
                if (map4 == null) {
                    Map<Integer, List<zzcgb>> mapZzal = zzaug().zzal(str, zzcgmVar.name);
                    if (mapZzal == null) {
                        mapZzal = new ArrayMap<>();
                    }
                    arrayMap5.put(zzcgmVar.name, mapZzal);
                    map = mapZzal;
                } else {
                    map = map4;
                }
                Iterator<Integer> it3 = map.keySet().iterator();
                while (it3.hasNext()) {
                    int iIntValue3 = it3.next().intValue();
                    if (hashSet.contains(Integer.valueOf(iIntValue3))) {
                        zzaum().zzayk().zzj("Skipping failed audience ID", Integer.valueOf(iIntValue3));
                    } else {
                        zzcgg zzcggVar4 = (zzcgg) arrayMap.get(Integer.valueOf(iIntValue3));
                        BitSet bitSet5 = (BitSet) arrayMap2.get(Integer.valueOf(iIntValue3));
                        BitSet bitSet6 = (BitSet) arrayMap3.get(Integer.valueOf(iIntValue3));
                        if (zzcggVar4 == null) {
                            zzcgg zzcggVar5 = new zzcgg();
                            arrayMap.put(Integer.valueOf(iIntValue3), zzcggVar5);
                            zzcggVar5.zziyu = true;
                            bitSet5 = new BitSet();
                            arrayMap2.put(Integer.valueOf(iIntValue3), bitSet5);
                            bitSet6 = new BitSet();
                            arrayMap3.put(Integer.valueOf(iIntValue3), bitSet6);
                        }
                        for (zzcgb zzcgbVar : map.get(Integer.valueOf(iIntValue3))) {
                            if (zzaum().zzad(2)) {
                                zzaum().zzayk().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(iIntValue3), zzcgbVar.zzixm, zzauh().zzje(zzcgbVar.zziyc));
                                zzaum().zzayk().zzj("Filter definition", zzauh().zza(zzcgbVar));
                            }
                            if (zzcgbVar.zzixm == null || zzcgbVar.zzixm.intValue() > 256) {
                                zzaum().zzayg().zze("Invalid property filter ID. appId, id", zzcbw.zzjf(str), String.valueOf(zzcgbVar.zzixm));
                                hashSet.add(Integer.valueOf(iIntValue3));
                                break;
                            }
                            if (bitSet5.get(zzcgbVar.zzixm.intValue())) {
                                zzaum().zzayk().zze("Property filter already evaluated true. audience ID, filter ID", Integer.valueOf(iIntValue3), zzcgbVar.zzixm);
                            } else {
                                zzcfz zzcfzVar = zzcgbVar.zziyd;
                                if (zzcfzVar == null) {
                                    zzaum().zzayg().zzj("Missing property filter. property", zzauh().zzje(zzcgmVar.name));
                                    boolZza = null;
                                } else {
                                    boolean zEquals = Boolean.TRUE.equals(zzcfzVar.zzixu);
                                    if (zzcgmVar.zziza != null) {
                                        if (zzcfzVar.zzixt == null) {
                                            zzaum().zzayg().zzj("No number filter for long property. property", zzauh().zzje(zzcgmVar.name));
                                            boolZza = null;
                                        } else {
                                            boolZza = zza(zza(zzcgmVar.zziza.longValue(), zzcfzVar.zzixt), zEquals);
                                        }
                                    } else if (zzcgmVar.zzixb != null) {
                                        if (zzcfzVar.zzixt == null) {
                                            zzaum().zzayg().zzj("No number filter for double property. property", zzauh().zzje(zzcgmVar.name));
                                            boolZza = null;
                                        } else {
                                            boolZza = zza(zza(zzcgmVar.zzixb.doubleValue(), zzcfzVar.zzixt), zEquals);
                                        }
                                    } else if (zzcgmVar.zzfwn == null) {
                                        zzaum().zzayg().zzj("User property has no value, property", zzauh().zzje(zzcgmVar.name));
                                        boolZza = null;
                                    } else if (zzcfzVar.zzixs == null) {
                                        if (zzcfzVar.zzixt == null) {
                                            zzaum().zzayg().zzj("No string or number filter defined. property", zzauh().zzje(zzcgmVar.name));
                                        } else if (zzcfw.zzkf(zzcgmVar.zzfwn)) {
                                            boolZza = zza(zza(zzcgmVar.zzfwn, zzcfzVar.zzixt), zEquals);
                                        } else {
                                            zzaum().zzayg().zze("Invalid user property value for Numeric number filter. property, value", zzauh().zzje(zzcgmVar.name), zzcgmVar.zzfwn);
                                        }
                                        boolZza = null;
                                    } else {
                                        boolZza = zza(zza(zzcgmVar.zzfwn, zzcfzVar.zzixs), zEquals);
                                    }
                                }
                                zzaum().zzayk().zzj("Property filter result", boolZza == null ? "null" : boolZza);
                                if (boolZza == null) {
                                    hashSet.add(Integer.valueOf(iIntValue3));
                                } else {
                                    bitSet6.set(zzcgbVar.zzixm.intValue());
                                    if (boolZza.booleanValue()) {
                                        bitSet5.set(zzcgbVar.zzixm.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        zzcgg[] zzcggVarArr = new zzcgg[arrayMap2.size()];
        Iterator it4 = arrayMap2.keySet().iterator();
        int i4 = 0;
        while (it4.hasNext()) {
            int iIntValue4 = ((Integer) it4.next()).intValue();
            if (!hashSet.contains(Integer.valueOf(iIntValue4))) {
                zzcgg zzcggVar6 = (zzcgg) arrayMap.get(Integer.valueOf(iIntValue4));
                zzcgg zzcggVar7 = zzcggVar6 == null ? new zzcgg() : zzcggVar6;
                int i5 = i4 + 1;
                zzcggVarArr[i4] = zzcggVar7;
                zzcggVar7.zzixi = Integer.valueOf(iIntValue4);
                zzcggVar7.zziys = new zzcgl();
                zzcggVar7.zziys.zzjag = zzcfw.zza((BitSet) arrayMap2.get(Integer.valueOf(iIntValue4)));
                zzcggVar7.zziys.zzjaf = zzcfw.zza((BitSet) arrayMap3.get(Integer.valueOf(iIntValue4)));
                zzcay zzcayVarZzaug = zzaug();
                zzcgl zzcglVar2 = zzcggVar7.zziys;
                zzcayVarZzaug.zzwk();
                zzcayVarZzaug.zzuj();
                com.google.android.gms.common.internal.zzbp.zzgg(str);
                com.google.android.gms.common.internal.zzbp.zzu(zzcglVar2);
                try {
                    byte[] bArr = new byte[zzcglVar2.zzhi()];
                    zzegy zzegyVarZzi = zzegy.zzi(bArr, 0, bArr.length);
                    zzcglVar2.zza(zzegyVarZzi);
                    zzegyVarZzi.zzccm();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", str);
                    contentValues.put("audience_id", Integer.valueOf(iIntValue4));
                    contentValues.put("current_results", bArr);
                    try {
                        if (zzcayVarZzaug.getWritableDatabase().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                            zzcayVarZzaug.zzaum().zzaye().zzj("Failed to insert filter results (got -1). appId", zzcbw.zzjf(str));
                        }
                        i4 = i5;
                    } catch (SQLiteException e) {
                        zzcayVarZzaug.zzaum().zzaye().zze("Error storing filter results. appId", zzcbw.zzjf(str), e);
                        i4 = i5;
                    }
                } catch (IOException e2) {
                    zzcayVarZzaug.zzaum().zzaye().zze("Configuration loss. Failed to serialize filter results. appId", zzcbw.zzjf(str), e2);
                    i4 = i5;
                }
            }
        }
        return (zzcgg[]) Arrays.copyOf(zzcggVarArr, i4);
    }

    @Override // com.google.android.gms.internal.zzcdu
    protected final void zzuk() {
    }
}
