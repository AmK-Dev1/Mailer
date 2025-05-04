package mymailer.controller;
import java.sql.SQLException;
import java.util.List;

import mymailer.util.DataBase.IDbConnector;
import mymailer.util.SessionManager;
import mymailer.DAO.TemplateDAO;
import mymailer.model.Template;


public class TemplateController {
    private final TemplateDAO dao;

    public TemplateController(IDbConnector connector) {
        this.dao = new TemplateDAO(connector);
    }

    public int getTemplateCount() {
        try {
            int userID = SessionManager.getInstance().getCurrentUserID();
            return dao.countByUser(userID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public List<Template> findAll() {
        return dao.findByUser(SessionManager.getInstance().getCurrentUserID());
    }
}
