package model.database.dao;

import model.entites.classes.Classe;
import model.entites.organisation.AnneeScolaire;
import model.entites.personnes.base.Adresse;
import model.entites.personnes.base.Sexe;
import model.entites.personnes.eleves.Eleve;

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
    public boolean create(Eleve obj) {
        String sql = "INSERT INTO Eleves " +
                " (code, nom, deuxiemeNom, prenom, sexe, dateNaissance, paysNaissance, villeNaissance, " +
                "telephone, email, adresseId) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Eleve obj) {
        String sql = "UPDATE Eleves SET " +
                "code = ?, nom = ?, deuxiemeNom = ?, prenom = ?, sexe = ?, dateNaissance = ?, paysNaissance = ?, " +
                "villeNaissance = ?, telephone = ?, email = ?, adresseId = ? " +
                " WHERE id = ? ";
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Eleve obj) {
        return delete(obj.getId());
    }

    public List<Eleve> getByNom(String nom) {
        String sql = "SELECT * FROM Eleves WHERE LOWER(nom) LIKE '" + nom.toLowerCase() + "%'";
        return getBySqlRequest(sql);
    }

    public List<Eleve> getByPrenom(String prenom) {
        String sql = "SELECT * FROM Eleves WHERE LOWER(prenom) LIKE '" + prenom.toLowerCase() + "%'";
        return getBySqlRequest(sql);
    }

    public List<Eleve> getByNomPrenom(String nom, String prenom) {
        String sql = "SELECT * FROM Eleves WHERE LOWER(nom) LIKE '" + nom.toLowerCase() +
                "%' AND LOWER(prenom) LIKE '" + prenom.toLowerCase() + "%'";
        return getBySqlRequest(sql);
    }

    public List<Eleve> getByNoms(String nom, String deuxiemeNom, String prenom) {
        String sql = "SELECT * FROM Eleves WHERE LOWER(nom) LIKE '" + nom.toLowerCase() +
                "%' AND LOWER(deuxiemeNom) LIKE '" + deuxiemeNom.toLowerCase() +
                "%' AND LOWER(prenom) LIKE '" + prenom.toLowerCase() + "%'";
        return getBySqlRequest(sql);
    }

    @Override
    protected Eleve getInResultSet(ResultSet resultSet) {
        Eleve eleve;
        Adresse adresse = new Adresse();
        try {
            adresse = new AdresseDAO(connection).getById(resultSet.getInt("adresseId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            eleve = new Eleve(
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
                    adresse
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        addAnneesScolairesClasses(eleve);
        return eleve;
    }

    private void addAnneesScolairesClasses(Eleve eleve) {
        try {
            String sql = "SELECT * FROM ElevesAnneesScolaires WHERE eleveId = ?";
            PreparedStatement statement = connection.prepareStatement(sql,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, eleve.getId());
            ResultSet resultSet = statement.executeQuery();
            AnneeScolaireDAO anneeScolaireDAO = new AnneeScolaireDAO(connection);
            ClasseDAO classeDAO = new ClasseDAO(connection);
            while (resultSet.next()) {
                AnneeScolaire anneeScolaire = anneeScolaireDAO.getById(resultSet.getInt("anneeScolaireId"));
                Classe classe = classeDAO.getById(resultSet.getInt("classeId"));
                if (anneeScolaire != null && classe != null)
                    eleve.addAnneeScolaireClasse(anneeScolaire, classe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
