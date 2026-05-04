package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzego extends IllegalArgumentException {
    zzego(int i, int i2) {
        super(new StringBuilder(54).append("Unpaired surrogate at index ").append(i).append(" of ").append(i2).toString());
    }
}
