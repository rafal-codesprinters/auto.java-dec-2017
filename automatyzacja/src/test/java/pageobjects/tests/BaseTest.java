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
    private static final int DRIVER_IMPLICIT_WAIT_TIMEOUT = 10;
    protected WebDriver driver;

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

        if (target.equals(TARGET_BROWSER)) {
            if (browser.equals(BROWSER_CHROME)) {
                driver = new ChromeDriver();

            } else if (browser.equals(BROWSER_FIREFOX)) {
                driver = new FirefoxDriver();

            } else {
                throw new InvalidPropertiesFormatException(PROP_BROWSER);
            }
        } else if (target.equals(TARGET_GRID)) {
            URL gridHubUrl = new URL("http://" + hubAddress + ":" + hubPort + "/wd/hub");
            if (browser.equals(BROWSER_CHROME)) {
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new RemoteWebDriver(gridHubUrl, chromeOptions);

            } else if (browser.equals(BROWSER_FIREFOX)) {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new RemoteWebDriver(gridHubUrl, firefoxOptions);

            } else {
                throw new InvalidPropertiesFormatException(PROP_BROWSER);
            }

        } else {
            throw new InvalidPropertiesFormatException(PROP_TARGET);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(DRIVER_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    @After
    public void closeBrowser() {
        if (driver != null) driver.quit();
    }
}
