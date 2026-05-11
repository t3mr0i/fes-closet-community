// Ensure cordova.plugins.SecureLocalStorage fallback before deviceready
window.cordova = window.cordova || {};
window.cordova.plugins = window.cordova.plugins || {};
if (!window.cordova.plugins.SecureLocalStorage) {
    window.cordova.plugins.SecureLocalStorage = {
        setItem: function(k,v){ return Promise.resolve(localStorage.setItem(k, typeof v==='string'?v:JSON.stringify(v))); },
        getItem: function(k){ return Promise.resolve(localStorage.getItem(k)); },
        removeItem: function(k){ return Promise.resolve(localStorage.removeItem(k)); },
        clear: function(){ return Promise.resolve(localStorage.clear()); }
    };
}

// Stub for cordova-plugin-google-analytics — must call success callbacks or promises hang
window.ga = {
    startTrackerWithId: function(id, success) { if (success) success(); },
    trackView: function(s, success) { if (success) success(); },
    trackEvent: function(c, a, l, v, success) { if (success) success(); },
    setAnonymizeIp: function(b, success) { if (success) success(); },
    debugMode: function(success) { if (success) success(); },
    setUserId: function(id, success) { if (success) success(); },
    addCustomDimension: function(i, v, success) { if (success) success(); }
};
