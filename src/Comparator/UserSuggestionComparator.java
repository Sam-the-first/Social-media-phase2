package Comparator;

import Models.Chat;
import Models.Post;
import Models.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class UserSuggestionComparator implements Comparator<User> {

    User user;
    public UserSuggestionComparator(User user) {
        this.user=user;
    }

    @Override
    public int compare(User o1, User o2) {
        int a=score(o1);
        int b=score(o2);
        return a-b;
    }
    public int score(User user1)
    {
        int a=0;
        if(user.getFollowers().contains(user1))
            a++;
        a+=mutualFriend(user1);
        a+=likedPost(user1);
        a+=mutualChat(user1);
        return a;
    }

    private int mutualChat(User user1) {
        int a=0;
        for (Chat chat : user.getChats()) {
            if(chat.getUsers().contains(user1))
                a++;
        }
        return a;
    }

    private int likedPost(User user1) {
        int a=0;
        for (Post post : user1.getPosts()) {
            if(post.getLikeByUser(user)!=null)
                a++;
        }
        return a;
    }

    private int mutualFriend(User user1) {
        Set<User> mutual=new HashSet<>(user1.getFollowers());
        mutual.addAll(user1.getFollowings());
        Set<User> mutual2=new HashSet<>(user.getFollowers());
        mutual2.addAll(user.getFollowings());
        mutual.retainAll(mutual2);
        return mutual.size();
    }
}
