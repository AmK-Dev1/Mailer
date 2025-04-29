package mymailer.util.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlServerConnector implements IDbConnector {

    private Connection connection;
    private final String url;

    public SqlServerConnector(String server, String database) {
        this.url = "jdbc:sqlserver://" + server + ";databaseName=" + database + ";encrypt=true;trustServerCertificate=true;integratedSecurity=true;";
    }

    @Override
    public Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url);
        }
        return connection;
    }

    @Override
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
