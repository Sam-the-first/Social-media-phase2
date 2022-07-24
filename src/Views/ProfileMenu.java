package Views;

import Controllers.PostSettingController;
import Controllers.ProfileController;
import Enums.WarningMessage;
import Models.User;

public class ProfileMenu extends Menu{

    private User user;
    private User userToCheck;
    private ProfileController controller;
    private PostSettingController postController;
    private LoggedInMenu loggedInMenu;
    private SearchMenu searchMenu;

    public ProfileMenu(String username, LoggedInMenu loggedInMenu, SearchMenu searchMenu, User userToCheck) {
        user = User.getUserByUsername(username);
        this.loggedInMenu = loggedInMenu;
        this.searchMenu = searchMenu;
        this.userToCheck = userToCheck;
        controller = new ProfileController(user);
    }


    @Override
    public void run() {
        showOptions();
        String choice = this.getChoice();
        switch (choice) {
            case "1":
                processOfFollowUnfollow();
                break;
            case "2":
                processOfChat();
                break;
            case "3":
                processOfShowPosts();
                break;
            case "4":
                processOfShowFollowers();
                break;
            case "5":
                processOfShowFollowings();
                break;
            case "6":
                processOfBlock();
                break;
            case "7":
                backToSearchMenu();
                break;
            case "8":
                backToLoggedInMenu();
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                this.run();
        }
    }

    private void processOfFollowUnfollow() {
        WarningMessage message = this.controller.followUnfollow(userToCheck);
        System.out.println(message);
    }

    private void processOfChat() {
        ChatMenu chatMenu = new ChatMenu(user, userToCheck, loggedInMenu);
        chatMenu.run();
    }
    //TO-DO
    private void processOfShowPosts() {

    }

    private void processOfShowFollowers() {
        loggedInMenu.showFollowers(userToCheck);
    }

    private void processOfShowFollowings() {
        loggedInMenu.showFollowings(userToCheck);
    }
    //TO-DO
    private void processOfBlock() {
    }

    private void backToSearchMenu() {
        searchMenu.run();
    }

    private void backToLoggedInMenu() {
        loggedInMenu.run();
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options: ");
        if(user.getFollowings().contains(userToCheck))
            System.out.println("1. Unfollow");
        else
            System.out.println("1. Follow");
        System.out.println("2. Chat");
        System.out.println("3. Show " + userToCheck.getFirstname() + "'s Posts");
        System.out.println("4. Show " + userToCheck.getFirstname() + "'s Followers");
        System.out.println("5. Show " + userToCheck.getFirstname() + "'s Followings");
        System.out.println("6. Block user");
    }
}
