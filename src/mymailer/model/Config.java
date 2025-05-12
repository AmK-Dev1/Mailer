package mymailer.model;

import java.time.LocalDateTime;

public class Config {
    private int             userID;
    private String          smtpHost;
    private int             smtpPort;
    private String          username;
    private String          appPassword;
    private boolean         useSSL;
    private LocalDateTime   updatedAt;

    public Config() { }

    public Config(int userID, String smtpHost, int smtpPort,
                       String username, String appPassword, boolean useSSL,
                       LocalDateTime updatedAt) {
        this.userID      = userID;
        this.smtpHost    = smtpHost;
        this.smtpPort    = smtpPort;
        this.username    = username;
        this.appPassword = appPassword;
        this.useSSL      = useSSL;
        this.updatedAt   = updatedAt;
    }

    public int getUserId() { return userID; }
    public void setUserId(int userID) { this.userID = userID; }

    public String getSmtpHost() { return smtpHost; }
    public void setSmtpHost(String smtpHost) { this.smtpHost = smtpHost; }

    public int getSmtpPort() { return smtpPort; }
    public void setSmtpPort(int smtpPort) { this.smtpPort = smtpPort; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getAppPassword() { return appPassword; }
    public void setAppPassword(String appPassword) { this.appPassword = appPassword; }

    public boolean isUseSSL() { return useSSL; }
    public void setUseSSL(boolean useSSL) { this.useSSL = useSSL; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return smtpHost + ":" + smtpPort;
    }
    
}
