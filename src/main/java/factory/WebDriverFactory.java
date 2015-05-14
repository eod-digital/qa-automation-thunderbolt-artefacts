package factory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

import static util.PropertiesUtil.getDesiredCapabilities;
import static util.PropertiesUtil.getSeleniumDriverLocation;

public class WebDriverFactory {

  public static RemoteWebDriver initiateRemoteWebDriver() {
        RemoteWebDriver driver = null;
        try {
            driver = getDriverInstance();
            driver.manage().window().maximize();
        } catch(MalformedURLException e) {
            e.printStackTrace();
        }
          return driver;
    }

    private static RemoteWebDriver getDriverInstance() throws MalformedURLException {
        String driverLocation = getSeleniumDriverLocation();
        switch(driverLocation) {
            case "localhost":
                return initiateLocalhostDriver();
            case "grid":
            default:
                return initiateCloudHostedDriver();
        }
    }
    private static RemoteWebDriver initiateCloudHostedDriver() {
        DesiredCapabilities capabilities;
        String browser = getDesiredCapabilities();
        switch(browser) {
            case "chrome":
                capabilities = new DesiredCapabilities("chrome", null, null);
                break;
            case "firefox":
                capabilities = new DesiredCapabilities("firefox", null, null);
                capabilities.setCapability(FirefoxDriver.PROFILE, getFirefoxProfile());
                break;
            default:
                capabilities = new DesiredCapabilities(browser, null, null);
                break;
        }
        return null;
    }
    private static RemoteWebDriver initiateLocalhostDriver() {
        String browser = getDesiredCapabilities();
        switch(browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "chromedriver");
                return new ChromeDriver();
            case "firefox":
            default:
                return new FirefoxDriver(getFirefoxProfile());
        }
    }
    private static FirefoxProfile getFirefoxProfile(){
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("webdriver.load.strategy", "unstable");
        firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
        return firefoxProfile;
    }
}
