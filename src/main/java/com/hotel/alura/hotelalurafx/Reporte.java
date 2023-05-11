package com.hotel.alura.hotelalurafx;

import Accesos_Datos.huespedesDAO;
import ConexionBD.conexionBD;
import Modelo.Huesped;
import Modelo.Reserva;
import Util.Util;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.sql.Connection;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class Reporte {
    public TextField nombre;
    public TextField apellido;
    public TextField telefono;
    public ComboBox<String> nacionalidad;
    public DatePicker fecha_nacimiento;
    public TableView<Reserva> tabla_cliente;
    public Pane panelHuesoed;
    public TextField fechanacimiento;
    public TextField nacionalidadtxt;
    public Button modificar;
    public Button guardar;
    public Button eliminar;
    Connection con;
    public static int idseleccionado = 0;
    ObservableList<Reserva> dato;
    List<Reserva> lista;
    private Timeline timeline;
    private int countdown = 5;
    public Reserva resultadolista;

    public void initialize() {
        guardar.setVisible(false);
        fecha_nacimiento.setVisible(false);
        nacionalidad.setVisible(false);
        nombre.setEditable(false);
        apellido.setEditable(false);
        telefono.setEditable(false);
        fechanacimiento.setEditable(false);
        nacionalidadtxt.setEditable(false);
        nacionalidadtxt.setVisible(true);
        fechanacimiento.setVisible(true);
        Util.llenar_combo(nacionalidad);
        con = new conexionBD().recuperaConexion();
        huespedesDAO rdao = new huespedesDAO(con);
        for (Huesped huesped : rdao.listarConReserva(idseleccionado)) {
            nombre.setText(huesped.getNombre());
            apellido.setText(huesped.getApellido());
            telefono.setText(huesped.getTelefono());
            nacionalidadtxt.setText(huesped.getNacionalidad());
            nacionalidad.setValue(huesped.getNacionalidad());
            fecha_nacimiento.setValue(huesped.getFecha_nacimiento().toLocalDate());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada = huesped.getFecha_nacimiento().toLocalDate().format(formatter);
            fechanacimiento.setText(fechaFormateada);
            for (Reserva reserva : huesped.getReservas()) {
                if (reserva.getId_huesped() != 0) {
                    Util.llenarTabla(tabla_cliente);
                    lista = huesped.getReservas();
                    dato = FXCollections.observableArrayList();
                    dato.addAll(lista);
                    tabla_cliente.setItems(dato);
                }else{
                    tabla_cliente.getItems().clear();
                }
            }
        }

        modificar.setOnAction(event -> {
            fecha_nacimiento.setVisible(true);
            nacionalidad.setVisible(true);
            nombre.setEditable(true);
            apellido.setEditable(true);
            telefono.setEditable(true);
            fechanacimiento.setVisible(false);
            nacionalidadtxt.setVisible(false);
            guardar.setVisible(true);

        });
        eliminar.setOnAction(event -> {
            Stage stage = (Stage) tabla_cliente.getScene().getWindow();

            ButtonType yesButton = new ButtonType("Sí", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, """
                    ¿Desea continuar?
                          Las Reservas Que pertenezcan a este\s
                          Huesped Tambien Seran Eliminadas""", yesButton, noButton);

            alert.setTitle("Eliminar Huesped");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    Objects.requireNonNull(getClass().getResource("..\\..\\..\\..\\css\\alerta.css")).toExternalForm());
            dialogPane.getStyleClass().add("myDialog");

            alert.initStyle(StageStyle.UTILITY);
            alert.setHeaderText("Espere 5 segundos");
            alert.getDialogPane().lookupButton(yesButton).setDisable(true);
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), mouseevent -> {
                countdown--;
                if (countdown <= 0) {
                    alert.getDialogPane().lookupButton(yesButton).setDisable(false);
                }
                alert.setHeaderText("Espere " + countdown + " segundos");
            }));
            timeline.setCycleCount(countdown);
            timeline.play();
            alert.showAndWait().ifPresent(response -> {
                if (response == yesButton) {
                    huespedesDAO hdao = new huespedesDAO(con);
                    hdao.eliminar(idseleccionado);
                    stage.close();
                }
                timeline.stop();
            });
        });
        guardar.setOnAction(event -> {
            for (Huesped modificar : rdao.listarConReserva(idseleccionado)) {
                Huesped huespedmodificado = new Huesped();
                huespedmodificado.setId(modificar.getId());
                huespedmodificado.setNombre(nombre.getText());
                huespedmodificado.setApellido(apellido.getText());
                huespedmodificado.setTelefono(telefono.getText());
                huespedmodificado.setNacionalidad(nacionalidad.getValue());
                huespedmodificado.setFecha_nacimiento(Date.valueOf(fecha_nacimiento.getValue()));
                huespedesDAO hdao = new huespedesDAO(con);
                hdao.modificar(huespedmodificado);
                initialize();
                Notifications noti = Notifications.create();
                Util.notificacionbien(noti);

            }
        });

        tabla_cliente.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 1) {
                if (tabla_cliente.getSelectionModel().getSelectedItem() != null) {
                    TratamientoFecha a = new TratamientoFecha();
                    a.idSeleccionado(tabla_cliente.getSelectionModel().getSelectedItem().getId());
                }
            }
            if (mouseEvent.getClickCount() == 2) {
                Object selectedItem = tabla_cliente.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    resultadolista = tabla_cliente.getSelectionModel().getSelectedItem();
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tratamiento_fecha.fxml"));
                        Parent root1 = fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.initModality(Modality.APPLICATION_MODAL);
                        stage1.setTitle("Fechas");
                        stage1.setResizable(false);
                        stage1.setScene(new Scene(root1));
                        stage1.setOnHidden(event -> initialize());
                        stage1.showAndWait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void idSeleccionado(int idHuesped) {
        idseleccionado = idHuesped;
    }

}