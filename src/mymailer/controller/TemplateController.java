package mymailer.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import mymailer.DAO.TemplateDAO;
import mymailer.model.Template;
import mymailer.util.DataBase.IDbConnector;
import mymailer.util.SessionManager;

/**
 * Controller for managing email templates in the MyMailer application.
 */
public class TemplateController {
    private final TemplateDAO dao;

    public TemplateController(IDbConnector connector) {
        this.dao = new TemplateDAO(connector);
    }

    /**
     * Returns the number of templates for the logged-in user.
     */
    public int getTemplateCount() {
        try {
            int userId = SessionManager.getInstance().getCurrentUserID();
            return dao.countByUser(userId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    /**
     * Retrieves all templates for the logged-in user.
     */
    public List<Template> findAll() {
        try {
            int userId = SessionManager.getInstance().getCurrentUserID();
            return dao.findByUser(userId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Adds a new template for the logged-in user.
     * @return true if insertion succeeded.
     */
    public boolean addTemplate(Template template) {
        try {
            template.setUserID(SessionManager.getInstance().getCurrentUserID());
            return dao.insert(template);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing template.
     * @return true if update succeeded.
     */
    public boolean updateTemplate(Template template) {
        try {
            return dao.update(template);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a template by its ID.
     * @return true if deletion succeeded.
     */
    public boolean deleteTemplate(int templateId) {
        try {
            return dao.deleteById(templateId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
