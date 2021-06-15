package domain.modelo.proveedores;

import domain.modelo.documentos.*;
import domain.modelo.producto.ProductoSeleccionable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Proveedor {

    private int cuit;
    private String responsabilidadIVA;
    private String razonSocial;
    private String nombreFantasia;
    private String direccion;
    private int telefono;
    private String correoElectronico;
    private int nroIIBB;
    private LocalDate inicioActividad;
    private int retencionImpuestos;

    private List<Factura> facturas;
    private List<NotaDeCredito> notasdecredito;
    private List<NotaDeDebito> notasdedebito;
    private List<OrdenDePago> ordenesdepago;
    private List<OrdenDeCompra> ordenesdecompra;

    private List<ProductoSeleccionable> productosSeleccionables;

    public Proveedor(int cuit, String responsabilidadIVA, String razonSocial,
                     String nombreFantasia, String direccion, int telefono,
                     String correoElectronico, int nroIIBB, LocalDate inicioActividad,
                     int retencionImpuestos) {

        this.cuit = cuit;
        this.responsabilidadIVA = responsabilidadIVA;
        this.razonSocial = razonSocial;
        this.nombreFantasia = nombreFantasia;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.nroIIBB = nroIIBB;
        this.inicioActividad = inicioActividad;
        this.retencionImpuestos = retencionImpuestos;

        this.notasdecredito = new ArrayList<>();
        this.notasdedebito = new ArrayList<>();
        this.ordenesdepago = new ArrayList<>();
        this.ordenesdecompra = new ArrayList<>();
        this.facturas = new ArrayList<Factura>();
        this.productosSeleccionables = new ArrayList<>();
    }

    public List<ProductoSeleccionable> getProductosSeleccionables() {
        return productosSeleccionables;
    }

    public void asociarProductoSeleccionable(ProductoSeleccionable ps){
        productosSeleccionables.add(ps);
    }

    public void setProductosSeleccionables(List<ProductoSeleccionable> productosSeleccionables) {
        this.productosSeleccionables = productosSeleccionables;
    }

    public void addFactura(Factura f){this.facturas.add(f);}

    public int getCuit() {
        return cuit;
    }

    public String getResponsabilidadIVA() {
        return responsabilidadIVA;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public int getNroIIBB() {
        return nroIIBB;
    }

    public LocalDate getInicioActividad() {
        return inicioActividad;
    }

    public int getRetencionImpuestos() {
        return retencionImpuestos;
    }

    public void setCuit(int cuit) {
        this.cuit = cuit;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public float balanceProveedor(){
        return 0;
    }

    public List<Documento> getDocumentosImpagos(){
        return null; // Iterar sobre documentos y delvolver los que esten en false.
    }

    public List<Factura> getFacturasXdia(LocalDate dia){
        return facturas;
    }

    public List<Factura> getFacturas() {
        return this.facturas;
    }

    public List<Factura> getFacturas(LocalDate desde, LocalDate hasta) {
        List<Factura> facturasxfecha = new ArrayList<>();
        for (Factura f : facturas){
            if( (f.getFecha().isBefore(hasta) || f.getFecha().isEqual(hasta)) &&
                    f.getFecha().isAfter(desde) || f.getFecha().isEqual(hasta))
            {
                facturasxfecha.add(f);
            }
        }
        return facturasxfecha;
    }

    public List<NotaDeCredito> getNotasdecredito() {
        return notasdecredito;
    }

    public List<NotaDeDebito> getNotasdedebito() {
        return notasdedebito;
    }

    public List<OrdenDePago> getOrdenesdepago() {
        return ordenesdepago;
    }

    public List<OrdenDeCompra> getOrdenesdecompra() {
        return ordenesdecompra;
    }

    public void addOrdenDePago(OrdenDePago op) {
        ordenesdepago.add(op);
    }
}
