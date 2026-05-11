package com.google.android.gms.common.data;

import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class zzg<T> extends AbstractDataBuffer<T> {
    private boolean zzfqx;
    private ArrayList<Integer> zzfqy;

    protected zzg(DataHolder dataHolder) {
        super(dataHolder);
        this.zzfqx = false;
    }

    private final void zzaiy() {
        synchronized (this) {
            if (!this.zzfqx) {
                int i = this.zzfle.zzfqp;
                this.zzfqy = new ArrayList<>();
                if (i > 0) {
                    this.zzfqy.add(0);
                    String strZzaix = zzaix();
                    String strZzd = this.zzfle.zzd(strZzaix, 0, this.zzfle.zzbx(0));
                    int i2 = 1;
                    while (i2 < i) {
                        int iZzbx = this.zzfle.zzbx(i2);
                        String strZzd2 = this.zzfle.zzd(strZzaix, i2, iZzbx);
                        if (strZzd2 == null) {
                            throw new NullPointerException(new StringBuilder(String.valueOf(strZzaix).length() + 78).append("Missing value for markerColumn: ").append(strZzaix).append(", at row: ").append(i2).append(", for window: ").append(iZzbx).toString());
                        }
                        if (strZzd2.equals(strZzd)) {
                            strZzd2 = strZzd;
                        } else {
                            this.zzfqy.add(Integer.valueOf(i2));
                        }
                        i2++;
                        strZzd = strZzd2;
                    }
                }
                this.zzfqx = true;
            }
        }
    }

    private final int zzca(int i) {
        if (i < 0 || i >= this.zzfqy.size()) {
            throw new IllegalArgumentException(new StringBuilder(53).append("Position ").append(i).append(" is out of bounds for this buffer").toString());
        }
        return this.zzfqy.get(i).intValue();
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final T get(int i) {
        int iIntValue;
        zzaiy();
        int iZzca = zzca(i);
        if (i < 0 || i == this.zzfqy.size()) {
            iIntValue = 0;
        } else {
            iIntValue = i == this.zzfqy.size() + (-1) ? this.zzfle.zzfqp - this.zzfqy.get(i).intValue() : this.zzfqy.get(i + 1).intValue() - this.zzfqy.get(i).intValue();
            if (iIntValue == 1) {
                this.zzfle.zzbx(zzca(i));
            }
        }
        return zzk(iZzca, iIntValue);
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public int getCount() {
        zzaiy();
        return this.zzfqy.size();
    }

    protected abstract String zzaix();

    protected abstract T zzk(int i, int i2);
}
