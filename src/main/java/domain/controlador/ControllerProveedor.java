package domain.controlador;

import domain.modelo.documentos.*;
import domain.modelo.producto.ProductoSeleccionable;
import domain.modelo.proveedores.Proveedor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerProveedor {
    List <Proveedor> proveedores ;
    int documentCounter = 1;

    private static final ControllerProveedor instance = new ControllerProveedor();
    public static ControllerProveedor getInstance(){return instance;}

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

    public void addFactura(int cuit, Boolean aprobacion, OrdenDeCompra ordenDeCompra, List detalle ){
        Proveedor p = this.getProveedorXcuit(cuit);
        Factura a = new Factura(++documentCounter,  aprobacion,  ordenDeCompra, detalle);
        a.calcularMonto();
        p.addFactura(a);
    }

    public void addProveedor(int cuit,String responsabilidadIVA, String razonSocial,
                             String nombreFantasia, String direccion, int telefono,
                             String correoElectronico, int nroIIBB, LocalDate inicioActividad,
                             int retencionImpuestos){
        Proveedor p = new Proveedor( cuit,  responsabilidadIVA,  razonSocial, nombreFantasia,  direccion,  telefono, correoElectronico,  nroIIBB,  inicioActividad,  retencionImpuestos);
        proveedores.add (p);
        mostrarProveedores();
    }

    public void addOrdenDePago(int cuit, int numDoc,float totalACancelar, float totalRetenciones, LocalDate fechaLimite){
        OrdenDePago op = new OrdenDePago(totalACancelar,totalRetenciones,fechaLimite);
        op.calcularMonto();
        for(Proveedor p: proveedores){
            if(p.getCuit() == cuit){
                p.addOrdenDePago(op);
                for(Factura f: p.getFacturas()){
                    if(f.getNumeroDocumento() == numDoc){
                        f.setOrdenDePago(op);
                        System.out.println("se asocio op a factura: " + f.getNumeroDocumento() + " con proveedor " + p.getNombreFantasia());
                    }
                }
            }
        }
    }

    public void addOrdenDeCompra(){
        OrdenDeCompra oc = new OrdenDeCompra();
    }


    public void imprimirfacturas( int cuit ){
        Proveedor p = getProveedorXcuit(cuit);
        List<Factura> facturas =  p.getFacturas();
        for (Factura f: facturas){
            System.out.println(f.getNumeroDocumento() + " " + f.getFecha());
            if (f.getDetalle() != null) {
                for (Item i : f.getDetalle()) {
                    System.out.println();
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


    // consultas generales

    private List<Documento> recopilarDocumentos( Proveedor p ){
        List documentos = new ArrayList<>();
        for (Factura factura: p.getFacturas()){
            documentos.add(factura);
        }
        for (NotaDeCredito nc: p.getNotasdecredito()){
            documentos.add(nc);
        }
        for (NotaDeDebito nd: p.getNotasdedebito()){
            documentos.add(nd);
        }
        for (OrdenDeCompra ordenCompra: p.getOrdenesdecompra()){
            documentos.add(ordenCompra);
        }
        for (OrdenDePago ordenPago: p.getOrdenesdepago()){
            documentos.add(ordenPago);
        }
        return documentos;
    }

    public int getFacturas(int cuit){
        Proveedor p = getProveedorXcuit(cuit);
        return p.getFacturas().size();
    }

    public List getFacturas(int cuit, LocalDate inicio, LocalDate fin ){
        Proveedor p = getProveedorXcuit(cuit);
        Map <String,Integer> facturasXproveedor  = new HashMap<>();
        System.out.println(inicio.toString() + " "  + fin.toString());
        if( inicio.isBefore(fin) && fin.isAfter(inicio)  && p != null || inicio.isEqual(fin) && p != null){
            List facturaProveedorPorDia = p.getFacturas(inicio,fin);
            facturasXproveedor.put(p.getNombreFantasia() +" qty:"  ,facturaProveedorPorDia.size());
            System.out.println(p.getNombreFantasia() +" qty:" + facturaProveedorPorDia.size());

        }
        return null;
    }

    public List getFacturas(LocalDate inicio, LocalDate fin){
        // map < Alias_Localdate , cantidad entre fechas >
        Map <String,Integer> facturasXproveedor  = new HashMap<>();
        System.out.println(inicio.toString() + " "  + fin.toString());
        if( inicio.isBefore(fin) && fin.isAfter(inicio) ||inicio.isEqual(fin)){
            for (Proveedor p: proveedores){
                List facturaProveedorPorDia = p.getFacturas(inicio,fin);
                facturasXproveedor.put(p.getNombreFantasia() +" qty:"  ,facturaProveedorPorDia.size());
                System.out.println(p.getNombreFantasia() +" qty:" + facturaProveedorPorDia.size());
            }
        }
        return null;
    }
}
