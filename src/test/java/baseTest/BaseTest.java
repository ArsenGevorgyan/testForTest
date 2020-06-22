package baseTest;

import factory.driver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.authenticationPage.AuthenticationPage;
import pages.homePage.HomePage;
import pages.shoppingCartPage.ShoppingCartPage;


public class BaseTest {

    public ThreadLocal<WebDriver> driver;

    public ShoppingCartPage shoppingCartPage;
    public AuthenticationPage authenticationPage;
    public HomePage homePage;

    public WebDriver webDriver() {
        return driver.get();
    }

    @BeforeClass(alwaysRun = true, description = "Before method")
    public void warmUp() {
        driver = new ThreadLocal<>();
        driver.set(WebDriverFactory.createWebDriver());

        // Pages initialisation
        shoppingCartPage = PageFactory.initElements(webDriver(), ShoppingCartPage.class);
        homePage = PageFactory.initElements(webDriver(), HomePage.class);
        authenticationPage = PageFactory.initElements(webDriver(), AuthenticationPage.class);

    }

    @AfterClass(alwaysRun = true, description = "After method")
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }
}
