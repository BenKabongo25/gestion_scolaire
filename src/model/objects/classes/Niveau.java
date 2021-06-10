package model.objects.classes;

import model.objects.utils.Type1;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Niveau extends Type1 {

    protected Set<Section> sectionList = new HashSet<>();

    public Niveau() {}

    public Niveau(int id, String nom, String code) {
        super(id, nom, code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Niveau niveau = (Niveau) o;
        return Objects.equals(nom, niveau.nom) && Objects.equals(code, niveau.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, code);
    }

    // Gestion des sections

    public Set<Section> getSections() {
        return sectionList;
    }

    public void setSections(Set<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public void addSection(Section section) {
        sectionList.add(section);
    }

    public void removeSection(Section section) {
        sectionList.remove(section);
    }
}
