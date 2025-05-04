package mymailer.model;
import java.time.LocalDateTime;


public class Template {

    private int             userID;
    private String          name;
    private String          subject;
    private String          body;
    private LocalDateTime   createdAt;
    private LocalDateTime   updatedAt;
    public Template() { }

    public Template(int userID, String name, String subject, String body,
    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userID     = userID;
        this.name       = name;
        this.subject    = subject;
        this.body       = body;
        this.createdAt  = createdAt;
        this.updatedAt  = updatedAt;
    }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return name;
    }
}
