package system.view;

import java.util.Arrays;
import java.util.Optional;
import system.controller.Controlador;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import system.model.Data;

public class MenuView extends Controlador{
    private VBox root;
    private Button btnIdioma, btnPresupuesto, btnFacturas, btnImpresoras, btnClientes, btnConfig;
    private Label titulo;
    private Stage stage;
    
    public MenuView(Stage stage){
        this.stage = stage;
        root = new VBox(80);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 100%, #A9D7F5, #FFFFFF);");
        stage.setResizable(false);

        
        DropShadow ds = new DropShadow();
        ds.setOffsetX(0);
        ds.setOffsetY(0);
        ds.setRadius(10);
        ds.setSpread(0.3);
        ds.setColor(Color.color(0, 0, 0, 0.4)); // negro semi-transparente
        
        titulo = new Label("");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        titulo.setStyle("-fx-font-smoothing-type: gray;");
        
        Label barra1 = new Label("");
        barra1.setPrefSize(1000,100);  
        barra1.setStyle("-fx-background-color: #FFFFFF;");
        barra1.setEffect(ds);
        
        Label barra2 = new Label("");
        barra2.setPrefSize(1000,100);  
        barra2.setStyle("-fx-background-color: #FFFFFF;");
        barra2.setEffect(ds);    
        
        
        String baseStyle =
            "-fx-font-size: 17px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10 10;" +
            "-fx-background-radius: 15;" +
            "-fx-border-radius: 15;" +
            "-fx-cursor: hand;";        
        
        btnIdioma = new Button("");
        btnIdioma.setPrefSize(50, 50);
        btnIdioma.setStyle(baseStyle +"-fx-background-color: #FFFFFF;" +"-fx-border-color: #a0a0a0;" +"-fx-text-fill: #333;");
        btnIdioma.setTextAlignment(TextAlignment.CENTER); 

        btnIdioma.setOnAction(e -> mostrarSelectorIdioma());

        
        btnPresupuesto = new Button("");
        btnPresupuesto.setPrefSize(130, 160);
        btnPresupuesto.setStyle(baseStyle +"-fx-background-color: linear-gradient(#4a77b8, #2f5f9e);" +"-fx-border-color: #244a7c;" +"-fx-text-fill: white;");
        btnPresupuesto.setEffect(ds);
        btnPresupuesto.setTextAlignment(TextAlignment.CENTER); 
        
        
        btnPresupuesto.setOnMouseEntered(e ->
            btnPresupuesto.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#5a8bd8, #3f6fb0);" +
                "-fx-border-color: #244a7c;" +
                "-fx-text-fill: white;"
        ));

        btnPresupuesto.setOnMouseExited(e ->
            btnPresupuesto.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#4a77b8, #2f5f9e);" +
                "-fx-border-color: #244a7c;" +
                "-fx-text-fill: white;"
        ));        
        
        
        
        btnFacturas = new Button("");
        btnFacturas.setPrefSize(130, 160);
        btnFacturas.setStyle(baseStyle +"-fx-background-color: linear-gradient(#7ecb5a, #5da93c);" +"-fx-border-color: #4a8d30;" +"-fx-text-fill: white;");
        btnFacturas.setEffect(ds);
        btnFacturas.setTextAlignment(TextAlignment.CENTER); 
        
        
        btnFacturas.setOnMouseEntered(e ->
            btnFacturas.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#8fdb6a, #6db94c);" +
                "-fx-border-color: #3f7c28;" +
                "-fx-text-fill: white;"
        ));

        btnFacturas.setOnMouseExited(e ->
            btnFacturas.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#7ecb5a, #5da93c);" +
                "-fx-border-color: #4a8d30;" +
                "-fx-text-fill: white;"
        ));
        
        btnClientes = new Button("");
        btnClientes.setPrefSize(130, 160);
        btnClientes.setStyle(baseStyle + "-fx-background-color: linear-gradient(#FFA500, #FF8C00);" + "-fx-border-color: #CC7000;" + "-fx-text-fill: white;");
        btnClientes.setEffect(ds);
        btnClientes.setTextAlignment(TextAlignment.CENTER); 

        btnClientes.setOnMouseEntered(e ->
            btnClientes.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#FFB733, #FF9C33);" +  // naranja más brillante al pasar el mouse
                "-fx-border-color: #CC7000;" +
                "-fx-text-fill: white;"
            )
        );

        btnClientes.setOnMouseExited(e ->
            btnClientes.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#FFA500, #FF8C00);" +
                "-fx-border-color: #CC7000;" +
                "-fx-text-fill: white;"
            )
        );

        btnImpresoras = new Button("");
        btnImpresoras.setPrefSize(130, 160);
        btnImpresoras.setStyle(baseStyle +"-fx-background-color: linear-gradient(#4a77b8, #2f5f9e);" +"-fx-border-color: #244a7c;" +"-fx-text-fill: white;");
        btnImpresoras.setEffect(ds);        
        btnImpresoras.setTextAlignment(TextAlignment.CENTER);        
        
        btnImpresoras.setOnMouseEntered(e ->
            btnImpresoras.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#5a8bd8, #3f6fb0);" +
                "-fx-border-color: #244a7c;" +
                "-fx-text-fill: white;"
        ));

        btnImpresoras.setOnMouseExited(e ->
            btnImpresoras.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#4a77b8, #2f5f9e);" +
                "-fx-border-color: #244a7c;" +
                "-fx-text-fill: white;"
        ));  
        
        
        
        
        btnConfig = new Button("");
        btnConfig.setPrefSize(130, 160);
        btnConfig.setStyle(baseStyle +"-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +"-fx-border-color: #a0a0a0;" +"-fx-text-fill: #333;");
        btnConfig.setEffect(ds);
        btnConfig.setTextAlignment(TextAlignment.CENTER); 
        
        btnConfig.setOnMouseEntered(e ->
            btnConfig.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#f0f0f0, #dcdcdc);" +
                "-fx-border-color: #909090;" +
                "-fx-text-fill: #222;"
        ));

        btnConfig.setOnMouseExited(e ->
            btnConfig.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +
                "-fx-border-color: #a0a0a0;" +
                "-fx-text-fill: #333;"
        ));   
        

        if(Data.administrador.getIdioma().equals("ES")) {
            stage.setTitle("Cotizador de impresión 3D");
        } else {
            stage.setTitle("3D Printing Quote Generator");
        }
        
        btnPresupuesto.setOnAction(e -> {

            if (Data.administrador.getNombre() == null &&
                Data.administrador.getApellido() == null &&
                Data.administrador.getDireccion() == null &&
                Data.administrador.getBarrio() == null &&
                Data.administrador.getProvincia() == null &&
                Data.administrador.getTelefono() == null &&
                Data.administrador.getMail() == null &&
                Data.administrador.getCodigoPostal() == null &&
                Data.administrador.getNombreNegocio() == null &&
                Data.administrador.getDocumento() == null &&
                Data.administrador.getContacto() == null) 
            {
                abrirAviso(
                    t("menu.advertencia"), t("menu.mensaje")
                );
            } else {
                abrirVentana(
                    new Stage(),
                    new PresupuestoView(stage, null).getRoot(),
                    t("menu.crearPresupuesto"),
                    950,
                    840
                );
            }
        });



        
        btnFacturas.setOnAction(e -> abrirVentana(new Stage(), new ColeccionView(stage).getRoot(), t("menu.tablaPresupuestos"), 1400, 600));
        btnClientes.setOnAction(e -> abrirVentana(new Stage(), new ClienteView(stage).getRoot(), t("menu.tablaClientes"), 1400, 600));
        btnImpresoras.setOnAction(e -> abrirVentana(new Stage(), new ImpresorasView(stage).getRoot(), t("menu.tablaImpresoras"), 1400, 600));
        btnConfig.setOnAction(e -> abrirVentana(new Stage(), new ConfigView(stage).getRoot(), t("menu.config"), 800, 650));
        
        HBox form = new HBox(20, btnPresupuesto, btnFacturas, btnClientes, btnImpresoras, btnConfig);
        form.setStyle(baseStyle + "-fx-background-color: linear-gradient(#FAFAFA, #F2F2F2);-fx-background-radius: 15;-fx-border-radius: 15;");
        form.setMaxWidth(785);
        form.setPrefHeight(200);
        form.setEffect(ds);
        form.setAlignment(Pos.CENTER);
        
        
        VBox form2 = new VBox(30,btnIdioma, titulo, form);
        form2.setMaxHeight(500);
        form2.setAlignment(Pos.CENTER);
        form2.setTranslateY(-50);
        
        
        root.getChildren().addAll(barra1,form2, barra2);
        btnIdioma.setTranslateX(-410);
        btnIdioma.setTranslateY(-85);
        
        if (Data.administrador.getIdioma().equals("EN")) {
            aplicarIdiomaIngles();
        } else {
            aplicarIdiomaEspanol();
        }
        
      
        
    }

    public VBox getRoot() {
        return root;
    }
    
    public void mostrarSelectorIdioma() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("English", Arrays.asList("English", "Español"));
        if ("EN".equals(Data.administrador.getIdioma())){
            dialog.setTitle("Select Language");
            dialog.setHeaderText("Choose the application language:");
            dialog.setContentText("Language:");
        } else {
            dialog.setTitle("Seleccionar Idioma");
            dialog.setHeaderText("Elige el idioma de la aplicación:");
            dialog.setContentText("Idioma:");
        }

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(idioma -> {
            switch (idioma) {
                case "Español" ->  {
                    aplicarIdiomaEspanol();
                    Data.administrador.setIdioma("ES");
                    stage.setTitle("Cotizador de impresión 3D"); // 🔹 actualizar título
                    Data.guardar();
                }
                case "English" ->  {
                    aplicarIdiomaIngles();
                    Data.administrador.setIdioma("EN");
                    stage.setTitle("3D Printing Quote Generator"); // 🔹 actualizar título
                    Data.guardar();
                }
            }
        });
    }


    private void aplicarIdiomaEspanol() {
        btnIdioma.setText("ES");
        btnPresupuesto.setText("Realizar\npresupuesto");
        btnFacturas.setText("Ver \npresupuestos");
        btnClientes.setText("Ver \nclientes");
        btnImpresoras.setText("Ver\nimpresoras");
        btnConfig.setText("Ajustes");
        titulo.setText("Cotizador de impresión 3D");
    }

    private void aplicarIdiomaIngles() {
        btnIdioma.setText("EN");
        btnPresupuesto.setText("Create\nQuote");
        btnFacturas.setText("View \nQuotes");
        btnClientes.setText("View \nClients");
        btnImpresoras.setText("View\nPrinters");
        btnConfig.setText("Settings");
        titulo.setText("3D Printing Quote Generator");
    }
    
    private String t(String clave) {

        switch (Data.administrador.getIdioma()) { // o donde estés guardando el idioma

            case "ES" -> {
                switch (clave) {
                    case "menu.crearPresupuesto" -> { return "Realizar presupuesto"; }
                    case "menu.tablaPresupuestos" -> { return "Tabla de Presupuestos"; }
                    case "menu.tablaClientes" -> { return "Tabla de Clientes"; }
                    case "menu.tablaImpresoras" -> { return "Tabla de Impresoras"; }
                    case "menu.config" -> { return "Ajustes"; }
                    case "menu.advertencia" -> { return "ADVERTENCIA"; }
                    case "menu.mensaje" -> { return "Completa la información en la ventana de Configuración."; }
                }
            }
            case "EN" -> {
                switch (clave) {
                    case "menu.crearPresupuesto" -> { return "Create Quote"; }
                    case "menu.tablaPresupuestos" -> { return "Quotes Table"; }
                    case "menu.tablaClientes" -> { return "Clients Table"; }
                    case "menu.tablaImpresoras" -> { return "Printers Table"; }
                    case "menu.config" -> { return "Settings"; }
                    case "menu.advertencia" -> { return "WARNING"; }
                    case "menu.mensaje" -> { return "Complete the information in the Settings window."; }
                    
                }
            }
        }
        return clave;
    }

}
