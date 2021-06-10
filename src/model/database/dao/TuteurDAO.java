package model.database.dao;

import model.objects.personnes.base.Adresse;
import model.objects.personnes.base.Sexe;
import model.objects.personnes.eleves.Eleve;
import model.objects.personnes.eleves.Tuteur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TuteurDAO extends DaoID<Tuteur> {

    public TuteurDAO(Connection connection) {
        super(connection, "Tuteurs");
    }

    @Override
    public void create(Tuteur obj) throws SQLException {
        String sql = "INSERT INTO Tuteurs " +
                " (code, nom, deuxiemeNom, prenom, sexe, dateNaissance, paysNaissance, villeNaissance, " +
                "telephone, email, adresseId) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getCode());
        statement.setString(2, obj.getNom());
        statement.setString(3, obj.getDeuxiemeNom());
        statement.setString(4, obj.getPrenom());
        statement.setInt(5, obj.getSexe().getValue());
        statement.setDate(6, obj.getDateNaissance());
        statement.setString(7, obj.getPaysNaissance());
        statement.setString(8, obj.getVilleNaissance());
        statement.setString(9, obj.getTelephone());
        statement.setString(10, obj.getEmail());
        statement.setInt(11, obj.getAdresse().getId());
        statement.executeUpdate();
    }

    @Override
    public void update(Tuteur obj) throws SQLException {
        String sql = "UPDATE Tuteurs SET " +
                "code = ?, nom = ?, deuxiemeNom = ?, prenom = ?, sexe = ?, dateNaissance = ?, paysNaissance = ?, " +
                "villeNaissance = ?, telephone = ?, email = ?, adresseId = ? " +
                " WHERE id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getCode());
        statement.setString(2, obj.getNom());
        statement.setString(3, obj.getDeuxiemeNom());
        statement.setString(4, obj.getPrenom());
        statement.setInt(5, obj.getSexe().getValue());
        statement.setDate(6, obj.getDateNaissance());
        statement.setString(7, obj.getPaysNaissance());
        statement.setString(8, obj.getVilleNaissance());
        statement.setString(9, obj.getTelephone());
        statement.setString(10, obj.getEmail());
        statement.setInt(11, obj.getAdresse().getId());
        statement.setInt(12, obj.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Tuteur obj) throws SQLException {
        delete(obj.getId());
    }

    @Override
    protected Tuteur getInResultSet(ResultSet resultSet) throws SQLException {
        Tuteur tuteur = new Tuteur(
                resultSet.getInt("id"),
                resultSet.getString("code"),
                resultSet.getString("nom"),
                resultSet.getString("deuxiemeNom"),
                resultSet.getString("prenom"),
                (resultSet.getInt("sexe") == Sexe.HOMME.getValue()) ? Sexe.HOMME : Sexe.FEMME,
                resultSet.getDate("dateNaissance"),
                resultSet.getString("paysNaissance"),
                resultSet.getString("villeNaissance"),
                resultSet.getString("telephone"),
                resultSet.getString("email"),
                new AdresseDAO(connection).getById(resultSet.getInt("adresseId"))
        );
        addEleves(tuteur);
        return tuteur;
    }

    public List<Tuteur> getByNom(String nom) throws SQLException {
        String sql = "SELECT * FROM Tuteurs WHERE LOWER(nom) LIKE '" + nom.toLowerCase() + "%'";
        return getBySqlRequest(sql);
    }

    public List<Tuteur> getByPrenom(String prenom) throws SQLException {
        String sql = "SELECT * FROM Tuteurs WHERE LOWER(prenom) LIKE '" + prenom.toLowerCase() + "%'";
        return getBySqlRequest(sql);
    }

    public List<Tuteur> getByNomPrenom(String nom, String prenom) throws SQLException {
        String sql = "SELECT * FROM Tuteurs WHERE LOWER(nom) LIKE '" + nom.toLowerCase() +
                "%' AND LOWER(prenom) LIKE '" + prenom.toLowerCase() + "%'";
        return getBySqlRequest(sql);
    }

    public List<Tuteur> getByNoms(String nom, String deuxiemeNom, String prenom) throws SQLException {
        String sql = "SELECT * FROM Tuteurs WHERE LOWER(nom) LIKE '" + nom.toLowerCase() +
                "%' AND LOWER(deuxiemeNom) LIKE '" + deuxiemeNom.toLowerCase() +
                "%' AND LOWER(prenom) LIKE '" + prenom.toLowerCase() + "%'";
        return getBySqlRequest(sql);
    }

    public List<Tuteur> getByCode(String code) throws SQLException {
        String sql = "SELECT * FROM Tuteurs WHERE LOWER(code) LIKE '" + code.toLowerCase() +"%'";
        return getBySqlRequest(sql);
    }

    private void addEleves(Tuteur tuteur) throws SQLException {
        String sql = "SELECT * FROM TuteursEleves WHERE tuteurId = ?";
        PreparedStatement statement = connection.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1, tuteur.getId());
        ResultSet resultSet = statement.executeQuery();
        EleveDAO eleveDAO = new EleveDAO(connection);
        while (resultSet.next()) {
            Eleve eleve = eleveDAO.getById(resultSet.getInt("eleveId"));
            if (eleve != null)
                tuteur.addEleve(eleve);
        }
    }
}
