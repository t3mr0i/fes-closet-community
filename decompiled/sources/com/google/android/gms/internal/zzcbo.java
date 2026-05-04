package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public interface zzcbo extends IInterface {
    List<zzcft> zza(zzcas zzcasVar, boolean z) throws RemoteException;

    List<zzcav> zza(String str, String str2, zzcas zzcasVar) throws RemoteException;

    List<zzcft> zza(String str, String str2, String str3, boolean z) throws RemoteException;

    List<zzcft> zza(String str, String str2, boolean z, zzcas zzcasVar) throws RemoteException;

    void zza(long j, String str, String str2, String str3) throws RemoteException;

    void zza(zzcas zzcasVar) throws RemoteException;

    void zza(zzcav zzcavVar, zzcas zzcasVar) throws RemoteException;

    void zza(zzcbk zzcbkVar, zzcas zzcasVar) throws RemoteException;

    void zza(zzcbk zzcbkVar, String str, String str2) throws RemoteException;

    void zza(zzcft zzcftVar, zzcas zzcasVar) throws RemoteException;

    byte[] zza(zzcbk zzcbkVar, String str) throws RemoteException;

    void zzb(zzcas zzcasVar) throws RemoteException;

    void zzb(zzcav zzcavVar) throws RemoteException;

    String zzc(zzcas zzcasVar) throws RemoteException;

    List<zzcav> zzj(String str, String str2, String str3) throws RemoteException;
}
