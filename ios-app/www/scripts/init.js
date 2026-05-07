function handleOpenURL(a) {}
var FES;
!function (a) {
    var b = function (cb) {
        var win = window;
        if (null != win.orientation) {
            var deps = win.Config.DEBUG ? ["cordova", "cdp.lazyload"] : ["cordova"];
            require(deps, function () { cb(); });
        } else {
            require(["cdp.lazyload"], function () { cb(); });
        }
    };
    b(function () {
        require(["cdp.framework.jqm"], function () {
            // Install SecureLocalStorage fallback before framework init runs Analytics
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
            CDP.Framework.initialize().done(function () {
                require(["app"], function (a) {
                    try {
                        console.log("[init] calling app.main, type:", typeof a.main);
                        a.main();
                    } catch(e) {
                        console.error("[init] app.main() threw:", e && e.message, e && e.stack);
                    }
                });
            }).fail(function (err) {
                console.error("[init] CDP.Framework.initialize() FAILED:", JSON.stringify(err));
            });
        });
    });
}(FES || (FES = {}));
