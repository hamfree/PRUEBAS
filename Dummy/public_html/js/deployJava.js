var version_regex_base = "^(\\d+)(?:\\.(\\d+)(?:\\.(\\d+)(?:[_\\.](\\d+))?)?)?";
var version_regex_strict = version_regex_base + "$";
var version_regex_with_family_modifier = version_regex_base + "(\\*|\\+)?$";
var deployJava = function () {
    var l = {
        core: ["id", "class", "title", "style"],
        i18n: ["lang", "dir"],
        events: ["onclick", "ondblclick", "onmousedown", "onmouseup", "onmouseover", "onmousemove", "onmouseout", "onkeypress",
            "onkeydown", "onkeyup"],
        applet: ["codebase", "code", "name", "archive", "object", "width", "height", "alt", "align", "hspace", "vspace"],
        object: ["classid", "codebase", "codetype", "data", "type", "archive", "declare", "standby", "height", "width", "usemap",
            "name", "tabindex", "align", "border", "hspace", "vspace"]
    };
    var v = l.object.concat(l.core, l.i18n, l.events);
    var j = l.applet.concat(l.core);
    if (typeof String.prototype.startsWith !== "function") {
        String.prototype.startsWith = function (x, w) {
            w = w || 0;
            return this.indexOf(x, w) === w;
        };
    }
    function k(w) {
        if (!i.debug) {
            return;
        }
        if (console.log) {
            console.log(w);
        } else {
            alert(w);
        }
    }
    function h() {
        var y = 'Java Plug-in is not supported by this browser. <a href="https://java.com/dt-redirect">More info</a>';
        var x = "background-color: #ffffce;text-align: left;border: solid 1px #f0c000; padding: 1.65em 1.65em .75em 0.5em; " +
                "font-family: Helvetica, Arial, sans-serif; font-size: 75%; bottom:0; left:0; right:0; position:fixed; " +
                "margin:auto; opacity:0.9; width:400px;";
        var B = "border: .85px; margin:-2.2em 0 0.55em 2.5em;";
        var w = "margin-left:10px;font-weight:bold;float:right;font-size:22px;line-height:20px;cursor:pointer;color:red;";
        var z = '<span style="' + w +
                '" onclick="this.parentElement.style.display=\'none\';">&times;</span><img src="https://java.com/js/alert_16.png">' +
                '<div style="' + B + '"><p>' + y + "</p>";
        var A = document.createElement("div");
        A.id = "messagebox";
        A.setAttribute("style", x);
        A.innerHTML = z;
        document.body.appendChild(A);
    }
    function o(x, w) {
        if (x === null || x.length === 0) {
            return true;
        }
        var A = x.charAt(x.length - 1);
        if (A !== "+" && A !== "*" && (x.indexOf("_") !== -1 && A !== "_")) {
            x = x + "*";
            A = "*";
        }
        x = x.substring(0, x.length - 1);
        if (x.length > 0) {
            var y = x.charAt(x.length - 1);
            if (y === "." || y === "_") {
                x = x.substring(0, x.length - 1);
            }
        }
        if (A === "*") {
            return(w.indexOf(x) === 0);
        } else {
            if (A === "+") {
                return x <= w;
            }
        }
        return false;
    }
    function a(y, x) {
        var D = 0;
        var F = y.match(version_regex_with_family_modifier);
        if (F !== null) {
            if (x && b()) {
                return true;
            }
            var A = false;
            var C = false;
            var z = new Array();
            for (var B = 1; B < F.length; ++B) {
                if ((typeof F[B] === "string") && (F[B] !== "")) {
                    z[D] = F[B];
                    D++;
                }
            }
            if (z[z.length - 1] === "+") {
                C = true;
                A = false;
                z.length--;
            } else {
                if (z[z.length - 1] === "*") {
                    C = false;
                    A = true;
                    z.length--;
                } else {
                    if (z.length < 4) {
                        C = false;
                        A = true;
                    }
                }
            }
            var E = deployJava.getJREs();
            for (var B = 0; B < E.length; ++B) {
                if (deployJava.compareVersionToPattern(E[B], z, A, C)) {
                    return true;
                }
            }
            return false;
        } else {
            var w = "Invalid versionPattern passed to versionCheck: " + y;
            k("[versionCheck()] " + w);
            alert(w);
            return false;
        }
    }
    function t() {
        return a("1.7.0+", false);
    }
    function c(w) {
        var y = ["http://", "https://", "file://"];
        for (var x = 0; x < y.length; x++) {
            if (w.toLowerCase().startsWith(y[x])) {
                return true;
            }
        }
        return false;
    }
    function r(y) {
        var z;
        if (c(y)) {
            z = y;
        } else {
            var w = window.location.href;
            var A = w.lastIndexOf("/");
            var x = A > -1 ? w.substring(0, A + 1) : w + "/";
            z = x + y;
        }
        return z;
    }
    function e(w) {
        document.location = "jnlp:" + r(w);
    }
    function b() {
        var w = deployJava.getBrowser();
        if (w === "Edge" || deployJava.browserName2 === "Chrome" || deployJava.browserName2 === "NoActiveX") {
            return true;
        }
        return false;
    }
    function q() {
        var w = "//java.com/js/webstart.png";
        try {
            return document.location.protocol.indexOf("http") !== -1 ? w : "https:" + w;
        } catch (x) {
            return"https:" + w;
        }
    }
    function d(x) {
        var w = "https://java.com/dt-redirect";
        if (x === null || x.length === 0) {
            return w;
        }
        if (x.charAt(0) === "&") {
            x = x.substring(1, x.length);
        }
        return w + "?" + x;
    }
    function f(y, x) {
        var w = y.length;
        for (var z = 0; z < w; z++) {
            if (y[z] === x) {
                return true;
            }
        }
        return false;
    }
    function u(w) {
        return f(j, w.toLowerCase());
    }
    function p(w) {
        return f(v, w.toLowerCase());
    }
    function n(w) {
        if ("MSIE" !== deployJava.browserName) {
            return true;
        }
        if (deployJava.compareVersionToPattern(deployJava.getPlugin().version, ["10", "0", "0"], false, true)) {
            return true;
        }
        if (w === null) {
            return false;
        }
        return !o("1.6.0_33+", w);
    }
    var i = {
        debug: null,
        version: "20120801",
        firefoxJavaVersion: null,
        useStaticMimeType: false,
        myInterval: null,
        preInstallJREList: null,
        returnPage: null,
        brand: null,
        locale: null,
        installType: null,
        EAInstallEnabled: false,
        EarlyAccessURL: null,
        oldMimeType: "application/npruntime-scriptable-plugin;DeploymentToolkit",
        mimeType: "application/java-deployment-toolkit",
        launchButtonPNG: q(),
        browserName: null,
        browserName2: null,
        getJREs: function () {
            var A = new Array();
            if (this.isPluginInstalled()) {
                var z = this.getPlugin();
                var w = z.jvms;
                for (var y = 0; y < w.getLength(); y++) {
                    A[y] = w.get(y).version;
                }
            } else {
                var x = this.getBrowser();
                if (x === "MSIE") {
                    if (this.testUsingActiveX("9")) {
                        A[0] = "9";
                    } else {
                        if (this.testUsingActiveX("1.8.0")) {
                            A[0] = "1.8.0";
                        } else {
                            if (this.testUsingActiveX("1.7.0")) {
                                A[0] = "1.7.0";
                            } else {
                                if (this.testUsingActiveX("1.6.0")) {
                                    A[0] = "1.6.0";
                                } else {
                                    if (this.testUsingActiveX("1.5.0")) {
                                        A[0] = "1.5.0";
                                    } else {
                                        if (this.testUsingActiveX("1.4.2")) {
                                            A[0] = "1.4.2";
                                        } else {
                                            if (this.testForMSVM()) {
                                                A[0] = "1.1";
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (x === "Netscape Family") {
                        this.getJPIVersionUsingMimeType();
                        if (this.firefoxJavaVersion !== null) {
                            A[0] = this.firefoxJavaVersion;
                        } else {
                            if (this.testUsingMimeTypes("9")) {
                                A[0] = "9";
                            } else {
                                if (this.testUsingMimeTypes("1.8")) {
                                    A[0] = "1.8.0";
                                } else {
                                    if (this.testUsingMimeTypes("1.7")) {
                                        A[0] = "1.7.0";
                                    } else {
                                        if (this.testUsingMimeTypes("1.6")) {
                                            A[0] = "1.6.0";
                                        } else {
                                            if (this.testUsingMimeTypes("1.5")) {
                                                A[0] = "1.5.0";
                                            } else {
                                                if (this.testUsingMimeTypes("1.4.2")) {
                                                    A[0] = "1.4.2";
                                                } else {
                                                    if (this.browserName2 === "Safari") {
                                                        if (this.testUsingPluginsArray("9")) {
                                                            A[0] = "9";
                                                        } else {
                                                            if (this.testUsingPluginsArray("1.8")) {
                                                                A[0] = "1.8.0";
                                                            } else {
                                                                if (this.testUsingPluginsArray("1.7")) {
                                                                    A[0] = "1.7.0";
                                                                } else {
                                                                    if (this.testUsingPluginsArray("1.6")) {
                                                                        A[0] = "1.6.0";
                                                                    } else {
                                                                        if (this.testUsingPluginsArray("1.5")) {
                                                                            A[0] = "1.5.0";
                                                                        } else {
                                                                            if (this.testUsingPluginsArray("1.4.2")) {
                                                                                A[0] = "1.4.2";
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (this.debug) {
                for (var y = 0; y < A.length; ++y) {
                    k("[getJREs()] We claim to have detected Java SE " + A[y]);
                }
            }
            return A;
        }, installJRE: function (x) {
            k("The Deployment Toolkit installJRE()  method no longer installs JRE. It just checks if the requested version of JRE " +
                    "is installed and calls installLatestJRE() otherwise. More Information on usage of the Deployment Toolkit can " +
                    "be found in the Deployment Guide at https://docs.oracle.com/javase/8/docs/technotes/guides/deploy/");
            if (x === "undefined" || x === null) {
                x = "1.1";
            }
            var w = x.match(version_regex_with_family_modifier);
            if (w === null) {
                k("Invalid requestVersion argument to installJRE(): " + x);
                x = "1.6";
            }
            if (!this.versionCheck(x)) {
                return this.installLatestJRE();
            }
            return true;
        }, isAutoInstallEnabled: function (w) {
            if (!this.isPluginInstalled()) {
                return false;
            }
            if (typeof w === "undefined") {
                w = null;
            }
            return n(w);
        }, isCallbackSupported: function () {
            return this.isPluginInstalled() && this.compareVersionToPattern(this.getPlugin().version, ["10", "2", "0"], false, true);
        }, installLatestJRE: function () {
            k("The Deployment Toolkit installLatestJRE() method no longer installs JRE. If user's version of Java is below the " +
                    "security baseline it redirects user to java.com to get an updated JRE. More Information on usage of the " +
                    "Deployment Toolkit can be found in the Deployment Guide at " +
                    "://docs.oracle.com/javase/8/docs/technotes/guides/deploy/");
            if (!this.isPluginInstalled() || !this.getPlugin().installLatestJRE()) {
                var x = this.getBrowser();
                var w = navigator.platform.toLowerCase();
                if (x === "MSIE") {
                    return this.IEInstall();
                } else {
                    if ((x === "Netscape Family") && (w.indexOf("win32") !== -1)) {
                        return this.FFInstall();
                    } else {
                        location.href = d(((this.returnPage !== null) ? ("&returnPage=" + this.returnPage) : "") +
                                ((this.locale !== null) ? ("&locale=" + this.locale) : "") + ((this.brand !== null) ? ("&brand=" + this.brand) : ""));
                    }
                }
                return false;
            }
            return true;
        }, runApplet: function (x, A, z) {
            if (z === "undefined" || z === null) {
                z = "1.1";
            }
            var w = z.match(version_regex_strict);
            if (this.returnPage === null) {
                this.returnPage = document.location;
            }
            if (w !== null) {
                var y = this.getBrowser();
                if (y !== "?") {
                    if (b()) {
                        var B = setInterval(function () {
                            if (document.readyState === "complete") {
                                clearInterval(B);
                                h();
                            }
                        }, 15);
                        k("[runApplet()] Java Plug-in is not supported by this browser");
                        return;
                    }
                    if (this.versionCheck(z + "+")) {
                        this.writeAppletTag(x, A);
                    } else {
                        if (this.installJRE(z + "+")) {
                            this.writeAppletTag(x, A);
                        }
                    }
                } else {
                    this.writeAppletTag(x, A);
                }
            } else {
                k("[runApplet()] Invalid minimumVersion argument to runApplet():" + z);
            }
        }, writeAppletTag: function (z, D) {
            var w = "<applet ";
            var y = "";
            var A = "</applet>";
            var E = true;
            if (null === D || typeof D !== "object") {
                D = new Object();
            }
            for (var x in z) {
                if (!u(x)) {
                    D[x] = z[x];
                } else {
                    w += (" " + x + '="' + z[x] + '"');
                    if (x === "code") {
                        E = false;
                    }
                }
            }
            var C = false;
            for (var B in D) {
                if (B === "codebase_lookup") {
                    C = true;
                }
                if (B === "object" || B === "java_object" || B === "java_code") {
                    E = false;
                }
                y += '<param name="' + B + '" value="' + D[B] + '"/>';
            }
            if (!C) {
                y += '<param name="codebase_lookup" value="false"/>';
            }
            if (E) {
                w += (' code="dummy"');
            }
            w += ">";
            document.write(w + "\n" + y + "\n" + A);
        }, versionCheck: function (w) {
            return a(w, b());
        }, isWebStartInstalled: function (z) {
            if (b()) {
                return true;
            }
            var y = this.getBrowser();
            if (y === "?") {
                return true;
            }
            if (z === "undefined" || z === null) {
                z = "1.4.2";
            }
            var x = false;
            var w = z.match(version_regex_strict);
            if (w !== null) {
                x = this.versionCheck(z + "+");
            } else {
                k("[isWebStartInstaller()] Invalid minimumVersion argument to isWebStartInstalled(): " + z);
                x = this.versionCheck("1.4.2+");
            }
            return x;
        }, getJPIVersionUsingMimeType: function () {
            for (var x = 0; x < navigator.mimeTypes.length; ++x) {
                var y = navigator.mimeTypes[x].type;
                var w = y.match(/^application\/x-java-applet;jpi-version=(.*)$/);
                if (w !== null) {
                    this.firefoxJavaVersion = w[1];
                    this.useStaticMimeType = true;
                    return;
                }
            }
            for (var x = 0; x < navigator.mimeTypes.length; ++x) {
                var y = navigator.mimeTypes[x].type;
                var w = y.match(/^application\/x-java-applet;version=(.*)$/);
                if (w !== null) {
                    this.firefoxJavaVersion = w[1];
                }
            }
        }, launchWebStartApplication: function (z) {
            var w = navigator.userAgent.toLowerCase();
            this.getJPIVersionUsingMimeType();
            if (t() === false) {
                if (b()) {
                    e(z);
                } else {
                    if ((this.installJRE("1.7.0+") === false) || ((this.isWebStartInstalled("1.7.0") === false))) {
                        return false;
                    }
                }
            }
            var B = null;
            if (document.documentURI) {
                B = document.documentURI;
            }
            if (B === null) {
                B = document.URL;
            }
            var x = this.getBrowser();
            var y;
            if (x === "MSIE") {
                y = '<object classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" width="0" height="0">' +
                        '<PARAM name="launchjnlp" value="' +
                        z + '"><PARAM name="docbase" value="' + encodeURIComponent(B) + '"></object>';
            } else {
                if (x === "Netscape Family") {
                    y = '<embed type="' + (this.useStaticMimeType ? "application/x-java-applet;jpi-version=" :
                            "application/x-java-applet;version=") +
                            this.firefoxJavaVersion + '" width="0" height="0" launchjnlp="' + z + '"docbase="' +
                            encodeURIComponent(B) + '" />';
                }
            }
            if (document.body === "undefined" || document.body === null) {
                document.write(y);
                document.location = B;
            } else {
                var A = document.createElement("div");
                A.id = "div1";
                A.style.position = "relative";
                A.style.left = "-10000px";
                A.style.margin = "0px auto";
                A.className = "dynamicDiv";
                A.innerHTML = y;
                document.body.appendChild(A);
            }
        }, createWebStartLaunchButtonEx: function (y, x) {
            if (this.returnPage === null) {
                this.returnPage = y;
            }
            var w = "javascript:deployJava.launchWebStartApplication('" + y + "');";
            document.write('<a href="' + w + '" onMouseOver="window.status=\'\'; return true;"><img src="' +
                    this.launchButtonPNG + '" border="0" /></a>');
        }, createWebStartLaunchButton: function (y, x) {
            if (this.returnPage === null) {
                this.returnPage = y;
            }
            var w = "javascript:if (!deployJava.isWebStartInstalled(&quot;" + x +
                    "&quot;)) {if (deployJava.installLatestJRE()) {if (deployJava.launch(&quot;" + y +
                    "&quot;)) {}}} else {if (deployJava.launch(&quot;" + y + "&quot;)) {}}";
            document.write('<a href="' + w + '" onMouseOver="window.status=\'\'; return true;"><img src="' +
                    this.launchButtonPNG + '" border="0" /></a>');
        }, launch: function (w) {
            document.location = w;
            return true;
        }, launchEx: function (w) {
            e(w);
            return true;
        }, isPluginInstalled: function () {
            var w = this.getPlugin();
            if (w && w.jvms) {
                return true;
            } else {
                return false;
            }
        }, isAutoUpdateEnabled: function () {
            if (this.isPluginInstalled()) {
                return this.getPlugin().isAutoUpdateEnabled();
            }
            return false;
        }, setAutoUpdateEnabled: function () {
            if (this.isPluginInstalled()) {
                return this.getPlugin().setAutoUpdateEnabled();
            }
            return false;
        }, setInstallerType: function (w) {
            k("The Deployment Toolkit no longer installs JRE. Method setInstallerType() is no-op. More Information on usage of the " +
                    "Deployment Toolkit can be found in the Deployment Guide at " +
                    "://docs.oracle.com/javase/8/docs/technotes/guides/deploy/");
            return false;
        }, setAdditionalPackages: function (w) {
            k("The Deployment Toolkit no longer installs JRE. Method setAdditionalPackages() is no-op. More Information on usage " +
                    "of the Deployment Toolkit can be found in the Deployment Guide at " +
                    "://docs.oracle.com/javase/8/docs/technotes/guides/deploy/");
            return false;
        }, setEarlyAccess: function (w) {
            this.EAInstallEnabled = w;
        }, isPlugin2: function () {
            if (this.isPluginInstalled()) {
                if (this.versionCheck("1.6.0_10+")) {
                    try {
                        return this.getPlugin().isPlugin2();
                    } catch (w) {
                    }
                }
            }
            return false;
        }, allowPlugin: function () {
            this.getBrowser();
            var w = ("Safari" !== this.browserName2 && "Opera" !== this.browserName2);
            return w;
        }, getPlugin: function () {
            this.refresh();
            var w = null;
            if (this.allowPlugin()) {
                w = document.getElementById("deployJavaPlugin");
            }
            return w;
        }, compareVersionToPattern: function (E, y, z, B) {
            if (E === undefined || y === undefined) {
                return false;
            }
            var F = E.match(version_regex_strict);
            if (F !== null) {
                var C = 0;
                var G = new Array();
                for (var A = 1; A < F.length; ++A) {
                    if ((typeof F[A] === "string") && (F[A] !== "")) {
                        G[C] = F[A];
                        C++;
                    }
                }
                var x = Math.min(G.length, y.length);
                if (B) {
                    for (var A = 0; A < x; ++A) {
                        var D = parseInt(G[A]);
                        var w = parseInt(y[A]);
                        if (D < w) {
                            return false;
                        } else {
                            if (D > w) {
                                return true;
                            }
                        }
                    }
                    return true;
                } else {
                    for (var A = 0; A < x; ++A) {
                        if (G[A] !== y[A]) {
                            return false;
                        }
                    }
                    if (z) {
                        return true;
                    } else {
                        return(G.length === y.length);
                    }
                }
            } else {
                return false;
            }
        }, getBrowser: function () {
            if (this.browserName === null) {
                var w = navigator.userAgent.toLowerCase();
                k("[getBrowser()] navigator.userAgent.toLowerCase() -> " + w);
                if (w.indexOf("edge") !== -1) {
                    this.browserName = "Edge";
                    this.browserName2 = "Edge";
                } else {
                    if ((w.indexOf("msie") !== -1) && (w.indexOf("opera") === -1)) {
                        this.browserName = "MSIE";
                        this.browserName2 = "MSIE";
                    } else {
                        if (w.indexOf("trident") !== -1 || w.indexOf("Trident") !== -1) {
                            this.browserName = "MSIE";
                            this.browserName2 = "MSIE";
                            if (w.indexOf("windows nt 6.3") !== -1 || w.indexOf("windows nt 6.2") !== -1) {
                                try {
                                    new ActiveXObject("htmlfile");
                                } catch (x) {
                                    this.browserName2 = "NoActiveX";
                                }
                            }
                        } else {
                            if (w.indexOf("iphone") !== -1) {
                                this.browserName = "Netscape Family";
                                this.browserName2 = "iPhone";
                            } else {
                                if ((w.indexOf("firefox") !== -1) && (w.indexOf("opera") === -1)) {
                                    this.browserName = "Netscape Family";
                                    this.browserName2 = "Firefox";
                                } else {
                                    if (w.indexOf("chrome") !== -1) {
                                        this.browserName = "Netscape Family";
                                        this.browserName2 = "Chrome";
                                    } else {
                                        if (w.indexOf("safari") !== -1) {
                                            this.browserName = "Netscape Family";
                                            this.browserName2 = "Safari";
                                        } else {
                                            if ((w.indexOf("mozilla") !== -1) && (w.indexOf("opera") === -1)) {
                                                this.browserName = "Netscape Family";
                                                this.browserName2 = "Other";
                                            } else {
                                                if (w.indexOf("opera") !== -1) {
                                                    this.browserName = "Netscape Family";
                                                    this.browserName2 = "Opera";
                                                } else {
                                                    this.browserName = "?";
                                                    this.browserName2 = "unknown";
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                k("[getBrowser()] Detected browser name:" + this.browserName + ", " + this.browserName2);
            }
            return this.browserName;
        }, testUsingActiveX: function (w) {
            var y = "JavaWebStart.isInstalled." + w + ".0";
            if (typeof ActiveXObject === "undefined" || !ActiveXObject) {
                k("[testUsingActiveX()] Browser claims to be IE, but no ActiveXObject object?");
                return false;
            }
            try {
                return(new ActiveXObject(y) !== null);
            } catch (x) {
                return false;
            }
        }, testForMSVM: function () {
            var x = "{08B0E5C0-4FCB-11CF-AAA5-00401C608500}";
            if (typeof oClientCaps !== "undefined") {
                var w = oClientCaps.getComponentVersion(x, "ComponentID");
                if ((w === "") || (w === "5,0,5000,0")) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }, testUsingMimeTypes: function (x) {
            if (!navigator.mimeTypes) {
                k("[testUsingMimeTypes()] Browser claims to be Netscape family, but no mimeTypes[] array?");
                return false;
            }
            for (var y = 0; y < navigator.mimeTypes.length; ++y) {
                s = navigator.mimeTypes[y].type;
                var w = s.match(/^application\/x-java-applet\x3Bversion=(1\.8|1\.7|1\.6|1\.5|1\.4\.2)$/);
                if (w !== null) {
                    if (this.compareVersions(w[1], x)) {
                        return true;
                    }
                }
            }
            return false;
        }, testUsingPluginsArray: function (x) {
            if ((!navigator.plugins) || (!navigator.plugins.length)) {
                return false;
            }
            var w = navigator.platform.toLowerCase();
            for (var y = 0; y < navigator.plugins.length; ++y) {
                s = navigator.plugins[y].description;
                if (s.search(/^Java Switchable Plug-in (Cocoa)/) !== -1) {
                    if (this.compareVersions("1.5.0", x)) {
                        return true;
                    }
                } else {
                    if (s.search(/^Java/) !== -1) {
                        if (w.indexOf("win") !== -1) {
                            if (this.compareVersions("1.5.0", x) || this.compareVersions("1.6.0", x)) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (this.compareVersions("1.5.0", x)) {
                return true;
            }
            return false;
        }, IEInstall: function () {
            location.href = d(((this.returnPage !== null) ? ("&returnPage=" + this.returnPage) : "") + ((this.locale !== null) ?
                    ("&locale=" + this.locale) : "") + ((this.brand !== null) ? ("&brand=" + this.brand) : ""));
            return false;
        }, done: function (x, w) {}, FFInstall: function () {
            location.href = d(((this.returnPage !== null) ? ("&returnPage=" + this.returnPage) : "") + ((this.locale !== null) ?
                    ("&locale=" + this.locale) : "") + ((this.brand !== null) ? ("&brand=" + this.brand) : "") +
                    ((this.installType !== null) ? ("&type=" + this.installType) : ""));
            return false;
        }, compareVersions: function (z, A) {
            var x = z.split(".");
            var w = A.split(".");
            for (var y = 0; y < x.length; ++y) {
                x[y] = Number(x[y]);
            }
            for (var y = 0; y < w.length; ++y) {
                w[y] = Number(w[y]);
            }
            if (x.length === 2) {
                x[2] = 0;
            }
            if (x[0] > w[0]) {
                return true;
            }
            if (x[0] < w[0]) {
                return false;
            }
            if (x[1] > w[1]) {
                return true;
            }
            if (x[1] < w[1]) {
                return false;
            }
            if (x[2] > w[2]) {
                return true;
            }
            if (x[2] < w[2]) {
                return false;
            }
            return true;
        }, enableAlerts: function () {
            this.browserName = null;
            this.debug = true;
        }, poll: function () {
            this.refresh();
            var w = this.getJREs();
            if ((this.preInstallJREList.length === 0) && (w.length !== 0)) {
                clearInterval(this.myInterval);
                if (this.returnPage !== null) {
                    location.href = this.returnPage;
                }
            }
            if ((this.preInstallJREList.length !== 0) && (w.length !== 0) && (this.preInstallJREList[0] !== w[0])) {
                clearInterval(this.myInterval);
                if (this.returnPage !== null) {
                    location.href = this.returnPage;
                }
            }
        }, writePluginTag: function () {
            var w = this.getBrowser();
            if (w === "MSIE") {
                document.write('<object classid="clsid:CAFEEFAC-DEC7-0000-0001-ABCDEFFEDCBA" id="deployJavaPlugin" width="0" ' + 
                        'height="0"></object>');
            } else {
                if (w === "Netscape Family" && this.allowPlugin()) {
                    this.writeEmbedTag();
                }
            }
        }, refresh: function () {
            navigator.plugins.refresh(false);
            var w = this.getBrowser();
            if (w === "Netscape Family" && this.allowPlugin()) {
                var x = document.getElementById("deployJavaPlugin");
                if (x === null) {
                    this.writeEmbedTag();
                }
            }
        }, writeEmbedTag: function () {
            var w = false;
            if (navigator.mimeTypes !== null) {
                for (var x = 0; x < navigator.mimeTypes.length; x++) {
                    if (navigator.mimeTypes[x].type === this.mimeType) {
                        if (navigator.mimeTypes[x].enabledPlugin) {
                            document.write('<embed id="deployJavaPlugin" type="' + this.mimeType + '" hidden="true" />');
                            w = true;
                        }
                    }
                }
                if (!w) {
                    for (var x = 0; x < navigator.mimeTypes.length; x++) {
                        if (navigator.mimeTypes[x].type === this.oldMimeType) {
                            if (navigator.mimeTypes[x].enabledPlugin) {
                                document.write('<embed id="deployJavaPlugin" type="' + this.oldMimeType + '" hidden="true" />');
                            }
                        }
                    }
                }
            }
        }};
    i.writePluginTag();
    if (i.locale === null) {
        var m = null;
        if (m === null) {
            try {
                m = navigator.userLanguage;
            } catch (g) {
            }
        }
        if (m === null) {
            try {
                m = navigator.systemLanguage;
            } catch (g) {
            }
        }
        if (m === null) {
            try {
                m = navigator.language;
            } catch (g) {
            }
        }
        if (m !== null) {
            m.replace("-", "_");
            i.locale = m;
        }
    }
    return i;
}();