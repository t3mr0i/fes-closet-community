package com.google.firebase.messaging;

/* loaded from: classes.dex */
public final class SendException extends Exception {
    public static final int ERROR_INVALID_PARAMETERS = 1;
    public static final int ERROR_SIZE = 2;
    public static final int ERROR_TOO_MANY_MESSAGES = 4;
    public static final int ERROR_TTL_EXCEEDED = 3;
    public static final int ERROR_UNKNOWN = 0;
    private final int mErrorCode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    SendException(java.lang.String r9) {
        /*
            r8 = this;
            r3 = 4
            r2 = 3
            r1 = 2
            r0 = 1
            r4 = 0
            r8.<init>(r9)
            if (r9 == 0) goto L1b
            java.util.Locale r5 = java.util.Locale.US
            java.lang.String r6 = r9.toLowerCase(r5)
            r5 = -1
            int r7 = r6.hashCode()
            switch(r7) {
                case -1743242157: goto L3d;
                case -1290953729: goto L47;
                case -920906446: goto L1f;
                case -617027085: goto L33;
                case -95047692: goto L29;
                default: goto L18;
            }
        L18:
            switch(r5) {
                case 0: goto L1c;
                case 1: goto L1c;
                case 2: goto L51;
                case 3: goto L53;
                case 4: goto L55;
                default: goto L1b;
            }
        L1b:
            r0 = r4
        L1c:
            r8.mErrorCode = r0
            return
        L1f:
            java.lang.String r7 = "invalid_parameters"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L18
            r5 = r4
            goto L18
        L29:
            java.lang.String r7 = "missing_to"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L18
            r5 = r0
            goto L18
        L33:
            java.lang.String r7 = "messagetoobig"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L18
            r5 = r1
            goto L18
        L3d:
            java.lang.String r7 = "service_not_available"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L18
            r5 = r2
            goto L18
        L47:
            java.lang.String r7 = "toomanymessages"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L18
            r5 = r3
            goto L18
        L51:
            r0 = r1
            goto L1c
        L53:
            r0 = r2
            goto L1c
        L55:
            r0 = r3
            goto L1c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.SendException.<init>(java.lang.String):void");
    }

    public final int getErrorCode() {
        return this.mErrorCode;
    }
}
