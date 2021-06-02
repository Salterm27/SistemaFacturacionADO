package domain.modelo.documentos;

public class NotaDeCredito extends Documento{
    private String detalle;
    private int numeroNotaCredito;


    public NotaDeCredito(String detalle,int numeroNotaCredito){
        this.detalle = detalle;
        this.numeroNotaCredito = numeroNotaCredito;
    }
}
