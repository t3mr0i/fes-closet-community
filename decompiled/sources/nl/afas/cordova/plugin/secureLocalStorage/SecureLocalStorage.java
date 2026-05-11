package nl.afas.cordova.plugin.secureLocalStorage;

import android.annotation.TargetApi;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.security.auth.x500.X500Principal;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class SecureLocalStorage extends CordovaPlugin {
    private static final String SECURELOCALSTORAGEALIAS = "SECURELOCALSTORAGEPPKEYALIAS";
    private static final String SECURELOCALSTORAGEFILE = "secureLocalStorage.sdat";
    private static final String SECURELOCALSTORAGEKEY = "secureLocalStorage.kdat";
    private CordovaInterface _cordova;
    private final ReentrantLock lock = new ReentrantLock();

    public enum ActionId {
        ACTION_NONE,
        ACTION_CLEARIFINVALID,
        ACTION_CLEAR,
        ACTION_GETITEM,
        ACTION_SETITEM,
        ACTION_REMOVEITEM
    }

    public class SecureLocalStorageException extends Exception {
        public SecureLocalStorageException(String message) {
            super(message);
        }

        public SecureLocalStorageException(String message, Exception ex) {
            super(message, ex);
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this._cordova = cordova;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        final ActionId actionId = getActionId(action);
        if (actionId == ActionId.ACTION_NONE) {
            return false;
        }
        PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true);
        callbackContext.sendPluginResult(pluginResult);
        this._cordova.getThreadPool().execute(new Runnable() { // from class: nl.afas.cordova.plugin.secureLocalStorage.SecureLocalStorage.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SecureLocalStorage.this.handleAction(actionId, args, callbackContext);
                } catch (SecureLocalStorageException ex) {
                    SecureLocalStorage.this.handleException(ex, callbackContext);
                } catch (JSONException ex2) {
                    SecureLocalStorage.this.handleException(ex2, callbackContext);
                }
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleException(Exception ex, CallbackContext callbackContext) {
        ex.printStackTrace();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        pw.close();
        PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, pw.toString());
        pluginResult.setKeepCallback(false);
        callbackContext.sendPluginResult(pluginResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAction(ActionId actionId, JSONArray args, CallbackContext callbackContext) throws JSONException, SecureLocalStorageException {
        if (Build.VERSION.SDK_INT < 18) {
            throw new SecureLocalStorageException("Invalid API Level (must be >= 18");
        }
        File file = this._cordova.getActivity().getBaseContext().getFileStreamPath(SECURELOCALSTORAGEFILE);
        HashMap<String, String> hashMap = new HashMap<>();
        this.lock.lock();
        try {
            KeyStore keyStore = initKeyStore();
            if (actionId == ActionId.ACTION_CLEAR) {
                clear(file, keyStore);
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
                pluginResult.setKeepCallback(false);
                callbackContext.sendPluginResult(pluginResult);
            } else if (actionId == ActionId.ACTION_CLEARIFINVALID) {
                try {
                    checkValidity();
                    if (file.exists()) {
                        HashMap<String, String> hashMap2 = readAndDecryptStorage(keyStore);
                        clear(file, keyStore);
                        keyStore = initKeyStore();
                        generateKey(keyStore);
                        writeAndEncryptStorage(keyStore, hashMap2);
                    }
                } catch (SecureLocalStorageException e) {
                    clear(file, keyStore);
                }
                PluginResult pluginResult2 = new PluginResult(PluginResult.Status.OK);
                pluginResult2.setKeepCallback(false);
                callbackContext.sendPluginResult(pluginResult2);
            } else {
                if (!file.exists()) {
                    generateKey(keyStore);
                    writeAndEncryptStorage(keyStore, hashMap);
                }
                HashMap<String, String> hashMap3 = readAndDecryptStorage(keyStore);
                String key = args.getString(0);
                if (key == null || key.length() == 0) {
                    throw new SecureLocalStorageException("Key is empty or null");
                }
                if (actionId == ActionId.ACTION_GETITEM) {
                    if (hashMap3.containsKey(key)) {
                        if (callbackContext != null) {
                            PluginResult pluginResult3 = new PluginResult(PluginResult.Status.OK, hashMap3.get(key));
                            pluginResult3.setKeepCallback(false);
                            callbackContext.sendPluginResult(pluginResult3);
                        }
                    } else {
                        PluginResult pluginResult4 = new PluginResult(PluginResult.Status.OK, (String) null);
                        pluginResult4.setKeepCallback(false);
                        callbackContext.sendPluginResult(pluginResult4);
                    }
                } else if (actionId == ActionId.ACTION_SETITEM) {
                    String value = args.getString(1);
                    if (value == null) {
                        throw new SecureLocalStorageException("Value is null");
                    }
                    hashMap3.put(key, value);
                    writeAndEncryptStorage(keyStore, hashMap3);
                    PluginResult pluginResult5 = new PluginResult(PluginResult.Status.OK);
                    pluginResult5.setKeepCallback(false);
                    callbackContext.sendPluginResult(pluginResult5);
                } else if (actionId == ActionId.ACTION_REMOVEITEM) {
                    hashMap3.remove(key);
                    writeAndEncryptStorage(keyStore, hashMap3);
                    PluginResult pluginResult6 = new PluginResult(PluginResult.Status.OK);
                    pluginResult6.setKeepCallback(false);
                    callbackContext.sendPluginResult(pluginResult6);
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    private ActionId getActionId(String action) {
        if (action.equals("clear")) {
            return ActionId.ACTION_CLEAR;
        }
        if (action.equals("getItem")) {
            return ActionId.ACTION_GETITEM;
        }
        if (action.equals("setItem")) {
            return ActionId.ACTION_SETITEM;
        }
        if (action.equals("removeItem")) {
            return ActionId.ACTION_REMOVEITEM;
        }
        if (action.equals("clearIfInvalid")) {
            return ActionId.ACTION_CLEARIFINVALID;
        }
        return ActionId.ACTION_NONE;
    }

    @TargetApi(18)
    private KeyStore initKeyStore() throws NoSuchAlgorithmException, SecureLocalStorageException, IOException, KeyStoreException, CertificateException, NoSuchProviderException, InvalidAlgorithmParameterException {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            if (!keyStore.containsAlias(SECURELOCALSTORAGEALIAS)) {
                Calendar start = Calendar.getInstance();
                Calendar end = Calendar.getInstance();
                end.add(1, 3);
                KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(this._cordova.getActivity()).setAlias(SECURELOCALSTORAGEALIAS).setSubject(new X500Principal(String.format("CN=%s, O=%s", "SecureLocalStorage", this._cordova.getActivity().getBaseContext().getPackageName()))).setSerialNumber(BigInteger.ONE).setStartDate(start.getTime()).setEndDate(end.getTime()).build();
                KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                generator.initialize(spec);
                generator.generateKeyPair();
            }
            return keyStore;
        } catch (Exception e) {
            throw new SecureLocalStorageException("Could not initialize keyStore", e);
        }
    }

    private void clear(File file, KeyStore keyStore) throws SecureLocalStorageException, KeyStoreException {
        if (file.exists() && !file.delete()) {
            throw new SecureLocalStorageException("Could not delete storage file");
        }
        try {
            if (keyStore.containsAlias(SECURELOCALSTORAGEALIAS)) {
                keyStore.deleteEntry(SECURELOCALSTORAGEALIAS);
            }
        } catch (Exception e) {
            throw new SecureLocalStorageException(e.getMessage(), e);
        }
    }

    private void checkValidity() throws NoSuchAlgorithmException, SecureLocalStorageException, IOException, KeyStoreException, CertificateException {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            if (keyStore.containsAlias(SECURELOCALSTORAGEALIAS)) {
                Certificate c = keyStore.getCertificate(SECURELOCALSTORAGEALIAS);
                if (c.getType().equals("X.509")) {
                    ((X509Certificate) c).checkValidity();
                }
            }
        } catch (Exception e) {
            throw new SecureLocalStorageException(e.getMessage(), e);
        }
    }

    private SecretKey getSecretKey(KeyStore keyStore) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidKeyException, ClassNotFoundException, KeyStoreException, NoSuchProviderException, UnrecoverableEntryException {
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(SECURELOCALSTORAGEALIAS, null);
        FileInputStream fis = this._cordova.getActivity().openFileInput(SECURELOCALSTORAGEKEY);
        ArrayList<Byte> values = new ArrayList<>();
        try {
            Cipher output = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            output.init(2, privateKeyEntry.getPrivateKey());
            CipherInputStream cipherInputStream = new CipherInputStream(fis, output);
            while (true) {
                try {
                    int nextByte = cipherInputStream.read();
                    if (nextByte == -1) {
                        break;
                    }
                    values.add(Byte.valueOf((byte) nextByte));
                } finally {
                    cipherInputStream.close();
                }
            }
            fis.close();
            byte[] bytes = new byte[values.size()];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = values.get(i).byteValue();
            }
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            try {
                SecretKey key = (SecretKey) ois.readObject();
                return key;
            } finally {
                ois.close();
            }
        } catch (Throwable th) {
            fis.close();
            throw th;
        }
    }

    private void generateKey(KeyStore keyStore) throws NoSuchPaddingException, NoSuchAlgorithmException, SecureLocalStorageException, IOException, InvalidKeyException {
        try {
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                try {
                    oos.writeObject(key);
                    bos.close();
                    KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(SECURELOCALSTORAGEALIAS, null);
                    Cipher input = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                    input.init(1, privateKeyEntry.getCertificate().getPublicKey());
                    FileOutputStream fos = this._cordova.getActivity().openFileOutput(SECURELOCALSTORAGEKEY, 0);
                    try {
                        CipherOutputStream cipherOutputStream = new CipherOutputStream(fos, input);
                        try {
                            cipherOutputStream.write(bos.toByteArray());
                        } finally {
                            cipherOutputStream.close();
                        }
                    } finally {
                        fos.close();
                    }
                } finally {
                    oos.close();
                }
            } catch (Throwable th) {
                bos.close();
                throw th;
            }
        } catch (Exception e) {
            throw new SecureLocalStorageException("Error generating key", e);
        }
    }

    private HashMap<String, String> readAndDecryptStorage(KeyStore keyStore) throws SecureLocalStorageException, IOException {
        try {
            SecretKey key = getSecretKey(keyStore);
            FileInputStream fis = this._cordova.getActivity().openFileInput(SECURELOCALSTORAGEFILE);
            ArrayList<Byte> values = new ArrayList<>();
            try {
                Cipher output = Cipher.getInstance("DES");
                output.init(2, key);
                CipherInputStream cipherInputStream = new CipherInputStream(fis, output);
                while (true) {
                    try {
                        int nextByte = cipherInputStream.read();
                        if (nextByte == -1) {
                            break;
                        }
                        values.add(Byte.valueOf((byte) nextByte));
                    } finally {
                        cipherInputStream.close();
                    }
                }
                fis.close();
                byte[] bytes = new byte[values.size()];
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = values.get(i).byteValue();
                }
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
                try {
                    HashMap<String, String> hashMap = (HashMap) ois.readObject();
                    return hashMap;
                } finally {
                    ois.close();
                }
            } catch (Throwable th) {
                fis.close();
                throw th;
            }
        } catch (Exception e) {
            throw new SecureLocalStorageException("Error decrypting storage", e);
        }
    }

    private void writeAndEncryptStorage(KeyStore keyStore, HashMap<String, String> hashMap) throws NoSuchPaddingException, NoSuchAlgorithmException, SecureLocalStorageException, IOException, InvalidKeyException {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                try {
                    oos.writeObject(hashMap);
                    bos.close();
                    SecretKey key = getSecretKey(keyStore);
                    Cipher input = Cipher.getInstance("DES");
                    input.init(1, key);
                    FileOutputStream fos = this._cordova.getActivity().openFileOutput(SECURELOCALSTORAGEFILE, 0);
                    try {
                        CipherOutputStream cipherOutputStream = new CipherOutputStream(fos, input);
                        try {
                            cipherOutputStream.write(bos.toByteArray());
                        } finally {
                            cipherOutputStream.close();
                        }
                    } finally {
                        fos.close();
                    }
                } finally {
                    oos.close();
                }
            } catch (Throwable th) {
                bos.close();
                throw th;
            }
        } catch (Exception e) {
            throw new SecureLocalStorageException("Error encrypting storage", e);
        }
    }
}
