/*!
 * FES-SA
 * 2020-03-16
 */
!(function (a, b) {
  "function" == typeof define && define.amd
    ? define(["cdp.nativebridge", "cdp.tools"], function () {
        return b(a.FES || (a.FES = {}));
      })
    : b(a.FES || (a.FES = {}));
})(this, function (a) {
  a.Utils = a.Utils || {};
  var a,
    b =
      (this && this.__extends) ||
      function (a, b) {
        function c() {
          this.constructor = a;
        }
        for (var d in b) b.hasOwnProperty(d) && (a[d] = b[d]);
        a.prototype =
          null === b
            ? Object.create(b)
            : ((c.prototype = b.prototype), new c());
      };
  !(function (a) {
    var c;
    !(function (c) {
      function d() {
        return g.Mobile
          ? (e || (e = new j()), e.genarate())
          : j.genarateFallback();
      }
      var e,
        f = CDP.makePromise,
        g = CDP.Framework.Platform,
        h = CDP.NativeBridge.Gate,
        i = "[FES.NativeBridge.UUID] ",
        j = (function (c) {
          function d() {
            c.call(this, {
              name: "UUID",
              android: { packageInfo: "jp.co.sony.fes.nativebridge.UUID" },
              ios: { packageInfo: "FESUUID" },
            });
          }
          return (
            b(d, c),
            (d.prototype.genarate = function () {
              var b = $.Deferred(),
                d = f(b);
              return (
                d
                  .dependOn(c.prototype.exec.call(this, "generate", arguments))
                  .done(function (a) {
                    b.resolve(a.params[0].split("-").join("").toLowerCase());
                  })
                  .fail(function (c) {
                    b.reject(
                      a.makeErrorInfo(
                        a.RESULT_CODE.ERROR_UTILS_NATIVE_BRIDGE_OPERATION,
                        i,
                        "UUID.generate(), failed",
                        c,
                      ),
                    );
                  }),
                d
              );
            }),
            (d.genarateFallback = function () {
              var a = $.Deferred(),
                b = f(a);
              return (
                setTimeout(function () {
                  a.resolve(d.createUUID().split("-").join("").toLowerCase());
                }),
                b
              );
            }),
            (d.createUUID = function () {
              console.warn(i + "createUUID() is not based upon RFC 4122.");
              var a = function (a) {
                for (var b = "", c = 0; c < a; c++) {
                  var d = parseInt(
                    (256 * Math.random()).toString(),
                    10,
                  ).toString(16);
                  1 === d.length && (d = "0" + d), (b += d);
                }
                return b;
              };
              return a(4) + "-" + a(2) + "-" + a(2) + "-" + a(2) + "-" + a(6);
            }),
            d
          );
        })(h);
      c.generateUUID = d;
    })((c = a.NativeBridge || (a.NativeBridge = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    function b(a) {
      var b = a;
      return !(!b || (n.SUCCEEDED !== b.code && "abort" !== b.message));
    }
    function c(a, b, c) {
      if (b <= 0 || l <= b)
        return void console.error(
          j +
            "declareResultCode(), invalid localCode range. [localCode: " +
            b +
            "]",
        );
      var d = a + b;
      return (m[d] = c ? c : "[RESULT_CODE: " + d + "]"), d;
    }
    function d(a) {
      return m[a] ? m[a] : "unregistered result code. [RESULT_CODE: " + a + "]";
    }
    function e(a, b) {
      var c = b || "[FES] ";
      return n[a] ? c + n[a] + ": " : c;
    }
    function f(a, b, c, f) {
      var g = !(!f || k !== f.message),
        h = g ? k : c,
        i = g ? n.SUCCEEDED : a;
      return $.extend(new Error(h || d(i)), {
        name: e(i, b),
        code: i,
        cause: f,
      });
    }
    function g(a, b, c, d) {
      var e;
      return b && (e = { name: b.name, message: b.name }), f(a, c, d, e);
    }
    function h(a, b) {
      return c(o.UTILS, a, b);
    }
    function i(a, b) {
      var c;
      if ("abort" === b) return f(n.SUCCEEDED, j, "ajax request aborted.");
      switch (a.status) {
        case 0:
          c = f(n.ERROR_UTILS_STORE_SERVER_NOT_CONNECTED, j, b);
          break;
        case 403:
          c = f(n.ERROR_UTILS_STORE_SERVER_PERMISSION_DENIED, j, b);
          break;
        case 404:
          c = f(n.ERROR_UTILS_STORE_SERVER_DATA_NOT_FOUND, j, b);
          break;
        case 422:
          c = f(n.ERROR_UTILS_STORE_SERVER_UNPROCESSABLE_ENTITY, j, b);
          break;
        case 429:
          c = f(n.ERROR_UTILS_STORE_SERVER_TOO_MANY_REQUESTS, j, b);
          break;
        case 500:
          c = f(
            n.ERROR_UTILS_STORE_SERVER_INTERNAL_SERVER_ERROR,
            j,
            a.response ? a.response.msg : null,
            { name: "internal server error", message: b },
          );
          break;
        case 501:
          c = f(n.ERROR_UTILS_STORE_SERVER_SERVICE_GONE, j, b);
          break;
        case 503:
          c = f(n.ERROR_UTILS_STORE_SERVER_SERVICE_UNAVAILABLE, j, b);
          break;
        default:
          c = f(
            n.ERROR_UTILS_STORE_SERVER_NETWORK_OTHER,
            j,
            "unknown server error. " + b + " [status: " + a.status + "]",
          );
      }
      return c;
    }
    var j = "[FES.Utils.ErrorDefs] ",
      k = "abort",
      l = 1e3,
      m = { 0: "operation succeeded.", "-1": "operation failed." };
    !(function (a) {
      (a[(a.SUCCEEDED = 0)] = "SUCCEEDED"), (a[(a.FAILED = -1)] = "FAILED");
    })(a.RESULT_CODE || (a.RESULT_CODE = {}));
    var n = a.RESULT_CODE;
    !(function (a) {
      (a[(a.PROTOCOL = 0)] = "PROTOCOL"),
        (a[(a.UTILS = 1 * l)] = "UTILS"),
        (a[(a.PLUGIN = 2 * l)] = "PLUGIN"),
        (a[(a.PURCHASE = 3 * l)] = "PURCHASE"),
        (a[(a.NOTIFICATION = 4 * l)] = "NOTIFICATION"),
        (a[(a.APPLICATION = 5 * l)] = "APPLICATION");
    })(a.RESULT_CODE_BASE || (a.RESULT_CODE_BASE = {}));
    var o = a.RESULT_CODE_BASE;
    (a.isCanceledError = b),
      (a.declareResultCode = c),
      (a.makeErrorInfo = f),
      (a.makeErrorInfoFromDOMError = g);
    var p,
      q = 10;
    !(function (a) {
      (a[(a.STORAGE_ACCESS = 0)] = "STORAGE_ACCESS"),
        (a[(a.DEVICE_STORAGE = 1 * q)] = "DEVICE_STORAGE"),
        (a[(a.SECURE_STORAGE = 2 * q)] = "SECURE_STORAGE"),
        (a[(a.WEB_STORAGE = 3 * q)] = "WEB_STORAGE"),
        (a[(a.BINARY = 4 * q)] = "BINARY"),
        (a[(a.NATIVE_BRIDGE = 5 * q)] = "NATIVE_BRIDGE"),
        (a[(a.STORE_SERVER = 6 * q)] = "STORE_SERVER"),
        (a[(a.ANALYTICS = 7 * q)] = "ANALYTICS");
    })(p || (p = {})),
      (function (a) {
        (a[(a.ERROR_UTILS_DECLARATION = 0)] = "ERROR_UTILS_DECLARATION"),
          (a[
            (a.ERROR_UTILS_STORAGE_UNEXPECTED = h(
              p.STORAGE_ACCESS + 1,
              "unexpected error occured.",
            ))
          ] = "ERROR_UTILS_STORAGE_UNEXPECTED"),
          (a[
            (a.ERROR_UTILS_STORAGE_INVALID_JSON = h(
              p.STORAGE_ACCESS + 2,
              "json is invalid.",
            ))
          ] = "ERROR_UTILS_STORAGE_INVALID_JSON"),
          (a[
            (a.ERROR_UTILS_STORAGE_INVALID_BASE64 = h(
              p.STORAGE_ACCESS + 3,
              "base64 is invalid.",
            ))
          ] = "ERROR_UTILS_STORAGE_INVALID_BASE64"),
          (a[
            (a.ERROR_UTILS_STORAGE_DATA_NOT_SUPPORTED = h(
              p.STORAGE_ACCESS + 4,
              "data is unspported.",
            ))
          ] = "ERROR_UTILS_STORAGE_DATA_NOT_SUPPORTED"),
          (a[
            (a.ERROR_UTILS_STORAGE_INVALID_PARAM = h(
              p.STORAGE_ACCESS + 5,
              "invalid parameter.",
            ))
          ] = "ERROR_UTILS_STORAGE_INVALID_PARAM"),
          (a[
            (a.ERROR_UTILS_STORAGE_DEVICE_FILE_OPERATION = h(
              p.DEVICE_STORAGE + 1,
              "Device Storage file operation error.",
            ))
          ] = "ERROR_UTILS_STORAGE_DEVICE_FILE_OPERATION"),
          (a[
            (a.ERROR_UTILS_STORAGE_DEVICE_FILE_SECURITY_ERR = h(
              p.DEVICE_STORAGE + 2,
              "Device Storage file sequrity error.",
            ))
          ] = "ERROR_UTILS_STORAGE_DEVICE_FILE_SECURITY_ERR"),
          (a[
            (a.ERROR_UTILS_STORAGE_SECURESTORAGE_OPERATION = h(
              p.SECURE_STORAGE + 1,
              "Secure Storage operation error.",
            ))
          ] = "ERROR_UTILS_STORAGE_SECURESTORAGE_OPERATION"),
          (a[
            (a.ERROR_UTILS_BINARY_UNEXPECTED = h(
              p.BINARY + 1,
              "unexpected error occured.",
            ))
          ] = "ERROR_UTILS_BINARY_UNEXPECTED"),
          (a[
            (a.ERROR_UTILS_BINARY_FILE_READER_OPERATION = h(
              p.BINARY + 2,
              "FileReader operation error.",
            ))
          ] = "ERROR_UTILS_BINARY_FILE_READER_OPERATION"),
          (a[
            (a.ERROR_UTILS_BINARY_IMAGE_OPERATION = h(
              p.BINARY + 3,
              "Image operation error.",
            ))
          ] = "ERROR_UTILS_BINARY_IMAGE_OPERATION"),
          (a[
            (a.ERROR_UTILS_NATIVE_BRIDGE_OPERATION = h(
              p.NATIVE_BRIDGE + 1,
              "NativeBridge operation error.",
            ))
          ] = "ERROR_UTILS_NATIVE_BRIDGE_OPERATION"),
          (a[
            (a.ERROR_UTILS_STORE_SERVER_NOT_CONNECTED = h(
              p.STORE_SERVER + 1,
              "cannot connect to store server.",
            ))
          ] = "ERROR_UTILS_STORE_SERVER_NOT_CONNECTED"),
          (a[
            (a.ERROR_UTILS_STORE_SERVER_PERMISSION_DENIED = h(
              p.STORE_SERVER + 2,
              "download permission denied.",
            ))
          ] = "ERROR_UTILS_STORE_SERVER_PERMISSION_DENIED"),
          (a[
            (a.ERROR_UTILS_STORE_SERVER_DATA_NOT_FOUND = h(
              p.STORE_SERVER + 3,
              "data not found.",
            ))
          ] = "ERROR_UTILS_STORE_SERVER_DATA_NOT_FOUND"),
          (a[
            (a.ERROR_UTILS_STORE_SERVER_UNPROCESSABLE_ENTITY = h(
              p.STORE_SERVER + 4,
              "unprocessable entity.",
            ))
          ] = "ERROR_UTILS_STORE_SERVER_UNPROCESSABLE_ENTITY"),
          (a[
            (a.ERROR_UTILS_STORE_SERVER_TOO_MANY_REQUESTS = h(
              p.STORE_SERVER + 5,
              "too many requests.",
            ))
          ] = "ERROR_UTILS_STORE_SERVER_TOO_MANY_REQUESTS"),
          (a[
            (a.ERROR_UTILS_STORE_SERVER_INTERNAL_SERVER_ERROR = h(
              p.STORE_SERVER + 6,
              "internal server error.",
            ))
          ] = "ERROR_UTILS_STORE_SERVER_INTERNAL_SERVER_ERROR"),
          (a[
            (a.ERROR_UTILS_STORE_SERVER_SERVICE_UNAVAILABLE = h(
              p.STORE_SERVER + 7,
              "server maintenance.",
            ))
          ] = "ERROR_UTILS_STORE_SERVER_SERVICE_UNAVAILABLE"),
          (a[
            (a.ERROR_UTILS_STORE_SERVER_SERVICE_GONE = h(
              p.STORE_SERVER + 8,
              "service has gone.",
            ))
          ] = "ERROR_UTILS_STORE_SERVER_SERVICE_GONE"),
          (a[
            (a.ERROR_UTILS_STORE_SERVER_NETWORK_OTHER = h(
              p.STORE_SERVER + 9,
              "network error occured.",
            ))
          ] = "ERROR_UTILS_STORE_SERVER_NETWORK_OTHER"),
          (a[
            (a.ERROR_UTILS_ANALYTICS_INITIALIZE_FAILD = h(
              p.ANALYTICS + 1,
              "google-analytics plugin initialize failed.",
            ))
          ] = "ERROR_UTILS_ANALYTICS_INITIALIZE_FAILD");
      })(a.RESULT_CODE || (a.RESULT_CODE = {}));
    var n = a.RESULT_CODE;
    a.makeServerErrorInfo = i;
  })(a || (a = {}));
  var a;
  !(function (a) {
    var c;
    !(function (c) {
      function d() {
        return (
          f || (f = new k()), h.Mobile ? f.getLevel() : f.getLevelFallback()
        );
      }
      function e() {
        return (
          f || (f = new k()), h.Mobile ? f.isPlugged() : f.isPluggedFallback()
        );
      }
      var f,
        g = CDP.makePromise,
        h = CDP.Framework.Platform,
        i = CDP.NativeBridge.Gate,
        j = "[FES.NativeBridge.BatteryStatus] ",
        k = (function (c) {
          function d() {
            c.call(this, {
              name: "BatteryStatus",
              android: {
                packageInfo: "jp.co.sony.fes.nativebridge.BatteryStatus",
              },
              ios: { packageInfo: "FESBatteryStatus" },
            }),
              (this._initialized = !1),
              (this._listener = null),
              (this._level = null),
              (this._isPlugged = null);
          }
          return (
            b(d, c),
            (d.prototype.getLevel = function () {
              var b = $.Deferred(),
                d = g(b);
              return (
                d
                  .dependOn(c.prototype.exec.call(this, "getLevel", arguments))
                  .done(function (a) {
                    b.resolve(a.params[0]);
                  })
                  .fail(function (c) {
                    b.reject(
                      a.makeErrorInfo(
                        a.RESULT_CODE.ERROR_UTILS_NATIVE_BRIDGE_OPERATION,
                        j,
                        "BatteryStatus.getLevel() failed",
                        c,
                      ),
                    );
                  }),
                d
              );
            }),
            (d.prototype.isPlugged = function () {
              var b = $.Deferred(),
                d = g(b);
              return (
                d
                  .dependOn(c.prototype.exec.call(this, "isPlugged", arguments))
                  .done(function (a) {
                    b.resolve(a.params[0]);
                  })
                  .fail(function (c) {
                    b.reject(
                      a.makeErrorInfo(
                        a.RESULT_CODE.ERROR_UTILS_NATIVE_BRIDGE_OPERATION,
                        j,
                        "BatteryStatus.getIsPlugged() failed",
                        c,
                      ),
                    );
                  }),
                d
              );
            }),
            (d.prototype.getLevelFallback = function () {
              return (
                this._initialized || this.initializeFallback(),
                g($.Deferred().resolve(this._level))
              );
            }),
            (d.prototype.isPluggedFallback = function () {
              return (
                this._initialized || this.initializeFallback(),
                g($.Deferred().resolve(this._isPlugged))
              );
            }),
            (d.prototype.initializeFallback = function () {
              (this._listener = this.onBatteryStatus.bind(this)),
                window.addEventListener("batterystatus", this._listener, !1),
                (this._initialized = !0);
            }),
            (d.prototype.onBatteryStatus = function (a) {
              console.log(j + "onBatteryStatus: " + JSON.stringify(a, null, 4)),
                (this._level = a.level),
                (this._isPlugged = a.isPlugged);
            }),
            d
          );
        })(i);
      (c.getBatteryLevel = d), (c.isBatteryPlugged = e);
    })((c = a.NativeBridge || (a.NativeBridge = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b = CDP.global.Config;
    (a.STORE_API_ENDPOINT_LOCAL = (function () {
      return CDP.Framework.toUrl("/res/data/api/");
    })()),
      (a.STORE_API_ENDPOINT_DEV = "https://fes-dev-api.fes-street.com/api/v1/"),
      (a.STORE_API_ENDPOINT_QA = "https://fes-qa-api.fes-street.com/api/v1/"),
      (a.STORE_API_ENDPOINT_STAGING =
        "https://fes-stg-api.fes-street.com/api/v1/"),
      (a.STORE_API_ENDPOINT_PRODUCTION = "https://api.fes-street.com/api/v1/"),
      (a.STORE_STORAGE_ENDPOINT_LOCAL = (function () {
        return CDP.Framework.toUrl("/res/data/storage/");
      })()),
      (a.STORE_ENDPOINT_STATIC_CN_PRODUCTION =
        "https://s3.cn-north-1.amazonaws.com.cn/fes-store-cn/"),
      (a.STORE_ENDPOINT_STATIC_CN_STAGING =
        "https://s3.cn-north-1.amazonaws.com.cn/fes-store-cn-staging/"),
      (a.STORE_ENDPOINT_STATIC_WW_PRODUCTION =
        "https://s3-ap-northeast-1.amazonaws.com/fes-store/"),
      (a.STORE_ENDPOINT_STATIC_WW_QA =
        "https://s3-ap-northeast-1.amazonaws.com/fes-store-qa/"),
      (a.STORE_ENDPOINT_STATIC_WW_SKINCHECK =
        "https://s3-ap-northeast-1.amazonaws.com/fes-store-skincheck/"),
      (a.STORE_ENDPOINT_STATIC_WW_DEV =
        "https://s3-ap-northeast-1.amazonaws.com/fes-store-dev/");
    var c = "api/",
      d = "storage/";
    (a.STORE_SS_ENDPOINT_DEV = "https://fes-dev-skin.s3.amazonaws.com/"),
      (a.STORE_SS_ENDPOINT_QA = "https://fes-qa-skin.s3.amazonaws.com/"),
      (a.STORE_SS_ENDPOINT_STAGING = "https://fes-stg-skin.s3.amazonaws.com/"),
      (a.STORE_SS_ENDPOINT_PRODUCTION =
        "https://fes-prod-skin.s3.amazonaws.com/"),
      (a.EXT_STORE_API_ENDPOINT_STATIC_CN_PRODUCTION = ""),
      (a.EXT_STORE_API_ENDPOINT_STATIC_CN_STAGING = ""),
      (a.EXT_STORE_API_ENDPOINT_STATIC_WW_SKINCHECK =
        "https://fes-skincheck-sd.fes-street.com/v1/"),
      (a.EXT_STORE_API_ENDPOINT_STATIC_WW_DEV =
        "https://fes-dev-sd.fes-street.com/v1/"),
      (a.EXT_STORE_API_ENDPOINT_STATIC_WW_QA =
        "https://fes-qa-sd.fes-street.com/v1/"),
      (a.EXT_STORE_API_ENDPOINT_STATIC_WW_PRODUCTION =
        "https://sd.fes-street.com/v1/"),
      (a.SERVER_ENV_NAME = (function () {
        return "%% target_server %%" === b.TARGET_SERVER
          ? "qa"
          : b.TARGET_SERVER;
      })()),
      (a.TARGET_STATIC = (function () {
        return (
          null !== b.TARGET_SERVER && b.TARGET_SERVER.indexOf("static") !== -1
        );
      })()),
      (a.TARGET_CN = (function () {
        return "cn" === b.BUILD_TYPE;
      })()),
      (a.TARGET_APP_NAME = (function () {
        return a.TARGET_CN ? $.t("app.namecn") : $.t("app.name");
      })()),
      (a.TARGET_LOCAL = (function () {
        return (
          null !== b.TARGET_SERVER && b.TARGET_SERVER.indexOf("local") !== -1
        );
      })()),
      (a.STORE_API_ENDPOINT = (function () {
        switch (a.SERVER_ENV_NAME) {
          case "prod-local-cn":
          case "stg-local-cn":
          case "dev-local-ww":
          case "qa-local-ww":
          case "prod-local-ww":
            return a.STORE_API_ENDPOINT_LOCAL;
          case "prod-static-cn":
            return a.STORE_ENDPOINT_STATIC_CN_PRODUCTION + c;
          case "stg-static-cn":
            return a.STORE_ENDPOINT_STATIC_CN_STAGING + c;
          case "skincheck-static-ww":
            return a.STORE_ENDPOINT_STATIC_WW_SKINCHECK + c;
          case "dev-static-ww":
            return a.STORE_ENDPOINT_STATIC_WW_DEV + c;
          case "qa-static-ww":
            return a.STORE_ENDPOINT_STATIC_WW_QA + c;
          case "prod-static-ww":
            return a.STORE_ENDPOINT_STATIC_WW_PRODUCTION + c;
          case "dev":
            return a.STORE_API_ENDPOINT_DEV;
          case "qa":
            return a.STORE_API_ENDPOINT_QA;
          case "stg":
            return a.STORE_API_ENDPOINT_STAGING;
          case "prod":
            return a.STORE_API_ENDPOINT_PRODUCTION;
          default:
            return a.STORE_API_ENDPOINT_QA;
        }
      })()),
      (a.STORE_STORAGE_ENDPOINT = (function () {
        switch (a.SERVER_ENV_NAME) {
          case "prod-local-cn":
          case "stg-local-cn":
          case "dev-local-ww":
          case "qa-local-ww":
          case "prod-local-ww":
            return a.STORE_STORAGE_ENDPOINT_LOCAL;
          case "prod-static-cn":
            return a.STORE_ENDPOINT_STATIC_CN_PRODUCTION + d;
          case "stg-static-cn":
            return a.STORE_ENDPOINT_STATIC_CN_STAGING + d;
          case "skincheck-static-ww":
            return a.STORE_ENDPOINT_STATIC_WW_SKINCHECK + d;
          case "dev-static-ww":
            return a.STORE_ENDPOINT_STATIC_WW_DEV + d;
          case "qa-static-ww":
            return a.STORE_ENDPOINT_STATIC_WW_QA + d;
          case "prod-static-ww":
            return a.STORE_ENDPOINT_STATIC_WW_PRODUCTION + d;
          default:
            return a.STORE_ENDPOINT_STATIC_CN_STAGING + d;
        }
      })()),
      (a.NOTIFICATION_API_ENDPOINT = (function () {
        switch (a.SERVER_ENV_NAME) {
          case "dev-static-ww":
            return a.STORE_API_ENDPOINT_DEV + "store/devices/register";
          case "qa-static-ww":
            return a.STORE_API_ENDPOINT_QA + "store/devices/register";
          case "prod-static-ww":
            return a.STORE_API_ENDPOINT_PRODUCTION + "store/devices/register";
        }
        return a.STORE_API_ENDPOINT + "store/devices/register";
      })()),
      (a.EXT_STORE_API_ENDPOINT = (function () {
        switch (a.SERVER_ENV_NAME) {
          case "prod-local-cn":
          case "prod-static-cn":
            return a.EXT_STORE_API_ENDPOINT_STATIC_CN_PRODUCTION;
          case "stg-local-cn":
          case "stg-static-cn":
            return a.EXT_STORE_API_ENDPOINT_STATIC_CN_STAGING;
          case "skincheck-static-ww":
            return a.EXT_STORE_API_ENDPOINT_STATIC_WW_SKINCHECK;
          case "dev-local-ww":
          case "dev-static-ww":
            return a.EXT_STORE_API_ENDPOINT_STATIC_WW_DEV;
          case "qa-local-ww":
          case "qa-static-ww":
            return a.EXT_STORE_API_ENDPOINT_STATIC_WW_QA;
          case "prod-local-ww":
          case "prod-static-ww":
            return a.EXT_STORE_API_ENDPOINT_STATIC_WW_PRODUCTION;
          default:
            return "";
        }
      })()),
      (a.PLATFORM_ID = (function () {
        return CDP.Framework.Platform.iOS ? "ios" : "android";
      })()),
      (a.MIME_TYPE_BINRAY_DATA = "application/octet-stream"),
      (a.MIME_TYPE_TEXT = "text/plain"),
      (a.MIME_TYPE_IMG_PNG = "image/png"),
      (a.ENCODING_UTF_8 = "utf-8"),
      (a.BIT_LENGTH_OF_BYTE = 8),
      (a.PROC_LOOP_THRESHOLD = 50),
      (a.CN_SERVER_URL = "https://www.sonystyle.com.cn/"),
      (a.WW_VERSION_BUCKET =
        "https://s3-ap-northeast-1.amazonaws.com/fes-sa-version/"),
      (a.STORAGE_DIRECTORY = "<STORAGE_DIRECTORY>/");
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (b) {
      var c = "[FES.Utils.BitArray] ",
        d = 32,
        e = (function () {
          function b(a, c) {
            (this._buffer = a),
              (this._view = new DataView(a)),
              (this._cursor = { byte: 0, bit: 0 }),
              (this._rwMode = b.s_defaultOptions.rwMode),
              (this._lsb0 = b.s_defaultOptions.lsb0),
              (this._littleEndian = b.s_defaultOptions.littleEndian),
              null != c &&
                ((this._rwMode = c.rwMode || this._rwMode),
                (this._lsb0 = c.lsb0 || this._lsb0),
                (this._littleEndian = c.littleEndian || this._littleEndian));
          }
          return (
            Object.defineProperty(b.prototype, "buffer", {
              get: function () {
                return this._buffer;
              },
              enumerable: !0,
              configurable: !0,
            }),
            Object.defineProperty(b.prototype, "cursor", {
              get: function () {
                return (
                  this._cursor.byte * a.BIT_LENGTH_OF_BYTE + this._cursor.bit
                );
              },
              set: function (b) {
                (this._cursor.byte = Math.floor(b / a.BIT_LENGTH_OF_BYTE)),
                  (this._cursor.bit = b % a.BIT_LENGTH_OF_BYTE);
              },
              enumerable: !0,
              configurable: !0,
            }),
            Object.defineProperty(b.prototype, "rwMode", {
              get: function () {
                return this._rwMode;
              },
              enumerable: !0,
              configurable: !0,
            }),
            Object.defineProperty(b.prototype, "lsb0", {
              get: function () {
                return this._lsb0;
              },
              enumerable: !0,
              configurable: !0,
            }),
            Object.defineProperty(b.prototype, "littleEndian", {
              get: function () {
                return this._littleEndian;
              },
              enumerable: !0,
              configurable: !0,
            }),
            (b.prototype.sliceBufferByCursor = function () {
              return 0 === this._cursor.bit
                ? this._buffer.slice(0, this._cursor.byte)
                : this._buffer.slice(0, this._cursor.byte + 1);
            }),
            (b.prototype.read = function (b) {
              var d = this;
              if ((void 0 === b && (b = 1), "read" !== this._rwMode))
                return console.error(c + "read() is in read mode only."), null;
              for (
                var e = null,
                  f = 0,
                  g = function () {
                    var b;
                    b = d._lsb0
                      ? d._cursor.bit
                      : a.BIT_LENGTH_OF_BYTE - d._cursor.bit - 1;
                    var c = d.getCursorByte(),
                      e = (c >> b) & 1;
                    return d.shiftCursor(1), e;
                  };
                f < b;

              ) {
                if (this.isCursorOver()) {
                  console.warn(c + "buffer overflow!");
                  break;
                }
                var h = g();
                null == e && (e = 0), (e = (e << 1) | h), f++;
              }
              return (
                null != e && this._littleEndian && (e = this.reverse(e, f)), e
              );
            }),
            (b.prototype.write = function (b, d) {
              var e = this;
              if ((void 0 === d && (d = 1), "write" !== this._rwMode))
                return (
                  console.error(c + "write() is in write mode only."), null
                );
              var f = b,
                g = 0;
              this._littleEndian || (f = this.reverse(f, d));
              for (
                var h = !0,
                  i = function (b) {
                    var c;
                    c = e._lsb0
                      ? e._cursor.bit
                      : a.BIT_LENGTH_OF_BYTE - e._cursor.bit - 1;
                    var d = e.getCursorByte(),
                      f = b << c;
                    e._view.setUint8(e._cursor.byte, d | f), e.shiftCursor(1);
                  };
                g < d;

              ) {
                if (this.isCursorOver()) {
                  console.warn(c + "buffer overflow!"), (h = !1);
                  break;
                }
                var j = 1 & f;
                i(j), (f >>>= 1), g++;
              }
              return h;
            }),
            (b.prototype.getCursorByte = function () {
              return this._view.getUint8(this._cursor.byte);
            }),
            (b.prototype.reverse = function (a, b) {
              var e = b;
              d < e &&
                (console.warn(c + "too large bit length. set 32 or less."),
                (e = d));
              for (var f = a, g = 0, h = 0; h < e; h++)
                (g <<= 1), (g |= 1 & f), (f >>>= 1);
              return g;
            }),
            (b.prototype.shiftCursor = function (b) {
              if ((void 0 === b && (b = 1), !(b < 0)))
                for (
                  this._cursor.bit += b;
                  this._cursor.bit >= a.BIT_LENGTH_OF_BYTE;

                )
                  this._cursor.byte++,
                    (this._cursor.bit -= a.BIT_LENGTH_OF_BYTE);
            }),
            (b.prototype.isCursorOver = function () {
              return (
                this._buffer.byteLength * a.BIT_LENGTH_OF_BYTE <= this.cursor
              );
            }),
            (b.s_defaultOptions = {
              rwMode: "read",
              lsb0: !1,
              littleEndian: !1,
            }),
            b
          );
        })();
      b.BitArray = e;
    })((b = a.Utils || (a.Utils = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (b) {
      var c = CDP.makePromise,
        d = b.BitArray,
        e = "[FES.Utils.Binary] ",
        f = (function () {
          function b() {}
          return (
            (b.readBlobAsArrayBuffer = function (b) {
              if (null == b)
                return c(
                  $.Deferred().reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_BINARY_FILE_READER_OPERATION,
                      e,
                      "blob not found.",
                    ),
                  ),
                );
              var d = $.Deferred(),
                f = new FileReader();
              (f.onload = function () {
                d.resolve(f.result);
              }),
                (f.onerror = function () {
                  d.reject(
                    a.makeErrorInfoFromDOMError(
                      a.RESULT_CODE.ERROR_UTILS_BINARY_FILE_READER_OPERATION,
                      f.error,
                      e,
                      "FileReader.readAsArrayBuffer() failed.",
                    ),
                  );
                }),
                f.readAsArrayBuffer(b);
              var g = function () {
                return f.abort();
              };
              return c(d, g);
            }),
            (b.readBlobAsUint8Array = function (a) {
              var d = $.Deferred(),
                e = c(d);
              return (
                e
                  .dependOn(b.readBlobAsArrayBuffer(a))
                  .done(function (a) {
                    var b = new Uint8Array(a);
                    d.resolve(b);
                  })
                  .fail(function (a) {
                    d.reject(a);
                  }),
                e
              );
            }),
            (b.readBlobAsText = function (b) {
              var d = $.Deferred(),
                f = new FileReader();
              (f.onload = function () {
                d.resolve(f.result);
              }),
                (f.onerror = function () {
                  d.reject(
                    a.makeErrorInfoFromDOMError(
                      a.RESULT_CODE.ERROR_UTILS_BINARY_FILE_READER_OPERATION,
                      f.error,
                      e,
                      "FileReader.readAsText() failed.",
                    ),
                  );
                }),
                f.readAsText(b, a.ENCODING_UTF_8);
              var g = function () {
                return f.abort();
              };
              return c(d, g);
            }),
            (b.readBlobAsDataURL = function (b) {
              var d = $.Deferred(),
                f = new FileReader();
              (f.onload = function () {
                d.resolve(f.result);
              }),
                (f.onerror = function () {
                  d.reject(
                    a.makeErrorInfoFromDOMError(
                      a.RESULT_CODE.ERROR_UTILS_BINARY_FILE_READER_OPERATION,
                      f.error,
                      e,
                      "FileReader.readAsDataURL() failed.",
                    ),
                  );
                }),
                f.readAsDataURL(b);
              var g = function () {
                return f.abort();
              };
              return c(d, g);
            }),
            (b.packUint8ClampedArray = function (b, e) {
              var f = $.Deferred(),
                g = c(f),
                h = 0,
                i = Math.ceil((b.length * e) / a.BIT_LENGTH_OF_BYTE),
                j = new d(new ArrayBuffer(i), {
                  rwMode: "write",
                  lsb0: !1,
                  littleEndian: !1,
                }),
                k = function () {
                  if ("pending" === f.state()) {
                    for (var c = Date.now(); ; ) {
                      var d = b[h++];
                      if (null == d)
                        return void f.resolve(j.sliceBufferByCursor());
                      if (
                        (j.write(d, e), Date.now() - c > a.PROC_LOOP_THRESHOLD)
                      )
                        break;
                    }
                    setTimeout(k);
                  }
                };
              return setTimeout(k), g;
            }),
            (b.createBinaryBlob = function (b) {
              var c = { type: a.MIME_TYPE_BINRAY_DATA };
              return new Blob(b, c);
            }),
            (b.base64ToImageData = function (d) {
              var f = $.Deferred(),
                g = new Image();
              return (
                (g.src = d),
                (g.onload = function () {
                  var a = b.imageElementToImageData(g);
                  f.resolve(a);
                }),
                (g.onerror = function (b) {
                  f.reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_BINARY_IMAGE_OPERATION,
                      e,
                      "loading image failed.",
                      { name: b.type, message: b.type },
                    ),
                  );
                }),
                c(f)
              );
            }),
            (b.getCanvas = function () {
              return (
                (b.s_canvasFactory =
                  b.s_canvasFactory || document.createElement("canvas")),
                b.s_canvasFactory.cloneNode(!1)
              );
            }),
            (b.imageElementToImageData = function (a) {
              if (!a.complete)
                return console.error(e + "image is not fully loaded."), null;
              var c = a.width,
                d = a.height,
                f = b.getCanvas();
              (f.width = c), (f.height = d);
              var g = f.getContext("2d");
              return (
                (g.mozImageSmoothingEnabled = !1),
                (g.webkitImageSmoothingEnabled = !1),
                (g.msImageSmoothingEnabled = !1),
                (g.imageSmoothingEnabled = !1),
                g.drawImage(a, 0, 0),
                g.getImageData(0, 0, c, d)
              );
            }),
            b
          );
        })();
      b.Binary = f;
    })((b = a.Utils || (a.Utils = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (b) {
      var c;
      !(function (a) {
        (a.DEVICE_STORAGE = "device:local-storage"),
          (a.SECURE_STORAGE = "device:secure-storage"),
          (a.WEB_STORAGE = "web:local-storage");
      })((c = b.STORAGE_KIND || (b.STORAGE_KIND = {})));
      var d;
      !(function (c) {
        function d(b) {
          var c,
            d = $.Deferred(),
            e = j(d);
          return (
            b instanceof Blob
              ? e
                  .dependOn(q(b))
                  .done(function (a) {
                    (c = k(a)), d.resolve(c);
                  })
                  .fail(function (b) {
                    d.reject(
                      a.makeErrorInfo(
                        a.RESULT_CODE.ERROR_UTILS_STORAGE_DATA_NOT_SUPPORTED,
                        t,
                        "readBlobAsArrayBuffer() failed.",
                        b,
                      ),
                    );
                  })
              : setTimeout(function () {
                  b instanceof ArrayBuffer
                    ? ((c = k(b)), d.resolve(c))
                    : b instanceof Uint8Array
                      ? ((c = l(b)), d.resolve(c))
                      : d.reject(
                          a.makeErrorInfo(
                            a.RESULT_CODE
                              .ERROR_UTILS_STORAGE_DATA_NOT_SUPPORTED,
                            t,
                            "unknown data object.",
                          ),
                        );
                }),
            e
          );
        }
        function e(b, c, d) {
          var e,
            f = $.Deferred(),
            g = j(f);
          return (
            setTimeout(function () {
              try {
                switch (c) {
                  case "blob":
                    e = m(b, d);
                    break;
                  case "buffer":
                    e = n(b);
                    break;
                  case "binary":
                    e = o(b);
                    break;
                  default:
                    return f.reject(
                      a.makeErrorInfo(
                        a.RESULT_CODE.ERROR_UTILS_STORAGE_DATA_NOT_SUPPORTED,
                        t,
                        "unknown data type: " + c,
                      ),
                    );
                }
                f.resolve(e);
              } catch (b) {
                console.error(t + "detect exception."),
                  f.reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_BASE64,
                      t,
                      "convert from base64 error.",
                      b,
                    ),
                  );
              }
            }),
            g
          );
        }
        function f(b) {
          var c = $.Deferred(),
            e = j(c);
          return (
            b instanceof Blob ||
            b instanceof ArrayBuffer ||
            b instanceof Uint8Array
              ? e
                  .dependOn(d(b))
                  .done(function (a) {
                    c.resolve(a);
                  })
                  .fail(function (a) {
                    c.reject(a);
                  })
              : setTimeout(function () {
                  try {
                    var d = JSON.stringify(b);
                    c.resolve(d);
                  } catch (b) {
                    console.error(t + "detect exception."),
                      c.reject(
                        a.makeErrorInfo(
                          a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_JSON,
                          t,
                          "json stringify error.",
                          b,
                        ),
                      );
                  }
                }),
            e
          );
        }
        function g(b, c, d) {
          var f = $.Deferred(),
            g = j(f);
          if (null == b) return f.resolve(), g;
          if ("text" !== c)
            g.dependOn(e(b, c, d))
              .done(function (a) {
                f.resolve(a);
              })
              .fail(function (a) {
                f.reject(a);
              });
          else
            try {
              var h = JSON.parse(b);
              f.resolve(h);
            } catch (b) {
              console.error(t + "detect exception."),
                f.reject(
                  a.makeErrorInfo(
                    a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_JSON,
                    t,
                    "json parse error.",
                    b,
                  ),
                );
            }
          return g;
        }
        function h(b, c) {
          var d = $.Deferred(),
            e = j(d);
          return (
            setTimeout(function () {
              var e;
              if (b instanceof Blob) e = b;
              else if (b instanceof ArrayBuffer)
                e = p(b, c || a.MIME_TYPE_BINRAY_DATA);
              else if (b instanceof Uint8Array)
                e = p(b.buffer, c || a.MIME_TYPE_BINRAY_DATA);
              else
                try {
                  var f = JSON.stringify(b);
                  e = new Blob([f], { type: c || a.MIME_TYPE_TEXT });
                } catch (b) {
                  console.error(t + "detect exception."),
                    d.reject(
                      a.makeErrorInfo(
                        a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_JSON,
                        t,
                        "json stringify error.",
                        b,
                      ),
                    );
                }
              e
                ? d.resolve(e)
                : "pending" === d.state() &&
                  d.reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_STORAGE_UNEXPECTED,
                    ),
                  );
            }),
            e
          );
        }
        function i(b, c) {
          var d = $.Deferred(),
            e = j(d);
          return (
            "blob" === c
              ? d.resolve(b)
              : "buffer" === c
                ? e
                    .dependOn(q(b))
                    .done(function (a) {
                      d.resolve(a);
                    })
                    .fail(function (a) {
                      d.reject(a);
                    })
                : "binary" === c
                  ? e
                      .dependOn(r(b))
                      .done(function (a) {
                        d.resolve(a);
                      })
                      .fail(function (a) {
                        d.reject(a);
                      })
                  : e
                      .dependOn(s(b))
                      .done(function (b) {
                        try {
                          var c = JSON.parse(b);
                          d.resolve(c);
                        } catch (b) {
                          console.error(t + "detect exception."),
                            d.reject(
                              a.makeErrorInfo(
                                a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_JSON,
                                t,
                                "json parse error.",
                                b,
                              ),
                            );
                        }
                      })
                      .fail(function (a) {
                        d.reject(a);
                      }),
            e
          );
        }
        var j = CDP.makePromise,
          k = CDP.Tools.Blob.arrayBufferToBase64,
          l = CDP.Tools.Blob.uint8ArrayToBase64,
          m = CDP.Tools.Blob.base64ToBlob,
          n = CDP.Tools.Blob.base64ToArrayBuffer,
          o = CDP.Tools.Blob.base64ToUint8Array,
          p = CDP.Tools.Blob.arrayBufferToBlob,
          q = b.Binary.readBlobAsArrayBuffer,
          r = b.Binary.readBlobAsUint8Array,
          s = b.Binary.readBlobAsText,
          t = "[FES.Utils.StorageTools] ";
        (c.convertAsDataText = f),
          (c.convertFromDataText = g),
          (c.convertAsDataBlob = h),
          (c.convertFromDataBlob = i);
      })((d = b.StorageTools || (b.StorageTools = {})));
    })((b = a.Utils || (a.Utils = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (b) {
      var c = CDP.makePromise,
        d = "[FES.Utils.WebStorage] ",
        e = (function () {
          function e() {}
          return (
            Object.defineProperty(e.prototype, "kind", {
              get: function () {
                return b.STORAGE_KIND.WEB_STORAGE;
              },
              enumerable: !0,
              configurable: !0,
            }),
            (e.prototype.setItem = function (e, f, g) {
              var h = $.Deferred(),
                i = c(h);
              return e
                ? null == f
                  ? (h.resolve(), i)
                  : (i
                      .dependOn(b.StorageTools.convertAsDataText(f))
                      .done(function (a) {
                        localStorage.setItem(e, a), h.resolve();
                      })
                      .fail(function (a) {
                        h.reject(a);
                      }),
                    i)
                : (h.reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_PARAM,
                      d,
                      "key is " + e + ".",
                    ),
                  ),
                  i);
            }),
            (e.prototype.getItem = function (e, f) {
              var g = $.Deferred(),
                h = c(g),
                i = "text",
                j = a.MIME_TYPE_BINRAY_DATA,
                k = function () {
                  var a = $.Deferred(),
                    b = c(a);
                  return (
                    setTimeout(function () {
                      a.resolve(localStorage.getItem(e));
                    }),
                    b
                  );
                };
              return e
                ? (f &&
                    f.dataInfo &&
                    ((i = f.dataInfo.dataType || i),
                    (j = f.dataInfo.mimeType || j)),
                  h
                    .dependOn(k())
                    .then(function (a) {
                      return null != a
                        ? h.dependOn(
                            b.StorageTools.convertFromDataText(a, i, j),
                          )
                        : null;
                    })
                    .done(function (a) {
                      g.resolve(a);
                    })
                    .fail(function (a) {
                      g.reject(a);
                    }),
                  h)
                : (g.reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_PARAM,
                      d,
                      "key is " + e + ".",
                    ),
                  ),
                  h);
            }),
            (e.prototype.removeItem = function (b, e) {
              var f = $.Deferred(),
                g = c(f);
              return b
                ? (setTimeout(function () {
                    localStorage.removeItem(b), f.resolve();
                  }),
                  g)
                : (f.reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_PARAM,
                      d,
                      "key is " + b + ".",
                    ),
                  ),
                  g);
            }),
            (e.prototype.clear = function (a) {
              var b = $.Deferred();
              return (
                setTimeout(function () {
                  localStorage.clear(), b.resolve();
                }),
                c(b)
              );
            }),
            e
          );
        })();
      b.WebStorage = e;
    })((b = a.Utils || (a.Utils = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (b) {
      var c = CDP.makePromise,
        d = CDP.waitForDeviceReady,
        e = CDP.Framework.Platform,
        f = "[FES.Utils.DeviceStorage] ",
        g = (function () {
          function g() {
            e.Mobile ||
              console.error(f + "DeviceStrage supports on mobile device only.");
          }
          return (
            Object.defineProperty(g.prototype, "kind", {
              get: function () {
                return b.STORAGE_KIND.DEVICE_STORAGE;
              },
              enumerable: !0,
              configurable: !0,
            }),
            (g.prototype.setItem = function (d, e, g) {
              var h,
                i = this,
                j = $.Deferred(),
                k = c(j);
              return d
                ? null == e
                  ? (j.resolve(), k)
                  : (g && g.dataInfo && (h = g.dataInfo.mimeType),
                    k
                      .dependOn(this.waitForDeviceReady())
                      .then(function () {
                        return k.dependOn(
                          b.StorageTools.convertAsDataBlob(e, h),
                        );
                      })
                      .then(function (a) {
                        return k.dependOn(i.saveItem(d, a, g || {}));
                      })
                      .done(function () {
                        j.resolve();
                      })
                      .fail(function (a) {
                        j.reject(a);
                      }),
                    k)
                : (j.reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_PARAM,
                      f,
                      "key is " + d + ".",
                    ),
                  ),
                  k);
            }),
            (g.prototype.getItem = function (b, d) {
              var e = this,
                g = $.Deferred(),
                h = c(g);
              return b
                ? (h
                    .dependOn(this.waitForDeviceReady())
                    .then(function () {
                      return h.dependOn(e.loadItem(b, d || {}));
                    })
                    .done(function (a) {
                      g.resolve(a);
                    })
                    .fail(function (a) {
                      g.reject(a);
                    }),
                  h)
                : (g.reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_PARAM,
                      f,
                      "key is " + b + ".",
                    ),
                  ),
                  h);
            }),
            (g.prototype.removeItem = function (b, d) {
              var e = this,
                g = $.Deferred(),
                h = c(g);
              return b
                ? (h
                    .dependOn(this.waitForDeviceReady())
                    .then(function () {
                      return h.dependOn(e.removeEntry(b, d || {}));
                    })
                    .done(function () {
                      g.resolve();
                    })
                    .fail(function (a) {
                      g.reject(a);
                    }),
                  h)
                : (g.reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_PARAM,
                      f,
                      "key is " + b + ".",
                    ),
                  ),
                  h);
            }),
            (g.prototype.clear = function (a) {
              var b = this,
                d = $.Deferred(),
                e = c(d);
              return (
                e
                  .dependOn(this.waitForDeviceReady())
                  .then(function () {
                    return e.dependOn(b.clearEntries(a || {}));
                  })
                  .done(function () {
                    d.resolve();
                  })
                  .fail(function (a) {
                    d.reject(a);
                  }),
                e
              );
            }),
            (g.prototype.waitForDeviceReady = function () {
              var a = $.Deferred();
              return (
                g._handleDeviceReady
                  ? a.resolve()
                  : d().done(function () {
                      (g._handleDeviceReady = !0), a.resolve();
                    }),
                c(a)
              );
            }),
            (g.prototype.splitPath = function (a) {
              var b = { dir: "", file: "" };
              if (!a) return b;
              var c = a.split("/");
              return (
                (b.file = c.pop()),
                0 < c.length && "" === c[0] && c.shift(),
                0 < c.length && (b.dir = c.join("/")),
                b
              );
            }),
            (g.prototype.saveItem = function (a, b, d) {
              var e = this,
                f = $.Deferred(),
                g = c(f),
                h = this.splitPath(a);
              return (
                (function () {
                  var a = d.root || cordova.file.dataDirectory;
                  window.resolveLocalFileSystemURL(
                    a,
                    function (a) {
                      g.dependOn(e.ensureDirectory(a, h))
                        .then(function (a) {
                          return g.dependOn(e.createFile(a, h.file, b, d));
                        })
                        .done(function () {
                          f.resolve();
                        })
                        .fail(function (a) {
                          f.reject(a);
                        });
                    },
                    function (a) {
                      f.reject(
                        e.makeErrorInfoFromFileError(
                          a,
                          "resolveLocalFileSystemURI() failed.",
                        ),
                      );
                    },
                  );
                })(),
                g
              );
            }),
            (g.prototype.ensureDirectory = function (a, b) {
              var d = this,
                e = $.Deferred();
              if (null == b.dir) e.resolve(a);
              else {
                var f = a,
                  g = b.dir.split("/"),
                  h = function () {
                    var a = g.shift();
                    return a
                      ? void f.getDirectory(
                          a,
                          { create: !0 },
                          function (a) {
                            (f = a), setTimeout(h);
                          },
                          function (a) {
                            e.reject(
                              d.makeErrorInfoFromFileError(
                                a,
                                "create directory failed.",
                              ),
                            );
                          },
                        )
                      : void e.resolve(f);
                  };
                setTimeout(h);
              }
              return c(e);
            }),
            (g.prototype.createFile = function (b, d, e, g) {
              var h,
                i = this,
                j = function () {
                  h && h.abort();
                },
                k = $.Deferred(),
                l = c(k, j);
              return (
                b.getFile(
                  d,
                  { create: !0 },
                  function (b) {
                    "pending" === k.state() &&
                      b.createWriter(
                        function (b) {
                          "pending" === k.state() &&
                            ((h = b),
                            b.write(e),
                            (b.onwriteend = function (a) {
                              k.resolve();
                            }),
                            (b.onprogress = function (a) {
                              var b = (a.loaded / a.total) * 100;
                              k.notify(b);
                            }),
                            (b.onerror = function (b) {
                              k.reject(
                                a.makeErrorInfo(
                                  a.RESULT_CODE
                                    .ERROR_UTILS_STORAGE_DEVICE_FILE_OPERATION,
                                  f,
                                  "writer file failed.",
                                ),
                              );
                            }));
                        },
                        function (a) {
                          k.reject(
                            i.makeErrorInfoFromFileError(
                              a,
                              "create writer failed.",
                            ),
                          );
                        },
                      );
                  },
                  function (a) {
                    k.reject(
                      i.makeErrorInfoFromFileError(a, "create file failed."),
                    );
                  },
                ),
                l
              );
            }),
            (g.prototype.loadItem = function (d, e) {
              var g = $.Deferred(),
                h = c(g),
                i = this.splitPath(d),
                j = e.root || cordova.file.dataDirectory;
              return i.file
                ? (i.dir && (j += i.dir),
                  h
                    .dependOn(this.loadFile(j, i.file, e))
                    .then(function (a) {
                      if (a) {
                        var c = a.type;
                        return (
                          e && e.dataInfo && (c = e.dataInfo.dataType || c),
                          h.dependOn(b.StorageTools.convertFromDataBlob(a, c))
                        );
                      }
                      return null;
                    })
                    .done(function (a) {
                      g.resolve(a);
                    })
                    .fail(function (a) {
                      g.reject(a);
                    }),
                  h)
                : $.Deferred().reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_PARAM,
                      f,
                      "key is invalid. [key: " + d + "]",
                    ),
                  );
            }),
            (g.prototype.loadFile = function (a, b, d) {
              var e = this,
                g = $.Deferred(),
                h = c(g);
              return (
                window.resolveLocalFileSystemURL(
                  a,
                  function (c) {
                    "pending" === g.state() &&
                      c.getFile(
                        b,
                        { create: !1 },
                        function (a) {
                          "pending" === g.state() &&
                            a.file(
                              function (a) {
                                g.resolve(a);
                              },
                              function (a) {
                                g.reject(
                                  e.makeErrorInfoFromFileError(
                                    a,
                                    "FileEntry.file() failed.",
                                  ),
                                );
                              },
                            );
                        },
                        function (c) {
                          1 === c.code
                            ? (console.log(
                                f + b + " doesn't exist. " + JSON.stringify(c),
                              ),
                              g.resolve(null))
                            : g.reject(e.makeErrorInfoFromFileError(c, a));
                        },
                      );
                  },
                  function (b) {
                    console.log(f + a + " doesn't exist. " + JSON.stringify(b)),
                      g.resolve(null);
                  },
                ),
                h
              );
            }),
            (g.prototype.removeEntry = function (b, d) {
              var e = this,
                g = $.Deferred(),
                h = c(g),
                i = d.target || "both",
                j = function (a) {
                  var b = $.Deferred(),
                    f = c(b),
                    g = d.root || cordova.file.dataDirectory,
                    h = e.splitPath(a),
                    i = g + "/" + h.dir;
                  return (
                    f
                      .dependOn(e.removeFile(i, h.file, d))
                      .done(function () {
                        b.resolve(h.dir);
                      })
                      .fail(function (a) {
                        b.reject(a);
                      }),
                    f
                  );
                },
                k = function (a) {
                  var b = $.Deferred(),
                    f = c(b),
                    g = a,
                    h = d.root || cordova.file.dataDirectory;
                  return (
                    g && ("/" !== g[0] && (g = "/" + g), (h += g)),
                    f
                      .dependOn(e.removeDirectory(h, d))
                      .done(function () {
                        b.resolve();
                      })
                      .fail(function (a) {
                        b.reject(a);
                      }),
                    f
                  );
                };
              switch (i) {
                case "file":
                  h.dependOn(j(b))
                    .done(function () {
                      g.resolve();
                    })
                    .fail(function (a) {
                      g.reject(a);
                    });
                  break;
                case "directory":
                  h.dependOn(k(b))
                    .done(function () {
                      g.resolve();
                    })
                    .fail(function (a) {
                      g.reject(a);
                    });
                  break;
                case "both":
                  h.dependOn(j(b))
                    .then(function (a) {
                      if (a) return h.dependOn(k(a));
                    })
                    .done(function () {
                      g.resolve();
                    })
                    .fail(function (a) {
                      g.reject(a);
                    });
                  break;
                default:
                  g.reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_PARAM,
                      f,
                      "unknown removeItem target: " + i,
                    ),
                  );
              }
              return h;
            }),
            (g.prototype.removeFile = function (a, b, d) {
              var e = this,
                g = $.Deferred(),
                h = c(g);
              return (
                window.resolveLocalFileSystemURL(
                  a,
                  function (c) {
                    "pending" === g.state() &&
                      c.getFile(
                        b,
                        { create: !1 },
                        function (a) {
                          "pending" === g.state() &&
                            a.remove(
                              function () {
                                g.resolve();
                              },
                              function (a) {
                                g.reject(
                                  e.makeErrorInfoFromFileError(
                                    a,
                                    "FileEntry.remove() failed.",
                                  ),
                                );
                              },
                            );
                        },
                        function (c) {
                          1 === c.code
                            ? (console.log(
                                f + b + " doesn't exist. " + JSON.stringify(c),
                              ),
                              g.resolve(null))
                            : g.reject(e.makeErrorInfoFromFileError(c, a));
                        },
                      );
                  },
                  function (b) {
                    console.log(f + a + " doesn't exist. " + JSON.stringify(b)),
                      g.resolve();
                  },
                ),
                h
              );
            }),
            (g.prototype.removeDirectory = function (a, b) {
              var d = this,
                e = $.Deferred(),
                g = c(e);
              return (
                window.resolveLocalFileSystemURL(
                  a,
                  function (a) {
                    "pending" === e.state() &&
                      a.removeRecursively(
                        function () {
                          e.resolve();
                        },
                        function (a) {
                          e.reject(
                            d.makeErrorInfoFromFileError(
                              a,
                              "DirectoryEntry.removeRecursively() failed.",
                            ),
                          );
                        },
                      );
                  },
                  function (b) {
                    console.log(f + a + " doesn't exist. " + JSON.stringify(b)),
                      e.resolve();
                  },
                ),
                g
              );
            }),
            (g.prototype.clearEntries = function (a) {
              var b = this,
                d = $.Deferred(),
                e = c(d),
                f = a.root || cordova.file.dataDirectory,
                g = function (a, d) {
                  var e = $.Deferred(),
                    f = c(e),
                    g = d.slice(),
                    h = d.length,
                    i = 0,
                    j = function () {
                      var c = g.shift();
                      return c
                        ? (i++,
                          void c.removeRecursively(
                            function () {
                              a.notify((i / h) * 100), setTimeout(j);
                            },
                            function (a) {
                              e.reject(
                                b.makeErrorInfoFromFileError(
                                  a,
                                  "DirectoryEntry.removeRecursively() failed.",
                                ),
                              );
                            },
                          ))
                        : void e.resolve();
                    };
                  return setTimeout(j), f;
                };
              return (
                e
                  .dependOn(this.parseEntries(f, a))
                  .then(function (a) {
                    return e.dependOn(g(d, a));
                  })
                  .done(function () {
                    d.resolve();
                  })
                  .fail(function (a) {
                    d.reject(a);
                  }),
                e
              );
            }),
            (g.prototype.parseEntries = function (a, b) {
              var d = this,
                e = $.Deferred(),
                f = c(e);
              return (
                window.resolveLocalFileSystemURL(
                  a,
                  function (a) {
                    if ("pending" === e.state()) {
                      var b = a.createReader();
                      b.readEntries(
                        function (a) {
                          var b = [];
                          a.forEach(function (a) {
                            a.isDirectory && b.push(a);
                          }),
                            e.resolve(b);
                        },
                        function (a) {
                          e.reject(
                            d.makeErrorInfoFromFileError(
                              a,
                              "DirectoryEntry.removeRecursively() failed.",
                            ),
                          );
                        },
                      );
                    }
                  },
                  function (b) {
                    e.reject(
                      d.makeErrorInfoFromFileError(b, a + " doesn't exist. "),
                    );
                  },
                ),
                f
              );
            }),
            (g.prototype.makeErrorInfoFromFileError = function (b, c) {
              var d =
                2 === b.code
                  ? a.RESULT_CODE.ERROR_UTILS_STORAGE_DEVICE_FILE_SECURITY_ERR
                  : a.RESULT_CODE.ERROR_UTILS_STORAGE_DEVICE_FILE_OPERATION;
              return a.makeErrorInfo(d, f, c, {
                name: "cordova-plugin-file",
                message: c,
                code: b.code,
              });
            }),
            (g._handleDeviceReady = !1),
            g
          );
        })();
      b.DeviceStorage = g;
    })((b = a.Utils || (a.Utils = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (b) {
      var c = CDP.makePromise,
        d = CDP.Framework.Platform,
        e = "[FES.Utils.SecureStorage] ",
        f = (function () {
          function f() {
            d.Mobile ||
              console.error(
                e + "SecureStorage supports on mobile device only.",
              );
          }
          return (
            Object.defineProperty(f.prototype, "kind", {
              get: function () {
                return b.STORAGE_KIND.SECURE_STORAGE;
              },
              enumerable: !0,
              configurable: !0,
            }),
            (f.prototype.setItem = function (d, f, g) {
              var h = $.Deferred(),
                i = c(h);
              return d
                ? null == f
                  ? (h.resolve(), i)
                  : (i
                      .dependOn(b.StorageTools.convertAsDataText(f))
                      .done(function (b) {
                        cordova.plugins.SecureLocalStorage.setItem(d, b).then(
                          function () {
                            h.resolve();
                          },
                          function (b) {
                            h.reject(
                              a.makeErrorInfo(
                                a.RESULT_CODE
                                  .ERROR_UTILS_STORAGE_SECURESTORAGE_OPERATION,
                                e,
                                b,
                              ),
                            );
                          },
                        );
                      })
                      .fail(function (a) {
                        h.reject(a);
                      }),
                    i)
                : (h.reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_PARAM,
                      e,
                      "key is " + d + ".",
                    ),
                  ),
                  i);
            }),
            (f.prototype.getItem = function (d, f) {
              var g = $.Deferred(),
                h = c(g),
                i = "text",
                j = a.MIME_TYPE_BINRAY_DATA,
                k = function () {
                  var b = $.Deferred(),
                    f = c(b);
                  return (
                    cordova.plugins.SecureLocalStorage.getItem(d).then(
                      function (a) {
                        b.resolve(a);
                      },
                      function (c) {
                        b.reject(
                          a.makeErrorInfo(
                            a.RESULT_CODE
                              .ERROR_UTILS_STORAGE_SECURESTORAGE_OPERATION,
                            e,
                            c,
                          ),
                        );
                      },
                    ),
                    f
                  );
                };
              return d
                ? (f &&
                    f.dataInfo &&
                    ((i = f.dataInfo.dataType || i),
                    (j = f.dataInfo.mimeType || j)),
                  h
                    .dependOn(k())
                    .then(function (a) {
                      return null != a
                        ? h.dependOn(
                            b.StorageTools.convertFromDataText(a, i, j),
                          )
                        : null;
                    })
                    .done(function (a) {
                      g.resolve(a);
                    })
                    .fail(function (a) {
                      g.reject(a);
                    }),
                  h)
                : (g.reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_PARAM,
                      e,
                      "key is " + d + ".",
                    ),
                  ),
                  h);
            }),
            (f.prototype.removeItem = function (b, d) {
              var f = $.Deferred(),
                g = c(f);
              return b
                ? (cordova.plugins.SecureLocalStorage.removeItem(b).then(
                    function () {
                      f.resolve();
                    },
                    function (b) {
                      f.reject(
                        a.makeErrorInfo(
                          a.RESULT_CODE
                            .ERROR_UTILS_STORAGE_SECURESTORAGE_OPERATION,
                          e,
                          b,
                        ),
                      );
                    },
                  ),
                  g)
                : (f.reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_UTILS_STORAGE_INVALID_PARAM,
                      e,
                      "key is " + b + ".",
                    ),
                  ),
                  g);
            }),
            (f.prototype.clear = function (b) {
              var d = $.Deferred(),
                f = c(d);
              return (
                cordova.plugins.SecureLocalStorage.clear().then(
                  function () {
                    d.resolve();
                  },
                  function (b) {
                    d.reject(
                      a.makeErrorInfo(
                        a.RESULT_CODE
                          .ERROR_UTILS_STORAGE_SECURESTORAGE_OPERATION,
                        e,
                        b,
                      ),
                    );
                  },
                ),
                f
              );
            }),
            f
          );
        })();
      b.SecureStorage = f;
    })((b = a.Utils || (a.Utils = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (a) {
      var b = CDP.Framework.Platform,
        c = "[FES.Utils.StorageAccess] ",
        d = (function () {
          function d() {}
          return (
            (d.getStorage = function (d) {
              switch (d) {
                case a.STORAGE_KIND.DEVICE_STORAGE:
                  return b.Mobile ? new a.DeviceStorage() : new a.WebStorage();
                case a.STORAGE_KIND.SECURE_STORAGE:
                  return b.Mobile ? new a.SecureStorage() : new a.WebStorage();
                case a.STORAGE_KIND.WEB_STORAGE:
                  return new a.WebStorage();
                default:
                  return (
                    console.error(
                      c + "unknown storage kind. [kind: " + d + "]",
                    ),
                    null
                  );
              }
            }),
            d
          );
        })();
      a.StorageAccess = d;
    })((b = a.Utils || (a.Utils = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (b) {
      var c = CDP.waitForDeviceReady,
        d = CDP.Framework.Platform,
        e = "[FES.Utils.Analytics] ",
        f = (function () {
          return "prod-local-ww" === a.SERVER_ENV_NAME ||
            "prod" === a.SERVER_ENV_NAME ||
            "prod-static-ww" === a.SERVER_ENV_NAME
            ? "UA-85792334-5"
            : "UA-85792334-4";
        })(),
        g = "google-analytics",
        h = (function () {
          function h() {}
          return (
            (h.initialize = function () {
              var e = this,
                f = $.Deferred(),
                h = function () {
                  if (d.Mobile && !a.TARGET_CN) {
                    var b = $.Deferred();
                    return (
                      e
                        .startTracker()
                        .then(function () {
                          return e.setAnonymizeIp();
                        })
                        .done(function () {
                          b.resolve();
                        })
                        .fail(function (a) {
                          b.reject(a);
                        }),
                      b.promise()
                    );
                  }
                  return $.Deferred().resolve();
                };
              return (
                (this.s_storage = b.StorageAccess.getStorage(
                  b.STORAGE_KIND.SECURE_STORAGE,
                )),
                c()
                  .then(function () {
                    return h();
                  })
                  .then(function () {
                    return e.s_storage.getItem(g);
                  })
                  .done(function (b) {
                    a.TARGET_CN || ((e.s_initialized = !0), (e.s_enable = b)),
                      f.resolve();
                  })
                  .fail(function (a) {
                    f.reject(a);
                  }),
                f.promise()
              );
            }),
            Object.defineProperty(h, "enable", {
              get: function () {
                return !!this.s_initialized && this.s_enable;
              },
              set: function (a) {
                (this.s_enable = a), this.s_storage.setItem(g, this.s_enable);
              },
              enumerable: !0,
              configurable: !0,
            }),
            (h.trackView = function (a, b) {
              d.Mobile && this.enable && ga.trackView(a, b);
            }),
            (h.trackEvent = function (a, b, c, e) {
              d.Mobile && this.enable && ga.trackEvent(a, b, c, e);
            }),
            (h.trackError = function (b, c) {
              if (d.Mobile && this.enable) {
                var e = a.RESULT_CODE[c] || a.RESULT_CODE[a.RESULT_CODE.FAILED];
                ga.trackEvent("Error", e, b, c);
              }
            }),
            (h.startTracker = function () {
              var b = $.Deferred();
              return (
                ga.startTrackerWithId(
                  f,
                  function () {
                    b.resolve();
                  },
                  function (c) {
                    b.reject(
                      a.makeErrorInfo(
                        a.RESULT_CODE.ERROR_UTILS_ANALYTICS_INITIALIZE_FAILD,
                        e,
                        "google-analytics plugin startTrackerWithId() failed. error: " +
                          c,
                      ),
                    );
                  },
                ),
                b.promise()
              );
            }),
            (h.setAnonymizeIp = function () {
              var b = $.Deferred();
              return (
                ga.setAnonymizeIp(
                  !0,
                  function () {
                    b.resolve();
                  },
                  function (c) {
                    b.reject(
                      a.makeErrorInfo(
                        a.RESULT_CODE.ERROR_UTILS_ANALYTICS_INITIALIZE_FAILD,
                        e,
                        "google-analytics plugin setAnonymizeIp() failed. error: " +
                          c,
                      ),
                    );
                  },
                ),
                b.promise()
              );
            }),
            h
          );
        })();
      b.Analytics = h;
    })((b = a.Utils || (a.Utils = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (a) {})((b = a.Utils || (a.Utils = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (a) {
      var b = (function () {
        function a(a) {
          (this._max = null),
            (this._beginTime = null),
            (this._max = a || 100),
            this.reset();
        }
        return (
          (a.prototype.reset = function () {
            this._beginTime = Date.now();
          }),
          (a.prototype.getProgressTime = function (a) {
            var b = Date.now() - this._beginTime,
              c = 1 / 0;
            return (
              null != a && 0 !== a && (c = (b * this._max) / a - b),
              { passTime: b, remainTime: c }
            );
          }),
          a
        );
      })();
      a.ProgressTime = b;
    })((b = a.Utils || (a.Utils = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (a) {
      var b;
      !(function (a) {
        function b(a, b, c) {
          return new Uint8ClampedArray(Array.prototype.filter.call(a, b, c));
        }
        function c(a, b, c) {
          return new Uint8ClampedArray(Array.prototype.slice.call(a, b, c));
        }
        (a.uint8clampedarray_filter = b), (a.uint8clampedarray_slice = c);
      })((b = a.TypedArray || (a.TypedArray = {})));
    })((b = a.Utils || (a.Utils = {})));
  })(a || (a = {}));
  var a;
  return (
    (function (a) {
      var b;
      !(function (b) {
        var c = CDP.makePromise,
          d = CDP.global.FES,
          e = (function () {
            function e() {}
            return (
              (e.ensureOwnerId = function () {
                var e = $.Deferred(),
                  f = c(e),
                  g = !1,
                  h = null,
                  i = b.StorageAccess.getStorage(b.STORAGE_KIND.SECURE_STORAGE);
                return (
                  f
                    .dependOn(i.getItem(d.STORAGE_KEY_OWNER_ID))
                    .then(function (b) {
                      return null == b
                        ? ((g = !0), f.dependOn(a.NativeBridge.generateUUID()))
                        : b;
                    })
                    .then(function (a) {
                      if (((h = a), g))
                        return f.dependOn(i.setItem(d.STORAGE_KEY_OWNER_ID, a));
                    })
                    .done(function () {
                      e.resolve(h);
                    })
                    .fail(function (a) {
                      e.reject(a);
                    }),
                  f
                );
              }),
              e
            );
          })();
        b.DeviceInfo = e;
      })((b = a.Utils || (a.Utils = {})));
    })(a || (a = {})),
    a.Utils
  );
});
