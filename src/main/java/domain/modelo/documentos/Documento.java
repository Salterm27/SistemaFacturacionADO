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

    public Documento() {
        monto = 0.0;
        fecha = LocalDate.now();
        productosSeleccionables = new ArrayList<>();
    }

}
