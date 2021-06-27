package domain.modelo.documentos;

import java.time.LocalDate;

public class Cheque {
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private String firmante;
    private Double importe;

    public Cheque(LocalDate fechaEmision, LocalDate fechaVencimiento, String firmante, Double importe) {
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.firmante = firmante;
        this.importe = importe;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getFirmante() {
        return firmante;
    }

    public Double getImporte() {
        return importe;
    }
}
