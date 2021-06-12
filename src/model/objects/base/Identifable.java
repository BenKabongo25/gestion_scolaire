package model.objects.base;

public abstract class Identifable {

    protected Integer id = 0;

    public Identifable(int id) {
        this.id = id;
    }

    public Identifable() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
