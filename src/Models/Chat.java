package Models;

import java.util.ArrayList;

public class Chat {
    private static int counter=1;
    private int id;
    private ArrayList<User> users=new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();
    public static ArrayList<Chat> chats = new ArrayList<>();

    public static Chat getChat(User user1, User user2) {
        for (Chat chat : chats) {
            if(chat.getUsers().contains(user1)&&chat.getUsers().contains(user2))
                return chat;
        }
        return null;
    }

    public Chat(User user1, User user2) {
        users.add(user1);
        users.add(user2) ;
        chats.add(this);
        id=counter;
        counter++;
    }
    public Chat()
    {
        chats.add(this);
    }

    public ArrayList<User> getUsers() {
        return users;
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

}
