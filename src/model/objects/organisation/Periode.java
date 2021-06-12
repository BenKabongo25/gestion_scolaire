package model.objects.organisation;

import model.objects.base.Type1;

import java.util.Objects;

public class Periode extends Type1 {

    protected Session session = new Session();
    protected int periodeId = 0;

    public Periode() {}

    public Periode(int id, String nom, String code, Session session, int periodeId) {
        super(id, nom, code);
        this.session = session;
        this.periodeId = periodeId;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getPeriodeId() {
        return periodeId;
    }

    public void setPeriodeId(int periodeId) {
        this.periodeId = periodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Periode periode = (Periode) o;
        return  nom.equals(periode.nom) &&
                code.equals(periode.code) &&
                periodeId == periode.periodeId &&
                session.equals(periode.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, code, session, periodeId);
    }
}
