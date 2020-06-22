package pages.authenticationPage;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basePage.BasePage;

public class AuthenticationPage extends BasePage {

    private String strPageTitle = "Login - My Store";

    @FindBy(className = "page-heading")
    public WebElement authenticationPageTitle;

    public AuthenticationPage(WebDriver webDriver) {
        super(webDriver);
    }



    @Step
    @Override
    protected void load(){
    }

    @Step
    @Override
    public void isLoaded() throws Error {
        checkPageTitle(strPageTitle);
        checkIfElementIsVisible(authenticationPageTitle);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
