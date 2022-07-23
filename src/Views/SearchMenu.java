package Views;

import Controllers.SearchController;
import Enums.WarningMessage;
import Models.User;

import java.util.ArrayList;

public class SearchMenu extends Menu {
    /*private static SearchMenu instance = null;

    private SearchController controller;

    private SearchMenu() {
        this.controller = SearchController.getInstance();
    }

    private static void setInstance(SearchMenu instance) {
        SearchMenu.instance = instance;
    }

    public static SearchMenu getInstance() {
        if (SearchMenu.instance == null) {
            SearchMenu.setInstance(new SearchMenu());
        }
        return SearchMenu.instance;
    }*/

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
        showOptions();
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
                loggedInMenu.run();
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                this.run();
        }
    }

    private void searchOtherUsers() {
        String searchKey = getInput("search...");
        ArrayList<User> searchedUsers = controller.searchUsers(searchKey);
        for (int i = 0; i < searchedUsers.size(); i++) {
            int j = i + 1;
            System.out.println(j + ". username: "
                    + searchedUsers.get(i).getUsername() + " name: "
                    + searchedUsers.get(i).getFirstname() + " "
                    + searchedUsers.get(i).getLastname()
            );
        }

        System.out.println(Integer.toString(searchedUsers.size() + 1)+". Previous Menu");

        int choice = Integer.parseInt(this.getChoice());

        if(choice == searchedUsers.size() + 1)
            this.run();
        else if(choice < searchedUsers.size() + 1) {
            showProfile(searchedUsers.get(choice - 1));

        }
        else {
            System.out.println("invalid input");
            this.run();
        }
    }

    private void showProfile(User user) {
        System.out.println(user.toString2());
        showOtherUsersOption(user);
        String choice=getChoice();
        switch (choice)
        {
            case "1":
                controller.FollowUnfollow(user);
                showProfile(user);
                break;
            case "2":
                ChatMenu chatMenu=new ChatMenu(this.user,user,loggedInMenu);
                chatMenu.run();
                break;
        }
    }

    private void searchPosts() {

    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Search Other Users");
        System.out.println("2. Search Posts");
        System.out.println("3. Previous Menu");
    }

    protected void showOtherUsersOption(User user) {
        System.out.println("Enter one of these options: ");
        if(LoggedInMenu.getLoggedInUser().getFollowings().contains(user))
            System.out.println("1. Unfollow");
        else
            System.out.println("1. Follow");
        System.out.println("2. Chat");
        System.out.println("3. Show " + user.getFirstname() + "'s Posts");
        System.out.println("4. Show " + user.getFirstname() + "'s Followers");
        System.out.println("5. Show " + user.getFirstname() + "'s Followings");
        System.out.println("6. Block");
        System.out.println("7. Previous Menu");
    }

}
