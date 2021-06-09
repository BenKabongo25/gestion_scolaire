package model.entites.personnes.eleves.infos;

import model.entites.organisation.Periode;
import model.entites.organisation.Session;
import model.entites.utils.OneToMany;

import java.util.Set;

public class SessionInfos extends OneToMany<Session, PeriodeInfos> {

    public SessionInfos(Session session) {
        super(session);
    }

    public SessionInfos(Session session, Set<PeriodeInfos> many) {
        super(session, many);
    }

    public Session getSession() {
        return one;
    }

    public void setSession(Session session) {
        one = session;
    }

    public PeriodeInfos getPeriodeInfos(Periode periode) {
        for (PeriodeInfos periodeInfos: many) {
            if (periodeInfos.getOne().equals(periode))
                return periodeInfos;
        }
        return null;
    }

    public PeriodeInfos getPeriodeInfos(int id) {
        for (PeriodeInfos periodeInfos: many) {
            if (periodeInfos.getOne().getId() == id)
                return periodeInfos;
        }
        return null;
    }
}
