package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzdbo;
import com.google.android.gms.internal.zzdbs;
import com.google.android.gms.internal.zzdbw;
import com.google.android.gms.tagmanager.zzei;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class Container {
    private final Context mContext;
    private final String zzjoz;
    private final DataLayer zzjpa;
    private zzfc zzjpb;
    private Map<String, FunctionCallMacroCallback> zzjpc;
    private Map<String, FunctionCallTagCallback> zzjpd;
    private volatile long zzjpe;
    private volatile String zzjpf;

    public interface FunctionCallMacroCallback {
        Object getValue(String str, Map<String, Object> map);
    }

    public interface FunctionCallTagCallback {
        void execute(String str, Map<String, Object> map);
    }

    class zza implements zzan {
        private zza() {
        }

        @Override // com.google.android.gms.tagmanager.zzan
        public final Object zzd(String str, Map<String, Object> map) {
            FunctionCallMacroCallback functionCallMacroCallbackZzld = Container.this.zzld(str);
            if (functionCallMacroCallbackZzld == null) {
                return null;
            }
            return functionCallMacroCallbackZzld.getValue(str, map);
        }
    }

    class zzb implements zzan {
        private zzb() {
        }

        @Override // com.google.android.gms.tagmanager.zzan
        public final Object zzd(String str, Map<String, Object> map) {
            FunctionCallTagCallback functionCallTagCallbackZzle = Container.this.zzle(str);
            if (functionCallTagCallbackZzle != null) {
                functionCallTagCallbackZzle.execute(str, map);
            }
            return zzgk.zzbfl();
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, com.google.android.gms.internal.zzbo zzboVar) throws InterruptedException {
        this.zzjpc = new HashMap();
        this.zzjpd = new HashMap();
        this.zzjpf = "";
        this.mContext = context;
        this.zzjpa = dataLayer;
        this.zzjoz = str;
        this.zzjpe = j;
        com.google.android.gms.internal.zzbl zzblVar = zzboVar.zzxw;
        if (zzblVar == null) {
            throw new NullPointerException();
        }
        try {
            zza(zzdbo.zza(zzblVar));
        } catch (zzdbw e) {
            String strValueOf = String.valueOf(zzblVar);
            String string = e.toString();
            zzdj.e(new StringBuilder(String.valueOf(strValueOf).length() + 46 + String.valueOf(string).length()).append("Not loading resource: ").append(strValueOf).append(" because it is invalid: ").append(string).toString());
        }
        if (zzboVar.zzxv != null) {
            zza(zzboVar.zzxv);
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzdbs zzdbsVar) throws InterruptedException {
        this.zzjpc = new HashMap();
        this.zzjpd = new HashMap();
        this.zzjpf = "";
        this.mContext = context;
        this.zzjpa = dataLayer;
        this.zzjoz = str;
        this.zzjpe = 0L;
        zza(zzdbsVar);
    }

    private final void zza(zzdbs zzdbsVar) throws InterruptedException {
        this.zzjpf = zzdbsVar.getVersion();
        String str = this.zzjpf;
        zzei.zzbei().zzbej().equals(zzei.zza.CONTAINER_DEBUG);
        zza(new zzfc(this.mContext, zzdbsVar, this.zzjpa, new zza(), new zzb(), new zzdr()));
        if (getBoolean("_gtm.loadEventEnabled")) {
            this.zzjpa.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", this.zzjoz));
        }
    }

    private final synchronized void zza(zzfc zzfcVar) {
        this.zzjpb = zzfcVar;
    }

    private final void zza(com.google.android.gms.internal.zzbn[] zzbnVarArr) {
        ArrayList arrayList = new ArrayList();
        for (com.google.android.gms.internal.zzbn zzbnVar : zzbnVarArr) {
            arrayList.add(zzbnVar);
        }
        zzbcp().zzaj(arrayList);
    }

    private final synchronized zzfc zzbcp() {
        return this.zzjpb;
    }

    public boolean getBoolean(String str) {
        zzfc zzfcVarZzbcp = zzbcp();
        if (zzfcVarZzbcp == null) {
            zzdj.e("getBoolean called for closed container.");
            return zzgk.zzbfj().booleanValue();
        }
        try {
            return zzgk.zzf(zzfcVarZzbcp.zzly(str).getObject()).booleanValue();
        } catch (Exception e) {
            String message = e.getMessage();
            zzdj.e(new StringBuilder(String.valueOf(message).length() + 66).append("Calling getBoolean() threw an exception: ").append(message).append(" Returning default value.").toString());
            return zzgk.zzbfj().booleanValue();
        }
    }

    public String getContainerId() {
        return this.zzjoz;
    }

    public double getDouble(String str) {
        zzfc zzfcVarZzbcp = zzbcp();
        if (zzfcVarZzbcp == null) {
            zzdj.e("getDouble called for closed container.");
            return zzgk.zzbfi().doubleValue();
        }
        try {
            return zzgk.zze(zzfcVarZzbcp.zzly(str).getObject()).doubleValue();
        } catch (Exception e) {
            String message = e.getMessage();
            zzdj.e(new StringBuilder(String.valueOf(message).length() + 65).append("Calling getDouble() threw an exception: ").append(message).append(" Returning default value.").toString());
            return zzgk.zzbfi().doubleValue();
        }
    }

    public long getLastRefreshTime() {
        return this.zzjpe;
    }

    public long getLong(String str) {
        zzfc zzfcVarZzbcp = zzbcp();
        if (zzfcVarZzbcp == null) {
            zzdj.e("getLong called for closed container.");
            return zzgk.zzbfh().longValue();
        }
        try {
            return zzgk.zzd(zzfcVarZzbcp.zzly(str).getObject()).longValue();
        } catch (Exception e) {
            String message = e.getMessage();
            zzdj.e(new StringBuilder(String.valueOf(message).length() + 63).append("Calling getLong() threw an exception: ").append(message).append(" Returning default value.").toString());
            return zzgk.zzbfh().longValue();
        }
    }

    public String getString(String str) {
        zzfc zzfcVarZzbcp = zzbcp();
        if (zzfcVarZzbcp == null) {
            zzdj.e("getString called for closed container.");
            return zzgk.zzbfl();
        }
        try {
            return zzgk.zzb(zzfcVarZzbcp.zzly(str).getObject());
        } catch (Exception e) {
            String message = e.getMessage();
            zzdj.e(new StringBuilder(String.valueOf(message).length() + 65).append("Calling getString() threw an exception: ").append(message).append(" Returning default value.").toString());
            return zzgk.zzbfl();
        }
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    public void registerFunctionCallMacroCallback(String str, FunctionCallMacroCallback functionCallMacroCallback) {
        if (functionCallMacroCallback == null) {
            throw new NullPointerException("Macro handler must be non-null");
        }
        synchronized (this.zzjpc) {
            this.zzjpc.put(str, functionCallMacroCallback);
        }
    }

    public void registerFunctionCallTagCallback(String str, FunctionCallTagCallback functionCallTagCallback) {
        if (functionCallTagCallback == null) {
            throw new NullPointerException("Tag callback must be non-null");
        }
        synchronized (this.zzjpd) {
            this.zzjpd.put(str, functionCallTagCallback);
        }
    }

    final void release() {
        this.zzjpb = null;
    }

    public void unregisterFunctionCallMacroCallback(String str) {
        synchronized (this.zzjpc) {
            this.zzjpc.remove(str);
        }
    }

    public void unregisterFunctionCallTagCallback(String str) {
        synchronized (this.zzjpd) {
            this.zzjpd.remove(str);
        }
    }

    public final String zzbco() {
        return this.zzjpf;
    }

    final FunctionCallMacroCallback zzld(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.zzjpc) {
            functionCallMacroCallback = this.zzjpc.get(str);
        }
        return functionCallMacroCallback;
    }

    public final FunctionCallTagCallback zzle(String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.zzjpd) {
            functionCallTagCallback = this.zzjpd.get(str);
        }
        return functionCallTagCallback;
    }

    public final void zzlf(String str) {
        zzbcp().zzlf(str);
    }
}
