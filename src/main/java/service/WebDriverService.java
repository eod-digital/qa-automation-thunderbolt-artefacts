package service;

import factory.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static util.PropertiesUtil.getDefaultWaitTime;
import static util.PropertiesUtil.getProperty;

public class WebDriverService {
    private static RemoteWebDriver driver;
    private static Wait<RemoteWebDriver> wait;
    private static Actions actions;

    public WebDriverService() {
        long WAIT_TIME = Long.parseLong(getProperty("default.wait.time"));
        driver = WebDriverFactory.initiateRemoteWebDriver();
        actions = new Actions(driver);
        wait = new FluentWait<>(driver)
                .withTimeout(WAIT_TIME, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
    }

    /*
        custom fluent implicit conditional waits.
     */
    public List<WebElement> waitUntilVisibilityOfAllElements(final By identifier) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(identifier));
    }
    public RemoteWebElement waitUntilVisibilityOfElement(final WebElement element) {
        return (RemoteWebElement) wait.until(ExpectedConditions.visibilityOf(element));
    }
    public RemoteWebElement waitUntilElementVisibilityIsRefreshed(final WebElement element) {
        return (RemoteWebElement) wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }
    public RemoteWebElement waitUntilElementIsClickable(final WebElement element) {
        return (RemoteWebElement) wait.until(ExpectedConditions.elementToBeClickable(waitUntilVisibilityOfElement(element)));
    }
    public boolean waitUntilInvisibilityOfElement(final WebElement element) {
        return wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
    }
    public boolean waitUntilTextIsPresentInElement(final WebElement element, final String text) {
        return wait.until(ExpectedConditions.textToBePresentInElement(waitUntilVisibilityOfElement(element), text));
    }
    public boolean waitUntilElementIsSelected(final WebElement element, boolean state) {
        return wait.until(ExpectedConditions.elementSelectionStateToBe(waitUntilVisibilityOfElement(element), state));
    }
    public boolean waitUntilElementIsSelected(final WebElement element) {
        return wait.until(ExpectedConditions.elementToBeSelected(waitUntilVisibilityOfElement(element)));
    }
    public boolean waitUntilInvisibilityOfAllElements(final List<WebElement> elements) {
        return wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfAllElements(elements)));
    }

    /*
        custom element finders
     */
    public RemoteWebElement findWebElementWithMatchingString(final By identifier, final String string) {
        for (WebElement element : waitUntilVisibilityOfAllElements(identifier)) {
            if (element.getText().equalsIgnoreCase(string)) return (RemoteWebElement) element;
        }        
        return null;
    }

    /*
        custom element actions
     */
    public void click(final WebElement element) {
        waitUntilVisibilityOfElement(element).click();
    }
    public void sendKeys(final WebElement element, final String value) {
        WebElement webElement = waitUntilVisibilityOfElement(element);
        if (!webElement.getAttribute("value").isEmpty())
            webElement.clear();

        webElement.sendKeys(value);
    }
    public void selectByVisibleText(final WebElement element, final String visibleText) {
        Select select = new Select(waitUntilVisibilityOfElement(element));
        select.selectByVisibleText(visibleText);
    }
    public void navigateTo(final String url) {
        driver.navigate()
                .to(url);
    }
    public void dragAndDrop(final WebElement source, final WebElement target) {
        actions.dragAndDrop(waitUntilVisibilityOfElement(source), waitUntilVisibilityOfElement(target))
                .perform();
    }
    public void maximiseWindow() {
        driver.manage().window()
                .maximize();
    }

    /*

     */
    public void switchToNewBrowserWindow() {
        getMainBrowserWindow();
        waitUntilNewBrowserWindowIsDisplay();
        for (String browser : driver.getWindowHandles()) {
            driver.switchTo().window(browser);
        }
    }
    public void switchToMainBrowserWindow() {
        driver.switchTo().window(getMainBrowserWindow());
    }

    private void waitUntilNewBrowserWindowIsDisplay() {
        new WebDriverWait(driver, Long.valueOf(getDefaultWaitTime())) {
        }.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return (driver.getWindowHandles().size() > 1);
            }
        });
    }
    private String getMainBrowserWindow() {
        return driver.getWindowHandle();
    }
}
