package Controllers;

import Enums.WarningMessage;
import Models.User;

public class ProfileController {

    /*private static ProfileController instance = null;
    private User user = LoggedInMenu.getLoggedInUser();

    private ProfileController() {

    }

    private static void setInstance(ProfileController instance) {
        ProfileController.instance = instance;
    }

    public static ProfileController getInstance() {
        if (ProfileController.instance == null)
            ProfileController.setInstance(new ProfileController());

        return ProfileController.instance;
    }*/

    private User user;

    public ProfileController(User user) {
        this.user = user;
    }

    public WarningMessage handleUsernameChange(User user, String newUsername) {
        if(WelcomeController.getInstance().doesUserExist(newUsername))
            return WarningMessage.USER_EXIST;

        user.setUsername(newUsername);

        return WarningMessage.USERNAME_CHANGED_SUCCESSFULLY;
    }

    public WarningMessage handleBioChange(String newBio) {
        if(newBio.length()<=StaticData.MaxBioLength) {
            user.setBio(newBio);
            return WarningMessage.SUCCESS;
        }

        return WarningMessage.LONG_BIO;
    }

    public WarningMessage handleNameChange(String firstname, String lastname) {
        user.setFirstname(firstname);
        user.setLastname(lastname);

        return WarningMessage.SUCCESS;
    }

}
