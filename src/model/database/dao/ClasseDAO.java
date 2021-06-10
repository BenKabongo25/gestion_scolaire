package model.database.dao;

import model.objects.classes.Classe;
import model.objects.classes.Section;
import model.objects.organisation.AnneeScolaire;
import model.objects.organisation.Organisation;
import model.objects.personnes.eleves.Eleve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClasseDAO extends DaoID<Classe> {

    public ClasseDAO(Connection connection) {
        super(connection, "Classes");
    }

    @Override
    public void create(Classe obj) throws SQLException {
        String sql = "INSERT INTO Classes " +
                " (nom, numero, label, code, sectionId, organisationId) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setInt(2, obj.getNumero());
        statement.setString(3, obj.getLabel());
        statement.setString(4, obj.getCode());
        statement.setInt(5, obj.getSection().getId());
        statement.setInt(6, obj.getOrganisation().getId());
        statement.executeUpdate();
    }

    @Override
    public void update(Classe obj) throws SQLException {
        String sql = "UPDATE Classes SET " +
                " nom = ?, numero = ?, label = ?, code = ?, sectionId = ?, organisationId = ? " +
                " WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setInt(2, obj.getNumero());
        statement.setString(3, obj.getLabel());
        statement.setString(4, obj.getCode());
        statement.setInt(5, obj.getSection().getId());
        statement.setInt(6, obj.getOrganisation().getId());
        statement.setInt(7, obj.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Classe obj) throws SQLException {
        delete(obj.getId());
    }

    @Override
    protected Classe getInResultSet(ResultSet resultSet) throws SQLException {
        Classe classe = new Classe(
                resultSet.getInt("id"),
                new OrganisationDAO(connection).getById(resultSet.getInt("organisationId")),
                new SectionDAO(connection).getById(resultSet.getInt("sectionId")),
                resultSet.getInt("numéro"),
                resultSet.getString("label"),
                resultSet.getString("nom"),
                resultSet.getString("code")
                );
        addAnneesScolairesEleves(classe);
        return classe;
    }

    private void addAnneesScolairesEleves(Classe classe) throws SQLException {
        String sql = "SELECT * FROM ElevesAnneesScolaires WHERE classeId = ?";
        PreparedStatement statement = connection.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1, classe.getId());
        ResultSet resultSet = statement.executeQuery();
        AnneeScolaireDAO anneeScolaireDAO = new AnneeScolaireDAO(connection);
        EleveDAO eleveDAO = new EleveDAO(connection);
        while (resultSet.next()) {
            AnneeScolaire anneeScolaire = anneeScolaireDAO
                    .getById(resultSet.getInt("anneeScolaireId"));
            Eleve eleve = eleveDAO.getById(resultSet.getInt("eleveId"));
            if (anneeScolaire != null && eleve != null)
                classe.addAnneeScolaireEleve(anneeScolaire, eleve);
        }
    }
}
