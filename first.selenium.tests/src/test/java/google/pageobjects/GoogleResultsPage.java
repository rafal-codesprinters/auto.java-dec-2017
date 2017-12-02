package google.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Kuba on 2017-12-02.
 */
public class GoogleResultsPage extends BasePage {
    public static final By LOCATOR_RESULT_LINK = By.cssSelector(".rc > .r >a");
    public static final By LOCATOR_NEXT_PAGE_LINK = By.id("pnnext");
    private WebDriver driver;

    public GoogleResultsPage(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public int countResultsWithSpecificUrl(String pageUrl) {
        return (int) getResultsStream().filter(result -> result.getAttribute("href").equals(pageUrl)).count();
    }

    public GoogleResultsPage displayNextResultsPage() {
        WebElement nextPageLink = driver.findElement(LOCATOR_NEXT_PAGE_LINK);
        nextPageLink.click();
        return new GoogleResultsPage(driver);
    }

    private Stream<WebElement> getResultsStream() {
        List<WebElement> resultsList = driver.findElements(LOCATOR_RESULT_LINK);
        return resultsList.stream();
    }

    public int CountResultsWithUrlStartingWith(String pageUrl) {
        return (int) getResultsStream().filter(result -> result.getAttribute("href").startsWith(pageUrl)).count();
    }
}
