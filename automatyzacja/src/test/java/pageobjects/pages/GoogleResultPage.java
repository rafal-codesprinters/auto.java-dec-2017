package pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Rafal on 2017-12-04.
 */
public class GoogleResultPage extends GooglePage {

    private static final By LOCATOR_SINGLE_RESULT = By.cssSelector(".rc > .r > a");
    private static final By LOCATOR_NEXT_PAGE_LINK = By.cssSelector("#pnnext");

    public GoogleResultPage(WebDriver driver) {
        super(driver);
        waitUntilElementIsVisible(LOCATOR_SINGLE_RESULT);
    }

    public int countResultWithUrl(String pageUrl) {
        List<WebElement> results = driver.findElements(LOCATOR_SINGLE_RESULT);
        Stream<WebElement> resultsStream = results.stream();
        return (int) resultsStream.filter(result -> result.getAttribute("href").equals(pageUrl)).count();
    }

    public GoogleResultPage displayNextResultsPage() {
        driver.findElement(LOCATOR_NEXT_PAGE_LINK).click();
        return new GoogleResultPage(driver);
    }

    public int countResultsWithUrlMatching(String pageUrl) {
        return (int) driver.findElements(LOCATOR_SINGLE_RESULT)
                .stream()
                .filter(result -> result.getAttribute("href").startsWith(pageUrl)).count();
    }
}
