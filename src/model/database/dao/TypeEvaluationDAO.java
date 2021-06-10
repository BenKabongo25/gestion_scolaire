package model.database.dao;

import model.objects.evaluations.TypeEvaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeEvaluationDAO extends DaoType1<TypeEvaluation> {

    public TypeEvaluationDAO(Connection connection) {
        super(connection, "TypesEvaluations");
    }

    @Override
    public void create(TypeEvaluation obj) throws SQLException {
        String sql = "INSERT INTO TypesEvaluations (nom, code) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.executeUpdate();
    }

    @Override
    public void update(TypeEvaluation obj) throws SQLException {
        String sql = "UPDATE TypesEvaluations SET nom = ?, code = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, obj.getNom());
        statement.setString(2, obj.getCode());
        statement.setInt(3, obj.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(TypeEvaluation obj) throws SQLException {
        delete(obj.getId());
    }

    @Override
    protected TypeEvaluation getInResultSet(ResultSet resultSet) throws SQLException {
        return new TypeEvaluation(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("code")
        );
    }
}
