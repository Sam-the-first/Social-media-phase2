package Controllers;

import Enums.WarningMessage;
import Models.Group;
import Models.Message;
import Models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GroupController extends Controller{
    User sender;
    Group group;
    ArrayList<User> receivers;
    GroupController(User sender, Group group) {
        this.sender = sender;
        this.group = group;
        ArrayList<User> receivers = new ArrayList<>(group.getUsers()) ;
        receivers.remove(sender);
    }
    public WarningMessage sendNewMessage(String text) {
        Message message = new Message(text, LocalDateTime.now(),sender,receivers);
        group.addMessage(message);
        return WarningMessage.SUCCESS;
    }

}
