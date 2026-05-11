package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
/* loaded from: classes.dex */
final class zzefx<FieldDescriptorType> extends zzefw<FieldDescriptorType, Object> {
    zzefx(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.zzefw
    public final void zzbht() {
        if (!isImmutable()) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= zzcdi()) {
                    break;
                }
                Map.Entry<FieldDescriptorType, Object> entryZzgw = zzgw(i2);
                if (((zzeeu) entryZzgw.getKey()).zzccu()) {
                    entryZzgw.setValue(Collections.unmodifiableList((List) entryZzgw.getValue()));
                }
                i = i2 + 1;
            }
            for (Map.Entry<FieldDescriptorType, Object> entry : zzcdj()) {
                if (((zzeeu) entry.getKey()).zzccu()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzbht();
    }
}
