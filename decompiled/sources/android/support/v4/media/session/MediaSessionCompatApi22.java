package android.support.v4.media.session;

import android.media.session.MediaSession;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MotionEventCompat;

@RequiresApi(MotionEventCompat.AXIS_GAS)
/* loaded from: classes.dex */
class MediaSessionCompatApi22 {
    MediaSessionCompatApi22() {
    }

    public static void setRatingType(Object sessionObj, int type) {
        ((MediaSession) sessionObj).setRatingType(type);
    }
}
