package wordpress.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Rafal on 2017-12-05.
 */
public class WordPressMainPage extends WordPressPage {

    public static final By LOCATOR_NOTE_TITLE = By.cssSelector(".entry-title > a");

    public WordPressMainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(WORDPRESS_ADDREESS);
    }

    public WordPressNotePage openLatestNote() {
        driver.findElement(LOCATOR_NOTE_TITLE).click();
        return new WordPressNotePage(driver);
    }
}
