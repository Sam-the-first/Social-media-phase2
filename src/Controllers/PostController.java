package Controllers;

import Enums.WarningMessage;
import Models.Comment;
import Models.Like;
import Models.Post;
import Models.User;

import java.util.ArrayList;

public class PostController extends Controller{

    private User user;
    private Post post;

    public PostController(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public void handleLike() {
        Like like = new Like(user);
        Like.likes.add(like);
        ArrayList<Like> likes = new ArrayList<>(post.getLikes());
        likes.add(like);
        post.setLikes(likes);
    }

    public void handleDeleteLike() {
        if (post.getLikeByUser(user) != null) {
            ArrayList<Like> likes = new ArrayList<>(post.getLikes());
            likes.remove(post.getLikeByUser(user));
            post.setLikes(likes);
        }
        else
            System.out.println(WarningMessage.YOU_HAVE_NOT_LIKE_IT_YET);
    }

    public void handleComment(String text) {
        Comment comment = new Comment(text, user);
        Comment.allComments.add(comment);
        ArrayList<Comment> comments = new ArrayList<>(post.getComments());
        comments.add(comment);
        post.setComments(comments);
    }

    public void handleDeleteComment() {
        if (!post.getCommentsByUser(user).isEmpty()) {
            ArrayList<Comment> comments = new ArrayList<>(post.getComments());
            comments.removeAll(post.getCommentsByUser(user));
            post.setComments(comments);
        }
        else
            System.out.println(WarningMessage.YOU_HAVE_NOT_COMMENT_FOR_IT_YET);
    }

    public int countOfLikes() {
        return post.getLikes().size();
    }

    public ArrayList<Comment> handleShowComments() {
        return post.getComments();
    }

}
