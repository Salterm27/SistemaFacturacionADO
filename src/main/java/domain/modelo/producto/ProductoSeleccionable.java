package domain.modelo.producto;

public class ProductoSeleccionable {

    private float precioPorUnidad;
    private int tipoDeUnidad;

    public ProductoSeleccionable(float precioPorUnidad, int tipoDeUnidad) {
        this.precioPorUnidad = precioPorUnidad;
        this.tipoDeUnidad = tipoDeUnidad;
    }

    public Float getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    public void setPrecioPorUnidad(Float precioPorUnidad) {
        this.precioPorUnidad = precioPorUnidad;
    }

    public Integer getTipoDeUnidad() {
        return tipoDeUnidad;
    }

    public void setTipoDeUnidad(Integer tipoDeUnidad) {
        this.tipoDeUnidad = tipoDeUnidad;
    }

    public agregarProducto(){
        return 0;
    }

}
