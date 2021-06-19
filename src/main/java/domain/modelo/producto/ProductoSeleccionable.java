package domain.modelo.producto;

import domain.modelo.proveedores.Proveedor;

public class ProductoSeleccionable {

    private float precioPorUnidad;
    private String tipoDeUnidad;
    private Producto producto;
    private Proveedor proveedor;

    public ProductoSeleccionable(  float precioPorUnidad, String tipoDeUnidad, Producto producto , Proveedor proveedor, float iva) {
        this.precioPorUnidad = precioPorUnidad;
        this.tipoDeUnidad = tipoDeUnidad;
        this.producto = producto;
        this.proveedor = proveedor;

    }

    public double getIva(){
        return this.producto.getIva();
    }

    public Float getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    public void setPrecioPorUnidad(Float precioPorUnidad) {
        this.precioPorUnidad = precioPorUnidad;
    }

    public String getTipoDeUnidad() {
        return tipoDeUnidad;
    }

    public void setTipoDeUnidad() {
        this.tipoDeUnidad = tipoDeUnidad;
    }

    public void setPrecioPorUnidad(float precioPorUnidad) {
        this.precioPorUnidad = precioPorUnidad;
    }

    public void setTipoDeUnidad(String tipoDeUnidad) {
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
