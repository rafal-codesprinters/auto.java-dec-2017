package google.pageobjects;

import google.domain.Google;
import org.openqa.selenium.WebDriver;

/**
 * Created by Kuba on 2017-12-02.
 */
public class BasePage {
    public static GoogleMainPage OpenGoogleMainPage(WebDriver driver) {
        driver.get(Google.PAGE_URL);
        return new GoogleMainPage(driver);
    }
}
