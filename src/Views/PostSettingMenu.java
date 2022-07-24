package Views;

import Controllers.PostSettingController;
import Enums.WarningMessage;
import Models.Post;
import Models.User;

public class PostSettingMenu extends Menu{

    private User user;
    private Post post;
    private PostSettingController controller;
    private LoggedInMenu loggedInMenu;

    PostSettingMenu(String username, LoggedInMenu loggedInMenu, Post post) {
        user = User.getUserByUsername(username);
        this.post = post;
        this.loggedInMenu = loggedInMenu;
        controller = new PostSettingController(user);
    }

    @Override
    public void run() {
        this.showOptions();

        String choice = this.getChoice();

        switch (choice) {
            case "1":
                processOfEditingPost(post);
                break;
            case "2":
                processOfDeletingPost(post);
                break;
            case "3":
                backToLoggedInMenu();
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                this.run();
        }
    }

    private void processOfDeletingPost(Post post) {
        this.controller.handleDeletePost(post);
        run();
    }

    private void processOfEditingPost(Post post) {
        String text = this.getInput("Enter new text ");
        this.controller.handleEditPost(post, text);
        run();
    }

    private void backToLoggedInMenu() {
        loggedInMenu.run();
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Edit post");
        System.out.println("2. Delete post");
        System.out.println("3. Previous menu");
    }

}
