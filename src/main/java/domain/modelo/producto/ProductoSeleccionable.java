package domain.modelo.producto;

import domain.modelo.proveedores.Proveedor;

public class ProductoSeleccionable {

    private float precioPorUnidad;
    private int tipoDeUnidad;
    private Producto producto;
    private Proveedor proveedor;

    public ProductoSeleccionable(  float precioPorUnidad, int tipoDeUnidad, Producto producto ) {
        this.precioPorUnidad = precioPorUnidad;
        this.tipoDeUnidad = tipoDeUnidad;
        this.producto = producto;
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



}
