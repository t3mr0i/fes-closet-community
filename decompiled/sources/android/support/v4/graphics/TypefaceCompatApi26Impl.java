package android.support.v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

@RequiresApi(MotionEventCompat.AXIS_SCROLL)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    private static final String ABORT_CREATION_METHOD = "abortCreation";
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
    private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String FREEZE_METHOD = "freeze";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi26Impl";
    private static final Method sAbortCreation;
    private static final Method sAddFontFromAssetManager;
    private static final Method sAddFontFromBuffer;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;
    private static final Method sFreeze;

    static {
        Class fontFamilyClass;
        Constructor fontFamilyCtor;
        Method addFontMethod;
        Method addFromBufferMethod;
        Method freezeMethod;
        Method abortCreationMethod;
        Method createFromFamiliesWithDefaultMethod;
        try {
            fontFamilyClass = Class.forName(FONT_FAMILY_CLASS);
            fontFamilyCtor = fontFamilyClass.getConstructor(new Class[0]);
            addFontMethod = fontFamilyClass.getMethod(ADD_FONT_FROM_ASSET_MANAGER_METHOD, AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class);
            addFromBufferMethod = fontFamilyClass.getMethod(ADD_FONT_FROM_BUFFER_METHOD, ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE);
            freezeMethod = fontFamilyClass.getMethod(FREEZE_METHOD, new Class[0]);
            abortCreationMethod = fontFamilyClass.getMethod(ABORT_CREATION_METHOD, new Class[0]);
            Object familyArray = Array.newInstance((Class<?>) fontFamilyClass, 1);
            createFromFamiliesWithDefaultMethod = Typeface.class.getDeclaredMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, familyArray.getClass(), Integer.TYPE, Integer.TYPE);
            createFromFamiliesWithDefaultMethod.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
            fontFamilyClass = null;
            fontFamilyCtor = null;
            addFontMethod = null;
            addFromBufferMethod = null;
            freezeMethod = null;
            abortCreationMethod = null;
            createFromFamiliesWithDefaultMethod = null;
        }
        sFontFamilyCtor = fontFamilyCtor;
        sFontFamily = fontFamilyClass;
        sAddFontFromAssetManager = addFontMethod;
        sAddFontFromBuffer = addFromBufferMethod;
        sFreeze = freezeMethod;
        sAbortCreation = abortCreationMethod;
        sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod;
    }

    private static boolean isFontFamilyPrivateAPIAvailable() {
        if (sAddFontFromAssetManager == null) {
            Log.w(TAG, "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return sAddFontFromAssetManager != null;
    }

    private static Object newFamily() {
        try {
            return sFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontFromAssetManager(Context context, Object family, String fileName, int ttcIndex, int weight, int style) {
        try {
            Boolean result = (Boolean) sAddFontFromAssetManager.invoke(family, context.getAssets(), fileName, 0, false, Integer.valueOf(ttcIndex), Integer.valueOf(weight), Integer.valueOf(style), null);
            return result.booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontFromBuffer(Object family, ByteBuffer buffer, int ttcIndex, int weight, int style) {
        try {
            Boolean result = (Boolean) sAddFontFromBuffer.invoke(family, buffer, Integer.valueOf(ttcIndex), null, Integer.valueOf(weight), Integer.valueOf(style));
            return result.booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Typeface createFromFamiliesWithDefault(Object family) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, NegativeArraySizeException {
        try {
            Object familyArray = Array.newInstance((Class<?>) sFontFamily, 1);
            Array.set(familyArray, 0, family);
            return (Typeface) sCreateFromFamiliesWithDefault.invoke(null, familyArray, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean freeze(Object family) {
        try {
            Boolean result = (Boolean) sFreeze.invoke(family, new Object[0]);
            return result.booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean abortCreation(Object family) {
        try {
            Boolean result = (Boolean) sAbortCreation.invoke(family, new Object[0]);
            return result.booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.support.v4.graphics.TypefaceCompatBaseImpl, android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry entry, Resources resources, int style) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context, entry, resources, style);
        }
        Object fontFamily = newFamily();
        for (FontResourcesParserCompat.FontFileResourceEntry fontFile : entry.getEntries()) {
            if (!addFontFromAssetManager(context, fontFamily, fontFile.getFileName(), 0, fontFile.getWeight(), fontFile.isItalic() ? 1 : 0)) {
                abortCreation(fontFamily);
                return null;
            }
        }
        if (freeze(fontFamily)) {
            return createFromFamiliesWithDefault(fontFamily);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0060  */
    @Override // android.support.v4.graphics.TypefaceCompatApi21Impl, android.support.v4.graphics.TypefaceCompatBaseImpl, android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r19, @android.support.annotation.Nullable android.os.CancellationSignal r20, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] r21, int r22) throws java.lang.Throwable {
        /*
            r18 = this;
            r0 = r21
            int r13 = r0.length
            r14 = 1
            if (r13 >= r14) goto L8
            r13 = 0
        L7:
            return r13
        L8:
            boolean r13 = isFontFamilyPrivateAPIAvailable()
            if (r13 != 0) goto L6f
            r0 = r18
            r1 = r21
            r2 = r22
            android.support.v4.provider.FontsContractCompat$FontInfo r4 = r0.findBestInfo(r1, r2)
            android.content.ContentResolver r10 = r19.getContentResolver()
            android.net.Uri r13 = r4.getUri()     // Catch: java.io.IOException -> L53
            java.lang.String r14 = "r"
            r0 = r20
            android.os.ParcelFileDescriptor r9 = r10.openFileDescriptor(r13, r14, r0)     // Catch: java.io.IOException -> L53
            r15 = 0
            android.graphics.Typeface$Builder r13 = new android.graphics.Typeface$Builder     // Catch: java.lang.Throwable -> L5a java.lang.Throwable -> Ld1
            java.io.FileDescriptor r14 = r9.getFileDescriptor()     // Catch: java.lang.Throwable -> L5a java.lang.Throwable -> Ld1
            r13.<init>(r14)     // Catch: java.lang.Throwable -> L5a java.lang.Throwable -> Ld1
            int r14 = r4.getWeight()     // Catch: java.lang.Throwable -> L5a java.lang.Throwable -> Ld1
            android.graphics.Typeface$Builder r13 = r13.setWeight(r14)     // Catch: java.lang.Throwable -> L5a java.lang.Throwable -> Ld1
            boolean r14 = r4.isItalic()     // Catch: java.lang.Throwable -> L5a java.lang.Throwable -> Ld1
            android.graphics.Typeface$Builder r13 = r13.setItalic(r14)     // Catch: java.lang.Throwable -> L5a java.lang.Throwable -> Ld1
            android.graphics.Typeface r13 = r13.build()     // Catch: java.lang.Throwable -> L5a java.lang.Throwable -> Ld1
            if (r9 == 0) goto L7
            if (r15 == 0) goto L56
            r9.close()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L53
            goto L7
        L4e:
            r14 = move-exception
            r15.addSuppressed(r14)     // Catch: java.io.IOException -> L53
            goto L7
        L53:
            r5 = move-exception
            r13 = 0
            goto L7
        L56:
            r9.close()     // Catch: java.io.IOException -> L53
            goto L7
        L5a:
            r13 = move-exception
            throw r13     // Catch: java.lang.Throwable -> L5c
        L5c:
            r14 = move-exception
            r15 = r13
        L5e:
            if (r9 == 0) goto L65
            if (r15 == 0) goto L6b
            r9.close()     // Catch: java.io.IOException -> L53 java.lang.Throwable -> L66
        L65:
            throw r14     // Catch: java.io.IOException -> L53
        L66:
            r13 = move-exception
            r15.addSuppressed(r13)     // Catch: java.io.IOException -> L53
            goto L65
        L6b:
            r9.close()     // Catch: java.io.IOException -> L53
            goto L65
        L6f:
            r0 = r19
            r1 = r21
            r2 = r20
            java.util.Map r12 = android.support.v4.provider.FontsContractCompat.prepareFontData(r0, r1, r2)
            java.lang.Object r8 = newFamily()
            r3 = 0
            r0 = r21
            int r15 = r0.length
            r13 = 0
            r14 = r13
        L83:
            if (r14 >= r15) goto Lba
            r6 = r21[r14]
            android.net.Uri r13 = r6.getUri()
            java.lang.Object r7 = r12.get(r13)
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            if (r7 != 0) goto L97
        L93:
            int r13 = r14 + 1
            r14 = r13
            goto L83
        L97:
            int r16 = r6.getTtcIndex()
            int r17 = r6.getWeight()
            boolean r13 = r6.isItalic()
            if (r13 == 0) goto Lb6
            r13 = 1
        La6:
            r0 = r16
            r1 = r17
            boolean r11 = addFontFromBuffer(r8, r7, r0, r1, r13)
            if (r11 != 0) goto Lb8
            abortCreation(r8)
            r13 = 0
            goto L7
        Lb6:
            r13 = 0
            goto La6
        Lb8:
            r3 = 1
            goto L93
        Lba:
            if (r3 != 0) goto Lc2
            abortCreation(r8)
            r13 = 0
            goto L7
        Lc2:
            boolean r13 = freeze(r8)
            if (r13 != 0) goto Lcb
            r13 = 0
            goto L7
        Lcb:
            android.graphics.Typeface r13 = createFromFamiliesWithDefault(r8)
            goto L7
        Ld1:
            r13 = move-exception
            r14 = r13
            goto L5e
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    @Override // android.support.v4.graphics.TypefaceCompatBaseImpl, android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int id, String path, int style) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context, resources, id, path, style);
        }
        Object fontFamily = newFamily();
        if (!addFontFromAssetManager(context, fontFamily, path, 0, -1, -1)) {
            abortCreation(fontFamily);
            return null;
        }
        if (freeze(fontFamily)) {
            return createFromFamiliesWithDefault(fontFamily);
        }
        return null;
    }
}
