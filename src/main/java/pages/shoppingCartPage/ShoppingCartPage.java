package pages.shoppingCartPage;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basePage.BasePage;

public class ShoppingCartPage extends BasePage {

    public String strPageTitle = "Order - My Store";

    @FindBy(id = "cart_title")
    public WebElement cartTitle;

    @FindBy(xpath = "//*[text() = 'Proceed to checkout']")
    public WebElement  proceedToCheckoutButton ;




    public ShoppingCartPage(WebDriver webDriver) {
        super(webDriver);
    }



    @Step
    public void clickOnProceedToCheckoutButton() {
        waitElementToBePresent(proceedToCheckoutButton, 10);
        waitElementToBeClickable(proceedToCheckoutButton);
        clickOnElement(proceedToCheckoutButton);
    }


    @Step
    @Override
    protected void load() {
    }

    @Step
    @Override
    public void isLoaded() throws Error {
        checkPageTitle(strPageTitle);
        checkIfElementIsVisible(cartTitle);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}