package system.view;

import system.model.Data;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import system.controller.Controlador;
import system.model.Cliente;

public class ClienteView extends Controlador{
    private VBox root;

    public ClienteView (Stage stage){
        
        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.requestFocus(); 
        root.setOnMouseClicked(e -> {
            root.requestFocus(); 
        });    
        
        DropShadow ds = new DropShadow();
        ds.setOffsetX(0);
        ds.setOffsetY(0);
        ds.setRadius(10);
        ds.setSpread(0.3);
        ds.setColor(Color.color(0, 0, 0, 0.4)); // negro semi-transparente
        
        //Campos de entrada
        TextField promptRazonSocial = new TextField();
        promptRazonSocial.setPromptText("Razón Social");
        promptRazonSocial.setPrefWidth(170);
        promptRazonSocial.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z ]*")) {
                promptRazonSocial.setText(oldValue);
            }
        });
        
        
        TextField promptTelefono = new TextField();
        promptTelefono.setPromptText("Telefono");
        promptTelefono.setPrefWidth(120);
        promptTelefono.textProperty().addListener((obs, oldValue, newValue) -> { 
            if (!newValue.matches("\\d*(\\d*)?")) { 
                promptTelefono.setText(oldValue); 
            } 
        });        
        
        
        TextField promptMail = new TextField();
        promptMail.setPromptText("Correo Electronico");
        promptMail.setPrefWidth(170);
        
        
        TextField promptDocumento = new TextField();
        promptDocumento.setPromptText("Nro Identificación");
        promptDocumento.setPrefWidth(120);
        promptDocumento.textProperty().addListener((obs, oldValue, newValue) -> { 
            if (!newValue.matches("[a-zA-Z0-9]*")) { 
                promptDocumento.setText(oldValue); 
            } 
        });   
        
        
        TextField promptDireccion = new TextField();
        promptDireccion.setPromptText("Dirección");
        promptDireccion.setPrefWidth(170);
        
        
        TextField promptLocalidad = new TextField();
        promptLocalidad.setPromptText("Localidad / Ciudad");
        promptLocalidad.setPrefWidth(120);
        
        
        TextField promptProvincia = new TextField();
        promptProvincia.setPromptText("Provincia / Estado");
        promptProvincia.setPrefWidth(120);
        
        
        TextField promptCodigoPostal = new TextField();
        promptCodigoPostal.setPromptText("Código Postal");
        promptCodigoPostal.setPrefWidth(100);
        
        String baseStyle =
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10 20;" +
            "-fx-background-radius: 6;" +
            "-fx-border-radius: 6;" +
            "-fx-cursor: hand;";

        Button btnEliminar = new Button("Eliminar");
        btnEliminar.setStyle(baseStyle +"-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +"-fx-border-color: #a0a0a0;" +"-fx-text-fill: #333;");
        btnEliminar.setEffect(ds);
        
        Button btnSalir = new Button("Cerrar");
        btnSalir.setStyle(baseStyle +"-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +"-fx-border-color: #a0a0a0;" +"-fx-text-fill: #333;");
        btnSalir.setEffect(ds);
        
        Button btnAgregar = new Button("Agregar");
        btnAgregar.setStyle(baseStyle +"-fx-background-color: linear-gradient(#4a77b8, #2f5f9e);" +"-fx-border-color: #244a7c;" +"-fx-text-fill: white;");
        btnAgregar.setEffect(ds);
        

        btnEliminar.setOnMouseEntered(e ->
            btnEliminar.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#f0f0f0, #dcdcdc);" +
                "-fx-border-color: #909090;" +
                "-fx-text-fill: #222;"
        ));

        btnEliminar.setOnMouseExited(e ->
            btnEliminar.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +
                "-fx-border-color: #a0a0a0;" +
                "-fx-text-fill: #333;"
        ));
        
        
        btnSalir.setOnMouseEntered(e ->
            btnSalir.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#f0f0f0, #dcdcdc);" +
                "-fx-border-color: #909090;" +
                "-fx-text-fill: #222;"
        ));

        btnSalir.setOnMouseExited(e ->
            btnSalir.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +
                "-fx-border-color: #a0a0a0;" +
                "-fx-text-fill: #333;"
        ));        
        
        
        btnAgregar.setOnMouseEntered(e ->
            btnAgregar.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#5a8bd8, #3f6fb0);" +
                "-fx-border-color: #244a7c;" +
                "-fx-text-fill: white;"
        ));

        btnAgregar.setOnMouseExited(e ->
            btnAgregar.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#4a77b8, #2f5f9e);" +
                "-fx-border-color: #244a7c;" +
                "-fx-text-fill: white;"
        ));        
        
        HBox form = new HBox(10, promptRazonSocial, promptDocumento, promptTelefono, promptMail, promptDireccion, promptLocalidad, promptProvincia, promptCodigoPostal, btnAgregar);
        form.setStyle("-fx-alignment: center;");

        
        TableView<Cliente> tabla = new TableView<>(Data.getClientes());
        TableColumn<Cliente, String> cRaz = new TableColumn<>("RAZÓN SOCIAL");
        TableColumn<Cliente, String> cDoc = new TableColumn<>("NRO IDENTIFICACIÓN");
        TableColumn<Cliente, String> cTel = new TableColumn<>("TELEFONO");
        TableColumn<Cliente, String> cMai = new TableColumn<>("MAIL");
        TableColumn<Cliente, String> cDir = new TableColumn<>("DIRECCIÓN");
        TableColumn<Cliente, String> cLoc = new TableColumn<>("LOCALIDAD / CIUDAD");
        TableColumn<Cliente, String> cPro = new TableColumn<>("PROVINCIA / ESTADO");
        TableColumn<Cliente, String> cCod = new TableColumn<>("CÓDIGO POSTAL");
        
        cRaz.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getRazonSocial()));
        cTel.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getTelefono()));
        cMai.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getMail()));
        cDoc.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDocumento()));
        cDir.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDireccion()));
        cLoc.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getLocalidad()));
        cPro.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getProvincia()));
        cCod.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getCodigoPostal()));

        
        tabla.getColumns().addAll(cRaz, cTel, cMai, cDoc, cDir, cLoc, cPro, cCod);
        cRaz.setPrefWidth(150);
        cTel.setPrefWidth(150);
        cMai.setPrefWidth(150);
        cDoc.setPrefWidth(150);
        cDir.setPrefWidth(150);
        cLoc.setPrefWidth(150);
        cPro.setPrefWidth(150);
        cCod.setPrefWidth(150);
        cTel.setStyle("-fx-alignment: CENTER;");
        cDoc.setStyle("-fx-alignment: CENTER;");
        cLoc.setStyle("-fx-alignment: CENTER;");
        cPro.setStyle("-fx-alignment: CENTER;");
        cCod.setStyle("-fx-alignment: CENTER;");

        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        btnAgregar.setOnAction(e -> {
            if (promptRazonSocial.getText().isEmpty() || 
                promptTelefono.getText().isEmpty() || 
                promptMail.getText().isEmpty() || 
                promptDocumento.getText().isEmpty() ||
                promptDireccion.getText().isEmpty() ||
                promptLocalidad.getText().isEmpty() ||
                promptProvincia.getText().isEmpty() ||
                promptCodigoPostal.getText().isEmpty()) {

                if (Data.administrador.getIdioma().equals("EN")) {
                    abrirAviso("WARNING", "You must complete all fields.");
                } else {
                    abrirAviso("ADVERTENCIA", "Debe completar todos los campos.");
                }

                return; // ✔ correcto
            }

            Data.getClientes().add(new Cliente(
                promptRazonSocial.getText(),
                promptTelefono.getText(),
                promptMail.getText(),
                promptDocumento.getText(),
                promptDireccion.getText(),
                promptLocalidad.getText(),
                promptProvincia.getText(),
                promptCodigoPostal.getText()
            ));
            
            promptRazonSocial.clear();
            promptTelefono.clear();
            promptMail.clear();
            promptDocumento.clear();
            promptDireccion.clear();
            promptLocalidad.clear();
            promptProvincia.clear();
            promptCodigoPostal.clear();
            Data.guardar();
        });
   
        
        btnEliminar.setOnAction(e -> {
            Cliente sel = tabla.getSelectionModel().getSelectedItem();
            if (sel != null) {
                Data.getClientes().remove(sel);
                Data.guardar();
            }
        });
        
        
        btnSalir.setOnAction(e -> {
            Stage stage1 = (Stage) btnSalir.getScene().getWindow();
            stage1.close();
        });

        aplicarIdioma(
            promptRazonSocial, promptTelefono, promptMail,
            promptDocumento, promptDireccion,
            promptLocalidad, promptProvincia, promptCodigoPostal,
            btnEliminar, btnSalir, btnAgregar,
            cRaz, cDoc, cTel, cMai, cDir, cLoc, cPro, cCod
        );

        root.getChildren().addAll(form, tabla, btnEliminar, btnSalir);     
        

    }
    
    public VBox getRoot() { return root; }
    
    
    private void aplicarIdioma(
        TextField promptRazonSocial, TextField promptTelefono, TextField promptMail,
        TextField promptDocumento, TextField promptDireccion,
        TextField promptLocalidad, TextField promptProvincia, TextField promptCodigoPostal,
        Button btnEliminar, Button btnSalir, Button btnAgregar,
        TableColumn<Cliente, String> cRaz,
        TableColumn<Cliente, String> cDoc, TableColumn<Cliente, String> cTel,
        TableColumn<Cliente, String> cMai, TableColumn<Cliente, String> cDir,
        TableColumn<Cliente, String> cLoc, TableColumn<Cliente, String> cPro,
        TableColumn<Cliente, String> cCod
    ) {

        if (Data.administrador.getIdioma().equals("EN")) {

            // INPUTS
            promptRazonSocial.setPromptText("Business Name");
            promptTelefono.setPromptText("Phone");
            promptMail.setPromptText("Email");
            promptDocumento.setPromptText("ID Number");
            promptDireccion.setPromptText("Address");
            promptLocalidad.setPromptText("City");
            promptProvincia.setPromptText("State");
            promptCodigoPostal.setPromptText("Zip Code");

            // BOTONES
            btnEliminar.setText("Delete");
            btnSalir.setText("Close");
            btnAgregar.setText("Add");

            // TABLA
            cRaz.setText("BUSINESS NAME");
            cDoc.setText("ID NUMBER");
            cTel.setText("PHONE");
            cMai.setText("EMAIL");
            cDir.setText("ADDRESS");
            cLoc.setText("CITY");
            cPro.setText("STATE");
            cCod.setText("ZIP CODE");

        } else {

            // INPUTS
            promptRazonSocial.setPromptText("Razón Social");
            promptTelefono.setPromptText("Teléfono");
            promptMail.setPromptText("Correo Electrónico");
            promptDocumento.setPromptText("Documento");
            promptDireccion.setPromptText("Dirección");
            promptLocalidad.setPromptText("Localidad / Ciudad");
            promptProvincia.setPromptText("Provincia / Estado");
            promptCodigoPostal.setPromptText("Código Postal");

            // BOTONES
            btnEliminar.setText("Eliminar");
            btnSalir.setText("Cerrar");
            btnAgregar.setText("Agregar");

            // TABLA
            cRaz.setText("RAZÓN SOCIAL");
            cDoc.setText("DOCUMENTO");
            cTel.setText("TELÉFONO");
            cMai.setText("CORREO ELECTRONICO");
            cDir.setText("DIRECCIÓN");
            cLoc.setText("LOCALIDAD / CIUDAD");
            cPro.setText("PROVINCIA / ESTADO");
            cCod.setText("CÓDIGO POSTAL");
        }
    }
    
    
}
