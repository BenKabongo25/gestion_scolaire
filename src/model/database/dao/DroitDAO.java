package model.database.dao;

import model.objects.utilisateurs.Droit;
import model.objects.utilisateurs.DroitInfos;
import model.objects.utilisateurs.GroupeUtilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DroitDAO extends DaoType1<Droit> {

    public DroitDAO(Connection connection) {
        super(connection, "Droits");
    }

    @Override
    public void create(Droit obj) throws SQLException {
        String sql = "INSERT INTO Droits " +
                " (nom, code, description) VALUES (?,?,?) ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.setString(3, obj.getDescription());
        statement.executeUpdate();
    }

    @Override
    public void update(Droit obj) throws SQLException {
        String sql = "UPDATE Droits SET " +
                " nom = ?, code = ?, description = ? WHERE id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.setString(3, obj.getDescription());
        statement.setInt(4, obj.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Droit obj) throws SQLException {
        delete(obj.getId());
    }

    @Override
    protected Droit getInResultSet(ResultSet resultSet) throws SQLException {
        Droit droit = new Droit(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("code"),
                resultSet.getString("description")
        );
        addGroupesUtilisateurs(droit);
        return droit;
    }

    private void addGroupesUtilisateurs(Droit droit) throws SQLException {
        String sql = "SELECT * FROM GroupesUtilisateursDroits WHERE droitId = ?";
        PreparedStatement statement = connection.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1, droit.getId());
        ResultSet resultSet = statement.executeQuery();
        GroupeUtilisateurDAO groupeUtilisateurDAO = new GroupeUtilisateurDAO(connection);
        while (resultSet.next()) {
            GroupeUtilisateur groupeUtilisateur = groupeUtilisateurDAO
                    .getById(resultSet.getInt("groupeUtilisateurId"));
            if (groupeUtilisateur != null) {
                DroitInfos droitInfos = new DroitInfos(
                        resultSet.getInt("canCreate") != 0,
                        resultSet.getInt("canRead") != 0,
                        resultSet.getInt("canUpdate") != 0,
                        resultSet.getInt("canDelete") != 0
                );
                droit.addGroupeUtilisateur(groupeUtilisateur, droitInfos);
            }
        }
    }
}
