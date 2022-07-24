package Views;

import Controllers.CommentSettingController;
import Enums.WarningMessage;
import Models.Comment;
import Models.Post;
import Models.User;

public class CommentSettingMenu extends Menu{

    private User user;
    private Comment comment;
    private CommentSettingController controller;
    private LoggedInMenu loggedInMenu;
    private PostMenu postMenu;

    public CommentSettingMenu(String username, LoggedInMenu loggedInMenu, PostMenu postMenu, Post post, Comment comment) {
        user = User.getUserByUsername(username);
        this.comment = comment;
        this.loggedInMenu = loggedInMenu;
        this.postMenu = postMenu;
        controller = new CommentSettingController(user, post, comment);
    }

    @Override
    public void run() {

        this.showOptions();
        String choice = this.getChoice();

        switch (choice) {
            case "1":
                processOfDeletingComment();
                break;
            case "2":
                processOfEditingComment();
                break;
            case "3":
                backToPostMenu();
                break;
            case "4":
                backToLoggedInMenu();
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                this.run();
        }

    }

    private void processOfDeletingComment() {
        this.controller.handleDeletingComment();
    }

    private void processOfEditingComment() {
        String text = this.getInput("enter your comment's new text");
        this.controller.handleEditingComment(text);
    }

    private void backToPostMenu() {
        postMenu.run();
    }

    private void backToLoggedInMenu() {
        loggedInMenu.run();
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Edit comment");
        System.out.println("2. Delete comment");
        System.out.println("3. back to post menu");
        System.out.println("4. back to loggedIn menu");
    }
}
