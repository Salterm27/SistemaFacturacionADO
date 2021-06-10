package domain.modelo.documentos;

import domain.modelo.impuestos.ImpuestoAplicable;

public class NotaDeDebito extends Documento{

    private int numeroNotaDebito;
    private int NroFactura;
    private ImpuestoAplicable impuestoAplicable;

    public NotaDeDebito(int numeroNotaDebito, int nroFactura, ImpuestoAplicable impuestoAplicable) {
        this.numeroNotaDebito = numeroNotaDebito;
        NroFactura = nroFactura;
        this.impuestoAplicable = impuestoAplicable;
        super.inicializarFecha();
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
