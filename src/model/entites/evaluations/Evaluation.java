package model.entites.evaluations;

import model.base.Identifable;
import model.entites.organisation.AnneeScolaire;
import model.entites.organisation.Periode;
import model.entites.classes.Classe;
import model.entites.cours.Cours;
import model.entites.personnes.eleves.Eleve;

import java.sql.Date;

public class Evaluation extends Identifable {

    protected Eleve eleve = new Eleve();
    protected Cours cours = new Cours();
    protected Classe classe = new Classe();
    protected Periode periode = new Periode();
    protected AnneeScolaire anneeScolaire = new AnneeScolaire();
    protected TypeEvaluation type = new TypeEvaluation();
    protected String nom = "";
    protected Date date = new Date(0);
    protected float note = 0;
    protected float max = 1;

    public Evaluation() {}
}
