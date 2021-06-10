package model.database.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class DaoType1<T> extends DaoID<T> {

    public DaoType1(Connection connection) {
        super(connection);
    }

    public DaoType1(Connection connection, String table) {
        super(connection, table);
    }

    public List<T> getByNom(String nom) throws SQLException {
        String sql = "SELECT * FROM " + table + " WHERE LOWER(nom) LIKE '" + nom.toLowerCase() +"%'";
        return getBySqlRequest(sql);
    }

    public List<T> getByCode(String code) throws SQLException {
        String sql = "SELECT * FROM " + table + " WHERE LOWER(code) LIKE '" + code.toLowerCase() +"%'";
        return getBySqlRequest(sql);
    }

}
