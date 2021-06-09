package model.entites.personnes.eleves.infos;

import model.entites.classes.Classe;
import model.entites.organisation.Session;
import model.entites.utils.OneToMany;

import java.util.Set;

public class ClasseInfos extends OneToMany<Classe, SessionInfos> {

    public ClasseInfos(Classe classe) {
        super(classe);
    }

    public ClasseInfos(Classe classe, Set<SessionInfos> many) {
        super(classe, many);
    }

    public Classe getClasse() {
        return one;
    }

    public void setClasse(Classe classe) {
        one = classe;
    }

    public SessionInfos getSessionInfos(Session session) {
        for (SessionInfos sessionInfos: many) {
            if (sessionInfos.getOne().equals(session))
                return sessionInfos;
        }
        return null;
    }

    public SessionInfos getSessionInfos(int id) {
        for (SessionInfos sessionInfos: many) {
            if (sessionInfos.getOne().getId() == id)
                return sessionInfos;
        }
        return null;
    }
}
