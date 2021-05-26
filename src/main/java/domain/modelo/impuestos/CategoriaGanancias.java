package domain.modelo.impuestos;

public class CategoriaGanancias {
    private String regimenDeGanancias;
    private float minimoNoImponible;
    private float porcentajeRetencion;

    public CategoriaGanancias(String regimenDeGanancias) {
        this.regimenDeGanancias = regimenDeGanancias;
    }

    public String getRegimenDeGanancias() {
        return regimenDeGanancias;
    }

    public void setRegimenDeGanancias(String regimenDeGanancias) {
        this.regimenDeGanancias = regimenDeGanancias;
    }

    public float getMinimoNoImponible() {
        return minimoNoImponible;
    }

    public void setMinimoNoImponible(float minimoNoImponible) {
        this.minimoNoImponible = minimoNoImponible;
    }

    public float getPorcentajeRetencion() {
        return porcentajeRetencion;
    }

    public void setPorcentajeRetencion(float porcentajeRetencion) {
        this.porcentajeRetencion = porcentajeRetencion;
    }
}
