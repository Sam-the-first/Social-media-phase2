package Models;

import java.util.HashSet;
import java.util.Set;

public class Group extends Chat{
    private User creator;
    private Set<User> admins=new HashSet<>();
    private String name;
    private String description;

    public Group(User creator, Set<User> users, String name, String description) {
        super();
        getUsers().addAll(users);
        this.creator = creator;
        this.name = name;
        this.description = description;
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

    public Set<User> getAdmins() {
        return admins;
    }

    public void addAdmin(User user) {
        admins.add(user);
    }

    public void leaveGroup(User user) {
        getUsers().remove(user);
        admins.remove(user);
        if(user == creator)
            deleteGroup();
    }

    public void deleteGroup() {

    }
}
