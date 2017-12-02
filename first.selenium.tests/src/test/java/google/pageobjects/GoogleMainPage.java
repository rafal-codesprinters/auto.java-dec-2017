package google.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Kuba on 2017-12-02.
 */
public class GoogleMainPage extends BasePage {

    public static final By LOCATOR_SEARCH_BOX = By.id("lst-ib");
    private WebDriver driver;

    public GoogleMainPage(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public GoogleResultsPage SearchFor(String pageName) {
        WebElement searchButton = driver.findElement(LOCATOR_SEARCH_BOX);
        searchButton.click();
        searchButton.clear();
        searchButton.sendKeys(pageName);
        searchButton.submit();
        return new GoogleResultsPage(driver);
    }
}
