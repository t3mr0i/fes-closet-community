package jp.co.sony.fes.services;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FESTokenService extends FirebaseInstanceIdService {
    private static final String TAG = "FESTokenService";

    @Override // com.google.firebase.iid.FirebaseInstanceIdService
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }

    public static void sendRegistrationToServer(String token, JSONObject options) throws JSONException, IOException {
        try {
            JSONObject registration = new JSONObject();
            String timezone = TimeZone.getDefault().getID();
            String utcOffset = String.valueOf(TimeZone.getDefault().getRawOffset());
            registration.putOpt("deviceToken", token).putOpt("appName", options.optString("snsName")).putOpt("locale", options.optString("locale")).putOpt("timezone", timezone).putOpt("utcOffset", utcOffset).putOpt("clientName", options.optString("clientName")).putOpt("clientVersion", options.optString("clientVersion")).putOpt("platform", options.optString("platform"));
            Log.d(TAG, "Sending Device Registration");
            URL url = new URL(options.optString("apiEndpoint"));
            String strData = registration.toString();
            Log.d(TAG, "Registration Data: " + strData);
            Log.d(TAG, "Registration URL: " + url.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Length", Integer.toString(strData.getBytes().length));
            conn.setDoOutput(true);
            conn.setDoInput(true);
            PrintWriter out = new PrintWriter(new BufferedOutputStream(conn.getOutputStream()));
            out.print(strData);
            out.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while (true) {
                String line = reader.readLine();
                if (line != null) {
                    Log.d("res", line);
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
