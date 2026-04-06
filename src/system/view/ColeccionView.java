package system.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import system.controller.Controlador;
import system.model.Data;
import system.model.Factura;

public class ColeccionView extends Controlador {
    private VBox root;
    
    public ColeccionView(Stage stage){
        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        stage.setResizable(false);
        root.setOnMouseClicked(e -> {
            root.requestFocus(); 
        });      
        
        
        DropShadow ds = new DropShadow();
        ds.setOffsetX(0);
        ds.setOffsetY(0);
        ds.setRadius(10);
        ds.setSpread(0.3);
        ds.setColor(Color.color(0, 0, 0, 0.4)); // negro semi-transparente
        
        
        String baseStyle =
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10 20;" +
            "-fx-background-radius: 6;" +
            "-fx-border-radius: 6;" +
            "-fx-cursor: hand;";
        
        
        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setStyle(baseStyle +"-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +"-fx-border-color: #a0a0a0;" +"-fx-text-fill: #333;");
        btnCerrar.setEffect(ds);  
        
        
        btnCerrar.setOnMouseEntered(e ->
            btnCerrar.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#f0f0f0, #dcdcdc);" +
                "-fx-border-color: #909090;" +
                "-fx-text-fill: #222;"
        ));

        btnCerrar.setOnMouseExited(e ->
            btnCerrar.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +
                "-fx-border-color: #a0a0a0;" +
                "-fx-text-fill: #333;"
        ));    
        
        
        
        
        
        TableView<Factura> tabla = new TableView<>(Data.getFacturas());

        TableColumn<Factura, String> cFec = new TableColumn<>("FECHA EMISIÓN");
        TableColumn<Factura, String> cNum = new TableColumn<>("NRO. COMPROBANTE");
        TableColumn<Factura, String> cIde = new TableColumn<>("NRO. IDENTIFICACIÓN");
        TableColumn<Factura, String> cRaz = new TableColumn<>("RAZÓN SOCIAL");
        TableColumn<Factura, String> cTol = new TableColumn<>("IMPORTE TOTAL");
        TableColumn<Factura, Void> cVer = new TableColumn<>("VER");
        TableColumn<Factura, Void> cBor = new TableColumn<>("BORRAR");
        
        cFec.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getFecha())));
        cNum.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumero()));
        cIde.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCliente().getDocumento()));
        cRaz.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCliente().getRazonSocial()));
        cTol.setCellValueFactory(data -> new SimpleStringProperty(String.format("$%.2f", data.getValue().getTotal())));
        cVer.setCellFactory(col -> new TableCell<Factura, Void>() {
            private final Button btnVer = new Button();

            {
                btnVer.setOnAction(e -> {
                    Factura factura = getTableView().getItems().get(getIndex());

                    FacturaFinalView facturaView = new FacturaFinalView(new Stage(), factura, false);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(facturaView.getRoot(),950,973));
                    stage.show();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {

                    // 🔥 ACÁ aplicás idioma dinámico
                    if (Data.administrador.getIdioma().equals("EN")) {
                        btnVer.setText("View");
                    } else {
                        btnVer.setText("Ver");
                    }

                    setGraphic(btnVer);
                }
            }
        });
        cBor.setCellFactory(col -> new TableCell<Factura, Void>() {
            private final Button btnBorrar = new Button();

            {
                btnBorrar.setOnAction(e -> {
                    Factura factura = getTableView().getItems().get(getIndex());
                    if (factura != null) {
                        Data.getFacturas().remove(factura);
                        Data.guardar();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {

                    // 🔥 idioma dinámico
                    if (Data.administrador.getIdioma().equals("EN")) {
                        btnBorrar.setText("Delete");
                    } else {
                        btnBorrar.setText("Borrar");
                    }

                    setGraphic(btnBorrar);
                }
            }
        });

        
        tabla.getColumns().addAll(cFec, cNum, cIde, cRaz, cTol, cVer, cBor);
        cFec.setPrefWidth(60);
        cNum.setPrefWidth(75);
        cIde.setPrefWidth(75);
        cRaz.setPrefWidth(60);
        cTol.setPrefWidth(60);
        cVer.setPrefWidth(40);
        cBor.setPrefWidth(40);
        cFec.setStyle("-fx-alignment: CENTER;");
        cNum.setStyle("-fx-alignment: CENTER;");
        cIde.setStyle("-fx-alignment: CENTER;");
        cRaz.setStyle("-fx-alignment: CENTER;");
        cTol.setStyle("-fx-alignment: CENTER;");
        cVer.setStyle("-fx-alignment: CENTER;");
        cBor.setStyle("-fx-alignment: CENTER;");
        
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        btnCerrar.setOnAction(e -> {
            ((Stage) btnCerrar.getScene().getWindow()).close();
        });    
        aplicarIdioma(
            btnCerrar,
            cFec, cNum, cIde, cRaz, cTol, cVer, cBor
        );
        root.getChildren().addAll(tabla, btnCerrar);
    }

    public VBox getRoot() {
        return root;
    }
    
    private void aplicarIdioma(
        Button btnCerrar,
        TableColumn<Factura, String> cFec, TableColumn<Factura, String> cNum,
        TableColumn<Factura, String> cIde,
        TableColumn<Factura, String> cRaz, TableColumn<Factura, String> cTol,
        TableColumn<Factura, Void> cVer, TableColumn<Factura, Void> cBor
    ) {

        if (Data.administrador.getIdioma().equals("EN")) {

            btnCerrar.setText("Close");

            cFec.setText("ISSUE DATE");
            cNum.setText("RECEIPT NO.");
            cIde.setText("ID NUMBER");
            cRaz.setText("BUSINESS NAME");
            cTol.setText("TOTAL AMOUNT");
            cVer.setText("VIEW");
            cBor.setText("DELETE");

        } else {

            btnCerrar.setText("Cerrar");

            cFec.setText("FECHA EMISIÓN");
            cNum.setText("NRO. COMPROBANTE");
            cIde.setText("NRO. IDENTIFICACIÓN");
            cRaz.setText("RAZÓN SOCIAL");
            cTol.setText("IMPORTE TOTAL");
            cVer.setText("VER");
            cBor.setText("BORRAR");
        }
    }
    
    
}
