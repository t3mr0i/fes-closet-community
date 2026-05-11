package org.apache.cordova;

import android.net.Uri;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class Whitelist {
    public static final String TAG = "Whitelist";
    private ArrayList<URLPattern> whiteList = new ArrayList<>();

    private static class URLPattern {
        public Pattern host;
        public Pattern path;
        public Integer port;
        public Pattern scheme;

        private String regexFromPattern(String pattern, boolean allowWildcards) {
            StringBuilder regex = new StringBuilder();
            for (int i = 0; i < pattern.length(); i++) {
                char c = pattern.charAt(i);
                if (c == '*' && allowWildcards) {
                    regex.append(".");
                } else if ("\\.[]{}()^$?+|".indexOf(c) > -1) {
                    regex.append('\\');
                }
                regex.append(c);
            }
            return regex.toString();
        }

        /* JADX WARN: Removed duplicated region for block: B:6:0x000d A[Catch: NumberFormatException -> 0x0043, TryCatch #0 {NumberFormatException -> 0x0043, blocks: (B:4:0x0005, B:19:0x0036, B:7:0x0010, B:9:0x0018, B:11:0x001d, B:28:0x0086, B:15:0x002a, B:29:0x0093, B:17:0x0032, B:13:0x0025, B:24:0x004c, B:26:0x0054, B:27:0x0079, B:6:0x000d), top: B:31:0x0005 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public URLPattern(java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) throws java.net.MalformedURLException {
            /*
                r4 = this;
                r4.<init>()
                if (r5 == 0) goto Ld
                java.lang.String r1 = "*"
                boolean r1 = r1.equals(r5)     // Catch: java.lang.NumberFormatException -> L43
                if (r1 == 0) goto L36
            Ld:
                r1 = 0
                r4.scheme = r1     // Catch: java.lang.NumberFormatException -> L43
            L10:
                java.lang.String r1 = "*"
                boolean r1 = r1.equals(r6)     // Catch: java.lang.NumberFormatException -> L43
                if (r1 == 0) goto L4c
                r1 = 0
                r4.host = r1     // Catch: java.lang.NumberFormatException -> L43
            L1b:
                if (r7 == 0) goto L25
                java.lang.String r1 = "*"
                boolean r1 = r1.equals(r7)     // Catch: java.lang.NumberFormatException -> L43
                if (r1 == 0) goto L86
            L25:
                r1 = 0
                r4.port = r1     // Catch: java.lang.NumberFormatException -> L43
            L28:
                if (r8 == 0) goto L32
                java.lang.String r1 = "/*"
                boolean r1 = r1.equals(r8)     // Catch: java.lang.NumberFormatException -> L43
                if (r1 == 0) goto L93
            L32:
                r1 = 0
                r4.path = r1     // Catch: java.lang.NumberFormatException -> L43
            L35:
                return
            L36:
                r1 = 0
                java.lang.String r1 = r4.regexFromPattern(r5, r1)     // Catch: java.lang.NumberFormatException -> L43
                r2 = 2
                java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1, r2)     // Catch: java.lang.NumberFormatException -> L43
                r4.scheme = r1     // Catch: java.lang.NumberFormatException -> L43
                goto L10
            L43:
                r0 = move-exception
                java.net.MalformedURLException r1 = new java.net.MalformedURLException
                java.lang.String r2 = "Port must be a number"
                r1.<init>(r2)
                throw r1
            L4c:
                java.lang.String r1 = "*."
                boolean r1 = r6.startsWith(r1)     // Catch: java.lang.NumberFormatException -> L43
                if (r1 == 0) goto L79
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.NumberFormatException -> L43
                r1.<init>()     // Catch: java.lang.NumberFormatException -> L43
                java.lang.String r2 = "([a-z0-9.-]*\\.)?"
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.NumberFormatException -> L43
                r2 = 2
                java.lang.String r2 = r6.substring(r2)     // Catch: java.lang.NumberFormatException -> L43
                r3 = 0
                java.lang.String r2 = r4.regexFromPattern(r2, r3)     // Catch: java.lang.NumberFormatException -> L43
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.NumberFormatException -> L43
                java.lang.String r1 = r1.toString()     // Catch: java.lang.NumberFormatException -> L43
                r2 = 2
                java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1, r2)     // Catch: java.lang.NumberFormatException -> L43
                r4.host = r1     // Catch: java.lang.NumberFormatException -> L43
                goto L1b
            L79:
                r1 = 0
                java.lang.String r1 = r4.regexFromPattern(r6, r1)     // Catch: java.lang.NumberFormatException -> L43
                r2 = 2
                java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1, r2)     // Catch: java.lang.NumberFormatException -> L43
                r4.host = r1     // Catch: java.lang.NumberFormatException -> L43
                goto L1b
            L86:
                r1 = 10
                int r1 = java.lang.Integer.parseInt(r7, r1)     // Catch: java.lang.NumberFormatException -> L43
                java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.NumberFormatException -> L43
                r4.port = r1     // Catch: java.lang.NumberFormatException -> L43
                goto L28
            L93:
                r1 = 1
                java.lang.String r1 = r4.regexFromPattern(r8, r1)     // Catch: java.lang.NumberFormatException -> L43
                java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1)     // Catch: java.lang.NumberFormatException -> L43
                r4.path = r1     // Catch: java.lang.NumberFormatException -> L43
                goto L35
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.Whitelist.URLPattern.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
        }

        public boolean matches(Uri uri) {
            try {
                if (this.scheme != null && !this.scheme.matcher(uri.getScheme()).matches()) {
                    return false;
                }
                if (this.host != null && !this.host.matcher(uri.getHost()).matches()) {
                    return false;
                }
                if (this.port != null && !this.port.equals(Integer.valueOf(uri.getPort()))) {
                    return false;
                }
                if (this.path != null) {
                    if (!this.path.matcher(uri.getPath()).matches()) {
                        return false;
                    }
                }
                return true;
            } catch (Exception e) {
                LOG.d(Whitelist.TAG, e.toString());
                return false;
            }
        }
    }

    public void addWhiteListEntry(String origin, boolean subdomains) {
        if (this.whiteList != null) {
            try {
                if (origin.compareTo("*") == 0) {
                    LOG.d(TAG, "Unlimited access to network resources");
                    this.whiteList = null;
                    return;
                }
                Pattern parts = Pattern.compile("^((\\*|[A-Za-z-]+):(//)?)?(\\*|((\\*\\.)?[^*/:]+))?(:(\\d+))?(/.*)?");
                Matcher m = parts.matcher(origin);
                if (m.matches()) {
                    String scheme = m.group(2);
                    String host = m.group(4);
                    if (("file".equals(scheme) || FirebaseAnalytics.Param.CONTENT.equals(scheme)) && host == null) {
                        host = "*";
                    }
                    String port = m.group(8);
                    String path = m.group(9);
                    if (scheme == null) {
                        this.whiteList.add(new URLPattern("http", host, port, path));
                        this.whiteList.add(new URLPattern("https", host, port, path));
                    } else {
                        this.whiteList.add(new URLPattern(scheme, host, port, path));
                    }
                }
            } catch (Exception e) {
                LOG.d(TAG, "Failed to add origin %s", origin);
            }
        }
    }

    public boolean isUrlWhiteListed(String uri) {
        if (this.whiteList == null) {
            return true;
        }
        Uri parsedUri = Uri.parse(uri);
        Iterator<URLPattern> pit = this.whiteList.iterator();
        while (pit.hasNext()) {
            URLPattern p = pit.next();
            if (p.matches(parsedUri)) {
                return true;
            }
        }
        return false;
    }
}
