package domain.modelo.documentos;

import domain.modelo.impuestos.ImpuestoAplicable;
import domain.modelo.producto.ProductoSeleccionable;
import domain.modelo.proveedores.Proveedor;

import java.util.List;
import java.util.Map;

public class Factura extends Documento{

    private OrdenDeCompra ordenDeCompra;
    private Boolean aprobacion;
    private ImpuestoAplicable impuestoAplicable;


    public Factura(int nroFactura, Boolean aprobacion, OrdenDeCompra ordenDeCompr, Map<ProductoSeleccionable, Integer> detalle ){
        super.numeroDocumento = nroFactura;
        this.ordenDeCompra = ordenDeCompra;
        this.aprobacion = aprobacion;
        super.inicializarFecha();
        super.detalle = detalle;
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
