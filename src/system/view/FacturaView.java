package system.view;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;
import system.controller.Controlador;
import system.model.Data;
import system.model.Cliente;
import system.model.Factura;
import system.model.ItemFactura;

public class FacturaView extends Controlador {

    private VBox root;
    private int contador;
    private Label rptaSubtotal, rptaMontoImpuesto, rptaTotal;
    private VBox listaItems = new VBox(2);
    private TextField promptPorcentajeImpuesto;
    private Cliente clienteSeleccionado;
    private TextArea promptNota;
    
    Button btnAgregar = new Button(t("factura.btn.agregar"));
    Button btnFinalizar = new Button(t("factura.btn.finalizar"));
    Button btnSalir = new Button(t("factura.btn.cancelar"));
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
    
    
    

    private ObservableList<ItemFactura> items = FXCollections.observableArrayList();
    
    public FacturaView(Stage stage) {
        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setOnMouseClicked(e -> {
            root.requestFocus(); 
        });        
        //--------------------------------------------------------------------------------

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
        
        
        btnAgregar.setStyle(baseStyle +"-fx-background-color: linear-gradient(#4a77b8, #2f5f9e);" +"-fx-border-color: #244a7c;" +"-fx-text-fill: white;");
        btnAgregar.setEffect(ds);
        
        
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
        
        btnFinalizar.setStyle(baseStyle +"-fx-background-color: linear-gradient(#7ecb5a, #5da93c);" +"-fx-border-color: #4a8d30;" +"-fx-text-fill: white;");
        btnFinalizar.setEffect(ds);
        
        
        btnFinalizar.setOnMouseEntered(e ->
            btnFinalizar.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#8fdb6a, #6db94c);" +
                "-fx-border-color: #3f7c28;" +
                "-fx-text-fill: white;"
        ));

        btnFinalizar.setOnMouseExited(e ->
            btnFinalizar.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#7ecb5a, #5da93c);" +
                "-fx-border-color: #4a8d30;" +
                "-fx-text-fill: white;"
        ));
        

        btnSalir.setStyle(baseStyle +"-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +"-fx-border-color: #a0a0a0;" +"-fx-text-fill: #333;");
        btnSalir.setEffect(ds);
        
        
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
        
        HBox botonera = new HBox(50, btnSalir, btnAgregar, btnFinalizar);
        
        // Label que actuará como contenedor
        Label tituloLogo = new Label(t("factura.label.sinLogo"));
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
        
        contador = Data.getContador();
        Label tituloID = new Label(String.format("0001-%06d", contador));
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
        
        //--------------------------------------------------------------------------------
        
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Label fecha = new Label(hoy.format(formato));
        fecha.setFont(Font.font("System", FontWeight.NORMAL, 15));
        fecha.setStyle("-fx-border-color: #A5B2C9; -fx-padding: 0 5 0 0;");
        fecha.setPrefHeight(25);
        fecha.setMaxWidth(800);
        fecha.setAlignment(Pos.CENTER_RIGHT);
        
        //--------------------------------------------------------------------------------
 
        Label nombre = new Label(Data.administrador.getApellido() + ", " + Data.administrador.getNombre());
        nombre.setFont(Font.font("System", FontWeight.NORMAL, 13)); 
        nombre.setPrefWidth(400);
        nombre.setAlignment(Pos.CENTER_RIGHT);
        
        Label direccion = new Label(Data.administrador.getDireccion() + ", " + Data.administrador.getBarrio() + ", " + Data.administrador.getProvincia() + ", C" + Data.administrador.getCodigoPostal());
        direccion.setFont(Font.font("System", FontWeight.NORMAL, 13)); 
        direccion.setPrefWidth(400);
        direccion.setAlignment(Pos.CENTER_RIGHT);
        
        Label documento = new Label(t("factura.label.documento"));
        documento.setFont(Font.font("System", FontWeight.NORMAL, 13)); 
        documento.setPrefWidth(400);
        documento.setAlignment(Pos.CENTER_RIGHT);        
        
        Label telefono = new Label(t("factura.label.telefono"));
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
        
        //--------------------------------------------------------------------------------
        
        Label barra = new Label("");
        barra.setFont(Font.font("System", FontWeight.BOLD, 13));
        barra.setPrefSize(800,25);  
        
        //--------------------------------------------------------------------------------
        
        cargarCliente.setFont(Font.font("System", FontWeight.BOLD, 13));
        cargarCliente.setStyle("-fx-border-color: #A5B2C9; -fx-background-color: #366092; -fx-text-fill: white;"); 
        cargarCliente.setPrefSize(200,27);
        
        TextField clientes = new TextField();
        clientes.setPrefSize(200,27);
        clientes.setStyle("-fx-border-color: #A5B2C9;");

        ListView<Cliente> sugerencias = new ListView<>();
        sugerencias.setPrefWidth(200);
        sugerencias.setPrefHeight(100);
        
        Popup popup = new Popup();
        popup.getContent().add(sugerencias);
        popup.setAutoHide(true);
        clientes.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.isEmpty()) {
                popup.hide();
                return;
            }
            ObservableList<Cliente> filtradas = FXCollections.observableArrayList();
            String busqueda = newVal.toLowerCase();
            for (Cliente imp : Data.getClientes()) {

                boolean coincideNombre = imp.getRazonSocial().toLowerCase().contains(busqueda);

                boolean coincideDocumento = imp.getDocumento() != null &&
                                            imp.getDocumento().toLowerCase().contains(busqueda);

                if (coincideNombre || coincideDocumento) {
                    filtradas.add(imp);
                }
            }
            if (filtradas.isEmpty()) {
                popup.hide();
                return;
            }
            sugerencias.setItems(filtradas);

            // Posicionar debajo del TextField
            double x = clientes.localToScreen(0, 0).getX();
            double y = clientes.localToScreen(0, 0).getY() + clientes.getHeight();

            popup.show(clientes, x, y);
        });
        
        VBox clienteBox = new VBox(clientes);
        Label vacio = new Label("");
        vacio.setFont(Font.font("System", FontWeight.BOLD, 13));
        vacio.setStyle("-fx-border-color: #A5B2C9; -fx-background-color: #366092;"); 
        vacio.setPrefSize(400,27);        
        
        //--------------------------------
        
        clienteRazonSocial.setFont(Font.font("System", FontWeight.BOLD, 13));   
        clienteRazonSocial.setPrefWidth(120);
                
        Label rptaClienteRazonSocial = new Label();
        rptaClienteRazonSocial.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaClienteRazonSocial.setPrefWidth(300);  
        rptaClienteRazonSocial.setAlignment(Pos.CENTER_LEFT);
        HBox dataClienteRazonSocial = new HBox(clienteRazonSocial, rptaClienteRazonSocial);
       
        clienteDireccion.setFont(Font.font("System", FontWeight.BOLD, 13));
        clienteDireccion.setPrefWidth(120);
        
        Label rptaClienteDireccion = new Label();
        rptaClienteDireccion.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaClienteDireccion.setPrefWidth(300);  
        rptaClienteDireccion.setAlignment(Pos.CENTER_LEFT);
        HBox dataClienteDireccion = new HBox(clienteDireccion, rptaClienteDireccion);
        
        clienteLocalidad.setFont(Font.font("System", FontWeight.BOLD, 13));
        clienteLocalidad.setPrefWidth(120);
        
        Label rptaclienteLocalidad = new Label("");
        rptaclienteLocalidad.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaclienteLocalidad.setPrefWidth(300);  
        rptaclienteLocalidad.setAlignment(Pos.CENTER_LEFT);
        
        HBox dataClienteLocalidad = new HBox(clienteLocalidad, rptaclienteLocalidad);        
        VBox cliente1 = new VBox(5,dataClienteRazonSocial, dataClienteDireccion, dataClienteLocalidad);
        cliente1.setPrefWidth(470);
        
        clienteDocumento.setFont(Font.font("System", FontWeight.BOLD, 13));
        clienteDocumento.setPrefWidth(170);
           
        Label rptaClienteDocumento = new Label();
        rptaClienteDocumento.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaClienteDocumento.setPrefWidth(300);  
        rptaClienteDocumento.setAlignment(Pos.CENTER_LEFT);
        HBox dataClienteDocumento = new HBox(clienteDocumento, rptaClienteDocumento);
        
        clienteMail.setFont(Font.font("System", FontWeight.BOLD, 13));
        clienteMail.setPrefWidth(170);
           
        Label rptaClienteMail = new Label();
        rptaClienteMail.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaClienteMail.setPrefWidth(300);  
        rptaClienteMail.setAlignment(Pos.CENTER_LEFT);
        HBox dataClienteMail = new HBox(clienteMail, rptaClienteMail);
        
        clienteTelefono.setFont(Font.font("System", FontWeight.BOLD, 13));
        clienteTelefono.setPrefWidth(170);
        
        Label rptaClienteTelefono = new Label();
        rptaClienteTelefono.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaClienteTelefono.setPrefWidth(300);   
        rptaClienteTelefono.setAlignment(Pos.CENTER_LEFT);  
        HBox dataClienteTelefono = new HBox(clienteTelefono, rptaClienteTelefono);
        
        sugerencias.setOnMouseClicked(e -> {
            Cliente seleccionada = sugerencias.getSelectionModel().getSelectedItem();

            if (seleccionada != null) {
                clienteSeleccionado = seleccionada;
                clientes.setText("");
                rptaClienteRazonSocial.setText(seleccionada.getRazonSocial());
                rptaClienteDireccion.setText(seleccionada.getDireccion() + ", C.P.: " + seleccionada.getCodigoPostal());
                rptaclienteLocalidad.setText(seleccionada.getLocalidad() + ", " + seleccionada.getProvincia());
                rptaClienteMail.setText(seleccionada.getMail());
                rptaClienteDocumento.setText(seleccionada.getDocumento());
                rptaClienteTelefono.setText(seleccionada.getTelefono());
                popup.hide();
            }
        });
        
        VBox cliente2 = new VBox(5,dataClienteDocumento,dataClienteMail,dataClienteTelefono);
        HBox ingresoCliente = new HBox(cargarCliente, clienteBox, vacio);
        HBox dataCliente = new HBox(cliente1, cliente2);
        VBox parteCliente = new VBox(barra, ingresoCliente, dataCliente);
        parteCliente.setStyle("-fx-border-color: #A5B2C9;");
        parteCliente.setMaxWidth(800);
        parteCliente.setPrefHeight(125);
        
        //--------------------------------------------------------------------------------
        
        Label barra2 = new Label("");
        barra2.setFont(Font.font("System", FontWeight.BOLD, 13));
        barra2.setPrefSize(800,25);  
        barra2.setStyle("-fx-border-color: transparent transparent #A5B2C9 transparent;");
        
        //--------------------------------------------------------------------------------

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
        
        TextField rptaDescripcion = new TextField();
        rptaDescripcion.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaDescripcion.setStyle("-fx-padding: 0 0 0 5;-fx-background-color: white;");
        rptaDescripcion.setAlignment(Pos.TOP_LEFT);
        rptaDescripcion.setPrefSize(400, 20);
    
        Label rptaCantidad = new Label(Data.getCantidad());
        rptaCantidad.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaCantidad.setAlignment(Pos.TOP_CENTER);
        rptaCantidad.setPrefSize(100, 250);
        
        Label rptaPrecio = new Label("$"+Data.getPrecio());
        rptaPrecio.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaPrecio.setAlignment(Pos.TOP_RIGHT);
        rptaPrecio.setPrefSize(150, 250);        
        
        Label rptaImporte = new Label("$"+Data.getImporte());
        rptaImporte.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaImporte.setAlignment(Pos.TOP_RIGHT);
        rptaImporte.setPrefSize(150, 250);
        
        HBox columnaImporte = new HBox(descripcion, cantidadTxt, precioTxt, importeTxt);
        columnaImporte.setMaxWidth(800);
        
        listaItems.setMaxWidth(800);
        
        VBox parteDescripcion = new VBox(barra2, columnaImporte, listaItems);
        parteDescripcion.setMaxHeight(350);
        parteDescripcion.setPrefHeight(350);
        
        //--------------------------------------------------------------------------------
        
        Label nota = new Label(t("factura.total.nota"));
        nota.setFont(Font.font("System", FontWeight.BOLD, 13));
        nota.setAlignment(Pos.CENTER_LEFT);
        nota.setPrefSize(50, 29);       

        promptNota = new TextArea();
        promptNota.setPromptText("");
        promptNota.setFont(Font.font("System", FontWeight.NORMAL, 13));
        promptNota.setPrefSize(498, 116);
        promptNota.setMaxWidth(498);
        promptNota.setStyle("-fx-background-color: white;");
        promptNota.setWrapText(true);
        
        subtotalTxt.setFont(Font.font("System", FontWeight.NORMAL, 13));
        subtotalTxt.setAlignment(Pos.CENTER_LEFT);
        subtotalTxt.setPrefSize(150, 29);    
        subtotalTxt.setStyle("-fx-background-color: #B8CCE4;"); 
        
        rptaSubtotal = new Label("$"+Data.getImporte());
        rptaSubtotal.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaSubtotal.setAlignment(Pos.CENTER_RIGHT);
        rptaSubtotal.setPrefSize(100, 29);
        rptaSubtotal.setStyle("-fx-background-color: #DBE5F1;-fx-padding: 0 5 0 0;");
        
        Label porcentajeImpuesto = new Label(t("factura.total.impuestoPorcentaje"));
        porcentajeImpuesto.setFont(Font.font("System", FontWeight.NORMAL, 13));
        porcentajeImpuesto.setAlignment(Pos.CENTER_LEFT);
        porcentajeImpuesto.setPrefSize(150, 29);
        porcentajeImpuesto.setStyle("-fx-background-color: #B8CCE4;"); 
           
        promptPorcentajeImpuesto = new TextField("0.0%");
        promptPorcentajeImpuesto.setFont(Font.font("System", FontWeight.NORMAL, 13));
        promptPorcentajeImpuesto.setAlignment(Pos.CENTER_RIGHT);
        promptPorcentajeImpuesto.setPrefSize(100, 29);
        promptPorcentajeImpuesto.setStyle("-fx-background-color: #DBE5F1;");
        
        Label montoImpuesto = new Label(t("factura.total.impuestoMonto"));
        montoImpuesto.setFont(Font.font("System", FontWeight.NORMAL, 13));
        montoImpuesto.setAlignment(Pos.CENTER_LEFT);
        montoImpuesto.setPrefSize(150, 29);
        montoImpuesto.setStyle("-fx-background-color: #B8CCE4;"); 

        rptaMontoImpuesto = new Label("$0.00");
        rptaMontoImpuesto.setFont(Font.font("System", FontWeight.NORMAL, 13));
        rptaMontoImpuesto.setAlignment(Pos.CENTER_RIGHT);
        rptaMontoImpuesto.setPrefSize(100, 29);
        rptaMontoImpuesto.setStyle("-fx-background-color: #DBE5F1;-fx-padding: 0 5 0 0;");
        
        total.setFont(Font.font("System", FontWeight.BOLD, 13));
        total.setAlignment(Pos.CENTER_LEFT);
        total.setPrefSize(150, 29);
        total.setStyle("-fx-background-color: #B8CCE4;"); 
        
        rptaTotal = new Label("$0,00");
        rptaTotal.setFont(Font.font("System", FontWeight.BOLD, 13));
        rptaTotal.setAlignment(Pos.CENTER_RIGHT);
        rptaTotal.setPrefSize(100, 29);
        rptaTotal.setStyle("-fx-padding: 0 5 0 0;-fx-background-color: #DBE5F1;");
        
        aplicarFiltroDecimal(promptPorcentajeImpuesto);

        promptPorcentajeImpuesto.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> 
        {
            if (!isNowFocused)
            {
                actualizarTotales(promptPorcentajeImpuesto, rptaMontoImpuesto, rptaTotal);
                double porcentaje = parseDoubleSeguro(promptPorcentajeImpuesto.getText());

                if (!promptPorcentajeImpuesto.getText().endsWith("%")) {
                    promptPorcentajeImpuesto.setText(porcentaje + "%");
                }
            }
        });

        VBox cuadroNota = new VBox(nota);
        VBox cuadroNotaPrompt = new VBox(promptNota);
        VBox cuadroTotales = new VBox(subtotalTxt, porcentajeImpuesto, montoImpuesto, total);
        VBox cuadroRptaTotales = new VBox(rptaSubtotal, promptPorcentajeImpuesto, rptaMontoImpuesto, rptaTotal);

        
        HBox conjuntoFinal = new HBox(cuadroNota, cuadroNotaPrompt, cuadroTotales, cuadroRptaTotales);
        conjuntoFinal.setMaxHeight(800);
        conjuntoFinal.setStyle("-fx-border-color: #A5B2C9;");
        
        //--------------------------------------------------------------------------------
        
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
        
        //--------------------------------------------------------------------------------
        VBox factura = new VBox(parteTitulo, fecha, datos, parteCliente, parteDescripcion, conjuntoFinal, comentarioBox);
        factura.setStyle("-fx-background-color: white;");
        HBox facturaCompleta = new HBox(factura);
        facturaCompleta.setStyle("-fx-alignment: center;");
        facturaCompleta.setMaxSize(800, 900);
        facturaCompleta.setStyle("-fx-border-color: #A5B2C9;");
        botonera.setAlignment(Pos.CENTER);
        facturaCompleta.setEffect(ds);
        
        
        
        btnAgregar.setOnAction(e -> {
            abrirVentana(
                new Stage(),
                new PresupuestoView(new Stage(), this).getRoot(),
                "Nuevo Presupuesto",
                950,
                840
            );          
        });
        
        btnSalir.setOnAction(e -> {
            Stage stage1 = (Stage) btnSalir.getScene().getWindow();
            stage1.close();
        });        
        
        
        btnFinalizar.setOnAction(e -> {

            // 🔴 VALIDACIONES
            if (clienteSeleccionado == null) {
                abrirAviso(t("factura.alerta.advertencia"), t("factura.validacion.cliente"));
                return;
            }

            if (items.isEmpty()) {
                abrirAviso(t("factura.alerta.advertencia"), t("factura.validacion.items"));
                return;
            }

            // 🔢 CÁLCULOS
            double subtotalF = calcularSubtotal();
            double porcentajeF = parseDoubleSeguro(promptPorcentajeImpuesto.getText());
            double impuestoF = subtotalF * (porcentajeF / 100);
            double totalF = subtotalF + impuestoF;

            // 🧾 CREAR FACTURA
            Factura facturaF = new Factura();
            facturaF.setCliente(clienteSeleccionado);
            facturaF.setNota(promptNota.getText());
            facturaF.setItems(new ArrayList<>(items)); // copia segura
            facturaF.setSubtotal(subtotalF);
            facturaF.setImpuestoSeteado(porcentajeF);
            facturaF.setImpuesto(impuestoF);
            facturaF.setTotal(totalF);
            facturaF.setNota(promptNota.getText());

            // 🪟 ABRIR VISTA FINAL
            Stage currentStage = (Stage) btnFinalizar.getScene().getWindow();

            currentStage.getScene().setRoot(
                new FacturaFinalView(currentStage, facturaF, true).getRoot()
            );

        });
        
        
        
        //--------------------------------------------------------------------------------
        
        root.getChildren().addAll(facturaCompleta, botonera);
    }

    public VBox getRoot() {
        return root;
    }

    private void aplicarFiltroDecimal(TextField campo) {
        Pattern pattern = Pattern.compile("\\d*(\\.|,)?\\d{0,2}%?");


        UnaryOperator<TextFormatter.Change> filtro = change -> {
            String nuevoTexto = change.getControlNewText();
            return pattern.matcher(nuevoTexto).matches() ? change : null;
        };

        campo.setTextFormatter(new TextFormatter<>(filtro));
    }

    private double parseDoubleSeguro(String texto) {
        try {
            if (texto == null || texto.isEmpty()) return 0;
            return Double.parseDouble(texto.replace(",", ".").replace("%", "").replace("$", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    private String formatoMoneda(double valor) {
        return String.format("$%.2f", valor);
    }
 
    public void agregarItem(ItemFactura item) {

        if (items.size() >= 10) {
            abrirAviso("Límite alcanzado", t("factura.validacion.limite"));
            return;
        }

        items.add(item);

        actualizarVista();
    }
    
    private double calcularSubtotal() {
        double subtotal = 0;

        for (ItemFactura item : items) {
            subtotal += item.getImporte();
        }

        return subtotal;
    }
    
    private void actualizarTotales(TextField promptPorcentajeImpuesto, Label rptaMontoImpuesto, Label rptaTotal) {

        double subtotal = calcularSubtotal();
        double porcentaje = parseDoubleSeguro(promptPorcentajeImpuesto.getText());

        double impuesto = subtotal * (porcentaje / 100);
        double totalFinal = subtotal + impuesto;

        rptaSubtotal.setText(formatoMoneda(subtotal));
        rptaMontoImpuesto.setText(formatoMoneda(impuesto));
        rptaTotal.setText(formatoMoneda(totalFinal));
    }
    
    private void actualizarVista() {

        listaItems.getChildren().clear();
        double subtotal = 0;

        for (ItemFactura item : items) {

            // 🔹 Descripción (siempre editable)
            TextField desc = new TextField(item.getDescripcion());
            desc.setPrefSize(380, 20);
            desc.setStyle("-fx-padding: 0 0 0 5;-fx-background-color: white;");
            desc.textProperty().addListener((obs, oldVal, newVal) -> {
                item.setDescripcion(newVal);
            });

            Node cantNode;
            Node precioNode;

            // 🔹 IMPORTE (siempre label)
            Label importe = new Label();
            importe.setPrefSize(150, 20);
            importe.setAlignment(Pos.CENTER_RIGHT);
            importe.setStyle("-fx-padding: 0 5 0 0;-fx-background-color: white;");

            if (item.isEditable()) {

                // ✅ EDITABLES
                TextField cant = new TextField(String.valueOf(item.getCantidad()));
                cant.setPrefSize(100, 20);
                cant.setAlignment(Pos.CENTER);
                cant.setStyle("-fx-padding: 0 0 0 5;-fx-background-color: white;");

                TextField precio = new TextField(String.valueOf(item.getPrecioUnitario()));
                precio.setPrefSize(150, 20);
                precio.setAlignment(Pos.CENTER_RIGHT);
                precio.setStyle("-fx-padding: 0 0 0 5;-fx-background-color: white;");
                
                aplicarFiltroEntero(cant);
                aplicarFiltroMoneda(precio);
               
                precio.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                    if (isNowFocused && !precio.getText().startsWith("$")) {
                        precio.setText("$" + precio.getText());
                    }

                    if (!isNowFocused) {
                        try {
                            double valor = Double.parseDouble(
                                precio.getText().replace("$", "").replace(",", ".")
                            );
                            precio.setText("$" + String.format("%.2f", valor));
                        } catch (Exception e) {
                            precio.setText("$0,00");
                        }
                    }
                });
                
                Runnable recalcular = () -> {
                    try {
                        int cantidad = Integer.parseInt(cant.getText());
                        double precioUnit = Double.parseDouble(precio.getText().replace("$", "").replace(",", "."));

                        item.setCantidad(cantidad);
                        item.setPrecioUnitario(precioUnit);

                        double imp = cantidad * precioUnit;
                        item.setImporte(imp);

                        importe.setText("$" + String.format("%.2f", imp));

                        actualizarTotales(promptPorcentajeImpuesto, rptaMontoImpuesto, rptaTotal);

                    } catch (Exception e) {
                        importe.setText("$0,00");
                    }
                };

                cant.textProperty().addListener((obs, o, n) -> recalcular.run());
                precio.textProperty().addListener((obs, o, n) -> recalcular.run());

                recalcular.run();

                cantNode = cant;
                precioNode = precio;

            } else {

                // ❌ NO EDITABLES
                Label cant = new Label(String.valueOf(item.getCantidad()));
                cant.setPrefSize(100, 20);
                cant.setAlignment(Pos.CENTER);

                Label precio = new Label("$" + String.format("%.2f", item.getPrecioUnitario()));
                precio.setPrefSize(150, 20);
                precio.setAlignment(Pos.CENTER_RIGHT);

                importe.setText("$" + String.format("%.2f", item.getImporte()));

                cantNode = cant;
                precioNode = precio;
            }

            // 🔴 Botón eliminar
            Button btnEliminar = new Button("X");
            btnEliminar.setFont(Font.font("System", FontWeight.BOLD, 13));
            btnEliminar.setStyle("-fx-background-color: transparent; -fx-text-fill: red;");
            btnEliminar.setVisible(false); // oculto por defecto

            btnEliminar.setOnAction(e -> {
                items.remove(item);
                actualizarVista();
            });

            // 🧱 Fila principal
            HBox filaContenido = new HBox(desc, cantNode, precioNode, importe);
            filaContenido.setAlignment(Pos.CENTER_LEFT);
            HBox.setHgrow(desc, Priority.ALWAYS);

            // 🧱 Contenedor completo (fila + botón)
            HBox fila = new HBox(btnEliminar, filaContenido);
            fila.setAlignment(Pos.CENTER_LEFT);
            
            fila.setOnMouseEntered(e -> {
                btnEliminar.setVisible(true);
                fila.setStyle("-fx-background-color: #f5f5f5;");
            });

            fila.setOnMouseExited(e -> {
                btnEliminar.setVisible(false);
                fila.setStyle("-fx-background-color: transparent;");
            });

            // 👇 Mostrar botón al pasar el mouse
            fila.setOnMouseEntered(e -> btnEliminar.setVisible(true));
            fila.setOnMouseExited(e -> btnEliminar.setVisible(false));

            // Agregar a la lista
            listaItems.getChildren().add(fila);

            subtotal += item.getImporte();
        }

        // 🔽 BOTÓN AGREGAR
        if (items.size() < 10) {
            Button btnAgregarManual = new Button(t("factura.tabla.agregarItem"));

            btnAgregarManual.setOnAction(e -> {
                ItemFactura nuevo = new ItemFactura(t("factura.tabla.nuevoProducto"), 1, 0.0);
                nuevo.setEditable(true); // 👈 CLAVE
                items.add(nuevo);
                actualizarVista();
            });

            listaItems.getChildren().add(btnAgregarManual);
        }

        // 🔢 Totales
        rptaSubtotal.setText(formatoMoneda(subtotal));
        actualizarTotales(promptPorcentajeImpuesto, rptaMontoImpuesto, rptaTotal);
    }
    
    
    
    private void aplicarFiltroEntero(TextField campo) {
        Pattern pattern = Pattern.compile("\\d*");

        UnaryOperator<TextFormatter.Change> filtro = change -> {
            String nuevoTexto = change.getControlNewText();
            return pattern.matcher(nuevoTexto).matches() ? change : null;
        };

        campo.setTextFormatter(new TextFormatter<>(filtro));
    }
    
    private void aplicarFiltroMoneda(TextField campo) {
        Pattern pattern = Pattern.compile("\\$?\\d*(\\.|,)?\\d{0,2}");

        UnaryOperator<TextFormatter.Change> filtro = change -> {
            String nuevoTexto = change.getControlNewText();
            return pattern.matcher(nuevoTexto).matches() ? change : null;
        };

        campo.setTextFormatter(new TextFormatter<>(filtro));
    }
    
    private String t(String clave) {

        switch (Data.administrador.getIdioma()) { // o donde estés guardando el idioma

            case "ES" -> {
                switch (clave) {

                    case "factura.btn.agregar" -> {
                        return "Agregar otro producto";
                    }
                    case "factura.btn.finalizar" -> {
                        return "Finalizar";
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
                        return "Complete";
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
