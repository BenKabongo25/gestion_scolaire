package model.database.dao;

import model.objects.personnes.personnel.Professeur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfesseurDAO extends DaoID<Professeur> {

    public ProfesseurDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Professeur obj) throws SQLException {
    }

    @Override
    public void update(Professeur obj) throws SQLException {
    }

    @Override
    public void delete(Professeur obj) throws SQLException{
    }

    @Override
    public void delete(int id) throws SQLException {
    }

    @Override
    public Professeur getById(int id) throws SQLException {
        return null;
    }

    @Override
    protected Professeur getInResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
