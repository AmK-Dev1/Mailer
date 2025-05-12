/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mymailer.util;

import mymailer.model.User;
import mymailer.util.EmailService.IEmailService;

/**
 *
 * @author medkh
 */
public class SessionManager {
    private static final SessionManager INSTANCE = new SessionManager();
    
    private int currentUserID;
    private IEmailService emailService;

    private SessionManager() { }

    public static SessionManager getInstance() {
        return INSTANCE;
    }

    /** Call this once after successful login */
    public void setCurrentUserID(int userID) {
        this.currentUserID = userID;
    }

    /** Returns the logged-in User object, or null if not set */
    public int getCurrentUserID() {
        return currentUserID;
    }
    
    public void setEmailService(IEmailService svc) {
        this.emailService = svc;
    }
    public IEmailService getEmailService() {
        return emailService;
    }

}
