package domain.modelo.documentos;

import domain.modelo.producto.ProductoSeleccionable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Documento {

    protected int numeroDocumento;
    protected Double monto;
    protected LocalDate fecha;
    protected Map <ProductoSeleccionable, Integer > detalle = new HashMap<ProductoSeleccionable, Integer>();

    public void inicializarFecha(){
        this.fecha = (LocalDate.now());
    }
    public Map<ProductoSeleccionable, Integer> getDetalle() {
        return detalle;
    }
    public void addProductoSeleccionable(ProductoSeleccionable ps, int cantidad){
        detalle.put(ps,cantidad);
        this.monto = this.monto + (ps.getPrecioPorUnidad()*cantidad);
    }

    public Double getMonto() {
        return monto;
    }

    /*public void calcularMonto(){
        monto = 0.0;
        for (Map.Entry<ProductoSeleccionable, Integer > entry : detalle.entrySet()){
            monto = monto + entry.getKey().getPrecioPorUnidad() * entry.getValue();
        }
    }*/

    public LocalDate getFecha() {
        return fecha;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
}
