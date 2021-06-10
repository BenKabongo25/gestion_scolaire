package model.database.dao;

import model.objects.classes.Niveau;
import model.objects.classes.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NiveauDAO extends DaoType1<Niveau> {

    public NiveauDAO(Connection connection) {
        super(connection, "Niveaux");
    }

    @Override
    public void create(Niveau obj) throws SQLException {
        String sql = "INSERT INTO Niveaux (nom, code) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.executeUpdate();
    }

    @Override
    public void update(Niveau obj) throws SQLException {
        String sql = "UPDATE Niveaux SET nom = ?, code = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.setInt(3, obj.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Niveau obj) throws SQLException {
        delete(obj.getId());
    }


    @Override
    protected Niveau getInResultSet(ResultSet resultSet) throws SQLException {
        Niveau niveau = new Niveau(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("code")
        );
        addSections(niveau);
        return niveau;
    }

    private void addSections(Niveau niveau) throws SQLException {
        String sql = "SELECT * FROM Sections WHERE niveauId = ?";
        PreparedStatement statement = connection.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1, niveau.getId());
        ResultSet resultSet = statement.executeQuery();
        SectionDAO sectionDAO = new SectionDAO(connection);
        while (resultSet.next()) {
            Section section = sectionDAO.getById(resultSet.getInt("id"));
            if (section != null)
                niveau.addSection(section);
        }
    }
}
