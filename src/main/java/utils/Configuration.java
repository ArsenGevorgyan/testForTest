package utils;

import java.util.Optional;

public class Configuration {
    private static final String DEFAULT_BROWSER = "chrome";
    private static final String DEFAULT_BASIC_AUTH_ENABLED = "false";
    private static final String DEFAULT_BROWSER_VERSION = "latest";
    private static final String DEFAULT_SITE_URL = "https://qa.smclk.net/";
    private static final String DEFAULT_SAUCE_URL = "http://%s:%s@ondemand.saucelabs.com:80/wd/hub";
    private static final String DEFAULT_GRID_URL = "http://localhost:4444/wd/hub";
    private static final String DEFAULT_IMPLICIT_WAIT = "20";
    private static final String DEFAULT_EXPLICIT_WAIT = "20";
    private static final String DEFAULT_IDLE_TIMEOUT = "125";
    private static final String DEFAULT_MAX_DELAY_TIME = "15";
    private static final String DEFAULT_LOCAL_RUN = "true";
    private static final String DEFAULT_SAUCE_RUN = "false";


    private String username;
    private String accessKey;
    private Integer implicitlyWait;
    private Integer explicitWaitTime;
    private Integer idleTimeout;
    private Integer maxDelayTime;
    private String browser;
    private Boolean basicAuthEnabled;
    private String basicAuthUserName;
    private String basicAuthUserPass;
    private String browserVersion;
    private String platform;
    private Boolean isLocal;
    private Boolean useSauceLabs;
    private String sauceLabsURL;
    private String gridURL;
    private String siteUrl;
    private String build;

    @Override
    public String toString() {
        return "Configuration{" +
                "username='" + username + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", implicitlyWait=" + implicitlyWait +
                ", explicitWaitTime=" + explicitWaitTime +
                ", idleTimeout=" + idleTimeout +
                ", maxDelayTime=" + maxDelayTime +
                ", browser='" + browser + '\'' +
                ", browserVersion='" + browserVersion + '\'' +
                ", basicAuthEnabled='" + basicAuthEnabled + '\'' +
                ", basicAuthUserName='" + basicAuthUserName + '\'' +
                ", basicAuthUserPass='" + basicAuthUserPass + '\'' +
                ", isLocal='" + isLocal + '\'' +
                ", useSauceLabs='" + useSauceLabs + '\'' +
                ", sauceLabsURL='" + sauceLabsURL + '\'' +
                ", gridURL='" + gridURL + '\'' +
                ", platform='" + platform + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                ", build='" + build + '\'' +
                '}';
    }

    public static Configuration instance() {
        final Configuration configuration = new Configuration();

        configuration.setImplicitlyWait(Integer
                .parseInt(Optional
                        .ofNullable(System.getProperty("implicit.wait"))
                        .orElse(DEFAULT_IMPLICIT_WAIT)));
        configuration.setExplicitWaitTime(Integer
                .parseInt(Optional
                        .ofNullable(System.getProperty("explicit.wait"))
                        .orElse(DEFAULT_EXPLICIT_WAIT)));
        configuration.setIdleTimeout(Integer
                .parseInt(Optional
                        .ofNullable(System.getProperty("idle.timeout"))
                        .orElse(DEFAULT_IDLE_TIMEOUT)));
        configuration.setMaxDelayTime(Integer
                .parseInt(Optional
                        .ofNullable(System.getProperty("max.delay.time"))
                        .orElse(DEFAULT_MAX_DELAY_TIME)));
        configuration.setIsLocal(Boolean
                .parseBoolean(Optional
                        .ofNullable(System.getProperty("local.run"))
                        .orElse(DEFAULT_LOCAL_RUN)));
        configuration.setUseSauceLabs(Boolean
                .parseBoolean(Optional
                        .ofNullable(System.getProperty("sauce.run"))
                        .orElse(DEFAULT_SAUCE_RUN)));
        configuration.setSauceLabsURL(Optional
                .ofNullable(System.getProperty("sauce.url"))
                .orElse(DEFAULT_SAUCE_URL));
        configuration.setGridURL(Optional
                .ofNullable(System.getProperty("grid.url"))
                .orElse(DEFAULT_GRID_URL));
        configuration.setBrowser(Optional
                .ofNullable(System.getProperty("browser"))
                .orElse(DEFAULT_BROWSER));
        configuration.setBasicAuthEnabled(Boolean
                .parseBoolean(Optional
                        .ofNullable(System.getProperty("basic.auth.enabled"))
                        .orElse(DEFAULT_BASIC_AUTH_ENABLED)));
        configuration.setBasicAuthUserName(Optional
                .ofNullable(System.getProperty("basic.auth.user.name"))
                .orElse(null));
        configuration.setBasicAuthUserPass(Optional
                .ofNullable(System.getProperty("basic.auth.user.pass"))
                .orElse(null));
        configuration.setBrowserVersion(Optional
                .ofNullable(System.getProperty("browser.version"))
                .orElse(DEFAULT_BROWSER_VERSION));
        configuration.setSiteUrl(Optional
                .ofNullable(System.getProperty("site.url"))
                .orElse(DEFAULT_SITE_URL));
        configuration.setPlatform(Optional
                .ofNullable(System.getenv("browser.platform"))
                .orElse(System.getProperty("os.name")));
        configuration.setBuild(Optional
                .ofNullable(System.getProperty("sauce.bamboo.build.number"))
                .orElse("na"));
        configuration.setUsername(Optional
                .ofNullable(System.getProperty("sauce.user.name"))
                .orElse(null));
        configuration.setAccessKey(Optional
                .ofNullable(System.getProperty("sauce.access.key"))
                .orElse(null));
        Log.info("C: Active configuration is; " + configuration.toString());
        return configuration;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public Integer getImplicitlyWait() {
        return implicitlyWait;
    }

    private void setImplicitlyWait(Integer implicitlyWait) {
        this.implicitlyWait = implicitlyWait;
    }

    public Integer getIdleTimeout() {
        return idleTimeout;
    }

    private void setIdleTimeout(Integer idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public String getBrowser() {
        return browser;
    }

    private void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    private void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getPlatform() {
        return platform;
    }

    private void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getMaxDelayTime() {
        return maxDelayTime;
    }

    private void setMaxDelayTime(Integer maxDelayTime) {
        this.maxDelayTime = maxDelayTime;
    }

    public Boolean getIsLocal() {
        return isLocal;
    }

    private void setIsLocal(boolean isLocal) {
        this.isLocal = isLocal;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    private void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getBuild() {
        return build;
    }

    private void setBuild(String build) {
        this.build = build;
    }

    private void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    private void setExplicitWaitTime(Integer explicitWaitTime) {
        this.explicitWaitTime = explicitWaitTime;
    }

    public Integer getExplicitWaitTime() {
        return explicitWaitTime;
    }

    public void setUseSauceLabs(Boolean useSauceLabs) {
        this.useSauceLabs = useSauceLabs;
    }

    public Boolean getUseSauceLabs() {
        return useSauceLabs;
    }

    public void setSauceLabsURL(String sauceLabsURL) {
        this.sauceLabsURL = sauceLabsURL;
    }

    public String getSauceLabsURL() {
        return sauceLabsURL;
    }

    public void setGridURL(String gridURL) {
        this.gridURL = gridURL;
    }

    public String getGridURL() {
        return gridURL;
    }

    public Boolean getBasicAuthEnabled() {
        return basicAuthEnabled;
    }

    public void setBasicAuthEnabled(Boolean basicAuthEnabled) {
        this.basicAuthEnabled = basicAuthEnabled;
    }

    public String getBasicAuthUserName() {
        return basicAuthUserName;
    }

    public void setBasicAuthUserName(String basicAuthUserName) {
        this.basicAuthUserName = basicAuthUserName;
    }

    public String getBasicAuthUserPass() {
        return basicAuthUserPass;
    }

    public void setBasicAuthUserPass(String basicAuthUserPass) {
        this.basicAuthUserPass = basicAuthUserPass;
    }
}
