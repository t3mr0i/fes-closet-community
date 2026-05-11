package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzame;
import com.google.android.gms.internal.zzami;
import com.google.android.gms.internal.zzamu;
import java.util.ListIterator;

/* loaded from: classes.dex */
public class zza extends zzi<zza> {
    private final zzamu zzdji;
    private boolean zzdjj;

    public zza(zzamu zzamuVar) {
        super(zzamuVar.zzwa(), zzamuVar.zzvx());
        this.zzdji = zzamuVar;
    }

    public final void enableAdvertisingIdCollection(boolean z) {
        this.zzdjj = z;
    }

    @Override // com.google.android.gms.analytics.zzi
    protected final void zza(zzg zzgVar) {
        zzame zzameVar = (zzame) zzgVar.zzb(zzame.class);
        if (TextUtils.isEmpty(zzameVar.zzve())) {
            zzameVar.setClientId(this.zzdji.zzwq().zzxp());
        }
        if (this.zzdjj && TextUtils.isEmpty(zzameVar.zzvf())) {
            zzami zzamiVarZzwp = this.zzdji.zzwp();
            zzameVar.zzdi(zzamiVarZzwp.zzvn());
            zzameVar.zzah(zzamiVarZzwp.zzvg());
        }
    }

    public final void zzcw(String str) {
        zzbp.zzgg(str);
        Uri uriZzcx = zzb.zzcx(str);
        ListIterator<zzm> listIterator = this.zzdku.getTransports().listIterator();
        while (listIterator.hasNext()) {
            if (uriZzcx.equals(listIterator.next().zztu())) {
                listIterator.remove();
            }
        }
        this.zzdku.getTransports().add(new zzb(this.zzdji, str));
    }

    final zzamu zztr() {
        return this.zzdji;
    }

    @Override // com.google.android.gms.analytics.zzi
    public final zzg zzts() {
        zzg zzgVarZztx = this.zzdku.zztx();
        zzgVarZztx.zza(this.zzdji.zzwi().zzxd());
        zzgVarZztx.zza(this.zzdji.zzwj().zzyh());
        zzd(zzgVarZztx);
        return zzgVarZztx;
    }
}
