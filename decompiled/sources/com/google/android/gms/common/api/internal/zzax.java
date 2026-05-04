package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

/* loaded from: classes.dex */
final class zzax extends zzbb {
    private /* synthetic */ zzar zzflw;
    private final ArrayList<Api.zze> zzfmc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzax(zzar zzarVar, ArrayList<Api.zze> arrayList) {
        super(zzarVar, null);
        this.zzflw = zzarVar;
        this.zzfmc = arrayList;
    }

    @Override // com.google.android.gms.common.api.internal.zzbb
    @WorkerThread
    public final void zzagz() {
        this.zzflw.zzflg.zzfjt.zzfmn = this.zzflw.zzahf();
        ArrayList<Api.zze> arrayList = this.zzfmc;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Api.zze zzeVar = arrayList.get(i);
            i++;
            zzeVar.zza(this.zzflw.zzfls, this.zzflw.zzflg.zzfjt.zzfmn);
        }
    }
}
