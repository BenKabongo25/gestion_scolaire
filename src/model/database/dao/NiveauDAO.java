package model.database.dao;

import model.entites.classes.Classe;
import model.entites.classes.Niveau;
import model.entites.classes.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NiveauDAO extends DaoType1<Niveau> {

    public NiveauDAO(Connection connection) {
        super(connection, "Niveaux");
    }

    @Override
    public boolean create(Niveau obj) {
        String sql = "INSERT INTO Niveaux (nom, code) VALUES (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getCode());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Niveau obj) {
        String sql = "UPDATE Niveaux SET nom = ?, code = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getCode());
            statement.setInt(3, obj.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Niveau obj) {
        return delete(obj.getId());
    }


    @Override
    protected Niveau getInResultSet(ResultSet resultSet) {
        Niveau niveau;
        try {
            niveau = new Niveau(
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("code")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        addSections(niveau);
        return niveau;
    }

    private void addSections(Niveau niveau) {
        try {
            String sql = "SELECT * FROM Sections WHERE niveauId = ?";
            PreparedStatement statement = connection.prepareStatement(sql,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, niveau.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SectionDAO sectionDAO = new SectionDAO(connection);
                Section section = sectionDAO.getById(resultSet.getInt("id"));
                if (section != null)
                    niveau.addSection(section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
