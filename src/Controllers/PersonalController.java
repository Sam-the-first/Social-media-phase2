package Controllers;

import Enums.WarningMessage;
import Models.User;

public class PersonalController {

    private User user;

    public PersonalController(User user) {
        this.user = user;
    }

    public WarningMessage handleUsernameChange(User user, String newUsername) {
        if(WelcomeController.getInstance().doesUserExist(newUsername))
            return WarningMessage.USER_EXIST;

        user.setUsername(newUsername);

        return WarningMessage.USERNAME_CHANGED_SUCCESSFULLY;
    }

    public WarningMessage handleBioChange(String newBio) {
        if(newBio.length() <= 1200) {
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
