package model.database.dao;

import model.objects.organisation.AnneeScolaire;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnneeScolaireDAO extends DaoID<AnneeScolaire> {

    public AnneeScolaireDAO(Connection connection) {
        super(connection, "AnneesScolaires");
    }

    @Override
    public void create(AnneeScolaire obj) throws SQLException {
        String sql = "INSERT INTO AnneeScolaire (debut, fin) VALUES (?,?) ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, obj.getDebut());
        statement.setDate(2, obj.getFin());
        statement.executeUpdate();
    }

    @Override
    public void update(AnneeScolaire obj) throws SQLException {
        String sql = "UPDATE AnneeScolaire SET " +
                " debut = ?, fin = ? WHERE id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, obj.getDebut());
        statement.setDate(2, obj.getFin());
        statement.setInt(3, obj.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(AnneeScolaire obj) throws SQLException {
        delete(obj.getId());
    }

    @Override
    protected AnneeScolaire getInResultSet(ResultSet resultSet) throws SQLException {
        return new AnneeScolaire(
                resultSet.getInt("id"),
                resultSet.getDate("debut"),
                resultSet.getDate("fin")
        );
    }

    private List<AnneeScolaire> getByDate(String columnname, Date date) throws SQLException {
        List<AnneeScolaire> anneeScolaires = new ArrayList<>();
        String sql = "SELECT * FROM AnneesScolaires WHERE " + columnname + " = ?";
        PreparedStatement statement = connection.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        statement.setDate(1, date);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            AnneeScolaire anneeScolaire = getInResultSet(resultSet);
            if (anneeScolaire != null)
                anneeScolaires.add(anneeScolaire);
        }
        return anneeScolaires;
    }

    public List<AnneeScolaire> getByDebutDate(Date date) throws SQLException {
        return getByDate("debut", date);
    }

    public List<AnneeScolaire> getByFinDate(Date date) throws SQLException {
        return getByDate("fin", date);
    }
}
