package com.google.firebase.messaging;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.Map;

/* loaded from: classes.dex */
public final class RemoteMessage extends zzbck {
    public static final Parcelable.Creator<RemoteMessage> CREATOR = new zzf();
    Bundle mBundle;
    private Map<String, String> zzdkr;
    private Notification zzmmk;

    public static class Builder {
        private final Bundle mBundle = new Bundle();
        private final Map<String, String> zzdkr = new ArrayMap();

        public Builder(String str) {
            if (TextUtils.isEmpty(str)) {
                String strValueOf = String.valueOf(str);
                throw new IllegalArgumentException(strValueOf.length() != 0 ? "Invalid to: ".concat(strValueOf) : new String("Invalid to: "));
            }
            this.mBundle.putString("google.to", str);
        }

        public Builder addData(String str, String str2) {
            this.zzdkr.put(str, str2);
            return this;
        }

        public RemoteMessage build() {
            Bundle bundle = new Bundle();
            for (Map.Entry<String, String> entry : this.zzdkr.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
            bundle.putAll(this.mBundle);
            String token = FirebaseInstanceId.getInstance().getToken();
            if (token != null) {
                this.mBundle.putString("from", token);
            } else {
                this.mBundle.remove("from");
            }
            return new RemoteMessage(bundle);
        }

        public Builder clearData() {
            this.zzdkr.clear();
            return this;
        }

        public Builder setCollapseKey(String str) {
            this.mBundle.putString("collapse_key", str);
            return this;
        }

        public Builder setData(Map<String, String> map) {
            this.zzdkr.clear();
            this.zzdkr.putAll(map);
            return this;
        }

        public Builder setMessageId(String str) {
            this.mBundle.putString("google.message_id", str);
            return this;
        }

        public Builder setMessageType(String str) {
            this.mBundle.putString("message_type", str);
            return this;
        }

        public Builder setTtl(int i) {
            this.mBundle.putString("google.ttl", String.valueOf(i));
            return this;
        }
    }

    public static class Notification {
        private final String mTag;
        private final String zzbrp;
        private final String zzehj;
        private final String zzmml;
        private final String[] zzmmm;
        private final String zzmmn;
        private final String[] zzmmo;
        private final String zzmmp;
        private final String zzmmq;
        private final String zzmmr;
        private final String zzmms;
        private final Uri zzmmt;

        private Notification(Bundle bundle) {
            this.zzehj = zza.zze(bundle, "gcm.n.title");
            this.zzmml = zza.zzh(bundle, "gcm.n.title");
            this.zzmmm = zzk(bundle, "gcm.n.title");
            this.zzbrp = zza.zze(bundle, "gcm.n.body");
            this.zzmmn = zza.zzh(bundle, "gcm.n.body");
            this.zzmmo = zzk(bundle, "gcm.n.body");
            this.zzmmp = zza.zze(bundle, "gcm.n.icon");
            this.zzmmq = zza.zzaf(bundle);
            this.mTag = zza.zze(bundle, "gcm.n.tag");
            this.zzmmr = zza.zze(bundle, "gcm.n.color");
            this.zzmms = zza.zze(bundle, "gcm.n.click_action");
            this.zzmmt = zza.zzae(bundle);
        }

        private static String[] zzk(Bundle bundle, String str) {
            Object[] objArrZzi = zza.zzi(bundle, str);
            if (objArrZzi == null) {
                return null;
            }
            String[] strArr = new String[objArrZzi.length];
            for (int i = 0; i < objArrZzi.length; i++) {
                strArr[i] = String.valueOf(objArrZzi[i]);
            }
            return strArr;
        }

        @Nullable
        public String getBody() {
            return this.zzbrp;
        }

        @Nullable
        public String[] getBodyLocalizationArgs() {
            return this.zzmmo;
        }

        @Nullable
        public String getBodyLocalizationKey() {
            return this.zzmmn;
        }

        @Nullable
        public String getClickAction() {
            return this.zzmms;
        }

        @Nullable
        public String getColor() {
            return this.zzmmr;
        }

        @Nullable
        public String getIcon() {
            return this.zzmmp;
        }

        @Nullable
        public Uri getLink() {
            return this.zzmmt;
        }

        @Nullable
        public String getSound() {
            return this.zzmmq;
        }

        @Nullable
        public String getTag() {
            return this.mTag;
        }

        @Nullable
        public String getTitle() {
            return this.zzehj;
        }

        @Nullable
        public String[] getTitleLocalizationArgs() {
            return this.zzmmm;
        }

        @Nullable
        public String getTitleLocalizationKey() {
            return this.zzmml;
        }
    }

    RemoteMessage(Bundle bundle) {
        this.mBundle = bundle;
    }

    public final String getCollapseKey() {
        return this.mBundle.getString("collapse_key");
    }

    public final Map<String, String> getData() {
        if (this.zzdkr == null) {
            this.zzdkr = new ArrayMap();
            for (String str : this.mBundle.keySet()) {
                Object obj = this.mBundle.get(str);
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (!str.startsWith("google.") && !str.startsWith("gcm.") && !str.equals("from") && !str.equals("message_type") && !str.equals("collapse_key")) {
                        this.zzdkr.put(str, str2);
                    }
                }
            }
        }
        return this.zzdkr;
    }

    public final String getFrom() {
        return this.mBundle.getString("from");
    }

    public final String getMessageId() {
        String string = this.mBundle.getString("google.message_id");
        return string == null ? this.mBundle.getString("message_id") : string;
    }

    public final String getMessageType() {
        return this.mBundle.getString("message_type");
    }

    public final Notification getNotification() {
        if (this.zzmmk == null && zza.zzad(this.mBundle)) {
            this.zzmmk = new Notification(this.mBundle);
        }
        return this.zzmmk;
    }

    public final long getSentTime() {
        Object obj = this.mBundle.get("google.sent_time");
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        if (obj instanceof String) {
            try {
                return Long.parseLong((String) obj);
            } catch (NumberFormatException e) {
                String strValueOf = String.valueOf(obj);
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(strValueOf).length() + 19).append("Invalid sent time: ").append(strValueOf).toString());
            }
        }
        return 0L;
    }

    public final String getTo() {
        return this.mBundle.getString("google.to");
    }

    public final int getTtl() {
        Object obj = this.mBundle.get("google.ttl");
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                String strValueOf = String.valueOf(obj);
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(strValueOf).length() + 13).append("Invalid TTL: ").append(strValueOf).toString());
            }
        }
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 2, this.mBundle, false);
        zzbcn.zzai(parcel, iZze);
    }
}
