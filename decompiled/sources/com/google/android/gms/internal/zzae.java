package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public final class zzae {
    private static Comparator<byte[]> zzbu = new zzaf();
    private List<byte[]> zzbq = new LinkedList();
    private List<byte[]> zzbr = new ArrayList(64);
    private int zzbs = 0;
    private final int zzbt;

    public zzae(int i) {
        this.zzbt = i;
    }

    private final synchronized void zzm() {
        while (this.zzbs > this.zzbt) {
            byte[] bArrRemove = this.zzbq.remove(0);
            this.zzbr.remove(bArrRemove);
            this.zzbs -= bArrRemove.length;
        }
    }

    public final synchronized void zza(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.zzbt) {
                this.zzbq.add(bArr);
                int iBinarySearch = Collections.binarySearch(this.zzbr, bArr, zzbu);
                if (iBinarySearch < 0) {
                    iBinarySearch = (-iBinarySearch) - 1;
                }
                this.zzbr.add(iBinarySearch, bArr);
                this.zzbs += bArr.length;
                zzm();
            }
        }
    }

    public final synchronized byte[] zzb(int i) {
        byte[] bArr;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.zzbr.size()) {
                bArr = new byte[i];
                break;
            }
            bArr = this.zzbr.get(i3);
            if (bArr.length >= i) {
                this.zzbs -= bArr.length;
                this.zzbr.remove(i3);
                this.zzbq.remove(bArr);
                break;
            }
            i2 = i3 + 1;
        }
        return bArr;
    }
}
