package model.objects.personnes.eleves.infos;

import model.objects.cours.UniteEnseignement;
import model.objects.organisation.Periode;
import model.objects.utils.OneToMany;

import java.util.Set;

public class PeriodeInfos extends OneToMany<Periode, UniteEnseignementInfos> {

    public PeriodeInfos(Periode periode) {
        super(periode);
    }

    public PeriodeInfos(Periode periode, Set<UniteEnseignementInfos> many) {
        super(periode, many);
    }

    public Periode getPeriode() {
        return one;
    }

    public void setPeriode(Periode periode) {
        one = periode;
    }

    public UniteEnseignementInfos getUniteEnseignementInfos(UniteEnseignement uniteEnseignement) {
        for (UniteEnseignementInfos uniteEnseignementInfos: many) {
            if (uniteEnseignementInfos.getOne().equals(uniteEnseignement))
                return uniteEnseignementInfos;
        }
        return null;
    }

    public UniteEnseignementInfos getUniteEnseignementInfos(int id) {
        for (UniteEnseignementInfos uniteEnseignementInfos: many) {
            if (uniteEnseignementInfos.getOne().getId() == id)
                return uniteEnseignementInfos;
        }
        return null;
    }
}
