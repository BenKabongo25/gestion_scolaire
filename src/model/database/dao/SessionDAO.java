package model.database.dao;

import model.objects.organisation.Organisation;
import model.objects.organisation.Periode;
import model.objects.organisation.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDAO extends DaoType1<Session> {

    public SessionDAO(Connection connection) {
        super(connection, "Sessions");
    }

    @Override
    public void create(Session obj) {
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
        }
    }

    @Override
    public void update(Session obj) {
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
        }
    }

    @Override
    public void delete(Session obj) throws SQLException {
        delete(obj.getId());
    }

    @Override
    protected Session getInResultSet(ResultSet resultSet) throws SQLException {
        Session session = new Session(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("code"),
                new OrganisationDAO(connection)
                        .getById(resultSet.getInt("organisationId")),
                resultSet.getInt("sessionId"),
                resultSet.getInt("nbPeriodes")
        );
        addPeriodes(session);
        return session;
    }

    private void addPeriodes(Session session) throws SQLException {
        String sql = "SELECT * FROM Periodes WHERE sessionId = ?";
        PreparedStatement statement = connection.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1, session.getId());
        ResultSet resultSet = statement.executeQuery();
        PeriodeDAO periodeDAO = new PeriodeDAO(connection);
        while (resultSet.next()) {
            Periode periode = periodeDAO.getById(resultSet.getInt("id"));
            if (periode != null)
                session.addPeriode(periode);
        }
    }
}
