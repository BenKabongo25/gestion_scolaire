package model.database.dao;

import model.entites.organisation.AnneeScolaire;

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
    public boolean create(AnneeScolaire obj) {
        String sql = "INSERT INTO AnneeScolaire (debut, fin) VALUES (?,?) ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, obj.getDebut());
            statement.setDate(2, obj.getFin());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(AnneeScolaire obj) {
        String sql = "UPDATE AnneeScolaire SET " +
                " debut = ?, fin = ? WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, obj.getDebut());
            statement.setDate(2, obj.getFin());
            statement.setInt(3, obj.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(AnneeScolaire obj) {
        return delete(obj.getId());
    }

    @Override
    protected AnneeScolaire getInResultSet(ResultSet resultSet) {
        try {
            return new AnneeScolaire(
                    resultSet.getInt("id"),
                    resultSet.getDate("debut"),
                    resultSet.getDate("fin")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<AnneeScolaire> getByDate(String columnname, Date date) {
        List<AnneeScolaire> anneeScolaires = new ArrayList<>();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anneeScolaires;
    }

    public List<AnneeScolaire> getByDebutDate(Date date) {
        return getByDate("debut", date);
    }

    public List<AnneeScolaire> getByFinDate(Date date) {
        return getByDate("fin", date);
    }
}
