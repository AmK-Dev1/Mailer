package mymailer.DAO;
import mymailer.model.Template;
import mymailer.model.User;
import mymailer.util.DataBase.IDbConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TemplateDAO {
    private IDbConnector connector;

    public TemplateDAO(IDbConnector connector) {
        this.connector = connector;
    }

    public int countByUser(int userID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM [Template] WHERE UserID = ?";

        try (Connection conn = connector.connect();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, userID);

            try (ResultSet rs = st.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

        public List<Template> findByUser(int userID) {
        String sql = "SELECT ID, UserId, Name, Subject, Body, CreatedAt, UpdatedAt "
                   + "FROM Template WHERE UserID = ?";
        List<Template> templates = new ArrayList<>();

        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Template t = new Template();
                    t.setUserID(rs.getInt("UserID"));
                    t.setName(rs.getString("Name"));
                    t.setSubject(rs.getString("Subject"));
                    t.setBody(rs.getString("Body"));
                    Timestamp created = rs.getTimestamp("CreatedAt");
                    if (created != null) {
                        t.setCreatedAt(created.toLocalDateTime());
                    }
                    Timestamp updated = rs.getTimestamp("UpdatedAt");
                    if (updated != null) {
                        t.setUpdatedAt(updated.toLocalDateTime());
                    }
                    templates.add(t);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return templates;
    }

}
