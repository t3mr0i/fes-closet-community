package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class zzbh {
    private final Object zzdee;
    private final List<String> zzfvp;

    private zzbh(Object obj) {
        this.zzdee = zzbp.zzu(obj);
        this.zzfvp = new ArrayList();
    }

    public final String toString() {
        StringBuilder sbAppend = new StringBuilder(100).append(this.zzdee.getClass().getSimpleName()).append('{');
        int size = this.zzfvp.size();
        for (int i = 0; i < size; i++) {
            sbAppend.append(this.zzfvp.get(i));
            if (i < size - 1) {
                sbAppend.append(", ");
            }
        }
        return sbAppend.append('}').toString();
    }

    public final zzbh zzg(String str, Object obj) {
        List<String> list = this.zzfvp;
        String str2 = (String) zzbp.zzu(str);
        String strValueOf = String.valueOf(obj);
        list.add(new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(strValueOf).length()).append(str2).append("=").append(strValueOf).toString());
        return this;
    }
}
