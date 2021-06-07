package model.database.dao;

import model.entites.organisation.Periode;
import model.entites.organisation.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeriodeDAO extends DaoID<Periode> {

    public PeriodeDAO(Connection connection) {
        super(connection, "Periodes");
    }

    @Override
    public boolean create(Periode obj) {
        String sql = "INSERT INTO Periodes " +
                " (nom, code, sessionId, periodeId) " +
                " VALUES (?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getCode());
            statement.setInt(3, obj.getSession().getId());
            statement.setInt(4, obj.getPeriodeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Periode obj) {
        String sql = "UPDATE Periodes SET " +
                " nom = ?, code = ?, sessionId = ?, periodeId = ? " +
                " WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getCode());
            statement.setInt(3, obj.getSession().getId());
            statement.setInt(4, obj.getPeriodeId());
            statement.setInt(5, obj.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Periode obj) {
        return delete(obj.getId());
    }

    @Override
    protected Periode getInResultSet(ResultSet resultSet) {
        Periode periode;
        Session session = new Session();
        try {
            session = new SessionDAO(connection).getById(resultSet.getInt("sessionId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            periode = new Periode(
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("code"),
                    session,
                    resultSet.getInt("periodeId")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return periode;
    }
}
