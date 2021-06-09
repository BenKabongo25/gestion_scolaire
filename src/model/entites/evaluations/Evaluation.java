package model.entites.evaluations;

import model.entites.cours.UniteEnseignement;
import model.entites.organisation.Session;
import model.entites.utils.Type1;
import model.entites.organisation.AnneeScolaire;
import model.entites.organisation.Periode;
import model.entites.classes.Classe;
import model.entites.cours.Cours;
import model.entites.personnes.eleves.Eleve;

import java.sql.Date;
import java.util.Objects;

public class Evaluation extends Type1 {

    protected AnneeScolaire anneeScolaire = new AnneeScolaire();
    protected Eleve eleve = new Eleve();
    protected Classe classe = new Classe();
    protected Session session = new Session();
    protected Periode periode = new Periode();
    protected UniteEnseignement uniteEnseignement = new UniteEnseignement();
    protected Cours cours = new Cours();
    protected TypeEvaluation typeEvaluation = new TypeEvaluation();
    protected Date date = new Date(0);
    protected float note = 0;
    protected float max = 1;

    public Evaluation() {}

    public Evaluation(int id,
                      String nom,
                      String code,
                      AnneeScolaire anneeScolaire,
                      Eleve eleve,
                      Classe classe,
                      Session session,
                      Periode periode,
                      UniteEnseignement uniteEnseignement,
                      Cours cours,
                      TypeEvaluation typeEvaluation,
                      Date date,
                      float note,
                      float max) {
        super(id, nom, code);
        this.anneeScolaire = anneeScolaire;
        this.eleve = eleve;
        this.classe = classe;
        this.session = session;
        this.periode = periode;
        this.uniteEnseignement = uniteEnseignement;
        this.cours = cours;
        this.typeEvaluation = typeEvaluation;
        this.date = date;
        this.note = note;
        this.max = max;
    }

    public AnneeScolaire getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public UniteEnseignement getUniteEnseignement() {
        return uniteEnseignement;
    }

    public void setUniteEnseignement(UniteEnseignement uniteEnseignement) {
        this.uniteEnseignement = uniteEnseignement;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public TypeEvaluation getTypeEvaluation() {
        return typeEvaluation;
    }

    public void setTypeEvaluation(TypeEvaluation typeEvaluation) {
        this.typeEvaluation = typeEvaluation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Evaluation that = (Evaluation) o;
        return  Objects.equals(anneeScolaire, that.anneeScolaire) &&
                Objects.equals(eleve, that.eleve) &&
                Objects.equals(classe, that.classe) &&
                Objects.equals(uniteEnseignement, that.uniteEnseignement) &&
                Objects.equals(cours, that.cours) &&
                Objects.equals(session, that.session) &&
                Objects.equals(periode, that.periode) &&
                Objects.equals(date, that.date) &&
                Float.compare(that.note, note) == 0 &&
                Float.compare(that.max, max) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                nom,
                code,
                anneeScolaire,
                eleve,
                classe,
                uniteEnseignement,
                cours,
                session,
                periode,
                date,
                note,
                max);
    }
}
