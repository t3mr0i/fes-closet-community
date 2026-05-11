package com.google.android.gms.tagmanager;

import com.google.android.gms.tagmanager.DataLayer;
import java.util.Map;

/* loaded from: classes.dex */
final class zzgb implements DataLayer.zzb {
    private /* synthetic */ TagManager zzjwa;

    zzgb(TagManager tagManager) {
        this.zzjwa = tagManager;
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.zzb
    public final void zzq(Map<String, Object> map) {
        Object obj = map.get("event");
        if (obj != null) {
            this.zzjwa.zzma(obj.toString());
        }
    }
}
