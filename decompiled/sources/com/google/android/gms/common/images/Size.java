package com.google.android.gms.common.images;

/* loaded from: classes.dex */
public final class Size {
    private final int zzakp;
    private final int zzakq;

    public Size(int i, int i2) {
        this.zzakp = i;
        this.zzakq = i2;
    }

    public static Size parseSize(String str) throws NumberFormatException {
        if (str == null) {
            throw new IllegalArgumentException("string must not be null");
        }
        int iIndexOf = str.indexOf(42);
        if (iIndexOf < 0) {
            iIndexOf = str.indexOf(120);
        }
        if (iIndexOf < 0) {
            throw zzfx(str);
        }
        try {
            return new Size(Integer.parseInt(str.substring(0, iIndexOf)), Integer.parseInt(str.substring(iIndexOf + 1)));
        } catch (NumberFormatException e) {
            throw zzfx(str);
        }
    }

    private static NumberFormatException zzfx(String str) {
        throw new NumberFormatException(new StringBuilder(String.valueOf(str).length() + 16).append("Invalid Size: \"").append(str).append("\"").toString());
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        return this.zzakp == size.zzakp && this.zzakq == size.zzakq;
    }

    public final int getHeight() {
        return this.zzakq;
    }

    public final int getWidth() {
        return this.zzakp;
    }

    public final int hashCode() {
        return this.zzakq ^ ((this.zzakp << 16) | (this.zzakp >>> 16));
    }

    public final String toString() {
        int i = this.zzakp;
        return new StringBuilder(23).append(i).append("x").append(this.zzakq).toString();
    }
}
