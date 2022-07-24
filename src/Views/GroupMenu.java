package Views;

import Controllers.GroupAdminController;
import Controllers.GroupController;
import Enums.WarningMessage;
import Models.Group;
import Models.Message;
import Models.User;

import java.util.ArrayList;

public class GroupMenu extends ChatMenu {
    User sender;
    Group group;
    GroupController controller;
    LoggedInMenu loggedInMenu;
    GroupMenu(User user, Group group, LoggedInMenu loggedInMenu) {
        super(user,group,new GroupController(user,group));
        this.sender=user;
        this.group=group;
        this.loggedInMenu=loggedInMenu;
        this.controller=new GroupController(user,group);
        super.controller=this.controller;
    }
    GroupMenu(User user, Group group, GroupAdminController controller)
    {
        super(user,group,controller);

    }

    @Override
    public void run() {
        showOptions();
        String choice = getChoice();
        switch (choice) {
            case "1":
                newMessage();
                break;
            case "2":
                unSeenMessages();
                break;
            case "3":
                previousMessages();
                break;
            case "4":
                searchMessage();
                break;
            case "5":
                showGroupProfile();
            case "6":
                loggedInMenu.run();
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                this.run();
        }
    }

    private void showGroupProfile() {
        System.out.println(group);
        int i=1;
        for (User user : group.getUsers()) {
            System.out.println(i+". "+user.getName());
            i++;
        }
        System.out.println(i+". Previous menu");
        int choice=Integer.parseInt(getChoice());
        if(choice>i)
            System.out.println(WarningMessage.INVALID_CHOICE);
        else if(choice==i)
            run();
        else
        {
            User user=group.getUsers().get(choice-1);
            if(user==sender)
            {
               PersonalMenu personalMenu= new PersonalMenu(user,loggedInMenu);
               personalMenu.run();
            }
            else
            {
                ProfileMenu profileMenu=new ProfileMenu(sender,loggedInMenu,user);
                profileMenu.run();
            }
        }
    }
    public void deleteMessage(Message message) {
            controller.deleteMessageForMe(message);
        this.run();
    }


    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options:");
        System.out.println("1. Send new message");
        System.out.println("2. View unseen messages");
        System.out.println("3. View previous messages");
        System.out.println("4. Group profile");
        System.out.println("5. Search");
        System.out.println("6. Back to main menu ");
    }
}
