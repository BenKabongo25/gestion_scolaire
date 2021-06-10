package model.objects.utilisateurs;

import model.objects.utils.Type1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GroupeUtilisateur extends Type1 {

    protected String description = "";
    protected Set<Utilisateur> utilisateurs = new HashSet<>();
    protected Map<Droit, DroitInfos> droits = new HashMap<>();

    public GroupeUtilisateur() {}

    public GroupeUtilisateur(int id, String nom, String code, String description) {
        super(id, nom, code);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public void addUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    public void removeUtilisateur(Utilisateur utilisateur) {
        utilisateurs.remove(utilisateur);
    }

    public Map<Droit, DroitInfos> getDroits() {
        return droits;
    }

    public void setDroits(Map<Droit, DroitInfos> droits) {
        this.droits = droits;
    }

    public void addDroit(Droit droit, DroitInfos infos) {
        droits.put(droit, infos);
    }

    public void removeDroit(Droit droit) {
        droits.remove(droit);
    }
}
