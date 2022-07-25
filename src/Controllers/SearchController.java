package Controllers;

import Comparator.PostComparator;
import Comparator.UserSearchComparator;
import Models.Post;
import Models.User;

import java.util.ArrayList;
import java.util.Collections;

public class SearchController {

    private User user;

    public SearchController(User user) {
        this.user=user;
    }

    public ArrayList<User> searchUsers(String searchKey) {
        ArrayList<User> users = User.users;
        ArrayList<User> containedUser = new ArrayList<>();
        for (User user1 : users) {
            if(user1 != user) {
                if (user1.getUsername().contains(searchKey)) {
                    containedUser.add(user1);
                } else if ((user.getFollowings().contains(user1) || user.getFollowers().contains(user1))
                        && ((user1.getLastname().contains(searchKey)) || user1.getFirstname().contains(searchKey))) {
                    containedUser.add(user1);
                }
            }
        }
        Collections.sort(containedUser,new UserSearchComparator(user,searchKey));
        return containedUser;
    }

    public ArrayList<Post> searchPosts(String searchKey) {
        ArrayList<Post> posts = Post.posts;
        ArrayList<Post> containedPosts = new ArrayList<>();
        for (Post post : posts) {
            if (post.getText().contains(searchKey))
                containedPosts.add(post);
        }

        Collections.sort(containedPosts,new PostComparator());
        return containedPosts;
    }
}
