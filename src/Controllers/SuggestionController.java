package Controllers;

import Comparator.UserSuggestionComparator;
import Models.User;

import java.util.ArrayList;
import java.util.Collections;

public class SuggestionController {
    User user;

    public SuggestionController(User user) {
        this.user = user;
    }

    public ArrayList<User> getSuggestedUser() {
        ArrayList<User> users=new ArrayList<>(User.users);
        users.removeAll(user.getFollowings());
        Collections.sort(users,new UserSuggestionComparator(user));
        return users;
    }
}
