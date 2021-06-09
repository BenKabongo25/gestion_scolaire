package model.entites.personnes.eleves.infos;

import model.entites.classes.Classe;
import model.entites.organisation.AnneeScolaire;
import model.entites.utils.OneToMany;

import java.util.Set;

public class AnneeScolaireInfos extends OneToMany<AnneeScolaire, ClasseInfos> {

    public AnneeScolaireInfos(AnneeScolaire anneeScolaire) {
        super(anneeScolaire);
    }

    public AnneeScolaireInfos(AnneeScolaire anneeScolaire, Set<ClasseInfos> many) {
        super(anneeScolaire, many);
    }

    public AnneeScolaire getAnneeScolaire() {
        return one;
    }

    public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
        one = anneeScolaire;
    }

    public ClasseInfos getClasseInfos(Classe classe) {
        for (ClasseInfos classeInfos: many) {
            if (classeInfos.getOne().equals(classe))
                return classeInfos;
        }
        return null;
    }

    public ClasseInfos getClasseInfos(int id) {
        for (ClasseInfos classeInfos: many) {
            if (classeInfos.getOne().getId() == id)
                return classeInfos;
        }
        return null;
    }
}
