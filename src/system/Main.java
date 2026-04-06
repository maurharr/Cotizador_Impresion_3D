package system;

import system.model.Data;
import system.view.MenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception{

        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("Java home: " + System.getProperty("java.home"));

        Data.cargarDatos(); // Datos iniciales
        Data.cargar(); // Datos iniciales
        MenuView menu = new MenuView(stage);
        stage.setTitle(t("menu.titulo"));
        Scene scene = new Scene(menu.getRoot(), 900, 600);
        stage.setScene(scene);
        stage.show();
    }    
    
    
    private String t(String clave) {

        switch (Data.administrador.getIdioma()) { // o donde estés guardando el idioma

            case "ES" -> {
                switch (clave) {
                    case "menu.titulo" -> { return "Cotizador de impresión 3D"; }
                }
            }
            case "EN" -> {
                switch (clave) {
                    case "menu.titulo" -> { return "3D Printing Quote Generator"; }
                }
            }
        }
        return clave;
    }
    
}
