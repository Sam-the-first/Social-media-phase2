package Views;

import Controllers.CommentController;
import Enums.WarningMessage;
import Models.Comment;
import Models.User;

import java.util.ArrayList;

public class CommentMenu extends Menu{

    private User user;
    private Comment comment;
    private CommentController controller;
    private LoggedInMenu loggedInMenu;
    private PostMenu postMenu;

    CommentMenu(String username, Comment comment, LoggedInMenu loggedInMenu, PostMenu postMenu) {
        user = User.getUserByUsername(username);
        this.comment = comment;
        this.loggedInMenu = loggedInMenu;
        this.postMenu = postMenu;
        controller = new CommentController(user, comment);
    }


    @Override
    public void run() {

        showOptions();
        String choice = this.getChoice();

        switch (choice) {
            case "1":
                processOfLike();
                break;
            case "2":
                processOfDeleteLike();
                break;
            case "3":
                processOfComment();
                break;
            case "4":
                processOfDeleteComment();
                break;
            case "5":
                likesNumber();
                break;
            case "6":
                processOfShowComments();
                break;
            case "7":
                backToPostMenu();
                break;
            case "8":
                backToLoggedInMenu();
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                this.run();
        }
    }

    private void processOfLike() {
        this.controller.handleLike();
    }

    private void processOfDeleteLike() {
        this.controller.handleDeleteLike();
    }

    private void processOfComment() {
        String text = this.getInput("enter text of your comment");
        this.controller.handleComment(text);
    }

    private void processOfDeleteComment() {
        this.controller.handleDeleteComment();
    }

    private void likesNumber() {
        System.out.println("like numbers: " + this.controller.countOfLikes());
    }

    private void processOfShowComments() {
        ArrayList<Comment> comments = this.controller.handleShowComments();
        postMenu.showComments(comments);
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
        System.out.println("1. to like");
        System.out.println("2. to delete like");
        System.out.println("3. to comment");
        System.out.println("4. to delete comment");
        System.out.println("5. show like's number");
        System.out.println("6. show comments");
        System.out.println("7. back to post menu");
        System.out.println("8. back to loggedIn menu");
    }
}


