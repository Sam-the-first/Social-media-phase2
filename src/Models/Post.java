package Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Post extends Comment{

    public static ArrayList<Post> posts = new ArrayList<>();

    private static int postId = 1;
    private ArrayList<User> viewers;


    public Post(String text, User commenter) {
        super(text, commenter);
        postId++;
        Post.posts.add(this);
        commenter.addPosts(this);
    }

    public static Post getPostByText (String text) {
        for (Post post : posts) {
            if (post.getText().equals(text))
                return post;
        }
        return null;
    }


    public boolean isAfter(Post post) {
        if(getTime().isAfter(post.getTime()))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "POST: \n" +
                "User: " + getCommenter() + "\n" +
                "Text: " + getText() + "\n" +
                "Time: " + getFormattedDateTime() + "\n" +
                "Count of likes: " + getLikes().size() + "\n" +
                "Count of comments: " + getComments().size() + "\n" +
                "Viewers: " + viewers;
    }

}
