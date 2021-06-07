package model.database.dao;

import model.entites.organisation.Organisation;
import model.entites.organisation.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganisationDAO extends DaoType1<Organisation> {

    public OrganisationDAO(Connection connection) {
        super(connection, "Organisations");
    }

    @Override
    public boolean create(Organisation obj) {
        String sql = "INSERT INTO Organisations " +
                " (nom, code, nbSessions) " +
                " VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getCode());
            statement.setInt(3, obj.getNbSessions());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Organisation obj) {
        String sql = "UPDATE Organisations SET " +
                " nom = ?, code = ?, nbSessions = ? " +
                " WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getCode());
            statement.setInt(3, obj.getNbSessions());
            statement.setInt(5, obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Organisation obj) {
        return delete(obj.getId());
    }

    @Override
    protected Organisation getInResultSet(ResultSet resultSet) {
        Organisation organisation;
        try {
            organisation = new Organisation(
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("code"),
                    resultSet.getInt("nbSessions")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        addSessions(organisation);
        return organisation;
    }

    private void addSessions(Organisation organisation) {
        try {
            String sql = "SELECT * FROM Sessions WHERE organisationId = ?";
            PreparedStatement statement = connection.prepareStatement(sql,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, organisation.getId());
            ResultSet resultSet = statement.executeQuery();
            SessionDAO sessionDAO = new SessionDAO(connection);
            while (resultSet.next()) {
                Session session = sessionDAO.getById(resultSet.getInt("id"));
                if (session != null)
                    organisation.addSession(session);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
