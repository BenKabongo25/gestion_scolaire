package model.database.dao;

import model.objects.adresse.Adresse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdresseDAO extends DaoID<Adresse> {

    public AdresseDAO(Connection connection) {
        super(connection, "Adresses");
    }

    @Override
    public void create(Adresse obj) throws SQLException {
        String sql = "INSERT INTO Adresses " +
                " (pays, ville, codepostal, adresse, complement) " +
                " VALUES (?,?,?,?,?) ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getPays());
        statement.setString(2, obj.getVille());
        statement.setString(3, obj.getCodepostal());
        statement.setString(4, obj.getAdresse());
        statement.setString(5, obj.getComplement());
        statement.executeUpdate();
    }

    @Override
    public void update(Adresse obj) throws SQLException {
        String sql = "UPDATE Adresses SET " +
                " pays = ?, ville = ?, codepostal = ?, adresse = ?, complement = ? " +
                " WHERE id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getPays());
        statement.setString(2, obj.getVille());
        statement.setString(3, obj.getCodepostal());
        statement.setString(4, obj.getAdresse());
        statement.setString(5, obj.getComplement());
        statement.setInt(6, obj.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Adresse obj) throws SQLException {
        delete(obj.getId());
    }

    @Override
    protected Adresse getInResultSet(ResultSet resultSet) throws SQLException {
        return new Adresse(
                resultSet.getInt("id"),
                resultSet.getString("pays"),
                resultSet.getString("ville"),
                resultSet.getString("codepostal"),
                resultSet.getString("adresse"),
                resultSet.getString("complement")
        );
    }
}
