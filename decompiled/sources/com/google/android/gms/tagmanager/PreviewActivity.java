package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/* loaded from: classes.dex */
public class PreviewActivity extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            zzdj.zzcq("Preview activity");
            Uri data = getIntent().getData();
            if (!TagManager.getInstance(this).zzq(data)) {
                String strValueOf = String.valueOf(data);
                String string = new StringBuilder(String.valueOf(strValueOf).length() + 73).append("Cannot preview the app with the uri: ").append(strValueOf).append(". Launching current version instead.").toString();
                zzdj.zzcr(string);
                AlertDialog alertDialogCreate = new AlertDialog.Builder(this).create();
                alertDialogCreate.setTitle("Preview failure");
                alertDialogCreate.setMessage(string);
                alertDialogCreate.setButton(-1, "Continue", new zzeh(this));
                alertDialogCreate.show();
            }
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
            if (launchIntentForPackage == null) {
                String strValueOf2 = String.valueOf(getPackageName());
                zzdj.zzcq(strValueOf2.length() != 0 ? "No launch activity found for package name: ".concat(strValueOf2) : new String("No launch activity found for package name: "));
            } else {
                String strValueOf3 = String.valueOf(getPackageName());
                zzdj.zzcq(strValueOf3.length() != 0 ? "Invoke the launch activity for package name: ".concat(strValueOf3) : new String("Invoke the launch activity for package name: "));
                startActivity(launchIntentForPackage);
            }
        } catch (Exception e) {
            String strValueOf4 = String.valueOf(e.getMessage());
            zzdj.e(strValueOf4.length() != 0 ? "Calling preview threw an exception: ".concat(strValueOf4) : new String("Calling preview threw an exception: "));
        }
    }
}
