package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzeeq {
    private static Class<?> zznbu = zzcco();

    private static Class<?> zzcco() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static zzeer zzccp() {
        if (zznbu != null) {
            try {
                return zzrl("getEmptyRegistry");
            } catch (Exception e) {
            }
        }
        return zzeer.zznbx;
    }

    private static final zzeer zzrl(String str) throws Exception {
        return (zzeer) zznbu.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
    }
}
