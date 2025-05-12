package mymailer.DAO;

import mymailer.model.Group;
import mymailer.util.DataBase.IDbConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for Group and GroupContact operations.
 */
public class GroupDAO {
    private final IDbConnector connector;

    public GroupDAO(IDbConnector connector) {
        this.connector = connector;
    }

    /** Returns the number of groups for a given user. */
    public int countByUser(int userID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM [Group] WHERE UserID = ?";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    /** Returns all groups for a given user. */
    public List<Group> findByUser(int userID) throws SQLException {
        String sql = "SELECT ID, UserID, Name, Description, CreatedAt" +
                     " FROM [Group] WHERE UserID = ?";
        List<Group> groups = new ArrayList<>();
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Group g = new Group(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getTimestamp("CreatedAt").toLocalDateTime()
                    );
                    groups.add(g);
                }
            }
        }
        return groups;
    }

    /** Inserts a new group and sets its generated ID. */
    public boolean insert(Group group) throws SQLException {
        String sql = "INSERT INTO [Group](UserID, Name, Description) VALUES(?, ?, ?)";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, group.getUserID());
            ps.setString(2, group.getName());
            ps.setString(3, group.getDescription());
            int affected = ps.executeUpdate();
            if (affected == 0) return false;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    group.setId(keys.getInt(1));
                }
            }
            return true;
        }
    }

    /** Adds a contact to a group in the junction table. */
    public boolean insertGroupContact(int groupId, int contactId) throws SQLException {
        String sql = "INSERT INTO GroupContact(GroupID, ContactID) VALUES(?,?)";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, groupId);
            ps.setInt(2, contactId);
            return ps.executeUpdate() > 0;
        }
    }

    /** Removes a contact from a group. */
    public boolean deleteGroupContact(int groupId, int contactId) throws SQLException {
        String sql = "DELETE FROM GroupContact WHERE GroupID = ? AND ContactID = ?";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, groupId);
            ps.setInt(2, contactId);
            return ps.executeUpdate() > 0;
        }
    }
}
