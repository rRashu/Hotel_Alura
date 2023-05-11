package com.hotel.alura.hotelalurafx;

import Accesos_Datos.reservasDAO;
import ConexionBD.conexionBD;
import Modelo.Huesped;
import Modelo.Reserva;
import Util.Util;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.sql.Connection;
import java.sql.Date;
import java.text.DecimalFormat;
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
    private double dias= 8.55;
    DecimalFormat df = new DecimalFormat("#.##");

    public void click() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader (getClass ().getResource ("ver_huespedes.fxml"));
            Parent root1 = fxmlLoader.load ();
            Stage stage = new Stage ();
            stage.initModality (Modality.APPLICATION_MODAL);
            stage.setTitle ("Ver Huespedes");
            VerHuespedes a = fxmlLoader.getController();
            a.cerrar(true);
            stage.setResizable (false);
            stage.setScene (new Scene (root1));
            stage.showAndWait ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        VerHuespedes verHuespedes = new VerHuespedes();
        seleccionado = verHuespedes.getHuespedSeleccionado();
        if (seleccionado!= null){
        llblHuesped.setText("("+seleccionado.getId()+") -> "+seleccionado.getNombre()+" "+seleccionado.getApellido());
    }}

    public void guardarenBD() {
        if (seleccionado == null || pago.getValue() == null) {
            Notifications noti = Notifications.create();
            Util.notificacion(noti,"Datos Incorrectos");
        } else {
            Reserva reserva = new Reserva();
            reserva.setDia_salida(Date.valueOf(salida.getValue()));
            reserva.setDia_entrada(Date.valueOf(entrada.getValue()));
            reserva.setId_huesped(seleccionado.getId());
            reserva.setValor(Double.parseDouble(df.format(dias * 8.55)));
            reserva.setForma_pago(pago.getValue());
            con = new conexionBD().recuperaConexion();
            reservasDAO rdao = new reservasDAO(con);
            rdao.guardar(reserva);
            Notifications noti = Notifications.create();
            Util.notificacionbien(noti);
        }
    }

    public void initialize() {
        Util.llenar_formapago(pago);
        entrada.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.isBefore(today));
            }
        });
        entrada.setValue(LocalDate.now());
        salida.setValue(LocalDate.now());
        entrada.valueProperty().addListener((observable, oldValue, newValue) -> salida.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(newValue));
            }
        }));
        salida.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (entrada.getValue() != null && newValue != null) {
                dias = ChronoUnit.DAYS.between(entrada.getValue(), newValue);
                valor.setText("$ " + (dias * 8.55));
            }
        });
    }
}
