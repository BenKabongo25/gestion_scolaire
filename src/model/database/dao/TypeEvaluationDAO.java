package model.database.dao;

import model.entites.evaluations.TypeEvaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeEvaluationDAO extends DaoType1<TypeEvaluation> {

    public TypeEvaluationDAO(Connection connection) {
        super(connection, "TypesEvaluations");
    }

    @Override
    public boolean create(TypeEvaluation obj) {
        String sql = "INSERT INTO TypesEvaluations (nom, code) VALUES (?, ?)";
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
    public boolean update(TypeEvaluation obj) {
        String sql = "UPDATE TypesEvaluations SET nom = ?, code = ? WHERE id = ?";
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
    public boolean delete(TypeEvaluation obj) {
        return delete(obj.getId());
    }

    @Override
    protected TypeEvaluation getInResultSet(ResultSet resultSet) {
        try {
            return new TypeEvaluation(
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("code")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
