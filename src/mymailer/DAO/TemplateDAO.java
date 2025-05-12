package mymailer.DAO;

import mymailer.model.Template;
import mymailer.util.DataBase.IDbConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Template entities.
 */
public class TemplateDAO {
    private final IDbConnector connector;

    public TemplateDAO(IDbConnector connector) {
        this.connector = connector;
    }

    /** Returns the number of templates for a given user. */
    public int countByUser(int userID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM [Template] WHERE UserID = ?";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    /** Finds all templates for a specific user. */
    public List<Template> findByUser(int userID) throws SQLException {
        String sql = "SELECT ID, UserID, Name, Subject, Body, CreatedAt, UpdatedAt "
                   + "FROM [Template] WHERE UserID = ?";
        List<Template> list = new ArrayList<>();
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Template t = new Template(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getString("Name"),
                        rs.getString("Subject"),
                        rs.getString("Body"),
                        rs.getTimestamp("CreatedAt").toLocalDateTime(),
                        rs.getTimestamp("UpdatedAt").toLocalDateTime()
                    );
                    list.add(t);
                }
            }
        }
        return list;
    }

    /** Inserts a new template. Returns true if successful. */
    public boolean insert(Template template) throws SQLException {
        String sql = "INSERT INTO [Template](UserID, Name, Subject, Body, CreatedAt, UpdatedAt) "
                   + "VALUES(?,?,?,?,GETDATE(),GETDATE())";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, template.getUserID());
            ps.setString(2, template.getName());
            ps.setString(3, template.getSubject());
            ps.setString(4, template.getBody());
            int affected = ps.executeUpdate();
            if (affected == 0) return false;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    template.setId(keys.getInt(1));
                }
            }
            return true;
        }
    }

    /** Updates an existing template. Returns true if successful. */
    public boolean update(Template template) throws SQLException {
        String sql = "UPDATE [Template] SET Name = ?, Subject = ?, Body = ?, UpdatedAt = GETDATE() "
                   + "WHERE ID = ?";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, template.getName());
            ps.setString(2, template.getSubject());
            ps.setString(3, template.getBody());
            ps.setInt(4, template.getId());
            return ps.executeUpdate() > 0;
        }
    }

    /** Deletes a template by its ID. Returns true if successful. */
    public boolean deleteById(int templateId) throws SQLException {
        String sql = "DELETE FROM [Template] WHERE ID = ?";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, templateId);
            return ps.executeUpdate() > 0;
        }
    }
}
