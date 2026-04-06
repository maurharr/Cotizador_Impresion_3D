package system.model;

import java.io.Serializable;

public class Amortizacion implements Serializable {
    private String amortizacion; // Español
    private double valor;

    public Amortizacion(String amortizacion) {
        this.amortizacion = amortizacion;
    }

    public String getDuracion() {
        return amortizacion;
    }

    public void setDuracion(String amortizacion) {
        this.amortizacion = amortizacion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    // NUEVO: devuelve según idioma
    public String getDuracion(String idioma) {
        if ("EN".equals(idioma)) {
            return switch (amortizacion) {
                case "6 MESES" -> "6 MONTHS";
                case "1 AÑO" -> "1 YEAR";
                case "2 AÑOS" -> "2 YEARS";
                case "SIN AMORTIZACION" -> "NO DEPRECIATION";
                default -> amortizacion;
            };
        }
        return amortizacion; // Español por defecto
    }

    @Override
    public String toString() {
        return amortizacion;
    }
}

