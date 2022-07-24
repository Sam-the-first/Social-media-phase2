package Controllers;

import Models.Post;
import Models.User;

public class PostSettingController extends Controller{

    private User user;

    public PostSettingController(User user) {
        this.user=user;
    }

    public void handleDeletePost(Post post) {
        Post.posts.remove(post);
    }

    public void handleEditPost(Post post, String text) {
        post.setText(text);
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
