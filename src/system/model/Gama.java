package system.model;

import java.io.Serializable;

public class Gama implements Serializable {
    private String gamaObjetivo; // Español
    private double factor;

    public Gama(String gamaObjetivo, double factor) {
        this.gamaObjetivo = gamaObjetivo;
        this.factor = factor;
    }

    public String getGamaObjetivo() {
        return gamaObjetivo;
    }

    public void setGamaObjetivo(String gamaObjetivo) {
        this.gamaObjetivo = gamaObjetivo;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    // NUEVO: devuelve según idioma
    public String getGamaObjetivo(String idioma) {
        if ("EN".equals(idioma)) {
            return switch (gamaObjetivo) {
                case "BAJA" -> "LOW";
                case "MEDIA" -> "MEDIUM";
                case "ALTA" -> "HIGH";
                default -> gamaObjetivo;
            };
        }
        return gamaObjetivo;
    }

    @Override
    public String toString() {
        return gamaObjetivo;
    }
}
