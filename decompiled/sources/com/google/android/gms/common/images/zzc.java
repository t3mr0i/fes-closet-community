package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbcd;
import com.google.android.gms.internal.zzbci;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class zzc extends zza {
    private WeakReference<ImageView> zzfrv;

    public zzc(ImageView imageView, int i) {
        super(null, i);
        com.google.android.gms.common.internal.zzc.zzr(imageView);
        this.zzfrv = new WeakReference<>(imageView);
    }

    public zzc(ImageView imageView, Uri uri) {
        super(uri, 0);
        com.google.android.gms.common.internal.zzc.zzr(imageView);
        this.zzfrv = new WeakReference<>(imageView);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzc)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ImageView imageView = this.zzfrv.get();
        ImageView imageView2 = ((zzc) obj).zzfrv.get();
        return (imageView2 == null || imageView == null || !zzbf.equal(imageView2, imageView)) ? false : true;
    }

    public final int hashCode() {
        return 0;
    }

    @Override // com.google.android.gms.common.images.zza
    protected final void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
        Drawable zzbcdVar;
        ImageView imageView = this.zzfrv.get();
        if (imageView != null) {
            boolean z4 = (z2 || z3) ? false : true;
            if (z4 && (imageView instanceof zzbci)) {
                int iZzajd = zzbci.zzajd();
                if (this.zzfrq != 0 && iZzajd == this.zzfrq) {
                    return;
                }
            }
            boolean zZzc = zzc(z, z2);
            if (zZzc) {
                Drawable drawable2 = imageView.getDrawable();
                if (drawable2 == null) {
                    drawable2 = null;
                } else if (drawable2 instanceof zzbcd) {
                    drawable2 = ((zzbcd) drawable2).zzajb();
                }
                zzbcdVar = new zzbcd(drawable2, drawable);
            } else {
                zzbcdVar = drawable;
            }
            imageView.setImageDrawable(zzbcdVar);
            if (imageView instanceof zzbci) {
                zzbci.zzn(z3 ? this.zzfro.uri : null);
                zzbci.zzcb(z4 ? this.zzfrq : 0);
            }
            if (zZzc) {
                ((zzbcd) zzbcdVar).startTransition(250);
            }
        }
    }
}
