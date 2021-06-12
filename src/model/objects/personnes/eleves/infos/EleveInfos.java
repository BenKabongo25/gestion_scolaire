package model.objects.personnes.eleves.infos;

import model.objects.organisation.AnneeScolaire;
import model.objects.personnes.eleves.Eleve;
import model.objects.base.OneToMany;

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
