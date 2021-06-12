package model.objects.utilisateurs;

import model.objects.base.Identifable;

import java.util.Map;
import java.util.Objects;

public class Utilisateur extends Identifable {

    protected String username = "";
    protected String password = "";
    protected String nom = "";
    protected String deuxiemeNom = "";
    protected String prenom = "";
    protected String email = "";
    protected String telephone = "";
    protected GroupeUtilisateur groupeUtilisateur = new GroupeUtilisateur();

    public Utilisateur() {}

    public Utilisateur(int id,
                       String username,
                       String password,
                       String nom,
                       String deuxiemeNom,
                       String prenom,
                       String email,
                       String telephone,
                       GroupeUtilisateur groupeUtilisateur) {
        super(id);
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.deuxiemeNom = deuxiemeNom;
        this.prenom = prenom;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDeuxiemeNom() {
        return deuxiemeNom;
    }

    public void setDeuxiemeNom(String deuxiemeNom) {
        this.deuxiemeNom = deuxiemeNom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilisateur)) return false;
        Utilisateur that = (Utilisateur) o;
        return  Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(deuxiemeNom, that.deuxiemeNom) &&
                Objects.equals(prenom, that.prenom) &&
                Objects.equals(email, that.email) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(groupeUtilisateur, that.groupeUtilisateur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, nom, deuxiemeNom, prenom, email, telephone, groupeUtilisateur);
    }
}
