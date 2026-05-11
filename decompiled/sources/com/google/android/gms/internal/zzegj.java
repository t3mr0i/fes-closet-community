package com.google.android.gms.internal;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* loaded from: classes.dex */
final class zzegj {
    private static final boolean zznbt;
    private static final boolean zznea;
    private static final boolean zzneb;
    private static final boolean zznec;
    private static final zzd zzned;
    private static final boolean zznee;
    private static final long zznef;
    private static final long zzneg;
    private static final long zzneh;
    private static final long zznei;
    private static final long zznej;
    private static final long zznek;
    private static final long zznel;
    private static final long zznem;
    private static final long zznen;
    private static final long zzneo;
    private static final long zznep;
    private static final long zzneq;
    private static final long zzner;
    private static final long zznes;
    private static final boolean zznet;
    private static final Logger logger = Logger.getLogger(zzegj.class.getName());
    private static final Unsafe zzndy = zzcdu();
    private static final Class<?> zzndz = zzrn("libcore.io.Memory");

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.zzegj.zzd
        public final void zze(Object obj, long j, byte b) {
            if (zzegj.zznet) {
                zzegj.zza(obj, j, b);
            } else {
                zzegj.zzb(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.zzegj.zzd
        public final byte zzf(Object obj, long j) {
            return zzegj.zznet ? zzegj.zzb(obj, j) : zzegj.zzc(obj, j);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.zzegj.zzd
        public final void zze(Object obj, long j, byte b) {
            if (zzegj.zznet) {
                zzegj.zza(obj, j, b);
            } else {
                zzegj.zzb(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.zzegj.zzd
        public final byte zzf(Object obj, long j) {
            return zzegj.zznet ? zzegj.zzb(obj, j) : zzegj.zzc(obj, j);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.zzegj.zzd
        public final void zze(Object obj, long j, byte b) {
            this.zzneu.putByte(obj, j, b);
        }

        @Override // com.google.android.gms.internal.zzegj.zzd
        public final byte zzf(Object obj, long j) {
            return this.zzneu.getByte(obj, j);
        }
    }

    static abstract class zzd {
        Unsafe zzneu;

        zzd(Unsafe unsafe) {
            this.zzneu = unsafe;
        }

        public abstract void zze(Object obj, long j, byte b);

        public abstract byte zzf(Object obj, long j);
    }

    static {
        Field fieldZza;
        zznea = zzrn("org.robolectric.Robolectric") != null;
        zzneb = zzi(Long.TYPE);
        zznec = zzi(Integer.TYPE);
        zzned = zzndy == null ? null : zzcdx() ? zzneb ? new zzb(zzndy) : zznec ? new zza(zzndy) : null : new zzc(zzndy);
        zznee = zzcdw();
        zznbt = zzcdv();
        zznef = zzg(byte[].class);
        zzneg = zzg(boolean[].class);
        zzneh = zzh(boolean[].class);
        zznei = zzg(int[].class);
        zznej = zzh(int[].class);
        zznek = zzg(long[].class);
        zznel = zzh(long[].class);
        zznem = zzg(float[].class);
        zznen = zzh(float[].class);
        zzneo = zzg(double[].class);
        zznep = zzh(double[].class);
        zzneq = zzg(Object[].class);
        zzner = zzh(Object[].class);
        if (!zzcdx() || (fieldZza = zza((Class<?>) Buffer.class, "effectiveDirectAddress")) == null) {
            fieldZza = zza((Class<?>) Buffer.class, "address");
        }
        zznes = (fieldZza == null || zzned == null) ? -1L : zzned.zzneu.objectFieldOffset(fieldZza);
        zznet = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    private zzegj() {
    }

    private static int zza(Object obj, long j) {
        return zzned.zzneu.getInt(obj, j);
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable th) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        int i = ((((int) j) ^ (-1)) & 3) << 3;
        zza(obj, j & (-4), (zza(obj, j & (-4)) & ((255 << i) ^ (-1))) | ((b & 255) << i));
    }

    private static void zza(Object obj, long j, int i) {
        zzned.zzneu.putInt(obj, j, i);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzned.zze(bArr, zznef + j, b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzb(Object obj, long j) {
        return (byte) (zza(obj, (-4) & j) >>> ((int) ((((-1) ^ j) & 3) << 3)));
    }

    static byte zzb(byte[] bArr, long j) {
        return zzned.zzf(bArr, zznef + j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        int i = (((int) j) & 3) << 3;
        zza(obj, j & (-4), (zza(obj, j & (-4)) & ((255 << i) ^ (-1))) | ((b & 255) << i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzc(Object obj, long j) {
        return (byte) (zza(obj, (-4) & j) >>> ((int) ((3 & j) << 3)));
    }

    static boolean zzcds() {
        return zznbt;
    }

    static boolean zzcdt() {
        return zznee;
    }

    private static Unsafe zzcdu() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzegk());
        } catch (Throwable th) {
            return null;
        }
    }

    private static boolean zzcdv() {
        if (zzndy == null) {
            return false;
        }
        try {
            Class<?> cls = zzndy.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("arrayBaseOffset", Class.class);
            cls.getMethod("arrayIndexScale", Class.class);
            cls.getMethod("getInt", Object.class, Long.TYPE);
            cls.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            cls.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
            cls.getMethod("getObject", Object.class, Long.TYPE);
            cls.getMethod("putObject", Object.class, Long.TYPE, Object.class);
            if (zzcdx()) {
                return true;
            }
            cls.getMethod("getByte", Object.class, Long.TYPE);
            cls.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
            cls.getMethod("getBoolean", Object.class, Long.TYPE);
            cls.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
            cls.getMethod("getFloat", Object.class, Long.TYPE);
            cls.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
            cls.getMethod("getDouble", Object.class, Long.TYPE);
            cls.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(th);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", new StringBuilder(String.valueOf(strValueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(strValueOf).toString());
            return false;
        }
    }

    private static boolean zzcdw() {
        if (zzndy == null) {
            return false;
        }
        try {
            Class<?> cls = zzndy.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            if (zzcdx()) {
                return true;
            }
            cls.getMethod("getByte", Long.TYPE);
            cls.getMethod("putByte", Long.TYPE, Byte.TYPE);
            cls.getMethod("getInt", Long.TYPE);
            cls.getMethod("putInt", Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Long.TYPE);
            cls.getMethod("putLong", Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(th);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", new StringBuilder(String.valueOf(strValueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(strValueOf).toString());
            return false;
        }
    }

    private static boolean zzcdx() {
        return (zzndz == null || zznea) ? false : true;
    }

    private static int zzg(Class<?> cls) {
        if (zznbt) {
            return zzned.zzneu.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzh(Class<?> cls) {
        if (zznbt) {
            return zzned.zzneu.arrayIndexScale(cls);
        }
        return -1;
    }

    private static boolean zzi(Class<?> cls) {
        if (!zzcdx()) {
            return false;
        }
        try {
            Class<?> cls2 = zzndz;
            cls2.getMethod("peekLong", cls, Boolean.TYPE);
            cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
            cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
            cls2.getMethod("peekInt", cls, Boolean.TYPE);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            cls2.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static <T> Class<T> zzrn(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable th) {
            return null;
        }
    }
}
