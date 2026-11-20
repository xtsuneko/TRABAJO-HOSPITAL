package GENERICS;

public abstract class ClaseBaseBddGenerica<T> {

    private boolean activo;
    private T id;

    //----CONSTRUCTORES----
    public ClaseBaseBddGenerica(boolean activo, T id) {
        this.activo = activo;
        this.id = id;
    }

    public ClaseBaseBddGenerica() {
    }

    //----GETTER Y SETTER----
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
