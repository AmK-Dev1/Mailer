package mymailer.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mymailer.DAO.ContactDAO;
import mymailer.model.Contact;
import mymailer.util.DataBase.IDbConnector;
import mymailer.util.SessionManager;

/**
 * Controller for managing contacts in the MyMailer application.
 */
public class ContactController {
    private final ContactDAO dao;

    public ContactController(IDbConnector connector) {
        this.dao = new ContactDAO(connector);
    }

    /**
     * Returns the number of contacts for the logged-in user.
     */
    public int getContactCount() {
        try {
            int userId = SessionManager.getInstance().getCurrentUserID();
            return dao.countByUser(userId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    /**
     * Retrieves all contacts for the logged-in user.
     */
    public List<Contact> findAll() {
        try {
            int userId = SessionManager.getInstance().getCurrentUserID();
            return dao.findByUser(userId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Adds a new contact for the logged-in user.
     * @return true if insert succeeded, false otherwise.
     */
    public boolean addContact(Contact contact) {
        try {
            // Associate with current user
            contact.setUserId(SessionManager.getInstance().getCurrentUserID());
            return dao.insert(contact);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing contact's details.
     * @return true if update succeeded.
     */
    public boolean updateContact(Contact contact) {
        try {
            return dao.update(contact);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a contact by its ID.
     * @return true if delete succeeded.
     */
    public boolean deleteContact(int contactId) {
        try {
            return dao.deleteById(contactId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
        /**
     * Retrieves all contacts that belong to a specific group.
     */
    public List<Contact> findByGroup(int groupId) {
        try {
            return dao.findByGroup(groupId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
    
}
