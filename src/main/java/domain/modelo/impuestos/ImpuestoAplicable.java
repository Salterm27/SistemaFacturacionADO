package domain.modelo.impuestos;

public class ImpuestoAplicable {
    private double iva;
    private double IIBB;

    public ImpuestoAplicable() {
        this.iva = 0;
        this.IIBB = 0;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getIIBB() {
        return IIBB;
    }

    public void setIIBB(double IIBB) {
        this.IIBB = IIBB;
    }

    public ImpuestoAplicable(double iva, double IIBB) {
        this.iva = iva;
        this.IIBB = IIBB;
    }
}
