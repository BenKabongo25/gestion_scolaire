package model.database.dao;

import model.objects.classes.Classe;
import model.objects.classes.Niveau;
import model.objects.classes.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionDAO extends DaoType1<Section> {

    public SectionDAO(Connection connection) {
        super(connection, "Sections");
    }

    @Override
    public void create(Section obj) throws SQLException {
        String sql = "INSERT INTO Sections (nom, code, niveauId) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.setInt(3, obj.getNiveau().getId());
        statement.executeUpdate();
    }

    @Override
    public void update(Section obj) throws SQLException {
        String sql = "UPDATE Sections SET nom = ?, code = ?, niveauId = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.setInt(3, obj.getNiveau().getId());
        statement.setInt(4, obj.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Section obj) throws SQLException {
        delete(obj.getId());
    }

    @Override
    protected Section getInResultSet(ResultSet resultSet) throws SQLException {
        Section section = new Section(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("code"),
                new NiveauDAO(connection).getById(resultSet.getInt("niveauId"))
        );
        addClasses(section);
        return section;
    }

    private void addClasses(Section section) throws SQLException {
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
    }
}
