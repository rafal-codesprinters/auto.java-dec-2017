package wordpress.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rafal on 2017-12-05.
 */
public class BaseTest {
    private static final int DRIVER_IMPLICIT_WAIT_TIMEOUT = 15;
    private static final int DRIVER_PAGE_LOAD_TIMEOUT = 15;
    protected WebDriver driver;

    @Before
    public void initWebDriver() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(DRIVER_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(DRIVER_PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
    }

    @After
    public void closeBrowser() {
        if (driver != null) driver.quit();
    }
}
