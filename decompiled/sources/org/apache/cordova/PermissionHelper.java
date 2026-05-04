package org.apache.cordova;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/* loaded from: classes.dex */
public class PermissionHelper {
    private static final String LOG_TAG = "CordovaPermissionHelper";

    public static void requestPermission(CordovaPlugin plugin, int requestCode, String permission) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        requestPermissions(plugin, requestCode, new String[]{permission});
    }

    public static void requestPermissions(CordovaPlugin plugin, int requestCode, String[] permissions) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Method requestPermission = CordovaInterface.class.getDeclaredMethod("requestPermissions", CordovaPlugin.class, Integer.TYPE, String[].class);
            requestPermission.invoke(plugin.cordova, plugin, Integer.valueOf(requestCode), permissions);
        } catch (IllegalAccessException illegalAccessException) {
            LOG.e(LOG_TAG, "IllegalAccessException when requesting permissions " + Arrays.toString(permissions), illegalAccessException);
        } catch (NoSuchMethodException e) {
            LOG.d(LOG_TAG, "No need to request permissions " + Arrays.toString(permissions));
            deliverPermissionResult(plugin, requestCode, permissions);
        } catch (InvocationTargetException invocationTargetException) {
            LOG.e(LOG_TAG, "invocationTargetException when requesting permissions " + Arrays.toString(permissions), invocationTargetException);
        }
    }

    public static boolean hasPermission(CordovaPlugin plugin, String permission) throws NoSuchMethodException, SecurityException {
        try {
            Method hasPermission = CordovaInterface.class.getDeclaredMethod("hasPermission", String.class);
            return ((Boolean) hasPermission.invoke(plugin.cordova, permission)).booleanValue();
        } catch (IllegalAccessException illegalAccessException) {
            LOG.e(LOG_TAG, "IllegalAccessException when checking permission " + permission, illegalAccessException);
            return false;
        } catch (NoSuchMethodException e) {
            LOG.d(LOG_TAG, "No need to check for permission " + permission);
            return true;
        } catch (InvocationTargetException invocationTargetException) {
            LOG.e(LOG_TAG, "invocationTargetException when checking permission " + permission, invocationTargetException);
            return false;
        }
    }

    private static void deliverPermissionResult(CordovaPlugin plugin, int requestCode, String[] permissions) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int[] requestResults = new int[permissions.length];
        Arrays.fill(requestResults, 0);
        try {
            Method onRequestPermissionResult = CordovaPlugin.class.getDeclaredMethod("onRequestPermissionResult", Integer.TYPE, String[].class, int[].class);
            onRequestPermissionResult.invoke(plugin, Integer.valueOf(requestCode), permissions, requestResults);
        } catch (IllegalAccessException illegalAccessException) {
            LOG.e(LOG_TAG, "IllegalAccessException when delivering permissions results", illegalAccessException);
        } catch (NoSuchMethodException noSuchMethodException) {
            LOG.e(LOG_TAG, "NoSuchMethodException when delivering permissions results", noSuchMethodException);
        } catch (InvocationTargetException invocationTargetException) {
            LOG.e(LOG_TAG, "InvocationTargetException when delivering permissions results", invocationTargetException);
        }
    }
}
