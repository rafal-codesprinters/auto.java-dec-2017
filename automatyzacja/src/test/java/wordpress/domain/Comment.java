package wordpress.domain;

import java.util.UUID;

/**
 * Created by Rafal on 2017-12-05.
 */
public class Comment {
    private User user;
    private String commentText;

    public Comment() {
        this.user = new User();
        this.commentText = generateRandomCommentText();
    }

    public Comment(User user) {
        this.user = user;
        this.commentText = generateRandomCommentText();
    }

    private String generateRandomCommentText() {
        return UUID.randomUUID().toString();
    }

    public Comment(User user, String commentText) {
        this.user = user;
        this.commentText = commentText;
    }

    public String getAuthorName() {
        return user.getName();
    }

    public String getAuthorEmail() {
        return user.getEmail();
    }

    public String getCommentText() {
        return commentText;
    }

    public User getUser() {
        return user;
    }
}
