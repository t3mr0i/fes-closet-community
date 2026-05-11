package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzbco;

/* loaded from: classes.dex */
public class zzd<T extends zzbco> extends AbstractDataBuffer<T> {
    private static final String[] zzfqi = {"data"};
    private final Parcelable.Creator<T> zzfqj;

    public zzd(DataHolder dataHolder, Parcelable.Creator<T> creator) {
        super(dataHolder);
        this.zzfqj = creator;
    }

    public static <T extends zzbco> void zza(DataHolder.zza zzaVar, T t) {
        Parcel parcelObtain = Parcel.obtain();
        t.writeToParcel(parcelObtain, 0);
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", parcelObtain.marshall());
        zzaVar.zza(contentValues);
        parcelObtain.recycle();
    }

    public static DataHolder.zza zzaiv() {
        return DataHolder.zza(zzfqi);
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    /* renamed from: zzbw, reason: merged with bridge method [inline-methods] */
    public T get(int i) {
        byte[] bArrZzg = this.zzfle.zzg("data", i, this.zzfle.zzbx(i));
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArrZzg, 0, bArrZzg.length);
        parcelObtain.setDataPosition(0);
        T tCreateFromParcel = this.zzfqj.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return tCreateFromParcel;
    }
}
