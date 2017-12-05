package wordpress.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Rafal on 2017-12-05.
 */
public class WordPressPage {
    protected static final String WORDPRESS_ADDREESS = "http://autotestjava.wordpress.com/";
    private static final int TIMEOUT_IN_SECONDS_WAITING_FOR_ELEMENT_VISIBLE = 30;
    private static final int TIMEOUT_IN_SECONDS_WAITING_FOR_ELEMENT_HIDDEN = 30;
    private static final int TIMEOUT_IN_SECONDS_WAITING_FOR_ELEMENT_CLICKABLE = 30;
    private static final int TIMEOUT_IN_SECONDS_WAITING_FOR_ELEMENT_PRESENT = 30;
    protected WebDriver driver;

    public WordPressPage(WebDriver driver) {
        this.driver = driver;
    }

    protected void waitUntilElementIsVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS_WAITING_FOR_ELEMENT_VISIBLE);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitUntilElementIsHidden(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS_WAITING_FOR_ELEMENT_HIDDEN);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitUntilElementIsClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS_WAITING_FOR_ELEMENT_CLICKABLE);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitUntilElementIsPresent(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS_WAITING_FOR_ELEMENT_PRESENT);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
