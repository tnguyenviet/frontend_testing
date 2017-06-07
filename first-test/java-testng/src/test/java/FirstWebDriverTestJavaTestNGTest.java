import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstWebDriverTestJavaTestNGTest {

    // Setting the url for the WebDriver
    private static final String URL = "http://localhost:4444/wd/hub";

    @Test
    public void checkPageTitle() throws MalformedURLException {
        for (int i = 0; i < 100; i++) {
            System.out.println("Execution # " + i);
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setCapability(CapabilityType.PLATFORM, Platform.LINUX);

            // Create a new instance of the remote web driver
            WebDriver driver = new RemoteWebDriver(new URL(URL), desiredCapabilities);

            // Maximize the window
            driver.manage().window().maximize();

            // Go to Selenium Console
            driver.get("http://localhost:4444/grid/console");

            // Close the browser
            driver.quit();
        }
    }

    @Test
    public void firefoxProfileTest() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        desiredCapabilities.setCapability(CapabilityType.PLATFORM, Platform.LINUX);

        // Create a new instance of the remote web driver
        WebDriver driver = new RemoteWebDriver(new URL(URL), desiredCapabilities);

        Thread.sleep(1000 * 70);

        // Maximize the window
        driver.manage().window().maximize();

        Thread.sleep(1000 * 70);

        // Go to Selenium Console
        driver.get("http://localhost:4444/grid/console");

        // Close the browser
        driver.quit();
    }

    @Test
    public void checkPageInVPN() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(CapabilityType.PLATFORM, Platform.LINUX);

        // Create a new instance of the remote web driver
        WebDriver driver = new RemoteWebDriver(new URL(URL), desiredCapabilities);

        // Maximize the window
        driver.manage().window().maximize();

        // Go to Selenium Console
        driver.get("https://techjira.zalando.net/secure/RapidBoard.jspa?rapidView=1090");

        // Close the browser
        driver.quit();
    }

}
