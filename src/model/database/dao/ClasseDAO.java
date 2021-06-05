package model.database.dao;

import model.entites.classes.Classe;
import model.entites.classes.Section;
import model.entites.organisation.Organisation;
import model.entites.personnes.eleves.Eleve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClasseDAO extends DaoID<Classe> {

    public ClasseDAO(Connection connection) {
        super(connection, "Classes");
    }

    @Override
    public boolean create(Classe obj) {
        String sql = "INSERT INTO Classes " +
                " (nom, numero, label, code, sectionId, organisationId) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obj.getNom());
            statement.setInt(2, obj.getNumero());
            statement.setString(3, obj.getLabel());
            statement.setString(4, obj.getCode());
            statement.setInt(5, obj.getSection().getId());
            statement.setInt(6, obj.getOrganisation().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Classe obj) {
        String sql = "UPDATE Classes SET " +
                " nom = ?, numero = ?, label = ?, code = ?, sectionId = ?, organisationId = ? " +
                " WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obj.getNom());
            statement.setInt(2, obj.getNumero());
            statement.setString(3, obj.getLabel());
            statement.setString(4, obj.getCode());
            statement.setInt(5, obj.getSection().getId());
            statement.setInt(6, obj.getOrganisation().getId());
            statement.setInt(7, obj.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Classe obj) {
        return delete(obj.getId());
    }

    @Override
    protected Classe getInResultSet(ResultSet resultSet) {
        Classe classe;
        Organisation organisation = new Organisation();
        Section section = new Section();
        try {
            organisation = new OrganisationDAO(connection).getById(resultSet.getInt("organisationId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            section = new SectionDAO(connection).getById(resultSet.getInt("sectionId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            classe = new Classe(
                    resultSet.getInt("id"),
                    organisation,
                    section,
                    resultSet.getInt("numéro"),
                    resultSet.getString("label"),
                    resultSet.getString("nom"),
                    resultSet.getString("code")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return classe;
    }

}
