package Models;

import java.util.ArrayList;

public class Like {

    private User user;

    public static ArrayList<Like> likes;

    public Like(User user) {
        this.user = user;
        likes.add(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
