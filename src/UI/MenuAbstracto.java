package UI;
import ENUM.TipoInterfaz;
import GENERICS.ClaseBaseBddGenerica;

public abstract class MenuAbstracto {

    private ClaseBaseBddGenerica datos;
    public abstract TipoInterfaz mostrarInterfaz();

    
    public ClaseBaseBddGenerica getDatos() {
        return datos;
    }

    public void setDatos(ClaseBaseBddGenerica datos) {
        this.datos = datos;
    }
}
