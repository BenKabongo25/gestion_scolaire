package tests.dao;

import model.database.DatabaseConnection;

import java.sql.*;

public class InitTest {

    public static void main(String[] args) {
        creationTableTest();
    }

    public static void creationTableTest() {
        try {
            DatabaseMetaData md = DatabaseConnection.getInstance().getMetaData();
            ResultSet rs1 = md.getTables(null, null, "%", null);

            while (rs1.next()) {
                String table = rs1.getString(3);
                System.out.println(table);
                ResultSetMetaData rs2 = DatabaseConnection
                        .getInstance()
                        .createStatement()
                        .executeQuery("SELECT * FROM " + table)
                        .getMetaData();
                for (int i = 1; i <= rs2.getColumnCount(); i++) {
                    System.out.print(rs2.getColumnName(i) + " ");
                    System.out.print(rs2.getColumnType(i) + " ");
                    System.out.print(rs2.getColumnDisplaySize(i) + " ");
                    System.out.print(rs2.isAutoIncrement(i) + " ");
                    System.out.println();
                }
                System.out.println("---------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
