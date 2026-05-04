package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class zzbdl {
    /* JADX WARN: Multi-variable type inference failed */
    protected static <O, I> I zza(zzbdm<I, O> zzbdmVar, Object obj) {
        return ((zzbdm) zzbdmVar).zzfwy != null ? zzbdmVar.convertBack(obj) : obj;
    }

    private static void zza(StringBuilder sb, zzbdm zzbdmVar, Object obj) {
        if (zzbdmVar.zzfwp == 11) {
            sb.append(zzbdmVar.zzfwv.cast(obj).toString());
        } else {
            if (zzbdmVar.zzfwp != 7) {
                sb.append(obj);
                return;
            }
            sb.append("\"");
            sb.append(com.google.android.gms.common.util.zzn.zzgl((String) obj));
            sb.append("\"");
        }
    }

    private static void zza(StringBuilder sb, zzbdm zzbdmVar, ArrayList<Object> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                zza(sb, zzbdmVar, obj);
            }
        }
        sb.append("]");
    }

    public String toString() {
        Map<String, zzbdm<?, ?>> mapZzzz = zzzz();
        StringBuilder sb = new StringBuilder(100);
        for (String str : mapZzzz.keySet()) {
            zzbdm<?, ?> zzbdmVar = mapZzzz.get(str);
            if (zza(zzbdmVar)) {
                Object objZza = zza(zzbdmVar, zzb(zzbdmVar));
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append("\"").append(str).append("\":");
                if (objZza != null) {
                    switch (zzbdmVar.zzfwr) {
                        case 8:
                            sb.append("\"").append(com.google.android.gms.common.util.zzb.encode((byte[]) objZza)).append("\"");
                            break;
                        case 9:
                            sb.append("\"").append(com.google.android.gms.common.util.zzb.zzj((byte[]) objZza)).append("\"");
                            break;
                        case 10:
                            com.google.android.gms.common.util.zzo.zza(sb, (HashMap) objZza);
                            break;
                        default:
                            if (zzbdmVar.zzfwq) {
                                zza(sb, (zzbdm) zzbdmVar, (ArrayList<Object>) objZza);
                                break;
                            } else {
                                zza(sb, zzbdmVar, objZza);
                                break;
                            }
                    }
                } else {
                    sb.append("null");
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }

    protected boolean zza(zzbdm zzbdmVar) {
        if (zzbdmVar.zzfwr != 11) {
            return zzgj(zzbdmVar.zzfwt);
        }
        if (zzbdmVar.zzfws) {
            String str = zzbdmVar.zzfwt;
            throw new UnsupportedOperationException("Concrete type arrays not supported");
        }
        String str2 = zzbdmVar.zzfwt;
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    protected Object zzb(zzbdm zzbdmVar) {
        String str = zzbdmVar.zzfwt;
        if (zzbdmVar.zzfwv == null) {
            return zzgi(zzbdmVar.zzfwt);
        }
        zzgi(zzbdmVar.zzfwt);
        com.google.android.gms.common.internal.zzbp.zza(true, "Concrete field shouldn't be value object: %s", zzbdmVar.zzfwt);
        boolean z = zzbdmVar.zzfws;
        try {
            char upperCase = Character.toUpperCase(str.charAt(0));
            String strSubstring = str.substring(1);
            return getClass().getMethod(new StringBuilder(String.valueOf(strSubstring).length() + 4).append("get").append(upperCase).append(strSubstring).toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract Object zzgi(String str);

    protected abstract boolean zzgj(String str);

    public abstract Map<String, zzbdm<?, ?>> zzzz();
}
