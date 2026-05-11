package com.google.android.gms.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
final class zzaoq {
    private int zzdtm;
    private ByteArrayOutputStream zzdtn = new ByteArrayOutputStream();
    private /* synthetic */ zzaop zzdto;

    public zzaoq(zzaop zzaopVar) {
        this.zzdto = zzaopVar;
    }

    public final byte[] getPayload() {
        return this.zzdtn.toByteArray();
    }

    public final boolean zze(zzaoi zzaoiVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzaoiVar);
        if (this.zzdtm + 1 > zzanv.zzya()) {
            return false;
        }
        String strZza = this.zzdto.zza(zzaoiVar, false);
        if (strZza == null) {
            this.zzdto.zzvy().zza(zzaoiVar, "Error formatting hit");
            return true;
        }
        byte[] bytes = strZza.getBytes();
        int length = bytes.length;
        if (length > zzanv.zzxw()) {
            this.zzdto.zzvy().zza(zzaoiVar, "Hit size exceeds the maximum size limit");
            return true;
        }
        if (this.zzdtn.size() > 0) {
            length++;
        }
        if (this.zzdtn.size() + length > zzaod.zzdry.get().intValue()) {
            return false;
        }
        try {
            if (this.zzdtn.size() > 0) {
                this.zzdtn.write(zzaop.zzdtl);
            }
            this.zzdtn.write(bytes);
            this.zzdtm++;
            return true;
        } catch (IOException e) {
            this.zzdto.zze("Failed to write payload when batching hits", e);
            return true;
        }
    }

    public final int zzza() {
        return this.zzdtm;
    }
}
