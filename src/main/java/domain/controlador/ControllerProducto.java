package domain.controlador;

import domain.modelo.producto.*;

import java.util.ArrayList;
import java.util.List;

public class ControllerProducto {
    List<Producto> productos = new ArrayList<>();
    List<ProductoSeleccionable> productoSeleccionables = new ArrayList<>();

    public void CrearProducto(String nombre, float precioPorUnidad, int tipoDeUnidad){
        Producto producto = new Producto(nombre);
        productos.add(producto);
        ProductoSeleccionable ps = new ProductoSeleccionable( precioPorUnidad,tipoDeUnidad, producto);
        productoSeleccionables.add(ps);
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
