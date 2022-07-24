package Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Group extends Chat{
    private User creator;
    private String name;
    private String description;

    public Group(User creator, ArrayList<User> users, String name, String description) {
        super();
        setUsers(users);
        this.creator = creator;
        this.name = name;
        this.description = description;
        for (User user : users) {
            user.addChat(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }


    public void leaveGroup(User user) {
        getUsers().remove(user);
        if(user == creator)
            deleteGroup();
    }

    public void deleteGroup() {
        for (User user : getUsers()) {
            user.removeChat(this);
        }
    }

    @Override
    public String toString() {
        return "Group{" +
                "creator=" + creator +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void kick(User user) {
        getUsers().remove(user);
        user.removeChat(this);
    }

    public void addMember(User user) {
        getUsers().add(user);
        user.addChat(this);
    }
}
