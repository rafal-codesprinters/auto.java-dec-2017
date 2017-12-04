package pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Rafal on 2017-12-04.
 */
public abstract class GooglePage {
    protected static final String GOOGLE_ADDRESS = "http://www.google.pl/";
    private static final int TIMEOUT_IN_SECONDS_WAITING_FOR_ELEMENT = 15;
    protected WebDriver driver;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void waitUntilElementIsVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS_WAITING_FOR_ELEMENT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
