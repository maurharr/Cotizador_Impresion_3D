package system.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import system.controller.Controlador;
import system.model.Data;
import system.model.Impresora;
import system.model.ItemFactura;
import system.model.Material;




public class PresupuestoView extends Controlador {
    private VBox root;
    private final NumberFormat formatoMoneda = new DecimalFormat("$ #,##0.00");
    
    
    Button btnRetroceder = new Button(t("Cancelar", "Cancel"));
    Button btnContinuar = new Button(t("Finalizar", "Finish"));
    Label txtConfig = new Label(t("CONFIGURACION", "SETTINGS"));
    Label tituloDatosTrabajo = new Label(t("DATOS DEL TRABAJO", "JOB DETAILS"));
    Label tituloOpcional = new Label(t("CONFIGURACIÓN OPCIONAL", "OPTIONAL SETTINGS"));
    Label tituloResumen = new Label(t("RESUMEN DE COSTOS", "COST SUMMARY"));
    Label tituloPrecios = new Label(t("PRECIOS", "PRICING"));
    Label txtPrecioOperario = new Label(t("PRECIO OPERARIO ($/h)", "OPERATOR COST ($/h)"));
    Label txtPrecioEnergia = new Label(t("PRECIO ENERGIA ($/kWh)", "ELECTRICITY COST ($/kWh)"));
    Label txtPrecioFilamento = new Label(t("PRECIO FILAMENTO ($/kg)", "FILAMENT PRICE ($/kg)"));
    Label txtImpresora = new Label(t("IMPRESORA", "PRINTER"));
    Label txtMaterial = new Label(t("MATERIAL", "MATERIAL"));
    Label l1 = new Label(t("CANTIDAD TOTAL DE PIEZAS", "TOTAL PARTS"));
    Label l2 = new Label(t("PIEZAS POR IMPRESIÓN (tanda)", "PARTS PER PRINT"));
    Label l3 = new Label(t("TIEMPO DE IMPRESIÓN (tanda)", "PRINT TIME (batch)"));
    Label l4 = new Label(t("PESO DE TANDA (g)", "BATCH WEIGHT (g)"));
    Label l5 = new Label(t("TIEMPO DE LAMINADO (min)", "SLICING TIME (min)"));
    Label l6 = new Label(t("TIEMPO DE PREPARACION (min)", "PREPARATION TIME (min)"));
    Label l7 = new Label(t("TIEMPO DE RETIRO (min)", "REMOVAL TIME (min)"));
    Label txtImpresionesNecesarias = new Label(t("Impresiones Necesarias", "Required prints"));
    Label txtHorasTotalesMaquina = new Label(t("Horas Totales Máquina", "Total Machine Hours"));
    Label txtMaterialTotal = new Label(t("Material Total (g)", "Total Material (g)"));
    Label txtCostoMaterial = new Label(t("Costo Material ($)", "Material Cost ($)"));
    Label txtCostoOperativo2 = new Label(t("Costo Operativo Maquina ($)", "Machine Operating Cost ($)"));
    Label txtGananciaMaquina = new Label(t("Ganancia Máquina ($)", "Machine Profit ($)"));
    Label txtTiempoOperadorTotal = new Label(t("Tiempo Operador Total (h)", "Total Operator Time (h)"));
    Label txtCostoOperador = new Label(t("Costo operador ($)", "Operator cost ($)"));
    Label txtPrecioCosto2 = new Label(t("PRECIO DE COSTO ($)", "UNIT COST ($)"));
    Label txtPrecioMinimo = new Label(t("PRECIO MINIMO ($)", "MINIMUM SELLING PRICE ($)"));
    Label txtValorAgregado = new Label(t("MARGEN (Urgencia / Valor Agregado)", "MARKUP (Urgency / Extra Value)"));
    Label txtPrecioFinal = new Label(t("PRECIO FINAL", "FINAL PRICE"));
    Label txtAmortizacion = new Label(t("AMORTIZACIÓN", "MACHINE DEPRECIATION"));
    Label txtTasaFallos = new Label(t("TASA DE FALLOS (%)", "FAILURE RATE (%)"));
    Label txtCostoOperativo = new Label(t("COSTO OPERATIVO ($/h)", "OPERATING COST ($/h)"));
    Label txtGanancia = new Label(t("GANANCIA ($/h)", "PROFIT ($/h)"));
    Label l8 = new Label(t("POSTPROCESADO UNITARIO", "UNIT POST-PROCESSING      "));
    Label l9 = new Label(t("VALOR MODELADO 3D O ARCHIVO ($)", "3D DESIGN COST ($)       "));    
    

    // 👇 DECLARÁS ACÁ
    private TextField promptPrecioOperario;
    private TextField promptPrecioEnergia;
    private TextField promptPrecioFilamento;
    private TextField promptCantidadPiezas;
    private TextField promptPiezasImpresion;
    private TextField promptHorasImpresion;
    private TextField promptMinutosImpresion;
    private TextField promptPesoTanda;
    private TextField promptTiempoLaminado;
    private TextField promptTiempoPreparacion;
    private TextField promptTiempoRetiro;
    private TextField promptHorasProcesado;
    private TextField promptMinutosProcesado;
    private TextField promptValorModelado;
    private TextField promptValorAgregado;
    private TextField promptAmortizacion;
    private TextField promptPrecioFinal;    
    private TextField promptCostoOperativo;
    private TextField promptGanancia;
    private TextField promptTasaFallos;
    private TextField promptPrecioCosto;
    private TextField promptPrecioMinimo;
    
    private ComboBox<Impresora> cbImpresoras;
    private ComboBox<Material> cbMaterial;
    
    private Label rptaImpresionesNecesarias;
    private Label rptaHorasTotales;
    private Label rptaMaterialTotal;
    private Label rptaCostoMaterial;
    private Label rptaCostoOperativo;
    private Label rptaGananciaMaquina;
    private Label rptaTiempoOperador;
    private Label rptaCostoOperador;
    
    private double precioFinal;
    
    private FacturaView facturaView;
    
    public PresupuestoView(Stage stage, FacturaView facturaView){
        this.facturaView = facturaView;
        
        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setOnMouseClicked(e -> {
            root.requestFocus(); 
        });
        // CONFIGURACION GENERAL ----------------------------------------------------------------------------------------------------------------------------------------
        
        
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

        
        btnRetroceder.setStyle(baseStyle +"-fx-background-color: linear-gradient(#e0e0e0, #cfcfcf);" +"-fx-border-color: #a0a0a0;" +"-fx-text-fill: #333;");
        btnRetroceder.setEffect(ds);
        
        btnContinuar.setStyle(baseStyle +"-fx-background-color: linear-gradient(#7ecb5a, #5da93c);" +"-fx-border-color: #4a8d30;" +"-fx-text-fill: white;");
        btnContinuar.setEffect(ds);
        
        txtConfig.setFont(Font.font("System", FontWeight.BOLD, 20));
        txtConfig.setMaxWidth(Double.MAX_VALUE);
        txtConfig.setAlignment(Pos.CENTER);
        txtConfig.setStyle(
            "-fx-background-color: #A5B2C9;" +
            "-fx-padding: 10;"
        );

        // Grid principal
        GridPane grid = new GridPane();
        grid.setHgap(30);
        grid.setVgap(10);

        // Columnas dinámicas
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);

        grid.getColumnConstraints().addAll(col1, col2);

        // ================= FILA 0 =================
        txtPrecioOperario.setFont(Font.font("System", FontWeight.BOLD, 12));

        promptPrecioOperario = new TextField(Data.getPrecioOperario());
        promptPrecioOperario.setMaxWidth(Double.MAX_VALUE);
        promptPrecioOperario.setAlignment(Pos.CENTER_RIGHT);
        promptPrecioOperario.setStyle(
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-border-color: black;"
        );
        promptPrecioOperario.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                promptPrecioOperario.setText(oldValue);
            }
        });         

        grid.add(txtPrecioOperario, 0, 0);
        grid.add(promptPrecioOperario, 1, 0);

        // ================= FILA 1 =================
        txtPrecioEnergia.setFont(Font.font("System", FontWeight.BOLD, 12));

        promptPrecioEnergia = new TextField(Data.getPrecioEnergia());
        promptPrecioEnergia.setMaxWidth(Double.MAX_VALUE);
        promptPrecioEnergia.setAlignment(Pos.CENTER_RIGHT);
        promptPrecioEnergia.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        promptPrecioEnergia.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                promptPrecioEnergia.setText(oldValue);
            }
        });  
        grid.add(txtPrecioEnergia, 0, 1);
        grid.add(promptPrecioEnergia, 1, 1);

        // ================= FILA 2 =================
        txtPrecioFilamento.setFont(Font.font("System", FontWeight.BOLD, 12));

        promptPrecioFilamento = new TextField(Data.getPrecioFilamento());
        promptPrecioFilamento.setMaxWidth(Double.MAX_VALUE);
        promptPrecioFilamento.setAlignment(Pos.CENTER_RIGHT);
        promptPrecioFilamento.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        promptPrecioFilamento.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                promptPrecioFilamento.setText(oldValue);
            }
        });  
        grid.add(txtPrecioFilamento, 0, 2);
        grid.add(promptPrecioFilamento, 1, 2);

        // ================= FILA 3 =================
        txtImpresora.setFont(Font.font("System", FontWeight.BOLD, 13));

        cbImpresoras = new ComboBox<>();
        cbImpresoras.setItems(Data.getImpresoras());
        cbImpresoras.setMaxWidth(Double.MAX_VALUE);
        cbImpresoras.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");

        grid.add(txtImpresora, 0, 3);
        grid.add(cbImpresoras, 1, 3);
        GridPane.setMargin(txtImpresora, new Insets(20, 0, 20, 0));
        GridPane.setMargin(cbImpresoras, new Insets(20, 0, 20, 0));
        
        // ================= FILA 4 =================
        txtAmortizacion.setFont(Font.font("System", FontWeight.BOLD, 12));

        promptAmortizacion = new TextField();
        promptAmortizacion.setEditable(false);
        promptAmortizacion.setMaxWidth(Double.MAX_VALUE);
        promptAmortizacion.setAlignment(Pos.CENTER_RIGHT);
        promptAmortizacion.setStyle(
            "-fx-background-color: #366092;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-border-color: black;"
        );

        grid.add(txtAmortizacion, 0, 4);
        grid.add(promptAmortizacion, 1, 4);
        GridPane.setMargin(txtAmortizacion, new Insets(10, 0, 0, 0));

        // ================= FILA 5 =================
        txtTasaFallos.setFont(Font.font("System", FontWeight.BOLD, 12));

        promptTasaFallos = new TextField();
        promptTasaFallos.setEditable(false);
        promptTasaFallos.setMaxWidth(Double.MAX_VALUE);
        promptTasaFallos.setAlignment(Pos.CENTER_RIGHT);
        promptTasaFallos.setStyle(
            "-fx-background-color: #366092;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-border-color: black;"
        );

        grid.add(txtTasaFallos, 0, 5);
        grid.add(promptTasaFallos, 1, 5);

        // ================= FILA 6 =================
        txtCostoOperativo.setFont(Font.font("System", FontWeight.BOLD, 12));

        promptCostoOperativo = new TextField();
        promptCostoOperativo.setEditable(false);
        promptCostoOperativo.setMaxWidth(Double.MAX_VALUE);
        promptCostoOperativo.setAlignment(Pos.CENTER_RIGHT);
        promptCostoOperativo.setStyle(
            "-fx-background-color: #366092;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-border-color: black;"
        );

        grid.add(txtCostoOperativo, 0, 6);
        grid.add(promptCostoOperativo, 1, 6);

        // ================= FILA 7 =================
        txtGanancia.setFont(Font.font("System", FontWeight.BOLD, 12));

        promptGanancia = new TextField();
        promptGanancia.setEditable(false);
        promptGanancia.setMaxWidth(Double.MAX_VALUE);
        promptGanancia.setAlignment(Pos.CENTER_RIGHT);
        promptGanancia.setStyle(
            "-fx-background-color: #366092;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-border-color: black;"
        );

        grid.add(txtGanancia, 0, 7);
        grid.add(promptGanancia, 1, 7);

        // ================= FILA 8 =================
        txtMaterial.setFont(Font.font("System", FontWeight.BOLD, 13));

        cbMaterial = new ComboBox<>();
        cbMaterial.setItems(Data.getMaterial());
        cbMaterial.setMaxWidth(Double.MAX_VALUE);
        cbMaterial.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");

        grid.add(txtMaterial, 0, 8);
        grid.add(cbMaterial, 1, 8);
        GridPane.setMargin(txtMaterial, new Insets(20, 0, 0, 0));
        GridPane.setMargin(cbMaterial, new Insets(20, 0, 0, 0));

        // ================= CONTENEDOR FINAL =================
        VBox contenido = new VBox(10, grid);
        contenido.setPadding(new Insets(10));

        VBox bConfiguracion = new VBox(0, txtConfig, contenido);
        bConfiguracion.setStyle(
            "-fx-border-width: 1.5px;" +
            "-fx-background-color: #BECDE8;" +
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-border-color: #A5B2C9;"
        );
        bConfiguracion.setPrefWidth(400);
        bConfiguracion.setEffect(ds);

        // DATOS DEL TRABAJO ----------------------------------------------------------------------------------------------------------------------------------------
        tituloDatosTrabajo.setFont(Font.font("System", FontWeight.BOLD, 20));
        tituloDatosTrabajo.setMaxWidth(Double.MAX_VALUE);
        tituloDatosTrabajo.setAlignment(Pos.CENTER);
        tituloDatosTrabajo.setStyle(
            "-fx-background-color: #A5B2C9;" +
            "-fx-padding: 10;"
        );

        GridPane gridTrabajo = new GridPane();
        gridTrabajo.setHgap(72);
        gridTrabajo.setVgap(10);

        gridTrabajo.getColumnConstraints().addAll(col1, col2);

        // ================= FILAS =================
        promptCantidadPiezas = new TextField("1");
        promptCantidadPiezas.setMaxWidth(Double.MAX_VALUE);
        promptCantidadPiezas.setAlignment(Pos.CENTER);
        promptCantidadPiezas.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");  
        promptCantidadPiezas.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\d*)?")) {
                promptCantidadPiezas.setText(oldValue);
            }
        });


        promptPiezasImpresion = new TextField("1");
        promptPiezasImpresion.setMaxWidth(Double.MAX_VALUE);
        promptPiezasImpresion.setAlignment(Pos.CENTER);
        promptPiezasImpresion.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;"); 
        promptPiezasImpresion.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\d*)?")) {
                promptPiezasImpresion.setText(oldValue);
            }
        });

        // Tiempo impresión
        promptHorasImpresion = new TextField("0");
        promptMinutosImpresion = new TextField("0");

        promptHorasImpresion.setPrefWidth(60);
        promptMinutosImpresion.setPrefWidth(60);
        
        promptHorasImpresion.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        promptMinutosImpresion.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");        

        promptHorasImpresion.setAlignment(Pos.CENTER);
        promptMinutosImpresion.setAlignment(Pos.CENTER);
        
        
        promptHorasImpresion.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\d*)?")) {
                promptHorasImpresion.setText(oldValue);
            }
        });
        promptMinutosImpresion.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\d*)?")) {
                promptMinutosImpresion.setText(oldValue);
            }
        });

        HBox tiempoImpresionBox = new HBox(10,
           new Label(t("Horas:", "Hours:")), promptHorasImpresion,
            new Label(t("Min:", "Min:")), promptMinutosImpresion
        );

        // Resto de campos
        promptPesoTanda = new TextField();
        promptTiempoLaminado = new TextField();
        promptTiempoPreparacion = new TextField();
        promptTiempoRetiro = new TextField();

        promptPesoTanda.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\d*)?")) {
                promptPesoTanda.setText(oldValue);
            }
        });        
        promptTiempoLaminado.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\d*)?")) {
                promptTiempoLaminado.setText(oldValue);
            }
        });        
        promptTiempoPreparacion.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\d*)?")) {
                promptTiempoPreparacion.setText(oldValue);
            }
        });      
        promptTiempoRetiro.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\d*)?")) {
                promptTiempoRetiro.setText(oldValue);
            }
        });        
        
        
        for (TextField tf : new TextField[]{
                promptPesoTanda,
                promptTiempoLaminado,
                promptTiempoPreparacion,
                promptTiempoRetiro
        }) {
            tf.setMaxWidth(Double.MAX_VALUE);
            tf.setAlignment(Pos.CENTER);
            tf.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
            tf.setText("0");
        }

        // Labels helper
        for (Label l : new Label[]{l1,l2,l3,l4,l5,l6,l7}) {
            l.setFont(Font.font("System", FontWeight.BOLD, 12));
        }

        // Agregar al grid
        gridTrabajo.add(l1, 0, 0);
        gridTrabajo.add(promptCantidadPiezas, 1, 0);

        gridTrabajo.add(l2, 0, 1);
        gridTrabajo.add(promptPiezasImpresion, 1, 1);

        gridTrabajo.add(l3, 0, 2);
        gridTrabajo.add(tiempoImpresionBox, 1, 2);

        gridTrabajo.add(l4, 0, 3);
        gridTrabajo.add(promptPesoTanda, 1, 3);

        gridTrabajo.add(l5, 0, 4);
        gridTrabajo.add(promptTiempoLaminado, 1, 4);

        gridTrabajo.add(l6, 0, 5);
        gridTrabajo.add(promptTiempoPreparacion, 1, 5);

        gridTrabajo.add(l7, 0, 6);
        gridTrabajo.add(promptTiempoRetiro, 1, 6);

        // ================= OPCIONAL =================
        tituloOpcional.setFont(Font.font("System", FontWeight.BOLD, 20));
        tituloOpcional.setMaxWidth(Double.MAX_VALUE);
        tituloOpcional.setAlignment(Pos.CENTER);
        tituloOpcional.setStyle(
            "-fx-background-color: #A5B2C9;" +
            "-fx-padding: 10;"
        );        
        
        GridPane gridOpcional = new GridPane();
        gridOpcional.setHgap(38);
        gridOpcional.setVgap(10);
        gridOpcional.getColumnConstraints().addAll(col1, col2);

        promptHorasProcesado = new TextField("0");
        promptMinutosProcesado = new TextField("0");

        promptHorasProcesado.setPrefWidth(60);
        promptMinutosProcesado.setPrefWidth(60);
        
        promptHorasProcesado.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        promptMinutosProcesado.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");

        promptHorasProcesado.setAlignment(Pos.CENTER);
        promptMinutosProcesado.setAlignment(Pos.CENTER);

        HBox postProcesadoBox = new HBox(10,
           new Label(t("Horas:", "Hours:")), promptHorasProcesado,
            new Label(t("Min:", "Min:")), promptMinutosProcesado
        );
        
        promptHorasProcesado.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\d*)?")) {
                promptHorasProcesado.setText(oldValue);
            }
        });        
                
        promptMinutosProcesado.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\d*)?")) {
                promptMinutosProcesado.setText(oldValue);
            }
        });        

        // Valor modelado
        promptValorModelado = new TextField("0");
        promptValorModelado.setMaxWidth(Double.MAX_VALUE);
        promptValorModelado.setAlignment(Pos.CENTER_RIGHT);
        promptValorModelado.setStyle("-fx-background-radius: 4; -fx-border-radius: 4; -fx-border-color: black;");
        promptValorModelado.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                promptValorModelado.setText(oldValue);
            }
        });   
        // Labels opcional
        l8.setFont(Font.font("System", FontWeight.BOLD, 12));
        l9.setFont(Font.font("System", FontWeight.BOLD, 12));

        // Agregar opcional
        gridOpcional.add(l8, 0, 0);
        gridOpcional.add(postProcesadoBox, 1, 0);

        gridOpcional.add(l9, 0, 1);
        gridOpcional.add(promptValorModelado, 1, 1);

        // ================= CONTENEDOR FINAL =================
        VBox contenidoTrabajo = new VBox(15,gridTrabajo);
        VBox contenidoOpcional = new VBox(15,gridOpcional);
        contenidoTrabajo.setPadding(new Insets(10));
        contenidoOpcional.setPadding(new Insets(10));

        VBox bDatosTrabajo = new VBox(0, tituloDatosTrabajo, contenidoTrabajo, tituloOpcional,contenidoOpcional);
        bDatosTrabajo.setStyle(
            "-fx-border-width: 1.5px;" +
            "-fx-background-color: #BECDE8;" +
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-border-color: #A5B2C9;"
        );
        bDatosTrabajo.setPrefWidth(490);
        bDatosTrabajo.setEffect(ds);

        // IMPRESIONES NECESARIAS ----------------------------------------------------------------------------------------------------------------------------------------
        
        
        // ================= OPCIONAL =================
        tituloResumen.setFont(Font.font("System", FontWeight.BOLD, 20));
        tituloResumen.setMaxWidth(Double.MAX_VALUE);
        tituloResumen.setAlignment(Pos.CENTER);
        tituloResumen.setStyle(
            "-fx-background-color: #A5B2C9;" +
            "-fx-padding: 10;"
        );        
        
        GridPane gridDatos = new GridPane();
        gridDatos.setHgap(20);
        gridDatos.setVgap(5);

        // ================= FILA 0 =================
        txtImpresionesNecesarias.setFont(Font.font("System", FontWeight.BOLD, 13));

        rptaImpresionesNecesarias = new Label("1");

        gridDatos.add(txtImpresionesNecesarias, 0, 0);
        gridDatos.add(rptaImpresionesNecesarias, 1, 0);

        // ================= FILA 1 =================
        txtHorasTotalesMaquina.setFont(Font.font("System", FontWeight.BOLD, 13));

        rptaHorasTotales = new Label("0");

        gridDatos.add(txtHorasTotalesMaquina, 0, 1);
        gridDatos.add(rptaHorasTotales, 1, 1);

        // ================= FILA 2 =================
        txtMaterialTotal.setFont(Font.font("System", FontWeight.BOLD, 13));

        rptaMaterialTotal = new Label("0");

        gridDatos.add(txtMaterialTotal, 0, 2);
        gridDatos.add(rptaMaterialTotal, 1, 2);


        // ================= FILA 3 =================
        txtCostoMaterial.setFont(Font.font("System", FontWeight.BOLD, 13));

        rptaCostoMaterial = new Label("0");

        gridDatos.add(txtCostoMaterial, 0, 3);
        gridDatos.add(rptaCostoMaterial, 1, 3);

        // ================= FILA 4 =================
        txtCostoOperativo2.setFont(Font.font("System", FontWeight.BOLD, 13));

        rptaCostoOperativo = new Label("0");

        gridDatos.add(txtCostoOperativo2, 0, 4);
        gridDatos.add(rptaCostoOperativo, 1, 4);

        // ================= FILA 5 =================
        txtGananciaMaquina.setFont(Font.font("System", FontWeight.BOLD, 13));

        rptaGananciaMaquina = new Label("0");

        gridDatos.add(txtGananciaMaquina, 0, 5);
        gridDatos.add(rptaGananciaMaquina, 1, 5);

        // ================= FILA 6 =================
        txtTiempoOperadorTotal.setFont(Font.font("System", FontWeight.BOLD, 13));

        rptaTiempoOperador = new Label("0,0");

        gridDatos.add(txtTiempoOperadorTotal, 0, 6);
        gridDatos.add(rptaTiempoOperador, 1, 6);

        // ================= FILA 7 =================
        txtCostoOperador.setFont(Font.font("System", FontWeight.BOLD, 13));

        rptaCostoOperador = new Label("0");

        gridDatos.add(txtCostoOperador, 0, 7);
        gridDatos.add(rptaCostoOperador, 1, 7);

        // ================= CONTENEDOR FINAL =================
        
        VBox contenidoResumen = new VBox(15,gridDatos);
        contenidoResumen.setPadding(new Insets(10));
        
        VBox bDatos = new VBox(0, tituloResumen, contenidoResumen);

        
        bDatos.setStyle(
            "-fx-border-width: 1.5px;" +
            "-fx-background-color: #BECDE8;" +
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-border-color: #A5B2C9;"
        );
        bDatos.setPrefWidth(400);
        bDatos.setEffect(ds);

        ColumnConstraints col3 = new ColumnConstraints();
        ColumnConstraints col4 = new ColumnConstraints();
        col3.setHgrow(Priority.ALWAYS);

        gridDatos.getColumnConstraints().addAll(col3, col4);

        // Alinear valores
        GridPane.setHalignment(rptaImpresionesNecesarias, HPos.RIGHT);
        GridPane.setHalignment(rptaHorasTotales, HPos.RIGHT);
        GridPane.setHalignment(rptaMaterialTotal, HPos.RIGHT);
        GridPane.setHalignment(rptaCostoMaterial, HPos.RIGHT);
        GridPane.setHalignment(rptaCostoOperativo, HPos.RIGHT);
        GridPane.setHalignment(rptaGananciaMaquina, HPos.RIGHT);
        GridPane.setHalignment(rptaTiempoOperador, HPos.RIGHT);
        GridPane.setHalignment(rptaCostoOperador, HPos.RIGHT);

        // PRECIO FINAL ----------------------------------------------------------------------------------------------------------------------------------------
        
        
        // ================= OPCIONAL =================
        tituloPrecios.setFont(Font.font("System", FontWeight.BOLD, 20));
        tituloPrecios.setMaxWidth(Double.MAX_VALUE);
        tituloPrecios.setAlignment(Pos.CENTER);
        tituloPrecios.setStyle(
            "-fx-background-color: #A5B2C9;" +
            "-fx-padding: 10;"
        );        
        
        GridPane gridPrecios = new GridPane();
        gridPrecios.setHgap(35);
        gridPrecios.setVgap(5);
        gridPrecios.getColumnConstraints().addAll(col3, col4);

        txtPrecioCosto2.setFont(Font.font("System", FontWeight.BOLD, 12));

        promptPrecioCosto = new TextField();
        promptPrecioCosto.setEditable(false);
        promptPrecioCosto.setStyle(
            "-fx-background-color: #F17B7B;" +
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-border-color: black;"
        ); 
        promptPrecioCosto.setAlignment(Pos.CENTER_RIGHT);
        promptPrecioCosto.setFont(Font.font("System", FontWeight.BOLD, 12));

        gridPrecios.add(txtPrecioCosto2, 0, 0);
        gridPrecios.add(promptPrecioCosto, 1, 0);        

        txtPrecioMinimo.setFont(Font.font("System", FontWeight.BOLD, 12));

        promptPrecioMinimo = new TextField();
        promptPrecioMinimo.setEditable(false);
        promptPrecioMinimo.setStyle(
            "-fx-background-color: #68A667;" +
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-border-color: black;"
        );
        promptPrecioMinimo.setAlignment(Pos.CENTER_RIGHT);
        promptPrecioMinimo.setFont(Font.font("System", FontWeight.BOLD, 12));
    
        gridPrecios.add(txtPrecioMinimo, 0, 1);  // Solo agregas el Label aquí
        gridPrecios.add(promptPrecioMinimo, 1, 1);  // Y el TextField en la segunda columna

        txtValorAgregado.setFont(Font.font("System", FontWeight.BOLD, 12));

        promptValorAgregado = new TextField("0%");
        promptValorAgregado.setStyle("-fx-background-radius: 4;" + "-fx-border-radius: 4;" + "-fx-border-color: black;");          
        promptValorAgregado.setAlignment(Pos.CENTER_RIGHT);
        promptValorAgregado.setFont(Font.font("System", FontWeight.NORMAL, 13));
        promptValorAgregado.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?%?")) {
                promptValorAgregado.setText(oldValue);
            }
        }); 
        gridPrecios.add(txtValorAgregado, 0, 2);
        gridPrecios.add(promptValorAgregado, 1, 2);   

        txtPrecioFinal.setFont(Font.font("System", FontWeight.BOLD, 20));

        promptPrecioFinal = new TextField();
        promptPrecioFinal.setEditable(false);
        promptPrecioFinal.setStyle(
            "-fx-background-color: #68A667;" +
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-border-color: black;"
        );
        promptPrecioFinal.setAlignment(Pos.CENTER_RIGHT);
        promptPrecioFinal.setFont(Font.font("System", FontWeight.BOLD, 15));

        gridPrecios.add(txtPrecioFinal, 0, 3);
        gridPrecios.add(promptPrecioFinal, 1, 3);   

        
        VBox contenidoPrecios = new VBox(15,gridPrecios);
        contenidoPrecios.setPadding(new Insets(10));
        
        VBox bPrecios = new VBox(0, tituloPrecios, contenidoPrecios);
        
        
        bPrecios.setStyle(
            "-fx-border-width: 1.5px;" +
            "-fx-background-color: #BECDE8;" +
            "-fx-background-radius: 4;" +
            "-fx-border-radius: 4;" +
            "-fx-border-color: #A5B2C9;"
        );
        bPrecios.setPrefWidth(490);
        gridPrecios.setVgap(20);
        bPrecios.setEffect(ds);
        
        // ----------------------------------------------------------------------------------------------------------------------------------------
      
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

        btnContinuar.setOnMouseEntered(e ->
            btnContinuar.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#8fdb6a, #6db94c);" +
                "-fx-border-color: #3f7c28;" +
                "-fx-text-fill: white;"
        ));

        btnContinuar.setOnMouseExited(e ->
            btnContinuar.setStyle(baseStyle +
                "-fx-background-color: linear-gradient(#7ecb5a, #5da93c);" +
                "-fx-border-color: #4a8d30;" +
                "-fx-text-fill: white;"
        ));

        agregarAutoUpdate(promptPrecioOperario);
        agregarAutoUpdate(promptPrecioEnergia);
        agregarAutoUpdate(promptPrecioFilamento);
        agregarAutoUpdate(promptCantidadPiezas);
        agregarAutoUpdate(promptPiezasImpresion);
        agregarAutoUpdate(promptHorasImpresion);
        agregarAutoUpdate(promptMinutosImpresion);
        agregarAutoUpdate(promptPesoTanda);
        agregarAutoUpdate(promptTiempoLaminado);
        agregarAutoUpdate(promptTiempoPreparacion);
        agregarAutoUpdate(promptTiempoRetiro);
        agregarAutoUpdate(promptHorasProcesado);
        agregarAutoUpdate(promptMinutosProcesado);
        agregarAutoUpdate(promptValorModelado);
        agregarAutoUpdate(promptValorAgregado);
        
        cbImpresoras.valueProperty().addListener((obs, oldVal, newVal) -> {
            actualizarCalculo();
        });

        cbMaterial.valueProperty().addListener((obs, oldVal, newVal) -> {
            actualizarCalculo();
        });


        // ================= EVENTOS =================
        btnContinuar.setOnAction(e -> {
            
            if (cbImpresoras.getValue() == null) {
                abrirAviso(
                    t("ADVERTENCIA", "WARNING"),
                    t("Selecciona una impresora.", "Select a printer.")
                );
                return;
            }

            if (cbMaterial.getValue() == null) {
                abrirAviso(
                    t("ADVERTENCIA", "WARNING"),
                    t("Selecciona un material.", "Select a material.")
                );
                return;
            }
            
            actualizarCalculo();

            // Si el precio final no se pudo calcular, no continuar
            if (promptPrecioFinal.getText().isEmpty() || precioFinal==0) {
                abrirAviso(
                    t("ADVERTENCIA", "WARNING"),
                    t("Resultado no válido.", "Invalid result.")
                );
                return;
            }

            ItemFactura item = new ItemFactura(
                t("Nuevo producto", "New item"),
                Integer.parseInt(promptCantidadPiezas.getText()),
                precioFinal
            );
            if (facturaView != null) {
                // Viene desde factura → agregar item
                facturaView.agregarItem(item);
            } else {
                // Viene desde menú → crear factura nueva
                FacturaView nuevaFactura = new FacturaView(stage);
                nuevaFactura.agregarItem(item);
                abrirVentana(new Stage(),nuevaFactura.getRoot(),t("Finalizar presupuesto", "Finish budget"),950,975);
            }
            ((Stage) btnContinuar.getScene().getWindow()).close();
        });

        btnRetroceder.setOnAction(e -> {
            ((Stage) btnRetroceder.getScene().getWindow()).close();
        });

        // ================= LAYOUT =================
        HBox form1 = new HBox(15, bConfiguracion, bDatosTrabajo);
        HBox form2 = new HBox(15, bDatos, bPrecios);
        VBox form = new VBox(18, form1, form2);
        HBox form3 = new HBox(15, btnRetroceder, btnContinuar);

        form1.setAlignment(Pos.CENTER);
        form2.setAlignment(Pos.CENTER);
        form3.setAlignment(Pos.CENTER);

        root.getChildren().addAll(form, form3);

    }
    
    public VBox getRoot() { return root; }
    
    private String formatMoney(double valor) {
        return formatoMoneda.format(valor);
    }
    
    private void actualizarCalculo() {
        try {
           
            // ================= SELECCIÓN =================
            Impresora impresoraSeleccionada = cbImpresoras.getValue();
            Material materialSeleccionado = cbMaterial.getValue();
            
            if (impresoraSeleccionada == null || materialSeleccionado == null){
                return;
            }

            
            // =================
            
            if (promptPrecioOperario.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: PRECIO OPERARIO ($/h)", "Missing value: OPERATOR COST ($/h)"));
                return;
            }

            if (promptPrecioEnergia.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: PRECIO ENERGIA ($/kWh)", "Missing value: ELECTRICITY COST ($/kWh)"));
                return;
            }            
            
            if (promptPrecioFilamento.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: PRECIO FILAMENTO ($/kg)", "Missing value: FILAMENT PRICE ($/kg)"));
                return;
            }            
            
            if (promptCantidadPiezas.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: CANTIDAD DE PIEZAS", "Missing value: TOTAL PARTS"));
                return;
            }            
            
            if (Double.parseDouble(promptCantidadPiezas.getText())<Double.parseDouble(promptPiezasImpresion.getText())){
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("La cantidad total de piezas no puede ser inferior a las piezas por impresión.",
                                            "Total parts cannot be less than parts per print."));
                promptCantidadPiezas.setText("1");
                promptPiezasImpresion.setText("1");
                return;
            }
            
            
            if (promptPiezasImpresion.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: PIEZAS POR IMPRESIÓN (tanda)", "Missing value: PARTS PER PRINT"));
                return;
            }            
            
            if (promptHorasImpresion.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: TIEMPO DE IMPRESIÓN (tanda): Horas", "Missing value: PRINT TIME PER BATCH: Hours"));
                return;
            }            
            
            if (promptMinutosImpresion.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: TIEMPO DE IMPRESIÓN (tanda): Minutos", "Missing value: PRINT TIME PER BATCH: Minutes"));
                return;
            }            
            
            if (promptPesoTanda.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: PESO DE TANDA (g)", "Missing value: BATCH WEIGHT (g)"));
                return;
            }            
            
            if (promptTiempoLaminado.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: TIEMPO DE LAMINADO (min)", "Missing value: SLICING TIME (min)"));
                return;
            }            
            
            if (promptTiempoPreparacion.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: TIEMPO DE PREPARACIÓN (min)", "Missing value: PREPARATION TIME (min)"));
                return;
            }            
            
            if (promptTiempoRetiro.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: TIEMPO DE RETIRO (min)", "Missing value: REMOVAL TIME (min)"));
                return;
            }            
            
            if (promptHorasProcesado.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: POSTPROCESADO UNITARIO: Horas", "Missing value: UNIT POST-PROCESSING: Hours"));
                return;
            }            
            
            if (promptMinutosProcesado.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: POSTPROCESADO UNITARIO: Minutos", "Missing value: UNIT POST-PROCESSING: Minutes"));
                return;
            }            
            
            if (promptValorModelado.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: VALOR MODELADO 3D O ARCHIVO ($)", "Missing value: 3D MODEL / FILE VALUE ($)"));
                return;
            }            
            
            if (promptValorAgregado.getText().trim().isEmpty()) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Falta valor en: VALOR AGREGADO / URGENCIA", "Missing value: ADDED VALUE / URGENCY"));
                return;
            }            
            
            // ================= 
               
            if (Double.parseDouble(promptCantidadPiezas.getText())==0) {
                abrirAviso("ERROR", t("La cantidad total de piezas no puede ser igual a 0.", "Total parts cannot be 0."));
                return;
            }    
            
            if (Double.parseDouble(promptPiezasImpresion.getText())==0) {
                abrirAviso("ERROR", t("Las piezas por impresión no puede ser igual a 0.", "Parts per print cannot be 0."));
                return;
            }         
            
            //=================
            
             if (Double.parseDouble(promptMinutosImpresion.getText()) >= 60) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("Los minutos tienen que ser entre 0 y 59.", "Minutes must be between 0 and 59."));
                promptMinutosImpresion.setText("0");
                return;
            }      
             
             if (Double.parseDouble(promptMinutosProcesado.getText()) >= 60) {
                abrirAviso(t("ADVERTENCIA", "WARNING"), t("No se pudo continuar con el programa.", "Unable to proceed. Please verify the inputs."));
                promptMinutosProcesado.setText("0");
                return;
            }                   
             
            // ================= DATOS BASE =================
            double precioOperario = Double.parseDouble(promptPrecioOperario.getText());
            double energia = Double.parseDouble(promptPrecioEnergia.getText());
            double precioFilamento = Double.parseDouble(promptPrecioFilamento.getText());
            
            Data.setPrecioOperario(promptPrecioOperario.getText());
            Data.setPrecioEnergia(promptPrecioEnergia.getText());
            Data.setPrecioFilamento(promptPrecioFilamento.getText());

            double tasaFallos = Double.parseDouble(impresoraSeleccionada.getTasaFallos());
            double horasUso = Double.parseDouble(impresoraSeleccionada.getHorasUso());
            double consumo = Double.parseDouble(impresoraSeleccionada.getConsumo());

            double factorGama = impresoraSeleccionada.getGamaObjetivo().getFactor();
            double factorConsumo = materialSeleccionado.getFactorConsumo();

            // ================= COSTOS MÁQUINA =================
            double valorAmortizacion = Data.calcularValorAmortizacion(impresoraSeleccionada);
            double cAmortizacion = Data.calcularAmortizacion(valorAmortizacion, horasUso);
            double cMantenimiento = Data.calcularMantenimiento(
                    Double.parseDouble(impresoraSeleccionada.getValor()),
                    materialSeleccionado.getFactorDesgaste()
            );
            double cElectricidad = Data.calcularElectricidad(consumo, energia, factorConsumo);
            double calculoSupervision = Data.calcularSupervision(precioOperario, tasaFallos);

            double costoOperativo = Data.calcularCostoOperativo(
                    cAmortizacion, cMantenimiento, cElectricidad, calculoSupervision
            );

            double ganancia = Data.calcularGananciaMaquina(
                    cMantenimiento, cElectricidad, calculoSupervision, factorGama
            );
            

            // ================= OUTPUT CONFIG =================
            promptAmortizacion.setText(impresoraSeleccionada.getAmortizacion().getDuracion(Data.administrador.getIdioma()));
            promptTasaFallos.setText(impresoraSeleccionada.getTasaFallos() + "%");
            promptCostoOperativo.setText(String.format("$%.2f", costoOperativo));
            promptGanancia.setText(formatMoney(ganancia));

            // ================= DATOS TRABAJO =================
            double cantidad = Double.parseDouble(promptCantidadPiezas.getText());
            double piezasPorTanda = Double.parseDouble(promptPiezasImpresion.getText());

            double impresionesNecesarias = Math.ceil(cantidad / piezasPorTanda);
            rptaImpresionesNecesarias.setText(String.format("%.0f", impresionesNecesarias));

            // ================= TIEMPOS =================
            double horasImpresion = Double.parseDouble(promptHorasImpresion.getText());
            double minutosImpresion = Double.parseDouble(promptMinutosImpresion.getText()) / 60;

            double tiempoPreparacion = Double.parseDouble(promptTiempoPreparacion.getText()) / 60;
            double tiempoRetiro = Double.parseDouble(promptTiempoRetiro.getText()) / 60;

            double horasTotales =
                    (impresionesNecesarias * horasImpresion) +
                    (impresionesNecesarias * minutosImpresion) +
                    (impresionesNecesarias * tiempoPreparacion) +
                    (impresionesNecesarias * tiempoRetiro);

            rptaHorasTotales.setText(String.format("%.1f", horasTotales));

            // ================= MATERIAL =================
            double pesoTanda = Double.parseDouble(promptPesoTanda.getText());
            double tandas = Math.ceil(cantidad / piezasPorTanda);
            double piezasReales = tandas * piezasPorTanda;

            double pesoPorPieza = pesoTanda / piezasPorTanda;

            double materialTotal = pesoPorPieza * piezasReales * (1 + tasaFallos / 100);




            rptaMaterialTotal.setText(String.format("%.0f", materialTotal));

            double costoMaterial = (materialTotal / 1000) * precioFilamento;
            rptaCostoMaterial.setText(formatMoney(costoMaterial));

            // ================= COSTOS MÁQUINA TOTAL =================
            double costoOperativoMaquina =
                    costoOperativo * horasTotales * (1 + (tasaFallos / 100));

            rptaCostoOperativo.setText(formatMoney(costoOperativoMaquina));

            double gananciaMaquina =
                    horasTotales * ganancia * (1 + (tasaFallos / 100));

            rptaGananciaMaquina.setText(formatMoney(gananciaMaquina));

            // ================= OPERADOR =================
            double tiempoLaminado = Double.parseDouble(promptTiempoLaminado.getText());
            double minutosPost = Double.parseDouble(promptMinutosProcesado.getText()) / 60;
            double horasPost = Double.parseDouble(promptHorasProcesado.getText());

            double tiempoOperadorTotal =
                    (tiempoLaminado / 60) +
                    (tiempoPreparacion * impresionesNecesarias) +
                    (tiempoRetiro * impresionesNecesarias) +
                    (minutosPost * cantidad) +
                    (horasPost * cantidad);

            rptaTiempoOperador.setText(String.format("%.1f", tiempoOperadorTotal));

            double costoOperador = tiempoOperadorTotal * precioOperario;
            rptaCostoOperador.setText(formatMoney(costoOperador));

            // ================= PRECIOS =================
            double costoUnitario = (costoOperativoMaquina + costoMaterial) / ((cantidad/piezasPorTanda)*piezasPorTanda);
            promptPrecioCosto.setText(formatMoney(costoUnitario));

            double valorModelado = Double.parseDouble(promptValorModelado.getText());

            double precioMinimo = (costoMaterial + costoOperativoMaquina + gananciaMaquina + costoOperador + valorModelado) / ((cantidad/piezasPorTanda)*piezasPorTanda); //valorModelado AGREGADO, preguntar

            promptPrecioMinimo.setText(formatMoney(precioMinimo));

            double valorAgregado = Double.parseDouble(
                    promptValorAgregado.getText().replace("%", "")
            );
            promptValorAgregado.setText(promptValorAgregado.getText() + "%");

            precioFinal = precioMinimo * (1 + valorAgregado / 100);
            promptPrecioFinal.setText(formatMoney(precioFinal));

        } catch (Exception e) {
            abrirAviso(t("ERROR", "ERROR"), t("No se pudo continuar con el programa.", "Could not continue the program."));
        }
    }

    private void agregarAutoUpdate(TextField tf) {
        tf.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                actualizarCalculo();
                guardarPreciosBase();
            }
        });
    }

    private void guardarPreciosBase() {
        try {
            if (!promptPrecioOperario.getText().isEmpty() &&
                !promptPrecioEnergia.getText().isEmpty() &&
                !promptPrecioFilamento.getText().isEmpty()) {

                Data.setPrecioOperario(promptPrecioOperario.getText());
                Data.setPrecioEnergia(promptPrecioEnergia.getText());
                Data.setPrecioFilamento(promptPrecioFilamento.getText());

                Data.guardar();
            }
        } catch (Exception e) {
            System.out.println(t("Error guardando precios base", "Error saving base prices"));
        }
    }
    
    private String t(String es, String en) {
        return Data.administrador.getIdioma().equals("EN") ? en : es;
    }

}
    

