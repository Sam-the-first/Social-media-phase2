package Views;

import Controllers.GroupAdminController;
import Models.Group;
import Models.User;


public class GroupAdminMenu extends  GroupMenu{

    GroupAdminController controller;
    GroupAdminMenu(User user, Group group, LoggedInMenu loggedInMenu) {
        super(user, group, new GroupAdminController(user,group));
        sender=user;
        this.loggedInMenu=loggedInMenu;
        controller=new GroupAdminController(user,group);
    }


   /* public void run() {
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
    }*/

   /* @Override
    protected void showOptions() {
        System.out.println("Enter one of these options:");
        System.out.println("1. Send new message");
        System.out.println("2. View unseen messages");
        System.out.println("3. View previous messages");
        System.out.println("4. Group profile");
        System.out.println("5. Back to main menu ");
    }*/
}
