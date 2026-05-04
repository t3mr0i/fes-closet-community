package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbck;

/* loaded from: classes.dex */
public abstract class StatsEvent extends zzbck implements ReflectedParcelable {
    public abstract int getEventType();

    public abstract long getTimeMillis();

    public String toString() {
        long timeMillis = getTimeMillis();
        int eventType = getEventType();
        long jZzala = zzala();
        String strZzalb = zzalb();
        return new StringBuilder(String.valueOf("\t").length() + 51 + String.valueOf("\t").length() + String.valueOf(strZzalb).length()).append(timeMillis).append("\t").append(eventType).append("\t").append(jZzala).append(strZzalb).toString();
    }

    public abstract long zzala();

    public abstract String zzalb();
}
