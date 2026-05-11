package com.google.android.gms.internal;

import com.google.android.gms.internal.zzbf;
import com.google.android.gms.tagmanager.zzdj;
import com.google.android.gms.tagmanager.zzgk;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final class zzdbo {
    private static zzbp zza(int i, zzbl zzblVar, zzbp[] zzbpVarArr, Set<Integer> set) throws zzdbw {
        if (set.contains(Integer.valueOf(i))) {
            String strValueOf = String.valueOf(set);
            zzmw(new StringBuilder(String.valueOf(strValueOf).length() + 90).append("Value cycle detected.  Current value reference: ").append(i).append(".  Previous value references: ").append(strValueOf).append(".").toString());
        }
        zzbp zzbpVar = (zzbp) zza(zzblVar.zzwk, i, "values");
        if (zzbpVarArr[i] != null) {
            return zzbpVarArr[i];
        }
        zzbp zzbpVarZzj = null;
        set.add(Integer.valueOf(i));
        switch (zzbpVar.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                zzbpVarZzj = zzbpVar;
                break;
            case 2:
                zzbf.zza zzaVarZzk = zzk(zzbpVar);
                zzbpVarZzj = zzj(zzbpVar);
                zzbpVarZzj.zzxz = new zzbp[zzaVarZzk.zzxl.length];
                int[] iArr = zzaVarZzk.zzxl;
                int length = iArr.length;
                int i2 = 0;
                int i3 = 0;
                while (i2 < length) {
                    zzbpVarZzj.zzxz[i3] = zza(iArr[i2], zzblVar, zzbpVarArr, set);
                    i2++;
                    i3++;
                }
                break;
            case 3:
                zzbpVarZzj = zzj(zzbpVar);
                zzbf.zza zzaVarZzk2 = zzk(zzbpVar);
                if (zzaVarZzk2.zzxm.length != zzaVarZzk2.zzxn.length) {
                    zzmw(new StringBuilder(58).append("Uneven map keys (").append(zzaVarZzk2.zzxm.length).append(") and map values (").append(zzaVarZzk2.zzxn.length).append(")").toString());
                }
                zzbpVarZzj.zzya = new zzbp[zzaVarZzk2.zzxm.length];
                zzbpVarZzj.zzyb = new zzbp[zzaVarZzk2.zzxm.length];
                int[] iArr2 = zzaVarZzk2.zzxm;
                int length2 = iArr2.length;
                int i4 = 0;
                int i5 = 0;
                while (i4 < length2) {
                    zzbpVarZzj.zzya[i5] = zza(iArr2[i4], zzblVar, zzbpVarArr, set);
                    i4++;
                    i5++;
                }
                int[] iArr3 = zzaVarZzk2.zzxn;
                int length3 = iArr3.length;
                int i6 = 0;
                int i7 = 0;
                while (i6 < length3) {
                    zzbpVarZzj.zzyb[i7] = zza(iArr3[i6], zzblVar, zzbpVarArr, set);
                    i6++;
                    i7++;
                }
                break;
            case 4:
                zzbpVarZzj = zzj(zzbpVar);
                zzbpVarZzj.zzyc = zzgk.zzb(zza(zzk(zzbpVar).zzxq, zzblVar, zzbpVarArr, set));
                break;
            case 7:
                zzbpVarZzj = zzj(zzbpVar);
                zzbf.zza zzaVarZzk3 = zzk(zzbpVar);
                zzbpVarZzj.zzyg = new zzbp[zzaVarZzk3.zzxp.length];
                int[] iArr4 = zzaVarZzk3.zzxp;
                int length4 = iArr4.length;
                int i8 = 0;
                int i9 = 0;
                while (i8 < length4) {
                    zzbpVarZzj.zzyg[i9] = zza(iArr4[i8], zzblVar, zzbpVarArr, set);
                    i8++;
                    i9++;
                }
                break;
        }
        if (zzbpVarZzj == null) {
            String strValueOf2 = String.valueOf(zzbpVar);
            zzmw(new StringBuilder(String.valueOf(strValueOf2).length() + 15).append("Invalid value: ").append(strValueOf2).toString());
        }
        zzbpVarArr[i] = zzbpVarZzj;
        set.remove(Integer.valueOf(i));
        return zzbpVarZzj;
    }

    private static zzdbq zza(zzbh zzbhVar, zzbl zzblVar, zzbp[] zzbpVarArr, int i) throws zzdbw {
        zzdbr zzdbrVarZzbhw = zzdbq.zzbhw();
        for (int i2 : zzbhVar.zzvv) {
            zzbk zzbkVar = (zzbk) zza(zzblVar.zzwl, Integer.valueOf(i2).intValue(), "properties");
            String str = (String) zza(zzblVar.zzwj, zzbkVar.key, "keys");
            zzbp zzbpVar = (zzbp) zza(zzbpVarArr, zzbkVar.value, "values");
            if (zzbe.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzdbrVarZzbhw.zzl(zzbpVar);
            } else {
                zzdbrVarZzbhw.zzb(str, zzbpVar);
            }
        }
        return zzdbrVarZzbhw.zzbhx();
    }

    public static zzdbs zza(zzbl zzblVar) throws zzdbw {
        zzbp[] zzbpVarArr = new zzbp[zzblVar.zzwk.length];
        for (int i = 0; i < zzblVar.zzwk.length; i++) {
            zza(i, zzblVar, zzbpVarArr, new HashSet(0));
        }
        zzdbt zzdbtVarZzbhy = zzdbs.zzbhy();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < zzblVar.zzwn.length; i2++) {
            arrayList.add(zza(zzblVar.zzwn[i2], zzblVar, zzbpVarArr, i2));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < zzblVar.zzwo.length; i3++) {
            arrayList2.add(zza(zzblVar.zzwo[i3], zzblVar, zzbpVarArr, i3));
        }
        ArrayList arrayList3 = new ArrayList();
        for (int i4 = 0; i4 < zzblVar.zzwm.length; i4++) {
            zzdbq zzdbqVarZza = zza(zzblVar.zzwm[i4], zzblVar, zzbpVarArr, i4);
            zzdbtVarZzbhy.zzc(zzdbqVarZza);
            arrayList3.add(zzdbqVarZza);
        }
        for (zzbm zzbmVar : zzblVar.zzwp) {
            zzdbtVarZzbhy.zzb(zza(zzbmVar, arrayList, arrayList3, arrayList2, zzblVar));
        }
        zzdbtVarZzbhy.zznj(zzblVar.version);
        zzdbtVarZzbhy.zzeq(zzblVar.zzwx);
        return zzdbtVarZzbhy.zzbia();
    }

    private static zzdbu zza(zzbm zzbmVar, List<zzdbq> list, List<zzdbq> list2, List<zzdbq> list3, zzbl zzblVar) {
        zzdbv zzdbvVar = new zzdbv();
        for (int i : zzbmVar.zzwz) {
            zzdbvVar.zzd(list3.get(Integer.valueOf(i).intValue()));
        }
        for (int i2 : zzbmVar.zzxa) {
            zzdbvVar.zze(list3.get(Integer.valueOf(i2).intValue()));
        }
        for (int i3 : zzbmVar.zzxb) {
            zzdbvVar.zzf(list.get(Integer.valueOf(i3).intValue()));
        }
        for (int i4 : zzbmVar.zzxd) {
            zzdbvVar.zznk(zzblVar.zzwk[Integer.valueOf(i4).intValue()].string);
        }
        for (int i5 : zzbmVar.zzxc) {
            zzdbvVar.zzg(list.get(Integer.valueOf(i5).intValue()));
        }
        for (int i6 : zzbmVar.zzxe) {
            zzdbvVar.zznl(zzblVar.zzwk[Integer.valueOf(i6).intValue()].string);
        }
        for (int i7 : zzbmVar.zzxf) {
            zzdbvVar.zzh(list2.get(Integer.valueOf(i7).intValue()));
        }
        for (int i8 : zzbmVar.zzxh) {
            zzdbvVar.zznm(zzblVar.zzwk[Integer.valueOf(i8).intValue()].string);
        }
        for (int i9 : zzbmVar.zzxg) {
            zzdbvVar.zzi(list2.get(Integer.valueOf(i9).intValue()));
        }
        for (int i10 : zzbmVar.zzxi) {
            zzdbvVar.zznn(zzblVar.zzwk[Integer.valueOf(i10).intValue()].string);
        }
        return zzdbvVar.zzbid();
    }

    private static <T> T zza(T[] tArr, int i, String str) throws zzdbw {
        if (i < 0 || i >= tArr.length) {
            zzmw(new StringBuilder(String.valueOf(str).length() + 45).append("Index out of bounds detected: ").append(i).append(" in ").append(str).toString());
        }
        return tArr[i];
    }

    public static void zzb(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    public static zzbp zzj(zzbp zzbpVar) {
        zzbp zzbpVar2 = new zzbp();
        zzbpVar2.type = zzbpVar.type;
        zzbpVar2.zzyh = (int[]) zzbpVar.zzyh.clone();
        if (zzbpVar.zzyi) {
            zzbpVar2.zzyi = zzbpVar.zzyi;
        }
        return zzbpVar2;
    }

    private static zzbf.zza zzk(zzbp zzbpVar) throws zzdbw {
        if (((zzbf.zza) zzbpVar.zza(zzbf.zza.zzxj)) == null) {
            String strValueOf = String.valueOf(zzbpVar);
            zzmw(new StringBuilder(String.valueOf(strValueOf).length() + 54).append("Expected a ServingValue and didn't get one. Value is: ").append(strValueOf).toString());
        }
        return (zzbf.zza) zzbpVar.zza(zzbf.zza.zzxj);
    }

    private static void zzmw(String str) throws zzdbw {
        zzdj.e(str);
        throw new zzdbw(str);
    }
}
