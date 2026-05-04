package jp.co.sony.fes.nativebridge;

import com.sony.cdp.plugin.nativebridge.Gate;
import com.sony.cdp.plugin.nativebridge.MethodContext;
import jp.co.sony.fes.MainActivity;

/* loaded from: classes.dex */
public class Misc extends Gate implements MainActivity.OnFocusChangeListener {
    private MethodContext mChangeFocusContext = null;

    public void changeStatusBarColor(double color) {
    }

    public void listenToChangeFocus() {
        if (this.mChangeFocusContext == null) {
            this.mChangeFocusContext = getContext();
            ((MainActivity) this.cordova.getActivity()).setFocusChangeListener(this);
        }
    }

    @Override // jp.co.sony.fes.MainActivity.OnFocusChangeListener
    public void onFocusChanged(boolean hasFocus) {
        if (this.mChangeFocusContext != null) {
            notifyParams(this.mChangeFocusContext, Boolean.valueOf(hasFocus));
        }
    }
}
