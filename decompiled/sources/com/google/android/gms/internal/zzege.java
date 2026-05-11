package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;

/* loaded from: classes.dex */
final class zzege {
    static String zzac(zzeec zzeecVar) {
        zzegf zzegfVar = new zzegf(zzeecVar);
        StringBuilder sb = new StringBuilder(zzegfVar.size());
        for (int i = 0; i < zzegfVar.size(); i++) {
            byte bZzgk = zzegfVar.zzgk(i);
            switch (bZzgk) {
                case 7:
                    sb.append("\\a");
                    break;
                case 8:
                    sb.append("\\b");
                    break;
                case 9:
                    sb.append("\\t");
                    break;
                case 10:
                    sb.append("\\n");
                    break;
                case 11:
                    sb.append("\\v");
                    break;
                case 12:
                    sb.append("\\f");
                    break;
                case 13:
                    sb.append("\\r");
                    break;
                case MotionEventCompat.AXIS_GENERIC_3 /* 34 */:
                    sb.append("\\\"");
                    break;
                case MotionEventCompat.AXIS_GENERIC_8 /* 39 */:
                    sb.append("\\'");
                    break;
                case 92:
                    sb.append("\\\\");
                    break;
                default:
                    if (bZzgk < 32 || bZzgk > 126) {
                        sb.append('\\');
                        sb.append((char) (((bZzgk >>> 6) & 3) + 48));
                        sb.append((char) (((bZzgk >>> 3) & 7) + 48));
                        sb.append((char) ((bZzgk & 7) + 48));
                        break;
                    } else {
                        sb.append((char) bZzgk);
                        break;
                    }
                    break;
            }
        }
        return sb.toString();
    }
}
