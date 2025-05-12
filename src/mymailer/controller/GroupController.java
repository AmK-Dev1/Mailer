package mymailer.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import mymailer.DAO.GroupDAO;
import mymailer.model.Group;
import mymailer.util.DataBase.IDbConnector;
import mymailer.util.SessionManager;

/**
 * Controller for managing groups and their memberships.
 */
public class GroupController {
    private final GroupDAO dao;

    public GroupController(IDbConnector connector) {
        this.dao = new GroupDAO(connector);
    }

    /** Returns the number of groups for the current user. */
    public int getGroupCount() {
        try {
            int userID = SessionManager.getInstance().getCurrentUserID();
            return dao.countByUser(userID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    /** Returns all groups belonging to the current user. */
    public List<Group> findAll() {
        try {
            int userID = SessionManager.getInstance().getCurrentUserID();
            return dao.findByUser(userID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    /** Creates a new group for the current user. */
    public boolean addGroup(Group group) {
        try {
            group.setUserID(SessionManager.getInstance().getCurrentUserID());
            return dao.insert(group);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /** Adds a contact to a group. */
    public boolean addContactToGroup(int groupId, int contactId) {
        try {
            return dao.insertGroupContact(groupId, contactId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /** Removes a contact from a group. */
    public boolean removeContactFromGroup(int groupId, int contactId) {
        try {
            return dao.deleteGroupContact(groupId, contactId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}