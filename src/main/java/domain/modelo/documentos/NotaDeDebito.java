package domain.modelo.documentos;

public class NotaDeDebito extends Documento{
    private String detalle;
    private int numeroNotaDebito;


    public NotaDeDebito(String detalle, int numeroNotaDebito){
        this.detalle = detalle;
        this.numeroNotaDebito = numeroNotaDebito;
    }
}
