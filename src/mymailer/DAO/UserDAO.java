package mymailer.dao;

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
        
        String sqlQuery = "SELECT * FROM users WHERE username = ? AND password = ?";
          
        
        try (Connection conn = connector.connect();
             PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery(); 

            return rs.next(); // if a record exists, authentication successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add new user
    public boolean register(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

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
}
