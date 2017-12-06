package wordpress.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Rafal on 2017-12-06.
 */
public class WordPressAdminPage extends WordPressPage {
    private @FindBy(id = "wpadminbar") List<WebElement> adminBar;

    public WordPressAdminPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isOpen() {
        // List<WebElement> adminBar = driver.findElements(By.id("wpadminbar"));
        return adminBar.size() == 1;
    }
}
