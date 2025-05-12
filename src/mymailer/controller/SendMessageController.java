package mymailer.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import javax.mail.MessagingException;
import mymailer.model.Contact;
import mymailer.model.Group;
import mymailer.model.Template;
import mymailer.util.DataBase.IDbConnector;
import mymailer.util.EmailService.IEmailService;

/**
 * Controller for Send Message functionality, with threaded send operations.
 */
public class SendMessageController {
    private final ContactController   contactCtrl;
    private final GroupController     groupCtrl;
    private final TemplateController  templateCtrl;
    private final IEmailService       emailService;

    // executor for background send tasks
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * @param connector    DB connector for sub-controllers
     * @param emailService email service implementation to send mail
     */
    public SendMessageController(IDbConnector connector, IEmailService emailService) {
        this.contactCtrl   = new ContactController(connector);
        this.groupCtrl     = new GroupController(connector);
        this.templateCtrl  = new TemplateController(connector);
        this.emailService  = emailService;
    }

    /**
     * Returns all contacts for selection.
     */
    public List<Contact> getAllContacts() {
        return contactCtrl.findAll();
    }

    /**
     * Returns all groups for selection.
     */
    public List<Group> getAllGroups() {
        return groupCtrl.findAll();
    }

    /**
     * Returns all templates for selection.
     */
    public List<Template> getAllTemplates() {
        return templateCtrl.findAll();
    }

    /**
     * Sends a single email to one contact using the given template.
     * @throws MessagingException on send failure
     */
    public void sendToContact(Contact contact, Template template) throws MessagingException {
        emailService.send(contact, template);
    }

    /**
     * Sends emails to all members of a group using the given template.
     * @throws MessagingException on any send failure
     */
    public void sendToGroup(Group group, Template template) throws MessagingException {
        emailService.send(group, template);
    }

    /**
     * Asynchronously sends to a contact.
     * @param contact  target contact
     * @param template email template
     * @param onSuccess callback on success
     * @param onError   callback with exception on failure
     */
    public void sendToContactAsync(Contact contact, Template template,
                                   Runnable onSuccess,
                                   Consumer<Exception> onError) {
        executor.submit(() -> {
            try {
                sendToContact(contact, template);
                onSuccess.run();
            } catch (Exception ex) {
                onError.accept(ex);
            }
        });
    }

    /**
     * Asynchronously sends to a group.
     * @param group     target group
     * @param template  email template
     * @param onSuccess callback on success
     * @param onError   callback with exception on failure
     */
    public void sendToGroupAsync(Group group, Template template,
                                 Runnable onSuccess,
                                 Consumer<Exception> onError) {
        executor.submit(() -> {
            try {
                sendToGroup(group, template);
                onSuccess.run();
            } catch (Exception ex) {
                onError.accept(ex);
            }
        });
    }

    /** Shuts down the background executor. */
    public void shutdown() {
        executor.shutdown();
    }
}
