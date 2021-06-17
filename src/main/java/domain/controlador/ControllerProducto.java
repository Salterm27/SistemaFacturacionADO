package domain.controlador;

import domain.modelo.producto.*;
import domain.modelo.proveedores.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class ControllerProducto {
    List<Producto> productos = new ArrayList<>();
    List<ProductoSeleccionable> productoSeleccionables = new ArrayList<>();
    List<Rubro> rubros = new ArrayList<>();

    private static final ControllerProducto instance = new ControllerProducto();
    public static ControllerProducto getInstance(){return instance;}

    public Rubro buscarRubro (String nombre){
        for (Rubro r : rubros){
            if (r.getNombre() == nombre) {
                return r;
            }
        }
        return null;
    }

    public void addRubro (String nombre, double porcentajeIVA ){
        Rubro rubro = new Rubro(nombre, porcentajeIVA);
        rubros.add(rubro);
    }

    public void CrearProducto(String nombre){
        Producto producto = new Producto(nombre);
        productos.add(producto);
    }

    public Producto getProducto(String nombre){
        for (Producto p : productos){
            if(p.getNombre() == nombre){
                return p;
            }
        }
        return null;
    }

    public List<ProductoSeleccionable> getProductoSeleccionables() {
        return productoSeleccionables;
    }

    public void setProductoSeleccionables(List<ProductoSeleccionable> productoSeleccionables) {
        this.productoSeleccionables = productoSeleccionables;
    }

    public ProductoSeleccionable CrearProductoSeleccionable(float precioPorUnidad, int tipoDeUnidad, Producto producto , Proveedor proveedor){
        ProductoSeleccionable ps = new ProductoSeleccionable( precioPorUnidad,tipoDeUnidad, producto, proveedor);
        productoSeleccionables.add(ps);
        return ps;
    }

    public ProductoSeleccionable getProductoSeleccionable(String nombre, int cuit){
        for (ProductoSeleccionable ps : productoSeleccionables){
            if(ps.getProducto().getNombre() == nombre && ps.getProveedor().getCuit() == cuit ){
                return ps;
            }
        }
        return null;
    }

    public void AsociarProveedor(){
    }
    public void EliminarProducto(){
    }
    public void DesasociarProveedor(){
    }
    public void OpcionesProducto(){
    }

}
