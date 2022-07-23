package Controllers;

import Enums.WarningMessage;
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

    public WarningMessage handleCreateUser(String username, String password, String repeatedPassword, String firstname, String lastname, String bio, String birthDate, String securityQuestionAnswer,int type) {
        if (this.doesUserExist(username)) {
            return WarningMessage.USERNAME_EXIST;
        }
        WarningMessage message;
        if ((message = this.validatePassword(password, repeatedPassword)) != WarningMessage.SUCCESS) {
            return message;
        }
        LocalDate birthDay = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String accountType = null;
        if(type == 1) {
            accountType = "Normal";
            new User(firstname, lastname, username, password, bio, birthDay, securityQuestionAnswer, accountType);
        }
        else if(type == 2) {
            accountType = "Business";
            new BusinessAccount(firstname, lastname, username, password, bio, birthDay, securityQuestionAnswer,accountType);
        }
       // new User(firstname, lastname, username, password, bio, birthDay, securityQuestionAnswer, accountType);
        /*String query = " insert into users (username, password, first_name, last_name," +
                " bio, birth_date, security_answer, account_type)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = Jdbc.getInstance().openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstname);
            preparedStatement.setString(4, lastname);
            preparedStatement.setString(5, bio);
            preparedStatement.setString(6, birthDate);
            preparedStatement.setString(7, securityQuestionAnswer);
            preparedStatement.setString(8, accountType);
            preparedStatement.execute();
            Jdbc.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return WarningMessage.SUCCESS;
    }

    public WarningMessage validatePassword(String password, String repeatedPassword) {
        if (!password.equals(repeatedPassword))
            return WarningMessage.MISMATCH_PASSWORD;
        if (password.length() < 8)
            return WarningMessage.SHORT_PASSWORD;
        if (password.length() > 20)
            return WarningMessage.LONG_PASSWORD;
        if (!this.isAlphaNumeric(password))
            return WarningMessage.NON_ALPHA_NUMERIC_PASSWORD;

        return WarningMessage.SUCCESS;
    }

    private boolean isAlphaNumeric(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
    }

    public WarningMessage handleLogin(String username, String password) {
        if (!this.doesUserExist(username)) {
            return WarningMessage.USER_DOES_NOT_EXIST;
        }

        User user = User.getUserByUsername(username);
        if (user.getPassword().equals(password)) {
            Menu.setLoggedInUser(user);
            return WarningMessage.SUCCESS;
        }
        return WarningMessage.INCORRECT_PASSWORD;
    }

    public WarningMessage handlePasswordChange(String username ,String password)
    {
        WarningMessage message;
        if ((message = this.validatePassword(password, password)) != WarningMessage.SUCCESS) {
            return message;
        }
        User user=User.getUserByUsername(username);
        user.setPassword(password);
        return WarningMessage.SUCCESS;
    }

    boolean doesUserExist(String username) {
        return User.getUserByUsername(username) != null;
    }

    public WarningMessage checkSecurityAnswer(String username, String answer) {
        User user = User.getUserByUsername(username);

        if (user.getSecurityAnswer().equals(answer)) {
            return WarningMessage.SUCCESS;
        }
        return WarningMessage.WRONG_ANSWER;
    }

}
