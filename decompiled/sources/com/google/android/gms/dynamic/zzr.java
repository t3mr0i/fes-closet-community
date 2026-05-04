package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/* loaded from: classes.dex */
public final class zzr extends zzl {
    private Fragment zzgpg;

    private zzr(Fragment fragment) {
        this.zzgpg = fragment;
    }

    public static zzr zza(Fragment fragment) {
        if (fragment != null) {
            return new zzr(fragment);
        }
        return null;
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final Bundle getArguments() {
        return this.zzgpg.getArguments();
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final int getId() {
        return this.zzgpg.getId();
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final boolean getRetainInstance() {
        return this.zzgpg.getRetainInstance();
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final String getTag() {
        return this.zzgpg.getTag();
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final int getTargetRequestCode() {
        return this.zzgpg.getTargetRequestCode();
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final boolean getUserVisibleHint() {
        return this.zzgpg.getUserVisibleHint();
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final IObjectWrapper getView() {
        return zzn.zzw(this.zzgpg.getView());
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final boolean isAdded() {
        return this.zzgpg.isAdded();
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final boolean isDetached() {
        return this.zzgpg.isDetached();
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final boolean isHidden() {
        return this.zzgpg.isHidden();
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final boolean isInLayout() {
        return this.zzgpg.isInLayout();
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final boolean isRemoving() {
        return this.zzgpg.isRemoving();
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final boolean isResumed() {
        return this.zzgpg.isResumed();
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final boolean isVisible() {
        return this.zzgpg.isVisible();
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final void setHasOptionsMenu(boolean z) {
        this.zzgpg.setHasOptionsMenu(z);
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final void setMenuVisibility(boolean z) {
        this.zzgpg.setMenuVisibility(z);
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final void setRetainInstance(boolean z) {
        this.zzgpg.setRetainInstance(z);
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final void setUserVisibleHint(boolean z) {
        this.zzgpg.setUserVisibleHint(z);
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final void startActivity(Intent intent) {
        this.zzgpg.startActivity(intent);
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final void startActivityForResult(Intent intent, int i) {
        this.zzgpg.startActivityForResult(intent, i);
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final IObjectWrapper zzaod() {
        return zzn.zzw(this.zzgpg.getActivity());
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final zzk zzaoe() {
        return zza(this.zzgpg.getParentFragment());
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final IObjectWrapper zzaof() {
        return zzn.zzw(this.zzgpg.getResources());
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final zzk zzaog() {
        return zza(this.zzgpg.getTargetFragment());
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final void zzv(IObjectWrapper iObjectWrapper) {
        this.zzgpg.registerForContextMenu((View) zzn.zzx(iObjectWrapper));
    }

    @Override // com.google.android.gms.dynamic.zzk
    public final void zzw(IObjectWrapper iObjectWrapper) {
        this.zzgpg.unregisterForContextMenu((View) zzn.zzx(iObjectWrapper));
    }
}
