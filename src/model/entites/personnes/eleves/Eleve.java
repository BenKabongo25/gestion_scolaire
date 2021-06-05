package model.entites.personnes.eleves;

import model.entites.organisation.AnneeScolaire;
import model.entites.classes.Classe;
import model.entites.personnes.base.Adresse;
import model.entites.personnes.base.Individu;
import model.entites.personnes.base.Sexe;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Eleve extends Individu {

    protected Map<AnneeScolaire, Classe> anneesScolairesClasses = new HashMap<>();

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

    // Gestion des années scolaires et des classes

    public Map<AnneeScolaire, Classe> getAnneesScolairesClasses() {
        return anneesScolairesClasses;
    }

    public void setAnneesScolairesClasses(Map<AnneeScolaire, Classe> anneesScolairesClasses) {
        this.anneesScolairesClasses = anneesScolairesClasses;
    }

    public void addAnneeScolaireClasse(AnneeScolaire anneeScolaire,
                                       Classe classe) {
        anneesScolairesClasses.put(anneeScolaire, classe);
    }

    public void removeAnneeScolaire(AnneeScolaire anneeScolaire) {
        anneesScolairesClasses.remove(anneeScolaire);
    }

    public void removeAnneeScolaireClasse(AnneeScolaire anneeScolaire,
                                          Classe classe) {
        anneesScolairesClasses.remove(anneeScolaire, classe);
    }
}
