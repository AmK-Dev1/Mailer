package mymailer.model;

import java.time.LocalDateTime;

/**
 * Represents an email template belonging to a user.
 */
public class Template {
    private int id;                  // primary key
    private int userID;              // owning user
    private String name;             // internal template name
    private String subject;          // email subject line
    private String body;             // email body (HTML/text)
    private LocalDateTime createdAt; // creation timestamp
    private LocalDateTime updatedAt; // last update timestamp

    public Template() {
        // no-arg constructor
    }

    /**
     * Constructor for creating a new template (id, timestamps set by DB).
     */
    public Template(int userID, String name, String subject, String body) {
        this.userID   = userID;
        this.name     = name;
        this.subject  = subject;
        this.body     = body;
    }

    /**
     * Full constructor for loading from the database.
     */
    public Template(int id,
                    int userID,
                    String name,
                    String subject,
                    String body,
                    LocalDateTime createdAt,
                    LocalDateTime updatedAt) {
        this.id        = id;
        this.userID    = userID;
        this.name      = name;
        this.subject   = subject;
        this.body      = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters & setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return name;
    }
}
