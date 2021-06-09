package model.entites.personnes.eleves.infos;

import model.entites.organisation.AnneeScolaire;
import model.entites.personnes.eleves.Eleve;
import model.entites.utils.OneToMany;

import java.util.Set;

public class EleveInfos extends OneToMany<Eleve, AnneeScolaireInfos> {

    public EleveInfos(Eleve eleve) {
        super(eleve);
    }

    public EleveInfos(Eleve eleve, Set<AnneeScolaireInfos> many) {
        super(eleve, many);
    }

    public Eleve getEleve() {
        return one;
    }

    public void setEleve(Eleve eleve) {
        one = eleve;
    }

    public AnneeScolaireInfos getAnneeScolaireInfos(AnneeScolaire anneeScolaire) {
        for (AnneeScolaireInfos anneeScolaireInfos: many) {
            if (anneeScolaireInfos.getOne().equals(anneeScolaire))
                return anneeScolaireInfos;
        }
        return null;
    }

    public AnneeScolaireInfos getAnneeScolaireInfos(int id) {
        for (AnneeScolaireInfos anneeScolaireInfos: many) {
            if (anneeScolaireInfos.getOne().getId() == id)
                return anneeScolaireInfos;
        }
        return null;
    }
}
