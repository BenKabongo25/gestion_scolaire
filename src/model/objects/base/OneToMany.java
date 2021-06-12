package model.objects.base;

import java.util.HashSet;
import java.util.Set;

public class OneToMany<One, Many> {

    protected One one;
    protected Set<Many> many = new HashSet<>();

    public OneToMany(One one) {
        this.one = one;
    }

    public OneToMany(One one, Set<Many> many) {
        this.one = one;
        this.many = many;
    }

    public One getOne() {
        return one;
    }

    public void setOne(One one) {
        this.one = one;
    }

    public Set<Many> getMany() {
        return many;
    }

    public void setMany(Set<Many> many) {
        this.many = many;
    }

    public void addMany(Many many) {
        this.many.add(many);
    }

    public void removeMany(Many many) {
        this.many.remove(many);
    }
}
