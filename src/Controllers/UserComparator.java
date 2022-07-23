package Controllers;

import Models.User;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {
    User user;
    String searchKey;
    UserComparator(User user, String searchKey)
    {
        this.user=user;
        this.searchKey=searchKey;
    }
    @Override
    public int compare(User a, User b) {
        int a1=0,b1=0;
        if(user.getFollowers().contains(a))
            a1++;
        if(user.getFollowings().contains(a))
            a1++;
        if(a.getFirstname().equals(searchKey))
            a1++;
        if(a.getLastname().equals(searchKey))
            a1++;
        if(a.getUsername().equals(searchKey))
            a1++;
        if(user.getFollowers().contains(b))
            b1++;
        if(user.getFollowings().contains(b))
            b1++;
        if(b.getFirstname().equals(searchKey))
            b1++;
        if(b.getLastname().equals(searchKey))
            b1++;
        if(b.getUsername().equals(searchKey))
            b1++;
        return a1-b1;
    }
}
