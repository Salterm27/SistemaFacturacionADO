package domain.modelo.documentos;

public class OrdenDePago {
    private float totalACancelar;
    private float totalRetenciones;

    public OrdenDePago( float totalACancelar, float totalRetenciones){
            this.totalRetenciones = totalRetenciones;
            this.totalACancelar = totalACancelar;
    }
}
