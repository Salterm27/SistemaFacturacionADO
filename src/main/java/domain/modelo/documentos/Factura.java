package domain.modelo.documentos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Factura extends Documento{

    private Integer NroFactura;
    private OrdenDeCompra ordenDeCompra;
    private Boolean aprobacion;

    public Factura(Integer nroFactura, OrdenDeCompra ordenDeCompra, Boolean aprobacion) {
        NroFactura = nroFactura;
        this.ordenDeCompra = ordenDeCompra;
        this.aprobacion = aprobacion;

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
