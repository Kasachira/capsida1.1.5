package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONNECTURL = "jdbc:mysql://localhost:3306/mydbtest";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(CONNECTURL,USERNAME,PASSWORD);
            System.out.println("Connected");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
