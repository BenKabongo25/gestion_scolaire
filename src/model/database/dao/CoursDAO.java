package model.database.dao;

import model.objects.cours.Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoursDAO extends DaoType1<Cours> {

    public CoursDAO(Connection connection) {
        super(connection, "Cours");
    }

    @Override
    public void create(Cours obj) throws SQLException {
        String sql = "INSERT INTO Cours (nom, code) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.executeUpdate();
    }

    @Override
    public void update(Cours obj) throws SQLException {
        String sql = "UPDATE Cours SET nom = ?, code = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.setInt(3, obj.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Cours obj) throws SQLException {
        delete(obj.getId());
    }

    @Override
    protected Cours getInResultSet(ResultSet resultSet) throws SQLException {
        return new Cours(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("code"));
    }
}
