package domain.modelo.documentos;

import java.time.LocalDate;
import java.util.List;

public class OrdenDePago extends Documento{

    private double totalACancelar;
    private double totalRetenciones;
    private LocalDate fechaLimite;
    private List<Integer> facturasAsociadas;
    private Cheque cheque;

    public OrdenDePago(int numeroDocumento, List<Item> detalle, double totalACancelar, double totalRetenciones,
                       LocalDate fechaLimite, List<Integer> facturasAsociadas){
            this.facturasAsociadas = facturasAsociadas;
            this.totalRetenciones = totalRetenciones;
            this.totalACancelar = totalACancelar;
            super.inicializarFecha();
            this.fechaLimite = fechaLimite;
            this.numeroDocumento = numeroDocumento;
            this.detalle = detalle;
    }

    public void setCheque(Cheque cheque) {
        this.cheque = cheque;
    }

    public Cheque getCheque() {
        return cheque;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public double getTotalACancelar() {
        return totalACancelar;
    }

    public double getTotalRetenciones() {
        return totalRetenciones;
    }

    public List<Integer> getFacturasAsociadas() {
        return facturasAsociadas;
    }
}
