package domain.modelo.documentos;

import domain.modelo.producto.ProductoSeleccionable;

public class Item {
    private ProductoSeleccionable ps;
    private int cantidad;
    private double TotalIva;

    public Item( ProductoSeleccionable ps, int cantidad) {
        this.ps = ps;
        this.cantidad = cantidad;
        this.TotalIva = (ps.getPrecioPorUnidad() * ps.getProducto().getIva() / 100) * cantidad;
    }

    public double getTotalIva() {
        return TotalIva;
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
