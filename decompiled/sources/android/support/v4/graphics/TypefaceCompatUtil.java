package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
    }

    public static File getTempFile(Context context) {
        String prefix = CACHE_FILE_PREFIX + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i = 0; i < 100; i++) {
            File file = new File(context.getCacheDir(), prefix + i);
            if (file.createNewFile()) {
                return file;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0031  */
    @android.support.annotation.RequiresApi(19)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.nio.ByteBuffer mmap(java.io.File r10) throws java.lang.Throwable {
        /*
            r8 = 0
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.io.IOException -> L24
            r7.<init>(r10)     // Catch: java.io.IOException -> L24
            r9 = 0
            java.nio.channels.FileChannel r0 = r7.getChannel()     // Catch: java.lang.Throwable -> L2b java.lang.Throwable -> L40
            long r4 = r0.size()     // Catch: java.lang.Throwable -> L2b java.lang.Throwable -> L40
            java.nio.channels.FileChannel$MapMode r1 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: java.lang.Throwable -> L2b java.lang.Throwable -> L40
            r2 = 0
            java.nio.MappedByteBuffer r1 = r0.map(r1, r2, r4)     // Catch: java.lang.Throwable -> L2b java.lang.Throwable -> L40
            if (r7 == 0) goto L1e
            if (r8 == 0) goto L27
            r7.close()     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L24
        L1e:
            return r1
        L1f:
            r2 = move-exception
            r9.addSuppressed(r2)     // Catch: java.io.IOException -> L24
            goto L1e
        L24:
            r6 = move-exception
            r1 = r8
            goto L1e
        L27:
            r7.close()     // Catch: java.io.IOException -> L24
            goto L1e
        L2b:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> L2d
        L2d:
            r2 = move-exception
            r3 = r1
        L2f:
            if (r7 == 0) goto L36
            if (r3 == 0) goto L3c
            r7.close()     // Catch: java.io.IOException -> L24 java.lang.Throwable -> L37
        L36:
            throw r2     // Catch: java.io.IOException -> L24
        L37:
            r1 = move-exception
            r3.addSuppressed(r1)     // Catch: java.io.IOException -> L24
            goto L36
        L3c:
            r7.close()     // Catch: java.io.IOException -> L24
            goto L36
        L40:
            r1 = move-exception
            r2 = r1
            r3 = r8
            goto L2f
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(java.io.File):java.nio.ByteBuffer");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0060  */
    @android.support.annotation.RequiresApi(19)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.nio.ByteBuffer mmap(android.content.Context r13, android.os.CancellationSignal r14, android.net.Uri r15) throws java.lang.Throwable {
        /*
            r10 = 0
            android.content.ContentResolver r9 = r13.getContentResolver()
            java.lang.String r1 = "r"
            android.os.ParcelFileDescriptor r8 = r9.openFileDescriptor(r15, r1, r14)     // Catch: java.io.IOException -> L46
            r11 = 0
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L4d
            java.io.FileDescriptor r1 = r8.getFileDescriptor()     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L4d
            r7.<init>(r1)     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L4d
            r12 = 0
            java.nio.channels.FileChannel r0 = r7.getChannel()     // Catch: java.lang.Throwable -> L5a java.lang.Throwable -> L78
            long r4 = r0.size()     // Catch: java.lang.Throwable -> L5a java.lang.Throwable -> L78
            java.nio.channels.FileChannel$MapMode r1 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: java.lang.Throwable -> L5a java.lang.Throwable -> L78
            r2 = 0
            java.nio.MappedByteBuffer r1 = r0.map(r1, r2, r4)     // Catch: java.lang.Throwable -> L5a java.lang.Throwable -> L78
            if (r7 == 0) goto L2d
            if (r10 == 0) goto L49
            r7.close()     // Catch: java.lang.Throwable -> L35 java.lang.Throwable -> L4d
        L2d:
            if (r8 == 0) goto L34
            if (r10 == 0) goto L56
            r8.close()     // Catch: java.io.IOException -> L46 java.lang.Throwable -> L51
        L34:
            return r1
        L35:
            r2 = move-exception
            r12.addSuppressed(r2)     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L4d
            goto L2d
        L3a:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> L3c
        L3c:
            r2 = move-exception
            r3 = r1
        L3e:
            if (r8 == 0) goto L45
            if (r3 == 0) goto L74
            r8.close()     // Catch: java.io.IOException -> L46 java.lang.Throwable -> L6f
        L45:
            throw r2     // Catch: java.io.IOException -> L46
        L46:
            r6 = move-exception
            r1 = r10
            goto L34
        L49:
            r7.close()     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L4d
            goto L2d
        L4d:
            r1 = move-exception
            r2 = r1
            r3 = r10
            goto L3e
        L51:
            r2 = move-exception
            r11.addSuppressed(r2)     // Catch: java.io.IOException -> L46
            goto L34
        L56:
            r8.close()     // Catch: java.io.IOException -> L46
            goto L34
        L5a:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> L5c
        L5c:
            r2 = move-exception
            r3 = r1
        L5e:
            if (r7 == 0) goto L65
            if (r3 == 0) goto L6b
            r7.close()     // Catch: java.lang.Throwable -> L4d java.lang.Throwable -> L66
        L65:
            throw r2     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L4d
        L66:
            r1 = move-exception
            r3.addSuppressed(r1)     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L4d
            goto L65
        L6b:
            r7.close()     // Catch: java.lang.Throwable -> L3a java.lang.Throwable -> L4d
            goto L65
        L6f:
            r1 = move-exception
            r3.addSuppressed(r1)     // Catch: java.io.IOException -> L46
            goto L45
        L74:
            r8.close()     // Catch: java.io.IOException -> L46
            goto L45
        L78:
            r1 = move-exception
            r2 = r1
            r3 = r10
            goto L5e
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    @RequiresApi(19)
    public static ByteBuffer copyToDirectBuffer(Context context, Resources res, int id) {
        ByteBuffer byteBufferMmap = null;
        File tmpFile = getTempFile(context);
        if (tmpFile != null) {
            try {
                if (copyToFile(tmpFile, res, id)) {
                    byteBufferMmap = mmap(tmpFile);
                }
            } finally {
                tmpFile.delete();
            }
        }
        return byteBufferMmap;
    }

    public static boolean copyToFile(File file, InputStream is) throws Throwable {
        FileOutputStream os;
        boolean z = false;
        FileOutputStream os2 = null;
        try {
            try {
                os = new FileOutputStream(file, false);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            byte[] buffer = new byte[1024];
            while (true) {
                int readLen = is.read(buffer);
                if (readLen == -1) {
                    break;
                }
                os.write(buffer, 0, readLen);
            }
            z = true;
            closeQuietly(os);
            os2 = os;
        } catch (IOException e2) {
            e = e2;
            os2 = os;
            Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
            closeQuietly(os2);
            return z;
        } catch (Throwable th2) {
            th = th2;
            os2 = os;
            closeQuietly(os2);
            throw th;
        }
        return z;
    }

    public static boolean copyToFile(File file, Resources res, int id) throws IOException {
        InputStream is = null;
        try {
            is = res.openRawResource(id);
            return copyToFile(file, is);
        } finally {
            closeQuietly(is);
        }
    }

    public static void closeQuietly(Closeable c) throws IOException {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }
}
