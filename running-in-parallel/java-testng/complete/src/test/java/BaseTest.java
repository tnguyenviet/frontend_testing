import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class BaseTest {

    private static final Logger LOGGER = Logger.getLogger(BaseTest.class.getName());
    private static final String SELENIUM_GRID_URL = "http://localhost:4444/wd/hub";
    // We need a thread safe environment to handle the webDriver variable in each thread separately
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    // Data provider which returns the browsers that will be used to run the tests
    @DataProvider(name = "browsersAndPlatforms", parallel = true)
    public static Object[][] browsersAndPlatformsProvider() {
        return new Object[][] {
                new Object[]{BrowserType.CHROME, Platform.LINUX},
                new Object[]{BrowserType.FIREFOX, Platform.LINUX}
        };
    }

    @BeforeMethod
    public void startWebDriverAndGetBaseUrl(Method method, Object[] testArgs) {
        String browserType = testArgs[0].toString();
        Platform platform = (Platform) testArgs[1];
        try {
            String message = String.format("STARTING %s on %s - %s", method.getName(), browserType, platform.name());
            LOGGER.info(message);
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, browserType);
            desiredCapabilities.setCapability(CapabilityType.PLATFORM, platform);
            desiredCapabilities.setCapability("name", method.getName());
            webDriver.set(new RemoteWebDriver(new URL(SELENIUM_GRID_URL), desiredCapabilities));
            webDriver.get().manage().window().maximize();
        } catch (Exception e) {
            String message = String.format("FAILED TO START %s on %s - %s, %s",
                    method.getName(), browserType, platform.name(), e.getMessage());
            LOGGER.info(message);
        }
    }

    @AfterMethod
    public void quitBrowser(Method method, Object[] testArgs) {
        String browserType = testArgs[0].toString();
        Platform platform = (Platform) testArgs[1];
        String message = String.format("FINISHING %s on %s - %s", method.getName(), browserType, platform.name());
        LOGGER.info(message);
        try {
            webDriver.get().quit();
        } catch (Exception e) {
            message = String.format("FAILED TO QUIT %s on %s - %s, %s",
                    method.getName(), browserType, platform.name(), e.getMessage());
            LOGGER.info(message);
        }

    }

    // Returns the webDriver for the current thread
    @SuppressWarnings("WeakerAccess")
    public WebDriver getWebDriver() {
        return webDriver.get();
    }

}
