package model.database.dao;

import model.entites.personnes.personnel.Professeur;

import java.sql.Connection;
import java.sql.ResultSet;

public class ProfesseurDAO extends DAO<Professeur> {

    public ProfesseurDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(Professeur obj) {
        return false;
    }

    @Override
    public boolean update(Professeur obj) {
        return false;
    }

    @Override
    public boolean delete(Professeur obj) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Professeur getById(int id) {
        return null;
    }

    @Override
    protected Professeur getInResultSet(ResultSet resultSet) {
        return null;
    }
}
