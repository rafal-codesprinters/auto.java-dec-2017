package wordpress.domain;

import java.util.UUID;

/**
 * Created by Rafal on 2017-12-05.
 */
public class User {
    private String userName;
    private String userEmail;

    public User() {
        this.userName = UUID.randomUUID().toString();
        this.userEmail = UUID.randomUUID().toString() + "@test.com";
    }

    public String getEmail() {
        return this.userEmail;
    }

    public String getName() {
        return this.userName;
    }
}
