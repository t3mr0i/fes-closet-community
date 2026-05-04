package com.google.android.gms.tagmanager;

import android.os.Looper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tagmanager.ContainerHolder;

/* loaded from: classes.dex */
final class zzv implements ContainerHolder {
    private Status mStatus;
    private final Looper zzakf;
    private boolean zzgfo;
    private Container zzjph;
    private Container zzjpi;
    private zzx zzjpj;
    private zzw zzjpk;
    private TagManager zzjpl;

    public zzv(Status status) {
        this.mStatus = status;
        this.zzakf = null;
    }

    public zzv(TagManager tagManager, Looper looper, Container container, zzw zzwVar) {
        this.zzjpl = tagManager;
        this.zzakf = looper == null ? Looper.getMainLooper() : looper;
        this.zzjph = container;
        this.zzjpk = zzwVar;
        this.mStatus = Status.zzfhu;
        tagManager.zza(this);
    }

    private final void zzbcr() {
        if (this.zzjpj != null) {
            zzx zzxVar = this.zzjpj;
            zzxVar.sendMessage(zzxVar.obtainMessage(1, this.zzjpi.zzbco()));
        }
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public final synchronized Container getContainer() {
        Container container = null;
        synchronized (this) {
            if (this.zzgfo) {
                zzdj.e("ContainerHolder is released.");
            } else {
                if (this.zzjpi != null) {
                    this.zzjph = this.zzjpi;
                    this.zzjpi = null;
                }
                container = this.zzjph;
            }
        }
        return container;
    }

    final String getContainerId() {
        if (!this.zzgfo) {
            return this.zzjph.getContainerId();
        }
        zzdj.e("getContainerId called on a released ContainerHolder.");
        return "";
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.mStatus;
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public final synchronized void refresh() {
        if (this.zzgfo) {
            zzdj.e("Refreshing a released ContainerHolder.");
        } else {
            this.zzjpk.zzbcs();
        }
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final synchronized void release() {
        if (this.zzgfo) {
            zzdj.e("Releasing a released ContainerHolder.");
        } else {
            this.zzgfo = true;
            this.zzjpl.zzb(this);
            this.zzjph.release();
            this.zzjph = null;
            this.zzjpi = null;
            this.zzjpk = null;
            this.zzjpj = null;
        }
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public final synchronized void setContainerAvailableListener(ContainerHolder.ContainerAvailableListener containerAvailableListener) {
        if (this.zzgfo) {
            zzdj.e("ContainerHolder is released.");
        } else if (containerAvailableListener == null) {
            this.zzjpj = null;
        } else {
            this.zzjpj = new zzx(this, containerAvailableListener, this.zzakf);
            if (this.zzjpi != null) {
                zzbcr();
            }
        }
    }

    public final synchronized void zza(Container container) {
        if (!this.zzgfo) {
            this.zzjpi = container;
            zzbcr();
        }
    }

    final String zzbcq() {
        if (!this.zzgfo) {
            return this.zzjpk.zzbcq();
        }
        zzdj.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }

    public final synchronized void zzlf(String str) {
        if (!this.zzgfo) {
            this.zzjph.zzlf(str);
        }
    }

    final void zzlg(String str) {
        if (this.zzgfo) {
            zzdj.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.zzjpk.zzlg(str);
        }
    }
}
