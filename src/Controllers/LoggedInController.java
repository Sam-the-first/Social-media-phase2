package Controllers;

import Comparator.PostComparator;
import Enums.WarningMessage;
import Models.Group;
import Models.Post;
import Models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class LoggedInController extends Controller{

    private User user;

    public LoggedInController(User user) {
        this.user=user;
    }

    public WarningMessage handlePost(String text, User user) {
        if (text.length() > 1200)
            return WarningMessage.LONG_TEXT;
        Post post = new Post(text, user);
        return WarningMessage.SUCCESS;
    }

    public ArrayList<Post> handleShowPosts(User user) {
        ArrayList<Post> posts = user.getPosts();
        Collections.sort(posts, new PostComparator());
        return posts;
    }
    public WarningMessage hadnleCreateGroup(User user, ArrayList<User> users, String name, String description)
    {
        Group group = new Group(user,users,name,description);
      //  user.addChat(group);
       return WarningMessage.GROUP_CREATED_SUCCESSFULLY;
    }
}
