package Views;

import Controllers.LoggedInController;
import Enums.WarningMessage;
import Models.Chat;
import Models.Group;
import Models.Post;
import Models.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LoggedInMenu extends Menu {

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
                this.toChat();
                break;
            case "4":
                this.personalSetting();
                break;
            case "5":
                this.showMyProfile();
                break;
            case "6":
                this.showFollowings(user);
                break;
            case "7":
                this.showFollowers(user);
                break;
            case "8":
                backToWelcomeMenu();
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
        String text = this.getInput("Enter the text");
        WarningMessage message = this.controller.handlePost(text, user);
        System.out.println(message);
        run();
    }

    private void toChat() {
        System.out.println("1. Create chat");
        int i=2;
        String username="";
        for (Chat chat : user.getChats()) {
            String name;
            if(chat instanceof Group) {
                Group group = (Group) chat;
                name = group.getName();
            }
            else {
                if(chat.getUser1() == user) {
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
        else {
            Chat chat=user.getChats().get(choice-2);
            if(chat instanceof Group) {
                Group group=(Group) chat;
                if(group.getCreator()==user) {
                    GroupAdminMenu groupAdminMenu = new GroupAdminMenu(user,group,this);
                    groupAdminMenu.run();
                }
                else
                {
                    GroupMenu groupMenu=new GroupMenu(user,group,this);
                    groupMenu.run();
                }
            }
            else {
                ChatMenu chatMenu=new ChatMenu(user,User.getUserByUsername(username),this);
                chatMenu.run();
            }
        }
    }

    private void createChat() {

        showCreateChatOption();
        String choice=getChoice();

        switch (choice) {
            case "1":
                createGroup();
                break;
            case "2":
                startChat();
                break;
            case "3":
                run();
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                createChat();
                break;
        }
    }

    public void startChat() {

        String username=getInput("user name");
        User user2=User.getUserByUsername(username);

        if(user2==null) {
            System.out.println(WarningMessage.USER_DOES_NOT_EXIST);
            startChat();
        }
        else if(user2==user) {
            System.out.println(WarningMessage.YOU_CANT_HAVE_CHAT_WITH_YOURSELF);
            startChat();
        }
        else {
            ChatMenu chatMenu=new ChatMenu(user, User.getUserByUsername(username), this);
            chatMenu.run();
        }
    }
    public void createGroup() {
        String name = getInput("name");
        String description = getInput("description");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        while (true) {
            System.out.println("1. to add user to your group");
            System.out.println("2. to create the group");
            String choice = getChoice();
            if(choice.equals("1")) {
                String username=getInput("user name");
                User user1=User.getUserByUsername(username);
                if(users.contains(user1))
                    System.out.println(WarningMessage.USER_EXIST);
                else if(user1==null)
                    System.out.println(WarningMessage.USER_DOES_NOT_EXIST);
                else
                    users.add(user1);
            }
            else
                break;
        }
        System.out.println(controller.hadnleCreateGroup(user,users,name,description));
        this.toChat();
    }

    private void personalSetting() {
        PersonalMenu personalMenu = new PersonalMenu(user, this);
        personalMenu.run();
    }

    private void showMyProfile() {
        showMyProfileOptions();
        String choice = this.getChoice();
        switch (choice) {
            case "1":
                System.out.println(user.toString2());
                run();
                break;
            case "2":
                processOfShowingPosts();
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                this.run();
        }
    }

    private void processOfShowingPosts() {
        ArrayList<Post> posts = controller.handleShowPosts(user);
        if (posts != null) {
            for (int i = 1; i < posts.size() + 1; i++)
                System.out.println(i + ". " + posts.get(i - 1).toString());
            int j = posts.size() + 1;
            System.out.println(j + ". Previous Menu");
            System.out.println("Choose the post number to do action on that or choose "
                    + j + " to go to previous menu."
            );
            int choice = Integer.parseInt(getChoice());
            if (choice > j)
                System.out.println(WarningMessage.INVALID_CHOICE);
            else if (choice == j)
                this.run();
            else {
                PostSettingMenu postSettingMenu = new PostSettingMenu(user.getUsername(), this, posts.get(choice - 1));
                postSettingMenu.run();
            }

        }
        else
            System.out.println(WarningMessage.NO_POST_YET);
        run();
    }

    protected void showFollowings(User user) {
        ArrayList<User> followings = new ArrayList<>(user.getFollowings());
        if (!followings.isEmpty()) {
            System.out.println("list of followings: ");
            for (int i = 0; i < followings.size(); i++) {
                int count = i + 1;
                System.out.println(count + ". " + followings.get(i).getUsername());
            }
        }
        else
            System.out.println("There is no following for " + user.getUsername());
    }

    protected void showFollowers(User user) {
        ArrayList<User> followers = new ArrayList<>(user.getFollowers());
        if (!followers.isEmpty()) {
            System.out.println("list of followers: ");
            for (int i = 0; i < followers.size(); i++) {
                int count = i + 1;
                System.out.println(count + ". " + followers.get(i).getUsername());
            }
        }
        else
            System.out.println("There is no follower for " + user.getUsername());
    }

    private void backToWelcomeMenu() {
        user = null;
        WelcomeMenu.getInstance().run();
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Search");
        System.out.println("2. Add post");
        System.out.println("3. chat");
        System.out.println("4. Account Setting");
        System.out.println("5. show profile and posts");
        System.out.println("6. Show followings");
        System.out.println("7. Show followers");
        System.out.println("8. LogOut");
    }

    private void showMyProfileOptions() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. show my profile details");
        System.out.println("2. show my posts");
    }

    protected void showCreateChatOption() {
        System.out.println("Enter one of these options: ");
        System.out.println("1. Create Group");
        System.out.println("2. Start chat by username");
        System.out.println("3. Previous menu");
    }


}
