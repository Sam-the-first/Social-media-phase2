package Controllers;

import Models.Group;
import Models.User;

public class GroupAdminController extends  GroupController{
    public GroupAdminController(User sender, Group group) {
        super(sender, group);
    }

    public void deleteGroup() {
        group.deleteGroup();

    }

    public void kick(User user) {
        group.kick(user);
    }

    public void editGroupDescription(String newDescription) {
        group.setDescription(newDescription);
    }

    public void editGroupName(String newName) {
        group.setName(newName);
    }

    public void addMember(User user) {
        group.addMember(user);
    }
}
