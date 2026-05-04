package com.google.android.gms.internal;

/* loaded from: classes.dex */
public final class zzanf extends zzams {
    private final zzalv zzdla;

    zzanf(zzamu zzamuVar) {
        super(zzamuVar);
        this.zzdla = new zzalv();
    }

    @Override // com.google.android.gms.internal.zzams
    protected final void zzuk() {
        zzwa().zzuh().zzb(this.zzdla);
        zzape zzapeVarZzwe = zzwe();
        String strZzun = zzapeVarZzwe.zzun();
        if (strZzun != null) {
            this.zzdla.setAppName(strZzun);
        }
        String strZzuo = zzapeVarZzwe.zzuo();
        if (strZzuo != null) {
            this.zzdla.setAppVersion(strZzuo);
        }
    }

    public final zzalv zzxd() {
        zzwk();
        return this.zzdla;
    }
}
