package com.google.android.gms.internal;

import com.google.android.gms.internal.zzedx;
import com.google.android.gms.internal.zzedy;
import java.io.IOException;

/* loaded from: classes.dex */
public abstract class zzedx<MessageType extends zzedx<MessageType, BuilderType>, BuilderType extends zzedy<MessageType, BuilderType>> implements zzefq {
    private static boolean zznba = false;
    protected int zznaz = 0;

    @Override // com.google.android.gms.internal.zzefq
    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzhi()];
            zzeeo zzeeoVarZzau = zzeeo.zzau(bArr);
            zza(zzeeoVarZzau);
            zzeeoVarZzau.zzccm();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException(new StringBuilder(String.valueOf(name).length() + 62 + String.valueOf("byte array").length()).append("Serializing ").append(name).append(" to a ").append("byte array").append(" threw an IOException (should never happen).").toString(), e);
        }
    }

    @Override // com.google.android.gms.internal.zzefq
    public final zzeec zzcbp() {
        try {
            zzeeh zzeehVarZzgl = zzeec.zzgl(zzhi());
            zza(zzeehVarZzgl.zzcbx());
            return zzeehVarZzgl.zzcbw();
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException(new StringBuilder(String.valueOf(name).length() + 62 + String.valueOf("ByteString").length()).append("Serializing ").append(name).append(" to a ").append("ByteString").append(" threw an IOException (should never happen).").toString(), e);
        }
    }
}
