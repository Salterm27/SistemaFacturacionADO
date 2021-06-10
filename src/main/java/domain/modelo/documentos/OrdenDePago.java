package domain.modelo.documentos;

import java.time.LocalDate;

public class OrdenDePago extends Documento{
    private float totalACancelar;
    private float totalRetenciones;
    private LocalDate fechaLimite;

    public OrdenDePago( float totalACancelar, float totalRetenciones, LocalDate fechaLimite){
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

    public float getTotalACancelar() {
        return totalACancelar;
    }

    public float getTotalRetenciones() {
        return totalRetenciones;
    }
}
