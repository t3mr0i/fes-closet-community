package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.EnvironmentCompat;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbh;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;

/* loaded from: classes.dex */
public class PlaceReport extends zzbck implements ReflectedParcelable {
    public static final Parcelable.Creator<PlaceReport> CREATOR = new zzl();
    private final String mTag;
    private final String zzdmd;
    private int zzdxr;
    private final String zzibi;

    PlaceReport(int i, String str, String str2, String str3) {
        this.zzdxr = i;
        this.zzibi = str;
        this.mTag = str2;
        this.zzdmd = str3;
    }

    public static PlaceReport create(String str, String str2) {
        boolean z = false;
        zzbp.zzu(str);
        zzbp.zzgg(str2);
        zzbp.zzgg(EnvironmentCompat.MEDIA_UNKNOWN);
        char c = 65535;
        switch (EnvironmentCompat.MEDIA_UNKNOWN.hashCode()) {
            case -1436706272:
                if (EnvironmentCompat.MEDIA_UNKNOWN.equals("inferredGeofencing")) {
                    c = 2;
                    break;
                }
                break;
            case -1194968642:
                if (EnvironmentCompat.MEDIA_UNKNOWN.equals("userReported")) {
                    c = 1;
                    break;
                }
                break;
            case -284840886:
                if (EnvironmentCompat.MEDIA_UNKNOWN.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                    c = 0;
                    break;
                }
                break;
            case -262743844:
                if (EnvironmentCompat.MEDIA_UNKNOWN.equals("inferredReverseGeocoding")) {
                    c = 4;
                    break;
                }
                break;
            case 1164924125:
                if (EnvironmentCompat.MEDIA_UNKNOWN.equals("inferredSnappedToRoad")) {
                    c = 5;
                    break;
                }
                break;
            case 1287171955:
                if (EnvironmentCompat.MEDIA_UNKNOWN.equals("inferredRadioSignals")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                z = true;
                break;
        }
        zzbp.zzb(z, "Invalid source");
        return new PlaceReport(1, str, str2, EnvironmentCompat.MEDIA_UNKNOWN);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return zzbf.equal(this.zzibi, placeReport.zzibi) && zzbf.equal(this.mTag, placeReport.mTag) && zzbf.equal(this.zzdmd, placeReport.zzdmd);
    }

    public String getPlaceId() {
        return this.zzibi;
    }

    public String getTag() {
        return this.mTag;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzibi, this.mTag, this.zzdmd});
    }

    public String toString() {
        zzbh zzbhVarZzt = zzbf.zzt(this);
        zzbhVarZzt.zzg("placeId", this.zzibi);
        zzbhVarZzt.zzg("tag", this.mTag);
        if (!EnvironmentCompat.MEDIA_UNKNOWN.equals(this.zzdmd)) {
            zzbhVarZzt.zzg(FirebaseAnalytics.Param.SOURCE, this.zzdmd);
        }
        return zzbhVarZzt.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxr);
        zzbcn.zza(parcel, 2, getPlaceId(), false);
        zzbcn.zza(parcel, 3, getTag(), false);
        zzbcn.zza(parcel, 4, this.zzdmd, false);
        zzbcn.zzai(parcel, iZze);
    }
}
