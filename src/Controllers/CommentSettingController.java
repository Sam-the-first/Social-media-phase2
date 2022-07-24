package Controllers;

import Models.Comment;
import Models.Post;
import Models.User;

import java.util.ArrayList;

public class CommentSettingController extends Controller{

    private User user;
    private Post post;
    private Comment comment;

    public CommentSettingController(User user, Post post, Comment comment) {
        this.user = user;
        this.post = post;
        this.comment = comment;
    }

    public void handleDeletingComment() {
        ArrayList<Comment> comments = new ArrayList<>(post.getComments());
        comments.remove(comment);
        post.setComments(comments);
    }

    public void handleEditingComment(String text) {
        comment.setText(text);
    }
}
