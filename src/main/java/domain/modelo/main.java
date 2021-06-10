package domain.modelo;

import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;
import domain.modelo.documentos.OrdenDeCompra;
import domain.modelo.producto.Producto;
import domain.modelo.producto.Rubro;
import domain.modelo.proveedores.Proveedor;

public class main {
    public static void main(String[] args) {
        ControllerProveedor crldProveedor = new ControllerProveedor();
        ControllerProducto crldProducto = new ControllerProducto();

        System.out.println("caso de prueba 1: se prueba crear un proveedor y añadirlo. Posteriormente se imprime por pantalla");
        crldProveedor.addProveedor(12345678, "Sebastian S.A");
        crldProveedor.addProveedor(97161561, "Marco S.A");
        crldProveedor.mostrarProveedores();

        System.out.println("Caso de prueba 2: adjuntar factura a proveedor");
        crldProveedor.addFactura( 12345678, 1,  true,  null);
        crldProveedor.imprimirfacturas(12345678);

        System.out.println("Caso de prueba 3: Crear instancia de producto y asociar rubro");
        crldProducto.CrearProducto("Television");
        crldProducto.addRubro ("Electronica", 10.5);
        Producto p = crldProducto.getProducto("Television");
        Rubro r = crldProducto.buscarRubro("Electronica");
        p.setRubro(r);
        crldProducto.getProducto("Television").mostrarDetalles() ;

        System.out.println("Caso de prueba 4: Crear producto seleccionable, asociar a producto y a proveedor");
        crldProducto.CrearProductoSeleccionable( 10, 1,crldProducto.getProducto("Television"), crldProveedor.getProveedorXcuit(12345678));



    }
}
