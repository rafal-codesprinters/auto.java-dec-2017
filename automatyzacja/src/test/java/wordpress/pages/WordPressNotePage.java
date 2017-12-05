package wordpress.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wordpress.domain.Comment;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Rafal on 2017-12-05.
 */
public class WordPressNotePage extends WordPressPage {

    private static final By LOCATOR_COMMENT_BOX = By.id("comment");
    private static final By LOCATOR_COMMENT_POST_BUTTON = By.id("comment-submit");
    private static final By LOCATOR_EMAIL_PLACEHOLDER_LABEL = By.xpath("//label[@for='email']");
    private static final By LOCATOR_EMAIL_BOX = By.id("email");
    private static final By LOCATOR_AUTHOR_PLACEHOLDER_LABEL = By.xpath("//label[@for='author']");
    private static final By LOCATOR_AUTHOR_BOX = By.id("author");
    private static final By LOCATOR_COMMENT = By.cssSelector("article.comment-body");
    private static final By LOCATOR_COMMENT_REPLY_LINK = By.className("comment-reply-link");
    private static final By LOCATOR_AUTHOR_NAME_IN_COMMENT = By.tagName("cite");
    private static final By LOCATOR_TEXT_IN_COMMENT = By.cssSelector(".comment-content > p");
    private static final By LOCATOR_COMMENT_WITH_REPLY = By.xpath("//li[.//ul[@class='children']]");

    public WordPressNotePage(WebDriver driver) {
        super(driver);
    }

    private void enterCommentDataAndSubmit(Comment comment) {
        waitUntilElementIsVisible(LOCATOR_COMMENT_POST_BUTTON);

        WebElement commentBox = driver.findElement(LOCATOR_COMMENT_BOX);
        commentBox.click();
        commentBox.clear();
        commentBox.sendKeys(comment.getCommentText());

        WebElement emailLabel = driver.findElement(LOCATOR_EMAIL_PLACEHOLDER_LABEL);
        emailLabel.click();
        waitUntilElementIsHidden(LOCATOR_EMAIL_PLACEHOLDER_LABEL);

        WebElement emailBox = driver.findElement(LOCATOR_EMAIL_BOX);
        emailBox.click();
        emailBox.clear();
        emailBox.sendKeys(comment.getAuthorEmail());

        WebElement authorLabel = driver.findElement(LOCATOR_AUTHOR_PLACEHOLDER_LABEL);
        authorLabel.click();
        waitUntilElementIsHidden(LOCATOR_AUTHOR_PLACEHOLDER_LABEL);

        WebElement authorBox = driver.findElement(LOCATOR_AUTHOR_BOX);
        authorBox.click();
        authorBox.clear();
        authorBox.sendKeys(comment.getAuthorName());

        waitUntilElementIsClickable(LOCATOR_COMMENT_POST_BUTTON);
        driver.findElement(LOCATOR_COMMENT_POST_BUTTON).click();
    }

    private void enterReplyDataAndSubmit(Comment reply) {
        waitUntilElementIsVisible(LOCATOR_COMMENT_POST_BUTTON);

        WebElement commentBox = driver.findElement(LOCATOR_COMMENT_BOX);
        commentBox.click();
        commentBox.clear();
        commentBox.sendKeys(reply.getCommentText());

        WebElement emailBox = driver.findElement(LOCATOR_EMAIL_BOX);
        emailBox.click();
        emailBox.clear();
        emailBox.sendKeys(reply.getAuthorEmail());

        WebElement authorBox = driver.findElement(LOCATOR_AUTHOR_BOX);
        authorBox.click();
        authorBox.clear();
        authorBox.sendKeys(reply.getAuthorName());

        waitUntilElementIsClickable(LOCATOR_COMMENT_POST_BUTTON);
        driver.findElement(LOCATOR_COMMENT_POST_BUTTON).click();
    }

    private By commentLocatorByAuthor(Comment comment) {
        return By.xpath("//article//cite[text()='" + comment.getAuthorName() + "']");
    }

    private Stream<WebElement> findComments(Comment comment) {
        Stream<WebElement> comments = driver.findElements(LOCATOR_COMMENT).stream();
        return comments
                .filter(element -> element.findElement(LOCATOR_AUTHOR_NAME_IN_COMMENT).getText().equals(comment.getAuthorName()))
                .filter(element -> element.findElement(LOCATOR_TEXT_IN_COMMENT).getText().equals(comment.getCommentText()));
    }

    public void addComment(Comment comment) {
        waitUntilElementIsPresent(LOCATOR_COMMENT_BOX);
        WebElement commentBox = driver.findElement(LOCATOR_COMMENT_BOX);
        commentBox.click();
        enterCommentDataAndSubmit(comment);
        waitForCommentsProcessing(comment);
    }

    private void waitForCommentsProcessing(Comment comment) {
        try {
            waitUntilElementIsPresent(commentLocatorByAuthor(comment));
        } catch (TimeoutException exception) {
            driver.navigate().refresh();
            waitUntilElementIsPresent(commentLocatorByAuthor(comment));
        }
    }

    public void addReplyToComment(Comment comment, Comment reply) throws Exception {
        List<WebElement> comments = findComments(comment).collect(Collectors.toList());

        if (comments.size() > 1) throw new Exception("Multiple comments found while only single comment is expected");
        if (comments.size() == 0) throw new Exception("No comment found");

        WebElement replyLink = comments.get(0).findElement(LOCATOR_COMMENT_REPLY_LINK);
        replyLink.click();
        enterReplyDataAndSubmit(reply);
        waitForCommentsProcessing(reply);
    }

    public boolean checkCommentExists(Comment comment) {
        return findComments(comment).count() == 1;

    }

    public boolean checkReplyExists(Comment comment, Comment reply) throws Exception {
        List<WebElement> commentsWithReplies = driver.findElements(LOCATOR_COMMENT_WITH_REPLY).stream()
                .filter(element -> element.findElement(LOCATOR_AUTHOR_NAME_IN_COMMENT).getText().equals(comment.getAuthorName()))
                .filter(element -> element.findElement(LOCATOR_TEXT_IN_COMMENT).getText().equals(comment.getCommentText()))
                .collect(Collectors.toList());

        if (commentsWithReplies.size() > 1) throw new Exception("Multiple comments with replies found while only single comment is expected");
        if (commentsWithReplies.size() == 0) throw new Exception("No comment with reply found");

        return commentsWithReplies.get(0).findElements(LOCATOR_COMMENT).stream()
                .filter(element -> element.findElement(LOCATOR_AUTHOR_NAME_IN_COMMENT).getText().equals(reply.getAuthorName()))
                .filter(element -> element.findElement(LOCATOR_TEXT_IN_COMMENT).getText().equals(reply.getCommentText())).count() == 1;

    }
}
