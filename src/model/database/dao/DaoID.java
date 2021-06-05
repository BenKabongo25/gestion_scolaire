package model.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DaoID <T> extends DAO <T> {

    protected String table = "";

    public DaoID(Connection connection) {
        super(connection);
    }

    public DaoID(Connection connection, String table) {
        super(connection);
        this.table = table;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM " + table + " WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public T getById(int id) {
        try {
            String sql = "SELECT * FROM " + table + " WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            // if (resultSet.first())
            if (resultSet.next())
                return getInResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
