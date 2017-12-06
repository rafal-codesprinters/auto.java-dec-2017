package wordpress.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Rafal on 2017-12-06.
 */
public class WordPressLoginPage extends WordPressPage {

    private static final String WORDPRESS_LOGIN_PAGE_ADDRESS = WORDPRESS_ADDREESS + "wp-login.php";
    private @FindBy(id = "usernameOrEmail") WebElement userNameInputBox;
    private @FindBy(id = "password") WebElement userPasswordBox;

    public WordPressLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WordPressAdminPage logIn(String userName, String passWord) {
        driver.get(WORDPRESS_LOGIN_PAGE_ADDRESS);

        // WebElement userNameInputBox = driver.findElement(By.id("usernameOrEmail"));
        writeInto(userName, userNameInputBox);
        userNameInputBox.submit();

        waitUntilElementIsClickable(userPasswordBox);

        // WebElement userPasswordBox = driver.findElement(By.id("password"));
        writeInto(passWord, userPasswordBox);
        userPasswordBox.submit();

        return new WordPressAdminPage(driver);
    }

}
