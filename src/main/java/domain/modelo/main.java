package domain.modelo;

import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;
import domain.modelo.documentos.OrdenDeCompra;
import domain.modelo.producto.Producto;
import domain.modelo.producto.ProductoSeleccionable;
import domain.modelo.producto.Rubro;
import domain.modelo.proveedores.Proveedor;
import domain.vista.AltaDocumento;
import domain.vista.AltaProveedor;
import domain.vista.ConsultasGenerales;
import domain.vista.MenuPrincipal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {

    public static void main(String[] args) {

        ControllerProducto cldrProducto  = ControllerProducto.getInstance();
        ControllerProveedor cldrProveedor = ControllerProveedor.getInstance();


        System.out.println("caso de prueba 1: se prueba crear un proveedor y añadirlo. Posteriormente se imprime por pantalla");
        cldrProveedor.addProveedor(12345678,  "resp_iva",  "Sebastian S.A",
                "sebas",  "Lima 774",  1156549788,
                "correoElectronico_proveedor",  12345678,
                LocalDate.of(2020,5,8),  15);

        cldrProveedor.addProveedor(11111111,  "resp_iva",  "Tomas S.A",
                "Tomas",  "Lima 774",  1156549788,
                "correoElectronico_proveedor",  12345678,
                LocalDate.of(2020,5,8),  15);

        cldrProveedor.mostrarProveedores();

        System.out.println("Caso de prueba 2: adjuntar factura a proveedor");
        cldrProveedor.addFactura( 12345678, true,  null, null);
        cldrProveedor.imprimirfacturas(12345678);
        cldrProveedor.addFactura( 11111111, true,  null, null);
        cldrProveedor.imprimirfacturas(11111111);


        System.out.println("Caso de prueba 3: Crear instancia de producto y asociar rubro");
        cldrProducto.CrearProducto("Television", "Electronica");
        Producto p = cldrProducto.getProducto("Television");
        Rubro r = cldrProducto.buscarRubro("Electronica");
        p.setRubro(r);
        cldrProducto.getProducto("Television").mostrarDetalles() ;



        System.out.println("Caso de prueba 4: Crear producto seleccionable, asociar a producto y a proveedor");
        String peso = null;
        ProductoSeleccionable ps = cldrProducto.CrearProductoSeleccionable( 10, peso,cldrProducto.getProducto("Television"), cldrProveedor.getProveedorXcuit(12345678), 21);
        cldrProveedor.asociarProductoSeleccionable(12345678, ps);
        ProductoSeleccionable ps2 = cldrProducto.CrearProductoSeleccionable( 50, peso,cldrProducto.getProducto("Television"), cldrProveedor.getProveedorXcuit(12345678),21);
        cldrProveedor.asociarProductoSeleccionable(12345678, ps2);



        System.out.println("Caso de prueba 5: Crear una factura y asociarle items");
        //creo el listado de items a agregar con su cantidad
        Map<ProductoSeleccionable, Integer > detalle = new HashMap<ProductoSeleccionable, Integer>();
        // le asigne el producto y la cantidad
        detalle.put(cldrProducto.getProductoSeleccionable("Television", 12345678),2);
        //ahora que tengo el hasmap, se lo paso como argumento
        cldrProveedor.addFactura( 12345678, true,  null, detalle);
        cldrProveedor.imprimirfacturas(12345678);

        System.out.println("Caso de prueba 6.2: Calcular ingresos brutos sobre una factura");


        System.out.println("Caso de prueba 6.3: Calcular IVA sobre una factura");

        System.out.println("Caso de prueba 8: Generar Orden de pago y asociar a factura");
        cldrProveedor.addOrdenDePago(12345678, 1, 11, 5, LocalDate.now());

        System.out.println("Caso de prueba 9: Calcular facturas emitidas por todos los proveedores en un periodo dado");
        cldrProveedor.getFacturas(LocalDate.now(),LocalDate.now());
        // GUIs
        //AltaDocumento GUIdocumento = new AltaDocumento(cldrProveedor, cldrProducto);
       // GUIdocumento.start();

        //AltaProveedor GUIproveedor = new AltaProveedor(cldrProveedor);
        //GUIproveedor.start();

        MenuPrincipal GUImenu = new MenuPrincipal();
        GUImenu.start();

        //ConsultasGenerales GUIconsultas = new ConsultasGenerales(cldrProveedor, cldrProducto);
        //GUIconsultas.start();
    }
}
