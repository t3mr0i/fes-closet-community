package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public final class DynamiteModule {
    private static Boolean zzgph;
    private static zzk zzgpi;
    private static zzm zzgpj;
    private static String zzgpk;
    private static final ThreadLocal<zza> zzgpl = new ThreadLocal<>();
    private static final zzi zzgpm = new com.google.android.gms.dynamite.zza();
    public static final zzd zzgpn = new com.google.android.gms.dynamite.zzb();
    private static zzd zzgpo = new com.google.android.gms.dynamite.zzc();
    public static final zzd zzgpp = new com.google.android.gms.dynamite.zzd();
    public static final zzd zzgpq = new zze();
    public static final zzd zzgpr = new zzf();
    public static final zzd zzgps = new zzg();
    private final Context zzgpt;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    static class zza {
        public Cursor zzgpu;

        private zza() {
        }

        /* synthetic */ zza(com.google.android.gms.dynamite.zza zzaVar) {
            this();
        }
    }

    static class zzb implements zzi {
        private final int zzgpv;
        private final int zzgpw = 0;

        public zzb(int i, int i2) {
            this.zzgpv = i;
        }

        @Override // com.google.android.gms.dynamite.zzi
        public final int zzad(Context context, String str) {
            return this.zzgpv;
        }

        @Override // com.google.android.gms.dynamite.zzi
        public final int zzb(Context context, String str, boolean z) {
            return 0;
        }
    }

    public static class zzc extends Exception {
        private zzc(String str) {
            super(str);
        }

        /* synthetic */ zzc(String str, com.google.android.gms.dynamite.zza zzaVar) {
            this(str);
        }

        private zzc(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ zzc(String str, Throwable th, com.google.android.gms.dynamite.zza zzaVar) {
            this(str, th);
        }
    }

    public interface zzd {
        zzj zza(Context context, String str, zzi zziVar) throws zzc;
    }

    private DynamiteModule(Context context) {
        this.zzgpt = (Context) zzbp.zzu(context);
    }

    private static Context zza(Context context, String str, int i, Cursor cursor, zzm zzmVar) {
        try {
            return (Context) com.google.android.gms.dynamic.zzn.zzx(zzmVar.zza(com.google.android.gms.dynamic.zzn.zzw(context), str, i, com.google.android.gms.dynamic.zzn.zzw(cursor)));
        } catch (Exception e) {
            String strValueOf = String.valueOf(e.toString());
            Log.e("DynamiteModule", strValueOf.length() != 0 ? "Failed to load DynamiteLoader: ".concat(strValueOf) : new String("Failed to load DynamiteLoader: "));
            return null;
        }
    }

    public static DynamiteModule zza(Context context, zzd zzdVar, String str) throws zzc {
        zza zzaVar = zzgpl.get();
        zza zzaVar2 = new zza(null);
        zzgpl.set(zzaVar2);
        try {
            zzj zzjVarZza = zzdVar.zza(context, str, zzgpm);
            Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length()).append("Considering local module ").append(str).append(":").append(zzjVarZza.zzgpx).append(" and remote module ").append(str).append(":").append(zzjVarZza.zzgpy).toString());
            if (zzjVarZza.zzgpz == 0 || ((zzjVarZza.zzgpz == -1 && zzjVarZza.zzgpx == 0) || (zzjVarZza.zzgpz == 1 && zzjVarZza.zzgpy == 0))) {
                throw new zzc(new StringBuilder(91).append("No acceptable module found. Local version is ").append(zzjVarZza.zzgpx).append(" and remote version is ").append(zzjVarZza.zzgpy).append(".").toString(), (com.google.android.gms.dynamite.zza) null);
            }
            if (zzjVarZza.zzgpz == -1) {
                DynamiteModule dynamiteModuleZzaf = zzaf(context, str);
                if (zzaVar2.zzgpu != null) {
                    zzaVar2.zzgpu.close();
                }
                zzgpl.set(zzaVar);
                return dynamiteModuleZzaf;
            }
            if (zzjVarZza.zzgpz != 1) {
                throw new zzc(new StringBuilder(47).append("VersionPolicy returned invalid code:").append(zzjVarZza.zzgpz).toString(), (com.google.android.gms.dynamite.zza) null);
            }
            try {
                DynamiteModule dynamiteModuleZza = zza(context, str, zzjVarZza.zzgpy);
                if (zzaVar2.zzgpu != null) {
                    zzaVar2.zzgpu.close();
                }
                zzgpl.set(zzaVar);
                return dynamiteModuleZza;
            } catch (zzc e) {
                String strValueOf = String.valueOf(e.getMessage());
                Log.w("DynamiteModule", strValueOf.length() != 0 ? "Failed to load remote module: ".concat(strValueOf) : new String("Failed to load remote module: "));
                if (zzjVarZza.zzgpx == 0 || zzdVar.zza(context, str, new zzb(zzjVarZza.zzgpx, 0)).zzgpz != -1) {
                    throw new zzc("Remote load failed. No local fallback found.", e, null);
                }
                DynamiteModule dynamiteModuleZzaf2 = zzaf(context, str);
                if (zzaVar2.zzgpu != null) {
                    zzaVar2.zzgpu.close();
                }
                zzgpl.set(zzaVar);
                return dynamiteModuleZzaf2;
            }
        } catch (Throwable th) {
            if (zzaVar2.zzgpu != null) {
                zzaVar2.zzgpu.close();
            }
            zzgpl.set(zzaVar);
            throw th;
        }
    }

    private static DynamiteModule zza(Context context, String str, int i) throws zzc {
        Boolean bool;
        synchronized (DynamiteModule.class) {
            bool = zzgph;
        }
        if (bool == null) {
            throw new zzc("Failed to determine which loading route to use.", (com.google.android.gms.dynamite.zza) null);
        }
        return bool.booleanValue() ? zzc(context, str, i) : zzb(context, str, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.gms.dynamite.zzm] */
    private static void zza(ClassLoader classLoader) throws zzc {
        zzn zznVar;
        com.google.android.gms.dynamite.zza zzaVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zznVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                zznVar = iInterfaceQueryLocalInterface instanceof zzm ? (zzm) iInterfaceQueryLocalInterface : new zzn(iBinder);
            }
            zzgpj = zznVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new zzc("Failed to instantiate dynamite loader", e, zzaVar);
        }
    }

    public static int zzad(Context context, String str) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, IllegalArgumentException {
        int i;
        try {
            Class<?> clsLoadClass = context.getApplicationContext().getClassLoader().loadClass(new StringBuilder(String.valueOf("com.google.android.gms.dynamite.descriptors.").length() + 1 + String.valueOf(str).length() + String.valueOf("ModuleDescriptor").length()).append("com.google.android.gms.dynamite.descriptors.").append(str).append(".").append("ModuleDescriptor").toString());
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                i = declaredField2.getInt(null);
            } else {
                String strValueOf = String.valueOf(declaredField.get(null));
                Log.e("DynamiteModule", new StringBuilder(String.valueOf(strValueOf).length() + 51 + String.valueOf(str).length()).append("Module descriptor id '").append(strValueOf).append("' didn't match expected id '").append(str).append("'").toString());
                i = 0;
            }
            return i;
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 45).append("Local module descriptor class for ").append(str).append(" not found.").toString());
            return 0;
        } catch (Exception e2) {
            String strValueOf2 = String.valueOf(e2.getMessage());
            Log.e("DynamiteModule", strValueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(strValueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static int zzae(Context context, String str) {
        return zzb(context, str, false);
    }

    private static DynamiteModule zzaf(Context context, String str) {
        String strValueOf = String.valueOf(str);
        Log.i("DynamiteModule", strValueOf.length() != 0 ? "Selected local version of ".concat(strValueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    public static int zzb(Context context, String str, boolean z) {
        Class<?> clsLoadClass;
        Field declaredField;
        synchronized (DynamiteModule.class) {
            Boolean bool = zzgph;
            if (bool == null) {
                try {
                    clsLoadClass = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName());
                    declaredField = clsLoadClass.getDeclaredField("sClassLoader");
                } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                    String strValueOf = String.valueOf(e);
                    Log.w("DynamiteModule", new StringBuilder(String.valueOf(strValueOf).length() + 30).append("Failed to load module via V2: ").append(strValueOf).toString());
                    bool = Boolean.FALSE;
                }
                synchronized (clsLoadClass) {
                    ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                    if (classLoader != null) {
                        if (classLoader == ClassLoader.getSystemClassLoader()) {
                            bool = Boolean.FALSE;
                        } else {
                            try {
                                zza(classLoader);
                            } catch (zzc e2) {
                            }
                            bool = Boolean.TRUE;
                        }
                    } else if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
                        declaredField.set(null, ClassLoader.getSystemClassLoader());
                        bool = Boolean.FALSE;
                    } else {
                        try {
                            int iZzd = zzd(context, str, z);
                            if (zzgpk == null || zzgpk.isEmpty()) {
                                return iZzd;
                            }
                            zzh zzhVar = new zzh(zzgpk, ClassLoader.getSystemClassLoader());
                            zza(zzhVar);
                            declaredField.set(null, zzhVar);
                            zzgph = Boolean.TRUE;
                            return iZzd;
                        } catch (zzc e3) {
                            declaredField.set(null, ClassLoader.getSystemClassLoader());
                            bool = Boolean.FALSE;
                        }
                    }
                    zzgph = bool;
                }
            }
            if (!bool.booleanValue()) {
                return zzc(context, str, z);
            }
            try {
                return zzd(context, str, z);
            } catch (zzc e4) {
                String strValueOf2 = String.valueOf(e4.getMessage());
                Log.w("DynamiteModule", strValueOf2.length() != 0 ? "Failed to retrieve remote module version: ".concat(strValueOf2) : new String("Failed to retrieve remote module version: "));
                return 0;
            }
        }
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws zzc {
        com.google.android.gms.dynamite.zza zzaVar = null;
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        zzk zzkVarZzcv = zzcv(context);
        if (zzkVarZzcv == null) {
            throw new zzc("Failed to create IDynamiteLoader.", zzaVar);
        }
        try {
            IObjectWrapper iObjectWrapperZza = zzkVarZzcv.zza(com.google.android.gms.dynamic.zzn.zzw(context), str, i);
            if (com.google.android.gms.dynamic.zzn.zzx(iObjectWrapperZza) == null) {
                throw new zzc("Failed to load remote module.", zzaVar);
            }
            return new DynamiteModule((Context) com.google.android.gms.dynamic.zzn.zzx(iObjectWrapperZza));
        } catch (RemoteException e) {
            throw new zzc("Failed to load remote module.", e, zzaVar);
        }
    }

    private static int zzc(Context context, String str, boolean z) {
        zzk zzkVarZzcv = zzcv(context);
        if (zzkVarZzcv == null) {
            return 0;
        }
        try {
            return zzkVarZzcv.zza(com.google.android.gms.dynamic.zzn.zzw(context), str, z);
        } catch (RemoteException e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", strValueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(strValueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    private static DynamiteModule zzc(Context context, String str, int i) throws zzc {
        zzm zzmVar;
        com.google.android.gms.dynamite.zza zzaVar = null;
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        synchronized (DynamiteModule.class) {
            zzmVar = zzgpj;
        }
        if (zzmVar == null) {
            throw new zzc("DynamiteLoaderV2 was not cached.", zzaVar);
        }
        zza zzaVar2 = zzgpl.get();
        if (zzaVar2 == null || zzaVar2.zzgpu == null) {
            throw new zzc("No result cursor", zzaVar);
        }
        Context contextZza = zza(context.getApplicationContext(), str, i, zzaVar2.zzgpu, zzmVar);
        if (contextZza == null) {
            throw new zzc("Failed to get module context", zzaVar);
        }
        return new DynamiteModule(contextZza);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.gms.dynamite.zzk] */
    private static zzk zzcv(Context context) {
        zzl zzlVar;
        synchronized (DynamiteModule.class) {
            if (zzgpi != null) {
                return zzgpi;
            }
            if (com.google.android.gms.common.zze.zzaex().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzlVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzlVar = iInterfaceQueryLocalInterface instanceof zzk ? (zzk) iInterfaceQueryLocalInterface : new zzl(iBinder);
                }
                if (zzlVar != null) {
                    zzgpi = zzlVar;
                    return zzlVar;
                }
            } catch (Exception e) {
                String strValueOf = String.valueOf(e.getMessage());
                Log.e("DynamiteModule", strValueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(strValueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            }
            return null;
        }
    }

    private static int zzd(Context context, String str, boolean z) throws Throwable {
        String str2;
        Cursor cursor = null;
        try {
            str2 = z ? "api_force_staging" : "api";
        } catch (Throwable th) {
            th = th;
        }
        try {
            Cursor cursorQuery = context.getContentResolver().query(Uri.parse(new StringBuilder(String.valueOf("content://com.google.android.gms.chimera/").length() + 1 + String.valueOf(str2).length() + String.valueOf(str).length()).append("content://com.google.android.gms.chimera/").append(str2).append("/").append(str).toString()), null, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        int i = cursorQuery.getInt(0);
                        if (i > 0) {
                            synchronized (DynamiteModule.class) {
                                zzgpk = cursorQuery.getString(2);
                            }
                            zza zzaVar = zzgpl.get();
                            if (zzaVar != null && zzaVar.zzgpu == null) {
                                zzaVar.zzgpu = cursorQuery;
                                cursorQuery = null;
                            }
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return i;
                    }
                } catch (Exception e) {
                    e = e;
                    if (e instanceof zzc) {
                        throw e;
                    }
                    throw new zzc("V2 version check failed", e, null);
                }
            }
            Log.w("DynamiteModule", "Failed to retrieve remote module version.");
            throw new zzc("Failed to connect to dynamite module ContentResolver.", (com.google.android.gms.dynamite.zza) null);
        } catch (Exception e2) {
            e = e2;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public final Context zzaoh() {
        return this.zzgpt;
    }

    public final IBinder zzgv(String str) throws zzc {
        try {
            return (IBinder) this.zzgpt.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String strValueOf = String.valueOf(str);
            throw new zzc(strValueOf.length() != 0 ? "Failed to instantiate module class: ".concat(strValueOf) : new String("Failed to instantiate module class: "), e, null);
        }
    }
}
