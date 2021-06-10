package model.objects.utilisateurs;

import model.objects.utils.Identifable;

import java.util.Map;
import java.util.Set;

public class Utilisateur extends Identifable {

    protected String username = "";
    protected String password = "";
    protected String email = "";
    protected String telephone = "";
    protected GroupeUtilisateur groupeUtilisateur = new GroupeUtilisateur();

    public Utilisateur() {}

    public Utilisateur(int id,
                       String username,
                       String password,
                       String email,
                       String telephone,
                       GroupeUtilisateur groupeUtilisateur) {
        super(id);
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.groupeUtilisateur = groupeUtilisateur;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public GroupeUtilisateur getGroupeUtilisateur() {
        return groupeUtilisateur;
    }

    public void setGroupeUtilisateur(GroupeUtilisateur groupeUtilisateur) {
        this.groupeUtilisateur = groupeUtilisateur;
    }

    public Map<Droit, DroitInfos> getDroits() {
        return groupeUtilisateur.getDroits();
    }
}
