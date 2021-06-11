package model.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DaoID <T> extends DAO <T> {

    protected String table = "";

    public DaoID(Connection connection) {
        super(connection);
    }

    public DaoID(Connection connection, String table) {
        super(connection);
        this.table = table;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM " + table + " WHERE id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public T getById(int id) throws SQLException {
        String sql = "SELECT * FROM " + table + " WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        // if (resultSet.first())
        if (resultSet.next())
            return getInResultSet(resultSet);
        return null;
    }

    @Override
    public List<T> all() throws SQLException {
        String sql = "SELECT * FROM " + table;
        return getBySqlRequest(sql);
    }

}
