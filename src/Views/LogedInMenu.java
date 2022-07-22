package Views;


import Controllers.LogedInController;
import Controllers.WelcomeController;
import Enums.Message;
import Models.User;

public class LogedInMenu extends Menu {
    private User user;
    private LogedInController logedInController;
    private WelcomeController welcomeController;
    LogedInMenu(String username)
    {
        user=User.getUserByUsername(username);
        logedInController=new LogedInController(user);
        welcomeController=WelcomeController.getInstance();
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
            case "5":
                showProfile();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
                run();
        }
    }
    private void showProfile()
    {
        System.out.println(user);
    }
    protected void search()
    {
        this.showSearchOption();
        String choice=this.getChoice();
        switch (choice)
        {
            case "1":
                searchOtherUsers();
                break;
            case "2":
                searchPosts();
                break;
            case "3":
                run();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
                search();
        }
    }
    private void searchOtherUsers()
    {
        String searchKey=getInput("search:");

    }
    private void searchPosts()
    {

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
                changePassword();
                break;
            case "3":
                changeBio();
                break;
            case "4":
                changeName();
                break;
            case "5":
                changeScurityQuestion();
            default:
                System.out.println(Message.INVALID_CHOICE);
                accountSetting();
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
        String password=this.getInput("Enter a new Password");
        String repeatedPassword=this.getInput("Repeat your new password");
        Message message=welcomeController.validatePassword(password,repeatedPassword);
        System.out.println(message == Message.SUCCESS ? welcomeController.handlePasswordChange(user.getUsername(),password) : message);
        accountSetting();

    }
    private void changeBio()
    {
        String bio =getInput("Enter your new bio");
        System.out.println(logedInController.handleBioChange(bio));
        accountSetting();
    }
    private void changeName()
    {
        String firstname=getInput("Enter a new firstname");
        String lastname=getInput("Enter a new lastname");
        System.out.println(logedInController.handleNameChange(firstname,lastname));
        accountSetting();
    }
    private void changeScurityQuestion()
    {
        String securityQuestion=getInput(User.SECURITY_QUESTION);
        accountSetting();
    }
    private void changeBirthDate()
    {
        accountSetting();
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Search");
        System.out.println("2. Add post");
        System.out.println("3. Create chat");
        System.out.println("4. Account Setting");
        System.out.println("5. show profile");
        System.out.println("6. LogOut");
    }
    protected void showSearchOption()
    {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Search Other Users");
        System.out.println("2. Search Posts");
        System.out.println("3. Previous Menu");
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
        System.out.println("7. Previous Menu");
    }
}
