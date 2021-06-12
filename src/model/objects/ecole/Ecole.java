package model.objects.ecole;

import model.objects.adresse.Adresse;

public class Ecole {

    protected String nom = "";
    protected String sigle = "";
    protected String telephone = "";
    protected String email = "";
    protected Adresse adresse = new Adresse();

    public Ecole() {}

    public Ecole(String nom, String sigle, String telephone, String email, Adresse adresse) {
        this.nom = nom;
        this.sigle = sigle;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
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
}
