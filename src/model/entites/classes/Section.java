package model.entites.classes;

import model.base.Type1;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Section extends Type1 {

    protected Niveau niveau = new Niveau();

    protected Set<Classe> classeList = new HashSet<>();

    public Section(int id, String nom, String code, Niveau niveau) {
        super(id, nom, code);
        this.niveau = niveau;
    }

    public Section() {}

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return  Objects.equals(nom, section.nom) &&
                Objects.equals(code, section.code) &&
                Objects.equals(niveau, section.niveau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, code, niveau);
    }

    // Gestion des classes

    public Set<Classe> getClasses() {
        return classeList;
    }

    public void setClasses(Set<Classe> classes) {
        this.classeList = classes;
    }

    public void addClasse(Classe classe) {
        classeList.add(classe);
    }

    public void removeClasse(Classe classe) {
        classeList.remove(classe);
    }
}
