package domain.controlador;

import domain.modelo.documentos.Factura;
import domain.modelo.documentos.OrdenDeCompra;
import domain.modelo.producto.ProductoSeleccionable;
import domain.modelo.proveedores.Proveedor;

import java.time.LocalDate;
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

    public void addProveedor(int cuit, String responsabilidadIVA, String razonSocial,
                             String nombreFantasia, String direccion, int telefono,
                             String correoElectronico, int nroIIBB, LocalDate inicioActividad,
                             int retencionImpuestos){
        Proveedor p = new Proveedor( cuit,  responsabilidadIVA,  razonSocial, nombreFantasia,  direccion,  telefono, correoElectronico,  nroIIBB,  inicioActividad,  retencionImpuestos);
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

    public void asociarProductoSeleccionable(int cuitProveedor, ProductoSeleccionable ps){
        getProveedorXcuit(cuitProveedor).asociarProductoSeleccionable(ps);
    }
}
