package Controllers;

import Comparator.PostComparator;
import Enums.WarningMessage;
import Models.Post;
import Models.User;

import java.util.ArrayList;
import java.util.Collections;

public class LoggedInController extends Controller{

    private User user;

    public LoggedInController(User user) {
        this.user=user;
    }

    public WarningMessage handlePost(String text, User user) {
        if (text.length() > 1200)
            return WarningMessage.LONG_TEXT;
        Post post = new Post(text, user);
        ArrayList<Post> posts = new ArrayList<>(user.getPosts());
        posts.add(post);
        user.setPosts(posts);
        return WarningMessage.SUCCESS;
    }

    public ArrayList<Post> handleShowPosts(User user) {
        ArrayList<Post> posts = user.getPosts();
        Collections.sort(posts, new PostComparator());
        return posts;
    }
}
