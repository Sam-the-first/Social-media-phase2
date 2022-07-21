package Controllers;

import Enums.Message;
import Models.BusinessAccount;
import Models.User;
import Views.Menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WelcomeController extends Controller{

    private static WelcomeController instance = null;

    private WelcomeController() {

    }

    private static void setInstance(WelcomeController instance) {
        WelcomeController.instance = instance;
    }

    public static WelcomeController getInstance() {
        if (WelcomeController.instance == null) {
            WelcomeController.setInstance(new WelcomeController());
        }

        return WelcomeController.instance;
    }

    public Message handleCreateUser(String username, String password, String repeatedPassword, String firstname, String lastname, String bio, String birthDate, String securityQuestionAnswer,int type) {
        if (this.doesUserExist(username)) {
            return Message.USERNAME_EXIST;
        }
        Message message;
        if ((message = this.validatePassword(password, repeatedPassword)) != Message.SUCCESS) {
            return message;
        }
        LocalDate birthDay;
        try {
             birthDay = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }
        catch (Exception e)
        {
            return Message.Invalid_BIRTH_DATE;
        }
        if(type==1)
        {
            new User(firstname, lastname, username, password, bio, birthDay, securityQuestionAnswer);
        }
        else if(type==2)
        {
            new BusinessAccount(firstname, lastname, username, password, bio, birthDay, securityQuestionAnswer);
        }
        return Message.SUCCESS;
    }

    public Message validatePassword(String password, String repeatedPassword) {
        if (!password.equals(repeatedPassword))
            return Message.MISMATCH_PASSWORD;
        if (password.length() < 8)
            return Message.SHORT_PASSWORD;
        if (password.length() > 20)
            return Message.LONG_PASSWORD;
        if (!this.isAlphaNumeric(password))
            return Message.NON_ALPHA_NUMERIC_PASSWORD;

        return Message.SUCCESS;
    }

    private boolean isAlphaNumeric(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
    }

    public Message handleLogin(String username, String password) {
        if (!this.doesUserExist(username)) {
            return Message.USER_DOES_NOT_EXIST;
        }

        User user = User.getUserByUsername(username);
        if (user.getPassword().equals(password)) {
            Menu.setLoggedInUser(user);
            return Message.SUCCESS;
        }
        return Message.INCORRECT_PASSWORD;
    }
    public Message handlePasswordChange(String username ,String password)
    {
        Message message;
        if ((message = this.validatePassword(password, password)) != Message.SUCCESS) {
            return message;
        }
        User user=User.getUserByUsername(username);
        user.setPassword(password);
        return Message.SUCCESS;
    }


    boolean doesUserExist(String username) {
        return User.getUserByUsername(username) != null;
    }

    public Message checkSecurityAnswer(String username, String answer) {
        User user = User.getUserByUsername(username);

        if (user.getSecurityAnswer().equals(answer)) {
            return Message.SUCCESS;
        }
        return Message.WRONG_ANSWER;
    }

}
