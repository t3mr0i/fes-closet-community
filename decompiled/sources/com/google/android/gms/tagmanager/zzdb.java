package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzdbq;
import com.google.android.gms.internal.zzdbs;
import com.google.android.gms.internal.zzdbt;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class zzdb {
    private static Object zzaf(Object obj) throws JSONException {
        if (obj instanceof JSONArray) {
            throw new RuntimeException("JSONArrays are not supported");
        }
        if (JSONObject.NULL.equals(obj)) {
            throw new RuntimeException("JSON nulls are not supported");
        }
        if (!(obj instanceof JSONObject)) {
            return obj;
        }
        JSONObject jSONObject = (JSONObject) obj;
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, zzaf(jSONObject.get(next)));
        }
        return map;
    }

    public static zzdbs zzlu(String str) throws JSONException {
        com.google.android.gms.internal.zzbp zzbpVarZzah = zzgk.zzah(zzaf(new JSONObject(str)));
        zzdbt zzdbtVarZzbhy = zzdbs.zzbhy();
        for (int i = 0; i < zzbpVarZzah.zzya.length; i++) {
            zzdbtVarZzbhy.zzc(zzdbq.zzbhw().zzb(com.google.android.gms.internal.zzbe.INSTANCE_NAME.toString(), zzbpVarZzah.zzya[i]).zzb(com.google.android.gms.internal.zzbe.FUNCTION.toString(), zzgk.zzme(zzt.zzbcm())).zzb(zzt.zzbcn(), zzbpVarZzah.zzyb[i]).zzbhx());
        }
        return zzdbtVarZzbhy.zzbia();
    }
}
