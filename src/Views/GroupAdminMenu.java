package Views;

import Controllers.GroupAdminController;
import Enums.WarningMessage;
import Models.Group;
import Models.Message;
import Models.User;


public class GroupAdminMenu extends  GroupMenu{

    GroupAdminController controller;
    GroupAdminMenu(User user, Group group, LoggedInMenu loggedInMenu) {
        super(user, group, new GroupAdminController(user,group));
        sender=user;
        this.loggedInMenu=loggedInMenu;
        controller=new GroupAdminController(user,group);
    }


    public void deleteMessage(Message message) {
        System.out.println("1. Delete for me");
        System.out.println("2. Delete for everyone");

        String choice=getChoice();

        if(choice.equals("1"))
            controller.deleteMessageForMe(message);
        else if(choice.equals("2"))
            controller.deleteMessageForEveryone(message);
        else {
            System.out.println(WarningMessage.INVALID_CHOICE);
            deleteMessage(message);
        }

        this.run();
    }
    public void showGroupProfile() {
        System.out.println(group);
        System.out.println("1. edit name");
        System.out.println("2. edit description");
        System.out.println("3. add member");
        int i=4;
        for (User user : group.getUsers()) {
            System.out.println(i+". "+user.getName());
            i++;
        }
        System.out.println(i+". delete Group");
        i++;
        System.out.println(i+". Previous menu");
        int choice=Integer.parseInt(getChoice());
        if(choice>i)
            System.out.println(WarningMessage.INVALID_CHOICE);
        else if(choice==i)
            run();
        else if(choice==i-1)
            controller.deleteGroup();

        else if(choice==1)
            editGroupName();

        else if(choice==2)
            editGroupDescription();
        else if(choice==3)
            addMember();
        else
        {
            User user=group.getUsers().get(choice-4);
            if(user==sender)
            {
                PersonalMenu personalMenu= new PersonalMenu(user,loggedInMenu);
                personalMenu.run();
            }
            else
            {
                userOption(user);
            }
        }
    }

    private void addMember() {
        String username=getInput("username");
        User user=User.getUserByUsername(username);
        if(user==null)
        {
            System.out.println(WarningMessage.USER_DOES_NOT_EXIST);
            addMember();
        }
        else
        {
            controller.addMember(user);
        }
    }

    private void editGroupDescription() {
        String newDescription=getInput("description");
        controller.editGroupDescription(newDescription);
    }

    private void editGroupName() {
        String newName=getInput("name");
        controller.editGroupName(newName);
    }

    public void userOption(User user)
    {
        showUserOption(user);
        String choice=getChoice();
        switch (choice)
        {
            case "1":
                kick(user);
                break;
            case "2":
                ProfileMenu profileMenu=new ProfileMenu(sender,loggedInMenu,user);
                profileMenu.run();
                break;
            case "3":
                showGroupProfile();
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                userOption(user);
        }
    }

    private void kick(User user) {
        controller.kick(user);
        showGroupProfile();
    }

    public void showUserOption(User user)
    {
        System.out.println("Enter one of these options:");
        System.out.println("1. kick");
        System.out.println("2. Show profile");
        System.out.println("3. Previous menu");
    }


}
