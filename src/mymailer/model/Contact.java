package mymailer.model;

import java.time.LocalDateTime;

/**
 * Represents a contact belonging to a user in the MyMailer app.
 */
public class Contact {
    private int id;                // Primary key
    private int userId;            // Owner's user ID
    private String name;           // Contact's name
    private String email;          // Contact's email address
    private LocalDateTime createdAt; // Timestamp when contact was created

    public Contact() {
        // no-arg constructor for frameworks or manual population
    }

    /**
     * Constructor for creating a new contact (ID and timestamp set by DB).
     */
    public Contact(int userId, String name, String email) {
        this.userId = userId;
        this.name   = name;
        this.email  = email;
    }

    /**
     * Full constructor, useful for reading from DB.
     */
    public Contact(int id, int userId, String name, String email, LocalDateTime createdAt) {
        this.id        = id;
        this.userId    = userId;
        this.name      = name;
        this.email     = email;
        this.createdAt = createdAt;
    }

    // Getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return name ;
    }
}
