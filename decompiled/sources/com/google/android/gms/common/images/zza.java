package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.internal.zzbcj;

/* loaded from: classes.dex */
public abstract class zza {
    final zzb zzfro;
    protected int zzfrq;
    private int zzfrp = 0;
    private boolean zzfrr = false;
    private boolean zzfrs = true;
    private boolean zzfrt = false;
    private boolean zzfru = true;

    public zza(Uri uri, int i) {
        this.zzfrq = 0;
        this.zzfro = new zzb(uri);
        this.zzfrq = i;
    }

    final void zza(Context context, Bitmap bitmap, boolean z) {
        com.google.android.gms.common.internal.zzc.zzr(bitmap);
        zza(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    final void zza(Context context, zzbcj zzbcjVar) {
        if (this.zzfru) {
            zza(null, false, true, false);
        }
    }

    final void zza(Context context, zzbcj zzbcjVar, boolean z) throws Resources.NotFoundException {
        Drawable drawable = null;
        if (this.zzfrq != 0) {
            drawable = context.getResources().getDrawable(this.zzfrq);
        }
        zza(drawable, z, false, false);
    }

    protected abstract void zza(Drawable drawable, boolean z, boolean z2, boolean z3);

    protected final boolean zzc(boolean z, boolean z2) {
        return (!this.zzfrs || z2 || z) ? false : true;
    }
}
