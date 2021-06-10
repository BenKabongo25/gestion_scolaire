package model.objects.personnes.personnel;

import model.objects.utils.Identifable;

import java.util.Objects;

public class Poste extends Identifable {

    protected String nom = "";
    protected String code = "";

    public Poste() {}

    public Poste(int id, String nom, String code) {
        super(id);
        this.nom = nom;
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poste poste = (Poste) o;
        return Objects.equals(nom, poste.nom) && Objects.equals(code, poste.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, code);
    }
}
