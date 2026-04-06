package system.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Factura implements Serializable {

    private Cliente cliente;
    private List<ItemFactura> items;
    private String numero;
    private double subtotal;
    private double impuesto;
    private double impuestoSeteado;
    private double total;
    private String nota;
    private LocalDate fecha;

    public LocalDate getFecha() {return fecha;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

    public String getNumero() {return numero;}
    public void setNumero(String numero) {this.numero = numero;}

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<ItemFactura> getItems() { return items; }
    public void setItems(List<ItemFactura> items) { this.items = items; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    
    public double getImpuestoSeteado() { return impuestoSeteado; }
    public void setImpuestoSeteado(double impuestoSeteado) { this.impuestoSeteado = impuestoSeteado; }    

    public double getImpuesto() { return impuesto; }
    public void setImpuesto(double impuesto) { this.impuesto = impuesto; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getNota() { return nota; }
    public void setNota(String nota) { this.nota = nota; }
}
