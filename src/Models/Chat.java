package Models;

import java.util.ArrayList;

public class Chat {

    private static int counter=1;
    private int id;
    private ArrayList<User> users=new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();
    public static ArrayList<Chat> allchats = new ArrayList<>();

    public static Chat getChat(User user1, User user2) {
        for (Chat chat : allchats) {
            if(chat.getUser1() == user1 && chat.getUser2() == user2)
                return chat;
            if(chat.getUser2() == user1 && chat.getUser1() == user2)
                return chat;
        }
        return null;
    }

    public Chat(User user1, User user2) {
        users.add(user1);
        users.add(user2) ;
        allchats.add(this);
        id = counter;
        counter++;
        user1.addChat(this);
        user2.addChat(this);
    }

    public Chat() {
        allchats.add(this);
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    public void setUsers(ArrayList<User> users)
    {
        this.users=users;
    }

    public User getUser1() {
        return users.get(0);
    }

    public User getUser2() {
        return users.get(1);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message)
    {
        messages.add(message);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
        for (Message message1 : messages) {
            if(message1.getReplied()==message)
                message1.setReplied(null);
        }
    }
}
