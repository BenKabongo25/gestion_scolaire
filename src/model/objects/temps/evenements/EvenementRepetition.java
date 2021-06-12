package model.objects.temps.evenements;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EvenementRepetition {

    public final int ANNEE = 0;
    public final int MOIS = 1;
    public final int SEMAINE = 2;
    public final int JOUR = 3;

    protected Evenement evenement = new Evenement();
    protected int chaque = JOUR;
    protected int nombre = 0;
    protected Date finRepetition = new Date(0);

    public EvenementRepetition() {}

    public EvenementRepetition(Evenement evenement, int chaque, int nombre, Date finRepetition) {
        this.evenement = evenement;
        this.chaque = chaque;
        this.nombre = nombre;
        this.finRepetition = finRepetition;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public int getChaque() {
        return chaque;
    }

    public void setChaque(int chaque) {
        this.chaque = chaque;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public Date getFinRepetition() {
        return finRepetition;
    }

    public void setFinRepetition(Date finRepetition) {
        this.finRepetition = finRepetition;
    }

    public List<Evenement> getEvenements() {
        List<Evenement> evenements = new ArrayList<>();

        if (chaque == JOUR) {
            //
        }
        else if (chaque == SEMAINE) {
            //
        }
        else if (chaque == MOIS) {

        }
        else if (chaque == ANNEE) {

        }
        else {

        }
        return evenements;
    }
}
