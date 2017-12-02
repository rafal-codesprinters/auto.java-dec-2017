package google.tests;

import google.domain.CodeSprinters;
import google.pageobjects.BasePage;
import google.pageobjects.GoogleMainPage;
import google.pageobjects.GoogleResultsPage;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Kuba on 2017-12-02.
 */
public class SearchTests extends BaseTests {
    @Test
    public void verifyCodeSprintersPageIsFound() {
        GoogleMainPage mainPage = BasePage.OpenGoogleMainPage(driver);

        GoogleResultsPage resultsPage = mainPage.SearchFor(CodeSprinters.PAGE_NAME);

        Assert.assertTrue("Found at least one page with URL " + CodeSprinters.PAGE_URL, resultsPage.countResultsWithSpecificUrl(CodeSprinters.PAGE_URL) > 0);
    }

    @Test
    public void verifyCodeSprintersPageIsNotFoundOnSecondResultsPage() {
        GoogleMainPage mainPage = BasePage.OpenGoogleMainPage(driver);

        GoogleResultsPage resultsPage = mainPage.SearchFor(CodeSprinters.PAGE_NAME);
        GoogleResultsPage nextResultsPage = resultsPage.displayNextResultsPage();

        Assert.assertTrue("Found no pages with URL containing " + CodeSprinters.PAGE_URL, nextResultsPage.CountResultsWithUrlStartingWith(CodeSprinters.PAGE_URL) == 0);
    }

}
