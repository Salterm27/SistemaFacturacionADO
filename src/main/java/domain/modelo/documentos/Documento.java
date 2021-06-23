package domain.modelo.documentos;

import domain.modelo.producto.ProductoSeleccionable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Documento {

    protected int numeroDocumento;
    protected double monto;
    protected LocalDate fecha;
    protected List <Item> detalle;
    protected double iva;


    public double getIva() { return iva; }
    public void inicializarFecha(){
        this.fecha = (LocalDate.now());
    }
    public List<Item> getDetalle() {
        return detalle;
    }

    public Double getMonto() {
        return monto;
    }

    public void calcularMonto(){
        monto = 0;
        iva = 0;
        if (detalle!=null){
            for (Item i: detalle){
                double montoPorProducto = i.getPs().getPrecioPorUnidad() * i.getCantidad();
                double ivaProducto=(montoPorProducto * i.getPs().getProducto().getIva()/100);
                montoPorProducto = montoPorProducto + ivaProducto;
                monto = monto + montoPorProducto;
                iva = iva + ivaProducto;
            }
            System.out.println("cantidad de productos: " + detalle.size());
            System.out.println("Monto: " + monto);
        }
    }

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
