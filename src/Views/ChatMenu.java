package Views;

import Controllers.ChatController;
import Enums.WarningMessage;
import Models.Chat;
import Models.Group;
import Models.Message;
import Models.User;

import java.util.ArrayList;

public class ChatMenu extends Menu {
     User user1;
     User user2;
     LoggedInMenu loggedInMenu;
     ChatController controller;
     Chat chat;

    public ChatMenu(User user1, User user2, LoggedInMenu loggedInMenu) {
        this.user1 = user1;
        this.user2 = user2;
        this.loggedInMenu = loggedInMenu;
        controller = new ChatController(user1,user2);
        chat=controller.getChat();
    }

    public ChatMenu(User user,Chat chat,ChatController chatController) {
        this.user1=user;
        this.chat=chat;
        this.controller=chatController;
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
                showProfile();
            case "6":
                loggedInMenu.run();
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                this.run();
        }
    }
    public void showProfile()
    {
        ProfileMenu profileMenu=new ProfileMenu(user1,loggedInMenu,user2);
        profileMenu.run();
    }

    public void newMessage() {
        String text = getInput("Enter your new message");
        System.out.println(controller.sendNewMessage(text));
        this.run();
    }

    public void unSeenMessages() {
        ArrayList<Message> messages = controller.getUnSeenMessages();
        showMessageList(messages);
    }

    public void previousMessages() {
        int input = Integer.parseInt(getInput("Enter a number"));
        ArrayList<Message> messages = controller.getPreviousMessages(input);
        showMessageList(messages);
    }

    public void showMessageList(ArrayList<Message> messages) {
        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            int j = i + 1;
            System.out.print(j + ". ");
            showMessage(message);
            if(user1!=chat.getUser1())
                message.setSeen(user1);
        }
        int j= messages.size()+1;
        System.out.println(j + ". Previous Menu");
        int choice = Integer.parseInt(getChoice());
        if(choice > j)
            System.out.println(WarningMessage.INVALID_CHOICE);
        else if(choice == j)
            run();
        else
            messageOption(messages.get(choice - 1));

    }

    public void messageOption(Message message) {
        showMessage(message);
        showMessageOption(message);
        String choice = getChoice();

        switch (choice) {
            case "1":
                forward(message);
                break;
            case "2":
                deleteMessage(message);
                break;
            case "3":
                reply(message);
                break;
            case "4":
                if(message.getSender() == user1)
                    edit(message);
                else run();
                break;
            case "5":
                if(message.getSender() == user1)
                    run();
                else
                    System.out.println(WarningMessage.INVALID_CHOICE);
                break;
            default:
                System.out.println(WarningMessage.INVALID_CHOICE);
                messageOption(message);
        }
    }

    public void forward(Message message) {
        System.out.println("1. Cancel");
        int i=2;

        for (Chat chat : user1.getChats()) {
            String name;
            if(chat instanceof Group) {
                Group group=(Group) chat;
                name=group.getName();
            }
            else {
                if(chat.getUser1()==user1)
                    name = chat.getUser2().getName();
                else
                    name = chat.getUser1().getName();
            }
            System.out.println(i+". "+name);
            i++;
        }

        int choice=Integer.parseInt(getChoice());

        if(choice==1)
            messageOption(message);
        else if(choice>user1.getChats().size()+1) {
            System.out.println(WarningMessage.INVALID_CHOICE);
            messageOption(message);
        }
        else {
            Chat chat=user1.getChats().get(choice-2);
            if(chat instanceof Group) {

            }
            else {
                ChatController chatController = new ChatController(chat);
                chatController.forwardedInto(message);
                run();
            }
        }
    }

    public void edit(Message message) {
        String newMessage=getInput("edit");
        System.out.println(controller.editMessage(message,newMessage));
        messageOption(message);
    }

    public void showMessage(Message message) {
        String sender;
        String seen = "";
        if(message.getSender() == user1) {
            sender = "you";
            if(message.isSeen())
                seen="seen";
            else
                seen="notSeen";
        }
        else
            sender = message.getSender().getFirstname();

        if(message.getForwardedFrom()!=null)
            System.out.println("forwarded from "+message.getForwardedFrom().getName());

        if(message.getReplied()!=null&&message.getReplied().canSee(user1)) {
            Message reply = message.getReplied();
            String replyShow;

            if(reply.getText().length()>10)
                replyShow =reply.getText().substring(0, 10);

            else
                replyShow=reply.getText();

            System.out.println("in reply to: " + replyShow);
        }

        System.out.println(message.getText()+" ---- " + "Sent By:"+sender+" ---- "+"at:"+message.getDate()+" ---- "+seen);
        if(user1 != chat.getUser1())
            message.setSeen(user1);
    }

    public void deleteMessage(Message message) {
        System.out.println("1. Delete for me");
        System.out.println("2. Also Delete for "+user2.getFirstname());

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

    public void reply(Message message) {
        String text = getInput("Enter your new message");
        controller.reply(message,text);
        System.out.println(WarningMessage.SUCCESS);
        run();
    }

    public void searchMessage() {
        String searchKey=getInput("search");
        ArrayList<Message> messages = controller.searchMessage(searchKey);
        showMessageList(messages);
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter one of these options:");
        System.out.println("1. Send new message");
        System.out.println("2. View unseen messages");
        System.out.println("3. View previous messages");
        System.out.println("4. Search");
        System.out.println("5. view "+user2.getName()+" profile");
        System.out.println("6. Back to main menu ");
    }

    protected void showMessageOption(Message message) {
        System.out.println("Enter one of these options:");
        System.out.println("1. Forward");
        System.out.println("2. delete");
        System.out.println("3. Reply");
        if(message.getSender() == user1) {
            System.out.println("4. Edit ");
            System.out.println("5. Previous menu");
        }
        else
            System.out.println("4. Previous menu");
    }
}
