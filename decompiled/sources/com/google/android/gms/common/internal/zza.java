package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;

/* loaded from: classes.dex */
public final class zza extends zzan {
    public static Account zza(zzam zzamVar) {
        Account account = null;
        if (zzamVar != null) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                account = zzamVar.getAccount();
            } catch (RemoteException e) {
                Log.w("AccountAccessor", "Remote account accessor probably died");
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
        return account;
    }

    public final boolean equals(Object obj) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.common.internal.zzam
    public final Account getAccount() {
        throw new NoSuchMethodError();
    }
}
