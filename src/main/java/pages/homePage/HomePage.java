package pages.homePage;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basePage.BasePage;

import java.util.List;

public class HomePage extends BasePage {

    public String strPageTitle = "My Store";
    public String strPageURL = "";


    @FindBy(id = "header_logo")
    public WebElement myStoreLogo;

    @FindBy(className = "right-block")
    public WebElement rightBlockElement;

    @FindBy(xpath = "//ul[@id = 'homefeatured']/li/div/div[@class='right-block']/div/span[@class='price-percent-reduction']")

    public List <WebElement> salesPercentElement;

    @FindBy(xpath = "//ul[@id = 'homefeatured']/li[contains(@class, 'hovered')]/div/div[@class='right-block']/div/a[@title = 'Add to cart']/span[text()='Add to cart']")
    public WebElement addToCartButton;


    @FindBy(xpath = "//*[@title = 'Continue shopping']")
    public WebElement continueShoppingButton;

    @FindBy(className = "layer_cart_product")
    public WebElement productCartArea;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    public WebElement viewShoppingCart;

    @FindBy(xpath = "//a[@title='Check out']")
    public WebElement checkOutButton;



    @Step
    public void moveOnSalesPercentageElement(int index){
        waitElementToBePresent(salesPercentElement.get(index), 10);
        moveOnElement(salesPercentElement.get(index));
    }

    @Step
    public void moveOnShoppingCartArea(){
        waitElementToBePresent(viewShoppingCart, 10);
        moveOnElement(viewShoppingCart);
    }

    @Step
    public void clickOnCheckOutButton(){
        waitElementToBePresent(checkOutButton, 10);
        waitElementToBeClickable(checkOutButton);
        clickOnElement(checkOutButton);
    }
    @Step
    public void clickOnAddToCartButton(){
        waitElementToBePresent(addToCartButton, 10);
        waitElementToBeClickable(addToCartButton);
        clickOnElement(addToCartButton);
    }
    @Step
    public void clickOnContinueShoppingButton(){
        waitElementToBePresent(continueShoppingButton, 10);
        waitElementToBeClickable(continueShoppingButton);
        clickOnElement(continueShoppingButton);
    }
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    @Override
    protected void load() {

        open(strPageURL, true);

    }

    @Step
    @Override
    public void isLoaded() throws Error {
        checkPageTitle(strPageTitle);
     //   checkPageURL(strPageURL);
        waitElementToBeVisible(myStoreLogo);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
