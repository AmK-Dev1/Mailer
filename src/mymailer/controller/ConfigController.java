package mymailer.controller;

import java.sql.SQLException;
import mymailer.DAO.ConfigDAO;
import mymailer.model.Config;
import mymailer.util.DataBase.IDbConnector;
import mymailer.util.SessionManager;

/**
 * Controller for managing the user's email configuration.
 */
public class ConfigController {
    private final ConfigDAO dao;

    public ConfigController(IDbConnector connector) {
        this.dao = new ConfigDAO(connector);
    }

    /**
     * Retrieves the current user's configuration, or null if none.
     */
    public Config getConfig() {
        try {
            int userId = SessionManager.getInstance().getCurrentUserID();
            return dao.findByUser(userId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Saves (inserts or updates) the user's configuration.
     * @return true if successful.
     */
    public boolean saveConfig(Config cfg) {
        try {
            cfg.setUserId(SessionManager.getInstance().getCurrentUserID());
            return dao.upsert(cfg);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
