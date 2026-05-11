package com.google.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzbz;
import com.google.android.gms.common.util.zzs;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class FirebaseOptions {
    private final String zzehx;
    private final String zzlgn;
    private final String zzlgo;
    private final String zzlgp;
    private final String zzlgq;
    private final String zzlgr;
    private final String zzlgs;

    public static final class Builder {
        private String zzehx;
        private String zzlgn;
        private String zzlgo;
        private String zzlgp;
        private String zzlgq;
        private String zzlgr;
        private String zzlgs;

        public Builder() {
        }

        public Builder(FirebaseOptions firebaseOptions) {
            this.zzehx = firebaseOptions.zzehx;
            this.zzlgn = firebaseOptions.zzlgn;
            this.zzlgo = firebaseOptions.zzlgo;
            this.zzlgp = firebaseOptions.zzlgp;
            this.zzlgq = firebaseOptions.zzlgq;
            this.zzlgr = firebaseOptions.zzlgr;
            this.zzlgs = firebaseOptions.zzlgs;
        }

        public final FirebaseOptions build() {
            return new FirebaseOptions(this.zzehx, this.zzlgn, this.zzlgo, this.zzlgp, this.zzlgq, this.zzlgr, this.zzlgs);
        }

        public final Builder setApiKey(@NonNull String str) {
            this.zzlgn = zzbp.zzh(str, "ApiKey must be set.");
            return this;
        }

        public final Builder setApplicationId(@NonNull String str) {
            this.zzehx = zzbp.zzh(str, "ApplicationId must be set.");
            return this;
        }

        public final Builder setDatabaseUrl(@Nullable String str) {
            this.zzlgo = str;
            return this;
        }

        public final Builder setGcmSenderId(@Nullable String str) {
            this.zzlgq = str;
            return this;
        }

        public final Builder setProjectId(@Nullable String str) {
            this.zzlgs = str;
            return this;
        }

        public final Builder setStorageBucket(@Nullable String str) {
            this.zzlgr = str;
            return this;
        }
    }

    private FirebaseOptions(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
        zzbp.zza(!zzs.zzgm(str), "ApplicationId must be set.");
        this.zzehx = str;
        this.zzlgn = str2;
        this.zzlgo = str3;
        this.zzlgp = str4;
        this.zzlgq = str5;
        this.zzlgr = str6;
        this.zzlgs = str7;
    }

    public static FirebaseOptions fromResource(Context context) {
        zzbz zzbzVar = new zzbz(context);
        String string = zzbzVar.getString("google_app_id");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return new FirebaseOptions(string, zzbzVar.getString("google_api_key"), zzbzVar.getString("firebase_database_url"), zzbzVar.getString("ga_trackingId"), zzbzVar.getString("gcm_defaultSenderId"), zzbzVar.getString("google_storage_bucket"), zzbzVar.getString("project_id"));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FirebaseOptions)) {
            return false;
        }
        FirebaseOptions firebaseOptions = (FirebaseOptions) obj;
        return zzbf.equal(this.zzehx, firebaseOptions.zzehx) && zzbf.equal(this.zzlgn, firebaseOptions.zzlgn) && zzbf.equal(this.zzlgo, firebaseOptions.zzlgo) && zzbf.equal(this.zzlgp, firebaseOptions.zzlgp) && zzbf.equal(this.zzlgq, firebaseOptions.zzlgq) && zzbf.equal(this.zzlgr, firebaseOptions.zzlgr) && zzbf.equal(this.zzlgs, firebaseOptions.zzlgs);
    }

    public final String getApiKey() {
        return this.zzlgn;
    }

    public final String getApplicationId() {
        return this.zzehx;
    }

    public final String getDatabaseUrl() {
        return this.zzlgo;
    }

    public final String getGcmSenderId() {
        return this.zzlgq;
    }

    public final String getProjectId() {
        return this.zzlgs;
    }

    public final String getStorageBucket() {
        return this.zzlgr;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzehx, this.zzlgn, this.zzlgo, this.zzlgp, this.zzlgq, this.zzlgr, this.zzlgs});
    }

    public final String toString() {
        return zzbf.zzt(this).zzg("applicationId", this.zzehx).zzg("apiKey", this.zzlgn).zzg("databaseUrl", this.zzlgo).zzg("gcmSenderId", this.zzlgq).zzg("storageBucket", this.zzlgr).zzg("projectId", this.zzlgs).toString();
    }
}
