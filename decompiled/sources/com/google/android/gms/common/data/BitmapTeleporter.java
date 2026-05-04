package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class BitmapTeleporter extends zzbck implements ReflectedParcelable {
    public static final Parcelable.Creator<BitmapTeleporter> CREATOR = new zza();
    private ParcelFileDescriptor zzcqy;
    private int zzdxr;
    private int zzecy;
    private Bitmap zzfqa;
    private boolean zzfqb;
    private File zzfqc;

    BitmapTeleporter(int i, ParcelFileDescriptor parcelFileDescriptor, int i2) {
        this.zzdxr = i;
        this.zzcqy = parcelFileDescriptor;
        this.zzecy = i2;
        this.zzfqa = null;
        this.zzfqb = false;
    }

    public BitmapTeleporter(Bitmap bitmap) {
        this.zzdxr = 1;
        this.zzcqy = null;
        this.zzecy = 0;
        this.zzfqa = bitmap;
        this.zzfqb = true;
    }

    private static void zza(Closeable closeable) throws IOException {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w("BitmapTeleporter", "Could not close stream", e);
        }
    }

    private final FileOutputStream zzaiu() throws IOException {
        if (this.zzfqc == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        try {
            File fileCreateTempFile = File.createTempFile("teleporter", ".tmp", this.zzfqc);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                this.zzcqy = ParcelFileDescriptor.open(fileCreateTempFile, 268435456);
                fileCreateTempFile.delete();
                return fileOutputStream;
            } catch (FileNotFoundException e) {
                throw new IllegalStateException("Temporary file is somehow already deleted");
            }
        } catch (IOException e2) {
            throw new IllegalStateException("Could not create temporary file", e2);
        }
    }

    public final void release() throws IOException {
        if (this.zzfqb) {
            return;
        }
        try {
            this.zzcqy.close();
        } catch (IOException e) {
            Log.w("BitmapTeleporter", "Could not close PFD", e);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) throws IOException {
        if (this.zzcqy == null) {
            Bitmap bitmap = this.zzfqa;
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
            bitmap.copyPixelsToBuffer(byteBufferAllocate);
            byte[] bArrArray = byteBufferAllocate.array();
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(zzaiu()));
            try {
                try {
                    dataOutputStream.writeInt(bArrArray.length);
                    dataOutputStream.writeInt(bitmap.getWidth());
                    dataOutputStream.writeInt(bitmap.getHeight());
                    dataOutputStream.writeUTF(bitmap.getConfig().toString());
                    dataOutputStream.write(bArrArray);
                } catch (IOException e) {
                    throw new IllegalStateException("Could not write into unlinked file", e);
                }
            } finally {
                zza(dataOutputStream);
            }
        }
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxr);
        zzbcn.zza(parcel, 2, (Parcelable) this.zzcqy, i | 1, false);
        zzbcn.zzc(parcel, 3, this.zzecy);
        zzbcn.zzai(parcel, iZze);
        this.zzcqy = null;
    }

    public final Bitmap zzait() throws IOException {
        if (!this.zzfqb) {
            DataInputStream dataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(this.zzcqy));
            try {
                try {
                    byte[] bArr = new byte[dataInputStream.readInt()];
                    int i = dataInputStream.readInt();
                    int i2 = dataInputStream.readInt();
                    Bitmap.Config configValueOf = Bitmap.Config.valueOf(dataInputStream.readUTF());
                    dataInputStream.read(bArr);
                    zza(dataInputStream);
                    ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, configValueOf);
                    bitmapCreateBitmap.copyPixelsFromBuffer(byteBufferWrap);
                    this.zzfqa = bitmapCreateBitmap;
                    this.zzfqb = true;
                } catch (IOException e) {
                    throw new IllegalStateException("Could not read from parcel file descriptor", e);
                }
            } catch (Throwable th) {
                zza(dataInputStream);
                throw th;
            }
        }
        return this.zzfqa;
    }

    public final void zzc(File file) {
        if (file == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.zzfqc = file;
    }
}
