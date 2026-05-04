package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class zzbdw extends zzbdo {
    public static final Parcelable.Creator<zzbdw> CREATOR = new zzbdx();
    private final String mClassName;
    private final int zzdxr;
    private final zzbdr zzfwx;
    private final Parcel zzfxe;
    private final int zzfxf = 2;
    private int zzfxg;
    private int zzfxh;

    zzbdw(int i, Parcel parcel, zzbdr zzbdrVar) {
        this.zzdxr = i;
        this.zzfxe = (Parcel) com.google.android.gms.common.internal.zzbp.zzu(parcel);
        this.zzfwx = zzbdrVar;
        if (this.zzfwx == null) {
            this.mClassName = null;
        } else {
            this.mClassName = this.zzfwx.zzakw();
        }
        this.zzfxg = 2;
    }

    private static void zza(StringBuilder sb, int i, Object obj) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append(obj);
                return;
            case 7:
                sb.append("\"").append(com.google.android.gms.common.util.zzn.zzgl(obj.toString())).append("\"");
                return;
            case 8:
                sb.append("\"").append(com.google.android.gms.common.util.zzb.encode((byte[]) obj)).append("\"");
                return;
            case 9:
                sb.append("\"").append(com.google.android.gms.common.util.zzb.zzj((byte[]) obj));
                sb.append("\"");
                return;
            case 10:
                com.google.android.gms.common.util.zzo.zza(sb, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException(new StringBuilder(26).append("Unknown type = ").append(i).toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void zza(StringBuilder sb, zzbdm<?, ?> zzbdmVar, Parcel parcel, int i) {
        double[] dArrCreateDoubleArray = null;
        BigInteger[] bigIntegerArr = null;
        int i2 = 0;
        if (!zzbdmVar.zzfws) {
            switch (zzbdmVar.zzfwr) {
                case 0:
                    sb.append(zzbcl.zzg(parcel, i));
                    return;
                case 1:
                    sb.append(zzbcl.zzk(parcel, i));
                    return;
                case 2:
                    sb.append(zzbcl.zzi(parcel, i));
                    return;
                case 3:
                    sb.append(zzbcl.zzl(parcel, i));
                    return;
                case 4:
                    sb.append(zzbcl.zzn(parcel, i));
                    return;
                case 5:
                    sb.append(zzbcl.zzp(parcel, i));
                    return;
                case 6:
                    sb.append(zzbcl.zzc(parcel, i));
                    return;
                case 7:
                    sb.append("\"").append(com.google.android.gms.common.util.zzn.zzgl(zzbcl.zzq(parcel, i))).append("\"");
                    return;
                case 8:
                    sb.append("\"").append(com.google.android.gms.common.util.zzb.encode(zzbcl.zzt(parcel, i))).append("\"");
                    return;
                case 9:
                    sb.append("\"").append(com.google.android.gms.common.util.zzb.zzj(zzbcl.zzt(parcel, i)));
                    sb.append("\"");
                    return;
                case 10:
                    Bundle bundleZzs = zzbcl.zzs(parcel, i);
                    Set<String> setKeySet = bundleZzs.keySet();
                    setKeySet.size();
                    sb.append("{");
                    Object[] objArr = true;
                    for (String str : setKeySet) {
                        if (objArr == false) {
                            sb.append(",");
                        }
                        sb.append("\"").append(str).append("\"");
                        sb.append(":");
                        sb.append("\"").append(com.google.android.gms.common.util.zzn.zzgl(bundleZzs.getString(str))).append("\"");
                        objArr = false;
                    }
                    sb.append("}");
                    return;
                case 11:
                    Parcel parcelZzad = zzbcl.zzad(parcel, i);
                    parcelZzad.setDataPosition(0);
                    zza(sb, zzbdmVar.zzaku(), parcelZzad);
                    return;
                default:
                    throw new IllegalStateException("Unknown field type out");
            }
        }
        sb.append("[");
        switch (zzbdmVar.zzfwr) {
            case 0:
                int[] iArrZzw = zzbcl.zzw(parcel, i);
                int length = iArrZzw.length;
                while (i2 < length) {
                    if (i2 != 0) {
                        sb.append(",");
                    }
                    sb.append(Integer.toString(iArrZzw[i2]));
                    i2++;
                }
                break;
            case 1:
                int iZza = zzbcl.zza(parcel, i);
                int iDataPosition = parcel.dataPosition();
                if (iZza != 0) {
                    int i3 = parcel.readInt();
                    bigIntegerArr = new BigInteger[i3];
                    while (i2 < i3) {
                        bigIntegerArr[i2] = new BigInteger(parcel.createByteArray());
                        i2++;
                    }
                    parcel.setDataPosition(iZza + iDataPosition);
                }
                com.google.android.gms.common.util.zza.zza(sb, bigIntegerArr);
                break;
            case 2:
                com.google.android.gms.common.util.zza.zza(sb, zzbcl.zzx(parcel, i));
                break;
            case 3:
                com.google.android.gms.common.util.zza.zza(sb, zzbcl.zzy(parcel, i));
                break;
            case 4:
                int iZza2 = zzbcl.zza(parcel, i);
                int iDataPosition2 = parcel.dataPosition();
                if (iZza2 != 0) {
                    dArrCreateDoubleArray = parcel.createDoubleArray();
                    parcel.setDataPosition(iZza2 + iDataPosition2);
                }
                com.google.android.gms.common.util.zza.zza(sb, dArrCreateDoubleArray);
                break;
            case 5:
                com.google.android.gms.common.util.zza.zza(sb, zzbcl.zzz(parcel, i));
                break;
            case 6:
                com.google.android.gms.common.util.zza.zza(sb, zzbcl.zzv(parcel, i));
                break;
            case 7:
                com.google.android.gms.common.util.zza.zza(sb, zzbcl.zzaa(parcel, i));
                break;
            case 8:
            case 9:
            case 10:
                throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
            case 11:
                Parcel[] parcelArrZzae = zzbcl.zzae(parcel, i);
                int length2 = parcelArrZzae.length;
                for (int i4 = 0; i4 < length2; i4++) {
                    if (i4 > 0) {
                        sb.append(",");
                    }
                    parcelArrZzae[i4].setDataPosition(0);
                    zza(sb, zzbdmVar.zzaku(), parcelArrZzae[i4]);
                }
                break;
            default:
                throw new IllegalStateException("Unknown field type out.");
        }
        sb.append("]");
    }

    private final void zza(StringBuilder sb, Map<String, zzbdm<?, ?>> map, Parcel parcel) {
        SparseArray sparseArray = new SparseArray();
        for (Map.Entry<String, zzbdm<?, ?>> entry : map.entrySet()) {
            sparseArray.put(entry.getValue().zzfwu, entry);
        }
        sb.append('{');
        int iZzd = zzbcl.zzd(parcel);
        boolean z = false;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            Map.Entry entry2 = (Map.Entry) sparseArray.get(65535 & i);
            if (entry2 != null) {
                if (z) {
                    sb.append(",");
                }
                String str = (String) entry2.getKey();
                zzbdm<?, ?> zzbdmVar = (zzbdm) entry2.getValue();
                sb.append("\"").append(str).append("\":");
                if (zzbdmVar.zzakt()) {
                    switch (zzbdmVar.zzfwr) {
                        case 0:
                            zzb(sb, zzbdmVar, zza(zzbdmVar, Integer.valueOf(zzbcl.zzg(parcel, i))));
                            break;
                        case 1:
                            zzb(sb, zzbdmVar, zza(zzbdmVar, zzbcl.zzk(parcel, i)));
                            break;
                        case 2:
                            zzb(sb, zzbdmVar, zza(zzbdmVar, Long.valueOf(zzbcl.zzi(parcel, i))));
                            break;
                        case 3:
                            zzb(sb, zzbdmVar, zza(zzbdmVar, Float.valueOf(zzbcl.zzl(parcel, i))));
                            break;
                        case 4:
                            zzb(sb, zzbdmVar, zza(zzbdmVar, Double.valueOf(zzbcl.zzn(parcel, i))));
                            break;
                        case 5:
                            zzb(sb, zzbdmVar, zza(zzbdmVar, zzbcl.zzp(parcel, i)));
                            break;
                        case 6:
                            zzb(sb, zzbdmVar, zza(zzbdmVar, Boolean.valueOf(zzbcl.zzc(parcel, i))));
                            break;
                        case 7:
                            zzb(sb, zzbdmVar, zza(zzbdmVar, zzbcl.zzq(parcel, i)));
                            break;
                        case 8:
                        case 9:
                            zzb(sb, zzbdmVar, zza(zzbdmVar, zzbcl.zzt(parcel, i)));
                            break;
                        case 10:
                            zzb(sb, zzbdmVar, zza(zzbdmVar, zzl(zzbcl.zzs(parcel, i))));
                            break;
                        case 11:
                            throw new IllegalArgumentException("Method does not accept concrete type.");
                        default:
                            throw new IllegalArgumentException(new StringBuilder(36).append("Unknown field out type = ").append(zzbdmVar.zzfwr).toString());
                    }
                } else {
                    zza(sb, zzbdmVar, parcel, i);
                }
                z = true;
            }
        }
        if (parcel.dataPosition() != iZzd) {
            throw new zzbcm(new StringBuilder(37).append("Overread allowed size end=").append(iZzd).toString(), parcel);
        }
        sb.append('}');
    }

    private Parcel zzaky() {
        switch (this.zzfxg) {
            case 0:
                this.zzfxh = zzbcn.zze(this.zzfxe);
            case 1:
                zzbcn.zzai(this.zzfxe, this.zzfxh);
                this.zzfxg = 2;
                break;
        }
        return this.zzfxe;
    }

    private final void zzb(StringBuilder sb, zzbdm<?, ?> zzbdmVar, Object obj) {
        if (!zzbdmVar.zzfwq) {
            zza(sb, zzbdmVar.zzfwp, obj);
            return;
        }
        ArrayList arrayList = (ArrayList) obj;
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            zza(sb, zzbdmVar.zzfwp, arrayList.get(i));
        }
        sb.append("]");
    }

    private static HashMap<String, String> zzl(Bundle bundle) {
        HashMap<String, String> map = new HashMap<>();
        for (String str : bundle.keySet()) {
            map.put(str, bundle.getString(str));
        }
        return map;
    }

    @Override // com.google.android.gms.internal.zzbdl
    public String toString() {
        com.google.android.gms.common.internal.zzbp.zzb(this.zzfwx, "Cannot convert to JSON on client side.");
        Parcel parcelZzaky = zzaky();
        parcelZzaky.setDataPosition(0);
        StringBuilder sb = new StringBuilder(100);
        zza(sb, this.zzfwx.zzgk(this.mClassName), parcelZzaky);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        zzbdr zzbdrVar;
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxr);
        zzbcn.zza(parcel, 2, zzaky(), false);
        switch (this.zzfxf) {
            case 0:
                zzbdrVar = null;
                break;
            case 1:
                zzbdrVar = this.zzfwx;
                break;
            case 2:
                zzbdrVar = this.zzfwx;
                break;
            default:
                throw new IllegalStateException(new StringBuilder(34).append("Invalid creation type: ").append(this.zzfxf).toString());
        }
        zzbcn.zza(parcel, 3, (Parcelable) zzbdrVar, i, false);
        zzbcn.zzai(parcel, iZze);
    }

    @Override // com.google.android.gms.internal.zzbdo, com.google.android.gms.internal.zzbdl
    public final Object zzgi(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    @Override // com.google.android.gms.internal.zzbdo, com.google.android.gms.internal.zzbdl
    public final boolean zzgj(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    @Override // com.google.android.gms.internal.zzbdl
    public final Map<String, zzbdm<?, ?>> zzzz() {
        if (this.zzfwx == null) {
            return null;
        }
        return this.zzfwx.zzgk(this.mClassName);
    }
}
