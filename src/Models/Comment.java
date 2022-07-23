package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Comment {

    private static int commentId = 1;
    private String text;
    private User commenter;
    private ArrayList<Comment> comments;
    private ArrayList<Like> likes;
    private LocalDateTime time;

    public Comment(String text, User commenter) {
        this.text = text;
        this.commenter = commenter;
        this.time = LocalDateTime.now();
        commentId++;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Like> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }

    public LocalDateTime getTime() {
        return time;
    }

}
