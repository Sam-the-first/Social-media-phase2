package Views;

import Controllers.PersonalController;
import Controllers.WelcomeController;
import Enums.WarningMessage;
import Models.User;

public class PersonalMenu extends Menu{

    private User user;
    private PersonalController controller;
    private LoggedInMenu loggedInMenu;

    PersonalMenu(String username, LoggedInMenu loggedInMenu) {
        user = User.getUserByUsername(username);
        this.loggedInMenu = loggedInMenu;
        controller = new PersonalController(user);
    }

    @Override
    public void run() {

        this.showOptions();

        String choice = this.getChoice();

        switch (choice) {
            case "1":
                this.changeUsername();
                break;
            case "2":
                changePassword();
                break;
            case "3":
                changeBio();
                break;
            case "4":
                changeName();
                break;
            case "5":
                changeSecurityQuestionAnswer();
                break;
            case "6":
                changeBirthDate();
                break;
            case "7":
                backToLoggedInMenu();
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                this.run();
        }
    }

    private void changeUsername() {
        String username = this.getInput("Enter a Valid new Username");
        System.out.println(controller.handleUsernameChange(LoggedInMenu.getLoggedInUser(),username));
        this.run();
    }

    private void changePassword() {
        String password=this.getInput("Enter a new Password");
        String repeatedPassword=this.getInput("Repeat your new password");
        WarningMessage message = WelcomeController.getInstance().validatePassword(password,repeatedPassword);
        System.out.println(message == WarningMessage.SUCCESS ? WelcomeController.getInstance().handlePasswordChange(LoggedInMenu.getLoggedInUser().getUsername(),password) : message);
        this.run();
    }

    private void changeBio() {
        String bio =getInput("Enter your new bio");
        System.out.println(controller.handleBioChange(bio));
        this.run();
    }

    private void changeName() {
        String firstname=getInput("Enter a new firstname");
        String lastname=getInput("Enter a new lastname");
        System.out.println(controller.handleNameChange(firstname,lastname));
        this.run();
    }

    //TO_DO
    private void changeSecurityQuestionAnswer() {
        String securityQuestion = getInput(User.SECURITY_QUESTION);
        this.run();
    }
    //TO_DO
    private void changeBirthDate() {
        this.run();
    }

    private void backToLoggedInMenu() {
        loggedInMenu.run();
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Change Username");
        System.out.println("2. Change Password");
        System.out.println("3. Change bio");
        System.out.println("4. Change Name");
        System.out.println("5. Change SecurityQuestion");
        System.out.println("6. Change BirthDate");
        System.out.println("7. Previous Menu");
    }
}
