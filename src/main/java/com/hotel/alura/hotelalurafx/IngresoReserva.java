package com.hotel.alura.hotelalurafx;

import Modelo.Huesped;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IngresoReserva {
    public DatePicker salida;
    public ComboBox<String> pago;
    public DatePicker entrada;
    public Label llblHuesped;


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
        Huesped seleccionado = verHuespedes.getHuespedSeleccionado();
        llblHuesped.setText("("+seleccionado.getId()+") -> "+seleccionado.getNombre()+" "+seleccionado.getApellido());
        System.out.println("dio click" + seleccionado);
    }
}
