package Configurations;

import ExceptionHandlers.JDBCExceptions.JDBCErrorConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/andiamoateatro";
    private static final String USER = "root";
    private static final String PASSWORD = "Admin";

    private static Connection connection = null;

    public static synchronized Connection getConnection() throws JDBCErrorConnectionException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Connection failed: " + e.getMessage());
               throw new JDBCErrorConnectionException("Connessione al database fallita.");
            }
        }
        return connection;
    }
}
