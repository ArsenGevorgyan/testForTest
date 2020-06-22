package pages.basePage;


import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.SystemClock;
import org.testng.Assert;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BasePage extends SlowLoadableComponent<BasePage> {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    private int maxDelayTime;


    protected BasePage(WebDriver webDriver) {
        super(new SystemClock(), 10);
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, 1);
    }

    /**
     * Open page by relative URL (URL is relative to "site.url").
     *
     * @param strPageURL URL to navigate
     */
    @Step
    protected void open(String strPageURL) {
        this.open(strPageURL, true);
    }

    /**
     * Open page by relative URL in case of second parameter is true.
     * Or navigates by absolute URL when second parameter is false.
     * In case of "basic.auth.enabled" property is true,
     * URL will be modified to use basic authentication.
     *
     * @param strPageURL  page URL
     * @param bUseBaseURL use base URL
     */

    @Step
    protected void open(String strPageURL, boolean bUseBaseURL) {
        if (bUseBaseURL) {
            webDriver.get(System.getProperty("application.site.url") + strPageURL);
        } else {
            webDriver.get(strPageURL);
        }
    }

    /**
     * Checks if current URL matches with provided one.
     * Provided URL is appended to "siteUrl" from configuration.
     *
     * @param strPageURL URL to check
     */
    @Step
    protected void checkPageURL(String strPageURL) {
        this.checkPageURL(strPageURL, true);
    }

    /**
     * Checks if current URL begins with provided one.
     * In case if "bUseBaseURL" is true provided URL will be appended to "siteUrl" from configuration.
     * In case if "bUseBaseURL" is false provided URL will not be appended to "siteUrl" from configuration.
     *
     * @param strPageURL  URL to be checked
     * @param bUseBaseURL should "siteUrl" to be considered as prefix for "strPageURL"
     */
    @Step
    protected void checkPageURL(String strPageURL, boolean bUseBaseURL) {
        if (bUseBaseURL) {
            Assert.assertEquals(webDriver.getCurrentUrl(), System.getProperty("application.site.url") + strPageURL, "Page URL is incorrect.");
        } else {
            Assert.assertEquals(webDriver.getCurrentUrl(), strPageURL, "Page URL is incorrect.");
        }
    }

    /**
     * Checks if current page Title matches with provided one.
     *
     * @param strPageTitle Title to check
     */
    @Step
    protected void checkPageTitle(String strPageTitle) {
        Assert.assertEquals(webDriver.getTitle(), strPageTitle, "Page title in incorrect.");
    }

    /**
     * Check element become visible.
     *
     * @param webElement which is expected
     */
    @Step
    protected void checkIfElementIsVisible(WebElement webElement) throws TimeoutException {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * WebDriver getter.
     *
     * @return WebDriver instance
     */
    @Step
    public WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * Perform Thread sleep for provided milliseconds.
     *
     * @param iTimeInMilliseconds time in milliseconds
     */
    @Step
    public void sleep(int iTimeInMilliseconds) {
        try {
            Thread.sleep(iTimeInMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
//            Log.error("Problem during Thread sleep");
        }
    }

    /**
     * Refreshes current page.
     */
    @Step
    public void refresh() {
        webDriver.navigate().refresh();
    }

    /**
     * Navigates back on web page.
     */
    @Step
    public void navigateBack() {
        webDriver.navigate().back();
    }

    /**
     * Navigates forward on web page.
     */
    @Step
    public void navigateForward() {
        webDriver.navigate().forward();
    }

    /**
     * Set maximum delay time
     *
     * @param maxDelayTime time in seconds
     */
    @Step
    public void setMaxDelayTime(int maxDelayTime) {
        this.maxDelayTime = maxDelayTime;
    }

    /**
     * Clicks on element.
     *
     * @param webElement WebElement
     */
    @Step
    public void clickOnElement(WebElement webElement) {
        webElement.click();
    }

    /**
     * Scrolls to element with use of Java Script executor.
     *
     * @param webElement Web element to be scrolled to
     */
    @Step
    public void scrollToElementJS(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    /**
     * Double clicks on element with use of Actions class.
     *
     * @param element WebElement actions to be performed on
     */
    @Step
    public void doubleClick(WebElement element) {
        try {
            Actions action = new Actions(webDriver).doubleClick(element);
            action.click().build().perform();
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page document " + Arrays.toString(e.getStackTrace()));
        } catch (NoSuchElementException e) {
            System.out.println("Element " + element + " was not found in DOM " + Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            System.out.println("Element " + element + " was not clickable " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Moves mouse to element and perform click on it with use of Actions class.
     *
     * @param element WebElement actions to be performed on
     */
    @Step
    public void moveAndClick(WebElement element) {
        try {
            Actions action = new Actions(webDriver).moveToElement(element);
            action.click().build().perform();

            System.out.println("Double clicked the element");
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page document " + Arrays.toString(e.getStackTrace()));
        } catch (NoSuchElementException e) {
            System.out.println("Element " + element + " was not found in DOM " + Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            System.out.println("Element " + element + " was not clickable " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Moves mouse to element and perform click on it with use of Actions class.
     *
     * @param element WebElement actions to be performed on
     */
    @Step
    public void moveOnElement(WebElement element) {
        try {
            Actions action = new Actions(webDriver);
            action.moveToElement(element).build().perform();

        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page document " + Arrays.toString(e.getStackTrace()));
        } catch (NoSuchElementException e) {
            System.out.println("Element " + element + " was not found in DOM " + Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            System.out.println("Element " + element + " was not clickable " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Fill text in input with performing clear prior to it.
     *
     * @param webElement WebElement text to filled
     * @param strText    text to fill
     */
    @Step
    public void fillText(WebElement webElement, String strText) {
        webElement.clear();
        webElement.sendKeys(strText);
    }

    /**
     * Deletes/clears text from input.
     *
     * @param webElement WebElement text to be cleared
     */
    @Step
    public void deleteText(WebElement webElement) {
        webElement.clear();
    }

    /**
     * Sends "DELETE" key press to provided WebElement.
     *
     * @param webElement WebElement to perform action on
     */
    @Step
    public void pressDeleteKey(WebElement webElement) {
        webElement.sendKeys(Keys.DELETE);
    }

    /**
     * Waits provided time till element become visible.
     *
     * @param seconds amount of seconds
     * @param webElement WebElement to perform action on
     */
    @Step
    public void waitElementToBePresent(WebElement webElement, long seconds) {
        new WebDriverWait(webDriver, seconds).until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Waits till element become visible.
     *
     * @param locator of elements which is expected
     */
    @Step
    public void waitElementToBePresent(By locator) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait provided time element to become clickable.
     *
     * @param webElement WebElement
     * @param seconds    time
     */
    @Step
    public void waitElementToBeClickable(WebElement webElement, long seconds) {
        new WebDriverWait(webDriver, seconds).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Wait element to become clickable.
     *
     * @param webElement WebElement
     */
    @Step
    public void waitElementToBeClickable(WebElement webElement) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Waits provided time text to be present in element.
     *
     * @param webElement web element
     * @param text       text to be present
     * @param seconds    time
     */
    @Step
    public void waitTextToBeVisibleInElement(WebElement webElement, String text, long seconds) {
        new WebDriverWait(webDriver, seconds).until(ExpectedConditions.textToBePresentInElement(webElement, text));
    }

    /**
     * Waits text to be present in element.
     *
     * @param webElement web element
     * @param text       text to be present
     */
    @Step
    public void waitTextToBeVisibleInElement(WebElement webElement, String text) {
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
    }

    /**
     * Wait provided time web element to be visible
     *
     * @param webElement WebElement
     */
    @Step
    public void waitElementToBeVisible(WebElement webElement, long seconds) {
        new WebDriverWait(webDriver, seconds).until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Wait web element to be visible
     *
     * @param webElement WebElement
     */
    @Step
    public void waitElementToBeVisible(WebElement webElement) {
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Wait provided time iFrame to be available and switch to it
     *
     * @param webElement iFrame web element
     */
    @Step
    public void waitFrameToBeAvailableAndSwitchToIt(WebElement webElement, long seconds) {
        new WebDriverWait(webDriver, seconds).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));
    }

    /**
     * Wait iFrame to be available and switch to it
     *
     * @param webElement iFrame web element
     */
    @Step
    public void waitFrameToBeAvailableAndSwitchToIt(String webElement) {
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));
    }

    /**
     * Wait provided time for page title to be provided value.
     *
     * @param title   title
     * @param seconds time
     */
    @Step
    public void waitForPageByTitle(String title, long seconds) {
        new WebDriverWait(webDriver, seconds).until(ExpectedConditions.titleContains(title));
    }

    /**
     * Wait for page title to be provided value.
     *
     * @param title title
     */
    @Step
    public void waitForPageByTitle(String title) {
        webDriverWait.until(ExpectedConditions.titleContains(title));
    }

    /**
     * Perform form submission on provided WebElement.
     *
     * @param element WebElement action to be performed on
     */
    @Step
    public void submit(WebElement element) {
        element.submit();
    }

    /**
     * Get current page URL.
     *
     * @return URL of current page
     */
    @Step
    public URL getCurrentURL() {
        try {
            return new URL(webDriver.getCurrentUrl());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select element from select menu (drop-down) by visible text.
     *
     * @param element select menu element
     * @param text    visible text to be selected
     */
    @Step
    public void selectElementByVisibleText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    /**
     * Select item in drop down by item name.
     *
     * @param element WebElement of drop down
     * @param name    name of item
     */
    @Step
    public void selectDropDownItemByName(WebElement element, String name) {
        new Select(element).selectByVisibleText(name);
    }

    /**
     * Select drop down list item by text.
     *
     * @param text text to be selected by
     */
    @Step
    public void selectDropDownListItemByText(String text) {
        List<WebElement> options = webDriver.findElements(By.xpath("//*[contains(text(), '" + text + "')]"));
        int index = options.size() - 1;
        options.get(index).click();
    }

    /**
     * Select drop down list item by title.
     *
     * @param title title to be selected by
     */
    @Step
    public void selectDropDownListItemByTitle(String title) {
        List<WebElement> options = webDriver.findElements(By.xpath("//*[contains(@title, '" + title + "')]"));
        int index = options.size() - 1;
        options.get(index).click();
    }

    /**
     * Select item from drop down by index.
     *
     * @param element drop down WebElement
     * @param index   index to be selected
     */
    @Step
    public void selectDropDownItemByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    /**
     * Select item from drop down by value.
     *
     * @param element drop down WebElement
     * @param value   value to be selected
     */
    @Step
    public void selectDropDownItemByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    /**
     * This method is designed to switch to "Present" iframe on web page.
     *
     * @param locator locator
     */
    @Step
    public void switchToIframe(By locator) {
       // waitElementToBePresent(locator, 15);
        List<WebElement> alliFrames = webDriver.findElements(locator);
        for (WebElement iframe : alliFrames) {
            if (iframe.isDisplayed()) {
                webDriver.switchTo().frame(iframe);
                break;
            }
        }
    }


    @Step
    public void switchToIframe(WebElement frameElement) {

        if (frameElement.isDisplayed()) {
            webDriver.switchTo().frame(frameElement);
        }

    }

    /**
     * Switches to iframe by name or ID.
     *
     * @param nameOrId iframe name or ID
     */
    @Step
    public void switchToIFrameByNameOrId(String nameOrId) {
        webDriver.switchTo().frame(nameOrId);
    }

    /**
     * Switches to iframe by WebElement and clicks on provided WebElement.
     *
     * @param iframe  WebElement of iframe
     * @param element WebElement to click on after switch
     */
    @Step
    public void switchOnIframeWithLocator(WebElement iframe, WebElement element) {
        webDriver.switchTo().frame(iframe);

        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     * Switches to default search context.
     */
    @Step
    public void switchToDefaultContent() {
        webDriver.switchTo().defaultContent();
    }

    /**
     * Scrolls page down by provided number of pixels.
     *
     * @param pixels number of pixels to scroll
     */
    @Step
    public void scrollPageDown(int pixels) {
        ((JavascriptExecutor) webDriver).executeScript("scroll(0, " + pixels + ");");
    }

    /**
     * Drag and drops from source to destination.
     *
     * @param sourceLocator      xpath of element which has to be drag
     * @param destinationLocator xpath of element which determines the the place for
     *                           dropping
     */
    @Step
    public void dragAndDrop(By sourceLocator, By destinationLocator) {
        WebElement sourceElement = null;
        WebElement destinationElement = null;
        try {
            webDriver.switchTo().defaultContent();
            sourceElement = webDriver.findElement(sourceLocator);
            destinationElement = webDriver.findElement(destinationLocator);
            Actions builder = new Actions(webDriver);
            webDriver.switchTo().defaultContent();
            Action hold = builder.clickAndHold(sourceElement).build();
            hold.perform();
            sleep(1000);
            Action move = builder.moveToElement(destinationElement).release(destinationElement).build();
            sleep(1000);
            move.perform();
            sleep(1000);
        } catch (StaleElementReferenceException e) {
            System.out.println("Element with " + sourceElement + "or" + destinationElement
                    + "is not attached to the page document " + Arrays.toString(e.getStackTrace()));
        } catch (NoSuchElementException e) {
            System.out.println("Element " + sourceElement + "or" + destinationElement + " was not found in DOM "
                    + Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            System.out.println(
                    "Error occurred while performing drag and drop operation " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Unchecks element in checkbox
     *
     * @param locator xpath to element
     */
    @Step
    public void unCheck(By locator) {
        if (isElementSelected(locator)) {
            webDriver.findElement(locator).click();
            sleep(300);
        } else {
            System.out.print("Element not found or not selected");
        }
    }

    /**
     * Checks element in checkbox
     *
     * @param element WebElement
     */
    @Step
    public void check(WebElement element) {
        if (!element.isSelected()) {
            element.click();
            sleep(500);
        } else {
            System.out.print("Element not found or not selected");
        }
    }

    /**
     * Checks whether element is selected or not
     *
     * @param locator xpath to element
     * @return {@code true} if element is selected, {@code false} - otherwise.
     */
    @Step
    public boolean isElementSelected(By locator) {
        return webDriver.findElement(locator).isSelected();
    }

    /**
     * Method returns attribute value of WebElement.
     *
     * @param element   WebElement
     * @param attribute attribute name
     * @return attribute value
     */
    @Step
    public String getAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    /**
     * Get current window handler.
     *
     * @return window handler
     */
    @Step
    public String getWindowHandler() {
        return webDriver.getWindowHandle();
    }

    /**
     * Close alert window.
     */
    @Step
    public void closeAlert() {
        try {
            webDriver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException ignore) {
        }
    }

    /**
     * Accept alert window.
     */
    @Step
    public void acceptAlert() {
        try {
            webDriverWait.until(ExpectedConditions.alertIsPresent());
            Alert alert = this.webDriver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            //exception handling
        }
    }

    /**
     * Get list of Drop Down Texts
     *
     * @param dropDownElement WebElement
     * @return list
     */
    @Step
    public List<String> getListOfDropDownTexts(WebElement dropDownElement) {
        List<String> listOfDropDownTexts = new ArrayList<>();
        List<WebElement> allOptions = dropDownElement.findElements(By.tagName("option"));
        listOfDropDownTexts.addAll(allOptions.stream().map(WebElement::getText).collect(Collectors.toList()));
        return listOfDropDownTexts;
    }

    /**
     * Wait WebElement to become invisible.
     *
     * @param webElement WebElement to check
     */
    @Step
    public void waitElementToBeInvisible(WebElement webElement) {
        webDriverWait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    /**
     * Check if web element is displayed
     *
     * @param webElement WebElement to check
     * @return true if WebElement is displayed, false if not
     */
    @Step
    public boolean checkIfElementIsDisplayed(WebElement webElement) {
        return webElement.isDisplayed();
    }

    /**
     * Select date from calendar.
     *
     * @param webElement WebElement
     * @param dd         ??
     */
    @Step
    public void selectCalendarDate(WebElement webElement, String dd) {
        List<WebElement> columns = webElement.findElements(By.tagName("td"));

        for (WebElement cell : columns) {
            // Select Date
            if (cell.getText().equals(dd)) {
                cell.findElement(By.linkText(dd)).click();
                break;
            }
        }
    }

    /////// Random Calendar Begin///////

    /**
     * For random create numbers
     *
     * @param lowerBound ??
     * @param upperBound ??
     * @return ??
     */
    @Step
    public int random(int lowerBound, int upperBound) {
        return (lowerBound + (int) Math.round(Math.random() * (upperBound - lowerBound)));
    }

    /**
     * Get this year
     *
     * @param year ??
     * @return ??
     */
    @Step
    public boolean isLeapYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        int noOfDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);

        if (noOfDays > 365) {
            return true;
        }
        return false;
    }

    /**
     * Return Random Date
     *
     * @param returnDate   return date
     * @param until_before ??
     * @return ??
     */
    @Step
    public String randomDate(String returnDate, String until_before) {
        String Date = null;
        int yyyy = 0;

        if (until_before.equals("until")) {
            yyyy = random(1900, 2017);
        } else if (until_before.equals("before")) {
            yyyy = random(2019, 2030);
        }
        int mm = random(1, 12);
        int dd = 0; // will set it later depending on year and month

        switch (mm) {
            case 2:
                if (isLeapYear(yyyy)) {
                    dd = random(1, 29);
                } else {
                    dd = random(1, 28);
                }
                break;
            case 12:
                dd = random(1, 31);
                break;
            default:
                dd = random(1, 30);
                break;
        }

        String year = Integer.toString(yyyy);
        String month = Integer.toString(mm);
        String day = Integer.toString(dd);

        if (mm < 10) {
            month = "0" + mm;
        }

        if (dd < 10) {
            day = "0" + dd;
        }

        if (returnDate.equals("day")) {
            Date = day;
        } else if (returnDate.equals("month")) {
            Date = month;
        } else if (returnDate.equals("year")) {
            Date = year;
        }
        if (returnDate.equals("date")) {

            Date = day + '/' + month + '/' + year;
        }
        return Date;
    }

    /**
     * Generate Random string
     *
     * @param count Length
     * @return String
     */
    @Step
    public static String randomAlphaNumeric(int count) {
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * symbols.length());
            builder.append(symbols.charAt(character));
        }
        return builder.toString();
    }

    /**
     * Generate Random numeric string
     *
     * @param count String Length
     * @return String
     */
    @Step
    public static String randomNumeric(int count) {
        String symbols = "0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * symbols.length());
            builder.append(symbols.charAt(character));
        }
        return builder.toString();
    }

    /**
     * Generate Random Alpha string
     *
     * @param count String Length
     * @return String
     */
    @Step
    public static String randomAlpha(int count) {
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * symbols.length());
            builder.append(symbols.charAt(character));
        }
        return builder.toString();
    }

    /**
     * Generate Random Alpha Armenian string
     *
     * @param count String Length
     * @return String
     */
    @Step
    public String randomAlphaArm(int count) {
        String symbols = "";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * symbols.length());
            builder.append(symbols.charAt(character));
        }
        return builder.toString();
    }

    @Step
    public static String randomDigits(int count) {
        String symbols = "0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * symbols.length());
            builder.append(symbols.charAt(character));
        }
        return builder.toString();
    }

    // Window manipulations

    /**
     * Closes current window(browser tab).
     */
    @Step
    public void closeWindow() {
        webDriver.close();
    }

    /**
     * Get current windows (browser tab) ID (window handler).
     *
     * @return current window (browser tab) ID
     */
    @Step
    public String getCurrentWindowHandler() {
        return webDriver.getWindowHandle();
    }

    /**
     * Switches to window (browser tab) with specified ID (window handler).
     *
     * @param windowHandler window (browser tab) ID switch to
     */
    @Step
    public void switchToWindow(String windowHandler) {
        webDriver.switchTo().window(windowHandler);
    }

    /**
     * Switch to last windows in window list.
     */
    @Step
    public void switchToWindow() {
        String mainWindow = webDriver.getWindowHandle();
        Set<String> set = webDriver.getWindowHandles();

        for (String childWindow : set) {
            if (!mainWindow.equals(childWindow)) {
                webDriver.switchTo().window(childWindow);
            }
        }
    }
//TODO need to find how to implement
//    public void setSpeed(Speed speed) {
//        options.setSpeed(speed);
//    }
//}

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {

    }
}
