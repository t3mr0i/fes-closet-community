package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RawRes;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.PendingResult;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
public class TagManager {
    private static TagManager zzjvz;
    private final Context mContext;
    private final DataLayer zzjpa;
    private final zzal zzjtz;
    private final zza zzjvw;
    private final zzfn zzjvx;
    private final ConcurrentMap<String, zzv> zzjvy;

    public interface zza {
        zzy zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzal zzalVar);
    }

    private TagManager(Context context, zza zzaVar, DataLayer dataLayer, zzfn zzfnVar) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.zzjvx = zzfnVar;
        this.zzjvw = zzaVar;
        this.zzjvy = new ConcurrentHashMap();
        this.zzjpa = dataLayer;
        this.zzjpa.zza(new zzgb(this));
        this.zzjpa.zza(new zzg(this.mContext));
        this.zzjtz = new zzal();
        this.mContext.registerComponentCallbacks(new zzgd(this));
        com.google.android.gms.tagmanager.zza.zzdp(this.mContext);
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (zzjvz == null) {
                if (context == null) {
                    zzdj.e("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
                zzjvz = new TagManager(context, new zzgc(), new DataLayer(new zzat(context)), zzfo.zzbfa());
            }
            tagManager = zzjvz;
        }
        return tagManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzma(String str) {
        Iterator<zzv> it = this.zzjvy.values().iterator();
        while (it.hasNext()) {
            it.next().zzlf(str);
        }
    }

    public void dispatch() {
        this.zzjvx.dispatch();
    }

    public DataLayer getDataLayer() {
        return this.zzjpa;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, @RawRes int i) {
        zzy zzyVarZza = this.zzjvw.zza(this.mContext, this, null, str, i, this.zzjtz);
        zzyVarZza.zzbct();
        return zzyVarZza;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, @RawRes int i, Handler handler) {
        zzy zzyVarZza = this.zzjvw.zza(this.mContext, this, handler.getLooper(), str, i, this.zzjtz);
        zzyVarZza.zzbct();
        return zzyVarZza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i) {
        zzy zzyVarZza = this.zzjvw.zza(this.mContext, this, null, str, i, this.zzjtz);
        zzyVarZza.zzbcv();
        return zzyVarZza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i, Handler handler) {
        zzy zzyVarZza = this.zzjvw.zza(this.mContext, this, handler.getLooper(), str, i, this.zzjtz);
        zzyVarZza.zzbcv();
        return zzyVarZza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, @RawRes int i) {
        zzy zzyVarZza = this.zzjvw.zza(this.mContext, this, null, str, i, this.zzjtz);
        zzyVarZza.zzbcu();
        return zzyVarZza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, @RawRes int i, Handler handler) {
        zzy zzyVarZza = this.zzjvw.zza(this.mContext, this, handler.getLooper(), str, i, this.zzjtz);
        zzyVarZza.zzbcu();
        return zzyVarZza;
    }

    public void setVerboseLoggingEnabled(boolean z) {
        zzdj.setLogLevel(z ? 2 : 5);
    }

    public final int zza(zzv zzvVar) {
        this.zzjvy.put(zzvVar.getContainerId(), zzvVar);
        return this.zzjvy.size();
    }

    public final boolean zzb(zzv zzvVar) {
        return this.zzjvy.remove(zzvVar.getContainerId()) != null;
    }

    final synchronized boolean zzq(Uri uri) {
        boolean z;
        zzei zzeiVarZzbei = zzei.zzbei();
        if (zzeiVarZzbei.zzq(uri)) {
            String containerId = zzeiVarZzbei.getContainerId();
            switch (zzge.zzjwb[zzeiVarZzbei.zzbej().ordinal()]) {
                case 1:
                    zzv zzvVar = this.zzjvy.get(containerId);
                    if (zzvVar != null) {
                        zzvVar.zzlg(null);
                        zzvVar.refresh();
                        break;
                    }
                    break;
                case 2:
                case 3:
                    for (String str : this.zzjvy.keySet()) {
                        zzv zzvVar2 = this.zzjvy.get(str);
                        if (str.equals(containerId)) {
                            zzvVar2.zzlg(zzeiVarZzbei.zzbek());
                            zzvVar2.refresh();
                        } else if (zzvVar2.zzbcq() != null) {
                            zzvVar2.zzlg(null);
                            zzvVar2.refresh();
                        }
                    }
                    break;
            }
            z = true;
        } else {
            z = false;
        }
        return z;
    }
}
