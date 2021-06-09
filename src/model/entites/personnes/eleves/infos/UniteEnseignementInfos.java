package model.entites.personnes.eleves.infos;

import model.entites.cours.Cours;
import model.entites.cours.UniteEnseignement;
import model.entites.utils.OneToMany;

import java.util.Set;

public class UniteEnseignementInfos extends OneToMany<UniteEnseignement, CoursInfos> {

    public UniteEnseignementInfos(UniteEnseignement uniteEnseignement) {
        super(uniteEnseignement);
    }

    public UniteEnseignementInfos(UniteEnseignement uniteEnseignement, Set<CoursInfos> many) {
        super(uniteEnseignement, many);
    }

    public UniteEnseignement getUniteEnseignement() {
        return one;
    }

    public void setUniteEnseignement(UniteEnseignement uniteEnseignement) {
        one = uniteEnseignement;
    }

    public CoursInfos getCoursInfos(Cours cours) {
        for (CoursInfos coursInfos: many) {
            if (coursInfos.getCours().equals(cours))
                return coursInfos;
        }
        return null;
    }

    public CoursInfos getCoursInfos(int id) {
        for (CoursInfos coursInfos: many) {
            if (coursInfos.getCours().getId() == id)
                return coursInfos;
        }
        return null;
    }
}
