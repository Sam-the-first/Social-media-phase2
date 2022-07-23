package Views;

import Controllers.LoggedInController;
import Enums.WarningMessage;
import Models.User;

public class LoggedInMenu extends Menu {

    /*private static LoggedInMenu instance = null;

    private static User loggedInUser = null;

    private LoggedInController controller;

    private LoggedInMenu() {
        this.controller = LoggedInController.getInstance();
    }

    private static void setInstance(LoggedInMenu instance) {
        LoggedInMenu.instance = instance;
    }

    public static LoggedInMenu getInstance() {
        if (LoggedInMenu.instance == null) {
            LoggedInMenu.setInstance(new LoggedInMenu());
        }
        return LoggedInMenu.instance;
    }*/

    private User user;
    private LoggedInController controller;

    LoggedInMenu(String username) {
        user = User.getUserByUsername(username);
        controller = new LoggedInController(user);
    }

    @Override
    public void run() {
        this.showOptions();
        String choice = this.getChoice();
        switch (choice) {
            case "1":
                this.searchMenu();
                break;
            case "2":
                this.addPost();
                break;
            case "3":
                this.createChat();
                break;
            case "4":
                this.profileSetting();
                break;
            case "5":
                showMyProfile();
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                run();
        }
    }

    private void searchMenu() {
        SearchMenu searchMenu = new SearchMenu(user.getUsername());
    }

    private void addPost() {

    }

    private void createChat() {

    }

    private void profileSetting() {
        ProfileMenu profileMenu = new ProfileMenu(user.getUsername());
        profileMenu.run();
    }

    private void showMyProfile() {
        System.out.println(user.toString());
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Search");
        System.out.println("2. Add post");
        System.out.println("3. Create chat");
        System.out.println("4. Account Setting");
        System.out.println("5. show profile");
        System.out.println("6. Show followings");
        System.out.println("7. Show followers");
        System.out.println("8. LogOut");
    }

}
