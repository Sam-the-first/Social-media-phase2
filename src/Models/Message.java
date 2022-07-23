package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Message {

    private int ID;
    private String text ;
    private LocalDateTime date;
    private Set<User> seen=new HashSet<>();
    private Message replied;
    private User sender;
    private User forwardedFrom;
    private ArrayList<User> receivers=new ArrayList<>();
    private boolean deleted=false;
    public Message(String text, LocalDateTime date, User sender, User receiver) {
        this.text=text;
        this.date=date;
        this.sender=sender;
        this.receivers.add(receiver);
        //seen=false;
    }
    public Message(Message message, LocalDateTime date, User sender, User receiver) {
        this.text=message.getText();
        this.date=date;
        this.sender=sender;
        this.receivers.add(receiver);
        forwardedFrom=message.getSender();
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Message(String text, LocalDateTime date, User sender, ArrayList<User> receiver) {
        this.text=text;
        this.date=date;
        this.sender=sender;
        this.receivers=receiver;
    }

    public User getForwardedFrom() {
        return forwardedFrom;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isSeen() {
        return (seen.size()>0);
    }

    public void setSeen(User user) {
        this.seen.add(user);
    }

    public Message getReplied() {
        return replied;
    }

    public void setReplied(Message replied) {
        this.replied = replied;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receivers.get(0);
    }

    public void setReceiver(User receiver) {
        this.receivers.set(0,receiver) ;
    }

    public boolean isAfter(Message message) {
        if(date.isAfter(message.getDate()))
            return true;
        else
            return false;
    }

    public void deleteMessage(User user)
    {
        if(user==sender)
            deleted=true;
        else
            receivers.remove(user);
    }
    public boolean canSee(User user)
    {
        if(user==sender) {
            if(deleted)
            return false;
            else
                return true;
        }
        if(!receivers.contains(user)) {
            return false;
        }
        return true;
    }
}
