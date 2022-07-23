package Controllers;

import Enums.WarningMessage;
import Models.Post;
import Models.User;
import Views.LoggedInMenu;

public class PostController extends Controller{

    /*private static PostController instance = null;
    private User user = LoggedInMenu.getLoggedInUser();

    private PostController() {}

    private static void setInstance (PostController instance) {
        PostController.instance = instance;
    }

    public static PostController getInstance() {
        if (PostController.instance == null) {
            PostController.setInstance(new PostController());
        }
        return PostController.instance;
    }*/

    private User user;

    public PostController(User user) {
        this.user=user;
    }

    public WarningMessage handlePost(String text) {
        if (text.length() > 1200)
            return WarningMessage.LONG_TEXT;
        new Post(text, user);
        return WarningMessage.SUCCESS;
    }

    public WarningMessage handleDeletePost(String text) {
        if (!doesPostExist(text)) {
            return WarningMessage.POST_DOES_NOT_EXIST;
        }
        Post post = Post.getPostByText(text);
        Post.posts.remove(post);
        return WarningMessage.SUCCESS;
    }

    public String handleShowPost(String text) {
        if (!doesPostExist(text)) {
            return "post does not exist";
        }
        Post post = Post.getPostByText(text);
        return post.toString();
    }

    boolean doesPostExist (String text) {
        return Post.getPostByText(text) != null;
    }
}
