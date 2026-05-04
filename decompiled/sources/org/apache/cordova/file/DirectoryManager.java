package org.apache.cordova.file;

import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import java.io.File;

/* loaded from: classes.dex */
public class DirectoryManager {
    private static final String LOG_TAG = "DirectoryManager";

    public static boolean testFileExists(String name) {
        if (testSaveLocationExists() && !name.equals("")) {
            File path = Environment.getExternalStorageDirectory();
            File newPath = constructFilePaths(path.toString(), name);
            boolean status = newPath.exists();
            return status;
        }
        return false;
    }

    public static long getFreeExternalStorageSpace() {
        String status = Environment.getExternalStorageState();
        if (status.equals("mounted")) {
            long freeSpaceInBytes = getFreeSpaceInBytes(Environment.getExternalStorageDirectory().getPath());
            return freeSpaceInBytes / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return -1L;
    }

    public static long getFreeSpaceInBytes(String path) {
        try {
            StatFs stat = new StatFs(path);
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } catch (IllegalArgumentException e) {
            return 0L;
        }
    }

    public static boolean testSaveLocationExists() {
        String sDCardStatus = Environment.getExternalStorageState();
        if (sDCardStatus.equals("mounted")) {
            return true;
        }
        return false;
    }

    private static File constructFilePaths(String file1, String file2) {
        if (file2.startsWith(file1)) {
            File newPath = new File(file2);
            return newPath;
        }
        File newPath2 = new File(file1 + "/" + file2);
        return newPath2;
    }
}
