package model.objects.organisation;

import model.objects.base.Type1;

import java.util.HashSet;
import java.util.Set;

public class Organisation extends Type1 {

    protected int nbSessions = 0;

    protected Set<Session> sessions = new HashSet<>();

    public Organisation() {}

    public Organisation(int id, String nom, String code, int nbSessions) {
        super(id, nom, code);
        this.nbSessions = nbSessions;
    }

    public int getNbSessions() {
        return nbSessions;
    }

    public void setNbSessions(int nbSessions) {
        this.nbSessions = nbSessions;
    }

    // Gestion des sessions

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
}
