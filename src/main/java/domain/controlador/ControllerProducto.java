package domain.controlador;

import domain.modelo.producto.*;

public class ControllerProducto {


    public void CrearProducto(String nombre, float precioPorUnidad, int tipoDeUnidad){
        Producto producto = new Producto(nombre);
        ProductoSeleccionable ps = new ProductoSeleccionable( precioPorUnidad,tipoDeUnidad, producto);

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
