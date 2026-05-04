package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* loaded from: classes.dex */
public final class zzami extends zzams {
    private static boolean zzdno;
    private AdvertisingIdClient.Info zzdnp;
    private final zzaoz zzdnq;
    private String zzdnr;
    private boolean zzdns;
    private final Object zzdnt;

    zzami(zzamu zzamuVar) {
        super(zzamuVar);
        this.zzdns = false;
        this.zzdnt = new Object();
        this.zzdnq = new zzaoz(zzamuVar.zzvx());
    }

    private final boolean zza(AdvertisingIdClient.Info info, AdvertisingIdClient.Info info2) {
        String strZzxq;
        String id = info2 == null ? null : info2.getId();
        if (TextUtils.isEmpty(id)) {
            return true;
        }
        String strZzxp = zzwg().zzxp();
        synchronized (this.zzdnt) {
            if (!this.zzdns) {
                this.zzdnr = zzvq();
                this.zzdns = true;
            } else if (TextUtils.isEmpty(this.zzdnr)) {
                String id2 = info != null ? info.getId() : null;
                if (id2 == null) {
                    String strValueOf = String.valueOf(id);
                    String strValueOf2 = String.valueOf(strZzxp);
                    return zzdl(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
                }
                String strValueOf3 = String.valueOf(id2);
                String strValueOf4 = String.valueOf(strZzxp);
                this.zzdnr = zzdk(strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3));
            }
            String strValueOf5 = String.valueOf(id);
            String strValueOf6 = String.valueOf(strZzxp);
            String strZzdk = zzdk(strValueOf6.length() != 0 ? strValueOf5.concat(strValueOf6) : new String(strValueOf5));
            if (TextUtils.isEmpty(strZzdk)) {
                return false;
            }
            if (strZzdk.equals(this.zzdnr)) {
                return true;
            }
            if (TextUtils.isEmpty(this.zzdnr)) {
                strZzxq = strZzxp;
            } else {
                zzdm("Resetting the client id because Advertising Id changed.");
                strZzxq = zzwg().zzxq();
                zza("New client Id", strZzxq);
            }
            String strValueOf7 = String.valueOf(id);
            String strValueOf8 = String.valueOf(strZzxq);
            return zzdl(strValueOf8.length() != 0 ? strValueOf7.concat(strValueOf8) : new String(strValueOf7));
        }
    }

    private static String zzdk(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigestZzec = zzapd.zzec("MD5");
        if (messageDigestZzec == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, messageDigestZzec.digest(str.getBytes())));
    }

    private final boolean zzdl(String str) throws NoSuchAlgorithmException, IOException {
        try {
            String strZzdk = zzdk(str);
            zzdm("Storing hashed adid.");
            FileOutputStream fileOutputStreamOpenFileOutput = getContext().openFileOutput("gaClientIdData", 0);
            fileOutputStreamOpenFileOutput.write(strZzdk.getBytes());
            fileOutputStreamOpenFileOutput.close();
            this.zzdnr = strZzdk;
            return true;
        } catch (IOException e) {
            zze("Error creating hash file", e);
            return false;
        }
    }

    private final synchronized AdvertisingIdClient.Info zzvo() {
        if (this.zzdnq.zzu(1000L)) {
            this.zzdnq.start();
            AdvertisingIdClient.Info infoZzvp = zzvp();
            if (zza(this.zzdnp, infoZzvp)) {
                this.zzdnp = infoZzvp;
            } else {
                zzdq("Failed to reset client id on adid change. Not using adid");
                this.zzdnp = new AdvertisingIdClient.Info("", false);
            }
        }
        return this.zzdnp;
    }

    private final AdvertisingIdClient.Info zzvp() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(getContext());
        } catch (IllegalStateException e) {
            zzdp("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
            return null;
        } catch (Throwable th) {
            if (zzdno) {
                return null;
            }
            zzdno = true;
            zzd("Error getting advertiser id", th);
            return null;
        }
    }

    private final String zzvq() throws IOException {
        IOException e;
        String str = null;
        try {
            FileInputStream fileInputStreamOpenFileInput = getContext().openFileInput("gaClientIdData");
            byte[] bArr = new byte[128];
            int i = fileInputStreamOpenFileInput.read(bArr, 0, 128);
            if (fileInputStreamOpenFileInput.available() > 0) {
                zzdp("Hash file seems corrupted, deleting it.");
                fileInputStreamOpenFileInput.close();
                getContext().deleteFile("gaClientIdData");
            } else if (i <= 0) {
                zzdm("Hash file is empty.");
                fileInputStreamOpenFileInput.close();
            } else {
                String str2 = new String(bArr, 0, i);
                try {
                    fileInputStreamOpenFileInput.close();
                    str = str2;
                } catch (FileNotFoundException e2) {
                    str = str2;
                } catch (IOException e3) {
                    e = e3;
                    str = str2;
                    zzd("Error reading Hash file, deleting it", e);
                    getContext().deleteFile("gaClientIdData");
                    return str;
                }
            }
        } catch (FileNotFoundException e4) {
        } catch (IOException e5) {
            e = e5;
        }
        return str;
    }

    @Override // com.google.android.gms.internal.zzams
    protected final void zzuk() {
    }

    public final boolean zzvg() {
        zzwk();
        AdvertisingIdClient.Info infoZzvo = zzvo();
        return (infoZzvo == null || infoZzvo.isLimitAdTrackingEnabled()) ? false : true;
    }

    public final String zzvn() {
        zzwk();
        AdvertisingIdClient.Info infoZzvo = zzvo();
        String id = infoZzvo != null ? infoZzvo.getId() : null;
        if (TextUtils.isEmpty(id)) {
            return null;
        }
        return id;
    }
}
