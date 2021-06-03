package domain.modelo.documentos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Factura extends Documento{

    private int NroFactura;
    private OrdenDeCompra ordenDeCompra;
    private Boolean aprobacion;

    public Factura(int nroFactura, Boolean aprobacion, OrdenDeCompra ordenDeCompra){
        NroFactura = nroFactura;
        this.ordenDeCompra = ordenDeCompra;
        this.aprobacion = aprobacion;
        setMonto(0.0);
        this.setFecha(LocalDate.now());
        this.setProductosSeleccionables(new ArrayList<>());
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
