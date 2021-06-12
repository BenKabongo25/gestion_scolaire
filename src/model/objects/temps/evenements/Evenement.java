package model.objects.temps.evenements;

import model.objects.base.Identifable;
import model.objects.personnes.eleves.Eleve;
import model.objects.personnes.personnel.Personnel;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Evenement extends Identifable {

    protected String nom = "";
    protected TypeEvenement typeEvenement = new TypeEvenement();
    protected String emplacement = "";
    protected Date debut = new Date(0);
    protected Date fin = new Date(0);
    protected String description = "";
    protected Set<Eleve> eleves = new HashSet<>();
    protected Set<Personnel> personnels = new HashSet<>();

    protected EvenementRepetition evenementRepetition = null;

    public Evenement() {}

    public Evenement(int id,
                     String nom,
                     TypeEvenement typeEvenement,
                     String emplacement,
                     Date debut,
                     Date fin,
                     String description) {
        super(id);
        this.nom = nom;
        this.typeEvenement = typeEvenement;
        this.emplacement = emplacement;
        this.debut = debut;
        this.fin = fin;
        this.description = description;
    }

    public Evenement(int id,
                     String nom,
                     TypeEvenement typeEvenement,
                     String emplacement,
                     Date debut,
                     Date fin,
                     String description,
                     Set<Eleve> eleves,
                     Set<Personnel> personnels) {
        this(id, nom, typeEvenement, emplacement, debut, fin, description);
        this.eleves = eleves;
        this.personnels = personnels;
    }

    public Evenement(int id,
                     String nom,
                     TypeEvenement typeEvenement,
                     String emplacement,
                     Date debut,
                     Date fin,
                     String description,
                     Set<Eleve> eleves,
                     Set<Personnel> personnels,
                     EvenementRepetition evenementRepetition) {
        this(id, nom, typeEvenement, emplacement, debut, fin, description, eleves, personnels);
        this.evenementRepetition = evenementRepetition;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeEvenement getTypeEvenement() {
        return typeEvenement;
    }

    public void setTypeEvenement(TypeEvenement typeEvenement) {
        this.typeEvenement = typeEvenement;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<Personnel> getPersonnels() {
        return personnels;
    }

    public void setPersonnels(Set<Personnel> personnels) {
        this.personnels = personnels;
    }

    public void addPersonnel(Personnel personnel) {
        personnels.add(personnel);
    }

    public void removePersonnel(Personnel personnel) {
        personnels.remove(personnel);
    }

    public EvenementRepetition getEvenementRepetition() {
        return evenementRepetition;
    }

    public void setEvenementRepetition(EvenementRepetition evenementRepetition) {
        this.evenementRepetition = evenementRepetition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evenement)) return false;
        Evenement evenement = (Evenement) o;
        return  Objects.equals(nom, evenement.nom) &&
                Objects.equals(typeEvenement, evenement.typeEvenement) &&
                Objects.equals(emplacement, evenement.emplacement) &&
                Objects.equals(debut, evenement.debut) &&
                Objects.equals(fin, evenement.fin) &&
                Objects.equals(description, evenement.description) &&
                Objects.equals(eleves, evenement.eleves) &&
                Objects.equals(personnels, evenement.personnels) &&
                Objects.equals(evenementRepetition, evenement.evenementRepetition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, typeEvenement, emplacement, debut, fin, description, eleves, personnels);
    }
}
