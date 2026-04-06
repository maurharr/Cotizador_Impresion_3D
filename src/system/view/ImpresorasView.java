package system.view;

import system.model.Data;
import system.model.Impresora;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import system.controller.Controlador;
import system.model.Amortizacion;
import system.model.Gama;

public class ImpresorasView extends Controlador {
    private VBox root;

    public ImpresorasView (Stage stage){
        
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
        TextField txtPromptModelo = new TextField();
        txtPromptModelo.setPromptText("MODELO");
        txtPromptModelo.setPrefWidth(150);
        TextField txtPromptValor = new TextField();
        txtPromptValor.setPromptText("VALOR");
        txtPromptValor.setPrefWidth(150);
        txtPromptValor.setAlignment(Pos.CENTER);
        txtPromptValor.textProperty().addListener((obs, oldValue, newValue) -> { 
            if (!newValue.matches("\\d*(\\d*)?")) { 
                txtPromptValor.setText(oldValue); 
            } 
        }); 
        TextField txtPromptConsumo = new TextField();
        txtPromptConsumo.setPromptText("CONSUMO PROMEDIO (W)");
        txtPromptConsumo.setPrefWidth(170);
        txtPromptConsumo.setAlignment(Pos.CENTER);
        txtPromptConsumo.textProperty().addListener((obs, oldValue, newValue) -> { 
            if (!newValue.matches("\\d*(\\d*)?")) { 
                txtPromptConsumo.setText(oldValue); 
            } 
        }); 
        TextField txtPromptTasaFallos = new TextField();
        txtPromptTasaFallos.setPromptText("TASA DE FALLOS");
        txtPromptTasaFallos.setPrefWidth(150);
        txtPromptTasaFallos.setAlignment(Pos.CENTER);
        txtPromptTasaFallos.textProperty().addListener((obs, oldValue, newValue) -> { 
            if (!newValue.matches("\\d*(\\d*)?")) { 
                txtPromptTasaFallos.setText(oldValue); 
            } 
        });         
        
        ComboBox<Amortizacion> cbAmortizacion = new ComboBox<>();
        cbAmortizacion.setPromptText("AMORTIZACIÓN");
        cbAmortizacion.setItems(Data.getAmortizacion());
        cbAmortizacion.setPrefWidth(170);
        
        ComboBox<Gama> cbGama = new ComboBox<>();
        cbGama.setPromptText("GAMA DE MAQUINA");
        cbGama.setItems(Data.getGamaObjetivo());
        cbGama.setPrefWidth(170);
        
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
        
        
        
        HBox form = new HBox(30, txtPromptModelo, txtPromptValor, txtPromptConsumo, txtPromptTasaFallos, cbAmortizacion, cbGama, btnAgregar);
        form.setStyle("-fx-alignment: center;");

        
        TableView<Impresora> tabla = new TableView<>(Data.getImpresoras());
        TableColumn<Impresora, String> cMod = new TableColumn<>("MODELO");
        TableColumn<Impresora, String> cVal = new TableColumn<>("VALOR");
        TableColumn<Impresora, String> cCon = new TableColumn<>("CONSUMO PROMEDIO (WATTS)");
        TableColumn<Impresora, String> cTas = new TableColumn<>("TASA DE FALLOS");
        TableColumn<Impresora, String> cAmo = new TableColumn<>("AMORTIZACIÓN");
        TableColumn<Impresora, String> cMar = new TableColumn<>("GAMA DE MAQUINA");
        
        cMod.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getModelo()));
        cVal.setCellValueFactory(p-> new SimpleStringProperty("$"+(int) Double.parseDouble(p.getValue().getValor())));
        cCon.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().getConsumo()));
        cTas.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().getTasaFallos()+"%"));
        cAmo.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAmortizacion().getDuracion(Data.administrador.getIdioma())));
        cMar.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getGamaObjetivo().getGamaObjetivo(Data.administrador.getIdioma())));
        
        tabla.getColumns().addAll(cMod, cVal, cCon, cTas, cAmo, cMar);
        cMod.setPrefWidth(150);
        cVal.setPrefWidth(150);
        cCon.setPrefWidth(150);
        cTas.setPrefWidth(150);
        cAmo.setPrefWidth(150);
        cMar.setPrefWidth(150);
        cMod.setStyle("-fx-alignment: CENTER-LEFT;");
        cVal.setStyle("-fx-alignment: CENTER;");
        cCon.setStyle("-fx-alignment: CENTER;");
        cTas.setStyle("-fx-alignment: CENTER;");
        cAmo.setStyle("-fx-alignment: CENTER;");
        cMar.setStyle("-fx-alignment: CENTER;");

        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        btnAgregar.setOnAction(e -> {
            if (txtPromptModelo.getText().isEmpty() || 
                txtPromptValor.getText().isEmpty() || 
                txtPromptConsumo.getText().isEmpty() || 
                txtPromptTasaFallos.getText().isEmpty() || 
                cbAmortizacion.getValue() == null ||
                cbGama.getValue() == null) {

                if (Data.administrador.getIdioma().equals("EN")) {
                    abrirAviso("WARNING", "You must complete all fields.");
                } else {
                    abrirAviso("ADVERTENCIA", "Debe completar todos los campos.");
                }

                return; // ✔ correcto
            }

            Data.getImpresoras().add(
                new Impresora(
                    txtPromptModelo.getText(),
                    txtPromptValor.getText(),
                    txtPromptConsumo.getText(),
                    txtPromptTasaFallos.getText(),
                    "20",
                    cbAmortizacion.getValue(),
                    cbGama.getValue()
                )
            );

            txtPromptModelo.clear();
            txtPromptValor.clear();
            txtPromptConsumo.clear();
            txtPromptTasaFallos.clear();
            cbAmortizacion.setValue(null);
            cbGama.setValue(null);

            Data.guardar();
        });

        
        btnEliminar.setOnAction(e -> {
            Impresora sel = tabla.getSelectionModel().getSelectedItem();
            if (sel != null){
                Data.getImpresoras().remove(sel);
                Data.guardar();
            }
        });
        
        
        btnSalir.setOnAction(e -> {
            Stage stage1 = (Stage) btnSalir.getScene().getWindow();
            stage1.close();
        });

        
        aplicarIdioma(
            txtPromptModelo, txtPromptValor, txtPromptConsumo, txtPromptTasaFallos,
            cbAmortizacion, cbGama,
            btnEliminar, btnSalir, btnAgregar,
            cMod, cVal, cCon, cTas, cAmo, cMar
        );
        
        
        root.getChildren().addAll(form, tabla, btnEliminar, btnSalir);     
        
        
        cbAmortizacion.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Amortizacion item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setText(Data.administrador.getIdioma().equals("EN") ? "DEPRECIATION" : "AMORTIZACIÓN");
                } else {
                    setText(item.getDuracion(Data.administrador.getIdioma()));
                }
            }
        });

        cbGama.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Gama item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setText(Data.administrador.getIdioma().equals("EN") ? "MACHINE RANGE" : "GAMA DE MAQUINA");
                } else {
                    setText(item.getGamaObjetivo(Data.administrador.getIdioma()));
                }
            }
        });
        
        cbAmortizacion.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Amortizacion item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getDuracion(Data.administrador.getIdioma()));
                }
            }
        });


        cbGama.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Gama item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getGamaObjetivo(Data.administrador.getIdioma()));
                }
            }
        });        
        

    }
    
    public VBox getRoot() { return root; }
    
    
    private void aplicarIdioma(
        TextField txtPromptModelo, TextField txtPromptValor, TextField txtPromptConsumo, TextField txtPromptTasaFallos,
        ComboBox<Amortizacion> cbAmortizacion, ComboBox<Gama> cbGama,
        Button btnEliminar, Button btnSalir, Button btnAgregar,
        TableColumn<Impresora, String> cMod, TableColumn<Impresora, String> cVal,
        TableColumn<Impresora, String> cCon, TableColumn<Impresora, String> cTas,
        TableColumn<Impresora, String> cAmo, TableColumn<Impresora, String> cMar
    ) {

        if (Data.administrador.getIdioma().equals("EN")) {

            // INPUTS
            txtPromptModelo.setPromptText("MODEL");
            txtPromptValor.setPromptText("PRICE");
            txtPromptConsumo.setPromptText("POWER CONSUMPTION (W)");
            txtPromptTasaFallos.setPromptText("FAILURE RATE");

            cbAmortizacion.setPromptText("DEPRECIATION");
            cbGama.setPromptText("MACHINE RANGE");

            // BOTONES
            btnEliminar.setText("Delete");
            btnSalir.setText("Close");
            btnAgregar.setText("Add");

            // TABLA
            cMod.setText("MODEL");
            cVal.setText("PRICE");
            cCon.setText("POWER CONSUMPTION (W)");
            cTas.setText("FAILURE RATE");
            cAmo.setText("DEPRECIATION");
            cMar.setText("MACHINE RANGE");

        } else {

            // INPUTS
            txtPromptModelo.setPromptText("MODELO");
            txtPromptValor.setPromptText("VALOR");
            txtPromptConsumo.setPromptText("CONSUMO PROMEDIO (W)");
            txtPromptTasaFallos.setPromptText("TASA DE FALLOS");

            cbAmortizacion.setPromptText("AMORTIZACIÓN");
            cbGama.setPromptText("GAMA DE MAQUINA");

            // BOTONES
            btnEliminar.setText("Eliminar");
            btnSalir.setText("Cerrar");
            btnAgregar.setText("Agregar");

            // TABLA
            cMod.setText("MODELO");
            cVal.setText("VALOR");
            cCon.setText("CONSUMO PROMEDIO (WATTS)");
            cTas.setText("TASA DE FALLOS");
            cAmo.setText("AMORTIZACIÓN");
            cMar.setText("GAMA DE MAQUINA");
        }
    }

}
