package model.entites.personnes.personnel;

import model.entites.personnes.base.Adresse;
import model.entites.personnes.base.Individu;
import model.entites.personnes.base.Sexe;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Personnel extends Individu {

    protected Set<Poste> posteList = new HashSet<>();

    public Personnel() {
    }

    public Personnel(int id,
                     String code,
                     String nom,
                     String deuxiemeNom,
                     String prenom,
                     Sexe sexe,
                     Date dateNaissance) {
        super(id, code, nom, deuxiemeNom, prenom, sexe, dateNaissance);
    }

    public Personnel(int id,
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

    // Gestion des postes

    public Set<Poste> getPostes() {
        return posteList;
    }

    public void setPostes(Set<Poste> postes) {
        this.posteList = postes;
    }

    public void addPoste(Poste poste) {
        posteList.add(poste);
    }

    public void removePoste(Poste poste) {
        posteList.remove(poste);
    }
}
