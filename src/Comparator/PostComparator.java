package Comparator;

import Models.Post;

import java.util.Comparator;

public class PostComparator implements Comparator<Post> {
    @Override
    public int compare(Post o1, Post o2) {
        if (o1.isAfter(o2))
            return 1;
        else
            return -1;
    }
}
