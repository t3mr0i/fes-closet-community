package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.internal.zzdbm;
import com.google.android.gms.internal.zzdbo;
import com.google.android.gms.internal.zzdbs;
import com.google.android.gms.internal.zzdbw;
import com.google.android.gms.internal.zzehf;
import com.google.android.gms.internal.zzehg;
import com.google.android.gms.tagmanager.zzei;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

/* loaded from: classes.dex */
final class zzey implements zzah {
    private final Context mContext;
    private final ExecutorService zzirz = Executors.newSingleThreadExecutor();
    private final String zzjoz;
    private zzdi<zzdbm> zzjty;

    zzey(Context context, String str) {
        this.mContext = context;
        this.zzjoz = str;
    }

    private static zzdbs zza(ByteArrayOutputStream byteArrayOutputStream) {
        try {
            return zzdb.zzlu(byteArrayOutputStream.toString("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            zzdj.zzca("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
            return null;
        } catch (JSONException e2) {
            zzdj.zzcr("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
            return null;
        }
    }

    private final File zzbeo() {
        String strValueOf = String.valueOf("resource_");
        String strValueOf2 = String.valueOf(this.zzjoz);
        return new File(this.mContext.getDir("google_tagmanager", 0), strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
    }

    private static zzdbs zzx(byte[] bArr) {
        try {
            zzdbs zzdbsVarZza = zzdbo.zza((com.google.android.gms.internal.zzbl) zzehg.zza(new com.google.android.gms.internal.zzbl(), bArr));
            if (zzdbsVarZza == null) {
                return zzdbsVarZza;
            }
            zzdj.v("The container was successfully loaded from the resource (using binary file)");
            return zzdbsVarZza;
        } catch (zzdbw e) {
            zzdj.zzcr("The resource file is invalid. The container from the binary file is invalid");
            return null;
        } catch (zzehf e2) {
            zzdj.e("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        }
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final synchronized void release() {
        this.zzirz.shutdown();
    }

    @Override // com.google.android.gms.tagmanager.zzah
    public final void zza(zzdbm zzdbmVar) {
        this.zzirz.execute(new zzfa(this, zzdbmVar));
    }

    @Override // com.google.android.gms.tagmanager.zzah
    public final void zza(zzdi<zzdbm> zzdiVar) {
        this.zzjty = zzdiVar;
    }

    final boolean zzb(zzdbm zzdbmVar) {
        File fileZzbeo = zzbeo();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileZzbeo);
            try {
                try {
                    fileOutputStream.write(zzehg.zzc(zzdbmVar));
                    return true;
                } catch (IOException e) {
                    zzdj.zzcr("Error writing resource to disk. Removing resource from disk.");
                    fileZzbeo.delete();
                    try {
                        fileOutputStream.close();
                        return false;
                    } catch (IOException e2) {
                        zzdj.zzcr("error closing stream for writing resource to disk");
                        return false;
                    }
                }
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e3) {
                    zzdj.zzcr("error closing stream for writing resource to disk");
                }
            }
        } catch (FileNotFoundException e4) {
            zzdj.e("Error opening resource file for writing");
            return false;
        }
    }

    @Override // com.google.android.gms.tagmanager.zzah
    public final void zzbcx() {
        this.zzirz.execute(new zzez(this));
    }

    final void zzben() {
        zzdbm zzdbmVar;
        if (this.zzjty == null) {
            throw new IllegalStateException("Callback must be set before execute");
        }
        zzdj.v("Attempting to load resource from disk");
        if ((zzei.zzbei().zzbej() == zzei.zza.CONTAINER || zzei.zzbei().zzbej() == zzei.zza.CONTAINER_DEBUG) && this.zzjoz.equals(zzei.zzbei().getContainerId())) {
            this.zzjty.zzed(zzda.zzjsk);
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(zzbeo());
            try {
                try {
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        zzdbo.zzb(fileInputStream, byteArrayOutputStream);
                        zzdbmVar = (zzdbm) zzehg.zza(new zzdbm(), byteArrayOutputStream.toByteArray());
                    } catch (IOException e) {
                        this.zzjty.zzed(zzda.zzjsl);
                        zzdj.zzcr("Failed to read the resource from disk");
                    }
                } catch (IllegalArgumentException e2) {
                    this.zzjty.zzed(zzda.zzjsl);
                    zzdj.zzcr("Failed to read the resource from disk. The resource is inconsistent");
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        zzdj.zzcr("Error closing stream for reading resource from disk");
                    }
                }
                if (zzdbmVar.zzxw == null && zzdbmVar.zzkfk == null) {
                    throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
                }
                this.zzjty.onSuccess(zzdbmVar);
                try {
                    fileInputStream.close();
                } catch (IOException e4) {
                    zzdj.zzcr("Error closing stream for reading resource from disk");
                }
                zzdj.v("The Disk resource was successfully read.");
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    zzdj.zzcr("Error closing stream for reading resource from disk");
                }
            }
        } catch (FileNotFoundException e6) {
            zzdj.zzca("Failed to find the resource in the disk");
            this.zzjty.zzed(zzda.zzjsk);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzah
    public final zzdbs zzee(int i) throws Resources.NotFoundException {
        try {
            InputStream inputStreamOpenRawResource = this.mContext.getResources().openRawResource(i);
            String resourceName = this.mContext.getResources().getResourceName(i);
            zzdj.v(new StringBuilder(String.valueOf(resourceName).length() + 66).append("Attempting to load a container from the resource ID ").append(i).append(" (").append(resourceName).append(")").toString());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzdbo.zzb(inputStreamOpenRawResource, byteArrayOutputStream);
                zzdbs zzdbsVarZza = zza(byteArrayOutputStream);
                if (zzdbsVarZza != null) {
                    zzdj.v("The container was successfully loaded from the resource (using JSON file format)");
                } else {
                    zzdbsVarZza = zzx(byteArrayOutputStream.toByteArray());
                }
                return zzdbsVarZza;
            } catch (IOException e) {
                String resourceName2 = this.mContext.getResources().getResourceName(i);
                zzdj.zzcr(new StringBuilder(String.valueOf(resourceName2).length() + 67).append("Error reading the default container with resource ID ").append(i).append(" (").append(resourceName2).append(")").toString());
                return null;
            }
        } catch (Resources.NotFoundException e2) {
            zzdj.zzcr(new StringBuilder(98).append("Failed to load the container. No default container resource found with the resource ID ").append(i).toString());
            return null;
        }
    }
}
