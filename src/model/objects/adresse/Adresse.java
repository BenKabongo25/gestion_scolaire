package model.objects.adresse;

import model.objects.base.Identifable;

import java.util.Objects;

public class Adresse extends Identifable {

    protected String pays = "";
    protected String ville = "";
    protected String codepostal = "";
    protected String adresse = "";
    protected String complement = "";

    public Adresse() {}

    public Adresse(int id,
                   String pays,
                   String ville,
                   String codepostal,
                   String adresse,
                   String complement) {
        super(id);
        this.pays = pays;
        this.ville = ville;
        this.codepostal = codepostal;
        this.adresse = adresse;
        this.complement = complement;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse1 = (Adresse) o;
        return  pays.equals(adresse1.pays) &&
                ville.equals(adresse1.ville) &&
                codepostal.equals(adresse1.codepostal) &&
                adresse.equals(adresse1.adresse) &&
                complement.equals(adresse1.complement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pays, ville, codepostal, adresse, complement);
    }
}
