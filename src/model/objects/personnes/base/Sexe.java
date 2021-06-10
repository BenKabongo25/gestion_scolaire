package model.objects.personnes.base;

public enum Sexe {
    HOMME (0),
    FEMME (1);

    private int value;

    Sexe(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return (value == HOMME.value) ? "Homme" : "Femme";
    }
}
