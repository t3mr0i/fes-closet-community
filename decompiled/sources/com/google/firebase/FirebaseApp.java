package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.support.v4.view.MotionEventCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.internal.zzk;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.internal.zzeam;
import com.google.android.gms.internal.zzean;
import com.google.android.gms.internal.zzeao;
import com.google.android.gms.internal.zzeap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class FirebaseApp {
    public static final String DEFAULT_APP_NAME = "[DEFAULT]";
    private final Context mApplicationContext;
    private final String mName;
    private final FirebaseOptions zzlge;
    private zzeao zzlgk;
    private static final List<String> zzlfz = Arrays.asList("com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId");
    private static final List<String> zzlga = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    private static final List<String> zzlgb = Arrays.asList("com.google.android.gms.measurement.AppMeasurement");
    private static final List<String> zzlgc = Arrays.asList(new String[0]);
    private static final Set<String> zzlgd = Collections.emptySet();
    private static final Object zzaqc = new Object();
    static final Map<String, FirebaseApp> zzhtm = new ArrayMap();
    private final AtomicBoolean zzlgf = new AtomicBoolean(false);
    private final AtomicBoolean zzlgg = new AtomicBoolean();
    private final List<zzb> zzlgh = new CopyOnWriteArrayList();
    private final List<zza> zzlgi = new CopyOnWriteArrayList();
    private final List<Object> zzlgj = new CopyOnWriteArrayList();
    private zzc zzlgl = new zzeam();

    public interface zza {
        void zzbe(boolean z);
    }

    public interface zzb {
        void zzb(@NonNull zzeap zzeapVar);
    }

    public interface zzc {
    }

    @TargetApi(MotionEventCompat.AXIS_DISTANCE)
    static class zzd extends BroadcastReceiver {
        private static AtomicReference<zzd> zzlgm = new AtomicReference<>();
        private final Context mApplicationContext;

        private zzd(Context context) {
            this.mApplicationContext = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void zzef(Context context) {
            if (zzlgm.get() == null) {
                zzd zzdVar = new zzd(context);
                if (zzlgm.compareAndSet(null, zzdVar)) {
                    context.registerReceiver(zzdVar, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            synchronized (FirebaseApp.zzaqc) {
                Iterator<FirebaseApp> it = FirebaseApp.zzhtm.values().iterator();
                while (it.hasNext()) {
                    it.next().zzbnq();
                }
            }
            this.mApplicationContext.unregisterReceiver(this);
        }
    }

    private FirebaseApp(Context context, String str, FirebaseOptions firebaseOptions) {
        this.mApplicationContext = (Context) zzbp.zzu(context);
        this.mName = zzbp.zzgg(str);
        this.zzlge = (FirebaseOptions) zzbp.zzu(firebaseOptions);
    }

    public static List<FirebaseApp> getApps(Context context) {
        ArrayList arrayList;
        zzean.zzep(context);
        synchronized (zzaqc) {
            arrayList = new ArrayList(zzhtm.values());
            zzean.zzbyu();
            Set<String> setZzbyv = zzean.zzbyv();
            setZzbyv.removeAll(zzhtm.keySet());
            for (String str : setZzbyv) {
                zzean.zzql(str);
                arrayList.add(initializeApp(context, null, str));
            }
        }
        return arrayList;
    }

    @Nullable
    public static FirebaseApp getInstance() {
        FirebaseApp firebaseApp;
        synchronized (zzaqc) {
            firebaseApp = zzhtm.get(DEFAULT_APP_NAME);
            if (firebaseApp == null) {
                String strZzall = zzq.zzall();
                throw new IllegalStateException(new StringBuilder(String.valueOf(strZzall).length() + 116).append("Default FirebaseApp is not initialized in this process ").append(strZzall).append(". Make sure to call FirebaseApp.initializeApp(Context) first.").toString());
            }
        }
        return firebaseApp;
    }

    public static FirebaseApp getInstance(@NonNull String str) {
        FirebaseApp firebaseApp;
        String strConcat;
        synchronized (zzaqc) {
            firebaseApp = zzhtm.get(str.trim());
            if (firebaseApp == null) {
                List<String> listZzbnp = zzbnp();
                if (listZzbnp.isEmpty()) {
                    strConcat = "";
                } else {
                    String strValueOf = String.valueOf(TextUtils.join(", ", listZzbnp));
                    strConcat = strValueOf.length() != 0 ? "Available app names: ".concat(strValueOf) : new String("Available app names: ");
                }
                throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", str, strConcat));
            }
        }
        return firebaseApp;
    }

    @Nullable
    public static FirebaseApp initializeApp(Context context) {
        FirebaseApp firebaseAppInitializeApp;
        synchronized (zzaqc) {
            if (zzhtm.containsKey(DEFAULT_APP_NAME)) {
                firebaseAppInitializeApp = getInstance();
            } else {
                FirebaseOptions firebaseOptionsFromResource = FirebaseOptions.fromResource(context);
                firebaseAppInitializeApp = firebaseOptionsFromResource == null ? null : initializeApp(context, firebaseOptionsFromResource);
            }
        }
        return firebaseAppInitializeApp;
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions) {
        return initializeApp(context, firebaseOptions, DEFAULT_APP_NAME);
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions, String str) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        FirebaseApp firebaseApp;
        zzean.zzep(context);
        if (context.getApplicationContext() instanceof Application) {
            zzk.zza((Application) context.getApplicationContext());
            zzk.zzafz().zza(new com.google.firebase.zza());
        }
        String strTrim = str.trim();
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (zzaqc) {
            zzbp.zza(!zzhtm.containsKey(strTrim), new StringBuilder(String.valueOf(strTrim).length() + 33).append("FirebaseApp name ").append(strTrim).append(" already exists!").toString());
            zzbp.zzb(context, "Application context cannot be null.");
            firebaseApp = new FirebaseApp(context, strTrim, firebaseOptions);
            zzhtm.put(strTrim, firebaseApp);
        }
        zzean.zze(firebaseApp);
        firebaseApp.zza(FirebaseApp.class, firebaseApp, zzlfz);
        if (firebaseApp.zzbnn()) {
            firebaseApp.zza(FirebaseApp.class, firebaseApp, zzlga);
            firebaseApp.zza(Context.class, firebaseApp.getApplicationContext(), zzlgb);
        }
        return firebaseApp;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T> void zza(Class<T> cls, T t, Iterable<String> iterable) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        boolean zIsDeviceProtectedStorage = ContextCompat.isDeviceProtectedStorage(this.mApplicationContext);
        if (zIsDeviceProtectedStorage) {
            zzd.zzef(this.mApplicationContext);
        }
        for (String str : iterable) {
            if (zIsDeviceProtectedStorage) {
                try {
                } catch (ClassNotFoundException e) {
                    if (zzlgd.contains(str)) {
                        throw new IllegalStateException(String.valueOf(str).concat(" is missing, but is required. Check if it has been removed by Proguard."));
                    }
                    Log.d("FirebaseApp", String.valueOf(str).concat(" is not linked. Skipping initialization."));
                } catch (IllegalAccessException e2) {
                    String strValueOf = String.valueOf(str);
                    Log.wtf("FirebaseApp", strValueOf.length() != 0 ? "Failed to initialize ".concat(strValueOf) : new String("Failed to initialize "), e2);
                } catch (NoSuchMethodException e3) {
                    throw new IllegalStateException(String.valueOf(str).concat("#getInstance has been removed by Proguard. Add keep rule to prevent it."));
                } catch (InvocationTargetException e4) {
                    Log.wtf("FirebaseApp", "Firebase API initialization failure.", e4);
                }
                if (zzlgc.contains(str)) {
                }
            }
            Method method = Class.forName(str).getMethod("getInstance", cls);
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                method.invoke(null, t);
            }
        }
    }

    public static void zzbe(boolean z) {
        synchronized (zzaqc) {
            ArrayList arrayList = new ArrayList(zzhtm.values());
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                FirebaseApp firebaseApp = (FirebaseApp) obj;
                if (firebaseApp.zzlgf.get()) {
                    firebaseApp.zzbz(z);
                }
            }
        }
    }

    private final void zzbnm() {
        zzbp.zza(!this.zzlgg.get(), "FirebaseApp was deleted");
    }

    private static List<String> zzbnp() {
        ArraySet arraySet = new ArraySet();
        synchronized (zzaqc) {
            Iterator<FirebaseApp> it = zzhtm.values().iterator();
            while (it.hasNext()) {
                arraySet.add(it.next().getName());
            }
            if (zzean.zzbyu() != null) {
                arraySet.addAll(zzean.zzbyv());
            }
        }
        ArrayList arrayList = new ArrayList(arraySet);
        Collections.sort(arrayList);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzbnq() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        zza(FirebaseApp.class, this, zzlfz);
        if (zzbnn()) {
            zza(FirebaseApp.class, this, zzlga);
            zza(Context.class, this.mApplicationContext, zzlgb);
        }
    }

    private final void zzbz(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        Iterator<zza> it = this.zzlgi.iterator();
        while (it.hasNext()) {
            it.next().zzbe(z);
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof FirebaseApp) {
            return this.mName.equals(((FirebaseApp) obj).getName());
        }
        return false;
    }

    @NonNull
    public Context getApplicationContext() {
        zzbnm();
        return this.mApplicationContext;
    }

    @NonNull
    public String getName() {
        zzbnm();
        return this.mName;
    }

    @NonNull
    public FirebaseOptions getOptions() {
        zzbnm();
        return this.zzlge;
    }

    public final Task<GetTokenResult> getToken(boolean z) {
        zzbnm();
        return this.zzlgk == null ? Tasks.forException(new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.")) : this.zzlgk.zzca(z);
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    public void setAutomaticResourceManagementEnabled(boolean z) {
        zzbnm();
        if (this.zzlgf.compareAndSet(!z, z)) {
            boolean zZzaga = zzk.zzafz().zzaga();
            if (z && zZzaga) {
                zzbz(true);
            } else {
                if (z || !zZzaga) {
                    return;
                }
                zzbz(false);
            }
        }
    }

    public String toString() {
        return zzbf.zzt(this).zzg("name", this.mName).zzg("options", this.zzlge).toString();
    }

    public final void zza(@NonNull zzeao zzeaoVar) {
        this.zzlgk = (zzeao) zzbp.zzu(zzeaoVar);
    }

    @UiThread
    public final void zza(@NonNull zzeap zzeapVar) {
        Log.d("FirebaseApp", "Notifying auth state listeners.");
        Iterator<zzb> it = this.zzlgh.iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next().zzb(zzeapVar);
            i++;
        }
        Log.d("FirebaseApp", String.format("Notified %d auth state listeners.", Integer.valueOf(i)));
    }

    public final void zza(zza zzaVar) {
        zzbnm();
        if (this.zzlgf.get() && zzk.zzafz().zzaga()) {
            zzaVar.zzbe(true);
        }
        this.zzlgi.add(zzaVar);
    }

    public final void zza(@NonNull zzb zzbVar) {
        zzbnm();
        zzbp.zzu(zzbVar);
        this.zzlgh.add(zzbVar);
        this.zzlgh.size();
    }

    public final boolean zzbnn() {
        return DEFAULT_APP_NAME.equals(getName());
    }

    public final String zzbno() {
        String strZzk = com.google.android.gms.common.util.zzb.zzk(getName().getBytes());
        String strZzk2 = com.google.android.gms.common.util.zzb.zzk(getOptions().getApplicationId().getBytes());
        return new StringBuilder(String.valueOf(strZzk).length() + 1 + String.valueOf(strZzk2).length()).append(strZzk).append("+").append(strZzk2).toString();
    }
}
