package wordpress.tests;

import org.junit.Assert;
import org.junit.Test;
import wordpress.domain.Comment;
import wordpress.pages.WordPressMainPage;
import wordpress.pages.WordPressNotePage;

/**
 * Created by Rafal on 2017-12-05.
 */
public class CommentsTests extends BaseTest {

    @Test
    public void verifyCommentCanBeAddedAnonymously() {
        Comment comment = new Comment();

        WordPressNotePage notePage = addCommentsToFirstNote(comment);

        Assert.assertTrue("Comment added to the first note", notePage.checkCommentExists(comment));
    }

    @Test
    public void verifyCommentCanBeAddedToComment() throws Exception {
        Comment comment = new Comment();
        Comment reply = new Comment();
        WordPressNotePage notePage = addCommentsToFirstNote(comment);

        notePage.addReplyToComment(comment, reply);

        Assert.assertTrue("Reply can be added to the comment", notePage.checkReplyExists(comment, reply));
    }

    private WordPressNotePage addCommentsToFirstNote(Comment comment) {
        WordPressMainPage mainPage = new WordPressMainPage(driver);
        mainPage.open();
        WordPressNotePage notePage = mainPage.openLatestNote();
        notePage.addComment(comment);
        return notePage;
    }

}
