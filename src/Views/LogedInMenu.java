package Views;

import Controllers.LogedInController;
import Controllers.WelcomeController;
import Enums.Message;
import Models.User;

public class LogedInMenu extends Menu {
 private User user;
 private LogedInController logedInController;
 private WelcomeController welcomeController;
LogedInMenu(String username) {
    user = User.getUserByUsername(username);
    LogedInController logedInController = new LogedInController(user);
    welcomeController = WelcomeController.getInstance();
}

    @Override
    public void run() {
        this.showOptions();
        String choice = this.getChoice();
        switch (choice) {
            case "1":
                this.search();
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                this.accountSetting();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
        }
    }

    protected void search()
    {
        this.showSearchOption();
        String choice=this.getChoice();
    }
    protected void accountSetting()
    {
        this.showSettingOption();
        String choice=this.getChoice();
        switch (choice)
        {
            case "1":
                this.changeUsername();
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
        }
    }
    private void changeUsername()
    {
        String username=this.getInput("Enter a Valid new Username");
        System.out.println(logedInController.handleUsernameChange(user,username));
        this.accountSetting();
    }
    private void changePassword()
    {
        String password=this.getInput("Enter a ew Password");
        String repeatedPassword=this.getInput("Repeat your new password");
        welcomeController.validatePassword(password,repeatedPassword);
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Search");
        System.out.println("2. Add post");
        System.out.println("3. Create chat");
        System.out.println("4. Account Setting");
        System.out.println("5. Exit");
    }
    protected void showSearchOption()
    {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Search Other Users");
        System.out.println("2. Search Posts");
        //System.out.println("3. Search Posts");
    }
    protected void showSettingOption()
    {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Change Username");
        System.out.println("2. Change Password");
        System.out.println("3. Change bio");
        System.out.println("4. Change Name");
        System.out.println("5. Change SecurityQuestion");
        System.out.println("6. Change BirthDate");
    }
}
