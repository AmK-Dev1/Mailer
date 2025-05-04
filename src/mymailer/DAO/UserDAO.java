package mymailer.DAO;

import mymailer.model.User;
import mymailer.util.DataBase.IDbConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private IDbConnector connector;

    public UserDAO(IDbConnector connector) {
        this.connector = connector;
    }

    // Authentication method
    public boolean login(String username, String password) {
        
        String sqlQuery = "SELECT * FROM [user] WHERE username = ? AND password = ?";
          
        
        try (Connection conn = connector.connect();
             PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery(); 

            return rs.next(); 

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add new user
    public boolean register(User user) {
        String sql = "INSERT INTO [user] (username, password) VALUES (?, ?)";

        try (Connection conn = connector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getUserIdByEmail(String email) {
        
        String sql = "SELECT ID FROM [user] WHERE userName = ?";

        try (Connection conn = connector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // get the integer from the first column (or use the column name):
                    return rs.getInt(1);
                } else {
                    // no user with that email
                    return -1;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // on error, also return -1 (or rethrow as unchecked, if you prefer)
            return -1;
        }
    }
    
}
