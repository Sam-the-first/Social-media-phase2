package Controllers;

import Enums.WarningMessage;
import Models.User;

public class ProfileController extends Controller{

    private User user;

    public ProfileController(User user) {
        this.user = user;
    }

    public WarningMessage followUnfollow(User user) {
        if(this.user.getFollowings().contains(user)) {
            this.user.removeFollowing(user);
            return WarningMessage.UNFOLLOWED_SUCCESSFULLY;
        }
        else {
            this.user.addFollowing(user);
            return WarningMessage.FOLLOWED_SUCCESSFULLY;
        }

    }

}
