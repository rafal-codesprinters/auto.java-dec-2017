package pageobjects.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rafal on 2017-12-04.
 */
public class BaseTest {
    private static final String PROP_BROWSER = "browser";
    private static final String PROP_TARGET = "target";
    private static final String PROP_HUB_ADDRESS = "hub.address";
    private static final String PROP_HUB_PORT = "hub.port";
    private static final String TARGET_BROWSER = "browser";
    private static final String TARGET_GRID = "grid";
    private static final String BROWSER_CHROME = "chrome";
    private static final String BROWSER_FIREFOX = "firefox";
    WebDriver driver;

    @Before
    public void initWebDriver() throws IOException {
        Properties testProperties = new Properties();
        InputStream testPropertiesInputStream = BaseTest.class.getClassLoader().getResourceAsStream("test.properties");

        if (testPropertiesInputStream != null) {
            testProperties.load(testPropertiesInputStream);
        } else {
            throw new FileNotFoundException("test.properties file not found");
        }

        String target = testProperties.getProperty(PROP_TARGET);
        String browser = testProperties.getProperty(PROP_BROWSER);
        String hubAddress = testProperties.getProperty(PROP_HUB_ADDRESS);
        String hubPort = testProperties.getProperty(PROP_HUB_PORT);

        switch (target) {
            case TARGET_BROWSER:
                switch (browser) {
                    case BROWSER_CHROME:
                        driver = new ChromeDriver();
                        break;
                    case BROWSER_FIREFOX:
                        driver = new FirefoxDriver();
                        break;
                    default:
                        throw new InvalidPropertiesFormatException(PROP_BROWSER);
                }
                break;
            case TARGET_GRID:
                URL gridHubUrl = new URL("http://" + hubAddress + ":" + hubPort + "/wd/hub");
                switch (browser) {
                    case BROWSER_CHROME:
                        ChromeOptions chromeOptions = new ChromeOptions();
                        driver = new RemoteWebDriver(gridHubUrl, chromeOptions);
                        break;
                    case BROWSER_FIREFOX:
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        driver = new RemoteWebDriver(gridHubUrl, firefoxOptions);
                        break;
                    default:
                        throw new InvalidPropertiesFormatException(PROP_BROWSER);
                }
                break;
            default:
                throw new InvalidPropertiesFormatException(PROP_TARGET);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void closeBrowser() {
        if (driver != null) driver.quit();
    }
}
