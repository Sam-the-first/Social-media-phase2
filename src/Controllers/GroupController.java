package Controllers;

import Enums.WarningMessage;
import Models.Group;
import Models.Message;
import Models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class GroupController extends ChatController{
    User sender;
    Group group;
    ArrayList<User> receivers;
    public GroupController(User sender, Group group) {
        super(sender,group);
        this.sender = sender;
        this.group = group;
        ArrayList<User> receivers = new ArrayList<>(group.getUsers()) ;
        receivers.remove(sender);
       // super.setChat(group);
    }
    public WarningMessage sendNewMessage(String text) {
        Message message = new Message(text, LocalDateTime.now(),sender,receivers);
        group.addMessage(message);
        return WarningMessage.SUCCESS;
    }

    public ArrayList<Message> getUnSeenMessages() {
        ArrayList<Message> messages=new ArrayList<>();
        for (Message message : group.getMessages()) {
            if(message.getSender()!=sender&&!message.isSeenBy(sender))
                messages.add(message);
        }
        Collections.sort(messages,new MessageComparator());
        return messages;
    }
    public void forwardedInto(Message message) {
        Message message1=new Message(message,LocalDateTime.now(),sender,receivers);
        group.addMessage(message1);
    }
    public void reply(Message message,String text) {
        Message newMessage=new Message(text, LocalDateTime.now(),sender,receivers);
        group.addMessage(newMessage);
        newMessage.setReplied(message);
    }
}
