package model.entites.personnes.eleves;

import model.entites.personnes.base.Adresse;
import model.entites.personnes.base.Individu;
import model.entites.personnes.base.Sexe;
import model.entites.personnes.eleves.infos.EleveInfos;

import java.sql.Date;

public class Eleve extends Individu {

    protected EleveInfos eleveInfos = new EleveInfos(this);

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
}
