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
    protected List <Item> detalle;

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
        monto = 0.0;
        if (detalle!=null){
            for (Item i: detalle){
                double montoPorProducto = i.getPs().getPrecioPorUnidad() * i.getCantidad();
                System.out.println("precio/producto: " + i.getPs().getPrecioPorUnidad() +" Cantidad:"+ i.getCantidad());
                montoPorProducto = montoPorProducto + (montoPorProducto * i.getPs().getProducto().getIva()/100);
                monto = monto + montoPorProducto;
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
