package com.google.android.gms.internal;

import com.google.android.gms.internal.zzedx;
import com.google.android.gms.internal.zzedy;

/* loaded from: classes.dex */
public abstract class zzedy<MessageType extends zzedx<MessageType, BuilderType>, BuilderType extends zzedy<MessageType, BuilderType>> implements zzefr {
    protected abstract BuilderType zza(MessageType messagetype);

    @Override // com.google.android.gms.internal.zzefr
    public final /* synthetic */ zzefr zzc(zzefq zzefqVar) {
        if (zzccx().getClass().isInstance(zzefqVar)) {
            return zza((zzedx) zzefqVar);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    @Override // 
    /* renamed from: zzcbq, reason: merged with bridge method [inline-methods] */
    public abstract BuilderType clone();
}
