package domain.modelo;

import domain.controlador.ControllerProducto;
import domain.controlador.ControllerProveedor;
import domain.controlador.datosVariosPruebas;
import domain.modelo.documentos.Item;
import domain.modelo.documentos.OrdenDeCompra;
import domain.modelo.producto.Producto;
import domain.modelo.producto.ProductoSeleccionable;
import domain.modelo.producto.Rubro;
import domain.modelo.proveedores.Proveedor;
import domain.vista.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {

    public static void main(String[] args) {

        ControllerProducto cldrProducto  = ControllerProducto.getInstance();
        ControllerProveedor cldrProveedor = ControllerProveedor.getInstance();
        datosVariosPruebas datos = new datosVariosPruebas();

        /*System.out.println("caso de prueba 1: se prueba crear un proveedor y añadirlo. Posteriormente se imprime por pantalla");
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
        cldrProveedor.addFactura( 12345678, true,  0, null);
        cldrProveedor.imprimirfacturas(12345678);
        cldrProveedor.addFactura( 11111111, true,  0, null);
        cldrProveedor.imprimirfacturas(11111111);


        System.out.println("Caso de prueba 3: Crear instancia de producto y asociar rubro");
        cldrProducto.CrearProducto("Television", "Electronica");
        Producto p = cldrProducto.getProducto("Television");
        Rubro r = cldrProducto.buscarRubro("Electronica");
        p.setRubro(r);
        cldrProducto.getProducto("Television").mostrarDetalles();



        System.out.println("Caso de prueba 4: Crear producto seleccionable, asociar a producto y a proveedor");
        String peso = null;
        ProductoSeleccionable ps = cldrProducto.CrearProductoSeleccionable( 1, peso,cldrProducto.getProducto("Television"), cldrProveedor.getProveedorXcuit(12345678), 21);
        cldrProveedor.asociarProductoSeleccionable(12345678, ps);
        ProductoSeleccionable ps2 = cldrProducto.CrearProductoSeleccionable( 5, peso,cldrProducto.getProducto("Television"), cldrProveedor.getProveedorXcuit(12345678),21);
        cldrProveedor.asociarProductoSeleccionable(12345678, ps2);



        System.out.println("Caso de prueba 5: Crear una factura y asociarle items");
        //creo el listado de items a agregar con su cantidad
        List<Item> detalle = new ArrayList();
        // le asigne el producto y la cantidad
        Item item = new Item(cldrProducto.getProductoSeleccionable("Television", 12345678),2);
        detalle.add(item);
        //ahora que tengo el hasmap, se lo paso como argumento
        cldrProveedor.addFactura( 12345678, true,  0, detalle);
        cldrProveedor.imprimirfacturas(12345678);

        System.out.println("Caso de prueba 6.2: Calcular ingresos brutos sobre una factura");


        System.out.println("Caso de prueba 6.3: Calcular IVA sobre una factura");

        System.out.println("Caso de prueba 8: Generar Orden de pago y asociar a factura");
        cldrProveedor.addOrdenDePago(12345678, 1, 11, 5, LocalDate.now());

        System.out.println("Caso de prueba 9: Calcular facturas emitidas por todos los proveedores en un periodo dado");
        cldrProveedor.getFacturas(LocalDate.now(),LocalDate.now());*/

        // GUIs
        MenuPrincipal GUImenu = new MenuPrincipal();
        GUImenu.start();

        /*
        AltaDocumento GUIdocumento = new AltaDocumento();
        GUIdocumento.start();

        AltaProveedor GUIproveedor = new AltaProveedor(cldrProveedor);
        GUIproveedor.start();



        ConsultaFactura cf = new ConsultaFactura();
        cf.start();

        ConsultaCuentaCorriente ccc = new ConsultaCuentaCorriente();
        ccc.start();*/
    }
}
