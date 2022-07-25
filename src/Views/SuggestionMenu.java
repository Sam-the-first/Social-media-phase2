package Views;

import Controllers.SuggestionController;
import Models.User;

import java.util.ArrayList;

public class SuggestionMenu extends Menu{
    User user;
    LoggedInMenu loggedInMenu;
    SuggestionController controller;
    public SuggestionMenu(User user, LoggedInMenu loggedInMenu) {
        this.user=user;
        this.loggedInMenu=loggedInMenu;
        this.controller=new SuggestionController(user);
    }

    @Override
    public void run() {
        String choice=getChoice();

        switch (choice)
        {
            case "1":
                userSuggestion();
                break;
            case "2":
                break;
            case "3":
                loggedInMenu.run();
                break;
        }

    }

    private void userSuggestion() {
        ArrayList<User> suggestedUser=controller.getSuggestedUser();
        for (int i = 0; i < suggestedUser.size(); i++) {
            int j = i + 1;
            System.out.println(j + ". username: "
                    + suggestedUser.get(i).getUsername() + "\n" +
                    "name: " + suggestedUser.get(i).getFirstname() +
                    " " + suggestedUser.get(i).getLastname()
            );
        }

        System.out.println((suggestedUser.size() + 1) + ". Previous Menu");

        int choice = Integer.parseInt(this.getChoice());
        while (choice > suggestedUser.size() + 1) {
            System.out.println("enter valid input.");
            choice = Integer.parseInt(this.getChoice());
        }

        if(choice == suggestedUser.size() + 1)
            this.run();
        else if(choice < suggestedUser.size() + 1) {
            showProfile(suggestedUser.get(choice - 1));
        }
    }

    private void showProfile(User user1) {
        ProfileMenu profileMenu=new ProfileMenu(user,loggedInMenu,user1);
        profileMenu.run();
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. User suggestion");
        System.out.println("2. Advertisement suggestion");
        System.out.println("3. Back to main menu");
    }
}
