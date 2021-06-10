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

public class EleveDAO extends DaoID<Eleve> {

    public EleveDAO(Connection connection) {
        super(connection, "Eleves");
    }

    @Override
    public void create(Eleve obj) throws SQLException {
        String sql = "INSERT INTO Eleves " +
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
    public void update(Eleve obj) throws SQLException {
        String sql = "UPDATE Eleves SET " +
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
    public void delete(Eleve obj) throws SQLException {
        delete(obj.getId());
    }

    public List<Eleve> getByNom(String nom) throws SQLException {
        String sql = "SELECT * FROM Eleves WHERE LOWER(nom) LIKE '" + nom.toLowerCase() + "%'";
        return getBySqlRequest(sql);
    }

    public List<Eleve> getByPrenom(String prenom) throws SQLException {
        String sql = "SELECT * FROM Eleves WHERE LOWER(prenom) LIKE '" + prenom.toLowerCase() + "%'";
        return getBySqlRequest(sql);
    }

    public List<Eleve> getByNomPrenom(String nom, String prenom) throws SQLException {
        String sql = "SELECT * FROM Eleves WHERE LOWER(nom) LIKE '" + nom.toLowerCase() +
                "%' AND LOWER(prenom) LIKE '" + prenom.toLowerCase() + "%'";
        return getBySqlRequest(sql);
    }

    public List<Eleve> getByNoms(String nom, String deuxiemeNom, String prenom) throws SQLException {
        String sql = "SELECT * FROM Eleves WHERE LOWER(nom) LIKE '" + nom.toLowerCase() +
                "%' AND LOWER(deuxiemeNom) LIKE '" + deuxiemeNom.toLowerCase() +
                "%' AND LOWER(prenom) LIKE '" + prenom.toLowerCase() + "%'";
        return getBySqlRequest(sql);
    }

    public List<Eleve> getByCode(String code) throws SQLException {
        String sql = "SELECT * FROM Eleves WHERE LOWER(code) LIKE '" + code.toLowerCase() +"%'";
        return getBySqlRequest(sql);
    }

    @Override
    protected Eleve getInResultSet(ResultSet resultSet) throws SQLException {
        Eleve eleve = new Eleve(
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
        addAnneesScolairesInfos(eleve);
        addTuteurs(eleve);
        return eleve;
    }

    private void addAnneesScolairesInfos(Eleve eleve) throws SQLException {

    }

    private void addTuteurs(Eleve eleve) throws SQLException {
        String sql = "SELECT * FROM TuteursEleves WHERE eleveId = ?";
        PreparedStatement statement = connection.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1, eleve.getId());
        ResultSet resultSet = statement.executeQuery();
        TuteurDAO tuteurDAO = new TuteurDAO(connection);
        while (resultSet.next()) {
            Tuteur tuteur = tuteurDAO.getById(resultSet.getInt("tuteurId"));
            if (tuteur != null)
                eleve.addTuteur(tuteur);
        }
    }
}
