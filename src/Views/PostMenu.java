package Views;

import Controllers.LoggedInController;
import Controllers.PostController;
import Enums.WarningMessage;
import Models.User;

public class PostMenu extends Menu{

    /*private static PostMenu instance = null;

    private PostController controller;

    private PostMenu() {
        this.controller = PostController.getInstance();
    }

    private static void setInstance(PostMenu instance) {
        PostMenu.instance = instance;
    }

    public static PostMenu getInstance() {
        if (PostMenu.instance == null) {
            PostMenu.setInstance(new PostMenu());
        }
        return PostMenu.instance;
    }*/

    private User user;
    private PostController controller;

    PostMenu(String username) {
        user = User.getUserByUsername(username);
        controller = new PostController(user);
    }

    @Override
    public void run() {
        this.showOptions();

        String choice = this.getChoice();

        switch (choice) {
            case "1":
                this.enterInformation();
                break;
            case "2":
                this.backToLoggedInMenu();
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
        }
    }

    private void enterInformation() {
        String text = this.getInput("Enter the text");
        WarningMessage message = this.controller.handlePost(text);
        System.out.println(message);
    }

    private void backToLoggedInMenu() {
        LoggedInMenu loggedInMenu = new LoggedInMenu(user.getUsername());
        loggedInMenu.run();
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Enter Post Information");
        System.out.println("2. Back to LoggedInMenu");
    }

}
