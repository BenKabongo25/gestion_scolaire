package model.objects.personnes.eleves;

import model.objects.personnes.base.Adresse;
import model.objects.personnes.base.Individu;
import model.objects.personnes.base.Sexe;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Tuteur extends Individu {

    protected Set<Eleve> eleves = new HashSet<>();

    public Tuteur() {
    }

    public Tuteur(int id,
                  String code,
                  String nom,
                  String deuxiemeNom,
                  String prenom,
                  Sexe sexe,
                  Date dateNaissance) {
        super(id, code, nom, deuxiemeNom, prenom, sexe, dateNaissance);
    }

    public Tuteur(int id,
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

    public Set<Eleve> getEleves() {
        return eleves;
    }

    public void setEleves(Set<Eleve> eleves) {
        this.eleves = eleves;
    }

    public void addEleve(Eleve eleve) {
        eleves.add(eleve);
    }

    public void removeEleve(Eleve eleve) {
        eleves.remove(eleve);
    }
}
