package domain.controlador;

import domain.modelo.documentos.Factura;
import domain.modelo.documentos.OrdenDeCompra;
import domain.modelo.producto.ProductoSeleccionable;
import domain.modelo.proveedores.Proveedor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControllerProveedor {
    List <Proveedor> proveedores ;
    int ItemizerFacturas = 0;


    public ControllerProveedor() {
        proveedores = new ArrayList<>();
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public Proveedor getProveedorXcuit(int cuit){
        for (Proveedor p: proveedores) {
            if (p.getCuit() == cuit){
                return p;
            }
        }
        return null;
    }

    public void addFactura(int cuit, Boolean aprobacion, OrdenDeCompra ordenDeCompra, Map<ProductoSeleccionable, Integer > detalle ){

        Proveedor p = this.getProveedorXcuit(cuit);
        Factura a = new Factura(++ItemizerFacturas,  aprobacion,  ordenDeCompra, detalle);
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
            //imprimir si el mapa esta ok, se puede comentar on continue
            if (f.getDetalle() != null) {
                for (Map.Entry<ProductoSeleccionable, Integer> entry : f.getDetalle().entrySet()) {
                    System.out.println("\t" + entry.getKey().getProducto().getNombre() + " " + entry.getValue().toString());
                }
            }
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
