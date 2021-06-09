package model.entites.personnes.eleves;

import model.entites.personnes.base.Adresse;
import model.entites.personnes.base.Individu;
import model.entites.personnes.base.Sexe;
import model.entites.personnes.eleves.infos.EleveInfos;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Eleve extends Individu {

    protected EleveInfos eleveInfos = new EleveInfos(this);
    protected Set<Tuteur> tuteurs = new HashSet<>();

    public Eleve() {
    }

    public Eleve(int id,
                 String code,
                 String nom,
                 String deuxiemeNom,
                 String prenom,
                 Sexe sexe,
                 Date dateNaissance) {
        super(id, code, nom, deuxiemeNom, prenom, sexe, dateNaissance);
    }

    public Eleve(int id,
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
        super(id, code, nom, deuxiemeNom, prenom, sexe, dateNaissance, paysNaissance, villeNaissance, telephone, email, adresse);
    }

    public EleveInfos getEleveInfos() {
        return eleveInfos;
    }

    public void setEleveInfos(EleveInfos eleveInfos) {
        this.eleveInfos = eleveInfos;
    }

    public Set<Tuteur> getTuteurs() {
        return tuteurs;
    }

    public void setTuteurs(Set<Tuteur> tuteurs) {
        this.tuteurs = tuteurs;
    }

    public void addTuteur(Tuteur tuteur) {
        tuteurs.add(tuteur);
    }

    public void removeTuteur(Tuteur tuteur) {
        tuteurs.remove(tuteur);
    }
}
