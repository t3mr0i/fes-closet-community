package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.zzbf;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class zzd extends zza {
    private WeakReference<ImageManager.OnImageLoadedListener> zzfrw;

    public zzd(ImageManager.OnImageLoadedListener onImageLoadedListener, Uri uri) {
        super(uri, 0);
        com.google.android.gms.common.internal.zzc.zzr(onImageLoadedListener);
        this.zzfrw = new WeakReference<>(onImageLoadedListener);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzd)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzd zzdVar = (zzd) obj;
        ImageManager.OnImageLoadedListener onImageLoadedListener = this.zzfrw.get();
        ImageManager.OnImageLoadedListener onImageLoadedListener2 = zzdVar.zzfrw.get();
        return onImageLoadedListener2 != null && onImageLoadedListener != null && zzbf.equal(onImageLoadedListener2, onImageLoadedListener) && zzbf.equal(zzdVar.zzfro, this.zzfro);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzfro});
    }

    @Override // com.google.android.gms.common.images.zza
    protected final void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
        ImageManager.OnImageLoadedListener onImageLoadedListener;
        if (z2 || (onImageLoadedListener = this.zzfrw.get()) == null) {
            return;
        }
        onImageLoadedListener.onImageLoaded(this.zzfro.uri, drawable, z3);
    }
}
