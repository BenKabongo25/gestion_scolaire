package model.database.dao;

import model.entites.personnes.base.Adresse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdresseDAO extends DaoID<Adresse> {

    public AdresseDAO(Connection connection) {
        super(connection, "Adresses");
    }

    @Override
    public boolean create(Adresse obj) {
        String sql = "INSERT INTO Adresses " +
                " (pays, ville, codepostal, adresse, complement) " +
                " VALUES (?,?,?,?,?) ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obj.getPays());
            statement.setString(2, obj.getVille());
            statement.setString(3, obj.getCodepostal());
            statement.setString(4, obj.getAdresse());
            statement.setString(5, obj.getComplement());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Adresse obj) {
        String sql = "UPDATE Adresses SET " +
                " pays = ?, ville = ?, codepostal = ?, adresse = ?, complement = ? " +
                " WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obj.getPays());
            statement.setString(2, obj.getVille());
            statement.setString(3, obj.getCodepostal());
            statement.setString(4, obj.getAdresse());
            statement.setString(5, obj.getComplement());
            statement.setInt(6, obj.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Adresse obj) {
        return delete(obj.getId());
    }

    @Override
    protected Adresse getInResultSet(ResultSet resultSet) {
        try {
            return new Adresse(
                    resultSet.getInt("id"),
                    resultSet.getString("pays"),
                    resultSet.getString("ville"),
                    resultSet.getString("codepostal"),
                    resultSet.getString("adresse"),
                    resultSet.getString("complement")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
