package Controllers;

import Comparator.MessageComparator;
import Enums.WarningMessage;
import Models.Chat;
import Models.Message;
import Models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class ChatController {

    private User sender;
    private User receiver;
    private Chat chat;

    public ChatController(User user1, User user2) {
        this.sender = user1;
        this.receiver = user2;
        chat = Chat.getChat(user1,user2);
        if(chat == null)
            chat = new Chat(user1 ,user2);
    }

    public ChatController(User sender,Chat chat) {
        this.sender = sender;
        this.chat = chat;
    }

    public Chat getChat()
    {
        return chat;
    }
    public void setChat(Chat chat)
    {
        this.chat=chat;
    }



    public WarningMessage sendNewMessage(String text) {
        if(receiver.hasBlcoked(sender))
            return WarningMessage.YOU_ARE_BLOCKED;
        else {
            Message message = new Message(text, LocalDateTime.now(), sender, receiver);
            chat.addMessage(message);
            return WarningMessage.SUCCESS;
        }
    }

    public ArrayList<Message> getUnSeenMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        for (Message message : chat.getMessages()) {
            if(message.getSender() != sender && !message.isSeen()&&message.canSee(sender))
                messages.add(message);
        }
        Collections.sort(messages,new MessageComparator());
        return messages;
    }

    public ArrayList<Message> getPreviousMessages(int n) {
        Collections.sort(chat.getMessages(),new MessageComparator());
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i <(Math.min(n,chat.getMessages().size())) ; i++) {
            if(chat.getMessages().get(i).canSee(sender))
            messages.add(chat.getMessages().get(i));
        }
        return messages;
    }

    public void reply(Message message,String text) {
        Message newMessage=new Message(text, LocalDateTime.now(),sender,receiver);
        chat.addMessage(newMessage);
        newMessage.setReplied(message);
    }

    public WarningMessage editMessage(Message message,String text) {
        if(message.getForwardedFrom() == null) {
            message.setText(text);
            return WarningMessage.EDITED_SUCCESSFULLY;
        }
        else
            return WarningMessage.MESSAGE_IS_FORWARDED;
    }

    public void deleteMessageForMe(Message message) {
        message.deleteMessage(sender);
    }

    public void deleteMessageForEveryone(Message message) {
        message.deleteMessage();
        chat.removeMessage(message);
    }

    public void forwardedInto(Message message) {
        Message message1=new Message(message,LocalDateTime.now(),sender,receiver);
        chat.addMessage(message1);
    }

    public ArrayList<Message> searchMessage(String searchKey) {
        ArrayList<Message> messages=new ArrayList<>();
        for (Message message : chat.getMessages()) {
            if(message.getText().contains(searchKey)&&message.canSee(sender))
                messages.add(message);
        }
        Collections.sort(messages,new MessageComparator());
        return messages;
    }

}
