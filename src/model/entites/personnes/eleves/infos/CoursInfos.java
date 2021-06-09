package model.entites.personnes.eleves.infos;

import model.entites.cours.Cours;

public class CoursInfos {

    protected Cours cours = new Cours();

    public CoursInfos(Cours cours) {
        this.cours = cours;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }
}
