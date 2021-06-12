package domain.modelo.producto;

public class Producto {

    private String nombre;
    private Rubro rubro;
    private double iva;

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public void mostrarDetalles(){
        System.out.println("nombre: " + this.nombre +" Rubro:"+ this.rubro.getNombre() ) ;
    }
}
