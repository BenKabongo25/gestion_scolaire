package model.database.dao;

import model.objects.organisation.Organisation;
import model.objects.organisation.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganisationDAO extends DaoType1<Organisation> {

    public OrganisationDAO(Connection connection) {
        super(connection, "Organisations");
    }

    @Override
    public void create(Organisation obj) throws SQLException {
        String sql = "INSERT INTO Organisations " +
                " (nom, code, nbSessions) " +
                " VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.setInt(3, obj.getNbSessions());
        statement.executeUpdate();
    }

    @Override
    public void update(Organisation obj) throws SQLException {
        String sql = "UPDATE Organisations SET " +
                " nom = ?, code = ?, nbSessions = ? " +
                " WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.setInt(3, obj.getNbSessions());
        statement.setInt(5, obj.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Organisation obj) throws SQLException {
        delete(obj.getId());
    }

    @Override
    protected Organisation getInResultSet(ResultSet resultSet) throws SQLException {
        Organisation organisation = new Organisation(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("code"),
                resultSet.getInt("nbSessions")
        );
        addSessions(organisation);
        return organisation;
    }

    private void addSessions(Organisation organisation) throws SQLException {
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
    }
}
