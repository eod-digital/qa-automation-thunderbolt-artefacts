package factory;

public class WebDriverFactory {
  public static RemoteWebDriver initiateRemoteWebDriver() {
    RemoteWebDriver driver = null;
    try {
        driver = getDriverInstance();
    } catch(MalformedURLException e) {
        
    }
    driver.manage().window().maximize();
    return driver;
}

private static RemoteWebDriver getRemoteWebDriverInstance() throws MalformedURLException {
    String driverLocation = PropertiesUtil.getRemoteWebDriverLocation();
    switch(driverLocation) {
        case "localhost":
            return initiateLocalhostDriver();
        case "grid":
        case "cloudHost":
        default:
            return initiateCloudHostedDriver();
    }
}

private static RemoteWebDriver initiateCloudHostedDriver() {
    DesiredCapabilities capabilities;
    String browser = PropertiesUtil.getBrowserType();
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
}

private static RemoteWebDriver initiateLocalhostDriver() {
    String browser = PropertiesUtil.getBrowserType();
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
