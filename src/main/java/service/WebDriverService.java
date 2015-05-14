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
    private long WAIT_TIME;
    private Actions actions;

    public WebDriverService() {
        this.propertiesUtil = new PropertiesUtil();
        this.WAIT_TIME = Long.parseLong(propertiesUtil.getProperty("deafult.wait.time"));
        this.driver = WebDriverFactory.initiateRemoteWebDriver();
        this.actions = new Actions(driver);
    }

    /*
        custom fluent implicit conditional waits.
     */
    private Wait<RemoteWebDriver> fluentImplicitWait() {
        wait = new FluentWait<RemoteWebDriver>(driver)
                .withTimeout(WAIT_TIME, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        return wait;
    }
    public RemoteWebElement waitUntilVisibilityOfElement(final WebElement element) {
        return (RemoteWebElement) wait.until(ExpectedConditions.visibilityOf(element));
    }
    public RemoteWebElement waitUntilElementVisibilityIsRefreshed(final WebElement element) {
        return (RemoteWebElement) wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }
    public boolean waitUntilInvisibilityOfElement(final WebElement element) {
        return wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
    }
    public RemoteWebElement waitUntilElementIsClickable(final WebElement element) {
        return (RemoteWebElement) wait.until(ExpectedConditions.elementToBeClickable(waitUntilVisibilityOfElement(element)));
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
    public List<WebElement> waitUntilVisibilityOfAllElements(final By identifier) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(identifier));
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
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }
    public void maximiseWindow() {
        driver.manage().window().maximize();
    }
    
    private void waitForNewWindowToOpen() {
        new WebDriverWait(webDriver, Integer.valueOf(PropertiesUtil.getWebDriverWaitTime())) {
        }.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (driver.getWindowHandles().size() > 1);
            }
        });
    }
    
public void switchToNewWindow() {
        mainWindowHandle = webDriver.getWindowHandle();
        waitForNewWindowToOpen();
        for (String windowHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(windowHandle);
        }
    }

    public void switchToMainWindow() {
        webDriver.switchTo().window(mainWindowHandle);
    }
}
