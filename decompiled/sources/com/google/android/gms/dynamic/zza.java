package com.google.android.gms.dynamic;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.LinkedList;

/* loaded from: classes.dex */
public abstract class zza<T extends LifecycleDelegate> {
    private T zzgot;
    private Bundle zzgou;
    private LinkedList<zzi> zzgov;
    private final zzo<T> zzgow = new zzb(this);

    static /* synthetic */ Bundle zza(zza zzaVar, Bundle bundle) {
        zzaVar.zzgou = null;
        return null;
    }

    private final void zza(Bundle bundle, zzi zziVar) {
        if (this.zzgot != null) {
            zziVar.zzb(this.zzgot);
            return;
        }
        if (this.zzgov == null) {
            this.zzgov = new LinkedList<>();
        }
        this.zzgov.add(zziVar);
        if (bundle != null) {
            if (this.zzgou == null) {
                this.zzgou = (Bundle) bundle.clone();
            } else {
                this.zzgou.putAll(bundle);
            }
        }
        zza(this.zzgow);
    }

    public static void zzb(FrameLayout frameLayout) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int iIsGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(context);
        String strZzi = zzt.zzi(context, iIsGooglePlayServicesAvailable);
        String strZzk = zzt.zzk(context, iIsGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(strZzi);
        linearLayout.addView(textView);
        Intent intentZza = com.google.android.gms.common.zze.zza(context, iIsGooglePlayServicesAvailable, null);
        if (intentZza != null) {
            Button button = new Button(context);
            button.setId(R.id.button1);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(strZzk);
            linearLayout.addView(button);
            button.setOnClickListener(new zzf(context, intentZza));
        }
    }

    private final void zzcv(int i) {
        while (!this.zzgov.isEmpty() && this.zzgov.getLast().getState() >= i) {
            this.zzgov.removeLast();
        }
    }

    public final void onCreate(Bundle bundle) {
        zza(bundle, new zzd(this, bundle));
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        zza(bundle, new zze(this, frameLayout, layoutInflater, viewGroup, bundle));
        if (this.zzgot == null) {
            zza(frameLayout);
        }
        return frameLayout;
    }

    public final void onDestroy() {
        if (this.zzgot != null) {
            this.zzgot.onDestroy();
        } else {
            zzcv(1);
        }
    }

    public final void onDestroyView() {
        if (this.zzgot != null) {
            this.zzgot.onDestroyView();
        } else {
            zzcv(2);
        }
    }

    public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
        zza(bundle2, new zzc(this, activity, bundle, bundle2));
    }

    public final void onLowMemory() {
        if (this.zzgot != null) {
            this.zzgot.onLowMemory();
        }
    }

    public final void onPause() {
        if (this.zzgot != null) {
            this.zzgot.onPause();
        } else {
            zzcv(5);
        }
    }

    public final void onResume() {
        zza((Bundle) null, new zzh(this));
    }

    public final void onSaveInstanceState(Bundle bundle) {
        if (this.zzgot != null) {
            this.zzgot.onSaveInstanceState(bundle);
        } else if (this.zzgou != null) {
            bundle.putAll(this.zzgou);
        }
    }

    public final void onStart() {
        zza((Bundle) null, new zzg(this));
    }

    public final void onStop() {
        if (this.zzgot != null) {
            this.zzgot.onStop();
        } else {
            zzcv(4);
        }
    }

    protected void zza(FrameLayout frameLayout) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int iIsGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(context);
        String strZzi = zzt.zzi(context, iIsGooglePlayServicesAvailable);
        String strZzk = zzt.zzk(context, iIsGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(strZzi);
        linearLayout.addView(textView);
        Intent intentZza = com.google.android.gms.common.zze.zza(context, iIsGooglePlayServicesAvailable, null);
        if (intentZza != null) {
            Button button = new Button(context);
            button.setId(R.id.button1);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(strZzk);
            linearLayout.addView(button);
            button.setOnClickListener(new zzf(context, intentZza));
        }
    }

    protected abstract void zza(zzo<T> zzoVar);

    public final T zzaoc() {
        return this.zzgot;
    }
}
