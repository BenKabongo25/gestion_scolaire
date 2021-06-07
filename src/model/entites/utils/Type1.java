package model.entites.utils;

import java.util.Objects;

public abstract class Type1 extends Identifable {

    protected String nom = "";
    protected String code = "";

    public Type1() {}

    public Type1(int id, String nom, String code) {
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
        Type1 type1 = (Type1) o;
        return Objects.equals(nom, type1.nom) && Objects.equals(code, type1.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, code);
    }
}
