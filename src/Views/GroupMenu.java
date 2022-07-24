package Views;

import Controllers.GroupController;
import Models.Group;
import Models.User;

public class GroupMenu extends Menu {
    User user;
    Group group;
    GroupController controller;
    LoggedInMenu loggedInMenu;
    GroupMenu(User user, Group group, LoggedInMenu loggedInMenu) {
        this.user=user;
        this.group=group;
        this.loggedInMenu=loggedInMenu;
    }

    @Override
    public void run() {
        showOptions();
        String choice=getChoice();
        switch (choice) {
            case "1":
                newMessage();
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                loggedInMenu.run();
                break;
        }
    }

    public void newMessage() {
        String text = getInput("Enter your new message");
        System.out.println(controller.sendNewMessage(text));
        this.run();
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options:");
        System.out.println("1. Send new message");
        System.out.println("2. View unseen messages");
        System.out.println("3. View previous messages");
        System.out.println("4. Group profile");
        System.out.println("5. Back to main menu ");
    }
}
