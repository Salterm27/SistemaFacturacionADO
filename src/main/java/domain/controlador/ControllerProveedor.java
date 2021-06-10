package domain.controlador;

import domain.modelo.documentos.Factura;
import domain.modelo.documentos.OrdenDeCompra;
import domain.modelo.proveedores.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class ControllerProveedor {
    List <Proveedor> proveedores ;

    public ControllerProveedor() {
        proveedores = new ArrayList<>();
    }

    public Proveedor getProveedorXcuit(int cuit){
        for (Proveedor p: proveedores) {
            if (p.getCuit() == cuit){
                return p;
            }
        }
        return null;
    }

    public void addFactura(int cuit,int nroFactura, Boolean aprobacion, OrdenDeCompra ordenDeCompra){
        Proveedor p = this.getProveedorXcuit(cuit);
        Factura a = new Factura( nroFactura,  aprobacion,  ordenDeCompra);
        p.addFactura(a);
    }

    public void addProveedor(int cuit, String razonSocial){
        Proveedor p = new Proveedor(cuit, razonSocial);
        proveedores.add (p);
    }
    public void imprimirfacturas( int cuit ){
        Proveedor p = getProveedorXcuit(cuit);
        List<Factura> facturas =  p.getFacturas();
        for (Factura f: facturas){
            System.out.println(f.getNroFactura() + " " + f.getFecha());
        }
    }
    public void mostrarProveedores(){
        for (Proveedor p: proveedores) {
            System.out.println(p.getCuit() + " " + p.getRazonSocial());
        }
    }
}
