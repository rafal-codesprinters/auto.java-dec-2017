package google.tests;

import google.pageobjects.BasePage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


/**
 * Created by Kuba on 2017-12-02.
 */
public class BaseTests {
    protected WebDriver driver;

    @Before
    public void initializeWebDriver() throws IOException {
        Properties properties = new Properties();
        properties.load(BasePage.class.getClassLoader().getResourceAsStream("test.properties"));

        String target = properties.getProperty("target");
        String mode = properties.getProperty("mode");
        String gridHubAddress = properties.getProperty("grid.hub.address");
        String gridHubPort = properties.getProperty("grid.hub.port");

        if (mode.equals("grid")) {
            URL gridHubUrl = new URL("http://" + gridHubAddress + ":" + gridHubPort + "/wd/hub");
            if (target.equals("chrome")) {
                driver = new RemoteWebDriver(gridHubUrl, DesiredCapabilities.chrome());
            } else if (target.equals("firefox")) {
                driver = new RemoteWebDriver(gridHubUrl, DesiredCapabilities.firefox());
            } else invalidTargetParameter();
        } else if (mode.equals("browser")) {
            if (target.equals("chrome")) {
                driver = new ChromeDriver();
            } else if (target.equals("firefox")) {
                driver = new FirefoxDriver();
            } else if (target.equals("ie")) {
                driver = new InternetExplorerDriver();
            } else invalidTargetParameter();
        } else invalidModeParameter();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    private void invalidModeParameter() throws InvalidParameterException {
        throw new InvalidParameterException("Invalid 'mode' in 'test.properties' file.");
    }

    private void invalidTargetParameter() throws InvalidParameterException {
        throw new InvalidParameterException("Invalid 'target' in 'test properties' file.");
    }

    @After
    public void tearDownWebDriver() {
        if (driver != null) driver.quit();
    }
}
