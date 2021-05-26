package domain.modelo.documentos;

public class OrdenDeCompra extends Documento{

    private Integer ordenDeCompraID;

    public OrdenDeCompra(Integer ordenDeCompraID) {
        this.ordenDeCompraID = ordenDeCompraID;
    }

    public Integer getOrdenDeCompraID() {
        return ordenDeCompraID;
    }

    public void setOrdenDeCompraID(Integer ordenDeCompraID) {
        this.ordenDeCompraID = ordenDeCompraID;
    }
}
