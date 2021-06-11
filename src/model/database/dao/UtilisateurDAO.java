package model.database.dao;

import model.objects.utilisateurs.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDAO extends DaoID<Utilisateur> {

    public UtilisateurDAO(Connection connection) {
        super(connection, "Utilisateurs");
    }

    @Override
    public void create(Utilisateur obj) throws SQLException {
        String sql = "INSERT INTO Utilisateurs " +
                " (username, password, nom, deuxiemeNom, prenom, email, telephone, groupeUtilisateurId) " +
                " VALUES (?,?,?,?,?,?,?,?) ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getUsername());
        statement.setString(2, obj.getPassword());
        statement.setString(3, obj.getNom());
        statement.setString(4, obj.getDeuxiemeNom());
        statement.setString(5, obj.getPrenom());
        statement.setString(6, obj.getEmail());
        statement.setString(7, obj.getTelephone());
        statement.setInt(8, obj.getGroupeUtilisateur().getId());
        statement.executeUpdate();
    }

    @Override
    public void update(Utilisateur obj) throws SQLException {
        String sql = "UPDATE Utilisateurs SET " +
                " username = ?, password = ?, nom = ?, deuxiemeNom = ?, prenom = ?, email = ?," +
                " telephone = ?, groupeUtilisateurId = ? " +
                " WHERE id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getUsername());
        statement.setString(2, obj.getPassword());
        statement.setString(3, obj.getNom());
        statement.setString(4, obj.getDeuxiemeNom());
        statement.setString(5, obj.getPrenom());
        statement.setString(6, obj.getEmail());
        statement.setString(7, obj.getTelephone());
        statement.setInt(8, obj.getGroupeUtilisateur().getId());
        statement.setInt(9, obj.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Utilisateur obj) throws SQLException {
        delete(obj.getId());
    }

    public void delete(String username) throws SQLException {
        String sql = "SELECT * FROM Utilisateurs WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.executeUpdate();
    }

    public Utilisateur getByUsername(String username) throws SQLException {
        String sql = "DELETE FROM Utilisateurs WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
            return getInResultSet(resultSet);
        return null;
    }


    @Override
    protected Utilisateur getInResultSet(ResultSet resultSet) throws SQLException {
        return new Utilisateur(
                resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("nom"),
                resultSet.getString("deuxiemeNom"),
                resultSet.getString("prenom"),
                resultSet.getString("email"),
                resultSet.getString("telephone"),
                new GroupeUtilisateurDAO(connection).getById(resultSet.getInt("groupeUtilisateurId"))
        );
    }


}
