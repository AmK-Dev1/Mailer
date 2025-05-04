package mymailer.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import mymailer.util.DataBase.IDbConnector;
import mymailer.util.SessionManager;
import mymailer.DAO.GroupDAO;
import mymailer.model.Group;

public class GroupController {
    
    private final GroupDAO dao;

    public GroupController(IDbConnector connector) {
        this.dao = new GroupDAO(connector);
    }
    
    public int getGroupCount() {
        try {
            int userID = SessionManager.getInstance().getCurrentUserID();
            return dao.countByUser(userID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public List<Group> findAll() {
        return dao.findByUser(SessionManager.getInstance().getCurrentUserID());
    }
}
