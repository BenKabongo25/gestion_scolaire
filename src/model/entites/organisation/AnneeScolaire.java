package model.entites.organisation;

import model.entites.utils.Identifable;

import java.sql.Date;
import java.util.Objects;

public class AnneeScolaire extends Identifable {

    protected Date debut = new Date(0);
    protected Date fin = new Date(0);

    public AnneeScolaire() {}

    public AnneeScolaire(int id, Date debut, Date fin) {
        super(id);
        this.debut = debut;
        this.fin = fin;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnneeScolaire that = (AnneeScolaire) o;
        return Objects.equals(debut, that.debut) && Objects.equals(fin, that.fin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(debut, fin);
    }

    @Override
    public String toString() {
        return "AnneeScolaire{" +
                "id=" + id +
                ", debut=" + debut +
                ", fin=" + fin +
                '}';
    }
}
