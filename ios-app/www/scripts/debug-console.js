// Stub for cordova-plugin-google-analytics — app calls ga.* at startup
window.ga = {
    startTrackerWithId: function() {},
    trackView: function() {},
    trackEvent: function() {},
    setAnonymizeIp: function() {},
    debugMode: function() {},
    setUserId: function() {},
    addCustomDimension: function() {}
};

(function () {
    var el = document.createElement('div');
    el.id = 'debug-console';
    el.style.cssText = 'position:fixed;bottom:0;left:0;right:0;max-height:40%;overflow-y:auto;background:rgba(0,0,0,0.85);color:#0f0;font:11px monospace;padding:4px;z-index:99999;';
    document.body.appendChild(el);

    function log(type, args) {
        var line = document.createElement('div');
        line.style.color = type === 'error' ? '#f66' : type === 'warn' ? '#fa0' : '#0f0';
        line.textContent = '[' + type + '] ' + Array.prototype.slice.call(args).join(' ');
        el.appendChild(line);
        el.scrollTop = el.scrollHeight;
    }

    ['log', 'warn', 'error', 'info'].forEach(function (t) {
        var orig = console[t].bind(console);
        console[t] = function () { orig.apply(console, arguments); log(t, arguments); };
    });

    window.addEventListener('error', function (e) {
        log('error', [e.message, e.filename + ':' + e.lineno]);
    });

    window.addEventListener('unhandledrejection', function (e) {
        log('error', ['UnhandledPromise:', e.reason]);
    });
})();
