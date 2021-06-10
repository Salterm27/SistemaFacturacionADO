package domain.modelo.producto;

import domain.modelo.proveedores.Proveedor;

public class ProductoSeleccionable {

    private float precioPorUnidad;
    private int tipoDeUnidad;
    private Producto producto;
    private Proveedor proveedor;

    public ProductoSeleccionable(  float precioPorUnidad, int tipoDeUnidad, Producto producto , Proveedor proveedor) {
        this.precioPorUnidad = precioPorUnidad;
        this.tipoDeUnidad = tipoDeUnidad;
        this.producto = producto;
        this.proveedor = proveedor;
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

    public void setPrecioPorUnidad(float precioPorUnidad) {
        this.precioPorUnidad = precioPorUnidad;
    }

    public void setTipoDeUnidad(int tipoDeUnidad) {
        this.tipoDeUnidad = tipoDeUnidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
