package Controllers;

import Models.User;
import Views.LoggedInMenu;

import java.util.ArrayList;
import java.util.Collections;

public class SearchController {

    /*private static SearchController instance = null;
    private User user = LoggedInMenu.getLoggedInUser();

    private SearchController() {}

    private static void setInstance(SearchController instance) {
        SearchController.instance = instance;
    }

    public static SearchController getInstance() {
        if (SearchController.instance == null)
            SearchController.setInstance(new SearchController());

        return SearchController.instance;
    }*/

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
        Collections.sort(containedUser,new UserComparator(user,searchKey));
        return containedUser;
    }

    public void FollowUnfollow(User user) {
        if(this.user.getFollowings().contains(user)) {
            this.user.removeFollowing(user);
        }
        else {
            this.user.addFollowing(user);
        }
    }
}
