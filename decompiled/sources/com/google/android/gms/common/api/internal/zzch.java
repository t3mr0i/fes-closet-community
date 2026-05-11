package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class zzch extends Fragment implements zzcg {
    private static WeakHashMap<Activity, WeakReference<zzch>> zzfoo = new WeakHashMap<>();
    private Bundle zzfoq;
    private Map<String, LifecycleCallback> zzfop = new ArrayMap();
    private int zzbyy = 0;

    public static zzch zzo(Activity activity) {
        zzch zzchVar;
        WeakReference<zzch> weakReference = zzfoo.get(activity);
        if (weakReference == null || (zzchVar = weakReference.get()) == null) {
            try {
                zzchVar = (zzch) activity.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
                if (zzchVar == null || zzchVar.isRemoving()) {
                    zzchVar = new zzch();
                    activity.getFragmentManager().beginTransaction().add(zzchVar, "LifecycleFragmentImpl").commitAllowingStateLoss();
                }
                zzfoo.put(activity, new WeakReference<>(zzchVar));
            } catch (ClassCastException e) {
                throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", e);
            }
        }
        return zzchVar;
    }

    @Override // android.app.Fragment
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        Iterator<LifecycleCallback> it = this.zzfop.values().iterator();
        while (it.hasNext()) {
            it.next().dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // android.app.Fragment
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Iterator<LifecycleCallback> it = this.zzfop.values().iterator();
        while (it.hasNext()) {
            it.next().onActivityResult(i, i2, intent);
        }
    }

    @Override // android.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbyy = 1;
        this.zzfoq = bundle;
        for (Map.Entry<String, LifecycleCallback> entry : this.zzfop.entrySet()) {
            entry.getValue().onCreate(bundle != null ? bundle.getBundle(entry.getKey()) : null);
        }
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.zzbyy = 5;
        Iterator<LifecycleCallback> it = this.zzfop.values().iterator();
        while (it.hasNext()) {
            it.next().onDestroy();
        }
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        this.zzbyy = 3;
        Iterator<LifecycleCallback> it = this.zzfop.values().iterator();
        while (it.hasNext()) {
            it.next().onResume();
        }
    }

    @Override // android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle == null) {
            return;
        }
        for (Map.Entry<String, LifecycleCallback> entry : this.zzfop.entrySet()) {
            Bundle bundle2 = new Bundle();
            entry.getValue().onSaveInstanceState(bundle2);
            bundle.putBundle(entry.getKey(), bundle2);
        }
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        this.zzbyy = 2;
        Iterator<LifecycleCallback> it = this.zzfop.values().iterator();
        while (it.hasNext()) {
            it.next().onStart();
        }
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        this.zzbyy = 4;
        Iterator<LifecycleCallback> it = this.zzfop.values().iterator();
        while (it.hasNext()) {
            it.next().onStop();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzcg
    public final <T extends LifecycleCallback> T zza(String str, Class<T> cls) {
        return cls.cast(this.zzfop.get(str));
    }

    @Override // com.google.android.gms.common.api.internal.zzcg
    public final void zza(String str, @NonNull LifecycleCallback lifecycleCallback) {
        if (this.zzfop.containsKey(str)) {
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 59).append("LifecycleCallback with tag ").append(str).append(" already added to this fragment.").toString());
        }
        this.zzfop.put(str, lifecycleCallback);
        if (this.zzbyy > 0) {
            new Handler(Looper.getMainLooper()).post(new zzci(this, lifecycleCallback, str));
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzcg
    public final Activity zzaik() {
        return getActivity();
    }
}
