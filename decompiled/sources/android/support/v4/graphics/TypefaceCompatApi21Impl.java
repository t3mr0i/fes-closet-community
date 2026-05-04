package android.support.v4.graphics;

import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.view.MotionEventCompat;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;

@RequiresApi(MotionEventCompat.AXIS_WHEEL)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    TypefaceCompatApi21Impl() {
    }

    private File getFile(ParcelFileDescriptor fd) throws ErrnoException {
        try {
            String path = Os.readlink("/proc/self/fd/" + fd.getFd());
            if (OsConstants.S_ISREG(Os.stat(path).st_mode)) {
                return new File(path);
            }
            return null;
        } catch (ErrnoException e) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x006c  */
    @Override // android.support.v4.graphics.TypefaceCompatBaseImpl, android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r12, android.os.CancellationSignal r13, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] r14, int r15) throws java.lang.Throwable {
        /*
            r11 = this;
            r6 = 0
            int r7 = r14.length
            r8 = 1
            if (r7 >= r8) goto L6
        L5:
            return r6
        L6:
            android.support.v4.provider.FontsContractCompat$FontInfo r0 = r11.findBestInfo(r14, r15)
            android.content.ContentResolver r5 = r12.getContentResolver()
            android.net.Uri r7 = r0.getUri()     // Catch: java.io.IOException -> L54
            java.lang.String r8 = "r"
            android.os.ParcelFileDescriptor r4 = r5.openFileDescriptor(r7, r8, r13)     // Catch: java.io.IOException -> L54
            r8 = 0
            java.io.File r2 = r11.getFile(r4)     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L5a
            if (r2 == 0) goto L25
            boolean r7 = r2.canRead()     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L5a
            if (r7 != 0) goto L7b
        L25:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L5a
            java.io.FileDescriptor r7 = r4.getFileDescriptor()     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L5a
            r3.<init>(r7)     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L5a
            r9 = 0
            android.graphics.Typeface r7 = super.createFromInputStream(r12, r3)     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L9b
            if (r3 == 0) goto L3a
            if (r6 == 0) goto L56
            r3.close()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L5a
        L3a:
            if (r4 == 0) goto L41
            if (r6 == 0) goto L63
            r4.close()     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L5e
        L41:
            r6 = r7
            goto L5
        L43:
            r10 = move-exception
            r9.addSuppressed(r10)     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L5a
            goto L3a
        L48:
            r7 = move-exception
            throw r7     // Catch: java.lang.Throwable -> L4a
        L4a:
            r8 = move-exception
            r9 = r7
        L4c:
            if (r4 == 0) goto L53
            if (r9 == 0) goto L97
            r4.close()     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L92
        L53:
            throw r8     // Catch: java.io.IOException -> L54
        L54:
            r1 = move-exception
            goto L5
        L56:
            r3.close()     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L5a
            goto L3a
        L5a:
            r7 = move-exception
            r8 = r7
            r9 = r6
            goto L4c
        L5e:
            r9 = move-exception
            r8.addSuppressed(r9)     // Catch: java.io.IOException -> L54
            goto L41
        L63:
            r4.close()     // Catch: java.io.IOException -> L54
            goto L41
        L67:
            r8 = move-exception
            throw r8     // Catch: java.lang.Throwable -> L69
        L69:
            r7 = move-exception
        L6a:
            if (r3 == 0) goto L71
            if (r8 == 0) goto L77
            r3.close()     // Catch: java.lang.Throwable -> L5a java.lang.Throwable -> L72
        L71:
            throw r7     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L5a
        L72:
            r9 = move-exception
            r8.addSuppressed(r9)     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L5a
            goto L71
        L77:
            r3.close()     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L5a
            goto L71
        L7b:
            android.graphics.Typeface r7 = android.graphics.Typeface.createFromFile(r2)     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L5a
            if (r4 == 0) goto L86
            if (r6 == 0) goto L8e
            r4.close()     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L89
        L86:
            r6 = r7
            goto L5
        L89:
            r9 = move-exception
            r8.addSuppressed(r9)     // Catch: java.io.IOException -> L54
            goto L86
        L8e:
            r4.close()     // Catch: java.io.IOException -> L54
            goto L86
        L92:
            r7 = move-exception
            r9.addSuppressed(r7)     // Catch: java.io.IOException -> L54
            goto L53
        L97:
            r4.close()     // Catch: java.io.IOException -> L54
            goto L53
        L9b:
            r7 = move-exception
            r8 = r6
            goto L6a
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi21Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }
}
