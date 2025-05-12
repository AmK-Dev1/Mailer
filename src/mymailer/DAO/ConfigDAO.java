package mymailer.DAO;

import mymailer.model.Config;
import mymailer.util.DataBase.IDbConnector;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * DAO for managing per-user email SMTP configurations.
 */
public class ConfigDAO {
    private final IDbConnector connector;

    public ConfigDAO(IDbConnector connector) {
        this.connector = connector;
    }

    /**
     * Retrieves the Config for a given user, or null if none exists.
     */
    public Config findByUser(int userId) throws SQLException {
        String sql = "SELECT SmtpHost, SmtpPort, Username, AppPassword, UseSSL, UpdatedAt "
                   + "FROM EmailConfig WHERE UserID = ?";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Config cfg = new Config();
                    cfg.setUserId(userId);
                    cfg.setSmtpHost(rs.getString("SmtpHost"));
                    cfg.setSmtpPort(rs.getInt("SmtpPort"));
                    cfg.setUsername(rs.getString("Username"));
                    cfg.setAppPassword(rs.getString("AppPassword"));
                    cfg.setUseSSL(rs.getBoolean("UseSSL"));
                    Timestamp ts = rs.getTimestamp("UpdatedAt");
                    if (ts != null) {
                        cfg.setUpdatedAt(ts.toLocalDateTime());
                    }
                    return cfg;
                }
            }
        }
        return null;
    }

    /**
     * Inserts a new config row for the user.
     */
    public boolean insert(Config cfg) throws SQLException {
        String sql = "INSERT INTO EmailConfig(UserID, SmtpHost, SmtpPort, Username, AppPassword, UseSSL, UpdatedAt) "
                   + "VALUES (?, ?, ?, ?, ?, ?, GETDATE())";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cfg.getUserId());
            ps.setString(2, cfg.getSmtpHost());
            ps.setInt(3, cfg.getSmtpPort());
            ps.setString(4, cfg.getUsername());
            ps.setString(5, cfg.getAppPassword());
            ps.setBoolean(6, cfg.isUseSSL());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Updates an existing config row for the user.
     */
    public boolean update(Config cfg) throws SQLException {
        String sql = "UPDATE EmailConfig "
                   + "SET SmtpHost = ?, SmtpPort = ?, Username = ?, AppPassword = ?, UseSSL = ?, UpdatedAt = GETDATE() "
                   + "WHERE UserID = ?";
        try (Connection conn = connector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cfg.getSmtpHost());
            ps.setInt(2, cfg.getSmtpPort());
            ps.setString(3, cfg.getUsername());
            ps.setString(4, cfg.getAppPassword());
            ps.setBoolean(5, cfg.isUseSSL());
            ps.setInt(6, cfg.getUserId());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Inserts or updates the config for a user (upsert semantics).
     */
    public boolean upsert(Config cfg) throws SQLException {
        if (findByUser(cfg.getUserId()) == null) {
            return insert(cfg);
        } else {
            return update(cfg);
        }
    }
}
