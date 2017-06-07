import org.openqa.selenium.Platform;
import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class ParallelTest extends BaseTest {

    @Test(dataProvider = "browsersAndPlatforms", alwaysRun = true)
    public void loadParallelTestPage_1(String browserType, Platform platform) {

        // Go to the homepage
        getWebDriver().get("http://www.google.com");
    }

    @Test(dataProvider = "browsersAndPlatforms", alwaysRun = true)
    public void loadParallelTestPage_2(String browserType, Platform platform) {

        // Go to the homepage
        getWebDriver().get("http://the-internet.herokuapp.com/");
    }

    @Test(dataProvider = "browsersAndPlatforms", alwaysRun = true)
    public void loadParallelTestPage_3(String browserType, Platform platform) {

        // Go to the homepage
        getWebDriver().get("http://the-internet.herokuapp.com/");
    }

    @Test(dataProvider = "browsersAndPlatforms", alwaysRun = true)
    public void loadParallelTestPage_4(String browserType, Platform platform) {

        // Go to the homepage
        getWebDriver().get("http://the-internet.herokuapp.com/");
    }

    @Test(dataProvider = "browsersAndPlatforms", alwaysRun = true)
    public void loadParallelTestPage_5(String browserType, Platform platform) {

        // Go to the homepage
        getWebDriver().get("http://the-internet.herokuapp.com/");
    }

    @Test(dataProvider = "browsersAndPlatforms", alwaysRun = true)
    public void loadParallelTestPage_6(String browserType, Platform platform) {

        // Go to the homepage
        getWebDriver().get("http://the-internet.herokuapp.com/");
    }

    @Test(dataProvider = "browsersAndPlatforms", alwaysRun = true)
    public void loadParallelTestPage_7(String browserType, Platform platform) {

        // Go to the homepage
        getWebDriver().get("http://the-internet.herokuapp.com/");
    }

    @Test(dataProvider = "browsersAndPlatforms", alwaysRun = true)
    public void loadParallelTestPage_8(String browserType, Platform platform) {

        // Go to the homepage
        getWebDriver().get("http://the-internet.herokuapp.com/");
    }

    @Test(dataProvider = "browsersAndPlatforms", alwaysRun = true)
    public void loadParallelTestPage_9(String browserType, Platform platform) {

        // Go to the homepage
        getWebDriver().get("http://the-internet.herokuapp.com/");
    }

    @Test(dataProvider = "browsersAndPlatforms", alwaysRun = true)
    public void loadParallelTestPage_10(String browserType, Platform platform) {

        // Go to the homepage
        getWebDriver().get("http://the-internet.herokuapp.com/");
    }
}
