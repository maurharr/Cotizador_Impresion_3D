package system.controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class Controlador {
    public void abrirVentana(Stage stage, VBox contenido, String titulo, int ancho, int alto){
        Scene scene = new Scene(contenido, ancho, alto);
        stage.setResizable(false);
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.show();
    }
    
    public void abrirAviso(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

