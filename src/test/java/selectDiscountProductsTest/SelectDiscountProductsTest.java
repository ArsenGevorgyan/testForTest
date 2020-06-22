package selectDiscountProductsTest;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import baseTest.BaseTest;

public class SelectDiscountProductsTest extends BaseTest {

    @BeforeClass()
    @Description("Log in To Application3")
    public void setUp() throws InterruptedException {
        //homePage.get();
        webDriver().get("http://automationpractice.com/index.php");
        homePage.isLoaded();

    }

    @Test(description = "Select Sales Offers")
    @Description("Select Sales Offers")
    public void SelectOffers() throws InterruptedException {
        for (int index = 0; index < homePage.salesPercentElement.size(); index++) {
            homePage.moveOnSalesPercentageElement(index);
            Assert.assertTrue(homePage.addToCartButton.isDisplayed(), "Add to chart button is not visible");
            homePage.clickOnAddToCartButton();
            homePage.clickOnContinueShoppingButton();
        }
        homePage.moveOnShoppingCartArea();
        homePage.clickOnCheckOutButton();
        shoppingCartPage.isLoaded();
        Assert.assertTrue(shoppingCartPage.cartTitle.getText().toString().contains("SHOPPING-CART SUMMARY"), "Shopping cart page title is incorrect");
        shoppingCartPage.clickOnProceedToCheckoutButton();
        authenticationPage.isLoaded();
        Assert.assertTrue(authenticationPage.authenticationPageTitle.getText().toString().contains("AUTHENTICATION"), "Authentication page title is incorrect");
    }
}
