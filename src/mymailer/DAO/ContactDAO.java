package mymailer.DAO;

import mymailer.model.Contact;
import mymailer.util.DataBase.IDbConnector;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Contact entities.
 */
public class ContactDAO {
    private final IDbConnector connector;

    public ContactDAO(IDbConnector connector) {
        this.connector = connector;
    }

    /** Returns the number of contacts for a given user. */
    public int countByUser(int userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Contact WHERE UserId = ?";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    /** Finds all contacts for a given user. */
    public List<Contact> findByUser(int userId) throws SQLException {
        String sql = "SELECT ID, UserId, Name, Email, CreatedAt "
                   + "FROM Contact WHERE UserId = ?";
        List<Contact> list = new ArrayList<>();
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Contact c = new Contact(
                        rs.getInt("ID"),
                        rs.getInt("UserId"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getTimestamp("CreatedAt").toLocalDateTime()
                    );
                    list.add(c);
                }
            }
        }
        return list;
    }

    /** Finds all contacts in a specific group. */
    public List<Contact> findByGroup(int groupId) throws SQLException {
        String sql = "SELECT c.ID, c.UserId, c.Name, c.Email, c.CreatedAt "
                   + "FROM Contact c "
                   + "INNER JOIN GroupContact gc ON c.ID = gc.ContactID "
                   + "WHERE gc.GroupId = ?";
        List<Contact> list = new ArrayList<>();
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, groupId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Contact c = new Contact(
                        rs.getInt("ID"),
                        rs.getInt("UserId"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getTimestamp("CreatedAt").toLocalDateTime()
                    );
                    list.add(c);
                }
            }
        }
        return list;
    }

    /** Inserts a new contact. Returns true if successful. */
    public boolean insert(Contact contact) throws SQLException {
        String sql = "INSERT INTO Contact(UserId, Name, Email) VALUES(?, ?, ?)";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, contact.getUserId());
            ps.setString(2, contact.getName());
            ps.setString(3, contact.getEmail());
            int affected = ps.executeUpdate();
            if (affected == 0) return false;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    contact.setId(keys.getInt(1));
                }
            }
            return true;
        }
    }

    /** Updates an existing contact. Returns true if successful. */
    public boolean update(Contact contact) throws SQLException {
        String sql = "UPDATE Contact SET Name = ?, Email = ? WHERE ContactId = ?";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getEmail());
            ps.setInt(3, contact.getId());
            return ps.executeUpdate() > 0;
        }
    }

    /** Deletes a contact by ID. Returns true if successful. */
    public boolean deleteById(int contactId) throws SQLException {
        String sql = "DELETE FROM Contact WHERE ContactId = ?";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, contactId);
            return ps.executeUpdate() > 0;
        }
    }
}
