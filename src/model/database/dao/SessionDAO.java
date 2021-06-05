package model.database.dao;

import model.entites.organisation.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDAO extends DaoType1<Session> {

    public SessionDAO(Connection connection) {
        super(connection, "Sessions");
    }

    @Override
    public boolean create(Session obj) {
        String sql = "INSERT INTO Sessions " +
                " (nom, code, organisationId, sessionId, nbPeriodes) " +
                " VALUES (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getCode());
            statement.setInt(3, obj.getOrganisation().getId());
            statement.setInt(4, obj.getSessionId());
            statement.setInt(5, obj.getNbPeriodes());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Session obj) {
        String sql = "UPDATE Sessions " +
                " nom = ?, code = ?, organisationId = ?, sessionId = ?, nbPeriodes = ? " +
                " WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getCode());
            statement.setInt(3, obj.getOrganisation().getId());
            statement.setInt(4, obj.getSessionId());
            statement.setInt(5, obj.getNbPeriodes());
            statement.setInt(6, obj.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Session obj) {
        return delete(obj.getId());
    }

    @Override
    protected Session getInResultSet(ResultSet resultSet) {
        return null;
    }

    private void addPeriodes(Session session) {

    }
}
