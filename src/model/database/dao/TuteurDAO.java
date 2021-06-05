package model.database.dao;

import model.entites.personnes.eleves.Tuteur;

import java.sql.Connection;
import java.sql.ResultSet;

public class TuteurDAO extends DAO<Tuteur> {

    public TuteurDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(Tuteur obj) {
        return false;
    }

    @Override
    public boolean update(Tuteur obj) {
        return false;
    }

    @Override
    public boolean delete(Tuteur obj) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Tuteur getById(int id) {
        return null;
    }

    @Override
    protected Tuteur getInResultSet(ResultSet resultSet) {
        return null;
    }
}
