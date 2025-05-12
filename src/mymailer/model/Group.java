package mymailer.model;

import java.time.LocalDateTime;

/**
 * Represents a user-defined group of contacts.
 */
public class Group {
    private int id;                   // primary key
    private int userID;              // owner's user ID
    private String name;             // group name
    private String description;      // optional description
    private LocalDateTime createdAt; // creation timestamp

    public Group() {
        // no-arg constructor
    }

    /**
     * Constructor for creating a new Group (id and createdAt assigned by DB).
     */
    public Group(int userID, String name, String description) {
        this.userID      = userID;
        this.name        = name;
        this.description = description;
    }

    /**
     * Full constructor for loading from database.
     */
    public Group(int id, int userID, String name, String description, LocalDateTime createdAt) {
        this.id          = id;
        this.userID      = userID;
        this.name        = name;
        this.description = description;
        this.createdAt   = createdAt;
    }

    // Getters and setters
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

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return name;
    }
}
