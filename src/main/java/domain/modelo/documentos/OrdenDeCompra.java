package domain.modelo.documentos;

import java.util.List;

public class OrdenDeCompra extends Documento{
    public OrdenDeCompra(int nroOC, List<Item> detalle ) {
        super.numeroDocumento = nroOC;
        super.inicializarFecha();
        super.detalle = detalle;
    }


}
