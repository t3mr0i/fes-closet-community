package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import java.util.UUID;

/* loaded from: classes.dex */
public final class zzamf extends com.google.android.gms.analytics.zzh<zzamf> {
    private String zzaph;
    private int zzdnd;
    private int zzdne;
    private String zzdnf;
    private String zzdng;
    private boolean zzdnh;
    private boolean zzdni;

    public zzamf() {
        this(false);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private zzamf(boolean z) {
        UUID uuidRandomUUID = UUID.randomUUID();
        int leastSignificantBits = (int) (uuidRandomUUID.getLeastSignificantBits() & 2147483647L);
        if (leastSignificantBits == 0 && (leastSignificantBits = (int) (uuidRandomUUID.getMostSignificantBits() & 2147483647L)) == 0) {
            Log.e("GAv4", "UUID.randomUUID() returned 0.");
            leastSignificantBits = Integer.MAX_VALUE;
        }
        this(false, leastSignificantBits);
    }

    private zzamf(boolean z, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("Given Integer is zero");
        }
        this.zzdnd = i;
        this.zzdni = false;
    }

    public final String toString() {
        HashMap map = new HashMap();
        map.put("screenName", this.zzaph);
        map.put("interstitial", Boolean.valueOf(this.zzdnh));
        map.put("automatic", Boolean.valueOf(this.zzdni));
        map.put("screenId", Integer.valueOf(this.zzdnd));
        map.put("referrerScreenId", Integer.valueOf(this.zzdne));
        map.put("referrerScreenName", this.zzdnf);
        map.put("referrerUri", this.zzdng);
        return zzh(map);
    }

    @Override // com.google.android.gms.analytics.zzh
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzh zzhVar) {
        zzamf zzamfVar = (zzamf) zzhVar;
        if (!TextUtils.isEmpty(this.zzaph)) {
            zzamfVar.zzaph = this.zzaph;
        }
        if (this.zzdnd != 0) {
            zzamfVar.zzdnd = this.zzdnd;
        }
        if (this.zzdne != 0) {
            zzamfVar.zzdne = this.zzdne;
        }
        if (!TextUtils.isEmpty(this.zzdnf)) {
            zzamfVar.zzdnf = this.zzdnf;
        }
        if (!TextUtils.isEmpty(this.zzdng)) {
            String str = this.zzdng;
            if (TextUtils.isEmpty(str)) {
                zzamfVar.zzdng = null;
            } else {
                zzamfVar.zzdng = str;
            }
        }
        if (this.zzdnh) {
            zzamfVar.zzdnh = this.zzdnh;
        }
        if (this.zzdni) {
            zzamfVar.zzdni = this.zzdni;
        }
    }

    public final String zzvk() {
        return this.zzaph;
    }

    public final int zzvl() {
        return this.zzdnd;
    }

    public final String zzvm() {
        return this.zzdng;
    }
}
