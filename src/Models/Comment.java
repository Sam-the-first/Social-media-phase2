package Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Comment {

    private static int commentId = 1;
    private String text;
    private User commenter;
    private ArrayList<Comment> comments;
    private ArrayList<Like> likes;
    private LocalDateTime time;
    private String formattedDateTime;

    public static ArrayList<Comment> allComments;

    public Comment(String text, User commenter) {
        this.text = text;
        this.commenter = commenter;
        this.time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.formattedDateTime = time.format(formatter);
        commentId++;
        allComments.add(this);
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

    public String getFormattedDateTime() {
        return formattedDateTime;
    }

    public void setFormattedDateTime(String formattedDateTime) {
        this.formattedDateTime = formattedDateTime;
    }

    public Like getLikeByUser(User user) {
        for (Like like : likes) {
            if (like.getUser().equals(user))
                return like;
        }
        return null;
    }

    public ArrayList<Comment> getCommentsByUser(User user) {
        ArrayList<Comment> arrayList = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getCommenter().equals(user))
                arrayList.add(comment);
        }
        return arrayList;
    }
}
