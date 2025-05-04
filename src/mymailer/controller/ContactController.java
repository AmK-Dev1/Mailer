/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mymailer.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mymailer.model.Contact;
import mymailer.util.DataBase.IDbConnector;
import mymailer.util.SessionManager;
import mymailer.util.DataBase.IDbConnector;
import mymailer.DAO.ContactDAO;

public class ContactController {
    
    private final ContactDAO dao;

    public ContactController(IDbConnector connector) {
        this.dao = new ContactDAO(connector);
    }

    
    public int getContactCount() {
        try {
            int userID = SessionManager.getInstance().getCurrentUserID();
            return dao.countByUser(userID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
