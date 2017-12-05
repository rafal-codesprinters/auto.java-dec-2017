package wordpress.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rafal on 2017-12-05.
 */
public class BaseTest {
    private static final int DRIVER_IMPLICIT_WAIT_TIMEOUT_IN_SECONDS = 15;
    private static final int DRIVER_PAGE_LOAD_TIMEOUT_IN_SECONDS = 15;
    protected WebDriver driver;

    @Before
    public void initWebDriver() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(DRIVER_IMPLICIT_WAIT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(DRIVER_PAGE_LOAD_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
    }

    @After
    public void closeBrowser() {
        if (driver != null) driver.quit();
    }
}
