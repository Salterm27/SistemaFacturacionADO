package domain.modelo.documentos;

import domain.modelo.producto.ProductoSeleccionable;

public class Item {
    private ProductoSeleccionable ps;
    private int cantidad;

    public Item( ProductoSeleccionable ps, int cantidad) {
        this.ps = ps;
        this.cantidad = cantidad;
    }

    public ProductoSeleccionable getPs() {
        return ps;
    }

    public void setPs(ProductoSeleccionable ps) {
        this.ps = ps;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
