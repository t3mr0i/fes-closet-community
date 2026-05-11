package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zzn;
import com.google.android.gms.auth.api.signin.internal.zzo;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class GoogleSignInOptions extends zzbck implements Api.ApiOptions.Optional, ReflectedParcelable {
    private int versionCode;
    private Account zzduy;
    private boolean zzeao;
    private String zzeap;
    private final ArrayList<Scope> zzecf;
    private final boolean zzecg;
    private final boolean zzech;
    private String zzeci;
    private ArrayList<zzn> zzecj;
    private Map<Integer, zzn> zzeck;
    public static final Scope zzecc = new Scope(Scopes.PROFILE);
    public static final Scope zzecd = new Scope("email");
    public static final Scope zzece = new Scope("openid");
    private static Scope SCOPE_GAMES = new Scope(Scopes.GAMES);
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(SCOPE_GAMES, new Scope[0]).build();
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new zzd();
    private static Comparator<Scope> zzecb = new zzc();

    public static final class Builder {
        private Account zzduy;
        private boolean zzeao;
        private String zzeap;
        private boolean zzecg;
        private boolean zzech;
        private String zzeci;
        private Set<Scope> zzecl;
        private Map<Integer, zzn> zzecm;

        public Builder() {
            this.zzecl = new HashSet();
            this.zzecm = new HashMap();
        }

        public Builder(@NonNull GoogleSignInOptions googleSignInOptions) {
            this.zzecl = new HashSet();
            this.zzecm = new HashMap();
            zzbp.zzu(googleSignInOptions);
            this.zzecl = new HashSet(googleSignInOptions.zzecf);
            this.zzecg = googleSignInOptions.zzecg;
            this.zzech = googleSignInOptions.zzech;
            this.zzeao = googleSignInOptions.zzeao;
            this.zzeap = googleSignInOptions.zzeap;
            this.zzduy = googleSignInOptions.zzduy;
            this.zzeci = googleSignInOptions.zzeci;
            this.zzecm = GoogleSignInOptions.zzu(googleSignInOptions.zzecj);
        }

        private final String zzeo(String str) {
            zzbp.zzgg(str);
            zzbp.zzb(this.zzeap == null || this.zzeap.equals(str), "two different server client ids provided");
            return str;
        }

        public final Builder addExtension(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
            if (this.zzecm.containsKey(1)) {
                throw new IllegalStateException("Only one extension per type may be added");
            }
            this.zzecm.put(1, new zzn(googleSignInOptionsExtension));
            return this;
        }

        public final GoogleSignInOptions build() {
            if (this.zzeao && (this.zzduy == null || !this.zzecl.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(3, new ArrayList(this.zzecl), this.zzduy, this.zzeao, this.zzecg, this.zzech, this.zzeap, this.zzeci, this.zzecm, null);
        }

        public final Builder requestEmail() {
            this.zzecl.add(GoogleSignInOptions.zzecd);
            return this;
        }

        public final Builder requestId() {
            this.zzecl.add(GoogleSignInOptions.zzece);
            return this;
        }

        public final Builder requestIdToken(String str) {
            this.zzeao = true;
            this.zzeap = zzeo(str);
            return this;
        }

        public final Builder requestProfile() {
            this.zzecl.add(GoogleSignInOptions.zzecc);
            return this;
        }

        public final Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.zzecl.add(scope);
            this.zzecl.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public final Builder requestServerAuthCode(String str) {
            return requestServerAuthCode(str, false);
        }

        public final Builder requestServerAuthCode(String str, boolean z) {
            this.zzecg = true;
            this.zzeap = zzeo(str);
            this.zzech = z;
            return this;
        }

        public final Builder setAccountName(String str) {
            this.zzduy = new Account(zzbp.zzgg(str), "com.google");
            return this;
        }

        public final Builder setHostedDomain(String str) {
            this.zzeci = zzbp.zzgg(str);
            return this;
        }
    }

    GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, ArrayList<zzn> arrayList2) {
        this(i, arrayList, account, z, z2, z3, str, str2, zzu(arrayList2));
    }

    private GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map<Integer, zzn> map) {
        this.versionCode = i;
        this.zzecf = arrayList;
        this.zzduy = account;
        this.zzeao = z;
        this.zzecg = z2;
        this.zzech = z3;
        this.zzeap = str;
        this.zzeci = str2;
        this.zzecj = new ArrayList<>(map.values());
        this.zzeck = map;
    }

    /* synthetic */ GoogleSignInOptions(int i, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map map, zzc zzcVar) {
        this(3, (ArrayList<Scope>) arrayList, account, z, z2, z3, str, str2, (Map<Integer, zzn>) map);
    }

    private final JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zzecf, zzecb);
            ArrayList<Scope> arrayList = this.zzecf;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Scope scope = arrayList.get(i);
                i++;
                jSONArray.put(scope.zzaft());
            }
            jSONObject.put("scopes", jSONArray);
            if (this.zzduy != null) {
                jSONObject.put("accountName", this.zzduy.name);
            }
            jSONObject.put("idTokenRequested", this.zzeao);
            jSONObject.put("forceCodeForRefreshToken", this.zzech);
            jSONObject.put("serverAuthRequested", this.zzecg);
            if (!TextUtils.isEmpty(this.zzeap)) {
                jSONObject.put("serverClientId", this.zzeap);
            }
            if (!TextUtils.isEmpty(this.zzeci)) {
                jSONObject.put("hostedDomain", this.zzeci);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static GoogleSignInOptions zzen(@Nullable String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String strOptString = jSONObject.optString("accountName", null);
        return new GoogleSignInOptions(3, (ArrayList<Scope>) new ArrayList(hashSet), !TextUtils.isEmpty(strOptString) ? new Account(strOptString, "com.google") : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", null), jSONObject.optString("hostedDomain", null), new HashMap());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<Integer, zzn> zzu(@Nullable List<zzn> list) {
        HashMap map = new HashMap();
        if (list == null) {
            return map;
        }
        for (zzn zznVar : list) {
            map.put(Integer.valueOf(zznVar.getType()), zznVar);
        }
        return map;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.zzecj.size() > 0 || googleSignInOptions.zzecj.size() > 0 || this.zzecf.size() != googleSignInOptions.zzaag().size() || !this.zzecf.containsAll(googleSignInOptions.zzaag())) {
                return false;
            }
            if (this.zzduy == null) {
                if (googleSignInOptions.zzduy != null) {
                    return false;
                }
            } else if (!this.zzduy.equals(googleSignInOptions.zzduy)) {
                return false;
            }
            if (TextUtils.isEmpty(this.zzeap)) {
                if (!TextUtils.isEmpty(googleSignInOptions.zzeap)) {
                    return false;
                }
            } else if (!this.zzeap.equals(googleSignInOptions.zzeap)) {
                return false;
            }
            if (this.zzech == googleSignInOptions.zzech && this.zzeao == googleSignInOptions.zzeao) {
                return this.zzecg == googleSignInOptions.zzecg;
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public final Account getAccount() {
        return this.zzduy;
    }

    public Scope[] getScopeArray() {
        return (Scope[]) this.zzecf.toArray(new Scope[this.zzecf.size()]);
    }

    public final String getServerClientId() {
        return this.zzeap;
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        ArrayList<Scope> arrayList2 = this.zzecf;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Scope scope = arrayList2.get(i);
            i++;
            arrayList.add(scope.zzaft());
        }
        Collections.sort(arrayList);
        return new zzo().zzo(arrayList).zzo(this.zzduy).zzo(this.zzeap).zzaq(this.zzech).zzaq(this.zzeao).zzaq(this.zzecg).zzaao();
    }

    public final boolean isIdTokenRequested() {
        return this.zzeao;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.versionCode);
        zzbcn.zzc(parcel, 2, zzaag(), false);
        zzbcn.zza(parcel, 3, (Parcelable) this.zzduy, i, false);
        zzbcn.zza(parcel, 4, this.zzeao);
        zzbcn.zza(parcel, 5, this.zzecg);
        zzbcn.zza(parcel, 6, this.zzech);
        zzbcn.zza(parcel, 7, this.zzeap, false);
        zzbcn.zza(parcel, 8, this.zzeci, false);
        zzbcn.zzc(parcel, 9, this.zzecj, false);
        zzbcn.zzai(parcel, iZze);
    }

    public final ArrayList<Scope> zzaag() {
        return new ArrayList<>(this.zzecf);
    }

    public final boolean zzaah() {
        return this.zzecg;
    }

    public final String zzaai() {
        return toJsonObject().toString();
    }
}
