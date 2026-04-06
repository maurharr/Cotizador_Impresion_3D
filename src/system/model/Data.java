package system.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class Data {
    
    private static int contador = 0;
    private static String cantidad, precio, importe;
    
    
    public static Admin administrador = new Admin(null,null,null,null,null,null,null,null,null,null,null, "EN");
    
    private static ObservableList<Impresora> impresoras =  FXCollections.observableArrayList();
    private static ObservableList<Material> material =  FXCollections.observableArrayList();
    private static ObservableList<Amortizacion> amortizacion =  FXCollections.observableArrayList();
    private static ObservableList<Gama> gamaObjetivo =  FXCollections.observableArrayList();
    private static ObservableList<Cliente> clientes =  FXCollections.observableArrayList();
    private static ObservableList<String> entidades =  FXCollections.observableArrayList();
    private static ObservableList<Factura> facturas = FXCollections.observableArrayList();

    public static ObservableList<Impresora> getImpresoras() { return impresoras; }
    public static ObservableList<Material> getMaterial() { return material; }
    public static ObservableList<Amortizacion> getAmortizacion() { return amortizacion; }
    public static ObservableList<Gama> getGamaObjetivo() { return gamaObjetivo; }
    public static ObservableList<Cliente> getClientes() { return clientes; }
    public static ObservableList<String> getEntidades() { return entidades; }
    public static ObservableList<Factura> getFacturas() { return facturas; }    
    
    
    public double cAmortizacion;
    public double cMantenimiento;
    public double cElectricidad;
    public double cSupervision;
    public double cCostoOperativo;
    public double cGananciaMaquina;
    
    private static String precioOperario = "0";
    private static String precioEnergia = "0";
    private static String precioFilamento = "0";

    public static int getContador() {
        return contador;
    }
    
    public static String getCantidad() {
        return cantidad;
    }

    public static void setCantidad(String cantidadIngresada) {
        cantidad = cantidadIngresada;
    }

    public static String getPrecio() {
        return precio;
    }

    public static void setPrecio(String precioIngresado) {
        precio = precioIngresado;
    }

    public static String getImporte() {
        try {
            if (getCantidad() == null || getCantidad().isEmpty()) return "0.00";
            if (getPrecio() == null || getPrecio().isEmpty()) return "0.00";

            double cantidad = Double.parseDouble(getCantidad());
            double precio = Double.parseDouble(getPrecio().replace(",", "."));

            return String.format("%.2f", cantidad * precio);

        } catch (Exception e) {
            return "0.00";
        }
    }


    public static void setImporte(String importeIngresado) {
        importe = importeIngresado;
    }

    public static void sumarContador(){
        contador++;
    }
    
    
    public static String getPrecioOperario() {
        return precioOperario;
    }

    public static void setPrecioOperario(String precioOperario) {
        Data.precioOperario = precioOperario;
    }

    public static String getPrecioEnergia() {
        return precioEnergia;
    }

    public static void setPrecioEnergia(String precioEnergia) {
        Data.precioEnergia = precioEnergia;
    }

    public static String getPrecioFilamento() {
        return precioFilamento;
    }

    public static void setPrecioFilamento(String precioFilamento) {
        Data.precioFilamento = precioFilamento;
    }

    public static void cargarDatos() {
        material.addAll(
                new Material("PLA", 1, 1),
                new Material("PETG", 1.2, 1.1),
                new Material("ABS", 1.4, 1.25),
                new Material("ASA", 1.4, 1.25),
                new Material("Nylon", 1.6, 1.25),
                new Material("Material con CF", 1.8, 1.15)
        );

    
        amortizacion.addAll(
                new Amortizacion("6 MESES"),
                new Amortizacion("1 AÑO"),
                new Amortizacion("2 AÑOS"),
                new Amortizacion("SIN AMORTIZACION")
        );

    
        gamaObjetivo.addAll(
                new Gama("BAJA", 200),
                new Gama("MEDIA", 300),
                new Gama("ALTA", 400)
        );
        
        entidades.addAll("Persona", "Empresa");
    }
    
    
    
    
    // CALCULOS DE AMORTIZACION
    
    public static double calcularValorAmortizacion(Impresora impresora){
        double numero = Double.parseDouble(impresora.getValor());
        if(null==impresora.getAmortizacion().getDuracion()){
            return 0;
        }else return switch (impresora.getAmortizacion().getDuracion()) {
            case "6 MESES" -> numero*2;
            case "1 AÑO" -> (numero*2)/2;
            case "2 AÑOS" -> ((numero*2)/2)/2;
            default -> 0;
        };
    }
    
    
    
    
    
    
    // CALCULOS AUTOMATICOS
    
    
    // 0 - AMORTIZACION
    
    public static double calcularAmortizacion(double valorAmortizacion, double horasUso){
        double divisor =  315*horasUso;
        return valorAmortizacion/divisor;

    }
    
    
    // 1 - MANTENIMIENTO
    
    public static double calcularMantenimiento(double valor, double factorDesgaste){
        return ((valor*0.1)/1000)*factorDesgaste;
    }
    
    
    // 2 - ELECTRICIDAD
    
    public static double calcularElectricidad(double consumoPromedio, double precioEnergia, double factorConsumoElectrico){
        return ((consumoPromedio/1000)*precioEnergia)*factorConsumoElectrico;
    }
    
    
    // 3 - SUPERVISION
    public static double calcularSupervision(double precioOperario, double tasaFallos){
        return (precioOperario*0.01)*(1+(tasaFallos*0.01));
    }
    
    
    // 4 - COSTO OPERATIVO
    public static double calcularCostoOperativo(double cAmortizacion, double cMantenimiento, double cElectricidad, double cSupervision){
        return cAmortizacion + cElectricidad + cMantenimiento + cSupervision; 
    }
    
    // 5 - GANANCIA MAQUINA
    public static double calcularGananciaMaquina(double mantenimeinto, double electricidad, double supervision, double factorObjetivo){
        return ((mantenimeinto+electricidad+supervision)*(factorObjetivo))/100;
    }
    
    public static void cargar() {      
            try {
                File archivo = new File(getRutaData());
                if (!archivo.exists()) return; // Si no hay archivo aún, no hacemos nada

                FileInputStream fis = new FileInputStream(archivo);
                ObjectInputStream ois = new ObjectInputStream(fis); 

                clientes = FXCollections.observableArrayList((ArrayList<Cliente>) ois.readObject());
                impresoras = FXCollections.observableArrayList((ArrayList<Impresora>) ois.readObject());
                administrador = (Admin) ois.readObject();
                precioOperario = (String) ois.readObject();
                precioEnergia = (String) ois.readObject();
                precioFilamento = (String) ois.readObject();
                contador = (int) ois.readObject();
                facturas = FXCollections.observableArrayList((ArrayList<Factura>) ois.readObject());

                ois.close();
            } catch (Exception ex) {
                System.out.println("Error al cargar: " + ex.getMessage());
            }
        }

        public static void guardar() {
             try {
                 FileOutputStream fos = new FileOutputStream(getRutaData()); 
                 ObjectOutputStream oos = new ObjectOutputStream(fos);

                 oos.writeObject(new ArrayList<>(clientes));
                 oos.writeObject(new ArrayList<>(impresoras));
                 oos.writeObject(administrador); 
                 oos.writeObject(precioOperario);
                 oos.writeObject(precioEnergia);
                 oos.writeObject(precioFilamento);
                 oos.writeObject(contador);
                 oos.writeObject(new ArrayList<>(facturas));

                 oos.close();
             } catch (IOException ex) {
                 System.out.println("Error al guardar: " + ex.getMessage());
             }
        }
   
    // Definimos la ruta dinámica en la carpeta de usuario
    private static String getRutaData() {
        String appData = System.getenv("APPDATA"); // C:\Users\Nombre\AppData\Roaming
        File carpetaApp = new File(appData, "SistemaImpresoras");
        
        if (!carpetaApp.exists()) {
            carpetaApp.mkdirs(); // Crea la carpeta si no existe la primera vez
        }
        
        return new File(carpetaApp, "data.ser").getAbsolutePath();
    }    
}
