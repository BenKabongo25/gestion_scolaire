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

    public abstract void create(T obj) throws SQLException;
    public abstract void update(T obj) throws SQLException;
    public abstract void delete(T obj) throws SQLException;
    public abstract List<T> all() throws SQLException;
    protected abstract T getInResultSet(ResultSet resultSet) throws SQLException;

    protected List<T> getBySqlRequest(String sql) throws SQLException {
        List<T> ts = new ArrayList<>();
        Statement statement = connection.createStatement(
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            T t = getInResultSet(resultSet);
            if (t != null)
                ts.add(t);
        }
        return ts;
    }
}
