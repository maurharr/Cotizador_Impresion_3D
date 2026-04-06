package system.view;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import system.model.Data;
import system.model.Factura;
import system.model.ItemFactura;

public class FacturaFinalView {

    private VBox root;
    private int contador;
    
    Button btnCerrar = new Button(t("factura.btn.finalizar"));
    Button btnImprimir = new Button(t("factura.btn.imprimir"));
    Label tituloComprobante = new Label(t("factura.titulo.documento"));
    Label tituloNumero = new Label(t("factura.titulo.numero"));
    Label tituloPresupuesto = new Label(t("factura.titulo.tipo"));
    Label cargarCliente = new Label(t("factura.cliente.facturarA"));
    Label descripcion = new Label(t("factura.tabla.descripcion"));
    Label cantidadTxt = new Label(t("factura.tabla.cantidad"));
    Label precioTxt = new Label(t("factura.tabla.precio"));
    Label importeTxt = new Label(t("factura.tabla.importe"));
    Label subtotalTxt = new Label(t("factura.total.subtotal"));
    Label total = new Label(t("factura.total.total"));
    Label clienteRazonSocial = new Label(t("factura.cliente.razonSocial"));
    Label clienteDireccion = new Label(t("factura.cliente.direccion"));
    Label clienteLocalidad = new Label(t("factura.cliente.localidad"));
    Label clienteDocumento = new Label(t("factura.cliente.documento"));
    Label clienteMail = new Label(t("factura.cliente.mail"));
    Label clienteTelefono = new Label(t("factura.cliente.telefono"));
    
    

    public FacturaFinalView(Stage stage, Factura factura, boolean esNueva) {
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
        
        
        btnImprimir.setStyle(baseStyle +"-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +"-fx-border-color: #a0a0a0;" +"-fx-text-fill: #333;");
        btnImprimir.setEffect(ds);     
        
        btnCerrar.setStyle(baseStyle +"-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +"-fx-border-color: #a0a0a0;" +"-fx-text-fill: #333;");
        btnCerrar.setEffect(ds);  
        
        HBox botonera = new HBox(30, btnImprimir, btnCerrar);
        
        
        //--------------------------------------------------------------------------------------------------
        Label tituloLogo = new Label("Sin logo");
        tituloLogo.setPrefSize(100, 100);
        tituloLogo.setMinSize(100, 100);
        tituloLogo.setMaxSize(100, 100);
        tituloLogo.setStyle("-fx-border-color: gray; -fx-alignment: center;");
        
        if (Data.administrador != null && Data.administrador.getRutaLogo() != null) {
            File file = new File(Data.administrador.getRutaLogo());

            if (file.exists()) {
                Image imagen = new Image(file.toURI().toString());

                ImageView imageView = new ImageView(imagen);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);

                tituloLogo.setGraphic(imageView);
                tituloLogo.setText("");
            }
        }
        //--------------------------------------------------------------------------------------------------
        
        
        Label tituloNombre = new Label(Data.administrador.getNombreNegocio());
        tituloNombre.setFont(Font.font("System", FontWeight.NORMAL, 20));
        tituloNombre.setStyle("-fx-border-color: #A5B2C9;-fx-text-fill: #366092; -fx-padding: 0 0 0 5;");   
        tituloNombre.setAlignment(Pos.CENTER_LEFT); 
        tituloNombre.setPrefSize(300, 100);
        tituloNombre.setWrapText(true);
        
        
        tituloComprobante.setFont(Font.font("System", FontWeight.BOLD, 13));
        tituloComprobante.setStyle("-fx-text-fill: #366092; -fx-padding: 0 0 0 5;");
        tituloComprobante.setAlignment(Pos.CENTER_LEFT);
        tituloComprobante.setPrefSize(200, 25);
        
        
        tituloNumero.setFont(Font.font("System", FontWeight.BOLD, 13)); 
        tituloNumero.setStyle("-fx-text-fill: #366092");
        tituloNumero.setAlignment(Pos.CENTER);
        tituloNumero.setPrefSize(200, 25);
        
        
        tituloPresupuesto.setFont(Font.font("System", FontWeight.NORMAL, 20));
        tituloPresupuesto.setStyle("-fx-text-fill: #366092; -fx-padding: 0 0 0 5;");
        tituloPresupuesto.setAlignment(Pos.CENTER_LEFT);
        tituloPresupuesto.setPrefSize(200, 71);
        
       
        if (esNueva) {
            int contador = Data.getContador();
            String numeroGenerado = String.format("0001-%06d", contador);

            factura.setNumero(numeroGenerado);
            factura.setFecha(LocalDate.now());
            Data.getFacturas().add(factura);

            Data.sumarContador();
            Data.guardar();
        }

        Label tituloID = new Label(factura.getNumero());
        tituloID.setFont(Font.font("System", FontWeight.NORMAL, 20));
        tituloID.setStyle("-fx-text-fill: #366092; -fx-padding: 0 5 0 0;");
        tituloID.setAlignment(Pos.CENTER_RIGHT);
        tituloID.setPrefSize(200, 71);
        
        
        HBox parteNombre = new HBox(tituloLogo, tituloNombre);
        HBox parteComprobante = new HBox(tituloComprobante, tituloNumero);
        parteComprobante.setStyle("-fx-border-color: #A5B2C9;-fx-text-fill: #366092;-fx-border-width: 1;");
        HBox partePresupuesto = new HBox(tituloPresupuesto, tituloID);
        partePresupuesto.setStyle("-fx-border-color: #A5B2C9;-fx-text-fill: #366092;-fx-border-width: 1;");
        VBox CuadroComprobante = new VBox(parteComprobante, partePresupuesto);
        HBox parteTitulo = new HBox(parteNombre, CuadroComprobante);
        parteTitulo.setMaxWidth(800);
        
        
        //--------------------------------------------------------------------------------------------------
        
        
        LocalDate fechaFactura = factura.getFecha();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Label fecha = new Label(fechaFactura.format(formato));
        fecha.setFont(Font.font("System", FontWeight.NORMAL, 15));
        fecha.setStyle("-fx-border-color: #A5B2C9; -fx-padding: 0 5 0 0;");
        fecha.setPrefHeight(25);
        fecha.setMaxWidth(800);
        fecha.setAlignment(Pos.CENTER_RIGHT);

        
        
        //--------------------------------------------------------------------------------------------------
        
        Label nombre = new Label(Data.administrador.getApellido() + ", " + Data.administrador.getNombre());
        nombre.setFont(Font.font("System", FontWeight.NORMAL, 13)); 
        nombre.setPrefWidth(400);
        nombre.setAlignment(Pos.CENTER_RIGHT);
        
        Label direccion = new Label(Data.administrador.getDireccion() + ", " + Data.administrador.getBarrio() + ", " + Data.administrador.getProvincia() + ", " + Data.administrador.getCodigoPostal());
        direccion.setFont(Font.font("System", FontWeight.NORMAL, 13)); 
        direccion.setPrefWidth(400);
        direccion.setAlignment(Pos.CENTER_RIGHT);
        
        Label documento = new Label("Doc. N.°: " + Data.administrador.getDocumento());
        documento.setFont(Font.font("System", FontWeight.NORMAL, 13)); 
        documento.setPrefWidth(400);
        documento.setAlignment(Pos.CENTER_RIGHT);        
        
        Label telefono = new Label("Teléfono: " + Data.administrador.getTelefono());
        telefono.setFont(Font.font("System", FontWeight.NORMAL, 13));  
        telefono.setPrefWidth(400); 
        telefono.setAlignment(Pos.CENTER_LEFT);
        
        Label mail = new Label(Data.administrador.getMail());
        mail.setFont(Font.font("System", FontWeight.NORMAL, 13));
        mail.setPrefWidth(400);       
        mail.setAlignment(Pos.CENTER_LEFT);        
    
        VBox datos1 = new VBox(nombre, direccion, documento);
        datos1.setStyle("-fx-border-color: #A5B2C9;-fx-text-fill: #366092;-fx-padding: 0 5 0 0;");
        datos1.setMaxWidth(401);
        
        VBox datos2 = new VBox(telefono, mail);
        datos2.setStyle("-fx-border-color: #A5B2C9;-fx-text-fill: #366092;-fx-padding: 0 0 0 5;");
        datos2.setMaxWidth(401);
        
        HBox datos = new HBox(datos1, datos2);
        datos.setMaxWidth(800);
        datos.setPrefHeight(75);
        
        //--------------------------------------------------------------------------------------------------
        
        Label barra = new Label("");
        barra.setFont(Font.font("System", FontWeight.BOLD, 13));
        barra.setPrefSize(800,25); 
        
        
        cargarCliente.setFont(Font.font("System", FontWeight.BOLD, 13));
        cargarCliente.setStyle("-fx-border-color: #A5B2C9; -fx-background-color: #366092; -fx-text-fill: white;"); 
        cargarCliente.setPrefSize(800,27);
          
        //--------------------------------------------------------------------------------------------------
        
        
        clienteRazonSocial.setFont(Font.font("System", FontWeight.BOLD, 13));   
        clienteRazonSocial.setPrefWidth(120);
        
        
        Label rptaClienteRazonSocial = new Label(factura.getCliente().getRazonSocial());
        rptaClienteRazonSocial.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaClienteRazonSocial.setPrefWidth(300);  
        rptaClienteRazonSocial.setAlignment(Pos.CENTER_LEFT);
        HBox dataClienteRazonSocial = new HBox(clienteRazonSocial, rptaClienteRazonSocial);
        
        
        clienteDireccion.setFont(Font.font("System", FontWeight.BOLD, 13));
        clienteDireccion.setPrefWidth(120);
        
        Label rptaClienteDireccion = new Label(factura.getCliente().getDireccion());
        rptaClienteDireccion.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaClienteDireccion.setPrefWidth(300);  
        rptaClienteDireccion.setAlignment(Pos.CENTER_LEFT);
        HBox dataClienteDireccion = new HBox(clienteDireccion, rptaClienteDireccion);
        
        
        clienteLocalidad.setFont(Font.font("System", FontWeight.BOLD, 13));
        clienteLocalidad.setPrefWidth(120);
        
        Label rptaclienteLocalidad = new Label(factura.getCliente().getLocalidad());
        rptaclienteLocalidad.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaclienteLocalidad.setPrefWidth(300);  
        rptaclienteLocalidad.setAlignment(Pos.CENTER_LEFT);
        
        
        HBox dataClienteLocalidad = new HBox(clienteLocalidad, rptaclienteLocalidad);        
        VBox cliente1 = new VBox(5,dataClienteRazonSocial, dataClienteDireccion, dataClienteLocalidad);
        cliente1.setPrefWidth(470);
        
        
        clienteDocumento.setFont(Font.font("System", FontWeight.BOLD, 13));
        clienteDocumento.setPrefWidth(170);
           
        Label rptaClienteDocumento = new Label(factura.getCliente().getDocumento());
        rptaClienteDocumento.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaClienteDocumento.setPrefWidth(300);  
        rptaClienteDocumento.setAlignment(Pos.CENTER_LEFT);
        HBox dataClienteDocumento = new HBox(clienteDocumento, rptaClienteDocumento);
        
        
        clienteMail.setFont(Font.font("System", FontWeight.BOLD, 13));
        clienteMail.setPrefWidth(170);
           
        Label rptaClienteMail = new Label(factura.getCliente().getMail());
        rptaClienteMail.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaClienteMail.setPrefWidth(300);  
        rptaClienteMail.setAlignment(Pos.CENTER_LEFT);
        HBox dataClienteMail = new HBox(clienteMail, rptaClienteMail);
        
        
        clienteTelefono.setFont(Font.font("System", FontWeight.BOLD, 13));
        clienteTelefono.setPrefWidth(170);
        
        Label rptaClienteTelefono = new Label(factura.getCliente().getTelefono());
        rptaClienteTelefono.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaClienteTelefono.setPrefWidth(300);   
        rptaClienteTelefono.setAlignment(Pos.CENTER_LEFT);  
        HBox dataClienteTelefono = new HBox(clienteTelefono, rptaClienteTelefono);
        
        
        VBox cliente2 = new VBox(5,dataClienteDocumento,dataClienteMail,dataClienteTelefono);
        HBox ingresoCliente = new HBox(cargarCliente);
        HBox dataCliente = new HBox(cliente1, cliente2);
        VBox parteCliente = new VBox(barra, ingresoCliente, dataCliente);
        parteCliente.setStyle("-fx-border-color: #A5B2C9;");
        parteCliente.setMaxWidth(800);
        parteCliente.setPrefHeight(125);
        
        //--------------------------------------------------------------------------------------------------
        
        Label barra2 = new Label("");
        barra2.setFont(Font.font("System", FontWeight.BOLD, 13));
        barra2.setPrefSize(800,25);  
        barra2.setStyle("-fx-border-color: transparent transparent #A5B2C9 transparent;");
        
        //--------------------------------------------------------------------------------------------------

        descripcion.setFont(Font.font("System", FontWeight.NORMAL, 15));
        descripcion.setStyle("-fx-padding: 0 0 0 5;");
        descripcion.setAlignment(Pos.CENTER_LEFT);
        descripcion.setPrefSize(400, 35);

        cantidadTxt.setFont(Font.font("System", FontWeight.NORMAL, 15)); 
        cantidadTxt.setAlignment(Pos.CENTER);
        cantidadTxt.setPrefSize(100, 35);

        precioTxt.setFont(Font.font("System", FontWeight.NORMAL, 15));
        precioTxt.setAlignment(Pos.CENTER_RIGHT);
        precioTxt.setPrefSize(150, 35);

        importeTxt.setFont(Font.font("System", FontWeight.NORMAL, 15)); 
        importeTxt.setStyle("-fx-padding: 0 5 0 0;");
        importeTxt.setAlignment(Pos.CENTER_RIGHT);
        importeTxt.setPrefSize(150, 35);
        
        HBox columnaImporte = new HBox(descripcion, cantidadTxt, precioTxt, importeTxt);
        columnaImporte.setMaxWidth(800);        
        
        //--------------------------------------------------------------------------------------------------
        
        VBox lista = new VBox(5);

        for (ItemFactura item : factura.getItems()) {

            Label rptaDescripcion = new Label(item.getDescripcion());
            rptaDescripcion.setFont(Font.font("System", FontWeight.NORMAL, 13));
            rptaDescripcion.setStyle("-fx-padding: 0 0 0 5;-fx-background-color: white;");
            rptaDescripcion.setAlignment(Pos.TOP_LEFT);
            rptaDescripcion.setPrefSize(400, 20);

            Label rptaCantidad = new Label(String.valueOf(item.getCantidad()));
            rptaCantidad.setFont(Font.font("System", FontWeight.NORMAL, 13));
            rptaCantidad.setAlignment(Pos.TOP_CENTER);
            rptaCantidad.setPrefSize(100, 20);

            Label rptaPrecio = new Label("$" + String.format("%.2f", item.getPrecioUnitario()));
            rptaPrecio.setFont(Font.font("System", FontWeight.NORMAL, 13));
            rptaPrecio.setAlignment(Pos.TOP_RIGHT);
            rptaPrecio.setPrefSize(150, 20);

            Label rptaImporte = new Label("$" + String.format("%.2f", item.getImporte()) + " ");
            rptaImporte.setFont(Font.font("System", FontWeight.NORMAL, 13));
            rptaImporte.setAlignment(Pos.TOP_RIGHT);
            rptaImporte.setPrefSize(150, 20);

            HBox fila = new HBox(
                rptaDescripcion,
                rptaCantidad,
                rptaPrecio,
                rptaImporte
            );

            fila.setMaxWidth(800);

            lista.getChildren().add(fila);
        }

        VBox parteDescripcion = new VBox(barra2, columnaImporte, lista);
        parteDescripcion.setMaxHeight(350);
        parteDescripcion.setPrefHeight(350);
        
        //--------------------------------------------------------------------------------------------------
        
        Label nota = new Label(t("factura.total.nota"));
        nota.setFont(Font.font("System", FontWeight.BOLD, 13));
        nota.setAlignment(Pos.CENTER_LEFT);
        nota.setPrefSize(50, 29);       

        Label promptNota = new Label(factura.getNota());
        promptNota.setFont(Font.font("System", FontWeight.NORMAL, 13));
        promptNota.setAlignment(Pos.TOP_LEFT);
        promptNota.setPrefSize(498, 116);
        promptNota.setMaxWidth(498);
        promptNota.setStyle("-fx-background-color: white;-fx-padding: 5 0 0 0;");
        promptNota.setWrapText(true);
        
        subtotalTxt.setFont(Font.font("System", FontWeight.NORMAL, 13));
        subtotalTxt.setAlignment(Pos.CENTER_LEFT);
        subtotalTxt.setPrefSize(150, 29);    
        subtotalTxt.setStyle("-fx-background-color: #B8CCE4;"); 
        
        Label rptaSubtotal = new Label(String.format("$%.2f", factura.getSubtotal()));
        rptaSubtotal.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaSubtotal.setAlignment(Pos.CENTER_RIGHT);
        rptaSubtotal.setPrefSize(100, 29);
        rptaSubtotal.setStyle("-fx-background-color: #DBE5F1;-fx-padding: 0 5 0 0;");
        
        Label porcentajeImpuesto = new Label(t("factura.total.impuestoPorcentaje"));
        porcentajeImpuesto.setFont(Font.font("System", FontWeight.NORMAL, 13));
        porcentajeImpuesto.setAlignment(Pos.CENTER_LEFT);
        porcentajeImpuesto.setPrefSize(150, 29);
        porcentajeImpuesto.setStyle("-fx-background-color: #B8CCE4;"); 
           
        Label porcentajeSeteado = new Label(String.valueOf(factura.getImpuestoSeteado() + "%" + " "));
        porcentajeSeteado.setFont(Font.font("System", FontWeight.NORMAL, 13));
        porcentajeSeteado.setAlignment(Pos.CENTER_RIGHT);
        porcentajeSeteado.setPrefSize(100, 29);
        porcentajeSeteado.setStyle("-fx-background-color: #DBE5F1;");
        
        Label montoImpuesto = new Label(t("factura.total.impuestoMonto"));
        montoImpuesto.setFont(Font.font("System", FontWeight.NORMAL, 13));
        montoImpuesto.setAlignment(Pos.CENTER_LEFT);
        montoImpuesto.setPrefSize(150, 29);
        montoImpuesto.setStyle("-fx-background-color: #B8CCE4;"); 

        Label rptaMontoImpuesto = new Label(String.format("$%.2f", factura.getImpuesto()));
        rptaMontoImpuesto.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaMontoImpuesto.setAlignment(Pos.CENTER_RIGHT);
        rptaMontoImpuesto.setPrefSize(100, 29);
        rptaMontoImpuesto.setStyle("-fx-background-color: #DBE5F1;-fx-padding: 0 5 0 0;");
        
        total.setFont(Font.font("System", FontWeight.BOLD, 13));
        total.setAlignment(Pos.CENTER_LEFT);
        total.setPrefSize(150, 29);
        total.setStyle("-fx-background-color: #B8CCE4;"); 
        
        Label rptaTotal = new Label(String.format("$%.2f", factura.getTotal()));
        rptaTotal.setFont(Font.font("System", FontWeight.BOLD, 13));
        rptaTotal.setAlignment(Pos.CENTER_RIGHT);
        rptaTotal.setPrefSize(100, 29);
        rptaTotal.setStyle("-fx-padding: 0 5 0 0;-fx-background-color: #DBE5F1;");
        
        VBox cuadroNota = new VBox(nota);
        VBox cuadroNotaPrompt = new VBox(promptNota);
        VBox cuadroTotales = new VBox(subtotalTxt, porcentajeImpuesto, montoImpuesto, total);
        VBox cuadroRptaTotales = new VBox(rptaSubtotal, porcentajeSeteado, rptaMontoImpuesto, rptaTotal);
        
        HBox conjuntoFinal = new HBox(cuadroNota, cuadroNotaPrompt, cuadroTotales, cuadroRptaTotales);
        conjuntoFinal.setMaxHeight(800);
        conjuntoFinal.setStyle("-fx-border-color: #A5B2C9;");

        //--------------------------------------------------------------------------------------------------
        
        Label comentario = new Label(t("factura.mensaje.contacto") + Data.administrador.getContacto());
        comentario.setFont(Font.font("System", FontWeight.NORMAL, 13));
        
        Label comentario2 = new Label(t("factura.mensaje.gracias"));
        comentario2.setFont(Font.font("System", FontWeight.BOLD, 13));
        
        VBox comentarioV = new VBox(comentario, comentario2);
        comentarioV.setAlignment(Pos.CENTER); // centra los labels dentro del VBox
        comentarioV.setFillWidth(true); // opcional

        HBox comentarioBox = new HBox(comentarioV);
        comentarioBox.setStyle("-fx-border-color: #A5B2C9;");
        comentarioBox.setAlignment(Pos.CENTER);
        comentarioBox.setPrefSize(800, 75);
        comentarioBox.setMaxWidth(800);

        comentarioV.setPrefHeight(Double.MAX_VALUE);
        HBox.setHgrow(comentarioV, Priority.ALWAYS);

        
        btnImprimir.setOnMouseEntered(e ->
            btnImprimir.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#f0f0f0, #dcdcdc);" +
                "-fx-border-color: #909090;" +
                "-fx-text-fill: #222;"
        ));

        btnImprimir.setOnMouseExited(e ->
            btnImprimir.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +
                "-fx-border-color: #a0a0a0;" +
                "-fx-text-fill: #333;"
        ));   
        
        
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
        
        
        
        btnCerrar.setOnAction(e -> {
            ((Stage) btnCerrar.getScene().getWindow()).close();
        });        
        
        //--------------------------------------------------------------------------------------------------
        
        VBox facturaF = new VBox(parteTitulo, fecha, datos, parteCliente, parteDescripcion, conjuntoFinal, comentarioBox);
        facturaF.setStyle("-fx-background-color: white;");
        HBox facturaCompleta = new HBox(facturaF);
        facturaCompleta.setStyle("-fx-alignment: center;");
        facturaCompleta.setMaxSize(800, 900);
        facturaCompleta.setStyle("-fx-border-color: #A5B2C9;");
        botonera.setAlignment(Pos.CENTER);
        facturaCompleta.setEffect(ds);
        
        btnImprimir.setOnAction(e -> imprimir(facturaCompleta));
        
        root.getChildren().addAll(facturaCompleta, botonera);

     
    }

    public VBox getRoot() {
        return root;
    }
    
    public void imprimir(Node nodo) {

        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null && job.showPrintDialog(nodo.getScene().getWindow())) {

            // 🔥 Usar configuración REAL elegida por el usuario
            PageLayout layout = job.getJobSettings().getPageLayout();

            // 🔧 Forzar layout correcto
            nodo.applyCss();
            if (nodo instanceof Parent) {
                ((Parent) nodo).layout();
            }

            // 🔥 Calidad
            double scaleFactor = 3.0; // 2.0 recomendado

            SnapshotParameters params = new SnapshotParameters();
            params.setTransform(new Scale(scaleFactor, scaleFactor));

            WritableImage snapshot = nodo.snapshot(params, null);

            ImageView imageView = new ImageView(snapshot);

            double printableWidth = layout.getPrintableWidth();
            double printableHeight = layout.getPrintableHeight();

            double imageWidth = snapshot.getWidth();
            double imageHeight = snapshot.getHeight();

            // 📏 Escalado dinámico (cualquier hoja)
            double scale = Math.min(
                printableWidth / imageWidth,
                printableHeight / imageHeight
            ) * 0.95; // % de uso

            imageView.getTransforms().add(new Scale(scale, scale));

            // 🎯 Centrado automático
            imageView.setTranslateX((printableWidth - imageWidth * scale) / 2);
            imageView.setTranslateY((printableHeight - imageHeight * scale) / 2);

            boolean success = job.printPage(imageView);

            if (success) {
                job.endJob();
            }
        }
    }
    
    private String t(String clave) {

        switch (Data.administrador.getIdioma()) { // o donde estés guardando el idioma

            case "ES" -> {
                switch (clave) {

                    case "factura.btn.agregar" -> {
                        return "Agregar otro producto";
                    }
                    case "factura.btn.finalizar" -> {
                        return "Cerrar";
                    }
                    case "factura.btn.cancelar" -> {
                        return "Cancelar";
                    }
                    
                    case "factura.titulo.documento" -> {
                        return "COMPROBANTE";
                    }
                    case "factura.titulo.numero" -> {
                        return "NÚMERO";
                    }
                    case "factura.titulo.tipo" -> {
                        return "Presupuesto";
                    }
                    
                    case "factura.cliente.facturarA" -> {
                        return " FACTURAR A: ";
                    }
                    case "factura.cliente.razonSocial" -> {
                        return " Razón Social: ";
                    }
                    case "factura.cliente.direccion" -> {
                        return " Dirección: ";
                    }
                    case "factura.cliente.localidad" -> {
                        return " Localidad: ";
                    }
                    case "factura.cliente.documento" -> {
                        return "Documento:";
                    }
                    case "factura.cliente.mail" -> {
                        return "Correo electrónico:";
                    }
                    case "factura.cliente.telefono" -> {
                        return "Teléfono:";
                    }
                    
                    case "factura.tabla.descripcion" -> {
                        return " DESCRIPCIÓN / RENGLÓN";
                    }
                    case "factura.tabla.cantidad" -> {
                        return "CANTIDAD";
                    }
                    case "factura.tabla.precio" -> {
                        return "PRECIO";
                    }
                    case "factura.tabla.importe" -> {
                        return "IMPORTE";
                    }
                    case "factura.tabla.agregarItem" -> {
                        return "Agregar ítem manual";
                    }
                    
                    case "factura.total.nota" -> {
                        return " NOTA:";
                    }
                    case "factura.total.subtotal" -> {
                        return " SUBTOTAL";
                    }
                    case "factura.total.impuestoPorcentaje" -> {
                        return " % IMPUESTO";
                    }
                    case "factura.total.impuestoMonto" -> {
                        return " MONTO DE IMPUESTOS";
                    }
                    case "factura.total.total" -> {
                        return " TOTAL";
                    }
                    
                    case "factura.mensaje.contacto" -> {
                        return "Si tiene alguna duda sobre esta factura, póngase en contacto: ";
                    }
                    case "factura.mensaje.gracias" -> {
                        return "Muchas gracias por su confianza.";
                    }
                    
                    case "factura.validacion.cliente" -> {
                        return "Selecciona un cliente.";
                    }
                    case "factura.validacion.items" -> {
                        return "Agrega al menos un ítem.";
                    }
                    case "factura.validacion.limite" -> {
                        return "Solo podés agregar hasta 10 ítems.";
                    }
                    case "factura.alerta.advertencia" -> {
                        return "ADVERTENCIA"; 
                    }
                    case "factura.label.documento" -> {
                        return "Documento: " + Data.administrador.getDocumento();
                    }
                    case "factura.label.telefono" -> {
                        return "Teléfono: " + Data.administrador.getTelefono();
                    }
                    case "factura.label.sinLogo" -> {
                        return "Sin logo";
                    }
                    case "factura.tabla.nuevoProducto" -> {
                        return "Nuevo producto";
                    }
                    case "factura.btn.imprimir" -> {
                        return "Imprimir";
                    }
                }
            }
            case "EN" -> {
                switch (clave) {
                    case "factura.tabla.nuevoProducto" -> {
                        return "New item";
                    }
                    case "factura.btn.agregar" -> {
                        return "Add another item";
                    }
                    case "factura.btn.finalizar" -> {
                        return "Close";
                    }
                    case "factura.btn.imprimir" -> {
                        return "Print";
                    }
                    case "factura.btn.cancelar" -> {
                        return "Cancel";
                    }
                    
                    case "factura.titulo.documento" -> {
                        return "INVOICE";
                    }
                    case "factura.titulo.numero" -> {
                        return "NUMBER";
                    }
                    case "factura.titulo.tipo" -> {
                        return "QUOTE";
                    }
                    
                    case "factura.cliente.facturarA" -> {
                        return " BILL TO: ";
                    }
                    case "factura.cliente.razonSocial" -> {
                        return " Business Name: ";
                    }
                    case "factura.cliente.direccion" -> {
                        return " Address: ";
                    }
                    case "factura.cliente.localidad" -> {
                        return " City: ";
                    }
                    case "factura.cliente.documento" -> {
                        return "ID Number:";
                    }
                    case "factura.cliente.mail" -> {
                        return "Email:";
                    }
                    case "factura.cliente.telefono" -> {
                        return "Phone:";
                    }
                    
                    case "factura.tabla.descripcion" -> {
                        return " DESCRIPTION";
                    }
                    case "factura.tabla.cantidad" -> {
                        return "QUANTITY";
                    }
                    case "factura.tabla.precio" -> {
                        return "PRICE";
                    }
                    case "factura.tabla.importe" -> {
                        return "AMOUNT";
                    }
                    case "factura.tabla.agregarItem" -> {
                        return "Add manual item";
                    }
                    
                    case "factura.total.nota" -> {
                        return " NOTE:";
                    }
                    case "factura.total.subtotal" -> {
                        return " SUBTOTAL";
                    }
                    case "factura.total.impuestoPorcentaje" -> {
                        return " TAX (%)";
                    }
                    case "factura.total.impuestoMonto" -> {
                        return " TAX AMOUNT";
                    }
                    case "factura.total.total" -> {
                        return " TOTAL";
                    }
                    
                    case "factura.mensaje.contacto" -> {
                        return "If you have any questions about this invoice, please contact us at: ";
                    }
                    case "factura.mensaje.gracias" -> {
                        return "We appreciate your business.";
                    }
                    
                    case "factura.validacion.cliente" -> {
                        return "Please select a client.";
                    }
                    case "factura.validacion.items" -> {
                        return "Please add at least one item.";
                    }
                    case "factura.validacion.limite" -> {
                        return "You can only add up to 10 items.";
                    }
                    case "factura.alerta.advertencia" -> { 
                        return "WARNING"; 
                    }
                    case "factura.label.documento" -> {
                        return "ID Number: " + Data.administrador.getDocumento();
                    }
                    case "factura.label.telefono" -> {
                        return "Phone: " + Data.administrador.getTelefono();
                    }
                    case "factura.label.sinLogo" -> {
                        return "No logo";
                    }
                }
            }

        }
        // o donde estés guardando el idioma
        // 🇪🇸 ESPAÑOL
        // 🇺🇸 INGLÉS

        return clave; // fallback
    }
    
}
