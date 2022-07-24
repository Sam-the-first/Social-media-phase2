package Controllers;

import Models.Group;
import Models.User;

public class GroupAdminController extends  GroupController{
    public GroupAdminController(User sender, Group group) {
        super(sender, group);
    }
}
