package com.google.android.gms.internal;

import com.google.android.gms.internal.zzeev;
import com.google.android.gms.internal.zzeew;

/* loaded from: classes.dex */
public class zzeew<MessageType extends zzeev<MessageType, BuilderType>, BuilderType extends zzeew<MessageType, BuilderType>> extends zzedy<MessageType, BuilderType> {
    private final MessageType zzncg;
    protected MessageType zznch;
    private boolean zznci = false;

    protected zzeew(MessageType messagetype) {
        this.zzncg = messagetype;
        this.zznch = (MessageType) messagetype.zza(zzefd.zzncr, null, null);
    }

    private static void zza(MessageType messagetype, MessageType messagetype2) {
        zzefc zzefcVar = zzefc.zzncm;
        messagetype.zza(zzefd.zznco, zzefcVar, messagetype2);
        messagetype.zznce = zzefcVar.zza(messagetype.zznce, messagetype2.zznce);
    }

    @Override // com.google.android.gms.internal.zzedy
    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        MessageType messagetype;
        zzeew zzeewVar = (zzeew) this.zzncg.zza(zzefd.zzncs, null, null);
        if (this.zznci) {
            messagetype = this.zznch;
        } else {
            MessageType messagetype2 = this.zznch;
            messagetype2.zza(zzefd.zzncq, null, null);
            messagetype2.zznce.zzbht();
            this.zznci = true;
            messagetype = this.zznch;
        }
        zzeewVar.zza((zzeew) messagetype);
        return zzeewVar;
    }

    @Override // com.google.android.gms.internal.zzedy
    public final BuilderType zza(MessageType messagetype) {
        zzccy();
        zza(this.zznch, messagetype);
        return this;
    }

    @Override // com.google.android.gms.internal.zzedy
    /* renamed from: zzcbq */
    public final /* synthetic */ zzedy clone() {
        return (zzeew) clone();
    }

    @Override // com.google.android.gms.internal.zzefs
    public final /* synthetic */ zzefq zzccx() {
        return this.zzncg;
    }

    protected final void zzccy() {
        if (this.zznci) {
            MessageType messagetype = (MessageType) this.zznch.zza(zzefd.zzncr, null, null);
            zza(messagetype, this.zznch);
            this.zznch = messagetype;
            this.zznci = false;
        }
    }

    public final MessageType zzccz() {
        if (this.zznci) {
            return this.zznch;
        }
        MessageType messagetype = this.zznch;
        messagetype.zza(zzefd.zzncq, null, null);
        messagetype.zznce.zzbht();
        this.zznci = true;
        return this.zznch;
    }

    public final MessageType zzcda() {
        MessageType messagetype;
        if (this.zznci) {
            messagetype = this.zznch;
        } else {
            MessageType messagetype2 = this.zznch;
            messagetype2.zza(zzefd.zzncq, null, null);
            messagetype2.zznce.zzbht();
            this.zznci = true;
            messagetype = this.zznch;
        }
        MessageType messagetype3 = messagetype;
        if (messagetype3.zza(zzefd.zzncn, Boolean.TRUE, null) != null) {
            return messagetype3;
        }
        throw new zzegh(messagetype3);
    }

    @Override // com.google.android.gms.internal.zzefr
    public final /* synthetic */ zzefq zzcdb() {
        MessageType messagetype;
        if (this.zznci) {
            messagetype = this.zznch;
        } else {
            MessageType messagetype2 = this.zznch;
            messagetype2.zza(zzefd.zzncq, null, null);
            messagetype2.zznce.zzbht();
            this.zznci = true;
            messagetype = this.zznch;
        }
        MessageType messagetype3 = messagetype;
        if (messagetype3.zza(zzefd.zzncn, Boolean.TRUE, null) != null) {
            return messagetype3;
        }
        throw new zzegh(messagetype3);
    }
}
