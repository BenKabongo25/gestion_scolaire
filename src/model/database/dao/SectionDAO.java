package model.database.dao;

import model.entites.classes.Classe;
import model.entites.classes.Niveau;
import model.entites.classes.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionDAO extends DaoType1<Section> {

    public SectionDAO(Connection connection) {
        super(connection, "Sections");
    }

    @Override
    public boolean create(Section obj) {
        String sql = "INSERT INTO Sections (nom, code, niveauId) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getCode());
            statement.setInt(3, obj.getNiveau().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Section obj) {
        String sql = "UPDATE Sections SET nom = ?, code = ?, niveauId = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getCode());
            statement.setInt(3, obj.getNiveau().getId());
            statement.setInt(4, obj.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Section obj) {
        return delete(obj.getId());
    }

    @Override
    protected Section getInResultSet(ResultSet resultSet) {
        Section section;
        Niveau niveau = new Niveau();
        try {
            niveau = new NiveauDAO(connection).getById(resultSet.getInt("niveauId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            section = new Section(
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("code"),
                    niveau
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        addClasses(section);
        return section;
    }

    private void addClasses(Section section) {
        try {
            String sql = "SELECT * FROM Classes WHERE sectionId = ?";
            PreparedStatement statement = connection.prepareStatement(sql,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, section.getId());
            ResultSet resultSet = statement.executeQuery();
            ClasseDAO classeDAO = new ClasseDAO(connection);
            while (resultSet.next()) {
                Classe classe = classeDAO.getById(resultSet.getInt("id"));
                if (classe != null)
                    section.addClasse(classe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
