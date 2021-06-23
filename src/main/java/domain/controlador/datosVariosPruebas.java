package domain.controlador;

import domain.modelo.documentos.Item;
import domain.modelo.producto.ProductoSeleccionable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class datosVariosPruebas {

    ControllerProducto cldrProducto;
    ControllerProveedor cldrProveedor;

    public datosVariosPruebas(){
        cldrProducto  = ControllerProducto.getInstance();
        cldrProveedor = ControllerProveedor.getInstance();
        dataRubros();
        dataProducto();
        dataProveedor();
        dataProductoSeleccionable();
    }
    private void dataRubros(){
        cldrProducto.crearRubro("Bazar");
        cldrProducto.crearRubro("Comestibles");
        cldrProducto.crearRubro("Electronica");
        cldrProducto.crearRubro("Electrodomesticos");
        cldrProducto.crearRubro("Inmuebles");
        cldrProducto.crearRubro("Limpieza");
        cldrProducto.crearRubro("Mobiliario");
        cldrProducto.crearRubro("Servicios");
    }
    private void dataProducto(){
        cldrProducto.CrearProducto("SmartPhone Samsung","Electronica");
        cldrProducto.CrearProducto("Licuadora Phillips","Electrodomesticos");
        cldrProducto.CrearProducto("Notebook HP","Electronica");
        cldrProducto.CrearProducto("Smart TV","Electronica");
        cldrProducto.CrearProducto("Pack 7 platos de vidrio","Bazar");
        cldrProducto.CrearProducto("Pack 3 tazas de porcelana","Bazar");
        cldrProducto.CrearProducto("Pack 6 Juegos de cubiertos","Bazar");
        cldrProducto.CrearProducto("Internet 4g 220Mbps","Servicios");
        cldrProducto.CrearProducto("Internet 5g 720Mbps","Servicios");
        cldrProducto.CrearProducto("Internet Fibra Optica 2000Mbps","Servicios");
    }

    private void dataProveedor(){
        cldrProveedor.addProveedor(161516161,"","Empresa uno S.A", "Empresa uno",
                "dire..",0000000,"xxxx@xxxx.com",5,
                LocalDate.of(2006,7,17),7);
        cldrProveedor.addProveedor(786578557,"","Empresa dos S.A", "Empresa dos",
                "dire..",0000000,"xxxx@xxxx.com",5,
                LocalDate.of(2005,8,15),8);
        cldrProveedor.addProveedor(785786444,"","Empresa tres S.A", "Empresa tres",
                "dire..",0000000,"xxxx@xxxx.com",5,
                LocalDate.of(2007,4,30),9);
        cldrProveedor.addProveedor(782656533,"","Empresa cuatro S.A", "Empresa cuatro",
                "dire..",0000000,"xxxx@xxxx.com",5,
                LocalDate.of(2020,6,16),10);
        cldrProveedor.addProveedor(872972611,"","Empresa cinco S.A", "Empresa cinco",
                "dire..",0000000,"xxxx@xxxx.com",5,
                LocalDate.of(2020,2,23),20);
        cldrProveedor.addProveedor(445242242,"","Empresa seis S.A", "Empresa seis",
                "dire..",0000000,"xxxx@xxxx.com",5,
                LocalDate.of(2013,1,13),10);
        cldrProveedor.addProveedor(887845533,"","Empresa siete S.A", "Empresa siete",
                "dire..",0000000,"xxxx@xxxx.com",5,
                LocalDate.of(2011,4,25),15);
        cldrProveedor.addProveedor(747472354,"","Empresa ocho S.A", "Empresa ocho",
                "dire..",0000000,"xxxx@xxxx.com",5,
                LocalDate.of(2010,1,12),10);


        //proveedor 1 no paga iva ni iibb
        cldrProveedor.getProveedorXcuit(161516161).getExcenciones().setExcencionIVA(LocalDate.of(2022,01,01));
        cldrProveedor.getProveedorXcuit(161516161).getExcenciones().setExcencionIIBB(LocalDate.of(2022,01,01));
        //proveedor 2 no paga iva
        cldrProveedor.getProveedorXcuit(786578557).getExcenciones().setExcencionIVA(LocalDate.of(2022,01,01));
        //proveedor 3 no paga IIBB
        cldrProveedor.getProveedorXcuit(785786444).getExcenciones().setExcencionIIBB(LocalDate.of(2022,01,01));

    }
    private void dataProductoSeleccionable(){
        // empresa 1, 2 y 3

        ProductoSeleccionable ps;

        ps = cldrProducto.CrearProductoSeleccionable(10000, "1",
                cldrProducto.getProducto("SmartPhone Samsung"),
                cldrProveedor.getProveedorXcuit(161516161),21);cldrProveedor.asociarProductoSeleccionable(161516161,ps);
        ps = cldrProducto.CrearProductoSeleccionable(15000, "1",
                cldrProducto.getProducto("SmartPhone Samsung"),
                cldrProveedor.getProveedorXcuit(786578557),21);cldrProveedor.asociarProductoSeleccionable(786578557,ps);
        ps = cldrProducto.CrearProductoSeleccionable(20000, "1",
                cldrProducto.getProducto("SmartPhone Samsung"),
                cldrProveedor.getProveedorXcuit(785786444),21);cldrProveedor.asociarProductoSeleccionable(785786444,ps);

        ps = cldrProducto.CrearProductoSeleccionable(7400, "1",
                cldrProducto.getProducto("Licuadora Phillips"),
                cldrProveedor.getProveedorXcuit(161516161),21); cldrProveedor.asociarProductoSeleccionable(161516161,ps);
        ps = cldrProducto.CrearProductoSeleccionable(8951, "1",
                cldrProducto.getProducto("Licuadora Phillips"),
                cldrProveedor.getProveedorXcuit(786578557),21); cldrProveedor.asociarProductoSeleccionable(786578557,ps);
        ps = cldrProducto.CrearProductoSeleccionable(2123, "1",
                cldrProducto.getProducto("Licuadora Phillips"),
                cldrProveedor.getProveedorXcuit(785786444),21); cldrProveedor.asociarProductoSeleccionable(785786444,ps);

        ps = cldrProducto.CrearProductoSeleccionable(67400, "1",
                cldrProducto.getProducto("Notebook HP"),
                cldrProveedor.getProveedorXcuit(161516161),21);cldrProveedor.asociarProductoSeleccionable(161516161,ps);
        ps = cldrProducto.CrearProductoSeleccionable(68951, "1",
                cldrProducto.getProducto("Notebook HP"),
                cldrProveedor.getProveedorXcuit(786578557),21); cldrProveedor.asociarProductoSeleccionable(786578557,ps);
        ps = cldrProducto.CrearProductoSeleccionable(52123, "1",
                cldrProducto.getProducto("Notebook HP"),
                cldrProveedor.getProveedorXcuit(785786444),21);cldrProveedor.asociarProductoSeleccionable(785786444,ps);

        ps = cldrProducto.CrearProductoSeleccionable(54544, "1",
                cldrProducto.getProducto("Smart TV"),
                cldrProveedor.getProveedorXcuit(161516161),21);cldrProveedor.asociarProductoSeleccionable(161516161,ps);
        ps = cldrProducto.CrearProductoSeleccionable(50231, "1",
                cldrProducto.getProducto("Smart TV"),
                cldrProveedor.getProveedorXcuit(786578557),21); cldrProveedor.asociarProductoSeleccionable(786578557,ps);
        ps = cldrProducto.CrearProductoSeleccionable(56985, "1",
                cldrProducto.getProducto("Smart TV"),
                cldrProveedor.getProveedorXcuit(785786444),21);cldrProveedor.asociarProductoSeleccionable(785786444,ps);

        // empresa 4,5,6

        ps = cldrProducto.CrearProductoSeleccionable(6510, "1",
                cldrProducto.getProducto("Pack 7 platos de vidrio"),
                cldrProveedor.getProveedorXcuit(782656533),21); cldrProveedor.asociarProductoSeleccionable(782656533,ps);
        ps = cldrProducto.CrearProductoSeleccionable(6000, "1",
                cldrProducto.getProducto("Pack 7 platos de vidrio"),
                cldrProveedor.getProveedorXcuit(872972611),21);cldrProveedor.asociarProductoSeleccionable(872972611,ps);
        ps = cldrProducto.CrearProductoSeleccionable(5000, "1",
                cldrProducto.getProducto("Pack 7 platos de vidrio"),
                cldrProveedor.getProveedorXcuit(445242242),21);cldrProveedor.asociarProductoSeleccionable(445242242,ps);

        ps = cldrProducto.CrearProductoSeleccionable(3000, "1",
                cldrProducto.getProducto("Pack 3 tazas de porcelana"),
                cldrProveedor.getProveedorXcuit(782656533),21);cldrProveedor.asociarProductoSeleccionable(782656533,ps);
        ps = cldrProducto.CrearProductoSeleccionable(2500, "1",
                cldrProducto.getProducto("Pack 3 tazas de porcelana"),
                cldrProveedor.getProveedorXcuit(872972611),21);cldrProveedor.asociarProductoSeleccionable(872972611,ps);
        ps = cldrProducto.CrearProductoSeleccionable(3540, "1",
                cldrProducto.getProducto("Pack 3 tazas de porcelana"),
                cldrProveedor.getProveedorXcuit(445242242),21);cldrProveedor.asociarProductoSeleccionable(445242242,ps);

        ps = cldrProducto.CrearProductoSeleccionable(1230, "1",
                cldrProducto.getProducto("Pack 6 Juegos de cubiertos"),
                cldrProveedor.getProveedorXcuit(782656533),21);cldrProveedor.asociarProductoSeleccionable(782656533,ps);
        ps = cldrProducto.CrearProductoSeleccionable(1200, "1",
                cldrProducto.getProducto("Pack 6 Juegos de cubiertos"),
                cldrProveedor.getProveedorXcuit(872972611),21);cldrProveedor.asociarProductoSeleccionable(872972611,ps);


        // empresa 7 y 8

        ps = cldrProducto.CrearProductoSeleccionable(1230, "1",
                cldrProducto.getProducto("Internet 4g 220Mbps"),
                cldrProveedor.getProveedorXcuit(887845533),21);cldrProveedor.asociarProductoSeleccionable(887845533,ps);
        ps = cldrProducto.CrearProductoSeleccionable(1200, "1",
                cldrProducto.getProducto("Internet 4g 220Mbps"),
                cldrProveedor.getProveedorXcuit(747472354),21);cldrProveedor.asociarProductoSeleccionable(747472354,ps);

        ps = cldrProducto.CrearProductoSeleccionable(1230, "1",
                cldrProducto.getProducto("Internet 5g 720Mbps"),
                cldrProveedor.getProveedorXcuit(887845533),21);cldrProveedor.asociarProductoSeleccionable(887845533,ps);
        ps =  cldrProducto.CrearProductoSeleccionable(1200, "1",
                cldrProducto.getProducto("Internet 5g 720Mbps"),
                cldrProveedor.getProveedorXcuit(747472354),21);cldrProveedor.asociarProductoSeleccionable(747472354,ps);


        ps =  cldrProducto.CrearProductoSeleccionable(1230, "1",
                cldrProducto.getProducto("Internet Fibra Optica 2000Mbps"),
                cldrProveedor.getProveedorXcuit(887845533),21);cldrProveedor.asociarProductoSeleccionable(887845533,ps);
        ps =  cldrProducto.CrearProductoSeleccionable(1200, "1",
                cldrProducto.getProducto("Internet Fibra Optica 2000Mbps"),
                cldrProveedor.getProveedorXcuit(747472354),21);cldrProveedor.asociarProductoSeleccionable(747472354,ps);

    }
}
