package model.database.dao;

import model.objects.organisation.Periode;
import model.objects.organisation.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeriodeDAO extends DaoID<Periode> {

    public PeriodeDAO(Connection connection) {
        super(connection, "Periodes");
    }

    @Override
    public void create(Periode obj) throws SQLException {
        String sql = "INSERT INTO Periodes " +
                " (nom, code, sessionId, periodeId) " +
                " VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.setInt(3, obj.getSession().getId());
        statement.setInt(4, obj.getPeriodeId());
        statement.executeUpdate();
    }

    @Override
    public void update(Periode obj) throws SQLException {
        String sql = "UPDATE Periodes SET " +
                " nom = ?, code = ?, sessionId = ?, periodeId = ? " +
                " WHERE id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.setInt(3, obj.getSession().getId());
        statement.setInt(4, obj.getPeriodeId());
        statement.setInt(5, obj.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Periode obj) throws SQLException {
        delete(obj.getId());
    }

    @Override
    protected Periode getInResultSet(ResultSet resultSet) throws SQLException {
        return new Periode(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("code"),
                new SessionDAO(connection).getById(resultSet.getInt("sessionId")),
                resultSet.getInt("periodeId")
        );
    }
}
