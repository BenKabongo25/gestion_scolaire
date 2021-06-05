package model.database.dao;

import model.utilisateurs.Utilisateur;

import java.sql.Connection;
import java.sql.ResultSet;

public class UtilisateurDAO extends DAO<Utilisateur> {

    public UtilisateurDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(Utilisateur obj) {
        return false;
    }

    @Override
    public boolean update(Utilisateur obj) {
        return false;
    }

    @Override
    public boolean delete(Utilisateur obj) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Utilisateur getById(int id) {
        return null;
    }

    public Utilisateur getByUsername(String username) {
        return null;
    }

    @Override
    protected Utilisateur getInResultSet(ResultSet resultSet) {
        return null;
    }
}
