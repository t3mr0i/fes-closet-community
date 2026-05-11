package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class GoogleSignInAccount extends zzbck implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zzb();
    private static com.google.android.gms.common.util.zzd zzebu = zzh.zzald();
    private static Comparator<Scope> zzecb = new zza();
    private int versionCode;
    private String zzbsw;
    private List<Scope> zzdxw;
    private String zzeae;
    private String zzeaf;
    private String zzeav;
    private String zzebv;
    private String zzebw;
    private Uri zzebx;
    private String zzeby;
    private long zzebz;
    private String zzeca;

    GoogleSignInAccount(int i, String str, String str2, String str3, String str4, Uri uri, String str5, long j, String str6, List<Scope> list, String str7, String str8) {
        this.versionCode = i;
        this.zzbsw = str;
        this.zzeav = str2;
        this.zzebv = str3;
        this.zzebw = str4;
        this.zzebx = uri;
        this.zzeby = str5;
        this.zzebz = j;
        this.zzeca = str6;
        this.zzdxw = list;
        this.zzeae = str7;
        this.zzeaf = str8;
    }

    private final JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            if (getId() != null) {
                jSONObject.put("id", getId());
            }
            if (getIdToken() != null) {
                jSONObject.put("tokenId", getIdToken());
            }
            if (getEmail() != null) {
                jSONObject.put("email", getEmail());
            }
            if (getDisplayName() != null) {
                jSONObject.put("displayName", getDisplayName());
            }
            if (getGivenName() != null) {
                jSONObject.put("givenName", getGivenName());
            }
            if (getFamilyName() != null) {
                jSONObject.put("familyName", getFamilyName());
            }
            if (getPhotoUrl() != null) {
                jSONObject.put("photoUrl", getPhotoUrl().toString());
            }
            if (getServerAuthCode() != null) {
                jSONObject.put("serverAuthCode", getServerAuthCode());
            }
            jSONObject.put("expirationTime", this.zzebz);
            jSONObject.put("obfuscatedIdentifier", this.zzeca);
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zzdxw, zzecb);
            Iterator<Scope> it = this.zzdxw.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().zzaft());
            }
            jSONObject.put("grantedScopes", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static GoogleSignInAccount zzem(@Nullable String str) throws JSONException, NumberFormatException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String strOptString = jSONObject.optString("photoUrl", null);
        Uri uri = TextUtils.isEmpty(strOptString) ? null : Uri.parse(strOptString);
        long j = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String strOptString2 = jSONObject.optString("id");
        String strOptString3 = jSONObject.optString("tokenId", null);
        String strOptString4 = jSONObject.optString("email", null);
        String strOptString5 = jSONObject.optString("displayName", null);
        String strOptString6 = jSONObject.optString("givenName", null);
        String strOptString7 = jSONObject.optString("familyName", null);
        Long lValueOf = Long.valueOf(j);
        GoogleSignInAccount googleSignInAccount = new GoogleSignInAccount(3, strOptString2, strOptString3, strOptString4, strOptString5, uri, null, (lValueOf == null ? Long.valueOf(zzebu.currentTimeMillis() / 1000) : lValueOf).longValue(), zzbp.zzgg(jSONObject.getString("obfuscatedIdentifier")), new ArrayList((Collection) zzbp.zzu(hashSet)), strOptString6, strOptString7);
        googleSignInAccount.zzeby = jSONObject.optString("serverAuthCode", null);
        return googleSignInAccount;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GoogleSignInAccount) {
            return ((GoogleSignInAccount) obj).toJsonObject().toString().equals(toJsonObject().toString());
        }
        return false;
    }

    @Nullable
    public Account getAccount() {
        if (this.zzebv == null) {
            return null;
        }
        return new Account(this.zzebv, "com.google");
    }

    @Nullable
    public String getDisplayName() {
        return this.zzebw;
    }

    @Nullable
    public String getEmail() {
        return this.zzebv;
    }

    @Nullable
    public String getFamilyName() {
        return this.zzeaf;
    }

    @Nullable
    public String getGivenName() {
        return this.zzeae;
    }

    @NonNull
    public Set<Scope> getGrantedScopes() {
        return new HashSet(this.zzdxw);
    }

    @Nullable
    public String getId() {
        return this.zzbsw;
    }

    @Nullable
    public String getIdToken() {
        return this.zzeav;
    }

    @Nullable
    public Uri getPhotoUrl() {
        return this.zzebx;
    }

    @Nullable
    public String getServerAuthCode() {
        return this.zzeby;
    }

    public int hashCode() {
        return toJsonObject().toString().hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.versionCode);
        zzbcn.zza(parcel, 2, getId(), false);
        zzbcn.zza(parcel, 3, getIdToken(), false);
        zzbcn.zza(parcel, 4, getEmail(), false);
        zzbcn.zza(parcel, 5, getDisplayName(), false);
        zzbcn.zza(parcel, 6, (Parcelable) getPhotoUrl(), i, false);
        zzbcn.zza(parcel, 7, getServerAuthCode(), false);
        zzbcn.zza(parcel, 8, this.zzebz);
        zzbcn.zza(parcel, 9, this.zzeca, false);
        zzbcn.zzc(parcel, 10, this.zzdxw, false);
        zzbcn.zza(parcel, 11, getGivenName(), false);
        zzbcn.zza(parcel, 12, getFamilyName(), false);
        zzbcn.zzai(parcel, iZze);
    }

    public final boolean zzaad() {
        return zzebu.currentTimeMillis() / 1000 >= this.zzebz - 300;
    }

    @NonNull
    public final String zzaae() {
        return this.zzeca;
    }

    public final String zzaaf() throws JSONException {
        JSONObject jsonObject = toJsonObject();
        jsonObject.remove("serverAuthCode");
        return jsonObject.toString();
    }
}
