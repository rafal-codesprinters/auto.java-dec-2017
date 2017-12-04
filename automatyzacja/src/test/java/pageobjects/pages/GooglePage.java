package pageobjects.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by Rafal on 2017-12-04.
 */
public abstract class GooglePage {
    protected static final String GOOGLE_ADDRESS = "http://www.google.pl/";
    protected WebDriver driver;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }
}
