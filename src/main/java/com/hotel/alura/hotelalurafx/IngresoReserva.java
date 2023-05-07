package com.hotel.alura.hotelalurafx;

import Accesos_Datos.reservasDAO;
import ConexionBD.conexionBD;
import Modelo.Huesped;
import Modelo.Reserva;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class IngresoReserva {
    public DatePicker salida;
    public ComboBox<String> pago;
    public DatePicker entrada;
    public Label llblHuesped;
    public Button guardar;
    public Label valor;
    Huesped seleccionado;
    Connection con;

    public void click() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader (getClass ().getResource ("ver_huespedes.fxml"));
            Parent root1 = fxmlLoader.load ();
            Stage stage = new Stage ();
            stage.initModality (Modality.APPLICATION_MODAL);
            stage.setTitle ("Ver Huespedes");
            stage.setResizable (false);
            stage.setScene (new Scene (root1));
            stage.showAndWait ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        VerHuespedes verHuespedes = new VerHuespedes();
        seleccionado = verHuespedes.getHuespedSeleccionado();
        llblHuesped.setText("("+seleccionado.getId()+") -> "+seleccionado.getNombre()+" "+seleccionado.getApellido());
    }

    public void guardarenBD() {
        if (seleccionado == null || pago.getValue() == null) {
            Notifications noti = Notifications.create();
            noti.title("Datos Incorrectos");
            IngresoHuesped.notificacion(noti);
        } else {
            Reserva reserva = new Reserva();
            reserva.setDia_salida(Date.valueOf(salida.getValue()));
            reserva.setDia_entrada(Date.valueOf(entrada.getValue()));
            reserva.setId_huesped(seleccionado.getId());
            reserva.setValor_cancelar(120);
            reserva.setForma_pago("tarjeta");
            con = new conexionBD().recuperaConexion();
            reservasDAO rdao = new reservasDAO(con);
            rdao.guardar(reserva);
            Notifications noti = Notifications.create();
            IngresoHuesped.notificacionbien(noti);
        }
    }

    public void initialize() {
        entrada.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });
        entrada.setValue(LocalDate.now());
        salida.setValue(LocalDate.now());
        entrada.valueProperty().addListener((observable, oldValue, newValue) -> {
            salida.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.isBefore(newValue));
                }
            });
        });
        salida.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (entrada.getValue() != null && newValue != null) {
                long dias = ChronoUnit.DAYS.between(entrada.getValue(), newValue);
                valor.setText("$ " + (dias * 8.55));
            }
        });
    }
}
