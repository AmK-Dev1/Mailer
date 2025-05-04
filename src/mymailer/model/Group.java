package mymailer.model;
import java.time.LocalDateTime;

/**
 *
 * @author medkh
 */
public class Group {

    private int             userID;
    private String          name;
    private String          description;
    private LocalDateTime   createdAt;

    public Group(){};

    public Group(int userID, String name, String description, LocalDateTime createdAt) {
        this.userID      = userID;
        this.name        = name;
        this.description = description;
        this.createdAt   = createdAt;
    }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return name;
    }
}
