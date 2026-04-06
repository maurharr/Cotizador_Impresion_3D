package system.view;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import system.model.Data;

public class ConfigView {
    private VBox root;
    

    public ConfigView(Stage stage) { 
        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        
        String baseStyle =
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10 20;" +
            "-fx-background-radius: 6;" +
            "-fx-border-radius: 6;" +
            "-fx-cursor: hand;";
        
        DropShadow ds = new DropShadow();
        ds.setOffsetX(0);
        ds.setOffsetY(0);
        ds.setRadius(10);
        ds.setSpread(0.3);
        ds.setColor(Color.color(0, 0, 0, 0.4)); // negro semi-transparente
        
        Button btnRetroceder = new Button("Cerrar");
        btnRetroceder.setStyle(baseStyle +"-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +"-fx-border-color: #a0a0a0;" +"-fx-text-fill: #333;");
        btnRetroceder.setEffect(ds);
        
        Button btnGuardar = new Button("Guardar");
        btnGuardar.setStyle(baseStyle +"-fx-background-color: linear-gradient(#7ecb5a, #5da93c);" +"-fx-border-color: #4a8d30;" +"-fx-text-fill: white;");
        btnGuardar.setEffect(ds);   
        
        Label establecerDatos = new Label("ESTABLECER DATOS");
        establecerDatos.setFont(Font.font("System", FontWeight.BOLD, 20));
        establecerDatos.setAlignment(Pos.CENTER);
        establecerDatos.setStyle("-fx-border-color: #A5B2C9; -fx-background-color: #366092; -fx-text-fill: white;"); 
        establecerDatos.setPrefSize(800,50);  
        
        Label txtNombre = new Label("Nombre: ");
        txtNombre.setFont(Font.font("System", FontWeight.BOLD, 15));
        TextField promptNombre = new TextField();
        promptNombre.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        HBox casillaNombre = new HBox(txtNombre, promptNombre);
        txtNombre.setPrefWidth(100);
        txtNombre.setStyle("-fx-padding: 0 0 0 20;");
        promptNombre.setPrefWidth(200);
        
        Label txtApellido = new Label("Apellido: ");
        txtApellido.setFont(Font.font("System", FontWeight.BOLD, 15));
        TextField promptApellido = new TextField();
        promptApellido.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        HBox casillaApellido = new HBox(txtApellido, promptApellido);
        txtApellido.setPrefWidth(100);
        txtApellido.setStyle("-fx-padding: 0 0 0 20;");
        promptApellido.setPrefWidth(200);
        
        Label txtTelefono = new Label("Teléfono: ");
        txtTelefono.setFont(Font.font("System", FontWeight.BOLD, 15));
        TextField promptTelefono = new TextField();
        promptTelefono.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        HBox casillaTelefono = new HBox(txtTelefono, promptTelefono);
        txtTelefono.setPrefWidth(100);
        txtTelefono.setStyle("-fx-padding: 0 0 0 20;");
        promptTelefono.setPrefWidth(200);        
        
        Label txtMail = new Label("Mail: ");
        txtMail.setFont(Font.font("System", FontWeight.BOLD, 15));
        TextField promptMail = new TextField();
        promptMail.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        HBox casillaMail = new HBox(txtMail, promptMail);
        txtMail.setPrefWidth(100);
        txtMail.setStyle("-fx-padding: 0 0 0 20;");
        promptMail.setPrefWidth(200);
        
        Label txtDocumento = new Label("Documento: ");
        txtDocumento.setFont(Font.font("System", FontWeight.BOLD, 15));
        TextField promptDocumento = new TextField();
        promptDocumento.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        HBox casillaDocumento = new HBox(txtDocumento, promptDocumento);
        txtDocumento.setPrefWidth(150);
        txtDocumento.setStyle("-fx-padding: 0 0 0 20;");
        promptMail.setPrefWidth(200);        
        
        VBox cuadro1 = new VBox(5, casillaNombre, casillaApellido, casillaTelefono, casillaMail, casillaDocumento);
        cuadro1.setPrefWidth(350);
        
        Label txtDireccion = new Label("Direccion: ");
        txtDireccion.setFont(Font.font("System", FontWeight.BOLD, 15));
        TextField promptDireccion = new TextField();
        promptDireccion.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        HBox casillaDireccion = new HBox(txtDireccion, promptDireccion);
        txtDireccion.setPrefWidth(170);
        txtDireccion.setStyle("-fx-padding: 0 0 0 20;");
        promptDireccion.setPrefWidth(200);
        
        
        Label txtLocalidad = new Label("Localidad / Ciudad: ");
        txtLocalidad.setFont(Font.font("System", FontWeight.BOLD, 15));
        TextField promptLocalidad = new TextField();
        promptLocalidad.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        HBox casillaLocalidad = new HBox(txtLocalidad, promptLocalidad);
        txtLocalidad.setPrefWidth(170);
        txtLocalidad.setStyle("-fx-padding: 0 0 0 20;");
        promptLocalidad.setPrefWidth(200);
        
        
        Label txtProvincia = new Label("Provincia / Estado: ");
        txtProvincia.setFont(Font.font("System", FontWeight.BOLD, 15));
        TextField promptProvincia = new TextField();
        promptProvincia.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        HBox casillaProvincia = new HBox(txtProvincia, promptProvincia);
        txtProvincia.setPrefWidth(170);
        txtProvincia.setStyle("-fx-padding: 0 0 0 20;");
        promptProvincia.setPrefWidth(200);
        
        Label txtCodigoPostal = new Label("Código Postal: ");
        txtCodigoPostal.setFont(Font.font("System", FontWeight.BOLD, 15));
        TextField promptCodigoPostal = new TextField();
        promptCodigoPostal.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        HBox casillaCodigoPostal = new HBox(txtCodigoPostal, promptCodigoPostal);
        txtCodigoPostal.setPrefWidth(170);
        txtCodigoPostal.setStyle("-fx-padding: 0 0 0 20;");
        promptCodigoPostal.setPrefWidth(200);
        
        VBox cuadro2 = new VBox(5, casillaDireccion, casillaLocalidad, casillaProvincia, casillaCodigoPostal);
        cuadro2.setPrefWidth(400);
        
        HBox planilla = new HBox(cuadro1, cuadro2);
        
        Label datosGuardados = new Label("DATOS GUARDADOS");
        datosGuardados.setFont(Font.font("System", FontWeight.BOLD, 20));
        datosGuardados.setAlignment(Pos.CENTER);
        datosGuardados.setStyle("-fx-border-color: #A5B2C9; -fx-background-color: #366092; -fx-text-fill: white;"); 
        datosGuardados.setPrefSize(800,50);        
        
        Label txtNombreEstablecido = new Label("Nombre: ");
        txtNombreEstablecido.setFont(Font.font("System", FontWeight.BOLD, 15));
        txtNombreEstablecido.setPrefWidth(100);
        txtNombreEstablecido.setStyle("-fx-padding: 0 0 0 20;");
        Label rptaNombre = new Label(Data.administrador.getNombre());
        rptaNombre.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaNombre.setPrefWidth(250);
        HBox casillaNombreEstablecido = new HBox(txtNombreEstablecido, rptaNombre);
        
        
        Label txtApellidoEstablecido = new Label("Apellido: ");
        txtApellidoEstablecido.setFont(Font.font("System", FontWeight.BOLD, 15));
        txtApellidoEstablecido.setPrefWidth(100);
        txtApellidoEstablecido.setStyle("-fx-padding: 0 0 0 20;");
        Label rptaApellido = new Label(Data.administrador.getApellido());
        rptaApellido.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaApellido.setPrefWidth(250);
        HBox casillaApellidoEstablecido = new HBox(txtApellidoEstablecido, rptaApellido);
                
        Label txtTelefonoEstablecido = new Label("Teléfono: ");
        txtTelefonoEstablecido.setFont(Font.font("System", FontWeight.BOLD, 15));
        txtTelefonoEstablecido.setPrefWidth(100);
        txtTelefonoEstablecido.setStyle("-fx-padding: 0 0 0 20;");
        Label rptaTelefono = new Label(Data.administrador.getTelefono());
        rptaTelefono.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaTelefono.setPrefWidth(250);
        
        HBox casillaTelefonoEstablecido = new HBox(txtTelefonoEstablecido, rptaTelefono);
        
        Label txtMailEstablecido = new Label("Mail: ");
        txtMailEstablecido.setFont(Font.font("System", FontWeight.BOLD, 15));
        txtMailEstablecido.setPrefWidth(100);
        txtMailEstablecido.setStyle("-fx-padding: 0 0 0 20;");
        Label rptaMail = new Label(Data.administrador.getMail());
        rptaMail.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaMail.setPrefWidth(250);   
        HBox casillaMailEstablecido = new HBox(txtMailEstablecido, rptaMail);
        
        Label txtDocumentoEstablecido = new Label("Documento: ");
        txtDocumentoEstablecido.setFont(Font.font("System", FontWeight.BOLD, 15));
        txtDocumentoEstablecido.setPrefWidth(170);
        txtDocumentoEstablecido.setStyle("-fx-padding: 0 0 0 20;");
        Label rptaDocumento = new Label(Data.administrador.getDocumento());
        rptaDocumento.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaDocumento.setPrefWidth(250);   
        HBox casillaDocumentoEstablecido = new HBox(txtDocumentoEstablecido, rptaDocumento); 
        
        
        VBox cuadro1Establecido = new VBox(10, casillaNombreEstablecido, casillaApellidoEstablecido, casillaTelefonoEstablecido, casillaMailEstablecido, casillaDocumentoEstablecido);
        cuadro1Establecido.setMaxWidth(350);
        
        Label txtDireccionEstablecido = new Label("Direccion: ");
        txtDireccionEstablecido.setFont(Font.font("System", FontWeight.BOLD, 15));
        txtDireccionEstablecido.setPrefWidth(170);
        txtDireccionEstablecido.setStyle("-fx-padding: 0 0 0 20;");
        Label rptaDireccion = new Label(Data.administrador.getDireccion());
        rptaDireccion.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaDireccion.setPrefWidth(250);   
        HBox casillaDireccionEstablecido = new HBox(txtDireccionEstablecido, rptaDireccion);
        
        Label txtLocalidadEstablecido = new Label("Localidad / Ciudad: ");
        txtLocalidadEstablecido.setFont(Font.font("System", FontWeight.BOLD, 15));
        txtLocalidadEstablecido.setPrefWidth(170);
        txtLocalidadEstablecido.setStyle("-fx-padding: 0 0 0 20;");
        Label rptaLocalidad = new Label(Data.administrador.getBarrio());
        rptaLocalidad.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaLocalidad.setPrefWidth(250);  
        HBox casillaLocalidadEstablecido = new HBox(txtLocalidadEstablecido, rptaLocalidad);
        
        Label txtProvinciaEstablecido = new Label("Provincia / Estado: ");
        txtProvinciaEstablecido.setFont(Font.font("System", FontWeight.BOLD, 15));
        txtProvinciaEstablecido.setPrefWidth(170);
        txtProvinciaEstablecido.setStyle("-fx-padding: 0 0 0 20;");
        Label rptaProvincia = new Label(Data.administrador.getProvincia());
        rptaProvincia.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaProvincia.setPrefWidth(250);  
        HBox casillaProvinciaEstablecido = new HBox(txtProvinciaEstablecido, rptaProvincia);
        
        Label txtCodigoPostalEstablecido = new Label("Código Postal: ");
        txtCodigoPostalEstablecido.setFont(Font.font("System", FontWeight.BOLD, 15));
        txtCodigoPostalEstablecido.setPrefWidth(170);
        txtCodigoPostalEstablecido.setStyle("-fx-padding: 0 0 0 20;");
        Label rptaCodigoPostal = new Label(Data.administrador.getCodigoPostal());
        rptaCodigoPostal.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaCodigoPostal.setPrefWidth(250);  
        HBox casillaCodigoPostalEstablecido = new HBox(txtCodigoPostalEstablecido, rptaCodigoPostal);        
        
        
        VBox cuadro2Establecido = new VBox(10, casillaDireccionEstablecido, casillaLocalidadEstablecido, casillaProvinciaEstablecido, casillaCodigoPostalEstablecido);
        cuadro2Establecido.setMaxWidth(400);
        
        HBox planillaEstablecido = new HBox(cuadro1Establecido, cuadro2Establecido);
        
        
        Label datosNegocio = new Label("DATOS DEL NEGOCIO");
        datosNegocio.setFont(Font.font("System", FontWeight.BOLD, 20));
        datosNegocio.setAlignment(Pos.CENTER);
        datosNegocio.setStyle("-fx-border-color: #A5B2C9; -fx-background-color: #366092; -fx-text-fill: white;"); 
        datosNegocio.setPrefSize(800,50);     
      
        btnRetroceder.setOnMouseEntered(e ->
            btnRetroceder.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#f0f0f0, #dcdcdc);" +
                "-fx-border-color: #909090;" +
                "-fx-text-fill: #222;"
        ));

        btnRetroceder.setOnMouseExited(e ->
            btnRetroceder.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +
                "-fx-border-color: #a0a0a0;" +
                "-fx-text-fill: #333;"
        ));

        btnGuardar.setOnMouseEntered(e ->
            btnGuardar.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#8fdb6a, #6db94c);" +
                "-fx-border-color: #3f7c28;" +
                "-fx-text-fill: white;"
        ));

        btnGuardar.setOnMouseExited(e ->
            btnGuardar.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#7ecb5a, #5da93c);" +
                "-fx-border-color: #4a8d30;" +
                "-fx-text-fill: white;"
        ));

        
        Label txtNombreNegocio = new Label("Nombre del Negocio: ");
        txtNombreNegocio.setFont(Font.font("System", FontWeight.BOLD, 15));
        txtNombreNegocio.setPrefWidth(200);
        txtNombreNegocio.setStyle("-fx-padding: 0 0 0 20;");
        TextField promptNombreNegocio = new TextField();
        promptNombreNegocio.setPrefWidth(150);
        promptNombreNegocio.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        HBox casillaNombreNegocio = new HBox(txtNombreNegocio, promptNombreNegocio);
        casillaNombreNegocio.setPrefWidth(370);
        
        Label txtNombreActual = new Label("Nombre actual: ");
        txtNombreActual.setFont(Font.font("System", FontWeight.BOLD, 15));
        txtNombreActual.setPrefWidth(200);
        txtNombreActual.setStyle("-fx-padding: 0 0 0 20;");
        Label rptaNombreNegocio = new Label(Data.administrador.getNombreNegocio());
        rptaNombreNegocio.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaNombreNegocio.setPrefWidth(150);  
        rptaNombreNegocio.setAlignment(Pos.BOTTOM_LEFT);
        rptaNombreNegocio.setWrapText(true);
        HBox casillaNombreActual = new HBox(10, txtNombreActual, rptaNombreNegocio);        
        
        VBox casillaNegocio = new VBox(casillaNombreNegocio, casillaNombreActual);
        
        Label txtContacto = new Label("Num/Mail de Contacto: ");
        txtContacto.setFont(Font.font("System", FontWeight.BOLD, 15));
        txtContacto.setPrefWidth(200);
        txtContacto.setStyle("-fx-padding: 0 0 0 20;");
        TextField promptContacto = new TextField();
        promptContacto.setPrefWidth(150);
        promptContacto.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        HBox casillaContacto = new HBox(txtContacto, promptContacto);
        casillaContacto.setPrefWidth(370);

        Label txtContactoActual = new Label("Contacto actual: ");
        txtContactoActual.setFont(Font.font("System", FontWeight.BOLD, 15));
        txtContactoActual.setPrefWidth(200);
        txtContactoActual.setStyle("-fx-padding: 0 0 0 20;");
        Label rptaContacto = new Label(Data.administrador.getContacto());
        rptaContacto.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaContacto.setPrefWidth(150);  
        rptaContacto.setAlignment(Pos.BOTTOM_LEFT);
        rptaContacto.setWrapText(true);
        HBox casillaContactoActual = new HBox(10, txtContactoActual, rptaContacto);        
        
        VBox casillaContactoCompleto = new VBox(casillaContacto, casillaContactoActual);
        
        Label txtLogo = new Label("Subir un logotipo: ");
        txtLogo.setFont(Font.font("System", FontWeight.BOLD, 15));
        txtLogo.setPrefWidth(140);

        // Label que actuará como contenedor
        Label logoLabel = new Label("Sin logo");
        logoLabel.setPrefSize(100, 100);
        logoLabel.setMinSize(100, 100);
        logoLabel.setMaxSize(100, 100);
        logoLabel.setStyle("-fx-border-color: gray; -fx-alignment: center;");
        
        if (Data.administrador.getRutaLogo() != null) {
            File file = new File(Data.administrador.getRutaLogo());

            if (file.exists()) {
                Image imagen = new Image(file.toURI().toString());

                ImageView imageView = new ImageView(imagen);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);

                logoLabel.setGraphic(imageView);
                logoLabel.setText("");
            }
        }

        Button btnCargar = new Button("Subir");
        btnCargar.setStyle(baseStyle +"-fx-background-color: linear-gradient(#EB772A, #CC6724);" +"-fx-border-color: #CC6724;" +"-fx-text-fill: white;");
        btnCargar.setEffect(ds);
        btnCargar.setPrefWidth(95);
        
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.setStyle(baseStyle +"-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +"-fx-border-color: #a0a0a0;" +"-fx-text-fill: #333;");
        btnEliminar.setEffect(ds);        
        
        btnCargar.setOnAction(e -> {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar logo");

            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
            );

            File archivo = fileChooser.showOpenDialog(stage);

            if (archivo != null) {
                try {
                    // Crear carpeta si no existe
                    File carpeta = new File("images");
                    if (!carpeta.exists()) {
                        carpeta.mkdirs();
                    }

                    // Ruta destino
                    File destino = new File("images/logo.png");

                    // Copiar archivo
                    java.nio.file.Files.copy(
                        archivo.toPath(),
                        destino.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                    );

                    // Guardar ruta en Admin
                    Data.administrador.setRutaLogo(destino.getAbsolutePath());

                    // Mostrar imagen
                    Image imagen = new Image(destino.toURI().toString());
                    ImageView imageView = new ImageView(imagen);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                    imageView.setPreserveRatio(true);

                    logoLabel.setGraphic(imageView);
                    logoLabel.setText("");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        btnEliminar.setOnAction(e -> {

            // 1. Obtener ruta actual
            String ruta = Data.administrador.getRutaLogo();

            if (ruta != null) {
                File archivo = new File(ruta);

                // 2. Borrar archivo físico (opcional pero recomendable)
                if (archivo.exists()) {
                    archivo.delete();
                }

                // 3. Limpiar datos en memoria
                Data.administrador.setRutaLogo(null);

                // 4. Limpiar UI
                logoLabel.setGraphic(null);
                logoLabel.setText("Sin logo");

                // 5. Guardar cambios
                Data.guardar();
            }
        });

        VBox botones2 = new VBox(20, btnCargar, btnEliminar);
        HBox casillaImagen = new HBox(20, txtLogo, botones2, logoLabel);
        
        
        HBox botones = new HBox(15, btnRetroceder, btnGuardar);
        botones.setStyle("-fx-alignment: center;");
        botones.setPrefHeight(100);
        
        VBox planillaNegocioContacto = new VBox(15,casillaNegocio, casillaContactoCompleto);
        HBox planillaNegocio = new HBox(planillaNegocioContacto, casillaImagen);

        VBox planillaCompleta = new VBox(10, establecerDatos, planilla, datosGuardados, planillaEstablecido, datosNegocio, planillaNegocio, botones);
        
        btnGuardar.setOnAction(e -> {
            String nombre = promptNombre.getText().trim();
            if (!nombre.isEmpty()) {
                Data.administrador.setNombre(nombre);
            }

            String apellido = promptApellido.getText().trim();
            if (!apellido.isEmpty()) {
                Data.administrador.setApellido(apellido);
            }

            String direccion = promptDireccion.getText().trim();
            if (!direccion.isEmpty()) {
                Data.administrador.setDireccion(direccion);
            }

            String barrio = promptLocalidad.getText().trim();
            if (!barrio.isEmpty()) {
                Data.administrador.setBarrio(barrio);
            }

            String provincia = promptProvincia.getText().trim();
            if (!provincia.isEmpty()) {
                Data.administrador.setProvincia(provincia);
            }

            String telefono = promptTelefono.getText().trim();
            if (!telefono.isEmpty()) {
                Data.administrador.setTelefono(telefono);
            }

            String mail = promptMail.getText().trim();
            if (!mail.isEmpty()) {
                Data.administrador.setMail(mail);
            }

            String codigoPostal = promptCodigoPostal.getText().trim();
            if (!codigoPostal.isEmpty()) {
                Data.administrador.setCodigoPostal(codigoPostal);
            }
            
            String nombreNegocio = promptNombreNegocio.getText().trim();
            if (!nombreNegocio.isEmpty()) {
                Data.administrador.setNombreNegocio(nombreNegocio);
            }            
            
            String documento = promptDocumento.getText().trim();
            if (!documento.isEmpty()) {
                Data.administrador.setDocumento(documento);
            }                        
                        
            String contacto = promptContacto.getText().trim();
            if (!contacto.isEmpty()) {
                Data.administrador.setContacto(contacto);
            }                        
            
            
            
            rptaNombre.setText(Data.administrador.getNombre());
            rptaApellido.setText(Data.administrador.getApellido());
            rptaTelefono.setText(Data.administrador.getTelefono());
            rptaMail.setText(Data.administrador.getMail());
            rptaDireccion.setText(Data.administrador.getDireccion());
            rptaLocalidad.setText(Data.administrador.getBarrio());
            rptaProvincia.setText(Data.administrador.getProvincia());
            rptaCodigoPostal.setText(Data.administrador.getCodigoPostal());
            rptaNombreNegocio.setText(Data.administrador.getNombreNegocio());
            rptaDocumento.setText(Data.administrador.getDocumento());
            rptaContacto.setText(Data.administrador.getContacto());
            
            promptNombre.clear();
            promptApellido.clear();
            promptDireccion.clear();
            promptLocalidad.clear();
            promptProvincia.clear();
            promptTelefono.clear();
            promptMail.clear();
            promptCodigoPostal.clear();
            promptNombreNegocio.clear();
            promptDocumento.clear();
            promptContacto.clear();

            Data.guardar();
        });

        btnRetroceder.setOnAction(e -> {
            Stage stage1 = (Stage) btnRetroceder.getScene().getWindow();
            stage1.close();
        });
        
        
        aplicarIdioma(
            btnRetroceder, btnGuardar, btnCargar, btnEliminar,
            establecerDatos, datosGuardados, datosNegocio,
            txtNombre, txtApellido, txtTelefono, txtMail, txtDocumento,
            txtDireccion, txtLocalidad, txtProvincia, txtCodigoPostal,
            txtNombreEstablecido, txtApellidoEstablecido, txtTelefonoEstablecido,
            txtMailEstablecido, txtDocumentoEstablecido,
            txtDireccionEstablecido, txtLocalidadEstablecido,
            txtProvinciaEstablecido, txtCodigoPostalEstablecido,
            txtNombreNegocio, txtNombreActual,
            txtContacto, txtContactoActual,
            txtLogo, logoLabel,
            stage
        );

        root.getChildren().add(planillaCompleta);
        
    }

    public VBox getRoot() {
        return root;
    }
    
private void aplicarIdioma(
    Button btnRetroceder, Button btnGuardar, Button btnCargar, Button btnEliminar,
    Label establecerDatos, Label datosGuardados, Label datosNegocio,
    Label txtNombre, Label txtApellido, Label txtTelefono, Label txtMail, Label txtDocumento,
    Label txtDireccion, Label txtLocalidad, Label txtProvincia, Label txtCodigoPostal,
    Label txtNombreEstablecido, Label txtApellidoEstablecido, Label txtTelefonoEstablecido,
    Label txtMailEstablecido, Label txtDocumentoEstablecido,
    Label txtDireccionEstablecido, Label txtLocalidadEstablecido,
    Label txtProvinciaEstablecido, Label txtCodigoPostalEstablecido,
    Label txtNombreNegocio, Label txtNombreActual,
    Label txtContacto, Label txtContactoActual,
    Label txtLogo, Label logoLabel,
    Stage stage
) {

    if (Data.administrador.getIdioma().equals("EN")) {

        // BOTONES
        btnRetroceder.setText("Close");
        btnGuardar.setText("Save");
        btnCargar.setText("Upload");
        btnEliminar.setText("Delete");

        // SECCIONES
        establecerDatos.setText("SET DATA");
        datosGuardados.setText("SAVED DATA");
        datosNegocio.setText("BUSINESS DATA");

        // CAMPOS
        txtNombre.setText("Name:");
        txtApellido.setText("Last Name:");
        txtTelefono.setText("Phone:");
        txtMail.setText("Email:");
        txtDocumento.setText("ID:");

        txtDireccion.setText("Address:");
        txtLocalidad.setText("City:");
        txtProvincia.setText("State:");
        txtCodigoPostal.setText("Postal Code:");

        // ESTABLECIDOS
        txtNombreEstablecido.setText("Name:");
        txtApellidoEstablecido.setText("Last Name:");
        txtTelefonoEstablecido.setText("Phone:");
        txtMailEstablecido.setText("Email:");
        txtDocumentoEstablecido.setText("ID:");

        txtDireccionEstablecido.setText("Address:");
        txtLocalidadEstablecido.setText("City:");
        txtProvinciaEstablecido.setText("State:");
        txtCodigoPostalEstablecido.setText("Postal Code:");

        // NEGOCIO
        txtNombreNegocio.setText("Business Name:");
        txtNombreActual.setText("Current name:");
        txtContacto.setText("Contact (Num/Email):");
        txtContactoActual.setText("Current contact:");
        txtLogo.setText("Upload logo:");

        if (logoLabel.getText().equals("Sin logo")) {
            logoLabel.setText("No logo");
        }

    } else {

        // BOTONES
        btnRetroceder.setText("Cerrar");
        btnGuardar.setText("Guardar");
        btnCargar.setText("Subir");
        btnEliminar.setText("Eliminar");

        // SECCIONES
        establecerDatos.setText("ESTABLECER DATOS");
        datosGuardados.setText("DATOS GUARDADOS");
        datosNegocio.setText("DATOS DEL NEGOCIO");

        // CAMPOS
        txtNombre.setText("Nombre:");
        txtApellido.setText("Apellido:");
        txtTelefono.setText("Teléfono:");
        txtMail.setText("Mail:");
        txtDocumento.setText("Documento:");

        txtDireccion.setText("Dirección:");
        txtLocalidad.setText("Localidad / Ciudad:");
        txtProvincia.setText("Provincia / Estado:");
        txtCodigoPostal.setText("Código Postal:");

        // ESTABLECIDOS
        txtNombreEstablecido.setText("Nombre:");
        txtApellidoEstablecido.setText("Apellido:");
        txtTelefonoEstablecido.setText("Teléfono:");
        txtMailEstablecido.setText("Mail:");
        txtDocumentoEstablecido.setText("Documento:");

        txtDireccionEstablecido.setText("Dirección:");
        txtLocalidadEstablecido.setText("Localidad / Ciudad:");
        txtProvinciaEstablecido.setText("Provincia / Estado:");
        txtCodigoPostalEstablecido.setText("Código Postal:");

        // NEGOCIO
        txtNombreNegocio.setText("Nombre del Negocio:");
        txtNombreActual.setText("Nombre actual:");
        txtContacto.setText("Num/Mail de Contacto:");
        txtContactoActual.setText("Contacto actual:");
        txtLogo.setText("Subir un logotipo:");

        if (logoLabel.getText().equals("No logo")) {
            logoLabel.setText("Sin logo");
        }
    }
}

    
}
