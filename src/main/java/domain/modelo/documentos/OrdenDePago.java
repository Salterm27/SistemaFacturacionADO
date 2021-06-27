package domain.modelo.documentos;

import java.time.LocalDate;
import java.util.List;

public class OrdenDePago extends Documento{
    private double totalACancelar;
    private double totalRetenciones;
    private LocalDate fechaLimite;
    private List<Integer> facturasAsociadas;
    public OrdenDePago( double totalACancelar, double totalRetenciones, LocalDate fechaLimite, List<Integer> facturasAsociadas ){
            this.facturasAsociadas = facturasAsociadas;
            this.totalRetenciones = totalRetenciones;
            this.totalACancelar = totalACancelar;
            super.inicializarFecha();
            this.fechaLimite = fechaLimite;
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
}
