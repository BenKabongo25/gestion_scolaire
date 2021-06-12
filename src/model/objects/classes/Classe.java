package model.objects.classes;

import model.objects.base.Identifable;
import model.objects.organisation.AnneeScolaire;
import model.objects.organisation.Organisation;
import model.objects.personnes.eleves.Eleve;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Classe extends Identifable {

    protected Organisation organisation = new Organisation();
    protected Section section = new Section();
    protected Integer numero = 0;
    protected String label = "";
    protected String nom = "";
    protected String code = "";

    public Classe(int id,
                  Organisation organisation,
                  Section section,
                  int numero,
                  String label,
                  String nom,
                  String code) {
        super(id);
        this.organisation = organisation;
        this.section = section;
        this.numero = numero;
        this.label = label;
        this.nom = nom;
        this.code = code;
    }

    public Classe() {}

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classe classe = (Classe) o;
        return  Objects.equals(section, classe.section) &&
                Objects.equals(numero, classe.numero) &&
                Objects.equals(label, classe.label) &&
                Objects.equals(nom, classe.nom) &&
                Objects.equals(code, classe.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, section, numero, label, nom, code);
    }

    // Gestion des élèves et des années scolaires

    protected Map<AnneeScolaire, Set<Eleve>> anneesScolairesEleves = new HashMap<>();

    public Map<AnneeScolaire, Set<Eleve>> getAnneesScolairesEleves() {
        return anneesScolairesEleves;
    }

    public void setAnneesScolairesEleves(Map<AnneeScolaire, Set<Eleve>> anneesScolairesEleves) {
        this.anneesScolairesEleves = anneesScolairesEleves;
    }

    public void addAnneeScolaireEleves(AnneeScolaire anneeScolaire,
                                      Set<Eleve> eleves) {
        anneesScolairesEleves.put(anneeScolaire, eleves);
    }

    public void addAnneeScolaireEleve(AnneeScolaire anneeScolaire,
                                      Eleve eleve) {
        if (anneesScolairesEleves.containsKey(anneeScolaire))
            anneesScolairesEleves.put(anneeScolaire, new HashSet<>());

        Set<Eleve> eleves = anneesScolairesEleves.get(anneeScolaire);
        if (eleves == null)
            eleves = new HashSet<>();

        eleves.add(eleve);
        // anneesScolairesEleves.put(anneeScolaire, eleves);
    }

    public void removeAnneeScolaireEleves(AnneeScolaire anneeScolaire) {
        anneesScolairesEleves.remove(anneeScolaire);
    }

    public void removeAnneeScolaireEleve(AnneeScolaire anneeScolaire,
                                         Eleve eleve) {
        if (anneesScolairesEleves.containsKey(anneeScolaire))
            anneesScolairesEleves.put(anneeScolaire, new HashSet<>());

        Set<Eleve> eleves = anneesScolairesEleves.get(anneeScolaire);
        if (eleves == null)
            eleves = new HashSet<>();

        eleves.remove(eleve);
        // anneesScolairesEleves.put(anneeScolaire, eleves);
    }
}
