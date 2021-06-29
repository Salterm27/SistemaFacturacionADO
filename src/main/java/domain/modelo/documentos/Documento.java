package domain.modelo.documentos;

import domain.modelo.impuestos.ImpuestoAplicable;
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
    protected ImpuestoAplicable impuestoAplicable;

    public double getIva() { return impuestoAplicable.getIva(); }
    public double getIIBB() { return impuestoAplicable.getIIBB(); }

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
        double iva = 0;
        double iibb = 0;
        impuestoAplicable = new ImpuestoAplicable();
        if (detalle!=null){
            for (Item i: detalle){
                //Recupero el producto y lo multiplico por el precio
                double montoPorProducto = i.getPs().getPrecioPorUnidad() * i.getCantidad();
                double ivaProducto=0;
                double iibbProducto = 0;

                //VALIDACION CERTIFICADOS DE EXCEPCION IVA Y CALCULO
                if (! i.getPs().getProveedor().getExcenciones().esExcentoIva()) {
                    ivaProducto = (montoPorProducto * i.getPs().getProducto().getIva() / 100);
                    iva = iva + ivaProducto;
                }
                //VALIDACION CERTIFICADOS DE EXCEPCION IIBB Y CALCULO
                if (! i.getPs().getProveedor().getExcenciones().esExcentoIIBB()) {
                    iibbProducto = (montoPorProducto * i.getPs().getProveedor().getPorcentajeIIBB() / 100);
                    iibb = iibb + iibbProducto;
                }

                montoPorProducto = montoPorProducto + ivaProducto + iibbProducto;
                monto = monto + montoPorProducto;
            }

            System.out.println("cantidad de productos: " + detalle.size());
            System.out.println("Monto: " + monto);

            this.impuestoAplicable.setIva(iva);
            this.impuestoAplicable.setIIBB(iibb);
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
