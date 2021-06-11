package model.database.dao;

import model.objects.utilisateurs.Droit;
import model.objects.utilisateurs.DroitInfos;
import model.objects.utilisateurs.GroupeUtilisateur;
import model.objects.utilisateurs.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupeUtilisateurDAO extends DaoType1<GroupeUtilisateur> {

    public GroupeUtilisateurDAO(Connection connection) {
        super(connection, "GroupesUtilisateurs");
    }

    @Override
    public void create(GroupeUtilisateur obj) throws SQLException {
        String sql = "INSERT INTO GroupesUtilisateurs " +
                " (nom, code, description) VALUES (?,?,?) ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.setString(3, obj.getDescription());
        statement.executeUpdate();
    }

    @Override
    public void update(GroupeUtilisateur obj) throws SQLException {
        String sql = "UPDATE GroupesUtilisateurs SET " +
                " nom = ?, code = ?, description = ? WHERE id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.setString(3, obj.getDescription());
        statement.setInt(4, obj.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(GroupeUtilisateur obj) throws SQLException {
        delete(obj.getId());
    }

    @Override
    protected GroupeUtilisateur getInResultSet(ResultSet resultSet) throws SQLException {
        GroupeUtilisateur groupeUtilisateur = new GroupeUtilisateur(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("code"),
                resultSet.getString("description")
        );
        addUtilisateurs(groupeUtilisateur);
        addDroits(groupeUtilisateur);
        return groupeUtilisateur;
    }

    private void addUtilisateurs(GroupeUtilisateur groupeUtilisateur) throws SQLException {
        String sql = "SELECT * FROM UtilisateursGroupesUtilisateurs WHERE groupeUtilisateurId = ? ";
        PreparedStatement statement = connection.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1, groupeUtilisateur.getId());
        ResultSet resultSet = statement.executeQuery();
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connection);
        while (resultSet.next()) {
            Utilisateur utilisateur = utilisateurDAO.getById(resultSet.getInt("utilisateurId"));
            if (utilisateur != null)
                groupeUtilisateur.addUtilisateur(utilisateur);
        }
    }

    private void addDroits(GroupeUtilisateur groupeUtilisateur) throws SQLException {
        String sql = "SELECT * FROM GroupesUtilisateursDroits WHERE groupeUtilisateurId = ?";
        PreparedStatement statement = connection.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1, groupeUtilisateur.getId());
        ResultSet resultSet = statement.executeQuery();
        DroitDAO droitDAO = new DroitDAO(connection);
        while (resultSet.next()) {
            Droit droit = droitDAO.getById(resultSet.getInt("droitId"));
            if (droit != null) {
                DroitInfos droitInfos = new DroitInfos(
                        resultSet.getInt("canCreate") != 0,
                        resultSet.getInt("canRead") != 0,
                        resultSet.getInt("canUpdate") != 0,
                        resultSet.getInt("canDelete") != 0
                );
                groupeUtilisateur.addDroit(droit, droitInfos);
            }
        }
    }
}
