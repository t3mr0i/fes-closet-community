package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import java.util.Arrays;

/* loaded from: classes.dex */
public class zzc {
    protected final DataHolder zzfle;
    protected int zzfqg;
    private int zzfqh;

    public zzc(DataHolder dataHolder, int i) {
        this.zzfle = (DataHolder) zzbp.zzu(dataHolder);
        zzbv(i);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc zzcVar = (zzc) obj;
        return zzbf.equal(Integer.valueOf(zzcVar.zzfqg), Integer.valueOf(this.zzfqg)) && zzbf.equal(Integer.valueOf(zzcVar.zzfqh), Integer.valueOf(this.zzfqh)) && zzcVar.zzfle == this.zzfle;
    }

    protected final boolean getBoolean(String str) {
        return this.zzfle.zze(str, this.zzfqg, this.zzfqh);
    }

    protected final byte[] getByteArray(String str) {
        return this.zzfle.zzg(str, this.zzfqg, this.zzfqh);
    }

    protected final float getFloat(String str) {
        return this.zzfle.zzf(str, this.zzfqg, this.zzfqh);
    }

    protected final int getInteger(String str) {
        return this.zzfle.zzc(str, this.zzfqg, this.zzfqh);
    }

    protected final long getLong(String str) {
        return this.zzfle.zzb(str, this.zzfqg, this.zzfqh);
    }

    protected final String getString(String str) {
        return this.zzfle.zzd(str, this.zzfqg, this.zzfqh);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzfqg), Integer.valueOf(this.zzfqh), this.zzfle});
    }

    public boolean isDataValid() {
        return !this.zzfle.isClosed();
    }

    protected final void zza(String str, CharArrayBuffer charArrayBuffer) {
        this.zzfle.zza(str, this.zzfqg, this.zzfqh, charArrayBuffer);
    }

    protected final void zzbv(int i) {
        zzbp.zzbg(i >= 0 && i < this.zzfle.zzfqp);
        this.zzfqg = i;
        this.zzfqh = this.zzfle.zzbx(this.zzfqg);
    }

    public final boolean zzfu(String str) {
        return this.zzfle.zzfu(str);
    }

    protected final Uri zzfv(String str) {
        String strZzd = this.zzfle.zzd(str, this.zzfqg, this.zzfqh);
        if (strZzd == null) {
            return null;
        }
        return Uri.parse(strZzd);
    }

    protected final boolean zzfw(String str) {
        return this.zzfle.zzh(str, this.zzfqg, this.zzfqh);
    }
}
