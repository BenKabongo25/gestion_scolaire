package model.database.dao;

import model.objects.cours.Cours;
import model.objects.cours.UniteEnseignement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UniteEnseignementDAO extends DaoType1<UniteEnseignement> {

    public UniteEnseignementDAO(Connection connection) {
        super(connection, "UnitesEnseignements");
    }

    @Override
    public void create(UniteEnseignement obj) throws SQLException {
        String sql = "INSERT INTO UnitesEnseignements (nom, code) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.executeUpdate();

    }

    @Override
    public void update(UniteEnseignement obj) throws SQLException {
        String sql = "UPDATE UnitesEnseignements SET nom = ?, code = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.setInt(3, obj.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(UniteEnseignement obj) throws SQLException {
        delete(obj.getId());
    }

    @Override
    protected UniteEnseignement getInResultSet(ResultSet resultSet) throws SQLException {
        UniteEnseignement uniteEnseignement = new UniteEnseignement(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("code")
        );
        addCours(uniteEnseignement);
        return uniteEnseignement;
    }

    private void addCours(UniteEnseignement uniteEnseignement) throws SQLException {
        String sql = "SELECT * FROM CoursUnitesEnseignements WHERE uniteEnseignementId = ?";
        PreparedStatement statement = connection.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1, uniteEnseignement.getId());
        ResultSet resultSet = statement.executeQuery();
        CoursDAO coursDAO = new CoursDAO(connection);
        while (resultSet.next()) {
            Cours cours = coursDAO.getById(resultSet.getInt("coursId"));
            if (cours != null)
                uniteEnseignement.addCours(cours);
        }
    }
}
