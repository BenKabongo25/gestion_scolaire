package model.objects.utilisateurs;

import model.objects.utils.Type1;

import java.util.HashMap;
import java.util.Map;

public class Droit extends Type1 {

    protected String description;
    protected Map<GroupeUtilisateur, DroitInfos> groupesUtilisateurs = new HashMap<>();

    public Droit() {}

    public Droit(int id, String nom, String code, String description) {
        super(id, nom, code);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<GroupeUtilisateur, DroitInfos> getGroupesUtilisateurs() {
        return groupesUtilisateurs;
    }

    public void setGroupesUtilisateurs(Map<GroupeUtilisateur, DroitInfos> groupesUtilisateurs) {
        this.groupesUtilisateurs = groupesUtilisateurs;
    }

    public void addGroupeUtilisateur(GroupeUtilisateur groupeUtilisateur, DroitInfos droitInfos) {
        groupesUtilisateurs.put(groupeUtilisateur, droitInfos);
    }

    public void removeGroupeUtilisateur(GroupeUtilisateur groupeUtilisateur) {
        groupesUtilisateurs.remove(groupeUtilisateur);
    }
}
