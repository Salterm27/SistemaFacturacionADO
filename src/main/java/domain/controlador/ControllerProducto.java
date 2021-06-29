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

    public List<Rubro> getRubros() {
        return rubros;
    }

    public Rubro crearRubro (String nombre ){
        Rubro rubro = new Rubro(nombre);
        rubros.add(rubro);
        return rubro;
    }


    public boolean CrearProducto(String nombre, String rubro){
        if(getProducto(nombre)==null){
            Producto producto = new Producto(nombre);
            producto.setRubro(getRubro(rubro));
            productos.add(producto);
            System.out.println("PRODUCTO:"+producto.getNombre()+ " " +"RUBRO:"+ producto.getRubro().getNombre());
            return true;
        }
        return false;
    }

    private Rubro getRubro(String rubro) {
        for(Rubro r : rubros){
            if (r.getNombre() == rubro){
                return r;
            }
        }
        return null;
    }

    public Producto getProducto(String nombre){
        for (Producto p : productos){
            if(p.getNombre() == nombre){
                return p;
            }
        }
        return null;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<ProductoSeleccionable> getProductoSeleccionables() {
        return productoSeleccionables;
    }

    public void setProductoSeleccionables(List<ProductoSeleccionable> productoSeleccionables) {
        this.productoSeleccionables = productoSeleccionables;
    }

    public ProductoSeleccionable CrearProductoSeleccionable(float precioPorUnidad, String tipoDeUnidad, Producto producto , Proveedor proveedor, float iva){
        ProductoSeleccionable ps = getProductoSeleccionable(producto.getNombre(), proveedor.getCuit());
        if(ps==null){
            ps = new ProductoSeleccionable(precioPorUnidad,tipoDeUnidad, producto, proveedor);
            ps.getProducto().setIva( iva );
            productoSeleccionables.add(ps);
            return ps;
        }
        else{
            ps.setPrecioPorUnidad(precioPorUnidad);
            ps.setTipoDeUnidad(tipoDeUnidad);
        }
        return null;
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
