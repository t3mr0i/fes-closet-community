package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzdbo;
import com.google.android.gms.internal.zzdbq;
import com.google.android.gms.internal.zzdbs;
import com.google.android.gms.internal.zzdbu;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
final class zzfc {
    private static final zzea<com.google.android.gms.internal.zzbp> zzjuh = new zzea<>(zzgk.zzbfm(), true);
    private final DataLayer zzjpa;
    private final zzdbs zzjui;
    private final zzbo zzjuj;
    private final Map<String, zzbr> zzjuk;
    private final Map<String, zzbr> zzjul;
    private final Map<String, zzbr> zzjum;
    private final zzp<zzdbq, zzea<com.google.android.gms.internal.zzbp>> zzjun;
    private final zzp<String, zzfi> zzjuo;
    private final Set<zzdbu> zzjup;
    private final Map<String, zzfj> zzjuq;
    private volatile String zzjur;
    private int zzjus;

    public zzfc(Context context, zzdbs zzdbsVar, DataLayer dataLayer, zzan zzanVar, zzan zzanVar2, zzbo zzboVar) {
        if (zzdbsVar == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.zzjui = zzdbsVar;
        this.zzjup = new HashSet(zzdbsVar.zzbhc());
        this.zzjpa = dataLayer;
        this.zzjuj = zzboVar;
        zzfd zzfdVar = new zzfd(this);
        new zzq();
        this.zzjun = zzq.zza(1048576, zzfdVar);
        zzfe zzfeVar = new zzfe(this);
        new zzq();
        this.zzjuo = zzq.zza(1048576, zzfeVar);
        this.zzjuk = new HashMap();
        zzb(new zzm(context));
        zzb(new zzam(zzanVar2));
        zzb(new zzaz(dataLayer));
        zzb(new zzgl(context, dataLayer));
        this.zzjul = new HashMap();
        zzc(new zzak());
        zzc(new zzbl());
        zzc(new zzbm());
        zzc(new zzbt());
        zzc(new zzbu());
        zzc(new zzdf());
        zzc(new zzdg());
        zzc(new zzem());
        zzc(new zzfz());
        this.zzjum = new HashMap();
        zza(new zze(context));
        zza(new zzf(context));
        zza(new zzh(context));
        zza(new zzi(context));
        zza(new zzj(context));
        zza(new zzk(context));
        zza(new zzl(context));
        zza(new zzt());
        zza(new zzaj(this.zzjui.getVersion()));
        zza(new zzam(zzanVar));
        zza(new zzas(dataLayer));
        zza(new zzbc(context));
        zza(new zzbd());
        zza(new zzbk());
        zza(new zzbp(this));
        zza(new zzbv());
        zza(new zzbw());
        zza(new zzcw(context));
        zza(new zzcy());
        zza(new zzde());
        zza(new zzdl());
        zza(new zzdn(context));
        zza(new zzeb());
        zza(new zzef());
        zza(new zzej());
        zza(new zzel());
        zza(new zzen(context));
        zza(new zzfk());
        zza(new zzfl());
        zza(new zzgf());
        zza(new zzgm());
        this.zzjuq = new HashMap();
        for (zzdbu zzdbuVar : this.zzjup) {
            for (int i = 0; i < zzdbuVar.zzbib().size(); i++) {
                zzdbq zzdbqVar = zzdbuVar.zzbib().get(i);
                zzfj zzfjVarZzf = zzf(this.zzjuq, zza(zzdbqVar));
                zzfjVarZzf.zza(zzdbuVar);
                zzfjVarZzf.zza(zzdbuVar, zzdbqVar);
                zzfjVarZzf.zza(zzdbuVar, "Unknown");
            }
            for (int i2 = 0; i2 < zzdbuVar.zzbic().size(); i2++) {
                zzdbq zzdbqVar2 = zzdbuVar.zzbic().get(i2);
                zzfj zzfjVarZzf2 = zzf(this.zzjuq, zza(zzdbqVar2));
                zzfjVarZzf2.zza(zzdbuVar);
                zzfjVarZzf2.zzb(zzdbuVar, zzdbqVar2);
                zzfjVarZzf2.zzb(zzdbuVar, "Unknown");
            }
        }
        for (Map.Entry<String, List<zzdbq>> entry : this.zzjui.zzbhz().entrySet()) {
            for (zzdbq zzdbqVar3 : entry.getValue()) {
                if (!zzgk.zzf(zzdbqVar3.zzbhe().get(com.google.android.gms.internal.zzbe.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                    zzf(this.zzjuq, entry.getKey()).zzb(zzdbqVar3);
                }
            }
        }
    }

    private final zzea<com.google.android.gms.internal.zzbp> zza(com.google.android.gms.internal.zzbp zzbpVar, Set<String> set, zzgn zzgnVar) {
        if (!zzbpVar.zzyi) {
            return new zzea<>(zzbpVar, true);
        }
        switch (zzbpVar.type) {
            case 2:
                com.google.android.gms.internal.zzbp zzbpVarZzj = zzdbo.zzj(zzbpVar);
                zzbpVarZzj.zzxz = new com.google.android.gms.internal.zzbp[zzbpVar.zzxz.length];
                for (int i = 0; i < zzbpVar.zzxz.length; i++) {
                    zzea<com.google.android.gms.internal.zzbp> zzeaVarZza = zza(zzbpVar.zzxz[i], set, zzgnVar.zzeg(i));
                    if (zzeaVarZza == zzjuh) {
                        return zzjuh;
                    }
                    zzbpVarZzj.zzxz[i] = zzeaVarZza.getObject();
                }
                return new zzea<>(zzbpVarZzj, false);
            case 3:
                com.google.android.gms.internal.zzbp zzbpVarZzj2 = zzdbo.zzj(zzbpVar);
                if (zzbpVar.zzya.length != zzbpVar.zzyb.length) {
                    String strValueOf = String.valueOf(zzbpVar.toString());
                    zzdj.e(strValueOf.length() != 0 ? "Invalid serving value: ".concat(strValueOf) : new String("Invalid serving value: "));
                    return zzjuh;
                }
                zzbpVarZzj2.zzya = new com.google.android.gms.internal.zzbp[zzbpVar.zzya.length];
                zzbpVarZzj2.zzyb = new com.google.android.gms.internal.zzbp[zzbpVar.zzya.length];
                for (int i2 = 0; i2 < zzbpVar.zzya.length; i2++) {
                    zzea<com.google.android.gms.internal.zzbp> zzeaVarZza2 = zza(zzbpVar.zzya[i2], set, zzgnVar.zzeh(i2));
                    zzea<com.google.android.gms.internal.zzbp> zzeaVarZza3 = zza(zzbpVar.zzyb[i2], set, zzgnVar.zzei(i2));
                    if (zzeaVarZza2 == zzjuh || zzeaVarZza3 == zzjuh) {
                        return zzjuh;
                    }
                    zzbpVarZzj2.zzya[i2] = zzeaVarZza2.getObject();
                    zzbpVarZzj2.zzyb[i2] = zzeaVarZza3.getObject();
                }
                return new zzea<>(zzbpVarZzj2, false);
            case 4:
                if (set.contains(zzbpVar.zzyc)) {
                    String str = zzbpVar.zzyc;
                    String string = set.toString();
                    zzdj.e(new StringBuilder(String.valueOf(str).length() + 79 + String.valueOf(string).length()).append("Macro cycle detected.  Current macro reference: ").append(str).append(".  Previous macro references: ").append(string).append(".").toString());
                    return zzjuh;
                }
                set.add(zzbpVar.zzyc);
                zzea<com.google.android.gms.internal.zzbp> zzeaVarZza4 = zzgo.zza(zza(zzbpVar.zzyc, set, zzgnVar.zzbed()), zzbpVar.zzyh);
                set.remove(zzbpVar.zzyc);
                return zzeaVarZza4;
            case 5:
            case 6:
            default:
                zzdj.e(new StringBuilder(25).append("Unknown type: ").append(zzbpVar.type).toString());
                return zzjuh;
            case 7:
                com.google.android.gms.internal.zzbp zzbpVarZzj3 = zzdbo.zzj(zzbpVar);
                zzbpVarZzj3.zzyg = new com.google.android.gms.internal.zzbp[zzbpVar.zzyg.length];
                for (int i3 = 0; i3 < zzbpVar.zzyg.length; i3++) {
                    zzea<com.google.android.gms.internal.zzbp> zzeaVarZza5 = zza(zzbpVar.zzyg[i3], set, zzgnVar.zzej(i3));
                    if (zzeaVarZza5 == zzjuh) {
                        return zzjuh;
                    }
                    zzbpVarZzj3.zzyg[i3] = zzeaVarZza5.getObject();
                }
                return new zzea<>(zzbpVarZzj3, false);
        }
    }

    private final zzea<Boolean> zza(zzdbq zzdbqVar, Set<String> set, zzeo zzeoVar) {
        zzea<com.google.android.gms.internal.zzbp> zzeaVarZza = zza(this.zzjul, zzdbqVar, set, zzeoVar);
        Boolean boolZzf = zzgk.zzf(zzeaVarZza.getObject());
        zzgk.zzah(boolZzf);
        return new zzea<>(boolZzf, zzeaVarZza.zzbee());
    }

    private final zzea<Boolean> zza(zzdbu zzdbuVar, Set<String> set, zzer zzerVar) {
        Iterator<zzdbq> it = zzdbuVar.zzbhh().iterator();
        boolean z = true;
        while (it.hasNext()) {
            zzea<Boolean> zzeaVarZza = zza(it.next(), set, zzerVar.zzbdw());
            if (zzeaVarZza.getObject().booleanValue()) {
                zzgk.zzah(false);
                return new zzea<>(false, zzeaVarZza.zzbee());
            }
            z = z && zzeaVarZza.zzbee();
        }
        Iterator<zzdbq> it2 = zzdbuVar.zzbhg().iterator();
        while (it2.hasNext()) {
            zzea<Boolean> zzeaVarZza2 = zza(it2.next(), set, zzerVar.zzbdx());
            if (!zzeaVarZza2.getObject().booleanValue()) {
                zzgk.zzah(false);
                return new zzea<>(false, zzeaVarZza2.zzbee());
            }
            z = z && zzeaVarZza2.zzbee();
        }
        zzgk.zzah(true);
        return new zzea<>(true, z);
    }

    private final zzea<com.google.android.gms.internal.zzbp> zza(String str, Set<String> set, zzdm zzdmVar) throws InterruptedException {
        zzdbq next;
        this.zzjus++;
        zzfi zzfiVar = this.zzjuo.get(str);
        if (zzfiVar != null) {
            zza(zzfiVar.zzbes(), set);
            this.zzjus--;
            return zzfiVar.zzber();
        }
        zzfj zzfjVar = this.zzjuq.get(str);
        if (zzfjVar == null) {
            String strZzbeq = zzbeq();
            zzdj.e(new StringBuilder(String.valueOf(strZzbeq).length() + 15 + String.valueOf(str).length()).append(strZzbeq).append("Invalid macro: ").append(str).toString());
            this.zzjus--;
            return zzjuh;
        }
        zzea<Set<zzdbq>> zzeaVarZza = zza(str, zzfjVar.zzbet(), zzfjVar.zzbeu(), zzfjVar.zzbev(), zzfjVar.zzbex(), zzfjVar.zzbew(), set, zzdmVar.zzbdf());
        if (zzeaVarZza.getObject().isEmpty()) {
            next = zzfjVar.zzbey();
        } else {
            if (zzeaVarZza.getObject().size() > 1) {
                String strZzbeq2 = zzbeq();
                zzdj.zzcr(new StringBuilder(String.valueOf(strZzbeq2).length() + 37 + String.valueOf(str).length()).append(strZzbeq2).append("Multiple macros active for macroName ").append(str).toString());
            }
            next = zzeaVarZza.getObject().iterator().next();
        }
        if (next == null) {
            this.zzjus--;
            return zzjuh;
        }
        zzea<com.google.android.gms.internal.zzbp> zzeaVarZza2 = zza(this.zzjum, next, set, zzdmVar.zzbdv());
        zzea<com.google.android.gms.internal.zzbp> zzeaVar = zzeaVarZza2 == zzjuh ? zzjuh : new zzea<>(zzeaVarZza2.getObject(), zzeaVarZza.zzbee() && zzeaVarZza2.zzbee());
        com.google.android.gms.internal.zzbp zzbpVarZzbes = next.zzbes();
        if (zzeaVar.zzbee()) {
            this.zzjuo.zzf(str, new zzfi(zzeaVar, zzbpVarZzbes));
        }
        zza(zzbpVarZzbes, set);
        this.zzjus--;
        return zzeaVar;
    }

    private final zzea<Set<zzdbq>> zza(String str, Set<zzdbu> set, Map<zzdbu, List<zzdbq>> map, Map<zzdbu, List<String>> map2, Map<zzdbu, List<zzdbq>> map3, Map<zzdbu, List<String>> map4, Set<String> set2, zzfb zzfbVar) {
        return zza(set, set2, new zzff(this, map, map2, map3, map4), zzfbVar);
    }

    private final zzea<com.google.android.gms.internal.zzbp> zza(Map<String, zzbr> map, zzdbq zzdbqVar, Set<String> set, zzeo zzeoVar) {
        boolean z;
        com.google.android.gms.internal.zzbp zzbpVar = zzdbqVar.zzbhe().get(com.google.android.gms.internal.zzbe.FUNCTION.toString());
        if (zzbpVar == null) {
            zzdj.e("No function id in properties");
            return zzjuh;
        }
        String str = zzbpVar.zzyd;
        zzbr zzbrVar = map.get(str);
        if (zzbrVar == null) {
            zzdj.e(String.valueOf(str).concat(" has no backing implementation."));
            return zzjuh;
        }
        zzea<com.google.android.gms.internal.zzbp> zzeaVar = this.zzjun.get(zzdbqVar);
        if (zzeaVar != null) {
            return zzeaVar;
        }
        HashMap map2 = new HashMap();
        boolean z2 = true;
        for (Map.Entry<String, com.google.android.gms.internal.zzbp> entry : zzdbqVar.zzbhe().entrySet()) {
            zzea<com.google.android.gms.internal.zzbp> zzeaVarZza = zza(entry.getValue(), set, zzeoVar.zzlv(entry.getKey()).zza(entry.getValue()));
            if (zzeaVarZza == zzjuh) {
                return zzjuh;
            }
            if (zzeaVarZza.zzbee()) {
                zzdbqVar.zza(entry.getKey(), zzeaVarZza.getObject());
                z = z2;
            } else {
                z = false;
            }
            map2.put(entry.getKey(), zzeaVarZza.getObject());
            z2 = z;
        }
        if (!zzbrVar.zzd(map2.keySet())) {
            String strValueOf = String.valueOf(zzbrVar.zzbdr());
            String strValueOf2 = String.valueOf(map2.keySet());
            zzdj.e(new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(strValueOf).length() + String.valueOf(strValueOf2).length()).append("Incorrect keys for function ").append(str).append(" required ").append(strValueOf).append(" had ").append(strValueOf2).toString());
            return zzjuh;
        }
        boolean z3 = z2 && zzbrVar.zzbck();
        zzea<com.google.android.gms.internal.zzbp> zzeaVar2 = new zzea<>(zzbrVar.zzp(map2), z3);
        if (!z3) {
            return zzeaVar2;
        }
        this.zzjun.zzf(zzdbqVar, zzeaVar2);
        return zzeaVar2;
    }

    private final zzea<Set<zzdbq>> zza(Set<zzdbu> set, Set<String> set2, zzfh zzfhVar, zzfb zzfbVar) {
        Set<zzdbq> hashSet = new HashSet<>();
        Set<zzdbq> hashSet2 = new HashSet<>();
        boolean z = true;
        for (zzdbu zzdbuVar : set) {
            zzer zzerVarZzbec = zzfbVar.zzbec();
            zzea<Boolean> zzeaVarZza = zza(zzdbuVar, set2, zzerVarZzbec);
            if (zzeaVarZza.getObject().booleanValue()) {
                zzfhVar.zza(zzdbuVar, hashSet, hashSet2, zzerVarZzbec);
            }
            z = z && zzeaVarZza.zzbee();
        }
        hashSet.removeAll(hashSet2);
        return new zzea<>(hashSet, z);
    }

    private static String zza(zzdbq zzdbqVar) {
        return zzgk.zzb(zzdbqVar.zzbhe().get(com.google.android.gms.internal.zzbe.INSTANCE_NAME.toString()));
    }

    private final void zza(com.google.android.gms.internal.zzbp zzbpVar, Set<String> set) throws InterruptedException {
        zzea<com.google.android.gms.internal.zzbp> zzeaVarZza;
        if (zzbpVar == null || (zzeaVarZza = zza(zzbpVar, set, new zzdy())) == zzjuh) {
            return;
        }
        Object objZzg = zzgk.zzg(zzeaVarZza.getObject());
        if (objZzg instanceof Map) {
            this.zzjpa.push((Map) objZzg);
            return;
        }
        if (!(objZzg instanceof List)) {
            zzdj.zzcr("pushAfterEvaluate: value not a Map or List");
            return;
        }
        for (Object obj : (List) objZzg) {
            if (obj instanceof Map) {
                this.zzjpa.push((Map) obj);
            } else {
                zzdj.zzcr("pushAfterEvaluate: value not a Map");
            }
        }
    }

    private final void zza(zzbr zzbrVar) {
        zza(this.zzjum, zzbrVar);
    }

    private static void zza(Map<String, zzbr> map, zzbr zzbrVar) {
        if (map.containsKey(zzbrVar.zzbdq())) {
            String strValueOf = String.valueOf(zzbrVar.zzbdq());
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Duplicate function type name: ".concat(strValueOf) : new String("Duplicate function type name: "));
        }
        map.put(zzbrVar.zzbdq(), zzbrVar);
    }

    private final void zzb(zzbr zzbrVar) {
        zza(this.zzjuk, zzbrVar);
    }

    private final String zzbeq() {
        if (this.zzjus <= 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.zzjus));
        for (int i = 2; i < this.zzjus; i++) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }

    private final void zzc(zzbr zzbrVar) {
        zza(this.zzjul, zzbrVar);
    }

    private static zzfj zzf(Map<String, zzfj> map, String str) {
        zzfj zzfjVar = map.get(str);
        if (zzfjVar != null) {
            return zzfjVar;
        }
        zzfj zzfjVar2 = new zzfj();
        map.put(str, zzfjVar2);
        return zzfjVar2;
    }

    private final synchronized void zzlz(String str) {
        this.zzjur = str;
    }

    public final synchronized void zzaj(List<com.google.android.gms.internal.zzbn> list) {
        for (com.google.android.gms.internal.zzbn zzbnVar : list) {
            if (zzbnVar.name == null || !zzbnVar.name.startsWith("gaExperiment:")) {
                String strValueOf = String.valueOf(zzbnVar);
                zzdj.v(new StringBuilder(String.valueOf(strValueOf).length() + 22).append("Ignored supplemental: ").append(strValueOf).toString());
            } else {
                zzbq.zza(this.zzjpa, zzbnVar);
            }
        }
    }

    final synchronized String zzbep() {
        return this.zzjur;
    }

    public final synchronized void zzlf(String str) {
        zzlz(str);
        zzar zzarVarZzbdp = this.zzjuj.zzlp(str).zzbdp();
        Iterator<zzdbq> it = zza(this.zzjup, new HashSet(), new zzfg(this), zzarVarZzbdp.zzbdf()).getObject().iterator();
        while (it.hasNext()) {
            zza(this.zzjuk, it.next(), new HashSet(), zzarVarZzbdp.zzbde());
        }
        zzlz(null);
    }

    public final zzea<com.google.android.gms.internal.zzbp> zzly(String str) {
        this.zzjus = 0;
        return zza(str, new HashSet(), this.zzjuj.zzlo(str).zzbdo());
    }
}
