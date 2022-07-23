package Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Post extends Comment{

    public static ArrayList<Post> posts = new ArrayList<>();

    private static int postId = 1;
    private LocalDateTime time;
    private String formattedDateTime;
    private ArrayList<User> viewers;


    public Post(String text, User commenter) {
        super(text, commenter);
        this.time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.formattedDateTime = time.format(formatter);
        postId++;
        Post.posts.add(this);
    }

    public static Post getPostByText (String text) {
        for (Post post : posts) {
            if (post.getText().equals(text))
                return post;
        }
        return null;
    }

    @Override
    public String toString() {
        return "POST: \n" +
                "User: " + getCommenter() + "\n" +
                "Text: " + getText() + "\n" +
                "Time: " + formattedDateTime + "\n" +
                "Count of likes: " + getLikes().size() + "\n" +
                "Count of comments: " + getComments().size() + "\n" +
                "Viewers: " + viewers;
    }
}
