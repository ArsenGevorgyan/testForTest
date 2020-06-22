package factory.driver;

import exceptions.WebDriverFactoryException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Log;
import utils.Configuration;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    private static final String CHROME_NAME = "chrome";
    private static final String FIREFOX_NAME = "firefox";
    private static final String EDGE_NAME = "edge";
    private static final String IE_NAME = "internet explorer";
    private static final Configuration configuration = Configuration.instance();

    /**
     * Create Firefox web driver base on configuration and Edge options.
     * IMPORTANT: In configuration "browser" should be set to "firefox"
     *
     * @param firefoxOptions Firefox driver options
     * @return created WebDriver object
     */
    public static WebDriver createWebDriver(FirefoxOptions firefoxOptions) {
        if (configuration.getIsLocal()) {
            if (FIREFOX_NAME.equals(configuration.getBrowser())) {
                return createWebDriver(null, firefoxOptions, null, null);
            } else {
                throw new WebDriverFactoryException("\"browser\" property should be \"firefox\"");
            }
        }
        else {
            throw new WebDriverFactoryException("Creation of remote Firefox driver with FirefoxOptions is not supported");
        }
    }

    /**
     * Create Chrome web driver base on configuration and Edge options.
     * IMPORTANT: In configuration "browser" should be set to "chrome"
     *
     * @param chromeOptions Chrome driver options
     * @return created WebDriver object
     */
    public static WebDriver createWebDriver(ChromeOptions chromeOptions) {
        if (configuration.getIsLocal()) {
            if (FIREFOX_NAME.equals(configuration.getBrowser())) {
                return createWebDriver(chromeOptions, null, null, null);
            } else {
                throw new WebDriverFactoryException("\"browser\" property should be \"chrome\"");
            }
        } else {
            throw new WebDriverFactoryException("Creation of remote Chrome driver with FirefoxProfile is not supported");
        }
    }

    /**
     * Create IE web driver base on configuration and Edge options.
     * IMPORTANT: In configuration "browser" should be set to "internet explorer"
     *
     * @param internetExplorerOptions IE driver options
     * @return created WebDriver object
     */
    public static WebDriver createWebDriver(InternetExplorerOptions internetExplorerOptions) {
        if (configuration.getIsLocal()) {
            if (FIREFOX_NAME.equals(configuration.getBrowser())) {
                return createWebDriver(null, null, internetExplorerOptions, null);
            } else {
                throw new WebDriverFactoryException("\"browser\" property should be \"chrome\"");
            }
        } else {
            throw new WebDriverFactoryException("Creation of remote IE driver with FirefoxProfile is not supported");
        }
    }

    /**
     * Create Edge web driver base on configuration and Edge options.
     * IMPORTANT: In configuration "browser" should be set to "edge"
     *
     * @param edgeOptions Edge driver options
     * @return created WebDriver object
     */
    public static WebDriver createWebDriver(EdgeOptions edgeOptions) {
        if (configuration.getIsLocal()) {
            if (FIREFOX_NAME.equals(configuration.getBrowser())) {
                return createWebDriver(null, null, null, edgeOptions);
            } else {
                throw new WebDriverFactoryException("\"browser\" property should be \"chrome\"");
            }
        } else {
            throw new WebDriverFactoryException("Creation of remote Edge driver with FirefoxProfile is not supported");
        }
    }

    /**
     * Create web driver base on configuration only.
     *
     * @return created WebDriver object
     */
    public static WebDriver createWebDriver() {
        return createWebDriver(null, null, null, null);
    }

    /**
     * Create web driver base on configuration and provided options.
     * Works only for windows environments.
     *
     * @param chromeOptions Chrome driver options
     * @param firefoxOptions Firefox driver options
     * @param internetExplorerOptions IE driver options
     * @param edgeOptions Edge driver options
     * @return created WebDriver object
     */
    private static WebDriver createWebDriver(ChromeOptions chromeOptions,
                                             FirefoxOptions firefoxOptions,
                                             InternetExplorerOptions internetExplorerOptions,
                                             EdgeOptions edgeOptions) {
        final WebDriver driver;
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        if (configuration.getIsLocal()) {
            String usedBrowser = configuration.getBrowser();
            if (IE_NAME.equals(usedBrowser)) {
                System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/IEDriverServer32.exe");
                if (internetExplorerOptions == null) {
                    driver = new InternetExplorerDriver();
                } else {
                    driver = new InternetExplorerDriver(internetExplorerOptions);
                }
            } else if (EDGE_NAME.equals(usedBrowser)) {
                System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/MicrosoftWebDriver.exe");
                driver = new EdgeDriver();
            } else if (CHROME_NAME.equals(usedBrowser)) {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                if (chromeOptions == null) {
                    driver = new ChromeDriver();
                } else {
                    driver = new ChromeDriver(chromeOptions);
                }
            } else if (FIREFOX_NAME.equals(usedBrowser)) {
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
                if (firefoxOptions == null) {
                    driver = new FirefoxDriver();
                } else {
                    driver = new FirefoxDriver(firefoxOptions);
                }
            } else {
                Log.error("WDF: Browser name in configuration you specified is not supported");
                throw new WebDriverFactoryException("Browser name in configuration you specified is not supported.");
            }
            driver.manage().window().maximize();
        } else {
            if (configuration.getUseSauceLabs()) {
                final String sauceUrl = String.format(configuration.getSauceLabsURL(),
                        configuration.getUsername(), configuration.getAccessKey());
                try {
                    desiredCapabilities.setBrowserName(configuration.getBrowser());
                    desiredCapabilities.setVersion(configuration.getBrowserVersion());
                    desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME, configuration.getPlatform());
                    desiredCapabilities.setCapability("idleTimeout", configuration.getIdleTimeout());
                    desiredCapabilities.setCapability("build", configuration.getBuild());
                    desiredCapabilities.setCapability("screenResolution", "1920x1080");
                    driver = new RemoteWebDriver(new URL(sauceUrl), desiredCapabilities);
                } catch (Exception ex) {
                    Log.error("WDF: Problem connecting to SauceLabs");
                    throw new RuntimeException(ex);
                }
            } else {
                try {
                    desiredCapabilities.setBrowserName(configuration.getBrowser());
                    desiredCapabilities.setVersion(configuration.getBrowserVersion());
                    desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME, configuration.getPlatform());
                    desiredCapabilities.setCapability("idleTimeout", configuration.getIdleTimeout());
                    desiredCapabilities.setCapability("screenResolution", "1920x1080");
                    driver = new RemoteWebDriver(new URL(configuration.getGridURL()), desiredCapabilities);
                } catch (Exception ex) {
                    Log.error("WDF: Problem connecting to Selenium Grid");
                    throw new RuntimeException(ex);
                }
            }
        }

        driver.manage().timeouts().implicitlyWait(configuration.getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }
}
