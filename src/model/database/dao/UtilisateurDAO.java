package model.database.dao;

import model.objects.utilisateurs.Utilisateur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDAO extends DaoID<Utilisateur> {

    public UtilisateurDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Utilisateur obj) {
    }

    @Override
    public void update(Utilisateur obj) {
    }

    @Override
    public void delete(Utilisateur obj) throws SQLException {
        delete(obj.getId());
    }

    public Utilisateur getByUsername(String username) {
        return null;
    }

    @Override
    protected Utilisateur getInResultSet(ResultSet resultSet) {
        return null;
    }
}
