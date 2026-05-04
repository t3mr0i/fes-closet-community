package com.google.android.gms.internal;

import com.google.android.gms.internal.zzeeu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class zzees<FieldDescriptorType extends zzeeu<FieldDescriptorType>> {
    private static final zzees zzncb = new zzees(true);
    private boolean zzkff;
    private boolean zznca = false;
    private final zzefw<FieldDescriptorType, Object> zznbz = zzefw.zzgv(16);

    private zzees() {
    }

    private zzees(boolean z) {
        if (this.zzkff) {
            return;
        }
        this.zznbz.zzbht();
        this.zzkff = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.ArrayList, java.util.List] */
    private void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        ?? arrayList;
        if (!fielddescriptortype.zzccu()) {
            zza(fielddescriptortype.zzcct(), obj);
            arrayList = obj;
        } else {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = (ArrayList) arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(fielddescriptortype.zzcct(), obj2);
            }
        }
        if (arrayList instanceof zzefl) {
            this.zznca = true;
        }
        this.zznbz.zza((zzefw<FieldDescriptorType, Object>) fielddescriptortype, (FieldDescriptorType) arrayList);
    }

    private static void zza(zzegr zzegrVar, Object obj) {
        boolean z = false;
        zzeff.zzu(obj);
        switch (zzegrVar.zzcdy()) {
            case INT:
                z = obj instanceof Integer;
                break;
            case LONG:
                z = obj instanceof Long;
                break;
            case FLOAT:
                z = obj instanceof Float;
                break;
            case DOUBLE:
                z = obj instanceof Double;
                break;
            case BOOLEAN:
                z = obj instanceof Boolean;
                break;
            case STRING:
                z = obj instanceof String;
                break;
            case BYTE_STRING:
                if ((obj instanceof zzeec) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
                break;
            case ENUM:
                if ((obj instanceof Integer) || (obj instanceof zzefg)) {
                    z = true;
                    break;
                }
                break;
            case MESSAGE:
                if ((obj instanceof zzefq) || (obj instanceof zzefl)) {
                    z = true;
                    break;
                }
                break;
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public static <T extends zzeeu<T>> zzees<T> zzccs() {
        return new zzees<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzees zzeesVar = new zzees();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.zznbz.zzcdi()) {
                break;
            }
            Map.Entry<K, Object> entryZzgw = this.zznbz.zzgw(i2);
            zzeesVar.zza((zzees) entryZzgw.getKey(), entryZzgw.getValue());
            i = i2 + 1;
        }
        Iterator it = this.zznbz.zzcdj().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            zzeesVar.zza((zzees) entry.getKey(), entry.getValue());
        }
        zzeesVar.zznca = this.zznca;
        return zzeesVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzees) {
            return this.zznbz.equals(((zzees) obj).zznbz);
        }
        return false;
    }

    public final int hashCode() {
        return this.zznbz.hashCode();
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        return this.zznca ? new zzefo(this.zznbz.entrySet().iterator()) : this.zznbz.entrySet().iterator();
    }
}
