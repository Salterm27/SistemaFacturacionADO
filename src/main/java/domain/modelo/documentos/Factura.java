package domain.modelo.documentos;

import domain.modelo.impuestos.ImpuestoAplicable;
import domain.modelo.producto.Producto;
import domain.modelo.producto.ProductoSeleccionable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura extends Documento{

    private int NroFactura;
    private OrdenDeCompra ordenDeCompra;
    private Boolean aprobacion;
    private ImpuestoAplicable impuestoAplicable;


    public Factura(int nroFactura, Boolean aprobacion, OrdenDeCompra ordenDeCompra){
        NroFactura = nroFactura;
        this.ordenDeCompra = ordenDeCompra;
        this.aprobacion = aprobacion;
        setMonto(0.0);
        this.setFecha(LocalDate.now());
    }


    public Integer getNroFactura() {
        return NroFactura;
    }

    public void setNroFactura(Integer nroFactura) {
        NroFactura = nroFactura;
    }

    public OrdenDeCompra getOrdenDeCompra() {
        return ordenDeCompra;
    }

    public void setOrdenDeCompra(OrdenDeCompra ordenDeCompra) {
        this.ordenDeCompra = ordenDeCompra;
    }

    public Boolean getAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(Boolean aprobacion) {
        this.aprobacion = aprobacion;
    }
}
