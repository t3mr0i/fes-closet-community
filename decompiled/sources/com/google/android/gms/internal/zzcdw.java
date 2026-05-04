package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class zzcdw extends zzcdu {
    protected zzcej zzius;
    private AppMeasurement.EventInterceptor zziut;
    private final Set<AppMeasurement.OnEventListener> zziuu;
    private boolean zziuv;
    private final AtomicReference<String> zziuw;

    protected zzcdw(zzccw zzccwVar) {
        super(zzccwVar);
        this.zziuu = new CopyOnWriteArraySet();
        this.zziuw = new AtomicReference<>();
    }

    public static int getMaxUserProperties(String str) {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        return zzcax.zzawb();
    }

    private final void zza(AppMeasurement.ConditionalUserProperty conditionalUserProperty) throws IllegalStateException {
        long jCurrentTimeMillis = zzvx().currentTimeMillis();
        com.google.android.gms.common.internal.zzbp.zzu(conditionalUserProperty);
        com.google.android.gms.common.internal.zzbp.zzgg(conditionalUserProperty.mName);
        com.google.android.gms.common.internal.zzbp.zzgg(conditionalUserProperty.mOrigin);
        com.google.android.gms.common.internal.zzbp.zzu(conditionalUserProperty.mValue);
        conditionalUserProperty.mCreationTimestamp = jCurrentTimeMillis;
        String str = conditionalUserProperty.mName;
        Object obj = conditionalUserProperty.mValue;
        if (zzaui().zzjy(str) != 0) {
            zzaum().zzaye().zzj("Invalid conditional user property name", zzauh().zzje(str));
            return;
        }
        if (zzaui().zzl(str, obj) != 0) {
            zzaum().zzaye().zze("Invalid conditional user property value", zzauh().zzje(str), obj);
            return;
        }
        Object objZzm = zzaui().zzm(str, obj);
        if (objZzm == null) {
            zzaum().zzaye().zze("Unable to normalize conditional user property value", zzauh().zzje(str), obj);
            return;
        }
        conditionalUserProperty.mValue = objZzm;
        long j = conditionalUserProperty.mTriggerTimeout;
        if (!TextUtils.isEmpty(conditionalUserProperty.mTriggerEventName) && (j > zzcax.zzawd() || j < 1)) {
            zzaum().zzaye().zze("Invalid conditional user property timeout", zzauh().zzje(str), Long.valueOf(j));
            return;
        }
        long j2 = conditionalUserProperty.mTimeToLive;
        if (j2 > zzcax.zzawe() || j2 < 1) {
            zzaum().zzaye().zze("Invalid conditional user property time to live", zzauh().zzje(str), Long.valueOf(j2));
        } else {
            zzaul().zzg(new zzcdy(this, conditionalUserProperty));
        }
    }

    private final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) throws IllegalStateException {
        Bundle bundle2;
        if (bundle == null) {
            bundle2 = new Bundle();
        } else {
            bundle2 = new Bundle(bundle);
            for (String str4 : bundle2.keySet()) {
                Object obj = bundle2.get(str4);
                if (obj instanceof Bundle) {
                    bundle2.putBundle(str4, new Bundle((Bundle) obj));
                } else if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 < parcelableArr.length) {
                            if (parcelableArr[i2] instanceof Bundle) {
                                parcelableArr[i2] = new Bundle((Bundle) parcelableArr[i2]);
                            }
                            i = i2 + 1;
                        }
                    }
                } else if (obj instanceof ArrayList) {
                    ArrayList arrayList = (ArrayList) obj;
                    int i3 = 0;
                    while (true) {
                        int i4 = i3;
                        if (i4 < arrayList.size()) {
                            Object obj2 = arrayList.get(i4);
                            if (obj2 instanceof Bundle) {
                                arrayList.set(i4, new Bundle((Bundle) obj2));
                            }
                            i3 = i4 + 1;
                        }
                    }
                }
            }
        }
        zzaul().zzg(new zzcee(this, str, str2, j, bundle2, z, z2, z3, str3));
    }

    private final void zza(String str, String str2, long j, Object obj) throws IllegalStateException {
        zzaul().zzg(new zzcef(this, str, str2, obj, j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) throws IllegalStateException {
        zza(str, str2, zzvx().currentTimeMillis(), bundle, true, z2, z3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zza(String str, String str2, Object obj, long j) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzgg(str2);
        zzuj();
        zzatw();
        zzwk();
        if (!this.zzikh.isEnabled()) {
            zzaum().zzayj().log("User property not set since app measurement is disabled");
        } else if (this.zzikh.zzayw()) {
            zzaum().zzayj().zze("Setting user property (FE)", zzauh().zzjc(str2), obj);
            zzaud().zzb(new zzcft(str2, j, obj, str));
        }
    }

    private final void zza(String str, String str2, String str3, Bundle bundle) throws IllegalStateException {
        long jCurrentTimeMillis = zzvx().currentTimeMillis();
        com.google.android.gms.common.internal.zzbp.zzgg(str2);
        AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
        conditionalUserProperty.mAppId = str;
        conditionalUserProperty.mName = str2;
        conditionalUserProperty.mCreationTimestamp = jCurrentTimeMillis;
        if (str3 != null) {
            conditionalUserProperty.mExpiredEventName = str3;
            conditionalUserProperty.mExpiredEventParams = bundle;
        }
        zzaul().zzg(new zzcdz(this, conditionalUserProperty));
    }

    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        if (zzaul().zzayt()) {
            zzaum().zzaye().log("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        }
        zzaul();
        if (zzccr.zzaq()) {
            zzaum().zzaye().log("Cannot get user properties from main thread");
            return Collections.emptyMap();
        }
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.zzikh.zzaul().zzg(new zzceb(this, atomicReference, str, str2, str3, z));
            try {
                atomicReference.wait(5000L);
            } catch (InterruptedException e) {
                zzaum().zzayg().zzj("Interrupted waiting for get user properties", e);
            }
        }
        List<zzcft> list = (List) atomicReference.get();
        if (list == null) {
            zzaum().zzayg().log("Timed out waiting for get user properties");
            return Collections.emptyMap();
        }
        ArrayMap arrayMap = new ArrayMap(list.size());
        for (zzcft zzcftVar : list) {
            arrayMap.put(zzcftVar.name, zzcftVar.getValue());
        }
        return arrayMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzb(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        zzuj();
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(conditionalUserProperty);
        com.google.android.gms.common.internal.zzbp.zzgg(conditionalUserProperty.mName);
        com.google.android.gms.common.internal.zzbp.zzgg(conditionalUserProperty.mOrigin);
        com.google.android.gms.common.internal.zzbp.zzu(conditionalUserProperty.mValue);
        if (!this.zzikh.isEnabled()) {
            zzaum().zzayj().log("Conditional property not sent since Firebase Analytics is disabled");
            return;
        }
        zzcft zzcftVar = new zzcft(conditionalUserProperty.mName, conditionalUserProperty.mTriggeredTimestamp, conditionalUserProperty.mValue, conditionalUserProperty.mOrigin);
        try {
            zzcbk zzcbkVarZza = zzaui().zza(conditionalUserProperty.mTriggeredEventName, conditionalUserProperty.mTriggeredEventParams, conditionalUserProperty.mOrigin, 0L, true, false);
            zzaud().zzf(new zzcav(conditionalUserProperty.mAppId, conditionalUserProperty.mOrigin, zzcftVar, conditionalUserProperty.mCreationTimestamp, false, conditionalUserProperty.mTriggerEventName, zzaui().zza(conditionalUserProperty.mTimedOutEventName, conditionalUserProperty.mTimedOutEventParams, conditionalUserProperty.mOrigin, 0L, true, false), conditionalUserProperty.mTriggerTimeout, zzcbkVarZza, conditionalUserProperty.mTimeToLive, zzaui().zza(conditionalUserProperty.mExpiredEventName, conditionalUserProperty.mExpiredEventParams, conditionalUserProperty.mOrigin, 0L, true, false)));
        } catch (IllegalArgumentException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) throws IllegalStateException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int length;
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzgg(str2);
        com.google.android.gms.common.internal.zzbp.zzu(bundle);
        zzuj();
        zzwk();
        if (!this.zzikh.isEnabled()) {
            zzaum().zzayj().log("Event not sent since app measurement is disabled");
            return;
        }
        if (!this.zziuv) {
            this.zziuv = true;
            try {
                try {
                    Class.forName("com.google.android.gms.tagmanager.TagManagerService").getDeclaredMethod("initialize", Context.class).invoke(null, getContext());
                } catch (Exception e) {
                    zzaum().zzayg().zzj("Failed to invoke Tag Manager's initialize() method", e);
                }
            } catch (ClassNotFoundException e2) {
                zzaum().zzayi().log("Tag Manager is not found and thus will not be used");
            }
        }
        boolean zEquals = "am".equals(str);
        boolean zZzkd = zzcfw.zzkd(str2);
        if (z && this.zziut != null && !zZzkd && !zEquals) {
            zzaum().zzayj().zze("Passing event to registered event handler (FE)", zzauh().zzjc(str2), zzauh().zzx(bundle));
            this.zziut.interceptEvent(str, str2, bundle, j);
            return;
        }
        if (this.zzikh.zzayw()) {
            int iZzjw = zzaui().zzjw(str2);
            if (iZzjw != 0) {
                zzaui();
                this.zzikh.zzaui().zza(str3, iZzjw, "_ev", zzcfw.zza(str2, zzcax.zzavo(), true), str2 != null ? str2.length() : 0);
                return;
            }
            List<String> listSingletonList = Collections.singletonList("_o");
            Bundle bundleZza = zzaui().zza(str2, bundle, listSingletonList, z3, true);
            ArrayList arrayList = new ArrayList();
            arrayList.add(bundleZza);
            long jNextLong = zzaui().zzazz().nextLong();
            int i = 0;
            String[] strArr = (String[]) bundleZza.keySet().toArray(new String[bundle.size()]);
            Arrays.sort(strArr);
            int length2 = strArr.length;
            int i2 = 0;
            while (i2 < length2) {
                String str4 = strArr[i2];
                Object obj = bundleZza.get(str4);
                zzaui();
                Bundle[] bundleArrZzac = zzcfw.zzac(obj);
                if (bundleArrZzac != null) {
                    bundleZza.putInt(str4, bundleArrZzac.length);
                    int i3 = 0;
                    while (true) {
                        int i4 = i3;
                        if (i4 >= bundleArrZzac.length) {
                            break;
                        }
                        Bundle bundleZza2 = zzaui().zza("_ep", bundleArrZzac[i4], listSingletonList, z3, false);
                        bundleZza2.putString("_en", str2);
                        bundleZza2.putLong("_eid", jNextLong);
                        bundleZza2.putString("_gn", str4);
                        bundleZza2.putInt("_ll", bundleArrZzac.length);
                        bundleZza2.putInt("_i", i4);
                        arrayList.add(bundleZza2);
                        i3 = i4 + 1;
                    }
                    length = bundleArrZzac.length + i;
                } else {
                    length = i;
                }
                i2++;
                i = length;
            }
            if (i != 0) {
                bundleZza.putLong("_eid", jNextLong);
                bundleZza.putInt("_epc", i);
            }
            zzcax.zzawl();
            zzcen zzcenVarZzazo = zzaue().zzazo();
            if (zzcenVarZzazo != null && !bundleZza.containsKey("_sc")) {
                zzcenVarZzazo.zzivw = true;
            }
            int i5 = 0;
            while (true) {
                int i6 = i5;
                if (i6 >= arrayList.size()) {
                    break;
                }
                Bundle bundle2 = (Bundle) arrayList.get(i6);
                String str5 = i6 != 0 ? "_ep" : str2;
                bundle2.putString("_o", str);
                if (!bundle2.containsKey("_sc")) {
                    zzcek.zza(zzcenVarZzazo, bundle2);
                }
                Bundle bundleZzy = z2 ? zzaui().zzy(bundle2) : bundle2;
                zzaum().zzayj().zze("Logging event (FE)", zzauh().zzjc(str2), zzauh().zzx(bundleZzy));
                zzaud().zzc(new zzcbk(str5, new zzcbh(bundleZzy), str, j), str3);
                if (!zEquals) {
                    Iterator<AppMeasurement.OnEventListener> it = this.zziuu.iterator();
                    while (it.hasNext()) {
                        it.next().onEvent(str, str2, new Bundle(bundleZzy), j);
                    }
                }
                i5 = i6 + 1;
            }
            zzcax.zzawl();
            if (zzaue().zzazo() == null || !AppMeasurement.Event.APP_EXCEPTION.equals(str2)) {
                return;
            }
            zzauk().zzbs(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzbp(boolean z) {
        zzuj();
        zzatw();
        zzwk();
        zzaum().zzayj().zzj("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzaun().setMeasurementEnabled(z);
        zzaud().zzazq();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzc(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        zzuj();
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(conditionalUserProperty);
        com.google.android.gms.common.internal.zzbp.zzgg(conditionalUserProperty.mName);
        if (!this.zzikh.isEnabled()) {
            zzaum().zzayj().log("Conditional property not cleared since Firebase Analytics is disabled");
            return;
        }
        try {
            zzaud().zzf(new zzcav(conditionalUserProperty.mAppId, conditionalUserProperty.mOrigin, new zzcft(conditionalUserProperty.mName, 0L, null, null), conditionalUserProperty.mCreationTimestamp, conditionalUserProperty.mActive, conditionalUserProperty.mTriggerEventName, null, conditionalUserProperty.mTriggerTimeout, null, conditionalUserProperty.mTimeToLive, zzaui().zza(conditionalUserProperty.mExpiredEventName, conditionalUserProperty.mExpiredEventParams, conditionalUserProperty.mOrigin, conditionalUserProperty.mCreationTimestamp, true, false)));
        } catch (IllegalArgumentException e) {
        }
    }

    private final List<AppMeasurement.ConditionalUserProperty> zzk(String str, String str2, String str3) {
        if (zzaul().zzayt()) {
            zzaum().zzaye().log("Cannot get conditional user properties from analytics worker thread");
            return Collections.emptyList();
        }
        zzaul();
        if (zzccr.zzaq()) {
            zzaum().zzaye().log("Cannot get conditional user properties from main thread");
            return Collections.emptyList();
        }
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.zzikh.zzaul().zzg(new zzcea(this, atomicReference, str, str2, str3));
            try {
                atomicReference.wait(5000L);
            } catch (InterruptedException e) {
                zzaum().zzayg().zze("Interrupted waiting for get conditional user properties", str, e);
            }
        }
        List<zzcav> list = (List) atomicReference.get();
        if (list == null) {
            zzaum().zzayg().zzj("Timed out waiting for get conditional user properties", str);
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (zzcav zzcavVar : list) {
            AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
            conditionalUserProperty.mAppId = str;
            conditionalUserProperty.mOrigin = str2;
            conditionalUserProperty.mCreationTimestamp = zzcavVar.zzimh;
            conditionalUserProperty.mName = zzcavVar.zzimg.name;
            conditionalUserProperty.mValue = zzcavVar.zzimg.getValue();
            conditionalUserProperty.mActive = zzcavVar.zzimi;
            conditionalUserProperty.mTriggerEventName = zzcavVar.zzimj;
            if (zzcavVar.zzimk != null) {
                conditionalUserProperty.mTimedOutEventName = zzcavVar.zzimk.name;
                if (zzcavVar.zzimk.zzinq != null) {
                    conditionalUserProperty.mTimedOutEventParams = zzcavVar.zzimk.zzinq.zzaya();
                }
            }
            conditionalUserProperty.mTriggerTimeout = zzcavVar.zziml;
            if (zzcavVar.zzimm != null) {
                conditionalUserProperty.mTriggeredEventName = zzcavVar.zzimm.name;
                if (zzcavVar.zzimm.zzinq != null) {
                    conditionalUserProperty.mTriggeredEventParams = zzcavVar.zzimm.zzinq.zzaya();
                }
            }
            conditionalUserProperty.mTriggeredTimestamp = zzcavVar.zzimg.zziwy;
            conditionalUserProperty.mTimeToLive = zzcavVar.zzimn;
            if (zzcavVar.zzimo != null) {
                conditionalUserProperty.mExpiredEventName = zzcavVar.zzimo.name;
                if (zzcavVar.zzimo.zzinq != null) {
                    conditionalUserProperty.mExpiredEventParams = zzcavVar.zzimo.zzinq.zzaya();
                }
            }
            arrayList.add(conditionalUserProperty);
        }
        return arrayList;
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws IllegalStateException {
        zzatw();
        zza((String) null, str, str2, bundle);
    }

    public final void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        zzatv();
        zza(str, str2, str3, bundle);
    }

    public final Task<String> getAppInstanceId() {
        try {
            String strZzayo = zzaun().zzayo();
            return strZzayo != null ? Tasks.forResult(strZzayo) : Tasks.call(zzaul().zzayu(), new zzceh(this));
        } catch (Exception e) {
            zzaum().zzayg().log("Failed to schedule task for getAppInstanceId");
            return Tasks.forException(e);
        }
    }

    public final List<AppMeasurement.ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        zzatw();
        return zzk(null, str, str2);
    }

    public final List<AppMeasurement.ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        zzatv();
        return zzk(str, str2, str3);
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        zzatw();
        return zzb(null, str, str2, z);
    }

    public final Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        zzatv();
        return zzb(str, str2, str3, z);
    }

    public final void registerOnMeasurementEventListener(AppMeasurement.OnEventListener onEventListener) {
        zzatw();
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(onEventListener);
        if (this.zziuu.add(onEventListener)) {
            return;
        }
        zzaum().zzayg().log("OnEventListener already registered");
    }

    public final void setConditionalUserProperty(AppMeasurement.ConditionalUserProperty conditionalUserProperty) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzu(conditionalUserProperty);
        zzatw();
        AppMeasurement.ConditionalUserProperty conditionalUserProperty2 = new AppMeasurement.ConditionalUserProperty(conditionalUserProperty);
        if (!TextUtils.isEmpty(conditionalUserProperty2.mAppId)) {
            zzaum().zzayg().log("Package name should be null when calling setConditionalUserProperty");
        }
        conditionalUserProperty2.mAppId = null;
        zza(conditionalUserProperty2);
    }

    public final void setConditionalUserPropertyAs(AppMeasurement.ConditionalUserProperty conditionalUserProperty) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzu(conditionalUserProperty);
        com.google.android.gms.common.internal.zzbp.zzgg(conditionalUserProperty.mAppId);
        zzatv();
        zza(new AppMeasurement.ConditionalUserProperty(conditionalUserProperty));
    }

    @WorkerThread
    public final void setEventInterceptor(AppMeasurement.EventInterceptor eventInterceptor) {
        zzuj();
        zzatw();
        zzwk();
        if (eventInterceptor != null && eventInterceptor != this.zziut) {
            com.google.android.gms.common.internal.zzbp.zza(this.zziut == null, "EventInterceptor already set.");
        }
        this.zziut = eventInterceptor;
    }

    public final void setMeasurementEnabled(boolean z) throws IllegalStateException {
        zzwk();
        zzatw();
        zzaul().zzg(new zzcdx(this, z));
    }

    public final void setMinimumSessionDuration(long j) throws IllegalStateException {
        zzatw();
        zzaul().zzg(new zzcec(this, j));
    }

    public final void setSessionTimeoutDuration(long j) throws IllegalStateException {
        zzatw();
        zzaul().zzg(new zzced(this, j));
    }

    public final void unregisterOnMeasurementEventListener(AppMeasurement.OnEventListener onEventListener) {
        zzatw();
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(onEventListener);
        if (this.zziuu.remove(onEventListener)) {
            return;
        }
        zzaum().zzayg().log("OnEventListener had not been registered");
    }

    public final void zza(String str, String str2, Bundle bundle, long j) throws IllegalStateException {
        zzatw();
        zza(str, str2, j, bundle, false, true, true, null);
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) throws IllegalStateException {
        zzatw();
        zza(str, str2, bundle, true, this.zziut == null || zzcfw.zzkd(str2), true, null);
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatv() {
        super.zzatv();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatw() {
        super.zzatw();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatx() {
        super.zzatx();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcan zzaty() {
        return super.zzaty();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcau zzatz() {
        return super.zzatz();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcdw zzaua() {
        return super.zzaua();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbr zzaub() {
        return super.zzaub();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbe zzauc() {
        return super.zzauc();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzceo zzaud() {
        return super.zzaud();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcek zzaue() {
        return super.zzaue();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbs zzauf() {
        return super.zzauf();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcay zzaug() {
        return super.zzaug();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbu zzauh() {
        return super.zzauh();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcfw zzaui() {
        return super.zzaui();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzccq zzauj() {
        return super.zzauj();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcfl zzauk() {
        return super.zzauk();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzccr zzaul() {
        return super.zzaul();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbw zzaum() {
        return super.zzaum();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcch zzaun() {
        return super.zzaun();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcax zzauo() {
        return super.zzauo();
    }

    @Nullable
    public final String zzayo() {
        zzatw();
        return this.zziuw.get();
    }

    public final void zzb(String str, String str2, Object obj) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        long jCurrentTimeMillis = zzvx().currentTimeMillis();
        int iZzjy = zzaui().zzjy(str2);
        if (iZzjy != 0) {
            zzaui();
            this.zzikh.zzaui().zza(iZzjy, "_ev", zzcfw.zza(str2, zzcax.zzavp(), true), str2 != null ? str2.length() : 0);
            return;
        }
        if (obj == null) {
            zza(str, str2, jCurrentTimeMillis, (Object) null);
            return;
        }
        int iZzl = zzaui().zzl(str2, obj);
        if (iZzl != 0) {
            zzaui();
            this.zzikh.zzaui().zza(iZzl, "_ev", zzcfw.zza(str2, zzcax.zzavp(), true), ((obj instanceof String) || (obj instanceof CharSequence)) ? String.valueOf(obj).length() : 0);
        } else {
            Object objZzm = zzaui().zzm(str2, obj);
            if (objZzm != null) {
                zza(str, str2, jCurrentTimeMillis, objZzm);
            }
        }
    }

    @Nullable
    final String zzbc(long j) {
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            zzaul().zzg(new zzcei(this, atomicReference));
            try {
                atomicReference.wait(j);
            } catch (InterruptedException e) {
                zzaum().zzayg().log("Interrupted waiting for app instance id");
                return null;
            }
        }
        return (String) atomicReference.get();
    }

    public final List<zzcft> zzbq(boolean z) {
        zzatw();
        zzwk();
        zzaum().zzayj().log("Fetching user attributes (FE)");
        if (zzaul().zzayt()) {
            zzaum().zzaye().log("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        }
        zzaul();
        if (zzccr.zzaq()) {
            zzaum().zzaye().log("Cannot get all user properties from main thread");
            return Collections.emptyList();
        }
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.zzikh.zzaul().zzg(new zzceg(this, atomicReference, z));
            try {
                atomicReference.wait(5000L);
            } catch (InterruptedException e) {
                zzaum().zzayg().zzj("Interrupted waiting for get user properties", e);
            }
        }
        List<zzcft> list = (List) atomicReference.get();
        if (list != null) {
            return list;
        }
        zzaum().zzayg().log("Timed out waiting for get user properties");
        return Collections.emptyList();
    }

    public final void zzc(String str, String str2, Bundle bundle) {
        zzatw();
        zza(str, str2, bundle, true, this.zziut == null || zzcfw.zzkd(str2), false, null);
    }

    final void zzjk(@Nullable String str) {
        this.zziuw.set(str);
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    @Override // com.google.android.gms.internal.zzcdu
    protected final void zzuk() {
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ com.google.android.gms.common.util.zzd zzvx() {
        return super.zzvx();
    }
}
