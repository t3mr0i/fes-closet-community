package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class zzdbu {
    private final List<zzdbq> zzkee;
    private final List<zzdbq> zzkef;
    private final List<zzdbq> zzkeg;
    private final List<zzdbq> zzkeh;
    private final List<zzdbq> zzkfm;
    private final List<zzdbq> zzkfn;
    private final List<String> zzkfo;
    private final List<String> zzkfp;
    private final List<String> zzkfq;
    private final List<String> zzkfr;

    private zzdbu(List<zzdbq> list, List<zzdbq> list2, List<zzdbq> list3, List<zzdbq> list4, List<zzdbq> list5, List<zzdbq> list6, List<String> list7, List<String> list8, List<String> list9, List<String> list10) {
        this.zzkee = Collections.unmodifiableList(list);
        this.zzkef = Collections.unmodifiableList(list2);
        this.zzkeg = Collections.unmodifiableList(list3);
        this.zzkeh = Collections.unmodifiableList(list4);
        this.zzkfm = Collections.unmodifiableList(list5);
        this.zzkfn = Collections.unmodifiableList(list6);
        this.zzkfo = Collections.unmodifiableList(list7);
        this.zzkfp = Collections.unmodifiableList(list8);
        this.zzkfq = Collections.unmodifiableList(list9);
        this.zzkfr = Collections.unmodifiableList(list10);
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zzkee);
        String strValueOf2 = String.valueOf(this.zzkef);
        String strValueOf3 = String.valueOf(this.zzkeg);
        String strValueOf4 = String.valueOf(this.zzkeh);
        String strValueOf5 = String.valueOf(this.zzkfm);
        String strValueOf6 = String.valueOf(this.zzkfn);
        return new StringBuilder(String.valueOf(strValueOf).length() + 102 + String.valueOf(strValueOf2).length() + String.valueOf(strValueOf3).length() + String.valueOf(strValueOf4).length() + String.valueOf(strValueOf5).length() + String.valueOf(strValueOf6).length()).append("Positive predicates: ").append(strValueOf).append("  Negative predicates: ").append(strValueOf2).append("  Add tags: ").append(strValueOf3).append("  Remove tags: ").append(strValueOf4).append("  Add macros: ").append(strValueOf5).append("  Remove macros: ").append(strValueOf6).toString();
    }

    public final List<zzdbq> zzbhg() {
        return this.zzkee;
    }

    public final List<zzdbq> zzbhh() {
        return this.zzkef;
    }

    public final List<zzdbq> zzbhi() {
        return this.zzkeg;
    }

    public final List<zzdbq> zzbhj() {
        return this.zzkeh;
    }

    public final List<zzdbq> zzbib() {
        return this.zzkfm;
    }

    public final List<zzdbq> zzbic() {
        return this.zzkfn;
    }
}
