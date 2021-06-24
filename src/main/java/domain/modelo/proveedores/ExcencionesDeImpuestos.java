package domain.modelo.proveedores;


import java.time.LocalDate;

public class ExcencionesDeImpuestos {
    LocalDate excencionIVA;
    LocalDate excencionIIBB;

    public ExcencionesDeImpuestos() {
        excencionIVA = LocalDate.of(1900,1,1);
        excencionIIBB = LocalDate.of(1900,1,1);
    }

    public LocalDate getExcencionIVA() {
        return excencionIVA;
    }

    public void setExcencionIVA(LocalDate excencionIVA) {
        this.excencionIVA = excencionIVA;
    }

    public LocalDate getExcencionIIBB() {
        return excencionIIBB;
    }

    public void setExcencionIIBB(LocalDate excencionIIBB) {
        this.excencionIIBB = excencionIIBB;
    }

    public boolean esExcentoIva(){
        return LocalDate.now().isBefore(excencionIVA);
    }

    public boolean esExcentoIIBB(){
        return LocalDate.now().isBefore(excencionIIBB);
    }
}
