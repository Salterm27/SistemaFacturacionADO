package domain.modelo.documentos;

import domain.modelo.producto.ProductoSeleccionable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Documento {

    private Double monto;
    private LocalDate fecha;
    private List<ProductoSeleccionable> productosSeleccionables;
    //private Boolean propio; ????????

    public void addProductosSeleccionable(ProductoSeleccionable ps) {
          productosSeleccionables.add(ps);
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setProductosSeleccionables(List<ProductoSeleccionable> productosSeleccionables) {
        this.productosSeleccionables = productosSeleccionables;
    }
}
