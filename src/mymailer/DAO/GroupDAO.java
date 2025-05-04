/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mymailer.DAO;

import mymailer.model.Group;
import mymailer.model.User;
import mymailer.util.DataBase.IDbConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author medkh
 */

    

public class GroupDAO {
    
    private IDbConnector connector;

    public GroupDAO(IDbConnector connector) {
        this.connector = connector;
    }
    
    public int countByUser(int userID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM [Group] WHERE UserID = ?";

        try (Connection conn = connector.connect();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, userID);

            try (ResultSet rs = st.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }
    

    public List<Group> findByUser(int userID) {
        String sql = "SELECT ID, UserID, Name, Description, CreatedAt "
                   + "FROM [Group] WHERE UserID = ?";
        List<Group> groups = new ArrayList<>();

        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Group g = new Group();
                    g.setUserID(rs.getInt("UserID"));
                    g.setName(rs.getString("Name"));
                    g.setDescription(rs.getString("Description"));
                    Timestamp ts = rs.getTimestamp("CreatedAt");
                    if (ts != null) {
                        g.setCreatedAt(ts.toLocalDateTime());
                    }
                    groups.add(g);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groups;
    }

}
