package domain.controlador;

import domain.modelo.documentos.*;
import domain.modelo.producto.ProductoSeleccionable;
import domain.modelo.proveedores.Proveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerProveedor {
    List <Proveedor> proveedores ;
    int documentCounter = 0;

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

    public void addFactura(int cuit, Boolean aprobacion, int idordenDeCompra, List detalle ){
        Proveedor p = this.getProveedorXcuit(cuit);
        OrdenDeCompra ordenDeCompra = null;
        for (OrdenDeCompra oc: p.getOrdenesdecompra()){ if(oc.getNumeroDocumento()==idordenDeCompra){ ordenDeCompra = oc; }}
        Factura a = new Factura(++documentCounter,  aprobacion,  ordenDeCompra, detalle);
        a.calcularMonto();
        p.addFactura(a);
    }

    public void addProveedor(int cuit,String responsabilidadIVA, String razonSocial,
                             String nombreFantasia, String direccion, int telefono,
                             String correoElectronico, double nroIIBB, LocalDate inicioActividad,
                             int retencionImpuestos){
        Proveedor p = new Proveedor( cuit,  responsabilidadIVA,  razonSocial,
                nombreFantasia,  direccion,  telefono, correoElectronico,
                nroIIBB,  inicioActividad,  retencionImpuestos);
        proveedores.add (p);
        mostrarProveedores();
    }

    public void addOrdenDePago(int cuit, double totalACancelar, double totalRetenciones, LocalDate fechaLimite, List<Integer> facturasAsociadas){
        OrdenDePago op = new OrdenDePago(totalACancelar,totalRetenciones,fechaLimite, facturasAsociadas);
        op.calcularMonto();
        for(Proveedor p: proveedores){
            if(p.getCuit() == cuit){
                p.addOrdenDePago(op);
            }
        }
    }

    public void addOrdenDeCompra(int cuit, List<Item>detalle){
        OrdenDeCompra oc = new OrdenDeCompra(++documentCounter,detalle);
        oc.calcularMonto();
        for(Proveedor p: proveedores){ if(p.getCuit() == cuit){p.addordenDeCompra(oc);} }
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

    public JComboBox comboProveedor(JComboBox x){
        for(Proveedor p: proveedores ){
            x.addItem(p.getNombreFantasia() + ", cuit:" +p.getCuit());
        }
        return x;
    }

    public DefaultTableModel RellenarDetalleFactura_aOrdenDePago(Proveedor proveedor, int id, DefaultTableModel model) {
        List<Item> detalle = null;

        for(Factura f: proveedor.getFacturas()){
            if(f.getNumeroDocumento() == id){
                detalle = f.getDetalle();
            }
        }
        for(Item item: detalle){
            model.addRow(new Object[]{
                    item.getPs().getProducto().getNombre(),
                    item.getPs().getPrecioPorUnidad(),
                    item.getCantidad(),
                    item.getCantidad() * item.getPs().getPrecioPorUnidad()
            });
        }
        return model;
    }

    public DefaultTableModel RellenarDetalleOrdenDeCompra_aFactura(Proveedor proveedor, int id, DefaultTableModel model) {
        List<Item> detalle = null;
        for(OrdenDeCompra oc: proveedor.getOrdenesdecompra()){
            if(oc.getNumeroDocumento() == id){
                detalle = oc.getDetalle();
            }
        }
        for(Item item: detalle){
            model.addRow(new Object[]{
                    item.getPs().getProducto().getNombre(),
                    item.getPs().getPrecioPorUnidad(),
                    item.getCantidad(),
                    item.getCantidad() * item.getPs().getPrecioPorUnidad()
            });
        }
        return model;
    }
}
