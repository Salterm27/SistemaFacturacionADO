package domain.modelo.documentos;

import domain.modelo.impuestos.ImpuestoAplicable;

import java.util.List;

public class NotaDeDebito extends Documento{

    private int numeroNotaDebito;
    private int NroFactura;
    private ImpuestoAplicable impuestoAplicable;

    public NotaDeDebito(int numeroNotaDebito, int nroFactura, ImpuestoAplicable impuestoAplicable, List detalle) {
        this.numeroNotaDebito = numeroNotaDebito;
        NroFactura = nroFactura;
        this.impuestoAplicable = impuestoAplicable;
        super.inicializarFecha();
        super.detalle =detalle;
    }

    public int getNumeroNotaDebito() {
        return numeroNotaDebito;
    }

    public int getNroFactura() {
        return NroFactura;
    }

    public ImpuestoAplicable getImpuestoAplicable() {
        return impuestoAplicable;
    }
}
