package domain.modelo.documentos;

import domain.modelo.impuestos.ImpuestoAplicable;

public class NotaDeCredito extends Documento{

    private int numeroNotaCredito;
    private int NroFactura;
    private ImpuestoAplicable impuestoAplicable;

    public NotaDeCredito(int numeroNotaCredito, int nroFactura, ImpuestoAplicable impuestoAplicable) {
        this.numeroNotaCredito = numeroNotaCredito;
        NroFactura = nroFactura;
        this.impuestoAplicable = impuestoAplicable;
        super.inicializarFecha();
    }

    public int getNumeroNotaCredito() {
        return numeroNotaCredito;
    }

    public int getNroFactura() {
        return NroFactura;
    }

    public ImpuestoAplicable getImpuestoAplicable() {
        return impuestoAplicable;
    }
}
