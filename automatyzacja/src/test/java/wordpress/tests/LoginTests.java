package wordpress.tests;

import org.junit.Assert;
import org.junit.Test;
import wordpress.pages.WordPressAdminPage;
import wordpress.pages.WordPressLoginPage;

/**
 * Created by Rafal on 2017-12-06.
 */
public class LoginTests extends BaseTest {
    @Test
    public void verifyUserCanLogin() {
        WordPressLoginPage loginPage = new WordPressLoginPage(driver);
        String admin = "autotestjava@gmail.com";
        String adminPassword = "P@ssw0rd1";
        WordPressAdminPage adminPage = loginPage.logIn(admin, adminPassword);
        Assert.assertTrue("Admin page is loaded", adminPage.isOpen());
    }
}
