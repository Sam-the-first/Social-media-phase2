package Views;

import Controllers.WelcomeController;
import Database.Jdbc;
import Enums.WarningMessage;
import Models.User;

public class WelcomeMenu extends Menu{
    private static WelcomeMenu instance = null;

    private WelcomeController controller;

    private WelcomeMenu() {
        this.controller = WelcomeController.getInstance();
    }

    private static void setInstance(WelcomeMenu instance) {
        WelcomeMenu.instance = instance;
    }

    public static WelcomeMenu getInstance() {
        if (WelcomeMenu.instance == null) {
            WelcomeMenu.setInstance(new WelcomeMenu());
        }
        return WelcomeMenu.instance;
    }

    @Override
    public void run() {
        this.showOptions();
        String choice = this.getChoice();
        
        switch (choice) {
            case "1":
                this.createAccount();
                break;
            case "2":
                this.logIn();
                break;
            case "3":
                this.exit();
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
        }
    }

    private void createAccount() {
        String username = this.getInput("enter username");
        String password = this.getInput("enter password");
        String repeatedPassword = this.getInput("repeat password");
        String firstname = this.getInput("enter firstname");
        String lastname = this.getInput("enter lastname");
        String bio = this.getInput("enter bio");
        String birthDate = this.getInput("enter your birthDate in this format(yyyy/MM/dd)");
        String securityQuestionAnswer = this.getInput("answer this security question: " + User.SECURITY_QUESTION);
        this.showTypesOfAccount();
        String choice=this.getChoice();
        WarningMessage message=this.controller.handleCreateUser(username, password, repeatedPassword, firstname, lastname, bio, birthDate, securityQuestionAnswer,Integer.parseInt(choice));
        System.out.println(message == WarningMessage.SUCCESS ? "user created successfully" : message);
        this.run();
    }

    private void logIn() {
        String username = this.getInput("enter username");

        showLogInOptions();
        String choice = this.getChoice();

        switch (choice) {
            case "1":
                this.logInWithPassword(username);
                break;
            case "2":
                this.forgotPassword(username);
                break;
            case "3":
                this.run();
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
        }
    }

    private void logInWithPassword(String username) {
        String password = this.getInput("enter password");
        WarningMessage message = this.controller.handleLogin(username, password);
        if (message == WarningMessage.SUCCESS) {
            LoggedInMenu loggedInMenu = new LoggedInMenu(username);
            loggedInMenu.run();
        } else {
            System.out.println(message);
            this.logIn();
        }
    }

    private void forgotPassword(String username) {
        String answer = this.getInput("answer this question to show your password: " + User.SECURITY_QUESTION);

        WarningMessage message = this.controller.checkSecurityAnswer(username, answer);
        if (message == WarningMessage.SUCCESS) {
            String newPassword = this.getInput("enter a new password");
            WarningMessage changeMessage = this.controller.handlePasswordChange(username,newPassword);
            if(changeMessage == WarningMessage.SUCCESS)
                this.logInWithPassword(username);
            else
                this.forgotPassword(username);
        } else
            this.forgotPassword(username);
    }

    private void exit() {
        Menu.getScanner().close();
        System.exit(0);
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Create account");
        System.out.println("2. Login as an account");
        System.out.println("3. Exit");
    }

    private void showLogInOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Enter password");
        System.out.println("2. Forgot Password");
        System.out.println("3. Previous menu");
    }
    private void  showTypesOfAccount() {
        System.out.println("Enter your type of account:\n" +
                "1. Normal Account\n" +
                "2. BusinessAccount");
    }
}
