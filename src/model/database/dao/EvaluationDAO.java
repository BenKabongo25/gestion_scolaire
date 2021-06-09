package model.database.dao;

import model.entites.classes.Classe;
import model.entites.cours.Cours;
import model.entites.cours.UniteEnseignement;
import model.entites.evaluations.Evaluation;
import model.entites.evaluations.TypeEvaluation;
import model.entites.organisation.AnneeScolaire;
import model.entites.organisation.Periode;
import model.entites.organisation.Session;
import model.entites.personnes.eleves.Eleve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EvaluationDAO extends DaoType1<Evaluation> {

    public EvaluationDAO(Connection connection) {
        super(connection, "Evaluations");
    }

    @Override
    public boolean create(Evaluation obj) {
        String sql = "INSERT INTO Evaluations " +
                " (nom, code, anneeScolaireId, eleveId, classeId, sessionId, periodeId, " +
                " uniteEnseignementId, coursId, typeEvaluationId, date, note, max) " +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getCode());
            statement.setInt(3, obj.getAnneeScolaire().getId());
            statement.setInt(4, obj.getEleve().getId());
            statement.setInt(5, obj.getClasse().getId());
            statement.setInt(6, obj.getSession().getId());
            statement.setInt(7, obj.getPeriode().getId());
            statement.setInt(8, obj.getUniteEnseignement().getId());
            statement.setInt(9, obj.getCours().getId());
            statement.setInt(10, obj.getTypeEvaluation().getId());
            statement.setDate(11, obj.getDate());
            statement.setFloat(12, obj.getNote());
            statement.setFloat(13, obj.getMax());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Evaluation obj) {
        String sql = "UPDATE Evaluations SET " +
                " nom = ?, code = ?, anneeScolaireId = ?, eleveId = ?, classeId = ?, sessionId = ?," +
                " periodeId = ?, uniteEnseignementId = ?, coursId = ?, typeEvaluationId = ?, date = ?," +
                " note = ?, max = ? WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getCode());
            statement.setInt(3, obj.getAnneeScolaire().getId());
            statement.setInt(4, obj.getEleve().getId());
            statement.setInt(5, obj.getClasse().getId());
            statement.setInt(6, obj.getSession().getId());
            statement.setInt(7, obj.getPeriode().getId());
            statement.setInt(8, obj.getUniteEnseignement().getId());
            statement.setInt(9, obj.getCours().getId());
            statement.setInt(10, obj.getTypeEvaluation().getId());
            statement.setDate(11, obj.getDate());
            statement.setFloat(12, obj.getNote());
            statement.setFloat(13, obj.getMax());
            statement.setInt(14, obj.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Evaluation obj) {
        return delete(obj.getId());
    }

    @Override
    protected Evaluation getInResultSet(ResultSet resultSet) {
        Evaluation evaluation;

        AnneeScolaire anneeScolaire = new AnneeScolaire();
        Eleve eleve = new Eleve();
        Classe classe = new Classe();
        Session session = new Session();
        Periode periode = new Periode();
        UniteEnseignement uniteEnseignement = new UniteEnseignement();
        Cours cours = new Cours();
        TypeEvaluation typeEvaluation = new TypeEvaluation();

        try {
            anneeScolaire = new AnneeScolaireDAO(connection)
                    .getById(resultSet.getInt("anneeScolaireId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            eleve = new EleveDAO(connection)
                    .getById( resultSet.getInt("eleveId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            classe = new ClasseDAO(connection)
                    .getById(resultSet.getInt("classeId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            session = new SessionDAO(connection)
                    .getById(resultSet.getInt("sessionId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            periode = new PeriodeDAO(connection)
                    .getById(resultSet.getInt("periodeId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            uniteEnseignement = new UniteEnseignementDAO(connection)
                    .getById(resultSet.getInt("uniteEnseignementId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            cours = new CoursDAO(connection)
                    .getById(resultSet.getInt("coursId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            typeEvaluation = new TypeEvaluationDAO(connection)
                    .getById(resultSet.getInt("typeEvaluationId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            evaluation = new Evaluation(
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("code"),
                    anneeScolaire,
                    eleve,
                    classe,
                    session,
                    periode,
                    uniteEnseignement,
                    cours,
                    typeEvaluation,
                    resultSet.getDate("date"),
                    resultSet.getFloat("note"),
                    resultSet.getInt("max")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return evaluation;
    }
}
