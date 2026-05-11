package com.google.android.gms.internal;

import com.google.android.gms.internal.zzeev;
import com.google.android.gms.internal.zzeew;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public abstract class zzeev<MessageType extends zzeev<MessageType, BuilderType>, BuilderType extends zzeew<MessageType, BuilderType>> extends zzedx<MessageType, BuilderType> {
    protected zzegi zznce = zzegi.zzcdq();
    protected int zzncf = -1;

    protected static <T extends zzeev<T, ?>> T zza(T t, zzeec zzeecVar) throws zzefj {
        T t2 = (T) zza(t, zzeecVar, zzeer.zzccr());
        if (t2 != null) {
            if (!(t2.zza(zzefd.zzncn, Boolean.TRUE, null) != null)) {
                throw new zzegh(t2).zzcdp().zze(t2);
            }
        }
        if (t2 != null) {
            if (!(t2.zza(zzefd.zzncn, Boolean.TRUE, null) != null)) {
                throw new zzegh(t2).zzcdp().zze(t2);
            }
        }
        return t2;
    }

    private static <T extends zzeev<T, ?>> T zza(T t, zzeec zzeecVar, zzeer zzeerVar) throws zzefj {
        try {
            zzeel zzeelVarZzcbt = zzeecVar.zzcbt();
            T t2 = (T) zza(t, zzeelVarZzcbt, zzeerVar);
            try {
                zzeelVarZzcbt.zzgm(0);
                return t2;
            } catch (zzefj e) {
                throw e.zze(t2);
            }
        } catch (zzefj e2) {
            throw e2;
        }
    }

    static <T extends zzeev<T, ?>> T zza(T t, zzeel zzeelVar, zzeer zzeerVar) throws zzefj {
        T t2 = (T) t.zza(zzefd.zzncr, null, null);
        try {
            t2.zza(zzefd.zzncp, zzeelVar, zzeerVar);
            t2.zza(zzefd.zzncq, null, null);
            t2.zznce.zzbht();
            return t2;
        } catch (RuntimeException e) {
            if (e.getCause() instanceof zzefj) {
                throw ((zzefj) e.getCause());
            }
            throw e;
        }
    }

    protected static <T extends zzeev<T, ?>> T zza(T t, byte[] bArr) throws zzefj {
        T t2 = (T) zza(t, bArr, zzeer.zzccr());
        if (t2 != null) {
            if (!(t2.zza(zzefd.zzncn, Boolean.TRUE, null) != null)) {
                throw new zzegh(t2).zzcdp().zze(t2);
            }
        }
        return t2;
    }

    private static <T extends zzeev<T, ?>> T zza(T t, byte[] bArr, zzeer zzeerVar) throws zzefj {
        try {
            zzeel zzeelVarZzat = zzeel.zzat(bArr);
            T t2 = (T) zza(t, zzeelVarZzat, zzeerVar);
            try {
                zzeelVarZzat.zzgm(0);
                return t2;
            } catch (zzefj e) {
                throw e.zze(t2);
            }
        } catch (zzefj e2) {
            throw e2;
        }
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static <E> zzefi<E> zzccv() {
        return zzefv.zzcdh();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzeev) zza(zzefd.zznct, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        try {
            zzeey zzeeyVar = zzeey.zzncj;
            zzeev zzeevVar = (zzeev) obj;
            zza(zzefd.zznco, zzeeyVar, zzeevVar);
            this.zznce = zzeeyVar.zza(this.zznce, zzeevVar.zznce);
            return true;
        } catch (zzeez e) {
            return false;
        }
    }

    public int hashCode() {
        if (this.zznaz != 0) {
            return this.zznaz;
        }
        zzefb zzefbVar = new zzefb();
        zza(zzefd.zznco, zzefbVar, this);
        this.zznce = zzefbVar.zza(this.zznce, this.zznce);
        this.zznaz = zzefbVar.hashCode;
        return this.zznaz;
    }

    public String toString() {
        return zzeft.zza(this, super.toString());
    }

    protected abstract Object zza(int i, Object obj, Object obj2);

    protected final boolean zza(int i, zzeel zzeelVar) throws IOException {
        if ((i & 7) == 4) {
            return false;
        }
        if (this.zznce == zzegi.zzcdq()) {
            this.zznce = zzegi.zzcdr();
        }
        return this.zznce.zzb(i, zzeelVar);
    }

    @Override // com.google.android.gms.internal.zzefq
    public final /* synthetic */ zzefr zzccw() {
        zzeew zzeewVar = (zzeew) zza(zzefd.zzncs, (Object) null, (Object) null);
        zzeewVar.zza((zzeew) this);
        return zzeewVar;
    }

    @Override // com.google.android.gms.internal.zzefs
    public final /* synthetic */ zzefq zzccx() {
        return (zzeev) zza(zzefd.zznct, (Object) null, (Object) null);
    }
}
