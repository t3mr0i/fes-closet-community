package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

/* loaded from: classes.dex */
public final class zzbcd extends Drawable implements Drawable.Callback {
    private int mFrom;
    private long zzdqs;
    private boolean zzfrs;
    private int zzfrx;
    private int zzfry;
    private int zzfrz;
    private int zzfsa;
    private int zzfsb;
    private boolean zzfsc;
    private zzbch zzfsd;
    private Drawable zzfse;
    private Drawable zzfsf;
    private boolean zzfsg;
    private boolean zzfsh;
    private boolean zzfsi;
    private int zzfsj;

    public zzbcd(Drawable drawable, Drawable drawable2) {
        this(null);
        drawable = drawable == null ? zzbcf.zzfsk : drawable;
        this.zzfse = drawable;
        drawable.setCallback(this);
        this.zzfsd.zzfsm |= drawable.getChangingConfigurations();
        drawable2 = drawable2 == null ? zzbcf.zzfsk : drawable2;
        this.zzfsf = drawable2;
        drawable2.setCallback(this);
        this.zzfsd.zzfsm |= drawable2.getChangingConfigurations();
    }

    zzbcd(zzbch zzbchVar) {
        this.zzfrx = 0;
        this.zzfrz = 255;
        this.zzfsb = 0;
        this.zzfrs = true;
        this.zzfsd = new zzbch(zzbchVar);
    }

    private final boolean canConstantState() {
        if (!this.zzfsg) {
            this.zzfsh = (this.zzfse.getConstantState() == null || this.zzfsf.getConstantState() == null) ? false : true;
            this.zzfsg = true;
        }
        return this.zzfsh;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        boolean z = false;
        switch (this.zzfrx) {
            case 1:
                this.zzdqs = SystemClock.uptimeMillis();
                this.zzfrx = 2;
                break;
            case 2:
                if (this.zzdqs >= 0) {
                    float fUptimeMillis = (SystemClock.uptimeMillis() - this.zzdqs) / this.zzfsa;
                    z = fUptimeMillis >= 1.0f;
                    if (z) {
                        this.zzfrx = 0;
                    }
                    this.zzfsb = (int) ((Math.min(fUptimeMillis, 1.0f) * this.zzfry) + 0.0f);
                }
            default:
                z = z;
                break;
        }
        int i = this.zzfsb;
        boolean z2 = this.zzfrs;
        Drawable drawable = this.zzfse;
        Drawable drawable2 = this.zzfsf;
        if (z) {
            if (!z2 || i == 0) {
                drawable.draw(canvas);
            }
            if (i == this.zzfrz) {
                drawable2.setAlpha(this.zzfrz);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z2) {
            drawable.setAlpha(this.zzfrz - i);
        }
        drawable.draw(canvas);
        if (z2) {
            drawable.setAlpha(this.zzfrz);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.zzfrz);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.zzfsd.mChangingConfigurations | this.zzfsd.zzfsm;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.zzfsd.mChangingConfigurations = getChangingConfigurations();
        return this.zzfsd;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return Math.max(this.zzfse.getIntrinsicHeight(), this.zzfsf.getIntrinsicHeight());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.max(this.zzfse.getIntrinsicWidth(), this.zzfsf.getIntrinsicWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        if (!this.zzfsi) {
            this.zzfsj = Drawable.resolveOpacity(this.zzfse.getOpacity(), this.zzfsf.getOpacity());
            this.zzfsi = true;
        }
        return this.zzfsj;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        if (!this.zzfsc && super.mutate() == this) {
            if (!canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.zzfse.mutate();
            this.zzfsf.mutate();
            this.zzfsc = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected final void onBoundsChange(Rect rect) {
        this.zzfse.setBounds(rect);
        this.zzfsf.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (this.zzfsb == this.zzfrz) {
            this.zzfsb = i;
        }
        this.zzfrz = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.zzfse.setColorFilter(colorFilter);
        this.zzfsf.setColorFilter(colorFilter);
    }

    public final void startTransition(int i) {
        this.mFrom = 0;
        this.zzfry = this.zzfrz;
        this.zzfsb = 0;
        this.zzfsa = 250;
        this.zzfrx = 1;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final Drawable zzajb() {
        return this.zzfsf;
    }
}
