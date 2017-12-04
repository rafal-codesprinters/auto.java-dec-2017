package pageobjects.tests;

import org.junit.Assert;
import org.junit.Test;
import pageobjects.domain.CodeSprinters;
import pageobjects.pages.GoogleMainPage;
import pageobjects.pages.GoogleResultPage;

/**
 * Created by Rafal on 2017-12-04.
 */
public class GoogleSearchTests extends BaseTest {
    @Test
    public void verifyGoogleFindsCodeSprintersPage() {
        GoogleResultPage resultPage = searchOnGoogle(CodeSprinters.PAGE_NAME);
        Assert.assertTrue("'" + CodeSprinters.PAGE_NAME + "' page found", resultPage.countResultWithUrl(CodeSprinters.PAGE_URL) > 0);
    }

    @Test
    public void verifyGoogleShowsNoCodeSprinterOnSecondPage() {
        GoogleResultPage resultPage = searchOnGoogle(CodeSprinters.PAGE_NAME);
        GoogleResultPage secondResultPage = resultPage.displayNextResultsPage();
        Assert.assertTrue("Pages with URL starting with'" + CodeSprinters.PAGE_URL + "' not found",
                secondResultPage.countResultsWithUrlMatching(CodeSprinters.PAGE_URL) == 0);
    }

    private GoogleResultPage searchOnGoogle(String pageName) {
        GoogleMainPage mainPage = new GoogleMainPage(driver);
        mainPage.open();
        return mainPage.searchFor(pageName);
    }
}
