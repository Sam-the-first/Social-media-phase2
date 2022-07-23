package Views;

import Controllers.LoggedInController;
import Enums.WarningMessage;
import Models.Chat;
import Models.Group;
import Models.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LoggedInMenu extends Menu {

    /*private static LoggedInMenu instance = null;

    private static User loggedInUser = null;

    private LoggedInController controller;

    private LoggedInMenu() {
        this.controller = LoggedInController.getInstance();
    }

    private static void setInstance(LoggedInMenu instance) {
        LoggedInMenu.instance = instance;
    }

    public static LoggedInMenu getInstance() {
        if (LoggedInMenu.instance == null) {
            LoggedInMenu.setInstance(new LoggedInMenu());
        }
        return LoggedInMenu.instance;
    }*/

    private User user;
    private LoggedInController controller;

    LoggedInMenu(String username) {
        user = User.getUserByUsername(username);
        controller = new LoggedInController(user);
    }

    @Override
    public void run() {
        this.showOptions();
        String choice = this.getChoice();
        switch (choice) {
            case "1":
                this.searchMenu();
                break;
            case "2":
                this.addPost();
                break;
            case "3":
                this.Chat();
                break;
            case "4":
                this.profileSetting();
                break;
            case "5":
                showMyProfile();
                break;
            case "6":
                break;
            case "7":
                break;
            case "8":
                WelcomeMenu.getInstance().run();
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                run();
        }
    }

    private void searchMenu() {
        SearchMenu searchMenu = new SearchMenu(user.getUsername(), this);
        searchMenu.run();
    }

    private void addPost() {
        PostMenu postMenu = new PostMenu(user.getUsername(), this);
        postMenu.run();
    }

    private void Chat() {
        System.out.println("1. Create chat");
        int i=2;
        String username="";
        for (Chat chat : user.getChats()) {
            String name;
            if(chat instanceof Group)
            {
                Group group=(Group) chat;
                name=group.getName();
            }
            else {
                if(chat.getUser1()==user) {
                    name = chat.getUser2().getName();
                    username=chat.getUser2().getUsername();
                }
                else {
                    name = chat.getUser1().getName();
                    username=chat.getUser1().getUsername();
                }
            }
            System.out.println(i+". "+name);
            i++;
        }
        System.out.println(i+". "+"Previous Menu");
        int choice=Integer.parseInt(getChoice());
        if(choice==1)
            createChat();
        else if(choice>user.getChats().size()+1)
            run();
        else
        {
            Chat chat=user.getChats().get(choice-2);
            if(chat instanceof Group)
            {

            }
            else
            {
                ChatMenu chatMenu=new ChatMenu(user,User.getUserByUsername(username),this);
                chatMenu.run();
            }
        }
    }
    private void createChat()
    {
        showCreateChatOption();
        String choice=getChoice();
        switch (choice)
        {
            case "1":
                createGroup();
                break;
            case "2":
                startChat();
                break;
            case "3":
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                createChat();
                break;
        }
    }
    public void startChat()
    {
        String username=getInput("user name");
        User user2=User.getUserByUsername(username);
        if(user2==null)
        {
            System.out.println(WarningMessage.USER_DOES_NOT_EXIST);
            startChat();
        }
        else if(user2==user)
        {
            System.out.println(WarningMessage.YOU_CANT_HAVE_CHAT_WITH_YOURSELF);
            startChat();
        }
        else {
            new ChatMenu(user, User.getUserByUsername(username), this);
            Chat();
        }
    }
    public void createGroup()
    {
        String name=getInput("name");
        String description=getInput("description");
        Set<User> users=new HashSet<>();
        users.add(user);
        while (true)
        {
            System.out.println("1. to add user to your group");
            System.out.println("2. to create the group");
            String choice=getChoice();
            if(choice.equals("1")){
                String username=getInput("user name");
                users.add(User.getUserByUsername(username));
            }
            else
                break;
        }
        Group group=new Group(user,users,name,description);
        System.out.println(WarningMessage.GROUP_CREATED_SUCCESSFULLY);
        user.addChat(group);
        Chat();
    }

    private void profileSetting() {
        ProfileMenu profileMenu = new ProfileMenu(user.getUsername(), this);
        profileMenu.run();
    }

    private void showMyProfile() {
        System.out.println(user.toString());
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Search");
        System.out.println("2. Add post");
        System.out.println("3. chat");
        System.out.println("4. Account Setting");
        System.out.println("5. show profile");
        System.out.println("6. Show followings");
        System.out.println("7. Show followers");
        System.out.println("8. LogOut");
    }
    protected void showCreateChatOption()
    {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Create Group");
        System.out.println("2. Start chat by username");
        System.out.println("3. Previous menu");
    }

}
