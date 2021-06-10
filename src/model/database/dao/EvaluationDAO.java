package model.database.dao;

import model.objects.classes.Classe;
import model.objects.cours.Cours;
import model.objects.cours.UniteEnseignement;
import model.objects.evaluations.Evaluation;
import model.objects.evaluations.TypeEvaluation;
import model.objects.organisation.AnneeScolaire;
import model.objects.organisation.Periode;
import model.objects.organisation.Session;
import model.objects.personnes.eleves.Eleve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EvaluationDAO extends DaoType1<Evaluation> {

    public EvaluationDAO(Connection connection) {
        super(connection, "Evaluations");
    }

    @Override
    public void create(Evaluation obj) throws SQLException {
        String sql = "INSERT INTO Evaluations " +
                " (nom, code, anneeScolaireId, eleveId, classeId, sessionId, periodeId, " +
                " uniteEnseignementId, coursId, typeEvaluationId, date, note, max) " +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ";
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
    }

    @Override
    public void update(Evaluation obj) throws SQLException {
        String sql = "UPDATE Evaluations SET " +
                " nom = ?, code = ?, anneeScolaireId = ?, eleveId = ?, classeId = ?, sessionId = ?," +
                " periodeId = ?, uniteEnseignementId = ?, coursId = ?, typeEvaluationId = ?, date = ?," +
                " note = ?, max = ? WHERE id = ? ";
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
    }

    @Override
    public void delete(Evaluation obj) throws SQLException {
        delete(obj.getId());
    }

    @Override
    protected Evaluation getInResultSet(ResultSet resultSet) throws SQLException {
        AnneeScolaire anneeScolaire = new AnneeScolaireDAO(connection)
                .getById(resultSet.getInt("anneeScolaireId"));
        Eleve eleve = new EleveDAO(connection)
                .getById( resultSet.getInt("eleveId"));
        Classe classe = new ClasseDAO(connection)
                .getById(resultSet.getInt("classeId"));
        Session session = new SessionDAO(connection)
                .getById(resultSet.getInt("sessionId"));
        Periode periode = new PeriodeDAO(connection)
                .getById(resultSet.getInt("periodeId"));
        UniteEnseignement uniteEnseignement = new UniteEnseignementDAO(connection)
                .getById(resultSet.getInt("uniteEnseignementId"));
        Cours cours = new CoursDAO(connection)
                .getById(resultSet.getInt("coursId"));
        TypeEvaluation typeEvaluation = new TypeEvaluationDAO(connection)
                .getById(resultSet.getInt("typeEvaluationId"));

        Evaluation evaluation = new Evaluation(
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
        return evaluation;
    }
}
