package jp.co.sony.fes.nativebridge;

import com.sony.cdp.plugin.nativebridge.Gate;

/* loaded from: classes.dex */
public class UUID extends Gate {
    public void generate() {
        returnParams(java.util.UUID.randomUUID().toString());
    }
}
