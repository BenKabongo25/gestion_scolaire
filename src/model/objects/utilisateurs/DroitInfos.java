package model.objects.utilisateurs;

public class DroitInfos {

    private boolean create  = false;
    private boolean read    = false;
    private boolean update  = false;
    private boolean delete  = false;

    public DroitInfos() {}

    public DroitInfos(boolean create, boolean read, boolean update, boolean delete) {
        this.create = create;
        this.read = read;
        this.update = update;
        this.delete = delete;
    }

    public boolean canCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public boolean canRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean canUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean canDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
