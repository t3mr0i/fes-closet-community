// Debug console overlay
(function () {
    var el = document.createElement('div');
    el.id = 'debug-console';
    el.style.cssText = 'position:fixed;top:0;left:0;right:0;max-height:40%;overflow-y:auto;background:rgba(0,0,0,0.85);color:#0f0;font:10px monospace;padding:4px;z-index:99999;';
    document.body.appendChild(el);
    function log(type, args) {
        var line = document.createElement('div');
        line.style.color = type === 'error' ? '#f66' : type === 'warn' ? '#fa0' : '#0f0';
        line.textContent = '[' + type + '] ' + Array.prototype.slice.call(args).join(' ');
        el.insertBefore(line, el.firstChild);
    }
    ['log', 'warn', 'error', 'info'].forEach(function (t) {
        var orig = console[t].bind(console);
        console[t] = function () { orig.apply(console, arguments); log(t, arguments); };
    });
    window.addEventListener('error', function (e) {
        if (e.message && (e.message.indexOf('cordova already defined') !== -1 || e.message.indexOf('nativeEvalAndFetch') !== -1)) return;
        var src = (e.filename || '').replace(/.*\/([^\/]+\.js).*/, '$1') + ':' + e.lineno;
        log('error', [e.message, src]);
    });
})();

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

// Stub for cordova-plugin-google-analytics
window.ga = {
    startTrackerWithId: function() {},
    trackView: function() {},
    trackEvent: function() {},
    setAnonymizeIp: function() {},
    debugMode: function() {},
    setUserId: function() {},
    addCustomDimension: function() {}
};
