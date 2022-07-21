package Controllers;

import Enums.Message;
import Models.User;

public class LogedInController {
    private User user;
    private WelcomeController welcomeController=WelcomeController.getInstance();

   public LogedInController(User user)
    {
        this.user=user;
    }
    public Message handleUsernameChange(User user, String newUsername)
    {
        if(welcomeController.doesUserExist(newUsername))
        {
            return Message.USER_EXIST;
        }
        user.setUsername(newUsername);
        return Message.USERNAME_CHANGED_SUCCESSFULLY;
    }
}
