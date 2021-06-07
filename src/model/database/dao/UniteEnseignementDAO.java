package model.database.dao;

import model.entites.cours.Cours;
import model.entites.cours.UniteEnseignement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UniteEnseignementDAO extends DaoType1<UniteEnseignement> {

    public UniteEnseignementDAO(Connection connection) {
        super(connection, "UnitesEnseignements");
    }

    @Override
    public boolean create(UniteEnseignement obj) {
        String sql = "INSERT INTO UnitesEnseignements (nom, code) VALUES (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
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
    public boolean update(UniteEnseignement obj) {
        String sql = "UPDATE UnitesEnseignements SET nom = ?, code = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
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
    public boolean delete(UniteEnseignement obj) {
        return delete(obj.getId());
    }

    @Override
    protected UniteEnseignement getInResultSet(ResultSet resultSet) {
        UniteEnseignement uniteEnseignement;
        try {
            uniteEnseignement = new UniteEnseignement(
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("code")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        addCours(uniteEnseignement);
        return uniteEnseignement;
    }

    private void addCours(UniteEnseignement uniteEnseignement) {
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
