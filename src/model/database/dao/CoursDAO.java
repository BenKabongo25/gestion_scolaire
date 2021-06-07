package model.database.dao;

import model.entites.cours.Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoursDAO extends DaoType1<Cours> {

    public CoursDAO(Connection connection) {
        super(connection, "Cours");
    }

    @Override
    public boolean create(Cours obj) {
        String sql = "INSERT INTO Cours (nom, code) VALUES (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getCode());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Cours obj) {
        String sql = "UPDATE Cours SET nom = ?, code = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getCode());
            statement.setInt(3, obj.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Cours obj) {
        return delete(obj.getId());
    }

    @Override
    protected Cours getInResultSet(ResultSet resultSet) {
        Cours cours;
        try {
            cours = new Cours(
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("code")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return cours;
    }
}
