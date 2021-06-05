package model.entites.personnes.base;

import model.base.Identifable;

import java.sql.Date;
import java.util.Objects;

public class Individu extends Identifable {

    protected String code = "";
    protected String nom = "";
    protected String deuxiemeNom = "";
    protected String prenom = "";
    protected Sexe sexe = Sexe.HOMME;
    protected Date dateNaissance = new Date(0);
    protected String paysNaissance = "";
    protected String villeNaissance = "";
    protected String telephone = "";
    protected String email = "";
    protected Adresse adresse = new Adresse();

    public Individu() {}

    public Individu(int id,
                    String code,
                    String nom,
                    String deuxiemeNom,
                    String prenom,
                    Sexe sexe,
                    Date dateNaissance) {
        super(id);
        this.code = code;
        this.nom = nom;
        this.deuxiemeNom = deuxiemeNom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
    }

    public Individu(int id,
                    String code,
                    String nom,
                    String deuxiemeNom,
                    String prenom,
                    Sexe sexe,
                    Date dateNaissance,
                    String paysNaissance,
                    String villeNaissance,
                    String telephone,
                    String email,
                    Adresse adresse) {
        super(id);
        this.code = code;
        this.nom = nom;
        this.deuxiemeNom = deuxiemeNom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.paysNaissance = paysNaissance;
        this.villeNaissance = villeNaissance;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPaysNaissance() {
        return paysNaissance;
    }

    public void setPaysNaissance(String paysNaissance) {
        this.paysNaissance = paysNaissance;
    }

    public String getVilleNaissance() {
        return villeNaissance;
    }

    public void setVilleNaissance(String villeNaissance) {
        this.villeNaissance = villeNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individu individu = (Individu) o;
        return  Objects.equals(code, individu.code) &&
                Objects.equals(nom, individu.nom) &&
                Objects.equals(deuxiemeNom, individu.deuxiemeNom) &&
                Objects.equals(prenom, individu.prenom) &&
                sexe == individu.sexe &&
                Objects.equals(dateNaissance, individu.dateNaissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, nom, deuxiemeNom, prenom, sexe, dateNaissance);
    }
}
