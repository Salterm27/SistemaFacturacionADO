package domain.modelo;

import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;
import domain.modelo.documentos.OrdenDeCompra;
import domain.modelo.producto.Producto;
import domain.modelo.producto.ProductoSeleccionable;
import domain.modelo.producto.Rubro;
import domain.modelo.proveedores.Proveedor;

import java.time.LocalDate;

public class main {
    public static void main(String[] args) {
        ControllerProveedor cldrProveedor = new ControllerProveedor();
        ControllerProducto cldrProducto = new ControllerProducto();


        System.out.println("caso de prueba 1: se prueba crear un proveedor y añadirlo. Posteriormente se imprime por pantalla");

        cldrProveedor.addProveedor(12345678,  "resp_iva",  "Sebastian S.A",
                "sebas",  "Lima 774",  1156549788,
                "correoElectronico_proveedor",  12345678,
                LocalDate.of(2020,5,8),  15);

        cldrProveedor.mostrarProveedores();

        System.out.println("Caso de prueba 2: adjuntar factura a proveedor");
        cldrProveedor.addFactura( 12345678, 1,  true,  null);
        cldrProveedor.imprimirfacturas(12345678);

        System.out.println("Caso de prueba 3: Crear instancia de producto y asociar rubro");
        cldrProducto.CrearProducto("Television");
        cldrProducto.addRubro ("Electronica", 10.5);
        Producto p = cldrProducto.getProducto("Television");
        Rubro r = cldrProducto.buscarRubro("Electronica");
        p.setRubro(r);
        cldrProducto.getProducto("Television").mostrarDetalles() ;

        System.out.println("Caso de prueba 4: Crear producto seleccionable, asociar a producto y a proveedor");
        ProductoSeleccionable ps = cldrProducto.CrearProductoSeleccionable( 10, 1,cldrProducto.getProducto("Television"), cldrProveedor.getProveedorXcuit(12345678));
        cldrProveedor.asociarProductoSeleccionable(12345678, ps);


    }
}
