package domain.modelo.documentos;

import domain.modelo.impuestos.ImpuestoAplicable;

import java.util.List;

public class NotaDeCredito extends Documento{

    private int numeroNotaCredito;
    private int NroFactura;
    private ImpuestoAplicable impuestoAplicable;

    public NotaDeCredito(int numeroNotaCredito, int nroFactura, ImpuestoAplicable impuestoAplicable, List detalle) {
        this.numeroNotaCredito = numeroNotaCredito;
        NroFactura = nroFactura;
        this.impuestoAplicable = impuestoAplicable;
        super.inicializarFecha();
        super.detalle = detalle;

    }
    //OverLoad convertir monto a negativo
    public void calcularMonto(){
        super.calcularMonto();
        this.monto = monto - monto * 2;
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
