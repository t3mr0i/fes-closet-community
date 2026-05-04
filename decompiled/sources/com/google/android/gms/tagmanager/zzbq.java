package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes.dex */
final class zzbq {
    /* JADX WARN: Removed duplicated region for block: B:23:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void zza(com.google.android.gms.tagmanager.DataLayer r14, com.google.android.gms.internal.zzbj r15) throws java.lang.InterruptedException {
        /*
            r3 = 0
            com.google.android.gms.internal.zzbi[] r4 = r15.zzwg
            int r5 = r4.length
            r2 = r3
        L5:
            if (r2 >= r5) goto Lb9
            r6 = r4[r2]
            java.lang.String r0 = r6.key
            if (r0 != 0) goto L16
            java.lang.String r0 = "GaExperimentRandom: No key"
            com.google.android.gms.tagmanager.zzdj.zzcr(r0)
        L12:
            int r0 = r2 + 1
            r2 = r0
            goto L5
        L16:
            java.lang.String r0 = r6.key
            java.lang.Object r1 = r14.get(r0)
            boolean r0 = r1 instanceof java.lang.Number
            if (r0 != 0) goto L88
            r0 = 0
        L21:
            long r8 = r6.zzwa
            long r10 = r6.zzwb
            boolean r7 = r6.zzwc
            if (r7 == 0) goto L3b
            if (r0 == 0) goto L3b
            long r12 = r0.longValue()
            int r7 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r7 < 0) goto L3b
            long r12 = r0.longValue()
            int r0 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r0 <= 0) goto L50
        L3b:
            int r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r0 > 0) goto L94
            double r0 = java.lang.Math.random()
            long r10 = r10 - r8
            double r10 = (double) r10
            double r0 = r0 * r10
            double r8 = (double) r8
            double r0 = r0 + r8
            long r0 = java.lang.Math.round(r0)
            java.lang.Long r1 = java.lang.Long.valueOf(r0)
        L50:
            java.lang.String r0 = r6.key
            r14.zzli(r0)
            java.lang.String r0 = r6.key
            java.util.Map r1 = com.google.android.gms.tagmanager.DataLayer.zzn(r0, r1)
            long r8 = r6.zzwd
            r10 = 0
            int r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r0 <= 0) goto L84
            java.lang.String r0 = "gtm"
            boolean r0 = r1.containsKey(r0)
            if (r0 != 0) goto L9b
            java.lang.String r0 = "gtm"
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]
            java.lang.String r8 = "lifetime"
            r7[r3] = r8
            r8 = 1
            long r10 = r6.zzwd
            java.lang.Long r6 = java.lang.Long.valueOf(r10)
            r7[r8] = r6
            java.util.Map r6 = com.google.android.gms.tagmanager.DataLayer.mapOf(r7)
            r1.put(r0, r6)
        L84:
            r14.push(r1)
            goto L12
        L88:
            r0 = r1
            java.lang.Number r0 = (java.lang.Number) r0
            long r8 = r0.longValue()
            java.lang.Long r0 = java.lang.Long.valueOf(r8)
            goto L21
        L94:
            java.lang.String r0 = "GaExperimentRandom: random range invalid"
            com.google.android.gms.tagmanager.zzdj.zzcr(r0)
            goto L12
        L9b:
            java.lang.String r0 = "gtm"
            java.lang.Object r0 = r1.get(r0)
            boolean r7 = r0 instanceof java.util.Map
            if (r7 == 0) goto Lb3
            java.util.Map r0 = (java.util.Map) r0
            java.lang.String r7 = "lifetime"
            long r8 = r6.zzwd
            java.lang.Long r6 = java.lang.Long.valueOf(r8)
            r0.put(r7, r6)
            goto L84
        Lb3:
            java.lang.String r0 = "GaExperimentRandom: gtm not a map"
            com.google.android.gms.tagmanager.zzdj.zzcr(r0)
            goto L84
        Lb9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzbq.zza(com.google.android.gms.tagmanager.DataLayer, com.google.android.gms.internal.zzbj):void");
    }

    public static void zza(DataLayer dataLayer, com.google.android.gms.internal.zzbn zzbnVar) throws InterruptedException {
        Map<String, Object> map;
        if (zzbnVar.zzxu == null) {
            zzdj.zzcr("supplemental missing experimentSupplemental");
            return;
        }
        for (com.google.android.gms.internal.zzbp zzbpVar : zzbnVar.zzxu.zzwf) {
            dataLayer.zzli(zzgk.zzb(zzbpVar));
        }
        for (com.google.android.gms.internal.zzbp zzbpVar2 : zzbnVar.zzxu.zzwe) {
            Object objZzg = zzgk.zzg(zzbpVar2);
            if (objZzg instanceof Map) {
                map = (Map) objZzg;
            } else {
                String strValueOf = String.valueOf(objZzg);
                zzdj.zzcr(new StringBuilder(String.valueOf(strValueOf).length() + 36).append("value: ").append(strValueOf).append(" is not a map value, ignored.").toString());
                map = null;
            }
            if (map != null) {
                dataLayer.push(map);
            }
        }
        zza(dataLayer, zzbnVar.zzxu);
    }
}
