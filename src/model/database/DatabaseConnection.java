package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private static Connection connection;

    public static Connection getInstance() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:../../databasetests01.db");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
