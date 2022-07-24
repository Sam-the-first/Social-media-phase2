package Controllers;

import Enums.WarningMessage;
import Models.Comment;
import Models.Like;
import Models.User;

import java.util.ArrayList;

public class CommentController extends Controller{

    private User user;
    private Comment comment;

    public CommentController(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }

    public void handleLike() {
        Like like = new Like(user);
        Like.likes.add(like);
        ArrayList<Like> likes = new ArrayList<>(comment.getLikes());
        likes.add(like);
        comment.setLikes(likes);
    }

    public void handleDeleteLike() {
        if (comment.getLikeByUser(user) != null) {
            ArrayList<Like> likes = new ArrayList<>(comment.getLikes());
            likes.remove(comment.getLikeByUser(user));
        }
        else
            System.out.println(WarningMessage.YOU_HAVE_NOT_LIKE_IT_YET);
    }

    public void handleComment(String text) {
        Comment comment = new Comment(text, user);
        Comment.allComments.add(comment);
        ArrayList<Comment> comments = new ArrayList<>(comment.getComments());
        comments.add(comment);
        comment.setComments(comments);
    }

    public void handleDeleteComment() {
        if (!comment.getCommentsByUser(user).isEmpty()) {
            ArrayList<Comment> comments = new ArrayList<>(comment.getComments());
            comments.removeAll(comment.getCommentsByUser(user));
            comment.setComments(comments);
        }
        else
            System.out.println(WarningMessage.YOU_HAVE_NOT_COMMENT_FOR_IT_YET);
    }

    public int countOfLikes() {
        return comment.getComments().size();
    }

    public ArrayList<Comment> handleShowComments() {
        return comment.getComments();
    }
}
