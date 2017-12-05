package selenium;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Rafal on 2017-12-04.
 */
public class SeleniumTest {
    private static final String LOCATOR_CS_PAGE = "//*[@class='rc']/*[@class='r']/a[@href='http://agileszkolenia.pl/']";
    private static final By CS_PAGE_RESULT_LINK = By.xpath(LOCATOR_CS_PAGE);
    private static final String CS_PAGE_NAME = "code sprinters";
    private static final String GOOGLE_LINK = "http://google.com";
    private static final By SEARCH_BOX_LOCATOR = By.id("lst-ib");
    public WebDriver driver;

    @Before
    public void initializeBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void canFindCsPageOnFirstGoogleResultPage() {
        driver.get(GOOGLE_LINK);
        WebElement searchBox = driver.findElement(SEARCH_BOX_LOCATOR);
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(CS_PAGE_NAME);
        searchBox.submit();

        Assert.assertTrue("CS page found", driver.findElements(CS_PAGE_RESULT_LINK).size() > 0);
    }

    @After
    public void closeBrowser() {
        if (driver != null) driver.quit();
    }
}
