package Views;

import Controllers.SearchController;
import Enums.WarningMessage;
import Models.Post;
import Models.User;

import java.util.ArrayList;

public class SearchMenu extends Menu {

    private User user;
    private SearchController controller;
    private LoggedInMenu loggedInMenu;

    SearchMenu(String username, LoggedInMenu loggedInMenu) {
        user = User.getUserByUsername(username);
        this.loggedInMenu = loggedInMenu;
        controller = new SearchController(user);
    }

    @Override
    public void run() {
        this.showOptions();
        String choice = this.getChoice();
        switch (choice)
        {
            case "1":
                searchOtherUsers();
                break;
            case "2":
                searchPosts();
                break;
            case "3":
                backToLoggedInMenu();
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                this.run();
        }
    }

    private void searchOtherUsers() {
        String searchKey = getInput("search for...");
        ArrayList<User> searchedUsers = controller.searchUsers(searchKey);
        for (int i = 0; i < searchedUsers.size(); i++) {
            int j = i + 1;
            System.out.println(j + ". username: "
                    + searchedUsers.get(i).getUsername() + "\n" +
                    "name: " + searchedUsers.get(i).getFirstname() +
                    " " + searchedUsers.get(i).getLastname()
            );
        }

        System.out.println((searchedUsers.size() + 1) + ". Previous Menu");

        int choice = Integer.parseInt(this.getChoice());
        while (choice > searchedUsers.size() + 1) {
            System.out.println("enter valid input.");
            choice = Integer.parseInt(this.getChoice());
        }

        if(choice == searchedUsers.size() + 1)
            this.run();
        else if(choice < searchedUsers.size() + 1) {
            showProfile(searchedUsers.get(choice - 1));
        }
    }

    private void showProfile(User user) {
        System.out.println(user.toString2());
        String input = this.getInput("Do you want to do any action with user? (yes/no)");
        if (input.equalsIgnoreCase("yes")) {
            ProfileMenu profileMenu = new ProfileMenu(this.user, this.loggedInMenu,  user);
            profileMenu.run();
        }
        else if (input.equalsIgnoreCase("no"))
            this.run();
        else
            showProfile(user);
    }

    private void searchPosts() {
        String searchKey = getInput("search for...");
        ArrayList<Post> searchedPosts = controller.searchPosts(searchKey);
        for (int i = 1; i < searchedPosts.size() + 1; i++) {
            System.out.println(i + "post text: \n" +
                    searchedPosts.get(i - 1).getText() + "\n" +
                    "by: " + searchedPosts.get(i - 1).getCommenter().getUsername() + "\n" +
                    "at: " + searchedPosts.get(i - 1).getFormattedDateTime()
            );
        }
        System.out.println((searchedPosts.size() + 1) + ". Previous Menu");
        System.out.println("choose post number to see details of that or choose "
                        + (searchedPosts.size() + 1) +
                        "to go to search menu.");

        int choice = Integer.parseInt(this.getChoice());
        while (choice > searchedPosts.size() + 1) {
            System.out.println("enter valid input.");
            choice = Integer.parseInt(this.getChoice());
        }
        if(choice == searchedPosts.size() + 1)
            this.run();
        else if(choice < searchedPosts.size() + 1) {
            if (searchedPosts.get(choice - 1).getCommenter().equals(user))
                showPostSettingOptions(searchedPosts.get(choice - 1));
            else
                showPostOptions(searchedPosts.get(choice - 1));
        }
    }

    private void showPostSettingOptions(Post post) {
        PostSettingMenu postSettingMenu = new PostSettingMenu(user.getUsername(), loggedInMenu, post);
        postSettingMenu.run();
    }

    private void showPostOptions(Post post) {
        PostMenu postMenu = new PostMenu(user.getUsername(), post, loggedInMenu, this);
        postMenu.run();
    }

    private void backToLoggedInMenu() {
        loggedInMenu.run();
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Search Other Users");
        System.out.println("2. Search Posts");
        System.out.println("3. Previous Menu");
    }


}
