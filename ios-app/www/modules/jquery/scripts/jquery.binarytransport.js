/**
 *
 * jquery.binarytransport.js
 *
 * @description. jQuery ajax transport for making binary data type requests.
 * @version 1.0
 * @author Henry Algus <henryalgus@gmail.com>
 *
 * [CDP modified]: 2017/03/15 Support Network error handling
 * [CDP modified]: 2016/01/20 Support file protocol. (as same as official way)
 */
(function (n) { "use strict"; var t = { 0: 200, 1223: 204 }; n.ajaxTransport("+binary", function (n) { if (window.FormData && (n.dataType && n.dataType == "binary" || n.data && (window.ArrayBuffer && n.data instanceof ArrayBuffer || window.Blob && n.data instanceof Blob))) return { send: function (i, r) { var u = new XMLHttpRequest, e = n.url, o = n.type, s = n.async || !0, h = n.responseType || "blob", c = n.data || null, l = n.username || null, a = n.password || null, f; u.addEventListener("load", function () { var i = {}; i[n.dataType] = u.response; r(t[u.status] || u.status, u.statusText, i, u.getAllResponseHeaders()) }); u.addEventListener("error", function () { var t = {}; t[n.dataType] = u.response; r(u.status, u.statusText, t, u.getAllResponseHeaders()) }); u.open(o, e, s, l, a); for (f in i) u.setRequestHeader(f, i[f]); u.responseType = h; u.send(c) }, abort: function () { } } }) })(window.jQuery);
