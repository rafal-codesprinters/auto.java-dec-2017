package pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Rafal on 2017-12-04.
 */
public class GoogleMainPage extends GooglePage {

    private static final By LOCATOR_SEARCH_BOX = By.id("lst-ib");

    public GoogleMainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(GOOGLE_ADDRESS);
        waitUntilElementIsVisible(LOCATOR_SEARCH_BOX);
    }

    public GoogleResultPage searchFor(String pageName) {
        WebElement searchBox = driver.findElement(LOCATOR_SEARCH_BOX);
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(pageName);
        searchBox.submit();
        return new GoogleResultPage(driver);
    }
}
