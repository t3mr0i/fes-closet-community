package com.google.android.gms.tagmanager;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
final class zzcy extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.JOINER.toString();
    private static final String zzjrk = com.google.android.gms.internal.zzbe.ARG0.toString();
    private static final String zzjsc = com.google.android.gms.internal.zzbe.ITEM_SEPARATOR.toString();
    private static final String zzjsd = com.google.android.gms.internal.zzbe.KEY_VALUE_SEPARATOR.toString();
    private static final String zzjse = com.google.android.gms.internal.zzbe.ESCAPE.toString();

    public zzcy() {
        super(ID, zzjrk);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000f, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0010, code lost:
    
        com.google.android.gms.tagmanager.zzdj.zzb("Joiner: unsupported encoding", r0);
     */
    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Set<Ljava/lang/Character;>;)Ljava/lang/String; */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String zza(java.lang.String r6, int r7, java.util.Set r8) {
        /*
            int[] r0 = com.google.android.gms.tagmanager.zzcz.zzjsf
            int r1 = r7 + (-1)
            r0 = r0[r1]
            switch(r0) {
                case 1: goto La;
                case 2: goto L16;
                default: goto L9;
            }
        L9:
            return r6
        La:
            java.lang.String r6 = com.google.android.gms.tagmanager.zzgo.zzmi(r6)     // Catch: java.io.UnsupportedEncodingException -> Lf
            goto L9
        Lf:
            r0 = move-exception
            java.lang.String r1 = "Joiner: unsupported encoding"
            com.google.android.gms.tagmanager.zzdj.zzb(r1, r0)
            goto L9
        L16:
            java.lang.String r0 = "\\"
            java.lang.String r1 = "\\\\"
            java.lang.String r0 = r6.replace(r0, r1)
            java.util.Iterator r2 = r8.iterator()
            r1 = r0
        L23:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L4f
            java.lang.Object r0 = r2.next()
            java.lang.Character r0 = (java.lang.Character) r0
            java.lang.String r3 = r0.toString()
            java.lang.String r4 = "\\"
            java.lang.String r0 = java.lang.String.valueOf(r3)
            int r5 = r0.length()
            if (r5 == 0) goto L49
            java.lang.String r0 = r4.concat(r0)
        L43:
            java.lang.String r0 = r1.replace(r3, r0)
            r1 = r0
            goto L23
        L49:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4)
            goto L43
        L4f:
            r6 = r1
            goto L9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcy.zza(java.lang.String, int, java.util.Set):java.lang.String");
    }

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Set<Ljava/lang/Character;>;)V */
    private static void zza(StringBuilder sb, String str, int i, Set set) {
        sb.append(zza(str, i, set));
    }

    private static void zza(Set<Character> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(Character.valueOf(str.charAt(i)));
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        HashSet hashSet;
        com.google.android.gms.internal.zzbp zzbpVar = map.get(zzjrk);
        if (zzbpVar == null) {
            return zzgk.zzbfm();
        }
        com.google.android.gms.internal.zzbp zzbpVar2 = map.get(zzjsc);
        String strZzb = zzbpVar2 != null ? zzgk.zzb(zzbpVar2) : "";
        com.google.android.gms.internal.zzbp zzbpVar3 = map.get(zzjsd);
        String strZzb2 = zzbpVar3 != null ? zzgk.zzb(zzbpVar3) : "=";
        int i = zzda.zzjsg;
        com.google.android.gms.internal.zzbp zzbpVar4 = map.get(zzjse);
        if (zzbpVar4 != null) {
            String strZzb3 = zzgk.zzb(zzbpVar4);
            if ("url".equals(strZzb3)) {
                i = zzda.zzjsh;
                hashSet = null;
            } else {
                if (!"backslash".equals(strZzb3)) {
                    String strValueOf = String.valueOf(strZzb3);
                    zzdj.e(strValueOf.length() != 0 ? "Joiner: unsupported escape type: ".concat(strValueOf) : new String("Joiner: unsupported escape type: "));
                    return zzgk.zzbfm();
                }
                int i2 = zzda.zzjsi;
                hashSet = new HashSet();
                zza(hashSet, strZzb);
                zza(hashSet, strZzb2);
                hashSet.remove('\\');
                i = i2;
            }
        } else {
            hashSet = null;
        }
        StringBuilder sb = new StringBuilder();
        switch (zzbpVar.type) {
            case 2:
                boolean z = true;
                com.google.android.gms.internal.zzbp[] zzbpVarArr = zzbpVar.zzxz;
                int length = zzbpVarArr.length;
                int i3 = 0;
                while (i3 < length) {
                    com.google.android.gms.internal.zzbp zzbpVar5 = zzbpVarArr[i3];
                    if (!z) {
                        sb.append(strZzb);
                    }
                    zza(sb, zzgk.zzb(zzbpVar5), i, hashSet);
                    i3++;
                    z = false;
                }
                break;
            case 3:
                for (int i4 = 0; i4 < zzbpVar.zzya.length; i4++) {
                    if (i4 > 0) {
                        sb.append(strZzb);
                    }
                    String strZzb4 = zzgk.zzb(zzbpVar.zzya[i4]);
                    String strZzb5 = zzgk.zzb(zzbpVar.zzyb[i4]);
                    zza(sb, strZzb4, i, hashSet);
                    sb.append(strZzb2);
                    zza(sb, strZzb5, i, hashSet);
                }
                break;
            default:
                zza(sb, zzgk.zzb(zzbpVar), i, hashSet);
                break;
        }
        return zzgk.zzah(sb.toString());
    }
}
