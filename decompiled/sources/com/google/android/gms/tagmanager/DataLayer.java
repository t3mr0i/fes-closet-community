package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class DataLayer {
    public static final String EVENT_KEY = "event";
    public static final Object OBJECT_NOT_PRESENT = new Object();
    private static String[] zzjqg = "gtm.lifetime".toString().split("\\.");
    private static final Pattern zzjqh = Pattern.compile("(\\d+)\\s*([smhd]?)");
    private final ConcurrentHashMap<zzb, Integer> zzjqi;
    private final Map<String, Object> zzjqj;
    private final ReentrantLock zzjqk;
    private final LinkedList<Map<String, Object>> zzjql;
    private final zzc zzjqm;
    private final CountDownLatch zzjqn;

    static final class zza {
        public final Object mValue;
        public final String zzbfe;

        zza(String str, Object obj) {
            this.zzbfe = str;
            this.mValue = obj;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zzaVar = (zza) obj;
            return this.zzbfe.equals(zzaVar.zzbfe) && this.mValue.equals(zzaVar.mValue);
        }

        public final int hashCode() {
            return Arrays.hashCode(new Integer[]{Integer.valueOf(this.zzbfe.hashCode()), Integer.valueOf(this.mValue.hashCode())});
        }

        public final String toString() {
            String str = this.zzbfe;
            String string = this.mValue.toString();
            return new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(string).length()).append("Key: ").append(str).append(" value: ").append(string).toString();
        }
    }

    interface zzb {
        void zzq(Map<String, Object> map);
    }

    interface zzc {
        void zza(zzaq zzaqVar);

        void zza(List<zza> list, long j);

        void zzlk(String str);
    }

    DataLayer() {
        this(new zzao());
    }

    DataLayer(zzc zzcVar) {
        this.zzjqm = zzcVar;
        this.zzjqi = new ConcurrentHashMap<>();
        this.zzjqj = new HashMap();
        this.zzjqk = new ReentrantLock();
        this.zzjql = new LinkedList<>();
        this.zzjqn = new CountDownLatch(1);
        this.zzjqm.zza(new zzap(this));
    }

    public static List<Object> listOf(Object... objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            arrayList.add(obj);
        }
        return arrayList;
    }

    public static Map<String, Object> mapOf(Object... objArr) {
        if (objArr.length % 2 != 0) {
            throw new IllegalArgumentException("expected even number of key-value pairs");
        }
        HashMap map = new HashMap();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= objArr.length) {
                return map;
            }
            if (!(objArr[i2] instanceof String)) {
                String strValueOf = String.valueOf(objArr[i2]);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf).length() + 21).append("key is not a string: ").append(strValueOf).toString());
            }
            map.put((String) objArr[i2], objArr[i2 + 1]);
            i = i2 + 2;
        }
    }

    private final void zza(Map<String, Object> map, String str, Collection<zza> collection) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String str2 = str.length() == 0 ? "" : ".";
            String key = entry.getKey();
            String string = new StringBuilder(String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(key).length()).append(str).append(str2).append(key).toString();
            if (entry.getValue() instanceof Map) {
                zza((Map) entry.getValue(), string, collection);
            } else if (!string.equals("gtm.lifetime")) {
                collection.add(new zza(string, entry.getValue()));
            }
        }
    }

    private final void zzb(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add(null);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                return;
            }
            Object obj = list.get(i2);
            if (obj instanceof List) {
                if (!(list2.get(i2) instanceof List)) {
                    list2.set(i2, new ArrayList());
                }
                zzb((List) obj, (List) list2.get(i2));
            } else if (obj instanceof Map) {
                if (!(list2.get(i2) instanceof Map)) {
                    list2.set(i2, new HashMap());
                }
                zzd((Map) obj, (Map) list2.get(i2));
            } else if (obj != OBJECT_NOT_PRESENT) {
                list2.set(i2, obj);
            }
            i = i2 + 1;
        }
    }

    private final void zzd(Map<String, Object> map, Map<String, Object> map2) {
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj instanceof List) {
                if (!(map2.get(str) instanceof List)) {
                    map2.put(str, new ArrayList());
                }
                zzb((List) obj, (List) map2.get(str));
            } else if (obj instanceof Map) {
                if (!(map2.get(str) instanceof Map)) {
                    map2.put(str, new HashMap());
                }
                zzd((Map) obj, (Map) map2.get(str));
            } else {
                map2.put(str, obj);
            }
        }
    }

    private static Long zzlj(String str) throws NumberFormatException {
        long j;
        Matcher matcher = zzjqh.matcher(str);
        if (!matcher.matches()) {
            String strValueOf = String.valueOf(str);
            zzdj.zzcq(strValueOf.length() != 0 ? "unknown _lifetime: ".concat(strValueOf) : new String("unknown _lifetime: "));
            return null;
        }
        try {
            j = Long.parseLong(matcher.group(1));
        } catch (NumberFormatException e) {
            String strValueOf2 = String.valueOf(str);
            zzdj.zzcr(strValueOf2.length() != 0 ? "illegal number in _lifetime value: ".concat(strValueOf2) : new String("illegal number in _lifetime value: "));
            j = 0;
        }
        if (j <= 0) {
            String strValueOf3 = String.valueOf(str);
            zzdj.zzcq(strValueOf3.length() != 0 ? "non-positive _lifetime: ".concat(strValueOf3) : new String("non-positive _lifetime: "));
            return null;
        }
        String strGroup = matcher.group(2);
        if (strGroup.length() == 0) {
            return Long.valueOf(j);
        }
        switch (strGroup.charAt(0)) {
            case 'd':
                break;
            case 'h':
                break;
            case 'm':
                break;
            case 's':
                break;
            default:
                String strValueOf4 = String.valueOf(str);
                zzdj.zzcr(strValueOf4.length() != 0 ? "unknown units in _lifetime: ".concat(strValueOf4) : new String("unknown units in _lifetime: "));
                break;
        }
        return null;
    }

    static Map<String, Object> zzn(String str, Object obj) {
        HashMap map = new HashMap();
        String[] strArrSplit = str.toString().split("\\.");
        int i = 0;
        HashMap map2 = map;
        while (i < strArrSplit.length - 1) {
            HashMap map3 = new HashMap();
            map2.put(strArrSplit[i], map3);
            i++;
            map2 = map3;
        }
        map2.put(strArrSplit[strArrSplit.length - 1], obj);
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzs(Map<String, Object> map) {
        this.zzjqk.lock();
        try {
            this.zzjql.offer(map);
            if (this.zzjqk.getHoldCount() == 1) {
                int i = 0;
                do {
                    int i2 = i;
                    Map<String, Object> mapPoll = this.zzjql.poll();
                    if (mapPoll != null) {
                        synchronized (this.zzjqj) {
                            for (String str : mapPoll.keySet()) {
                                zzd(zzn(str, mapPoll.get(str)), this.zzjqj);
                            }
                        }
                        Iterator<zzb> it = this.zzjqi.keySet().iterator();
                        while (it.hasNext()) {
                            it.next().zzq(mapPoll);
                        }
                        i = i2 + 1;
                    }
                } while (i <= 500);
                this.zzjql.clear();
                throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
            }
            Object objZzt = zzt(map);
            Long lZzlj = objZzt == null ? null : zzlj(objZzt.toString());
            if (lZzlj != null) {
                ArrayList arrayList = new ArrayList();
                zza(map, "", arrayList);
                this.zzjqm.zza(arrayList, lZzlj.longValue());
            }
        } finally {
            this.zzjqk.unlock();
        }
    }

    private static Object zzt(Map<String, Object> map) {
        Object obj = map;
        for (String str : zzjqg) {
            if (!(obj instanceof Map)) {
                return null;
            }
            obj = ((Map) obj).get(str);
        }
        return obj;
    }

    public Object get(String str) {
        synchronized (this.zzjqj) {
            Object obj = this.zzjqj;
            for (String str2 : str.split("\\.")) {
                if (!(obj instanceof Map)) {
                    return null;
                }
                obj = ((Map) obj).get(str2);
                if (obj == null) {
                    return null;
                }
            }
            return obj;
        }
    }

    public void push(String str, Object obj) throws InterruptedException {
        push(zzn(str, obj));
    }

    public void push(Map<String, Object> map) throws InterruptedException {
        try {
            this.zzjqn.await();
        } catch (InterruptedException e) {
            zzdj.zzcr("DataLayer.push: unexpected InterruptedException");
        }
        zzs(map);
    }

    public void pushEvent(String str, Map<String, Object> map) throws InterruptedException {
        HashMap map2 = new HashMap(map);
        map2.put("event", str);
        push(map2);
    }

    public String toString() {
        String string;
        synchronized (this.zzjqj) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Object> entry : this.zzjqj.entrySet()) {
                sb.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", entry.getKey(), entry.getValue()));
            }
            string = sb.toString();
        }
        return string;
    }

    final void zza(zzb zzbVar) {
        this.zzjqi.put(zzbVar, 0);
    }

    final void zzli(String str) throws InterruptedException {
        push(str, null);
        this.zzjqm.zzlk(str);
    }
}
