package Views;

import Controllers.PostController;
import Enums.WarningMessage;
import Models.Comment;
import Models.Post;
import Models.User;

import java.util.ArrayList;

public class PostMenu extends Menu{

    private User user;
    private Post post;
    private PostController controller;
    private LoggedInMenu loggedInMenu;
    private SearchMenu searchMenu;

    public PostMenu(String username, Post post, LoggedInMenu loggedInMenu, SearchMenu searchMenu) {
        user = User.getUserByUsername(username);
        this.post = post;
        this.loggedInMenu = loggedInMenu;
        this.searchMenu = searchMenu;
        controller = new PostController(user, post);
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
                processOfDeletingLike();
                break;
            case "3":
                processOfComment();
                break;
            case "4":
                processOfDeletingComment();
                break;
            case "5":
                processOfShare();
                break;
            case "6":
                processOfSave();
                break;
            case "7":
                likesNumber();
                break;
            case "8":
                processOfShowComments();
                break;
            case "9":
                processOfShowViewers();
                break;
            case "10":
                backToSearchMenu();
                break;
            case "11":
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

    private void processOfDeletingLike() {
        this.controller.handleDeleteLike();
    }

    private void processOfComment() {
        String text = this.getInput("enter text of your comment");
        this.controller.handleComment(text);
    }

    private void processOfDeletingComment() {
        this.controller.handleDeleteComment();
    }

    //TO-DO
    private void processOfShare() {

    }

    private void processOfSave() {

    }

    private void likesNumber() {
        System.out.println("like numbers: " + this.controller.countOfLikes());
    }

    private void processOfShowComments() {
        ArrayList<Comment> comments = this.controller.handleShowComments();
        showComments(comments);
    }

    protected void showComments(ArrayList<Comment> comments) {
        if (!comments.isEmpty()) {
            System.out.println("list of comments");
            for (int i = 1; i < comments.size() + 1; i++) {
                System.out.println(i + ". " + comments.get(i - 1).getText() + "\n" +
                        "by: " + comments.get(i - 1).getCommenter() + "\n" +
                        "at: " + comments.get(i - 1).getFormattedDateTime());
            }
            System.out.println((comments.size() + 1) + ". Previous Menu");
            System.out.println("choose comment number to see details of that or choose "
                    + (comments.size() + 1) +
                    "to go to search menu.");
            int choice = Integer.parseInt(this.getChoice());
            while (choice > comments.size() + 1) {
                System.out.println("enter valid input.");
                choice = Integer.parseInt(this.getChoice());
            }
            if(choice == comments.size() + 1)
                this.run();
            else if(choice < comments.size() + 1) {
                if (comments.get(choice - 1).getCommenter().equals(user))
                    showCommentSettingOption(comments.get(choice - 1));
                else
                    showCommentOption(comments.get(choice - 1));
            }
        }
        else
            System.out.println(WarningMessage.NO_COMMENT_FOR_THIS);
    }

    private void showCommentOption(Comment comment) {
        CommentMenu commentMenu = new CommentMenu(user.getUsername(), comment, loggedInMenu, this);
        commentMenu.run();
    }

    private void showCommentSettingOption(Comment comment) {
        CommentSettingMenu commentSettingMenu = new CommentSettingMenu(user.getUsername(), loggedInMenu, this, post, comment);
        commentSettingMenu.run();
    }

    //TO-DO
    private void processOfShowViewers() {

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
        System.out.println("1. to like");
        System.out.println("2. to delete like");
        System.out.println("3. to comment");
        System.out.println(" . to delete comment");
        System.out.println("3. to share");
        System.out.println("4. to save");
        System.out.println("5. show like's number");
        System.out.println("6. show comments");
        System.out.println("7. show viewers");
        System.out.println("8. back to search menu");
        System.out.println("9. back to loggedIn menu");
    }
}
