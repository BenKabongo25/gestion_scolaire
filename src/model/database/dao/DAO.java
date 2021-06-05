package model.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T> {

    protected Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }

    public abstract boolean create(T obj);
    public abstract boolean update(T obj);
    public abstract boolean delete(T obj);
    public abstract boolean delete(int id);
    public abstract T getById(int id);
    protected abstract T getInResultSet(ResultSet resultSet);

    protected List<T> getBySqlRequest(String sql) {
        List<T> ts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                T t = getInResultSet(resultSet);
                if (t != null)
                    ts.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ts;
    }
}
