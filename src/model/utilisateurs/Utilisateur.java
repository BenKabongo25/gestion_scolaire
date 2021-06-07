package model.utilisateurs;

import model.entites.utils.Identifable;

public class Utilisateur extends Identifable {

    public enum Type {
        ADMINISTRATEUR,
        INVITE;
    }

    protected String username;
    protected String password;
    protected Type type;

    public Utilisateur(Integer id, String username, String password, Type type) {
        super(id);
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
