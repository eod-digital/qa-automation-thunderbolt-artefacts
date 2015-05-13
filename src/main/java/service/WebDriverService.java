package service;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import util.PropertiesUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverService {
    private RemoteWebDriver driver;
    private Wait<RemoteWebDriver> wait;
    private PropertiesUtil propertiesUtil;
    private long TIMEOUT;
    private Actions actions;

    public WebDriverService() {
        this.propertiesUtil = new PropertiesUtil();
        this.TIMEOUT = Long.parseLong(propertiesUtil.getProperty("implicitTimeout"));
//        this.driver = new RemoteWebDriver();
        this.actions = new Actions(driver);
    }

    /*
        custom fluent implicit conditional waits.
     */
    public Wait<RemoteWebDriver> fluentImplicitWait() {
        wait = new FluentWait<RemoteWebDriver>(driver)
                .withTimeout(TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        return wait;
    }
    public void waitUntilVisibilityOfElement(final WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitUntilVisibilityOfAllElements(final List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
    public void waitUntilPresenceOfElement(final By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public void waitUntilInvisibilityOfElement(final By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public void waitUntilElementIsClickable(final WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void waitUntilElementIsClickable(final By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /*
        custom element finders
     */
    public WebElement findWebElement(final List<WebElement> elementList, final String string) {
        waitUntilVisibilityOfAllElements(elementList);
        for (WebElement element : elementList) {
            if (element.getText().equalsIgnoreCase(string)) return element;
        }
        return null;
    }

    /*
        custom element actions
     */
    public void click(final WebElement element) {
        waitUntilElementIsClickable(element);
        element.click();
    }
    public void sendKeys(final WebElement element, final String value) {
        waitUntilVisibilityOfElement(element);
        if (!element.getAttribute("value").isEmpty())
            element.clear();

        element.sendKeys(value);
    }
    public void selectByVisibleText(final WebElement element, final String visibleText) {
        waitUntilVisibilityOfElement(element);
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }
    public void maximiseWindow() {
        driver.manage().window().maximize();
    }
}
