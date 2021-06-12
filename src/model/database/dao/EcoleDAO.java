package model.database.dao;

import model.objects.ecole.Ecole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class EcoleDAO extends DAO<Ecole> {

    public EcoleDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Ecole obj) throws SQLException {
        String sql = "INSERT INTO Ecole " +
                " (nom, sigle, telephone, email, adresseId) " +
                " VALUES (?,?,?,?,?) ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getSigle());
        statement.setString(3, obj.getTelephone());
        statement.setString(4, obj.getEmail());
        statement.setInt(5, obj.getAdresse().getId());
        statement.executeUpdate();
    }

    @Override
    public void update(Ecole obj) throws SQLException {
        String sql = "UPDATE Ecole SET " +
                " nom = ?, sigle = ?, telephone = ?, email = ?, adresse = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getSigle());
        statement.setString(3, obj.getTelephone());
        statement.setString(4, obj.getEmail());
        statement.setInt(5, obj.getAdresse().getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Ecole obj) throws SQLException {
        connection.createStatement().execute("DELETE FROM Ecole");
    }

    @Override
    public List<Ecole> all() throws SQLException {
        ResultSet resultSet = connection
                .createStatement()
                .executeQuery("SELECT * FROM Ecole");
        if (resultSet.next())
            return Collections.singletonList(getInResultSet(resultSet));
        return Collections.emptyList();
    }

    @Override
    protected Ecole getInResultSet(ResultSet resultSet) throws SQLException {
        return new Ecole(
                resultSet.getString("nom"),
                resultSet.getString("sigle"),
                resultSet.getString("telephone"),
                resultSet.getString("email"),
                new AdresseDAO(connection).getById(resultSet.getInt("adresseId"))
        );
    }
}
