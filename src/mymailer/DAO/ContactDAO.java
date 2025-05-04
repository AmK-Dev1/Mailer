/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mymailer.DAO;
import mymailer.model.User;
import mymailer.util.DataBase.IDbConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author medkh
 */

    

public class ContactDAO {
    
    private IDbConnector connector;

    public ContactDAO(IDbConnector connector) {
        this.connector = connector;
    }
    
    public int countByUser(int userID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Contact WHERE UserID = ?";

        try (Connection conn = connector.connect();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, userID);

            try (ResultSet rs = st.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }
    
}
