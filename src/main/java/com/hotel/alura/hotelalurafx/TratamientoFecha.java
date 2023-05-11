package com.hotel.alura.hotelalurafx;

import Accesos_Datos.reservasDAO;
import ConexionBD.conexionBD;
import Modelo.Reserva;
import Util.Util;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.sql.Connection;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class TratamientoFecha {
    public DatePicker fecheentrada;
    public DatePicker fechasalida;
    public ComboBox<String> formapago;
    public Label valor;
    static public int idseleccionado;
    public Button guardar;
    public Button eliminar;
    Connection con;
    Reserva busquedaid;
    private Timeline timeline;
    private int countdown = 5;
    long dias;

    public void initialize() {

        con = new conexionBD().recuperaConexion();
        reservasDAO rdao = new reservasDAO(con);
        busquedaid = rdao.busquedaid(idseleccionado);
        fecheentrada.setValue(busquedaid.getDia_entrada().toLocalDate());
        fechasalida.setValue(busquedaid.getDia_salida().toLocalDate());
        formapago.setValue(busquedaid.getForma_pago());
        valor.setText(String.valueOf(busquedaid.getValor()));

        guardar.setOnAction(event -> {
            Stage stage = (Stage) fecheentrada.getScene().getWindow();
            Reserva reservamodificado = new Reserva();
            reservamodificado.setId(busquedaid.getId());
            reservamodificado.setDia_entrada(Date.valueOf(fecheentrada.getValue()));
            reservamodificado.setDia_salida(Date.valueOf(fechasalida.getValue()));
            reservamodificado.setValor(dias * 8.55);
            reservamodificado.setForma_pago(formapago.getValue());
            reservamodificado.setId_huesped(busquedaid.getId_huesped());
            reservasDAO rdao2 = new reservasDAO(con);
            rdao2.modificar(reservamodificado);
            initialize();
            Notifications noti = Notifications.create();
            Util.notificacionbien(noti);
            stage.close();
        });
        fechasalida.valueProperty().addListener((obs, oldVal, newVal) -> {
            dias = ChronoUnit.DAYS.between(fecheentrada.getValue(), fechasalida.getValue());
        valor.setText(String.valueOf(dias*8.55));
        });
        eliminar.setOnAction(event -> {
            Stage stage = (Stage) fecheentrada.getScene().getWindow();
            ButtonType yesButton = new ButtonType("Sí", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "¿Desea continuar?", yesButton, noButton);
            alert.setTitle("Eliminar Reserva");
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
                    reservasDAO hdao = new reservasDAO(con);
                    hdao.eliminar(idseleccionado);
                    stage.close();
                }
                timeline.stop();
            });

        });

    }

    public void idSeleccionado(int id) {
        idseleccionado = id;
    }
}
