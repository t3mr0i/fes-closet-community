package com.danielcwilson.plugins.analytics;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class UniversalAnalyticsPlugin extends CordovaPlugin {
    public static final String ADD_DIMENSION = "addCustomDimension";
    public static final String ADD_TRANSACTION = "addTransaction";
    public static final String ADD_TRANSACTION_ITEM = "addTransactionItem";
    public static final String DEBUG_MODE = "debugMode";
    public static final String ENABLE_UNCAUGHT_EXCEPTION_REPORTING = "enableUncaughtExceptionReporting";
    public static final String SET_ALLOW_IDFA_COLLECTION = "setAllowIDFACollection";
    public static final String SET_ANONYMIZE_IP = "setAnonymizeIp";
    public static final String SET_APP_VERSION = "setAppVersion";
    public static final String SET_OPT_OUT = "setOptOut";
    public static final String SET_USER_ID = "setUserId";
    public static final String START_TRACKER = "startTrackerWithId";
    public static final String TRACK_EVENT = "trackEvent";
    public static final String TRACK_EXCEPTION = "trackException";
    public static final String TRACK_METRIC = "trackMetric";
    public static final String TRACK_TIMING = "trackTiming";
    public static final String TRACK_VIEW = "trackView";
    public Tracker tracker;
    public Boolean trackerStarted = false;
    public Boolean debugModeEnabled = false;
    public HashMap<Integer, String> customDimensions = new HashMap<>();

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (START_TRACKER.equals(action)) {
            String id = args.getString(0);
            startTracker(id, callbackContext);
            return true;
        }
        if (TRACK_VIEW.equals(action)) {
            int length = args.length();
            String screen = args.getString(0);
            trackView(screen, length > 1 ? args.getString(1) : "", length > 2 ? args.getBoolean(2) : false, callbackContext);
            return true;
        }
        if (TRACK_EVENT.equals(action)) {
            int length2 = args.length();
            if (length2 > 0) {
                trackEvent(args.getString(0), length2 > 1 ? args.getString(1) : "", length2 > 2 ? args.getString(2) : "", length2 > 3 ? args.getLong(3) : 0L, length2 > 4 ? args.getBoolean(4) : false, callbackContext);
            }
            return true;
        }
        if (TRACK_EXCEPTION.equals(action)) {
            String description = args.getString(0);
            Boolean fatal = Boolean.valueOf(args.getBoolean(1));
            trackException(description, fatal, callbackContext);
            return true;
        }
        if (TRACK_TIMING.equals(action)) {
            int length3 = args.length();
            if (length3 > 0) {
                trackTiming(args.getString(0), length3 > 1 ? args.getLong(1) : 0L, length3 > 2 ? args.getString(2) : "", length3 > 3 ? args.getString(3) : "", callbackContext);
            }
            return true;
        }
        if (TRACK_METRIC.equals(action)) {
            int length4 = args.length();
            if (length4 > 0) {
                trackMetric(Integer.valueOf(args.getInt(0)), length4 > 1 ? args.getString(1) : "", callbackContext);
            }
            return true;
        }
        if (ADD_DIMENSION.equals(action)) {
            Integer key = Integer.valueOf(args.getInt(0));
            String value = args.getString(1);
            addCustomDimension(key, value, callbackContext);
            return true;
        }
        if (ADD_TRANSACTION.equals(action)) {
            int length5 = args.length();
            if (length5 > 0) {
                addTransaction(args.getString(0), length5 > 1 ? args.getString(1) : "", length5 > 2 ? args.getDouble(2) : 0.0d, length5 > 3 ? args.getDouble(3) : 0.0d, length5 > 4 ? args.getDouble(4) : 0.0d, length5 > 5 ? args.getString(5) : null, callbackContext);
            }
            return true;
        }
        if (ADD_TRANSACTION_ITEM.equals(action)) {
            int length6 = args.length();
            if (length6 > 0) {
                addTransactionItem(args.getString(0), length6 > 1 ? args.getString(1) : "", length6 > 2 ? args.getString(2) : "", length6 > 3 ? args.getString(3) : "", length6 > 4 ? args.getDouble(4) : 0.0d, length6 > 5 ? args.getLong(5) : 0L, length6 > 6 ? args.getString(6) : null, callbackContext);
            }
            return true;
        }
        if (SET_ALLOW_IDFA_COLLECTION.equals(action)) {
            setAllowIDFACollection(Boolean.valueOf(args.getBoolean(0)), callbackContext);
        } else if (SET_USER_ID.equals(action)) {
            String userId = args.getString(0);
            setUserId(userId, callbackContext);
        } else if (SET_ANONYMIZE_IP.equals(action)) {
            boolean anonymize = args.getBoolean(0);
            setAnonymizeIp(anonymize, callbackContext);
        } else if (SET_OPT_OUT.equals(action)) {
            boolean optout = args.getBoolean(0);
            setOptOut(optout, callbackContext);
        } else if (SET_APP_VERSION.equals(action)) {
            String version = args.getString(0);
            setAppVersion(version, callbackContext);
        } else if (DEBUG_MODE.equals(action)) {
            debugMode(callbackContext);
        } else if (ENABLE_UNCAUGHT_EXCEPTION_REPORTING.equals(action)) {
            Boolean enable = Boolean.valueOf(args.getBoolean(0));
            enableUncaughtExceptionReporting(enable, callbackContext);
        }
        return false;
    }

    private void startTracker(String id, CallbackContext callbackContext) {
        if (id != null && id.length() > 0) {
            this.tracker = GoogleAnalytics.getInstance(this.cordova.getActivity()).newTracker(id);
            callbackContext.success("tracker started");
            this.trackerStarted = true;
            GoogleAnalytics.getInstance(this.cordova.getActivity()).setLocalDispatchPeriod(30);
            return;
        }
        callbackContext.error("tracker id is not valid");
    }

    private void addCustomDimension(Integer key, String value, CallbackContext callbackContext) {
        if (key.intValue() <= 0) {
            callbackContext.error("Expected positive integer argument for key.");
        } else if (value == null || value.length() == 0) {
            callbackContext.error("Expected non-empty string argument for value.");
        } else {
            this.customDimensions.put(key, value);
            callbackContext.success("custom dimension started");
        }
    }

    private <T> void addCustomDimensionsToHitBuilder(T builder) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Method builderMethod = builder.getClass().getMethod("setCustomDimension", Integer.TYPE, String.class);
            for (Map.Entry<Integer, String> entry : this.customDimensions.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue();
                try {
                    builderMethod.invoke(builder, key, value);
                } catch (IllegalAccessException e) {
                } catch (IllegalArgumentException e2) {
                } catch (InvocationTargetException e3) {
                }
            }
        } catch (NoSuchMethodException e4) {
        } catch (SecurityException e5) {
        }
    }

    private void trackView(String screenname, String campaignUrl, boolean newSession, CallbackContext callbackContext) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!this.trackerStarted.booleanValue()) {
            callbackContext.error("Tracker not started");
            return;
        }
        if (screenname != null && screenname.length() > 0) {
            this.tracker.setScreenName(screenname);
            HitBuilders.ScreenViewBuilder hitBuilder = new HitBuilders.ScreenViewBuilder();
            addCustomDimensionsToHitBuilder(hitBuilder);
            if (!campaignUrl.equals("")) {
                hitBuilder.setCampaignParamsFromUrl(campaignUrl);
            }
            if (!newSession) {
                this.tracker.send(hitBuilder.build());
            } else {
                this.tracker.send(hitBuilder.setNewSession().build());
            }
            callbackContext.success("Track Screen: " + screenname);
            return;
        }
        callbackContext.error("Expected one non-empty string argument.");
    }

    private void trackEvent(String category, String action, String label, long value, boolean newSession, CallbackContext callbackContext) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!this.trackerStarted.booleanValue()) {
            callbackContext.error("Tracker not started");
            return;
        }
        if (category != null && category.length() > 0) {
            HitBuilders.EventBuilder hitBuilder = new HitBuilders.EventBuilder();
            addCustomDimensionsToHitBuilder(hitBuilder);
            if (!newSession) {
                this.tracker.send(hitBuilder.setCategory(category).setAction(action).setLabel(label).setValue(value).build());
            } else {
                this.tracker.send(hitBuilder.setCategory(category).setAction(action).setLabel(label).setValue(value).setNewSession().build());
            }
            callbackContext.success("Track Event: " + category);
            return;
        }
        callbackContext.error("Expected non-empty string arguments.");
    }

    private void trackMetric(Integer key, String value, CallbackContext callbackContext) {
        if (!this.trackerStarted.booleanValue()) {
            callbackContext.error("Tracker not started");
        } else {
            if (key.intValue() >= 0) {
                HitBuilders.ScreenViewBuilder hitBuilder = new HitBuilders.ScreenViewBuilder();
                this.tracker.send(hitBuilder.setCustomMetric(key.intValue(), Float.parseFloat(value)).build());
                callbackContext.success("Track Metric: " + key + ", value: " + value);
                return;
            }
            callbackContext.error("Expected integer key: " + key + ", and string value: " + value);
        }
    }

    private void trackException(String description, Boolean fatal, CallbackContext callbackContext) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!this.trackerStarted.booleanValue()) {
            callbackContext.error("Tracker not started");
            return;
        }
        if (description != null && description.length() > 0) {
            HitBuilders.ExceptionBuilder hitBuilder = new HitBuilders.ExceptionBuilder();
            addCustomDimensionsToHitBuilder(hitBuilder);
            this.tracker.send(hitBuilder.setDescription(description).setFatal(fatal.booleanValue()).build());
            callbackContext.success("Track Exception: " + description);
            return;
        }
        callbackContext.error("Expected non-empty string arguments.");
    }

    private void trackTiming(String category, long intervalInMilliseconds, String name, String label, CallbackContext callbackContext) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!this.trackerStarted.booleanValue()) {
            callbackContext.error("Tracker not started");
            return;
        }
        if (category != null && category.length() > 0) {
            HitBuilders.TimingBuilder hitBuilder = new HitBuilders.TimingBuilder();
            addCustomDimensionsToHitBuilder(hitBuilder);
            this.tracker.send(hitBuilder.setCategory(category).setValue(intervalInMilliseconds).setVariable(name).setLabel(label).build());
            callbackContext.success("Track Timing: " + category);
            return;
        }
        callbackContext.error("Expected non-empty string arguments.");
    }

    private void addTransaction(String id, String affiliation, double revenue, double tax, double shipping, String currencyCode, CallbackContext callbackContext) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!this.trackerStarted.booleanValue()) {
            callbackContext.error("Tracker not started");
            return;
        }
        if (id != null && id.length() > 0) {
            HitBuilders.TransactionBuilder hitBuilder = new HitBuilders.TransactionBuilder();
            addCustomDimensionsToHitBuilder(hitBuilder);
            this.tracker.send(hitBuilder.setTransactionId(id).setAffiliation(affiliation).setRevenue(revenue).setTax(tax).setShipping(shipping).setCurrencyCode(currencyCode).build());
            callbackContext.success("Add Transaction: " + id);
            return;
        }
        callbackContext.error("Expected non-empty ID.");
    }

    private void addTransactionItem(String id, String name, String sku, String category, double price, long quantity, String currencyCode, CallbackContext callbackContext) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!this.trackerStarted.booleanValue()) {
            callbackContext.error("Tracker not started");
            return;
        }
        if (id != null && id.length() > 0) {
            HitBuilders.ItemBuilder hitBuilder = new HitBuilders.ItemBuilder();
            addCustomDimensionsToHitBuilder(hitBuilder);
            this.tracker.send(hitBuilder.setTransactionId(id).setName(name).setSku(sku).setCategory(category).setPrice(price).setQuantity(quantity).setCurrencyCode(currencyCode).build());
            callbackContext.success("Add Transaction Item: " + id);
            return;
        }
        callbackContext.error("Expected non-empty ID.");
    }

    private void setAllowIDFACollection(Boolean enable, CallbackContext callbackContext) {
        if (!this.trackerStarted.booleanValue()) {
            callbackContext.error("Tracker not started");
        } else {
            this.tracker.enableAdvertisingIdCollection(enable.booleanValue());
            callbackContext.success("Enable Advertising Id Collection: " + enable);
        }
    }

    private void debugMode(CallbackContext callbackContext) {
        GoogleAnalytics.getInstance(this.cordova.getActivity()).getLogger().setLogLevel(0);
        this.debugModeEnabled = true;
        callbackContext.success("debugMode enabled");
    }

    private void setAnonymizeIp(boolean anonymize, CallbackContext callbackContext) {
        if (!this.trackerStarted.booleanValue()) {
            callbackContext.error("Tracker not started");
        } else {
            this.tracker.setAnonymizeIp(anonymize);
            callbackContext.success("Set AnonymizeIp " + anonymize);
        }
    }

    private void setOptOut(boolean optout, CallbackContext callbackContext) {
        if (!this.trackerStarted.booleanValue()) {
            callbackContext.error("Tracker not started");
        } else {
            GoogleAnalytics.getInstance(this.cordova.getActivity()).setAppOptOut(optout);
            callbackContext.success("Set Opt-Out " + optout);
        }
    }

    private void setUserId(String userId, CallbackContext callbackContext) {
        if (!this.trackerStarted.booleanValue()) {
            callbackContext.error("Tracker not started");
        } else {
            this.tracker.set("&uid", userId);
            callbackContext.success("Set user id" + userId);
        }
    }

    private void setAppVersion(String version, CallbackContext callbackContext) {
        if (!this.trackerStarted.booleanValue()) {
            callbackContext.error("Tracker not started");
        } else {
            this.tracker.set("&av", version);
            callbackContext.success("Set app version: " + version);
        }
    }

    private void enableUncaughtExceptionReporting(Boolean enable, CallbackContext callbackContext) {
        if (!this.trackerStarted.booleanValue()) {
            callbackContext.error("Tracker not started");
        } else {
            this.tracker.enableExceptionReporting(enable.booleanValue());
            callbackContext.success((enable.booleanValue() ? "Enabled" : "Disabled") + " uncaught exception reporting");
        }
    }
}
