package org.apache.cordova.camera;

import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;

/* loaded from: classes.dex */
public class CordovaUri {
    private Uri androidUri;
    private String fileName;
    private Uri fileUri;

    CordovaUri(Uri inputUri) {
        if (inputUri.getScheme().equals(FirebaseAnalytics.Param.CONTENT)) {
            this.androidUri = inputUri;
            this.fileName = getFileNameFromUri(this.androidUri);
            this.fileUri = Uri.parse("file://" + this.fileName);
        } else {
            this.fileUri = inputUri;
            this.fileName = FileHelper.stripFileProtocol(inputUri.toString());
        }
    }

    public Uri getFileUri() {
        return this.fileUri;
    }

    public String getFilePath() {
        return this.fileName;
    }

    public Uri getCorrectUri() {
        return Build.VERSION.SDK_INT >= 23 ? this.androidUri : this.fileUri;
    }

    private String getFileNameFromUri(Uri uri) {
        String fullUri = uri.toString();
        String partial_path = fullUri.split("external_files")[1];
        File external_storage = Environment.getExternalStorageDirectory();
        String path = external_storage.getAbsolutePath() + partial_path;
        return path;
    }
}
