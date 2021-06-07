package model.entites.organisation;

import model.entites.utils.Type1;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Session extends Type1 {

    protected Organisation organisation = new Organisation();
    protected int sessionId = 0;
    protected int nbPeriodes = 0;

    protected Set<Periode> periodes = new HashSet<>();

    public Session() {}

    public Session(int id,
                   String nom,
                   String code,
                   Organisation organisation,
                   int sessionId,
                   int nbPeriodes) {
        super(id, nom, code);
        this.organisation = organisation;
        this.sessionId = sessionId;
        this.nbPeriodes = nbPeriodes;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getNbPeriodes() {
        return nbPeriodes;
    }

    public void setNbPeriodes(int nbPeriodes) {
        this.nbPeriodes = nbPeriodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Session session = (Session) o;
        return  nom.equals(session.nom) &&
                code.equals(session.code) &&
                sessionId == session.sessionId &&
                nbPeriodes == session.nbPeriodes &&
                organisation.equals(session.organisation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, code, organisation, sessionId, nbPeriodes);
    }

    // Gestion des périodes

    public Set<Periode> getPeriodes() {
        return periodes;
    }

    public void setPeriodes(Set<Periode> periodes) {
        this.periodes = periodes;
    }

    public void addPeriode(Periode periode) {
        periodes.add(periode);
    }

    public void removePeriode(Periode periode) {
        periodes.remove(periode);
    }
}
