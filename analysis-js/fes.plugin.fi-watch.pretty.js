/*!
 * FES-SA
 * 2020-03-16
 */
!(function (a, b) {
  "function" == typeof define && define.amd
    ? define(["zip", "fes.utils", "fes.protocol.ble"], function () {
        return b(a.FES || (a.FES = {}));
      })
    : b(a.FES || (a.FES = {}));
})(this, function (a) {
  a.Plugin = a.Plugin || {};
  var a;
  !(function (a) {
    var b;
    !(function (a) {
      (a.FI_WATCH_SKIN_FORMAT_VERSION = 1),
        (a.TRIAL_SKIN_ID = "C46E40EB9352451296B9ACD29D97151A");
    })((b = a.Plugin || (a.Plugin = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    function b(b, c) {
      return a.declareResultCode(a.RESULT_CODE_BASE.PLUGIN, b, c);
    }
    var c,
      d = 10;
    !(function (a) {
      (a[(a.SKIN_ZIP_PARSER = 0)] = "SKIN_ZIP_PARSER"),
        (a[(a.JSON_CONVERTER = 1 * d)] = "JSON_CONVERTER"),
        (a[(a.IMAGE_CONVERTER = 2 * d)] = "IMAGE_CONVERTER"),
        (a[(a.IMAGE_COMPRESSOR = 3 * d)] = "IMAGE_COMPRESSOR"),
        (a[(a.WATCH_TRANSFER = 4 * d)] = "WATCH_TRANSFER"),
        (a[(a.FW_UPDATE = 5 * d)] = "FW_UPDATE");
    })(c || (c = {})),
      (function (a) {
        (a[(a.ERROR_PLUGIN_DECLARATION = 0)] = "ERROR_PLUGIN_DECLARATION"),
          (a[
            (a.ERROR_PLUGIN_ZIP_READER = b(
              c.SKIN_ZIP_PARSER + 1,
              "zip reader error.",
            ))
          ] = "ERROR_PLUGIN_ZIP_READER"),
          (a[
            (a.ERROR_PLUGIN_ZIP_NO_ENTRY = b(
              c.SKIN_ZIP_PARSER + 2,
              "zip has no entries.",
            ))
          ] = "ERROR_PLUGIN_ZIP_NO_ENTRY"),
          (a[
            (a.ERROR_PLUGIN_ZIP_INVALID_JSON = b(
              c.SKIN_ZIP_PARSER + 3,
              "json is invalid.",
            ))
          ] = "ERROR_PLUGIN_ZIP_INVALID_JSON"),
          (a[
            (a.ERROR_PLUGIN_IMAGE_CONV_INVALID_IMAGE = b(
              c.IMAGE_CONVERTER + 1,
              "image is invalid.",
            ))
          ] = "ERROR_PLUGIN_IMAGE_CONV_INVALID_IMAGE"),
          (a[
            (a.ERROR_PLUGIN_IMAGE_COMP_INVALID_BINARY = b(
              c.IMAGE_COMPRESSOR + 1,
              "binary image is invalid.",
            ))
          ] = "ERROR_PLUGIN_IMAGE_COMP_INVALID_BINARY"),
          (a[
            (a.ERROR_PLUGIN_WATCH_TRANS_INVALID_FI = b(
              c.WATCH_TRANSFER + 1,
              "FI is invalid.",
            ))
          ] = "ERROR_PLUGIN_WATCH_TRANS_INVALID_FI"),
          (a[
            (a.ERROR_PLUGIN_WATCH_TRANS_BUSY = b(
              c.WATCH_TRANSFER + 2,
              "transfer is busy.",
            ))
          ] = "ERROR_PLUGIN_WATCH_TRANS_BUSY"),
          (a[
            (a.ERROR_PLUGIN_FW_UPDATE_FAILED = b(
              c.FW_UPDATE + 1,
              "fw update failed.",
            ))
          ] = "ERROR_PLUGIN_FW_UPDATE_FAILED"),
          (a[
            (a.ERROR_PLUGIN_FW_UPDATE_TIMEOUT = b(
              c.FW_UPDATE + 2,
              "fw update timeout.",
            ))
          ] = "ERROR_PLUGIN_FW_UPDATE_TIMEOUT");
      })(a.RESULT_CODE || (a.RESULT_CODE = {}));
    a.RESULT_CODE;
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (b) {
      var c,
        d = CDP.makePromise,
        e = a.Utils.Binary,
        f = "[FES.Plugin.FIWatchTransfer] ",
        g = (function () {
          function b() {
            (this._promiseTrans = null), (this._cancelable = null);
          }
          return (
            (b.getInstance = function () {
              return null == c && (c = new b()), c;
            }),
            (b.prototype.isRunning = function () {
              return (
                null != this._promiseTrans &&
                "pending" === this._promiseTrans.state()
              );
            }),
            (b.prototype.installSkin = function (b, c, g) {
              var h = this;
              if (this.isRunning())
                return d(
                  $.Deferred().reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_PLUGIN_WATCH_TRANS_BUSY,
                      f,
                      "busy with other data transfer.",
                    ),
                  ),
                );
              var i = $.Deferred();
              this._promiseTrans = d(i);
              var j = g.context.HEXString_to_Uint8Array(b);
              return (
                this._promiseTrans
                  .dependOn(e.readBlobAsUint8Array(c))
                  .then(function (b) {
                    return null == g.FI
                      ? $.Deferred().reject(
                          a.makeErrorInfo(
                            a.RESULT_CODE.ERROR_PLUGIN_WATCH_TRANS_INVALID_FI,
                            f,
                            "FI connection is invalid.",
                          ),
                        )
                      : h._promiseTrans.dependOn(
                          g.FI.installSkin(j, b, g.settings),
                        );
                  })
                  .progress(function (a) {
                    i.notify(a);
                  })
                  .done(function () {
                    i.resolve();
                  })
                  .fail(function (a) {
                    i.reject(a);
                  }),
                this._promiseTrans
              );
            }),
            (b.prototype.cancelInstallSkin = function () {
              this.isRunning() && this._promiseTrans.abort();
            }),
            (b.prototype.updateFW = function (b, c, g, h) {
              var i = this;
              if (this.isRunning())
                return d(
                  $.Deferred().reject(
                    a.makeErrorInfo(
                      a.RESULT_CODE.ERROR_PLUGIN_WATCH_TRANS_BUSY,
                      f,
                      "busy with other data transfer.",
                    ),
                  ),
                );
              var j = $.Deferred();
              (this._promiseTrans = d(j)), (this._cancelable = !0);
              var k = [],
                l = this.versionToUint32(c);
              return (
                this._promiseTrans
                  .dependOn(e.readBlobAsUint8Array(b[0]))
                  .then(function (a) {
                    return (
                      k.push(a),
                      i._promiseTrans.dependOn(e.readBlobAsUint8Array(b[1]))
                    );
                  })
                  .then(function (b) {
                    return (
                      k.push(b),
                      null == g.FI
                        ? $.Deferred().reject(
                            a.makeErrorInfo(
                              a.RESULT_CODE.ERROR_PLUGIN_WATCH_TRANS_INVALID_FI,
                              f,
                              "FI connection is invalid.",
                            ),
                          )
                        : i._promiseTrans.dependOn(i.startUpdateFW(g, k, l, h))
                    );
                  })
                  .progress(function (a) {
                    (i._cancelable = !1), j.notify(a);
                  })
                  .done(function () {
                    j.resolve();
                  })
                  .fail(function (a) {
                    j.reject(a);
                  })
                  .always(function () {
                    i._cancelable = !1;
                  }),
                this._promiseTrans
              );
            }),
            (b.prototype.cancelUpdateFW = function () {
              return (
                !(!this.isRunning() || !this._cancelable) &&
                (this._promiseTrans.abort(), !0)
              );
            }),
            (b.prototype.startUpdateFW = function (b, c, e, g) {
              var h = $.Deferred(),
                i = d(h);
              return (
                i
                  .dependOn(b.FI.updateFW(c, e, g, b.settings))
                  .progress(function (a) {
                    h.notify(a);
                  })
                  .done(function () {
                    h.resolve();
                  })
                  .fail(function (b) {
                    73 === b.code
                      ? h.reject(
                          a.makeErrorInfo(
                            a.RESULT_CODE.ERROR_PLUGIN_FW_UPDATE_TIMEOUT,
                            f,
                            "FW Update timeout.",
                            b,
                          ),
                        )
                      : h.reject(
                          a.makeErrorInfo(
                            a.RESULT_CODE.ERROR_PLUGIN_FW_UPDATE_FAILED,
                            f,
                            "FW Update failed.",
                            b,
                          ),
                        );
                  }),
                i
              );
            }),
            (b.prototype.versionToUint32 = function (a) {
              if (!/^\d+\.\d+\.\d+$/.test(a)) return null;
              var b = new ArrayBuffer(4),
                c = new Uint16Array(b, 0, 1),
                d = new Uint8Array(b, 2, 1),
                e = new Uint8Array(b, 3, 1),
                f = a.split(".");
              return (
                (e[0] = parseInt(f[0], 10)),
                (d[0] = parseInt(f[1], 10)),
                (c[0] = parseInt(f[2], 10)),
                new Uint32Array(b)[0]
              );
            }),
            b
          );
        })();
      b.FIWatchTransfer = g;
    })((b = a.Plugin || (a.Plugin = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (b) {
      var c = CDP.makePromise,
        d = a.Utils.BitArray,
        e = a.Utils.TypedArray,
        f = "[FES.Plugin.ImageCompressor] ",
        g = 32,
        h = 8,
        i = 1,
        j = 2,
        k = (function () {
          function b() {}
          return (
            (b.compressImage = function (a, b, d) {
              var e = this,
                f = $.Deferred(),
                g = c(f);
              return (
                g
                  .dependOn(this.encodeXor(a, b, d))
                  .then(function (a) {
                    return g.dependOn(e.encodeZeroLength(a));
                  })
                  .done(function (a) {
                    f.resolve(a);
                  })
                  .fail(function (a) {
                    f.reject(a);
                  }),
                g
              );
            }),
            (b.uncompressImage = function (a, b, d) {
              var e = this,
                f = $.Deferred(),
                g = c(f);
              return (
                g
                  .dependOn(this.decodeZeroLength(a))
                  .then(function (a) {
                    return g.dependOn(e.decodeXor(a, b, d));
                  })
                  .done(function (a) {
                    f.resolve(a);
                  })
                  .fail(function (a) {
                    f.reject(a);
                  }),
                g
              );
            }),
            (b.encodeXor = function (b, d, e) {
              var g = $.Deferred(),
                h = c(g);
              this.isCorrectLength(b, d, e) ||
                g.reject(
                  a.makeErrorInfo(
                    a.RESULT_CODE.ERROR_PLUGIN_IMAGE_COMP_INVALID_BINARY,
                    f,
                    "binary image has wrong length in encoding.",
                  ),
                );
              var i = b.length,
                j = new Uint8ClampedArray(i),
                k = 0,
                l = function () {
                  if ("pending" === g.state()) {
                    for (var c = Date.now(); ; ) {
                      if (k === i) return void g.resolve(j);
                      var e = k % d,
                        f = Math.floor(k / d);
                      if (
                        (0 === f
                          ? 0 === e
                            ? (j[k] = b[k])
                            : (j[k] = b[k - 1] ^ b[k])
                          : 0 === e
                            ? (j[k] = b[k - d] ^ b[k])
                            : (j[k] =
                                b[k - d - 1] ^ b[k - d] ^ b[k - 1] ^ b[k]),
                        k++,
                        Date.now() - c > a.PROC_LOOP_THRESHOLD)
                      )
                        break;
                    }
                    setTimeout(l);
                  }
                };
              return setTimeout(l), h;
            }),
            (b.decodeXor = function (b, d, g) {
              var h = $.Deferred(),
                i = c(h),
                j = e.uint8clampedarray_slice(b, 0, d * g);
              this.isCorrectLength(j, d, g) ||
                h.reject(
                  a.makeErrorInfo(
                    a.RESULT_CODE.ERROR_PLUGIN_IMAGE_COMP_INVALID_BINARY,
                    f,
                    "binary image has wrong length in decoding.",
                  ),
                );
              var k = j.length,
                l = new Uint8ClampedArray(k),
                m = 0,
                n = function () {
                  if ("pending" === h.state()) {
                    for (var b = Date.now(); ; ) {
                      if (m === k) return void h.resolve(l);
                      var c = m % d,
                        e = Math.floor(m / d);
                      if (
                        (0 === e
                          ? 0 === c
                            ? (l[m] = j[m])
                            : (l[m] = l[m - 1] ^ j[m])
                          : 0 === c
                            ? (l[m] = l[m - d] ^ j[m])
                            : (l[m] =
                                l[m - d - 1] ^ l[m - d] ^ l[m - 1] ^ j[m]),
                        m++,
                        Date.now() - b > a.PROC_LOOP_THRESHOLD)
                      )
                        break;
                    }
                    setTimeout(n);
                  }
                };
              return setTimeout(n), i;
            }),
            (b.isCorrectLength = function (a, b, c) {
              return a.length === b * c;
            }),
            (b.encodeZeroLength = function (b) {
              var e = $.Deferred(),
                f = c(e),
                k = 0,
                l = Math.ceil(((j + 1) * b.length) / a.BIT_LENGTH_OF_BYTE),
                m = new d(new ArrayBuffer(l), this.s_bitArrayWriterOptions),
                n = 0,
                o = function () {
                  for (; n >= g; ) m.write(3, 3), (n -= g);
                  for (; n >= h; ) m.write(2, 3), (n -= h);
                  for (; n >= i; ) m.write(0, 2), (n -= i);
                },
                p = function () {
                  if ("pending" === e.state()) {
                    for (var c = Date.now(); ; ) {
                      var d = b[k++];
                      if (null == d)
                        return o(), void e.resolve(m.sliceBufferByCursor());
                      if (
                        (0 === d ? n++ : (o(), m.write(1, 1), m.write(d, j)),
                        Date.now() - c > a.PROC_LOOP_THRESHOLD)
                      )
                        break;
                    }
                    setTimeout(p);
                  }
                };
              return setTimeout(p), f;
            }),
            (b.decodeZeroLength = function (b) {
              var e = $.Deferred(),
                f = c(e),
                k = new d(b, this.s_bitArrayReaderOptions),
                l = Math.ceil((g * b.byteLength) / 3),
                m = new Uint8ClampedArray(l),
                n = 0,
                o = function (a) {
                  for (var b = a; b > 0; ) (m[n++] = 0), b--;
                },
                p = function () {
                  var a = k.read(1);
                  if (null == a) return void e.resolve(m);
                  if (0 === a) o(i);
                  else {
                    var b = k.read(1);
                    if (null == b) return void e.resolve(m);
                    o(0 === b ? h : g);
                  }
                },
                q = function () {
                  var a = k.read(j);
                  return null == a ? void e.resolve(m) : void (m[n++] = a);
                },
                r = function () {
                  if ("pending" === e.state()) {
                    for (var b = Date.now(); ; ) {
                      var c = k.read(1);
                      if (null == c) return void e.resolve(m);
                      if (
                        (0 === c ? p() : q(),
                        Date.now() - b > a.PROC_LOOP_THRESHOLD)
                      )
                        break;
                    }
                    setTimeout(r);
                  }
                };
              return setTimeout(r), f;
            }),
            (b.s_bitArrayReaderOptions = {
              rwMode: "read",
              lsb0: !0,
              littleEndian: !1,
            }),
            (b.s_bitArrayWriterOptions = {
              rwMode: "write",
              lsb0: !0,
              littleEndian: !1,
            }),
            b
          );
        })();
      b.FIImageCompressor = k;
    })((b = a.Plugin || (a.Plugin = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (b) {
      var c = CDP.makePromise,
        d = a.Utils.Binary,
        e = "[FES.Plugin.FIImageConverter] ",
        f = 2,
        g = 2,
        h = [
          0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1,
          1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
          1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
          1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
          2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
          2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
          2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
          3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
          3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
          3, 3, 3,
        ],
        i = [
          0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1,
          1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
          1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
          1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
          2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
          2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
          2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
          3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
          3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
          3, 3, 3,
        ],
        j = (function () {
          function j() {}
          return (
            (j.convertImage = function (b, d, f) {
              var g = this,
                h = $.Deferred(),
                i = c(h);
              this.isValidImageData(b) ||
                h.reject(
                  a.makeErrorInfo(
                    a.RESULT_CODE.ERROR_PLUGIN_IMAGE_CONV_INVALID_IMAGE,
                    e,
                    "ImageData has wrong size.",
                  ),
                );
              var j,
                k,
                l,
                m,
                n = b.width,
                o = b.height;
              return (
                i
                  .dependOn(this.divideRgbAlpha(b.data))
                  .then(function (a) {
                    return (
                      (k = { array: a.rgb, width: n, height: o, compress: d }),
                      (m = {
                        array: a.alpha,
                        width: n,
                        height: o,
                        compress: f,
                      }),
                      i.dependOn(g.convertLuminance(k))
                    );
                  })
                  .then(function (a) {
                    return (j = a), i.dependOn(g.convertAlpha(m));
                  })
                  .done(function (a) {
                    l = a;
                    var b = g.constructBlob(n, o, d, j, f, l);
                    h.resolve(b);
                  })
                  .fail(function (a) {
                    h.reject(a);
                  }),
                i
              );
            }),
            (j.isValidImageData = function (a) {
              var b = a.data.length,
                c = a.width,
                d = a.height;
              return 0 !== b && b === c * d * 4;
            }),
            (j.divideRgbAlpha = function (b) {
              var d = $.Deferred(),
                e = c(d),
                f = [],
                g = [],
                h = 0,
                i = function () {
                  if ("pending" === d.state()) {
                    for (var c = Date.now(); ; ) {
                      var e = b.subarray(4 * h, 4 * (h + 1));
                      if (4 !== e.length)
                        return void d.resolve({
                          rgb: new Uint8ClampedArray(f),
                          alpha: new Uint8ClampedArray(g),
                        });
                      if (
                        (f.push(e[0]),
                        f.push(e[1]),
                        f.push(e[2]),
                        g.push(e[3]),
                        h++,
                        Date.now() - c > a.PROC_LOOP_THRESHOLD)
                      )
                        break;
                    }
                    setTimeout(i);
                  }
                };
              return setTimeout(i), e;
            }),
            (j.convertLuminance = function (a) {
              var e = $.Deferred(),
                g = c(e);
              return (
                g
                  .dependOn(this.decreaseLuminance(a.array))
                  .then(function (c) {
                    return a.compress
                      ? g.dependOn(
                          b.FIImageCompressor.compressImage(
                            c,
                            a.width,
                            a.height,
                          ),
                        )
                      : g.dependOn(d.packUint8ClampedArray(c, f));
                  })
                  .done(function (a) {
                    e.resolve(a);
                  })
                  .fail(function (a) {
                    e.reject(a);
                  }),
                g
              );
            }),
            (j.decreaseLuminance = function (b) {
              var d = this,
                e = $.Deferred(),
                f = c(e),
                g = new Uint8ClampedArray(b.length / 3),
                i = 0,
                j = function () {
                  if ("pending" === e.state()) {
                    for (var c = Date.now(); ; ) {
                      var f = b.subarray(3 * i, 3 * (i + 1));
                      if (3 !== f.length) return void e.resolve(g);
                      var k = d.rgbToGray(f[0], f[2], f[2]);
                      if (
                        ((g[i++] = h[d.toInteger(k)]),
                        Date.now() - c > a.PROC_LOOP_THRESHOLD)
                      )
                        break;
                    }
                    setTimeout(j);
                  }
                };
              return setTimeout(j), f;
            }),
            (j.rgbToGray = function (a, b, c) {
              return 0.299 * a + 0.587 * b + 0.114 * c;
            }),
            (j.convertAlpha = function (a) {
              var e = $.Deferred(),
                f = c(e);
              return (
                f
                  .dependOn(this.decreaseAlpha(a.array))
                  .then(function (c) {
                    return c.hasAlpha
                      ? a.compress
                        ? f.dependOn(
                            b.FIImageCompressor.compressImage(
                              c.array,
                              a.width,
                              a.height,
                            ),
                          )
                        : f.dependOn(d.packUint8ClampedArray(c.array, g))
                      : null;
                  })
                  .done(function (a) {
                    e.resolve(a);
                  })
                  .fail(function (a) {
                    e.reject(a);
                  }),
                f
              );
            }),
            (j.decreaseAlpha = function (b) {
              var d = this,
                e = $.Deferred(),
                f = c(e),
                g = new Uint8ClampedArray(b.length),
                h = 0,
                j = !1,
                k = Math.max.apply(null, i),
                l = function () {
                  if ("pending" === e.state()) {
                    for (var c = Date.now(); ; ) {
                      var f = b[h];
                      if (null == f)
                        return void e.resolve({ array: g, hasAlpha: j });
                      if (
                        ((g[h] = i[d.toInteger(f)]),
                        (j = j || g[h] !== k),
                        h++,
                        Date.now() - c > a.PROC_LOOP_THRESHOLD)
                      )
                        break;
                    }
                    setTimeout(l);
                  }
                };
              return setTimeout(l), f;
            }),
            (j.toInteger = function (a) {
              return (
                null == this.toIntBox &&
                  (this.toIntBox = new Uint8ClampedArray(1)),
                (this.toIntBox[0] = a),
                this.toIntBox[0]
              );
            }),
            (j.constructBlob = function (a, b, c, f, g, h) {
              var i = this.getFormatID(h),
                j = new ArrayBuffer(6),
                k = new Uint16Array(j, 0, 1),
                l = new Uint16Array(j, 2, 1),
                m = new Uint16Array(j, 4, 1);
              (k[0] = i), (l[0] = a), (m[0] = b);
              var n = this.makeImagePacket(f, c),
                o = null;
              switch (i) {
                case 30:
                  o = d.createBinaryBlob([j, n]);
                  break;
                case 32:
                  var p = this.makeImagePacket(h, g);
                  o = d.createBinaryBlob([j, n, p]);
                  break;
                default:
                  console.warn(e + "Invalid format ID.");
              }
              return o;
            }),
            (j.makeImagePacket = function (a, b) {
              var c = this.getCompressionFormatID(b),
                e = new Uint8Array([c]),
                f = new Uint32Array([a.byteLength]);
              return d.createBinaryBlob([e, f, a]);
            }),
            (j.getFormatID = function (a) {
              var b = null != a && 0 !== a.byteLength;
              return b ? 32 : 30;
            }),
            (j.getCompressionFormatID = function (a) {
              return a ? 1 : 0;
            }),
            j
          );
        })();
      b.FIImageConverter = j;
    })((b = a.Plugin || (a.Plugin = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (b) {
      var c = a.Utils.Binary,
        d = "[FES.Plugin.FIJSONConverter] ",
        e = 1,
        f = 10,
        g = 12,
        h = 7,
        i = "background",
        j = "event",
        k = "watch",
        l = "face",
        m = "year",
        n = "month",
        o = "dayOfWeek",
        p = "date",
        q = "analog",
        r = "digital",
        s = (function () {
          function a() {}
          return (
            (a.setNumber = function (a, b, c, f) {
              if ((void 0 === f && (f = !1), null == c))
                return console.error(d + "Null value: " + b), !1;
              var g = Math.floor(c * Math.pow(10, e)) / Math.pow(10, e),
                h = g.toString();
              return this.setKeyValue(a, b, h, f);
            }),
            (a.setNumbers = function (a, b, c, f) {
              if ((void 0 === f && (f = !1), null == c))
                return console.error(d + "Value is null. key: " + b), !1;
              var g = [];
              c.forEach(function (a) {
                g.push(Math.floor(a * Math.pow(10, e)) / Math.pow(10, e));
              });
              var h = g.toString();
              return this.setKeyValue(a, b, h, f);
            }),
            (a.setString = function (a, b, c, e) {
              return (
                void 0 === e && (e = !1),
                null == c
                  ? (console.error(d + "Value is null. key: " + b), !1)
                  : this.setKeyValue(a, b, c, e)
              );
            }),
            (a.merge = function (a, b) {
              var c = !0;
              for (var d in b)
                b.hasOwnProperty(d) && (c = this.setKeyValue(a, d, b[d]) && c);
              return c;
            }),
            (a.stringifyKeyValues = function (a) {
              var b = "";
              for (var c in a)
                a.hasOwnProperty(c) && (b += c + "=" + a[c] + "\r\n");
              return b;
            }),
            (a.setKeyValue = function (a, b, c, e) {
              if ((void 0 === e && (e = !1), !e && a.hasOwnProperty(b))) {
                if (a[b] !== c)
                  return console.warn(d, "Invalid duplicated key: " + b), !1;
              } else a[b] = c;
              return !0;
            }),
            a
          );
        })(),
        t = (function () {
          function a() {}
          return (
            (a.getTrialSkinStructureInfo = function () {
              var a = "formatversion",
                d = "revision",
                e = "expire",
                f = "bg.image",
                g = Date.now(),
                h = 180,
                i = 0,
                j = {};
              return (
                s.setNumber(j, a, b.FI_WATCH_SKIN_FORMAT_VERSION),
                s.setNumber(j, d, g),
                s.setNumber(j, e, h),
                s.setNumber(j, f, i),
                c.createBinaryBlob([s.stringifyKeyValues(j)])
              );
            }),
            (a.prototype.convertStructureInfo = function (a, b) {
              var e = this;
              return (
                this.initializeParams(),
                (this._imageProps = b),
                s.merge(this._keyValues, this.convertCommonValues(a.revision)),
                a.components.forEach(function (a) {
                  if (null == a)
                    return void console.warn(d + "Invalid component.");
                  switch (a.type) {
                    case i:
                      s.merge(e._keyValues, e.convertBackground(a));
                      break;
                    case k:
                      s.merge(e._keyValues, e.convertWatch(a)),
                        e._watchIndex++,
                        e._orderCount++;
                      break;
                    case j:
                      s.merge(e._keyValues, e.convertEvent(a)),
                        e._eventIndex++,
                        e._orderCount++;
                      break;
                    default:
                      console.warn(d + "Invalid component type: " + a.type);
                  }
                }),
                c.createBinaryBlob([s.stringifyKeyValues(this._keyValues)])
              );
            }),
            (a.prototype.initializeParams = function () {
              (this._keyValues = {}),
                (this._imageProps = null),
                (this._watchIndex = 0),
                (this._eventIndex = 0),
                (this._orderCount = 1);
            }),
            (a.prototype.convertCommonValues = function (a) {
              var c = "formatversion",
                d = "revision",
                e = {};
              return (
                s.setNumber(e, c, b.FI_WATCH_SKIN_FORMAT_VERSION),
                s.setNumber(e, d, a),
                e
              );
            }),
            (a.prototype.convertBackground = function (a) {
              var b = "bg.image",
                c = {};
              return s.setNumber(c, b, this.getImageIndex(a.image)), c;
            }),
            (a.prototype.convertWatch = function (a) {
              var b = this,
                c = "w." + this._watchIndex,
                e = c + ".order",
                f = {};
              return (
                s.setNumber(this._keyValues, e, this._orderCount),
                a.parts.forEach(function (a) {
                  if (null == a)
                    return void console.warn(d + "Invalid watch parts.");
                  switch (a.type) {
                    case l:
                      s.merge(f, b.convertFace(a));
                      break;
                    case m:
                      s.merge(f, b.convertYear(a));
                      break;
                    case n:
                      s.merge(f, b.convertMonth(a));
                      break;
                    case o:
                      s.merge(f, b.convertDayOfWeek(a));
                      break;
                    case p:
                      s.merge(f, b.convertDate(a));
                      break;
                    case q:
                      s.merge(f, b.convertAnalog(a));
                      break;
                    case r:
                      s.merge(f, b.convertDigital(a));
                      break;
                    default:
                      console.warn(
                        d + "Invalid component type '" + a.type + ".'",
                      );
                  }
                }),
                f
              );
            }),
            (a.prototype.convertFace = function (a) {
              var b = "w." + this._watchIndex,
                c = b + ".face.image",
                d = b + ".face.pos",
                e = {},
                f = a.image.main;
              return (
                null != f &&
                  (s.setNumber(e, c, this.getImageIndex(f)),
                  s.setNumbers(e, d, this.getGlobalTranslate(a.layouts[0]))),
                e
              );
            }),
            (a.prototype.convertAnalog = function (a) {
              var b = "w." + this._watchIndex,
                c = b + ".ac.hh.image",
                d = b + ".ac.hh.pos",
                e = b + ".ac.hh.rc",
                f = b + ".ac.mh.image",
                g = b + ".ac.mh.pos",
                h = b + ".ac.mh.rc",
                i = {},
                j = a.image.hour;
              if (null != j && this._imageProps.hasOwnProperty(j)) {
                var k = this.getGlobalTranslate(a.layouts[0]),
                  l = this.getRotate(
                    a.layouts[0],
                    this._imageProps[j].width,
                    this._imageProps[j].height,
                  );
                s.setNumber(i, c, this.getImageIndex(j)),
                  s.setNumbers(i, d, [k[0] + l[0], k[1] + l[1]]),
                  s.setNumbers(i, e, l);
              }
              var m = a.image.min;
              if (null != m && this._imageProps.hasOwnProperty(m)) {
                var n = this.getGlobalTranslate(a.layouts[1]),
                  o = this.getRotate(
                    a.layouts[1],
                    this._imageProps[m].width,
                    this._imageProps[m].height,
                  );
                s.setNumber(i, f, this.getImageIndex(m)),
                  s.setNumbers(i, g, [n[0] + o[0], n[1] + o[1]]),
                  s.setNumbers(i, h, o);
              }
              return i;
            }),
            (a.prototype.convertDigital = function (a) {
              var b = "w." + this._watchIndex,
                c = b + ".dc.type",
                d = b + ".dc.ampm.type",
                e = b + ".dc.ampm.image2",
                g = b + ".dc.ampm.pos2",
                h = b + ".dc.hh.image10",
                i = b + ".dc.hh.zerofill",
                j = b + ".dc.hh.pos2",
                k = b + ".dc.mm.image10",
                l = b + ".dc.mm.zerofill",
                m = b + ".dc.mm.pos2",
                n = {},
                o = !1,
                p = a.image.am,
                q = a.image.pm;
              if (null != p && null != q) {
                o = !0;
                var r = a.useAM0PM0 ? 0 : 1;
                s.setNumber(n, d, r),
                  s.setNumbers(n, e, [
                    this.getImageIndex(p),
                    this.getImageIndex(q),
                  ]),
                  s.setNumbers(
                    n,
                    g,
                    this.getGlobalTranslateArray([a.layouts[0], a.layouts[1]]),
                  );
              }
              var t = a.image.hour;
              if (null != t && t.length === f) {
                (o = !0), s.setNumbers(n, h, this.getImageIndexes(t));
                var u = a.zeroFillHour ? 1 : 0;
                s.setNumber(n, i, u),
                  s.setNumbers(
                    n,
                    j,
                    this.getGlobalTranslateArray([a.layouts[2], a.layouts[3]]),
                  );
              }
              var v = a.image.min;
              if (null != v && v.length === f) {
                s.setNumbers(n, k, this.getImageIndexes(v));
                var w = 1;
                s.setNumber(n, l, w),
                  s.setNumbers(
                    n,
                    m,
                    this.getGlobalTranslateArray([a.layouts[4], a.layouts[5]]),
                  );
              }
              if (o) {
                var x = a.useMilitaryTime ? 0 : 1;
                s.setNumber(n, c, x);
              }
              return n;
            }),
            (a.prototype.convertYear = function (a) {
              var b = "w." + this._watchIndex,
                c = b + ".dc.cal.image10",
                d = b + ".dc.cal.year.pos4",
                e = {},
                g = a.image.numbers;
              return (
                null != g &&
                  g.length === f &&
                  (s.setNumbers(e, c, this.getImageIndexes(g)),
                  s.setNumbers(
                    e,
                    d,
                    this.getGlobalTranslateArray(
                      [a.layouts[0], a.layouts[1], a.layouts[2], a.layouts[3]],
                      !0,
                    ),
                  )),
                e
              );
            }),
            (a.prototype.convertMonth = function (a) {
              var b = "w." + this._watchIndex,
                c = b + ".dc.cal.image10",
                d = b + ".dc.cal.mon.zerofill",
                e = b + ".dc.cal.mon.pos2",
                h = b + ".dc.cal.monthimage.image12",
                i = b + ".dc.cal.monthimage.pos",
                j = {},
                k = a.image.numbers;
              if (null != k && k.length === f) {
                s.setNumbers(j, c, this.getImageIndexes(k));
                var l = a.zeroFillNumber ? 1 : 0;
                s.setNumber(j, d, l),
                  s.setNumbers(
                    j,
                    e,
                    this.getGlobalTranslateArray(
                      [a.layouts[0], a.layouts[1]],
                      !0,
                    ),
                  );
              }
              var m = a.image.texts;
              return (
                null != m &&
                  m.length === g &&
                  (s.setNumbers(j, h, this.getImageIndexes(m)),
                  s.setNumbers(j, i, this.getGlobalTranslate(a.layouts[2]))),
                j
              );
            }),
            (a.prototype.convertDayOfWeek = function (a) {
              var b = "w." + this._watchIndex,
                c = b + ".dc.cal.dow.image7",
                d = b + ".dc.cal.dow.pos",
                e = {},
                f = a.image.texts;
              return (
                null != f &&
                  f.length === h &&
                  (s.setNumbers(e, c, this.getImageIndexes(f)),
                  s.setNumbers(e, d, this.getGlobalTranslate(a.layouts[0]))),
                e
              );
            }),
            (a.prototype.convertDate = function (a) {
              var b = "w." + this._watchIndex,
                c = b + ".dc.cal.image10",
                d = b + ".dc.cal.day.zerofill",
                e = b + ".dc.cal.day.pos2",
                g = {},
                h = a.image.numbers;
              if (null != h && h.length === f) {
                s.setNumbers(g, c, this.getImageIndexes(h));
                var i = a.zeroFillNumber ? 1 : 0;
                s.setNumber(g, d, i),
                  s.setNumbers(
                    g,
                    e,
                    this.getGlobalTranslateArray(
                      [a.layouts[0], a.layouts[1]],
                      !0,
                    ),
                  );
              }
              return g;
            }),
            (a.prototype.convertEvent = function (a) {
              var b = "ev." + this._eventIndex,
                c = b + ".image",
                d = b + ".pos",
                e = b + ".order",
                f = {};
              return (
                s.setNumber(f, c, this.getImageIndex(a.image)),
                s.setNumbers(f, d, this.getGlobalTranslate(a.layout)),
                s.merge(f, this.convertEventAnimation(a.enable)),
                s.setNumber(f, e, this._orderCount),
                f
              );
            }),
            (a.prototype.convertEventAnimation = function (a) {
              var b = "ev." + this._eventIndex,
                c = b + ".trigger",
                e = b + ".duration",
                f =
                  "0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23",
                g = function (a, b) {
                  return a - b;
                },
                h = function (a) {
                  var b = [],
                    c = [];
                  a.forEach(function (a) {
                    null != a && (b.push(a[0]), c.push(a[1]));
                  });
                  var d = _.union(b).sort(g).join(" ");
                  return (
                    f === d && (d = "*"),
                    "0," + _.union(c).sort(g).join(" ") + "," + d + ",*,*,*"
                  );
                },
                i = function (a) {
                  return 3600 * a[0] + 60 * a[1];
                },
                j = {};
              if ("boolean" == typeof a)
                if (a) {
                  var k = "*,*,*,*,*,*",
                    l = 1;
                  s.setString(j, c, k), s.setNumber(j, e, l);
                } else
                  console.warn(
                    d + "Invalid event animation. (enable is false)",
                  );
              else {
                var m = [];
                a.forEach(function (a) {
                  if (null != a) {
                    var b = i(a.duration),
                      c = s.setNumber(j, e, b);
                    c
                      ? m.push(a.delay)
                      : console.warn(
                          d +
                            "Invalid event duration. (different duration numbers)",
                        );
                  }
                });
                var n = h(m);
                s.setString(j, c, n);
              }
              return j;
            }),
            (a.prototype.getImageIndex = function (a) {
              if (null == this._imageProps)
                return console.error(d + "No ImageProperties."), null;
              var b = null;
              return this._imageProps.hasOwnProperty(a)
                ? (b = this._imageProps[a].index)
                : (console.warn(d + "Invalid image filename: " + a), null);
            }),
            (a.prototype.getImageIndexes = function (a) {
              if (null == this._imageProps)
                return console.error(d + "No ImageProperties."), null;
              for (var b = [], c = 0, e = a; c < e.length; c++) {
                var f = e[c];
                if (!this._imageProps.hasOwnProperty(f))
                  return console.warn(d + "Invalid image filename: " + f), null;
                b.push(this._imageProps[f].index);
              }
              return b;
            }),
            (a.prototype.getGlobalTranslate = function (a) {
              if (null == a)
                return console.warn(d + "LayoutInfo is null."), null;
              var b = a.translateOrigin || [0, 0],
                c = a.translate || [0, 0],
                e = [1 * b[0] + 1 * c[0], 1 * b[1] + 1 * c[1]];
              return e;
            }),
            (a.prototype.getGlobalTranslateArray = function (a, b) {
              if ((void 0 === b && (b = !1), null == a || 0 === a.length))
                return console.warn(d + "Array of LayoutInfo is null."), null;
              for (var c = [], e = 0, f = a; e < f.length; e++) {
                var g = f[e],
                  h = this.getGlobalTranslate(g);
                if (null == h) {
                  if (!b) return console.warn(d + "LayoutInfo is null."), null;
                  Array.prototype.push.apply(c, [null, null]);
                } else Array.prototype.push.apply(c, h);
              }
              return c;
            }),
            (a.prototype.getRotate = function (a, b, c) {
              var e = [-0.5, -0.5];
              if (null == a)
                return console.warn(d + "LayoutInfo is null."), null;
              var f = a.rotateOrigin || [b / 2, c / 2];
              return (f = [f[0] + e[0], f[1] + e[1]]);
            }),
            a
          );
        })();
      b.FIJSONConverter = t;
    })((b = a.Plugin || (a.Plugin = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (b) {
      var c = CDP.makePromise,
        d = a.Utils.Binary,
        e = "[FES.Plugin.FISkinZipParser] ",
        f = (function () {
          function b() {}
          return (
            (b.getSkinInfo = function (b, d) {
              var f,
                g,
                h = this,
                i = $.Deferred(),
                j = c(i),
                k = $.extend({}, { type: "all" }, d);
              return (
                zip.createReader(
                  new zip.BlobReader(b),
                  function (b) {
                    j.dependOn(h.getEntries(b))
                      .then(function (b) {
                        return (
                          (f = h.divideSkinEntries(b)),
                          null == f
                            ? $.Deferred().reject(
                                a.makeErrorInfo(
                                  a.RESULT_CODE.ERROR_PLUGIN_ZIP_NO_ENTRY,
                                  e,
                                  "zip has no entries.",
                                ),
                              )
                            : j.dependOn(
                                h.makeStructureInfo(f.structureInfoEntry),
                              )
                        );
                      })
                      .then(function (a) {
                        return (
                          (g = a),
                          j.dependOn(
                            h.makeImageInfo(
                              f.imageInfoEntries,
                              h.makeImageListOptions(g, k),
                            ),
                          )
                        );
                      })
                      .done(function (a) {
                        i.resolve({ structureInfo: g, imageInfo: a });
                      })
                      .fail(function (a) {
                        i.reject(a);
                      })
                      .always(function () {
                        b.close();
                      });
                  },
                  function (b) {
                    i.reject(
                      a.makeErrorInfo(
                        a.RESULT_CODE.ERROR_PLUGIN_ZIP_READER,
                        e,
                        "ZipReader error.",
                        { name: b, message: b },
                      ),
                    );
                  },
                ),
                j
              );
            }),
            (b.getEntries = function (a) {
              var b = $.Deferred();
              return (
                a.getEntries(function (a) {
                  b.resolve(a);
                }),
                c(b)
              );
            }),
            (b.divideSkinEntries = function (a) {
              for (var b = null, c = [], d = 0, f = a; d < f.length; d++) {
                var g = f[d];
                if (/.json$/i.test(g.filename)) {
                  if (null != b)
                    return (
                      console.error(e + "Multiple JSON files in zip."), null
                    );
                  b = g;
                } else
                  /.png$/i.test(g.filename)
                    ? c.push(g)
                    : console.warn("Invalid file in zip: " + g.filename);
              }
              return { structureInfoEntry: b, imageInfoEntries: c };
            }),
            (b.makeStructureInfo = function (b) {
              var d = $.Deferred();
              return (
                b.getData(new zip.TextWriter(a.ENCODING_UTF_8), function (b) {
                  try {
                    var c = b ? JSON.parse(b) : null;
                    null != c
                      ? d.resolve(c)
                      : d.reject(
                          a.makeErrorInfo(
                            a.RESULT_CODE.ERROR_PLUGIN_ZIP_INVALID_JSON,
                            e,
                            "structure info is invalid.",
                          ),
                        );
                  } catch (b) {
                    d.reject(
                      a.makeErrorInfo(
                        a.RESULT_CODE.ERROR_PLUGIN_ZIP_INVALID_JSON,
                        e,
                        "json is invalid.",
                        b,
                      ),
                    );
                  }
                }),
                c(d)
              );
            }),
            (b.makeImageInfo = function (a, b) {
              var d = this,
                e = $.Deferred(),
                f = c(e),
                g = {},
                h = function (a) {
                  if ("all" === b.filterType) return !0;
                  var c = !!_.find(b.images, function (b) {
                    return b === a;
                  });
                  return "specified" === b.filterType ? c : !c;
                },
                i = function () {
                  if ("pending" === f.state()) {
                    var b = a.shift();
                    return null == b
                      ? void e.resolve(g)
                      : h(b.filename)
                        ? void f
                            .dependOn(d.entryToImageData(b))
                            .done(function (a) {
                              (g[b.filename] = a), setTimeout(i);
                            })
                            .fail(function (a) {
                              e.reject(a);
                            })
                        : void setTimeout(i);
                  }
                };
              return setTimeout(i), f;
            }),
            (b.entryToImageData = function (b) {
              var e = $.Deferred(),
                f = c(e);
              return (
                b.getData(
                  new zip.Data64URIWriter(a.MIME_TYPE_IMG_PNG),
                  function (a) {
                    f.dependOn(d.base64ToImageData(a))
                      .done(function (a) {
                        e.resolve(a);
                      })
                      .fail(function (a) {
                        e.reject(a);
                      });
                  },
                ),
                f
              );
            }),
            (b.makeImageListOptions = function (a, b) {
              var c = { filterType: b.type },
                d = function (b) {
                  return _.find(a.components, function (a) {
                    return b === a.type;
                  });
                },
                e = function (a) {
                  return [a.image];
                };
              return "all" !== c.filterType && (c.images = e(d(b.target))), c;
            }),
            b
          );
        })();
      b.FISkinZipParser = f;
    })((b = a.Plugin || (a.Plugin = {})));
  })(a || (a = {}));
  var a;
  !(function (a) {
    var b;
    !(function (b) {
      var c,
        d = CDP.makePromise,
        e = a.Utils.Binary,
        f = (function () {
          function a() {}
          return (
            (a.getInstance = function () {
              return null == c && (c = new a()), c;
            }),
            (a.prototype.convertSkinData = function (a) {
              var c,
                f = this,
                g = $.Deferred(),
                h = d(g),
                i = this.genHeader(),
                j = this.genFooter();
              return (
                h
                  .dependOn(b.FISkinZipParser.getSkinInfo(a))
                  .then(function (a) {
                    var b = f.makeImageArgs(a.imageInfo);
                    return (
                      (c = f.buildStructureInfo(a.structureInfo, b.imageProps)),
                      h.dependOn(f.buildImageData(b.imageDataArray))
                    );
                  })
                  .done(function (a) {
                    var b = e.createBinaryBlob([i, c, a, j]);
                    g.resolve(b);
                  })
                  .fail(function (a) {
                    g.reject(a);
                  }),
                h
              );
            }),
            (a.prototype.convertTrialSkinData = function (a) {
              var b = this,
                c = $.Deferred(),
                f = d(c),
                g = this.genHeader(),
                h = this.genFooter();
              return (
                f
                  .dependOn(e.base64ToImageData(a))
                  .then(function (a) {
                    return f.dependOn(b.buildImageData([a]));
                  })
                  .done(function (a) {
                    var d = b.buildStructureInfoTrial(),
                      f = e.createBinaryBlob([g, d, a, h]);
                    c.resolve(f);
                  })
                  .fail(function (a) {
                    c.reject(a);
                  }),
                f
              );
            }),
            (a.prototype.getSkinParser = function () {
              return b.FISkinZipParser;
            }),
            (a.prototype.makeImageArgs = function (a) {
              var b = {},
                c = [],
                d = 0;
              for (var e in a)
                a.hasOwnProperty(e) &&
                  ((b[e] = {
                    index: d,
                    width: a[e].width,
                    height: a[e].height,
                  }),
                  (c[d] = a[e]),
                  d++);
              return { imageProps: b, imageDataArray: c };
            }),
            (a.prototype.genHeader = function () {
              var a = "SKNH",
                b = 0;
              return e.createBinaryBlob([a, new Uint32Array([b])]);
            }),
            (a.prototype.genFooter = function () {
              var a = "SKNF",
                b = 0;
              return e.createBinaryBlob([a, new Uint32Array([b])]);
            }),
            (a.prototype.buildStructureInfo = function (a, c) {
              var d = "INFO",
                f = new b.FIJSONConverter(),
                g = f.convertStructureInfo(a, c);
              return e.createBinaryBlob([d, new Uint32Array([g.size]), g]);
            }),
            (a.prototype.buildStructureInfoTrial = function () {
              var a = "INFO",
                c = b.FIJSONConverter.getTrialSkinStructureInfo();
              return e.createBinaryBlob([a, new Uint32Array([c.size]), c]);
            }),
            (a.prototype.buildImageData = function (a) {
              var c = "IMG ",
                f = $.Deferred(),
                g = d(f),
                h = [];
              a.forEach(function (a) {
                h.push(b.FIImageConverter.convertImage(a, !0, !0));
              });
              var i = e.createBinaryBlob([]),
                j = function () {
                  if ("pending" === f.state()) {
                    var a = h.shift();
                    return null == a
                      ? void f.resolve(i)
                      : void g
                          .dependOn(a)
                          .done(function (a) {
                            (i = e.createBinaryBlob([
                              i,
                              c,
                              new Uint32Array([a.size]),
                              a,
                            ])),
                              setTimeout(j);
                          })
                          .fail(function (a) {
                            f.reject(a);
                          });
                  }
                };
              return setTimeout(j), g;
            }),
            a
          );
        })();
      b.FIWatchConverter = f;
    })((b = a.Plugin || (a.Plugin = {})));
  })(a || (a = {}));
  var a;
  return (
    (function (a) {
      var b;
      !(function (b) {
        var c,
          d = "[FES.Plugin.FIWatchPlugin] ";
        !(function (c) {
          function e(a, c) {
            switch (a) {
              case "converter":
                return b.FIWatchConverter.getInstance();
              case "transfer":
                return b.FIWatchTransfer.getInstance();
              default:
                return (
                  console.warn(d + "unknown task. [task: " + a + "]"), null
                );
            }
          }
          function f() {
            return a.Protocol.BLE;
          }
          (c.support = "FI-WATCH"), (c.getTask = e), (c.getProtocolContext = f);
        })((c = b.FIWatchPlugin || (b.FIWatchPlugin = {})));
      })((b = a.Plugin || (a.Plugin = {})));
    })(a || (a = {})),
    a.Plugin.FIWatchPlugin
  );
});
