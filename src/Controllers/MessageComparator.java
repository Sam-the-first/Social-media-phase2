package Controllers;

import Models.Message;

import java.util.Comparator;

public class MessageComparator implements Comparator<Message> {

    @Override
    public int compare(Message o1, Message o2) {
        if(o1.isAfter(o2))
            return 1;
        else
            return -1;
    }
}
