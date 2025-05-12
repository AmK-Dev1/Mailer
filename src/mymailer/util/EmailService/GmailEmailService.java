package mymailer.util.EmailService;

import mymailer.model.Config;
import mymailer.controller.ConfigController;
import mymailer.controller.ContactController;
import mymailer.model.Contact;
import mymailer.model.Group;
import mymailer.model.Template;
import mymailer.util.DataBase.IDbConnector;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.List;
import java.util.Properties;

/**
 * Gmail implementation of IEmailService using Jakarta Mail.
 */
public class GmailEmailService implements IEmailService {
    private final Session session;
    private final String fromAddress;
    private final ContactController contactCtrl;

    public GmailEmailService(IDbConnector connector) {
        // Initialize controllers
        contactCtrl = new ContactController(connector);
        Config cfg = new ConfigController(connector).getConfig();
        if (cfg == null) {
            throw new IllegalStateException("No SMTP configuration found for user");
        }

        // Set sender address
        this.fromAddress = cfg.getUsername();

        // Configure SMTP properties for SSL on port (e.g., 465)
        Properties props = new Properties();
        props.put("mail.smtp.host",       cfg.getSmtpHost());
        props.put("mail.smtp.port",       String.valueOf(cfg.getSmtpPort()));
        props.put("mail.smtp.auth",       "true");
        props.put("mail.smtp.ssl.enable", "true");

        // Create mail session with authentication
        this.session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                    cfg.getUsername(),
                    cfg.getAppPassword()
                );
            }
        });
    }

    @Override
    public void send(Contact contact, Template template) throws MessagingException {
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(fromAddress));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(contact.getEmail()));
        msg.setSubject(template.getSubject());
        msg.setText(template.getBody());
        Transport.send(msg);
    }

    @Override
    public void send(Group group, Template template) throws MessagingException {
        List<Contact> members = contactCtrl.findByGroup(group.getId());
        for (Contact c : members) {
            send(c, template);
        }
    }
}
