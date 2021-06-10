package model.objects.cours;

import model.objects.utils.Type1;

import java.util.HashSet;
import java.util.Set;

public class UniteEnseignement extends Type1 {

    protected Set<Cours> cours = new HashSet<>();

    public UniteEnseignement() {}

    public UniteEnseignement(int id, String nom, String code) {
        super(id, nom, code);
    }

    // Gestion des cours

    public Set<Cours> getCours() {
        return cours;
    }

    public void setCours(Set<Cours> cours) {
        this.cours = cours;
    }

    public void addCours(Cours c) {
        cours.add(c);
    }

    public void removeCours(Cours c) {
        cours.remove(c);
    }
}
