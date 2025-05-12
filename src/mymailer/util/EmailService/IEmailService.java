/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mymailer.util.EmailService;

import mymailer.model.Contact;
import mymailer.model.Group;
import mymailer.model.Template;
import javax.mail.MessagingException;

/**
 *
 * @author medkh
 */
public interface IEmailService {
    
    void send(Contact contact, Template template) throws MessagingException;

    /** Send to every member of a group. */
    void send(Group group, Template template) throws MessagingException;
}
