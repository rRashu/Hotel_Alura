package com.hotel.alura.hotelalurafx;

import Accesos_Datos.huespedesDAO;
import ConexionBD.conexionBD;
import Modelo.Huesped;
import Util.Util;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

import java.sql.Connection;
import java.sql.Date;

public class
IngresoHuesped {
    public Button guardarHuesped;
    public DatePicker fecha_nacimiento;
    public TextField txtnombre;
    public TextField txtapellido;
    public TextField txttelefono;

Connection con;
    public ComboBox<String> combo_naciolidad;

    public void initialize() {
        //llenar combo-box
       Util.llenar_combo(combo_naciolidad);
     }

    public void Acceder() {
        if (txtnombre.getText().isBlank() || txtapellido.getText().isBlank() || txttelefono.getText().isBlank() || fecha_nacimiento.getValue() == null) {
            Notifications noti = Notifications.create();
            Util.notificacion(noti,"Campos Vacios");
        } else {
            Huesped hues = new Huesped();
            hues.setNombre(txtnombre.getText());
            hues.setApellido(txtapellido.getText());
            hues.setFecha_nacimiento(Date.valueOf(fecha_nacimiento.getValue()));
            hues.setNacionalidad(combo_naciolidad.getValue());
            hues.setTelefono(txttelefono.getText());
            con = new conexionBD().recuperaConexion();
            huespedesDAO hdao = new huespedesDAO(con);
            hdao.guardar(hues);
            Notifications noti = Notifications.create();
            Util.notificacionbien(noti);

        }
    }
}
