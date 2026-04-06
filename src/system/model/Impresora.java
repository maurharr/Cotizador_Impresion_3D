package system.model;

import java.io.Serializable;


public class Impresora implements Serializable{
    private String modelo, valor, consumo, tasaFallos, horasUso;
    private Amortizacion amortizacion;
    private Gama gamaObjetivo;
    
    public Impresora(String modelo, String valor, String consumo, String tasaFallos, String horasUso, Amortizacion amortizacion, Gama gamaObjetivo) {
        this.modelo = modelo;
        this.valor = valor;
        this.consumo = consumo;
        this.tasaFallos = tasaFallos;
        this.horasUso = horasUso;
        this.amortizacion = amortizacion;
        this.gamaObjetivo = gamaObjetivo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public String getTasaFallos() {
        return tasaFallos;
    }

    public void setTasaFallos(String tasaFallos) {
        this.tasaFallos = tasaFallos;
    }

    public String getHorasUso() {
        return horasUso;
    }

    public void setHorasUso(String horasUso) {
        this.horasUso = horasUso;
    }

    public Amortizacion getAmortizacion() {
        return amortizacion;
    }


    public Gama getGamaObjetivo() {
        return gamaObjetivo;
    }

    @Override
    public String toString() {
        return modelo;
    }


    
    
}
