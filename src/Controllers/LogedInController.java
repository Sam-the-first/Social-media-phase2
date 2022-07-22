package Controllers;

import Enums.Message;
import Models.User;

import java.util.ArrayList;
import java.util.Collections;

public class LogedInController {
    private User user;
    private WelcomeController welcomeController = WelcomeController.getInstance();

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
    public Message handleBioChange(String newBio)
    {
        if(newBio.length()<=StaticData.MaxBioLenth)
        {
            user.setBio(newBio);
            return Message.SUCCESS;
        }
        return Message.LONG_BIO;
    }
    public Message handleNameChange(String firstname,String lastname)
    {
        user.setFirstname(firstname);
        user.setLastname(lastname);
        return Message.SUCCESS;
    }
    public ArrayList<User> searchUsers(String searchKey)
    {
        ArrayList<User> users=User.users;
        ArrayList<User> containedUser=new ArrayList<>();
        for (User user1 : users) {
            if(user1.getUsername().contains(searchKey))
            {
                containedUser.add(user1);
            }
            else if((user.getFollowings().contains(user1)&&(user1.getLastname().contains(searchKey))||user1.getFirstname().contains(searchKey))
                    ||(user1.getFollowers().contains(user1)&&(user1.getLastname().contains(searchKey))||user1.getFirstname().contains(searchKey)))
            {
                containedUser.add(user1);
            }
        }
       // Collections.sort(containedUser,User.searchComprator);
        return containedUser;
    }
}
