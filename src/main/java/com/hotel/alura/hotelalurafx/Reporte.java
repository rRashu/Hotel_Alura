package com.hotel.alura.hotelalurafx;

import Accesos_Datos.huespedesDAO;
import ConexionBD.conexionBD;
import Modelo.Huesped;
import Modelo.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import Util.Util;

import java.sql.Connection;
import java.util.List;

public class Reporte {
    public TextField nombre;
    public TextField apellido;
    public TextField telefono;
    public ComboBox<String> nacionalidad;
    public DatePicker fecha_nacimiento;
    public TableView tabla_cliente;
    Connection con;
    public static int idseleccionado;


    public void initialize() {
        con = new conexionBD().recuperaConexion();
        huespedesDAO rdao = new huespedesDAO(con);
        for (Huesped huesped : rdao.listarConReserva(idseleccionado)) {
            nombre.setText(huesped.getNombre());
            apellido.setText(huesped.getApellido());
            telefono.setText(huesped.getTelefono());
            nacionalidad.setValue(huesped.getNacionalidad());
            fecha_nacimiento.setValue(huesped.getFecha_nacimiento().toLocalDate());
            for (Reserva reserva : huesped.getReservas()) {
                if (reserva.getId_huesped() != 0) {
                    Util.llenarTabla(tabla_cliente);
                    List<Reserva> lista = huesped.getReservas();
                    ObservableList<Reserva> dato = FXCollections.observableArrayList();
                    dato.addAll(lista);
                    tabla_cliente.setItems(dato);
                }
            }
        }

    }

    public void idSeleccionado(int idHuesped) {
        idseleccionado = idHuesped;
    }

}