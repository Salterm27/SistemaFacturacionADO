package domain.modelo.producto;

public class Rubro {

    private String nombre;
    private double porcentajeIVA;

    public Rubro(String nombre) {
        this.nombre = nombre; this.porcentajeIVA = porcentajeIVA;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPorcentajeIVA() {
        return porcentajeIVA;
    }

    public void setPorcentajeIVA(float porcentajeIVA) {
        this.porcentajeIVA = porcentajeIVA;
    }
}
