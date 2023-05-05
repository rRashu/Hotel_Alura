package com.hotel.alura.hotelalurafx;

import Accesos_Datos.reservasDAO;
import ConexionBD.conexionBD;
import Modelo.Huesped;
import Modelo.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;

public class IngresoReserva {
    public DatePicker salida;
    public ComboBox<String> pago;
    public DatePicker entrada;
    public Label llblHuesped;
    public Button guardar;
    Huesped seleccionado;
    Connection con;
    public void initialize(){

}
    public void buscarHusesped() {

    }

    public void click() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader (getClass ().getResource ("ver_huespedes.fxml"));
            Parent root1 = fxmlLoader.load ();
            Stage stage = new Stage ();
            stage.initModality (Modality.APPLICATION_MODAL);
            stage.setTitle ("HWI - Login");
            stage.setResizable (false);
            stage.setScene (new Scene (root1));
            stage.showAndWait ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        VerHuespedes verHuespedes = new VerHuespedes();
        seleccionado = verHuespedes.getHuespedSeleccionado();
        llblHuesped.setText("("+seleccionado.getId()+") -> "+seleccionado.getNombre()+" "+seleccionado.getApellido());
        System.out.println("dio click" + seleccionado);
    }

    public void guardarenBD(ActionEvent actionEvent) {
        Reserva reserva = new Reserva();
        reserva.setDia_salida(Date.valueOf(salida.getValue()));
        reserva.setDia_entrada(Date.valueOf(entrada.getValue()));
        reserva.setId_huesped(seleccionado.getId());
        reserva.setValor_cancelar(120);
        reserva.setForma_pago("tarjeta");
        con =  new conexionBD().recuperaConexion();
        reservasDAO rdao = new reservasDAO(con);
        rdao.guardar(reserva);
    }
}
